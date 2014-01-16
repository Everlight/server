package openbns.loginserver;

import openbns.commons.util.ExProperties;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Eugene Chipachenko
 * Date: 16.01.14
 * Time: 20:44
 */
public class Config
{
  private static final String SERVER_CONFIG_FILE = "/loginserver.properties";
  public static String LS_HOST;
  public static int LS_PORT;

  public static void load() throws IOException
  {
    loadServerProperties();
  }

  private static void loadServerProperties() throws IOException
  {
    InputStream is = Config.class.getResourceAsStream( SERVER_CONFIG_FILE );
    ExProperties properties = new ExProperties( is );

    LS_HOST = properties.getProperty( "loginserver.host", "127.0.0.1" );
    LS_PORT = properties.getProperty( "loginserver.port", 6600 );

    properties.clear();
    is.close();
  }
}