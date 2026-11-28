package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import net.augustus.modules.render.BlockMarker;
import net.minecraft.block.Block;

public class BlockMarkerBlockOption implements SelectableOption {
   private final String name;
   private final Block block;
   private boolean selected;
   public final BlockMarker blockMarker;

   public BlockMarkerBlockOption(BlockMarker blockMarker2, String str, Block block2) {
      this.blockMarker = blockMarker2;
      this.name = str;
      this.block = block2;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   @Override
   public void setSelected(boolean flag) {
      this.selected = flag;
   }

   public static Block getBlock(BlockMarkerBlockOption blockMarkerBlockOption) {
      return blockMarkerBlockOption.block;
   }
}
