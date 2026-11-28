package net.augustus.modules.modes.velocity;

import net.augustus.events.EventPacket;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

public class LadderVelocityMode extends VelocityMode implements PacketListener {
   private final BooleanValue ladderXZOnly;

   public LadderVelocityMode(Velocity velocity, SettingGroup doubleValue) {
      super(velocity, doubleValue, "Ladder");
      this.ladderXZOnly = (BooleanValue)new BooleanValue(this.parentModule, "LadderXZOnly", true, this.modeSetting).withVisibility(() -> this.isSelected());
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new LadderVelocityMode((Velocity)obj, (SettingGroup)obj2);
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

   @Override
   public void onPacketReceive(EventPacket event) {
      if (this.canApplyVelocity() && !event.isCancelled() && mc.thePlayer != null) {
         if (event.getPacket() instanceof S12PacketEntityVelocity && mc.thePlayer.isOnLadder()) {
            S12PacketEntityVelocity packet2 = (S12PacketEntityVelocity)event.getPacket();
            if (packet2.getEntityID() == mc.thePlayer.getEntityId()) {
               packet2.setMotionX(0);
               packet2.setMotionZ(0);
               if (!this.ladderXZOnly.getValue()) {
                  packet2.setMotionY(0);
               }
            }
         }
      }
   }

}
