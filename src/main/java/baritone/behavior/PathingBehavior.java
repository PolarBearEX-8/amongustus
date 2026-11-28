package baritone.behavior;

import baritone.Baritone;
import baritone.api.behavior.IPathingBehavior;
import baritone.api.event.events.PathEvent;
import baritone.api.event.events.PlayerUpdateEvent;
import baritone.api.event.events.RenderEvent;
import baritone.api.event.events.SprintStateEvent;
import baritone.api.event.events.TickEvent;
import baritone.api.pathing.calc.IPath;
import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.goals.GoalXZ;
import baritone.api.process.PathingCommand;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.Helper;
import baritone.utils.pathing.PathingCommandContext;
import baritone.api.pathing.path.IPathExecutor;
import baritone.api.utils.PathCalculationResult;
import baritone.pathing.path.PathExecutor;
import baritone.api.utils.PathCalculationResultType;
import baritone.api.utils.interfaces.IGoalRenderPos;
import baritone.pathing.calc.AStarPathFinder;
import baritone.pathing.calc.AbstractNodeCostSearch;
import baritone.pathing.movement.CalculationContext;
import baritone.pathing.movement.MovementHelper;
import baritone.utils.PathRenderer;
import baritone.utils.pathing.Favoring;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import net.minecraft.util.BlockPos;

public final class PathingBehavior extends Behavior implements IPathingBehavior, Helper {
   private PathExecutor current;
   private PathExecutor next;
   private Goal goal;
   private CalculationContext context;
   private int ticksElapsedSoFar;
   private BetterBlockPos startPosition;
   private boolean safeToCancel;
   private boolean pauseRequestedLastTick;
   private boolean unpausedLastTick;
   private boolean pausedThisTick;
   private boolean cancelRequested;
   private boolean calcFailedLastTick;
   private volatile AbstractNodeCostSearch inProgress;
   private final Object pathCalcLock = new Object();
   private final Object pathPlanLock = new Object();
   private BetterBlockPos expectedSegmentStart;
   private final LinkedBlockingQueue toDispatch = new LinkedBlockingQueue();

   public PathingBehavior(Baritone baritone2) {
      super(baritone2);
   }

   @Override
   public void onPlayerSprintState(SprintStateEvent sprintStateEvent) {
      if (this.isPathing()) {
         sprintStateEvent.setState(this.current.isSprintNextTick());
      }
   }

   public PathExecutor getCurrent2() {
      return this.current;
   }

   public boolean cancelSegmentIfSafe() {
      if (this.isSafeToCancel()) {
         this.secretInternalSegmentCancel();
         return true;
      } else {
         return false;
      }
   }

   public BetterBlockPos pathStart() {
      BetterBlockPos betterBlockPos = this.ctx.playerFeet();
      if (!MovementHelper.canWalkOn2(this.ctx, betterBlockPos.down2())) {
         if (this.ctx.player().onGround) {
            double posX = this.ctx.player().posX;
            double posZ = this.ctx.player().posZ;
            ArrayList list = new ArrayList();

            for (int j = -1; j <= 1; j++) {
               for (int k = -1; k <= 1; k++) {
                  list.add(new BetterBlockPos(betterBlockPos.x2 + j, betterBlockPos.y2, betterBlockPos.z2 + k));
               }
            }

            list.sort(
               Comparator.comparingDouble(
                  obj -> ((double)((BetterBlockPos)obj).x2 + 0.5 - posX) * ((double)((BetterBlockPos)obj).x2 + 0.5 - posX)
                        + ((double)((BetterBlockPos)obj).z2 + 0.5 - posZ) * ((double)((BetterBlockPos)obj).z2 + 0.5 - posZ)
               )
            );

            for (int m = 0; m < 4; m++) {
               BetterBlockPos betterBlockPos2 = (BetterBlockPos)list.get(m);
               double d = Math.abs((double)betterBlockPos2.x2 + 0.5 - posX);
               double d2 = Math.abs((double)betterBlockPos2.z2 + 0.5 - posZ);
               if ((!(d > 0.8) || !(d2 > 0.8))
                  && MovementHelper.canWalkOn2(this.ctx, betterBlockPos2.down2())
                  && MovementHelper.canWalkThrough(this.ctx, betterBlockPos2)
                  && MovementHelper.canWalkThrough(this.ctx, betterBlockPos2.up2())) {
                  return betterBlockPos2;
               }
            }
         } else if (MovementHelper.canWalkOn2(this.ctx, betterBlockPos.down2().down2())) {
            return betterBlockPos.down2();
         }
      }

      return betterBlockPos;
   }

   private void resetStartPosition() {
      this.resetStartPosition2(this.expectedSegmentStart);
   }

   @Override
   public void forceCancel() {
      this.cancelEverything();
      this.secretInternalSegmentCancel();
      synchronized (this.pathCalcLock) {
         this.inProgress = null;
      }
   }

   public boolean isCalcFailedLastTick() {
      return this.calcFailedLastTick;
   }

   @Override
   public void onRenderPass(RenderEvent renderEvent) {
      PathRenderer.render(renderEvent, this);
   }

   private void resetStartPosition2(BetterBlockPos betterBlockPos) {
      this.ticksElapsedSoFar = 0;
      this.startPosition = betterBlockPos;
   }

   @Override
   public void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
   }

   private void dispatchEvents() {
      ArrayList list = new ArrayList();
      this.toDispatch.drainTo(list);
      this.calcFailedLastTick = list.contains(PathEvent.CALC_FAILED);

      for (PathEvent pathEvent : (Iterable<PathEvent>)(list)) {
         this.baritone.getGameEventHandler().onPathEvent(pathEvent);
      }
   }

   @Override
   public Goal getGoal() {
      return this.goal;
   }

   @Override
   public IPathExecutor getCurrent() {
      return this.getCurrent2();
   }

   private void findPathInNewThread(BlockPos pos, boolean flag, CalculationContext calculationContext) {
      if (!Thread.holdsLock(this.pathCalcLock)) {
         throw new IllegalStateException("Must be called with synchronization on pathCalcLock");
      } else if (this.inProgress != null) {
         throw new IllegalStateException("Already doing it");
      } else if (!calculationContext.safeForThreadedUse) {
         throw new IllegalStateException("Improper context thread safety level");
      } else {
         Goal goal2 = this.goal;
         if (goal2 == null) {
            this.logDebug("no goal");
         } else {
            long l;
            long l2;
            if (this.current == null) {
               l = (Long)Baritone.settings().primaryTimeoutMS.value;
               l2 = (Long)Baritone.settings().failureTimeoutMS.value;
            } else {
               l = (Long)Baritone.settings().planAheadPrimaryTimeoutMS.value;
               l2 = (Long)Baritone.settings().planAheadFailureTimeoutMS.value;
            }

            AbstractNodeCostSearch nodeCostSearch = createPathfinder(pos, goal2, this.current == null ? null : this.current.getPath(), calculationContext);
            if (!Objects.equals(nodeCostSearch.getGoal(), goal2)) {
               this.logDebug("Simplifying " + goal2.getClass() + " to GoalXZ due to distance");
            }

            this.inProgress = nodeCostSearch;
            Executor threadPool = Baritone.getThreadPool();
            long l3 = l2;
            long l4 = l;
            threadPool.execute(() -> {
               if (flag) {
                  this.logDebug("Starting to search for path from " + pos + " to " + goal2);
               }

               PathCalculationResult pathCalculationResult = nodeCostSearch.calculate(l4, l3);
               synchronized (this.pathPlanLock) {
                  Optional optional = pathCalculationResult.getPath().map(obj -> new PathExecutor(this, (IPath)obj));
                  if (this.current == null) {
                     if (optional.isPresent()) {
                        if (((PathExecutor)optional.get()).getPath().positions().contains(this.expectedSegmentStart)) {
                           this.queuePathEvent(PathEvent.CALC_FINISHED_NOW_EXECUTING);
                           this.current = (PathExecutor)optional.get();
                           this.resetStartPosition(pos);
                        } else {
                           this.logDebug("Warning: discarding orphan path segment with incorrect start");
                        }
                     } else if (pathCalculationResult.getType() != PathCalculationResultType.CANCELLATION && pathCalculationResult.getType() != PathCalculationResultType.EXCEPTION) {
                        this.queuePathEvent(PathEvent.CALC_FAILED);
                     }
                  } else if (this.next == null) {
                     if (optional.isPresent()) {
                        if (((PathExecutor)optional.get()).getPath().getSrc().equals(this.current.getPath().getDest())) {
                           this.queuePathEvent(PathEvent.NEXT_SEGMENT_CALC_FINISHED);
                           this.next = (PathExecutor)optional.get();
                        } else {
                           this.logDebug("Warning: discarding orphan next segment with incorrect start");
                        }
                     } else {
                        this.queuePathEvent(PathEvent.NEXT_CALC_FAILED);
                     }
                  } else {
                     this.logDirect2("Warning: PathingBehaivor illegal state! Discarding invalid path!");
                  }

                  if (flag && this.current != null && this.current.getPath() != null) {
                     if (goal2.isInGoal(this.current.getPath().getDest())) {
                        this.logDebug("Finished finding a path from " + pos + " to " + goal2 + ". " + this.current.getPath().getNumNodesConsidered() + " nodes considered");
                     } else {
                        this.logDebug("Found path segment from " + pos + " towards " + goal2 + ". " + this.current.getPath().getNumNodesConsidered() + " nodes considered");
                     }
                  }

                  synchronized (this.pathCalcLock) {
                     this.inProgress = null;
                  }
               }
            });
         }
      }
   }

   public void requestPause() {
      this.pauseRequestedLastTick = true;
   }

   private static AbstractNodeCostSearch createPathfinder(BlockPos pos, Goal goal2, IPath path, CalculationContext calculationContext) {
      Object obj = goal2;
      if ((Boolean)Baritone.settings().simplifyUnloadedYCoord.value && goal2 instanceof IGoalRenderPos) {
         BlockPos goalPos = ((IGoalRenderPos)goal2).getGoalPos();
         if (!calculationContext.bsi.worldContainsLoadedChunk(goalPos.getX(), goalPos.getZ())) {
            obj = new GoalXZ(goalPos.getX(), goalPos.getZ());
         }
      }

      Favoring favoring = new Favoring(calculationContext.getBaritone().getPlayerContext(), path, calculationContext);
      return new AStarPathFinder(pos.getX(), pos.getY(), pos.getZ(), (Goal)obj, favoring, calculationContext);
   }

   @Override
   public boolean cancelEverything() {
      boolean isSafeToCancel = this.isSafeToCancel();
      if (isSafeToCancel) {
         this.secretInternalSegmentCancel();
      }

      this.baritone.getPathingControlManager2().cancelEverything();
      return isSafeToCancel;
   }

   private void tickPath() {
      this.pausedThisTick = false;
      if (this.pauseRequestedLastTick && this.safeToCancel) {
         this.pauseRequestedLastTick = false;
         if (this.unpausedLastTick) {
            this.baritone.getInputOverrideHandler2().clearAllKeys();
            this.baritone.getInputOverrideHandler2().getBlockBreakHelper().stopBreakingBlock();
         }

         this.unpausedLastTick = false;
         this.pausedThisTick = true;
      } else {
         this.unpausedLastTick = true;
         if (this.cancelRequested) {
            this.cancelRequested = false;
            this.baritone.getInputOverrideHandler2().clearAllKeys();
         }

         synchronized (this.pathPlanLock) {
            synchronized (this.pathCalcLock) {
               if (this.inProgress != null) {
                  BetterBlockPos start = this.inProgress.getStart();
                  Optional optional = this.inProgress.bestPathSoFar();
                  if ((this.current == null || !this.current.getPath().getDest().equals(start))
                     && !start.equals(this.ctx.playerFeet())
                     && !start.equals(this.expectedSegmentStart)
                     && (!optional.isPresent() || !((IPath)optional.get()).positions().contains(this.ctx.playerFeet()) && !((IPath)optional.get()).positions().contains(this.expectedSegmentStart))) {
                     this.inProgress.cancel();
                  }
               }
            }

            if (this.current != null) {
               this.safeToCancel = this.current.onTick();
               if (this.current.isFailed() || this.current.finished()) {
                  this.current = null;
                  if (this.goal != null && !this.goal.isInGoal(this.ctx.playerFeet())) {
                     if (this.next != null && !this.next.getPath().positions().contains(this.ctx.playerFeet()) && !this.next.getPath().positions().contains(this.expectedSegmentStart)) {
                        this.logDebug("Discarding next path as it does not contain current position");
                        this.queuePathEvent(PathEvent.DISCARD_NEXT);
                        this.next = null;
                     }

                     if (this.next != null) {
                        this.logDebug("Continuing on to planned next path");
                        this.queuePathEvent(PathEvent.CONTINUING_ONTO_PLANNED_NEXT);
                        this.current = this.next;
                        this.next = null;
                        this.current.onTick();
                     } else {
                        synchronized (this.pathCalcLock) {
                           if (this.inProgress != null) {
                              this.queuePathEvent(PathEvent.PATH_FINISHED_NEXT_STILL_CALCULATING);
                              return;
                           }

                           this.queuePathEvent(PathEvent.CALC_STARTED);
                           this.findPathInNewThread(this.expectedSegmentStart, true, this.context);
                        }
                     }
                  } else {
                     this.logDebug("All done. At " + this.goal);
                     this.queuePathEvent(PathEvent.AT_GOAL);
                     this.next = null;
                     if ((Boolean)Baritone.settings().disconnectOnArrival.value) {
                        this.ctx.world().sendQuittingDisconnectingPacket();
                     }
                  }
               } else if (this.safeToCancel && this.next != null && this.next.snipsnapifpossible()) {
                  this.logDebug("Splicing into planned next path early...");
                  this.queuePathEvent(PathEvent.SPLICING_ONTO_NEXT_EARLY);
                  this.current = this.next;
                  this.next = null;
                  this.current.onTick();
               } else {
                  if ((Boolean)Baritone.settings().splicePath.value) {
                     this.current = this.current.trySplice(this.next);
                  }

                  if (this.next != null && this.current.getPath().getDest().equals(this.next.getPath().getDest())) {
                     this.next = null;
                  }

                  synchronized (this.pathCalcLock) {
                     if (this.inProgress != null) {
                        return;
                     }

                     if (this.next != null) {
                        return;
                     }

                     if (this.goal == null || this.goal.isInGoal(this.current.getPath().getDest())) {
                        return;
                     }

                     if ((Double)this.ticksRemainingInSegment(false).get() < (double)((Integer)Baritone.settings().planningTickLookahead.value).intValue()) {
                        this.logDebug("Path almost over. Planning ahead...");
                        this.queuePathEvent(PathEvent.NEXT_SEGMENT_CALC_STARTED);
                        this.findPathInNewThread(this.current.getPath().getDest(), false, this.context);
                     }
                  }
               }
            }
         }
      }
   }

   private void resetStartPosition(BlockPos pos) {
      this.resetStartPosition2(new BetterBlockPos(pos));
   }

   @Override
   public void onTick(TickEvent tickEvent) {
      this.dispatchEvents();
      if (tickEvent.getType() == TickEvent.Type.OUT) {
         this.secretInternalSegmentCancel();
         this.baritone.getPathingControlManager2().cancelEverything();
      } else {
         this.expectedSegmentStart = this.pathStart();
         this.baritone.getPathingControlManager2().preTick();
         this.tickPath();
         this.ticksElapsedSoFar++;
         this.dispatchEvents();
      }
   }

   @Override
   public IPathExecutor getNext() {
      return this.getNext2();
   }

   @Override
   public boolean isPathing() {
      return this.hasPath() && !this.pausedThisTick;
   }

   public boolean isSafeToCancel() {
      return this.safeToCancel;
   }

   public void secretInternalSegmentCancel() {
      this.queuePathEvent(PathEvent.CANCELED);
      synchronized (this.pathPlanLock) {
         this.getInProgress().ifPresent(obj -> ((AbstractNodeCostSearch)obj).cancel());
         if (this.current != null) {
            this.current = null;
            this.next = null;
            this.baritone.getInputOverrideHandler2().clearAllKeys();
            this.baritone.getInputOverrideHandler2().getBlockBreakHelper().stopBreakingBlock();
         }
      }
   }

   public boolean secretInternalSetGoalAndPath(PathingCommand pathingCommand) {
      this.setGoal(pathingCommand.goal);
      if (pathingCommand instanceof PathingCommandContext) {
         this.context = ((PathingCommandContext)pathingCommand).desiredCalcContext;
      } else {
         this.context = new CalculationContext(this.baritone, true);
      }

      if (this.goal == null) {
         return false;
      } else if (!this.goal.isInGoal(this.ctx.playerFeet()) && !this.goal.isInGoal(this.expectedSegmentStart)) {
         synchronized (this.pathPlanLock) {
            if (this.current != null) {
               return false;
            } else {
               boolean flag;
               synchronized (this.pathCalcLock) {
                  if (this.inProgress != null) {
                     return false;
                  }

                  this.queuePathEvent(PathEvent.CALC_STARTED);
                  this.findPathInNewThread(this.expectedSegmentStart, true, this.context);
                  flag = true;
               }

               return flag;
            }
         }
      } else {
         return false;
      }
   }

   public void setGoal(Goal goal2) {
      this.goal = goal2;
   }

   @Override
   public Optional estimatedTicksToGoal() {
      BetterBlockPos betterBlockPos = this.ctx.playerFeet();
      if (this.goal != null && betterBlockPos != null && this.startPosition != null) {
         if (this.goal.isInGoal(this.ctx.playerFeet())) {
            this.resetStartPosition();
            return Optional.of(0.0);
         } else if (this.ticksElapsedSoFar == 0) {
            return Optional.empty();
         } else {
            double d = this.goal.heuristic(betterBlockPos.x2, betterBlockPos.y2, betterBlockPos.z2);
            double d2 = this.goal.heuristic(this.startPosition.x2, this.startPosition.y2, this.startPosition.z2);
            if (d == d2) {
               return Optional.empty();
            } else {
               return Optional.of(Math.abs(d - this.goal.heuristic()) * (double)this.ticksElapsedSoFar / Math.abs(d2 - d));
            }
         }
      } else {
         return Optional.empty();
      }
   }

   public PathExecutor getNext2() {
      return this.next;
   }

   private void queuePathEvent(PathEvent pathEvent) {
      this.toDispatch.add(pathEvent);
   }

   public CalculationContext getContext() {
      return this.context;
   }

   public void softCancelIfSafe() {
      synchronized (this.pathPlanLock) {
         this.getInProgress().ifPresent(obj -> ((AbstractNodeCostSearch)obj).cancel());
         if (!this.isSafeToCancel()) {
            return;
         }

         this.current = null;
         this.next = null;
      }

      this.cancelRequested = true;
   }

   @Override
   public Optional getInProgress() {
      return Optional.ofNullable(this.inProgress);
   }

}
