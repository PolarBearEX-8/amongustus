package cc.baritone.utils;

public class AStarSamplerHelperAStarSamplerService implements AStarSamplerService {
   public AStarSamplerHelper aStarSamplerHelper = null;

   @Override
   public boolean isActive() {
      return this.aStarSamplerHelper == null;
   }

   @Override
   public void applyAStarSampler(AStarSampler aStarSampler) {
   }

   @Override
   public void applyAStarSamplerByAStarSampler(AStarSampler aStarSampler) {
      AStarSamplerHelper aStarSamplerHelper2 = new AStarSamplerHelper();
      AStarSamplerHelper.resolveAStarSampler(aStarSamplerHelper2, aStarSampler);
      AStarSamplerHelper.resolveAStarSamplerHelper(aStarSamplerHelper2, this.aStarSamplerHelper);
      this.aStarSamplerHelper = aStarSamplerHelper2;
   }

   @Override
   public AStarSampler getAStarSampler() {
      if (this.aStarSamplerHelper == null) {
         return null;
      } else {
         AStarSamplerHelper aStarSamplerHelper2 = AStarSamplerHelper.resolveAStarSamplerHelper(this.aStarSamplerHelper);
         if (aStarSamplerHelper2 == null) {
            AStarSamplerHelper aStarSamplerHelper3 = this.aStarSamplerHelper;
            this.aStarSamplerHelper = null;
            return AStarSamplerHelper.resolveAStarSampler(aStarSamplerHelper3);
         } else {
            AStarSamplerHelper aStarSamplerHelper4 = this.aStarSamplerHelper;
            double value = AStarSamplerHelper.resolveAStarSampler(this.aStarSamplerHelper).value3;
            AStarSamplerHelper aStarSamplerHelper5 = this.aStarSamplerHelper;

            AStarSamplerHelper aStarSamplerHelper6;
            for (aStarSamplerHelper6 = null; aStarSamplerHelper2 != null; aStarSamplerHelper2 = AStarSamplerHelper.resolveAStarSamplerHelper(aStarSamplerHelper2)) {
               double value2 = AStarSamplerHelper.resolveAStarSampler(aStarSamplerHelper2).value3;
               if (value2 < value) {
                  value = value2;
                  aStarSamplerHelper5 = aStarSamplerHelper2;
                  aStarSamplerHelper6 = aStarSamplerHelper4;
               }

               aStarSamplerHelper4 = aStarSamplerHelper2;
            }

            if (aStarSamplerHelper6 == null) {
               this.aStarSamplerHelper = AStarSamplerHelper.resolveAStarSamplerHelper(this.aStarSamplerHelper);
               AStarSamplerHelper.resolveAStarSamplerHelper(aStarSamplerHelper5, null);
               return AStarSamplerHelper.resolveAStarSampler(aStarSamplerHelper5);
            } else {
               AStarSamplerHelper.resolveAStarSamplerHelper(aStarSamplerHelper6, AStarSamplerHelper.resolveAStarSamplerHelper(aStarSamplerHelper5));
               AStarSamplerHelper.resolveAStarSamplerHelper(aStarSamplerHelper5, null);
               return AStarSamplerHelper.resolveAStarSampler(aStarSamplerHelper5);
            }
         }
      }
   }
}
