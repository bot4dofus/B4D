package test.libs;

import java.awt.AWTException;
import java.awt.Point;
import java.io.IOException;

import org.junit.Test;

import fr.B4D.modules.B4DScreen;
import fr.B4D.utils.Rectangle;
import net.sourceforge.tess4j.TesseractException;
;
public class Tesseract {

	@Test
	public void test() throws AWTException, IOException, TesseractException {
		String out = B4DScreen.OCR(new Rectangle(new Point(340,500), new Point(1170,990)));
		System.out.println(out);
	}

}
