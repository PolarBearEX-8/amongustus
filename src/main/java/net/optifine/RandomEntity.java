package net.optifine;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;

public class RandomEntity implements IRandomEntity {
   private Entity entity;

   @Override
   public String getName() {
      return this.entity.hasCustomName() ? this.entity.getCustomNameTag() : null;
   }

   @Override
   public BlockPos getSpawnPosition() {
      return this.entity.getDataWatcher().spawnPosition;
   }

   @Override
   public int getId() {
      return (int)(this.entity.getUniqueID().getLeastSignificantBits() & 2147483647L);
   }

   @Override
   public int getHealth() {
      if (!(this.entity instanceof EntityLiving)) {
         return 0;
      } else {
         return (int)((EntityLiving)this.entity).getCurrentHealth();
      }
   }

   public Entity getEntity() {
      return this.entity;
   }

   @Override
   public int getMaxHealth() {
      if (!(this.entity instanceof EntityLiving)) {
         return 0;
      } else {
         return (int)((EntityLiving)this.entity).getMaxHealth();
      }
   }

   public void setEntity(Entity entity2) {
      this.entity = entity2;
   }

   @Override
   public BiomeGenBase getSpawnBiome() {
      return this.entity.getDataWatcher().spawnBiome;
   }
}
