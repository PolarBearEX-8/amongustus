package augustus.modes.combat;

import augustus.modules.combat.Velocity;
import client.event.EventPacket;
import client.event.PacketListener;
import client.setting.SettingGroup;
import client.util.InternalStringArrayEntry;
import client.util.Packet;
import java.util.function.BiFunction;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

public class VelocityGrimMode extends InternalStringArrayEntry implements PacketListener {
   public static BiFunction getBiFunction() {
      return ((item, group) -> new VelocityGrimMode((Velocity)item, (SettingGroup)group));
   }

   @Override
   public void updateState() {
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
            if (minecraft.thePlayer.onGround && (double)packet3.getEntityId2() / 8000.0 < 0.2 && (double)packet3.getEntityId2() / 8000.0 > 0.41995) {
               minecraft.thePlayer.motionY = (double)packet3.getEntityId2() / 8000.0;
            }
         }
      }
   }

   @Override
   public void updateState2() {
      byte byteValue = 26;
      this.applyIndex(byteValue);
   }

   public VelocityGrimMode(Velocity velocity, SettingGroup settingGroup) {
      super(velocity, settingGroup, "HypixelTest");
   }
}
