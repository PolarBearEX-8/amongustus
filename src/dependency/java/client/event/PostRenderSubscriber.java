package client.event;

import client.util.HitBoxAggregator;

@FunctionalInterface
public interface PostRenderSubscriber {
   void applyHitBoxAggregator(HitBoxAggregator hitBoxAggregator);
}
