package augustus.modules.misc;

import client.Client;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventAutoSoupListener;
import client.event.EventClick;
import client.event.EventMove;
import client.event.MotionListener;
import client.event.MoveListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.ActionSetting;
import client.setting.AutoSoupPerformRespawnType;
import client.setting.BooleanSetting;
import client.setting.CrafterLeatherType;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.SettingContainer;
import client.setting.SettingProvider;
import client.util.BooleanSupplier;
import client.util.EnchantmentManager;
import client.util.InventoryUtil;
import client.util.LvlManager;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;
import client.util.MovingObjectType;
import client.util.SlotManager;
import client.util.TimeHelper;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.block.BlockCache;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C16PacketClientStatus;

public class AutoArmor extends Module implements ClickListener, TickListener, RenderListener, MotionListener, MoveListener, EventAutoSoupListener {
   public final ModeSetting settingBasic;
   public final BooleanSetting settingNoMove;
   public final BooleanSetting settingInteractCheck;
   public final BooleanSetting settingStopMovement;
   public final NumberSetting settingStartDelay;
   public final ActionSetting actionSetting;
   public final BooleanSetting settingHotbar;
   public final BooleanSetting settingGommeQSG;
   public final NumberSetting settingHStartDelay;
   public final ActionSetting actionSetting2;
   public final BooleanSetting settingSmartDelay;
   public final BooleanSetting settingStopOnInteraction;
   public final BooleanSetting settingVisualize;
   public final ModeSetting settingNearest;
   public final ModeSetting settingEventRender;
   public final ModeSetting settingBasic2;
   public final TimeHelper timer;
   public final TimeHelper timer2;
   public final TimeHelper timer3;
   public final ArrayList arrayList;
   public ArrayList arrayList2;
   public ArrayList arrayList3;
   public ArrayList arrayList4;
   public ArrayList arrayList5;
   public boolean enabled3;
   public boolean enabled4;
   public long timestamp;
   public long timestamp2;
   public int count6;
   public int count7;

   public AutoArmor() {
        super("AutoArmor", "AutoRüstung", new Color(23, 123, 208, 255), Category.PLAYER);
      
      this.settingBasic = new ModeSetting(this, "Mode", "OpenInv", new String[]{"OpenInv", "SpoofInv", "Basic"});
      BooleanSetting setting = new BooleanSetting(this, "NoMove", false);
      BooleanSupplier booleanSupplier = this::isActive;
      this.settingNoMove = (BooleanSetting)setting.getSetting(booleanSupplier);
      setting = new BooleanSetting(this, "InteractCheck", false);
      BooleanSupplier booleanSupplier2 = this::isActive3;
      this.settingInteractCheck = (BooleanSetting)setting.getSetting(booleanSupplier2);
      setting = new BooleanSetting(this, "StopMovement", false);
      BooleanSupplier booleanSupplier3 = this::isActive6;
      this.settingStopMovement = (BooleanSetting)setting.getSetting(booleanSupplier3);
      this.settingStartDelay = new NumberSetting(this, "StartDelay", 200.0, 0.0, 1000.0, 0);
      this.actionSetting = new ActionSetting(this, "Delay", 50.0, 100.0, 0.0, 400.0, 0);
      setting = new BooleanSetting(this, "Hotbar", false);
      BooleanSupplier booleanSupplier4 = this::isActive2;
      this.settingHotbar = (BooleanSetting)setting.getSetting(booleanSupplier4);
      setting = new BooleanSetting(this, "GommeQSG", false);
      BooleanSupplier booleanSupplier5 = this::isActive5;
      this.settingGommeQSG = (BooleanSetting)setting.getSetting(booleanSupplier5);
      NumberSetting setting2 = new NumberSetting(this, "HStartDelay", 200.0, 0.0, 1000.0, 0);
      BooleanSupplier booleanSupplier6 = this::isActive4;
      this.settingHStartDelay = setting2.getNumberSetting(booleanSupplier6);
      ActionSetting setting3 = new ActionSetting(this, "HotbarDelay", 70.0, 120.0, 0.0, 400.0, 0);
      BooleanSupplier booleanSupplier7 = this::isActiveAsFlag;
      this.actionSetting2 = setting3.getActionSetting(booleanSupplier7);
      this.settingSmartDelay = new BooleanSetting(this, "SmartDelay", false);
      this.settingStopOnInteraction = new BooleanSetting(this, "StopOnInteraction", false);
      this.settingVisualize = new BooleanSetting(this, "Visualize", false);
      this.settingNearest = new ModeSetting(this, "Sequence", "Basic", new String[]{"Basic", "Nearest"});
      this.settingEventRender = new ModeSetting(this, "EventToClick", "EventTick", new String[]{"EventTick", "EventRender"});
      this.settingBasic2 = new ModeSetting(this, "ClickMode", "Basic", new String[]{"Basic"});
      this.timer = new TimeHelper();
      this.timer2 = new TimeHelper();
      this.timer3 = new TimeHelper();
      this.arrayList = new ArrayList();
      this.enabled4 = true;
      this.timestamp = 0L;
      this.timestamp2 = 0L;
      this.count6 = -1;
      this.count7 = 0;
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
      settingProvider = SettingContainer.settingProvider;
      double doubleItem3 = this.actionSetting2.getDouble6();
      double doubleItem4 = this.actionSetting2.getDouble3();
      this.timestamp2 = settingProvider.getLong2(doubleItem4, doubleItem3);
   }

   @Override
   public void onDisable() {
      this.count6 = -1;
   }

   @Override
   public void onEnable() {
      this.registerEvent(7);
      this.registerEvent(40);
      this.registerEvent(32);
      this.registerEvent(34);
      this.registerEvent(21);
      this.registerEvent(9);
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
         this.updateState2();
         this.enabled3 = true;
         this.timer.reset();
      }

      if (this.settingBasic.getSelected().equalsIgnoreCase("OpenInv")
         && this.settingHotbar.isEnabled()
         && MinecraftAccessor.minecraft.currentScreen == null
         && this.count7 >= 0) {
         event.updateInt2(1);
         this.count7 = -11;
      }
   }

   @Override
   public void onPostMotion() {
      if (this.settingBasic.getSelected().equalsIgnoreCase("OpenInv") && this.settingHotbar.isEnabled() && MinecraftAccessor.minecraft.currentScreen == null) {
         this.updateState();
         if (this.settingHotbar.isEnabled() && this.count7 >= 0) {
            int count = this.count7;
            byte byteValue = 6;
            SlotManager.applyModule3(this, byteValue, count);
         }
      }
   }

   @Override
   public void onPreTick() {
      String selected = this.settingBasic.getSelected();
      this.applyString4(selected);
      if (MinecraftAccessor.minecraft.currentScreen != null) {
         this.timer2.reset();
      }

      if (this.settingEventRender.getSelected().equals("EventTick")) {
         this.settingBasic.getSelected().equals("SpoofInv");
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
      this.settingEventRender.getSelected().equals("EventRender");
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

   public void updateState() {
      this.arrayList2 = new ArrayList();
      this.arrayList3 = new ArrayList();
      this.arrayList5 = new ArrayList();
      this.arrayList4 = new ArrayList();
      long value2 = (long)(this.settingHStartDelay.getValue() + (double)SettingContainer.settingProvider.getLong(-35L, 35L));
      ItemStack stack = null;
      ItemStack stack2 = null;
      ItemStack stack3 = null;
      ItemStack stack4 = null;
      ItemStack stack5 = null;
      ItemStack stack6 = null;
      ItemStack stack7 = null;
      ItemStack stack8 = null;
      if (this.settingHotbar.isEnabled() && MinecraftAccessor.minecraft.currentScreen == null) {
         boolean enabled = this.settingGommeQSG.isEnabled();

         for (int index = 36; index < MinecraftAccessor.minecraft.thePlayer.container.itemList2.size(); index++) {
            ItemStack stack9 = MinecraftAccessor.minecraft.thePlayer.container.getSlot(index).getStack();
            if (stack9 != null && stack9.getItem() instanceof ItemGoldenBoots) {
               if (((ItemGoldenBoots)stack9.getItem()).indexOffset3 == 0) {
                  stack9.setStackSize(index);
                  this.arrayList3.add(stack9);
               } else if (((ItemGoldenBoots)stack9.getItem()).indexOffset3 == 1) {
                  stack9.setStackSize(index);
                  this.arrayList2.add(stack9);
               } else if (((ItemGoldenBoots)stack9.getItem()).indexOffset3 == 2) {
                  stack9.setStackSize(index);
                  this.arrayList5.add(stack9);
               } else if (((ItemGoldenBoots)stack9.getItem()).indexOffset3 == 3) {
                  stack9.setStackSize(index);
                  this.arrayList4.add(stack9);
               }
            }
         }

         for (int index2 = 0; index2 < MinecraftAccessor.minecraft.thePlayer.container.itemList2.size(); index2++) {
            ItemStack stack10 = MinecraftAccessor.minecraft.thePlayer.container.getSlot(index2).getStack();
            if (stack10 != null && stack10.getItem() instanceof ItemGoldenBoots) {
               if (index2 == 5) {
                  stack10.setStackSize(index2);
                  stack = stack10;
               }

               if (index2 == 6) {
                  stack10.setStackSize(index2);
                  stack3 = stack10;
               }

               if (index2 == 7) {
                  stack10.setStackSize(index2);
                  stack4 = stack10;
               }

               if (index2 == 8) {
                  stack10.setStackSize(index2);
                  stack2 = stack10;
               }
            }
         }

         for (ItemStack stack11 : (Iterable<ItemStack>)(Iterable<?>)this.arrayList3) {
            if (stack == null) {
               if (stack5 != null) {
                  if (this.computeValue(stack11) > this.computeValue(stack5)) {
                     stack5 = stack11;
                  }
               } else {
                  stack5 = stack11;
               }
            } else if (stack.getItem() instanceof ItemGoldenBoots && enabled) {
               if (stack5 != null) {
                  if (this.computeValue(stack11) > this.computeValue(stack5)) {
                     stack5 = stack11;
                  }
               } else if (this.computeValue(stack11) > this.computeValue(stack)) {
                  stack5 = stack11;
               }
            }
         }

         for (ItemStack stack12 : (Iterable<ItemStack>)(Iterable<?>)this.arrayList2) {
            if (stack3 == null) {
               if (stack7 != null) {
                  if (this.computeValue(stack12) > this.computeValue(stack7)) {
                     stack7 = stack12;
                  }
               } else {
                  stack7 = stack12;
               }
            } else if (stack3.getItem() instanceof ItemGoldenBoots && enabled) {
               if (stack7 != null) {
                  if (this.computeValue(stack12) > this.computeValue(stack7)) {
                     stack7 = stack12;
                  }
               } else if (this.computeValue(stack12) > this.computeValue(stack3)) {
                  stack7 = stack12;
               }
            }
         }

         for (ItemStack stack13 : (Iterable<ItemStack>)(Iterable<?>)this.arrayList5) {
            if (stack4 == null) {
               if (stack8 != null) {
                  if (this.computeValue(stack13) > this.computeValue(stack8)) {
                     stack8 = stack13;
                  }
               } else {
                  stack8 = stack13;
               }
            } else if (stack4.getItem() instanceof ItemGoldenBoots && enabled) {
               if (stack8 != null) {
                  if (this.computeValue(stack13) > this.computeValue(stack8)) {
                     stack8 = stack13;
                  }
               } else if (this.computeValue(stack13) > this.computeValue(stack4)) {
                  stack8 = stack13;
               }
            }
         }

         for (ItemStack stack14 : (Iterable<ItemStack>)(Iterable<?>)this.arrayList4) {
            if (stack2 == null) {
               if (stack6 != null) {
                  if (this.computeValue(stack14) > this.computeValue(stack6)) {
                     stack6 = stack14;
                  }
               } else {
                  stack6 = stack14;
               }
            } else if (stack2.getItem() instanceof ItemGoldenBoots && enabled) {
               if (stack6 != null) {
                  if (this.computeValue(stack14) > this.computeValue(stack6)) {
                     stack6 = stack14;
                  }
               } else if (this.computeValue(stack14) > this.computeValue(stack2)) {
                  stack6 = stack14;
               }
            }
         }

         if (this.timer2.hasReached(value2)) {
            long timestamp3 = this.timestamp2;
            if (this.timer.hasReached(timestamp3)
               && (
                  MinecraftAccessor.minecraft.objectMouseOver == null
                     || MinecraftAccessor.minecraft.objectMouseOver.typeOfHit == MovingObjectType.MISS
                     || MinecraftAccessor.minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK
                        && !MinecraftAccessor.minecraft.theWorld.getBlockState(MinecraftAccessor.minecraft.objectMouseOver.getBlockPos()).getBlock().isState8()
               )) {
               SettingProvider settingProvider = SettingContainer.settingProvider;
               double doubleItem = this.actionSetting2.getDouble6();
               double doubleItem2 = this.actionSetting2.getDouble3();
               this.timestamp2 = settingProvider.getLong2(doubleItem2, doubleItem);
               if (stack6 == null && stack8 == null && stack7 == null && stack5 == null) {
                  this.timer2.reset();
               } else {
                  if (stack7 != null) {
                     if (stack3 == null || enabled) {
                        int stackSize = stack7.getStackSize();
                        this.applyIndex4(stackSize);
                     }

                     this.timer.reset();
                     return;
                  }

                  if (stack8 != null) {
                     if (stack4 == null || enabled) {
                        int stackSize2 = stack8.getStackSize();
                        this.applyIndex4(stackSize2);
                     }

                     this.timer.reset();
                     return;
                  }

                  if (stack5 != null) {
                     if (stack == null || enabled) {
                        int stackSize3 = stack5.getStackSize();
                        this.applyIndex4(stackSize3);
                     }

                     this.timer.reset();
                     return;
                  }

                  if (stack6 != null) {
                     if (stack2 == null || enabled) {
                        int stackSize4 = stack6.getStackSize();
                        this.applyIndex4(stackSize4);
                     }

                     this.timer.reset();
                     return;
                  }
               }

               this.count7 = -11;
            }
         } else {
            this.count7 = -11;
         }
      } else {
         this.count7 = -11;
      }
   }

   public void applyArrayList(boolean flag, ArrayList list) {
      this.arrayList2 = new ArrayList();
      this.arrayList3 = new ArrayList();
      this.arrayList5 = new ArrayList();
      this.arrayList4 = new ArrayList();
      long longItem = SettingContainer.settingProvider.getLong(-25L, 25L);
      long value = 0L;
      if (flag) {
         value = (long)(this.settingStartDelay.getValue() + (double)longItem);
      }

      ItemStack stack = null;
      ItemStack stack2 = null;
      ItemStack stack3 = null;
      ItemStack stack4 = null;
      ItemStack stack5 = null;
      ItemStack stack6 = null;
      ItemStack stack7 = null;
      ItemStack stack8 = null;
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
            ItemStack stack9 = MinecraftAccessor.minecraft.thePlayer.container.getSlot(index).getStack();
            if (stack9 != null && stack9.getItem() instanceof ItemGoldenBoots) {
               if (((ItemGoldenBoots)stack9.getItem()).indexOffset3 == 0) {
                  stack9.setStackSize(index);
                  this.arrayList3.add(stack9);
               } else if (((ItemGoldenBoots)stack9.getItem()).indexOffset3 == 1) {
                  stack9.setStackSize(index);
                  this.arrayList2.add(stack9);
               } else if (((ItemGoldenBoots)stack9.getItem()).indexOffset3 == 2) {
                  stack9.setStackSize(index);
                  this.arrayList5.add(stack9);
               } else if (((ItemGoldenBoots)stack9.getItem()).indexOffset3 == 3) {
                  stack9.setStackSize(index);
                  this.arrayList4.add(stack9);
               }

               if (stack9.getStackSize() == 5) {
                  stack9.setStackSize(index);
                  stack = stack9;
               }

               if (stack9.getStackSize() == 6) {
                  stack9.setStackSize(index);
                  stack3 = stack9;
               }

               if (stack9.getStackSize() == 7) {
                  stack9.setStackSize(index);
                  stack4 = stack9;
               }

               if (stack9.getStackSize() == 8) {
                  stack9.setStackSize(index);
                  stack2 = stack9;
               }
            }
         }

         for (ItemStack stack10 : (Iterable<ItemStack>)(Iterable<?>)this.arrayList3) {
            if (stack == null) {
               if (stack5 != null) {
                  if (this.computeValue(stack10) > this.computeValue(stack5)) {
                     stack5 = stack10;
                  } else if (this.computeValue(stack10) == this.computeValue(stack5) && stack10.getIndexOffset2() < stack5.getIndexOffset2()) {
                     stack5 = stack10;
                  }
               } else {
                  stack5 = stack10;
               }
            } else if (stack.getItem() instanceof ItemGoldenBoots) {
               if (stack5 != null) {
                  if (this.computeValue(stack10) > this.computeValue(stack5)) {
                     stack5 = stack10;
                  } else if (this.computeValue(stack10) == this.computeValue(stack5) && stack10.getIndexOffset2() < stack5.getIndexOffset2()) {
                     stack5 = stack10;
                  }
               } else if (this.computeValue(stack10) > this.computeValue(stack)) {
                  stack5 = stack10;
               } else if (this.computeValue(stack10) == this.computeValue(stack) && stack10.getIndexOffset2() < stack.getIndexOffset2()) {
                  stack5 = stack10;
               }
            }
         }

         for (ItemStack stack11 : (Iterable<ItemStack>)(Iterable<?>)this.arrayList2) {
            if (stack3 == null) {
               if (stack7 != null) {
                  if (this.computeValue(stack11) > this.computeValue(stack7)) {
                     stack7 = stack11;
                  } else if (this.computeValue(stack11) == this.computeValue(stack7) && stack11.getIndexOffset2() < stack7.getIndexOffset2()) {
                     stack7 = stack11;
                  }
               } else {
                  stack7 = stack11;
               }
            } else if (stack3.getItem() instanceof ItemGoldenBoots) {
               if (stack7 != null) {
                  if (this.computeValue(stack11) > this.computeValue(stack7)) {
                     stack7 = stack11;
                  } else if (this.computeValue(stack11) == this.computeValue(stack7) && stack11.getIndexOffset2() < stack7.getIndexOffset2()) {
                     stack7 = stack11;
                  }
               } else if (this.computeValue(stack11) > this.computeValue(stack3)) {
                  stack7 = stack11;
               } else if (this.computeValue(stack11) == this.computeValue(stack3) && stack11.getIndexOffset2() < stack3.getIndexOffset2()) {
                  stack7 = stack11;
               }
            }
         }

         for (ItemStack stack12 : (Iterable<ItemStack>)(Iterable<?>)this.arrayList5) {
            if (stack4 == null) {
               if (stack8 != null) {
                  if (this.computeValue(stack12) > this.computeValue(stack8)) {
                     stack8 = stack12;
                  } else if (this.computeValue(stack12) == this.computeValue(stack8) && stack12.getIndexOffset2() < stack8.getIndexOffset2()) {
                     stack8 = stack12;
                  }
               } else {
                  stack8 = stack12;
               }
            } else if (stack4.getItem() instanceof ItemGoldenBoots) {
               if (stack8 != null) {
                  if (this.computeValue(stack12) > this.computeValue(stack8)) {
                     stack8 = stack12;
                  } else if (this.computeValue(stack12) == this.computeValue(stack8) && stack12.getIndexOffset2() < stack8.getIndexOffset2()) {
                     stack8 = stack12;
                  }
               } else if (this.computeValue(stack12) > this.computeValue(stack4)) {
                  stack8 = stack12;
               } else if (this.computeValue(stack12) == this.computeValue(stack4) && stack12.getIndexOffset2() < stack4.getIndexOffset2()) {
                  stack8 = stack12;
               }
            }
         }

         for (ItemStack stack13 : (Iterable<ItemStack>)(Iterable<?>)this.arrayList4) {
            if (stack2 == null) {
               if (stack6 != null) {
                  if (this.computeValue(stack13) > this.computeValue(stack6)) {
                     stack6 = stack13;
                  } else if (this.computeValue(stack13) == this.computeValue(stack6) && stack13.getIndexOffset2() < stack6.getIndexOffset2()) {
                     stack6 = stack13;
                  }
               } else {
                  stack6 = stack13;
               }
            } else if (stack2.getItem() instanceof ItemGoldenBoots) {
               if (stack6 != null) {
                  if (this.computeValue(stack13) > this.computeValue(stack6)) {
                     stack6 = stack13;
                  } else if (this.computeValue(stack13) == this.computeValue(stack6) && stack13.getIndexOffset2() < stack6.getIndexOffset2()) {
                     stack6 = stack13;
                  }
               } else if (this.computeValue(stack13) > this.computeValue(stack2)) {
                  stack6 = stack13;
               } else if (this.computeValue(stack13) == this.computeValue(stack2) && stack13.getIndexOffset2() < stack2.getIndexOffset2()) {
                  stack6 = stack13;
               }
            }
         }

         this.enabled4 = stack6 == null && stack8 == null && stack7 == null && stack5 == null;
         this.timer3.hasReached(value);
         if (flag && this.enabled4) {
            this.count6 = -1;
         }
      } else {
         this.timer3.reset();
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

   private void updateState2() {
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

   private void updateState3() {
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

   private double computeValue(ItemStack stack) {
      double offset = 0.0;
      if (stack.getItem() instanceof ItemGoldenBoots) {
         offset = offset
            + (double)(
               (
                     (float)((ItemGoldenBoots)stack.getItem()).indexOffset4
                        + (float)(
                              6
                                 + LvlManager.getIndex(EnchantmentManager.enchantmentManager6.indexOffset, stack)
                                    * LvlManager.getIndex(EnchantmentManager.enchantmentManager6.indexOffset, stack)
                           )
                           / 3.0F
                  )
                  * 2.0F
            )
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager18.indexOffset, stack) / 50.0
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager16.indexOffset, stack) / 50.0
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager14.indexOffset, stack) / 50.0
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager1.indexOffset, stack) / 69.0
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager12.indexOffset, stack) / 50.0
            + (double)LvlManager.getIndex(EnchantmentManager.enchantmentManager20.indexOffset, stack) / 50.0;
         if (((ItemGoldenBoots)stack.getItem()).indexOffset3 == 0 && ((ItemGoldenBoots)stack.getItem()).getCrafterLeatherType() == CrafterLeatherType.GOLD) {
            offset -= 0.01;
         }
      }

      return offset;
   }

   private void applyArrayListByArrayList(int count, ArrayList list) {
      this.updateState3();
      if (this.settingBasic.getSelected().equals("SpoofInv") && this.settingEventRender.getSelected().equals("EventTick")) {
         list.add((Runnable)(() -> this.applyIndex2(count)));
      } else {
         this.applyIndexByIndex(count);
      }
   }

   private void applyIndexByIndex(int count) {
      if (MinecraftAccessor.minecraft.currentScreen instanceof GuiInventory) {
         try {
            Slot slot = MinecraftAccessor.minecraft.thePlayer.container.getSlot(count);
            GuiInventory currentScreen = (GuiInventory)MinecraftAccessor.minecraft.currentScreen;
            if (this.settingSmartDelay.isEnabled()) {
               double offset = Math.abs((double)(slot.indexOffset + 2 + currentScreen.indexOffset1) - InventoryUtil.getCoordOffsetArray()[0]);
               double offset2 = Math.abs((double)(slot.indexOffset2 + 2 + currentScreen.indexOffset2) - InventoryUtil.getCoordOffsetArray()[1]);
               Math.sqrt(offset * offset + offset2 * offset2);
               double offset3 = this.actionSetting.getDouble3() + this.actionSetting.getDouble6();
               this.actionSetting.getDouble3();
            }

            double[] offsetArray = new double[]{(double)(slot.indexOffset + 2 + currentScreen.indexOffset1), (double)(slot.indexOffset2 + 2 + currentScreen.indexOffset2)};
            String selected = this.settingBasic2.getSelected();
            switch (selected) {
               case "Basic":
                  currentScreen.state2 = true;
                  currentScreen.processAction(slot.indexOffset + 2 + currentScreen.indexOffset1, slot.indexOffset2 + 2 + currentScreen.indexOffset2, 0);
                  break;
               case "Other":
                  Client.getClient().getClass();
                  break;
               case "Exploit":
                  Client.getClient().getClass();
            }
         } catch (IOException ioException) {
            ioException.printStackTrace();
         }
      } else {
         String selected2 = this.settingBasic2.getSelected();
         switch (selected2) {
            case "Basic":
               MinecraftAccessor.minecraft
                  .playerController
                  .windowClick(MinecraftAccessor.minecraft.thePlayer.container.indexOffset2, count, 0, 1, MinecraftAccessor.minecraft.thePlayer);
               break;
            case "Other":
               Client.getClient().getClass();
               break;
            case "Exploit":
               Client.getClient().getClass();
         }
      }

      this.updateState4();
   }

   private void applyArrayList(int count, ArrayList list) {
      this.updateState3();
      if (this.settingBasic.getSelected().equals("SpoofInv") && this.settingEventRender.getSelected().equals("EventTick")) {
         list.add((Runnable)(() -> this.applyIndex3(count)));
      } else {
         this.applyIndex(count);
      }
   }

   private void applyIndex(int count) {
      if (MinecraftAccessor.minecraft.currentScreen instanceof GuiInventory) {
         GuiInventory currentScreen = (GuiInventory)MinecraftAccessor.minecraft.currentScreen;
         Slot slot = currentScreen.container.getSlot(count);
         if (this.settingSmartDelay.isEnabled()) {
            double offset = Math.abs((double)(slot.indexOffset + 2 + currentScreen.indexOffset1) - InventoryUtil.getCoordOffsetArray()[0]);
            double offset2 = Math.abs((double)(slot.indexOffset2 + 2 + currentScreen.indexOffset2) - InventoryUtil.getCoordOffsetArray()[1]);
            Math.sqrt(offset * offset + offset2 * offset2);
            double offset3 = this.actionSetting.getDouble3() + this.actionSetting.getDouble6();
            this.actionSetting.getDouble3();
         }

         double[] offsetArray = new double[]{(double)(slot.indexOffset + 2 + currentScreen.indexOffset1), (double)(slot.indexOffset2 + 2 + currentScreen.indexOffset2)};
      }

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

      this.updateState4();
   }

   private void applyIndex4(int count) {
      this.count7 = count - 36;
      this.timer.reset();
   }

   public boolean isEnabled4() {
      return this.enabled4;
   }

   public void setEnabled4(boolean enabled) {
      this.enabled4 = enabled;
   }

   public void updateState4() {
   }

   public int getCount6() {
      return this.count6;
   }

   private void applyIndex3(int count) {
      this.applyIndex(count);
   }

   private void applyIndex2(int count) {
      this.applyIndexByIndex(count);
   }

   private boolean isActiveAsFlag() {
      return this.settingHotbar.isVisible() ? this.settingHotbar.isEnabled() : this.settingBasic.getSelected().equals("OpenInv");
   }

   private boolean isActive4() {
      return this.settingHotbar.isVisible() ? this.settingHotbar.isEnabled() : this.settingBasic.getSelected().equals("OpenInv");
   }

   private boolean isActive5() {
      return this.settingHotbar.isVisible() ? this.settingHotbar.isEnabled() : this.settingBasic.getSelected().equals("OpenInv");
   }

   private boolean isActive2() {
      return this.settingBasic.getSelected().equals("OpenInv");
   }

   private boolean isActive6() {
      return this.settingBasic.getSelected().equals("SpoofInv");
   }

   private boolean isActive3() {
      return this.settingBasic.getSelected().equals("SpoofInv");
   }

   private boolean isActive() {
      return this.settingBasic.getSelected().equals("SpoofInv");
   }
}
