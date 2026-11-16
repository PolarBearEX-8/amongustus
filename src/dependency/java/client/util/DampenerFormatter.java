package client.util;

import net.minecraft.util.BlockPos;

public class DampenerFormatter {
   public BlockPos blockPos;
   public long timestamp;

   public DampenerFormatter(BlockPos blockPos2, long value) {
      this.blockPos = blockPos2;
      this.timestamp = value;
   }
}
