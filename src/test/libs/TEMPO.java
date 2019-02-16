package test.libs;

import java.awt.AWTException;
import java.awt.Point;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import fr.B4D.utils.PointF;
import net.sourceforge.tess4j.TesseractException;

public class TEMPO {

	@Test
	public void test() throws AWTException, IOException, TesseractException {
		
		String in = "new PointF(0.545,0.112)";
		
		do {
			Scanner sc = new Scanner(System.in);
			in = sc.nextLine();
			if(in.contains("out"))
				break;
			
			int deb = in.indexOf("(");
			int mid = in.indexOf(",");
			int fin = in.indexOf(")");
			
			float x = Float.parseFloat(in.substring(deb+1,mid));
			float y = Float.parseFloat(in.substring(mid+1,fin));
			
			PointF inP = new PointF(x,y);
			PointF outP = pointToPointF(pointFToPoint(inP));
			String out = "new PointF(" + outP.x + "," + outP.y + ")";
			
			System.out.println(in + " -> " + out);
		}while(true);
	}
	
    public Point pointFToPoint(PointF point) {
    	final double x = 0;
		final double y = 29;
		final double w = 1920;
		final double h = 1003;
    	
    	double X = (point.getX() * w) + x;
    	double Y = (point.getY() * h) + y;
        return new Point((int)X, (int)Y);
    }
    
    /** Ecran > Proportionnel **/
    public PointF pointToPointF(Point point){
    	final double x = 334;
		final double y = 29;
		final double w = 1250;
		final double h = 1002;

		final double Precision = 10000;
		
    	double X = (point.getX() - x) / w;
    	double Y = (point.getY() - y) / h;
        return new PointF(Math.round(X*Precision)/Precision, Math.round(Y*Precision)/Precision);
    }
}
