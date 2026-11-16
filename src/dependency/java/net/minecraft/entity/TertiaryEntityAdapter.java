package net.minecraft.entity;

import client.render.WorldRenderer;
import client.util.ServiceStringEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TertiaryEntityAdapter extends Entity {
   public int count12;
   public int count11;
   public float factor7;
   public float factor10;
   public int count13;
   public int count14;
   public float factor12;
   public float factor6;
   public float factor8;
   public float factor11;
   public float factor5;
   public float factor9 = 1.0F;
   public ServiceStringEntry serviceStringEntry;
   public static double value8;
   public static double value6;
   public static double value7;

   public float getFactor8() {
      return this.factor8;
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   public void applyFloatByFloat(float posX, float posY, float posZ) {
      this.factor8 = posX;
      this.factor11 = posY;
      this.factor5 = posZ;
   }

   public int getCount4() {
      return 0;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
   }

   public float getFactor9() {
      return this.factor9;
   }

   public TertiaryEntityAdapter resolveTertiaryEntityAdapter(float factor) {
      this.motionX *= (double)factor;
      this.motionY = (this.motionY - 0.1F) * (double)factor + 0.1F;
      this.motionZ *= (double)factor;
      return this;
   }

   public TertiaryEntityAdapter resolveTertiaryEntityAdapterByFloat(float factor) {
      this.applyFloat(0.2F * factor, 0.2F * factor);
      this.factor12 *= factor;
      return this;
   }

   public void applyIndex5(int count) {
      if (this.getCount4() != 0) {
         throw new RuntimeException("Invalid call to Particle.setMiscTex");
      } else {
         this.count12 = count % 16;
         this.count11 = count / 16;
      }
   }

   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      float factor = (float)this.count12 / 16.0F;
      float factor2 = factor + 0.0624375F;
      float factor3 = (float)this.count11 / 16.0F;
      float factor4 = factor3 + 0.0624375F;
      float factor13 = 0.1F * this.factor12;
      if (this.serviceStringEntry != null) {
         factor = this.serviceStringEntry.getFactor5();
         factor2 = this.serviceStringEntry.getFactor2();
         factor3 = this.serviceStringEntry.getFactor();
         factor4 = this.serviceStringEntry.getFactor6();
      }

      float factor14 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)posX - value8);
      float factor15 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)posX - value6);
      float factor16 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)posX - value7);
      int count = this.computeCount(posX);
      int count2 = count >> 16 & 65535;
      int count3 = count & 65535;
      worldRenderer.getWorldRenderer((double)(factor14 - posX2 * factor13 - posY * factor13), (double)(factor15 - posX3 * factor13), (double)(factor16 - posX4 * factor13 - posZ * factor13))
         .getWorldRenderer6((double)factor2, (double)factor4)
         .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, this.factor9)
         .resolveWorldRenderer(count2, count3)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor14 - posX2 * factor13 + posY * factor13), (double)(factor15 + posX3 * factor13), (double)(factor16 - posX4 * factor13 + posZ * factor13))
         .getWorldRenderer6((double)factor2, (double)factor3)
         .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, this.factor9)
         .resolveWorldRenderer(count2, count3)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor14 + posX2 * factor13 + posY * factor13), (double)(factor15 + posX3 * factor13), (double)(factor16 + posX4 * factor13 + posZ * factor13))
         .getWorldRenderer6((double)factor, (double)factor3)
         .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, this.factor9)
         .resolveWorldRenderer(count2, count3)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor14 + posX2 * factor13 - posY * factor13), (double)(factor15 - posX3 * factor13), (double)(factor16 + posX4 * factor13 - posZ * factor13))
         .getWorldRenderer6((double)factor, (double)factor4)
         .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, this.factor9)
         .resolveWorldRenderer(count2, count3)
         .updateState();
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
   }

   public void applyServiceStringEntry(ServiceStringEntry serviceStringEntry2) {
      if (this.getCount4() == 1) {
         this.serviceStringEntry = serviceStringEntry2;
      } else {
         throw new RuntimeException("Invalid call to Particle.setTex, use coordinate methods");
      }
   }

   public float getFactor5() {
      return this.factor5;
   }

   public void updateState2() {
      this.count12++;
   }

   public float getFactor11() {
      return this.factor11;
   }

   public void applyFloat(float factor) {
      if (this.factor9 == 1.0F && factor < 1.0F) {
         Minecraft.getMinecraft().effectRenderer.applyTertiaryEntityAdapter3(this);
      } else if (this.factor9 < 1.0F && factor == 1.0F) {
         Minecraft.getMinecraft().effectRenderer.applyTertiaryEntityAdapter(this);
      }

      this.factor9 = factor;
   }

   @Override
   public boolean isActive3() {
      return false;
   }

   @Override
   public void updateState() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.count13++ >= this.count14) {
         this.updateState9();
      }

      this.motionY = this.motionY - 0.04 * (double)this.factor6;
      this.applyDouble(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.98F;
      this.motionY *= 0.98F;
      this.motionZ *= 0.98F;
      if (this.onGround) {
         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }
   }

   @Override
   public void updateState7() {
   }

   public TertiaryEntityAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      this(world, posX, posX2, posX3);
      this.motionX = posX4 + (Math.random() * 2.0 - 1.0) * 0.4F;
      this.motionY = posY + (Math.random() * 2.0 - 1.0) * 0.4F;
      this.motionZ = posZ + (Math.random() * 2.0 - 1.0) * 0.4F;
      float factor = (float)(Math.random() + Math.random() + 1.0) * 0.15F;
      float factor2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
      this.motionX = this.motionX / (double)factor2 * (double)factor * 0.4F;
      this.motionY = this.motionY / (double)factor2 * (double)factor * 0.4F + 0.1F;
      this.motionZ = this.motionZ / (double)factor2 * (double)factor * 0.4F;
   }

   public TertiaryEntityAdapter(World world, double posX, double posY, double posZ) {
      super(world);
      this.applyFloat(0.2F, 0.2F);
      this.processDouble2(posX, posY, posZ);
      this.lastTickPosX = this.prevPosX = posX;
      this.lastTickPosY = this.prevPosY = posY;
      this.lastTickPosZ = this.prevPosZ = posZ;
      this.factor8 = this.factor11 = this.factor5 = 1.0F;
      this.factor7 = this.random.nextFloat() * 3.0F;
      this.factor10 = this.random.nextFloat() * 3.0F;
      this.factor12 = (this.random.nextFloat() * 0.5F + 0.5F) * 2.0F;
      this.count14 = (int)(4.0F / (this.random.nextFloat() * 0.9F + 0.1F));
      this.count13 = 0;
   }

   @Override
   public String toString() {
      return this.getClass().getSimpleName()
         + ", Pos ("
         + this.posX
         + ","
         + this.posY
         + ","
         + this.posZ
         + "), RGBA ("
         + this.factor8
         + ","
         + this.factor11
         + ","
         + this.factor5
         + ","
         + this.factor9
         + "), Age "
         + this.count13;
   }
}
