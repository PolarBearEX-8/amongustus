package baritone.api.schematic.mask;

import baritone.api.schematic.mask.operator.BinaryOperatorMask;
import baritone.api.schematic.mask.operator.MaskBinaryOperator;
import baritone.api.schematic.mask.operator.NotMask;
import net.minecraft.block.state.IBlockState;

public interface Mask {
   default Mask intersection(Mask mask) {
      return new BinaryOperatorMask(this, mask, MaskBinaryOperator.AND);
   }

   int heightY();

   default Mask union(Mask mask) {
      return new BinaryOperatorMask(this, mask, MaskBinaryOperator.OR);
   }

   int widthX();

   int lengthZ();

   boolean partOfMask(int n, int n2, int n3, IBlockState state);

   default Mask not() {
      return new NotMask(this);
   }

   default Mask xor(Mask mask) {
      return new BinaryOperatorMask(this, mask, MaskBinaryOperator.XOR);
   }
}
