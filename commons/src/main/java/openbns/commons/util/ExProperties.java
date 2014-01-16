package openbns.commons.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Eugene Chipachenko
 * Date: 16.01.14
 * Time: 20:42
 */
public class ExProperties extends Properties
{
  public ExProperties( InputStream is ) throws IOException
  {
    super();
    load( is );
  }

  public int getProperty( String key, int defaultValue )
  {
    String v = getProperty( key );
    return v == null ? defaultValue : Integer.parseInt( v );
  }
}
