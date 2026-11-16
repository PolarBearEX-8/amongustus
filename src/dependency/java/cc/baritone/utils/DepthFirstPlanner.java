package cc.baritone.utils;

public enum DepthFirstPlanner {
    EXPLORED,
    NOT_EXPLORED,
    UNKNOWN;

   public static final DepthFirstPlanner[] ENUM_VALUES = new DepthFirstPlanner[]{EXPLORED, NOT_EXPLORED, UNKNOWN};

}
