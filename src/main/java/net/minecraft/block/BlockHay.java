package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockHay extends BlockRotatedPillar {
   @Override
   public ItemStack createStackedBlock(IBlockState state) {
      return new ItemStack(Item.getItemFromBlock(this), 1, 0);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      byte b = 0;
      EnumFacing.Axis value = (EnumFacing.Axis)state.getValue(AXIS);
      if (value == EnumFacing.Axis.X) {
         b |= 4;
      } else if (value == EnumFacing.Axis.Z) {
         b |= 8;
      }

      return b;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{AXIS});
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      return super.onBlockPlaced(world, pos, facing, f, f2, f3, n, entity).withProperty(AXIS, facing.getAxis());
   }

   public BlockHay() {
      super(Material.grass, MapColor.yellowColor);
      this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.Y));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      EnumFacing.Axis axis = EnumFacing.Axis.Y;
      int n2 = n & 12;
      if (n2 == 4) {
         axis = EnumFacing.Axis.X;
      } else if (n2 == 8) {
         axis = EnumFacing.Axis.Z;
      }

      return this.getDefaultState().withProperty(AXIS, axis);
   }
}
