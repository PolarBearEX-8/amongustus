package client.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.local.LocalEventLoopGroup;

public final class InternalClientManagerDispatcher extends InterpolationEstimator {
   @Override
   public Object getTarget() {
      return this.getLocalEventLoopGroup();
   }

   public LocalEventLoopGroup getLocalEventLoopGroup() {
      return new LocalEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Local Server IO #%d").setDaemon(true).build());
   }
}
