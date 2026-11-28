package baritone.api.cache;

import java.util.function.Consumer;

public interface IWorldProvider {
   IWorldData getCurrentWorld();

   default void ifWorldLoaded(Consumer consumer) {
      IWorldData currentWorld = this.getCurrentWorld();
      if (currentWorld != null) {
         consumer.accept(currentWorld);
      }
   }
}
