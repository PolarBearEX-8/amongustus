package net.augustus.modules.player;

import net.minecraft.util.MovingObjectPosition;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.RenderListener;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.SilentSlotListener;
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
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C16PacketClientStatus;

public class AutoArmor extends Module implements ClickListener, TickListener, RenderListener, SilentSlotListener, MoveInputListener, DropItemListener {
   public final StringValue mode;
   public final BooleanValue noMove;
   public final BooleanValue interactCheck;
   public final BooleanValue stopMovement;
   public final DoubleValue startDelay;
   public final RangeSetting delay;
   public final BooleanValue hotbar;
   public final BooleanValue gommeQSG;
   public final DoubleValue hStartDelay;
   public final RangeSetting hotbarDelay;
   public final BooleanValue smartDelay;
   public final BooleanValue stopOnInteraction;
   public final BooleanValue visualize;
   public final StringValue sequence;
   public final StringValue eventToClick;
   public final StringValue clickMode;
   private final TimeHelper hotbarEquipTimer;
   private final TimeHelper hotbarStartTimer;
   private final TimeHelper invStartTimer;
   private final ArrayList queuedActions;
   private ArrayList chestplates;
   private ArrayList helmets;
   private ArrayList boots;
   private ArrayList leggings;
   private boolean interactionBlocked;
   private boolean allArmorEquipped;
   public long equipDelayMs;
   private long hotbarDelayMs;
   private int currentTargetSlot;
   private int hotbarEquipSlot;

   public AutoArmor() {
       super("AutoArmor", "AutoRüstung", new Color(23, 123, 208, 255), Categorys.PLAYER);
      this.mode = new StringValue(this, "Mode", "OpenInv", new String[]{"OpenInv", "SpoofInv", "Basic"});
      this.noMove = (BooleanValue)new BooleanValue(this, "NoMove", false).withVisibility(() -> this.isNoMoveVisible());
      this.interactCheck = (BooleanValue)new BooleanValue(this, "InteractCheck", false).withVisibility(() -> this.isInteractCheckVisible());
      this.stopMovement = (BooleanValue)new BooleanValue(this, "StopMovement", false).withVisibility(() -> this.isStopMovementVisible());
      this.startDelay = new DoubleValue(this, "StartDelay", 200.0, 0.0, 1000.0, 0);
      this.delay = new RangeSetting(this, "Delay", 50.0, 100.0, 0.0, 400.0, 0);
      this.hotbar = (BooleanValue)new BooleanValue(this, "Hotbar", false).withVisibility(() -> this.isHotbarVisible());
      this.gommeQSG = (BooleanValue)new BooleanValue(this, "GommeQSG", false).withVisibility(() -> this.isGommeQsgVisible());
      this.hStartDelay = new DoubleValue(this, "HStartDelay", 200.0, 0.0, 1000.0, 0).visibleWhen(() -> this.isHotbarStartDelayVisible());
      RangeSetting rangeSetting = new RangeSetting(this, "HotbarDelay", 70.0, 120.0, 0.0, 400.0, 0);
      SettingVisibilityCondition condition7 = () -> {
         return this.hotbar.isVisible() ? this.hotbar.getValue() : this.mode.getSelectedValue().equals("OpenInv");
      };
      this.hotbarDelay = rangeSetting.visibleWhen(condition7);
      this.smartDelay = new BooleanValue(this, "SmartDelay", false);
      this.stopOnInteraction = new BooleanValue(this, "StopOnInteraction", false);
      this.visualize = new BooleanValue(this, "Visualize", false);
      this.sequence = new StringValue(this, "Sequence", "Basic", new String[]{"Basic", "Nearest"});
      this.eventToClick = new StringValue(this, "EventToClick", "EventTick", new String[]{"EventTick", "EventRender"});
      this.clickMode = new StringValue(this, "ClickMode", "Basic", new String[]{"Basic"});
      this.hotbarEquipTimer = new TimeHelper();
      this.hotbarStartTimer = new TimeHelper();
      this.invStartTimer = new TimeHelper();
      this.queuedActions = new ArrayList();
      this.allArmorEquipped = true;
      this.equipDelayMs = 0L;
      this.hotbarDelayMs = 0L;
      this.currentTargetSlot = -1;
      this.hotbarEquipSlot = 0;
   }

   @Override
   public void onEnable() {
      if (mc.thePlayer != null) {
         mc.thePlayer.setSpoofedScreen(mc.currentScreen);
      }

      RandomNumberGenerator instance = DefaultRandomGenerator.instance;
      double minValue = this.delay.getMinValue();
      this.equipDelayMs = instance.nextLong(minValue, this.delay.getMaxValue());
      RandomNumberGenerator instance2 = DefaultRandomGenerator.instance;
      minValue = this.hotbarDelay.getMinValue();
      this.hotbarDelayMs = instance2.nextLong(minValue, this.hotbarDelay.getMaxValue());
   }

   @Override
   public void onDisable() {
      this.currentTargetSlot = -1;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(34);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(9);
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
         this.hotbarEquipTimer.reset();
      }

      if (this.mode.getSelectedValue().equalsIgnoreCase("OpenInv") && this.hotbar.getValue() && mc.currentScreen == null && this.hotbarEquipSlot >= 0) {
         event.addRightClicks((byte)1);
         this.hotbarEquipSlot = -11;
      }
   }

   @Override
   public void onPostSilentSlot() {
      if (this.mode.getSelectedValue().equalsIgnoreCase("OpenInv") && this.hotbar.getValue() && mc.currentScreen == null) {
         this.updateHotbarArmor();
         if (this.hotbar.getValue() && this.hotbarEquipSlot >= 0) {
            SilentSlotManager.requestSlot(this, (byte)6, this.hotbarEquipSlot);
         }
      }
   }

   @Override
   public void onEarlyTick() {
      this.setSuffix(this.mode.getSelectedValue());
      if (mc.currentScreen != null) {
         this.hotbarStartTimer.reset();
      }

      if (this.eventToClick.getSelectedValue().equals("EventTick") && !this.mode.getSelectedValue().equals("SpoofInv")) {
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
      }
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

   public void updateHotbarArmor() {
      this.chestplates = new ArrayList();
      this.helmets = new ArrayList();
      this.leggings = new ArrayList();
      this.boots = new ArrayList();
      long l3 = (long)(this.hStartDelay.getValue() + (double)DefaultRandomGenerator.instance.nextLong(-35L, 35L));
      ItemStack stack = null;
      ItemStack stack2 = null;
      ItemStack stack3 = null;
      ItemStack stack4 = null;
      ItemStack stack5 = null;
      ItemStack stack6 = null;
      ItemStack stack7 = null;
      ItemStack stack8 = null;
      if (this.hotbar.getValue() && mc.currentScreen == null) {
         boolean value2 = this.gommeQSG.getValue();

         for (int i = 36; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
            ItemStack stack9 = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if (stack9 != null && stack9.getItem() instanceof ItemArmor) {
               if (((ItemArmor)stack9.getItem()).armorType == 0) {
                  stack9.setSlotIndex(i);
                  this.helmets.add(stack9);
               } else if (((ItemArmor)stack9.getItem()).armorType == 1) {
                  stack9.setSlotIndex(i);
                  this.chestplates.add(stack9);
               } else if (((ItemArmor)stack9.getItem()).armorType == 2) {
                  stack9.setSlotIndex(i);
                  this.leggings.add(stack9);
               } else if (((ItemArmor)stack9.getItem()).armorType == 3) {
                  stack9.setSlotIndex(i);
                  this.boots.add(stack9);
               }
            }
         }

         for (int j = 0; j < mc.thePlayer.inventoryContainer.inventorySlots.size(); j++) {
            ItemStack stack10 = mc.thePlayer.inventoryContainer.getSlot(j).getStack();
            if (stack10 != null && stack10.getItem() instanceof ItemArmor) {
               if (j == 5) {
                  stack10.setSlotIndex(j);
                  stack = stack10;
               }

               if (j == 6) {
                  stack10.setSlotIndex(j);
                  stack3 = stack10;
               }

               if (j == 7) {
                  stack10.setSlotIndex(j);
                  stack4 = stack10;
               }

               if (j == 8) {
                  stack10.setSlotIndex(j);
                  stack2 = stack10;
               }
            }
         }

         for (ItemStack stack11 : (Iterable<ItemStack>)(this.helmets)) {
            if (stack == null) {
               if (stack5 != null) {
                  if (this.scoreArmor(stack11) > this.scoreArmor(stack5)) {
                     stack5 = stack11;
                  }
               } else {
                  stack5 = stack11;
               }
            } else if (stack.getItem() instanceof ItemArmor && value2) {
               if (stack5 != null) {
                  if (this.scoreArmor(stack11) > this.scoreArmor(stack5)) {
                     stack5 = stack11;
                  }
               } else if (this.scoreArmor(stack11) > this.scoreArmor(stack)) {
                  stack5 = stack11;
               }
            }
         }

         for (ItemStack stack12 : (Iterable<ItemStack>)(this.chestplates)) {
            if (stack3 == null) {
               if (stack7 != null) {
                  if (this.scoreArmor(stack12) > this.scoreArmor(stack7)) {
                     stack7 = stack12;
                  }
               } else {
                  stack7 = stack12;
               }
            } else if (stack3.getItem() instanceof ItemArmor && value2) {
               if (stack7 != null) {
                  if (this.scoreArmor(stack12) > this.scoreArmor(stack7)) {
                     stack7 = stack12;
                  }
               } else if (this.scoreArmor(stack12) > this.scoreArmor(stack3)) {
                  stack7 = stack12;
               }
            }
         }

         for (ItemStack stack13 : (Iterable<ItemStack>)(this.leggings)) {
            if (stack4 == null) {
               if (stack8 != null) {
                  if (this.scoreArmor(stack13) > this.scoreArmor(stack8)) {
                     stack8 = stack13;
                  }
               } else {
                  stack8 = stack13;
               }
            } else if (stack4.getItem() instanceof ItemArmor && value2) {
               if (stack8 != null) {
                  if (this.scoreArmor(stack13) > this.scoreArmor(stack8)) {
                     stack8 = stack13;
                  }
               } else if (this.scoreArmor(stack13) > this.scoreArmor(stack4)) {
                  stack8 = stack13;
               }
            }
         }

         for (ItemStack stack14 : (Iterable<ItemStack>)(this.boots)) {
            if (stack2 == null) {
               if (stack6 != null) {
                  if (this.scoreArmor(stack14) > this.scoreArmor(stack6)) {
                     stack6 = stack14;
                  }
               } else {
                  stack6 = stack14;
               }
            } else if (stack2.getItem() instanceof ItemArmor && value2) {
               if (stack6 != null) {
                  if (this.scoreArmor(stack14) > this.scoreArmor(stack6)) {
                     stack6 = stack14;
                  }
               } else if (this.scoreArmor(stack14) > this.scoreArmor(stack2)) {
                  stack6 = stack14;
               }
            }
         }

         if (this.hotbarStartTimer.reached(l3)) {
            long hotbarDelayMs2 = this.hotbarDelayMs;
            if (this.hotbarEquipTimer.reached(hotbarDelayMs2)
               && (
                  mc.objectMouseOver == null
                     || mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.MISS
                     || mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && !mc.theWorld.getBlockState(mc.objectMouseOver.getBlockPos()).getBlock().hasTileEntity()
               )) {
               this.hotbarDelayMs = DefaultRandomGenerator.instance.nextLong(this.hotbarDelay.getMinValue(), this.hotbarDelay.getMaxValue());
               if (stack6 == null && stack8 == null && stack7 == null && stack5 == null) {
                  this.hotbarStartTimer.reset();
               } else {
                  if (stack7 != null) {
                     if (stack3 == null || value2) {
                        this.selectHotbarArmorSlot(stack7.getSlotIndex());
                     }

                     this.hotbarEquipTimer.reset();
                     return;
                  }

                  if (stack8 != null) {
                     if (stack4 == null || value2) {
                        this.selectHotbarArmorSlot(stack8.getSlotIndex());
                     }

                     this.hotbarEquipTimer.reset();
                     return;
                  }

                  if (stack5 != null) {
                     if (stack == null || value2) {
                        this.selectHotbarArmorSlot(stack5.getSlotIndex());
                     }

                     this.hotbarEquipTimer.reset();
                     return;
                  }

                  if (stack6 != null) {
                     if (stack2 == null || value2) {
                        this.selectHotbarArmorSlot(stack6.getSlotIndex());
                     }

                     this.hotbarEquipTimer.reset();
                     return;
                  }
               }

               this.hotbarEquipSlot = -11;
            }
         } else {
            this.hotbarEquipSlot = -11;
         }
      } else {
         this.hotbarEquipSlot = -11;
      }
   }

   public void updateInventoryArmor(boolean flag, ArrayList list) {
      this.chestplates = new ArrayList();
      this.helmets = new ArrayList();
      this.leggings = new ArrayList();
      this.boots = new ArrayList();
      long l3 = DefaultRandomGenerator.instance.nextLong(-25L, 25L);
      long l4 = 0L;
      if (flag) {
         l4 = (long)(this.startDelay.getValue() + (double)l3);
      }

      ItemStack stack = null;
      ItemStack stack2 = null;
      ItemStack stack3 = null;
      ItemStack stack4 = null;
      ItemStack stack5 = null;
      ItemStack stack6 = null;
      ItemStack stack7 = null;
      ItemStack stack8 = null;
      boolean flag2 = mc.currentScreen instanceof GuiInventory
         || !this.mode.getSelectedValue().equalsIgnoreCase("OpenInv")
            && (mc.currentScreen == null || mc.currentScreen instanceof GuiInventory)
            && (
               !this.noMove.getValue()
                  || this.noMove.getValue() && !MoveUtil.isMoving()
                  || !this.mode.getSelectedValue().equals("SpoofInv")
            );
      if (flag2 && !this.interactionBlocked) {
         for (int i = 0; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
            ItemStack stack9 = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if (stack9 != null && stack9.getItem() instanceof ItemArmor) {
               if (((ItemArmor)stack9.getItem()).armorType == 0) {
                  stack9.setSlotIndex(i);
                  this.helmets.add(stack9);
               } else if (((ItemArmor)stack9.getItem()).armorType == 1) {
                  stack9.setSlotIndex(i);
                  this.chestplates.add(stack9);
               } else if (((ItemArmor)stack9.getItem()).armorType == 2) {
                  stack9.setSlotIndex(i);
                  this.leggings.add(stack9);
               } else if (((ItemArmor)stack9.getItem()).armorType == 3) {
                  stack9.setSlotIndex(i);
                  this.boots.add(stack9);
               }

               if (stack9.getSlotIndex() == 5) {
                  stack9.setSlotIndex(i);
                  stack = stack9;
               }

               if (stack9.getSlotIndex() == 6) {
                  stack9.setSlotIndex(i);
                  stack3 = stack9;
               }

               if (stack9.getSlotIndex() == 7) {
                  stack9.setSlotIndex(i);
                  stack4 = stack9;
               }

               if (stack9.getSlotIndex() == 8) {
                  stack9.setSlotIndex(i);
                  stack2 = stack9;
               }
            }
         }

         for (ItemStack stack10 : (Iterable<ItemStack>)(this.helmets)) {
            if (stack == null) {
               if (stack5 != null) {
                  if (this.scoreArmor(stack10) > this.scoreArmor(stack5)) {
                     stack5 = stack10;
                  } else if (this.scoreArmor(stack10) == this.scoreArmor(stack5) && stack10.getItemDamage() < stack5.getItemDamage()) {
                     stack5 = stack10;
                  }
               } else {
                  stack5 = stack10;
               }
            } else if (stack.getItem() instanceof ItemArmor) {
               if (stack5 != null) {
                  if (this.scoreArmor(stack10) > this.scoreArmor(stack5)) {
                     stack5 = stack10;
                  } else if (this.scoreArmor(stack10) == this.scoreArmor(stack5) && stack10.getItemDamage() < stack5.getItemDamage()) {
                     stack5 = stack10;
                  }
               } else if (this.scoreArmor(stack10) > this.scoreArmor(stack)) {
                  stack5 = stack10;
               } else if (this.scoreArmor(stack10) == this.scoreArmor(stack) && stack10.getItemDamage() < stack.getItemDamage()) {
                  stack5 = stack10;
               }
            }
         }

         for (ItemStack stack11 : (Iterable<ItemStack>)(this.chestplates)) {
            if (stack3 == null) {
               if (stack7 != null) {
                  if (this.scoreArmor(stack11) > this.scoreArmor(stack7)) {
                     stack7 = stack11;
                  } else if (this.scoreArmor(stack11) == this.scoreArmor(stack7) && stack11.getItemDamage() < stack7.getItemDamage()) {
                     stack7 = stack11;
                  }
               } else {
                  stack7 = stack11;
               }
            } else if (stack3.getItem() instanceof ItemArmor) {
               if (stack7 != null) {
                  if (this.scoreArmor(stack11) > this.scoreArmor(stack7)) {
                     stack7 = stack11;
                  } else if (this.scoreArmor(stack11) == this.scoreArmor(stack7) && stack11.getItemDamage() < stack7.getItemDamage()) {
                     stack7 = stack11;
                  }
               } else if (this.scoreArmor(stack11) > this.scoreArmor(stack3)) {
                  stack7 = stack11;
               } else if (this.scoreArmor(stack11) == this.scoreArmor(stack3) && stack11.getItemDamage() < stack3.getItemDamage()) {
                  stack7 = stack11;
               }
            }
         }

         for (ItemStack stack12 : (Iterable<ItemStack>)(this.leggings)) {
            if (stack4 == null) {
               if (stack8 != null) {
                  if (this.scoreArmor(stack12) > this.scoreArmor(stack8)) {
                     stack8 = stack12;
                  } else if (this.scoreArmor(stack12) == this.scoreArmor(stack8) && stack12.getItemDamage() < stack8.getItemDamage()) {
                     stack8 = stack12;
                  }
               } else {
                  stack8 = stack12;
               }
            } else if (stack4.getItem() instanceof ItemArmor) {
               if (stack8 != null) {
                  if (this.scoreArmor(stack12) > this.scoreArmor(stack8)) {
                     stack8 = stack12;
                  } else if (this.scoreArmor(stack12) == this.scoreArmor(stack8) && stack12.getItemDamage() < stack8.getItemDamage()) {
                     stack8 = stack12;
                  }
               } else if (this.scoreArmor(stack12) > this.scoreArmor(stack4)) {
                  stack8 = stack12;
               } else if (this.scoreArmor(stack12) == this.scoreArmor(stack4) && stack12.getItemDamage() < stack4.getItemDamage()) {
                  stack8 = stack12;
               }
            }
         }

         for (ItemStack stack13 : (Iterable<ItemStack>)(this.boots)) {
            if (stack2 == null) {
               if (stack6 != null) {
                  if (this.scoreArmor(stack13) > this.scoreArmor(stack6)) {
                     stack6 = stack13;
                  } else if (this.scoreArmor(stack13) == this.scoreArmor(stack6) && stack13.getItemDamage() < stack6.getItemDamage()) {
                     stack6 = stack13;
                  }
               } else {
                  stack6 = stack13;
               }
            } else if (stack2.getItem() instanceof ItemArmor) {
               if (stack6 != null) {
                  if (this.scoreArmor(stack13) > this.scoreArmor(stack6)) {
                     stack6 = stack13;
                  } else if (this.scoreArmor(stack13) == this.scoreArmor(stack6) && stack13.getItemDamage() < stack6.getItemDamage()) {
                     stack6 = stack13;
                  }
               } else if (this.scoreArmor(stack13) > this.scoreArmor(stack2)) {
                  stack6 = stack13;
               } else if (this.scoreArmor(stack13) == this.scoreArmor(stack2) && stack13.getItemDamage() < stack2.getItemDamage()) {
                  stack6 = stack13;
               }
            }
         }

         this.allArmorEquipped = stack6 == null && stack8 == null && stack7 == null && stack5 == null;
         if (this.invStartTimer.reached(l4)) {
         }

         if (flag && this.allArmorEquipped) {
            this.currentTargetSlot = -1;
         }
      } else {
         this.invStartTimer.reset();
      }
   }

   private double getSlotDistance(int n, int n2) {
      GuiInventory currentScreen = (GuiInventory)mc.currentScreen;
      Slot slot = currentScreen.inventorySlots.getSlot(n2);
      Slot slot2 = currentScreen.inventorySlots.getSlot(n);
      double d = (double)Math.abs(slot.xDisplayPosition + 2 + currentScreen.guiLeft - (slot2.xDisplayPosition + 2 + currentScreen.guiLeft));
      double d2 = (double)Math.abs(slot.yDisplayPosition + 2 + currentScreen.guiTop - (slot2.yDisplayPosition + 2 + currentScreen.guiTop));
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

   private double scoreArmor(ItemStack stack) {
      double d = 0.0;
      if (stack.getItem() instanceof ItemArmor) {
         d += (double)(
            (
                  (float)((ItemArmor)stack.getItem()).damageReduceAmount
                     + (float)(
                           6
                              + EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, stack)
                                 * EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, stack)
                        )
                        / 3.0F
               )
               * 2.0F
         );
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.blastProtection.effectId, stack) / 50.0;
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.projectileProtection.effectId, stack) / 50.0;
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.fireProtection.effectId, stack) / 50.0;
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 69.0;
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.thorns.effectId, stack) / 50.0;
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.featherFalling.effectId, stack) / 50.0;
         if (((ItemArmor)stack.getItem()).armorType == 0 && ((ItemArmor)stack.getItem()).getArmorMaterial() == ItemArmor.ArmorMaterial.GOLD) {
            d -= 0.01;
         }
      }

      return d;
   }

   private void queueShiftClick(int n, ArrayList list) {
      this.openSpoofedInventory();
      if (this.mode.getSelectedValue().equals("SpoofInv") && this.eventToClick.getSelectedValue().equals("EventTick")) {
         list.add((Runnable)() -> this.executeQueuedShiftClick(n));
      } else {
         this.shiftClickSlot(n);
      }
   }

   private void shiftClickSlot(int n) {
      if (mc.currentScreen instanceof GuiInventory) {
         try {
            Slot slot = mc.thePlayer.inventoryContainer.getSlot(n);
            GuiInventory currentScreen = (GuiInventory)mc.currentScreen;
            if (this.smartDelay.getValue()) {
               double d = Math.abs((double)(slot.xDisplayPosition + 2 + currentScreen.guiLeft) - InventoryUtil.getLastCursorPos().x);
               double d2 = Math.abs((double)(slot.yDisplayPosition + 2 + currentScreen.guiTop) - InventoryUtil.getLastCursorPos().y);
               double d3 = Math.sqrt(d * d + d2 * d2);
               double d4 = this.delay.getMinValue() + this.delay.getMaxValue() - this.delay.getMinValue() / 2.0;
               double d5 = d3 / 30.0;
               if (d3 > 36.0) {
               }
            }

            InventoryUtil.setLastCursorPos((double)(slot.xDisplayPosition + 2 + currentScreen.guiLeft), (double)(slot.yDisplayPosition + 2 + currentScreen.guiTop));
            switch (this.clickMode.getSelectedValue()) {
               case "Basic":
                  currentScreen.forceShiftClick = true;
                  currentScreen.mouseClicked(slot.xDisplayPosition + 2 + currentScreen.guiLeft, slot.yDisplayPosition + 2 + currentScreen.guiTop, 0);
                  break;
               case "Other":
                  break;
               case "Exploit":
            }
         } catch (IOException e) {
            e.printStackTrace();
         }
      } else {
         switch (this.clickMode.getSelectedValue()) {
            case "Basic":
               mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, n, 0, 1, mc.thePlayer);
               break;
            case "Other":
               break;
            case "Exploit":
         }
      }

      this.onActionComplete();
   }

   private void queueDropSlot(int n, ArrayList list) {
      this.openSpoofedInventory();
      if (this.mode.getSelectedValue().equals("SpoofInv") && this.eventToClick.getSelectedValue().equals("EventTick")) {
         list.add((Runnable)() -> this.executeQueuedDrop(n));
      } else {
         this.dropSlot(n);
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
            double d4 = this.delay.getMinValue() + this.delay.getMaxValue() - this.delay.getMinValue() / 2.0;
            double d5 = d3 / 30.0;
            if (d3 > 36.0) {
            }
         }

         InventoryUtil.setLastCursorPos((double)(slot.xDisplayPosition + 2 + currentScreen.guiLeft), (double)(slot.yDisplayPosition + 2 + currentScreen.guiTop));
      }

      switch (this.clickMode.getSelectedValue()) {
         case "Basic":
            mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, n, 1, 4, mc.thePlayer);
            break;
         case "Other":
            break;
         case "Exploit":
      }

      this.onActionComplete();
   }

   private void selectHotbarArmorSlot(int n) {
      this.hotbarEquipSlot = n - 36;
      this.hotbarEquipTimer.reset();
   }

   public boolean isAllArmorEquipped() {
      return this.allArmorEquipped;
   }

   public void setAllArmorEquipped(boolean flag) {
      this.allArmorEquipped = flag;
   }

   public void onActionComplete() {
   }

   public int getCurrentTargetSlot() {
      return this.currentTargetSlot;
   }

   private void executeQueuedDrop(int n) {
      this.dropSlot(n);
   }

   private void executeQueuedShiftClick(int n) {
      this.shiftClickSlot(n);
   }

   private boolean isHotbarStartDelayVisible() {
      return this.hotbar.isVisible() ? this.hotbar.getValue() : this.mode.getSelectedValue().equals("OpenInv");
   }

   private boolean isGommeQsgVisible() {
      return this.hotbar.isVisible() ? this.hotbar.getValue() : this.mode.getSelectedValue().equals("OpenInv");
   }

   private boolean isHotbarVisible() {
      return this.mode.getSelectedValue().equals("OpenInv");
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
