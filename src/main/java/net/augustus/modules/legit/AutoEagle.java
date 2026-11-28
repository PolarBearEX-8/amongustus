package net.augustus.modules.legit;

import java.awt.Color;
import java.util.function.BiFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.modules.world.BridgeClickController;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.settings.ModeSetting;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.modules.modes.autoeagle.UnlegitAutoEagleMode;
import net.augustus.modules.modes.autoeagle.MoonWalkAutoEagleMode;
import net.augustus.modules.modes.autoeagle.EagleAutoEagleMode;
import net.augustus.utils.world.BlockUtil;
import net.augustus.Augustus;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;

public class AutoEagle extends Module implements ClickListener, SilentSlotListener {
   public final ModeSetting mode;
   private final DoubleValue minPitchToEagle;
   public final BooleanValue fastPlace;
   private final RandomizedRangeSettings cps;
   private final StringValue dcpsMode;
   private final RandomizedRangeSettings dcps;
   private final BooleanValue alwaysPlaceWhenPossible;
   private final BooleanValue delayClick;
   private final BooleanValue clickPause;
   private final BooleanValue oneClick;
   public final BooleanValue onlyWhileSneak;
   private final BooleanValue autoSwap;
   private final BridgeClickController bridgeClickController;
   private boolean firstPlaceAfterPress;
   public boolean eagling;
   private boolean wasBlockBelow;
   private int postEdgeClickTicks;
   private int preEdgeLookaheadTicks;
   private int postEdgeDelayTicks;
   private final TimeHelper timeHelper;

   public AutoEagle() {
       super("AutoEagle", "AutoAdler", new Color(194, 222, 146), Categorys.LEGIT);
      BiFunction[] biFunctions = new BiFunction[]{
         (obj, obj2) -> new EagleAutoEagleMode((AutoEagle)obj, (SettingGroup)obj2),
         (obj3, obj4) -> new MoonWalkAutoEagleMode((AutoEagle)obj3, (SettingGroup)obj4),
         (obj5, obj6) -> new UnlegitAutoEagleMode((AutoEagle)obj5, (SettingGroup)obj6)
      };
      this.mode = new ModeSetting(this, "Mode", "Eagle", biFunctions);
      this.minPitchToEagle = new DoubleValue(this, "MinPitchToEagle", 65.0, -90.0, 90.0, 0);
      this.fastPlace = new BooleanValue(this, "FastPlace", true);
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(this, "CPS", 10.0, 20.0, 0.0, 40.0, 0)).withoutNotInARow();
      BooleanValue fastPlace2 = this.fastPlace;
      BooleanValue booleanValue = fastPlace2;
      this.cps = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> booleanValue.getValue());
      StringValue setting = new StringValue(this, "DCPSMode", "External", new String[]{"Internal", "External"});
      fastPlace2 = this.fastPlace;
      BooleanValue booleanValue2 = fastPlace2;
      this.dcpsMode = (StringValue)setting.withVisibility(() -> booleanValue2.getValue());
      this.dcps = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "DCPS", 2.0, 4.0, 0.0, 20.0, 0)).withoutNotInARow().withVisibility(() -> this.isExternalDcpsMode());
      BooleanValue booleanValue3 = new BooleanValue(this, "AlwaysPlaceWhenPossible", true);
      fastPlace2 = this.fastPlace;
      BooleanValue booleanValue4 = fastPlace2;
      this.alwaysPlaceWhenPossible = (BooleanValue)booleanValue3.withVisibility(() -> booleanValue4.getValue());
      BooleanValue booleanValue5 = new BooleanValue(this, "DelayClick", false);
      fastPlace2 = this.fastPlace;
      BooleanValue booleanValue6 = fastPlace2;
      this.delayClick = (BooleanValue)booleanValue5.withVisibility(() -> booleanValue6.getValue());
      BooleanValue booleanValue7 = new BooleanValue(this, "ClickPause", false);
      fastPlace2 = this.fastPlace;
      BooleanValue booleanValue8 = fastPlace2;
      this.clickPause = (BooleanValue)booleanValue7.withVisibility(() -> booleanValue8.getValue());
      BooleanValue booleanValue9 = new BooleanValue(this, "OneClick", false);
      fastPlace2 = this.fastPlace;
      BooleanValue booleanValue10 = fastPlace2;
      this.oneClick = (BooleanValue)booleanValue9.withVisibility(() -> booleanValue10.getValue());
      this.onlyWhileSneak = new BooleanValue(this, "OnlyWhileSneak", true);
      this.autoSwap = (BooleanValue)(new BooleanValue(this, "AutoSwap", false)).markVisualOnly();
      this.bridgeClickController = new BridgeClickController(this.cps, this.dcpsMode, this.dcps, this.alwaysPlaceWhenPossible, this.oneClick, this.delayClick);
      this.firstPlaceAfterPress = false;
      this.postEdgeClickTicks = 0;
      this.preEdgeLookaheadTicks = DefaultRandomGenerator.instance.nextInt((byte)0, (byte)2);
      this.postEdgeDelayTicks = DefaultRandomGenerator.instance.nextInt((byte)0, (byte)2);
      this.timeHelper = new TimeHelper();
   }

   @Override
   public void onEnable() {
      this.firstPlaceAfterPress = true;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(34);
   }

   @Override
   public void onClick(EventClick event) {
      boolean isFullBlock = mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ).getBlock().isFullBlock();
      if (isFullBlock && !this.wasBlockBelow) {
         this.preEdgeLookaheadTicks = DefaultRandomGenerator.instance.nextInt((byte)0, (byte)1);
         this.postEdgeDelayTicks = DefaultRandomGenerator.instance.nextInt((byte)0, (byte)1);
      }

      if (!isFullBlock && this.wasBlockBelow) {
         this.postEdgeClickTicks = this.postEdgeDelayTicks;
      }

      boolean flag = (this.preEdgeLookaheadTicks >= 0 || !this.wasBlockBelow) && !isFullBlock;
      this.wasBlockBelow = isFullBlock;
      double[] doubles = PlayerUtil.predictPlayerPosition(this.preEdgeLookaheadTicks - 1, mc.thePlayer);
      if (isFullBlock && !mc.theWorld.getBlockState(doubles[0], mc.thePlayer.posY - 1.0, doubles[2]).getBlock().isFullBlock()) {
         flag = true;
      }

      if (isFullBlock && this.postEdgeClickTicks > 0) {
         flag = true;
         this.postEdgeClickTicks--;
      }

      if (!mc.gameSettings.keyBindUseItem.isKeyDown()) {
         this.firstPlaceAfterPress = true;
      }

      if (mc.gameSettings.keyBindUseItem.isKeyDown()
         && this.eagling
         && mc.isNotInGui()
         && mc.objectMouseOver != null
         && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
         && mc.thePlayer.getCurrentEquippedItem() != null
         && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock
         && mc.objectMouseOver.getBlockPos() != null) {
         if (flag || !this.clickPause.getValue()) {
            boolean firstPlaceAfterPress2 = this.firstPlaceAfterPress;
            MovingObjectPosition objectMouseOver = mc.objectMouseOver;
            this.bridgeClickController.handleDefaultBlockClick(objectMouseOver, event, firstPlaceAfterPress2);
            this.firstPlaceAfterPress = false;
         }

         event.setIgnoreVanillaClicks(true);
         event.setRightMouseDown(false);
      }

      if (event.getRightClicks() > 0) {
         this.timeHelper.reset();
      }
   }

   public boolean isPitchAboveMinimum() {
      return (double)Augustus.getInstance().getYawPitchHelper().realPitch > this.minPitchToEagle.getValue();
   }

   @Override
   public void onPostSilentSlot() {
      if (this.autoSwap.getValue() && this.fastPlace.getValue()) {
         if (!this.timeHelper.reached(350L) && this.eagling && !BlockUtil.isValidScaffoldBlock(mc.thePlayer.getHeldItem())) {
            for (int i = 0; i < 9; i++) {
               if (BlockUtil.isValidScaffoldBlock(mc.thePlayer.inventoryContainer.getSlot(i + 36).getStack())) {
                  SilentSlotManager.requestSlot(this, (byte)-2, i, true);
                  SilentSlotManager.realHotbarSlot = i;
                  break;
               }
            }
         }
      }
   }

   private boolean isExternalDcpsMode() {
      return this.dcpsMode.isVisible() && this.dcpsMode.getSelectedValue().equals("External");
   }

}
