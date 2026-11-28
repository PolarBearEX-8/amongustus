package net.augustus.modules.modes.velocity;

import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.SettingGroup;

public abstract class VelocityMode extends AbstractSubMode {

   public VelocityMode(Velocity velocity, SettingGroup doubleValue, String str) {
      super(velocity, doubleValue, str);
   }

   public boolean canApplyVelocity() {
      return mc.thePlayer == null
         ? false
         : (!((Velocity)this.parentModule).notWhileSPressed.getValue() || !((Velocity)this.parentModule).movingBackward) && (!mc.thePlayer.isBurning() || !((Velocity)this.parentModule).ignoreFire.getValue());
   }



}
