package cc.baritone.utils;

import net.minecraft.util.EnumFacing;

import cc.baritone.api.ItemStackEntry;

public enum HeuristicPredictor {
   DOWNWARD(0, -1, 0) {
      @Override
      public double computeValue(ItemStackEntry itemStackEntry, int count, int count2, int count3) {
         return ItemStackEntryAStarPipelineAdapter.computeValue(itemStackEntry, count, count2, count3);
      }

      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry2, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         return new ItemStackEntryAStarPipelineAdapter(itemStackEntry2.getActiveListener(), betterBlockPosXSYSZSBlockPos, betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos4());
      }
   },
   PILLAR(0, 1, 0) {
      @Override
      public double computeValue(ItemStackEntry itemStackEntry, int count, int count2, int count3) {
         return UnableClimbAStarPipeline.computeValue(itemStackEntry, count, count2, count3);
      }

      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry2, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         return new UnableClimbAStarPipeline(itemStackEntry2.getActiveListener(), betterBlockPosXSYSZSBlockPos, betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos3());
      }
   },
   TRAVERSE_NORTH(0, 0, -1) {
      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         return new WrongCoordinateAStarPipeline(itemStackEntry.getActiveListener(), betterBlockPosXSYSZSBlockPos, betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos5());
      }


      @Override
      public double computeValue(ItemStackEntry itemStackEntry2, int count, int count2, int count3) {
         return WrongCoordinateAStarPipeline.computeValue(itemStackEntry2, count, count2, count3, count, count3 - 1);
      }
   },
   TRAVERSE_SOUTH(0, 0, 1) {
      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry, BetterBlockPosXSYSZSBlockPos origin) {
         return new WrongCoordinateAStarPipeline(itemStackEntry.getActiveListener(), origin, origin.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS());
      }

      @Override
      public double computeValue(ItemStackEntry itemStackEntry, int x, int y, int z) {
         return WrongCoordinateAStarPipeline.computeValue(itemStackEntry, x, y, z, x, z + 1);
      }
   },
   TRAVERSE_EAST(1, 0, 0) {
      @Override
      public double computeValue(ItemStackEntry itemStackEntry, int count, int count2, int count3) {
         return WrongCoordinateAStarPipeline.computeValue(itemStackEntry, count, count2, count3, count + 1, count3);
      }


      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry2, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         return new WrongCoordinateAStarPipeline(itemStackEntry2.getActiveListener(), betterBlockPosXSYSZSBlockPos, betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos2());
      }
   },
   TRAVERSE_WEST(-1, 0, 0) {
      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         return new WrongCoordinateAStarPipeline(itemStackEntry.getActiveListener(), betterBlockPosXSYSZSBlockPos, betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos());
      }

      @Override
      public double computeValue(ItemStackEntry itemStackEntry2, int count, int count2, int count3) {
         return WrongCoordinateAStarPipeline.computeValue(itemStackEntry2, count, count2, count3, count - 1, count3);
      }
   },
   ASCEND_NORTH(0, 1, -1) {
      @Override
      public double computeValue(ItemStackEntry itemStackEntry, int count, int count2, int count3) {
         return HeuristicPlannerAStarPipeline.computeValue(itemStackEntry, count, count2, count3, count, count3 - 1);
      }

      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry2, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         return new HeuristicPlannerAStarPipeline(
            itemStackEntry2.getActiveListener(), betterBlockPosXSYSZSBlockPos, new BetterBlockPosXSYSZSBlockPos(betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12 + 1, betterBlockPosXSYSZSBlockPos.count10 - 1)
         );
      }
   },
   ASCEND_SOUTH(0, 1, 1) {
      @Override
      public double computeValue(ItemStackEntry itemStackEntry, int count, int count2, int count3) {
         return HeuristicPlannerAStarPipeline.computeValue(itemStackEntry, count, count2, count3, count, count3 + 1);
      }


      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry2, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         return new HeuristicPlannerAStarPipeline(
            itemStackEntry2.getActiveListener(), betterBlockPosXSYSZSBlockPos, new BetterBlockPosXSYSZSBlockPos(betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12 + 1, betterBlockPosXSYSZSBlockPos.count10 + 1)
         );
      }
   },
   ASCEND_EAST(1, 1, 0) {
      @Override
      public double computeValue(ItemStackEntry itemStackEntry, int count, int count2, int count3) {
         return HeuristicPlannerAStarPipeline.computeValue(itemStackEntry, count, count2, count3, count + 1, count3);
      }


      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry2, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         return new HeuristicPlannerAStarPipeline(
            itemStackEntry2.getActiveListener(), betterBlockPosXSYSZSBlockPos, new BetterBlockPosXSYSZSBlockPos(betterBlockPosXSYSZSBlockPos.count9 + 1, betterBlockPosXSYSZSBlockPos.count12 + 1, betterBlockPosXSYSZSBlockPos.count10)
         );
      }
   },
   ASCEND_WEST(-1, 1, 0) {
      @Override
      public double computeValue(ItemStackEntry itemStackEntry, int count, int count2, int count3) {
         return HeuristicPlannerAStarPipeline.computeValue(itemStackEntry, count, count2, count3, count - 1, count3);
      }


      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry2, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         return new HeuristicPlannerAStarPipeline(
            itemStackEntry2.getActiveListener(), betterBlockPosXSYSZSBlockPos, new BetterBlockPosXSYSZSBlockPos(betterBlockPosXSYSZSBlockPos.count9 - 1, betterBlockPosXSYSZSBlockPos.count12 + 1, betterBlockPosXSYSZSBlockPos.count10)
         );
      }
   },
   DESCEND_EAST(1, -1, 0, false, true) {
      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         HeuristicCalculator heuristicCalculator = new HeuristicCalculator();
         this.applyItemStackEntry(itemStackEntry, betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos.count10, heuristicCalculator);
         return (AStarPipeline)(heuristicCalculator.count2 == betterBlockPosXSYSZSBlockPos.count12 - 1
            ? new ItemStackEntryAStarPipelineImpl(itemStackEntry.getActiveListener(), betterBlockPosXSYSZSBlockPos, new BetterBlockPosXSYSZSBlockPos(heuristicCalculator.count3, heuristicCalculator.count2, heuristicCalculator.count))
            : new ItemStackAStarPipeline(itemStackEntry.getActiveListener(), betterBlockPosXSYSZSBlockPos, new BetterBlockPosXSYSZSBlockPos(heuristicCalculator.count3, heuristicCalculator.count2, heuristicCalculator.count)));
      }


      @Override
      public void applyItemStackEntry(ItemStackEntry itemStackEntry2, int count, int count2, int count3, HeuristicCalculator heuristicCalculator2) {
         ItemStackEntryAStarPipelineImpl.applyItemStackEntry(itemStackEntry2, count, count2, count3, count + 1, count3, heuristicCalculator2);
      }
   },
   DESCEND_WEST(-1, -1, 0, false, true) {
      @Override
      public void applyItemStackEntry(ItemStackEntry itemStackEntry, int count, int count2, int count3, HeuristicCalculator heuristicCalculator) {
         ItemStackEntryAStarPipelineImpl.applyItemStackEntry(itemStackEntry, count, count2, count3, count - 1, count3, heuristicCalculator);
      }


      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry2, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         HeuristicCalculator heuristicCalculator2 = new HeuristicCalculator();
         this.applyItemStackEntry(itemStackEntry2, betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos.count10, heuristicCalculator2);
         return (AStarPipeline)(heuristicCalculator2.count2 == betterBlockPosXSYSZSBlockPos.count12 - 1
            ? new ItemStackEntryAStarPipelineImpl(itemStackEntry2.getActiveListener(), betterBlockPosXSYSZSBlockPos, new BetterBlockPosXSYSZSBlockPos(heuristicCalculator2.count3, heuristicCalculator2.count2, heuristicCalculator2.count))
            : new ItemStackAStarPipeline(itemStackEntry2.getActiveListener(), betterBlockPosXSYSZSBlockPos, new BetterBlockPosXSYSZSBlockPos(heuristicCalculator2.count3, heuristicCalculator2.count2, heuristicCalculator2.count)));
      }
   },
   DESCEND_NORTH(0, -1, -1, false, true) {
      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         HeuristicCalculator heuristicCalculator = new HeuristicCalculator();
         this.applyItemStackEntry(itemStackEntry, betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos.count10, heuristicCalculator);
         return (AStarPipeline)(heuristicCalculator.count2 == betterBlockPosXSYSZSBlockPos.count12 - 1
            ? new ItemStackEntryAStarPipelineImpl(itemStackEntry.getActiveListener(), betterBlockPosXSYSZSBlockPos, new BetterBlockPosXSYSZSBlockPos(heuristicCalculator.count3, heuristicCalculator.count2, heuristicCalculator.count))
            : new ItemStackAStarPipeline(itemStackEntry.getActiveListener(), betterBlockPosXSYSZSBlockPos, new BetterBlockPosXSYSZSBlockPos(heuristicCalculator.count3, heuristicCalculator.count2, heuristicCalculator.count)));
      }

      @Override
      public void applyItemStackEntry(ItemStackEntry itemStackEntry2, int count, int count2, int count3, HeuristicCalculator heuristicCalculator2) {
         ItemStackEntryAStarPipelineImpl.applyItemStackEntry(itemStackEntry2, count, count2, count3, count, count3 - 1, heuristicCalculator2);
      }
   },
   DESCEND_SOUTH(0, -1, 1, false, true) {
      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         HeuristicCalculator heuristicCalculator = new HeuristicCalculator();
         this.applyItemStackEntry(itemStackEntry, betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos.count10, heuristicCalculator);
         return (AStarPipeline)(heuristicCalculator.count2 == betterBlockPosXSYSZSBlockPos.count12 - 1
            ? new ItemStackEntryAStarPipelineImpl(itemStackEntry.getActiveListener(), betterBlockPosXSYSZSBlockPos, new BetterBlockPosXSYSZSBlockPos(heuristicCalculator.count3, heuristicCalculator.count2, heuristicCalculator.count))
            : new ItemStackAStarPipeline(itemStackEntry.getActiveListener(), betterBlockPosXSYSZSBlockPos, new BetterBlockPosXSYSZSBlockPos(heuristicCalculator.count3, heuristicCalculator.count2, heuristicCalculator.count)));
      }

      @Override
      public void applyItemStackEntry(ItemStackEntry itemStackEntry2, int count, int count2, int count3, HeuristicCalculator heuristicCalculator2) {
         ItemStackEntryAStarPipelineImpl.applyItemStackEntry(itemStackEntry2, count, count2, count3, count, count3 + 1, heuristicCalculator2);
      }
   },
   DIAGONAL_NORTHEAST(1, 0, -1, false, true) {
      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry, BetterBlockPosXSYSZSBlockPos origin) {
         HeuristicCalculator result = new HeuristicCalculator();
         this.applyItemStackEntry(itemStackEntry, origin.count9, origin.count12, origin.count10, result);
         return new ItemStackEntryAStarPipeline(itemStackEntry.getActiveListener(), origin, net.minecraft.util.EnumFacing.NORTH, net.minecraft.util.EnumFacing.EAST, result.count2 - origin.count12);
      }

      @Override
      public void applyItemStackEntry(ItemStackEntry itemStackEntry, int x, int y, int z, HeuristicCalculator result) {
         ItemStackEntryAStarPipeline.applyItemStackEntry(itemStackEntry, x, y, z, x + 1, z - 1, result);
      }
   },
   DIAGONAL_NORTHWEST(-1, 0, -1, false, true) {
      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         HeuristicCalculator heuristicCalculator = new HeuristicCalculator();
         this.applyItemStackEntry(itemStackEntry, betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos.count10, heuristicCalculator);
         return new ItemStackEntryAStarPipeline(itemStackEntry.getActiveListener(), betterBlockPosXSYSZSBlockPos, EnumFacing.NORTH, EnumFacing.WEST, heuristicCalculator.count2 - betterBlockPosXSYSZSBlockPos.count12);
      }


      @Override
      public void applyItemStackEntry(ItemStackEntry itemStackEntry2, int count, int count2, int count3, HeuristicCalculator heuristicCalculator2) {
         ItemStackEntryAStarPipeline.applyItemStackEntry(itemStackEntry2, count, count2, count3, count - 1, count3 - 1, heuristicCalculator2);
      }
   },
   DIAGONAL_SOUTHEAST(1, 0, 1, false, true) {
      @Override
      public void applyItemStackEntry(ItemStackEntry itemStackEntry, int count, int count2, int count3, HeuristicCalculator heuristicCalculator) {
         ItemStackEntryAStarPipeline.applyItemStackEntry(itemStackEntry, count, count2, count3, count + 1, count3 + 1, heuristicCalculator);
      }


      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry2, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         HeuristicCalculator heuristicCalculator2 = new HeuristicCalculator();
         this.applyItemStackEntry(itemStackEntry2, betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos.count10, heuristicCalculator2);
         return new ItemStackEntryAStarPipeline(itemStackEntry2.getActiveListener(), betterBlockPosXSYSZSBlockPos, EnumFacing.SOUTH, EnumFacing.EAST, heuristicCalculator2.count2 - betterBlockPosXSYSZSBlockPos.count12);
      }
   },
   DIAGONAL_SOUTHWEST(-1, 0, 1, false, true) {
      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         HeuristicCalculator heuristicCalculator = new HeuristicCalculator();
         this.applyItemStackEntry(itemStackEntry, betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos.count10, heuristicCalculator);
         return new ItemStackEntryAStarPipeline(itemStackEntry.getActiveListener(), betterBlockPosXSYSZSBlockPos, EnumFacing.SOUTH, EnumFacing.WEST, heuristicCalculator.count2 - betterBlockPosXSYSZSBlockPos.count12);
      }


      @Override
      public void applyItemStackEntry(ItemStackEntry itemStackEntry2, int count, int count2, int count3, HeuristicCalculator heuristicCalculator2) {
         ItemStackEntryAStarPipeline.applyItemStackEntry(itemStackEntry2, count, count2, count3, count - 1, count3 + 1, heuristicCalculator2);
      }
   },
   PARKOUR_NORTH(0, 0, -4, true, true) {
      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         return LOLSorryAStarPipeline.resolveLOLSorryAStarPipeline(itemStackEntry, betterBlockPosXSYSZSBlockPos, EnumFacing.NORTH);
      }

      @Override
      public void applyItemStackEntry(ItemStackEntry itemStackEntry2, int count, int count2, int count3, HeuristicCalculator heuristicCalculator) {
         LOLSorryAStarPipeline.applyItemStackEntry(itemStackEntry2, count, count2, count3, EnumFacing.NORTH, heuristicCalculator);
      }
   },
   PARKOUR_SOUTH(0, 0, 4, true, true) {
      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry, BetterBlockPosXSYSZSBlockPos origin) {
         return LOLSorryAStarPipeline.resolveLOLSorryAStarPipeline(itemStackEntry, origin, net.minecraft.util.EnumFacing.SOUTH);
      }

      @Override
      public void applyItemStackEntry(ItemStackEntry itemStackEntry, int x, int y, int z, HeuristicCalculator result) {
         LOLSorryAStarPipeline.applyItemStackEntry(itemStackEntry, x, y, z, net.minecraft.util.EnumFacing.SOUTH, result);
      }
   },
   PARKOUR_EAST(4, 0, 0, true, true) {
      @Override
      public void applyItemStackEntry(ItemStackEntry itemStackEntry, int count, int count2, int count3, HeuristicCalculator heuristicCalculator) {
         LOLSorryAStarPipeline.applyItemStackEntry(itemStackEntry, count, count2, count3, EnumFacing.EAST, heuristicCalculator);
      }

      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry2, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
         return LOLSorryAStarPipeline.resolveLOLSorryAStarPipeline(itemStackEntry2, betterBlockPosXSYSZSBlockPos, EnumFacing.EAST);
      }
   },
   PARKOUR_WEST(-4, 0, 0, true, true) {
      @Override
      public AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry, BetterBlockPosXSYSZSBlockPos origin) {
         return LOLSorryAStarPipeline.resolveLOLSorryAStarPipeline(itemStackEntry, origin, net.minecraft.util.EnumFacing.WEST);
      }

      @Override
      public void applyItemStackEntry(ItemStackEntry itemStackEntry, int x, int y, int z, HeuristicCalculator result) {
         LOLSorryAStarPipeline.applyItemStackEntry(itemStackEntry, x, y, z, net.minecraft.util.EnumFacing.WEST, result);
      }
   };

   public final boolean dynamicXZ;
   public final boolean dynamicY;
   public final int xOffset;
   public final int yOffset;
   public final int zOffset;
   public static final HeuristicPredictor[] ENUM_VALUES = values();

   public abstract AStarPipeline resolveAStarPipeline(ItemStackEntry itemStackEntry, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos);

   public double computeValue(ItemStackEntry itemStackEntry, int count, int count2, int count3) {
      throw new UnsupportedOperationException();
   }

   private HeuristicPredictor(int count, int count2, int count3) {
      this(count, count2, count3, false, false);
   }

   private HeuristicPredictor(int count, int count2, int count3, boolean flag, boolean flag2) {
      this.xOffset = count;
      this.yOffset = count2;
      this.zOffset = count3;
      this.dynamicXZ = flag;
      this.dynamicY = flag2;
   }


   public void applyItemStackEntry(ItemStackEntry itemStackEntry, int count, int count2, int count3, HeuristicCalculator heuristicCalculator) {
      if (!this.dynamicXZ && !this.dynamicY) {
         heuristicCalculator.count3 = count + this.xOffset;
         heuristicCalculator.count2 = count2 + this.yOffset;
         heuristicCalculator.count = count3 + this.zOffset;
         heuristicCalculator.value = this.computeValue(itemStackEntry, count, count2, count3);
      } else {
         throw new UnsupportedOperationException();
      }
   }

}
