package net.augustus.modules.combat;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.ClickListener;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.utils.ActionRateAccumulator;
import net.augustus.events.EventInventoryClose;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.events.EventClick;
import net.augustus.settings.RangeSetting;
import net.augustus.utils.player.ContainerUtil;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.viaversion.ViaAugustus;
import net.augustus.events.listeners.DropItemListener;
import net.augustus.utils.player.ContainerSlotQuery;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.events.listeners.InventoryCloseListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S0DPacketCollectItem;

public class AutoSoup extends Module implements ClickListener, InventoryCloseListener, SilentSlotListener, PacketListener, DropItemListener {
   private final RangeSetting health;
   private final RandomizedRangeSettings tickDelayAfterClosingGui;
   private final RangeSetting tickDelayBeforeSwitch;
   private final RangeSetting tickDelayBeforeClick;
   private final BooleanValue drop;
   private final BooleanValue inventoryDrop;
   private final DoubleValue dropBowlsIfYouHaveMoreThan;
   private final RangeSetting tickDelayDrop;
   private final RangeSetting tickDelayAfter;
   private final BooleanValue silent;
   private final BooleanValue random;
   private final BooleanValue stopClickingAtSouping;
   private final SettingGroup deprecated;
   private final BooleanValue openInv;
   private final BooleanValue autoOpen;
   private final RandomizedRangeSettings autoOpenDelay;
   private final RandomizedRangeSettings startDelay;
   private final BooleanValue fill;
   private final RandomizedRangeSettings fillDelay;
   private final BooleanValue craft;
   private final DoubleValue reCraftIfSoupsLowerThan;
   private final BooleanValue craftInstant;
   private final RandomizedRangeSettings craftCPS;
   private final BooleanValue autoClose;
   private final RandomizedRangeSettings autoCloseDelay;
   private final ArrayList soupHotbarSlots;
   private boolean soupConsumed;
   private boolean bowlDropped;
   private boolean readyToSoup;
   private boolean healthChanged;
   private int targetSoupSlot;
   private int lastSoupSlot;
   private boolean soupingActive;
   private int soupCycleTicks;
   private int lastHealthChangeTick;
   private int delayBeforeSwitch;
   private int delayBeforeClick;
   private int delayBeforeDrop;
   private int delayAfterClick;
   private int healthThreshold;
   private float lastPlayerHealth;
   private boolean inventoryDirty;
   private boolean clickBlocked;
   private final ActionRateAccumulator actionRateAccumulator;
   private final TimeHelper fillActionTimer;
   private final TimeHelper inventoryStartTimer;
   public boolean performedInventoryAction;
   private final TimeHelper autoOpenTimer;
   private boolean pendingAutoCloseDelay;

   public AutoSoup() {
       super("AutoSoup", "AutoSuppe", new Color(19, 178, 110), Categorys.COMBAT);
      this.health = new RangeSetting(this, "Health", 11.0, 14.0, 1.0, 20.0, 0);
      this.tickDelayAfterClosingGui = (new RandomizedRangeSettings(this, "TickDelayAfterClosingGui", 2.0, 3.0, 1.0, 20.0, 0)).withoutNotInARow();
      this.tickDelayBeforeSwitch = new RangeSetting(this, "TickDelayBeforeSwitch", 1.0, 2.0, 0.0, 7.0, 0);
      this.tickDelayBeforeClick = new RangeSetting(this, "TickDelayBeforeClick", 1.0, 2.0, 0.0, 7.0, 0);
      this.drop = new BooleanValue(this, "Drop", true);
      BooleanValue booleanValue = new BooleanValue(this, "InventoryDrop", false);
      BooleanValue drop2 = this.drop;
      BooleanValue booleanValue2 = drop2;
      this.inventoryDrop = (BooleanValue)booleanValue.withVisibility(() -> booleanValue2.getValue());
      DoubleValue dropBowlsIfYouHaveMoreThan2 = new DoubleValue(this, "DropBowlsIfYouHaveMoreThan", 0.0, 0.0, 64.0, 0);
      drop2 = this.drop;
      BooleanValue booleanValue3 = drop2;
      this.dropBowlsIfYouHaveMoreThan = dropBowlsIfYouHaveMoreThan2.visibleWhen(() -> booleanValue3.getValue());
      RangeSetting rangeSetting = new RangeSetting(this, "TickDelayDrop", 1.0, 2.0, 0.0, 7.0, 0);
      drop2 = this.drop;
      BooleanValue booleanValue4 = drop2;
      this.tickDelayDrop = rangeSetting.visibleWhen(() -> booleanValue4.getValue());
      this.tickDelayAfter = new RangeSetting(this, "TickDelayAfter", 1.0, 2.0, 0.0, 7.0, 0);
      this.silent = new BooleanValue(this, "Silent", true);
      this.random = new BooleanValue(this, "Random", true);
      this.stopClickingAtSouping = new BooleanValue(this, "StopClickingAtSouping", false);
      this.deprecated = new SettingGroup(this, "Deprecated");
      this.openInv = new BooleanValue(this, "OpenInv", true, this.deprecated);
      this.autoOpen = new BooleanValue(this, "AutoOpen", false, this.deprecated);
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(this, "AutoOpenDelay", 150.0, 250.0, 0.0, 1000.0, 0, this.deprecated)).withoutNotInARow();
      BooleanValue autoOpen2 = this.autoOpen;
      BooleanValue booleanValue5 = autoOpen2;
      this.autoOpenDelay = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> booleanValue5.getValue());
      this.startDelay = (new RandomizedRangeSettings(this, "StartDelay", 150.0, 200.0, 0.0, 1000.0, 0, this.deprecated)).withoutNotInARow();
      this.fill = new BooleanValue(this, "Fill", true, this.deprecated);
      RandomizedRangeSettings randomizedRangeSettings2 = (new RandomizedRangeSettings(this, "FillDelay", 50.0, 100.0, 0.0, 500.0, 0, this.deprecated)).withoutNotInARow();
      autoOpen2 = this.fill;
      BooleanValue booleanValue6 = autoOpen2;
      this.fillDelay = (RandomizedRangeSettings)randomizedRangeSettings2.withVisibility(() -> booleanValue6.getValue());
      this.craft = new BooleanValue(this, "Craft", true, this.deprecated);
      DoubleValue reCraftIfSoupsLowerThan2 = new DoubleValue(this, "ReCraftIfSoupsLowerThan", 4.0, 0.0, 20.0, 0, this.deprecated);
      drop2 = this.craft;
      BooleanValue booleanValue7 = drop2;
      this.reCraftIfSoupsLowerThan = reCraftIfSoupsLowerThan2.visibleWhen(() -> booleanValue7.getValue());
      BooleanValue booleanValue8 = new BooleanValue(this, "CraftInstant", false, this.deprecated);
      drop2 = this.craft;
      BooleanValue booleanValue9 = drop2;
      this.craftInstant = (BooleanValue)booleanValue8.withVisibility(() -> booleanValue9.getValue());
      this.craftCPS = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "CraftCPS", 13.0, 16.0, 0.0, 20.0, 0, this.deprecated)).withoutNotInARow().withVisibility(() -> this.isCraftCpsVisible());
      BooleanValue booleanValue10 = new BooleanValue(this, "AutoClose", true, this.deprecated);
      drop2 = this.fill;
      BooleanValue booleanValue11 = drop2;
      this.autoClose = (BooleanValue)booleanValue10.withVisibility(() -> booleanValue11.getValue());
      this.autoCloseDelay = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "AutoCloseDelay", 150.0, 250.0, 0.0, 1000.0, 0, this.deprecated)).withoutNotInARow().withVisibility(() -> this.isAutoCloseDelayVisible());
      this.soupHotbarSlots = new ArrayList();
      this.soupConsumed = false;
      this.bowlDropped = false;
      this.readyToSoup = true;
      this.healthChanged = true;
      this.soupingActive = false;
      this.soupCycleTicks = 0;
      this.lastHealthChangeTick = 0;
      this.delayBeforeSwitch = 1;
      this.delayBeforeClick = 1;
      this.delayBeforeDrop = 1;
      this.delayAfterClick = 1;
      this.healthThreshold = 13;
      this.lastPlayerHealth = 0.0F;
      this.inventoryDirty = false;
      this.clickBlocked = false;
      this.actionRateAccumulator = new ActionRateAccumulator(20, 0.0);
      this.fillActionTimer = new TimeHelper();
      this.inventoryStartTimer = new TimeHelper();
      this.performedInventoryAction = false;
      this.autoOpenTimer = new TimeHelper();
      this.pendingAutoCloseDelay = true;
   }

   @Override
   public void onEnable() {
      this.fillDelay.nextValue();
      this.targetSoupSlot = -1;
      this.resetSoupCycle();
      this.readyToSoup = true;
      this.inventoryDirty = false;
      this.performedInventoryAction = false;
      this.tickDelayAfterClosingGui.nextValue();
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7, 20);
      this.toggleEventSubscription(52, 3);
      this.toggleEventSubscription(34);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(9);
   }

   @Override
   public void onPostSilentSlot() {
      if (!mc.playerController.isInCreativeMode()) {
         if (this.inventoryDirty && mc.thePlayer != null && mc.isNotInGui() && mc.thePlayer.ticksExisted > 50 && mc.thePlayer.getCurrentHealth() > 0.0F) {
         }

         if (this.autoOpen.getValue() && mc.isNotInGui()) {
            boolean flag = false;

            for (int i = 36; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
               ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
               if (stack != null && stack.getItem().equals(Items.mushroom_stew)) {
                  flag = true;
                  break;
               }
            }

            int n = 0;
            int n2 = 0;
            int n3 = 0;
            int n4 = 0;

            for (int j = 0; j < mc.thePlayer.inventoryContainer.inventorySlots.size(); j++) {
               ItemStack stack2 = mc.thePlayer.inventoryContainer.getSlot(j).getStack();
               if (stack2 != null) {
                  Item item = stack2.getItem();
                  if (item.equals(Items.bowl)) {
                     n += stack2.stackSize;
                  } else if (item.equals(Item.getItemById(40))) {
                     n2 += stack2.stackSize;
                  } else if (item.equals(Item.getItemById(39))) {
                     n3 += stack2.stackSize;
                  } else if (item.equals(Items.mushroom_stew)) {
                     n4++;
                  }
               }
            }

            if (!flag && (n4 > 0 || n3 > 0 && n2 > 0 && n > 0)) {
               if (this.autoOpenTimer.reached((long)this.autoOpenDelay.getLastIntValue())) {
                  ViaAugustus.sendOpenInventoryPacketIfLegacy();
                  mc.displayGuiScreen(new GuiInventory(mc.thePlayer));
                  this.autoOpenTimer.reset();
                  this.autoOpenDelay.nextValue();
               }
            } else {
               this.autoOpenTimer.reset();
            }
         } else {
            this.autoOpenTimer.reset();
         }

         this.targetSoupSlot = this.selectSoupHotbarSlot();
         if (this.targetSoupSlot > -1 && this.targetSoupSlot < 9) {
            SilentSlotManager.requestSlot(this, (byte)5, this.targetSoupSlot, this.silent.getValue());
         }
      }
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S07PacketRespawn) {
         this.inventoryDirty = true;
         this.resetSoupCycle();
      }

      if (packet instanceof S0DPacketCollectItem) {
         this.inventoryDirty = true;
      }
   }

   @Override
   public void onClick(EventClick event) {
      if (!mc.playerController.isInCreativeMode()) {
         this.clickBlocked = event.isIgnoreVanillaClicks() || event.isBlockAllClicks() || !SilentSlotManager.isOwnedBy(this) && SilentSlotManager.getActiveModule();
         if (SilentSlotManager.isOwnedBy(this) && this.stopClickingAtSouping.getValue()) {
            event.setBlockAllClicks(true);
         }

         if (mc.isNotInGui() && !this.clickBlocked) {
            boolean flag2 = false;
            if (this.targetSoupSlot > -1) {
               if (this.soupCycleTicks > this.delayBeforeClick && !mc.thePlayer.isUsingItem() && !this.soupConsumed) {
                  if (this.stopClickingAtSouping.getValue()) {
                     event.clearLeftClicks();
                     event.setLeftMouseDown(false);
                     event.setBlockAllClicks(false);
                     event.setIgnoreVanillaClicks(true);
                  }

                  event.addRightClicks((byte)1);
                  event.setRightClickAction(() -> this.consumeSoupAndDropBowl());
                  flag2 = true;
                  this.soupConsumed = true;
               }

               if (!flag2) {
                  int n = this.shouldDropBowl() ? this.delayBeforeDrop : 0;
                  if (n == 0 && this.soupCycleTicks > this.delayBeforeClick + n) {
                     if (this.shouldDropBowl() && this.soupConsumed && !this.bowlDropped) {
                        if (this.inventoryDrop.getValue()) {
                           mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, this.targetSoupSlot + 36, 1, 4, mc.thePlayer);
                        } else {
                           mc.thePlayer.dropOneItem(true);
                        }

                        this.bowlDropped = true;
                     }
                  }

                  if (this.soupCycleTicks > this.delayBeforeClick + n + this.delayAfterClick && this.soupConsumed) {
if (!this.bowlDropped) {
if (!this.shouldDropBowl()) {
this.resetSoupCycle();
}
} else {
this.resetSoupCycle();
}
}

                  this.soupCycleTicks++;
               }
            }

            if (!flag2) {
               this.soupCycleTicks++;
            }
         }
      }
   }

   @Override
   public void onDropItem() {
      if (!mc.playerController.isInCreativeMode()) {
         if (mc.isNotInGui() && this.targetSoupSlot > -1 && !this.clickBlocked) {
            int n = this.shouldDropBowl() ? this.delayBeforeDrop : 0;
            if (n > 0 && this.soupCycleTicks > this.delayBeforeClick + n) {
               if (this.shouldDropBowl() && this.soupConsumed && !this.bowlDropped) {
                  if (this.inventoryDrop.getValue()) {
                     mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, this.targetSoupSlot + 36, 1, 4, mc.thePlayer);
                  } else {
                     mc.thePlayer.dropOneItem(true);
                  }

                  this.bowlDropped = true;
               }
            }
         }
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

   private void clickInventorySlot(Container container, int n, int n2) {
      mc.playerController.windowClick(container.windowId, n, n2, 0, mc.thePlayer);
      this.fillActionTimer.reset();
      this.performedInventoryAction = true;
   }

   private boolean craftSoupsInInventory() {
      if (mc.currentScreen instanceof GuiInventory && this.craft.getValue()) {
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
                              if (this.isCraftRateLimited()) {
                                 return false;
                              }

                              this.clickInventorySlot(mc.thePlayer.inventoryContainer, i, n3);
                              flag = true;
                              i = 0;
                           }

                           if (flags[0] && flags[1] && flags[2]) {
                              if (this.isCraftRateLimited()) {
                                 return false;
                              }

                              this.shiftClickSlot((byte)0);
                              flag = true;
                              i = 0;
                           }
                        } else {
                           Item item2 = mc.thePlayer.inventory.getItemStack().getItem();

                           for (int k = 0; k < 3; k++) {
                              if (item2.equals(items[k]) && !flags[k]) {
                                 if (this.isCraftRateLimited()) {
                                    return false;
                                 }

                                 int n4 = k + 1;
                                 this.clickInventorySlot(mc.thePlayer.inventoryContainer, n4, (byte)0);
                                 flag = true;
                                 i = 0;
                              }
                           }
                        }
                     } else if (i > 5) {
                        for (int m = 0; m < items.length; m++) {
                           if (flags[m]) {
                              if (this.isCraftRateLimited()) {
                                 return false;
                              }

                              ItemStack stack2 = null;
                              int n6 = 0;

                              for (int idx = 0; idx < 5; idx++) {
                                 ItemStack stack3 = mc.thePlayer.inventoryContainer.getSlot(idx).getStack();
                                 if (stack3 != null && stack3.getItem().equals(items[m])) {
                                    stack2 = stack3;
                                    n6 = idx;
                                    break;
                                 }
                              }

                              for (int index = 9; index < mc.thePlayer.inventoryContainer.inventorySlots.size(); index++) {
                                 ItemStack stack4 = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
                                 if (stack2 != null && (stack4 == null || stack4.getItem().equals(stack2.getItem()) && stack4.stackSize < stack2.getMaxStackSize())) {
                                    this.shiftClickSlot(n6);
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

   private boolean isCraftRateLimited() {
      if (this.craftInstant.getValue()) {
         return false;
      } else {
         boolean shouldTrigger = this.actionRateAccumulator.shouldTrigger(this.craftCPS.getLastIntValue());
         if (shouldTrigger) {
            this.craftCPS.nextValue();
         }

         return !shouldTrigger;
      }
   }

   private boolean refillHotbarSoups() {
      if (!this.fill.getValue()) {
         return true;
      } else if (!this.fillActionTimer.reached((long)this.fillDelay.getLastIntValue())) {
            return false;
         } else {
            boolean flag = false;

            for (int i = 9; i < 36; i++) {
               ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
               if (stack == null) {
                  flag = true;
                  break;
               }

               if (stack.getItem().equals(Items.bowl) && stack.stackSize < stack.getMaxStackSize()) {
                  flag = true;
                  break;
               }
            }

            if (flag) {
               for (int j = 36; j < mc.thePlayer.inventoryContainer.inventorySlots.size(); j++) {
                  ItemStack stack2 = mc.thePlayer.inventoryContainer.getSlot(j).getStack();
                  if (stack2 != null && stack2.getItem().equals(Items.bowl)) {
                     this.shiftClickSlot(j);
                     if (this.fillDelay.getMinValue() != 0.0) {
                        return false;
                     }

                     if (this.fillDelay.getMaxValue() != 0.0) {
                        return false;
                     }
                     break;
                  }
               }
            }

            int n = 0;

            for (int k = 36; k < mc.thePlayer.inventoryContainer.inventorySlots.size(); k++) {
               if (mc.thePlayer.inventoryContainer.getSlot(k).getStack() == null) {
                  n++;
               }
            }

            for (int m = 0; m < 36 && n > 0; m++) {
               ItemStack stack4 = mc.thePlayer.inventoryContainer.getSlot(m).getStack();
               if (stack4 != null && stack4.getItem().equals(Items.mushroom_stew)) {
                  this.shiftClickSlot(m);
                  n--;
                  if (this.fillDelay.getMinValue() != 0.0) {
                     return false;
                  }

                  if (this.fillDelay.getMaxValue() != 0.0) {
                     return false;
                  }
               }
            }

            return true;
         }
   }

   @Override
   public boolean onPreInventoryClose() {
      if (mc.playerController.isInCreativeMode()) {
         return false;
      } else {
         if (mc.isNotInGui()) {
            this.inventoryStartTimer.reset();
            this.performedInventoryAction = false;
            this.pendingAutoCloseDelay = true;
         }

         if (mc.currentScreen != null && mc.previousScreen == null) {
            this.tickDelayAfterClosingGui.nextValue();
         }

         if (!ContainerUtil.isInventoryOpen() && this.openInv.getValue()) {
            return false;
         } else {
            if ((mc.currentScreen instanceof GuiInventory || !this.openInv.getValue() && mc.isNotInGui() && this.fill.getValue())
               && (this.fill.getValue() || this.craft.getValue())) {
               if (!this.inventoryStartTimer.reached((long)this.startDelay.getLastIntValue())) {
                  return true;
               }

               this.startDelay.nextValue();
               if (!this.craftSoupsInInventory()) {
                  return true;
               }

               if (!this.refillHotbarSoups()) {
                  return true;
               }

               if (this.fill.getValue() && this.autoClose.getValue() && mc.currentScreen != null && this.performedInventoryAction && this.pendingAutoCloseDelay) {
                  this.pendingAutoCloseDelay = false;
                  this.autoCloseDelay.nextValue();
               }
            }

            return false;
         }
      }
   }

   @Override
   public void onPostInventoryClose(EventInventoryClose event) {
      if (!mc.isNotInGui()) {
         if (this.autoClose.getValue() && this.performedInventoryAction && this.fill.getValue()) {
            if (event.canCloseAfterDelay((double)this.autoCloseDelay.getLastIntValue())) {
               mc.thePlayer.closeScreen();
            }
         }
      }
   }

   private boolean canStartSoupSwitch() {
      return (mc.thePlayer.ticksExisted - this.lastHealthChangeTick) >= this.delayBeforeSwitch && this.healthChanged;
   }

   private int selectSoupHotbarSlot() {
      if (mc.thePlayer.getCurrentHealth() != this.lastPlayerHealth) {
         this.healthChanged = true;
         this.lastHealthChangeTick = mc.thePlayer.ticksExisted;
      }

      this.lastPlayerHealth = mc.thePlayer.getCurrentHealth();
      if (mc.currentScreen != null || this.soupHotbarSlots.isEmpty() && this.inventoryDirty) {
         this.inventoryDirty = false;
         this.resetSoupCycle();
         this.targetSoupSlot = -1;
      }

      if (mc.isNotInGui() && mc.getTicksWithoutScreen() >= this.tickDelayAfterClosingGui.getLastIntValue() && mc.thePlayer.getCurrentHealth() < (float)this.healthThreshold && this.readyToSoup && !this.soupConsumed) {
         if (this.canStartSoupSwitch()) {
            this.healthChanged = false;
            this.soupHotbarSlots.clear();

            for (int i = 36; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
               ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
               if (stack != null && stack.getItem().equals(Items.mushroom_stew)) {
                  this.soupHotbarSlots.add(i - 36);
               }
            }

            if (this.random.getValue()) {
               Collections.shuffle(this.soupHotbarSlots);
            }

            this.soupCycleTicks = 0;
            if (!this.soupHotbarSlots.isEmpty()) {
               this.readyToSoup = false;
            }
         }
      }

      return this.soupHotbarSlots.isEmpty() ? -1 : (Integer)this.soupHotbarSlots.get(0);
   }

   private void resetSoupCycle() {
      RandomNumberGenerator instance = DefaultRandomGenerator.instance;
      double minValue = this.health.getMinValue();
      double maxValue = this.health.getMaxValue();
      this.healthThreshold = instance.nextInt(minValue, maxValue);
      RandomNumberGenerator instance2 = DefaultRandomGenerator.instance;
      minValue = this.tickDelayBeforeClick.getMinValue();
      maxValue = this.tickDelayBeforeClick.getMaxValue();
      this.delayBeforeClick = instance2.nextInt(minValue, maxValue);
      RandomNumberGenerator instance3 = DefaultRandomGenerator.instance;
      minValue = this.tickDelayAfter.getMinValue();
      maxValue = this.tickDelayAfter.getMaxValue();
      this.delayAfterClick = instance3.nextInt(minValue, maxValue);
      RandomNumberGenerator instance4 = DefaultRandomGenerator.instance;
      minValue = this.tickDelayDrop.getMinValue();
      maxValue = this.tickDelayDrop.getMaxValue();
      this.delayBeforeDrop = instance4.nextInt(minValue, maxValue);
      RandomNumberGenerator instance5 = DefaultRandomGenerator.instance;
      minValue = this.tickDelayBeforeSwitch.getMinValue();
      maxValue = this.tickDelayBeforeSwitch.getMaxValue();
      this.delayBeforeSwitch = instance5.nextInt(minValue, maxValue);
      this.soupConsumed = false;
      this.bowlDropped = false;
      this.soupHotbarSlots.clear();
      this.readyToSoup = true;
      this.healthChanged = true;
      this.targetSoupSlot = -1;
   }

   private void closeSilentInventory() {
      if (mc.thePlayer.getSpoofedScreen() != null && !this.openInv.getValue() && mc.currentScreen == null && mc.previousScreen == null) {
         mc.thePlayer.sendQueue.addToSendQueue(new C0DPacketCloseWindow(mc.thePlayer.inventoryContainer.windowId));
         mc.thePlayer.setSpoofedScreen(null);
      }
   }

   private void openSilentInventory() {
      if (!this.openInv.getValue() && mc.thePlayer.getSpoofedScreen() == null && mc.currentScreen == null && mc.previousScreen == null) {
         mc.thePlayer.sendQueue.addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT));
         mc.thePlayer.setSpoofedScreen(new GuiInventory(mc.thePlayer));
      }
   }

   private void shiftClickSlot(int n) {
      this.openSilentInventory();
      if (mc.currentScreen instanceof GuiInventory) {
         try {
            Slot slot = mc.thePlayer.inventoryContainer.getSlot(n);
            GuiInventory currentScreen = (GuiInventory)mc.currentScreen;
            currentScreen.forceShiftClick = true;
            currentScreen.mouseClicked(slot.xDisplayPosition + 2 + currentScreen.guiLeft, slot.yDisplayPosition + 2 + currentScreen.guiTop, 0);
         } catch (IOException e) {
            e.printStackTrace();
         }
      } else {
         mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, n, 0, 1, mc.thePlayer);
      }

      this.performedInventoryAction = true;
      this.fillActionTimer.reset();
      this.fillDelay.nextValue();
      this.closeSilentInventory();
   }

   private void swapSlotToHotbar(int n, int n2) {
      this.openSilentInventory();
      this.fillActionTimer.reset();
      this.fillDelay.nextValue();
      mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, n, n2 - 36, 2, mc.thePlayer);
      this.closeSilentInventory();
      this.performedInventoryAction = true;
   }

   private boolean shouldDropBowl() {
      if (this.drop.getValue()) {
         if ((double)ContainerSlotQuery.of(mc.thePlayer.inventoryContainer).filterItemExact(Items.bowl).count() >= Math.max(0.0, this.dropBowlsIfYouHaveMoreThan.getValue())) {
            return true;
         }
      }

      return false;
   }

   private void consumeSoupAndDropBowl() {
      mc.rightClickMouse();
      int n = this.shouldDropBowl() ? this.delayBeforeDrop : 0;
      if (n == 0 && this.soupCycleTicks > this.delayBeforeClick + n) {
         if (this.shouldDropBowl() && this.soupConsumed && !this.bowlDropped) {
            if (this.inventoryDrop.getValue()) {
               mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, this.targetSoupSlot + 36, 1, 4, mc.thePlayer);
            } else {
               mc.thePlayer.dropOneItem(true);
            }

            this.bowlDropped = true;
         }
      }

      if (this.soupCycleTicks > this.delayBeforeClick + n + this.delayAfterClick && this.soupConsumed) {
if (!this.bowlDropped) {
if (!this.shouldDropBowl()) {
this.resetSoupCycle();
}
} else {
this.resetSoupCycle();
}
}

      this.soupCycleTicks++;
   }

   private boolean isAutoCloseDelayVisible() {
      return this.autoClose.getValue() && this.fill.getValue();
   }

   private boolean isCraftCpsVisible() {
      return this.craft.getValue() && !this.craftInstant.getValue();
   }


}
