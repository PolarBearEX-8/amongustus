package net.augustus.modules.player;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;
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
import net.augustus.settings.ModeSetting;
import net.augustus.utils.math.Vec2d;
import net.augustus.utils.player.ContainerUtil;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.modules.modes.stealer.StealerNoneMouseMode;
import net.augustus.modules.modes.stealer.AbstractStealerMouseMode;
import net.augustus.modules.modes.stealer.StealerLinearMouseMode;
import net.augustus.modules.modes.stealer.StealerCubicMouseMode;
import net.augustus.modules.modes.stealer.StealerMouseSimulationMode;
import net.augustus.utils.math.DoubleSampleWindow;
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

public class Stealer extends Module implements TickListener, RenderListener {
   private final RandomizedRangeSettings startDelay;
   private final BooleanValue autoClose;
   private final RandomizedRangeSettings closeDelay;
   private final RandomizedRangeSettings chestOpenTime;
   private final ModeSetting mouseMode;
   private final StringValue clickCalculationMode;
   private final SettingGroup clickSettings;
   private final RandomizedRangeSettings cps;
   private final RandomizedRangeSettings delayBeforeClick;
   private final RandomizedRangeSettings delayAfterClick;
   private final RandomizedRangeSettings misClickChance;
   private final RandomizedRangeSettings failRateChance;
   private final RandomizedRangeSettings failDuration;
   private final RandomizedRangeSettings doubleClickChance;
   private final BooleanValue stealOnlyOncePerTick;
   private final BooleanValue row;
   private final SettingGroup rowSettings;
   private final RandomizedRangeSettings rowCPS;
   private final RandomizedRangeSettings rowDelayBeforeClick;
   private final RandomizedRangeSettings rowDelayAfterClick;
   private final BooleanValue stealZeroStackSizeItems;
   private final BooleanValue stealAbilityCheck;
   private final BooleanValue intelligent;
   private final BooleanValue ignoreBadPotions;
   private final SettingGroup itemBlackList;
   private final BooleanValue autoSort;
   public final StringValue whereToSteal;
   private final BooleanValue ignoreEnderChests;
   private final StringValue sequence;
   private final StringValue clickMode;
   private final BooleanValue toggleStorageESP;
   private final BooleanValue toggleChestAura;
   public final BooleanValue silent;
   public final BooleanValue visualize;
   private final BooleanValue stealIndicator;
   private final ColorSetting color2;
   private final BooleanValue backGround;
   private final ColorSetting backgroundColor;
   private final BlurShadowSettings blurShadowSettings;
   public final StringListSetting chestStorage;
   private final ItemFilterSetting items;
   private final TickCounter startDelayCounter;
   private final TickCounter closeDelayCounter;
   private final TickCounter chestOpenCounter;
   private final TickCounter afterClickCounter;
   private final TickCounter beforeClickCounter;
   private final TickCounter failDurationCounter;
   private final ActionRateAccumulator cpsAccumulator;
   private final ActionRateAccumulator misClickAccumulator;
   private final ActionRateAccumulator failRateAccumulator;
   private final ActionRateAccumulator doubleClickAccumulator;
   private final DoubleSampleWindow clickIntervalWindow;
   private long lastClickNanoTime;
   private boolean wasEmptyLastTick;
   private List targetSlots;
   private int initialSlotCount;
   private int stolenSlotCount;
   private boolean hadItemsOnOpen;
   private boolean clickedCurrentSlot;
   private boolean wasHoveringTargetSlot;
   private int completedClickCount;
   private boolean clickedThisTick;
   private boolean chestInitialized;

   public Stealer() {
       super("Stealer", "Dieb", Color.black, Categorys.PLAYER);
      this.startDelay = (new RandomizedRangeSettings(this, "StartDelay", 2.0, 4.0, 0.0, 20.0, 0)).withoutNotInARow();
      this.autoClose = new BooleanValue(this, "AutoClose", true);
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(this, "CloseDelay", 1.0, 2.0, 0.0, 20.0, 0)).withoutNotInARow();
      BooleanValue autoClose2 = this.autoClose;
      BooleanValue booleanValue = autoClose2;
      this.closeDelay = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> booleanValue.getValue());
      RandomizedRangeSettings randomizedRangeSettings2 = (new RandomizedRangeSettings(this, "ChestOpenTime", 3.0, 4.0, 0.0, 20.0, 0)).withoutNotInARow();
      autoClose2 = this.autoClose;
      BooleanValue booleanValue2 = autoClose2;
      this.chestOpenTime = (RandomizedRangeSettings)randomizedRangeSettings2.withVisibility(() -> booleanValue2.getValue());
      BiFunction[] biFunctions = new BiFunction[]{
         (obj, obj2) -> new StealerNoneMouseMode((Stealer)obj, (SettingGroup)obj2),
         (obj3, obj4) -> new StealerLinearMouseMode((Stealer)obj3, (SettingGroup)obj4),
         (obj5, obj6) -> new StealerCubicMouseMode((Stealer)obj5, (SettingGroup)obj6),
         null
      };
      biFunctions[3] = (obj7, obj8) -> new StealerMouseSimulationMode((Stealer)obj7, (SettingGroup)obj8);
      this.mouseMode = new ModeSetting(this, "MouseMode", "Linear", biFunctions);
      this.clickCalculationMode = new StringValue(this, "ClickCalculationMode", "Default", new String[]{"Instant", "Default", "Intave"});
      this.clickSettings = new SettingGroup(this, "ClickSettings", true).visibleWhen(() -> this.isClickSettingsVisible());
      this.cps = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "CPS", 8.0, 12.0, 1.0, 20.0, 0, this.clickSettings).withVisibility(() -> this.isDefaultClickMode());
      this.delayBeforeClick = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "DelayBeforeClick", 0.0, 2.0, 0.0, 4.0, 0, this.clickSettings).withVisibility(() -> this.isDefaultClickMode());
      this.delayAfterClick = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "DelayAfterClick", 0.0, 2.0, 0.0, 4.0, 0, this.clickSettings).withVisibility(() -> this.isDefaultClickMode());
      this.misClickChance = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "MisClickChance", 0.0, 0.0, 0.0, 100.0, 0, this.clickSettings)).withoutNotInARow().withVisibility(() -> this.isDefaultClickMode());
      this.failRateChance = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "FailRateChance", 5.0, 10.0, 0.0, 100.0, 0, this.clickSettings)).withoutNotInARow().withVisibility(() -> this.isDefaultClickMode());
      this.failDuration = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(this, "FailDuration", 2.0, 3.0, 0.0, 5.0, 0, this.clickSettings).withVisibility(() -> this.isFailDurationVisible())).withVisibility(() -> this.isDefaultClickMode());
      this.doubleClickChance = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "DoubleClickChance", 50.0, 70.0, 0.0, 100.0, 0, this.clickSettings)).withoutNotInARow().withVisibility(() -> this.isDefaultClickMode());
      this.stealOnlyOncePerTick = (BooleanValue)new BooleanValue(this, "StealOnlyOncePerTick", false, this.clickSettings).withVisibility(() -> this.isDefaultClickMode());
      this.row = (BooleanValue)new BooleanValue(this, "Row", true, this.clickSettings).withVisibility(() -> this.isDefaultClickMode());
      SettingGroup rowSettingsGroup = new SettingGroup(this, "RowSettings", true, this.clickSettings);
      autoClose2 = this.row;
      BooleanValue booleanValue5 = autoClose2;
      this.rowSettings = rowSettingsGroup.visibleWhen(() -> booleanValue5.getValue()).visibleWhen(() -> this.isRowSettingsVisible());
      this.rowCPS = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "RowCPS", 8.0, 12.0, 1.0, 20.0, 0, this.rowSettings).withVisibility(() -> this.isDefaultClickMode());
      this.rowDelayBeforeClick = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "RowDelayBeforeClick", 0.0, 2.0, 0.0, 4.0, 0, this.rowSettings).withVisibility(() -> this.isDefaultClickMode());
      this.rowDelayAfterClick = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "RowDelayAfterClick", 0.0, 2.0, 0.0, 4.0, 0, this.rowSettings).withVisibility(() -> this.isDefaultClickMode());
      this.stealZeroStackSizeItems = new BooleanValue(this, "StealZeroStackSizeItems", false);
      this.stealAbilityCheck = new BooleanValue(this, "StealAbilityCheck", true);
      this.intelligent = new BooleanValue(this, "Intelligent", true);
      autoClose2 = new BooleanValue(this, "IgnoreBadPotions", false);
      BooleanValue intelligent2 = this.intelligent;
      BooleanValue booleanValue6 = intelligent2;
      this.ignoreBadPotions = (BooleanValue)autoClose2.withVisibility(() -> booleanValue6.getValue());
      SettingGroup itemBlackListGroup = new SettingGroup(this, "ItemBlackList");
      intelligent2 = this.intelligent;
      BooleanValue booleanValue7 = intelligent2;
      this.itemBlackList = itemBlackListGroup.visibleWhen(() -> booleanValue7.getValue());
      autoClose2 = new BooleanValue(this, "AutoSort", true);
      this.autoSort = (BooleanValue)autoClose2.withVisibility(() -> this.isAutoSortVisible());
      this.whereToSteal = new StringValue(this, "WhereToSteal", "ChestsOnly", new String[]{"Always", "ChestsOnly", "CustomChest"});
      autoClose2 = new BooleanValue(this, "IgnoreEnderChests", false);
      this.ignoreEnderChests = (BooleanValue)autoClose2.withVisibility(() -> this.isIgnoreEnderChestsVisible());
      this.sequence = new StringValue(this, "Sequence", "Nearest", new String[]{"Basic", "Nearest", "Random"});
      this.clickMode = new StringValue(this, "ClickMode", "Basic", new String[]{"Basic"});
      this.toggleStorageESP = new BooleanValue(this, "ToggleStorageESP", false);
      this.toggleChestAura = new BooleanValue(this, "ToggleChestAura", false);
      this.silent = (BooleanValue)(new BooleanValue(this, "Silent", false)).markVisualOnly();
      autoClose2 = new BooleanValue(this, "Visualize", true);
      this.visualize = (BooleanValue)((BooleanValue)autoClose2.withVisibility(() -> this.isVisualizeVisible())).markVisualOnly();
      autoClose2 = new BooleanValue(this, "StealIndicator", true);
      intelligent2 = this.silent;
      BooleanValue booleanValue8 = intelligent2;
      this.stealIndicator = (BooleanValue)((BooleanValue)autoClose2.withVisibility(() -> booleanValue8.getValue())).markVisualOnly();
      this.color2 = (ColorSetting)((ColorSetting)new ColorSetting(this, "Color", Color.WHITE).withVisibility(() -> this.isIndicatorColorVisible())).markVisualOnly();
      autoClose2 = new BooleanValue(this, "BackGround", true);
      this.backGround = (BooleanValue)((BooleanValue)autoClose2.withVisibility(() -> this.isBackgroundVisible())).markVisualOnly();
      this.backgroundColor = (ColorSetting)((ColorSetting)new ColorSetting(this, "BackGroundColor", new Color(0, 0, 0, 100)).withVisibility(() -> this.isBackgroundColorVisible())).markVisualOnly();
      BlurShadowSettings blurShadowSettings3 = new BlurShadowSettings(this).withCustomShadowColor(Color.black);
      SettingVisibilityCondition condition28 = () -> {
         return this.silent.getValue() && this.stealIndicator.getValue();
      };
      this.blurShadowSettings = (BlurShadowSettings)((BlurShadowSettings)blurShadowSettings3.withVisibility(condition28)).markAllVisualOnly();
      this.chestStorage = (StringListSetting)new StringListSetting(this, "ChestStorage", new ArrayList()).withVisibility(() -> false);
      SettingGroup itemBlackList2 = this.itemBlackList;
      BooleanValue intelligent3 = this.intelligent;
      SettingVisibilityCondition condition30 = () -> intelligent3.getValue();
      this.items = new ItemFilterSetting(this, "Items", itemBlackList2, condition30, obj9 -> {
         return !this.isGearItem((Item)obj9) && !(obj9 instanceof ItemPotion) && InventoryUtil.isCleanableItem((Item)obj9);
      });
      this.startDelayCounter = new TickCounter();
      this.closeDelayCounter = new TickCounter();
      this.chestOpenCounter = new TickCounter();
      this.afterClickCounter = new TickCounter();
      this.beforeClickCounter = new TickCounter();
      this.failDurationCounter = new TickCounter();
      this.cpsAccumulator = new ActionRateAccumulator(60, (double)DefaultRandomGenerator.instance.nextInt((byte)0, (byte)60));
      this.misClickAccumulator = new ActionRateAccumulator(300, (double)DefaultRandomGenerator.instance.nextInt((byte)0, (byte)100));
      this.failRateAccumulator = new ActionRateAccumulator(100, (double)DefaultRandomGenerator.instance.nextInt((byte)0, (byte)100));
      this.doubleClickAccumulator = new ActionRateAccumulator(100, (double)DefaultRandomGenerator.instance.nextInt((byte)0, (byte)100));
      this.clickIntervalWindow = new DoubleSampleWindow(30);
      this.lastClickNanoTime = System.currentTimeMillis();
      this.wasEmptyLastTick = false;
      this.targetSlots = new ArrayList();
      this.initialSlotCount = 0;
      this.stolenSlotCount = 0;
      this.hadItemsOnOpen = false;
      this.clickedCurrentSlot = false;
      this.wasHoveringTargetSlot = false;
      this.completedClickCount = 0;
      this.clickedThisTick = false;
      this.chestInitialized = false;
   }

   @Override
   public void onEnable() {
      this.toggleLinkedModules(true);
      this.chestInitialized = false;
      this.completedClickCount = 0;
   }

   @Override
   public void onDisable() {
      this.toggleLinkedModules(false);
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(32);
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      if (this.silent.getValue() && this.stealIndicator.getValue() && ContainerUtil.isChestOpen()) {
         String str = "Stealing (" + this.stolenSlotCount + "/" + this.initialSlotCount + ")";
         BlurShadowManager.enableOneShotPass(this.blurShadowSettings.isBlurActive(), this.blurShadowSettings.isShadowActive());
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderStealIndicator(str, sr, shaderRenderPass));
         RenderUtil.resetGlColorState();
      }
   }

   private boolean isValidChestOpen() {
      if (ContainerUtil.isChestOpen()) {
         if (this.shouldStealFromChest((GuiChest)mc.currentScreen)) {
            return ((GuiChest)mc.currentScreen).inventorySlots.isReceivedItems();
         }
      }

      return false;
   }

   @Override
   public void onEarlyTick() {
      if (this.isValidChestOpen()) {
         Container inventorySlots = ((GuiChest)mc.currentScreen).inventorySlots;
         if (!this.chestInitialized) {
            this.refreshTargetSlots(inventorySlots);
            this.initialSlotCount = this.targetSlots.size();
            this.hadItemsOnOpen = this.hasTargetSlots();
            this.chestOpenCounter.reset();
            this.startDelayCounter.reset();
            this.startDelay.nextValue();
            ((AbstractStealerMouseMode)this.mouseMode.getAbstractSubMode()).resetCursorPosition();
            this.chestInitialized = true;
            return;
         }

         if (this.checkAutoClose()) {
            return;
         }

         try {
            this.processChestSteal((GuiChest)mc.currentScreen);
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      } else {
         this.chestInitialized = false;
         this.completedClickCount = 0;
      }

      this.incrementTickCounters();
   }

   private boolean checkAutoClose() {
      if (this.autoClose.getValue()) {
         if (!this.hasTargetSlots()) {
            if (this.hadItemsOnOpen) {
               if (!this.wasEmptyLastTick) {
                  this.closeDelayCounter.reset();
               }

               if (this.closeDelayCounter.hasReached(this.closeDelay.getLastIntValue())) {
                  mc.thePlayer.closeScreen();
                  this.closeDelay.nextValue();
                  return true;
               }
            } else {
               if (this.chestOpenCounter.hasReached(this.chestOpenTime.getLastIntValue())) {
                  mc.thePlayer.closeScreen();
                  this.chestOpenTime.nextValue();
                  return true;
               }
            }

            return false;
         }
      }

      return false;
   }

   private void processChestSteal(GuiChest guiChest) throws IOException {
      if (this.startDelayCounter.hasReached(this.startDelay.getLastIntValue())) {
         Container inventorySlots = guiChest.inventorySlots;
         this.wasEmptyLastTick = !this.hasTargetSlots();
         AtomicBoolean atomicBoolean = new AtomicBoolean(false);
         this.clickedThisTick = false;
         if (!this.stealInstantly(inventorySlots)) {
            for (int i = 0; i < 3; i++) {
               if (!this.hasTargetSlots() || this.mouseMode.getAbstractSubMode() == null) {
                  return;
               }

               Slot slot = (Slot)this.targetSlots.get(0);
               Vec2d vec2d = ((AbstractStealerMouseMode)this.mouseMode.getAbstractSubMode()).computeNextCursorPos(inventorySlots, this.targetSlots);
               int n = (int)Math.round(vec2d.x);
               int n2 = (int)Math.round(vec2d.y);
               boolean isMouseOverSlot = guiChest.isMouseOverSlot(slot, n, n2);
               if (isMouseOverSlot) {
                  if (this.stealSlot(inventorySlots, slot, this.targetSlots)) {
                     this.refreshTargetSlots(inventorySlots);
                     ((AbstractStealerMouseMode)this.mouseMode.getAbstractSubMode()).onTargetSlotChanged();
                     this.cps.nextValue();
                     this.rowCPS.nextValue();
                     this.completedClickCount++;
                     this.clickedThisTick = true;
                  }
               } else {
                  if (this.misClickChance.getMaxValue() > 0.0 && !atomicBoolean.get()) {
                     ContainerSlotQuery.of(inventorySlots).emptyOnly().toSlotList().stream().filter(obj -> {
                        return guiChest.isMouseOverSlot((Slot)obj, n, n2) && ((Slot)obj).getDistanceTo(slot) < 25.0 && ((Slot)obj).slotNumber != InventoryUtil.getLastClickedSlot();
                     }).findFirst().ifPresent(obj2 -> this.tryMisClickSlot(inventorySlots, atomicBoolean, (Slot)obj2));
                  }
               }

               this.wasHoveringTargetSlot = isMouseOverSlot;
            }
         }
      }
   }

   private boolean stealInstantly(Container container) {
      if (!this.clickCalculationMode.getSelectedValue().equals("Instant")) {
         return false;
      } else {
         this.refreshTargetSlots(container);

         for (Slot slot : (Iterable<Slot>)(this.targetSlots)) {
            this.stealSlot(container, slot, this.targetSlots);
            this.refreshTargetSlots(container);
         }

         return true;
      }
   }

   private boolean executeClickInstant(List list, Runnable runnable) {
      runnable.run();
      return true;
   }

   private boolean executeClickAction(List list, Runnable runnable) {
      switch (this.clickCalculationMode.getSelectedValue()) {
         case "Default":
            return this.executeClickDefault(list, runnable);
         case "Instant":
            return this.executeClickInstant(list, runnable);
         case "Intave":
            return this.executeClickIntave(list, runnable);
         default:
            return false;
      }
   }

   private boolean executeClickIntave(List list, Runnable runnable) {
      if (!this.wasHoveringTargetSlot) {
         this.beforeClickCounter.reset();
      }

      int n = this.computeOptimalIntaveDelay();
      if (this.beforeClickCounter.hasReached(n) && !this.clickedThisTick) {
         if (!this.clickedCurrentSlot) {
            runnable.run();
            if (this.completedClickCount > 0) {
               double d = (double)(System.nanoTime() - this.lastClickNanoTime) / 1.0E9;
               this.clickIntervalWindow.add(d);
            }

            this.lastClickNanoTime = System.nanoTime();
         }

         this.clickedCurrentSlot = true;
         this.clickedThisTick = true;
      }

      return this.beforeClickCounter.hasReached(n) && this.clickedCurrentSlot;
   }

   private boolean executeClickDefault(List list, Runnable runnable) {
      boolean isConsecutiveRow = this.isConsecutiveRow(list);
      if (!this.wasHoveringTargetSlot) {
         this.rerollDelayBeforeClick(isConsecutiveRow);
         this.beforeClickCounter.reset();
      }

      TickCounter beforeClickCounter2 = this.beforeClickCounter;
      if (beforeClickCounter2.hasReached(this.getDelayBeforeClick(isConsecutiveRow))) {
         if (this.cpsAccumulator.shouldTrigger(this.getEffectiveCps(isConsecutiveRow)) && (!this.stealOnlyOncePerTick.getValue() || !this.clickedThisTick)) {
            if (!this.clickedCurrentSlot) {
               if (this.failRateAccumulator.shouldTrigger(this.failRateChance.getLastIntValue())) {
                  this.failDurationCounter.reset();
                  this.failRateChance.nextValue();
                  this.failDuration.nextValue();
               }
            }

            beforeClickCounter2 = this.failDurationCounter;
            if (beforeClickCounter2.hasReached(this.failDuration.getLastIntValue())) {
               if (!this.clickedCurrentSlot) {
                  runnable.run();
               } else {
                  if (this.doubleClickAccumulator.shouldTrigger(this.doubleClickChance.getLastIntValue())) {
                     runnable.run();
                     this.doubleClickChance.nextValue();
                  }
               }

               this.clickedCurrentSlot = true;
            }
         }
      }

      if (!this.clickedCurrentSlot) {
         this.afterClickCounter.reset();
         this.rerollDelayAfterClick(isConsecutiveRow);
      }

      beforeClickCounter2 = this.afterClickCounter;
      if (beforeClickCounter2.hasReached(this.getDelayAfterClick(isConsecutiveRow))) {
         beforeClickCounter2 = this.beforeClickCounter;
         if (beforeClickCounter2.hasReached(this.getDelayBeforeClick(isConsecutiveRow)) && this.clickedCurrentSlot) {
            return true;
         }
      }

      return false;
   }

   private void rerollDelayAfterClick(boolean flag) {
      if (flag) {
         this.rowDelayAfterClick.nextValue();
      } else {
         this.delayAfterClick.nextValue();
      }
   }

   private void rerollDelayBeforeClick(boolean flag) {
      if (flag) {
         this.rowDelayBeforeClick.nextValue();
      } else {
         this.delayBeforeClick.nextValue();
      }
   }

   private int computeOptimalIntaveDelay() {
      int n = 0;
      double d = 0.0;

      for (int i = 2; i < 6; i++) {
         double d2 = (double)((System.nanoTime() + Math.max((long)i - this.beforeClickCounter.getTicks(), 0L) * 50000000L) - this.lastClickNanoTime) / 1.0E9;
         ArrayList list = new ArrayList(this.clickIntervalWindow.getSamples());
         list.add(d2);
         double d3 = this.calculateVariance(list);
         if (d3 > d) {
            d = d3;
            n = i;
         }
      }

      return n;
   }

   private int getDelayAfterClick(boolean flag) {
      int n;
      if (flag) {
         n = this.rowDelayAfterClick.nextInt();
      } else {
         n = this.delayAfterClick.nextInt();
      }

      return n;
   }

   private int getDelayBeforeClick(boolean flag) {
      int n;
      if (flag) {
         n = this.rowDelayBeforeClick.nextInt();
      } else {
         n = this.delayBeforeClick.nextInt();
      }

      return n;
   }

   private double calculateVariance(List list) {
      double d = 0.0;

      for (double d2 : (Iterable<Double>)(list)) {
         d += d2;
      }

      double d3 = d / (double)list.size();
      double d4 = 0.0;

      for (double d5 : (Iterable<Double>)(list)) {
         d4 += Math.pow(d5 - d3, 2.0);
      }

      return d4 / (double)list.size();
   }

   private int getEffectiveCps(boolean flag) {
      int n;
      if (flag) {
         n = this.rowCPS.getLastIntValue();
      } else {
         n = this.cps.nextInt();
      }

      return n * (this.stealOnlyOncePerTick.getValue() ? 1 : 3);
   }

   private boolean isConsecutiveRow(List list) {
      if (list.size() > 2 && this.row.getValue()) {
         boolean flag = false;

         for (int i = 0; i < 2; i++) {
            Slot slot = (Slot)list.get(i);
            Slot slot2 = (Slot)list.get(i + 1);
            int n = Math.abs(slot.xDisplayPosition - slot2.xDisplayPosition);
            int n2 = Math.abs(slot.yDisplayPosition - slot2.yDisplayPosition);
            if (n != 0 && n2 != 0) {
               return false;
            }

            if (i == 0) {
               flag = n != 0;
            } else if (flag) {
               if (n2 != 0) {
                  return false;
               }
            } else if (n != 0) {
               return false;
            }

            if (n > 19 || n2 > 19) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private void refreshTargetSlots(Container container) {
      this.targetSlots = this.collectOrderedTargetSlots(container);
      this.stolenSlotCount = Math.max(this.initialSlotCount - this.targetSlots.size(), 0);
      this.clickedCurrentSlot = false;
   }

   private boolean stealSlot(Container container, Slot slot, List list) {
      if (this.intelligent.getValue() && this.autoSort.getValue()) {
         String itemCategoryName = InventoryUtil.getItemCategoryName(slot.getStack());
         ArrayList hotbarSlotsForCategory = ClientManagers.moduleManager.invManager.getHotbarSlotsForCategory(itemCategoryName);
         if (!hotbarSlotsForCategory.isEmpty()) {
            if (ContainerSlotQuery.of(mc.thePlayer.inventoryContainer).fromSlot((byte)9).hasAnyEmptySlot()) {
               int n = (Integer)hotbarSlotsForCategory.get(0);
               if (!itemCategoryName.equals("Sword") && !itemCategoryName.equals("Bow") && !itemCategoryName.equals("Rod")) {
                  if (InventoryUtil.getItemCategoryName(mc.thePlayer.inventoryContainer.getSlot(n + 36).getStack()).equals(itemCategoryName)) {
                     return this.executeClickAction(list, () -> this.clickOverflowSortSlot(container, slot));
                  }
               }

               Runnable runnable2 = () -> {
                  ContainerUtil.swapHotbarSlot(container, this.clickMode.getSelectedValue(), slot, n);
               };
               return this.executeClickAction(list, runnable2);
            }
         }
      }

      return this.executeClickAction(list, () -> this.clickTargetSlot(container, slot));
   }

   private List collectOrderedTargetSlots(Container container) {
      List list = this.collectOrderedTargetSlotIndices(container);
      ArrayList list2 = new ArrayList();

      for (Integer num : (Iterable<Integer>)(list)) {
         list2.add(container.getSlot(num));
      }

      return list2;
   }

   private List collectOrderedTargetSlotIndices(Container container) {
      return InventoryUtil.sortSlotsByMode(container, this.collectStealableSlotIndices(container), this.sequence.getSelectedValue(), InventoryUtil.getContainerGuiHeight(container));
   }

   private List collectStealableSlotIndices(Container container) {
      ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(container);
      containerSlotQuery = containerSlotQuery.toSlot(container.inventorySlots.size() - 36);
      List list = containerSlotQuery.nonEmptyOrNonNull(this.stealZeroStackSizeItems.getValue()).toSlotIndexList();
      if (this.stealAbilityCheck.getValue()) {
         for (int i = list.size() - 1; i >= 0; i--) {
            if (!this.canFitInInventory(container.getSlot((Integer)list.get(i)))) {
               list.remove(i);
            }
         }
      }

      if (!this.intelligent.getValue()) {
         return list;
      } else {
         List list2 = this.collectBestArmorStacks(container);
         ArrayList list3 = new ArrayList();
         ArrayList list4;
         if (this.ignoreBadPotions.getValue()) {
            list4 = InventoryUtil.getGoodPotionSlots(container, (byte)0);
         } else {
            list4 = new ArrayList();
         }

         ArrayList list5 = list4;

         for (int n2 : (Iterable<Integer>)(list)) {
            Slot slot3 = container.getSlot(n2);
            ItemStack stack = slot3.getStack();
            if (this.isBestItemInChest(stack, container, list2) && this.isUpgradeOverInventory(stack, list2)) {
               if (!this.items.isItemAllowed(stack.getItem()) || list5.contains(slot3.slotNumber)) {
                  list3.add(n2);
               }
            }
         }

         return list3;
      }
   }

   private boolean canFitInInventory(Slot slot) {
      ItemStack stack = slot.getStack();

      for (Slot slot2 : (Iterable<Slot>)(mc.thePlayer.inventoryContainer.inventorySlots)) {
         if (slot2.slotNumber >= 9 && !slot2.getHasStack()) {
            return true;
         }
      }

      return mc.thePlayer.inventoryContainer.inventorySlots.stream().anyMatch(obj -> {
         ItemStack stack2 = ((Slot)obj).getStack();
         return ((Slot)obj).slotNumber >= 9 && stack2 != null && stack2.stackSize != stack2.getMaxStackSize() && stack2.isStackable() ? stack2.isItemEqual(stack) : false;
      });
   }

   private List collectBestArmorStacks(Container container) {
      List list = ContainerSlotQuery.of(container).filterItem(obj -> obj instanceof ItemArmor).toStackList();
      list.addAll(ContainerSlotQuery.of(mc.thePlayer.inventoryContainer).filterItem(obj2 -> obj2 instanceof ItemArmor).toStackList());
      return InventoryUtil.findBestArmorSetFromList(list, ClientManagers.moduleManager.invManager.isToggled() && InvManagerActionOption.PUT_ON_ARMOR.isSelected() && ClientManagers.moduleManager.invManager.ignoreDurability.getValue());
   }

   public boolean isBestItemInChest(ItemStack stack, Container container, List list) {
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

               if (item instanceof ItemArmor && item2 instanceof ItemArmor && !list.contains(stack)) {
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

   public boolean isUpgradeOverInventory(ItemStack stack, List list) {
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
                     !list.contains(stack)
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

   private void toggleLinkedModules(boolean flag) {
      if (this.toggleStorageESP.getValue()) {
         ClientManagers.moduleManager.storageESP.setToggled(flag);
      }

      if (this.toggleChestAura.getValue()) {
         ClientManagers.moduleManager.chestAura.setToggled(flag);
      }
   }

   public boolean shouldStealFromChest(GuiChest guiChest) {
      return ContainerUtil.isValidChest(guiChest, this.whereToSteal.getSelectedValue(), this.ignoreEnderChests.getValue(), this.chestStorage);
   }

   private void incrementTickCounters() {
      this.startDelayCounter.increment();
      this.closeDelayCounter.increment();
      this.chestOpenCounter.increment();
      this.afterClickCounter.increment();
      this.beforeClickCounter.increment();
      this.failDurationCounter.increment();
   }

   private boolean hasTargetSlots() {
      return !this.targetSlots.isEmpty();
   }

   public Slot getNextTargetSlot() {
      return this.hasTargetSlots() ? (Slot)this.targetSlots.get(0) : null;
   }

   private boolean isDefaultClickMode() {
      return this.clickCalculationMode.getSelectedValue().equals("Default");
   }

   private void clickTargetSlot(Container container, Slot slot) {
      ContainerUtil.shiftClickSlot(container, this.clickMode.getSelectedValue(), slot);
   }

   private void clickOverflowSortSlot(Container container, Slot slot) {
      ContainerUtil.shiftClickSlot(container, this.clickMode.getSelectedValue(), slot);
   }

   private void tryMisClickSlot(Container container, AtomicBoolean atomicBoolean, Slot slot) {
      if (this.misClickAccumulator.shouldTrigger(this.misClickChance.getLastIntValue())) {
         this.misClickChance.nextValue();
         ContainerUtil.shiftClickSlot(container, this.clickMode.getSelectedValue(), slot);
         atomicBoolean.set(true);
      }
   }

   private void renderStealIndicator(String str, ScaledResolution sr, ShaderRenderPass shaderRenderPass) {
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
   }

   private boolean isBackgroundColorVisible() {
      return this.silent.getValue() && this.stealIndicator.getValue() && this.backGround.getValue();
   }

   private boolean isBackgroundVisible() {
      return this.silent.getValue() && this.stealIndicator.getValue();
   }

   private boolean isIndicatorColorVisible() {
      return this.silent.getValue() && this.stealIndicator.getValue();
   }

   private boolean isVisualizeVisible() {
      return !this.silent.getValue();
   }

   private boolean isIgnoreEnderChestsVisible() {
      return !this.whereToSteal.getSelectedValue().equals("Always");
   }

   private boolean isAutoSortVisible() {
      return this.intelligent.getValue()
         && Augustus.getInstance().getModuleManager() != null
         && Augustus.getInstance().getModuleManager().invManager != null
         && Augustus.getInstance().getModuleManager().invManager.isToggled()
         && InvManagerActionOption.SORT.isSelected();
   }

   private boolean isRowSettingsVisible() {
      return this.isDefaultClickMode() && this.row.getValue();
   }

   private boolean isFailDurationVisible() {
      return this.failRateChance.getMaxValue() > 0.0;
   }

   private boolean isClickSettingsVisible() {
      return this.isDefaultClickMode() || this.clickCalculationMode.getSelectedValue().equals("Intave");
   }

}
