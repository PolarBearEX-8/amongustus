package client.util;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class TertiaryRandomEntry {
   public int count = 8;
   public Random random = new Random();
   public World world;

   public void applyIChunkProvider(IChunkProvider iChunkProvider, World world2, int count2, int count3, ShortArrayEntry shortArrayEntry) {
      int count4 = this.count;
      this.world = world2;
      this.random.setSeed(world2.getTimestamp());
      long value = this.random.nextLong();
      long value2 = this.random.nextLong();

      for (int index = count2 - count4; index <= count2 + count4; index++) {
         for (int index2 = count3 - count4; index2 <= count3 + count4; index2++) {
            long value3 = (long)index * value;
            long value4 = (long)index2 * value2;
            this.random.setSeed(value3 ^ value4 ^ world2.getTimestamp());
            this.applyWorld(world2, index, index2, count2, count3, shortArrayEntry);
         }
      }
   }

   public void applyWorld(World world2, int count2, int count3, int count4, int count5, ShortArrayEntry shortArrayEntry) {
   }
}
