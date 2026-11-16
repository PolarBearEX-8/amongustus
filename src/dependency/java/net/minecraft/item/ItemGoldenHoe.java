package net.minecraft.item;

import client.setting.GlobalOptionType;
import client.setting.MovementOffsetProperty;
import client.util.CurveFormatter;
import client.util.IBlockState;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemGoldenHoe extends Item {
   public GlobalOptionType globalOptionType;

   @Override
   public boolean isState4() {
      return true;
   }

   public String getText() {
      return this.globalOptionType.toString();
   }

   public boolean matchesItemStackByItemStack(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, IBlockState blockState) {
      world.applyText(
         (double)((float)blockPos.getX() + 0.5F),
         (double)((float)blockPos.getY() + 0.5F),
         (double)((float)blockPos.getZ() + 0.5F),
         blockState.getBlock().stepManager20.getText(),
         (blockState.getBlock().stepManager20.getFactor2() + 1.0F) / 2.0F,
         blockState.getBlock().stepManager20.getFactor() * 0.8F
      );
      if (world.state) {
         return true;
      } else {
         world.matchesBlockPos(blockPos, blockState);
         stack.applyEntityLivingBaseByEntityLivingBase(1, player);
         return true;
      }
   }

   public GlobalOptionType getGlobalOptionType() {
      return this.globalOptionType;
   }

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!player.matchesBlockPos(blockPos.offset(enumFacing), enumFacing, stack)) {
         return false;
      } else {
         IBlockState blockState = world.getBlockState(blockPos);
         Block block = blockState.getBlock();
         if (enumFacing != EnumFacing.DOWN && world.getBlockState(blockPos.up()).getBlock().getMaterial() == Material.coreItemEntry) {
            if (block == Blocks.blockGrass) {
               return this.matchesItemStackByItemStack(stack, player, world, blockPos, Blocks.block7.getDefaultState());
            }

            if (block == Blocks.block115) {
               switch (CurveFormatter.countArray[((MovementOffsetProperty)blockState.getComparable(BlockDirt.immutableSetEntry)).ordinal()]) {
                  case 1:
                     return this.matchesItemStackByItemStack(stack, player, world, blockPos, Blocks.block7.getDefaultState());
                  case 2:
                     return this.matchesItemStackByItemStack(
                        stack, player, world, blockPos, Blocks.block115.getDefaultState().resolveIBlockState(BlockDirt.immutableSetEntry, MovementOffsetProperty.DIRT)
                     );
               }
            }
         }

         return false;
      }
   }

   public ItemGoldenHoe(GlobalOptionType globalOptionType2) {
      this.globalOptionType = globalOptionType2;
      this.indexOffset1 = 1;
      this.processAction3(globalOptionType2.getCount3());
      this.processAction(ItemGroupManager.itemGroupManager2);
   }
}
