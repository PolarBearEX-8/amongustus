package baritone.api.process;

import net.minecraft.util.BlockPos;

public interface IFarmProcess extends IBaritoneProcess {
   default void farm(int n) {
      this.farm(n, null);
   }

   void farm(int n, BlockPos pos);

   default void farm() {
      this.farm(0, null);
   }
}
