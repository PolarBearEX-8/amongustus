package net.augustus.modules.legit;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.utils.ActionRateAccumulator;
import net.augustus.events.EventInventoryClose;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.utils.player.ContainerUtil;
import net.augustus.viaversion.ViaAugustus;
import net.augustus.utils.player.InventoryUtil;
import net.augustus.settings.options.RefillItemTypeOption;
import net.augustus.utils.player.ContainerSlotQuery;
import net.augustus.events.listeners.InventoryCloseListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Refill extends Module implements SilentSlotListener, InventoryCloseListener, RenderListener {
   private final BooleanValue autoOpen;
   private final RandomizedRangeSettings autoOpenDelay;
   private final BooleanValue autoClose;
   private final RandomizedRangeSettings autoCloseDelay;
   private final RandomizedRangeSettings startDelay;
   private final MultiOptionSetting types;
   private final BooleanValue blockInvActions;
   private final SettingGroup craftGroup;
   private final BooleanValue craft;
   private final DoubleValue reCraftIfSoupsLowerThan;
   private final BooleanValue craftInstant;
   private final DoubleValue maxMouseMoveSpeedCraft;
   private final RandomizedRangeSettings craftCPS;
   private final SettingGroup refillGroup;
   private final BooleanValue refillInstant;
   private final DoubleValue maxMouseMoveSpeedRefill;
   private final RandomizedRangeSettings refillCPS;
   private final RandomizedRangeSettings missClickRate;
   private final RandomizedRangeSettings failRate;
   private final RandomizedRangeSettings doubleClickRate;
   private final StringValue sequence;
   private final TimeHelper inventoryOpenTimer;
   private final ActionRateAccumulator missClickAccumulator;
   private final ActionRateAccumulator failClickAccumulator;
   private final ActionRateAccumulator doubleClickAccumulator;
   private int lastClickedSlot;
   private final ActionRateAccumulator clickRateAccumulator;
   private double refillMouseMoveBudget;
   private double craftMouseMoveBudget;
   private boolean performedAction;
   private final TimeHelper autoOpenTimer;
   private final TimeHelper autoCloseTimer;
   private boolean awaitingAutoCloseStart;
   private final TimeHelper lastInventoryActionTimer;

   public Refill() {
       super("Refill", "Auffüllen", new Color(66, 203, 245), Categorys.LEGIT);
      this.autoOpen = new BooleanValue(this, "AutoOpen", false);
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(this, "AutoOpenDelay", 150.0, 250.0, 0.0, 1000.0, 0)).withoutNotInARow();
      BooleanValue autoOpen2 = this.autoOpen;
      BooleanValue booleanValue = autoOpen2;
      this.autoOpenDelay = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> booleanValue.getValue());
      this.autoClose = new BooleanValue(this, "AutoClose", true);
      RandomizedRangeSettings randomizedRangeSettings2 = (new RandomizedRangeSettings(this, "AutoCloseDelay", 150.0, 250.0, 0.0, 1000.0, 0)).withoutNotInARow();
      autoOpen2 = this.autoClose;
      BooleanValue booleanValue2 = autoOpen2;
      this.autoCloseDelay = (RandomizedRangeSettings)randomizedRangeSettings2.withVisibility(() -> booleanValue2.getValue());
      this.startDelay = (new RandomizedRangeSettings(this, "StartDelay", 200.0, 260.0, 0.0, 1000.0, 0)).withoutNotInARow();
      this.types = new MultiOptionSetting(this, "Types", RefillItemTypeOption.values());
      this.blockInvActions = new BooleanValue(this, "BlockInvActions", false);
      this.craftGroup = ((SettingGroup)new SettingGroup(this, "CraftGroup", true).withDisplayName("Craft")).visibleWhen(RefillItemTypeOption.SOUPS::isSelected);
      this.craft = new BooleanValue(this, "Craft", true, this.craftGroup);
      DoubleValue reCraftIfSoupsLowerThan2 = new DoubleValue(this, "ReCraftIfSoupsLowerThan", 4.0, 0.0, 20.0, 0, this.craftGroup);
      autoOpen2 = this.craft;
      BooleanValue booleanValue3 = autoOpen2;
      this.reCraftIfSoupsLowerThan = reCraftIfSoupsLowerThan2.visibleWhen(() -> booleanValue3.getValue());
      BooleanValue booleanValue4 = new BooleanValue(this, "CraftInstant", false, this.craftGroup);
      autoOpen2 = this.craft;
      BooleanValue booleanValue5 = autoOpen2;
      this.craftInstant = (BooleanValue)booleanValue4.withVisibility(() -> booleanValue5.getValue());
      this.maxMouseMoveSpeedCraft = new DoubleValue(this, "MaxMouseMoveSpeedCraft", 19.0, 0.0, 200.0, 0, this.craftGroup).named("MaxMouseMoveSpeed").visibleWhen(() -> this.isCraftMouseSpeedVisible());
      RandomizedRangeSettings randomizedRangeSettings3 = (new RandomizedRangeSettings(this, "CraftCPS", 13.0, 16.0, 0.0, 20.0, 0, this.craftGroup)).withoutNotInARow();
      SettingVisibilityCondition condition7 = () -> {
         return this.craft.getValue() && !this.craftInstant.getValue();
      };
      this.craftCPS = (RandomizedRangeSettings)randomizedRangeSettings3.withVisibility(condition7);
      this.refillGroup = (SettingGroup)new SettingGroup(this, "RefillGroup", true).withDisplayName("Refill");
      this.refillInstant = new BooleanValue(this, "RefillInstant", false, this.refillGroup);
      this.maxMouseMoveSpeedRefill = new DoubleValue(this, "MaxMouseMoveSpeedRefill", 19.0, 0.0, 200.0, 0, this.refillGroup).named("MaxMouseMoveSpeed").visibleWhen(() -> this.isRefillMouseSpeedVisible());
      this.refillCPS = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "RefillCPS", 10.0, 15.0, 0.0, 20.0, 0, this.refillGroup)).withoutNotInARow().withVisibility(() -> this.isRefillCpsVisible());
      this.missClickRate = (new RandomizedRangeSettings(this, "MissClickRate", 5.0, 10.0, 0.0, 100.0, 0, this.refillGroup)).withoutNotInARow();
      this.failRate = (new RandomizedRangeSettings(this, "FailRate", 5.0, 10.0, 0.0, 100.0, 0, this.refillGroup)).withoutNotInARow();
      this.doubleClickRate = (new RandomizedRangeSettings(this, "DoubleClickRate", 5.0, 10.0, 0.0, 100.0, 0, this.refillGroup)).withoutNotInARow();
      this.sequence = new StringValue(this, "Sequence", "Basic", new String[]{"Basic", "Nearest", "Random"}, this.refillGroup);
      this.inventoryOpenTimer = new TimeHelper();
      this.missClickAccumulator = new ActionRateAccumulator(100, 0.0);
      this.failClickAccumulator = new ActionRateAccumulator(100, 0.0);
      this.doubleClickAccumulator = new ActionRateAccumulator(100, 0.0);
      this.lastClickedSlot = -1;
      this.clickRateAccumulator = new ActionRateAccumulator(20, 0.0);
      this.refillMouseMoveBudget = 0.0;
      this.craftMouseMoveBudget = 0.0;
      this.performedAction = false;
      this.autoOpenTimer = new TimeHelper();
      this.autoCloseTimer = new TimeHelper();
      this.awaitingAutoCloseStart = true;
      this.lastInventoryActionTimer = new TimeHelper();
   }

   @Override
   public void onEnable() {
      this.performedAction = false;
      this.awaitingAutoCloseStart = true;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(52, 2);
      this.toggleEventSubscription(34);
   }

   @Override
   public void onPostSilentSlot() {
      if (this.autoOpen.getValue() && mc.currentScreen == null && !mc.playerController.isInCreativeMode()) {
         RefillItemTypeOption soups = RefillItemTypeOption.SOUPS;
         int size2 = soups.findMatchingSlots((byte)36, mc.thePlayer.inventoryContainer.inventorySlots.size()).size();
         soups = RefillItemTypeOption.HEAL_POTS;
         int size4 = soups.findMatchingSlots((byte)36, mc.thePlayer.inventoryContainer.inventorySlots.size()).size();
         int n;
         if (RefillItemTypeOption.SOUPS.isSelected()) {
            n = RefillItemTypeOption.SOUPS.findMatchingSlots((byte)9, (byte)36).size();
         } else {
            n = 0;
         }

         int n2 = n;
         int n3;
         if (RefillItemTypeOption.HEAL_POTS.isSelected()) {
            n3 = RefillItemTypeOption.HEAL_POTS.findMatchingSlots((byte)9, (byte)36).size();
         } else {
            n3 = 0;
         }

         int n4 = n3;
         long l;
         if (RefillItemTypeOption.SOUPS.isSelected()) {
            l = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer).filterItemExact(Items.bowl).count();
         } else {
            l = 0L;
         }

         long l2 = l;
         long l3;
         if (RefillItemTypeOption.SOUPS.isSelected()) {
            l3 = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer).filterItemExact(Item.getItemById(40)).count();
         } else {
            l3 = 0L;
         }

         long l4 = l3;
         long l5;
         if (RefillItemTypeOption.SOUPS.isSelected()) {
            l5 = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer).filterItemExact(Item.getItemById(39)).count();
         } else {
            l5 = 0L;
         }

         long l6 = l5;
         if (size2 == 0 && (n2 > 0 || l6 > 0L && l4 > 0L && l2 > 0L && this.craft.getValue()) || size4 == 0 && n4 > 0) {
            boolean flag = false;

            for (int i = 36; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
               ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
               if (stack == null || stack.getItem() == Items.bowl) {
                  flag = true;
                  break;
               }
            }

            if (flag) {
               if (this.autoOpenTimer.reached((long)this.autoOpenDelay.getLastIntValue())) {
                  ViaAugustus.sendOpenInventoryPacketIfLegacy();
                  mc.displayGuiScreen(new GuiInventory(mc.thePlayer));
                  this.autoOpenTimer.reset();
                  this.autoOpenDelay.nextValue();
               }
            }
         } else {
            this.autoOpenTimer.reset();
         }
      } else {
         this.autoOpenTimer.reset();
      }
   }

   @Override
   public boolean onPreInventoryClose() {
      if (!ContainerUtil.isInventoryOpen()) {
         this.awaitingAutoCloseStart = true;
         this.performedAction = false;
         this.lastClickedSlot = -1;
         this.inventoryOpenTimer.reset();
         this.resetMouseMoveBudgets();
         if (mc.previousScreen != null) {
            this.startDelay.nextValue();
         }

         return false;
      } else {
         if (!this.inventoryOpenTimer.reached((long)this.startDelay.getLastIntValue())) {
            this.lastInventoryActionTimer.reset();
            return true;
         } else {
            boolean flag = this.processRefill();
            if (flag) {
               this.lastInventoryActionTimer.reset();
            }

            return flag;
         }
      }
   }

   @Override
   public void onPostInventoryClose(EventInventoryClose event) {
      if (!mc.isNotInGui()) {
         if (this.autoClose.getValue() && this.performedAction) {
            if (event.canCloseAfterDelay((double)this.autoCloseDelay.getLastIntValue())) {
               mc.thePlayer.closeScreen();
               this.awaitingAutoCloseStart = true;
            }
         }
      }
   }

   private boolean processRefill() {
      this.craftMouseMoveBudget = this.craftMouseMoveBudget + this.maxMouseMoveSpeedCraft.getValue();
      if (!this.processSoupCrafting()) {
         return true;
      } else {
         this.refillMouseMoveBudget = this.refillMouseMoveBudget + this.maxMouseMoveSpeedRefill.getValue();
         boolean flag = false;

         for (int i = 9; i < 36; i++) {
            ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if (stack == null || stack.getItem().equals(Items.bowl) && stack.stackSize < stack.getMaxStackSize()) {
               flag = true;
               break;
            }
         }

         if (flag) {
            for (int j = 36; j < mc.thePlayer.inventoryContainer.inventorySlots.size(); j++) {
               ItemStack stack2 = mc.thePlayer.inventoryContainer.getSlot(j).getStack();
               if (stack2 != null && stack2.getItem().equals(Items.bowl)) {
                  if (this.isRefillClickDelayed(j)) {
                     return true;
                  }

                  this.clickRefillSlot(j);
               }
            }
         }

         int n = 0;

         for (int k = 36; k < mc.thePlayer.inventoryContainer.inventorySlots.size(); k++) {
            if (mc.thePlayer.inventoryContainer.getSlot(k).getStack() == null) {
               n++;
            }
         }

         ArrayList list = new ArrayList();
         int n2 = n;

         for (RefillItemTypeOption refillItemTypeOption : RefillItemTypeOption.values()) {

            for (int n3 : (Iterable<Integer>)(refillItemTypeOption.findMatchingSlots((byte)9, (byte)36))) {
               n2--;
               list.add(n3);
            }

            if (n2 != n) {
               break;
            }
         }

         List list2 = InventoryUtil.sortSlotsByMode(mc.thePlayer.inventoryContainer, list, this.sequence.getSelectedValue());

         for (int m = 0; m < list2.size() && n > 0; m++) {
            int n4 = (Integer)list2.get(m);
            if (this.isRefillClickDelayed(n4)) {
               return true;
            }

            this.clickRefillSlot(n4);
            n--;
         }

         if (this.autoClose.getValue() && !mc.isNotInGui() && this.performedAction) {
            if (this.awaitingAutoCloseStart) {
               this.autoCloseTimer.reset();
               this.awaitingAutoCloseStart = false;
               this.autoCloseDelay.nextValue();
            }

            return false;
         } else {
            return false;
         }
      }
   }

   private boolean processSoupCrafting() {
      if (!RefillItemTypeOption.SOUPS.isSelected()) {
         return true;
      } else if (mc.currentScreen instanceof GuiInventory && this.craft.getValue()) {
         int[] ints = this.countSoupIngredients(mc.thePlayer.inventoryContainer);
         if (ints[1] != 0 && ints[2] != 0 && ints[3] != 0) {
            int n = Math.min(ints[1], Math.min(ints[2], ints[3]));
            int n2 = ints[0];
            if (n <= 0) {
               return true;
            } else {
               int n3 = n > n2 ? 1 : 0;
               Item[] items = new Item[]{Items.bowl, Item.getItemById(40), Item.getItemById(39)};
               boolean[] flags = new boolean[]{false, false, false};
               boolean flag = false;

               for (int i = 1; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
                  if (i == 1) {
                     flags = new boolean[]{false, false, false};
                     n2 = this.countSoupIngredients(mc.thePlayer.inventoryContainer)[0];
                  }

                  ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                  if (stack != null) {
                     Item item = stack.getItem();
                     if (i < 5) {
                        for (int j = 0; j < items.length; j++) {
                           if (items[j].equals(item)) {
                              flags[j] = true;
                           }
                        }
                     }

                     if (n2 > 0 && (double)ints[4] <= this.reCraftIfSoupsLowerThan.getValue()) {
                        if (mc.thePlayer.inventory.getItemStack() == null) {
                           if (i > 8 && (item.equals(items[0]) && !flags[0] || item.equals(items[1]) && !flags[1] || item.equals(items[2]) && !flags[2])
                              )
                            {
                              if (this.isCraftClickDelayed(i)) {
                                 return false;
                              }

                              this.clickContainerSlot(mc.thePlayer.inventoryContainer, i, n3);
                              flag = true;
                              i = 0;
                           }

                           if (flags[0] && flags[1] && flags[2]) {
                              if (this.isCraftClickDelayed((byte)0)) {
                                 return false;
                              }

                              this.simulateInventorySlotClick((byte)0);
                              flag = true;
                              i = 0;
                           }
                        } else {
                           Item item2 = mc.thePlayer.inventory.getItemStack().getItem();

                           for (int k = 0; k < 3; k++) {
                              if (item2.equals(items[k]) && !flags[k]) {
                                 if (this.isCraftClickDelayed(k + 1)) {
                                    return false;
                                 }

                                 int n5 = k + 1;
                                 this.clickContainerSlot(mc.thePlayer.inventoryContainer, n5, (byte)0);
                                 flag = true;
                                 i = 0;
                              }
                           }
                        }
                     } else if (i > 5) {
                        for (int m = 0; m < items.length; m++) {
                           if (flags[m]) {
                              ItemStack stack2 = null;
                              int n7 = 0;

                              for (int idx = 0; idx < 5; idx++) {
                                 ItemStack stack3 = mc.thePlayer.inventoryContainer.getSlot(idx).getStack();
                                 if (stack3 != null && stack3.getItem().equals(items[m])) {
                                    stack2 = stack3;
                                    n7 = idx;
                                    break;
                                 }
                              }

                              for (int index = 9; index < mc.thePlayer.inventoryContainer.inventorySlots.size(); index++) {
                                 ItemStack stack4 = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
                                 if (stack2 != null && (stack4 == null || stack4.getItem().equals(stack2.getItem()) && stack4.stackSize < stack2.getMaxStackSize())) {
                                    if (this.isCraftClickDelayed(n7)) {
                                       return false;
                                    }

                                    this.simulateInventorySlotClick(n7);
                                    flag = true;
                                    i = 0;
                                    break;
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               return !flag;
            }
         } else {
            return true;
         }
      } else {
         return true;
      }
   }

   private int[] countSoupIngredients(Container container) {
      int[] ints = new int[5];

      for (int i = -1; i < container.inventorySlots.size(); i++) {
         ItemStack stack = i == -1 ? mc.thePlayer.inventory.getItemStack() : container.getSlot(i).getStack();
         if (stack == null) {
            if (i > 8) {
               ints[0]++;
            }
         } else {
            Item item = stack.getItem();
            if (item.equals(Items.bowl)) {
               ints[1] += stack.stackSize;
            } else if (item.equals(Item.getItemById(40))) {
               ints[2] += stack.stackSize;
            } else if (item.equals(Item.getItemById(39))) {
               ints[3] += stack.stackSize;
            } else if (item.equals(Items.mushroom_stew) && i != 0) {
               ints[4]++;
            }
         }
      }

      return ints;
   }

   private boolean isCraftClickDelayed(int n) {
      if (this.craftInstant.getValue()) {
         return false;
      } else {
         int n2 = this.clickRateAccumulator.accumulate(this.craftCPS.getLastIntValue());
         Container inventoryContainer = mc.thePlayer.inventoryContainer;
         n2 = this.filterClicksByMouseSpeed(this.craftMouseMoveBudget, inventoryContainer, n, n2);
         if (n2 > 0) {
            this.craftCPS.nextValue();
         }

         return n2 <= 0;
      }
   }

   private boolean isRefillClickDelayed(int n) {
      if (this.refillInstant.getValue()) {
         return false;
      } else {
         int n2 = this.clickRateAccumulator.accumulate(this.refillCPS.getLastIntValue());
         Container inventoryContainer = mc.thePlayer.inventoryContainer;
         n2 = this.filterClicksByMouseSpeed(this.refillMouseMoveBudget, inventoryContainer, n, n2);
         if (n2 > 0) {
            this.refillCPS.nextValue();
         }

         return n2 <= 0;
      }
   }

   private int filterClicksByMouseSpeed(double d, Container container, int n, int n2) {
      Slot slot = container.getSlot(n);
      ScaledResolution sr = new ScaledResolution(mc);
      int n3 = (sr.getScaledWidth() - 176) / 2;
      int n4 = (sr.getScaledHeight() - this.getContainerGuiHeight(container)) / 2;
      double d2 = Math.abs((double)(slot.xDisplayPosition + 2 + n3) - InventoryUtil.getLastCursorPos().x);
      double d3 = Math.abs((double)(slot.yDisplayPosition + 2 + n4) - InventoryUtil.getLastCursorPos().y);
      return d > Math.sqrt(d2 * d2 + d3 * d3) ? n2 : 0;
   }

   private int getContainerGuiHeight(Container container) {
      return 114 + (container.inventorySlots.size() - 36) / 9 * 18;
   }

   private void clickContainerSlot(Container container, int n, int n2) {
      mc.playerController.windowClick(container.windowId, n, n2, 0, mc.thePlayer);
      this.updateVirtualCursorToSlot(container, n);
      this.performedAction = true;
   }

   private void clickRefillSlot(int n) {
      this.performedAction = true;
      if (!this.rollFailClick() || this.refillInstant.getValue()) {
         if (!this.refillInstant.getValue() && this.rollMissClick() && this.lastClickedSlot > 0) {
            Slot slot = mc.thePlayer.inventoryContainer.getSlot(this.lastClickedSlot);
            int xDisplayPosition = slot.xDisplayPosition;
            int yDisplayPosition = slot.yDisplayPosition;
            double maxvalue = Double.MAX_VALUE;
            int n2 = -1;

            for (int i = 9; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
               if (mc.thePlayer.inventoryContainer.getSlot(i).getStack() == null && i != this.lastClickedSlot && i != n2) {
                  Slot slot2 = mc.thePlayer.inventoryContainer.getSlot(i);
                  int n3 = xDisplayPosition - slot2.xDisplayPosition;
                  int n4 = yDisplayPosition - slot2.yDisplayPosition;
                  double d = Math.sqrt((double)(n3 * n3 + n4 * n4));
                  if (d < maxvalue) {
                     maxvalue = d;
                     n2 = i;
                  }
               }
            }

            if (maxvalue < 36.0) {
               this.simulateInventorySlotClick(n2);
               this.lastClickedSlot = n;
               return;
            }
         }

         for (int j = this.rollDoubleClick() ? 2 : 1; j > 0; j--) {
            this.simulateInventorySlotClick(n);
            this.lastClickedSlot = n;
         }
      }
   }

   private void simulateInventorySlotClick(int n) {
      this.updateVirtualCursorToSlot(mc.thePlayer.inventoryContainer, n);

      try {
         Slot slot = mc.thePlayer.inventoryContainer.getSlot(n);
         GuiInventory currentScreen = (GuiInventory)mc.currentScreen;
         currentScreen.forceShiftClick = true;
         currentScreen.mouseClicked(slot.xDisplayPosition + 2 + currentScreen.guiLeft, slot.yDisplayPosition + 2 + currentScreen.guiTop, 0);
      } catch (IOException e) {
         e.printStackTrace();
      }

      this.performedAction = true;
   }

   private boolean rollFailClick() {
      boolean shouldTrigger = this.failClickAccumulator.shouldTrigger(this.failRate.getLastIntValue());
      if (shouldTrigger) {
         this.failRate.nextValue();
      }

      return shouldTrigger;
   }

   private boolean rollMissClick() {
      boolean shouldTrigger = this.missClickAccumulator.shouldTrigger(this.missClickRate.getLastIntValue());
      if (shouldTrigger) {
         this.missClickRate.nextValue();
      }

      return shouldTrigger;
   }

   private boolean rollDoubleClick() {
      boolean shouldTrigger = this.doubleClickAccumulator.shouldTrigger(this.doubleClickRate.getLastIntValue());
      if (shouldTrigger) {
         this.doubleClickRate.nextValue();
      }

      return shouldTrigger;
   }

   private void resetMouseMoveBudgets() {
      this.craftMouseMoveBudget = 0.0;
      this.refillMouseMoveBudget = 0.0;
   }

   private void updateVirtualCursorToSlot(Container container, int n) {
      this.resetMouseMoveBudgets();
      ScaledResolution sr = new ScaledResolution(mc);
      int n2 = (sr.getScaledWidth() - 176) / 2;
      int n3 = (sr.getScaledHeight() - this.getContainerGuiHeight(container)) / 2;
      Slot slot = container.getSlot(n);
      InventoryUtil.setLastCursorPos((double)(slot.xDisplayPosition + 2 + n2), (double)(slot.yDisplayPosition + 2 + n3));
      InventoryUtil.setLastClickedSlot(n);
      this.lastInventoryActionTimer.reset();
   }

   public boolean shouldBlockInventoryActions() {
      if (mc.thePlayer != null && mc.theWorld != null && this.blockInvActions.getValue()) {
         if (!this.lastInventoryActionTimer.reached(500L) && mc.currentScreen instanceof GuiInventory) {
            return true;
         }
      }

      return false;
   }

   private boolean isRefillCpsVisible() {
      return !this.refillInstant.getValue();
   }

   private boolean isRefillMouseSpeedVisible() {
      return !this.refillInstant.getValue();
   }

   private boolean isCraftMouseSpeedVisible() {
      return this.craft.getValue() && !this.craftInstant.getValue();
   }

}
