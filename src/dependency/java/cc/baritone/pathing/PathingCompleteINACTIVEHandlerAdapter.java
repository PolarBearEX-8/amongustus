package cc.baritone.pathing;

import cc.baritone.api.INACTIVEHandlerAdapter;
import cc.baritone.api.INACTIVEHandlerExtension;
import cc.baritone.utils.AuxiliaryOptionType;
import cc.baritone.utils.BaritoneManager;
import cc.baritone.utils.DepthFirstEngine;
import cc.baritone.utils.HeuristicCoordinator;
import cc.baritone.utils.Listener5Entry;
import cc.baritone.utils.SecondaryListener;

public final class PathingCompleteINACTIVEHandlerAdapter extends INACTIVEHandlerAdapter implements INACTIVEHandlerExtension {
   public SecondaryListener secondaryListener;
   public SecondaryListener secondaryListener2;
   public AuxiliaryOptionType auxiliaryOptionType;

   @Override
   public String getText() {
      return "Custom Goal " + this.secondaryListener;
   }

   @Override
   public SecondaryListener getSecondaryListener2() {
      return this.secondaryListener2;
   }

   @Override
   public void updateState() {
      this.auxiliaryOptionType = AuxiliaryOptionType.NONE;
      this.secondaryListener = null;
   }

   @Override
   public void applySecondaryListener(SecondaryListener secondaryListener3) {
      this.secondaryListener = secondaryListener3;
      this.secondaryListener2 = secondaryListener3;
      if (this.auxiliaryOptionType == AuxiliaryOptionType.NONE) {
         this.auxiliaryOptionType = AuxiliaryOptionType.GOAL_SET;
      }

      if (this.auxiliaryOptionType == AuxiliaryOptionType.EXECUTING) {
         this.auxiliaryOptionType = AuxiliaryOptionType.PATH_REQUESTED;
      }
   }

   @Override
   public boolean isEnabled() {
      return this.auxiliaryOptionType != AuxiliaryOptionType.NONE;
   }

   @Override
   public SecondaryListener getSecondaryListener() {
      return this.secondaryListener;
   }

   @Override
   public Listener5Entry resolveListener5Entry(boolean flag, boolean flag2) {
      switch (DepthFirstEngine.countArray[this.auxiliaryOptionType.ordinal()]) {
         case 1:
            return new Listener5Entry(this.secondaryListener, HeuristicCoordinator.CANCEL_AND_SET_GOAL);
         case 2:
            Listener5Entry listener5Entry = new Listener5Entry(this.secondaryListener, HeuristicCoordinator.FORCE_REVALIDATE_GOAL_AND_PATH);
            this.auxiliaryOptionType = AuxiliaryOptionType.EXECUTING;
            return listener5Entry;
         case 3:
            if (flag) {
               this.updateState();
               return new Listener5Entry(this.secondaryListener, HeuristicCoordinator.CANCEL_AND_SET_GOAL);
            } else {
               if (this.secondaryListener == null
                  || this.secondaryListener.matchesBlockPos(this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS())
                     && this.secondaryListener.matchesBlockPos(this.baritoneManager.getAlreadyDoingItManager().getBetterBlockPosXSYSZSBlockPos())) {
                  this.updateState();
                  if ((Boolean)BaritoneManager.getSettings().disconnectOnArrival.dataRef) {
                     this.staticListener.getWorld().updateState();
                  }

                  if ((Boolean)BaritoneManager.getSettings().notificationOnPathComplete.dataRef) {
                     this.applyText("Pathing complete", false);
                  }

                  return new Listener5Entry(this.secondaryListener, HeuristicCoordinator.CANCEL_AND_SET_GOAL);
               }

               return new Listener5Entry(this.secondaryListener, HeuristicCoordinator.SET_GOAL_AND_PATH);
            }
         default:
            throw new IllegalStateException();
      }
   }

   @Override
   public void updateState2() {
      this.auxiliaryOptionType = AuxiliaryOptionType.PATH_REQUESTED;
   }

   public PathingCompleteINACTIVEHandlerAdapter(BaritoneManager baritoneManager) {
      super(baritoneManager);
   }
}
