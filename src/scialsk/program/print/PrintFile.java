package scialsk.program.print;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JComponent;

public class PrintFile{     
        private static final Dimension PREFERRED_SIZE = new Dimension(300, 300);
    
	public PrintFile() throws PrinterException {
            PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(new PrintComponent());
            if(job.printDialog(attributes))
                job.print(attributes);
        }
        
        @SuppressWarnings("serial")
		class PrintComponent extends JComponent implements Printable{
            @Override
            public void paintComponent(Graphics g){
                Graphics2D g2 = (Graphics2D) g;
                drawPage(g2);
            }
            
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if(pageIndex >= 1)
                    return Printable.NO_SUCH_PAGE;
                
                Graphics2D g2 = (Graphics2D) graphics;
                g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                g2.draw(new Rectangle2D.Double(0, 0, pageFormat.getImageableWidth(), pageFormat.getImageableHeight()));
                
                drawPage(g2);
                return Printable.PAGE_EXISTS;
            }
            
             public void drawPage(Graphics2D g2){
                 FontRenderContext context = g2.getFontRenderContext();
                 Font f = new Font("Serif", Font.PLAIN, 72);
                 GeneralPath clipShape = new GeneralPath();
                 
                 TextLayout layout = new TextLayout("Witaj", f, context);
                 AffineTransform transform = AffineTransform.getTranslateInstance(0, 72);
                 Shape outline = layout.getOutline(transform);
                 clipShape.append(outline, false);
                 
                 g2.draw(clipShape);
                 g2.clip(clipShape);
                 
                 final int NLINES = 50;
                 Point2D p = new Point2D.Double(0, 0);
                 for(int i =0; i <NLINES; i++){
                     double x = (2 *getWidth() *i) /NLINES;
                     double y = (2 *getHeight() *(NLINES -1 -i)) /NLINES;
                     Point2D q = new Point2D.Double(x, y);
                     g2.draw(new Line2D.Double(p, q));
                 }
             }
             
            @Override
            public Dimension getPreferredSize(){return PREFERRED_SIZE;}
        }
}
