package cc.baritone.utils;

public class DepthFirstPipelineDepthFirstPipelineDeptImpl extends DepthFirstPipelineDepthFirstPipe {
   public int count6;
   public float factor4;
   public float factor5;
   public float factor6;

   public DepthFirstPipelineDepthFirstPipelineDeptImpl(int count) {
      super(count);
   }

   @Override
   public void applyDepthFirstPipeline(DepthFirstPipeline depthFirstPipeline, AStarService aStarService, ControllerProvider controllerProvider) {
      super.applyDepthFirstPipeline(depthFirstPipeline, aStarService, controllerProvider);
   }

   @Override
   public void applyDepthFirstPipeline(DepthFirstPipeline depthFirstPipeline, ControllerProvider controllerProvider) {
      if (this.count != 0) {
         this.count2 = depthFirstPipeline.computeCount3(2);
         this.count6 = depthFirstPipeline.computeCount3(2);
         if (controllerProvider != null) {
            controllerProvider.applyIndex(this.count2, 2);
            controllerProvider.applyIndex(this.count6, 2);
         }
      }
   }

   @Override
   public boolean matchesSfdSerController(int count, SfdSerController sfdSerController, SfdSerController sfdSerController2) {
      if (this.count != 0) {
         float factor = this.factorArray3[this.count3];
         if (this.factorArray2[0] == null) {
            factor = (factor + this.factorArray4[0]) * this.factorArray5[0];
         }

         if (count == 0) {
            float factor2;
            float factor3;
            if (this.count4 <= 4) {
               factor2 = factor * this.factor;
               factor3 = factor * this.factor4;
            } else if (this.count4 <= 8) {
               factor2 = factor * this.factor2;
               factor3 = factor * this.factor5;
            } else {
               factor2 = factor * this.factor3;
               factor3 = factor * this.factor6;
            }

            sfdSerController.applyFloat(factor2, this.count5);
            sfdSerController2.applyFloat(factor3, this.count5);
         } else if (count == 1) {
            float factor7;
            if (this.count4 <= 4) {
               factor7 = factor * this.factor;
            } else if (this.count4 <= 8) {
               factor7 = factor * this.factor2;
            } else {
               factor7 = factor * this.factor3;
            }

            sfdSerController.applyFloat(factor7, this.count5);
         } else {
            float factor8;
            if (this.count4 <= 4) {
               factor8 = factor * this.factor4;
            } else if (this.count4 <= 8) {
               factor8 = factor * this.factor5;
            } else {
               factor8 = factor * this.factor6;
            }

            sfdSerController.applyFloat(factor8, this.count5);
         }
      }

      return ++this.count3 == 3;
   }

   @Override
   public void applyDepthFirstPipeline(DepthFirstPipeline depthFirstPipeline, AStarService aStarService) {
      if (this.count != 0) {
         super.applyDepthFirstPipeline(depthFirstPipeline, aStarService);
         switch (this.count6) {
            case 0:
               this.factor4 = factorArray[depthFirstPipeline.computeCount3(6)];
               this.factor5 = factorArray[depthFirstPipeline.computeCount3(6)];
               this.factor6 = factorArray[depthFirstPipeline.computeCount3(6)];
               break;
            case 1:
               this.factor4 = this.factor5 = factorArray[depthFirstPipeline.computeCount3(6)];
               this.factor6 = factorArray[depthFirstPipeline.computeCount3(6)];
               break;
            case 2:
               this.factor4 = this.factor5 = this.factor6 = factorArray[depthFirstPipeline.computeCount3(6)];
               break;
            case 3:
               this.factor4 = factorArray[depthFirstPipeline.computeCount3(6)];
               this.factor5 = this.factor6 = factorArray[depthFirstPipeline.computeCount3(6)];
         }
      }
   }

   @Override
   public boolean matchesDepthFirstPipeline(DepthFirstPipeline depthFirstPipeline) {
      return super.matchesDepthFirstPipeline(depthFirstPipeline);
   }
}
