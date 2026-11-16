package cc.baritone.utils;

public enum BreadthFirstService {
    LOAD,
    UNLOAD,
    POPULATE_FULL,
    POPULATE_PARTIAL;

   public static final BreadthFirstService[] ENUM_VALUES = new BreadthFirstService[]{LOAD, UNLOAD, POPULATE_FULL, POPULATE_PARTIAL};

   public final boolean isActive() {
      return this == POPULATE_FULL || this == POPULATE_PARTIAL;
   }


}
