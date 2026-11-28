package net.augustus.modules.modes.stealer;

import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.math.Vec2d;
import net.augustus.utils.player.ContainerUtil;
import net.augustus.utils.player.InventoryUtil;
import java.util.List;
import net.augustus.modules.player.Stealer;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class StealerLinearMouseMode extends AbstractStealerMouseMode {
   private int cursorX;
   private int cursorY;
   private final RandomizedRangeSettings linearSpeed;
   private final BooleanValue linearIgnoreNeighbours;

   public StealerLinearMouseMode(Stealer stealer, SettingGroup doubleValue) {
       super(stealer, doubleValue, "Linear");
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(this.parentModule, "LinearSpeed", 4.0, 5.0, 1.0, 20.0, 0, this.modeSetting)).withoutNotInARow();
      randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Speed");
      this.linearSpeed = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isSelected());
      this.linearIgnoreNeighbours = (BooleanValue)((BooleanValue)new BooleanValue(this.parentModule, "LinearIgnoreNeighbours", false, this.modeSetting).withDisplayName("IgnoreNeighbours")).withVisibility(() -> this.isSelected());
   }

   @Override
   public Vec2d computeNextCursorPos(Container container, List list) {
      Slot slot = (Slot)list.get(0);
      int slotCenterX = (int)ContainerUtil.getSlotCenterX(slot);
      int slotCenterY = (int)ContainerUtil.getSlotCenterY(container, slot);
      if (this.linearIgnoreNeighbours.getValue()) {
         if (InventoryUtil.getLastCursorPos().subtract(new Vec2d((double)slotCenterX, (double)slotCenterY)).length() < 20.0) {
            this.cursorX = slotCenterX;
            this.cursorY = slotCenterY;
            return new Vec2d((double)this.cursorX, (double)this.cursorY);
         }
      }

      int n = slotCenterX - this.cursorX;
      int n2 = slotCenterY - this.cursorY;
      if (n == 0 && n2 == 0) {
         return new Vec2d((double)this.cursorX, (double)this.cursorY);
      } else {
         this.linearSpeed.nextValue();
         double d = Math.sqrt((double)(n * n + n2 * n2));
         if (d <= 5.0) {
            this.cursorX = slotCenterX;
            this.cursorY = slotCenterY;
         } else {
            double d2 = (double)n / d;
            double d3 = (double)n2 / d;
            this.cursorX = this.cursorX + (int)Math.round(d2 * (double)this.linearSpeed.getLastIntValue());
            this.cursorY = this.cursorY + (int)Math.round(d3 * (double)this.linearSpeed.getLastIntValue());
         }

         return new Vec2d((double)this.cursorX, (double)this.cursorY);
      }
   }

   @Override
   public void resetCursorPosition() {
      ScaledResolution sr = new ScaledResolution(mc);
      this.cursorX = sr.getScaledWidth() / 2;
      this.cursorY = sr.getScaledHeight() / 2;
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
