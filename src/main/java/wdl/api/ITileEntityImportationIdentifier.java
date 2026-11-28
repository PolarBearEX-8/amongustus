package wdl.api;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;

public interface ITileEntityImportationIdentifier extends IWDLMod {
   boolean shouldImportTileEntity(String str, BlockPos pos, Block block, NBTTagCompound tag, Chunk chunk);
}
