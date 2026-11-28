package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemSeedFood extends ItemFood {
   private Block crops;
   private Block soilId;

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (facing != EnumFacing.UP) {
         return false;
      } else if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
         return false;
      } else if (world.getBlockState(pos).getBlock() == this.soilId && world.isAirBlock(pos.up())) {
         world.setBlockState(pos.up(), this.crops.getDefaultState());
         stack.stackSize--;
         return true;
      } else {
         return false;
      }
   }

   public ItemSeedFood(int n, float f, Block block, Block block2) {
      super(n, f, false);
      this.crops = block;
      this.soilId = block2;
   }
}
