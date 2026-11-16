package net.minecraft.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PotionEffect {
   public static final Logger logger = LogManager.getLogger();
   public int indexOffset3;
   public int indexOffset;
   public int indexOffset2;
   public boolean state4;
   public boolean state5;
   public boolean state;
   public boolean state1;

   public void applyEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity) {
      if (this.indexOffset > 0) {
         Potion.potionArray[this.indexOffset3].applyEntityLivingBase(livingEntity, this.indexOffset2);
      }
   }

   public void setState(boolean state2) {
      this.state = state2;
   }

   public boolean isState5() {
      return this.state5;
   }

   public int getIndexOffset() {
      return this.indexOffset;
   }

   public boolean isState() {
      return this.state;
   }

   private int processAction() {
      return --this.indexOffset;
   }

   public void setState4(boolean state2) {
      this.state4 = state2;
   }

   public static PotionEffect resolvePotionEffect(NBTTagCompound tagCompound) {
      byte byteValue = tagCompound.computeByteCode("Id");
      if (byteValue >= 0 && byteValue < Potion.potionArray.length && Potion.potionArray[byteValue] != null) {
         byte byteValue2 = tagCompound.computeByteCode("Amplifier");
         int count = tagCompound.computeCount("Duration");
         boolean flag = tagCompound.matchesText("Ambient");
         boolean flag2 = true;
         if (tagCompound.matchesText("ShowParticles", 1)) {
            flag2 = tagCompound.matchesText("ShowParticles");
         }

         return new PotionEffect(byteValue, count, byteValue2, flag, flag2);
      } else {
         return null;
      }
   }

   public int getIndexOffset3() {
      return this.indexOffset3;
   }

   public void applyPotionEffect(PotionEffect potionEffect) {
      if (this.indexOffset3 != potionEffect.indexOffset3) {
         logger.warn("This method should only be called for matching effects!");
      }

      if (potionEffect.indexOffset2 > this.indexOffset2) {
         this.indexOffset2 = potionEffect.indexOffset2;
         this.indexOffset = potionEffect.indexOffset;
      } else if (potionEffect.indexOffset2 == this.indexOffset2 && this.indexOffset < potionEffect.indexOffset) {
         this.indexOffset = potionEffect.indexOffset;
      } else if (!potionEffect.state5 && this.state5) {
         this.state5 = potionEffect.state5;
      }

      this.state1 = potionEffect.state1;
   }

   public String processAction2() {
      return Potion.potionArray[this.indexOffset3].getText();
   }

   public boolean matchesEntityLivingBase(EntityLivingBase livingEntity) {
      if (this.indexOffset > 0) {
         Potion potion = Potion.potionArray[this.indexOffset3];
         if (potion != null && potion.checkIndex(this.indexOffset, this.indexOffset2)) {
            this.applyEntityLivingBaseByEntityLivingBase(livingEntity);
         }

         this.processAction();
      }

      return this.indexOffset > 0;
   }

   public int getIndexOffset2() {
      return this.indexOffset2;
   }

   public boolean isState1() {
      return this.state1;
   }

   public NBTTagCompound resolveNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("Id", (byte)this.getIndexOffset3());
      tagCompound.put("Amplifier", (byte)this.getIndexOffset2());
      tagCompound.put("Duration", this.getIndexOffset());
      tagCompound.applyText("Ambient", this.isState5());
      tagCompound.applyText("ShowParticles", this.isState1());
      return tagCompound;
   }

   public PotionEffect(int count, int count2) {
      this(count, count2, 0);
   }

   public PotionEffect(int count, int count2, int count3, boolean flag, boolean flag2) {
      this.indexOffset3 = count;
      this.indexOffset = count2;
      this.indexOffset2 = count3;
      this.state5 = flag;
      this.state1 = flag2;
   }

   public PotionEffect(int count, int count2, int count3) {
      this(count, count2, count3, false, true);
   }

   public PotionEffect(PotionEffect potionEffect) {
      this.indexOffset3 = potionEffect.indexOffset3;
      this.indexOffset = potionEffect.indexOffset;
      this.indexOffset2 = potionEffect.indexOffset2;
      this.state5 = potionEffect.state5;
      this.state1 = potionEffect.state1;
   }

   @Override
   public boolean equals(Object other) {
      if (!(other instanceof PotionEffect)) {
         return false;
      } else {
         PotionEffect potionEffect = (PotionEffect)other;
         return this.indexOffset3 == potionEffect.indexOffset3
            && this.indexOffset2 == potionEffect.indexOffset2
            && this.indexOffset == potionEffect.indexOffset
            && this.state4 == potionEffect.state4
            && this.state5 == potionEffect.state5;
      }
   }

   @Override
   public String toString() {
      String text;
      if (this.getIndexOffset2() > 0) {
         text = this.processAction2() + " x " + (this.getIndexOffset2() + 1) + ", Duration: " + this.getIndexOffset();
      } else {
         text = this.processAction2() + ", Duration: " + this.getIndexOffset();
      }

      if (this.state4) {
         text = text + ", Splash: true";
      }

      if (!this.state1) {
         text = text + ", Particles: false";
      }

      return Potion.potionArray[this.indexOffset3].isState() ? "(" + text + ")" : text;
   }

   @Override
   public int hashCode() {
      return this.indexOffset3;
   }
}
