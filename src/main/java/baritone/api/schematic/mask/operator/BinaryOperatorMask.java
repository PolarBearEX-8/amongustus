package baritone.api.schematic.mask.operator;

import baritone.api.schematic.mask.AbstractMask;
import baritone.api.schematic.mask.Mask;
import baritone.api.utils.BooleanBinaryOperator;
import net.minecraft.block.state.IBlockState;

public final class BinaryOperatorMask extends AbstractMask {
   private final Mask a;
   private final Mask b;
   private final BooleanBinaryOperator operator;

   private static boolean partOfMask(Mask mask, int n, int n2, int n3, IBlockState state) {
      return n < mask.widthX() && n2 < mask.heightY() && n3 < mask.lengthZ() && mask.partOfMask(n, n2, n3, state);
   }

   public BinaryOperatorMask(Mask mask, Mask mask2, BooleanBinaryOperator booleanBinaryOperator) {
      super(Math.max(mask.widthX(), mask2.widthX()), Math.max(mask.heightY(), mask2.heightY()), Math.max(mask.lengthZ(), mask2.lengthZ()));
      this.a = mask;
      this.b = mask2;
      this.operator = booleanBinaryOperator;
   }

   @Override
   public boolean partOfMask(int n, int n2, int n3, IBlockState state) {
      return this.operator.applyAsBoolean(partOfMask(this.a, n, n2, n3, state), partOfMask(this.b, n, n2, n3, state));
   }
}
