package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockSlab extends Block {
   public static final PropertyEnum HALF;

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      if (this.isDouble()) {
         return super.shouldSideBeRendered(world, n, n2, n3, facing);
      } else if (facing != EnumFacing.UP && facing != EnumFacing.DOWN && !super.shouldSideBeRendered(world, n, n2, n3, facing)) {
         return false;
      } else {
         EnumFacing opposite = facing.getOpposite();
         int n4 = n + opposite.getFrontOffsetX();
         int n5 = n2 + opposite.getFrontOffsetY();
         int n6 = n3 + opposite.getFrontOffsetZ();
         IBlockState blockState2 = world.getBlockState2(n, n2, n3);
         IBlockState blockState22 = world.getBlockState2(n4, n5, n6);
         boolean flag = isSlab(blockState2.getBlock()) && blockState2.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP;
         return (isSlab(blockState22.getBlock()) && blockState22.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
            ? facing == EnumFacing.DOWN || facing == EnumFacing.UP && super.shouldSideBeRendered(world, n, n2, n3, facing) || !isSlab(blockState2.getBlock()) || !flag
            : facing == EnumFacing.UP || facing == EnumFacing.DOWN && super.shouldSideBeRendered(world, n, n2, n3, facing) || !isSlab(blockState2.getBlock()) || flag;
      }
   }

   public abstract Object getVariant(ItemStack stack);

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      if (this.isDouble()) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else {
         IBlockState blockState2 = world.getBlockState2(n, n2, n3);
         if (blockState2.getBlock() == this) {
            if (blockState2.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP) {
               this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            }
         }
      }
   }

   static {
      HALF = PropertyEnum.create("half", BlockSlab.EnumBlockHalf.class);
   }

   public BlockSlab(Material material) {
      super(material);
      if (this.isDouble()) {
         this.fullBlock = true;
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      }

      this.setLightOpacity(255);
   }

   public static boolean isSlab(Block block) {
      return block == Blocks.stone_slab || block == Blocks.wooden_slab || block == Blocks.stone_slab2;
   }

   @Override
   public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB box, List list, Entity entity) {
      this.setBlockBoundsBasedOnState(world, pos.getX(), pos.getY(), pos.getZ());
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
   }

   public abstract String getUnlocalizedName(int n);

   public abstract IProperty getVariantProperty();

   @Override
   public boolean isFullCube() {
      return this.isDouble();
   }

   @Override
   public int quantityDropped(Random rand) {
      return this.isDouble() ? 2 : 1;
   }

   @Override
   public void setBlockBoundsForItemRender() {
      if (this.isDouble()) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      }
   }

   @Override
   public int getDamageValue(World world, BlockPos pos) {
      return super.getDamageValue(world, pos) & 7;
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      IBlockState state = super.onBlockPlaced(world, pos, facing, f, f2, f3, n, entity).withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
      return this.isDouble()
         ? state
         : (facing == EnumFacing.DOWN || facing != EnumFacing.UP && !((double)f2 <= 0.5) ? state.withProperty(HALF, BlockSlab.EnumBlockHalf.TOP) : state);
   }

   public abstract boolean isDouble();

   @Override
   public boolean isOpaqueCube() {
      return this.isDouble();
   }

   @Override
   public boolean canSilkHarvest() {
      return false;
   }


   public enum EnumBlockHalf implements IStringSerializable {
      TOP("top"),
      BOTTOM("bottom");

   private final String name;
   private EnumBlockHalf(String str) {
         this.name = str;
      }

      @Override
      public String toString() {
         return this.name;
      }

      @Override
      public String getName() {
         return this.name;
      }
   }
}
