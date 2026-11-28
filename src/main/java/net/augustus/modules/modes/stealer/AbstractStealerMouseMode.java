package net.augustus.modules.modes.stealer;

import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.utils.math.Vec2d;
import java.util.List;
import net.augustus.modules.player.Stealer;
import net.augustus.settings.SettingGroup;
import net.minecraft.inventory.Container;

public abstract class AbstractStealerMouseMode extends AbstractSubMode {

   public AbstractStealerMouseMode(Stealer stealer, SettingGroup doubleValue, String str) {
      super(stealer, doubleValue, str);
   }

   public abstract Vec2d computeNextCursorPos(Container container, List list);

   public abstract void resetCursorPosition();

   public void onTargetSlotChanged() {
   }



}
