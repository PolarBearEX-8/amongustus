package net.augustus.modules.movement;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.world.World;

public class FlyDragonEntity extends EntityDragon {
   public final Fly fly;

   public FlyDragonEntity(Fly fly2, World world) {
      super(world);
      this.fly = fly2;
   }

   public boolean isSilent() {
      return !Fly.getEnderDragonSoundSetting(this.fly).getValue();
   }

   public void collideWithEntity(Entity entity) {
   }

   public void collideWithNearbyEntities() {
   }
}
