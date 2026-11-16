package cc.baritone.utils;

public class DepthFirstPipelineDepthFirstPipelineDept extends DepthFirstPipelineDepthFirstPipe {
   public int count6;
   public int count7;
   public float factor4;
   public float factor5;
   public float factor6;
   public int[] countArray7 = new int[]{0};
   public float[] factorArray28 = new float[]{0.0F};
   public float[] factorArray29;
   public float[] factorArray30 = new float[]{0.0F};
   public float[] factorArray31 = new float[]{0.0F};

   @Override
   public boolean matchesDepthFirstPipeline(DepthFirstPipeline depthFirstPipeline) {
      boolean flag = super.matchesDepthFirstPipeline(depthFirstPipeline);
      if (this.count6 != 0) {
         if (this.factorArray2[1] != null) {
            int count = depthFirstPipeline.computeCount3(this.countArray7[0]);
            int count2 = count + (count << 1);
            float[] factorArray = this.factorArray29;
            float[] factorArray2 = this.factorArray2[1];
            int count3 = 0;
            factorArray[0] = factorArray2[count2];
            count2++;
            count3++;
            factorArray[1] = factorArray2[count2];
            count2++;
            count3++;
            factorArray[2] = factorArray2[count2];
         } else {
            this.factorArray29[0] = (float)((double)((float)depthFirstPipeline.computeCount3(this.countArray7[0]) * this.factorArray28[0]) - 1.0);
            this.factorArray29[1] = (float)((double)((float)depthFirstPipeline.computeCount3(this.countArray7[0]) * this.factorArray28[0]) - 1.0);
            this.factorArray29[2] = (float)((double)((float)depthFirstPipeline.computeCount3(this.countArray7[0]) * this.factorArray28[0]) - 1.0);
         }
      }

      return flag;
   }

   @Override
   public void applyDepthFirstPipeline(DepthFirstPipeline depthFirstPipeline, ControllerProvider controllerProvider) {
      if (this.count != 0) {
         this.count2 = depthFirstPipeline.computeCount3(2);
         if (controllerProvider != null) {
            controllerProvider.applyIndex(this.count2, 2);
         }
      }

      if (this.count6 != 0) {
         this.count7 = depthFirstPipeline.computeCount3(2);
         if (controllerProvider != null) {
            controllerProvider.applyIndex(this.count7, 2);
         }
      }
   }

   @Override
   public boolean matchesSfdSerController(int count, SfdSerController sfdSerController, SfdSerController sfdSerController2) {
      boolean flag = super.matchesSfdSerController(count, sfdSerController, sfdSerController2);
      if (this.count6 != 0 && count != 1) {
         float factor = this.factorArray29[this.count3 - 1];
         if (this.factorArray2[1] == null) {
            factor = (factor + this.factorArray31[0]) * this.factorArray30[0];
         }

         float factor2;
         if (this.count4 <= 4) {
            factor2 = factor * this.factor4;
         } else if (this.count4 <= 8) {
            factor2 = factor * this.factor5;
         } else {
            factor2 = factor * this.factor6;
         }

         if (count == 0) {
            sfdSerController2.applyFloat(factor2, this.count5);
         } else {
            sfdSerController.applyFloat(factor2, this.count5);
         }
      }

      return flag;
   }

   public DepthFirstPipelineDepthFirstPipelineDept(int count) {
      super(count);
      this.factorArray29 = new float[3];
   }

   @Override
   public void applyDepthFirstPipeline(DepthFirstPipeline depthFirstPipeline, AStarService aStarService) {
      super.applyDepthFirstPipeline(depthFirstPipeline, aStarService);
      if (this.count6 != 0) {
         switch (this.count7) {
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

         this.applyAStarService(aStarService, this.count6, 1, this.factorArray28, this.countArray7, this.factorArray30, this.factorArray31);
      }
   }

   @Override
   public void applyDepthFirstPipeline(DepthFirstPipeline depthFirstPipeline, AStarService aStarService, ControllerProvider controllerProvider) {
      int count = this.computeCount(aStarService);
      this.count = depthFirstPipeline.computeCount3(count);
      this.count6 = depthFirstPipeline.computeCount3(count);
      if (controllerProvider != null) {
         controllerProvider.applyIndex(this.count, count);
         controllerProvider.applyIndex(this.count6, count);
      }
   }
}
