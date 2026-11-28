package net.augustus.modules.modes.velocity;

import net.augustus.events.EventPacket;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

public class WaterVelocityMode extends VelocityMode implements PacketListener {
   private final DoubleValue waterInterval;
   private int waterHitCount;

   public WaterVelocityMode(Velocity velocity, SettingGroup doubleValue) {
       super(velocity, doubleValue, "Water");
      this.waterInterval = new DoubleValue(this.parentModule, "WaterInterval", 1.0, 1.0, 3.0, 0, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.waterHitCount = 0;
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new WaterVelocityMode((Velocity)obj, (SettingGroup)obj2);
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
   }

   @Override
   public void onEnable() {
      this.waterHitCount = 0;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (this.canApplyVelocity() && !event.isCancelled() && mc.thePlayer != null) {
         Packet packet = event.getPacket();
         if (packet instanceof S12PacketEntityVelocity) {
            S12PacketEntityVelocity packet2 = (S12PacketEntityVelocity)packet;
            if (packet2.getEntityID() == mc.thePlayer.getEntityId()) {
               if (!mc.thePlayer.isInWater()) {
                  this.waterHitCount = 0;
               } else {
                  int n = Math.max(1, (int)this.waterInterval.getValue());
                  if (this.waterHitCount % n == 0) {
                     packet2.setMotionX(0);
                     packet2.setMotionZ(0);
                  }

                  this.waterHitCount++;
               }
            }
         }
      }
   }

}
