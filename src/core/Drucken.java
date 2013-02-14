//aus Java ist auch eine Insel

import java.awt.print.*;

public class Drucken
{
  public static void main( String[] args ) throws PrinterException
  {
    PrinterJob pjob = PrinterJob.getPrinterJob();
    if ( pjob.printDialog() == false )
    return;
    pjob.setPrintable( new TextPrintable() );
    pjob.print();
  }
}