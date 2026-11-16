package cc.baritone.utils;

import cc.baritone.api.INACTIVEHandler;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ListenerHandler implements LocalListener {
   public final BaritoneManager baritoneManager;
   public final HashSet hashSet;
   public final List list;
   public INACTIVEHandler iNACTIVEHandler2;
   public INACTIVEHandler iNACTIVEHandler;
   public Listener5Entry listener5Entry;

   @Override
   public Optional ofNullableAsOptional() {
      return Optional.ofNullable(this.iNACTIVEHandler);
   }

   @Override
   public void applyINACTIVEHandler(INACTIVEHandler iNACTIVEHandler3) {
      iNACTIVEHandler3.updateState();
      this.hashSet.add(iNACTIVEHandler3);
   }

   public void updateState() {
      this.iNACTIVEHandler2 = this.iNACTIVEHandler;
      this.iNACTIVEHandler = null;
      AlreadyDoingItManager alreadyDoingItManager = this.baritoneManager.getAlreadyDoingItManager();
      this.listener5Entry = this.getListener5Entry();
      if (this.listener5Entry == null) {
         alreadyDoingItManager.isActive3();
         alreadyDoingItManager.setSecondaryListener(null);
      } else {
         if (!Objects.equals(this.iNACTIVEHandler, this.iNACTIVEHandler2)
            && this.listener5Entry.heuristicCoordinator != HeuristicCoordinator.REQUEST_PAUSE
            && this.iNACTIVEHandler2 != null
            && !this.iNACTIVEHandler2.isActive()) {
            alreadyDoingItManager.isActive3();
         }

         switch (AStarProcessor.countArray[this.listener5Entry.heuristicCoordinator.ordinal()]) {
            case 1:
               alreadyDoingItManager.matchesListener5Entry(this.listener5Entry);
            case 2:
               alreadyDoingItManager.updateState5();
               break;
            case 3:
               alreadyDoingItManager.setSecondaryListener(this.listener5Entry.secondaryListener);
               alreadyDoingItManager.isActive3();
               break;
            case 4:
            case 5:
               if (!alreadyDoingItManager.isActiveAsFlag() && !alreadyDoingItManager.ofNullable().isPresent()) {
                  alreadyDoingItManager.matchesListener5Entry(this.listener5Entry);
               }
               break;
            case 6:
               if (this.listener5Entry.secondaryListener != null) {
                  alreadyDoingItManager.matchesListener5Entry(this.listener5Entry);
               }
               break;
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean matchesSecondaryListener(SecondaryListener secondaryListener) {
      DepthFirstCalculator depthFirstCalculator = this.baritoneManager.getAlreadyDoingItManager().getDepthFirstCalculator2();
      if (depthFirstCalculator != null) {
         SecondaryListener secondaryListener2 = depthFirstCalculator.getCustom().getSecondaryListener();
         BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = depthFirstCalculator.getCustom().getBetterBlockPosXSYSZSBlockPos2();
         if (secondaryListener2.matchesBlockPos(betterBlockPosXSYSZSBlockPos) && !secondaryListener.matchesBlockPos(betterBlockPosXSYSZSBlockPos)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public Optional ofNullable() {
      return Optional.ofNullable(this.listener5Entry);
   }

   public void updateState2() {
      this.iNACTIVEHandler2 = null;
      this.iNACTIVEHandler = null;
      this.listener5Entry = null;
      this.list.clear();

      for (INACTIVEHandler iNACTIVEHandler3 : (Iterable<INACTIVEHandler>)(Iterable<?>)this.hashSet) {
         iNACTIVEHandler3.updateState();
         if (iNACTIVEHandler3.isEnabled() && !iNACTIVEHandler3.isActive()) {
            throw new IllegalStateException(iNACTIVEHandler3.getTextAsText());
         }
      }
   }

   public static void applyListenerHandler(ListenerHandler listenerHandler) {
      listenerHandler.updateState3();
   }

   public boolean matchesSecondaryListenerBySecondaryListene(SecondaryListener secondaryListener) {
      DepthFirstCalculator depthFirstCalculator = this.baritoneManager.getAlreadyDoingItManager().getDepthFirstCalculator2();
      if (depthFirstCalculator != null) {
         return secondaryListener.matchesBlockPos(depthFirstCalculator.getCustom().getBetterBlockPosXSYSZSBlockPos2()) ? false : !secondaryListener.equals(depthFirstCalculator.getCustom().getSecondaryListener());
      } else {
         return false;
      }
   }

   public Listener5Entry getListener5Entry() {
      for (INACTIVEHandler iNACTIVEHandler3 : (Iterable<INACTIVEHandler>)(Iterable<?>)this.hashSet) {
         if (iNACTIVEHandler3.isEnabled()) {
            if (!this.list.contains(iNACTIVEHandler3)) {
               this.list.add(0, iNACTIVEHandler3);
            }
         } else {
            this.list.remove(iNACTIVEHandler3);
         }
      }

      this.list.sort(Comparator.comparingDouble(INACTIVEHandler::getValue).reversed());
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         INACTIVEHandler iNACTIVEHandler4 = (INACTIVEHandler)iterator.next();
         Listener5Entry listener5Entry2 = iNACTIVEHandler4.resolveListener5Entry(
            Objects.equals(iNACTIVEHandler4, this.iNACTIVEHandler2) && this.baritoneManager.getAlreadyDoingItManager().isEnabled3(),
            this.baritoneManager.getAlreadyDoingItManager().isEnabled2()
         );
         if (listener5Entry2 == null) {
            if (iNACTIVEHandler4.isEnabled()) {
               throw new IllegalStateException(iNACTIVEHandler4.getTextAsText() + " actively returned null PathingCommand");
            }
         } else if (listener5Entry2.heuristicCoordinator != HeuristicCoordinator.DEFER) {
            this.iNACTIVEHandler = iNACTIVEHandler4;
            if (!iNACTIVEHandler4.isActive()) {
               iterator.forEachRemaining(handler -> ((INACTIVEHandler)handler).updateState());
            }

            return listener5Entry2;
         }
      }

      return null;
   }

   private void updateState3() {
      if (this.listener5Entry != null) {
         AlreadyDoingItManager alreadyDoingItManager = this.baritoneManager.getAlreadyDoingItManager();
         switch (AStarProcessor.countArray[this.listener5Entry.heuristicCoordinator.ordinal()]) {
            case 4:
               if (this.listener5Entry.secondaryListener == null
                  || this.matchesSecondaryListenerBySecondaryListene(this.listener5Entry.secondaryListener)
                  || this.matchesSecondaryListener(this.listener5Entry.secondaryListener)) {
                  alreadyDoingItManager.updateState2();
               }

               alreadyDoingItManager.matchesListener5Entry(this.listener5Entry);
               break;
            case 5:
               if ((Boolean)BaritoneManager.getSettings().cancelOnGoalInvalidation.dataRef
                  && (this.listener5Entry.secondaryListener == null || this.matchesSecondaryListener(this.listener5Entry.secondaryListener))) {
                  alreadyDoingItManager.updateState2();
               }

               alreadyDoingItManager.matchesListener5Entry(this.listener5Entry);
         }
      }
   }

   public ListenerHandler(BaritoneManager baritoneManager2) {
      this.baritoneManager = baritoneManager2;
      this.hashSet = new HashSet();
      this.list = new java.util.ArrayList();
      baritoneManager2.getCompositeListener().add(new DepthFirstProvider(this));
   }
}
