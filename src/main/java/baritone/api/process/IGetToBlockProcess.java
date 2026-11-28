package baritone.api.process;

import baritone.api.utils.BlockOptionalMeta;
import net.minecraft.block.Block;

public interface IGetToBlockProcess extends IBaritoneProcess {
   void getToBlock(BlockOptionalMeta blockOptionalMeta);

   boolean blacklistClosest();

   default void getToBlock2(Block block) {
      this.getToBlock(new BlockOptionalMeta(block));
   }
}
