package net.augustus.modules.player;

import java.awt.Color;
import java.util.ArrayList;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.RenderListener;
import net.augustus.ClientManagers;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.events.EventClick;
import net.augustus.settings.RangeSetting;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.world.BlockUtil;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.DropItemListener;
import net.augustus.Augustus;
import net.augustus.utils.player.InventoryUtil;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class InvCleaner extends Module implements ClickListener, TickListener, RenderListener, MoveInputListener, DropItemListener, PacketListener {
   public final StringValue mode;
   public final BooleanValue noMove;
   public final BooleanValue interactCheck;
   public final BooleanValue stopMovement;
   public final DoubleValue startDelay;
   public final RangeSetting sortDelay;
   public final RangeSetting dropDelay;
   public final RangeSetting delayBeforeAutoArmor;
   public final BooleanValue sort;
   public final BooleanValue smartDelay;
   public final BooleanValue stopOnInteraction;
   public final BooleanValue visualize;
   public final DoubleValue failRate;
   public final StringValue sequence;
   public final StringValue eventToClick;
   public final StringValue clickMode;
   private final SettingGroup slots;
   private final StringValue slot1;
   private final StringValue slot2;
   private final StringValue slot3;
   private final StringValue slot4;
   private final StringValue slot5;
   private final StringValue slot6;
   private final StringValue slot7;
   private final StringValue slot8;
   private final StringValue slot9;
   private final TimeHelper actionTimer;
   private final TimeHelper startDelayTimer;
   private final TimeHelper postActionTimer;
   private final TimeHelper dropTimer;
   private final ArrayList<Runnable> queuedActions;
   private boolean interactionBlocked;
   public long nextActionDelayMs;
   private boolean closeWindowSent;
   private int currentTargetSlot;

   public InvCleaner() {
       super("InvCleaner", "InventarLeerer", new Color(32, 94, 103), Categorys.PLAYER);
      this.mode = new StringValue(this, "Mode", "OpenInv", new String[]{"OpenInv", "SpoofInv", "Basic"});
      this.noMove = (BooleanValue)new BooleanValue(this, "NoMove", false).withVisibility(() -> this.isNoMoveVisible());
      this.interactCheck = (BooleanValue)new BooleanValue(this, "InteractCheck", false).withVisibility(() -> this.isInteractCheckVisible());
      this.stopMovement = (BooleanValue)new BooleanValue(this, "StopMovement", false).withVisibility(() -> this.isStopMovementVisible());
      this.startDelay = new DoubleValue(this, "StartDelay", 100.0, 0.0, 1000.0, 0);
      this.sortDelay = new RangeSetting(this, "SortDelay", 70.0, 120.0, 0.0, 400.0, 0);
      this.dropDelay = new RangeSetting(this, "DropDelay", 70.0, 120.0, 0.0, 400.0, 0);
      this.delayBeforeAutoArmor = new RangeSetting(this, "DelayBeforeAutoArmor", 70.0, 120.0, 0.0, 400.0, 0);
      this.sort = new BooleanValue(this, "Sort", true);
      this.smartDelay = new BooleanValue(this, "SmartDelay", false);
      this.stopOnInteraction = new BooleanValue(this, "StopOnInteraction", false);
      this.visualize = new BooleanValue(this, "Visualize", false);
      this.failRate = new DoubleValue(this, "FailRate", 5.0, 0.0, 100.0, 0);
      this.sequence = new StringValue(this, "Sequence", "Basic", new String[]{"Basic", "Nearest", "Random"});
      this.eventToClick = new StringValue(this, "EventToClick", "EventTick", new String[]{"EventTick", "EventRender"});
      this.clickMode = new StringValue(this, "ClickMode", "Basic", new String[]{"Basic"});
      this.slots = new SettingGroup(this, "Slots");
      this.slot1 = new StringValue(this, "Slot1", "Sword", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.slots);
      this.slot2 = new StringValue(this, "Slot2", "Bow", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.slots);
      this.slot3 = new StringValue(this, "Slot3", "Rod", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.slots);
      this.slot4 = new StringValue(this, "Slot4", "None", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.slots);
      this.slot5 = new StringValue(this, "Slot5", "None", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.slots);
      this.slot6 = new StringValue(this, "Slot6", "None", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.slots);
      this.slot7 = new StringValue(this, "Slot7", "None", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.slots);
      this.slot8 = new StringValue(this, "Slot8", "Food", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.slots);
      this.slot9 = new StringValue(this, "Slot9", "Gapple", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.slots);
      this.actionTimer = new TimeHelper();
      this.startDelayTimer = new TimeHelper();
      this.postActionTimer = new TimeHelper();
      this.dropTimer = new TimeHelper();
      this.queuedActions = new ArrayList<>();
      this.closeWindowSent = false;
      this.currentTargetSlot = -1;
   }

   @Override
   public void onEnable() {
      if (mc.thePlayer != null) {
         mc.thePlayer.setSpoofedScreen(mc.currentScreen);
      }

      this.nextActionDelayMs = DefaultRandomGenerator.instance.nextLong(this.sortDelay.getMinValue(), this.sortDelay.getMaxValue());
   }

   @Override
   public void onDisable() {
      this.currentTargetSlot = -1;
      this.queuedActions.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(9);
      this.toggleEventSubscription(21, 100000);
      this.toggleEventSubscription(26);
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (mc.currentScreen == null && this.mode.getSelectedValue().equals("SpoofInv") && mc.thePlayer.getSpoofedScreen() != null && this.stopMovement.getValue()) {
         event.setJump(false);
         event.setSneak(false);
         event.setMoveForward(0.0F);
         event.setMoveStrafe(0.0F);
      }
   }

   @Override
   public void onClick(EventClick event) {
      this.interactionBlocked = false;
      if (this.mode.getSelectedValue().equals("SpoofInv")
         && this.interactCheck.getValue()
         && (
            mc.gameSettings.keyBindUseItem.isKeyDown()
               || mc.gameSettings.keyBindAttack.isKeyDown()
               || mc.gameSettings.keyBindAttack.getPressTime() != 0
               || mc.gameSettings.keyBindUseItem.getPressTime() != 0
               || ClientManagers.moduleManager.killAura.isToggled() && ClientManagers.moduleManager.killAura.target != null
               || BlockUtil.isScaffoldActiveAndValid()
         )) {
         this.closeSpoofedInventory();
         this.interactionBlocked = true;
         this.startDelayTimer.reset();
      }
   }

   @Override
   public void onEarlyTick() {
      this.setSuffix(this.mode.getSelectedValue());
      if (this.eventToClick.getSelectedValue().equals("EventTick") && !this.mode.getSelectedValue().equals("SpoofInv")) {
         this.processInventory();
      }

      if (this.eventToClick.getSelectedValue().equals("EventTick") && this.mode.getSelectedValue().equals("SpoofInv")) {
         if (mc.thePlayer.getSpoofedScreen() instanceof GuiInventory) {
            if (mc.currentScreen != null) {
               this.processInventory();
            }

            this.queuedActions.forEach(Runnable::run);
            this.queuedActions.clear();
            this.closeSpoofedInventory();
         } else if (!this.queuedActions.isEmpty()) {
            this.openSpoofedInventory();
         } else {
            this.processInventory();
         }
      }
   }

   @Override
   public void onDropItem() {
      if (this.eventToClick.getSelectedValue().equals("EventTick") && this.mode.getSelectedValue().equals("SpoofInv")) {
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (this.eventToClick.getSelectedValue().equals("EventRender")) {
         this.processInventory();
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      if (event.getPacket() instanceof C0DPacketCloseWindow) {
         this.closeWindowSent = true;
      }
   }

   private void processInventory() {
      ArrayList list = new ArrayList();
      ArrayList list2 = new ArrayList();
      ArrayList list3 = new ArrayList();
      ArrayList list4 = new ArrayList();
      ArrayList list5 = new ArrayList();
      ArrayList list6 = new ArrayList();
      ArrayList list7 = new ArrayList();
      ArrayList list8 = new ArrayList();
      ArrayList list9 = new ArrayList();
      ArrayList list10 = new ArrayList();
      int n = this.slot1.getSelectedValue().equals("Sword")
         ? 0
         : (
            this.slot2.getSelectedValue().equals("Sword")
               ? 1
               : (
                  this.slot3.getSelectedValue().equals("Sword")
                     ? 2
                     : (
                        this.slot4.getSelectedValue().equals("Sword")
                           ? 3
                           : (
                              this.slot5.getSelectedValue().equals("Sword")
                                 ? 4
                                 : (
                                    this.slot6.getSelectedValue().equals("Sword")
                                       ? 5
                                       : (
                                          this.slot7.getSelectedValue().equals("Sword")
                                             ? 6
                                             : (this.slot8.getSelectedValue().equals("Sword") ? 7 : (this.slot9.getSelectedValue().equals("Sword") ? 8 : -1))
                                       )
                                 )
                           )
                     )
               )
         );
      int n2 = this.slot1.getSelectedValue().equals("Bow")
         ? 0
         : (
            this.slot2.getSelectedValue().equals("Bow")
               ? 1
               : (
                  this.slot3.getSelectedValue().equals("Bow")
                     ? 2
                     : (
                        this.slot4.getSelectedValue().equals("Bow")
                           ? 3
                           : (
                              this.slot5.getSelectedValue().equals("Bow")
                                 ? 4
                                 : (
                                    this.slot6.getSelectedValue().equals("Bow")
                                       ? 5
                                       : (
                                          this.slot7.getSelectedValue().equals("Bow")
                                             ? 6
                                             : (this.slot8.getSelectedValue().equals("Bow") ? 7 : (this.slot9.getSelectedValue().equals("Bow") ? 8 : -1))
                                       )
                                 )
                           )
                     )
               )
         );
      int n3 = this.slot1.getSelectedValue().equals("Rod")
         ? 0
         : (
            this.slot2.getSelectedValue().equals("Rod")
               ? 1
               : (
                  this.slot3.getSelectedValue().equals("Rod")
                     ? 2
                     : (
                        this.slot4.getSelectedValue().equals("Rod")
                           ? 3
                           : (
                              this.slot5.getSelectedValue().equals("Rod")
                                 ? 4
                                 : (
                                    this.slot6.getSelectedValue().equals("Rod")
                                       ? 5
                                       : (
                                          this.slot7.getSelectedValue().equals("Rod")
                                             ? 6
                                             : (this.slot8.getSelectedValue().equals("Rod") ? 7 : (this.slot9.getSelectedValue().equals("Rod") ? 8 : -1))
                                       )
                                 )
                           )
                     )
               )
         );
      int n4 = this.slot1.getSelectedValue().equals("Food")
         ? 0
         : (
            this.slot2.getSelectedValue().equals("Food")
               ? 1
               : (
                  this.slot3.getSelectedValue().equals("Food")
                     ? 2
                     : (
                        this.slot4.getSelectedValue().equals("Food")
                           ? 3
                           : (
                              this.slot5.getSelectedValue().equals("Food")
                                 ? 4
                                 : (
                                    this.slot6.getSelectedValue().equals("Food")
                                       ? 5
                                       : (
                                          this.slot7.getSelectedValue().equals("Food")
                                             ? 6
                                             : (this.slot8.getSelectedValue().equals("Food") ? 7 : (this.slot9.getSelectedValue().equals("Food") ? 8 : -1))
                                       )
                                 )
                           )
                     )
               )
         );
      int n5 = this.slot1.getSelectedValue().equals("Gapple")
         ? 0
         : (
            this.slot2.getSelectedValue().equals("Gapple")
               ? 1
               : (
                  this.slot3.getSelectedValue().equals("Gapple")
                     ? 2
                     : (
                        this.slot4.getSelectedValue().equals("Gapple")
                           ? 3
                           : (
                              this.slot5.getSelectedValue().equals("Gapple")
                                 ? 4
                                 : (
                                    this.slot6.getSelectedValue().equals("Gapple")
                                       ? 5
                                       : (
                                          this.slot7.getSelectedValue().equals("Gapple")
                                             ? 6
                                             : (this.slot8.getSelectedValue().equals("Gapple") ? 7 : (this.slot9.getSelectedValue().equals("Gapple") ? 8 : -1))
                                       )
                                 )
                           )
                     )
               )
         );
      ItemStack stack = null;
      ItemStack stack2 = null;
      ItemStack stack3 = null;
      ItemStack stack4 = null;
      ItemStack stack5 = null;
      ItemStack stack6 = null;
      ItemStack stack7 = null;
      ItemStack stack8 = null;
      ItemStack stack9 = null;
      ItemStack stack10 = null;
      ItemStack stack11 = null;
      ItemStack stack12 = null;
      ItemStack stack13 = null;
      ItemStack stack14 = null;
      ItemStack stack15 = null;
      ItemStack stack16 = null;
      double value = this.startDelay.getValue();
      long l3 = (long)Math.max(value + (double)DefaultRandomGenerator.instance.nextLong(-35L, 35L), 0.0);
      boolean flag = mc.currentScreen instanceof GuiInventory
         || !this.mode.getSelectedValue().equalsIgnoreCase("OpenInv")
            && (mc.currentScreen == null || mc.currentScreen instanceof GuiInventory)
            && (
               !this.noMove.getValue()
                  || this.noMove.getValue() && !MoveUtil.isMoving()
                  || !this.mode.getSelectedValue().equals("SpoofInv")
            );
      if (flag && !this.interactionBlocked) {
         for (int i = 0; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
            ItemStack stack17 = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if (stack17 != null) {
               if (stack17.getItem() instanceof ItemSword) {
                  stack17.setSlotIndex(i);
                  list.add(stack17);
                  if (i == n + 36) {
                     stack17.setSlotIndex(i);
                     stack = stack17;
                  }
               } else if (stack17.getItem() instanceof ItemAxe) {
                  stack17.setSlotIndex(i);
                  list7.add(stack17);
                  if (i == n + 36) {
                     stack17.setSlotIndex(i);
                     stack6 = stack17;
                  }
               } else if (stack17.getItem() instanceof ItemPickaxe) {
                  stack17.setSlotIndex(i);
                  list8.add(stack17);
               } else if (stack17.getItem() instanceof ItemSpade) {
                  stack17.setSlotIndex(i);
                  list9.add(stack17);
               } else if (stack17.getItem() instanceof ItemBlock) {
                  if (this.isUsefulItem(stack17)) {
                     stack17.setSlotIndex(i);
                     list10.add(stack17);
                  }
               } else if (stack17.getItem() instanceof ItemBow) {
                  stack17.setSlotIndex(i);
                  list2.add(stack17);
                  if (i == n2 + 36) {
                     stack17.setSlotIndex(i);
                     stack2 = stack17;
                  }
               } else if (stack17.getItem() instanceof ItemFishingRod) {
                  stack17.setSlotIndex(i);
                  list3.add(stack17);
                  if (i == n3 + 36) {
                     stack17.setSlotIndex(i);
                     stack3 = stack17;
                  }
               } else if (stack17.getItem() instanceof ItemFood) {
                  if (stack17.getItem() == Item.getByNameOrId("golden_apple")) {
                     stack17.setSlotIndex(i);
                     list5.add(stack17);
                     list10.add(stack17);
                     if (i == n5 + 36) {
                        stack17.setSlotIndex(i);
                        stack5 = stack17;
                     }
                  } else if (this.isUsefulItem(stack17)) {
                     stack17.setSlotIndex(i);
                     list4.add(stack17);
                     list10.add(stack17);
                     if (i == n4 + 36) {
                        stack17.setSlotIndex(i);
                        stack4 = stack17;
                     }
                  }
               } else if (stack17.getItem() instanceof ItemPotion) {
                  ItemPotion item = (ItemPotion)stack17.getItem();
                  if (!item.getEffects(stack17).isEmpty()) {
                     PotionEffect potionEffect = (PotionEffect)item.getEffects(stack17).get(0);
                     if (!Potion.potionTypes[potionEffect.getPotionID()].isBadEffect()) {
                        stack17.setSlotIndex(i);
                        list6.add(stack17);
                        list10.add(stack17);
                     }
                  }
               } else if (stack17.getItem() instanceof ItemTool) {
                  if (this.isUsefulItem(stack17)) {
                     stack17.setSlotIndex(i);
                     list10.add(stack17);
                  }
               } else if (this.isKeepableMiscItem(stack17)) {
                  stack17.setSlotIndex(i);
                  list10.add(stack17);
               } else if (!(stack17.getItem() instanceof ItemArmor) && this.isUsefulItem(stack17)) {
                  stack17.setSlotIndex(i);
                  list10.add(stack17);
               }
            }
         }

         for (ItemStack stack18 : (Iterable<ItemStack>)(list)) {
            if (stack18.getSlotIndex() != n + 36) {
               if (stack == null) {
                  if (stack9 != null) {
                     if (this.scoreSword(stack18) > this.scoreSword(stack9)) {
                        stack9 = stack18;
                     } else if (this.scoreSword(stack18) == this.scoreSword(stack9) && stack18.getItemDamage() < stack9.getItemDamage()) {
                        stack9 = stack18;
                     }
                  } else {
                     stack9 = stack18;
                  }
               } else if (stack.getItem() instanceof ItemSword) {
                  if (stack9 != null) {
                     if (this.scoreSword(stack18) > this.scoreSword(stack9)) {
                        stack9 = stack18;
                     } else if (this.scoreSword(stack18) == this.scoreSword(stack9) && stack18.getItemDamage() < stack9.getItemDamage()) {
                        stack9 = stack18;
                     }
                  } else if (this.scoreSword(stack18) > this.scoreSword(stack)) {
                     stack9 = stack18;
                  } else if (this.scoreSword(stack18) == this.scoreSword(stack) && stack18.getItemDamage() < stack.getItemDamage()) {
                     stack9 = stack18;
                  }
               }
            }
         }

         for (ItemStack stack19 : (Iterable<ItemStack>)(list7)) {
            if (stack6 == null) {
               if (stack14 != null) {
                  if (this.scoreCombatTool(stack19) > this.scoreCombatTool(stack14)) {
                     stack14 = stack19;
                  } else if (this.scoreCombatTool(stack19) == this.scoreCombatTool(stack14) && stack19.getItemDamage() < stack14.getItemDamage()) {
                     stack14 = stack19;
                  }
               } else {
                  stack14 = stack19;
               }
            } else if (stack6.getItem() instanceof ItemAxe) {
               if (stack14 != null) {
                  if (this.scoreCombatTool(stack19) > this.scoreCombatTool(stack14)) {
                     stack14 = stack19;
                  } else if (this.scoreCombatTool(stack19) == this.scoreCombatTool(stack14) && stack19.getItemDamage() < stack14.getItemDamage()) {
                     stack14 = stack19;
                  }
               } else if (this.scoreCombatTool(stack19) > this.scoreCombatTool(stack6)) {
                  stack14 = stack19;
               } else if (this.scoreCombatTool(stack19) == this.scoreCombatTool(stack6) && stack19.getItemDamage() < stack6.getItemDamage()) {
                  stack14 = stack19;
               }
            }
         }

         for (ItemStack stack20 : (Iterable<ItemStack>)(list8)) {
            if (stack7 == null) {
               if (stack15 != null) {
                  value = this.scoreMiningTool(stack20);
                  if (value > this.scoreMiningTool(stack15)) {
                     stack15 = stack20;
                  } else {
                     value = this.scoreMiningTool(stack20);
                     if (value == this.scoreMiningTool(stack15) && stack20.getItemDamage() < stack15.getItemDamage()) {
                        stack15 = stack20;
                     }
                  }
               } else {
                  stack15 = stack20;
               }
            } else if (stack7.getItem() instanceof ItemPickaxe) {
               if (stack15 != null) {
                  value = this.scoreMiningTool(stack20);
                  if (value > this.scoreMiningTool(stack15)) {
                     stack15 = stack20;
                  } else {
                     value = this.scoreMiningTool(stack20);
                     if (value == this.scoreMiningTool(stack15) && stack20.getItemDamage() < stack15.getItemDamage()) {
                        stack15 = stack20;
                     }
                  }
               } else {
                  value = this.scoreMiningTool(stack20);
                  if (value > this.scoreMiningTool((ItemStack)stack7)) {
                     stack15 = stack20;
                  } else {
                     value = this.scoreMiningTool(stack20);
                     if (value == this.scoreMiningTool((ItemStack)stack7) && stack20.getItemDamage() < stack7.getItemDamage()) {
                        stack15 = stack20;
                     }
                  }
               }
            }
         }

         for (ItemStack stack21 : (Iterable<ItemStack>)(list9)) {
            if (stack8 == null) {
               if (stack16 != null) {
                  value = this.scoreMiningTool(stack21);
                  if (value > this.scoreMiningTool(stack16)) {
                     stack16 = stack21;
                  } else {
                     value = this.scoreMiningTool(stack21);
                     if (value == this.scoreMiningTool(stack16) && stack21.getItemDamage() < stack16.getItemDamage()) {
                        stack16 = stack21;
                     }
                  }
               } else {
                  stack16 = stack21;
               }
            } else if (stack8.getItem() instanceof ItemSpade) {
               if (stack16 != null) {
                  value = this.scoreMiningTool(stack21);
                  if (value > this.scoreMiningTool(stack16)) {
                     stack16 = stack21;
                  } else {
                     value = this.scoreMiningTool(stack21);
                     if (value == this.scoreMiningTool(stack16) && stack21.getItemDamage() < stack16.getItemDamage()) {
                        stack16 = stack21;
                     }
                  }
               } else {
                  value = this.scoreMiningTool(stack21);
                  if (value > this.scoreMiningTool((ItemStack)stack8)) {
                     stack16 = stack21;
                  } else {
                     value = this.scoreMiningTool(stack21);
                     if (value == this.scoreMiningTool((ItemStack)stack8) && stack21.getItemDamage() < stack8.getItemDamage()) {
                        stack16 = stack21;
                     }
                  }
               }
            }
         }

         for (ItemStack stack22 : (Iterable<ItemStack>)(list2)) {
            if (stack2 == null) {
               if (stack10 != null) {
                  if (this.scoreBow(stack22) > this.scoreBow(stack10)) {
                     stack10 = stack22;
                  }
               } else {
                  stack10 = stack22;
               }
            } else if (stack2.getItem() instanceof ItemBow) {
               if (stack10 != null) {
                  if (this.scoreBow(stack22) > this.scoreBow(stack10)) {
                     stack10 = stack22;
                  }
               } else if (this.scoreBow(stack22) > this.scoreBow(stack2)) {
                  stack10 = stack22;
               }
            }
         }

         for (ItemStack stack23 : (Iterable<ItemStack>)(list3)) {
            if (stack23.getSlotIndex() != n3 + 36) {
               if (stack3 == null) {
                  if (stack11 != null) {
                     if (stack23.getItemDamage() < stack11.getItemDamage()) {
                        stack11 = stack23;
                     }
                  } else {
                     stack11 = stack23;
                  }
               } else if (stack3.getItem() instanceof ItemFishingRod) {
                  if (stack11 != null) {
                     if (stack23.getItemDamage() < stack11.getItemDamage()) {
                        stack11 = stack23;
                     }
                  } else if (stack23.getItemDamage() < stack3.getItemDamage()) {
                     stack11 = stack23;
                  }
               }
            }
         }

         for (ItemStack stack24 : (Iterable<ItemStack>)(list4)) {
            if (stack24.getSlotIndex() != n4 + 36) {
               if (stack4 == null) {
                  if (stack12 != null) {
                     ItemFood item2 = (ItemFood)stack24.getItem();
                     ItemFood item3 = (ItemFood)stack12.getItem();
                     if (item2.getHealAmount(stack24) > item3.getHealAmount(stack12)) {
                        stack12 = stack24;
                     } else if (item2.getHealAmount(stack24) == item3.getHealAmount(stack12)
                        && item2.getSaturationModifier(stack24) > item3.getSaturationModifier(stack12)) {
                        stack12 = stack24;
                     }
                  } else {
                     stack12 = stack24;
                  }
               } else if (stack4.getItem() instanceof ItemFood) {
                  ItemFood item4 = (ItemFood)stack24.getItem();
                  ItemFood item5 = (ItemFood)stack4.getItem();
                  if (stack12 != null) {
                     ItemFood item6 = (ItemFood)stack12.getItem();
                     if (item4.getHealAmount(stack24) > item6.getHealAmount(stack12)) {
                        stack12 = stack24;
                     } else if (item4.getHealAmount(stack24) == item6.getHealAmount(stack12)
                        && item4.getSaturationModifier(stack24) > item6.getSaturationModifier(stack12)) {
                        stack12 = stack24;
                     }
                  } else if (item4.getHealAmount(stack24) > item5.getHealAmount(stack4)) {
                     stack12 = stack24;
                  } else if (item4.getHealAmount(stack24) == item5.getHealAmount(stack4)
                     && item4.getSaturationModifier(stack24) > item5.getSaturationModifier(stack4)) {
                     stack12 = stack24;
                  }
               }
            }
         }

         for (ItemStack stack25 : (Iterable<ItemStack>)(list5)) {
            if (stack25.getSlotIndex() != n5 + 36) {
               if (stack5 == null) {
                  if (stack13 != null) {
                     if (stack25.stackSize > stack13.stackSize) {
                        stack13 = stack25;
                     }
                  } else {
                     stack13 = stack25;
                  }
               } else if (stack5.getItem() instanceof ItemFood) {
                  if (stack13 != null) {
                     if (stack25.stackSize > stack13.stackSize) {
                        stack13 = stack25;
                     }
                  } else if (stack25.stackSize > stack5.stackSize) {
                     stack13 = stack25;
                  }
               }
            }
         }

         if (stack9 != null) {
            list10.add(stack9);
         }

         if (stack != null) {
            list10.add(stack);
         }

         if (stack10 != null) {
            list10.add(stack10);
         }

         if (stack2 != null) {
            list10.add(stack2);
         }

         if (stack11 != null) {
            list10.add(stack11);
         }

         if (stack3 != null) {
            list10.add(stack3);
         }

         if (stack12 != null) {
            list10.add(stack12);
         }

         if (stack4 != null) {
            list10.add(stack4);
         }

         if (stack13 != null) {
            list10.add(stack13);
         }

         if (stack5 != null) {
            list10.add(stack5);
         }

         if (stack6 != null) {
            list10.add(stack6);
         }

         if (stack14 != null) {
            list10.add(stack14);
         }

         if (stack7 != null) {
            list10.add(stack7);
         }

         if (stack15 != null) {
            list10.add(stack15);
         }

         if (stack8 != null) {
            list10.add(stack8);
         }

         if (stack16 != null) {
            list10.add(stack16);
         }

         if (this.startDelayTimer.reached(l3)) {
            long nextActionDelayMs2 = this.nextActionDelayMs;
            if (this.actionTimer.reached(nextActionDelayMs2) && this.sort.getValue()) {
               if (stack9 != null && n >= 0) {
                  this.queueHotbarSwap(stack9, n);
                  this.postActionTimer.reset();
                  if (this.sortDelay.getMinValue() != 0.0) {
                     return;
                  }

                  if (this.sortDelay.getMaxValue() != 0.0) {
                     return;
                  }
               }

               if (stack == null && stack6 == null && stack14 != null && n > 0) {
this.queueHotbarSwap(stack14, n);
                  this.postActionTimer.reset();
if (this.sortDelay.getMinValue() == 0.0) {
if (this.sortDelay.getMaxValue() != 0.0) {
return;
}
} else {
return;
}
}

               if (stack10 != null && n2 >= 0) {
                  this.queueHotbarSwap(stack10, n2);
                  this.postActionTimer.reset();
                  if (this.sortDelay.getMinValue() != 0.0) {
                     return;
                  }

                  if (this.sortDelay.getMaxValue() != 0.0) {
                     return;
                  }
               }

               if (stack11 != null && n3 >= 0) {
this.queueHotbarSwap(stack11, n3);
                  this.postActionTimer.reset();
if (this.sortDelay.getMinValue() == 0.0) {
if (this.sortDelay.getMaxValue() != 0.0) {
return;
}
} else {
return;
}
}

               if (stack12 != null && n4 >= 0) {
                  this.queueHotbarSwap(stack12, n4);
                  this.postActionTimer.reset();
                  if (this.sortDelay.getMinValue() != 0.0) {
                     return;
                  }

                  if (this.sortDelay.getMaxValue() != 0.0) {
                     return;
                  }
               }

               if (stack13 != null && n5 >= 0) {
                  this.queueHotbarSwap(stack13, n5);
                  this.postActionTimer.reset();
                  if (this.sortDelay.getMinValue() == 0.0) {
                     if (this.sortDelay.getMaxValue() == 0.0) {
                        return;
                     }
                  }

                  return;
               }
            }
         }
      } else {
         this.startDelayTimer.reset();
         this.actionTimer.reset();
      }
   }

   private double getSlotDistance(int n, int n2) {
      GuiInventory currentScreen = (GuiInventory)mc.currentScreen;
      Slot slot = currentScreen.inventorySlots.getSlot(n2);
      Slot slot10 = currentScreen.inventorySlots.getSlot(n);
      double d = (double)Math.abs(slot.xDisplayPosition + 2 + currentScreen.guiLeft - (slot10.xDisplayPosition + 2 + currentScreen.guiLeft));
      double d2 = (double)Math.abs(slot.yDisplayPosition + 2 + currentScreen.guiTop - (slot10.yDisplayPosition + 2 + currentScreen.guiTop));
      return Math.sqrt(d * d + d2 * d2);
   }

   private double getDistanceFromCursor(double[] doubles, int n) {
      GuiInventory currentScreen = (GuiInventory)mc.currentScreen;
      Slot slot = currentScreen.inventorySlots.getSlot(n);
      double d = Math.abs((double)(slot.xDisplayPosition + 2 + currentScreen.guiLeft) - doubles[0]);
      double d2 = Math.abs((double)(slot.yDisplayPosition + 2 + currentScreen.guiTop) - doubles[1]);
      return Math.sqrt(d * d + d2 * d2);
   }

   private void closeSpoofedInventory() {
      if (mc.thePlayer.getSpoofedScreen() != null && this.mode.getSelectedValue().equals("SpoofInv")) {
         if (mc.currentScreen == null) {
            mc.thePlayer.sendQueue.addToSendQueue(new C0DPacketCloseWindow(mc.thePlayer.inventoryContainer.windowId));
            mc.thePlayer.setSpoofedScreen(null);
         }
      }
   }

   private void openSpoofedInventory() {
      if (this.mode.getSelectedValue().equals("SpoofInv") && mc.thePlayer.getSpoofedScreen() == null && mc.currentScreen == null) {
         mc.thePlayer.sendQueue.addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT));
         mc.thePlayer.setSpoofedScreen(new GuiInventory(mc.thePlayer));
      }
   }

   private boolean isKeepableMiscItem(ItemStack stack) {
      Item item = stack.getItem();
      return item.equals(Item.getItemById(288)) || item.equals(Item.getItemById(289));
   }

   private boolean isUsefulItem(ItemStack stack) {
      Item item = stack.getItem();

      for (int i = 183; i < 192; i++) {
         if (item.equals(Item.getItemById(i))) {
            return false;
         }
      }

      for (int j = 290; j < 295; j++) {
         if (item.equals(Item.getItemById(j))) {
            return false;
         }
      }

      if (item.equals(Item.getItemById(39)) || item.equals(Item.getItemById(40))) {
         return ClientManagers.moduleManager.autoSoup.isToggled();
      } else {
         return item.equals(Item.getItemById(287))
            ? ClientManagers.moduleManager.autoRod.isToggled()
            : !item.equals(Item.getItemById(289))
               && !item.equals(Item.getItemById(289))
               && !item.equals(Item.getByNameOrId("tallgrass"))
               && !item.equals(Item.getByNameOrId("deadbush"))
               && !item.equals(Item.getByNameOrId("red_flower"))
               && !item.equals(Item.getItemById(53))
               && !item.equals(Item.getItemById(66))
               && !item.equals(Item.getItemById(67))
               && !item.equals(Item.getItemById(70))
               && !item.equals(Item.getItemById(72))
               && !item.equals(Item.getItemById(77))
               && !item.equals(Item.getItemById(85))
               && !item.equals(Item.getItemById(81))
               && !item.equals(Item.getItemById(96))
               && !item.equals(Item.getItemById(101))
               && !item.equals(Item.getItemById(102))
               && !item.equals(Item.getItemById(106))
               && !item.equals(Item.getItemById(107))
               && !item.equals(Item.getItemById(108))
               && !item.equals(Item.getItemById(109))
               && !item.equals(Item.getItemById(111))
               && !item.equals(Item.getItemById(113))
               && !item.equals(Item.getItemById(114))
               && !item.equals(Item.getItemById(128))
               && !item.equals(Item.getItemById(131))
               && !item.equals(Item.getItemById(134))
               && !item.equals(Item.getItemById(135))
               && !item.equals(Item.getItemById(136))
               && !item.equals(Item.getItemById(143))
               && !item.equals(Item.getItemById(136))
               && !item.equals(Item.getItemById(147))
               && !item.equals(Item.getItemById(148))
               && !item.equals(Item.getItemById(151))
               && !item.equals(Item.getItemById(154))
               && !item.equals(Item.getItemById(156))
               && !item.equals(Item.getItemById(157))
               && !item.equals(Item.getItemById(163))
               && !item.equals(Item.getItemById(164))
               && !item.equals(Item.getItemById(167))
               && !item.equals(Item.getItemById(180))
               && !item.equals(Item.getItemById(287))
               && !item.equals(Item.getItemById(287))
               && !item.equals(Item.getItemById(318))
               && !item.equals(Item.getItemById(321))
               && !item.equals(Item.getItemById(337))
               && !item.equals(Item.getItemById(338))
               && !item.equals(Item.getItemById(323))
               && !item.equals(Item.getItemById(348))
               && !item.equals(Item.getItemById(352))
               && !item.equals(Item.getItemById(353))
               && !item.equals(Item.getItemById(354))
               && !item.equals(Item.getItemById(356))
               && !item.equals(Item.getItemById(361))
               && !item.equals(Item.getItemById(259))
               && !item.equals(Item.getItemById(362))
               && !item.equals(Item.getItemById(367))
               && !item.equals(Item.getItemById(370))
               && !item.equals(Item.getItemById(371))
               && !item.equals(Item.getItemById(372))
               && !item.equals(Item.getItemById(373))
               && !item.equals(Item.getItemById(334))
               && !item.equals(Item.getByNameOrId("snow_layer"))
               && !item.equals(Item.getByNameOrId("wooden_slab"))
               && !item.equals(Item.getByNameOrId("cobblestone_wall"))
               && !item.equals(Item.getByNameOrId("anvil"))
               && !item.equals(Item.getByNameOrId("stained_glass_pane"))
               && !item.equals(Item.getByNameOrId("carpet"))
               && !item.equals(Item.getByNameOrId("double_plant"))
               && !item.equals(Item.getByNameOrId("stone_slab2"))
               && !item.equals(Item.getByNameOrId("sapling"));
      }
   }

   private void queueHotbarSwap(ItemStack stack, int n) {
      if (!((double)DefaultRandomGenerator.instance.nextInt((byte)1, (byte)100) < this.failRate.getValue())) {
         this.openSpoofedInventory();
         if (this.mode.getSelectedValue().equals("SpoofInv") && this.eventToClick.getSelectedValue().equals("EventTick")) {
            this.queuedActions.add(() -> this.executeQueuedHotbarSwap(stack, n));
         } else {
            this.swapToHotbarSlot(stack, n);
         }
      }
   }

   private void swapToHotbarSlot(ItemStack stack, int n) {
      if (mc.currentScreen instanceof GuiInventory) {
         GuiInventory currentScreen = (GuiInventory)mc.currentScreen;
         Slot slot = currentScreen.inventorySlots.getSlot(stack.getSlotIndex());
         if (this.smartDelay.getValue()) {
            double d = Math.abs((double)(slot.xDisplayPosition + 2 + currentScreen.guiLeft) - InventoryUtil.getLastCursorPos().x);
            double d2 = Math.abs((double)(slot.yDisplayPosition + 2 + currentScreen.guiTop) - InventoryUtil.getLastCursorPos().y);
            double d3 = Math.sqrt(d * d + d2 * d2);
            double d4 = this.sortDelay.getMinValue() + this.sortDelay.getMaxValue() - this.sortDelay.getMinValue() / 2.0;
            double d5 = d3 / 30.0;
            if (d3 > 36.0) {
               long l = (long)(d4 * d5);
               if (!this.actionTimer.reached(l)) {
                  return;
               }
            }
         }

         InventoryUtil.setLastCursorPos((double)(slot.xDisplayPosition + 2 + currentScreen.guiLeft), (double)(slot.yDisplayPosition + 2 + currentScreen.guiTop));
      }

      this.resetActionTimers();
      switch (this.clickMode.getSelectedValue()) {
         case "Exploit":
            break;
         case "Basic":
         case "Other":
            mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, stack.getSlotIndex(), n, 2, mc.thePlayer);
      }

      ItemStack stack2 = mc.thePlayer.inventoryContainer.getSlot(stack.getSlotIndex()).getStack();
      if (stack2 != null) {
         stack2.setSlotIndex(stack.getSlotIndex());
      }

      stack.setSlotIndex(n + 36);
      this.nextActionDelayMs = DefaultRandomGenerator.instance.nextLong(this.sortDelay.getMinValue(), this.sortDelay.getMaxValue());
   }

   private void queueDropSlot(int n) {
      if (!((double)DefaultRandomGenerator.instance.nextInt((byte)1, (byte)100) < this.failRate.getValue())) {
         this.openSpoofedInventory();
         if (this.mode.getSelectedValue().equals("SpoofInv") && this.eventToClick.getSelectedValue().equals("EventTick")) {
            this.queuedActions.add(() -> {
               this.dropSlot(n);
            });
         } else {
            this.dropSlot(n);
            this.nextActionDelayMs = DefaultRandomGenerator.instance.nextLong(this.dropDelay.getMinValue(), this.dropDelay.getMaxValue());
         }
      }
   }

   private void dropSlot(int n) {
      if (mc.currentScreen instanceof GuiInventory) {
         GuiInventory currentScreen = (GuiInventory)mc.currentScreen;
         Slot slot = currentScreen.inventorySlots.getSlot(n);
         if (this.smartDelay.getValue()) {
            double d = Math.abs((double)(slot.xDisplayPosition + 2 + currentScreen.guiLeft) - InventoryUtil.getLastCursorPos().x);
            double d2 = Math.abs((double)(slot.yDisplayPosition + 2 + currentScreen.guiTop) - InventoryUtil.getLastCursorPos().y);
            double d3 = Math.sqrt(d * d + d2 * d2);
            double d4 = this.dropDelay.getMinValue() + this.dropDelay.getMaxValue() - this.dropDelay.getMinValue() / 2.0;
            double d5 = d3 / 30.0;
            if (d3 > 36.0) {
               long l = (long)(d4 * d5);
               if (!this.actionTimer.reached(l)) {
                  return;
               }
            }
         }

         InventoryUtil.setLastCursorPos((double)(slot.xDisplayPosition + 2 + currentScreen.guiLeft), (double)(slot.yDisplayPosition + 2 + currentScreen.guiTop));
      }

      this.resetActionTimers();
      switch (this.clickMode.getSelectedValue()) {
         case "Basic":
            mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, n, 1, 4, mc.thePlayer);
            break;
         case "Other":
            break;
         case "Exploit":
      }
   }

   private double scoreSword(ItemStack stack) {
      double d = 0.0;
      if (stack.getItem() instanceof ItemSword) {
         d += (double)(((ItemSword)stack.getItem()).getAttackDamage() + (float)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) * 1.25F);
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack) / 11.0;
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, stack) / 11.0;
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 11.0;
      }

      return d;
   }

   private double scoreCombatTool(ItemStack stack) {
      double d = 0.0;
      if (stack.getItem() instanceof ItemAxe) {
         d += (double)stack.getItem().getStrVsBlock(stack, new Block(Material.wood, MapColor.woodColor))
            + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack) / 11.0;
      } else if (stack.getItem() instanceof ItemPickaxe) {
         d += (double)stack.getItem().getStrVsBlock(stack, new Block(Material.rock, MapColor.stoneColor))
            + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack) / 11.0;
      } else if (stack.getItem() instanceof ItemSpade) {
         d += (double)stack.getItem().getStrVsBlock(stack, new Block(Material.sand, MapColor.sandColor))
            + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack) / 11.0;
      }

      d += (double)((float)stack.getItem().getMaxDamage() + (float)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) * 1.25F);
      d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack) / 11.0;
      d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, stack) / 11.0;
      return d + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 11.0;
   }

   private double scoreMiningTool(ItemStack stack) {
      double d = 0.0;
      if (stack.getItem() instanceof ItemTool) {
         if (stack.getItem() instanceof ItemAxe) {
            d += (double)(
               stack.getItem().getStrVsBlock(stack, new Block(Material.wood, MapColor.woodColor))
                  + (float)EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack)
            );
         } else if (stack.getItem() instanceof ItemPickaxe) {
            d += (double)(
               stack.getItem().getStrVsBlock(stack, new Block(Material.rock, MapColor.stoneColor))
                  + (float)EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack)
            );
         } else if (stack.getItem() instanceof ItemSpade) {
            d += (double)(
               stack.getItem().getStrVsBlock(stack, new Block(Material.sand, MapColor.sandColor))
                  + (float)EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack)
            );
         }

         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 11.0;
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, stack) / 11.0;
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) / 33.0;
      }

      return d;
   }

   private double scoreBow(ItemStack stack) {
      double d = 10.0;
      if (stack != null && stack.getItem() instanceof ItemBow) {
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack) / 5.0;
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack) / 11.0;
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) / 5.0;
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) / 11.0;
         d -= (double)stack.getItemDamage() / 10000.0;
      }

      return d;
   }

   public void resetActionTimers() {
      this.actionTimer.reset();
      this.postActionTimer.reset();
   }

   public TimeHelper getPostActionTimer() {
      return this.postActionTimer;
   }

   public TimeHelper getActionTimer() {
      return this.actionTimer;
   }

   public int getCurrentTargetSlot() {
      return this.currentTargetSlot;
   }

   private void executeQueuedHotbarSwap(ItemStack stack, int n) {
      this.swapToHotbarSlot(stack, n);
   }

   private boolean isStopMovementVisible() {
      return this.mode.getSelectedValue().equals("SpoofInv");
   }

   private boolean isInteractCheckVisible() {
      return this.mode.getSelectedValue().equals("SpoofInv");
   }

   private boolean isNoMoveVisible() {
      return this.mode.getSelectedValue().equals("SpoofInv");
   }

}
