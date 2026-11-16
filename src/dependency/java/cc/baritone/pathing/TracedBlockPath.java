package cc.baritone.pathing;

import client.util.MinecraftAccessor;
import client.util.TimeHelper;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.BlockPos;

public class TracedBlockPath implements Comparable, MinecraftAccessor {
   public float factor;
   public final List list = new ArrayList();
   public TracedBlockPath tracedBlockPath;
   public final PathPoint pathPoint;
   public float factor2;
   public float factor3;
   public final int count;
   public float factor4;

   public List getList() {
      return this.list;
   }

   public float getFactor() {
      return this.factor;
   }

   public TracedBlockPath(PathPoint pathPoint2, float factor5, float factor6, TracedBlockPath tracedBlockPath2, int count2) {
      this.factor2 = factor6;
      this.pathPoint = pathPoint2;
      this.count = count2;
      this.factor3 = MinecraftAccessor.minecraft.theWorld.getBlockState(pathPoint2.getBlockPos()).getBlock().processAction8(null, null);
      this.factor = factor5;
      this.tracedBlockPath = tracedBlockPath2;
      this.list.clear();
      this.factor4 = factor5 + factor6;
      this.applyIndex(1, 0, 0);
      this.applyIndex(-1, 0, 0);
      this.applyIndex(0, 0, 1);
      this.applyIndex(0, 0, -1);
      this.applyIndex(0, 1, 0);
      this.applyIndex(0, -1, 0);
   }

   public float getFactor4() {
      return this.factor4;
   }

   private void applyIndex(int count2, int count3, int count4) {
      BlockPos blockPos = this.pathPoint.getBlockPos().add(count2, count3, count4);
      if (blockPos.getY() >= this.count) {
         if (!blockPos.checkBlockPos2(this.pathPoint.getBlockPos())) {
            this.list.add(new PathPoint(blockPos));
         }
      }
   }

   public PathPoint getPathPoint() {
      return this.pathPoint;
   }

   public TracedBlockPath getTracedBlockPath() {
      return this.tracedBlockPath;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public void applyFloatByFloat(float factor5) {
      this.factor2 = factor5;
      this.factor4 = this.factor + factor5;
   }

   public void setTracedBlockPath(TracedBlockPath tracedBlockPath2) {
      this.tracedBlockPath = tracedBlockPath2;
   }

   @Override
   public int compareTo(Object entry) {
      if (entry instanceof TracedBlockPath) {
         if (((TracedBlockPath)entry).getFactor() > this.getFactor()) {
            return -2;
         } else {
            return ((TracedBlockPath)entry).getFactor() < this.getFactor()
               ? 2
               : Double.compare((double)this.getFactor2(), (double)((TracedBlockPath)entry).getFactor2());
         }
      } else {
         return 0;
      }
   }

   public void applyFloat(float factor5) {
      this.factor = factor5;
      this.factor4 = factor5 + this.factor2;
   }
}
