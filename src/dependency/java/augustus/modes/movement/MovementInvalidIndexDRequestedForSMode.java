package augustus.modes.movement;

import client.util.BaseManagerStyleIChatComponentAdapter;

public class MovementInvalidIndexDRequestedForSMode extends IllegalArgumentException {
   public MovementInvalidIndexDRequestedForSMode(BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter, String text) {
      super(String.format("Error parsing: %s: %s", baseManagerStyleIChatComponentAdapter, text));
   }

   public MovementInvalidIndexDRequestedForSMode(BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter, Throwable throwable) {
      super(String.format("Error while parsing: %s", baseManagerStyleIChatComponentAdapter), throwable);
   }

   public MovementInvalidIndexDRequestedForSMode(BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter, int count) {
      super(String.format("Invalid index %d requested for %s", count, baseManagerStyleIChatComponentAdapter));
   }
}
