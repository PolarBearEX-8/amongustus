package cc.baritone.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

public interface BlockPosSpecialListener extends SpecialListener {
   boolean matchesBlockPos(BlockPos blockPos, NBTTagCompound tagCompound, EXISTINGIMPORTEDEnum eXISTINGIMPORTEDEnum);

   void applyBlockPos(BlockPos blockPos, NBTTagCompound tagCompound, EXISTINGIMPORTEDEnum eXISTINGIMPORTEDEnum);
}
