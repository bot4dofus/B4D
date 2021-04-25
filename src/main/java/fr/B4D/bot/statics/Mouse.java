package fr.B4D.bot.statics;

import java.awt.AWTException;

import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Configuration;
import fr.B4D.gui.JFrame_GetPoint;
import fr.B4D.gui.JFrame_GetPointImage;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointD;
import fr.B4D.utils.PointF;

/**
 * The {@code Mouse} class is used to access to the mouse methods.
 * 
 * @author Lucas
 *
 */
public final class Mouse {

	/**
	 * Configuration of the screen.
	 */
	private Configuration configuration;
	
	/**
	 * Constructor of the {@code Mouse} class.
     * @param configuration - Configuration of the game.
     */
    public Mouse(Configuration configuration) {
    	this.configuration = configuration;
    }

	/**
	 * Displays a window to retrieve the coordinates of an element on screen in simple coordinates.
	 * @param text - Text to display.
	 * @param mouseAdapter - Action to perform on click.
	 */
	public void getPoint(String text, MouseAdapter mouseAdapter) {
		JFrame_GetPoint window = new JFrame_GetPoint(text, mouseAdapter);
		window.setVisible(true);
	}
	
	/**
	 * Displays a window to retrieve the coordinates of an element on screen in simple coordinates.
	 * @param text - Text to display
	 * @param image - Image to display.
	 * @param mouseAdapter - Action to perform on click.
	 */
	public void getPoint(String text, ImageIcon image, MouseAdapter mouseAdapter) {
		JFrame_GetPointImage window = new JFrame_GetPointImage(text, image, mouseAdapter);
		window.setVisible(true);
	}

	/**
	 * Displays a window to retrieve two coordinates of elements on screen in simple coordinates.
	 * @param text1 - Text to display for the first click.
	 * @param mouseAdapter1 - Action to perform on first click.
	 * @param text2 - Text to display for the second click.
	 * @param mouseAdapter2 - Action to perform on second click.
	 */
	public void getPoints(String text1, MouseAdapter mouseAdapter1, String text2, MouseAdapter mouseAdapter2) {
		JFrame_GetPoint window1 = new JFrame_GetPoint(text1, mouseAdapter1);
		JFrame_GetPoint window2 = new JFrame_GetPoint(text2, mouseAdapter2);
		window1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				window2.setVisible(true);
			}
		});
		window1.setVisible(true);
	}
	
	/**
	 * Displays a window to retrieve two coordinates of elements on screen in simple coordinates.
	 * @param text1 - Text to display for the first click.
	 * @param image1 - Image to display for the second click.
	 * @param mouseAdapter1 - Action to perform on first click.
	 * @param text2 - Text to display for the second click.
	 * @param image2 - Image to display for the second click.
	 * @param mouseAdapter2 - Action to perform on second click.
	 */
	public void getPoints(String text1, ImageIcon image1, MouseAdapter mouseAdapter1, String text2, ImageIcon image2, MouseAdapter mouseAdapter2) {
		JFrame_GetPointImage window1 = new JFrame_GetPointImage(text1, image1, mouseAdapter1);
		JFrame_GetPointImage window2 = new JFrame_GetPointImage(text2, image2, mouseAdapter2);
		window1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				window2.setVisible(true);
			}
		});
		window1.setVisible(true);
	}
	
	/**
	 * Places the cursor at a given location.
	 * @param position - Location of the cursor in simple coordinates.
	 * @param millis - Time to wait after the click in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void place(Point position, int millis) throws StopProgramException, CancelProgramException, B4DException {
		try {
			Robot robot = new Robot();
			robot.mouseMove((int)position.getX(),(int)position.getY());
			B4D.wait.sleep(millis);
		} catch(AWTException e) {
			throw new B4DException(e);
		}
	}
	
	/**
	 * Places the cursor at a given location and wait for 0 ms after it.
	 * This is the same as {@code place(position, 0)}.
	 * @param position - Location of the cursor in simple coordinates.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void place(Point position) throws StopProgramException, CancelProgramException, B4DException {
		place(position, 0);
	}
	/**
	 * Places the cursor at a given location.
	 * @param position - Location of the cursor in relative coordinates.
	 * @param millis - Time to wait after the click in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void place(PointF position, int millis) throws StopProgramException, CancelProgramException, B4DException {
		place(B4D.converter.toPoint(position), millis);
	}

	/**
	 * Places the cursor at a given location and wait for 0 ms after it.
	 * This is the same as {@code place(position, 0)}.
	 * @param position - Location of the cursor in relative coordinates.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void place(PointF position) throws StopProgramException, CancelProgramException, B4DException {
		place(B4D.converter.toPoint(position), 0);
	}

	/**
	 * Places the cursor at a given location and wait for 0 ms after it.
	 * @param position - Location of the cursor in draughtboard coordinates.
	 * @param millis - Time to wait after the click in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void place(PointD position, int millis) throws StopProgramException, CancelProgramException, B4DException {
		place(B4D.converter.toPoint(position), millis);
	}

	/**
	 * Places the cursor at a given location and wait for 0 ms after it.
	 * This is the same as {@code place(position, 0)}.
	 * @param position - Location of the cursor in draughtboard coordinates.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void place(PointD position) throws StopProgramException, CancelProgramException, B4DException {
		place(B4D.converter.toPoint(position), 0);
	}

	/**
	 * Performs a right click.
	 * @param position - Location of the mouse on the screen in simple coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @param millis - Time to wait after the click in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void rightClick(Point position, boolean maj, int millis) throws StopProgramException, CancelProgramException, B4DException {
		try {
			Robot robot = new Robot();
			robot.mouseMove((int)position.getX(),(int)position.getY());
				
			if(maj)
				robot.keyPress(KeyEvent.VK_SHIFT);
			robot.mousePress(KeyEvent.BUTTON3_DOWN_MASK);
			robot.mouseRelease(KeyEvent.BUTTON3_DOWN_MASK);
			if(maj)	
				robot.keyRelease(KeyEvent.VK_SHIFT);
	
			B4D.wait.sleep(millis);
		} catch(AWTException e) {
			throw new B4DException(e);
		}
	}
	
	/**
	 * Performs a right click and wait for 1000 ms after the click.
	 * This is the same as {@code rightClick(position, maj, 1000)}.
	 * @param position - Location of the mouse on the screen in simple coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void rightClick(Point position, boolean maj) throws StopProgramException, CancelProgramException, B4DException {
		rightClick(position, maj, 1000);
	}
	
	/**
	 * Performs a right click.
	 * @param position - Location of the mouse on the screen in relative coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @param millis - Time to wait after the click in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void rightClick(PointF position, boolean maj, int millis) throws StopProgramException, CancelProgramException, B4DException {
		rightClick(B4D.converter.toPoint(position), maj, millis);
	}
	
	/**
	 * Performs a right click and wait for 1000 ms after the click.
	 * This is the same as {@code rightClick(position, maj, 1000)}.
	 * @param position - Location of the mouse on the screen in relative coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void rightClick(PointF position, boolean maj) throws StopProgramException, CancelProgramException, B4DException {
		rightClick(B4D.converter.toPoint(position), maj, 1000);
	}
	
	/**
	 * Performs a right click.
	 * @param position - Location of the mouse on the screen in draughtboard coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @param millis - Time to wait after the click in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void rightClick(PointD position, boolean maj, int millis) throws StopProgramException, CancelProgramException, B4DException {
		rightClick(B4D.converter.toPoint(position), maj, millis);
	}
	
	/**
	 * Performs a right click and wait for 1000 ms after the click.
	 * This is the same as {@code rightClick(position, maj, 1000)}.
	 * @param position - Location of the mouse on the screen in draughtboard coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void rightClick(PointD position, boolean maj) throws StopProgramException, CancelProgramException, B4DException {
		rightClick(B4D.converter.toPoint(position), maj, 1000);
	}
	
	/**
	 * Performs a left click.
	 * @param position - Location of the mouse on the screen in simple coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @param millis - Time to wait after the click in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void leftClick(Point position, boolean maj, int millis) throws StopProgramException, CancelProgramException, B4DException {
		try {
			Robot robot = new Robot();
			robot.mouseMove((int)position.getX(),(int)position.getY());
				
			if(maj)
				robot.keyPress(KeyEvent.VK_SHIFT);
			robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
			if(maj)	
				robot.keyRelease(KeyEvent.VK_SHIFT);
	
			B4D.wait.sleep(millis);
		} catch(AWTException e) {
			throw new B4DException(e);
		}
	}
	
	/**
	 * Performs a left click and wait for 1000 ms after the click.
	 * This is the same as {@code leftClick(position, maj, 1000)}.
	 * @param position - Location of the mouse on the screen in simple coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void leftClick(Point position, boolean maj) throws StopProgramException, CancelProgramException, B4DException {
		leftClick(position, maj, 1000);
	}
	
	/**
	 * Performs a left click.
	 * @param position - Location of the mouse on the screen in relative coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @param millis - Time to wait after the click in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void leftClick(PointF position, boolean maj, int millis) throws StopProgramException, CancelProgramException, B4DException {
		leftClick(B4D.converter.toPoint(position), maj, millis);
	}
	
	/**
	 * Performs a left click and wait for 1000 ms after the click.
	 * This is the same as {@code leftClick(position, maj, 1000)}.
	 * @param position - Location of the mouse on the screen in relative coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void leftClick(PointF position, boolean maj) throws StopProgramException, CancelProgramException, B4DException {
		leftClick(B4D.converter.toPoint(position), maj, 1000);
	}
	
	/**
	 * Performs a left click.
	 * @param position - Location of the mouse on the screen in draughtboard coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @param millis - Time to wait after the click in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void leftClick(PointD position, boolean maj, int millis) throws StopProgramException, CancelProgramException, B4DException {
		leftClick(B4D.converter.toPoint(position), maj, millis);
	}
	
	/**
	 * Performs a left click and wait for 1000 ms after the click.
	 * This is the same as {@code leftClick(position, maj, 1000)}.
	 * @param position - Location of the mouse on the screen in draughtboard coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void leftClick(PointD position, boolean maj) throws StopProgramException, CancelProgramException, B4DException {
		leftClick(B4D.converter.toPoint(position), maj, 1000);
	}
	
	/**
	 * Performs a double left click.
	 * @param position - Location of the mouse on the screen in simple coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @param millis - Time to wait after the click in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void doubleLeftClick(Point position, boolean maj, int millis) throws StopProgramException, CancelProgramException, B4DException {
		leftClick(position, maj, 0);
		leftClick(position, maj, millis);
	}
	
	/**
	 * Performs a left click and wait for 1000 ms after the click.
	 * This is the same as {@code doubleLeftClick(position, maj, 1000)}.
	 * @param position - Location of the mouse on the screen in simple coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void doubleLeftClick(Point position, boolean maj) throws StopProgramException, CancelProgramException, B4DException {
		doubleLeftClick(position, maj, 1000);
	}
	
	/**
	 * Performs a double left click.
	 * @param position - Location of the mouse on the screen in relative coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @param millis - Time to wait after the click in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void doubleLeftClick(PointF position, boolean maj, int millis) throws StopProgramException, CancelProgramException, B4DException {
		doubleLeftClick(B4D.converter.toPoint(position), maj, millis);
	}
	
	/**
	 * Performs a left click and wait for 1000 ms after the click.
	 * This is the same as {@code doubleLeftClick(position, maj, 1000)}.
	 * @param position - Location of the mouse on the screen in relative coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void doubleLeftClick(PointF position, boolean maj) throws StopProgramException, CancelProgramException, B4DException {
		doubleLeftClick(B4D.converter.toPoint(position), maj, 1000);
	}
	
	/**
	 * Performs a double left click.
	 * @param position - Location of the mouse on the screen in draughtboard coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @param millis - Time to wait after the click in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void doubleLeftClick(PointD position, boolean maj, int millis) throws StopProgramException, CancelProgramException, B4DException {
		doubleLeftClick(B4D.converter.toPoint(position), maj, millis);
	}
	
	/**
	 * Performs a left click and wait for 1000 ms after the click.
	 * This is the same as {@code doubleLeftClick(position, maj, 1000)}.
	 * @param position - Location of the mouse on the screen in draughtboard coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void doubleLeftClick(PointD position, boolean maj) throws StopProgramException, CancelProgramException, B4DException {
		doubleLeftClick(B4D.converter.toPoint(position), maj, 1000);
	}
	
	/**
	 * Performs a triple left click.
	 * @param position - Location of the mouse on the screen in simple coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @param millis - Time to wait after the click in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void tripleLeftClick(Point position, boolean maj, int millis) throws StopProgramException, CancelProgramException, B4DException {
		leftClick(position, maj, 0);
		leftClick(position, maj, 0);
		leftClick(position, maj, millis);
	}
	
	/**
	 * Performs a left click and wait for 1000 ms after the click.
	 * This is the same as {@code tripleLeftClick(position, maj, 1000)}.
	 * @param position - Location of the mouse on the screen in simple coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void tripleLeftClick(Point position, boolean maj) throws StopProgramException, CancelProgramException, B4DException {
		tripleLeftClick(position, maj, 1000);
	}
	
	/**
	 * Performs a triple left click.
	 * @param position - Location of the mouse on the screen in relative coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @param millis - Time to wait after the click in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void tripleLeftClick(PointF position, boolean maj, int millis) throws StopProgramException, CancelProgramException, B4DException {
		tripleLeftClick(B4D.converter.toPoint(position), maj, millis);
	}
	
	/**
	 * Performs a left click and wait for 1000 ms after the click.
	 * This is the same as {@code tripleLeftClick(position, maj, 1000)}.
	 * @param position - Location of the mouse on the screen in relative coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void tripleLeftClick(PointF position, boolean maj) throws StopProgramException, CancelProgramException, B4DException {
		tripleLeftClick(B4D.converter.toPoint(position), maj, 1000);
	}
	
	/**
	 * Performs a triple left click.
	 * @param position - Location of the mouse on the screen in draughtboard coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @param millis - Time to wait after the click in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void tripleLeftClick(PointD position, boolean maj, int millis) throws StopProgramException, CancelProgramException, B4DException {
		tripleLeftClick(B4D.converter.toPoint(position), maj, millis);
	}
	
	/**
	 * Performs a triple left click and wait for 1000 ms after the click.
	 * This is the same as {@code tripleLeftClick(position, maj, 1000)}.
	 * @param position - Location of the mouse on the screen in draughtboard coordinates.
	 * @param maj - {@code true} if Shift must be pressed at the same time. It can be used to stack actions.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void tripleLeftClick(PointD position, boolean maj) throws StopProgramException, CancelProgramException, B4DException {
		tripleLeftClick(B4D.converter.toPoint(position), maj, 1000);
	}
	
	/**
	 * Performs a drag and drop.
	 * @param pressedPosition - Location of the mouse on press in simple coordinates.
	 * @param releasedPosition - Location of the mouse on release in simple coordinates.
	 * @param millis - Time between the press and release in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void dragDrop(Point pressedPosition, Point releasedPosition, int millis) throws StopProgramException, CancelProgramException, B4DException {
		try {
			Robot robot = new Robot();
			robot.mouseMove((int)pressedPosition.getX(),(int)pressedPosition.getY());
			robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
	
			B4D.wait.sleep(millis);
			
			robot.mouseMove((int)releasedPosition.getX(),(int)releasedPosition.getY());
			robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
	
			B4D.wait.sleep(millis);
		} catch(AWTException e) {
			throw new B4DException(e);
		}
	}

	/**
	 * Performs a drag and drop and wait for 1000 ms between the press and release.
	 * @param pressedPosition - Location of the mouse on press in simple coordinates.
	 * @param releasedPosition - Location of the mouse on release in simple coordinates.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void dragDrop(Point pressedPosition, Point releasedPosition) throws StopProgramException, CancelProgramException, B4DException {
		dragDrop(pressedPosition, releasedPosition, 1000);
	}

	/**
	 * Performs a drag and drop.
	 * @param pressedPosition - Location of the mouse on press in relative coordinates.
	 * @param releasedPosition - Location of the mouse on release in relative coordinates.
	 * @param millis - Time between the press and release in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void dragDrop(PointF pressedPosition, PointF releasedPosition, int millis) throws StopProgramException, CancelProgramException, B4DException {
		dragDrop(B4D.converter.toPoint(pressedPosition), B4D.converter.toPoint(releasedPosition), millis);
	}

	/**
	 * Performs a drag and drop and wait for 1000 ms between the press and release.
	 * @param pressedPosition - Location of the mouse on press in relative coordinates.
	 * @param releasedPosition - Location of the mouse on release in relative coordinates.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void dragDrop(PointF pressedPosition, PointF releasedPosition) throws StopProgramException, CancelProgramException, B4DException {
		dragDrop(B4D.converter.toPoint(pressedPosition), B4D.converter.toPoint(releasedPosition), 1000);
	}

	/**
	 * Performs a drag and drop.
	 * @param pressedPosition - Location of the mouse on press in draughtboard coordinates.
	 * @param releasedPosition - Location of the mouse on release in draughtboard coordinates.
	 * @param millis - Time between the press and release in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void dragDrop(PointD pressedPosition, PointD releasedPosition, int millis) throws StopProgramException, CancelProgramException, B4DException {
		dragDrop(B4D.converter.toPoint(pressedPosition), B4D.converter.toPoint(releasedPosition), millis);
	}

	/**
	 * Performs a drag and drop and wait for 1000 ms between the press and release.
	 * @param pressedPosition - Location of the mouse on press in draughtboard coordinates.
	 * @param releasedPosition - Location of the mouse on release in draughtboard coordinates.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void dragDrop(PointD pressedPosition, PointD releasedPosition) throws StopProgramException, CancelProgramException, B4DException {
		dragDrop(B4D.converter.toPoint(pressedPosition), B4D.converter.toPoint(releasedPosition), 1000);
	}
	
	/**
	 * Performs a click in the chat bar.
	 * @param millis - Time to wait after the click in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void chatClick(int millis) throws StopProgramException, CancelProgramException, B4DException {
		leftClick(configuration.getChatBar(), false, millis);
	}
	
	/**
	 * Performs a click in the chat bar and wait for 500 ms after the click.
	 * This is the same as {@code chatClick(500)}.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if the platform configuration does not allow low-level input control.
	 */
	public void chatClick() throws StopProgramException, CancelProgramException, B4DException {
		chatClick(500);
	}
}
