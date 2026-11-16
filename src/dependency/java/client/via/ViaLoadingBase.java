package client.via;

import client.setting.AutoSoupPerformRespawnType;
import client.util.ViaPlatformLoader;
import com.viaversion.viabackwards.protocol.v1_17to1_16_4.Protocol1_17To1_16_4;
import com.viaversion.vialoader.ViaLoader;
import com.viaversion.vialoader.impl.platform.ViaAprilFoolsPlatformImpl;
import com.viaversion.vialoader.impl.platform.ViaBackwardsPlatformImpl;
import com.viaversion.vialoader.impl.platform.ViaLegacyPlatformImpl;
import com.viaversion.vialoader.impl.platform.ViaRewindPlatformImpl;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.packet.PacketWrapper;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.exception.InformativeException;
import com.viaversion.viaversion.protocols.v1_16_1to1_16_2.packet.ClientboundPackets1_16_2;
import com.viaversion.viaversion.protocols.v1_16_1to1_16_2.packet.ServerboundPackets1_16_2;
import com.viaversion.viaversion.protocols.v1_16_4to1_17.packet.ClientboundPackets1_17;
import com.viaversion.viaversion.protocols.v1_16_4to1_17.packet.ServerboundPackets1_17;
import io.netty.util.AttributeKey;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C16PacketClientStatus;

public class ViaLoadingBase {
   public static ProtocolVersion protocolVersion = ProtocolVersion.v1_8;
   public static final AttributeKey attributeKey = AttributeKey.newInstance("viaprotocolhack-minecraft-connection");

   public static void processAction() {
      ViaLoader.init(
         null,
         new ViaPlatformLoader(),
         new ViaPipelineInjector(),
         null,
         new Supplier[]{ViaBackwardsPlatformImpl::new, ViaRewindPlatformImpl::new, ViaAprilFoolsPlatformImpl::new, ViaLegacyPlatformImpl::new}
      );
      Protocol1_17To1_16_4 protocol = (Protocol1_17To1_16_4)Via.getManager().getProtocolManager().getProtocol(Protocol1_17To1_16_4.class);
      protocol.registerClientbound(ClientboundPackets1_17.PING, ClientboundPackets1_16_2.CONTAINER_ACK, ViaLoadingBase::handlePacketWrapper2, true);
      protocol.registerServerbound(ServerboundPackets1_16_2.CONTAINER_ACK, ServerboundPackets1_17.PONG, ViaLoadingBase::handlePacketWrapper, true);
   }

   public static double processAction2() {
      return protocolVersion.newerThan(ProtocolVersion.v1_9) ? 0.003 : 0.005;
   }

   private static void handlePacketWrapper(PacketWrapper packet) throws InformativeException {
   }

   private static void handlePacketWrapper2(PacketWrapper packet) throws InformativeException {
   }

   public static void processAction(int count, int count2, char character) {
      if (protocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_11_1)) {
         Minecraft.getMinecraft().getNetHandlerPlayClient().handlePacket(new C16PacketClientStatus(AutoSoupPerformRespawnType.OPEN_INVENTORY_ACHIEVEMENT));
      }
   }
}
