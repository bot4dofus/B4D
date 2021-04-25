package fr.B4D.bot.statics;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Configuration;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.socket.event.ChangeMapEvent;
import fr.B4D.socket.store.EventStore;
import fr.B4D.utils.PointD;
import fr.B4D.utils.PointF;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * The {@code Screen} class is used to access to the screen methods.
 * 
 * @author Lucas
 *
 */
public final class Screen {
	
	/**
	 * Configuration of the screen.
	 */
	private Configuration configuration;
    
	/**
	 * Constructor of the {@code Screen} class.
     * @param configuration - Configuration of the game.
     */
    public Screen(Configuration configuration) {
    	this.configuration = configuration;
    }
	
	/**
	 * Returns the color of the pixel at a given location.
	 * @param point - Location of the pixel on the screen in simple coordinates.
	 * @return Color of the pixel.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public Color getPixelColor(Point point) throws B4DException {
		try {
			Robot robot = new Robot();
			return robot.getPixelColor(point.x, point.y);
		} catch(AWTException e) {
			throw new B4DException(e);
		}
	}
	
	/**
	 * Returns the color of the pixel at a given location.
	 * @param point - Location of the pixel on the screen in relative coordinates.
	 * @return Color of the pixel.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public Color getPixelColor(PointF point) throws B4DException {
		return getPixelColor(B4D.converter.toPoint(point));
	}
	
	/**
	 * Returns the color of the pixel at a given location.
	 * @param point - Location of the pixel on the screen in draughtboard coordinates.
	 * @return Color of the pixel.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public Color getPixelColor(PointD point) throws B4DException {
		return getPixelColor(B4D.converter.toPoint(point));
	}
	
	/**
	 * Performs a pixel research for a given area.
	 * @param topLeftHandCorner - Top left hand corner of the research area in relative coordinates.
	 * @param bottomRightHandCorner - Bottom left hand corner of the research area in relative coordinates.
	 * @param min - Minimum color.
	 * @param max - Maximum color.
	 * @return Location of the first pixel matching the color criteria in relative coordinates. {@code null} if no pixel match the research criteria.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public PointF searchPixel(PointF topLeftHandCorner, PointF bottomRightHandCorner, Color min, Color max) throws B4DException {
		Point Point1 = B4D.converter.toPoint(topLeftHandCorner);
		Point Point2 = B4D.converter.toPoint(bottomRightHandCorner);
		
		Color color;
		Point point;
		
		for(int y=Point1.y;y<=Point2.y;y++) {
			for(int x=Point1.x;x<=Point2.x;x++) {
				point = new Point(x, y);
				color = getPixelColor(point);
				if(isBetween(color, min, max))
					return B4D.converter.toPointF(point);
			}
		}
		return null;
	}
	
	/**
	 * Performs a pixel research for a given area.
	 * @param topLeftHandCorner - Top left hand corner of the research area in relative coordinates.
	 * @param bottomRightHandCorner - Bottom left hand corner of the research area in relative coordinates.
	 * @param min - Minimum color.
	 * @param max - Maximum color.
	 * @return List of pixels matching the color criteria in relative coordinates. {@code null} if no pixel match the research criteria.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public ArrayList<PointF> searchPixels(PointF topLeftHandCorner, PointF bottomRightHandCorner, Color min, Color max) throws B4DException {
		
		Point Point1 = B4D.converter.toPoint(topLeftHandCorner);
		Point Point2 = B4D.converter.toPoint(bottomRightHandCorner);
		ArrayList<PointF> points = new ArrayList<PointF>();
		
		Color color;
		Point point;
		
		for(int y=Point1.y;y<=Point2.y;y++) {
			for(int x=Point1.x;x<=Point2.x;x++) {
				point = new Point(x, y);
				color = getPixelColor(point);
				
				if(isBetween(color, min, max))
					points.add(B4D.converter.toPointF(point));
			}
		}
		if (points.isEmpty())
			points = null;
		return points;
	}
	
	/**
	 * Performs a screenshot for a given area.
	 * @param rectangle - Screenshot area in simple coordinates.
	 * @return Screenshot of the rectangle area.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public BufferedImage takeSreenshot(Rectangle rectangle) throws B4DException {
		try {
			Robot robot = new Robot();
			return robot.createScreenCapture(rectangle);
		} catch (AWTException e) {
			throw new B4DException(e);
		}
	}
	
	/**
	 * Performs a screenshot for the game frame area.
	 * @param rectangle - Screenshot area in simple coordinates.
	 * @return Screenshot of the game frame area.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public BufferedImage takeSreenshot() throws B4DException {
		return takeSreenshot(configuration.getGameFrame());
	}
	
	/**
	 * Performs an Optical Character Recognition on a given area.
	 * @param rectangle - OCR area in simple coordinates.
	 * @return String recognized on screen, {@code null} if nothing have been found.
	 * @throws B4DException if cannot perform the OCR operation.
	 */
	public String OCR(Rectangle rectangle) throws B4DException {
		try {		
			BufferedImage image = takeSreenshot(rectangle);
			Tesseract tessInst = new Tesseract();
			tessInst.setLanguage("fra");
			String out = tessInst.doOCR(image);
			return out.replaceAll("\n", " ");
		} catch (TesseractException e) {
			throw new B4DException(e);
		}
	}
	
	/**
	 * Performs an Optical Character Recognition on a given area.
	 * @param topLeftHandCorner - Top left hand corner of the OCR area in simple coordinates.
	 * @param bottomRightHandCorner - Bottom left hand corner of the OCR area in simple coordinates.
	 * @return String recognized on screen, {@code null} if nothing have been found.
	 * @throws B4DException if cannot perform the OCR operation.
	 */
	public String OCR(Point topLeftHandCorner, Point bottomRightHandCorner) throws B4DException {
		return OCR(new Rectangle(topLeftHandCorner.x,  topLeftHandCorner.y, bottomRightHandCorner.x - topLeftHandCorner.x, bottomRightHandCorner.y - topLeftHandCorner.y));
	}

	/**
	 * Performs an Optical Character Recognition on a given area.
	 * @param topLeftHandCorner - Top left hand corner of the OCR area in relative coordinates.
	 * @param bottomRightHandCorner - Bottom left hand corner of the OCR area in relative coordinates.
	 * @return String recognized on screen, {@code null} if nothing have been found.
	 * @throws B4DException if cannot perform the OCR operation.
	 */
	public String OCR(PointF topLeftHandCorner, PointF bottomRightHandCorner) throws B4DException {
		return OCR(B4D.converter.toPoint(topLeftHandCorner), B4D.converter.toPoint(bottomRightHandCorner));
	}

	/**
	 * Returns the selected string by doing a double click and doing Ctrl+C.
	 * @param position - Location of the string in simple coordinates.
	 * @return Selected string on screen, {@code null} if nothing have been found.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public String getSelection(Point position) throws StopProgramException, CancelProgramException, B4DException {
		try {
			Robot robot = new Robot();
			B4D.mouse.doubleLeftClick(position, false, 100);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_C);
			robot.keyRelease(KeyEvent.VK_C);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			B4D.wait.sleep(1000);
			return B4D.keyboard.getClipboard();
		}
		catch(AWTException e) {
			throw new B4DException(e);
		}
	}
	
	/**
	 * Returns the selected string by doing a double click and doing Ctrl+C.
	 * @param position - Location of the string in relative coordinates.
	 * @return Selected string on screen, {@code null} if nothing have been found.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public String getSelection(PointF position) throws StopProgramException, CancelProgramException, B4DException {
		return getSelection(B4D.converter.toPoint(position));
	}
	
	/**
	 * Checks whether a color is in a given interval.
	 * @param couleur - Color of the pixel to check.
	 * @param min - Minimal color.
	 * @param max - Maximal color.
	 * @return {@code true} if the color is in the interval, {@code false} otherwise.
	 */
	public boolean isBetween(Color couleur, Color min, Color max) {
		return (min.getRed() <= couleur.getRed() && couleur.getRed() <= max.getRed() && min.getGreen() <= couleur.getGreen() && couleur.getGreen() <= max.getGreen() && min.getBlue() <= couleur.getBlue() && couleur.getBlue() <= max.getBlue());
	}
	
	/**
	 * Waits for the map to change.
	 * @param timeOut - Time in ms before timeout.
	 * @return {@code true} true if the map has changed, {@code false} if timeout.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 */
	public boolean waitForMap(int timeOut) throws StopProgramException, CancelProgramException {
		//return waitForChangingPixel(B4D.converter.toPointF(configuration.getMinimap()), timeOut) != null;
		ChangeMapEvent event = EventStore.getInstance().waitForEvent(ChangeMapEvent.class, timeOut);
		B4D.wait.sleep(2000);
		return event != null;
	}
	
	/**
	 * Wait for a string to be displayed on screen.
	 * @param rectangle - OCR area in simple coordinates.
	 * @param regex - Regular expression that the string must contain.
	 * @param timeOut - Time in ms before timeout.
	 * @return String recognized on screen, {@code null} if nothing have been found.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if cannot perform the OCR operation.
	 */
	public String waitForOCR(Rectangle rectangle, String regex, int timeOut) throws StopProgramException, CancelProgramException, B4DException {
		String text;
		do {
			text = B4D.screen.OCR(rectangle);
			B4D.wait.sleep(100);
		}while(!text.contains(regex));
		return text;
	}

	/**
	 * Wait for a string to be displayed on screen.
	 * @param topLeftHandCorner - Top left hand corner of the OCR area in simple coordinates.
	 * @param bottomRightHandCorner - Bottom left hand corner of the OCR area in simple coordinates.
	 * @param regex - Regular expression that the string must contain.
	 * @param timeOut - Time in ms before timeout.
	 * @return String recognized on screen, {@code null} if nothing have been found.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if cannot perform the OCR operation.
	 */
	public String waitForOCR(Point topLeftHandCorner, Point bottomRightHandCorner, String regex, int timeOut) throws StopProgramException, CancelProgramException, B4DException {
		return waitForOCR(new Rectangle(topLeftHandCorner.x,  topLeftHandCorner.y, bottomRightHandCorner.x - topLeftHandCorner.x, bottomRightHandCorner.y - topLeftHandCorner.y), regex, timeOut);
	}

	/**
	 * Wait for a string to be displayed on screen.
	 * @param topLeftHandCorner - Top left hand corner of the OCR area in relative coordinates.
	 * @param bottomRightHandCorner - Bottom left hand corner of the OCR area in relative coordinates.
	 * @param regex - Regular expression that the string must contain.
	 * @param timeOut - Time in ms before timeout.
	 * @return String recognized on screen, {@code null} if nothing have been found.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if cannot perform the OCR operation.
	 */
	public String waitForOCR(PointF topLeftHandCorner, PointF bottomRightHandCorner, String regex, int timeOut) throws StopProgramException, CancelProgramException, B4DException {
		return waitForOCR(B4D.converter.toPoint(topLeftHandCorner), B4D.converter.toPoint(bottomRightHandCorner), regex, timeOut);
	}
	
	/**
	 * Waits for a pixel to change color.
	 * @param point - Location of the pixel in simple coordinates.
	 * @param timeOut - Time in ms before timeout.
	 * @return New color of the pixel, {@code null} if timeout.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public Color waitForChangingPixel(Point point, int timeOut) throws StopProgramException, CancelProgramException, B4DException {
		Color newColor, color = B4D.screen.getPixelColor(point);
		do {
			newColor = B4D.screen.getPixelColor(point);
			B4D.wait.sleep(100);
		}while(color.equals(newColor));		
		return newColor;
	}

	/**
	 * Waits for a pixel to change color.
	 * @param point - Location of the pixel in relative coordinates.
	 * @param timeOut - Time in ms before timeout.
	 * @return New color of the pixel, {@code null} if timeout.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public Color waitForChangingPixel(PointF point, int timeOut) throws StopProgramException, CancelProgramException, B4DException {
		return waitForChangingPixel(B4D.converter.toPoint(point), timeOut);
	}
	
	/**
	 * Waits for a pixel to be in a given interval of color.
	 * @param point - Location of the pixel in simple coordinates.
	 * @param min - Minimum color.
	 * @param max - Maximum color.
	 * @param timeOut - Time in ms before timeout.
	 * @return New color of the pixel, {@code null} if timeout.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public Color waitForColor(Point point, Color min, Color max, int timeOut) throws StopProgramException, CancelProgramException, B4DException {
		Color color;
		do {
			color = B4D.screen.getPixelColor(point);
			B4D.wait.sleep(100);
		}while(!B4D.screen.isBetween(color, min, max));
		return color;
	}
	
	/**
	 * Waits for a pixel to be in a given interval of color.
	 * @param point - Location of the pixel in relative coordinates.
	 * @param min - Minimum color.
	 * @param max - Maximum color.
	 * @param timeOut - Time in ms before timeout.
	 * @return New color of the pixel, {@code null} if timeout.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public Color waitForColor(PointF point, Color min, Color max, int timeOut) throws StopProgramException, CancelProgramException, B4DException {
		return waitForColor(B4D.converter.toPoint(point), min, max, timeOut);
	}
}
