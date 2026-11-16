package client.event;

import java.util.Set;

public interface DynamicListener {
   default Set getSet() {
      return null;
   }

   void run(String text);

   default boolean isActive() {
      return false;
   }

   default String getLabelText() {
      return null;
   }
}
