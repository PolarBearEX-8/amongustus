package cc.baritone.utils;

public enum HeuristicValidator {
    SUCCESS_TO_GOAL,
    SUCCESS_SEGMENT,
    FAILURE,
    CANCELLATION,
    EXCEPTION;

   public static final HeuristicValidator[] ENUM_VALUES = new HeuristicValidator[]{SUCCESS_TO_GOAL, SUCCESS_SEGMENT, FAILURE, CANCELLATION, EXCEPTION};

}
