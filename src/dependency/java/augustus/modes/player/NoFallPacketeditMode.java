package augustus.modes.player;

import augustus.modules.combat.Criticals;
import client.event.EventPacket;
import client.event.PacketListener;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.setting.SettingProvider;
import client.util.BooleanSupplier;
import client.util.InternalAttackEntityListenerAdapter;
import client.util.Packet;
import java.util.function.BiFunction;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFallPacketeditMode extends InternalAttackEntityListenerAdapter implements PacketListener {
   public final BooleanSetting settingSetOnGroundToFalse;
   public final ActionSetting actionSetting;

   @Override
   public void onPacketSend(EventPacket packet) {
      Packet packet2 = packet.getPacket();
      if (packet2 instanceof C03PacketPlayer && this.isActive()) {
         C03PacketPlayer packet3 = (C03PacketPlayer)packet2;
         if (this.settingSetOnGroundToFalse.isEnabled()) {
            packet3.setOnGround(false);
         }

         double coordinate = packet3.getCoordinate();
         SettingProvider settingProvider = SettingContainer.settingProvider;
         double doubleItem = this.actionSetting.getDouble6();
         double doubleItem2 = this.actionSetting.getDouble3();
         packet3.setCoordinate(coordinate + settingProvider.getDouble4(doubleItem2, doubleItem));
      }
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new NoFallPacketeditMode((Criticals)item, (SettingGroup)group));
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

   public NoFallPacketeditMode(Criticals criticals, SettingGroup settingGroup) {
        super(criticals, settingGroup, "PacketEdit");
      
      BooleanSetting setting = new BooleanSetting(this.module, "SetOnGroundToFalse", false, this.settingGroup);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingSetOnGroundToFalse = (BooleanSetting)setting.getSetting(booleanSupplier);
      ActionSetting setting2 = new ActionSetting(this.module, "Yadd", 1.0E-6, 2.0E-5, 0.0, 1.0, 12, this.settingGroup);
      BooleanSupplier booleanSupplier2 = this::booleanValue;
      this.actionSetting = setting2.getActionSetting(booleanSupplier2);
   }
}
