package client.network;

import client.setting.EventPacketServerboundType;
import client.util.CustomLoggerEntry;
import client.util.INetHandlerExtensionAdapter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import net.minecraft.network.NetworkManager;

public class ChannelInitializerAdapter extends ChannelInitializer {
   public final CustomLoggerEntry customLoggerEntry;

   public void initChannel(Channel channel) throws Exception {
      NetworkManager networkManager = new NetworkManager(EventPacketServerboundType.SERVERBOUND);
      networkManager.applyINetHandler(new INetHandlerExtensionAdapter(CustomLoggerEntry.resolveVanillaRunnable(this.customLoggerEntry), networkManager));
      CustomLoggerEntry.resolveList(this.customLoggerEntry).add(networkManager);
      channel.pipeline().addLast("packet_handler", networkManager);
   }

   public ChannelInitializerAdapter(CustomLoggerEntry customLoggerEntry2) {
      this.customLoggerEntry = customLoggerEntry2;
   }
}
