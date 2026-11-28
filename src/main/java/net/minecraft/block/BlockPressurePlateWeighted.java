package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockPressurePlateWeighted extends BlockBasePressurePlate {
   public static final PropertyInteger POWER;
   private final int maxWeight;

   @Override
   public IBlockState setRedstoneStrength(IBlockState state, int n) {
      return state.withProperty(POWER, n);
   }

   static {
      POWER = PropertyInteger.create("power", 0, 15);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(POWER);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{POWER});
   }

   @Override
   public int tickRate(World world) {
      return 10;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(POWER, n);
   }

   @Override
   public int computeRedstoneStrength(World world, BlockPos pos) {
      int n = Math.min(world.getEntitiesWithinAABB(Entity.class, this.getSensitiveAABB(pos)).size(), this.maxWeight);
      if (n > 0) {
         return MathHelper.ceiling_float_int(((float)Math.min(this.maxWeight, n) / (float)this.maxWeight) * 15.0F);
      } else {
         return 0;
      }
   }

   @Override
   public int getRedstoneStrength(IBlockState state) {
      return (Integer)state.getValue(POWER);
   }

   public BlockPressurePlateWeighted(Material material, int n, MapColor mapColor) {
      super(material, mapColor);
      this.setDefaultState(this.blockState.getBaseState().withProperty(POWER, 0));
      this.maxWeight = n;
   }

   public BlockPressurePlateWeighted(Material material, int n) {
      this(material, n, material.getMaterialMapColor());
   }
}
