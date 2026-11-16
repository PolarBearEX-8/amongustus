package client.util;

import net.minecraft.entity.EntityPigzombie;
import net.minecraft.entity.player.EntityPlayer;

public class HitBoxCollector extends ExtendedClassEntry {
   @Override
   public boolean isActive() {
      return ((EntityPigzombie)this.auxiliaryUUIDEntry).isActive34() && super.isActive();
   }

   public HitBoxCollector(EntityPigzombie entityPigzombie) {
      super(entityPigzombie, EntityPlayer.class, true);
   }
}
