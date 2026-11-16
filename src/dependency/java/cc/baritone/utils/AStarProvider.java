package cc.baritone.utils;

public enum AStarProvider implements CommonListener {
    INSTANCE;

   @Override
   public AStarTracker getAStarTracker() {
      return RuntimeComponentHandler.INSTANCE;
   }
}
