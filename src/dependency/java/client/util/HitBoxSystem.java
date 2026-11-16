package client.util;

import net.minecraft.entity.EntitySpider;

public class HitBoxSystem extends ExtendedClassEntry {
   @Override
   public boolean isActive() {
      return this.auxiliaryUUIDEntry.computeFactor(1.0F) >= 0.5F ? false : super.isActive();
   }

   public HitBoxSystem(EntitySpider entitySpider, Class clazz) {
      super(entitySpider, clazz, true);
   }
}
