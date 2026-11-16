package cc.baritone.api;

import cc.baritone.utils.AStarOptimizer;
import cc.baritone.utils.AStarScheduler;
import cc.baritone.utils.AStarService;
import cc.baritone.utils.AuxiliaryExceptionAdapter;
import cc.baritone.utils.BreadthFirstCalibrator;
import cc.baritone.utils.BreadthFirstPlanner;
import cc.baritone.utils.DepthFirstPipeline;
import cc.baritone.utils.HeuristicScheduler;
import cc.baritone.utils.InternalCustom;
import cc.baritone.utils.RuntimeComponent;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class TertiaryItemEntry {
   public int count2 = 0;
   public DepthFirstPipeline depthFirstPipeline;
   public InputStream inputStream;
   public BreadthFirstPlanner breadthFirstPlanner;
   public InternalCustom internalCustom;
   public boolean enabled2 = false;
   public boolean enabled = false;
   public int count = 0;

   public boolean isActive() throws AuxiliaryExceptionAdapter {
      try {
         if (this.internalCustom == null) {
            return false;
         } else {
            AStarService aStarServiceAsAStarService = this.depthFirstPipeline.getAStarServiceAsAStarService();
            if (aStarServiceAsAStarService == null) {
               return false;
            } else {
               BreadthFirstCalibrator breadthFirstCalibrator = (BreadthFirstCalibrator)this.breadthFirstPlanner.resolveExtraCustom(aStarServiceAsAStarService, this.depthFirstPipeline);
               synchronized (this) {
                  InternalCustom internalCustom2 = this.internalCustom;
                  if (internalCustom2 != null) {
                     internalCustom2.applyShortArray(breadthFirstCalibrator.getShortCodeArray(), 0, breadthFirstCalibrator.getCount());
                  }
               }

               this.depthFirstPipeline.updateState2();
               return true;
            }
         }
      } catch (RuntimeException | RuntimeComponent exception) {
         throw new AuxiliaryExceptionAdapter("Exception decoding audio frame", exception);
      }
   }

   public void applyIndex(int count3) throws AuxiliaryExceptionAdapter {
      this.matchesAtomicBoolean(count3, new AtomicBoolean(true));
   }

   public int getCount() {
      int count3 = this.count;
      InternalCustom internalCustom2 = this.internalCustom;
      if (internalCustom2 != null) {
         count3 = internalCustom2.getMicrosecondPosition();
      }

      return count3;
   }

   public synchronized void updateState() {
      InternalCustom internalCustom2 = this.internalCustom;
      if (internalCustom2 != null) {
         this.enabled2 = true;
         this.internalCustom = null;
         internalCustom2.updateState();
         this.count = internalCustom2.getMicrosecondPosition();

         try {
            this.depthFirstPipeline.updateState3();
         } catch (AStarScheduler exception) {
         }
      }
   }

   public boolean checkIndex(int count3) throws AuxiliaryExceptionAdapter {
      boolean flag = true;
      int count4 = count3 / 26;

      while (count4-- > 0 && flag) {
         flag = this.isActiveAsFlag();
      }

      return flag;
   }

   public boolean matchesAtomicBoolean(int count3, AtomicBoolean atomicBoolean) throws AuxiliaryExceptionAdapter {
      int count4 = count3;
      boolean flag = true;

      while (count4-- > 0 && flag && atomicBoolean.get()) {
         flag = this.isActive();
      }

      if (flag && !atomicBoolean.get()) {
         flag = false;
      }

      if (!flag) {
         InternalCustom internalCustom2 = this.internalCustom;
         if (internalCustom2 != null) {
            internalCustom2.updateState2();
            synchronized (this) {
               this.enabled = !this.enabled2;
               this.updateState();
            }
         }
      }

      return flag;
   }

   public boolean checkFloat(float factor) {
      if (this.internalCustom instanceof HeuristicScheduler) {
         HeuristicScheduler internalCustom2 = (HeuristicScheduler)this.internalCustom;

         try {
            internalCustom2.applyShortArray(null, 0, 0);
         } catch (AuxiliaryExceptionAdapter exception) {
            exception.printStackTrace();
         }

         internalCustom2.checkFloat(factor);
      }

      return false;
   }

   public synchronized boolean isEnabled() {
      return this.enabled;
   }

   public void updateState2() throws AuxiliaryExceptionAdapter {
      this.applyIndex(Integer.MAX_VALUE);
   }

   public boolean isActiveAsFlag() throws AuxiliaryExceptionAdapter {
      if (this.depthFirstPipeline.getAStarServiceAsAStarService() == null) {
         return false;
      } else {
         this.depthFirstPipeline.updateState2();
         return true;
      }
   }

   public void applyAtomicBoolean(AtomicBoolean atomicBoolean) throws AuxiliaryExceptionAdapter {
      this.matchesAtomicBoolean(Integer.MAX_VALUE, atomicBoolean);
   }

   public TertiaryItemEntry(InputStream inputStream2) throws AuxiliaryExceptionAdapter {
      this(inputStream2, null);
   }

   public TertiaryItemEntry(InputStream inputStream2, InternalCustom internalCustom2) throws AuxiliaryExceptionAdapter {
      this.inputStream = inputStream2;
      this.depthFirstPipeline = new DepthFirstPipeline(inputStream2);
      this.breadthFirstPlanner = new BreadthFirstPlanner();
      if (internalCustom2 != null) {
         this.internalCustom = internalCustom2;
      } else {
         AStarOptimizer aStarOptimizer = AStarOptimizer.getAStarOptimizer();
         this.internalCustom = aStarOptimizer.getInternalCustom();
      }

      this.internalCustom.applyBreadthFirstPlanner(this.breadthFirstPlanner);
   }
}
