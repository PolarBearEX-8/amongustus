package augustus.modes.combat;

import augustus.modules.combat.Criticals;
import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.setting.SettingGroup;
import client.util.ExtendedStringArrayEntry;
import java.util.function.BiFunction;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C04PacketPlayerPosition;

public class CriticalsPacketMode extends ExtendedStringArrayEntry implements AttackEntityListener {
   @Override
   public void updateState() {
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      if (this.isActive()) {
         minecraft.thePlayer
            .netHandlerPlayClient
            .handlePacket(new C04PacketPlayerPosition(minecraft.thePlayer.posX, minecraft.thePlayer.posY + 6.0E-15, minecraft.thePlayer.posZ, false));
         minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C03PacketPlayer(false));
      }
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new CriticalsPacketMode((Criticals)item, (SettingGroup)group));
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
      byte byteValue = 1;
      this.applyIndex(byteValue);
   }

   public CriticalsPacketMode(Criticals criticals, SettingGroup settingGroup) {
      super(criticals, settingGroup, "Packet");
   }
}
