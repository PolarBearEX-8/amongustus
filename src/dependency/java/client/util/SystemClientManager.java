package client.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.DiamondShovel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class SystemClientManager extends DiamondShovel {
   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      MovingObjectPosition hitResult = this.resolveMovingObjectPositionByWorld(world, player, true);
      if (hitResult == null) {
         return stack;
      } else {
         if (hitResult.typeOfHit == MovingObjectType.BLOCK) {
            BlockPos blockPos = hitResult.getBlockPos();
            if (!world.matchesEntityPlayer(player, blockPos)) {
               return stack;
            }

            if (!player.matchesBlockPos(blockPos.offset(hitResult.sideHit), hitResult.sideHit, stack)) {
               return stack;
            }

            BlockPos blockPos2 = blockPos.up();
            IBlockState blockState = world.getBlockState(blockPos);
            if (blockState.getBlock().getMaterial() == Material.coreItemEntry1
               && (Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry) == 0
               && world.isAirBlock(blockPos2)) {
               world.matchesBlockPos(blockPos2, Blocks.block33.getDefaultState());
               if (!player.invulnerableManager.enabled2) {
                  stack.animationsToGo--;
               }

               player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
            }
         }

         return stack;
      }
   }

   @Override
   public int processAction(ItemStack stack, int count) {
      return Blocks.block33.processAction2(Blocks.block33.processAction(stack.getIndexOffset()));
   }

   public SystemClientManager(Block block) {
      super(block, false);
   }
}
