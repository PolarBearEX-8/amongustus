package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.BlockSolidType;
import client.setting.EnumFacingXType;
import client.setting.MovementFactorProperty;
import client.util.AdvancedPredicateAdapter;
import client.util.AutoWalkPath;
import client.util.CurveReader;
import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockTorch extends Block {
   public static final AutoWalkPath autoWalkPath = AutoWalkPath.resolveAutoWalkPath("facing", new AdvancedPredicateAdapter());

   private boolean matchesWorld3(World world, BlockPos blockPos) {
      if (World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY(), blockPos.getZ())) {
         return true;
      } else {
         Block block = world.getBlockState(blockPos).getBlock();
         return block instanceof BlockAcaciaFence || block == Blocks.block68 || block == Blocks.block63 || block == Blocks.blockStainedGlass;
      }
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      if (!this.matchesWorld2(world, blockPos, blockState)) {
         return true;
      } else {
         EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
         EnumFacingXType enumFacingXType = comparable.getEnumFacingXType();
         EnumFacing enumFacing = comparable.getEnumFacing5();
         boolean flag = false;
         if (enumFacingXType.isActiveAsFlag() && !world.matchesBlockPos4(blockPos.offset(enumFacing), true)) {
            flag = true;
         } else if (enumFacingXType.isActive() && !this.matchesWorld3(world, blockPos.offset(enumFacing))) {
            flag = true;
         }

         if (flag) {
            this.processAction(world, blockPos, blockState, 0);
            world.matchesBlockPos8(blockPos);
            return true;
         } else {
            return false;
         }
      }
   }

   @Override
   public IBlockState processAction(int count) {
      IBlockState defaultState = this.getDefaultState();
      IBlockState blockState;
      switch (count) {
         case 1:
            blockState = defaultState.resolveIBlockState(autoWalkPath, EnumFacing.EAST);
            break;
         case 2:
            blockState = defaultState.resolveIBlockState(autoWalkPath, EnumFacing.WEST);
            break;
         case 3:
            blockState = defaultState.resolveIBlockState(autoWalkPath, EnumFacing.SOUTH);
            break;
         case 4:
            blockState = defaultState.resolveIBlockState(autoWalkPath, EnumFacing.NORTH);
            break;
         case 5:
         default:
            blockState = defaultState.resolveIBlockState(autoWalkPath, EnumFacing.UP);
      }

      return blockState;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      this.matchesWorld(world, blockPos, blockState);
   }

   public boolean matchesWorld2(World world, BlockPos blockPos, IBlockState blockState) {
      if (blockState.getBlock() == this && this.matchesWorld2(world, blockPos, (EnumFacing)blockState.getComparable(autoWalkPath))) {
         return true;
      } else {
         if (world.getBlockState(blockPos).getBlock() == this) {
            this.processAction(world, blockPos, blockState, 0);
            world.matchesBlockPos8(blockPos);
         }

         return false;
      }
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      if (this.matchesWorld2(world, blockPos, enumFacing)) {
         return this.getDefaultState().resolveIBlockState(autoWalkPath, enumFacing);
      } else {
         for (Object enumFacing2Obj : MovementFactorProperty.HORIZONTAL) {
            EnumFacing enumFacing2 = (EnumFacing)enumFacing2Obj;
            if (world.matchesBlockPos4(blockPos.offset(enumFacing2.getEnumFacing5()), true)) {
               return this.getDefaultState().resolveIBlockState(autoWalkPath, enumFacing2);
            }
         }

         return this.getDefaultState();
      }
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      for (Object enumFacingObj : autoWalkPath.getCollection()) {
         EnumFacing enumFacing = (EnumFacing)enumFacingObj;
         if (this.matchesWorld2(world, blockPos, enumFacing)) {
            return true;
         }
      }

      return false;
   }

   private boolean matchesWorld2(World world, BlockPos blockPos, EnumFacing enumFacing) {
      BlockPos blockPos2 = blockPos.offset(enumFacing.getEnumFacing5());
      return enumFacing.getEnumFacingXType().isActiveAsFlag() && world.matchesBlockPos4(blockPos2, true) || enumFacing.equals(EnumFacing.UP) && this.matchesWorld3(world, blockPos2);
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      this.matchesWorld2(world, blockPos, blockState);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath});
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      byte byteValue = 0;
      int count;
      switch (CurveReader.countArray[((EnumFacing)blockState.getComparable(autoWalkPath)).ordinal()]) {
         case 1:
            count = byteValue | 1;
            break;
         case 2:
            count = byteValue | 2;
            break;
         case 3:
            count = byteValue | 3;
            break;
         case 4:
            count = byteValue | 4;
            break;
         case 5:
         case 6:
         default:
            count = byteValue | 5;
      }

      return count;
   }

   @Override
   public MovingObjectPosition resolveMovingObjectPositionByWorld(
      World world, int count, int count2, int count3, double posX, double posX2, double posX3, double posX4, double posY, double posZ
   ) {
      EnumFacing comparable = (EnumFacing)world.getIBlockState(count, count2, count3).getComparable(autoWalkPath);
      float factor = 0.15F;
      if (comparable == EnumFacing.EAST) {
         this.setBlockBounds(0.0F, 0.2F, 0.5F - factor, factor * 2.0F, 0.8F, 0.5F + factor);
      } else if (comparable == EnumFacing.WEST) {
         this.setBlockBounds(1.0F - factor * 2.0F, 0.2F, 0.5F - factor, 1.0F, 0.8F, 0.5F + factor);
      } else if (comparable == EnumFacing.SOUTH) {
         this.setBlockBounds(0.5F - factor, 0.2F, 0.0F, 0.5F + factor, 0.8F, factor * 2.0F);
      } else if (comparable == EnumFacing.NORTH) {
         this.setBlockBounds(0.5F - factor, 0.2F, 1.0F - factor * 2.0F, 0.5F + factor, 0.8F, 1.0F);
      } else {
         float factor2 = 0.1F;
         this.setBlockBounds(0.5F - factor2, 0.0F, 0.5F - factor2, 0.5F + factor2, 0.6F, 0.5F + factor2);
      }

      return super.resolveMovingObjectPositionByWorld(world, count, count2, count3, posX, posX2, posX3, posX4, posY, posZ);
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
      double offset = (double)blockPos.getX() + 0.5;
      double offset2 = (double)blockPos.getY() + 0.7;
      double offset3 = (double)blockPos.getZ() + 0.5;
      double offset4 = 0.22;
      double offset5 = 0.27;
      if (comparable.getEnumFacingXType().isActiveAsFlag()) {
         EnumFacing enumFacing = comparable.getEnumFacing5();
         world.applyAttackEffectsExplosionNorm(
            AttackEffectsExplosionNormalType.SMOKE_NORMAL,
            offset + offset5 * (double)enumFacing.getInt2(),
            offset2 + offset4,
            offset3 + offset5 * (double)enumFacing.getInt(),
            0.0,
            0.0,
            0.0,
            new int[0]
         );
         world.applyAttackEffectsExplosionNorm(
            AttackEffectsExplosionNormalType.FLAME,
            offset + offset5 * (double)enumFacing.getInt2(),
            offset2 + offset4,
            offset3 + offset5 * (double)enumFacing.getInt(),
            0.0,
            0.0,
            0.0,
            new int[0]
         );
      } else {
         world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_NORMAL, offset, offset2, offset3, 0.0, 0.0, 0.0, new int[0]);
         world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.FLAME, offset, offset2, offset3, 0.0, 0.0, 0.0, new int[0]);
      }
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   @Override
   public boolean processAction17() {
      return false;
   }

   public BlockTorch() {
      super(Material.material22);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(autoWalkPath, EnumFacing.UP));
      this.processAction(true);
      this.processAction(ItemGroupManager.itemGroupManager8);
   }
}
