package net.minecraft.block;

import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class TertiaryBlockAdapter extends Block {
   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (!world.state) {
         int count = this.computeCount(blockState);
         if (count > 0) {
            this.applyWorldByWorld(world, blockPos, blockState, count);
         }
      }
   }

   @Override
   public void processAction2() {
      this.setBlockBounds(0.0F, 0.375F, 0.0F, 1.0F, 0.625F, 1.0F);
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      this.applyIBlockState(iBlockAccess.getIBlockState(count, count2, count3));
   }

   @Override
   public void processAction2(World world, BlockPos blockPos, IBlockState blockState, Random random) {
   }

   private boolean matchesWorld3(World world, BlockPos blockPos) {
      return World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY(), blockPos.getZ()) || world.getBlockState(blockPos).getBlock() instanceof BlockAcaciaFence;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return true;
   }

   @Override
   public boolean processAction7() {
      return true;
   }

   public abstract int computeCount(IBlockState blockState);

   @Override
   public int processAction(World world) {
      return 20;
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      if (this.computeCount(blockState) > 0) {
         this.applyWorld2(world, blockPos);
      }

      super.onBlockAdded(world, blockPos, blockState);
   }

   public abstract IBlockState resolveIBlockState(IBlockState blockState, int count);

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos blockPos, IBlockState blockState, Entity entity) {
      if (!world.state) {
         int count = this.computeCount(blockState);
         if (count == 0) {
            this.applyWorldByWorld(world, blockPos, blockState, count);
         }
      }
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return this.computeCount(blockState);
   }

   @Override
   public int processAction8() {
      return 1;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!this.matchesWorld3(world, blockPos.down())) {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos8(blockPos);
      }
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return this.matchesWorld3(world, blockPos.down());
   }

   public void applyIBlockState(IBlockState blockState) {
      if (this.computeCount(blockState) > 0) {
         this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.03125F, 0.9375F);
      } else {
         this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.0625F, 0.9375F);
      }
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return true;
   }

   @Override
   public int processAction2(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return enumFacing == EnumFacing.UP ? this.computeCount(blockState) : 0;
   }

   public AxisAlignedBB resolveAxisAlignedBB(BlockPos blockPos) {
      return new AxisAlignedBB(
         (double)((float)blockPos.getX() + 0.125F),
         (double)blockPos.getY(),
         (double)((float)blockPos.getZ() + 0.125F),
         (double)((float)(blockPos.getX() + 1) - 0.125F),
         (double)blockPos.getY() + 0.25,
         (double)((float)(blockPos.getZ() + 1) - 0.125F)
      );
   }

   public void applyWorldByWorld(World world, BlockPos blockPos, IBlockState blockState, int count) {
      int count2 = this.computeCount2(world, blockPos);
      boolean flag = count > 0;
      boolean flag2 = count2 > 0;
      if (count != count2) {
         IBlockState blockState2 = this.resolveIBlockState(blockState, count2);
         world.matchesBlockPos(blockPos, blockState2, 2);
         this.applyWorld2(world, blockPos);
         world.applyBlockPos(blockPos, blockPos);
      }

      if (!flag2 && flag) {
         world.applyText((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.1, (double)blockPos.getZ() + 0.5, "random.click", 0.3F, 0.5F);
      } else if (flag2 && !flag) {
         world.applyText((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.1, (double)blockPos.getZ() + 0.5, "random.click", 0.3F, 0.6F);
      }

      if (flag2) {
         world.applyBlockPos(blockPos, this, this.processAction(world));
      }
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
   }

   public void applyWorld2(World world, BlockPos blockPos) {
      world.applyBlockPosByBlockPos(blockPos, this);
      world.applyBlockPosByBlockPos(blockPos.down(), this);
   }

   public abstract int computeCount2(World world, BlockPos blockPos);

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   @Override
   public boolean isActiveAsFlag() {
      return true;
   }

   public TertiaryBlockAdapter(Material material) {
      this(material, material.getMapColor());
   }

   public TertiaryBlockAdapter(Material material, MapColor mapColor) {
      super(material, mapColor);
      this.processAction(ItemGroupManager.itemGroupManager9);
      this.processAction(true);
   }
}
