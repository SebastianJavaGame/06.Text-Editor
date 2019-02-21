package scialsk.program.print;

import java.awt.print.PrinterJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

<<<<<<< HEAD
=======
@SuppressWarnings("serial")
>>>>>>> no message
public class OptionPageFrame{
	public OptionPageFrame() {
		PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
                PrinterJob job = PrinterJob.getPrinterJob();
                job.pageDialog(attributes);
        
        }
}