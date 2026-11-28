package baritone.api.process;

import baritone.api.utils.BlockOptionalMeta;
import baritone.api.utils.BlockOptionalMetaLookup;
import java.util.stream.Stream;
import net.minecraft.block.Block;

public interface IMineProcess extends IBaritoneProcess {
   default void mine2(BlockOptionalMetaLookup blockOptionalMetaLookup) {
      this.mine(0, blockOptionalMetaLookup);
   }

   default void mine3(int n, Block[] blocks) {
      this.mine(n, new BlockOptionalMetaLookup((BlockOptionalMeta[])Stream.of(blocks).map(BlockOptionalMeta::new).toArray(IMineProcess::newBlockOptionalMetaArray)));
   }

   default void mine(Block[] blocks) {
      this.mine3(0, blocks);
   }

   void mineByName(int n, String[] strings);

   static BlockOptionalMeta[] newBlockOptionalMetaArray(int n) {
      return new BlockOptionalMeta[n];
   }

   default void mineByName(String[] strings) {
      this.mineByName(0, strings);
   }

   default void mine2(int n, BlockOptionalMeta[] blockOptionalMetas) {
      this.mine(n, new BlockOptionalMetaLookup(blockOptionalMetas));
   }

   void mine(int n, BlockOptionalMetaLookup blockOptionalMetaLookup);

   default void mine3(BlockOptionalMeta[] blockOptionalMetas) {
      this.mine2(0, blockOptionalMetas);
   }

   default void cancel() {
      this.onLostControl();
   }
}
