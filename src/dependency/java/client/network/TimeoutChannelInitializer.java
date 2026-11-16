package client.network;

import client.Client;
import client.setting.EventPacketServerboundType;
import client.util.AimPredictor;
import client.util.LengthWiderThan21BitByteToMessageDecoder;
import client.util.OUTMessageToByteEncoder;
import client.util.PACKETRECEIVEDByteToMessageDecoder;
import client.util.UnableToFitMessageToByteEncoder;
import client.via.ViaLoadingBase;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.connection.UserConnectionImpl;
import com.viaversion.viaversion.protocol.ProtocolPipelineImpl;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import net.minecraft.network.NetworkManager;

public final class TimeoutChannelInitializer extends ChannelInitializer {
   public final boolean enabled;
   public final NetworkManager networkManager;
   public final boolean enabled2;

   public void initChannel(Channel channel) {
      try {
         channel.config().setOption(ChannelOption.TCP_NODELAY, Boolean.TRUE);
      } catch (ChannelException channelException) {
      }

      if (Client.getClient().getClientNetworkConfig() != null && this.enabled) {
         Client.getClient().getClientNetworkConfig().applyChannel(channel);
      }

      channel.pipeline()
         .addLast("timeout", new ReadTimeoutHandler(30))
         .addLast("splitter", new LengthWiderThan21BitByteToMessageDecoder())
         .addLast("decoder", new PACKETRECEIVEDByteToMessageDecoder(EventPacketServerboundType.CLIENTBOUND))
         .addLast("prepender", new UnableToFitMessageToByteEncoder())
         .addLast("encoder", new OUTMessageToByteEncoder(EventPacketServerboundType.SERVERBOUND))
         .addLast("packet_handler", this.networkManager);
      if (channel instanceof SocketChannel && ViaLoadingBase.protocolVersion != ProtocolVersion.v1_8) {
         UserConnectionImpl userConnectionImpl = new UserConnectionImpl(channel, true);
         new ProtocolPipelineImpl(userConnectionImpl);
         channel.pipeline().addLast(new ChannelHandler[]{new AimPredictor(userConnectionImpl, ViaLoadingBase.protocolVersion)});
      }

      if (this.enabled2) {
         channel.pipeline().addFirst("network-stats-tracker", new TrafficShapingTracker());
      }
   }

   public TimeoutChannelInitializer(boolean flag, NetworkManager networkManager2, boolean flag2) {
      this.enabled = flag;
      this.networkManager = networkManager2;
      this.enabled2 = flag2;
   }
}
