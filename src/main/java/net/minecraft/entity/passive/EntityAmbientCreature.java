package net.minecraft.entity.passive;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class EntityAmbientCreature extends EntityLiving implements IAnimals {
   @Override
   public boolean allowLeashing() {
      return false;
   }

   public EntityAmbientCreature(World world) {
      super(world);
   }

   @Override
   public boolean interact(EntityPlayer player) {
      return false;
   }
}
