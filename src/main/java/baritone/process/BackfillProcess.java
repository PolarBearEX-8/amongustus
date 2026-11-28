package baritone.process;

import baritone.pathing.movement.PlaceResult;
import baritone.Baritone;
import baritone.api.process.PathingCommand;
import baritone.api.process.PathingCommandType;
import baritone.api.utils.Rotation;
import baritone.api.utils.input.Input;
import baritone.pathing.path.PathExecutor;
import baritone.pathing.movement.Movement;
import baritone.pathing.movement.MovementHelper;
import baritone.pathing.movement.MovementState;
import baritone.utils.BaritoneProcessHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.chunk.EmptyChunk;

public final class BackfillProcess extends BaritoneProcessHelper {
   public HashMap blocksToReplace = new HashMap();

   @Override
   public double priority() {
      return 5.0;
   }

   public BackfillProcess(Baritone baritone2) {
      super(baritone2);
   }

   @Override
   public boolean isActive() {
      if (this.ctx.player() != null && this.ctx.world() != null) {
         if (!(Boolean)Baritone.settings().backfill.value) {
            return false;
         } else if ((Boolean)Baritone.settings().allowParkour.value) {
            this.logDirect2("Backfill cannot be used with allowParkour true");
            Baritone.settings().backfill.value = false;
            return false;
         } else {
            for (BlockPos pos : (Iterable<BlockPos>)(new ArrayList(this.blocksToReplace.keySet()))) {
               if (this.ctx.world().getChunkFromBlockCoords(pos) instanceof EmptyChunk || this.ctx.world().getBlockState(pos).getBlock() != Blocks.air) {
                  this.blocksToReplace.remove(pos);
               }
            }

            this.amIBreakingABlockHMMMMMMM();
            this.baritone.getInputOverrideHandler2().clearAllKeys();
            return !this.toFillIn().isEmpty();
         }
      } else {
         return false;
      }
   }

   public List toFillIn() {
      return (List)this.blocksToReplace
         .keySet()
         .stream()
         .filter(obj -> this.ctx.world().getBlockState((BlockPos)obj).getBlock() == Blocks.air)
         .filter(obj2 -> this.ctx.world().canBlockBePlaced(Blocks.dirt, (BlockPos)obj2, false, EnumFacing.UP, null, null))
         .filter(obj3 -> !this.partOfCurrentMovement((BlockPos)obj3))
         .sorted(Comparator.<BlockPos>comparingDouble(this.ctx.player()::getDistanceSq).reversed())
         .collect(Collectors.toList());
   }

   @Override
   public boolean isTemporary() {
      return true;
   }

   private void amIBreakingABlockHMMMMMMM() {
      if (this.ctx.getSelectedBlock().isPresent() && this.baritone.getPathingBehavior2().isPathing()) {
         this.blocksToReplace.put(this.ctx.getSelectedBlock().get(), this.ctx.world().getBlockState((BlockPos)this.ctx.getSelectedBlock().get()));
      }
   }

   @Override
   public void onLostControl() {
      if (this.blocksToReplace != null && !this.blocksToReplace.isEmpty()) {
         this.blocksToReplace.clear();
      }
   }

   @Override
   public String displayName0() {
      return "Backfill";
   }

   private boolean partOfCurrentMovement(BlockPos pos) {
      PathExecutor current2 = this.baritone.getPathingBehavior2().getCurrent2();
      if (current2 != null && !current2.finished() && !current2.isFailed()) {
         return Arrays.asList(((Movement)current2.getPath().movements().get(current2.getPosition())).getPositionsToBreak()).contains(pos);
      } else {
         return false;
      }
   }

   @Override
   public PathingCommand onTick(boolean flag, boolean flag2) {
      if (!flag2) {
         return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
      } else {
         this.baritone.getInputOverrideHandler2().clearAllKeys();

         for (BlockPos pos : (Iterable<BlockPos>)(this.toFillIn())) {
            MovementState movementState = new MovementState();
            switch (MovementHelper.attemptToPlaceABlock(movementState, this.baritone, pos, false, false)) {
               case NO_OPTION:
                  break;
               case READY_TO_PLACE:
                  this.baritone.getInputOverrideHandler2().setInputForceState(Input.CLICK_RIGHT, true);
                  return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
               case ATTEMPTING:
                  this.baritone.getLookBehavior2().updateTarget((Rotation)movementState.getTarget().getRotation().get(), true);
                  return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
               default:
                  throw new IllegalStateException();
            }
         }

         return new PathingCommand(null, PathingCommandType.DEFER);
      }
   }

}
