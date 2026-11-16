package net.minecraft.block;

import client.setting.BlockSolidType;
import client.setting.EnumFacingXType;
import client.setting.MovementFactorProperty;
import client.util.AutoWalkPath;
import client.util.IBlockState;
import client.util.SplinePicker;
import client.via.ViaLoadingBase;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLadder extends Block {
   public static final AutoWalkPath autoWalkPath = AutoWalkPath.resolveAutoWalkPath("facing", MovementFactorProperty.HORIZONTAL);

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   @Override
   public AxisAlignedBB processAction(World world, BlockPos blockPos) {
      this.processAction(world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
      return super.processAction(world, blockPos);
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      IBlockState iBlockState = iBlockAccess.getIBlockState(count, count2, count3);
      if (iBlockState.getBlock() == this) {
         if (ViaLoadingBase.protocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8)) {
            float factor = 0.125F;
            switch (SplinePicker.countArray[((EnumFacing)iBlockState.getComparable(autoWalkPath)).ordinal()]) {
               case 1:
                  this.setBlockBounds(0.0F, 0.0F, 1.0F - factor, 1.0F, 1.0F, 1.0F);
                  break;
               case 2:
                  this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, factor);
                  break;
               case 3:
                  this.setBlockBounds(1.0F - factor, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                  break;
               case 4:
               default:
                  this.setBlockBounds(0.0F, 0.0F, 0.0F, factor, 1.0F, 1.0F);
            }
         } else {
            switch (SplinePicker.countArray[((EnumFacing)iBlockState.getComparable(autoWalkPath)).ordinal()]) {
               case 1:
                  this.processAction(0.0, 0.0, 0.8125, 1.0, 1.0, 1.0);
                  break;
               case 2:
                  this.processAction(0.0, 0.0, 0.0, 1.0, 1.0, 0.1875);
                  break;
               case 3:
                  this.processAction(0.8125, 0.0, 0.0, 1.0, 1.0, 1.0);
                  break;
               case 4:
               default:
                  this.processAction(0.0, 0.0, 0.0, 0.1875, 1.0, 1.0);
            }
         }
      }
   }

   @Override
   public IBlockState processAction(int count) {
      EnumFacing enumFacing = EnumFacing.getEnumFacing6(count);
      if (enumFacing.getEnumFacingXType() == EnumFacingXType.Y) {
         enumFacing = EnumFacing.NORTH;
      }

      return this.getDefaultState().resolveIBlockState(autoWalkPath, enumFacing);
   }

   public boolean matchesWorld2(World world, BlockPos blockPos, EnumFacing enumFacing) {
      return world.getBlockState(blockPos.offset(enumFacing.getEnumFacing5())).getBlock().isActive2();
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
      if (!this.matchesWorld2(world, blockPos, comparable)) {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos8(blockPos);
      }

      super.processAction(world, blockPos, blockState, block);
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      if (enumFacing.getEnumFacingXType().isActiveAsFlag() && this.matchesWorld2(world, blockPos, enumFacing)) {
         return this.getDefaultState().resolveIBlockState(autoWalkPath, enumFacing);
      } else {
         for (EnumFacing enumFacing2 : (Iterable<EnumFacing>) (Iterable) MovementFactorProperty.HORIZONTAL) {
            if (this.matchesWorld2(world, blockPos, enumFacing2)) {
               return this.getDefaultState().resolveIBlockState(autoWalkPath, enumFacing2);
            }
         }

         return this.getDefaultState();
      }
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return world.getBlockState(blockPos.getBlockPos()).getBlock().isActive2()
         || world.getBlockState(blockPos.getBlockPos3()).getBlock().isActive2()
         || world.getBlockState(blockPos.getBlockPosAsBlockPos()).getBlock().isActive2()
         || world.getBlockState(blockPos.getBlockPos2()).getBlock().isActive2();
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath});
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      this.processAction(world, count, count2, count3);
      return super.processAction(world, blockState, count, count2, count3);
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset1();
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockLadder() {
      super(Material.material22);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(autoWalkPath, EnumFacing.NORTH));
      this.processAction(ItemGroupManager.itemGroupManager8);
   }
}
