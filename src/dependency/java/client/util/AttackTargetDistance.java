package client.util;

import client.setting.RangeSetting;
import net.minecraft.entity.EntityLivingBase;

public class AttackTargetDistance implements MinecraftAccessor {
   public boolean enabled2;
   public double value3;
   public boolean enabled4;
   public double value2;
   public boolean enabled5;
   public boolean enabled3;
   public boolean enabled = true;
   public double value;

   public void applyEntityLivingBase(EntityLivingBase livingEntity, EntityLivingBase livingEntity2, RangeSetting setting, double offset) {
      if (livingEntity == null && livingEntity2 != null && this.enabled5) {
         this.value = setting.recalculate();
         this.enabled2 = false;
         this.enabled5 = false;
         this.enabled = true;
         this.enabled3 = false;
         this.enabled4 = false;
      } else if (livingEntity != null) {
         if (offset < this.value && this.enabled) {
            this.value3 = setting.recalculate();
            this.enabled = false;
            this.enabled5 = true;
            if (this.value3 >= this.value) {
               this.value = this.value3;
            } else {
               this.enabled4 = true;
            }
         }

         if (this.enabled4 && (offset > this.value2 || offset < this.value3)) {
            this.value = this.value3;
            this.enabled4 = false;
         }

         if (offset > this.value && this.enabled5) {
            this.value3 = setting.recalculate();
            this.enabled5 = false;
            this.enabled = true;
            if (this.value3 <= this.value) {
               this.value = this.value3;
            } else {
               this.enabled3 = true;
            }
         }

         if (this.enabled3 && (offset < this.value2 || offset > this.value3)) {
            this.value = this.value3;
            this.enabled3 = false;
         }

         this.value2 = offset;
         this.enabled2 = offset < this.value;
      }
   }

   public void applyEntityLivingBase2(EntityLivingBase livingEntity, EntityLivingBase livingEntity2, RangeSetting setting, int count) {
      double offset = livingEntity != null ? (double)MinecraftAccessor.minecraft.thePlayer.getDistanceToEntity(livingEntity) : 0.0;
      this.applyEntityLivingBase(livingEntity, livingEntity2, setting, offset);
   }

   public double getCoordinate() {
      return this.value;
   }

   public void applyRangeSetting(RangeSetting setting) {
      this.value = setting.recalculate();
      this.enabled2 = false;
      this.enabled = true;
      this.enabled5 = true;
      this.enabled4 = false;
      this.enabled3 = false;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public AttackTargetDistance() {
      this.enabled5 = true;
      this.enabled4 = false;
      this.enabled3 = false;
      this.value2 = 0.0;
      this.enabled2 = false;
   }
}
