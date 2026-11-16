package client.util;

import client.event.NativeListener;
import net.minecraft.world.World;

public abstract class HitBoxBuilder extends AuxiliaryUUIDEntry implements NativeListener {
   @Override
   public String getText9() {
      return "none";
   }

   @Override
   public void processFloat2(float factor, float factor2) {
   }

   @Override
   public String getText3() {
      return "none";
   }

   @Override
   public String getText10() {
      return "none";
   }

   @Override
   public int getCount7() {
      return 120;
   }

   @Override
   public boolean isActive24() {
      return false;
   }

   public HitBoxBuilder(World world) {
      super(world);
   }
}
