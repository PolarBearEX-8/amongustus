package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemHoe extends Item {
   public Item.ToolMaterial theToolMaterial;

   public boolean useHoe(ItemStack stack, EntityPlayer player, World world, BlockPos pos, IBlockState state) {
      world.playSoundEffect(
         (double)((float)pos.getX() + 0.5F),
         (double)((float)pos.getY() + 0.5F),
         (double)((float)pos.getZ() + 0.5F),
         state.getBlock().stepSound.getStepSound(),
         (state.getBlock().stepSound.getVolume() + 1.0F) / 2.0F,
         state.getBlock().stepSound.getFrequency() * 0.8F
      );
      if (world.isRemote) {
         return true;
      } else {
         world.setBlockState(pos, state);
         stack.damageItem(1, player);
         return true;
      }
   }

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
         return false;
      } else {
         IBlockState blockState = world.getBlockState(pos);
         Block block = blockState.getBlock();
         if (facing != EnumFacing.DOWN && world.getBlockState(pos.up()).getBlock().getMaterial() == Material.air) {
            if (block == Blocks.grass) {
               return this.useHoe(stack, player, world, pos, Blocks.farmland.getDefaultState());
            }

            if (block == Blocks.dirt) {
               switch (((BlockDirt.DirtType)blockState.getValue(BlockDirt.VARIANT))) {
                  case DIRT:
                     return this.useHoe(stack, player, world, pos, Blocks.farmland.getDefaultState());
                  case COARSE_DIRT:
                     return this.useHoe(stack, player, world, pos, Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
               }
            }
         }

         return false;
      }
   }

   public String getMaterialName() {
      return this.theToolMaterial.toString();
   }

   @Override
   public boolean isFull3D() {
      return true;
   }

   public ItemHoe(Item.ToolMaterial toolMaterial) {
      this.theToolMaterial = toolMaterial;
      this.maxStackSize = 1;
      this.setMaxDamage(toolMaterial.getMaxUses());
      this.setCreativeTab(CreativeTabs.tabTools);
   }

   public Item.ToolMaterial getTheToolMaterial() {
      return this.theToolMaterial;
   }

}
