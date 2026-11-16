package client.util;

public interface Service2 {
   default boolean matchesUUIDControllerHandler(UUIDControllerHandler uUIDControllerHandler) {
      return true;
   }

   default void applyUUIDControllerHandler(UUIDControllerHandler uUIDControllerHandler) {
   }
}
