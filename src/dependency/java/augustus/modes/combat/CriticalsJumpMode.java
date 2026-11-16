package augustus.modes.combat;

import augustus.modules.combat.Criticals;
import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.setting.SettingGroup;
import client.util.ExtendedStringArrayEntry;
import java.util.function.BiFunction;

public class CriticalsJumpMode extends ExtendedStringArrayEntry implements AttackEntityListener {
   @Override
   public void updateState() {
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      if (this.isActive() && minecraft.thePlayer.onGround && !minecraft.thePlayer.canExecute2() && minecraft.thePlayer.hurtTime != 10 && minecraft.thePlayer.hurtTime != 9) {
         minecraft.thePlayer.motionY = 0.085;
      }
   }

   @Override
   public void clear() {
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new CriticalsJumpMode((Criticals)item, (SettingGroup)group));
   }

   @Override
   public void updateState2() {
      byte byteValue = 1;
      this.applyIndex(byteValue);
   }

   public CriticalsJumpMode(Criticals criticals, SettingGroup settingGroup) {
      super(criticals, settingGroup, "IntaveLowHop");
   }
}
