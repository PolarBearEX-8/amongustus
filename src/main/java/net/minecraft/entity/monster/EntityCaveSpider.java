package net.minecraft.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityCaveSpider extends EntitySpider {
   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficultyInstance, IEntityLivingData entityLivingData) {
      return entityLivingData;
   }

   @Override
   public float getEyeHeight() {
      return 0.45F;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0);
   }

   public EntityCaveSpider(World world) {
      super(world);
      this.setSize(0.7F, 0.5F);
   }

   @Override
   public boolean attackEntityAsMob(Entity entity) {
      if (super.attackEntityAsMob(entity)) {
         if (entity instanceof EntityLivingBase) {
            byte b = 0;
            if (this.entityWorld.getDifficulty() == EnumDifficulty.NORMAL) {
               b = 7;
            } else if (this.entityWorld.getDifficulty() == EnumDifficulty.HARD) {
               b = 15;
            }

            if (b > 0) {
               ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, b * 20, 0));
            }
         }

         return true;
      } else {
         return false;
      }
   }
}
