package client.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.local.LocalEventLoopGroup;

public final class NettyLocalClientIODManager extends InterpolationEstimator {
   @Override
   public Object getTarget() {
      return this.getLocalEventLoopGroup();
   }

   public LocalEventLoopGroup getLocalEventLoopGroup() {
      return new LocalEventLoopGroup(2, new ThreadFactoryBuilder().setNameFormat("Netty Local Client IO #%d").setDaemon(true).build());
   }
}
