package cc.baritone.utils;

import cc.baritone.api.INACTIVEHandler;
import net.minecraft.util.BlockPos;

public interface CustomListener extends INACTIVEHandler {
   default void updateState2() {
      this.applyBlockPos(0, null);
   }

   void applyBlockPos(int count, BlockPos blockPos);

   default void applyIndex(int count) {
      this.applyBlockPos(count, null);
   }
}
