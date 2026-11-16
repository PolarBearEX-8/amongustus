package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityVillagergolem;

public class DiamondScythe extends PassiveFloatEntry {
   public BoxesManager boxesManager5 = new BoxesManager(this).resolveBoxesManager(128, 128);
   public BoxesManager boxesManager2;
   public BoxesManager boxesManager3;
   public BoxesManager boxesManager;
   public BoxesManager boxesManager4;
   public BoxesManager boxesManager6;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager5.applyFloat3(posZ);
      this.boxesManager2.applyFloat3(posZ);
      this.boxesManager4.applyFloat3(posZ);
      this.boxesManager6.applyFloat3(posZ);
      this.boxesManager3.applyFloat3(posZ);
      this.boxesManager.applyFloat3(posZ);
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      this.boxesManager5.factor9 = posX4 / (180.0F / (float)Math.PI);
      this.boxesManager5.factor = posY / (180.0F / (float)Math.PI);
      this.boxesManager4.factor = -1.5F * this.abs(posX, 13.0F) * posX2;
      this.boxesManager6.factor = 1.5F * this.abs(posX, 13.0F) * posX2;
      this.boxesManager4.factor9 = 0.0F;
      this.boxesManager6.factor9 = 0.0F;
   }

   private float abs(float factor, float factor2) {
      return (Math.abs(factor % factor2 - factor2 * 0.5F) - factor2 * 0.25F) / (factor2 * 0.25F);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      EntityVillagergolem entityVillagergolem = (EntityVillagergolem)livingEntity;
      int count = entityVillagergolem.getCount30();
      if (count > 0) {
         this.boxesManager3.factor = -2.0F + 1.5F * this.abs((float)count - posZ, 10.0F);
         this.boxesManager.factor = -2.0F + 1.5F * this.abs((float)count - posZ, 10.0F);
      } else {
         int count2 = entityVillagergolem.getCount28();
         if (count2 > 0) {
            this.boxesManager3.factor = -0.8F + 0.025F * this.abs((float)count2, 70.0F);
            this.boxesManager.factor = 0.0F;
         } else {
            this.boxesManager3.factor = (-0.2F + 1.5F * this.abs(posX, 13.0F)) * posY;
            this.boxesManager.factor = (-0.2F - 1.5F * this.abs(posX, 13.0F)) * posY;
         }
      }
   }

   public DiamondScythe(float factor, float factor2) {
      this.boxesManager5.applyFloat(0.0F, 0.0F + factor2, -2.0F);
      this.boxesManager5.resolveBoxesManagerByIndex(0, 0).applyFloatByFloat(-4.0F, -12.0F, -5.5F, 8, 10, 8, factor);
      this.boxesManager5.resolveBoxesManagerByIndex(24, 0).applyFloatByFloat(-1.0F, -5.0F, -7.5F, 2, 4, 2, factor);
      this.boxesManager2 = new BoxesManager(this).resolveBoxesManager(128, 128);
      this.boxesManager2.applyFloat(0.0F, 0.0F + factor2, 0.0F);
      this.boxesManager2.resolveBoxesManagerByIndex(0, 40).applyFloatByFloat(-9.0F, -2.0F, -6.0F, 18, 12, 11, factor);
      this.boxesManager2.resolveBoxesManagerByIndex(0, 70).applyFloatByFloat(-4.5F, 10.0F, -3.0F, 9, 5, 6, factor + 0.5F);
      this.boxesManager3 = new BoxesManager(this).resolveBoxesManager(128, 128);
      this.boxesManager3.applyFloat(0.0F, -7.0F, 0.0F);
      this.boxesManager3.resolveBoxesManagerByIndex(60, 21).applyFloatByFloat(-13.0F, -2.5F, -3.0F, 4, 30, 6, factor);
      this.boxesManager = new BoxesManager(this).resolveBoxesManager(128, 128);
      this.boxesManager.applyFloat(0.0F, -7.0F, 0.0F);
      this.boxesManager.resolveBoxesManagerByIndex(60, 58).applyFloatByFloat(9.0F, -2.5F, -3.0F, 4, 30, 6, factor);
      this.boxesManager4 = new BoxesManager(this, 0, 22).resolveBoxesManager(128, 128);
      this.boxesManager4.applyFloat(-4.0F, 18.0F + factor2, 0.0F);
      this.boxesManager4.resolveBoxesManagerByIndex(37, 0).applyFloatByFloat(-3.5F, -3.0F, -3.0F, 6, 16, 5, factor);
      this.boxesManager6 = new BoxesManager(this, 0, 22).resolveBoxesManager(128, 128);
      this.boxesManager6.enabled = true;
      this.boxesManager6.resolveBoxesManagerByIndex(60, 0).applyFloat(5.0F, 18.0F + factor2, 0.0F);
      this.boxesManager6.applyFloatByFloat(-3.5F, -3.0F, -3.0F, 6, 16, 5, factor);
   }

   public DiamondScythe(float factor) {
      this(factor, -7.0F);
   }

   public DiamondScythe() {
      this(0.0F);
   }
}
