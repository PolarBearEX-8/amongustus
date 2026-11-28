package baritone.api.pathing.movement;

import baritone.api.utils.BetterBlockPos;
import net.minecraft.util.BlockPos;

public interface IMovement {
   boolean calculatedWhileLoaded();

   BetterBlockPos getSrc();

   void resetBlockCache();

   boolean safeToCancel();

   double getCost();

   BlockPos getDirection();

   void reset();

   BetterBlockPos getDest();

   MovementStatus update();
}
