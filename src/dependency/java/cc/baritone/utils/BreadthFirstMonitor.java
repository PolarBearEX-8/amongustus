package cc.baritone.utils;

import cc.baritone.command.SelectionCommand;
import client.util.IBlockState;
import net.minecraft.util.Vec3i;

public class BreadthFirstMonitor extends IBlockStateInternalListen {
   public final IBlockState[][][] iBlockStateArray2;
   public final Vec3i vec3i;
   public final SelectionCommand selectionCommand;

   public BreadthFirstMonitor(SelectionCommand selectionCommand2, IBlockState[][][] blockStateMatrix, Vec3i vec) {
      this.selectionCommand = selectionCommand2;
      this.iBlockStateArray2 = blockStateMatrix;
      this.vec3i = vec;
      this.iBlockStateArray = this.iBlockStateArray2;
      this.count = this.vec3i.getX();
      this.count2 = this.vec3i.getY();
      this.count3 = this.vec3i.getZ();
   }
}
