package net.minecraft.block;

import client.event.ServiceListener;
import client.setting.MovementToggleProperty;
import client.util.AngleRanker;
import client.util.AutoWalkPath;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import client.util.RotationReader;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPiston extends Block {
   public static final AutoWalkPath autoWalkPath = AutoWalkPath.resolveAutoWalkPath("facing");
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("extended");
   public final boolean enabled;

   @Override
   public void processAction2() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public IBlockState processAction(IBlockState blockState) {
      return this.getDefaultState().resolveIBlockState(autoWalkPath, EnumFacing.UP);
   }

   public static EnumFacing resolveEnumFacingByWorld(World world, BlockPos blockPos, EntityLivingBase livingEntity) {
      if (MathHelper.computeFactor((float)livingEntity.posX - (float)blockPos.getX()) < 2.0F && MathHelper.computeFactor((float)livingEntity.posZ - (float)blockPos.getZ()) < 2.0F) {
         double offset = livingEntity.posY + (double)livingEntity.getHeight2();
         if (offset - (double)blockPos.getY() > 2.0) {
            return EnumFacing.UP;
         }

         if ((double)blockPos.getY() - offset > 0.0) {
            return EnumFacing.DOWN;
         }
      }

      return livingEntity.getEnumFacing2().getEnumFacing5();
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase livingEntity, ItemStack stack) {
      world.matchesBlockPos(blockPos, blockState.resolveIBlockState(autoWalkPath, resolveEnumFacingByWorld(world, blockPos, livingEntity)), 2);
      if (!world.state) {
         this.applyWorld3(world, blockPos, blockState);
      }
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      IBlockState iBlockState = iBlockAccess.getIBlockState(count, count2, count3);
      if (iBlockState.getBlock() == this && (Boolean)iBlockState.getComparable(internalImmutableSetEntry)) {
         EnumFacing comparable = (EnumFacing)iBlockState.getComparable(autoWalkPath);
         if (comparable != null) {
            switch (AngleRanker.countArray[comparable.ordinal()]) {
               case 1:
                  this.setBlockBounds(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
                  break;
               case 2:
                  this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
                  break;
               case 3:
                  this.setBlockBounds(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
                  break;
               case 4:
                  this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
                  break;
               case 5:
                  this.setBlockBounds(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                  break;
               case 6:
                  this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
            }
         }
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   private boolean matchesWorld2(World world, BlockPos blockPos, EnumFacing enumFacing) {
      for (EnumFacing enumFacing2 : EnumFacing.getEntries()) {
         if (enumFacing2 != enumFacing && world.matchesBlockPos(blockPos.offset(enumFacing2), enumFacing2)) {
            return true;
         }
      }

      if (world.matchesBlockPos(blockPos, EnumFacing.DOWN)) {
         return true;
      } else {
         BlockPos blockPos2 = blockPos.up();

         for (EnumFacing enumFacing3 : EnumFacing.getEntries()) {
            if (enumFacing3 != EnumFacing.DOWN && world.matchesBlockPos(blockPos2.offset(enumFacing3), enumFacing3)) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(autoWalkPath, resolveEnumFacing(count)).resolveIBlockState(internalImmutableSetEntry, (count & 8) > 0);
   }

   private boolean matchesWorld(World world, BlockPos blockPos, EnumFacing enumFacing, boolean flag) {
      if (!flag) {
         world.matchesBlockPos8(blockPos.offset(enumFacing));
      }

      RotationReader rotationReader = new RotationReader(world, blockPos, enumFacing, flag);
      List list = rotationReader.getList();
      List list2 = rotationReader.getList2();
      if (!rotationReader.isActive()) {
         return false;
      } else {
         int count = list.size() + list2.size();
         Block[] blockArray = new Block[count];
         EnumFacing enumFacing2 = flag ? enumFacing : enumFacing.getEnumFacing5();

         for (int index = list2.size() - 1; index >= 0; index--) {
            BlockPos blockPos2 = (BlockPos)list2.get(index);
            Block block = world.getBlockState(blockPos2).getBlock();
            block.processAction(world, blockPos2, world.getBlockState(blockPos2), 0);
            world.matchesBlockPos8(blockPos2);
            count--;
            blockArray[count] = block;
         }

         for (int index2 = list.size() - 1; index2 >= 0; index2--) {
            BlockPos blockPos3 = (BlockPos)list.get(index2);
            IBlockState blockState = world.getBlockState(blockPos3);
            Block block2 = blockState.getBlock();
            block2.getIndex(blockState);
            world.matchesBlockPos8(blockPos3);
            BlockPos blockPos4 = blockPos3.offset(enumFacing2);
            world.matchesBlockPos(blockPos4, Blocks.blockPistonExtension.getDefaultState().resolveIBlockState(autoWalkPath, enumFacing), 4);
            world.applyBlockPos(blockPos4, BlockPistonExtension.resolveTileEntity(blockState, enumFacing, flag, false));
            count--;
            blockArray[count] = block2;
         }

         BlockPos blockPos5 = blockPos.offset(enumFacing);
         if (flag) {
            MovementToggleProperty movementToggleProperty = this.enabled ? MovementToggleProperty.STICKY : MovementToggleProperty.DEFAULT;
            IBlockState blockState2 = Blocks.blockPistonHead
               .getDefaultState()
               .resolveIBlockState(BlockPistonHead.autoWalkPath, enumFacing)
               .resolveIBlockState(BlockPistonHead.immutableSetEntry, movementToggleProperty);
            IBlockState blockState3 = Blocks.blockPistonExtension
               .getDefaultState()
               .resolveIBlockState(BlockPistonExtension.autoWalkPath, enumFacing)
               .resolveIBlockState(BlockPistonExtension.immutableSetEntry, this.enabled ? MovementToggleProperty.STICKY : MovementToggleProperty.DEFAULT);
            world.matchesBlockPos(blockPos5, blockState3, 4);
            world.applyBlockPos(blockPos5, BlockPistonExtension.resolveTileEntity(blockState2, enumFacing, true, false));
         }

         for (int index3 = list2.size() - 1; index3 >= 0; index3--) {
            world.applyBlockPosByBlockPos((BlockPos)list2.get(index3), blockArray[count++]);
         }

         for (int index4 = list.size() - 1; index4 >= 0; index4--) {
            world.applyBlockPosByBlockPos((BlockPos)list.get(index4), blockArray[count++]);
         }

         if (flag) {
            world.applyBlockPosByBlockPos(blockPos5, Blocks.blockPistonHead);
            world.applyBlockPosByBlockPos(blockPos, this);
         }

         return true;
      }
   }

   public static boolean matchesBlock(Block block, World world, BlockPos blockPos, EnumFacing enumFacing, boolean flag) {
      if (block == Blocks.block113) {
         return false;
      } else if (!world.getInternalListEntry().checkBlockPos(blockPos)) {
         return false;
      } else if (blockPos.getY() >= 0 && (enumFacing != EnumFacing.DOWN || blockPos.getY() != 0)) {
         if (blockPos.getY() <= world.getCount() - 1 && (enumFacing != EnumFacing.UP || blockPos.getY() != world.getCount() - 1)) {
            if (block != Blocks.blockPiston2 && block != Blocks.blockPiston) {
               if (block.processAction8(world, blockPos) == -1.0F) {
                  return false;
               }

               if (block.processAction8() == 2) {
                  return false;
               }

               if (block.processAction8() == 1) {
                  if (!flag) {
                     return false;
                  }

                  return true;
               }
            } else if ((Boolean)world.getBlockState(blockPos).getComparable(internalImmutableSetEntry)) {
               return false;
            }

            return !(block instanceof ServiceListener);
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @Override
   public void applyWorld(World world, BlockPos blockPos, IBlockState blockState, AxisAlignedBB boundingBox, List list, Entity entity) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!world.state) {
         this.applyWorld3(world, blockPos, blockState);
      }
   }

   public static EnumFacing resolveEnumFacing(int count) {
      int count2 = count & 7;
      return count2 > 5 ? null : EnumFacing.getEnumFacing6(count2);
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      return this.getDefaultState()
         .resolveIBlockState(autoWalkPath, resolveEnumFacingByWorld(world, blockPos, livingEntity))
         .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE);
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      if (!world.state && world.resolveTileEntity(blockPos) == null) {
         this.applyWorld3(world, blockPos, blockState);
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, internalImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset1();
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         count |= 8;
      }

      return count;
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      this.processAction(world, count, count2, count3);
      return super.processAction(world, blockState, count, count2, count3);
   }

   private void applyWorld3(World world, BlockPos blockPos, IBlockState blockState) {
      EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
      boolean flag = this.matchesWorld2(world, blockPos, comparable);
      if (flag && !(Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         if (new RotationReader(world, blockPos, comparable, true).isActive()) {
            world.applyBlockPosByBlockPos(blockPos, this, 0, comparable.getIndexOffset1());
         }
      } else if (!flag && (Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE), 2);
         world.applyBlockPosByBlockPos(blockPos, this, 1, comparable.getIndexOffset1());
      }
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, int count, int count2) {
      EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
      if (!world.state) {
         boolean flag = this.matchesWorld2(world, blockPos, comparable);
         if (flag && count == 1) {
            world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE), 2);
            return false;
         }

         if (!flag && count == 0) {
            return false;
         }
      }

      if (count == 0) {
         if (!this.matchesWorld(world, blockPos, comparable, true)) {
            return false;
         }

         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE), 2);
         world.applyText(
            (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5, "tile.piston.out", 0.5F, world.random.nextFloat() * 0.25F + 0.6F
         );
      } else if (count == 1) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos.offset(comparable));
         if (tileEntity instanceof TileEntityPiston) {
            ((TileEntityPiston)tileEntity).updateState2();
         }

         world.matchesBlockPos(
            blockPos,
            Blocks.blockPistonExtension
               .getDefaultState()
               .resolveIBlockState(BlockPistonExtension.autoWalkPath, comparable)
               .resolveIBlockState(BlockPistonExtension.immutableSetEntry, this.enabled ? MovementToggleProperty.STICKY : MovementToggleProperty.DEFAULT),
            3
         );
         world.applyBlockPos(blockPos, BlockPistonExtension.resolveTileEntity(this.processAction(count2), comparable, false, true));
         if (this.enabled) {
            BlockPos blockPos2 = blockPos.add(comparable.getInt2() * 2, comparable.getInt3() * 2, comparable.getInt() * 2);
            Block block = world.getBlockState(blockPos2).getBlock();
            boolean flag2 = false;
            if (block == Blocks.blockPistonExtension) {
               TileEntity tileEntity2 = world.resolveTileEntity(blockPos2);
               if (tileEntity2 instanceof TileEntityPiston) {
                  TileEntityPiston tileEntityPiston = (TileEntityPiston)tileEntity2;
                  if (tileEntityPiston.getEnumFacing() == comparable && tileEntityPiston.isEnabled2()) {
                     tileEntityPiston.updateState2();
                     flag2 = true;
                  }
               }
            }

            if (!flag2
               && block.getMaterial() != Material.coreItemEntry
               && matchesBlock(block, world, blockPos2, comparable.getEnumFacing5(), false)
               && (block.processAction8() == 0 || block == Blocks.blockPiston2 || block == Blocks.blockPiston)) {
               this.matchesWorld(world, blockPos, comparable, false);
            }
         } else {
            world.matchesBlockPos8(blockPos.offset(comparable));
         }

         world.applyText(
            (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5, "tile.piston.in", 0.5F, world.random.nextFloat() * 0.15F + 0.6F
         );
      }

      return true;
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockPiston(boolean flag) {
      super(Material.material33);
      this.setIBlockState(
         this.blockManager.get().resolveIBlockState(autoWalkPath, EnumFacing.NORTH).resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
      );
      this.enabled = flag;
      this.processAction(stepManager10);
      this.getBlock5(0.5F);
      this.processAction(ItemGroupManager.itemGroupManager9);
   }
}
