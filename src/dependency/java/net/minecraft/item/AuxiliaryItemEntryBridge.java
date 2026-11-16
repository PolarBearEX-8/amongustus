package net.minecraft.item;

import client.util.ClientManagerAdapter2;
import client.util.CommonIntArrayEntry;
import client.util.DistanceAggregator;
import client.util.GlobalListEntry;
import client.util.IBlockState;
import client.util.VectorEvaluator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobspawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class AuxiliaryItemEntryBridge extends GoldenIngot {
   public boolean enabled;
   public boolean enabled2;
   public boolean enabled3;
   public int count2;

   public AuxiliaryItemEntryBridge() {
   }

   @Override
   public boolean matchesWorld(World world, DistanceAggregator distanceAggregator, Random random, int count, int count3, int count4, List list, int count5) {
      BlockPos blockPos = new BlockPos(this.computeCountByIndex(count, count4), this.computeCount(count3), this.computeCount(count, count4));
      if (distanceAggregator.matchesVec3i(blockPos) && world.getBlockState(blockPos).getBlock().getMaterial() == Material.coreItemEntry) {
         byte byteValue = ((byte)((random.nextBoolean()) ? 1 : 0));
         world.matchesBlockPos(blockPos, Blocks.block13.processAction(this.computeCount(Blocks.block13, byteValue)), 2);
         VectorEvaluator vectorEvaluator = new VectorEvaluator(
            world, (double)((float)blockPos.getX() + 0.5F), (double)((float)blockPos.getY() + 0.5F), (double)((float)blockPos.getZ() + 0.5F)
         );
         ClientManagerAdapter2.applyRandom(random, list, vectorEvaluator, count5);
         world.matchesEntity(vectorEvaluator);
         return true;
      } else {
         return false;
      }
   }

   public static DistanceAggregator resolveDistanceAggregator(List list, Random random, int count, int count3, int count4, EnumFacing enumFacing) {
      DistanceAggregator distanceAggregator = new DistanceAggregator(count, count3, count4, count, count3 + 2, count4);

      int count5;
      for (count5 = random.nextInt(3) + 2; count5 > 0; count5--) {
         int count6 = count5 * 5;
         switch (CommonIntArrayEntry.countArray[enumFacing.ordinal()]) {
            case 1:
               distanceAggregator.count5 = count + 2;
               distanceAggregator.count4 = count4 - (count6 - 1);
               break;
            case 2:
               distanceAggregator.count5 = count + 2;
               distanceAggregator.count = count4 + (count6 - 1);
               break;
            case 3:
               distanceAggregator.count3 = count - (count6 - 1);
               distanceAggregator.count = count4 + 2;
               break;
            case 4:
               distanceAggregator.count5 = count + (count6 - 1);
               distanceAggregator.count = count4 + 2;
         }

         if (GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null) {
            break;
         }
      }

      return count5 > 0 ? distanceAggregator : null;
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesWorld(world, distanceAggregator)) {
         return false;
      } else {
         int count = this.count2 * 5 - 1;
         this.applyWorld(world, distanceAggregator, 0, 0, 0, 2, 1, count, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, random, 0.8F, 0, 2, 0, 2, 2, count, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         if (this.enabled2) {
            this.applyWorld(world, distanceAggregator, random, 0.6F, 0, 0, 0, 2, 1, count, Blocks.web.getDefaultState(), Blocks.air.getDefaultState(), false);
         }

         for (int index = 0; index < this.count2; index++) {
            int count3 = 2 + index * 5;
            this.applyWorld(world, distanceAggregator, 0, 0, count3, 0, 1, count3, Blocks.block111.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.applyWorld(world, distanceAggregator, 2, 0, count3, 2, 1, count3, Blocks.block111.getDefaultState(), Blocks.air.getDefaultState(), false);
            if (random.nextInt(4) == 0) {
               this.applyWorld(world, distanceAggregator, 0, 2, count3, 0, 2, count3, Blocks.block142.getDefaultState(), Blocks.air.getDefaultState(), false);
               this.applyWorld(world, distanceAggregator, 2, 2, count3, 2, 2, count3, Blocks.block142.getDefaultState(), Blocks.air.getDefaultState(), false);
            } else {
               this.applyWorld(world, distanceAggregator, 0, 2, count3, 2, 2, count3, Blocks.block142.getDefaultState(), Blocks.air.getDefaultState(), false);
            }

            this.applyWorld(world, distanceAggregator, random, 0.1F, 0, 2, count3 - 1, Blocks.web.getDefaultState());
            this.applyWorld(world, distanceAggregator, random, 0.1F, 2, 2, count3 - 1, Blocks.web.getDefaultState());
            this.applyWorld(world, distanceAggregator, random, 0.1F, 0, 2, count3 + 1, Blocks.web.getDefaultState());
            this.applyWorld(world, distanceAggregator, random, 0.1F, 2, 2, count3 + 1, Blocks.web.getDefaultState());
            this.applyWorld(world, distanceAggregator, random, 0.05F, 0, 2, count3 - 2, Blocks.web.getDefaultState());
            this.applyWorld(world, distanceAggregator, random, 0.05F, 2, 2, count3 - 2, Blocks.web.getDefaultState());
            this.applyWorld(world, distanceAggregator, random, 0.05F, 0, 2, count3 + 2, Blocks.web.getDefaultState());
            this.applyWorld(world, distanceAggregator, random, 0.05F, 2, 2, count3 + 2, Blocks.web.getDefaultState());
            this.applyWorld(world, distanceAggregator, random, 0.05F, 1, 2, count3 - 1, Blocks.block21.processAction(EnumFacing.UP.getIndexOffset1()));
            this.applyWorld(world, distanceAggregator, random, 0.05F, 1, 2, count3 + 1, Blocks.block21.processAction(EnumFacing.UP.getIndexOffset1()));
            if (random.nextInt(100) == 0) {
               this.matchesWorld(
                  world,
                  distanceAggregator,
                  random,
                  2,
                  0,
                  count3 - 1,
                  ClientManagerAdapter2.resolveList(
                     GlobalListEntry.getList(), new ClientManagerAdapter2[]{Items.enchantedBook.resolveClientManagerAdapter2(random)}
                  ),
                  3 + random.nextInt(4)
               );
            }

            if (random.nextInt(100) == 0) {
               this.matchesWorld(
                  world,
                  distanceAggregator,
                  random,
                  0,
                  0,
                  count3 + 1,
                  ClientManagerAdapter2.resolveList(
                     GlobalListEntry.getList(), new ClientManagerAdapter2[]{Items.enchantedBook.resolveClientManagerAdapter2(random)}
                  ),
                  3 + random.nextInt(4)
               );
            }

            if (this.enabled2 && !this.enabled3) {
               int count4 = this.computeCount(0);
               int count5 = count3 - 1 + random.nextInt(3);
               int count6 = this.computeCountByIndex(1, count5);
               int count7 = this.computeCount(1, count5);
               BlockPos blockPos = new BlockPos(count6, count4, count7);
               if (distanceAggregator.matchesVec3i(blockPos)) {
                  this.enabled3 = true;
                  world.matchesBlockPos(blockPos, Blocks.block29.getDefaultState(), 2);
                  TileEntity tileEntity = world.resolveTileEntity(blockPos);
                  if (tileEntity instanceof TileEntityMobspawner) {
                     ((TileEntityMobspawner)tileEntity).getPassiveStringEntry().setLabelText("CaveSpider");
                  }
               }
            }
         }

         for (int index2 = 0; index2 <= 2; index2++) {
            for (int index3 = 0; index3 <= count; index3++) {
               if (this.resolveIBlockState(world, index2, -1, index3, distanceAggregator).getBlock().getMaterial() == Material.coreItemEntry) {
                  this.applyWorld(world, Blocks.block142.getDefaultState(), index2, -1, index3, distanceAggregator);
               }
            }
         }

         if (this.enabled) {
            for (int index4 = 0; index4 <= count; index4++) {
               IBlockState blockState = this.resolveIBlockState(world, 1, -1, index4, distanceAggregator);
               if (blockState.getBlock().getMaterial() != Material.coreItemEntry && blockState.getBlock().isState18()) {
                  this.applyWorld(world, distanceAggregator, random, 0.7F, 1, 0, index4, Blocks.block13.processAction(this.computeCount(Blocks.block13, 0)));
               }
            }
         }

         return true;
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.applyText("hr", this.enabled);
      tagCompound.applyText("sc", this.enabled2);
      tagCompound.applyText("hps", this.enabled3);
      tagCompound.put("Num", this.count2);
   }

   public AuxiliaryItemEntryBridge(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
      this.enabled = random.nextInt(3) == 0;
      this.enabled2 = !this.enabled && random.nextInt(23) == 0;
      if (this.enumFacing != EnumFacing.NORTH && this.enumFacing != EnumFacing.SOUTH) {
         this.count2 = distanceAggregator.getCountAsIndex() / 5;
      } else {
         this.count2 = distanceAggregator.getCount() / 5;
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      this.enabled = tagCompound.matchesText("hr");
      this.enabled2 = tagCompound.matchesText("sc");
      this.enabled3 = tagCompound.matchesText("hps");
      this.count2 = tagCompound.computeCount("Num");
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      int count = this.getCount();
      int count3 = random.nextInt(4);
      if (this.enumFacing != null) {
         switch (CommonIntArrayEntry.countArray[this.enumFacing.ordinal()]) {
            case 1:
               if (count3 <= 1) {
                  GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                     goldenIngot,
                     list,
                     random,
                     this.distanceAggregator.count3,
                     this.distanceAggregator.count6 - 1 + random.nextInt(3),
                     this.distanceAggregator.count4 - 1,
                     this.enumFacing,
                     count
                  );
               } else if (count3 == 2) {
                  GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                     goldenIngot,
                     list,
                     random,
                     this.distanceAggregator.count3 - 1,
                     this.distanceAggregator.count6 - 1 + random.nextInt(3),
                     this.distanceAggregator.count4,
                     EnumFacing.WEST,
                     count
                  );
               } else {
                  GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                     goldenIngot,
                     list,
                     random,
                     this.distanceAggregator.count5 + 1,
                     this.distanceAggregator.count6 - 1 + random.nextInt(3),
                     this.distanceAggregator.count4,
                     EnumFacing.EAST,
                     count
                  );
               }
               break;
            case 2:
               if (count3 <= 1) {
                  GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                     goldenIngot,
                     list,
                     random,
                     this.distanceAggregator.count3,
                     this.distanceAggregator.count6 - 1 + random.nextInt(3),
                     this.distanceAggregator.count + 1,
                     this.enumFacing,
                     count
                  );
               } else if (count3 == 2) {
                  GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                     goldenIngot,
                     list,
                     random,
                     this.distanceAggregator.count3 - 1,
                     this.distanceAggregator.count6 - 1 + random.nextInt(3),
                     this.distanceAggregator.count - 3,
                     EnumFacing.WEST,
                     count
                  );
               } else {
                  GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                     goldenIngot,
                     list,
                     random,
                     this.distanceAggregator.count5 + 1,
                     this.distanceAggregator.count6 - 1 + random.nextInt(3),
                     this.distanceAggregator.count - 3,
                     EnumFacing.EAST,
                     count
                  );
               }
               break;
            case 3:
               if (count3 <= 1) {
                  GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                     goldenIngot,
                     list,
                     random,
                     this.distanceAggregator.count3 - 1,
                     this.distanceAggregator.count6 - 1 + random.nextInt(3),
                     this.distanceAggregator.count4,
                     this.enumFacing,
                     count
                  );
               } else if (count3 == 2) {
                  GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                     goldenIngot,
                     list,
                     random,
                     this.distanceAggregator.count3,
                     this.distanceAggregator.count6 - 1 + random.nextInt(3),
                     this.distanceAggregator.count4 - 1,
                     EnumFacing.NORTH,
                     count
                  );
               } else {
                  GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                     goldenIngot,
                     list,
                     random,
                     this.distanceAggregator.count3,
                     this.distanceAggregator.count6 - 1 + random.nextInt(3),
                     this.distanceAggregator.count + 1,
                     EnumFacing.SOUTH,
                     count
                  );
               }
               break;
            case 4:
               if (count3 <= 1) {
                  GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                     goldenIngot,
                     list,
                     random,
                     this.distanceAggregator.count5 + 1,
                     this.distanceAggregator.count6 - 1 + random.nextInt(3),
                     this.distanceAggregator.count4,
                     this.enumFacing,
                     count
                  );
               } else if (count3 == 2) {
                  GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                     goldenIngot,
                     list,
                     random,
                     this.distanceAggregator.count5 - 3,
                     this.distanceAggregator.count6 - 1 + random.nextInt(3),
                     this.distanceAggregator.count4 - 1,
                     EnumFacing.NORTH,
                     count
                  );
               } else {
                  GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                     goldenIngot,
                     list,
                     random,
                     this.distanceAggregator.count5 - 3,
                     this.distanceAggregator.count6 - 1 + random.nextInt(3),
                     this.distanceAggregator.count + 1,
                     EnumFacing.SOUTH,
                     count
                  );
               }
         }
      }

      if (count < 8) {
         if (this.enumFacing != EnumFacing.NORTH && this.enumFacing != EnumFacing.SOUTH) {
            for (int index = this.distanceAggregator.count3 + 3; index + 3 <= this.distanceAggregator.count5; index += 5) {
               int count4 = random.nextInt(5);
               if (count4 == 0) {
                  GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                     goldenIngot, list, random, index, this.distanceAggregator.count6, this.distanceAggregator.count4 - 1, EnumFacing.NORTH, count + 1
                  );
               } else if (count4 == 1) {
                  GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                     goldenIngot, list, random, index, this.distanceAggregator.count6, this.distanceAggregator.count + 1, EnumFacing.SOUTH, count + 1
                  );
               }
            }
         } else {
            for (int index2 = this.distanceAggregator.count4 + 3; index2 + 3 <= this.distanceAggregator.count; index2 += 5) {
               int count5 = random.nextInt(5);
               if (count5 == 0) {
                  GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                     goldenIngot, list, random, this.distanceAggregator.count3 - 1, this.distanceAggregator.count6, index2, EnumFacing.WEST, count + 1
                  );
               } else if (count5 == 1) {
                  GlobalListEntry.resolveGoldenIngotByGoldenIngot(
                     goldenIngot, list, random, this.distanceAggregator.count5 + 1, this.distanceAggregator.count6, index2, EnumFacing.EAST, count + 1
                  );
               }
            }
         }
      }
   }
}
