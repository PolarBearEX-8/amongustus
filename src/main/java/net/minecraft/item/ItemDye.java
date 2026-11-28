package net.minecraft.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ItemDye extends Item {
   public static final int[] dyeColors = new int[]{
      1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320
   };

   public ItemDye() {
      this.setHasSubtypes(true);
      this.setMaxDamage(0);
      this.setCreativeTab(CreativeTabs.tabMaterials);
   }

   @Override
   public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity) {
      if (entity instanceof EntitySheep) {
         EntitySheep entitySheep = (EntitySheep)entity;
         EnumDyeColor enumDyeColor = EnumDyeColor.byDyeDamage(stack.getMetadata());
         if (!entitySheep.getSheared() && entitySheep.getFleeceColor() != enumDyeColor) {
            entitySheep.setFleeceColor(enumDyeColor);
            stack.stackSize--;
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public String getUnlocalizedName(ItemStack stack) {
      int metadata = stack.getMetadata();
      return super.getUnlocalizedName() + "." + EnumDyeColor.byDyeDamage(metadata).getUnlocalizedName();
   }

   public static void spawnBonemealParticles(World world, BlockPos pos, int n) {
      if (n == 0) {
         n = 15;
      }

      Block block = world.getBlockState(pos).getBlock();
      if (block.getMaterial() != Material.air) {
         block.setBlockBoundsBasedOnState(world, pos.getX(), pos.getY(), pos.getZ());

         for (int i = 0; i < n; i++) {
            double d = itemRand.nextGaussian() * 0.02;
            double d2 = itemRand.nextGaussian() * 0.02;
            double d3 = itemRand.nextGaussian() * 0.02;
            world.spawnParticle(
               EnumParticleTypes.VILLAGER_HAPPY,
               (double)((float)pos.getX() + itemRand.nextFloat()),
               (double)pos.getY() + (double)itemRand.nextFloat() * block.getBlockBoundsMaxY(),
               (double)((float)pos.getZ() + itemRand.nextFloat()),
               d,
               d2,
               d3,
               new int[0]
            );
         }
      }
   }

   @Override
   public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
      for (int i = 0; i < 16; i++) {
         list.add(new ItemStack(item, 1, i));
      }
   }

   public static boolean applyBonemeal(ItemStack stack, World world, BlockPos pos) {
      IBlockState blockState = world.getBlockState(pos);
      if (blockState.getBlock() instanceof IGrowable) {
         IGrowable block = (IGrowable)blockState.getBlock();
         if (block.canGrow(world, pos, blockState, world.isRemote)) {
            if (!world.isRemote) {
               if (block.canUseBonemeal(world, world.rand, pos, blockState)) {
                  block.grow(world, world.rand, pos, blockState);
               }

               stack.stackSize--;
            }

            return true;
         }
      }

      return false;
   }

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
         return false;
      } else {
         EnumDyeColor enumDyeColor = EnumDyeColor.byDyeDamage(stack.getMetadata());
         if (enumDyeColor == EnumDyeColor.WHITE) {
            if (applyBonemeal(stack, world, pos)) {
               if (!world.isRemote) {
                  world.playAuxSFX(2005, pos, 0);
               }

               return true;
            }
         } else if (enumDyeColor == EnumDyeColor.BROWN) {
            IBlockState blockState = world.getBlockState(pos);
            if (blockState.getBlock() == Blocks.log && blockState.getValue(BlockPlanks.VARIANT) == BlockPlanks.EnumType.JUNGLE) {
               if (facing == EnumFacing.DOWN) {
                  return false;
               }

               if (facing == EnumFacing.UP) {
                  return false;
               }

               pos = pos.offset(facing);
               if (world.isAirBlock(pos)) {
                  world.setBlockState(pos, Blocks.cocoa.onBlockPlaced(world, pos, facing, f, f2, f3, 0, player), 2);
                  if (!player.capabilities.isCreativeMode) {
                     stack.stackSize--;
                  }
               }

               return true;
            }
         }

         return false;
      }
   }
}
