package fr.B4D.bot.statics;

import java.awt.Point;

import fr.B4D.bot.Configuration;
import fr.B4D.utils.PointD;
import fr.B4D.utils.PointF;

/**
 * The {@code Converter} class is used to convert a location from a system to another.<br><br>
 * 
 * Simple coordinates :<br>
 * 
 *	&nbsp;_______<br>
 *	|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Y<br>
 *	|<br>
 *	|&nbsp;&nbsp;X<br><br>
 *
 * Relative coordinates :<br>
 * 
 *	&nbsp;_______<br>
 *	|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1<br>
 *	|<br>
 *	|&nbsp;&nbsp;1<br><br>
 * 
 * Draughtboard coordinates :<br>
 * 
 *	&nbsp;&nbsp;/&nbsp;X<br>
 *	/<br>
 *	\<br>
 *  &nbsp;&nbsp;\&nbsp;Y<br><br>
 * 
 * @author Lucas
 *
 */
public final class Converter {
	
	private static final double Precision = 10000;
    private static final double CaseWidthF = 0.0346588315;
    private static final double CaseHeightF  = 0.0216600199;

    private Configuration configuration;
    
	/**
	 * Constructor of the {@code Converter} class.
     * @param configuration - Current configuration of the game.
     */
    public Converter(Configuration configuration) {
    	this.configuration = configuration;
    }

    /**
     * Converts a point from simple coordinates to relative coordinates.
     * @param point - Point in simple coordinates.
     * @return Point in relative coordinates.
     */
    public PointF toPointF(Point point){
    	double X = (point.getX() - configuration.getGameFrame().getX()) / configuration.getGameFrame().getWidth();
    	double Y = (point.getY() - configuration.getGameFrame().getY()) / configuration.getGameFrame().getHeight();
        return new PointF(Math.round(X*Precision)/Precision, Math.round(Y*Precision)/Precision);
    }

    /**
     * Converts a point from draughtboard coordinates to relative coordinates.
     * @param point - Point in draughtboard coordinates.
     * @return Point in relative coordinates.
     */
    public PointF toPointF(PointD point){
    	double X = (point.getX() + point.getY()) * CaseWidthF;
    	double Y = (-point.getX() + point.getY()) * CaseHeightF;
        return new PointF(Math.round(X*Precision)/Precision, Math.round(Y*Precision)/Precision);
    }

    /**
     * Converts a point from relative coordinates to simple coordinates.
     * @param point - Point in relative coordinates.
     * @return Point in simple coordinates.
     */
    public Point toPoint(PointF point) {
    	double X = (point.getX() * configuration.getGameFrame().getWidth()) + configuration.getGameFrame().getX();
    	double Y = (point.getY() * configuration.getGameFrame().getHeight()) + configuration.getGameFrame().getY();
        return new Point((int)Math.round(X), (int)Math.round(Y));
    }

    /**
     * Converts a point from draughtboard coordinates to simple coordinates.
     * @param point - Point in draughtboard coordinates.
     * @return Point in simple coordinates.
     */
    public Point toPoint(PointD point) {
    	return toPoint(toPointF(point));
    }

    /**
     * Converts a point from simple coordinates to draughtboard coordinates.
     * @param point - Point in simple coordinates.
     * @return Point in draughtboard coordinates.
     */
    public PointD toPointD(Point point) {
    	return toPointD(toPointF(point));
    }

    /**
     * Converts a point from relative coordinates to draughtboard coordinates.
     * @param point - Point in relative coordinates.
     * @return Point in draughtboard coordinates.
     */
    public PointD toPointD(PointF point) {
    	double X = (point.getX() / CaseWidthF - point.getY() / CaseHeightF) / 2;
    	double Y = (point.getX() / CaseWidthF + point.getY() / CaseHeightF) / 2;
    	return new PointD((int)Math.round(X), (int)Math.round(Y));
    }    
}
