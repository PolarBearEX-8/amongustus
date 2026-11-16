package client.util;

import augustus.modules.combat.Criticals;
import client.ClientGlobals;
import client.module.Module;
import client.setting.ModeOptionSupplier;
import client.setting.SettingGroup;
import net.minecraft.potion.Potion;

public abstract class ExtendedStringArrayEntry extends ModeOptionSupplier {
   public boolean isActive() {
      return this.isActiveAsFlag()
         && (minecraft.thePlayer.secondaryFloatEntry.state || !((Criticals)this.module).settingOnlyWhileJumpKeyPressed.isEnabled())
         && (
            ((Module)ClientGlobals.moduleManager.killAura).isToggled() && ClientGlobals.moduleManager.killAura.target != null
               || !((Criticals)this.module).settingOnlyWhileKillAuraTarget.isEnabled()
         )
         && (
            ((Criticals)this.module).settingCriticalsOnGround.isEnabled() && minecraft.thePlayer.onGround
               || ((Criticals)this.module).settingCriticalsInAir.isEnabled() && !minecraft.thePlayer.onGround
         );
   }

   public boolean isActiveAsFlag() {
      return !minecraft.playerController.state
         && !minecraft.thePlayer.canExecute9()
         && !minecraft.thePlayer.isInWater()
         && !minecraft.thePlayer.checkPotion(Potion.potion16)
         && minecraft.thePlayer.ridingEntity == null;
   }

   public ExtendedStringArrayEntry(Criticals criticals, SettingGroup settingGroup, String text) {
      super(criticals, settingGroup, text);
   }
}
