package net.augustus.modules.combat.killaura;

import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.entity.EntityLivingBase;

public class HysteresisRangeTracker implements MC {
   public boolean pendingIncreaseThreshold;
   public boolean canShrinkThreshold = true;
   private boolean withinRange;
   public boolean canExpandThreshold = true;
   public boolean pendingDecreaseThreshold = false;
   private double pendingThreshold;
   private double currentThreshold;
   private double lastDistance;

   public double getCurrentThreshold() {
      return this.currentThreshold;
   }

   public boolean isWithinRange() {
      return this.withinRange;
   }

   public void updateRangeWithDistance(EntityLivingBase entity, EntityLivingBase entity2, RandomizedRangeSettings randomizedRangeSettings, double d) {
      if (entity == null && entity2 != null && this.canExpandThreshold) {
         this.currentThreshold = randomizedRangeSettings.nextValue();
         this.withinRange = false;
         this.canExpandThreshold = false;
         this.canShrinkThreshold = true;
         this.pendingIncreaseThreshold = false;
         this.pendingDecreaseThreshold = false;
      } else if (entity != null) {
         if (d < this.currentThreshold && this.canShrinkThreshold) {
            this.pendingThreshold = randomizedRangeSettings.nextValue();
            this.canShrinkThreshold = false;
            this.canExpandThreshold = true;
            if (this.pendingThreshold >= this.currentThreshold) {
               this.currentThreshold = this.pendingThreshold;
            } else {
               this.pendingDecreaseThreshold = true;
            }
         }

         if (this.pendingDecreaseThreshold && (d > this.lastDistance || d < this.pendingThreshold)) {
            this.currentThreshold = this.pendingThreshold;
            this.pendingDecreaseThreshold = false;
         }

         if (d > this.currentThreshold && this.canExpandThreshold) {
            this.pendingThreshold = randomizedRangeSettings.nextValue();
            this.canExpandThreshold = false;
            this.canShrinkThreshold = true;
            if (this.pendingThreshold <= this.currentThreshold) {
               this.currentThreshold = this.pendingThreshold;
            } else {
               this.pendingIncreaseThreshold = true;
            }
         }

         if (this.pendingIncreaseThreshold && (d < this.lastDistance || d > this.pendingThreshold)) {
            this.currentThreshold = this.pendingThreshold;
            this.pendingIncreaseThreshold = false;
         }

         this.lastDistance = d;
         this.withinRange = d < this.currentThreshold;
      }
   }

   public HysteresisRangeTracker() {
      this.pendingIncreaseThreshold = false;
      this.lastDistance = 0.0;
      this.withinRange = false;
   }

   public void reset(RandomizedRangeSettings randomizedRangeSettings) {
      this.currentThreshold = randomizedRangeSettings.nextValue();
      this.withinRange = false;
      this.canShrinkThreshold = true;
      this.canExpandThreshold = true;
      this.pendingDecreaseThreshold = false;
      this.pendingIncreaseThreshold = false;
   }

   public void updateRange(EntityLivingBase entity, EntityLivingBase entity2, RandomizedRangeSettings randomizedRangeSettings) {
      this.updateRangeWithDistance(entity, entity2, randomizedRangeSettings, entity != null ? (double)mc.thePlayer.getDistanceToEntityBox(entity) : 0.0);
   }
}
