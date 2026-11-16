package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGuardian;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class DiamondCrystal extends PassiveFloatEntry {
   public BoxesManager boxesManager2;
   public BoxesManager boxesManager;
   public BoxesManager[] boxesManagerArray;
   public BoxesManager[] boxesManagerArray2;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager2.applyFloat3(posZ);
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      EntityGuardian entityGuardian = (EntityGuardian)entity;
      float factor = posX3 - (float)entityGuardian.ticksExisted;
      this.boxesManager2.factor9 = posX4 / (180.0F / (float)Math.PI);
      this.boxesManager2.factor = posY / (180.0F / (float)Math.PI);
      float[] factorArray = new float[]{1.75F, 0.25F, 0.0F, 0.0F, 0.5F, 0.5F, 0.5F, 0.5F, 1.25F, 0.75F, 0.0F, 0.0F};
      float[] factorArray2 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.25F, 1.75F, 1.25F, 0.75F, 0.0F, 0.0F, 0.0F, 0.0F};
      float[] factorArray3 = new float[]{0.0F, 0.0F, 0.25F, 1.75F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.75F, 1.25F};
      float[] factorArray4 = new float[]{0.0F, 0.0F, 8.0F, -8.0F, -8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F, 8.0F, -8.0F};
      float[] factorArray5 = new float[]{-8.0F, -8.0F, -8.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F};
      float[] factorArray6 = new float[]{8.0F, -8.0F, 0.0F, 0.0F, -8.0F, -8.0F, 8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F};
      float factor2 = (1.0F - entityGuardian.computeFactor3(factor)) * 0.55F;

      for (int index = 0; index < 12; index++) {
         this.boxesManagerArray[index].factor = (float) Math.PI * factorArray[index];
         this.boxesManagerArray[index].factor9 = (float) Math.PI * factorArray2[index];
         this.boxesManagerArray[index].factor8 = (float) Math.PI * factorArray3[index];
         this.boxesManagerArray[index].factor5 = factorArray4[index] * (1.0F + MathHelper.cos(posX3 * 1.5F + (float)index) * 0.01F - factor2);
         this.boxesManagerArray[index].factor2 = 16.0F + factorArray5[index] * (1.0F + MathHelper.cos(posX3 * 1.5F + (float)index) * 0.01F - factor2);
         this.boxesManagerArray[index].factor10 = factorArray6[index] * (1.0F + MathHelper.cos(posX3 * 1.5F + (float)index) * 0.01F - factor2);
      }

      this.boxesManager.factor10 = -8.25F;
      Object renderViewEntity = Minecraft.getMinecraft().getRenderViewEntity();
      if (entityGuardian.isActive33()) {
         renderViewEntity = entityGuardian.getEntityLivingBase7();
      }

      if (renderViewEntity != null) {
         Vec3 vec3 = ((Entity)renderViewEntity).getVec35(0.0F);
         Vec3 vec32 = entity.getVec35(0.0F);
         if (vec3.yCoord - vec32.yCoord > 0.0) {
            this.boxesManager.factor2 = 0.0F;
         } else {
            this.boxesManager.factor2 = 1.0F;
         }

         Vec3 vec33 = entity.getVec36(0.0F);
         Vec3 vec = new Vec3(vec33.xCoord, 0.0, vec33.zCoord);
         Vec3 vec2 = new Vec3(vec32.xCoord - vec3.xCoord, 0.0, vec32.zCoord - vec3.zCoord).normalize().resolveVec3((float) (Math.PI / 2));
         double offset = vec.distanceTo(vec2);
         this.boxesManager.factor5 = MathHelper.sin((float)Math.abs(offset)) * 2.0F * (float)Math.signum(offset);
      }

      this.boxesManager.enabled3 = true;
      float factor3 = entityGuardian.computeFactor5(factor);
      this.boxesManagerArray2[0].factor9 = MathHelper.abs(factor3) * (float) Math.PI * 0.05F;
      this.boxesManagerArray2[1].factor9 = MathHelper.abs(factor3) * (float) Math.PI * 0.1F;
      this.boxesManagerArray2[1].factor5 = -1.5F;
      this.boxesManagerArray2[1].factor2 = 0.5F;
      this.boxesManagerArray2[1].factor10 = 14.0F;
      this.boxesManagerArray2[2].factor9 = MathHelper.abs(factor3) * (float) Math.PI * 0.15F;
      this.boxesManagerArray2[2].factor5 = 0.5F;
      this.boxesManagerArray2[2].factor2 = 0.5F;
      this.boxesManagerArray2[2].factor10 = 6.0F;
   }

   public int getCount() {
      return 54;
   }

   public DiamondCrystal() {
      this.count2 = 64;
      this.count = 64;
      this.boxesManagerArray = new BoxesManager[12];
      this.boxesManager2 = new BoxesManager(this);
      this.boxesManager2.resolveBoxesManagerByIndex(0, 0).resolveBoxesManager(-6.0F, 10.0F, -8.0F, 12, 12, 16);
      this.boxesManager2.resolveBoxesManagerByIndex(0, 28).resolveBoxesManager(-8.0F, 10.0F, -6.0F, 2, 12, 12);
      this.boxesManager2.resolveBoxesManagerByIndex(0, 28).resolveBoxesManager(6.0F, 10.0F, -6.0F, 2, 12, 12, true);
      this.boxesManager2.resolveBoxesManagerByIndex(16, 40).resolveBoxesManager(-6.0F, 8.0F, -6.0F, 12, 2, 12);
      this.boxesManager2.resolveBoxesManagerByIndex(16, 40).resolveBoxesManager(-6.0F, 22.0F, -6.0F, 12, 2, 12);

      for (int index = 0; index < this.boxesManagerArray.length; index++) {
         this.boxesManagerArray[index] = new BoxesManager(this, 0, 0);
         this.boxesManagerArray[index].resolveBoxesManager(-1.0F, -4.5F, -1.0F, 2, 9, 2);
         this.boxesManager2.applyBoxesManager(this.boxesManagerArray[index]);
      }

      this.boxesManager = new BoxesManager(this, 8, 0);
      this.boxesManager.resolveBoxesManager(-1.0F, 15.0F, 0.0F, 2, 2, 1);
      this.boxesManager2.applyBoxesManager(this.boxesManager);
      this.boxesManagerArray2 = new BoxesManager[3];
      this.boxesManagerArray2[0] = new BoxesManager(this, 40, 0);
      this.boxesManagerArray2[0].resolveBoxesManager(-2.0F, 14.0F, 7.0F, 4, 4, 8);
      this.boxesManagerArray2[1] = new BoxesManager(this, 0, 54);
      this.boxesManagerArray2[1].resolveBoxesManager(0.0F, 14.0F, 0.0F, 3, 3, 7);
      this.boxesManagerArray2[2] = new BoxesManager(this);
      this.boxesManagerArray2[2].resolveBoxesManagerByIndex(41, 32).resolveBoxesManager(0.0F, 14.0F, 0.0F, 2, 2, 6);
      this.boxesManagerArray2[2].resolveBoxesManagerByIndex(25, 19).resolveBoxesManager(1.0F, 10.5F, 3.0F, 1, 9, 9);
      this.boxesManager2.applyBoxesManager(this.boxesManagerArray2[0]);
      this.boxesManagerArray2[0].applyBoxesManager(this.boxesManagerArray2[1]);
      this.boxesManagerArray2[1].applyBoxesManager(this.boxesManagerArray2[2]);
   }
}
