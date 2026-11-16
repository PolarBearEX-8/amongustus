package client.event;

import net.minecraft.util.BlockPos;

public class EventMoveController {
   public EventMove eventMove;
   public int count;
   public BlockPos blockPos;
   public float factor;
   public float factor2;

   public float getFactor() {
      return this.factor;
   }

   public EventMove getEventMove() {
      return this.eventMove;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public EventMoveController(EventMove event, float factor3, float factor4, BlockPos blockPos2, int count2) {
      this.eventMove = event;
      this.factor = factor3;
      this.factor2 = factor4;
      this.blockPos = blockPos2;
      this.count = count2;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public void setCount(int count2) {
      this.count = count2;
   }

   public void setEventMove(EventMove eventMove2) {
      this.eventMove = eventMove2;
   }

   public void setFactor2(float factor3) {
      this.factor2 = factor3;
   }

   public void setBlockPos(BlockPos blockPos2) {
      this.blockPos = blockPos2;
   }

   public void setFactor(float factor3) {
      this.factor = factor3;
   }

   public int getCount() {
      return this.count;
   }
}
