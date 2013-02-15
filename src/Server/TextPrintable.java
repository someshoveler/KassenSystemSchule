package Server;

//aus Java ist auch eine Insel!!

import java.awt.print.*;
import java.awt.*;

  public class TextPrintable implements Printable
{
  static Font font = new Font( "Times", Font.PLAIN, 20 );
  
  public int print( Graphics g, PageFormat pageFormat, int pageIndex )
  {
    if ( pageIndex >= 2 )
    return Printable.NO_SUCH_PAGE;
    g.setFont( font );
    g.drawString( "Hallo auf Seite " + pageIndex, 100, 100 );
    return Printable.PAGE_EXISTS;
  }
}
