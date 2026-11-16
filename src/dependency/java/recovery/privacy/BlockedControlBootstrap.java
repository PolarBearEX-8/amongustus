package recovery.privacy;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.embedded.EmbeddedChannel;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.SocketAddress;

public final class BlockedControlBootstrap extends Bootstrap {
   private ChannelFuture blocked() {
      EmbeddedChannel embeddedChannel = new EmbeddedChannel();
      ChannelFuture channelFuture = embeddedChannel.newFailedFuture(new ConnectException("Recovered client control channel disabled"));
      embeddedChannel.close();
      return channelFuture;
   }

   public ChannelFuture connect() {
      return this.blocked();
   }

   public ChannelFuture connect(String text, int count) {
      return this.blocked();
   }

   public ChannelFuture connect(InetAddress inetAddress, int count) {
      return this.blocked();
   }

   public ChannelFuture connect(SocketAddress socketAddress) {
      return this.blocked();
   }

   public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2) {
      return this.blocked();
   }

   public Bootstrap clone() {
      return this;
   }

   public Bootstrap clone(EventLoopGroup event) {
      return this;
   }
}
