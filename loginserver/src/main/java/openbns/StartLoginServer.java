package openbns;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import openbns.loginserver.Config;
import openbns.loginserver.net.LoginServerInitializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Eugene Chipachenko
 * Date: 16.01.14
 * Time: 20:50
 */
public class StartLoginServer
{
  private static final Log log = LogFactory.getLog( StartLoginServer.class );

  public static void main( String[] args ) throws IOException, InterruptedException
  {
    log.info( "Start loading login server" );
    Config.load();

    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    try
    {
      ServerBootstrap b = new ServerBootstrap();
      b.option( ChannelOption.SO_BACKLOG, 1024 );
      b.group( bossGroup, workerGroup ).channel( NioServerSocketChannel.class ).childHandler( new LoginServerInitializer() );

      log.info( "Start listening clients on " + Config.LS_HOST + ":" + Config.LS_PORT );
      Channel ch = b.bind( Config.LS_HOST, Config.LS_PORT ).sync().channel();
      ch.closeFuture().sync();
    }
    finally
    {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }
}
