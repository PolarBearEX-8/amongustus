package net.augustus.modules.modes.clicking;

import net.minecraft.util.MovingObjectPosition;
import net.augustus.events.EventClick;
import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.modules.Module;
import net.augustus.settings.SettingGroup;

public abstract class AbstractClickPatternMode extends AbstractSubMode {

   public AbstractClickPatternMode(Module module, SettingGroup doubleValue, String str) {
      super(module, doubleValue, str);
   }

   public abstract int computeClicks(EventClick event);

   public abstract void onTick();

   public boolean simulatesMousePress() {
      return false;
   }

   public boolean isNotLookingAtEntity() {
      return mc.objectMouseOver == null || mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY;
   }

   public Module getParentModule() {
      return this.parentModule;
   }



}
