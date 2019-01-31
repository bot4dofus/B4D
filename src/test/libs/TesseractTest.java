package test.libs;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.io.IOException;

import org.junit.Test;

import fr.B4D.bot.B4D;
import fr.B4D.utils.PointF;
import fr.B4D.utils.Rectangle;
import net.sourceforge.tess4j.TesseractException;

public class TesseractTest {

	@Test
	public void test() throws AWTException, IOException, TesseractException {
		String out = B4D.screen.OCR(new Rectangle(new Point(340,500), new Point(1170,990)));
		System.out.println(out);
	}
	@Test
	public void test2() throws AWTException {
		System.out.println(B4D.screen.searchPixel(new PointF(0,0), new PointF(0,0), new Color(100, 100, 0), new Color(255, 255, 50)));
	}
}
