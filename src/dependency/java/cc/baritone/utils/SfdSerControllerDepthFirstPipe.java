package cc.baritone.utils;

public class SfdSerControllerDepthFirstPipe extends AbstractDepthFirstPipelineController {
   public static final float[] factorArray2 = new float[]{
      0.0F,
      0.6666667F,
      0.2857143F,
      0.13333334F,
      0.06451613F,
      0.031746034F,
      0.015748031F,
      0.007843138F,
      0.0039138943F,
      0.0019550342F,
      9.770396E-4F,
      4.884005E-4F,
      2.4417043E-4F,
      1.2207776E-4F,
      6.103702E-5F
   };
   public static final float[] factorArray3 = new float[]{
      0.0F,
      -0.6666667F,
      -0.8571429F,
      -0.9333334F,
      -0.9677419F,
      -0.98412704F,
      -0.992126F,
      -0.9960785F,
      -0.99804306F,
      -0.9990225F,
      -0.9995115F,
      -0.99975586F,
      -0.9998779F,
      -0.99993896F,
      -0.9999695F
   };
   public int count;
   public int count2;
   public int count3;
   public float factor;
   public int count4;
   public float factor2;
   public float factor3;
   public float factor4;

   @Override
   public boolean matchesSfdSerController(int count5, SfdSerController sfdSerController, SfdSerController sfdSerController2) {
      if (this.count3 != 0 && count5 != 2) {
         float factor5 = (this.factor2 * this.factor3 + this.factor4) * this.factor;
         sfdSerController.applyFloat(factor5, this.count);
      }

      return true;
   }

   @Override
   public boolean matchesDepthFirstPipeline(DepthFirstPipeline depthFirstPipeline) {
      if (this.count3 != 0) {
         this.factor2 = (float)depthFirstPipeline.computeCount3(this.count4);
      }

      if (++this.count2 == 12) {
         this.count2 = 0;
         return true;
      } else {
         return false;
      }
   }

   public SfdSerControllerDepthFirstPipe(int count5) {
      this.count = count5;
      this.count2 = 0;
   }

   @Override
   public void applyDepthFirstPipeline(DepthFirstPipeline depthFirstPipeline, AStarService aStarService, ControllerProvider controllerProvider) throws RuntimeComponent {
      if ((this.count3 = depthFirstPipeline.computeCount3(4)) == 15) {
         throw new RuntimeComponent(514, null);
      } else {
         if (controllerProvider != null) {
            controllerProvider.applyIndex(this.count3, 4);
         }

         if (this.count3 != 0) {
            this.count4 = this.count3 + 1;
            this.factor3 = factorArray2[this.count3];
            this.factor4 = factorArray3[this.count3];
         }
      }
   }

   @Override
   public void applyDepthFirstPipeline(DepthFirstPipeline depthFirstPipeline, AStarService aStarService) {
      if (this.count3 != 0) {
         this.factor = factorArray[depthFirstPipeline.computeCount3(6)];
      }
   }
}
