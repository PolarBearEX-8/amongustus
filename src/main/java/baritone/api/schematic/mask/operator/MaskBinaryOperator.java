package baritone.api.schematic.mask.operator;


import baritone.api.utils.BooleanBinaryOperator;
public enum MaskBinaryOperator implements BooleanBinaryOperator {
   OR((flag, flag2) -> flag || flag2),
   AND((flag, flag2) -> flag && flag2),
   XOR((flag, flag2) -> flag ^ flag2);
   private final BooleanBinaryOperator operator;
   private MaskBinaryOperator(BooleanBinaryOperator booleanBinaryOperator) {
      this.operator = booleanBinaryOperator;
   }

   @Override
   public boolean applyAsBoolean(boolean flag, boolean flag2) {
      return this.operator.applyAsBoolean(flag, flag2);
   }

}
