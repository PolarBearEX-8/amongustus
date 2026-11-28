package net.augustus.modules.modes.autoeagle;

import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.MoveUtil;
import java.util.function.BiFunction;
import net.augustus.modules.legit.AutoEagle;
import net.augustus.settings.SettingGroup;
import net.minecraft.util.AxisAlignedBB;

public class EagleAutoEagleMode extends AutoEagleMode implements MoveInputListener {
   private final RandomizedRangeSettings minSneakTicks;
   private final RandomizedRangeSettings startDistToBlockEagle;
   private int ticksSinceEdge;
   private boolean wasSneaking;

   public EagleAutoEagleMode(AutoEagle autoEagle, SettingGroup doubleValue) {
       super(autoEagle, doubleValue, "Eagle");
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(this.parentModule, "MinSneakTicks", 1.0, 2.0, 1.0, 5.0, 0, this.modeSetting)).withoutNotInARow();
      double d = 25.0;
      double d2 = 15.0;
      double d3 = 2.0;
      double d4 = 1.0;
      double d5 = 3.0;
      randomizedRangeSettings = randomizedRangeSettings.withOutliers(3.0, d5, d4, d3, d2, d);
      this.minSneakTicks = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isSelected());
      randomizedRangeSettings = (new RandomizedRangeSettings(this.parentModule, "StartDistToBlockEagle", 0.05, 0.2, -0.2, 0.5, 2, this.modeSetting)).withoutNotInARow();
      randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("StartDistToBlock");
      this.startDistToBlockEagle = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isSelected());
      this.ticksSinceEdge = 0;
      this.wasSneaking = false;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(21);
   }

   @Override
   public void onEnable() {
      this.minSneakTicks.nextValue();
      this.ticksSinceEdge = 0;
      this.wasSneaking = false;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (!((AutoEagle)this.parentModule).isPitchAboveMinimum()) {
         ((AutoEagle)this.parentModule).eagling = false;
      } else {
         if (!((AutoEagle)this.parentModule).onlyWhileSneak.getValue()) {
            event.setSneak(true);
         }

         ((AutoEagle)this.parentModule).eagling = ((AutoEagle)this.parentModule).fastPlace.getValue() && event.isSneak() && ((AutoEagle)this.parentModule).isPitchAboveMinimum();
         if (event.isSneak() || !((AutoEagle)this.parentModule).onlyWhileSneak.getValue()) {
            this.handleEdgeSneak(event);
         }

         if (!event.isSneak() && this.wasSneaking) {
            this.startDistToBlockEagle.nextValue();
            this.minSneakTicks.nextValue();
         }

         this.wasSneaking = event.isSneak();
      }
   }

   private void handleEdgeSneak(EventMoveInput event) {
      if (MoveUtil.isMoving()) {
         boolean flag = false;

         for (AxisAlignedBB box : (Iterable<AxisAlignedBB>)(mc.theWorld.getCollidingBoundingBoxesCapped(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0.0, -1.0, 0.0)))) {
            if (mc.thePlayer
               .getEntityBoundingBox()
               .expand(-0.3, 0.0, -0.3)
               .offset(0.0, -1.0, 0.0)
               .intersectsWith(
                  box.expand(
                     Math.abs(mc.thePlayer.motionX) > 0.02 ? -this.startDistToBlockEagle.getLastValue() : 0.0, 0.0, Math.abs(mc.thePlayer.motionZ) > 0.02 ? -this.startDistToBlockEagle.getLastValue() : 0.0
                  )
               )) {
               flag = true;
               break;
            }
         }

         if (event.isSneak() && !flag) {
            event.setSneak(true);
            this.ticksSinceEdge = 0;
         } else if (this.ticksSinceEdge >= this.minSneakTicks.getLastIntValue() && mc.gameSettings.keyBindUseItem.isKeyDown()) {
            event.setSneak(false);
         }

         this.ticksSinceEdge++;
      }
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new EagleAutoEagleMode((AutoEagle)obj, (SettingGroup)obj2);
   }

}
