package cc.baritone.utils;

public enum AuxiliaryOptionType {
    NONE,
    GOAL_SET,
    PATH_REQUESTED,
    EXECUTING;

   public static final AuxiliaryOptionType[] ENUM_VALUES = new AuxiliaryOptionType[]{NONE, GOAL_SET, PATH_REQUESTED, EXECUTING};

}
