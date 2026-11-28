package net.augustus.modules.modes.criticals;

import net.augustus.events.EventPacket;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.RangeSetting;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.math.RandomNumberGenerator;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Criticals;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class PacketEditCriticalsMode extends PacketCriticalsModeBase implements PacketListener {
   private final BooleanValue setOnGroundToFalse;
   private final RangeSetting yadd;

   public PacketEditCriticalsMode(Criticals criticals, SettingGroup doubleValue) {
       super(criticals, doubleValue, "PacketEdit");
      this.setOnGroundToFalse = (BooleanValue)new BooleanValue(this.parentModule, "SetOnGroundToFalse", false, this.modeSetting).withVisibility(() -> this.isSelected());
      this.yadd = new RangeSetting(this.parentModule, "Yadd", 1.0E-6, 2.0E-5, 0.0, 1.0, 12, this.modeSetting).visibleWhen(() -> this.isSelected());
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
      return (obj, obj2) -> new PacketEditCriticalsMode((Criticals)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof C03PacketPlayer && this.shouldCrit()) {
         C03PacketPlayer packet2 = (C03PacketPlayer)packet;
         if (this.setOnGroundToFalse.getValue()) {
            packet2.setOnGround(false);
         }

         packet2.setPositionY(packet2.getPositionY() + DefaultRandomGenerator.instance.nextDouble(this.yadd.getMinValue(), this.yadd.getMaxValue()));
      }
   }

}
