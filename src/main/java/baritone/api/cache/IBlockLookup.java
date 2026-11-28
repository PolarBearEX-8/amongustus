package baritone.api.cache;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;

public interface IBlockLookup {
   default IBlockState getBlock(BlockPos pos) {
      return this.getBlock(pos.getX(), pos.getY(), pos.getZ());
   }

   IBlockState getBlock(int n, int n2, int n3);
}
