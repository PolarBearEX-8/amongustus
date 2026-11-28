package net.augustus.modules.modes.spider;

import net.augustus.events.EventBlockBoundingBox;
import net.augustus.events.listeners.BlockBoundingBoxListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import java.util.function.BiFunction;
import net.augustus.modules.movement.Spider;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.util.AxisAlignedBB;

public class BoundingBoxSpiderMode extends AbstractSpiderMode implements BlockBoundingBoxListener, TickListener {
   private final BooleanValue customMotion;
   private final RandomizedRangeSettings motion;
   private final DoubleValue motionTickDelay;
   private int motionTickCounter;

   public BoundingBoxSpiderMode(Spider spider, SettingGroup doubleValue) {
      super(spider, doubleValue, "BoundingBox");
      this.customMotion = (BooleanValue)new BooleanValue(this.parentModule, "Custom Motion", false, this.modeSetting).withVisibility(() -> this.isSelected());
      this.motion = (RandomizedRangeSettings)new RandomizedRangeSettings(this.parentModule, "Motion", 0.6, 0.6, 0.0, 1.0, 4, this.modeSetting).withVisibility(() -> this.isMotionVisible());
      this.motionTickDelay = new DoubleValue(this.parentModule, "MotionTickDelay", 0.0, 0.0, 10.0, 0, this.modeSetting).visibleWhen(() -> this.isMotionTickDelayVisible());
      this.motionTickCounter = 0;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(3);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new BoundingBoxSpiderMode((Spider)obj, (SettingGroup)obj2);
   }

   @Override
   public void onBlockBoundingBox(EventBlockBoundingBox event) {
      if (this.canShrinkBoundingBox() && event.getBoundingBox() != null && (double)event.getBlockPos().getY() >= mc.thePlayer.posY) {
         event.setBoundingBox(event.getBoundingBox().contract(1.0E-5, 0.0, 1.0E-5));
      }
   }

   @Override
   public void onTick() {
      if (this.customMotion.getValue() && this.canClimbWall()) {
         this.motionTickCounter++;
         if ((double)this.motionTickCounter > this.motionTickDelay.getValue()) {
            mc.thePlayer.motionY = (double)((float)this.motion.nextValue());
            this.motionTickCounter = 0;
         }
      } else {
         this.motionTickCounter = 0;
      }
   }

   private boolean canShrinkBoundingBox() {
      return mc.thePlayer != null && !mc.thePlayer.isOnLadder() && !mc.thePlayer.isInWater() && !mc.thePlayer.isInLava();
   }

   private boolean isMotionTickDelayVisible() {
      return this.customMotion.getValue() && this.isSelected();
   }

   private boolean isMotionVisible() {
      return this.customMotion.getValue() && this.isSelected();
   }

}
