package net.augustus.modules.modes.criticals;

import net.augustus.events.EventPacket;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Criticals;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoGroundCriticalsMode extends PacketCriticalsModeBase implements PacketListener {
   private final DoubleValue floating;

   public NoGroundCriticalsMode(Criticals criticals, SettingGroup doubleValue) {
       super(criticals, doubleValue, "NoGround");
      this.floating = new DoubleValue(this.parentModule, "Floating", 0.0, 0.0, 1.0, 7, this.modeSetting).visibleWhen(() -> this.isSelected());
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
      this.toggleEventSubscription(26);
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new NoGroundCriticalsMode((Criticals)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof C03PacketPlayer && this.shouldCrit()) {
         C03PacketPlayer packet2 = (C03PacketPlayer)packet;
         packet2.setOnGround(false);
         if (this.floating.getValue() != 0.0) {
            packet2.setPositionY(packet2.getPositionY() + this.floating.getValue());
         }

         this.clearPendingAttack();
      }
   }

}
