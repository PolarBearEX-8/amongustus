package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemSeeds extends Item {
   private Block crops;
   private Block soilBlockID;

   public ItemSeeds(Block block, Block block2) {
      this.crops = block;
      this.soilBlockID = block2;
      this.setCreativeTab(CreativeTabs.tabMaterials);
   }

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (facing != EnumFacing.UP) {
         return false;
      } else if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
         return false;
      } else if (world.getBlockState(pos).getBlock() == this.soilBlockID && world.isAirBlock(pos.up())) {
         world.setBlockState(pos.up(), this.crops.getDefaultState());
         stack.stackSize--;
         return true;
      } else {
         return false;
      }
   }
}
