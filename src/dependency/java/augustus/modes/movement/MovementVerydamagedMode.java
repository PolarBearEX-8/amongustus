package augustus.modes.movement;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntry;

public class MovementVerydamagedMode extends BlockEntry {
   @Override
   public int processAction2(int count) {
      return count << 2;
   }

   public MovementVerydamagedMode(Block block) {
      super(block, block, new String[]{"intact", "slightlyDamaged", "veryDamaged"});
   }
}
