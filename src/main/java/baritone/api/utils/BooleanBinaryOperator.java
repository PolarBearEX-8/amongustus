package baritone.api.utils;

@FunctionalInterface
public interface BooleanBinaryOperator {
   boolean applyAsBoolean(boolean flag, boolean flag2);
}
