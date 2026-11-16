package net.minecraft.block;

import client.setting.MovementToggleProperty;
import client.util.AutoWalkPath;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPistonExtension extends AdvancedBlockAdapter {
   public static final AutoWalkPath autoWalkPath = BlockPistonHead.autoWalkPath;
   public static final ImmutableSetEntry immutableSetEntry = BlockPistonHead.immutableSetEntry;

   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
      if (!world.state) {
         TileEntityPiston tileEntityPiston = this.resolveTileEntityPiston(world, blockPos);
         if (tileEntityPiston != null) {
            IBlockState iBlockState = tileEntityPiston.getIBlockState();
            iBlockState.getBlock().processAction(world, blockPos, iBlockState, 0);
         }
      }
   }

   @Override
   public void breakBlock(World world, BlockPos blockPos, IBlockState blockState) {
      BlockPos blockPos2 = blockPos.offset(((EnumFacing)blockState.getComparable(autoWalkPath)).getEnumFacing5());
      IBlockState blockState2 = world.getBlockState(blockPos2);
      if (blockState2.getBlock() instanceof BlockPiston && (Boolean)blockState2.getComparable(BlockPiston.internalImmutableSetEntry)) {
         world.matchesBlockPos8(blockPos2);
      }
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      TileEntityPiston tileEntityPiston = this.resolveTileEntityPiston(iBlockAccess, count, count2, count3);
      if (tileEntityPiston != null) {
         Block block = tileEntityPiston.getIBlockState().getBlock();
         if (block == this || block.getMaterial() == Material.coreItemEntry) {
            return;
         }

         float factor = tileEntityPiston.computeFactor3(0.0F);
         if (tileEntityPiston.isEnabled2()) {
            factor = 1.0F - factor;
         }

         block.processAction(iBlockAccess, count, count2, count3);
         if (block == Blocks.blockPiston2 || block == Blocks.blockPiston) {
            factor = 0.0F;
         }

         EnumFacing enumFacing = tileEntityPiston.getEnumFacing();
         this.coordOffset = block.getCoordOffset() - (double)((float)enumFacing.getInt2() * factor);
         this.coordOffset11 = block.getCoordOffset11() - (double)((float)enumFacing.getInt3() * factor);
         this.coordOffset2 = block.getCoordOffset2() - (double)((float)enumFacing.getInt() * factor);
         this.coordOffset9 = block.getCoordOffset9() - (double)((float)enumFacing.getInt2() * factor);
         this.coordOffset6 = block.getCoordOffset6() - (double)((float)enumFacing.getInt3() * factor);
         this.coordOffset15 = block.getCoordOffset15() - (double)((float)enumFacing.getInt() * factor);
      }
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return !world.state && world.resolveTileEntity(blockPos) == null;
   }

   private TileEntityPiston resolveTileEntityPiston(IBlockAccess iBlockAccess, BlockPos blockPos) {
      TileEntity tileEntity = iBlockAccess.resolveTileEntity(blockPos);
      return tileEntity instanceof TileEntityPiston ? (TileEntityPiston)tileEntity : null;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(autoWalkPath, BlockPistonHead.resolveEnumFacing(count))
         .resolveIBlockState(immutableSetEntry, (count & 8) > 0 ? MovementToggleProperty.STICKY : MovementToggleProperty.DEFAULT);
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return null;
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      if (tileEntity instanceof TileEntityPiston) {
         ((TileEntityPiston)tileEntity).updateState2();
      } else {
         super.onBlockAdded(world, blockPos, blockState);
      }
   }

   private TileEntityPiston resolveTileEntityPiston(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      TileEntity tileEntity = iBlockAccess.resolveTileEntity(new BlockPos(count, count2, count3));
      return tileEntity instanceof TileEntityPiston ? (TileEntityPiston)tileEntity : null;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!world.state) {
         world.resolveTileEntity(blockPos);
      }
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return null;
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return null;
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, EnumFacing enumFacing) {
      return false;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!world.state && world.resolveTileEntity(blockPos) == null) {
         world.matchesBlockPos8(blockPos);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return false;
   }

   public AxisAlignedBB resolveAxisAlignedBBByWorld(World world, int count, int count2, int count3, IBlockState blockState, float factor, EnumFacing enumFacing) {
      if (blockState.getBlock() != this && blockState.getBlock().getMaterial() != Material.coreItemEntry) {
         AxisAlignedBB boundingBox = blockState.getBlock().processAction(world, blockState, count, count2, count3);
         if (boundingBox == null) {
            return null;
         } else {
            double minX = boundingBox.minX;
            double minY = boundingBox.minY;
            double minZ = boundingBox.minZ;
            double maxX = boundingBox.maxX;
            double maxY = boundingBox.maxY;
            double maxZ = boundingBox.maxZ;
            if (enumFacing.getInt2() < 0) {
               minX -= (double)((float)enumFacing.getInt2() * factor);
            } else {
               maxX -= (double)((float)enumFacing.getInt2() * factor);
            }

            if (enumFacing.getInt3() < 0) {
               minY -= (double)((float)enumFacing.getInt3() * factor);
            } else {
               maxY -= (double)((float)enumFacing.getInt3() * factor);
            }

            if (enumFacing.getInt() < 0) {
               minZ -= (double)((float)enumFacing.getInt() * factor);
            } else {
               maxZ -= (double)((float)enumFacing.getInt() * factor);
            }

            return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
         }
      } else {
         return null;
      }
   }

   public MovingObjectPosition resolveMovingObjectPositionByWorld(World world, BlockPos blockPos, Vec3 vec, Vec3 vec2) {
      return null;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, immutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset1();
      if (blockState.getComparable(immutableSetEntry) == MovementToggleProperty.STICKY) {
         count |= 8;
      }

      return count;
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      TileEntityPiston tileEntityPiston = this.resolveTileEntityPiston(world, count, count2, count3);
      if (tileEntityPiston == null) {
         return null;
      } else {
         float factor = tileEntityPiston.computeFactor3(0.0F);
         if (tileEntityPiston.isEnabled2()) {
            factor = 1.0F - factor;
         }

         return this.resolveAxisAlignedBBByWorld(world, count, count2, count3, tileEntityPiston.getIBlockState(), factor, tileEntityPiston.getEnumFacing());
      }
   }

   public static TileEntity resolveTileEntity(IBlockState blockState, EnumFacing enumFacing, boolean flag, boolean flag2) {
      return new TileEntityPiston(blockState, enumFacing, flag, flag2);
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockPistonExtension() {
      super(Material.material33);
      this.setIBlockState(
         this.blockManager.get().resolveIBlockState(autoWalkPath, EnumFacing.NORTH).resolveIBlockState(immutableSetEntry, MovementToggleProperty.DEFAULT)
      );
      this.getBlock5(-1.0F);
   }
}
