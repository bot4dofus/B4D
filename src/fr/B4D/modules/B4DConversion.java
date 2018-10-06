package fr.B4D.modules;

import java.awt.Point;

import fr.B4D.classes.Bot;
import fr.B4D.classes.PointD;
import fr.B4D.classes.PointF;

public final class B4DConversion {
	
	private static final double Precision = 10000;
    private static final double CaseWidthF = 0.0346588315;
    private static final double CaseHeightF  = 0.0216600199;


    //       / X                        |                _______                            |            _______
    //      /           DOFUS           |               |        Y          SCREEN          |           |        1          PROPORTIONNAL    
    //      \           COORDINATE      |               |                   COORDINATE      |           |                   COORDINATE
    //       \ Y                        |               | X                                 |           | 1

      /******************************/
     /* CONVERSION > Proportionnel */
    /******************************/

    /** Ecran > Proportionnel **/
    public static PointF pointToPointF(Point point){
    	double X = (point.getX() - Bot.MyConfiguration.gameFrame.getX()) / Bot.MyConfiguration.gameFrame.getWidth();
    	double Y = (point.getY() - Bot.MyConfiguration.gameFrame.getY()) / Bot.MyConfiguration.gameFrame.getHeight();
        return new PointF(Math.round(X*Precision)/Precision, Math.round(Y*Precision)/Precision);
    }

    /** Dofus > Proportionnel **/
    public static PointF pointDToPointF(PointD point){
    	double X = (point.getX() + point.getY()) * CaseWidthF;
    	double Y = (-point.getX() + point.getY()) * CaseHeightF;
        return new PointF(Math.round(X*Precision)/Precision, Math.round(Y*Precision)/Precision);
    }

      /**********************/
     /* CONVERSION > Ecran */
    /**********************/
    
    /** Proportionnel > Ecran **/
    public static Point pointFToPoint(PointF point) {
    	double X = (point.getX() * Bot.MyConfiguration.gameFrame.getWidth()) + Bot.MyConfiguration.gameFrame.getX();
    	double Y = (point.getY() * Bot.MyConfiguration.gameFrame.getHeight()) + Bot.MyConfiguration.gameFrame.getY();
        return new Point((int)X, (int)Y);
    }

    /** Dofus > Ecran **/
    public static Point pointDToPoint(PointD point) {
    	return pointFToPoint(pointDToPointF(point));
    }

    /**********************/
   /* CONVERSION > Dofus */
  /**********************/

    /** Ecran > Dofus **/
    public static PointD pointToPointD(Point point) {
    	return pointFToPointD(pointToPointF(point));
    }

    /** Proportionnel > Dofus **/
    public static PointD pointFToPointD(PointF point) {
    	double X = (point.getX() / CaseWidthF - point.getY() / CaseHeightF) / 2;
    	double Y = (point.getX() / CaseWidthF + point.getY() / CaseHeightF) / 2;
    	return new PointD((int)X, (int)Y);
    }    
}
