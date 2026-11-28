package net.minecraft.item;

import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemEnderEye extends Item {

   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      MovingObjectPosition hit = this.getMovingObjectPositionFromPlayer(world, player, false);
      if (hit != null
         && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
         && world.getBlockState(hit.getBlockPos()).getBlock() == Blocks.end_portal_frame) {
         return stack;
      } else {
         if (!world.isRemote) {
            BlockPos strongholdPos = world.getStrongholdPos("Stronghold", new BlockPos(player));
            if (strongholdPos != null) {
               EntityEnderEye entityEnderEye = new EntityEnderEye(world, player.posX, player.posY, player.posZ);
               entityEnderEye.moveTowards(strongholdPos);
               world.spawnEntityInWorld(entityEnderEye);
               world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
               world.playAuxSFXAtEntity((EntityPlayer)null, 1002, new BlockPos(player), 0);
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
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      IBlockState blockState = world.getBlockState(pos);
      if (!player.canPlayerEdit(pos.offset(facing), facing, stack) || blockState.getBlock() != Blocks.end_portal_frame || (Boolean)blockState.getValue(BlockEndPortalFrame.EYE)) {
         return false;
      } else if (world.isRemote) {
         return true;
      } else {
         world.setBlockState(pos, blockState.withProperty(BlockEndPortalFrame.EYE, Boolean.TRUE), 2);
         world.updateComparatorOutputLevel(pos, Blocks.end_portal_frame);
         stack.stackSize--;

         for (int i = 0; i < 16; i++) {
            double d = (double)((float)pos.getX() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F);
            double d2 = (double)((float)pos.getY() + 0.8125F);
            double d3 = (double)((float)pos.getZ() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F);
            double d4 = 0.0;
            double d5 = 0.0;
            double d6 = 0.0;
            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d, d2, d3, d4, d5, d6, new int[0]);
         }

         EnumFacing value = (EnumFacing)blockState.getValue(BlockEndPortalFrame.FACING);
         int n = 0;
         int n2 = 0;
         boolean flag = false;
         boolean flag2 = true;
         EnumFacing facing2 = value.rotateY();

         for (int j = -2; j <= 2; j++) {
            IBlockState blockState2 = world.getBlockState(pos.offset(facing2, j));
            if (blockState2.getBlock() == Blocks.end_portal_frame) {
               if (!(Boolean)blockState2.getValue(BlockEndPortalFrame.EYE)) {
                  flag2 = false;
                  break;
               }

               n2 = j;
               if (!flag) {
                  n = j;
                  flag = true;
               }
            }
         }

         if (flag2 && n2 == n + 2) {
            BlockPos pos3 = pos.offset(value, 4);

            for (int k = n; k <= n2; k++) {
               IBlockState blockState3 = world.getBlockState(pos3.offset(facing2, k));
               if (blockState3.getBlock() != Blocks.end_portal_frame || !(Boolean)blockState3.getValue(BlockEndPortalFrame.EYE)) {
                  flag2 = false;
                  break;
               }
            }

            for (int m = n - 1; m <= n2 + 1; m += 4) {
               pos3 = pos.offset(facing2, m);

               for (int idx = 1; idx <= 3; idx++) {
                  IBlockState blockState4 = world.getBlockState(pos3.offset(value, idx));
                  if (blockState4.getBlock() != Blocks.end_portal_frame || !(Boolean)blockState4.getValue(BlockEndPortalFrame.EYE)) {
                     flag2 = false;
                     break;
                  }
               }
            }

            if (flag2) {
               for (int index = n; index <= n2; index++) {
                  pos3 = pos.offset(facing2, index);

                  for (int i2 = 1; i2 <= 3; i2++) {
                     world.setBlockState(pos3.offset(value, i2), Blocks.end_portal.getDefaultState(), 2);
                  }
               }
            }
         }

         return true;
      }
   }

   public ItemEnderEye() {
      this.setCreativeTab(CreativeTabs.tabMisc);
   }
}
