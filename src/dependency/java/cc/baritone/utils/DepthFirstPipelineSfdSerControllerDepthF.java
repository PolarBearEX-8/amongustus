package cc.baritone.utils;

public class DepthFirstPipelineSfdSerControllerDepthF extends SfdSerControllerDepthFirstPipe {
   public float factor5;

   @Override
   public void applyDepthFirstPipeline(DepthFirstPipeline depthFirstPipeline, AStarService aStarService, ControllerProvider controllerProvider) throws RuntimeComponent {
      super.applyDepthFirstPipeline(depthFirstPipeline, aStarService, controllerProvider);
   }

   public DepthFirstPipelineSfdSerControllerDepthF(int count) {
      super(count);
   }

   @Override
   public boolean matchesSfdSerController(int count, SfdSerController sfdSerController, SfdSerController sfdSerController2) {
      if (this.count3 != 0) {
         this.factor2 = this.factor2 * this.factor3 + this.factor4;
         if (count == 0) {
            float factor = this.factor2 * this.factor;
            float factor2 = this.factor2 * this.factor5;
            sfdSerController.applyFloat(factor, this.count);
            sfdSerController2.applyFloat(factor2, this.count);
         } else if (count == 1) {
            float factor3 = this.factor2 * this.factor;
            sfdSerController.applyFloat(factor3, this.count);
         } else {
            float factor4 = this.factor2 * this.factor5;
            sfdSerController.applyFloat(factor4, this.count);
         }
      }

      return true;
   }

   @Override
   public boolean matchesDepthFirstPipeline(DepthFirstPipeline depthFirstPipeline) {
      return super.matchesDepthFirstPipeline(depthFirstPipeline);
   }

   @Override
   public void applyDepthFirstPipeline(DepthFirstPipeline depthFirstPipeline, AStarService aStarService) {
      if (this.count3 != 0) {
         this.factor = factorArray[depthFirstPipeline.computeCount3(6)];
         this.factor5 = factorArray[depthFirstPipeline.computeCount3(6)];
      }
   }
}
