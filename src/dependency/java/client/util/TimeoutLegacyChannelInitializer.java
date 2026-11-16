package client.util;

import client.setting.EventPacketServerboundType;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import net.minecraft.network.NetworkManager;

public class TimeoutLegacyChannelInitializer extends ChannelInitializer {
   public final CustomLoggerEntry customLoggerEntry;

   public TimeoutLegacyChannelInitializer(CustomLoggerEntry customLoggerEntry2) {
      this.customLoggerEntry = customLoggerEntry2;
   }

   public void initChannel(Channel channel) throws Exception {
      try {
         channel.config().setOption(ChannelOption.TCP_NODELAY, Boolean.TRUE);
      } catch (ChannelException channelException) {
      }

      channel.pipeline()
         .addLast("timeout", new ReadTimeoutHandler(30))
         .addLast("legacy_query", new PingLegacyChannelInboundHandlerA(this.customLoggerEntry))
         .addLast("splitter", new LengthWiderThan21BitByteToMessageDecoder())
         .addLast("decoder", new PACKETRECEIVEDByteToMessageDecoder(EventPacketServerboundType.SERVERBOUND))
         .addLast("prepender", new UnableToFitMessageToByteEncoder())
         .addLast("encoder", new OUTMessageToByteEncoder(EventPacketServerboundType.CLIENTBOUND));
      NetworkManager networkManager = new NetworkManager(EventPacketServerboundType.SERVERBOUND);
      CustomLoggerEntry.resolveList(this.customLoggerEntry).add(networkManager);
      channel.pipeline().addLast("packet_handler", networkManager);
      networkManager.applyINetHandler(new OutdatedServerAuxiliaryINetHandlerEx(CustomLoggerEntry.resolveVanillaRunnable(this.customLoggerEntry), networkManager));
   }
}
