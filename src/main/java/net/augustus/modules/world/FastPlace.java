package net.augustus.modules.world;

import net.minecraft.util.MovingObjectPosition;
import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.utils.ActionRateAccumulator;
import net.augustus.settings.options.FastPlaceItemOption;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.world.BlockUtil;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class FastPlace extends Module implements ClickListener, SilentSlotListener {
   private final MultiOptionSetting items;
   private final BooleanValue onlyOnClick;
   private BooleanValue alwaysPlaceWhenPossible;
   private BooleanValue oneClick;
   private BooleanValue clickPause;
   private BooleanValue autoSwap;
   private BooleanValue onlyInAir;
   private BooleanValue onlyIfMotionIsPositive;
   private boolean placedBlockThisTick;
   private boolean initialClickPending;
   private boolean wasOnFullBlock;
   private int remainingPostEdgeTicks;
   private int preEdgePredictTicks;
   private int postEdgePauseTicks;

   public FastPlace() {
       super("FastPlace", "SchnellPlatzierung", new Color(54, 144, 217), Categorys.WORLD);
      this.items = (MultiOptionSetting)(new MultiOptionSetting(this, "Items", FastPlaceItemOption.values())).markVisualOnly();
      this.onlyOnClick = (BooleanValue)(new BooleanValue(this, "OnlyOnClick", true)).markVisualOnly();
      this.placedBlockThisTick = false;
      this.initialClickPending = false;
      this.remainingPostEdgeTicks = 0;
      this.preEdgePredictTicks = DefaultRandomGenerator.instance.nextInt((byte)0, (byte)2);
      this.postEdgePauseTicks = DefaultRandomGenerator.instance.nextInt((byte)0, (byte)2);

      for (FastPlaceItemOption fastPlaceItemOption : FastPlaceItemOption.values()) {
         SettingGroup settingGroup = (SettingGroup)new SettingGroup(this, fastPlaceItemOption.getName()).visibleWhen(() -> fastPlaceItemOption.selected).markVisualOnly();
         RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(this, fastPlaceItemOption.name + "CPS", 10.0, 20.0, 0.0, 60.0, 0, settingGroup)).withoutNotInARow();
         fastPlaceItemOption.cpsRange = (RandomizedRangeSettings)((RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("CPS")).markAllVisualOnly();
         fastPlaceItemOption.cpsRange.nextValue();
         randomizedRangeSettings = (new RandomizedRangeSettings(this, fastPlaceItemOption.name + "DCPS", 2.0, 4.0, 0.0, 20.0, 0, settingGroup)).withoutNotInARow();
         fastPlaceItemOption.doubleClickCpsRange = (RandomizedRangeSettings)((RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("DCPS")).markAllVisualOnly();
         fastPlaceItemOption.doubleClickCpsRange.nextValue();
         if (fastPlaceItemOption == FastPlaceItemOption.BLOCKS) {
            this.alwaysPlaceWhenPossible = (BooleanValue)(new BooleanValue(this, "AlwaysPlaceWhenPossible", false, settingGroup)).markVisualOnly();
            this.oneClick = (BooleanValue)(new BooleanValue(this, "OneClick", false, settingGroup)).markVisualOnly();
            this.clickPause = (BooleanValue)(new BooleanValue(this, "ClickPause", false, settingGroup)).markVisualOnly();
            this.autoSwap = (BooleanValue)(new BooleanValue(this, "AutoSwap", false, settingGroup)).markVisualOnly();
            this.onlyInAir = (BooleanValue)(new BooleanValue(this, "OnlyInAir", false, settingGroup)).markVisualOnly();
            BooleanValue booleanValue = new BooleanValue(this, "OnlyIfMotionIsPositive", false, settingGroup);
            BooleanValue onlyInAir2 = this.onlyInAir;
            this.onlyIfMotionIsPositive = (BooleanValue)((BooleanValue)booleanValue.withVisibility(() -> onlyInAir2.getValue())).markVisualOnly();
         }
      }
   }

   @Override
   public void onEnable() {
      this.initialClickPending = true;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7, 2);
      this.toggleEventSubscription(34);
   }

   @Override
   public void onPostSilentSlot() {
      if (this.autoSwap.getValue() && FastPlaceItemOption.BLOCKS.isSelected() && this.placedBlockThisTick) {
         for (int i = 0; i < 9; i++) {
            if (BlockUtil.isValidScaffoldBlock(mc.thePlayer.inventoryContainer.getSlot(i + 36).getStack())) {
               SilentSlotManager.requestSlot(this, (byte)-2, i, true);
               SilentSlotManager.realHotbarSlot = i;
               break;
            }
         }
      }
   }

   @Override
   public void onClick(EventClick event) {
      this.placedBlockThisTick = false;
      if (mc.isNotInGui() && (mc.gameSettings.keyBindUseItem.isKeyDown() || !this.onlyOnClick.getValue()) && mc.thePlayer.getCurrentEquippedItem() != null) {
         for (FastPlaceItemOption fastPlaceItemOption : FastPlaceItemOption.values()) {
            ItemStack currentEquippedItem = mc.thePlayer.getCurrentEquippedItem();
            Item item = currentEquippedItem.getItem();
            if (fastPlaceItemOption.matchesItem(currentEquippedItem) || !true) {
               if (!fastPlaceItemOption.isSelected()) {
                  break;
               }

               if (!(item instanceof ItemBlock)) {
                  ActionRateAccumulator cpsAccumulator = fastPlaceItemOption.cpsAccumulator;
                  int n = cpsAccumulator.accumulate(fastPlaceItemOption.cpsRange.getLastIntValue());
                  boolean flag = n > 0;

                  boolean flag2;
                  for (flag2 = false; n > 0; flag2 = true) {
                     event.addRightClicks((byte)1);
                     n--;
                  }

                  if (flag) {
                     cpsAccumulator = fastPlaceItemOption.doubleClickCpsAccumulator;
                     int lastIntValue2 = fastPlaceItemOption.doubleClickCpsRange.getLastIntValue();

                     for (int i = cpsAccumulator.accumulate(lastIntValue2); i > 0; i--) {
                        event.addRightClicks((byte)1);
                        flag2 = true;
                     }

                     this.rerollOptionCps(fastPlaceItemOption);
                  }

                  if (this.initialClickPending && !flag2) {
                     event.addRightClicks((byte)1);
                  }

                  event.setIgnoreVanillaClicks(true);
                  event.setRightMouseDown(false);
                  this.initialClickPending = false;
                  break;
               }

               if ((!this.onlyInAir.getValue() || !mc.thePlayer.onGround) && (!this.onlyInAir.getValue() || !this.onlyIfMotionIsPositive.getValue() || !(mc.thePlayer.motionY <= 0.0))) {
                  boolean isFullBlock = mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ).getBlock().isFullBlock();
                  if (isFullBlock && !this.wasOnFullBlock) {
                     this.preEdgePredictTicks = DefaultRandomGenerator.instance.nextInt((byte)0, (byte)1);
                     this.postEdgePauseTicks = DefaultRandomGenerator.instance.nextInt((byte)0, (byte)1);
                  }

                  if (!isFullBlock && this.wasOnFullBlock) {
                     this.remainingPostEdgeTicks = this.postEdgePauseTicks;
                  }

                  boolean flag5 = (this.preEdgePredictTicks >= 0 || !this.wasOnFullBlock) && !isFullBlock;
                  this.wasOnFullBlock = isFullBlock;
                  double[] doubles = PlayerUtil.predictPlayerPosition(this.preEdgePredictTicks - 1, mc.thePlayer);
                  if (isFullBlock && !mc.theWorld.getBlockState(doubles[0], mc.thePlayer.posY - 1.0, doubles[2]).getBlock().isFullBlock()) {
                     flag5 = true;
                  }

                  if (isFullBlock && this.remainingPostEdgeTicks > 0) {
                     flag5 = true;
                     this.remainingPostEdgeTicks--;
                  }

                  if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                     boolean flag6 = false;
                     if (flag5 || !this.clickPause.getValue()) {
                        ItemStack currentEquippedItem2 = mc.thePlayer.getCurrentEquippedItem();
                        BlockPos blockPos = mc.objectMouseOver.getBlockPos();
                        Vec3 hitVec = mc.objectMouseOver.hitVec;
                        boolean canPlaceBlockAt = PlayerUtil.canPlaceBlockAt(currentEquippedItem2, blockPos, mc.objectMouseOver.sideHit, hitVec);
                        ActionRateAccumulator cpsAccumulator2 = fastPlaceItemOption.cpsAccumulator;
                        int n2 = cpsAccumulator2.accumulate(fastPlaceItemOption.cpsRange.getLastIntValue());
                        boolean flag8 = n2 > 0 && !this.oneClick.getValue() || canPlaceBlockAt && (this.oneClick.getValue() || this.alwaysPlaceWhenPossible.getValue());

                        while (n2 > 0 && !this.oneClick.getValue() || canPlaceBlockAt && (this.oneClick.getValue() || this.alwaysPlaceWhenPossible.getValue())) {
                           event.addRightClicks((byte)1);
                           event.setAllowMultiRightClick(this.oneClick.getValue() || this.alwaysPlaceWhenPossible.getValue());
                           canPlaceBlockAt = false;
                           flag6 = true;
                           if (--n2 < -30) {
                              break;
                           }
                        }

                        if (flag8) {
                           cpsAccumulator2 = fastPlaceItemOption.doubleClickCpsAccumulator;
                           int lastIntValue4 = fastPlaceItemOption.doubleClickCpsRange.getLastIntValue();

                           for (int j = cpsAccumulator2.accumulate(lastIntValue4); j > 0; flag6 = true) {
                              event.addRightClicks((byte)1);
                              j--;
                           }

                           this.rerollOptionCps(fastPlaceItemOption);
                        }
                     }

                     if (this.initialClickPending && !flag6) {
                        event.addRightClicks((byte)1);
                     }

                     event.setIgnoreVanillaClicks(true);
                     event.setRightMouseDown(false);
                     this.placedBlockThisTick = true;
                     this.initialClickPending = false;
                  }
                  break;
               }
            }
         }
      } else {
         this.initialClickPending = true;
      }
   }

   private void rerollOptionCps(FastPlaceItemOption fastPlaceItemOption) {
      fastPlaceItemOption.cpsRange.nextValue();
      fastPlaceItemOption.doubleClickCpsRange.nextValue();
   }


}
