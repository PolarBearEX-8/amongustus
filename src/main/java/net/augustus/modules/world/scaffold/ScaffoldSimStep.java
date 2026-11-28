package net.augustus.modules.world.scaffold;

import net.augustus.events.EventMoveInput;
import net.minecraft.util.BlockPos;

public class ScaffoldSimStep {
   private float strafeYawOffset;
   private EventMoveInput moveInput;
   private float yaw;
   private BlockPos targetBlockPos;
   private int offGroundTicks;

   public void setOffGroundTicks(int n) {
      this.offGroundTicks = n;
   }

   public void setYaw(float f) {
      this.yaw = f;
   }

   public void setMoveInput(EventMoveInput event) {
      this.moveInput = event;
   }

   public BlockPos getTargetBlockPos() {
      return this.targetBlockPos;
   }

   public float getYaw() {
      return this.yaw;
   }

   public float getStrafeYawOffset() {
      return this.strafeYawOffset;
   }

   public void setStrafeYawOffset(float f) {
      this.strafeYawOffset = f;
   }

   public EventMoveInput getMoveInput() {
      return this.moveInput;
   }

   public ScaffoldSimStep(EventMoveInput event, float f, float f2, BlockPos pos, int n) {
      this.moveInput = event;
      this.yaw = f;
      this.strafeYawOffset = f2;
      this.targetBlockPos = pos;
      this.offGroundTicks = n;
   }

   public int getOffGroundTicks() {
      return this.offGroundTicks;
   }

   public void setTargetBlockPos(BlockPos pos) {
      this.targetBlockPos = pos;
   }
}
