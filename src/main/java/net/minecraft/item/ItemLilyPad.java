package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemLilyPad extends ItemColored {
   public ItemLilyPad(Block block2) {
      super(block2, false);
   }

   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      MovingObjectPosition hit = this.getMovingObjectPositionFromPlayer(world, player, true);
      if (hit == null) {
         return stack;
      } else {
         if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            BlockPos blockPos = hit.getBlockPos();
            if (!world.isBlockModifiable(player, blockPos)) {
               return stack;
            }

            if (!player.canPlayerEdit(blockPos.offset(hit.sideHit), hit.sideHit, stack)) {
               return stack;
            }

            BlockPos pos = blockPos.up();
            IBlockState blockState = world.getBlockState(blockPos);
            if (blockState.getBlock().getMaterial() == Material.water && (Integer)blockState.getValue(BlockLiquid.LEVEL) == 0 && world.isAirBlock(pos)) {
               world.setBlockState(pos, Blocks.waterlily.getDefaultState());
               if (!player.capabilities.isCreativeMode) {
                  stack.stackSize--;
               }

               player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
            }
         }

         return stack;
      }
   }

   @Override
   public int getColorFromItemStack(ItemStack stack, int n) {
      return Blocks.waterlily.getRenderColor(Blocks.waterlily.getStateFromMeta(stack.getMetadata()));
   }
}
