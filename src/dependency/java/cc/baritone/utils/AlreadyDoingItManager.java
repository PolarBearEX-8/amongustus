package cc.baritone.utils;

import cc.baritone.api.AuxiliaryItemEntry;
import cc.baritone.api.InternalItemEntry;
import cc.baritone.api.ItemStackEntry;
import cc.baritone.pathing.PathFinderCannotBeReusedManager;
import cc.baritone.pathing.PathFinderCannotBeReusedManagerAdapter;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;
import net.minecraft.util.BlockPos;

public final class AlreadyDoingItManager extends AuxiliaryItemEntry implements CoreListener, DRNHandler {
   public DepthFirstCalculator depthFirstCalculator2;
   public DepthFirstCalculator depthFirstCalculator;
   public SecondaryListener secondaryListener;
   public ItemStackEntry itemStackEntry;
   public int count;
   public BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2;
   public boolean enabled2;
   public boolean enabled4;
   public boolean enabled;
   public boolean enabled6;
   public boolean enabled5;
   public boolean enabled3;
   public volatile PathFinderCannotBeReusedManager pathFinderCannotBeReusedManager;
   public final Object dataRef = new Object();
   public final Object dataRef2 = new Object();
   public BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos;
   public final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();

   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos() {
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
      if (!AStarPredictor.matchesStaticListener(this.staticListener, betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getBetterBlockPosXSYSZSBlockPos4())) {
         if (this.staticListener.getEntityPlayerSP().onGround) {
            double posX = this.staticListener.getEntityPlayerSP().posX;
            double posZ = this.staticListener.getEntityPlayerSP().posZ;
            java.util.ArrayList list = new java.util.ArrayList();

            for (int index = -1; index <= 1; index++) {
               for (int index2 = -1; index2 <= 1; index2++) {
                  list.add(new BetterBlockPosXSYSZSBlockPos(betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count9 + index, betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count12, betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count10 + index2));
               }
            }

            list.sort(Comparator.comparingDouble(pos -> AlreadyDoingItManager.computeValue(posX, posZ, (BetterBlockPosXSYSZSBlockPos)pos)));

            for (int index3 = 0; index3 < 4; index3++) {
               BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos3 = (BetterBlockPosXSYSZSBlockPos)list.get(index3);
               double offset = Math.abs((double)betterBlockPosXSYSZSBlockPos3.count9 + 0.5 - posX);
               double offset2 = Math.abs((double)betterBlockPosXSYSZSBlockPos3.count10 + 0.5 - posZ);
               if ((!(offset > 0.8) || !(offset2 > 0.8))
                  && AStarPredictor.matchesStaticListener(this.staticListener, betterBlockPosXSYSZSBlockPos3.getBetterBlockPosXSYSZSBlockPos4())
                  && AStarPredictor.matchesStaticListenerByStaticListener(this.staticListener, betterBlockPosXSYSZSBlockPos3)
                  && AStarPredictor.matchesStaticListenerByStaticListener(this.staticListener, betterBlockPosXSYSZSBlockPos3.getBetterBlockPosXSYSZSBlockPos3())) {
                  return betterBlockPosXSYSZSBlockPos3;
               }
            }
         } else if (AStarPredictor.matchesStaticListener(this.staticListener, betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getBetterBlockPosXSYSZSBlockPos4().getBetterBlockPosXSYSZSBlockPos4())) {
            return betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getBetterBlockPosXSYSZSBlockPos4();
         }
      }

      return betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS;
   }

   public boolean isActive3() {
      if (this.isEnabled2()) {
         this.updateState3();
         return true;
      } else {
         return false;
      }
   }

   public void updateState5() {
      this.enabled4 = true;
   }

   public void updateState3() {
      this.add(AStarWatcher.CANCELED);
      synchronized (this.dataRef2) {
         this.ofNullable().ifPresent(finder -> ((PathFinderCannotBeReusedManager)finder).updateState());
         if (this.depthFirstCalculator2 != null) {
            this.depthFirstCalculator2 = null;
            this.depthFirstCalculator = null;
            this.baritoneManager.getMapEntry().clear();
            this.baritoneManager.getMapEntry().getClickListenerAdapter().updateState();
         }
      }
   }

   private void applyBlockPos(BlockPos blockPos, boolean flag, ItemStackEntry itemStackEntry2) {
      if (!Thread.holdsLock(this.dataRef)) {
         throw new IllegalStateException("Must be called with synchronization on pathCalcLock");
      } else if (this.pathFinderCannotBeReusedManager != null) {
         throw new IllegalStateException("Already doing it");
      } else if (!itemStackEntry2.enabled) {
         throw new IllegalStateException("Improper context thread safety level");
      } else {
         SecondaryListener secondaryListener2 = this.secondaryListener;
         if (secondaryListener2 == null) {
            this.applyTextByText("no goal");
         } else {
            long value;
            long value2;
            if (this.depthFirstCalculator2 == null) {
               value = (Long)BaritoneManager.getSettings().primaryTimeoutMS.dataRef;
               value2 = (Long)BaritoneManager.getSettings().failureTimeoutMS.dataRef;
            } else {
               value = (Long)BaritoneManager.getSettings().planAheadPrimaryTimeoutMS.dataRef;
               value2 = (Long)BaritoneManager.getSettings().planAheadFailureTimeoutMS.dataRef;
            }

            PathFinderCannotBeReusedManager pathFinderCannotBeReusedManager2 = resolvePathFinderCannotBeReusedMana(
               blockPos, secondaryListener2, this.depthFirstCalculator2 == null ? null : this.depthFirstCalculator2.getCustom(), itemStackEntry2
            );
            if (!Objects.equals(pathFinderCannotBeReusedManager2.getSecondaryListener(), secondaryListener2)) {
               this.applyTextByText("Simplifying " + secondaryListener2.getClass() + " to GoalXZ due to distance");
            }

            this.pathFinderCannotBeReusedManager = pathFinderCannotBeReusedManager2;
            BaritoneManager.getExecutor().execute(() -> this.applyBlockPos(flag, blockPos, secondaryListener2, pathFinderCannotBeReusedManager2, value, value2));
         }
      }
   }

   @Override
   public PrimaryListener getPrimaryListener() {
      return this.getDepthFirstCalculator();
   }

   @Override
   public void updateState() {
      this.isActive2();
      this.updateState3();
      synchronized (this.dataRef) {
         this.pathFinderCannotBeReusedManager = null;
      }
   }

   private DepthFirstCalculator resolveDepthFirstCalculator(Custom custom) {
      return new DepthFirstCalculator(this, custom);
   }

   public boolean matchesListener5Entry(Listener5Entry listener5Entry) {
      this.setSecondaryListener(listener5Entry.secondaryListener);
      if (listener5Entry instanceof Listener5EntryAdapter) {
         this.itemStackEntry = ((Listener5EntryAdapter)listener5Entry).itemStackEntry;
      } else {
         this.itemStackEntry = new ItemStackEntry(this.baritoneManager, true);
      }

      if (this.secondaryListener == null) {
         return false;
      } else if (!this.secondaryListener.matchesBlockPos(this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS())
         && !this.secondaryListener.matchesBlockPos(this.betterBlockPosXSYSZSBlockPos)) {
         synchronized (this.dataRef2) {
            if (this.depthFirstCalculator2 != null) {
               return false;
            } else {
               boolean flag;
               synchronized (this.dataRef) {
                  if (this.pathFinderCannotBeReusedManager != null) {
                     return false;
                  }

                  this.add(AStarWatcher.CALC_STARTED);
                  this.applyBlockPos(this.betterBlockPosXSYSZSBlockPos, true, this.itemStackEntry);
                  flag = true;
               }

               return flag;
            }
         }
      } else {
         return false;
      }
   }

   @Override
   public void forEach(AStarAuditor aStarAuditor) {
      if (this.isActiveAsFlag()) {
         aStarAuditor.setEnabled(this.depthFirstCalculator2.isEnabled3());
      }
   }

   public DepthFirstCalculator getDepthFirstCalculator2() {
      return this.depthFirstCalculator2;
   }

   public void updateState2() {
      synchronized (this.dataRef2) {
         this.ofNullable().ifPresent(finder -> ((PathFinderCannotBeReusedManager)finder).updateState());
         if (!this.isEnabled2()) {
            return;
         }

         this.depthFirstCalculator2 = null;
         this.depthFirstCalculator = null;
      }

      this.enabled5 = true;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   private void applyBetterBlockPosXSYSZSBlockP(BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos3) {
      this.count = 0;
      this.betterBlockPosXSYSZSBlockPos2 = betterBlockPosXSYSZSBlockPos3;
   }

   private void updateState4() {
      java.util.ArrayList list = new java.util.ArrayList();
      this.linkedBlockingQueue.drainTo(list);
      this.enabled3 = list.contains(AStarWatcher.CALC_FAILED);

      for (AStarWatcher aStarWatcher : (Iterable<AStarWatcher>)(Iterable<?>)list) {
         this.baritoneManager.getCompositeListener().forEach(aStarWatcher);
      }
   }

   @Override
   public void forEach(AStarCalculator aStarCalculator) {
   }

   public ItemStackEntry getItemStackEntry() {
      return this.itemStackEntry;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   @Override
   public Optional getOptional() {
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
      if (this.secondaryListener != null && betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS != null && this.betterBlockPosXSYSZSBlockPos2 != null) {
         if (this.secondaryListener.matchesBlockPos(this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS())) {
            this.updateState6();
            return Optional.of(0.0);
         } else if (this.count == 0) {
            return Optional.empty();
         } else {
            double offset = this.secondaryListener.computeValue(betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count9, betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count12, betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count10);
            double offset2 = this.secondaryListener
               .computeValue(this.betterBlockPosXSYSZSBlockPos2.count9, this.betterBlockPosXSYSZSBlockPos2.count12, this.betterBlockPosXSYSZSBlockPos2.count10);
            return offset == offset2
               ? Optional.empty()
               : Optional.of(Math.abs(offset - this.secondaryListener.getValue()) * (double)this.count / Math.abs(offset2 - offset));
         }
      } else {
         return Optional.empty();
      }
   }

   @Override
   public boolean isActive2() {
      boolean enabled7 = this.isEnabled2();
      if (enabled7) {
         this.updateState3();
      }

      this.baritoneManager.getListenerHandler().updateState2();
      return enabled7;
   }

   private static double computeValue(double offset, double offset2, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos3) {
      return ((double)betterBlockPosXSYSZSBlockPos3.count9 + 0.5 - offset) * ((double)betterBlockPosXSYSZSBlockPos3.count9 + 0.5 - offset)
         + ((double)betterBlockPosXSYSZSBlockPos3.count10 + 0.5 - offset2) * ((double)betterBlockPosXSYSZSBlockPos3.count10 + 0.5 - offset2);
   }

   private void updateState7() {
      this.enabled6 = false;
      if (this.enabled4 && this.enabled2) {
         this.enabled4 = false;
         if (this.enabled) {
            this.baritoneManager.getMapEntry().clear();
            this.baritoneManager.getMapEntry().getClickListenerAdapter().updateState();
         }

         this.enabled = false;
         this.enabled6 = true;
      } else {
         this.enabled = true;
         if (this.enabled5) {
            this.enabled5 = false;
            this.baritoneManager.getMapEntry().clear();
         }

         synchronized (this.dataRef2) {
            synchronized (this.dataRef) {
               if (this.pathFinderCannotBeReusedManager != null) {
                  BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos3 = this.pathFinderCannotBeReusedManager.getBetterBlockPosXSYSZSBlockPos();
                  Optional optional = this.pathFinderCannotBeReusedManager.getOptional();
                  if ((this.depthFirstCalculator2 == null || !this.depthFirstCalculator2.getCustom().getBetterBlockPosXSYSZSBlockPos2().equals(betterBlockPosXSYSZSBlockPos3))
                     && !betterBlockPosXSYSZSBlockPos3.equals(this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS())
                     && !betterBlockPosXSYSZSBlockPos3.equals(this.betterBlockPosXSYSZSBlockPos)
                     && (
                        !optional.isPresent()
                           || !((Custom)optional.get()).unmodifiableList().contains(this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS())
                              && !((Custom)optional.get()).unmodifiableList().contains(this.betterBlockPosXSYSZSBlockPos)
                     )) {
                     this.pathFinderCannotBeReusedManager.updateState();
                  }
               }
            }

            if (this.depthFirstCalculator2 != null) {
               this.enabled2 = this.depthFirstCalculator2.isActive();
               if (this.depthFirstCalculator2.isEnabled() || this.depthFirstCalculator2.isActiveAsFlag()) {
                  this.depthFirstCalculator2 = null;
                  if (this.secondaryListener != null
                     && !this.secondaryListener.matchesBlockPos(this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS())) {
                     if (this.depthFirstCalculator != null
                        && !this.depthFirstCalculator
                           .getCustom()
                           .unmodifiableList()
                           .contains(this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS())
                        && !this.depthFirstCalculator.getCustom().unmodifiableList().contains(this.betterBlockPosXSYSZSBlockPos)) {
                        this.applyTextByText("Discarding next path as it does not contain current position");
                        this.add(AStarWatcher.DISCARD_NEXT);
                        this.depthFirstCalculator = null;
                     }

                     if (this.depthFirstCalculator != null) {
                        this.applyTextByText("Continuing on to planned next path");
                        this.add(AStarWatcher.CONTINUING_ONTO_PLANNED_NEXT);
                        this.depthFirstCalculator2 = this.depthFirstCalculator;
                        this.depthFirstCalculator = null;
                        this.depthFirstCalculator2.isActive();
                     } else {
                        synchronized (this.dataRef) {
                           if (this.pathFinderCannotBeReusedManager != null) {
                              this.add(AStarWatcher.PATH_FINISHED_NEXT_STILL_CALCULATING);
                              return;
                           }

                           this.add(AStarWatcher.CALC_STARTED);
                           this.applyBlockPos(this.betterBlockPosXSYSZSBlockPos, true, this.itemStackEntry);
                        }
                     }
                  } else {
                     this.applyTextByText("All done. At " + this.secondaryListener);
                     this.add(AStarWatcher.AT_GOAL);
                     this.depthFirstCalculator = null;
                     if ((Boolean)BaritoneManager.getSettings().disconnectOnArrival.dataRef) {
                        this.staticListener.getWorld().updateState();
                     }
                  }
               } else if (this.enabled2 && this.depthFirstCalculator != null && this.depthFirstCalculator.isActive3()) {
                  this.applyTextByText("Splicing into planned next path early...");
                  this.add(AStarWatcher.SPLICING_ONTO_NEXT_EARLY);
                  this.depthFirstCalculator2 = this.depthFirstCalculator;
                  this.depthFirstCalculator = null;
                  this.depthFirstCalculator2.isActive();
               } else {
                  if ((Boolean)BaritoneManager.getSettings().splicePath.dataRef) {
                     this.depthFirstCalculator2 = this.depthFirstCalculator2.resolveDepthFirstCalculator(this.depthFirstCalculator);
                  }

                  if (this.depthFirstCalculator != null
                     && this.depthFirstCalculator2
                        .getCustom()
                        .getBetterBlockPosXSYSZSBlockPos2()
                        .equals(this.depthFirstCalculator.getCustom().getBetterBlockPosXSYSZSBlockPos2())) {
                     this.depthFirstCalculator = null;
                  }

                  synchronized (this.dataRef) {
                     if (this.pathFinderCannotBeReusedManager != null) {
                        return;
                     }

                     if (this.depthFirstCalculator != null) {
                        return;
                     }

                     if (this.secondaryListener == null
                        || this.secondaryListener.matchesBlockPos(this.depthFirstCalculator2.getCustom().getBetterBlockPosXSYSZSBlockPos2())) {
                        return;
                     }

                     if ((Double)this.resolveOptional(false).get() < (double)((Integer)BaritoneManager.getSettings().planningTickLookahead.dataRef).intValue()) {
                        this.applyTextByText("Path almost over. Planning ahead...");
                        this.add(AStarWatcher.NEXT_SEGMENT_CALC_STARTED);
                        this.applyBlockPos(this.depthFirstCalculator2.getCustom().getBetterBlockPosXSYSZSBlockPos2(), false, this.itemStackEntry);
                     }
                  }
               }
            }
         }
      }
   }

   public DepthFirstCalculator getDepthFirstCalculator() {
      return this.depthFirstCalculator;
   }

   @Override
   public PrimaryListener getPrimaryListenerAsPrimaryListener() {
      return this.getDepthFirstCalculator2();
   }

   private void add(AStarWatcher aStarWatcher) {
      this.linkedBlockingQueue.add(aStarWatcher);
   }

   private static PathFinderCannotBeReusedManager resolvePathFinderCannotBeReusedMana(BlockPos blockPos, SecondaryListener secondaryListener2, Custom custom, ItemStackEntry itemStackEntry2) {
      Object entry = secondaryListener2;
      if ((Boolean)BaritoneManager.getSettings().simplifyUnloadedYCoord.dataRef && secondaryListener2 instanceof VirtualCustom) {
         BlockPos blockPos2 = ((VirtualCustom)secondaryListener2).getBlockPos();
         if (!itemStackEntry2.heuristicManager.checkIndex(blockPos2.getX(), blockPos2.getZ())) {
            entry = new DiagonalEvaluator(blockPos2.getX(), blockPos2.getZ());
         }
      }

      AuxiliaryCustom auxiliaryCustom = new AuxiliaryCustom(itemStackEntry2.getActiveListener().getStaticListener(), custom, itemStackEntry2);
      return new PathFinderCannotBeReusedManagerAdapter(blockPos.getX(), blockPos.getY(), blockPos.getZ(), (SecondaryListener)entry, auxiliaryCustom, itemStackEntry2);
   }

   private void applyBlockPos(BlockPos blockPos) {
      this.applyBetterBlockPosXSYSZSBlockP(new BetterBlockPosXSYSZSBlockPos(blockPos));
   }

   @Override
   public boolean isActiveAsFlag() {
      return this.isActive() && !this.enabled6;
   }

   private void updateState6() {
      this.applyBetterBlockPosXSYSZSBlockP(this.betterBlockPosXSYSZSBlockPos);
   }

   private void applyBlockPos(boolean flag, BlockPos blockPos, SecondaryListener secondaryListener2, PathFinderCannotBeReusedManager pathFinderCannotBeReusedManager2, long value, long value2) {
      if (flag) {
         this.applyTextByText("Starting to search for path from " + blockPos + " to " + secondaryListener2);
      }

      TertiaryCustom tertiaryCustom = pathFinderCannotBeReusedManager2.resolveTertiaryCustom(value, value2);
      synchronized (this.dataRef2) {
         Optional optional = tertiaryCustom.ofNullable().map((item -> this.resolveDepthFirstCalculator((Custom)item)));
         if (this.depthFirstCalculator2 == null) {
            if (optional.isPresent()) {
               if (((DepthFirstCalculator)optional.get()).getCustom().unmodifiableList().contains(this.betterBlockPosXSYSZSBlockPos)) {
                  this.add(AStarWatcher.CALC_FINISHED_NOW_EXECUTING);
                  this.depthFirstCalculator2 = (DepthFirstCalculator)optional.get();
                  this.applyBlockPos(blockPos);
               } else {
                  this.applyTextByText("Warning: discarding orphan path segment with incorrect start");
               }
            } else if (tertiaryCustom.getHeuristicValidator() != HeuristicValidator.CANCELLATION && tertiaryCustom.getHeuristicValidator() != HeuristicValidator.EXCEPTION) {
               this.add(AStarWatcher.CALC_FAILED);
            }
         } else if (this.depthFirstCalculator == null) {
            if (optional.isPresent()) {
               if (((DepthFirstCalculator)optional.get())
                  .getCustom()
                  .getBetterBlockPosXSYSZSBlockPos()
                  .equals(this.depthFirstCalculator2.getCustom().getBetterBlockPosXSYSZSBlockPos2())) {
                  this.add(AStarWatcher.NEXT_SEGMENT_CALC_FINISHED);
                  this.depthFirstCalculator = (DepthFirstCalculator)optional.get();
               } else {
                  this.applyTextByText("Warning: discarding orphan next segment with incorrect start");
               }
            } else {
               this.add(AStarWatcher.NEXT_CALC_FAILED);
            }
         } else {
            this.applyText("Warning: PathingBehaivor illegal state! Discarding invalid path!");
         }

         if (flag && this.depthFirstCalculator2 != null && this.depthFirstCalculator2.getCustom() != null) {
            if (secondaryListener2.matchesBlockPos(this.depthFirstCalculator2.getCustom().getBetterBlockPosXSYSZSBlockPos2())) {
               this.applyTextByText(
                  "Finished finding a path from " + blockPos + " to " + secondaryListener2 + ". " + this.depthFirstCalculator2.getCustom().getCount() + " nodes considered"
               );
            } else {
               this.applyTextByText(
                  "Found path segment from " + blockPos + " towards " + secondaryListener2 + ". " + this.depthFirstCalculator2.getCustom().getCount() + " nodes considered"
               );
            }
         }

         synchronized (this.dataRef) {
            this.pathFinderCannotBeReusedManager = null;
         }
      }
   }

   @Override
   public void forEach(DepthFirstTracker depthFirstTracker) {
      DepthFirstSampler.applyDepthFirstTracker(depthFirstTracker, this);
   }

   @Override
   public Optional ofNullable() {
      return Optional.ofNullable(this.pathFinderCannotBeReusedManager);
   }

   @Override
   public void applyInternalItemEntry(InternalItemEntry internalItemEntry) {
      this.updateState4();
      if (internalItemEntry.getInternalOptionType() == InternalOptionType.OUT) {
         this.updateState3();
         this.baritoneManager.getListenerHandler().updateState2();
      } else {
         this.betterBlockPosXSYSZSBlockPos = this.getBetterBlockPosXSYSZSBlockPos();
         this.baritoneManager.getListenerHandler().updateState();
         this.updateState7();
         this.count++;
         this.updateState4();
      }
   }

   @Override
   public SecondaryListener getSecondaryListener() {
      return this.secondaryListener;
   }

   public void setSecondaryListener(SecondaryListener secondaryListener2) {
      this.secondaryListener = secondaryListener2;
   }

   public AlreadyDoingItManager(BaritoneManager baritoneManager) {
      super(baritoneManager);
   }
}
