package client.util;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionAdapter extends Potion {
   @Override
   public double computeValue(int count, InvalidOperationManager invalidOperationManager) {
      return this.indexOffset3 == Potion.potion26.indexOffset3 ? (double)(-0.5F * (float)(count + 1)) : 1.3 * (double)(count + 1);
   }

   public PotionAdapter(int count, ResourceLocation location, boolean flag, int count2) {
      super(count, location, flag, count2);
   }
}
