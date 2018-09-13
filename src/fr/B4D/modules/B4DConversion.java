package fr.B4D.modules;

import java.awt.Point;
import fr.B4D.classes.Bot;
import fr.B4D.classes.PointD;
import fr.B4D.classes.PointF;

public final class B4DConversion {
	
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
        return new PointF((point.getX() - Bot.MyConfiguration.gameFrame.getX()) / Bot.MyConfiguration.gameFrame.getWidth(), (point.getY() - Bot.MyConfiguration.gameFrame.getY()) / Bot.MyConfiguration.gameFrame.getHeight());
    }

    /** Dofus > Proportionnel **/
    public static PointF pointDToPointF(PointD point){
        return new PointF((point.getX() + point.getY()) * CaseWidthF, (-point.getX() + point.getY()) * CaseHeightF);
    }

      /**********************/
     /* CONVERSION > Ecran */
    /**********************/
    
    /** Proportionnel > Ecran **/
    public static Point pointFToPoint(PointF point) {
        return new Point((int)((point.getX() * Bot.MyConfiguration.gameFrame.getWidth()) + Bot.MyConfiguration.gameFrame.getX()), (int)((point.getY() * Bot.MyConfiguration.gameFrame.getHeight()) + Bot.MyConfiguration.gameFrame.getY()));
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
    	return new PointD((int)((point.getX() / CaseWidthF - point.getY() / CaseHeightF) / 2), (int)((point.getX() / CaseWidthF + point.getY() / CaseHeightF) / 2));
    }    
}
