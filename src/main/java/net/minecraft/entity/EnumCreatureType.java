package net.minecraft.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;

public enum EnumCreatureType {
   MONSTER(IMob.class, 70, Material.air, false, false),
   CREATURE(EntityAnimal.class, 10, Material.air, true, true),
   AMBIENT(EntityAmbientCreature.class, 15, Material.air, true, false),
   WATER_CREATURE(EntityWaterMob.class, 5, Material.water, true, false);

private final Class creatureClass;
   private final int maxNumberOfCreature;
   private final Material creatureMaterial;
   private final boolean isPeacefulCreature;
   private final boolean isAnimal;

   public boolean getAnimal() {
      return this.isAnimal;
   }

   public int getMaxNumberOfCreature() {
      return this.maxNumberOfCreature;
   }

   public boolean getPeacefulCreature() {
      return this.isPeacefulCreature;
   }

   private EnumCreatureType(Class clazz, int n, Material material, boolean flag, boolean flag2) {
      this.creatureClass = clazz;
      this.maxNumberOfCreature = n;
      this.creatureMaterial = material;
      this.isPeacefulCreature = flag;
      this.isAnimal = flag2;
   }

   public Class getCreatureClass() {
      return this.creatureClass;
   }
}
