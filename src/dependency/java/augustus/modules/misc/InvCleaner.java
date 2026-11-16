package augustus.modules.misc;

import client.Client;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventAutoSoupListener;
import client.event.EventClick;
import client.event.EventMove;
import client.event.EventPacket;
import client.event.MoveListener;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.ActionSetting;
import client.setting.AutoSoupPerformRespawnType;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.setting.SettingProvider;
import client.util.BooleanSupplier;
import client.util.EnchantmentManager;
import client.util.InventoryUtil;
import client.util.LvlManager;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCache;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemGoldenAxe;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemGoldenPickaxe;
import net.minecraft.item.ItemGoldenShovel;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class InvCleaner extends Module implements ClickListener, TickListener, RenderListener, MoveListener, EventAutoSoupListener, PacketListener {
   public final ModeSetting settingBasic;
   public final BooleanSetting settingNoMove;
   public final BooleanSetting settingInteractCheck;
   public final BooleanSetting settingStopMovement;
   public final NumberSetting settingStartDelay;
   public final ActionSetting actionSetting;
   public final ActionSetting actionSetting2;
   public final ActionSetting actionSetting3;
   public final BooleanSetting settingSort;
   public final BooleanSetting settingSmartDelay;
   public final BooleanSetting settingStopOnInteraction;
   public final BooleanSetting settingVisualize;
   public final NumberSetting settingFailRate;
   public final ModeSetting settingRandom;
   public final ModeSetting settingEventRender;
   public final ModeSetting settingBasic2;
   public final SettingGroup settingSlots;
   public final ModeSetting settingGapple;
   public final ModeSetting settingGapple2;
   public final ModeSetting settingGapple3;
   public final ModeSetting settingGapple4;
   public final ModeSetting settingGapple5;
   public final ModeSetting settingGapple6;
   public final ModeSetting settingGapple7;
   public final ModeSetting settingGapple8;
   public final ModeSetting settingGapple9;
   public final TimeHelper timer;
   public final TimeHelper timer2;
   public final TimeHelper timer3;
   public final TimeHelper timer4;
   public final ArrayList arrayList;
   public boolean enabled3;
   public long timestamp;
   public boolean enabled4;
   public int count6;

   public InvCleaner() {
        super("InvCleaner", "InventarLeerer", new Color(32, 94, 103), Category.PLAYER);
      
      this.settingBasic = new ModeSetting(this, "Mode", "OpenInv", new String[]{"OpenInv", "SpoofInv", "Basic"});
      BooleanSetting setting = new BooleanSetting(this, "NoMove", false);
      BooleanSupplier booleanSupplier = this::isActive2;
      this.settingNoMove = (BooleanSetting)setting.getSetting(booleanSupplier);
      setting = new BooleanSetting(this, "InteractCheck", false);
      BooleanSupplier booleanSupplier2 = this::isActive;
      this.settingInteractCheck = (BooleanSetting)setting.getSetting(booleanSupplier2);
      setting = new BooleanSetting(this, "StopMovement", false);
      BooleanSupplier booleanSupplier3 = this::isActiveAsFlag;
      this.settingStopMovement = (BooleanSetting)setting.getSetting(booleanSupplier3);
      this.settingStartDelay = new NumberSetting(this, "StartDelay", 100.0, 0.0, 1000.0, 0);
      this.actionSetting = new ActionSetting(this, "SortDelay", 70.0, 120.0, 0.0, 400.0, 0);
      this.actionSetting2 = new ActionSetting(this, "DropDelay", 70.0, 120.0, 0.0, 400.0, 0);
      this.actionSetting3 = new ActionSetting(this, "DelayBeforeAutoArmor", 70.0, 120.0, 0.0, 400.0, 0);
      this.settingSort = new BooleanSetting(this, "Sort", true);
      this.settingSmartDelay = new BooleanSetting(this, "SmartDelay", false);
      this.settingStopOnInteraction = new BooleanSetting(this, "StopOnInteraction", false);
      this.settingVisualize = new BooleanSetting(this, "Visualize", false);
      this.settingFailRate = new NumberSetting(this, "FailRate", 5.0, 0.0, 100.0, 0);
      this.settingRandom = new ModeSetting(this, "Sequence", "Basic", new String[]{"Basic", "Nearest", "Random"});
      this.settingEventRender = new ModeSetting(this, "EventToClick", "EventTick", new String[]{"EventTick", "EventRender"});
      this.settingBasic2 = new ModeSetting(this, "ClickMode", "Basic", new String[]{"Basic"});
      this.settingSlots = new SettingGroup(this, "Slots");
      this.settingGapple = new ModeSetting(this, "Slot1", "Sword", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.settingSlots);
      this.settingGapple2 = new ModeSetting(this, "Slot2", "Bow", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.settingSlots);
      this.settingGapple3 = new ModeSetting(this, "Slot3", "Rod", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.settingSlots);
      this.settingGapple4 = new ModeSetting(this, "Slot4", "None", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.settingSlots);
      this.settingGapple5 = new ModeSetting(this, "Slot5", "None", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.settingSlots);
      this.settingGapple6 = new ModeSetting(this, "Slot6", "None", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.settingSlots);
      this.settingGapple7 = new ModeSetting(this, "Slot7", "None", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.settingSlots);
      this.settingGapple8 = new ModeSetting(this, "Slot8", "Food", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.settingSlots);
      this.settingGapple9 = new ModeSetting(this, "Slot9", "Gapple", new String[]{"None", "Sword", "Bow", "Rod", "Food", "Gapple"}, this.settingSlots);
      this.timer = new TimeHelper();
      this.timer2 = new TimeHelper();
      this.timer3 = new TimeHelper();
      this.timer4 = new TimeHelper();
      this.arrayList = new ArrayList();
      this.enabled4 = false;
      this.count6 = -1;
   }

   @Override
   public void onPreDisable() {
      byte byteValue = 0;
      int count = 306858775;
      short shortValue = 9708;
      if (MinecraftAccessor.minecraft.thePlayer != null) {
         MinecraftAccessor.minecraft.thePlayer.setGuiScreen(MinecraftAccessor.minecraft.currentScreen);
      }

      SettingProvider settingProvider = SettingContainer.settingProvider;
      double doubleItem = this.actionSetting.getDouble6();
      double doubleItem2 = this.actionSetting.getDouble3();
      this.timestamp = settingProvider.getLong2(doubleItem2, doubleItem);
   }

   @Override
   public void onDisable() {
      this.count6 = -1;
      this.arrayList.clear();
   }

   @Override
   public void onEnable() {
      this.registerEvent(7);
      this.registerEvent(40);
      this.registerEvent(32);
      this.registerEvent(9);
      this.registerEvent(21, 100000);
      this.registerEvent(26);
   }

   @Override
   public void onMove(EventMove event) {
      if (MinecraftAccessor.minecraft.currentScreen == null
         && this.settingBasic.getSelected().equals("SpoofInv")
         && MinecraftAccessor.minecraft.thePlayer.getGuiScreen() != null
         && this.settingStopMovement.isEnabled()) {
         event.setState(false);
         event.setState4(false);
         float factor = 0.0F;
         event.setScaleFactor7(factor);
         float factor2 = 0.0F;
         event.setScaleFactor2(factor2);
      }
   }

   @Override
   public void onClick(EventClick event) {
      this.enabled3 = false;
      if (this.settingBasic.getSelected().equals("SpoofInv")
         && this.settingInteractCheck.isEnabled()
         && (
            MinecraftAccessor.minecraft.gameSettings.keyBindUseItem.isKeyDown()
               || MinecraftAccessor.minecraft.gameSettings.keyBindAttack.isKeyDown()
               || MinecraftAccessor.minecraft.gameSettings.keyBindAttack.getPressTime() != 0
               || MinecraftAccessor.minecraft.gameSettings.keyBindUseItem.getPressTime() != 0
               || ((Module)ClientGlobals.moduleManager.killAura).isToggled() && ClientGlobals.moduleManager.killAura.target != null
               || BlockCache.canExecute()
         )) {
         this.updateState4();
         this.enabled3 = true;
         this.timer2.reset();
      }
   }

   @Override
   public void onPreTick() {
      String selected = this.settingBasic.getSelected();
      this.applyString4(selected);
      if (this.settingEventRender.getSelected().equals("EventTick") && !this.settingBasic.getSelected().equals("SpoofInv")) {
         this.updateState3();
      }

      if (this.settingEventRender.getSelected().equals("EventTick") && this.settingBasic.getSelected().equals("SpoofInv")) {
         if (MinecraftAccessor.minecraft.thePlayer.getGuiScreen() instanceof GuiInventory) {
            if (MinecraftAccessor.minecraft.currentScreen != null) {
               this.updateState3();
            }

            this.arrayList.forEach(task -> ((Runnable)task).run());
            this.arrayList.clear();
            this.updateState4();
         } else if (!this.arrayList.isEmpty()) {
            this.updateState();
         } else {
            this.updateState3();
         }
      }
   }

   @Override
   public void eatSoup() {
      if (this.settingEventRender.getSelected().equals("EventTick")) {
         this.settingBasic.getSelected().equals("SpoofInv");
      }
   }

   @Override
   public void onRender3D(float factor) {
      if (this.settingEventRender.getSelected().equals("EventRender")) {
         this.updateState3();
      }
   }

   @Override
   public void onPacketSend(EventPacket packet) {
      if (packet.getPacket() instanceof C0DPacketCloseWindow) {
         this.enabled4 = true;
      }
   }

   private void updateState3() {
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
      int count = this.settingGapple.getSelected().equals("Sword")
         ? 0
         : (
            this.settingGapple2.getSelected().equals("Sword")
               ? 1
               : (
                  this.settingGapple3.getSelected().equals("Sword")
                     ? 2
                     : (
                        this.settingGapple4.getSelected().equals("Sword")
                           ? 3
                           : (
                              this.settingGapple5.getSelected().equals("Sword")
                                 ? 4
                                 : (
                                    this.settingGapple6.getSelected().equals("Sword")
                                       ? 5
                                       : (
                                          this.settingGapple7.getSelected().equals("Sword")
                                             ? 6
                                             : (
                                                this.settingGapple8.getSelected().equals("Sword")
                                                   ? 7
                                                   : (this.settingGapple9.getSelected().equals("Sword") ? 8 : -1)
                                             )
                                       )
                                 )
                           )
                     )
               )
         );
      int count2 = this.settingGapple.getSelected().equals("Bow")
         ? 0
         : (
            this.settingGapple2.getSelected().equals("Bow")
               ? 1
               : (
                  this.settingGapple3.getSelected().equals("Bow")
                     ? 2
                     : (
                        this.settingGapple4.getSelected().equals("Bow")
                           ? 3
                           : (
                              this.settingGapple5.getSelected().equals("Bow")
                                 ? 4
                                 : (
                                    this.settingGapple6.getSelected().equals("Bow")
                                       ? 5
                                       : (
                                          this.settingGapple7.getSelected().equals("Bow")
                                             ? 6
                                             : (
                                                this.settingGapple8.getSelected().equals("Bow")
                                                   ? 7
                                                   : (this.settingGapple9.getSelected().equals("Bow") ? 8 : -1)
                                             )
                                       )
                                 )
                           )
                     )
               )
         );
      int count3 = this.settingGapple.getSelected().equals("Rod")
         ? 0
         : (
            this.settingGapple2.getSelected().equals("Rod")
               ? 1
               : (
                  this.settingGapple3.getSelected().equals("Rod")
                     ? 2
                     : (
                        this.settingGapple4.getSelected().equals("Rod")
                           ? 3
                           : (
                              this.settingGapple5.getSelected().equals("Rod")
                                 ? 4
                                 : (
                                    this.settingGapple6.getSelected().equals("Rod")
                                       ? 5
                                       : (
                                          this.settingGapple7.getSelected().equals("Rod")
                                             ? 6
                                             : (
                                                this.settingGapple8.getSelected().equals("Rod")
                                                   ? 7
                                                   : (this.settingGapple9.getSelected().equals("Rod") ? 8 : -1)
                                             )
                                       )
                                 )
                           )
                     )
               )
         );
      int count4 = this.settingGapple.getSelected().equals("Food")
         ? 0
         : (
            this.settingGapple2.getSelected().equals("Food")
               ? 1
               : (
                  this.settingGapple3.getSelected().equals("Food")
                     ? 2
                     : (
                        this.settingGapple4.getSelected().equals("Food")
                           ? 3
                           : (
                              this.settingGapple5.getSelected().equals("Food")
                                 ? 4
                                 : (
                                    this.settingGapple6.getSelected().equals("Food")
                                       ? 5
                                       : (
                                          this.settingGapple7.getSelected().equals("Food")
                                             ? 6
                                             : (
                                                this.settingGapple8.getSelected().equals("Food")
                                                   ? 7
                                                   : (this.settingGapple9.getSelected().equals("Food") ? 8 : -1)
                                             )
                                       )
                                 )
                           )
                     )
               )
         );
      int count5 = this.settingGapple.getSelected().equals("Gapple")
         ? 0
         : (
            this.settingGapple2.getSelected().equals("Gapple")
               ? 1
               : (
                  this.settingGapple3.getSelected().equals("Gapple")
                     ? 2
                     : (
                        this.settingGapple4.getSelected().equals("Gapple")
                           ? 3
                           : (
                              this.settingGapple5.getSelected().equals("Gapple")
                                 ? 4
                                 : (
                                    this.settingGapple6.getSelected().equals("Gapple")
                                       ? 5
                                       : (
                                          this.settingGapple7.getSelected().equals("Gapple")
                                             ? 6
                                             : (
                                                this.settingGapple8.getSelected().equals("Gapple")
                                                   ? 7
                                                   : (this.settingGapple9.getSelected().equals("Gapple") ? 8 : -1)
                                             )
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
      long value = (long)Math.max(this.settingStartDelay.getValue() + (double)SettingContainer.settingProvider.getLong(-35L, 35L), 0.0);
      if ((
            MinecraftAccessor.minecraft.currentScreen instanceof GuiInventory
               || !this.settingBasic.getSelected().equalsIgnoreCase("OpenInv")
                  && (MinecraftAccessor.minecraft.currentScreen == null || MinecraftAccessor.minecraft.currentScreen instanceof GuiInventory)
                  && (
                     !this.settingNoMove.isEnabled()
                        || this.settingNoMove.isEnabled() && !MoveUtil.canExecute()
                        || !this.settingBasic.getSelected().equals("SpoofInv")
                  )
         )
         && !this.enabled3) {
         for (int index = 0; index < MinecraftAccessor.minecraft.thePlayer.container.itemList2.size(); index++) {
            ItemStack stack11 = MinecraftAccessor.minecraft.thePlayer.container.getSlot(index).getStack();
            if (stack11 != null) {
               if (stack11.getItem() instanceof ItemSword) {
                  stack11.setStackSize(index);
                  list.add(stack11);
                  if (index == count + 36) {
                     stack11.setStackSize(index);
                     stack = stack11;
                  }
               } else if (stack11.getItem() instanceof ItemGoldenAxe) {
                  stack11.setStackSize(index);
                  list7.add(stack11);
                  if (index == count + 36) {
                     stack11.setStackSize(index);
                     stack2 = stack11;
                  }
               } else if (stack11.getItem() instanceof ItemGoldenPickaxe) {
                  stack11.setStackSize(index);
                  list8.add(stack11);
               } else if (stack11.getItem() instanceof ItemGoldenShovel) {
                  stack11.setStackSize(index);
                  list9.add(stack11);
               } else if (stack11.getItem() instanceof ItemBlock) {
                  if (this.matchesItemStack(stack11)) {
                     stack11.setStackSize(index);
                     list10.add(stack11);
                  }
               } else if (stack11.getItem() instanceof ItemBow) {
                  stack11.setStackSize(index);
                  list2.add(stack11);
                  if (index == count2 + 36) {
                     stack11.setStackSize(index);
                  }
               } else if (stack11.getItem() instanceof ItemFishingRod) {
                  stack11.setStackSize(index);
                  list3.add(stack11);
                  if (index == count3 + 36) {
                     stack11.setStackSize(index);
                  }
               } else if (stack11.getItem() instanceof ItemFood) {
                  if (stack11.getItem() == Item.getItem("golden_apple")) {
                     stack11.setStackSize(index);
                     list5.add(stack11);
                     list10.add(stack11);
                  } else if (this.matchesItemStack(stack11)) {
                     stack11.setStackSize(index);
                     list4.add(stack11);
                     list10.add(stack11);
                  }
               } else if (stack11.getItem() instanceof ItemPotion) {
                  ItemPotion item = (ItemPotion)stack11.getItem();
                  if (!item.getList(stack11).isEmpty()) {
                     PotionEffect potionEffect = (PotionEffect)item.getList(stack11).get(0);
                     if (!Potion.potionArray[potionEffect.getIndexOffset3()].isState2()) {
                        stack11.setStackSize(index);
                        list6.add(stack11);
                        list10.add(stack11);
                     }
                  }
               } else if (stack11.getItem() instanceof ItemTool) {
                  if (this.matchesItemStack(stack11)) {
                     stack11.setStackSize(index);
                     list10.add(stack11);
                  }
               } else if (this.matchesItemStackByItemStack(stack11)) {
                  stack11.setStackSize(index);
                  list10.add(stack11);
               } else if (!(stack11.getItem() instanceof ItemGoldenBoots) && this.matchesItemStack(stack11)) {
                  stack11.setStackSize(index);
                  list10.add(stack11);
               }
            }
         }

         for (ItemStack stack12 : (Iterable<ItemStack>)(Iterable<?>)list) {
            if (stack12.getStackSize() != count + 36) {
               if (stack == null) {
                  if (stack3 != null) {
                     if (this.computeValueByItemStack(stack12) > this.computeValueByItemStack(stack3)) {
                        stack3 = stack12;
                     } else if (this.computeValueByItemStack(stack12) == this.computeValueByItemStack(stack3) && stack12.getIndexOffset2() < stack3.getIndexOffset2()
                        )
                      {
                        stack3 = stack12;
                     }
                  } else {
                     stack3 = stack12;
                  }
               } else if (stack.getItem() instanceof ItemSword) {
                  if (stack3 != null) {
                     if (this.computeValueByItemStack(stack12) > this.computeValueByItemStack(stack3)) {
                        stack3 = stack12;
                     } else if (this.computeValueByItemStack(stack12) == this.computeValueByItemStack(stack3) && stack12.getIndexOffset2() < stack3.getIndexOffset2()
                        )
                      {
                        stack3 = stack12;
                     }
                  } else if (this.computeValueByItemStack(stack12) > this.computeValueByItemStack(stack)) {
                     stack3 = stack12;
                  } else if (this.computeValueByItemStack(stack12) == this.computeValueByItemStack(stack) && stack12.getIndexOffset2() < stack.getIndexOffset2()) {
                     stack3 = stack12;
                  }
               }
            }
         }

         for (ItemStack stack13 : (Iterable<ItemStack>)(Iterable<?>)list7) {
            if (stack2 == null) {
               if (stack8 != null) {
                  if (this.computeValue3(stack13) > this.computeValue3(stack8)) {
                     stack8 = stack13;
                  } else if (this.computeValue3(stack13) == this.computeValue3(stack8) && stack13.getIndexOffset2() < stack8.getIndexOffset2()) {
                     stack8 = stack13;
                  }
               } else {
                  stack8 = stack13;
               }
            } else if (stack2.getItem() instanceof ItemGoldenAxe) {
               if (stack8 != null) {
                  if (this.computeValue3(stack13) > this.computeValue3(stack8)) {
                     stack8 = stack13;
                  } else if (this.computeValue3(stack13) == this.computeValue3(stack8) && stack13.getIndexOffset2() < stack8.getIndexOffset2()) {
                     stack8 = stack13;
                  }
               } else if (this.computeValue3(stack13) > this.computeValue3(stack2)) {
                  stack8 = stack13;
               } else if (this.computeValue3(stack13) == this.computeValue3(stack2) && stack13.getIndexOffset2() < stack2.getIndexOffset2()) {
                  stack8 = stack13;
               }
            }
         }

         for (ItemStack stack14 : (Iterable<ItemStack>)(Iterable<?>)list8) {
            if (stack9 != null) {
               if (this.computeValue2(stack14) > this.computeValue2(stack9)) {
                  stack9 = stack14;
               } else if (this.computeValue2(stack14) == this.computeValue2(stack9) && stack14.getIndexOffset2() < stack9.getIndexOffset2()) {
                  stack9 = stack14;
               }
            } else {
               stack9 = stack14;
            }
         }

         for (ItemStack stack15 : (Iterable<ItemStack>)(Iterable<?>)list9) {
            if (stack10 != null) {
               if (this.computeValue2(stack15) > this.computeValue2(stack10)) {
                  stack10 = stack15;
               } else if (this.computeValue2(stack15) == this.computeValue2(stack10) && stack15.getIndexOffset2() < stack10.getIndexOffset2()) {
                  stack10 = stack15;
               }
            } else {
               stack10 = stack15;
            }
         }

         for (ItemStack stack16 : (Iterable<ItemStack>)(Iterable<?>)list2) {
            if (stack4 != null) {
               if (this.computeValue(stack16) > this.computeValue(stack4)) {
                  stack4 = stack16;
               }
            } else {
               stack4 = stack16;
            }
         }

         for (ItemStack stack17 : (Iterable<ItemStack>)(Iterable<?>)list3) {
            if (stack17.getStackSize() != count3 + 36) {
               if (stack5 != null) {
                  if (stack17.getIndexOffset2() < stack5.getIndexOffset2()) {
                     stack5 = stack17;
                  }
               } else {
                  stack5 = stack17;
               }
            }
         }

         for (ItemStack stack18 : (Iterable<ItemStack>)(Iterable<?>)list4) {
            if (stack18.getStackSize() != count4 + 36) {
               if (stack6 != null) {
                  ItemFood item2 = (ItemFood)stack18.getItem();
                  ItemFood item3 = (ItemFood)stack6.getItem();
                  if (item2.computeCount(stack18) > item3.computeCount(stack6)) {
                     stack6 = stack18;
                  } else if (item2.computeCount(stack18) == item3.computeCount(stack6) && item2.computeFactor(stack18) > item3.computeFactor(stack6)) {
                     stack6 = stack18;
                  }
               } else {
                  stack6 = stack18;
               }
            }
         }

         for (ItemStack stack19 : (Iterable<ItemStack>)(Iterable<?>)list5) {
            if (stack19.getStackSize() != count5 + 36) {
               if (stack7 != null) {
                  if (stack19.animationsToGo > stack7.animationsToGo) {
                     stack7 = stack19;
                  }
               } else {
                  stack7 = stack19;
               }
            }
         }

         if (stack3 != null) {
            list10.add(stack3);
         }

         if (stack4 != null) {
            list10.add(stack4);
         }

         if (stack5 != null) {
            list10.add(stack5);
         }

         if (stack2 != null) {
            list10.add(stack2);
         }

         if (stack8 != null) {
            list10.add(stack8);
         }

         if (stack9 != null) {
            list10.add(stack9);
         }

         if (stack10 != null) {
            list10.add(stack10);
         }

         if (this.timer2.hasReached(value)) {
            long timestamp2 = this.timestamp;
            if (this.timer.hasReached(timestamp2) && this.settingSort.isEnabled()) {
               if (stack3 != null && count >= 0) {
                  this.applyItemStackByItemStack(stack3, count);
                  this.timer3.reset();
                  if (this.actionSetting.getDouble3() != 0.0 || this.actionSetting.getDouble6() != 0.0) {
                     return;
                  }
               }

               if (stack == null && stack2 == null && stack8 != null && count > 0) {
                  this.applyItemStackByItemStack(stack8, count);
                  this.timer3.reset();
                  if (this.actionSetting.getDouble3() != 0.0 || this.actionSetting.getDouble6() != 0.0) {
                     return;
                  }
               }

               if (stack4 != null && count2 >= 0) {
                  this.applyItemStackByItemStack(stack4, count2);
                  this.timer3.reset();
                  if (this.actionSetting.getDouble3() != 0.0 || this.actionSetting.getDouble6() != 0.0) {
                     return;
                  }
               }

               if (stack5 != null && count3 >= 0) {
                  this.applyItemStackByItemStack(stack5, count3);
                  this.timer3.reset();
                  if (this.actionSetting.getDouble3() != 0.0 || this.actionSetting.getDouble6() != 0.0) {
                     return;
                  }
               }

               if (stack6 != null && count4 >= 0) {
                  this.applyItemStackByItemStack(stack6, count4);
                  this.timer3.reset();
                  if (this.actionSetting.getDouble3() != 0.0 || this.actionSetting.getDouble6() != 0.0) {
                     return;
                  }
               }

               if (stack7 != null && count5 >= 0) {
                  this.applyItemStackByItemStack(stack7, count5);
                  this.timer3.reset();
                  if (this.actionSetting.getDouble3() != 0.0 || this.actionSetting.getDouble6() != 0.0) {
                     return;
                  }
               }
            }
         }
      } else {
         this.timer2.reset();
         this.timer.reset();
      }
   }

   private double computeValue(int count, int count2) {
      GuiInventory currentScreen = (GuiInventory)MinecraftAccessor.minecraft.currentScreen;
      Slot slot = currentScreen.container.getSlot(count2);
      Slot slot2 = currentScreen.container.getSlot(count);
      double offset = (double)Math.abs(slot.indexOffset + 2 + currentScreen.indexOffset1 - (slot2.indexOffset + 2 + currentScreen.indexOffset1));
      double offset2 = (double)Math.abs(slot.indexOffset2 + 2 + currentScreen.indexOffset2 - (slot2.indexOffset2 + 2 + currentScreen.indexOffset2));
      return Math.sqrt(offset * offset + offset2 * offset2);
   }

   private double computeValue(double[] offsetArray, int count) {
      GuiInventory currentScreen = (GuiInventory)MinecraftAccessor.minecraft.currentScreen;
      Slot slot = currentScreen.container.getSlot(count);
      double offset = Math.abs((double)(slot.indexOffset + 2 + currentScreen.indexOffset1) - offsetArray[0]);
      double offset2 = Math.abs((double)(slot.indexOffset2 + 2 + currentScreen.indexOffset2) - offsetArray[1]);
      return Math.sqrt(offset * offset + offset2 * offset2);
   }

   private void updateState4() {
      if (MinecraftAccessor.minecraft.thePlayer.getGuiScreen() != null
         && this.settingBasic.getSelected().equals("SpoofInv")
         && MinecraftAccessor.minecraft.currentScreen == null) {
         MinecraftAccessor.minecraft
            .thePlayer
            .netHandlerPlayClient
            .handlePacket(new C0DPacketCloseWindow(MinecraftAccessor.minecraft.thePlayer.container.indexOffset2));
         MinecraftAccessor.minecraft.thePlayer.setGuiScreen(null);
      }
   }

   private void updateState() {
      if (this.settingBasic.getSelected().equals("SpoofInv")
         && MinecraftAccessor.minecraft.thePlayer.getGuiScreen() == null
         && MinecraftAccessor.minecraft.currentScreen == null) {
         MinecraftAccessor.minecraft
            .thePlayer
            .netHandlerPlayClient
            .handlePacket(new C16PacketClientStatus(AutoSoupPerformRespawnType.OPEN_INVENTORY_ACHIEVEMENT));
         MinecraftAccessor.minecraft.thePlayer.setGuiScreen(new GuiInventory(MinecraftAccessor.minecraft.thePlayer));
      }
   }

   private boolean matchesItemStackByItemStack(ItemStack stack) {
      Item item = stack.getItem();
      return item.equals(Item.processAction(288)) || item.equals(Item.processAction(289));
   }

   private boolean matchesItemStack(ItemStack stack) {
      Item item = stack.getItem();

      for (int index = 183; index < 192; index++) {
         if (item.equals(Item.processAction(index))) {
            return false;
         }
      }

      for (int index2 = 290; index2 < 295; index2++) {
         if (item.equals(Item.processAction(index2))) {
            return false;
         }
      }

      if (item.equals(Item.processAction(39)) || item.equals(Item.processAction(40))) {
         return ((Module)ClientGlobals.moduleManager.autoSoup).isToggled();
      } else {
         return item.equals(Item.processAction(287))
            ? ((Module)ClientGlobals.moduleManager.autoRod).isToggled()
            : !item.equals(Item.processAction(289))
               && !item.equals(Item.processAction(289))
               && !item.equals(Item.getItem("tallgrass"))
               && !item.equals(Item.getItem("deadbush"))
               && !item.equals(Item.getItem("red_flower"))
               && !item.equals(Item.processAction(53))
               && !item.equals(Item.processAction(66))
               && !item.equals(Item.processAction(67))
               && !item.equals(Item.processAction(70))
               && !item.equals(Item.processAction(72))
               && !item.equals(Item.processAction(77))
               && !item.equals(Item.processAction(85))
               && !item.equals(Item.processAction(81))
               && !item.equals(Item.processAction(96))
               && !item.equals(Item.processAction(101))
               && !item.equals(Item.processAction(102))
               && !item.equals(Item.processAction(106))
               && !item.equals(Item.processAction(107))
               && !item.equals(Item.processAction(108))
               && !item.equals(Item.processAction(109))
               && !item.equals(Item.processAction(111))
               && !item.equals(Item.processAction(113))
               && !item.equals(Item.processAction(114))
               && !item.equals(Item.processAction(128))
               && !item.equals(Item.processAction(131))
               && !item.equals(Item.processAction(134))
               && !item.equals(Item.processAction(135))
               && !item.equals(Item.processAction(136))
               && !item.equals(Item.processAction(143))
               && !item.equals(Item.processAction(136))
               && !item.equals(Item.processAction(147))
               && !item.equals(Item.processAction(148))
               && !item.equals(Item.processAction(151))
               && !item.equals(Item.processAction(154))
               && !item.equals(Item.processAction(156))
               && !item.equals(Item.processAction(157))
               && !item.equals(Item.processAction(163))
               && !item.equals(Item.processAction(164))
               && !item.equals(Item.processAction(167))
               && !item.equals(Item.processAction(180))
               && !item.equals(Item.processAction(287))
               && !item.equals(Item.processAction(287))
               && !item.equals(Item.processAction(318))
               && !item.equals(Item.processAction(321))
               && !item.equals(Item.processAction(337))
               && !item.equals(Item.processAction(338))
               && !item.equals(Item.processAction(323))
               && !item.equals(Item.processAction(348))
               && !item.equals(Item.processAction(352))
               && !item.equals(Item.processAction(353))
               && !item.equals(Item.processAction(354))
               && !item.equals(Item.processAction(356))
               && !item.equals(Item.processAction(361))
               && !item.equals(Item.processAction(259))
               && !item.equals(Item.processAction(362))
               && !item.equals(Item.processAction(367))
               && !item.equals(Item.processAction(370))
               && !item.equals(Item.processAction(371))
               && !item.equals(Item.processAction(372))
               && !item.equals(Item.processAction(373))
               && !item.equals(Item.processAction(334))
               && !item.equals(Item.getItem("snow_layer"))
               && !item.equals(Item.getItem("wooden_slab"))
               && !item.equals(Item.getItem("cobblestone_wall"))
               && !item.equals(Item.getItem("anvil"))
               && !item.equals(Item.getItem("stained_glass_pane"))
               && !item.equals(Item.getItem("carpet"))
               && !item.equals(Item.getItem("double_plant"))
               && !item.equals(Item.getItem("stone_slab2"))
               && !item.equals(Item.getItem("sapling"));
      }
   }

   private void applyItemStackByItemStack(ItemStack stack, int count) {
      if (!((double)SettingContainer.settingProvider.getInt2(1, 100) < this.settingFailRate.getValue())) {
         this.updateState();
         if (this.settingBasic.getSelected().equals("SpoofInv") && this.settingEventRender.getSelected().equals("EventTick")) {
            this.arrayList.add((Runnable)(() -> this.applyItemStack(stack, count)));
         } else {
            this.applyItemStack2(stack, count);
         }
      }
   }

   private void applyItemStack2(ItemStack stack, int count) {
      long value = 0x1e34f4bf6596L;
      if (MinecraftAccessor.minecraft.currentScreen instanceof GuiInventory) {
         GuiInventory currentScreen = (GuiInventory)MinecraftAccessor.minecraft.currentScreen;
         Slot slot = currentScreen.container.getSlot(stack.getStackSize());
         if (this.settingSmartDelay.isEnabled()) {
            double offset = Math.abs((double)(slot.indexOffset + 2 + currentScreen.indexOffset1) - InventoryUtil.getCoordOffsetArray()[0]);
            double offset2 = Math.abs((double)(slot.indexOffset2 + 2 + currentScreen.indexOffset2) - InventoryUtil.getCoordOffsetArray()[1]);
            double offset3 = Math.sqrt(offset * offset + offset2 * offset2);
            double offset4 = this.actionSetting.getDouble3() + this.actionSetting.getDouble6() - this.actionSetting.getDouble3() / 2.0;
            double offset5 = offset3 / 30.0;
            if (offset3 > 36.0) {
               long value2 = (long)(offset4 * offset5);
               if (!this.timer.hasReached(value2)) {
                  return;
               }
            }
         }

         double[] offsetArray = new double[]{(double)(slot.indexOffset + 2 + currentScreen.indexOffset1), (double)(slot.indexOffset2 + 2 + currentScreen.indexOffset2)};
      }

      this.updateState2();
      String selected = this.settingBasic2.getSelected();
      switch (selected) {
         case "Exploit":
            Client.getClient().getClass();
            break;
         case "Other":
            MinecraftAccessor.minecraft
               .playerController
               .windowClick(MinecraftAccessor.minecraft.thePlayer.container.indexOffset2, stack.getStackSize(), count, 2, MinecraftAccessor.minecraft.thePlayer);
      }

      ItemStack stack2 = MinecraftAccessor.minecraft.thePlayer.container.getSlot(stack.getStackSize()).getStack();
      if (stack2 != null) {
         stack2.setStackSize(stack.getStackSize());
      }

      stack.setStackSize(count + 36);
      SettingProvider settingProvider = SettingContainer.settingProvider;
      double doubleItem = this.actionSetting.getDouble6();
      double doubleItem2 = this.actionSetting.getDouble3();
      this.timestamp = settingProvider.getLong2(doubleItem2, doubleItem);
   }

   private void applyIndex(int count) {
      if (!((double)SettingContainer.settingProvider.getInt2(1, 100) < this.settingFailRate.getValue())) {
         this.updateState();
         if (this.settingBasic.getSelected().equals("SpoofInv") && this.settingEventRender.getSelected().equals("EventTick")) {
            this.arrayList.add((Runnable)(() -> this.applyIndexByIndex(count)));
         } else {
            this.applyIndex2(count);
            SettingProvider settingProvider = SettingContainer.settingProvider;
            double doubleItem = this.actionSetting2.getDouble6();
            double doubleItem2 = this.actionSetting2.getDouble3();
            this.timestamp = settingProvider.getLong2(doubleItem2, doubleItem);
         }
      }
   }

   private void applyIndex2(int count) {
      if (MinecraftAccessor.minecraft.currentScreen instanceof GuiInventory) {
         GuiInventory currentScreen = (GuiInventory)MinecraftAccessor.minecraft.currentScreen;
         Slot slot = currentScreen.container.getSlot(count);
         if (this.settingSmartDelay.isEnabled()) {
            double offset = Math.abs((double)(slot.indexOffset + 2 + currentScreen.indexOffset1) - InventoryUtil.getCoordOffsetArray()[0]);
            double offset2 = Math.abs((double)(slot.indexOffset2 + 2 + currentScreen.indexOffset2) - InventoryUtil.getCoordOffsetArray()[1]);
            double offset3 = Math.sqrt(offset * offset + offset2 * offset2);
            double offset4 = this.actionSetting2.getDouble3() + this.actionSetting2.getDouble6() - this.actionSetting2.getDouble3() / 2.0;
            double offset5 = offset3 / 30.0;
            if (offset3 > 36.0) {
               long value = (long)(offset4 * offset5);
               if (!this.timer.hasReached(value)) {
                  return;
               }
            }
         }

         double[] offsetArray = new double[]{(double)(slot.indexOffset + 2 + currentScreen.indexOffset1), (double)(slot.indexOffset2 + 2 + currentScreen.indexOffset2)};
      }

      this.updateState2();
      String selected = this.settingBasic2.getSelected();
      switch (selected) {
         case "Basic":
            MinecraftAccessor.minecraft
               .playerController
               .windowClick(MinecraftAccessor.minecraft.thePlayer.container.indexOffset2, count, 1, 4, MinecraftAccessor.minecraft.thePlayer);
            break;
         case "Other":
            Client.getClient().getClass();
            break;
         case "Exploit":
            Client.getClient().getClass();
      }
   }

   private double computeValueByItemStack(ItemStack stack) {
      double offset = 0.0;
      if (stack.getItem() instanceof ItemSword) {
         offset = offset
            + (double)(
               ((ItemSword)stack.getItem()).getScaleFactor() + (float)LvlManager.getIndex(EnchantmentManager.enchantmentManager2.indexOffset, stack) * 1.25F
            )
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager3.indexOffset, stack) / 11.0
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager.indexOffset, stack) / 11.0
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager1.indexOffset, stack) / 11.0;
      }

      return offset;
   }

   private double computeValue3(ItemStack stack) {
      double offset = 0.0;
      if (stack.getItem() instanceof ItemGoldenAxe) {
         offset += (double)stack.getItem().processAction(stack, new Block(Material.material, MapColor.mapColor18))
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager5.indexOffset, stack) / 11.0;
      } else if (stack.getItem() instanceof ItemGoldenPickaxe) {
         offset += (double)stack.getItem().processAction(stack, new Block(Material.material4, MapColor.mapColor10))
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager5.indexOffset, stack) / 11.0;
      } else if (stack.getItem() instanceof ItemGoldenShovel) {
         offset += (double)stack.getItem().processAction(stack, new Block(Material.material28, MapColor.mapColor20))
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager5.indexOffset, stack) / 11.0;
      }

      return offset
         + (double)((float)stack.getItem().getIndexOffset2() + (float)LvlManager.getIndex(EnchantmentManager.enchantmentManager2.indexOffset, stack) * 1.25F)
         + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager3.indexOffset, stack) / 11.0
         + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager.indexOffset, stack) / 11.0
         + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager1.indexOffset, stack) / 11.0;
   }

   private double computeValue2(ItemStack stack) {
      double offset = 0.0;
      if (stack.getItem() instanceof ItemTool) {
         if (stack.getItem() instanceof ItemGoldenAxe) {
            offset += (double)(
               stack.getItem().processAction(stack, new Block(Material.material, MapColor.mapColor18))
                  + (float)LvlManager.getIndex(EnchantmentManager.enchantmentManager5.indexOffset, stack)
            );
         } else if (stack.getItem() instanceof ItemGoldenPickaxe) {
            offset += (double)(
               stack.getItem().processAction(stack, new Block(Material.material4, MapColor.mapColor10))
                  + (float)LvlManager.getIndex(EnchantmentManager.enchantmentManager5.indexOffset, stack)
            );
         } else if (stack.getItem() instanceof ItemGoldenShovel) {
            offset += (double)(
               stack.getItem().processAction(stack, new Block(Material.material28, MapColor.mapColor20))
                  + (float)LvlManager.getIndex(EnchantmentManager.enchantmentManager5.indexOffset, stack)
            );
         }

         offset = offset
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager1.indexOffset, stack) / 11.0
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager4.indexOffset, stack) / 11.0
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager2.indexOffset, stack) / 33.0;
      }

      return offset;
   }

   private double computeValue(ItemStack stack) {
      double offset = 10.0;
      if (stack != null && stack.getItem() instanceof ItemBow) {
         offset = offset
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager10.indexOffset, stack) / 5.0
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager8.indexOffset, stack) / 11.0
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager24.indexOffset, stack) / 5.0
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager17.indexOffset, stack) / 11.0
            - (double)stack.getIndexOffset2() / 10000.0;
      }

      return offset;
   }

   public void updateState2() {
      this.timer.reset();
      this.timer3.reset();
   }

   public TimeHelper getTimer3() {
      return this.timer3;
   }

   public TimeHelper getTimer() {
      return this.timer;
   }

   public int getCount6() {
      return this.count6;
   }

   private void applyIndexByIndex(int count) {
      this.applyIndex2(count);
   }

   private void applyItemStack(ItemStack stack, int count) {
      this.applyItemStack2(stack, count);
   }

   private boolean isActiveAsFlag() {
      return this.settingBasic.getSelected().equals("SpoofInv");
   }

   private boolean isActive() {
      return this.settingBasic.getSelected().equals("SpoofInv");
   }

   private boolean isActive2() {
      return this.settingBasic.getSelected().equals("SpoofInv");
   }
}
