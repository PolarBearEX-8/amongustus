package cc.baritone.utils;

public interface AStarSamplerService {
   void applyAStarSampler(AStarSampler aStarSampler);

   AStarSampler getAStarSampler();

   void applyAStarSamplerByAStarSampler(AStarSampler aStarSampler);

   boolean isActive();
}
