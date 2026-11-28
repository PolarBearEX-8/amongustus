package wdl.api;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

public interface ITileEntityEditor extends IWDLMod {
   void editTileEntity(BlockPos pos, NBTTagCompound tag, TileEntityCreationMode tileEntityCreationMode);

   boolean shouldEdit(BlockPos pos, NBTTagCompound tag, TileEntityCreationMode tileEntityCreationMode);
}
