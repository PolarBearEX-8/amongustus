package net.minecraft.tileentity;

import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityMusic extends TileEntity {
   public byte byteFlag;
   public boolean enabled2;

   public void updateState() {
      this.byteFlag = (byte)((this.byteFlag + 1) % 25);
      this.processAction5();
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      this.byteFlag = tagCompound.computeByteCode("note");
      this.byteFlag = (byte)MathHelper.getInt12(this.byteFlag, 0, 24);
   }

   public void applyWorldByWorld(World world, BlockPos blockPos) {
      if (world.getBlockState(blockPos.up()).getBlock().getMaterial() == Material.coreItemEntry) {
         Material material = world.getBlockState(blockPos.down()).getBlock().getMaterial();
         byte byteValue = 0;
         if (material == Material.material4) {
            byteValue = 1;
         }

         if (material == Material.material28) {
            byteValue = 2;
         }

         if (material == Material.material26) {
            byteValue = 3;
         }

         if (material == Material.material) {
            byteValue = 4;
         }

         world.applyBlockPosByBlockPos(blockPos, Blocks.block143, byteValue, this.byteFlag);
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      tagCompound.put("note", this.byteFlag);
   }
}
