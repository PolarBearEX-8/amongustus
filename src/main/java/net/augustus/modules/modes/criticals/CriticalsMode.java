package net.augustus.modules.modes.criticals;

import net.augustus.ClientManagers;
import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.modules.combat.Criticals;
import net.augustus.settings.SettingGroup;
import net.minecraft.potion.Potion;

public abstract class CriticalsMode extends AbstractSubMode {

   public CriticalsMode(Criticals criticals, SettingGroup doubleValue, String str) {
      super(criticals, doubleValue, str);
   }

   public boolean shouldCrit() {
      return this.canPerformCrit()
         && (mc.thePlayer.movementInput.jump || !((Criticals)this.parentModule).onlyWhileJumpKeyPressed.getValue())
         && (ClientManagers.moduleManager.killAura.isToggled() && ClientManagers.moduleManager.killAura.target != null || !((Criticals)this.parentModule).onlyWhileKillAuraTarget.getValue())
         && (((Criticals)this.parentModule).criticalsOnGround.getValue() && mc.thePlayer.onGround || ((Criticals)this.parentModule).criticalsInAir.getValue() && !mc.thePlayer.onGround);
   }

   public boolean canPerformCrit() {
      return !mc.playerController.isHittingBlock
         && !mc.thePlayer.isOnLadder()
         && !mc.thePlayer.isInWater()
         && !mc.thePlayer.isPotionActive(Potion.blindness)
         && mc.thePlayer.ridingEntity == null;
   }



}
