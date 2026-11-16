package client.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.nio.NioEventLoopGroup;

public final class NettyClientIODManager extends InterpolationEstimator {
   @Override
   public Object getTarget() {
      return this.getNioEventLoopGroup();
   }

   public NioEventLoopGroup getNioEventLoopGroup() {
      return new NioEventLoopGroup(2, new ThreadFactoryBuilder().setNameFormat("Netty Client IO #%d").setDaemon(true).build());
   }
}
