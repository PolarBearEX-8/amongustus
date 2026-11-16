package net.minecraft.block;

import client.setting.MovementModeProperty;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class ExtraBlockAdapter extends Block {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("half", MovementModeProperty.class);

   @Override
   public void processAction2() {
      if (this.isActive5()) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      }
   }

   public abstract String resolveText(int count);

   @Override
   public int processAction2(World world, BlockPos blockPos) {
      return super.processAction2(world, blockPos) & 7;
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      if (this.isActive5()) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else {
         IBlockState iBlockState = iBlockAccess.getIBlockState(count, count2, count3);
         if (iBlockState.getBlock() == this) {
            if (iBlockState.getComparable(immutableSetEntry) == MovementModeProperty.TOP) {
               this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            }
         }
      }
   }

   @Override
   public boolean isActive() {
      return false;
   }

   public abstract Object resolveTarget(ItemStack stack);

   @Override
   public void applyWorld(World world, BlockPos blockPos, IBlockState blockState, AxisAlignedBB boundingBox, List list, Entity entity) {
      this.processAction(world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
   }

   public static boolean matchesBlockByBlock(Block block) {
      return block == Blocks.extraBlockAdapter6 || block == Blocks.extraBlockAdapter2 || block == Blocks.extraBlockAdapter4;
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      IBlockState blockState = super.onBlockPlaced(world, blockPos, enumFacing, posX, posY, posZ, count, livingEntity).resolveIBlockState(immutableSetEntry, MovementModeProperty.BOTTOM);
      return this.isActive5()
         ? blockState
         : (
            enumFacing == EnumFacing.DOWN || enumFacing != EnumFacing.UP && !((double)posY <= 0.5)
               ? blockState.resolveIBlockState(immutableSetEntry, MovementModeProperty.TOP)
               : blockState
         );
   }

   @Override
   public int processAction(Random random) {
      return this.isActive5() ? 2 : 1;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      if (this.isActive5()) {
         return super.matchesIBlockAccess(iBlockAccess, count, count2, count3, enumFacing);
      } else if (enumFacing != EnumFacing.UP && enumFacing != EnumFacing.DOWN && !super.matchesIBlockAccess(iBlockAccess, count, count2, count3, enumFacing)) {
         return false;
      } else {
         EnumFacing enumFacing2 = enumFacing.getEnumFacing5();
         int count4 = count + enumFacing2.getInt2();
         int count5 = count2 + enumFacing2.getInt3();
         int count6 = count3 + enumFacing2.getInt();
         IBlockState iBlockState = iBlockAccess.getIBlockState(count, count2, count3);
         IBlockState iBlockState2 = iBlockAccess.getIBlockState(count4, count5, count6);
         boolean flag = matchesBlockByBlock(iBlockState.getBlock()) && iBlockState.getComparable(immutableSetEntry) == MovementModeProperty.TOP;
         return matchesBlockByBlock(iBlockState2.getBlock()) && iBlockState2.getComparable(immutableSetEntry) == MovementModeProperty.TOP
            ? enumFacing == EnumFacing.DOWN
               || enumFacing == EnumFacing.UP && super.matchesIBlockAccess(iBlockAccess, count, count2, count3, enumFacing)
               || !matchesBlockByBlock(iBlockState.getBlock())
               || !flag
            : enumFacing == EnumFacing.UP
               || enumFacing == EnumFacing.DOWN && super.matchesIBlockAccess(iBlockAccess, count, count2, count3, enumFacing)
               || !matchesBlockByBlock(iBlockState.getBlock())
               || flag;
      }
   }

   public abstract IProperty getIProperty();

   @Override
   public boolean processAction14() {
      return this.isActive5();
   }

   @Override
   public boolean processAction15() {
      return this.isActive5();
   }

   public abstract boolean isActive5();

   public ExtraBlockAdapter(Material material) {
      super(material);
      if (this.isActive5()) {
         this.state18 = true;
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      }

      this.getBlockById(255);
   }
}
