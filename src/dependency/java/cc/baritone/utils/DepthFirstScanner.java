package cc.baritone.utils;

import cc.baritone.api.ItemStackEntry;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DepthFirstScanner extends AbstractSecondaryListenerCustom {
   public final BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos;
   public final BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2;
   public final List list;
   public final List list2;
   public final List list3;
   public final SecondaryListener secondaryListener;
   public final int count;
   public final ItemStackEntry itemStackEntry;
   public volatile boolean enabled;

   private boolean isActive() {
      if (!this.list.isEmpty() && this.list2.isEmpty()) {
         for (int index = 0; index < this.list.size() - 1; index++) {
            double offset = ((AStarSampler)this.list3.get(index + 1)).value2 - ((AStarSampler)this.list3.get(index)).value2;
            AStarPipeline aStarPipeline = this.resolveAStarPipeline(
               (BetterBlockPosXSYSZSBlockPos)this.list.get(index), (BetterBlockPosXSYSZSBlockPos)this.list.get(index + 1), offset
            );
            if (aStarPipeline == null) {
               return true;
            }

            this.list2.add(aStarPipeline);
         }

         return false;
      } else {
         throw new IllegalStateException();
      }
   }

   @Override
   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos2() {
      return this.betterBlockPosXSYSZSBlockPos2;
   }

   @Override
   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos() {
      return this.betterBlockPosXSYSZSBlockPos;
   }

   public DepthFirstScanner(AStarSampler aStarSampler, AStarSampler aStarSampler2, int count2, SecondaryListener secondaryListener2, ItemStackEntry itemStackEntry2) {
      this.betterBlockPosXSYSZSBlockPos = new BetterBlockPosXSYSZSBlockPos(aStarSampler.count, aStarSampler.count3, aStarSampler.count2);
      this.betterBlockPosXSYSZSBlockPos2 = new BetterBlockPosXSYSZSBlockPos(aStarSampler2.count, aStarSampler2.count3, aStarSampler2.count2);
      this.count = count2;
      this.list2 = new java.util.ArrayList();
      this.secondaryListener = secondaryListener2;
      this.itemStackEntry = itemStackEntry2;
      AStarSampler aStarSampler3 = aStarSampler2;
      LinkedList list4 = new LinkedList();

      LinkedList list5;
      for (list5 = new LinkedList(); aStarSampler3 != null; aStarSampler3 = aStarSampler3.aStarSampler) {
         list5.addFirst(aStarSampler3);
         list4.addFirst(new BetterBlockPosXSYSZSBlockPos(aStarSampler3.count, aStarSampler3.count3, aStarSampler3.count2));
      }

      this.list = new java.util.ArrayList(list4);
      this.list3 = new java.util.ArrayList(list5);
   }

   private AStarPipeline resolveAStarPipeline(BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos3, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos4, double offset) {
      HeuristicPredictor[] values = HeuristicPredictor.values();
      int length = values.length;

      for (int index = 0; index < length; index++) {
         AStarPipeline aStarPipeline = values[index].resolveAStarPipeline(this.itemStackEntry, betterBlockPosXSYSZSBlockPos3);
         if (aStarPipeline.getBetterBlockPosXSYSZSBlockPos2().equals(betterBlockPosXSYSZSBlockPos4)) {
            aStarPipeline.applyDouble(Math.min(aStarPipeline.computeValue(this.itemStackEntry), offset));
            return aStarPipeline;
         }
      }

      DRNHandler.dRNHandler.applyTextByText("Movement became impossible during calculation " + betterBlockPosXSYSZSBlockPos3 + " " + betterBlockPosXSYSZSBlockPos4 + " " + betterBlockPosXSYSZSBlockPos4.getBlockPos(betterBlockPosXSYSZSBlockPos3));
      return null;
   }

   @Override
   public SecondaryListener getSecondaryListener() {
      return this.secondaryListener;
   }

   @Override
   public List unmodifiableListAsList() {
      if (!this.enabled) {
         throw new IllegalStateException();
      } else {
         return Collections.unmodifiableList(this.list2);
      }
   }

   @Override
   public List unmodifiableList() {
      return Collections.unmodifiableList(this.list);
   }

   private void applyAStarPipeline(AStarPipeline aStarPipeline) {
      aStarPipeline.applyItemStackEntry(this.itemStackEntry);
   }

   @Override
   public Custom getCustom() {
      if (this.enabled) {
         throw new IllegalStateException();
      } else {
         this.enabled = true;
         boolean active = this.isActive();
         this.list2.forEach((item -> this.applyAStarPipeline((AStarPipeline)item)));
         if (active) {
            SecondaryListenerSecondaryListe secondaryListenerSecondaryListe = new SecondaryListenerSecondaryListe(this, this.unmodifiableListAsList().size());
            if (secondaryListenerSecondaryListe.unmodifiableListAsList().size() != this.list2.size()) {
               throw new IllegalStateException();
            } else {
               return secondaryListenerSecondaryListe;
            }
         } else {
            this.updateState();
            return this;
         }
      }
   }

   @Override
   public int getCount() {
      return this.count;
   }
}
