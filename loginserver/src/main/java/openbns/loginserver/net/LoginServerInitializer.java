package openbns.loginserver.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Created with IntelliJ IDEA.
 * User: Eugene Chipachenko
 * Date: 16.01.14
 * Time: 20:56
 */
public class LoginServerInitializer extends ChannelInitializer<SocketChannel>
{
  @Override
  protected void initChannel( SocketChannel ch ) throws Exception
  {
    ChannelPipeline p = ch.pipeline();
    p.addLast( "codec", new HttpServerCodec() );
    p.addLast( "handler", new LoginServerHandler() );
  }
}
