package net.augustus.modules.legit;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.input.KeyboardInputUtil;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.utils.ActionRateAccumulator;
import net.augustus.events.EventInventoryClick;
import net.augustus.utils.input.MouseInputUtil;
import net.augustus.events.listeners.InventoryClickListener;
import net.augustus.utils.TickCounter;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Mouse;

public class InventoryClicker extends Module implements TickListener, InventoryClickListener {
   private final RandomizedRangeSettings cps;
   private final RandomizedRangeSettings cpsRecalculateDelay;
   private final RandomizedRangeSettings dcps;
   private final RandomizedRangeSettings dcpsRecalculateDelay;
   private final ActionRateAccumulator cpsAccumulator;
   private final ActionRateAccumulator dcpsAccumulator;
   private final TickCounter cpsRecalculateCounter;
   private final TickCounter dcpsRecalculateCounter;
   private final BooleanValue rightClick;
   private final BooleanValue forceClickOnItem;
   private final BooleanValue onlyOnShiftClick;
   private final BooleanValue onlyOnLeftClick;
   private boolean wasHotbarFullForHoveredItem;

   public InventoryClicker() {
       super("InventoryClicker", "AutomatischesInventarKlicken", new Color(119, 234, 247), Categorys.LEGIT);
      this.cps = new RandomizedRangeSettings(this, " CPS", 16.0, 17.0, 0.0, 40.0, 0);
      this.cpsRecalculateDelay = (new RandomizedRangeSettings(this, " CPS recalculate delay", 3.0, 8.0, 0.0, 30.0, 0)).withoutNotInARow();
      this.dcps = new RandomizedRangeSettings(this, " DCPS", 3.0, 5.0, 0.0, 20.0, 0);
      this.dcpsRecalculateDelay = (new RandomizedRangeSettings(this, " DCPS recalculate delay", 3.0, 8.0, 0.0, 30.0, 0)).withoutNotInARow();
      this.cpsAccumulator = new ActionRateAccumulator(20, 0.0);
      this.dcpsAccumulator = new ActionRateAccumulator(20, 0.0);
      this.cpsRecalculateCounter = new TickCounter();
      this.dcpsRecalculateCounter = new TickCounter();
      this.rightClick = new BooleanValue(this, "RightClick", false);
      this.forceClickOnItem = new BooleanValue(this, "ForceClickOnItem", false);
      this.onlyOnShiftClick = new BooleanValue(this, "OnlyOnShiftClick", true);
      BooleanValue booleanValue = new BooleanValue(this, "OnlyOnLeftClick", true);
      BooleanValue onlyOnShiftClick2 = this.onlyOnShiftClick;
      this.onlyOnLeftClick = (BooleanValue)booleanValue.withVisibility(() -> onlyOnShiftClick2.getValue());
      this.wasHotbarFullForHoveredItem = false;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(51);
   }

   @Override
   public void onLateTick() {
      this.cpsRecalculateCounter.increment();
      this.dcpsRecalculateCounter.increment();
   }

   public void onPreClick() {
   }

   public void onPostClick() {
   }

   @Override
   public void onInventoryClick(EventInventoryClick event) {
      if (mc.currentScreen instanceof GuiContainer
         && (!this.onlyOnShiftClick.getValue() || KeyboardInputUtil.isKeyDown(42) || KeyboardInputUtil.isKeyDown(54))
         && (!this.onlyOnShiftClick.getValue() || !this.onlyOnLeftClick.getValue() || MouseInputUtil.isButtonDown(0))) {
         if (this.onlyOnShiftClick.getValue() && this.onlyOnLeftClick.getValue()) {
            event.setOverrideMouseInput(true);
         }

         GuiContainer currentScreen = (GuiContainer)mc.currentScreen;
         TickCounter cpsRecalculateCounter2 = this.cpsRecalculateCounter;
         if (cpsRecalculateCounter2.hasReached(this.cpsRecalculateDelay.getLastIntValue())) {
            this.cpsRecalculateCounter.reset();
            this.cpsRecalculateDelay.nextValue();
            this.cps.nextValue();
         }

         cpsRecalculateCounter2 = this.dcpsRecalculateCounter;
         if (cpsRecalculateCounter2.hasReached(this.dcpsRecalculateDelay.getLastIntValue())) {
            this.dcpsRecalculateCounter.reset();
            this.dcpsRecalculateDelay.nextValue();
            this.dcps.nextValue();
         }

         Slot slotUnderMouse = this.getSlotUnderMouse(currentScreen, Mouse.getX(), Mouse.getY());
         boolean isHotbarFullForSlot = this.isHotbarFullForSlot(currentScreen, slotUnderMouse);
         if (!isHotbarFullForSlot && !this.wasHotbarFullForHoveredItem) {
            this.wasHotbarFullForHoveredItem = false;
            int n = this.cpsAccumulator.accumulate(this.cps.getLastIntValue());
            if (n > 0) {
               ActionRateAccumulator dcpsAccumulator2 = this.dcpsAccumulator;
               int lastIntValue4 = this.dcps.getLastIntValue();
               n += dcpsAccumulator2.accumulate(lastIntValue4);
            }

            if (n == 0 && this.forceClickOnItem.getValue() && slotUnderMouse != null && slotUnderMouse.getHasStack()) {
               n++;
            }

            for (int i = 0; i < n; i++) {
               if (this.rightClick.getValue()) {
                  event.incrementRightClicks();
               } else {
                  event.incrementLeftClicks();
               }
            }
         } else {
            this.wasHotbarFullForHoveredItem = isHotbarFullForSlot;
         }
      }
   }

   private Slot getSlotUnderMouse(GuiContainer guiContainer, int n, int n2) {
      ScaledResolution sr = new ScaledResolution(mc);

      for (int i = 0; i < guiContainer.inventorySlots.inventorySlots.size(); i++) {
         Slot slot = guiContainer.inventorySlots.getSlot(i);
         int scaledWidth = sr.getScaledWidth();
         int scaledHeight = sr.getScaledHeight();
         if (guiContainer.isMouseOverSlot(slot, n * scaledWidth / mc.displayWidth, scaledHeight - n2 * scaledHeight / mc.displayHeight - 1)) {
            return slot;
         }
      }

      return null;
   }

   private boolean isHotbarFullForSlot(GuiContainer guiContainer, Slot slot) {
      if (guiContainer instanceof GuiInventory && slot != null && !this.rightClick.getValue()) {
         ItemStack stack = slot.getStack();
         if (stack == null) {
            return false;
         } else {
            for (int i = 36; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
               if (slot.equals(mc.thePlayer.inventoryContainer.getSlot(i))) {
                  return false;
               }
            }

            for (int j = 36; j < mc.thePlayer.inventoryContainer.inventorySlots.size(); j++) {
               ItemStack stack2 = mc.thePlayer.inventoryContainer.getSlot(j).getStack();
               if (stack2 == null) {
                  return false;
               }

               if (stack2.getItem().getClass().equals(stack.getItem().getClass()) && stack.isStackable() && stack2.stackSize < stack2.getMaxStackSize()) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

}
