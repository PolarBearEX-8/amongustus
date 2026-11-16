package client.event;

import client.util.HitBoxProvider;

@FunctionalInterface
public interface FactoryListener {
   void applyHitBoxProvider(HitBoxProvider hitBoxProvider);
}
