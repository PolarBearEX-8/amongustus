package client.util;

import net.minecraft.entity.EntityVillagergolem;

public class HitBoxSelector extends RotationPool {
   public final EntityVillagergolem ironGolem;

   public HitBoxSelector(EntityVillagergolem ironGolem) {
      this.ironGolem = ironGolem;
   }

   @Override
   public boolean isActive() {
      return false;
   }
}
