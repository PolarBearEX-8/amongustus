package augustus.modes.player;

import augustus.modules.combat.Criticals;
import client.event.EventPacket;
import client.event.PacketListener;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.InternalAttackEntityListenerAdapter;
import client.util.Packet;
import java.util.function.BiFunction;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFallNogroundMode extends InternalAttackEntityListenerAdapter implements PacketListener {
   public final NumberSetting settingFloating;

   public static BiFunction getBiFunction() {
      return ((item, group) -> new NoFallNogroundMode((Criticals)item, (SettingGroup)group));
   }

   @Override
   public void onPacketSend(EventPacket packet) {
      Packet packet2 = packet.getPacket();
      if (packet2 instanceof C03PacketPlayer && this.isActive()) {
         C03PacketPlayer packet3 = (C03PacketPlayer)packet2;
         packet3.setOnGround(false);
         if (this.settingFloating.getValue() != 0.0) {
            packet3.setCoordinate(packet3.getCoordinate() + this.settingFloating.getValue());
         }

         this.updateState3();
      }
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
      super.updateState2();
      byte byteValue = 26;
      this.applyIndex(byteValue);
   }

   public NoFallNogroundMode(Criticals criticals, SettingGroup settingGroup) {
      super(criticals, settingGroup, "NoGround");
      NumberSetting setting = new NumberSetting(this.module, "Floating", 0.0, 0.0, 1.0, 7, this.settingGroup);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingFloating = setting.getNumberSetting(booleanSupplier);
   }
}
