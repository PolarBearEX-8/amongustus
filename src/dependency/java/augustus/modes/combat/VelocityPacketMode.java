package augustus.modes.combat;

import augustus.modules.combat.Velocity;
import client.event.EventPacket;
import client.event.PacketListener;
import client.setting.SettingGroup;
import client.util.InternalStringArrayEntry;
import client.util.Packet;
import java.util.function.BiFunction;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

public class VelocityPacketMode extends InternalStringArrayEntry implements PacketListener {
   @Override
   public void updateState() {
   }

   public static BiFunction getBiFunction() {
      return (velocity, settingGroup) -> new VelocityPacketMode((Velocity)velocity, (SettingGroup)settingGroup);
   }

   @Override
   public void clear() {
   }

   @Override
   public void onPacketPostSend(EventPacket packet) {
      Packet packet2 = packet.getPacket();
      if (packet2 instanceof S12PacketEntityVelocity && this.isActive()) {
         S12PacketEntityVelocity packet3 = (S12PacketEntityVelocity)packet2;
         if (packet3.getEntityId() == minecraft.thePlayer.getEntityId()) {
            packet.cancel();
            minecraft.getNetHandlerPlayClient()
               .handlePacket(
                  new C04PacketPlayerPosition(
                     minecraft.thePlayer.posX + (double)packet3.getEntityId3() / 8000.0,
                     minecraft.thePlayer.posY + (double)packet3.getEntityId2() / 8000.0,
                     minecraft.thePlayer.posZ + (double)packet3.getEntityId4() / 8000.0,
                     false
                  )
               );
         }
      }
   }

   @Override
   public void updateState2() {
      byte byteValue = 26;
      this.applyIndex(byteValue);
   }

   public VelocityPacketMode(Velocity velocity, SettingGroup settingGroup) {
      super(velocity, settingGroup, "Spoof");
   }
}
