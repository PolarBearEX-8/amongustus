package net.minecraft.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PotionEffect {
   private static final Logger LOGGER = LogManager.getLogger();
   private int potionID;
   private int duration;
   private int amplifier;
   private boolean isSplashPotion;
   private boolean isAmbient;
   private boolean isPotionDurationMax;
   private boolean showParticles;

   public PotionEffect(int n, int n2, int n3, boolean flag, boolean flag2) {
      this.potionID = n;
      this.duration = n2;
      this.amplifier = n3;
      this.isAmbient = flag;
      this.showParticles = flag2;
   }

   public int getPotionID() {
      return this.potionID;
   }

   public PotionEffect(int n, int n2) {
      this(n, n2, 0);
   }

   public NBTTagCompound writeCustomPotionEffectToNBT(NBTTagCompound tag) {
      tag.setByte("Id", (byte)this.getPotionID());
      tag.setByte("Amplifier", (byte)this.getAmplifier());
      tag.setInteger("Duration", this.getDuration());
      tag.setBoolean("Ambient", this.getIsAmbient());
      tag.setBoolean("ShowParticles", this.getIsShowParticles());
      return tag;
   }

   public boolean onUpdate(EntityLivingBase entity) {
      if (this.duration > 0) {
         Potion potion = Potion.potionTypes[this.potionID];
         if (potion != null && potion.isReady(this.duration, this.amplifier)) {
            this.performEffect(entity);
         }

         this.deincrementDuration();
      }

      return this.duration > 0;
   }

   public boolean getIsAmbient() {
      return this.isAmbient;
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof PotionEffect)) {
         return false;
      } else {
         PotionEffect potionEffect = (PotionEffect)obj;
         return this.potionID == potionEffect.potionID
            && this.amplifier == potionEffect.amplifier
            && this.duration == potionEffect.duration
            && this.isSplashPotion == potionEffect.isSplashPotion
            && this.isAmbient == potionEffect.isAmbient;
      }
   }

   public void setPotionDurationMax(boolean flag) {
      this.isPotionDurationMax = flag;
   }

   public int getAmplifier() {
      return this.amplifier;
   }

   @Override
   public int hashCode() {
      return this.potionID;
   }

   public PotionEffect(PotionEffect potionEffect) {
      this.potionID = potionEffect.potionID;
      this.duration = potionEffect.duration;
      this.amplifier = potionEffect.amplifier;
      this.isAmbient = potionEffect.isAmbient;
      this.showParticles = potionEffect.showParticles;
   }

   public boolean getIsShowParticles() {
      return this.showParticles;
   }

   private int deincrementDuration() {
      return --this.duration;
   }

   public int getDuration() {
      return this.duration;
   }

   public String getEffectName() {
      return Potion.potionTypes[this.potionID].getName();
   }

   public boolean getIsPotionDurationMax() {
      return this.isPotionDurationMax;
   }

   public void setSplashPotion(boolean flag) {
      this.isSplashPotion = flag;
   }

   public PotionEffect(int n, int n2, int n3) {
      this(n, n2, n3, false, true);
   }

   public static PotionEffect readCustomPotionEffectFromNBT(NBTTagCompound tag) {
      byte b = tag.getByte("Id");
      if (b >= 0 && b < Potion.potionTypes.length && Potion.potionTypes[b] != null) {
         byte b2 = tag.getByte("Amplifier");
         int integer = tag.getInteger("Duration");
         boolean flag = tag.getBoolean("Ambient");
         boolean flag2 = true;
         if (tag.hasKey("ShowParticles", 1)) {
            flag2 = tag.getBoolean("ShowParticles");
         }

         return new PotionEffect(b, integer, b2, flag, flag2);
      } else {
         return null;
      }
   }

   public void performEffect(EntityLivingBase entity) {
      if (this.duration > 0) {
         Potion.potionTypes[this.potionID].performEffect(entity, this.amplifier);
      }
   }

   public void combine(PotionEffect potionEffect) {
      if (this.potionID != potionEffect.potionID) {
         LOGGER.warn("This method should only be called for matching effects!");
      }

      if (potionEffect.amplifier > this.amplifier) {
         this.amplifier = potionEffect.amplifier;
         this.duration = potionEffect.duration;
      } else if (potionEffect.amplifier == this.amplifier && this.duration < potionEffect.duration) {
         this.duration = potionEffect.duration;
      } else if (!potionEffect.isAmbient && this.isAmbient) {
         this.isAmbient = potionEffect.isAmbient;
      }

      this.showParticles = potionEffect.showParticles;
   }

   @Override
   public String toString() {
      String str = "";
      if (this.getAmplifier() > 0) {
         str = this.getEffectName() + " x " + (this.getAmplifier() + 1) + ", Duration: " + this.getDuration();
      } else {
         str = this.getEffectName() + ", Duration: " + this.getDuration();
      }

      if (this.isSplashPotion) {
         str = str + ", Splash: true";
      }

      if (!this.showParticles) {
         str = str + ", Particles: false";
      }

      return Potion.potionTypes[this.potionID].isUsable() ? "(" + str + ")" : str;
   }
}
