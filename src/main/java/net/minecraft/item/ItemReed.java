package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemReed extends Item {
   private Block block;

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      IBlockState blockState = world.getBlockState(pos);
      Block block2 = blockState.getBlock();
      if (block2 == Blocks.snow_layer && (Integer)blockState.getValue(BlockSnow.LAYERS) < 1) {
         facing = EnumFacing.UP;
      } else if (!block2.isReplaceable(world, pos)) {
         pos = pos.offset(facing);
      }

      if (!player.canPlayerEdit(pos, facing, stack)) {
         return false;
      } else if (stack.stackSize == 0) {
         return false;
      } else {
         if (world.canBlockBePlaced(this.block, pos, false, facing, (Entity)null, stack)) {
            IBlockState state = this.block.onBlockPlaced(world, pos, facing, f, f2, f3, 0, player);
            if (world.setBlockState(pos, state, 3)) {
               state = world.getBlockState(pos);
               if (state.getBlock() == this.block) {
                  ItemBlock.setTileEntityNBT(world, player, pos, stack);
                  state.getBlock().onBlockPlacedBy(world, pos, state, player, stack);
               }

               world.playSoundEffect(
                  (double)((float)pos.getX() + 0.5F),
                  (double)((float)pos.getY() + 0.5F),
                  (double)((float)pos.getZ() + 0.5F),
                  this.block.stepSound.getPlaceSound(),
                  (this.block.stepSound.getVolume() + 1.0F) / 2.0F,
                  this.block.stepSound.getFrequency() * 0.8F
               );
               stack.stackSize--;
               return true;
            }
         }

         return false;
      }
   }

   public ItemReed(Block block2) {
      this.block = block2;
   }
}
