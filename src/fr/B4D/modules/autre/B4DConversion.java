package fr.B4D.modules.autre;

import java.awt.Point;
import java.awt.geom.Point2D.Double;

import fr.B4D.classes.Bot;
import fr.B4D.classes.PointD;

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
    public static Double PointToDouble(Point MyPoint){
        return new Double((MyPoint.getX() - Bot.MaConfiguration.zoneDeJeu.getX()) / Bot.MaConfiguration.zoneDeJeu.getWidth(), (MyPoint.getY() - Bot.MaConfiguration.zoneDeJeu.getY()) / Bot.MaConfiguration.zoneDeJeu.getHeight());
    }

    /** Dofus > Proportionnel **/
    public static Double PointDToDouble(PointD MyPoint){
        return new Double((MyPoint.getX() + MyPoint.getY()) * CaseWidthF, (-MyPoint.getX() + MyPoint.getY()) * CaseHeightF);
    }

      /**********************/
     /* CONVERSION > Ecran */
    /**********************/
    
    /** Proportionnel > Ecran **/
    public static Point DoubleToPoint(Double MyPointF) {
        return new Point((int)((MyPointF.getX() * Bot.MaConfiguration.zoneDeJeu.getWidth()) + Bot.MaConfiguration.zoneDeJeu.getX()), (int)((MyPointF.getY() * Bot.MaConfiguration.zoneDeJeu.getHeight()) + Bot.MaConfiguration.zoneDeJeu.getY()));
    }

    /** Dofus > Ecran **/
    public static Point PointDToPoint(PointD MyPoint) {
    	return DoubleToPoint(PointDToDouble(MyPoint));
    }

    /**********************/
   /* CONVERSION > Dofus */
  /**********************/

    /** Ecran > Dofus **/
    public static PointD PointToPointD(Point MyPoint) {
    	return DoubleToPointD(PointToDouble(MyPoint));
    }

    /** Proportionnel > Dofus **/
    public static PointD DoubleToPointD(Double MyPoint) {
    	return new PointD((int)((MyPoint.getX() / CaseWidthF - MyPoint.getY() / CaseHeightF) / 2), (int)((MyPoint.getX() / CaseWidthF + MyPoint.getY() / CaseHeightF) / 2));
    }    
}
