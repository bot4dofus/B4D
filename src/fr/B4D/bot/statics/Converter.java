package fr.B4D.bot.statics;

import java.awt.Point;

import fr.B4D.bot.Configuration;
import fr.B4D.utils.PointD;
import fr.B4D.utils.PointF;

public final class Converter {
	
	private static final double Precision = 10000;
    private static final double CaseWidthF = 0.0346588315;
    private static final double CaseHeightF  = 0.0216600199;

    private Configuration configuration;
    
	/*************/
	/** BUILDER **/
	/*************/
    
    public Converter(Configuration configuration) {
    	this.configuration = configuration;
    }

	/*************/
	/** SETTERS **/
	/*************/
    
    public void setConfiguration(Configuration configuration) {
  		this.configuration = configuration;
  	}
    
    //       / X                        |                _______                            |            _______
    //      /           DOFUS           |               |        Y          SCREEN          |           |        1          PROPORTIONNAL    
    //      \           COORDINATE      |               |                   COORDINATE      |           |                   COORDINATE
    //       \ Y                        |               | X                                 |           | 1

      /********************************/
     /** CONVERSION > Proportionnel **/
    /********************************/

    /** Ecran > Proportionnel **/
    public PointF pointToPointF(Point point){
    	double X = (point.getX() - configuration.getGameFrame().getX()) / configuration.getGameFrame().getWidth();
    	double Y = (point.getY() - configuration.getGameFrame().getY()) / configuration.getGameFrame().getHeight();
        return new PointF(Math.round(X*Precision)/Precision, Math.round(Y*Precision)/Precision);
    }

	/** Dofus > Proportionnel **/
    public PointF pointDToPointF(PointD point){
    	double X = (point.getX() + point.getY()) * CaseWidthF;
    	double Y = (-point.getX() + point.getY()) * CaseHeightF;
        return new PointF(Math.round(X*Precision)/Precision, Math.round(Y*Precision)/Precision);
    }

      /************************/
     /** CONVERSION > Ecran **/
    /************************/
    
    /** Proportionnel > Ecran **/
    public Point pointFToPoint(PointF point) {
    	double X = (point.getX() * configuration.getGameFrame().getWidth()) + configuration.getGameFrame().getX();
    	double Y = (point.getY() * configuration.getGameFrame().getHeight()) + configuration.getGameFrame().getY();
        return new Point((int)Math.round(X), (int)Math.round(Y));
    }

    /** Dofus > Ecran **/
    public Point pointDToPoint(PointD point) {
    	return pointFToPoint(pointDToPointF(point));
    }

    /************************/
   /** CONVERSION > Dofus **/
  /************************/

    /** Ecran > Dofus **/
    public PointD pointToPointD(Point point) {
    	return pointFToPointD(pointToPointF(point));
    }

    /** Proportionnel > Dofus **/
    public PointD pointFToPointD(PointF point) {
    	double X = (point.getX() / CaseWidthF - point.getY() / CaseHeightF) / 2;
    	double Y = (point.getX() / CaseWidthF + point.getY() / CaseHeightF) / 2;
    	return new PointD((int)Math.round(X), (int)Math.round(Y));
    }    
}
