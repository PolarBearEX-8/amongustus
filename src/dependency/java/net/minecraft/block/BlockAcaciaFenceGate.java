package net.minecraft.block;

import client.setting.EnumFacingXType;
import client.setting.MovementProperty;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAcaciaFenceGate extends BaseBlockAdapter {
   public static final InternalImmutableSetEntry internalImmutableSetEntry3;
   public static final InternalImmutableSetEntry internalImmutableSetEntry2;
   public static final InternalImmutableSetEntry internalImmutableSetEntry;

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      IBlockState blockState2 = blockState;
      EnumFacingXType enumFacingXType = ((EnumFacing)blockState.getComparable(autoWalkPath)).getEnumFacingXType();
      if (enumFacingXType == EnumFacingXType.Z
            && (
               iBlockAccess.getIBlockState(count - 1, count2, count3).getBlock() == Blocks.block63 || iBlockAccess.getIBlockState(count + 1, count2, count3).getBlock() == Blocks.block63
            )
         || enumFacingXType == EnumFacingXType.X
            && (
               iBlockAccess.getIBlockState(count, count2, count3 - 1).getBlock() == Blocks.block63 || iBlockAccess.getIBlockState(count, count2, count3 + 1).getBlock() == Blocks.block63
            )) {
         blockState2 = blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE);
      }

      return blockState2;
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      if (((EnumFacing)iBlockAccess.getIBlockState(count, count2, count3).getComparable(autoWalkPath)).getEnumFacingXType() == EnumFacingXType.Z) {
         this.setBlockBounds(0.0F, 0.0F, 0.375F, 1.0F, 1.0F, 0.625F);
      } else {
         this.setBlockBounds(0.375F, 0.0F, 0.0F, 0.625F, 1.0F, 1.0F);
      }
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return (Boolean)iBlockAccess.getIBlockState(count, count2, count3).getComparable(internalImmutableSetEntry3);
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(autoWalkPath, EnumFacing.resolveEnumFacing(count))
         .resolveIBlockState(internalImmutableSetEntry3, (count & 4) != 0)
         .resolveIBlockState(internalImmutableSetEntry2, (count & 8) != 0);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!world.state) {
         boolean flag = world.matchesBlockPos7(blockPos);
         if (flag || block.processAction7()) {
            if (flag && !(Boolean)blockState.getComparable(internalImmutableSetEntry3) && !(Boolean)blockState.getComparable(internalImmutableSetEntry2)) {
               world.matchesBlockPos(
                  blockPos, blockState.resolveIBlockState(internalImmutableSetEntry3, Boolean.TRUE).resolveIBlockState(internalImmutableSetEntry2, Boolean.TRUE), 2
               );
               world.applyEntityPlayerByEntityPlayer((EntityPlayer)null, 1003, blockPos, 0);
            } else if (!flag && (Boolean)blockState.getComparable(internalImmutableSetEntry3) && (Boolean)blockState.getComparable(internalImmutableSetEntry2)) {
               world.matchesBlockPos(
                  blockPos, blockState.resolveIBlockState(internalImmutableSetEntry3, Boolean.FALSE).resolveIBlockState(internalImmutableSetEntry2, Boolean.FALSE), 2
               );
               world.applyEntityPlayerByEntityPlayer((EntityPlayer)null, 1006, blockPos, 0);
            } else if (flag != (Boolean)blockState.getComparable(internalImmutableSetEntry2)) {
               world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry2, flag), 2);
            }
         }
      }
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return (Boolean)iBlockAccess.getBlockState(blockPos).getComparable(internalImmutableSetEntry3);
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      return this.getDefaultState()
         .resolveIBlockState(autoWalkPath, livingEntity.getEnumFacing2())
         .resolveIBlockState(internalImmutableSetEntry3, Boolean.FALSE)
         .resolveIBlockState(internalImmutableSetEntry2, Boolean.FALSE)
         .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE);
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return world.getBlockState(blockPos.down()).getBlock().getMaterial().canExecute2() ? super.processAction7(world, blockPos) : false;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      IBlockState blockState2 = blockState;
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry3)) {
         blockState2 = blockState.resolveIBlockState(internalImmutableSetEntry3, Boolean.FALSE);
         world.matchesBlockPos(blockPos, blockState2, 2);
      } else {
         EnumFacing enumFacing2 = EnumFacing.resolveEnumFacing((double)player.rotationYaw);
         if (blockState.getComparable(autoWalkPath) == enumFacing2.getEnumFacing5()) {
            blockState2 = blockState.resolveIBlockState(autoWalkPath, enumFacing2);
         }

         blockState2 = blockState2.resolveIBlockState(internalImmutableSetEntry3, Boolean.TRUE);
         world.matchesBlockPos(blockPos, blockState2, 2);
      }

      world.applyEntityPlayerByEntityPlayer(player, ((Boolean)blockState2.getComparable(internalImmutableSetEntry3)) ? 1003 : 1006, blockPos, 0);
      return true;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      return true;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, internalImmutableSetEntry3, internalImmutableSetEntry2, internalImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset();
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry2)) {
         count |= 8;
      }

      if ((Boolean)blockState.getComparable(internalImmutableSetEntry3)) {
         count |= 4;
      }

      return count;
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry3)) {
         return null;
      } else {
         return ((EnumFacing)blockState.getComparable(autoWalkPath)).getEnumFacingXType() == EnumFacingXType.Z
            ? new AxisAlignedBB(
               (double)count, (double)count2, (double)((float)count3 + 0.375F), (double)(count + 1), (double)((float)count2 + 1.5F), (double)((float)count3 + 0.625F)
            )
            : new AxisAlignedBB(
               (double)((float)count + 0.375F), (double)count2, (double)count3, (double)((float)count + 0.625F), (double)((float)count2 + 1.5F), (double)(count3 + 1)
            );
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

   public BlockAcaciaFenceGate(MovementProperty movementProperty) {
      super(Material.material, movementProperty.getMapColor());
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(internalImmutableSetEntry3, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry2, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
      );
      this.processAction(ItemGroupManager.itemGroupManager9);
   }

   static {
      String[] textArray = new String[]{"powered", "open", "in_wall"};
      internalImmutableSetEntry3 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[1]);
      internalImmutableSetEntry2 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[0]);
      internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[2]);
   }
}
