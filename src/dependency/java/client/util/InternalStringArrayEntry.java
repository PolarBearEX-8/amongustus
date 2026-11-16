package client.util;

import augustus.modules.combat.Velocity;
import client.setting.ModeOptionSupplier;
import client.setting.SettingGroup;

public abstract class InternalStringArrayEntry extends ModeOptionSupplier {
   public boolean isActive() {
      return minecraft.thePlayer == null
         ? false
         : (!((Velocity)this.module).settingNotWhileSPressed.isEnabled() || !((Velocity)this.module).expanded2)
            && (!minecraft.thePlayer.canExecute2() || !((Velocity)this.module).settingIgnoreFire.isEnabled());
   }

   public InternalStringArrayEntry(Velocity velocity, SettingGroup settingGroup, String text) {
      super(velocity, settingGroup, text);
   }
}
