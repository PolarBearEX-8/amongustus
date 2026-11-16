package client.util;

import client.setting.StandardOptionType;
import java.util.Random;
import net.minecraft.world.World;

public class TargetDeserializer extends BiomeIDIsOutOfBoundsManager {
   @Override
   public StandardOptionType getOCEAN() {
      return StandardOptionType.OCEAN;
   }

   @Override
   public void applyWorld(World world, Random random, ShortArrayEntry shortArrayEntry, int count, int count2, double offset) {
      super.applyWorld(world, random, shortArrayEntry, count, count2, offset);
   }

   public TargetDeserializer(int count) {
      super(count);
      this.list4.clear();
   }
}
