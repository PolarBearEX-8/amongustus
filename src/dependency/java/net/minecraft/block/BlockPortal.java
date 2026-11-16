package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.BlockSolidType;
import client.setting.EnumFacingPositiveType;
import client.setting.EnumFacingXType;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.PredicateArrayEntry;
import client.util.SecondaryWorldEntry;
import client.util.WorldEntry;
import com.google.common.cache.LoadingCache;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPortal extends ExtendedBlockAdapter {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry(
      "axis", EnumFacingXType.class, new EnumFacingXType[]{EnumFacingXType.X, EnumFacingXType.Z}
   );

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      super.updateTick(world, blockPos, blockState, random);
      if (world.secondaryFloatArrayEntry.isActive2()
         && world.getDoFireTickManager().matchesText("doMobSpawning")
         && random.nextInt(2000) < world.getGameSettingsPeacefulType().getIndex()) {
         int coordY = blockPos.getY();
         BlockPos blockPos2 = blockPos;

         while (!World.matchesIBlockAccess(world, blockPos2.getX(), blockPos2.getY(), blockPos2.getZ()) && blockPos2.getY() > 0) {
            blockPos2 = blockPos2.down();
         }

         if (coordY > 0 && !world.getBlockState(blockPos2.up()).getBlock().isActive2()) {
            Entity entity = ItemSpawnEgg.resolveEntityByWorld(world, 57, (double)blockPos2.getX() + 0.5, (double)blockPos2.getY() + 1.1, (double)blockPos2.getZ() + 0.5);
            if (entity != null) {
               entity.count10 = entity.getCount2();
            }
         }
      }
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.TRANSLUCENT;
   }

   public static int computeCount(EnumFacingXType enumFacingXType) {
      return enumFacingXType == EnumFacingXType.X ? 1 : (enumFacingXType == EnumFacingXType.Z ? 2 : 0);
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      EnumFacingXType comparable = (EnumFacingXType)iBlockAccess.getIBlockState(count, count2, count3).getComparable(immutableSetEntry);
      float factor = 0.125F;
      float factor2 = 0.125F;
      if (comparable == EnumFacingXType.X) {
         factor = 0.5F;
      }

      if (comparable == EnumFacingXType.Z) {
         factor2 = 0.5F;
      }

      this.setBlockBounds(0.5F - factor, 0.0F, 0.5F - factor2, 0.5F + factor, 1.0F, 0.5F + factor2);
   }

   public InternalBlockPosEntry resolveInternalBlockPosEntry(World world, BlockPos blockPos) {
      EnumFacingXType enumFacingXType = EnumFacingXType.Z;
      WorldEntry worldEntry = new WorldEntry(world, blockPos, EnumFacingXType.X);
      LoadingCache loadingCache = PredicateArrayEntry.resolveLoadingCache(world, true);
      if (!worldEntry.isActive()) {
         enumFacingXType = EnumFacingXType.X;
         worldEntry = new WorldEntry(world, blockPos, EnumFacingXType.Z);
      }

      if (!worldEntry.isActive()) {
         return new InternalBlockPosEntry(blockPos, EnumFacing.NORTH, EnumFacing.UP, loadingCache, 1, 1, 1);
      } else {
         int[] countArray = new int[EnumFacingPositiveType.values().length];
         EnumFacing enumFacing = WorldEntry.resolveEnumFacing(worldEntry).getEnumFacing2();
         BlockPos blockPos2 = WorldEntry.resolveBlockPos(worldEntry).resolveBlockPos2(worldEntry.getCount2() - 1);

         for (EnumFacingPositiveType enumFacingPositiveType : EnumFacingPositiveType.values()) {
            InternalBlockPosEntry internalBlockPosEntry = new InternalBlockPosEntry(
               enumFacing.getEnumFacingPositiveType() == enumFacingPositiveType ? blockPos2 : blockPos2.getBlockPos(WorldEntry.resolveEnumFacing(worldEntry), worldEntry.getCount3() - 1),
               EnumFacing.resolveEnumFacing(enumFacingPositiveType, enumFacingXType),
               EnumFacing.UP,
               loadingCache,
               worldEntry.getCount3(),
               worldEntry.getCount2(),
               1
            );

            for (int index = 0; index < worldEntry.getCount3(); index++) {
               for (int index2 = 0; index2 < worldEntry.getCount2(); index2++) {
                  SecondaryWorldEntry secondaryWorldEntry = internalBlockPosEntry.resolveSecondaryWorldEntry(index, index2, 1);
                  if (secondaryWorldEntry.getIBlockState() != null && secondaryWorldEntry.getIBlockState().getBlock().getMaterial() != Material.coreItemEntry) {
                     countArray[enumFacingPositiveType.ordinal()]++;
                  }
               }
            }
         }

         EnumFacingPositiveType enumFacingPositiveType2 = EnumFacingPositiveType.POSITIVE;

         for (EnumFacingPositiveType enumFacingPositiveType3 : EnumFacingPositiveType.values()) {
            if (countArray[enumFacingPositiveType3.ordinal()] < countArray[enumFacingPositiveType2.ordinal()]) {
               enumFacingPositiveType2 = enumFacingPositiveType3;
            }
         }

         return new InternalBlockPosEntry(
            enumFacing.getEnumFacingPositiveType() == enumFacingPositiveType2 ? blockPos2 : blockPos2.getBlockPos(WorldEntry.resolveEnumFacing(worldEntry), worldEntry.getCount3() - 1),
            EnumFacing.resolveEnumFacing(enumFacingPositiveType2, enumFacingXType),
            EnumFacing.UP,
            loadingCache,
            worldEntry.getCount3(),
            worldEntry.getCount2(),
            1
         );
      }
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return null;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, (count & 3) == 2 ? EnumFacingXType.Z : EnumFacingXType.X);
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos blockPos, IBlockState blockState, Entity entity) {
      if (entity.ridingEntity == null && entity.entity == null) {
         entity.applyBlockPos(blockPos);
      }
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      EnumFacingXType comparable = (EnumFacingXType)blockState.getComparable(immutableSetEntry);
      if (comparable == EnumFacingXType.X) {
         WorldEntry worldEntry = new WorldEntry(world, blockPos, EnumFacingXType.X);
         if (!worldEntry.isActive() || WorldEntry.computeCountByWorldEntry(worldEntry) < WorldEntry.computeCount2(worldEntry) * WorldEntry.computeCount(worldEntry)) {
            world.matchesBlockPos(blockPos, Blocks.air.getDefaultState());
         }
      } else if (comparable == EnumFacingXType.Z) {
         WorldEntry worldEntry2 = new WorldEntry(world, blockPos, EnumFacingXType.Z);
         if (!worldEntry2.isActive() || WorldEntry.computeCountByWorldEntry(worldEntry2) < WorldEntry.computeCount2(worldEntry2) * WorldEntry.computeCount(worldEntry2)) {
            world.matchesBlockPos(blockPos, Blocks.air.getDefaultState());
         }
      }
   }

   @Override
   public int processAction(Random random) {
      return 0;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      EnumFacingXType enumFacingXType = null;
      IBlockState iBlockState = iBlockAccess.getIBlockState(count, count2, count3);
      if (iBlockAccess.getIBlockState(count, count2, count3).getBlock() == this) {
         enumFacingXType = (EnumFacingXType)iBlockState.getComparable(immutableSetEntry);
         if (enumFacingXType == null) {
            return false;
         }

         if (enumFacingXType == EnumFacingXType.Z && enumFacing != EnumFacing.EAST && enumFacing != EnumFacing.WEST) {
            return false;
         }

         if (enumFacingXType == EnumFacingXType.X && enumFacing != EnumFacing.SOUTH && enumFacing != EnumFacing.NORTH) {
            return false;
         }
      }

      boolean flag = iBlockAccess.getIBlockState(count - 1, count2, count3).getBlock() == this && iBlockAccess.getIBlockState(count - 2, count2, count3).getBlock() != this;
      boolean flag2 = iBlockAccess.getIBlockState(count + 1, count2, count3).getBlock() == this && iBlockAccess.getIBlockState(count + 2, count2, count3).getBlock() != this;
      boolean flag3 = iBlockAccess.getIBlockState(count, count2, count3 - 1).getBlock() == this && iBlockAccess.getIBlockState(count, count2, count3 - 2).getBlock() != this;
      boolean flag4 = iBlockAccess.getIBlockState(count, count2, count3 + 1).getBlock() == this && iBlockAccess.getIBlockState(count, count2, count3 + 2).getBlock() != this;
      boolean flag5 = flag || flag2 || enumFacingXType == EnumFacingXType.X;
      boolean flag6 = flag3 || flag4 || enumFacingXType == EnumFacingXType.Z;
      return flag5 && enumFacing == EnumFacing.WEST
         ? true
         : (flag5 && enumFacing == EnumFacing.EAST ? true : (flag6 && enumFacing == EnumFacing.NORTH ? true : flag6 && enumFacing == EnumFacing.SOUTH));
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return computeCount((EnumFacingXType)blockState.getComparable(immutableSetEntry));
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
   }

   public boolean matchesWorld3(World world, BlockPos blockPos) {
      WorldEntry worldEntry = new WorldEntry(world, blockPos, EnumFacingXType.X);
      if (worldEntry.isActive() && WorldEntry.computeCountByWorldEntry(worldEntry) == 0) {
         worldEntry.updateState();
         return true;
      } else {
         WorldEntry worldEntry2 = new WorldEntry(world, blockPos, EnumFacingXType.Z);
         if (worldEntry2.isActive() && WorldEntry.computeCountByWorldEntry(worldEntry2) == 0) {
            worldEntry2.updateState();
            return true;
         } else {
            return false;
         }
      }
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (random.nextInt(100) == 0) {
         world.applyText(
            (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5, "portal.portal", 0.5F, random.nextFloat() * 0.4F + 0.8F, false
         );
      }

      for (int index = 0; index < 4; index++) {
         double offset = (double)((float)blockPos.getX() + random.nextFloat());
         double offset2 = (double)((float)blockPos.getY() + random.nextFloat());
         double offset3 = (double)((float)blockPos.getZ() + random.nextFloat());
         double offset4 = ((double)random.nextFloat() - 0.5) * 0.5;
         double offset5 = ((double)random.nextFloat() - 0.5) * 0.5;
         double offset6 = ((double)random.nextFloat() - 0.5) * 0.5;
         int count = random.nextInt(2) * 2 - 1;
         if (world.getBlockState(blockPos.getBlockPos()).getBlock() != this && world.getBlockState(blockPos.getBlockPos3()).getBlock() != this) {
            offset = (double)blockPos.getX() + 0.5 + 0.25 * (double)count;
            offset4 = (double)(random.nextFloat() * 2.0F * (float)count);
         } else {
            offset3 = (double)blockPos.getZ() + 0.5 + 0.25 * (double)count;
            offset6 = (double)(random.nextFloat() * 2.0F * (float)count);
         }

         world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.PORTAL, offset, offset2, offset3, offset4, offset5, offset6, new int[0]);
      }
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction17() {
      return false;
   }

   public BlockPortal() {
      super(Material.material17, false);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, EnumFacingXType.X));
      this.processAction(true);
   }
}
