package client.util;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.Future;

public class ListenerAdapter3ChannelFutureListener implements ChannelFutureListener {
   public final ListenerAdapter3 listenerAdapter3;

   public void applyChannelFuture(ChannelFuture channelFuture) throws Exception {
      this.listenerAdapter3.networkManager.applyIndex(ListenerAdapter3.resolveVanillaRunnable(this.listenerAdapter3).getCount3());
   }

   @Override
   public void operationComplete(ChannelFuture future) throws Exception {
      this.applyChannelFuture(future);
   }

   public ListenerAdapter3ChannelFutureListener(ListenerAdapter3 listenerAdapter) {
      this.listenerAdapter3 = listenerAdapter;
   }
}
