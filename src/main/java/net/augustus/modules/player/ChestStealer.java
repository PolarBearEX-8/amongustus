package net.augustus.modules.player;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.utils.ActionRateAccumulator;
import net.augustus.ClientManagers;
import net.augustus.settings.StringListSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.utils.player.ContainerUtil;
import net.augustus.utils.TuplePair;
import net.augustus.render.RenderUtil;
import net.augustus.settings.options.InvManagerActionOption;
import net.augustus.utils.TickCounter;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.Augustus;
import net.augustus.settings.ItemFilterSetting;
import net.augustus.utils.player.InventoryUtil;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.player.ContainerSlotQuery;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ChestStealer extends Module implements TickListener, RenderListener {
   private final StringValue mode;
   private final RandomizedRangeSettings startDelay;
   private final BooleanValue instant;
   private final DoubleValue maxMouseMoveSpeed;
   private final RandomizedRangeSettings mouseMoveRandom;
   private final RandomizedRangeSettings cps;
   private final RandomizedRangeSettings cpsRecalculate;
   private final RandomizedRangeSettings doubleClickRate;
   private final RandomizedRangeSettings failRate;
   private final RandomizedRangeSettings missClickRate;
   private final RandomizedRangeSettings cpsNew;
   private final RandomizedRangeSettings cpsRecalculateNew;
   private final RandomizedRangeSettings doubleClickRateNew;
   private final RandomizedRangeSettings sideStopTicks;
   private final RandomizedRangeSettings emptySlotFailRate;
   private final BooleanValue removeEmptyRows;
   private final BooleanValue clickWindow;
   private final BooleanValue stealZeroStackSizeItems;
   private final BooleanValue intelligent;
   private final BooleanValue ignoreBadPotions;
   private final SettingGroup itemBlackList;
   private final BooleanValue autoSort;
   private final BooleanValue autoClose;
   private final BooleanValue autoCloseIfInvFull;
   private final RandomizedRangeSettings closeDelay;
   private final RandomizedRangeSettings chestOpenTime;
   public final BooleanValue visualize;
   public final StringValue whereToSteal;
   private final BooleanValue ignoreEnderChests;
   private final StringValue sequence;
   private final StringValue clickMode;
   private final BooleanValue toggleStorageESP;
   private final BooleanValue toggleChestAura;
   public final StringListSetting chestStorage;
   public final BooleanValue silent;
   private final BooleanValue stealIndicator;
   private final ColorSetting color2;
   private final BooleanValue backGround;
   private final ColorSetting backgroundColor;
   private final BlurShadowSettings blurShadowSettings;
   private boolean wasChestOpen;
   private int nextTargetSlot;
   private int ticksSinceLastClick;
   private final TimeHelper startDelayTimer;
   private final TimeHelper closeDelayTimer;
   private final TimeHelper chestOpenTimer;
   private final ActionRateAccumulator doubleClickAccumulator;
   private final ActionRateAccumulator failClickAccumulator;
   private final ActionRateAccumulator missClickAccumulator;
   private final ActionRateAccumulator cpsAccumulator;
   private final TickCounter cpsRecalculateCounter;
   private final TickCounter cpsNewRecalculateCounter;
   private final TickCounter secondaryTickCounter;
   private final TickCounter sideStopCounter;
   private final ActionRateAccumulator cpsNewAccumulator;
   private final ActionRateAccumulator doubleClickNewAccumulator;
   private final ActionRateAccumulator emptySlotFailAccumulator;
   private final ArrayList failedEmptySlots;
   private final ArrayList visitedSlots;
   private ArrayList bestArmorStacks;
   private List cachedRandomSlotOrder;
   private final ItemFilterSetting items;
   private int lastRowIndex;
   private int initialItemCount;
   private int stolenItemCount;
   private boolean wasChestOpenLastTick;
   private boolean startedStealing;

   @Override
   public void onEnable() {
      this.toggleLinkedModules(true);
      this.resetRandomizers();
   }

   @Override
   public void onDisable() {
      this.toggleLinkedModules(false);
      this.nextTargetSlot = -1;
      this.bestArmorStacks.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40, 10);
      this.toggleEventSubscription(32, -2);
   }

   public ChestStealer() {
       super("ChestStealer", "TruhenAusbeutung", new Color(196, 184, 249, 255), Categorys.PLAYER);
      this.mode = new StringValue(this, "Mode", "Default", new String[]{"Default", "Experimental"});
      this.startDelay = (new RandomizedRangeSettings(this, "StartDelay", 125.0, 175.0, 0.0, 1000.0, 0)).withoutNotInARow();
      this.instant = new BooleanValue(this, "Instant", false);
      this.maxMouseMoveSpeed = new DoubleValue(this, "MaxMouseMoveSpeed", 19.0, 0.0, 200.0, 0).visibleWhen(() -> this.isMaxMouseMoveSpeedVisible());
      RandomizedRangeSettings randomizedRangeSettings = new RandomizedRangeSettings(this, "MouseMoveRandom", 0.0, 0.0, -1.0, 2.0, 0);
      SettingVisibilityCondition condition2 = () -> {
         return !this.instant.getValue() && this.isDefaultMode();
      };
      this.mouseMoveRandom = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(condition2);
      RandomizedRangeSettings randomizedRangeSettings2 = new RandomizedRangeSettings(this, "CPS", 17.0, 20.0, 0.0, 40.0, 0);
      SettingVisibilityCondition condition3 = () -> {
         return !this.instant.getValue() && this.isDefaultMode();
      };
      this.cps = (RandomizedRangeSettings)randomizedRangeSettings2.withVisibility(condition3);
      this.cpsRecalculate = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "CPSRecalculate", 6.0, 8.0, 0.0, 40.0, 0)).withoutNotInARow().withVisibility(() -> this.isCpsRecalculateVisible());
      this.doubleClickRate = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "DoubleClickRate", 5.0, 10.0, 0.0, 100.0, 0)).withoutNotInARow().withVisibility(() -> this.isDoubleClickRateVisible());
      this.failRate = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "FailRate", 5.0, 10.0, 0.0, 100.0, 0)).withoutNotInARow().withVisibility(() -> this.isFailRateVisible());
      this.missClickRate = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "MissClickRate", 0.0, 0.0, 0.0, 100.0, 0)).withoutNotInARow().withVisibility(() -> this.isMissClickRateVisible());
      this.cpsNew = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "CPSNew", 15.0, 20.0, 0.0, 40.0, 0)).withoutNotInARow().withDisplayNamePrefix("CPS")).withVisibility(() -> this.isCpsNewVisible());
      this.cpsRecalculateNew = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(this, "CPSRecalculateNew", 6.0, 8.0, 0.0, 60.0, 0).withDisplayNamePrefix("CPSRecalculate")).withoutNotInARow().withVisibility(() -> this.isCpsRecalculateNewVisible());
      this.doubleClickRateNew = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "DoubleClickRateNew", 6.0, 12.0, 0.0, 100.0, 0)).withoutNotInARow().withDisplayNamePrefix("DoubleClickRate")).withVisibility(() -> this.isDoubleClickRateNewVisible());
      this.sideStopTicks = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "SideStopTicks", 3.0, 6.0, 0.0, 20.0, 0)).withoutNotInARow().withVisibility(() -> this.isSideStopTicksVisible());
      RandomizedRangeSettings randomizedRangeSettings14 = (new RandomizedRangeSettings(this, "EmptySlotFailRate", 5.0, 10.0, 0.0, 100.0, 0)).withoutNotInARow();
      SettingVisibilityCondition condition12 = () -> {
         return !this.isDefaultMode();
      };
      this.emptySlotFailRate = (RandomizedRangeSettings)randomizedRangeSettings14.withVisibility(condition12);
      this.removeEmptyRows = (BooleanValue)new BooleanValue(this, "RemoveEmptyRows", true).withVisibility(() -> this.isRemoveEmptyRowsVisible());
      this.clickWindow = new BooleanValue(this, "ClickWindow", true);
      this.stealZeroStackSizeItems = new BooleanValue(this, "StealZeroStackSizeItems", false);
      this.intelligent = new BooleanValue(this, "Intelligent", true);
      BooleanValue booleanValue2 = new BooleanValue(this, "IgnoreBadPotions", false);
      BooleanValue intelligent2 = this.intelligent;
      BooleanValue booleanValue3 = intelligent2;
      this.ignoreBadPotions = (BooleanValue)booleanValue2.withVisibility(() -> booleanValue3.getValue());
      SettingGroup itemBlackListGroup = new SettingGroup(this, "ItemBlackList");
      intelligent2 = this.intelligent;
      BooleanValue booleanValue4 = intelligent2;
      this.itemBlackList = itemBlackListGroup.visibleWhen(() -> booleanValue4.getValue());
      this.autoSort = (BooleanValue)new BooleanValue(this, "AutoSort", true).withVisibility(() -> this.isAutoSortVisible());
      this.autoClose = new BooleanValue(this, "AutoClose", true);
      BooleanValue booleanValue6 = new BooleanValue(this, "AutoCloseIfInvFull", false);
      intelligent2 = this.autoClose;
      BooleanValue booleanValue7 = intelligent2;
      this.autoCloseIfInvFull = (BooleanValue)booleanValue6.withVisibility(() -> booleanValue7.getValue());
      RandomizedRangeSettings randomizedRangeSettings15 = (new RandomizedRangeSettings(this, "CloseDelay", 50.0, 50.0, 0.0, 1000.0, 0)).withoutNotInARow();
      intelligent2 = this.autoClose;
      BooleanValue booleanValue8 = intelligent2;
      this.closeDelay = (RandomizedRangeSettings)randomizedRangeSettings15.withVisibility(() -> booleanValue8.getValue());
      RandomizedRangeSettings randomizedRangeSettings16 = (new RandomizedRangeSettings(this, "ChestOpenTime", 160.0, 260.0, 0.0, 1000.0, 0)).withoutNotInARow();
      intelligent2 = this.autoClose;
      BooleanValue booleanValue9 = intelligent2;
      this.chestOpenTime = (RandomizedRangeSettings)randomizedRangeSettings16.withVisibility(() -> booleanValue9.getValue());
      this.visualize = (BooleanValue)new BooleanValue(this, "Visualize", true).withVisibility(() -> this.isVisualizeVisible());
      this.whereToSteal = new StringValue(this, "WhereToSteal", "ChestsOnly", new String[]{"Always", "ChestsOnly", "CustomChest"});
      this.ignoreEnderChests = (BooleanValue)new BooleanValue(this, "IgnoreEnderChests", false).withVisibility(() -> this.isIgnoreEnderChestsVisible());
      this.sequence = (StringValue)new StringValue(this, "Sequence", "Nearest", new String[]{"Basic", "Nearest", "Random"}).withVisibility(() -> this.isDefaultMode());
      this.clickMode = (StringValue)new StringValue(this, "ClickMode", "Basic", new String[]{"Basic"}).withVisibility(() -> this.isDefaultMode());
      this.toggleStorageESP = new BooleanValue(this, "ToggleStorageESP", false);
      this.toggleChestAura = new BooleanValue(this, "ToggleChestAura", false);
      this.chestStorage = new StringListSetting(this, "ChestStorage", new ArrayList());
      this.silent = new BooleanValue(this, "Silent", false);
      BooleanValue booleanValue12 = new BooleanValue(this, "StealIndicator", true);
      intelligent2 = this.silent;
      BooleanValue booleanValue13 = intelligent2;
      this.stealIndicator = (BooleanValue)booleanValue12.withVisibility(() -> booleanValue13.getValue());
      this.color2 = (ColorSetting)new ColorSetting(this, "Color", Color.WHITE).withVisibility(() -> this.isIndicatorColorVisible());
      this.backGround = (BooleanValue)new BooleanValue(this, "BackGround", true).withVisibility(() -> this.isBackgroundVisible());
      ColorSetting colorSetting2 = new ColorSetting(this, "BackGroundColor", new Color(0, 0, 0, 100));
      SettingVisibilityCondition condition27 = () -> {
         return this.silent.getValue() && this.stealIndicator.getValue() && this.backGround.getValue();
      };
      this.backgroundColor = (ColorSetting)colorSetting2.withVisibility(condition27);
      BlurShadowSettings blurShadowSettings3 = new BlurShadowSettings(this).withCustomShadowColor(Color.black);
      SettingVisibilityCondition condition28 = () -> {
         return this.silent.getValue() && this.stealIndicator.getValue();
      };
      this.blurShadowSettings = (BlurShadowSettings)blurShadowSettings3.withVisibility(condition28);
      this.wasChestOpen = false;
      this.nextTargetSlot = -1;
      this.ticksSinceLastClick = 0;
      this.startDelayTimer = new TimeHelper();
      this.closeDelayTimer = new TimeHelper();
      this.chestOpenTimer = new TimeHelper();
      this.doubleClickAccumulator = new ActionRateAccumulator(100, 0.0);
      this.failClickAccumulator = new ActionRateAccumulator(100, 0.0);
      this.missClickAccumulator = new ActionRateAccumulator(100, 0.0);
      this.cpsAccumulator = new ActionRateAccumulator(20, 0.0);
      this.cpsRecalculateCounter = new TickCounter();
      this.cpsNewRecalculateCounter = new TickCounter();
      this.secondaryTickCounter = new TickCounter();
      this.sideStopCounter = new TickCounter();
      this.cpsNewAccumulator = new ActionRateAccumulator(20, 0.0);
      this.doubleClickNewAccumulator = new ActionRateAccumulator(20, 0.0);
      this.emptySlotFailAccumulator = new ActionRateAccumulator(100, 0.0);
      this.failedEmptySlots = new ArrayList();
      this.visitedSlots = new ArrayList();
      this.bestArmorStacks = new ArrayList();
      this.cachedRandomSlotOrder = new ArrayList();
      SettingGroup itemBlackList2 = this.itemBlackList;
      BooleanValue intelligent3 = this.intelligent;
      this.items = new ItemFilterSetting(this, "Items", itemBlackList2, () -> intelligent3.getValue(), obj -> {
         return !this.isGearItem((Item)obj) && !(obj instanceof ItemPotion) && InventoryUtil.isCleanableItem((Item)obj);
      });
      this.lastRowIndex = 0;
      this.initialItemCount = 0;
      this.stolenItemCount = 0;
      this.wasChestOpenLastTick = false;
      this.startedStealing = false;
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      if (this.silent.getValue() && this.stealIndicator.getValue()) {
         if (mc.currentScreen instanceof GuiChest) {
            String str = "Stealing (" + this.stolenItemCount + "/" + this.initialItemCount + ")";
            BlurShadowManager.enableOneShotPass(this.blurShadowSettings.isBlurActive(), this.blurShadowSettings.isShadowActive());
            BlurShadowManager.submitRenderTask(false, shaderRenderPass -> {
               int stringWidth = mc.fontRendererObj.getStringWidth(str);
               int n = sr.getScaledWidth() / 2 - stringWidth / 2;
               int n2 = sr.getScaledHeight() / 2 - 4;
               if (this.backGround.getValue()) {
                  int colorInt = this.backgroundColor.getColorInt((byte)0);
                  RenderUtil.drawRect(n - 2, n2 - 2, n + stringWidth + 1, n2 + 9, this.blurShadowSettings.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, colorInt)));
               }

               if (shaderRenderPass == ShaderRenderPass.NONE || !this.backGround.getValue() && shaderRenderPass == ShaderRenderPass.SHADOW) {
                  mc.fontRendererObj.drawString(str, n, n2, this.color2.getColorInt((byte)0));
               }
            });
         }

         RenderUtil.resetGlColorState();
      }
   }

   @Override
   public void onEarlyTick() {
      this.processChestSteal();
      this.cpsRecalculateCounter.increment();
      this.cpsNewRecalculateCounter.increment();
      this.secondaryTickCounter.increment();
      this.sideStopCounter.increment();
      if (mc.currentScreen instanceof GuiChest) {
         GuiChest currentScreen = (GuiChest)mc.currentScreen;
         ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(currentScreen.inventorySlots);
         containerSlotQuery = containerSlotQuery.toSlot(currentScreen.inventorySlots.inventorySlots.size() - 36);
         long count = containerSlotQuery.nonEmptyOrNonNull(this.stealZeroStackSizeItems.getValue()).count();
         if (!this.wasChestOpenLastTick) {
            this.initialItemCount = (int)count;
         }

         this.stolenItemCount = Math.max(this.initialItemCount - (int)count, 0);
      }

      this.wasChestOpenLastTick = mc.currentScreen instanceof GuiChest;
   }

   private void processChestSteal() {
      this.handleChestOpenTransition();
      if (!(mc.currentScreen instanceof GuiChest)) {
         this.chestOpenTimer.reset();
         this.startDelayTimer.reset();
         this.closeDelayTimer.reset();
         this.nextTargetSlot = -1;
         this.resetClickTickCounter();
         this.startedStealing = false;
         this.bestArmorStacks.clear();
      } else {
         GuiChest currentScreen = (GuiChest)mc.currentScreen;
         if (this.shouldStealFromChest(currentScreen)) {
            if (this.startDelayTimer.reached((long)this.startDelay.getLastIntValue()) && !mc.currentScreen.isAllowNativeMouseClick()) {
               if (mc.thePlayer.inventory.getItemStack() != null) {
                  return;
               }

               if (this.intelligent.getValue()) {
                  ArrayList list = new ArrayList();

                  for (Slot slot : (Iterable<Slot>)(currentScreen.inventorySlots.inventorySlots)) {
                     ItemStack stack = slot.getStack();
                     if (stack != null) {
                        if (stack.getItem() instanceof ItemArmor) {
                           list.add(stack);
                        }
                     }
                  }

                  for (int i = 0; i < 9; i++) {
                     ItemStack stack2 = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                     if (stack2 != null) {
                        if (stack2.getItem() instanceof ItemArmor) {
                           list.add(stack2);
                        }
                     }
                  }

                  this.bestArmorStacks = InventoryUtil.findBestArmorSetFromList(list, ClientManagers.moduleManager.invManager.isToggled() && InvManagerActionOption.PUT_ON_ARMOR.isSelected() && ClientManagers.moduleManager.invManager.ignoreDurability.getValue());
               }

               if (this.isDefaultMode()) {
                  this.stealDefault(currentScreen);
               } else {
                  this.stealExperimental(currentScreen);
               }

               return;
            }
         }
      }
   }

   private void stealExperimental(GuiChest guiChest) {
      Container inventorySlots = guiChest.inventorySlots;
      List list = InventoryUtil.sortSlotsSerpentine(this.collectExperimentalSlots(guiChest));
      Collections.reverse(list);
      ArrayList<Runnable> list2 = new ArrayList<>();
      ArrayList list3 = new ArrayList();

      for (int n : (Iterable<Integer>)(list)) {
         list2.add(() -> this.executeClickSlotExperimental(n, guiChest));
         list3.add(n);
      }

      if (!this.checkAutoClose(list2)) {
         this.startedStealing = true;
         int n2 = this.cpsNewAccumulator.accumulate(this.cpsNew.getLastIntValue());
         if (this.instant.getValue()) {
            list2.forEach(Runnable::run);
            this.updateCpsRecalculation();
         } else {
            while (n2 > 0 && !list2.isEmpty()) {
               int n3 = (Integer)list3.get(0);
               int n4 = (int)((double)n3 / 9.0);
               if (n4 != this.lastRowIndex) {
                  if (!this.sideStopCounter.hasReached(this.sideStopTicks.getLastIntValue())) {
                     if (this.clickWindow.getValue()) {
                        RandomizedRangeSettings doubleClickRateNew2 = this.doubleClickRateNew;
                        if (this.rollAccumulator(this.doubleClickNewAccumulator, doubleClickRateNew2)) {
                           mc.playerController.windowClick(guiChest.inventorySlots.windowId, -999, 0, 4, mc.thePlayer);
                        }
                     }

                     if (this.clickWindow.getValue()) {
                        mc.playerController.windowClick(guiChest.inventorySlots.windowId, -999, 0, 4, mc.thePlayer);
                     }

                     n2--;
                     continue;
                  }

                  this.sideStopTicks.nextValue();
               }

               this.lastRowIndex = n4;
               this.sideStopCounter.reset();
               if (this.failedEmptySlots.contains(n3)) {
                  list2.remove(0);
                  list3.remove(0);
                  this.visitedSlots.add(n3);
                  break;
               }

               ((Runnable)list2.get(0)).run();
               RandomizedRangeSettings doubleClickRateNew3 = this.doubleClickRateNew;
               if (this.rollAccumulator(this.doubleClickNewAccumulator, doubleClickRateNew3)) {
                  ((Runnable)list2.get(0)).run();
               }

               if (inventorySlots.getSlot(n3).getStack() == null) {
                  this.visitedSlots.add(n3);
               }

               n2--;
               list2.remove(0);
               list3.remove(0);
            }

            this.updateCpsRecalculation();
         }
      }
   }

   private void stealDefault(GuiChest guiChest) {
      this.ticksSinceLastClick++;
      Container inventorySlots = guiChest.inventorySlots;
      List list;
      if (this.sequence.getSelectedValue().equals("Random") && !this.cachedRandomSlotOrder.isEmpty()) {
         list = this.cachedRandomSlotOrder;
      } else {
         list = InventoryUtil.sortSlotsByMode(inventorySlots, this.collectDefaultSlots(guiChest), this.sequence.getSelectedValue(), this.getContainerGuiHeight(inventorySlots));
      }

      ArrayList<Runnable> list4 = new ArrayList<>();
      ArrayList list5 = new ArrayList();

      for (int n : (Iterable<Integer>)(list)) {
         if (this.intelligent.getValue() && this.autoSort.getValue()) {
            String itemCategoryName = InventoryUtil.getItemCategoryName(inventorySlots.getSlot(n).getStack());
            ArrayList hotbarSlotsForCategory = ClientManagers.moduleManager.invManager.getHotbarSlotsForCategory(itemCategoryName);
            if (!hotbarSlotsForCategory.isEmpty()) {
               int n2 = (Integer)hotbarSlotsForCategory.get(0);
               if (!itemCategoryName.equals("Sword") && !itemCategoryName.equals("Bow") && !itemCategoryName.equals("Rod")) {
                  if (InventoryUtil.getItemCategoryName(mc.thePlayer.inventoryContainer.getSlot(n2 + 36).getStack()).equals(itemCategoryName)) {
                     list4.add(() -> {
                        this.clickSlotDefault(n, guiChest);
                     });
                     list5.add(n);
                     continue;
                  }
               }

               list4.add(() -> this.executeHotbarSwapSlot(n2, n, guiChest));
               list5.add(n);
               continue;
            }
         }

         list4.add(() -> {
            this.clickSlotDefault(n, guiChest);
         });
         list5.add(n);
      }

      if (!this.checkAutoClose(list4)) {
         this.startedStealing = true;
         this.executeStealActions(list4, list5, this.cpsAccumulator, this.doubleClickRate, this.failRate, this.missClickRate, inventorySlots, guiChest, this.instant.getValue(), this.cps.getLastIntValue(), this.ticksSinceLastClick);
         this.updateCpsRecalculation();
      }
   }

   private boolean checkAutoClose(ArrayList list) {
      boolean isEmpty = list.isEmpty();
      if (this.autoClose.getValue() && this.autoCloseIfInvFull.getValue()) {
         boolean flag = true;

         for (int i = 9; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
            if (mc.thePlayer.inventoryContainer.getSlot(i).getStack() == null) {
               flag = false;
               break;
            }
         }

         if (!isEmpty) {
            isEmpty = flag;
         }
      }

      if (isEmpty && this.autoClose.getValue() && this.startedStealing) {
         TimeHelper closeDelayTimer2 = this.closeDelayTimer;
         if (closeDelayTimer2.reached((long)this.closeDelay.getLastIntValue())) {
            closeDelayTimer2 = this.chestOpenTimer;
            if (closeDelayTimer2.reached((long)this.chestOpenTime.getLastIntValue())) {
               mc.thePlayer.closeScreen();
            }
         }

         return true;
      } else {
         this.closeDelayTimer.reset();
         return false;
      }
   }

   private void executeStealActions(
      ArrayList list,
      ArrayList list2,
      ActionRateAccumulator actionRateAccumulator,
      RandomizedRangeSettings randomizedRangeSettings,
      RandomizedRangeSettings randomizedRangeSettings2,
      RandomizedRangeSettings randomizedRangeSettings3,
      Container container,
      GuiChest guiChest,
      boolean flag,
      int n,
      int n2
   ) {
      if (flag) {
         list.forEach(obj -> ((Runnable)obj).run());
      } else {
         if (!list2.isEmpty()) {
            this.nextTargetSlot = (Integer)list2.get(list2.size() > 1 ? 1 : 0);
         }

         if (!this.rollAccumulator(this.failClickAccumulator, randomizedRangeSettings2)) {
            int n3 = actionRateAccumulator.accumulate(n);
            boolean flag2 = false;

            while (n3 > 0 && !list.isEmpty()) {
               if (this.tryMissClick(randomizedRangeSettings3, container, guiChest)) {
                  n3--;
               } else {
                  double d = (double)n2;
                  int n4 = (Integer)list2.get(0);
                  n3 = this.filterClicksByMouseSpeed(d, container, n4, n3);
                  if (n3 <= 0) {
                     break;
                  }

                  Runnable runnable = (Runnable)list.get(0);
                  runnable.run();
                  flag2 = true;
                  if (this.rollAccumulator(this.doubleClickAccumulator, randomizedRangeSettings)) {
                     runnable.run();
                  }

                  list.remove(0);
                  list2.remove(0);
                  n3--;
               }
            }

            if (flag2 && this.sequence.getSelectedValue().equals("Random")) {
               this.cachedRandomSlotOrder = InventoryUtil.sortSlotsByMode(container, this.collectDefaultSlots(guiChest), this.sequence.getSelectedValue(), this.getContainerGuiHeight(container));
            }
         }
      }
   }

   private boolean tryMissClick(RandomizedRangeSettings randomizedRangeSettings, Container container, GuiChest guiChest) {
      if (InventoryUtil.getLastClickedSlot() > 0) {
         Slot slot = container.getSlot(InventoryUtil.getLastClickedSlot());
         int xDisplayPosition = slot.xDisplayPosition;
         int yDisplayPosition = slot.yDisplayPosition;
         double maxvalue = Double.MAX_VALUE;
         int n = -1;

         for (int i = 5; i < container.inventorySlots.size(); i++) {
            if (container.getSlot(i).getStack() == null && i != InventoryUtil.getLastClickedSlot() && i != n) {
               Slot slot2 = container.getSlot(i);
               int n2 = xDisplayPosition - slot2.xDisplayPosition;
               int n3 = yDisplayPosition - slot2.yDisplayPosition;
               double d = Math.sqrt((double)(n2 * n2 + n3 * n3));
               if (d < maxvalue) {
                  maxvalue = d;
                  n = i;
               }
            }
         }

         if (maxvalue < 36.0) {
            if (this.rollAccumulator(this.missClickAccumulator, randomizedRangeSettings)) {
               this.clickSlotDefault(n, guiChest);
               return true;
            }
         }
      }

      return false;
   }

   private int filterClicksByMouseSpeed(double d, Container container, int n, int n2) {
      Slot slot = container.getSlot(n);
      ScaledResolution sr = new ScaledResolution(mc);
      int n3 = (sr.getScaledWidth() - 176) / 2;
      int n4 = (sr.getScaledHeight() - this.getContainerGuiHeight(container)) / 2;
      double d2 = Math.abs((double)(slot.xDisplayPosition + 2 + n3) - InventoryUtil.getLastCursorPos().x);
      double d3 = Math.abs((double)(slot.yDisplayPosition + 2 + n4) - InventoryUtil.getLastCursorPos().y);
      double d4 = Math.sqrt(d2 * d2 + d3 * d3);
      int n5 = (int)(d4 / this.maxMouseMoveSpeed.getValue(true));
      if (n5 > 0) {
         n5 += this.mouseMoveRandom.getLastIntValue();
      }

      if (d > (double)n5) {
         if (d4 > 19.0) {
            this.mouseMoveRandom.nextValue();
         }

         return n2;
      } else {
         return 0;
      }
   }

   private boolean rollAccumulator(ActionRateAccumulator actionRateAccumulator, RandomizedRangeSettings randomizedRangeSettings) {
      if (randomizedRangeSettings == null) {
         return false;
      } else {
         boolean shouldTrigger = actionRateAccumulator.shouldTrigger(randomizedRangeSettings.getLastIntValue());
         if (shouldTrigger) {
            randomizedRangeSettings.nextValue();
         }

         return shouldTrigger;
      }
   }

   private ArrayList collectExperimentalSlots(GuiChest guiChest) {
      Container inventorySlots = guiChest.inventorySlots;
      ArrayList list = InventoryUtil.createSlotIndexRange(0, inventorySlots.inventorySlots.size() - 36);
      list.removeAll(this.visitedSlots);
      if (this.removeEmptyRows.getValue()) {
         ArrayList list2 = new ArrayList();
         ArrayList list3 = new ArrayList();

         for (int n : (Iterable<Integer>)(list)) {
            if (n % 9 == 0) {
               if (list3.size() == 9) {
                  list2.addAll(list3);
               }

               list3.clear();
            }

            if (guiChest.inventorySlots.getSlot(n).getStack() == null) {
               list3.add(n);
            }
         }

         list.removeAll(list2);
      }

      if (!this.intelligent.getValue()) {
         return list;
      } else {
         ArrayList list4 = new ArrayList();
         ArrayList list5;
         if (this.ignoreBadPotions.getValue()) {
            list5 = InventoryUtil.getGoodPotionSlots(inventorySlots, (byte)0);
         } else {
            list5 = new ArrayList();
         }

         ArrayList list6 = list5;

         for (int n2 : (Iterable<Integer>)(list)) {
            ItemStack stack2 = inventorySlots.getSlot(n2).getStack();
            if (stack2 != null) {
               if (!this.isBestItemInChest(stack2, inventorySlots) || !this.isUpgradeOverInventory(stack2)) {
                  continue;
               }

               if (this.items.isItemAllowed(stack2.getItem()) && !list6.contains(n2)) {
                  continue;
               }
            }

            list4.add(n2);
         }

         return list4;
      }
   }

   private List collectDefaultSlots(GuiChest guiChest) {
      Container inventorySlots = guiChest.inventorySlots;
      List list = ContainerSlotQuery.of(inventorySlots).toSlot(inventorySlots.inventorySlots.size() - 36).nonEmptyOrNonNull(this.stealZeroStackSizeItems.getValue()).toSlotIndexList();
      if (!this.intelligent.getValue()) {
         return list;
      } else {
         ArrayList list2 = new ArrayList();
         ArrayList list3;
         if (this.ignoreBadPotions.getValue()) {
            list3 = InventoryUtil.getGoodPotionSlots(inventorySlots, (byte)0);
         } else {
            list3 = new ArrayList();
         }

         ArrayList list4 = list3;

         for (int n2 : (Iterable<Integer>)(list)) {
            ItemStack stack = inventorySlots.getSlot(n2).getStack();
            if (this.isBestItemInChest(stack, inventorySlots) && this.isUpgradeOverInventory(stack)) {
               if (!this.items.isItemAllowed(stack.getItem()) || list4.contains(n2)) {
                  list2.add(n2);
               }
            }
         }

         return list2;
      }
   }

   public boolean isBestItemInChest(ItemStack stack, Container container) {
      if (!this.isGearItemStack(stack)) {
         return true;
      } else {
         for (int i = 0; i < container.inventorySlots.size() - 36; i++) {
            ItemStack stack2 = container.getSlot(i).getStack();
            if (stack2 != null) {
               Item item = stack.getItem();
               Item item2 = stack2.getItem();
               if (item instanceof ItemSword && item2 instanceof ItemSword && InventoryUtil.getItemScore(stack) < InventoryUtil.getItemScore(stack2)) {
                  return false;
               }

               if (item instanceof ItemBow && item2 instanceof ItemBow && InventoryUtil.getItemScore(stack) < InventoryUtil.getItemScore(stack2)) {
                  return false;
               }

               if (item instanceof ItemArmor && item2 instanceof ItemArmor && !this.bestArmorStacks.contains(stack)) {
                  return false;
               }

               if (item instanceof ItemFishingRod && item2 instanceof ItemFishingRod && InventoryUtil.getItemScore(stack) < InventoryUtil.getItemScore(stack2)) {
                  return false;
               }

               if (item instanceof ItemAxe && item2 instanceof ItemAxe) {
                  if (InventoryUtil.getItemScore(stack, true) < InventoryUtil.getItemScore(stack2, true)) {
                     return false;
                  }
               }

               if (item instanceof ItemPickaxe && item2 instanceof ItemPickaxe) {
                  if (InventoryUtil.getItemScore(stack, true) < InventoryUtil.getItemScore(stack2, true)) {
                     return false;
                  }
               }

               if (item instanceof ItemSpade && item2 instanceof ItemSpade) {
                  if (InventoryUtil.getItemScore(stack, true) < InventoryUtil.getItemScore(stack2, true)) {
                     return false;
                  }
               }
            }
         }

         return true;
      }
   }

   public boolean isUpgradeOverInventory(ItemStack stack) {
      if (!this.isGearItemStack(stack)) {
         return true;
      } else {
         for (int i = 0; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
            ItemStack stack2 = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if (stack2 != null) {
               Item item = stack2.getItem();
               Item item2 = stack.getItem();
               if (item2 instanceof ItemSword && item instanceof ItemSword && InventoryUtil.getItemScore(stack) <= InventoryUtil.getItemScore(stack2)) {
                  return false;
               }

               if (item2 instanceof ItemBow && item instanceof ItemBow && InventoryUtil.getItemScore(stack) <= InventoryUtil.getItemScore(stack2)) {
                  return false;
               }

               if (item2 instanceof ItemArmor
                  && item instanceof ItemArmor
                  && (
                     !this.bestArmorStacks.contains(stack)
                        || ((ItemArmor)item2).armorType == ((ItemArmor)item).armorType && InventoryUtil.getItemScore(stack2) == InventoryUtil.getItemScore(stack)
                  )) {
                  return false;
               }

               if (item2 instanceof ItemFishingRod && item instanceof ItemFishingRod && InventoryUtil.getItemScore(stack) <= InventoryUtil.getItemScore(stack2)) {
                  return false;
               }

               if (item2 instanceof ItemAxe && item instanceof ItemAxe) {
                  if (InventoryUtil.getItemScore(stack, true) < InventoryUtil.getItemScore(stack2, true)) {
                     return false;
                  }
               }

               if (item2 instanceof ItemPickaxe && item instanceof ItemPickaxe) {
                  if (InventoryUtil.getItemScore(stack, true) < InventoryUtil.getItemScore(stack2, true)) {
                     return false;
                  }
               }

               if (item2 instanceof ItemSpade && item instanceof ItemSpade) {
                  if (InventoryUtil.getItemScore(stack, true) < InventoryUtil.getItemScore(stack2, true)) {
                     return false;
                  }
               }
            }
         }

         return true;
      }
   }

   private boolean isGearItemStack(ItemStack stack) {
      return this.isGearItem(stack.getItem());
   }

   private boolean isGearItem(Item item) {
      return item instanceof ItemSword
         || item instanceof ItemBow
         || item instanceof ItemArmor
         || item instanceof ItemAxe
         || item instanceof ItemPickaxe
         || item instanceof ItemSpade
         || item instanceof ItemFishingRod;
   }

   private void clickSlotExperimental(int n, GuiChest guiChest) {
      Slot slot = guiChest.inventorySlots.getSlot(n);
      this.updateVirtualCursor(guiChest.inventorySlots, n);

      try {
         guiChest.forceShiftClick = true;
         guiChest.mouseClicked(slot.xDisplayPosition + 2 + guiChest.guiLeft, slot.yDisplayPosition + 2 + guiChest.guiTop, 0);
         this.resetClickTickCounter();
      } catch (IOException e) {
      }
   }

   private void clickSlotDefault(int n, GuiChest guiChest) {
      Slot slot = guiChest.inventorySlots.getSlot(n);
      this.updateVirtualCursor(guiChest.inventorySlots, n);

      try {
         switch (this.clickMode.getSelectedValue()) {
            case "Basic":
               guiChest.forceShiftClick = true;
               guiChest.mouseClicked(slot.xDisplayPosition + 2 + guiChest.guiLeft, slot.yDisplayPosition + 2 + guiChest.guiTop, 0);
               break;
            case "Other":
               break;
            case "Exploit":
         }

         this.resetClickTickCounter();
      } catch (IOException e) {
      }
   }

   private void hotbarSwapSlot(int n, int n2, GuiChest guiChest) {
      ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(n + 36).getStack();
      if (stack != null) {
         stack.setSlotIndex(n2);
      }

      this.updateVirtualCursor(guiChest.inventorySlots, n2);
      switch (this.clickMode.getSelectedValue()) {
         case "Exploit":
            break;
         case "Basic":
         case "Other":
            mc.playerController.windowClick(guiChest.inventorySlots.windowId, n2, n, 2, mc.thePlayer);
      }

      this.resetClickTickCounter();
   }

   private void updateVirtualCursor(Container container, int n) {
      ScaledResolution sr = new ScaledResolution(mc);
      int n2 = (sr.getScaledWidth() - 176) / 2;
      int n3 = (sr.getScaledHeight() - this.getContainerGuiHeight(container)) / 2;
      Slot slot = container.getSlot(n);
      InventoryUtil.setLastCursorPos((double)(slot.xDisplayPosition + 2 + n2), (double)(slot.yDisplayPosition + 2 + n3));
      InventoryUtil.setLastClickedSlot(n);
   }

   private ArrayList findDestinationSlots(int n, Container container) {
      ItemStack stack = container.getSlot(n).getStack();
      if (stack == null) {
         return new ArrayList();
      } else {
         int stackSize = stack.stackSize;
         ArrayList list = new ArrayList();
         if (stack.getMaxStackSize() > 1) {
            for (TuplePair tuplePair : (Iterable<TuplePair>)(this.findPartialStackSlots(stack, container))) {
               stackSize -= ((Number)tuplePair.getSecond()).intValue();
               list.add(tuplePair.getFirst());
               if (stackSize <= 0) {
                  return list;
               }
            }
         }

         for (int i = container.inventorySlots.size() - 1; i >= container.inventorySlots.size() - 36; i--) {
            if (container.getSlot(i).getStack() == null) {
               list.add(i);
               break;
            }
         }

         return list;
      }
   }

   private ArrayList findPartialStackSlots(ItemStack stack, Container container) {
      if (stack.getMaxStackSize() <= 1) {
         return new ArrayList();
      } else {
         ArrayList list = new ArrayList();

         for (int i = container.inventorySlots.size() - 36; i < container.inventorySlots.size(); i++) {
            ItemStack stack2 = container.getSlot(i).getStack();
            if (stack2 != null && stack2.stackSize < stack2.getMaxStackSize()) {
               if (stack2.getItem().equals(stack.getItem())) {
                  list.add(new TuplePair(i, stack2.getMaxStackSize() - stack2.stackSize));
               }
            }
         }

         return list;
      }
   }

   private void updateCpsRecalculation() {
      TickCounter cpsRecalculateCounter2 = this.cpsRecalculateCounter;
      if (cpsRecalculateCounter2.hasReached(this.cpsRecalculate.getLastIntValue())) {
         this.cps.nextValue();
         this.cpsRecalculate.nextValue();
         this.cpsRecalculateCounter.reset();
      }

      cpsRecalculateCounter2 = this.cpsNewRecalculateCounter;
      if (cpsRecalculateCounter2.hasReached(this.cpsRecalculateNew.getLastIntValue())) {
         this.cpsNew.nextValue();
         this.cpsRecalculateNew.nextValue();
         this.cpsNewRecalculateCounter.reset();
      }
   }

   private void resetRandomizers() {
      this.cps.nextValue();
      this.cpsRecalculate.nextValue();
      this.missClickRate.nextValue();
      this.doubleClickRate.nextValue();
      this.failRate.nextValue();
      this.startDelay.nextValue();
      this.closeDelay.nextValue();
      this.chestOpenTime.nextValue();
      this.cpsNew.nextValue();
      this.cpsRecalculateNew.nextValue();
      this.emptySlotFailRate.nextValue();
      this.sideStopTicks.nextValue();
      this.doubleClickRateNew.nextValue();
      this.nextTargetSlot = -1;
      this.lastRowIndex = 0;
      this.startedStealing = false;
      this.wasChestOpenLastTick = false;
      this.visitedSlots.clear();
      this.failedEmptySlots.clear();
      this.resetClickTickCounter();
   }

   public void resetClickTickCounter() {
      this.ticksSinceLastClick = 0;
   }

   private void toggleLinkedModules(boolean flag) {
      if (this.toggleStorageESP.getValue()) {
         ClientManagers.moduleManager.storageESP.setToggled(flag);
      }

      if (this.toggleChestAura.getValue()) {
         ClientManagers.moduleManager.chestAura.setToggled(flag);
      }
   }

   public int getNextTargetSlot() {
      return this.nextTargetSlot;
   }

   private int getContainerGuiHeight(Container container) {
      return 114 + (container.inventorySlots.size() - 36) / 9 * 18;
   }

   private void handleChestOpenTransition() {
      boolean flag = mc.currentScreen instanceof GuiChest;
      if (flag && !this.wasChestOpen) {
         this.startDelay.nextValue();
         this.closeDelay.nextValue();
         this.chestOpenTime.nextValue();
         this.visitedSlots.clear();
         if (!this.isDefaultMode()) {
            List list = ContainerSlotQuery.of(((GuiChest)mc.currentScreen).inventorySlots).emptyOnly().toSlotIndexList();
            this.failedEmptySlots.clear();

            for (int n : (Iterable<Integer>)(list)) {
               RandomizedRangeSettings emptySlotFailRate2 = this.emptySlotFailRate;
               if (this.rollAccumulator(this.emptySlotFailAccumulator, emptySlotFailRate2)) {
                  this.failedEmptySlots.add(n);
               }
            }
         }
      }

      this.wasChestOpen = flag;
   }

   private boolean isDefaultMode() {
      return this.mode.getSelectedValue().equals("Default");
   }

   public boolean shouldStealFromChest(GuiChest guiChest) {
      return ContainerUtil.isValidChest(guiChest, this.whereToSteal.getSelectedValue(), this.ignoreEnderChests.getValue(), this.chestStorage);
   }

   private void executeHotbarSwapSlot(int n, int n2, GuiChest guiChest) {
      this.hotbarSwapSlot(n, n2, guiChest);
   }

   private void executeClickSlotExperimental(int n, GuiChest guiChest) {
      this.clickSlotExperimental(n, guiChest);
   }

   private boolean isBackgroundVisible() {
      return this.silent.getValue() && this.stealIndicator.getValue();
   }

   private boolean isIndicatorColorVisible() {
      return this.silent.getValue() && this.stealIndicator.getValue();
   }

   private boolean isIgnoreEnderChestsVisible() {
      return !this.whereToSteal.getSelectedValue().equals("Always");
   }

   private boolean isVisualizeVisible() {
      return !this.silent.getValue();
   }

   private boolean isAutoSortVisible() {
      return this.intelligent.getValue()
         && Augustus.getInstance().getModuleManager() != null
         && Augustus.getInstance().getModuleManager().invManager != null
         && Augustus.getInstance().getModuleManager().invManager.isToggled()
         && InvManagerActionOption.SORT.isSelected();
   }

   private boolean isRemoveEmptyRowsVisible() {
      return !this.isDefaultMode();
   }

   private boolean isSideStopTicksVisible() {
      return !this.isDefaultMode();
   }

   private boolean isDoubleClickRateNewVisible() {
      return !this.isDefaultMode();
   }

   private boolean isCpsRecalculateNewVisible() {
      return !this.isDefaultMode();
   }

   private boolean isCpsNewVisible() {
      return !this.isDefaultMode();
   }

   private boolean isMissClickRateVisible() {
      return !this.instant.getValue() && this.isDefaultMode();
   }

   private boolean isFailRateVisible() {
      return !this.instant.getValue() && this.isDefaultMode();
   }

   private boolean isDoubleClickRateVisible() {
      return !this.instant.getValue() && this.isDefaultMode();
   }

   private boolean isCpsRecalculateVisible() {
      return !this.instant.getValue() && this.isDefaultMode();
   }

   private boolean isMaxMouseMoveSpeedVisible() {
      return !this.instant.getValue() && this.isDefaultMode();
   }


}
