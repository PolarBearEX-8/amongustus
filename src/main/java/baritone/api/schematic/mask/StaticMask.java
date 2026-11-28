package baritone.api.schematic.mask;

import baritone.api.schematic.mask.operator.MaskBinaryOperator;
import baritone.api.schematic.mask.operator.StaticBinaryOperatorMask;
import baritone.api.schematic.mask.operator.StaticNotMask;
import net.minecraft.block.state.IBlockState;

public interface StaticMask extends Mask {
   boolean partOfMask(int n, int n2, int n3);

   default StaticMask not2() {
      return new StaticNotMask(this);
   }

   default StaticMask compute() {
      return new PreComputedMask(this);
   }

   @Override
   default boolean partOfMask(int n, int n2, int n3, IBlockState state) {
      return this.partOfMask(n, n2, n3);
   }

   default StaticMask union2(StaticMask staticMask) {
      return new StaticBinaryOperatorMask(this, staticMask, MaskBinaryOperator.OR);
   }

   @Override
   default Mask not() {
      return this.not2();
   }

   default StaticMask xor2(StaticMask staticMask) {
      return new StaticBinaryOperatorMask(this, staticMask, MaskBinaryOperator.XOR);
   }

   default StaticMask intersection2(StaticMask staticMask) {
      return new StaticBinaryOperatorMask(this, staticMask, MaskBinaryOperator.AND);
   }
}
