package client.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import net.minecraft.network.NetworkManager;

public final class AuxiliaryChannelInitializerAdapter extends ChannelInitializer {
   public final NetworkManager networkManager;

   public AuxiliaryChannelInitializerAdapter(NetworkManager networkManager2) {
      this.networkManager = networkManager2;
   }

   public void initChannel(Channel channel) {
      channel.pipeline().addLast("packet_handler", this.networkManager);
   }
}
