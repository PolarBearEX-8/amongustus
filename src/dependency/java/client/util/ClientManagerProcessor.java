package client.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.epoll.EpollEventLoopGroup;

public final class ClientManagerProcessor extends InterpolationEstimator {
   @Override
   public Object getTarget() {
      return this.getEpollEventLoopGroup();
   }

   public EpollEventLoopGroup getEpollEventLoopGroup() {
      return new EpollEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Epoll Server IO #%d").setDaemon(true).build());
   }
}
