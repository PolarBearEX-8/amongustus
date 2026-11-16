package cc.baritone.utils;

import client.util.IBlockState;
import java.util.List;

public class DepthFirstOptimizer implements InternalListener {
   public final InternalListener internalListener;
   public final int count;
   public final int count2;
   public final BuilderPausedSchematicsHandler builderPausedSchematicsHandler;

   public DepthFirstOptimizer(BuilderPausedSchematicsHandler builderPausedSchematicsHandler2, InternalListener internalListener2, int count3, int count4) {
      this.builderPausedSchematicsHandler = builderPausedSchematicsHandler2;
      this.internalListener = internalListener2;
      this.count = count3;
      this.count2 = count4;
   }

   @Override
   public IBlockState resolveIBlockState(int count3, int count4, int count5, IBlockState blockState, List list) {
      return this.internalListener.resolveIBlockState(count3, count4, count5, blockState, BuilderPausedSchematicsHandler.resolveList(this.builderPausedSchematicsHandler));
   }

   @Override
   public void updateState() {
      this.internalListener.updateState();
   }

   @Override
   public int getCount2() {
      return this.internalListener.getCount2();
   }

   @Override
   public boolean matchesIBlockState(int count3, int count4, int count5, IBlockState blockState) {
      return InternalListener.super.matchesIBlockState(count3, count4, count5, blockState)
         && count4 >= this.count
         && count4 <= this.count2
         && this.internalListener.matchesIBlockState(count3, count4, count5, blockState);
   }

   @Override
   public int getCount() {
      return this.internalListener.getCount();
   }

   @Override
   public int getCount3() {
      return this.internalListener.getCount3();
   }
}
