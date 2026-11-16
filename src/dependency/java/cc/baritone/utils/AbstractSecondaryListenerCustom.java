package cc.baritone.utils;

import net.minecraft.util.BlockPos;

public abstract class AbstractSecondaryListenerCustom implements Custom {
   public AbstractSecondaryListenerCustom resolveAbstractSecondaryListenerCus(SecondaryListener secondaryListener) {
      int dataRef = (Integer)SettingsTxtManager.getSettings().pathCutoffMinimumLength.dataRef;
      if (this.size() < dataRef) {
         return this;
      } else if (secondaryListener != null && !secondaryListener.matchesBlockPos(this.getBetterBlockPosXSYSZSBlockPos2())) {
         double dataRef2 = (Double)SettingsTxtManager.getSettings().pathCutoffFactor.dataRef;
         int count = (int)((double)(this.size() - dataRef) * dataRef2) + dataRef - 1;
         return new SecondaryListenerSecondaryListe(this, count);
      } else {
         return this;
      }
   }

   @Override
   public Custom resolveCustom(Object entry) {
      return this.resolveAbstractSecondaryListenerCus(entry);
   }

   @Override
   public Custom resolveCustom(SecondaryListener secondaryListener) {
      return this.resolveAbstractSecondaryListenerCus(secondaryListener);
   }

   public AbstractSecondaryListenerCustom resolveAbstractSecondaryListenerCus(Object entry) {
      if (!(Boolean)BaritoneManager.getSettings().cutoffAtLoadBoundary.dataRef) {
         return this;
      } else {
         HeuristicManager heuristicManager = (HeuristicManager)entry;

         for (int index = 0; index < this.unmodifiableList().size(); index++) {
            BlockPos blockPos = (BlockPos)this.unmodifiableList().get(index);
            if (!heuristicManager.checkIndex(blockPos.getX(), blockPos.getZ())) {
               return new SecondaryListenerSecondaryListe(this, index);
            }
         }

         return this;
      }
   }
}
