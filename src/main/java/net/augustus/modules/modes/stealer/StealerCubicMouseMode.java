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
import net.minecraft.util.MathHelper;

public class StealerCubicMouseMode extends AbstractStealerMouseMode {
   private double cursorX;
   private double cursorY;
   private final RandomizedRangeSettings cubicSpeed;
   private final BooleanValue cubicIgnoreNeighbours;

   public StealerCubicMouseMode(Stealer stealer, SettingGroup doubleValue) {
       super(stealer, doubleValue, "Cubic");
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(this.parentModule, "CubicSpeed", 4.0, 5.0, 1.0, 20.0, 0, this.modeSetting)).withoutNotInARow();
      randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Speed");
      this.cubicSpeed = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isSelected());
      this.cubicIgnoreNeighbours = (BooleanValue)((BooleanValue)new BooleanValue(this.parentModule, "CubicIgnoreNeighbours", false, this.modeSetting).withDisplayName("IgnoreNeighbours")).withVisibility(() -> this.isSelected());
   }

   @Override
   public Vec2d computeNextCursorPos(Container container, List list) {
      Slot slot = (Slot)list.get(0);
      int slotCenterX = (int)ContainerUtil.getSlotCenterX(slot);
      int slotCenterY = (int)ContainerUtil.getSlotCenterY(container, slot);
      if (this.cubicIgnoreNeighbours.getValue()) {
         if (InventoryUtil.getLastCursorPos().subtract(new Vec2d((double)slotCenterX, (double)slotCenterY)).length() < 20.0) {
            this.cursorX = (double)slotCenterX;
            this.cursorY = (double)slotCenterY;
            return new Vec2d(this.cursorX, this.cursorY);
         }
      }

      double d = (double)slotCenterX - this.cursorX;
      double d2 = (double)slotCenterY - this.cursorY;
      if (d == 0.0 && d2 == 0.0) {
         return new Vec2d(this.cursorX, this.cursorY);
      } else {
         Vec2d lastCursorPos2 = InventoryUtil.getLastCursorPos();
         Vec2d vec2d2 = new Vec2d((double)slotCenterX, (double)slotCenterY);
         double d3 = MathHelper.clamp_double(1.0 / (lastCursorPos2.subtract(vec2d2).length() / 25.0), 1.0, 0.5);
         double d4 = this.cubicSpeed.nextValue() * d3;
         double d5 = Math.sqrt(d * d + d2 * d2);
         if (d5 <= 5.0) {
            this.cursorX = (double)slotCenterX;
            this.cursorY = (double)slotCenterY;
         } else {
            double d6 = d / d5;
            double d7 = d2 / d5;
            this.cursorX = this.cursorX + (double)((int)Math.round(d6 * d4));
            this.cursorY = this.cursorY + (double)((int)Math.round(d7 * d4));
         }

         return new Vec2d(this.cursorX, this.cursorY);
      }
   }

   @Override
   public void resetCursorPosition() {
      ScaledResolution sr = new ScaledResolution(mc);
      this.cursorX = (double)(sr.getScaledWidth() / 2);
      this.cursorY = (double)(sr.getScaledHeight() / 2);
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

}
