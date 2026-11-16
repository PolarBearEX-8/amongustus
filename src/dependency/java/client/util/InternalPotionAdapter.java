package client.util;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class InternalPotionAdapter extends Potion {
   @Override
   public boolean checkIndex(int count, int count2) {
      return count >= 1;
   }

   @Override
   public boolean isActive() {
      return true;
   }

   public InternalPotionAdapter(int count, ResourceLocation location, boolean flag, int count2) {
      super(count, location, flag, count2);
   }
}
