package cc.baritone.utils;

public class AStarSamplerHelper {
   public AStarSamplerHelper aStarSamplerHelper;
   public AStarSampler aStarSampler;

   public static AStarSamplerHelper resolveAStarSamplerHelper(AStarSamplerHelper aStarSamplerHelper2) {
      return aStarSamplerHelper2.aStarSamplerHelper;
   }

   public static AStarSamplerHelper resolveAStarSamplerHelper(AStarSamplerHelper aStarSamplerHelper2, AStarSamplerHelper aStarSamplerHelper3) {
      return aStarSamplerHelper2.aStarSamplerHelper = aStarSamplerHelper3;
   }

   public static AStarSampler resolveAStarSampler(AStarSamplerHelper aStarSamplerHelper2) {
      return aStarSamplerHelper2.aStarSampler;
   }

   public static AStarSampler resolveAStarSampler(AStarSamplerHelper aStarSamplerHelper2, AStarSampler aStarSampler2) {
      return aStarSamplerHelper2.aStarSampler = aStarSampler2;
   }
}
