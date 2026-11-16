package client.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

public class BlockPosRecord {
   public final BlockPos blockPos;
   public final IBlockState iBlockState;
   public final NBTTagCompound nBTTagCompound;

   public BlockPosRecord(BlockPos blockPos2, IBlockState blockState, NBTTagCompound tagCompound) {
      this.blockPos = blockPos2;
      this.iBlockState = blockState;
      this.nBTTagCompound = tagCompound;
   }
}
