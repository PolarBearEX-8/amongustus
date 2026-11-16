package client.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.nio.NioEventLoopGroup;

public final class AuxiliaryClientManagerDispatcher extends InterpolationEstimator {
   public NioEventLoopGroup getNioEventLoopGroup() {
      return new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Server IO #%d").setDaemon(true).build());
   }

   @Override
   public Object getTarget() {
      return this.getNioEventLoopGroup();
   }
}
