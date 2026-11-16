package cc.baritone.utils;

import net.minecraft.entity.Entity;

public interface SecondaryListener12Extension extends SpecialListener {
   boolean matchesEntity(Entity entity);

   void applyEntity(Entity entity);
}
