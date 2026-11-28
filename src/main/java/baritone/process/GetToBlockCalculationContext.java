package baritone.process;

import baritone.pathing.movement.CalculationContext;
import net.minecraft.block.state.IBlockState;

public class GetToBlockCalculationContext extends CalculationContext {
   public final GetToBlockProcess getToBlockProcess;

   @Override
   public double breakCostMultiplierAt(int n, int n2, int n3, IBlockState state) {
      return 1.0;
   }

   public GetToBlockCalculationContext(GetToBlockProcess getToBlockProcess2, boolean flag) {
      super(GetToBlockProcess.getBaritone(getToBlockProcess2), flag);
      this.getToBlockProcess = getToBlockProcess2;
   }
}
