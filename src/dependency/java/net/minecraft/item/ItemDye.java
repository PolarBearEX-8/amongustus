package net.minecraft.item;

import client.event.PreMotionFilter;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.LocalOptionType;
import client.setting.MovementProperty;
import client.util.IBlockState;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemDye extends Item {
   public static final int[] countArray = new int[]{
      1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320
   };

   public static boolean matchesItemStack(ItemStack stack, World world, BlockPos blockPos) {
      IBlockState blockState = world.getBlockState(blockPos);
      if (blockState.getBlock() instanceof PreMotionFilter) {
         PreMotionFilter block = (PreMotionFilter)blockState.getBlock();
         if (block.matchesWorld(world, blockPos, blockState, world.state)) {
            if (!world.state) {
               if (block.nextFloat(world, world.random, blockPos, blockState)) {
                  block.applyWorld(world, world.random, blockPos, blockState);
               }

               stack.animationsToGo--;
            }

            return true;
         }
      }

      return false;
   }

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, EntityLivingBase livingEntity) {
      if (livingEntity instanceof EntitySheep) {
         EntitySheep entitySheep = (EntitySheep)livingEntity;
         LocalOptionType localOptionType = LocalOptionType.resolveLocalOptionTypeByIndex(stack.getIndexOffset());
         if (!entitySheep.isActive32() && entitySheep.getLocalOptionType() != localOptionType) {
            entitySheep.applyLocalOptionType(localOptionType);
            stack.animationsToGo--;
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (int index = 0; index < 16; index++) {
         list.add(new ItemStack(item, 1, index));
      }
   }

   public static void applyWorld(World world, BlockPos blockPos, int count) {
      int count2 = count;
      if (count == 0) {
         count2 = 15;
      }

      Block block = world.getBlockState(blockPos).getBlock();
      if (block.getMaterial() != Material.coreItemEntry) {
         block.processAction(world, blockPos.getX(), blockPos.getY(), blockPos.getZ());

         for (int index = 0; index < count2; index++) {
            double offset = random.nextGaussian() * 0.02;
            double offset2 = random.nextGaussian() * 0.02;
            double offset3 = random.nextGaussian() * 0.02;
            world.applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.VILLAGER_HAPPY,
               (double)((float)blockPos.getX() + random.nextFloat()),
               (double)blockPos.getY() + (double)random.nextFloat() * block.getCoordOffset6(),
               (double)((float)blockPos.getZ() + random.nextFloat()),
               offset,
               offset2,
               offset3,
               new int[0]
            );
         }
      }
   }

   @Override
   public String getText(ItemStack stack) {
      int indexOffset = stack.getIndexOffset();
      return super.getString() + "." + LocalOptionType.resolveLocalOptionTypeByIndex(indexOffset).getLabelText();
   }

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!player.matchesBlockPos(blockPos.offset(enumFacing), enumFacing, stack)) {
         return false;
      } else {
         LocalOptionType localOptionType = LocalOptionType.resolveLocalOptionTypeByIndex(stack.getIndexOffset());
         if (localOptionType == LocalOptionType.WHITE) {
            if (matchesItemStack(stack, world, blockPos)) {
               if (!world.state) {
                  world.applyBlockPosByBlockPos(2005, blockPos, 0);
               }

               return true;
            }
         } else if (localOptionType == LocalOptionType.BROWN) {
            IBlockState blockState = world.getBlockState(blockPos);
            if (blockState.getBlock() == Blocks.block140 && blockState.getComparable(BlockPlanks.immutableSetEntry) == MovementProperty.JUNGLE) {
               if (enumFacing == EnumFacing.DOWN) {
                  return false;
               }

               if (enumFacing == EnumFacing.UP) {
                  return false;
               }

               BlockPos blockPos2 = blockPos.offset(enumFacing);
               if (world.isAirBlock(blockPos2)) {
                  IBlockState blockState2 = Blocks.block90.onBlockPlaced(world, blockPos2, enumFacing, posX, posY, posZ, 0, player);
                  world.matchesBlockPos(blockPos2, blockState2, 2);
                  if (!player.invulnerableManager.enabled2) {
                     stack.animationsToGo--;
                  }
               }

               return true;
            }
         }

         return false;
      }
   }

   public ItemDye() {
      this.processAction(true);
      this.processAction3(0);
      this.processAction(ItemGroupManager.itemGroupManager11);
   }
}
