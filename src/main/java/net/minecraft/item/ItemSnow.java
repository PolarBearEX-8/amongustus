package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemSnow extends ItemBlock {
   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (stack.stackSize == 0) {
         return false;
      } else if (!player.canPlayerEdit(pos, facing, stack)) {
         return false;
      } else {
         IBlockState blockState = world.getBlockState(pos);
         Block block2 = blockState.getBlock();
         BlockPos pos2 = pos;
         if ((facing != EnumFacing.UP || block2 != this.block) && !block2.isReplaceable(world, pos)) {
            pos2 = pos.offset(facing);
            blockState = world.getBlockState(pos2);
            block2 = blockState.getBlock();
         }

         if (block2 == this.block) {
            int value = (Integer)blockState.getValue(BlockSnow.LAYERS);
            if (value <= 7) {
               IBlockState state = blockState.withProperty(BlockSnow.LAYERS, value + 1);
               AxisAlignedBB collisionBoundingBox = this.block.getCollisionBoundingBox(world, state, pos2.getX(), pos2.getY(), pos2.getZ());
               if (collisionBoundingBox != null && world.checkNoEntityCollision(collisionBoundingBox) && world.setBlockState(pos2, state, 2)) {
                  world.playSoundEffect(
                     (double)((float)pos2.getX() + 0.5F),
                     (double)((float)pos2.getY() + 0.5F),
                     (double)((float)pos2.getZ() + 0.5F),
                     this.block.stepSound.getPlaceSound(),
                     (this.block.stepSound.getVolume() + 1.0F) / 2.0F,
                     this.block.stepSound.getFrequency() * 0.8F
                  );
                  stack.stackSize--;
                  return true;
               }
            }
         }

         return super.onItemUse(stack, player, world, pos2, facing, f, f2, f3);
      }
   }

   public ItemSnow(Block block2) {
      super(block2);
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   @Override
   public int getMetadata(int n) {
      return n;
   }
}
