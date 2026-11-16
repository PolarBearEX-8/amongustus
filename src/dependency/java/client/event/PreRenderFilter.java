package client.event;

import client.util.HitBoxEstimator;

@FunctionalInterface
public interface PreRenderFilter {
   void applyHitBoxEstimator(HitBoxEstimator hitBoxEstimator);
}
