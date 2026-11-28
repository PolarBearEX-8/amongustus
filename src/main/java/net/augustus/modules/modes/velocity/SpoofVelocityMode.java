package net.augustus.modules.modes.velocity;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.PacketListener;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.SettingGroup;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

public class SpoofVelocityMode extends VelocityMode implements PacketListener {

   public SpoofVelocityMode(Velocity velocity, SettingGroup doubleValue) {
      super(velocity, doubleValue, "Spoof");
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new SpoofVelocityMode((Velocity)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S12PacketEntityVelocity && this.canApplyVelocity()) {
         S12PacketEntityVelocity packet2 = (S12PacketEntityVelocity)packet;
         if (packet2.getEntityID() == mc.thePlayer.getEntityId()) {
            event.cancel();
            mc.getNetHandler()
               .addToSendQueue(
                  new C03PacketPlayer.C04PacketPlayerPosition(
                     mc.thePlayer.posX + (double)packet2.getMotionX() / 8000.0,
                     mc.thePlayer.posY + (double)packet2.getMotionY() / 8000.0,
                     mc.thePlayer.posZ + (double)packet2.getMotionZ() / 8000.0,
                     false
                  )
               );
         }
      }
   }

}
