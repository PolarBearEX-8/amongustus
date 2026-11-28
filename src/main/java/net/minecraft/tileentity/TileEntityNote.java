package net.minecraft.tileentity;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityNote extends TileEntity {
   public byte note;
   public boolean previousRedstoneState;

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setByte("note", this.note);
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      this.note = tag.getByte("note");
      this.note = (byte)MathHelper.clamp_int(this.note, 0, 24);
   }

   public void changePitch() {
      this.note = (byte)((this.note + 1) % 25);
      this.markDirty();
   }

   public void triggerNote(World world, BlockPos pos2) {
      if (world.getBlockState(pos2.up()).getBlock().getMaterial() == Material.air) {
         Material material = world.getBlockState(pos2.down()).getBlock().getMaterial();
         byte b = 0;
         if (material == Material.rock) {
            b = 1;
         }

         if (material == Material.sand) {
            b = 2;
         }

         if (material == Material.glass) {
            b = 3;
         }

         if (material == Material.wood) {
            b = 4;
         }

         world.addBlockEvent(pos2, Blocks.noteblock, b, this.note);
      }
   }
}
