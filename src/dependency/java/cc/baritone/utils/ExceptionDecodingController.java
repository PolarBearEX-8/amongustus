package cc.baritone.utils;

import java.io.InputStream;

public class ExceptionDecodingController {
   public DepthFirstPipeline depthFirstPipeline;
   public BreadthFirstPlanner breadthFirstPlanner;
   public InternalCustom internalCustom;
   public boolean enabled = false;
   public boolean enabled2 = false;
   public int count = 0;
   public AbstractController abstractController;

   public void updateState() {
      this.abstractController.applyExceptionDecodingConControByExceptionDecodin(this.resolveExceptionDecodingConControll(ExceptionDecodingConController.count));
      this.updateState3();
   }

   public ExceptionDecodingController(InputStream inputStream, InternalCustom internalCustom2) throws AuxiliaryExceptionAdapter {
      this.depthFirstPipeline = new DepthFirstPipeline(inputStream);
      if (internalCustom2 != null) {
         this.internalCustom = internalCustom2;
      } else {
         this.internalCustom = AStarOptimizer.getAStarOptimizer().getInternalCustom();
      }

      this.internalCustom.applyBreadthFirstPlanner(this.breadthFirstPlanner = new BreadthFirstPlanner());
   }

   public ExceptionDecodingController(InputStream inputStream) throws AuxiliaryExceptionAdapter {
      this(inputStream, null);
   }

   public void setAbstractController(AbstractController abstractController2) {
      this.abstractController = abstractController2;
   }

   private ExceptionDecodingConController resolveExceptionDecodingConControll(InternalCustom internalCustom2, int count2) {
      return new ExceptionDecodingConController(this, count2, internalCustom2.getMicrosecondPosition());
   }

   private ExceptionDecodingConController resolveExceptionDecodingConControll(int count2) {
      return this.resolveExceptionDecodingConControll(this.internalCustom, count2);
   }

   public AbstractController getAbstractController() {
      return this.abstractController;
   }

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

   public void updateState2() throws AuxiliaryExceptionAdapter {
      this.checkIndex(Integer.MAX_VALUE);
   }

   public boolean isActiveAsFlag() throws AuxiliaryExceptionAdapter {
      if (this.depthFirstPipeline.getAStarServiceAsAStarService() == null) {
         return false;
      } else {
         this.depthFirstPipeline.updateState2();
         return true;
      }
   }

   public boolean checkIndex(int count2, int count3) throws AuxiliaryExceptionAdapter {
      boolean flag = true;
      int count4 = count2;

      while (count4-- > 0 && flag) {
         flag = this.isActiveAsFlag();
      }

      return this.checkIndex(count3 - count2);
   }

   public synchronized void updateState3() {
      InternalCustom internalCustom2 = this.internalCustom;
      if (internalCustom2 != null) {
         this.enabled = true;
         this.internalCustom = null;
         internalCustom2.updateState();
         this.count = internalCustom2.getMicrosecondPosition();

         try {
            this.depthFirstPipeline.updateState3();
         } catch (AStarScheduler exception) {
         }
      }
   }

   public boolean checkIndex(int count2) throws AuxiliaryExceptionAdapter {
      int count3 = count2;
      boolean flag = true;
      if (this.abstractController != null) {
         this.abstractController.applyExceptionDecodingConContro(this.resolveExceptionDecodingConControll(ExceptionDecodingConController.count2));
      }

      while (count3-- > 0 && flag) {
         flag = this.isActive();
      }

      InternalCustom internalCustom2 = this.internalCustom;
      if (internalCustom2 != null) {
         internalCustom2.updateState2();
         synchronized (this) {
            this.enabled2 = !this.enabled;
            this.updateState3();
         }

         if (this.abstractController != null) {
            this.abstractController
               .applyExceptionDecodingConControByExceptionDecodin(this.resolveExceptionDecodingConControll(internalCustom2, ExceptionDecodingConController.count));
         }
      }

      return flag;
   }
}
