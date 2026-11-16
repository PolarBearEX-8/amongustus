package net.minecraft.block;

import client.setting.BlockSolidType;
import client.setting.CombatStateProperty;
import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.InternalImmutableSetEntry;
import client.util.SplineSorter;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBed extends BaseBlockAdapter {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("part", CombatStateProperty.class);
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("occupied");

   public static BlockPos resolveBlockPosByWorld(World world, BlockPos blockPos, int count) {
      int count2 = count;
      EnumFacing comparable = (EnumFacing)world.getBlockState(blockPos).getComparable(autoWalkPath);
      int coordX = blockPos.getX();
      int coordY = blockPos.getY();
      int coordZ = blockPos.getZ();

      for (int index = 0; index <= 1; index++) {
         int count3 = coordX - comparable.getInt2() * index - 1;
         int count4 = coordZ - comparable.getInt() * index - 1;
         int count5 = count3 + 2;

         for (int index2 = count4 + 2; count3 <= count5; count3++) {
            for (int index3 = count4; index3 <= index2; index3++) {
               BlockPos blockPos2 = new BlockPos(count3, coordY, index3);
               if (matchesWorld4(world, blockPos2)) {
                  if (count2 <= 0) {
                     return blockPos2;
                  }

                  count2--;
               }
            }
         }
      }

      return null;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player) {
      if (player.invulnerableManager.enabled2 && blockState.getComparable(immutableSetEntry) == CombatStateProperty.HEAD) {
         BlockPos blockPos2 = blockPos.offset(((EnumFacing)blockState.getComparable(autoWalkPath)).getEnumFacing5());
         if (world.getBlockState(blockPos2).getBlock() == this) {
            world.matchesBlockPos8(blockPos2);
         }
      }
   }

   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
      if (blockState.getComparable(immutableSetEntry) == CombatStateProperty.FOOT) {
         super.applyWorld3(world, blockPos, blockState, factor, 0);
      }
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   public static boolean matchesWorld4(World world, BlockPos blockPos) {
      return World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())
         && !world.getBlockState(blockPos).getBlock().getMaterial().canExecute2()
         && !world.getBlockState(blockPos.up()).getBlock().getMaterial().canExecute2();
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      this.updateState2();
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      IBlockState blockState2 = blockState;
      if (blockState.getComparable(immutableSetEntry) == CombatStateProperty.FOOT) {
         EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
         IBlockState iBlockState = iBlockAccess.getIBlockState(count + comparable.getInt2(), count2 + comparable.getInt3(), count3 + comparable.getInt());
         if (iBlockState.getBlock() == this) {
            blockState2 = blockState.resolveIBlockState(internalImmutableSetEntry, iBlockState.getComparable(internalImmutableSetEntry));
         }
      }

      return blockState2;
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Items.bed;
   }

   @Override
   public IBlockState processAction(int count) {
      EnumFacing enumFacing = EnumFacing.resolveEnumFacing(count);
      return (count & 8) > 0
         ? this.getDefaultState()
            .resolveIBlockState(immutableSetEntry, CombatStateProperty.HEAD)
            .resolveIBlockState(autoWalkPath, enumFacing)
            .resolveIBlockState(internalImmutableSetEntry, (count & 4) > 0)
         : this.getDefaultState().resolveIBlockState(immutableSetEntry, CombatStateProperty.FOOT).resolveIBlockState(autoWalkPath, enumFacing);
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return blockState.getComparable(immutableSetEntry) == CombatStateProperty.HEAD ? null : Items.bed;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
      if (blockState.getComparable(immutableSetEntry) == CombatStateProperty.HEAD) {
         if (world.getBlockState(blockPos.offset(comparable.getEnumFacing5())).getBlock() != this) {
            world.matchesBlockPos8(blockPos);
         }
      } else if (world.getBlockState(blockPos.offset(comparable)).getBlock() != this) {
         world.matchesBlockPos8(blockPos);
         if (!world.state) {
            this.processAction(world, blockPos, blockState, 0);
         }
      }
   }

   @Override
   public int processAction8() {
      return 1;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      BlockPos blockPos2 = blockPos;
      IBlockState blockState2 = blockState;
      if (world.state) {
         return true;
      } else {
         if (blockState.getComparable(immutableSetEntry) != CombatStateProperty.HEAD) {
            blockPos2 = blockPos.offset((EnumFacing)blockState.getComparable(autoWalkPath));
            blockState2 = world.getBlockState(blockPos2);
            if (blockState2.getBlock() != this) {
               return true;
            }
         }

         if (world.secondaryFloatArrayEntry.isActiveAsFlag()
            && world.resolveBiomeIDIsOutOfBoundsManager(blockPos2) != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager38) {
            if ((Boolean)blockState2.getComparable(internalImmutableSetEntry)) {
               if (this.resolveEntityPlayer(world, blockPos2) != null) {
                  player.applyIChatComponentByIChatComponent(new BaseManagerStyleIChatComponentAdapter("tile.bed.occupied", new Object[0]));
                  return true;
               }

               blockState2 = blockState2.resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE);
               world.matchesBlockPos(blockPos2, blockState2, 4);
            }

            SplineSorter splineSorter = player.resolveSplineSorter(blockPos2);
            if (splineSorter == SplineSorter.OK) {
               blockState2 = blockState2.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE);
               world.matchesBlockPos(blockPos2, blockState2, 4);
            } else if (splineSorter == SplineSorter.NOT_POSSIBLE_NOW) {
               player.applyIChatComponentByIChatComponent(new BaseManagerStyleIChatComponentAdapter("tile.bed.noSleep", new Object[0]));
            } else if (splineSorter == SplineSorter.NOT_SAFE) {
               player.applyIChatComponentByIChatComponent(new BaseManagerStyleIChatComponentAdapter("tile.bed.notSafe", new Object[0]));
            }

            return true;
         } else {
            world.matchesBlockPos8(blockPos2);
            BlockPos blockPos3 = blockPos2.offset(((EnumFacing)blockState2.getComparable(autoWalkPath)).getEnumFacing5());
            if (world.getBlockState(blockPos3).getBlock() == this) {
               world.matchesBlockPos8(blockPos3);
            }

            world.resolveBlockBreakState((Entity)null, (double)blockPos2.getX() + 0.5, (double)blockPos2.getY() + 0.5, (double)blockPos2.getZ() + 0.5, 5.0F, true, true);
            return true;
         }
      }
   }

   private EntityPlayer resolveEntityPlayer(World world, BlockPos blockPos) {
      for (EntityPlayer player : (Iterable<EntityPlayer>)(Iterable<?>)world.itemList7) {
         if (player.isState5() && player.blockPos2.equals(blockPos)) {
            return player;
         }
      }

      return null;
   }

   private void updateState2() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, immutableSetEntry, internalImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset();
      if (blockState.getComparable(immutableSetEntry) == CombatStateProperty.HEAD) {
         count |= 8;
         if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
            count |= 4;
         }
      }

      return count;
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockBed() {
      super(Material.material19);
      this.setIBlockState(
         this.blockManager.get().resolveIBlockState(immutableSetEntry, CombatStateProperty.FOOT).resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
      );
      this.updateState2();
   }
}
