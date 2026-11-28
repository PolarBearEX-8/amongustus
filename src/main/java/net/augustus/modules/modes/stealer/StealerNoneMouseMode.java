package net.augustus.modules.modes.stealer;

import net.augustus.utils.math.Vec2d;
import net.augustus.utils.player.ContainerUtil;
import java.util.List;
import net.augustus.modules.player.Stealer;
import net.augustus.settings.SettingGroup;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class StealerNoneMouseMode extends AbstractStealerMouseMode {
   public StealerNoneMouseMode(Stealer stealer, SettingGroup doubleValue) {
      super(stealer, doubleValue, "None");
   }

   @Override
   public Vec2d computeNextCursorPos(Container container, List list) {
      Slot slot = (Slot)list.get(0);
      int slotCenterX = (int)ContainerUtil.getSlotCenterX(slot);
      int slotCenterY = (int)ContainerUtil.getSlotCenterY(container, slot);
      return new Vec2d((double)slotCenterX, (double)slotCenterY);
   }

   @Override
   public void resetCursorPosition() {
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
      this.resetCursorPosition();
   }

   @Override
   public void onDisable() {
   }
}
