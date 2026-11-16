package client.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.epoll.EpollEventLoopGroup;

public final class NettyEpollClientIODClientManager extends InterpolationEstimator {
   @Override
   public Object getTarget() {
      return this.getEpollEventLoopGroup();
   }

   public EpollEventLoopGroup getEpollEventLoopGroup() {
      return new EpollEventLoopGroup(2, new ThreadFactoryBuilder().setNameFormat("Netty Epoll Client IO #%d").setDaemon(true).build());
   }
}
