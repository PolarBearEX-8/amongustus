package baritone.pathing.movement;


import baritone.pathing.movement.movements.MovementAscend;
import baritone.pathing.movement.movements.MovementDescend;
import baritone.pathing.movement.movements.MovementDiagonal;
import baritone.pathing.movement.movements.MovementDownward;
import baritone.pathing.movement.movements.MovementFall;
import baritone.pathing.movement.movements.MovementParkour;
import baritone.pathing.movement.movements.MovementPillar;
import baritone.pathing.movement.movements.MovementTraverse;
import net.minecraft.util.EnumFacing;
import baritone.api.utils.BetterBlockPos;
import baritone.utils.pathing.MutableMoveResult;

public enum Moves {
   DOWNWARD(0, -1, 0) {
      @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            return new MovementDownward(calculationContext.getBaritone(), betterBlockPos, betterBlockPos.down2());
         }
      
         @Override
         public double cost(CalculationContext calculationContext, int n, int n2, int n3) {
            return MovementDownward.cost(calculationContext, n, n2, n3);
         }
   },
   PILLAR(0, 1, 0) {
      @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            return new MovementPillar(calculationContext.getBaritone(), betterBlockPos, betterBlockPos.up2());
         }
      
         @Override
         public double cost(CalculationContext calculationContext, int n, int n2, int n3) {
            return MovementPillar.cost(calculationContext, n, n2, n3);
         }
   },
   TRAVERSE_NORTH(0, 0, -1) {
      @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            return new MovementTraverse(calculationContext.getBaritone(), betterBlockPos, betterBlockPos.north2());
         }
      
         @Override
         public double cost(CalculationContext calculationContext, int n, int n2, int n3) {
            return MovementTraverse.cost(calculationContext, n, n2, n3, n, n3 - 1);
         }
   },
   TRAVERSE_SOUTH(0, 0, 1) {
      @Override
         public double cost(CalculationContext calculationContext, int n, int n2, int n3) {
            return MovementTraverse.cost(calculationContext, n, n2, n3, n, n3 + 1);
         }
      
      
         @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            return new MovementTraverse(calculationContext.getBaritone(), betterBlockPos, betterBlockPos.south2());
         }
   },
   TRAVERSE_EAST(1, 0, 0) {
      @Override
         public double cost(CalculationContext calculationContext, int n, int n2, int n3) {
            return MovementTraverse.cost(calculationContext, n, n2, n3, n + 1, n3);
         }
      
         @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            return new MovementTraverse(calculationContext.getBaritone(), betterBlockPos, betterBlockPos.east2());
         }
   },
   TRAVERSE_WEST(-1, 0, 0) {
      @Override
         public double cost(CalculationContext calculationContext, int n, int n2, int n3) {
            return MovementTraverse.cost(calculationContext, n, n2, n3, n - 1, n3);
         }
      
      
         @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            return new MovementTraverse(calculationContext.getBaritone(), betterBlockPos, betterBlockPos.west2());
         }
   },
   ASCEND_NORTH(0, 1, -1) {
      @Override
         public double cost(CalculationContext calculationContext, int n, int n2, int n3) {
            return MovementAscend.cost(calculationContext, n, n2, n3, n, n3 - 1);
         }
      
         @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            return new MovementAscend(calculationContext.getBaritone(), betterBlockPos, new BetterBlockPos(betterBlockPos.x2, betterBlockPos.y2 + 1, betterBlockPos.z2 - 1));
         }
   },
   ASCEND_SOUTH(0, 1, 1) {
      @Override
         public double cost(CalculationContext calculationContext, int n, int n2, int n3) {
            return MovementAscend.cost(calculationContext, n, n2, n3, n, n3 + 1);
         }
      
         @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            return new MovementAscend(calculationContext.getBaritone(), betterBlockPos, new BetterBlockPos(betterBlockPos.x2, betterBlockPos.y2 + 1, betterBlockPos.z2 + 1));
         }
   },
   ASCEND_EAST(1, 1, 0) {
      @Override
         public double cost(CalculationContext calculationContext, int n, int n2, int n3) {
            return MovementAscend.cost(calculationContext, n, n2, n3, n + 1, n3);
         }
      
         @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            return new MovementAscend(calculationContext.getBaritone(), betterBlockPos, new BetterBlockPos(betterBlockPos.x2 + 1, betterBlockPos.y2 + 1, betterBlockPos.z2));
         }
   },
   ASCEND_WEST(-1, 1, 0) {
      @Override
         public double cost(CalculationContext calculationContext, int n, int n2, int n3) {
            return MovementAscend.cost(calculationContext, n, n2, n3, n - 1, n3);
         }
      
         @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            return new MovementAscend(calculationContext.getBaritone(), betterBlockPos, new BetterBlockPos(betterBlockPos.x2 - 1, betterBlockPos.y2 + 1, betterBlockPos.z2));
         }
   },
   DESCEND_EAST(1, -1, 0, false, true) {
      @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            MutableMoveResult mutableMoveResult = new MutableMoveResult();
            this.apply(calculationContext, betterBlockPos.x2, betterBlockPos.y2, betterBlockPos.z2, mutableMoveResult);
            return (Movement)(mutableMoveResult.y == betterBlockPos.y2 - 1
               ? new MovementDescend(calculationContext.getBaritone(), betterBlockPos, new BetterBlockPos(mutableMoveResult.x, mutableMoveResult.y, mutableMoveResult.z))
               : new MovementFall(calculationContext.getBaritone(), betterBlockPos, new BetterBlockPos(mutableMoveResult.x, mutableMoveResult.y, mutableMoveResult.z)));
         }
      
         @Override
         public void apply(CalculationContext calculationContext, int n, int n2, int n3, MutableMoveResult mutableMoveResult) {
            MovementDescend.cost(calculationContext, n, n2, n3, n + 1, n3, mutableMoveResult);
         }
   },
   DESCEND_WEST(-1, -1, 0, false, true) {
      @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            MutableMoveResult mutableMoveResult = new MutableMoveResult();
            this.apply(calculationContext, betterBlockPos.x2, betterBlockPos.y2, betterBlockPos.z2, mutableMoveResult);
            return (Movement)(mutableMoveResult.y == betterBlockPos.y2 - 1
               ? new MovementDescend(calculationContext.getBaritone(), betterBlockPos, new BetterBlockPos(mutableMoveResult.x, mutableMoveResult.y, mutableMoveResult.z))
               : new MovementFall(calculationContext.getBaritone(), betterBlockPos, new BetterBlockPos(mutableMoveResult.x, mutableMoveResult.y, mutableMoveResult.z)));
         }
      
      
         @Override
         public void apply(CalculationContext calculationContext, int n, int n2, int n3, MutableMoveResult mutableMoveResult) {
            MovementDescend.cost(calculationContext, n, n2, n3, n - 1, n3, mutableMoveResult);
         }
   },
   DESCEND_NORTH(0, -1, -1, false, true) {
      @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            MutableMoveResult mutableMoveResult = new MutableMoveResult();
            this.apply(calculationContext, betterBlockPos.x2, betterBlockPos.y2, betterBlockPos.z2, mutableMoveResult);
            return (Movement)(mutableMoveResult.y == betterBlockPos.y2 - 1
               ? new MovementDescend(calculationContext.getBaritone(), betterBlockPos, new BetterBlockPos(mutableMoveResult.x, mutableMoveResult.y, mutableMoveResult.z))
               : new MovementFall(calculationContext.getBaritone(), betterBlockPos, new BetterBlockPos(mutableMoveResult.x, mutableMoveResult.y, mutableMoveResult.z)));
         }
      
         @Override
         public void apply(CalculationContext calculationContext, int n, int n2, int n3, MutableMoveResult mutableMoveResult) {
            MovementDescend.cost(calculationContext, n, n2, n3, n, n3 - 1, mutableMoveResult);
         }
   },
   DESCEND_SOUTH(0, -1, 1, false, true) {
      @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            MutableMoveResult mutableMoveResult = new MutableMoveResult();
            this.apply(calculationContext, betterBlockPos.x2, betterBlockPos.y2, betterBlockPos.z2, mutableMoveResult);
            return (Movement)(mutableMoveResult.y == betterBlockPos.y2 - 1
               ? new MovementDescend(calculationContext.getBaritone(), betterBlockPos, new BetterBlockPos(mutableMoveResult.x, mutableMoveResult.y, mutableMoveResult.z))
               : new MovementFall(calculationContext.getBaritone(), betterBlockPos, new BetterBlockPos(mutableMoveResult.x, mutableMoveResult.y, mutableMoveResult.z)));
         }
      
      
         @Override
         public void apply(CalculationContext calculationContext, int n, int n2, int n3, MutableMoveResult mutableMoveResult) {
            MovementDescend.cost(calculationContext, n, n2, n3, n, n3 + 1, mutableMoveResult);
         }
   },
   DIAGONAL_NORTHEAST(1, 0, -1, false, true) {
      @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            MutableMoveResult mutableMoveResult = new MutableMoveResult();
            this.apply(calculationContext, betterBlockPos.x2, betterBlockPos.y2, betterBlockPos.z2, mutableMoveResult);
            return new MovementDiagonal(calculationContext.getBaritone(), betterBlockPos, EnumFacing.NORTH, EnumFacing.EAST, mutableMoveResult.y - betterBlockPos.y2);
         }
      
         @Override
         public void apply(CalculationContext calculationContext, int n, int n2, int n3, MutableMoveResult mutableMoveResult) {
            MovementDiagonal.cost(calculationContext, n, n2, n3, n + 1, n3 - 1, mutableMoveResult);
         }
   },
   DIAGONAL_NORTHWEST(-1, 0, -1, false, true) {
      @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            MutableMoveResult mutableMoveResult = new MutableMoveResult();
            this.apply(calculationContext, betterBlockPos.x2, betterBlockPos.y2, betterBlockPos.z2, mutableMoveResult);
            return new MovementDiagonal(calculationContext.getBaritone(), betterBlockPos, EnumFacing.NORTH, EnumFacing.WEST, mutableMoveResult.y - betterBlockPos.y2);
         }
      
         @Override
         public void apply(CalculationContext calculationContext, int n, int n2, int n3, MutableMoveResult mutableMoveResult) {
            MovementDiagonal.cost(calculationContext, n, n2, n3, n - 1, n3 - 1, mutableMoveResult);
         }
   },
   DIAGONAL_SOUTHEAST(1, 0, 1, false, true) {
      @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            MutableMoveResult mutableMoveResult = new MutableMoveResult();
            this.apply(calculationContext, betterBlockPos.x2, betterBlockPos.y2, betterBlockPos.z2, mutableMoveResult);
            return new MovementDiagonal(calculationContext.getBaritone(), betterBlockPos, EnumFacing.SOUTH, EnumFacing.EAST, mutableMoveResult.y - betterBlockPos.y2);
         }
      
         @Override
         public void apply(CalculationContext calculationContext, int n, int n2, int n3, MutableMoveResult mutableMoveResult) {
            MovementDiagonal.cost(calculationContext, n, n2, n3, n + 1, n3 + 1, mutableMoveResult);
         }
   },
   DIAGONAL_SOUTHWEST(-1, 0, 1, false, true) {
      @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            MutableMoveResult mutableMoveResult = new MutableMoveResult();
            this.apply(calculationContext, betterBlockPos.x2, betterBlockPos.y2, betterBlockPos.z2, mutableMoveResult);
            return new MovementDiagonal(calculationContext.getBaritone(), betterBlockPos, EnumFacing.SOUTH, EnumFacing.WEST, mutableMoveResult.y - betterBlockPos.y2);
         }
      
      
         @Override
         public void apply(CalculationContext calculationContext, int n, int n2, int n3, MutableMoveResult mutableMoveResult) {
            MovementDiagonal.cost(calculationContext, n, n2, n3, n - 1, n3 + 1, mutableMoveResult);
         }
   },
   PARKOUR_NORTH(0, 0, -4, true, true) {
      @Override
         public void apply(CalculationContext calculationContext, int n, int n2, int n3, MutableMoveResult mutableMoveResult) {
            MovementParkour.cost(calculationContext, n, n2, n3, EnumFacing.NORTH, mutableMoveResult);
         }
      
         @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            return MovementParkour.cost(calculationContext, betterBlockPos, EnumFacing.NORTH);
         }
   },
   PARKOUR_SOUTH(0, 0, 4, true, true) {
      @Override
         public void apply(CalculationContext calculationContext, int n, int n2, int n3, MutableMoveResult mutableMoveResult) {
            MovementParkour.cost(calculationContext, n, n2, n3, EnumFacing.SOUTH, mutableMoveResult);
         }
      
         @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            return MovementParkour.cost(calculationContext, betterBlockPos, EnumFacing.SOUTH);
         }
   },
   PARKOUR_EAST(4, 0, 0, true, true) {
      @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            return MovementParkour.cost(calculationContext, betterBlockPos, EnumFacing.EAST);
         }
      
         @Override
         public void apply(CalculationContext calculationContext, int n, int n2, int n3, MutableMoveResult mutableMoveResult) {
            MovementParkour.cost(calculationContext, n, n2, n3, EnumFacing.EAST, mutableMoveResult);
         }
   },
   PARKOUR_WEST(-4, 0, 0, true, true) {
      @Override
         public Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
            return MovementParkour.cost(calculationContext, betterBlockPos, EnumFacing.WEST);
         }
      
         @Override
         public void apply(CalculationContext calculationContext, int n, int n2, int n3, MutableMoveResult mutableMoveResult) {
            MovementParkour.cost(calculationContext, n, n2, n3, EnumFacing.WEST, mutableMoveResult);
         }
   };

   public final boolean dynamicXZ;
   public final boolean dynamicY;
   public final int xOffset;
   public final int yOffset;
   public final int zOffset;
public void apply(CalculationContext calculationContext, int n, int n2, int n3, MutableMoveResult mutableMoveResult) {
      if (!this.dynamicXZ && !this.dynamicY) {
         mutableMoveResult.x = n + this.xOffset;
         mutableMoveResult.y = n2 + this.yOffset;
         mutableMoveResult.z = n3 + this.zOffset;
         mutableMoveResult.cost = this.cost(calculationContext, n, n2, n3);
      } else {
         throw new UnsupportedOperationException();
      }
   }

   private Moves(int n, int n2, int n3, boolean flag, boolean flag2) {
      this.xOffset = n;
      this.yOffset = n2;
      this.zOffset = n3;
      this.dynamicXZ = flag;
      this.dynamicY = flag2;
   }

   public abstract Movement apply0(CalculationContext calculationContext, BetterBlockPos betterBlockPos);

   public double cost(CalculationContext calculationContext, int n, int n2, int n3) {
      throw new UnsupportedOperationException();
   }

   private Moves(int n, int n2, int n3) {
      this(n, n2, n3, false, false);
   }
}
