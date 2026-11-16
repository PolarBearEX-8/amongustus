package net.minecraft.item;

import client.setting.AttackEffectsExplosionNormalType;
import client.util.IBlockState;
import client.util.MovingObjectType;
import client.util.StatCraftItemManager;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityEyeofendersignal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemEnderEye extends Item {
   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      IBlockState blockState = world.getBlockState(blockPos);
      if (!player.matchesBlockPos(blockPos.offset(enumFacing), enumFacing, stack)
         || blockState.getBlock() != Blocks.block52
         || (Boolean)blockState.getComparable(BlockEndPortalFrame.internalImmutableSetEntry)) {
         return false;
      } else if (world.state) {
         return true;
      } else {
         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(BlockEndPortalFrame.internalImmutableSetEntry, Boolean.TRUE), 2);
         world.applyBlockPos(blockPos, Blocks.block52);
         stack.animationsToGo--;

         for (int index = 0; index < 16; index++) {
            double offset = (double)((float)blockPos.getX() + (5.0F + random.nextFloat() * 6.0F) / 16.0F);
            double offset2 = (double)((float)blockPos.getY() + 0.8125F);
            double offset3 = (double)((float)blockPos.getZ() + (5.0F + random.nextFloat() * 6.0F) / 16.0F);
            double offset4 = 0.0;
            double offset5 = 0.0;
            double offset6 = 0.0;
            world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_NORMAL, offset, offset2, offset3, offset4, offset5, offset6, new int[0]);
         }

         EnumFacing comparable = (EnumFacing)blockState.getComparable(BlockEndPortalFrame.autoWalkPath);
         int count = 0;
         int count2 = 0;
         boolean flag = false;
         boolean flag2 = true;
         EnumFacing enumFacingAsEnumFacing = comparable.getEnumFacingAsEnumFacing();

         for (int index2 = -2; index2 <= 2; index2++) {
            BlockPos blockPos2 = blockPos.getBlockPos(enumFacingAsEnumFacing, index2);
            IBlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.getBlock() == Blocks.block52) {
               if (!(Boolean)blockState2.getComparable(BlockEndPortalFrame.internalImmutableSetEntry)) {
                  flag2 = false;
                  break;
               }

               count2 = index2;
               if (!flag) {
                  count = index2;
                  flag = true;
               }
            }
         }

         if (flag2 && count2 == count + 2) {
            BlockPos blockPos3 = blockPos.getBlockPos(comparable, 4);

            for (int index3 = count; index3 <= count2; index3++) {
               BlockPos blockPos4 = blockPos3.getBlockPos(enumFacingAsEnumFacing, index3);
               IBlockState blockState3 = world.getBlockState(blockPos4);
               if (blockState3.getBlock() != Blocks.block52 || !(Boolean)blockState3.getComparable(BlockEndPortalFrame.internalImmutableSetEntry)) {
                  flag2 = false;
                  break;
               }
            }

            for (int index4 = count - 1; index4 <= count2 + 1; index4 += 4) {
               BlockPos blockPos5 = blockPos.getBlockPos(enumFacingAsEnumFacing, index4);

               for (int index5 = 1; index5 <= 3; index5++) {
                  BlockPos blockPos6 = blockPos5.getBlockPos(comparable, index5);
                  IBlockState blockState4 = world.getBlockState(blockPos6);
                  if (blockState4.getBlock() != Blocks.block52 || !(Boolean)blockState4.getComparable(BlockEndPortalFrame.internalImmutableSetEntry)) {
                     flag2 = false;
                     break;
                  }
               }
            }

            if (flag2) {
               for (int index6 = count; index6 <= count2; index6++) {
                  BlockPos blockPos7 = blockPos.getBlockPos(enumFacingAsEnumFacing, index6);

                  for (int index7 = 1; index7 <= 3; index7++) {
                     BlockPos blockPos8 = blockPos7.getBlockPos(comparable, index7);
                     world.matchesBlockPos(blockPos8, Blocks.block2.getDefaultState(), 2);
                  }
               }
            }
         }

         return true;
      }
   }

   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      MovingObjectPosition hitResult = this.resolveMovingObjectPositionByWorld(world, player, false);
      if (hitResult != null && hitResult.typeOfHit == MovingObjectType.BLOCK && world.getBlockState(hitResult.getBlockPos()).getBlock() == Blocks.block52) {
         return stack;
      } else {
         if (!world.state) {
            BlockPos blockPos = world.resolveBlockPos("Stronghold", new BlockPos(player));
            if (blockPos != null) {
               EntityEyeofendersignal entityEyeofendersignal = new EntityEyeofendersignal(world, player.posX, player.posY, player.posZ);
               entityEyeofendersignal.applyBlockPosByBlockPos(blockPos);
               world.matchesEntity(entityEyeofendersignal);
               world.applyEntityByEntity(player, "random.bow", 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
               world.applyEntityPlayerByEntityPlayer((EntityPlayer)null, 1002, new BlockPos(player), 0);
               if (!player.invulnerableManager.enabled2) {
                  stack.animationsToGo--;
               }

               player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
            }
         }

         return stack;
      }
   }

   public ItemEnderEye() {
      this.processAction(ItemGroupManager.itemGroupManager5);
   }
}
