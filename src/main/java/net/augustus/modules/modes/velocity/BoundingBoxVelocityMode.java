package net.augustus.modules.modes.velocity;

import net.augustus.events.EventBlockBoundingBox;
import net.augustus.events.listeners.BlockBoundingBoxListener;
import net.augustus.settings.RangeSetting;
import net.augustus.settings.SettingVisibilityCondition;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.block.BlockAir;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class BoundingBoxVelocityMode extends VelocityMode implements BlockBoundingBoxListener {
   private final RangeSetting endBoundingBoxHurtTime;
   private final DoubleValue boundingBoxHeight;

   public BoundingBoxVelocityMode(Velocity velocity, SettingGroup doubleValue) {
      super(velocity, doubleValue, "BoundingBox");
      RangeSetting rangeSetting = new RangeSetting(this.parentModule, "EndBoundingBoxHurtTime", "StartBoundingBoxHurtTime", 0.0, 10.0, 0.0, 10.0, 0, this.modeSetting);
      String str = "StartHurtTime";
      this.endBoundingBoxHurtTime = rangeSetting.withBoundNames("EndHurtTime", str).withMaxFirst().visibleWhen(() -> this.isSelected());
      this.boundingBoxHeight = new DoubleValue(this.parentModule, "BoundingBoxHeight", 1.0, 0.0, 1.0, 2, this.modeSetting).visibleWhen(() -> this.isSelected());
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(3);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new BoundingBoxVelocityMode((Velocity)obj, (SettingGroup)obj2);
   }

   @Override
   public void onBlockBoundingBox(EventBlockBoundingBox event) {
      if (this.canApplyVelocity() && mc.thePlayer != null) {
         if (!(((double)mc.thePlayer.hurtTime) > this.endBoundingBoxHurtTime.getMaxValue()) && !((double)mc.thePlayer.hurtTime < this.endBoundingBoxHurtTime.getMinValue() + 1.0) && event.getBlock() instanceof BlockAir) {
            BlockPos blockPos = event.getBlockPos();
            if (blockPos.getY() != (int)mc.thePlayer.posY + 1) {
               return;
            }

            AxisAlignedBB box = new AxisAlignedBB(
               (double)blockPos.getX(),
               (double)blockPos.getY(),
               (double)blockPos.getZ(),
               (double)(blockPos.getX() + 1),
               (double)blockPos.getY() + this.boundingBoxHeight.getValue(),
               (double)(blockPos.getZ() + 1)
            );
            event.setBoundingBox(box);
            return;
         }
      }
   }

}
