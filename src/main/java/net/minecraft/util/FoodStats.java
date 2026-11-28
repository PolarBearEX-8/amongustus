package net.minecraft.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.EnumDifficulty;

public class FoodStats {
   private int foodLevel = 20;
   private float foodSaturationLevel = 5.0F;
   private float foodExhaustionLevel;
   private int foodTimer;
   private int prevFoodLevel = 20;

   public void addExhaustion(float f) {
      this.foodExhaustionLevel = Math.min(this.foodExhaustionLevel + f, 40.0F);
   }

   public void setFoodSaturationLevel(float f) {
      this.foodSaturationLevel = f;
   }

   public float getSaturationLevel() {
      return this.foodSaturationLevel;
   }

   public void onUpdate(EntityPlayer player) {
      EnumDifficulty difficulty = player.entityWorld.getDifficulty();
      this.prevFoodLevel = this.foodLevel;
      if (this.foodExhaustionLevel > 4.0F) {
         this.foodExhaustionLevel -= 4.0F;
         if (this.foodSaturationLevel > 0.0F) {
            this.foodSaturationLevel = Math.max(this.foodSaturationLevel - 1.0F, 0.0F);
         } else if (difficulty != EnumDifficulty.PEACEFUL) {
            this.foodLevel = Math.max(this.foodLevel - 1, 0);
         }
      }

      if (player.entityWorld.getGameRules().getBoolean("naturalRegeneration") && this.foodLevel >= 18 && player.shouldHeal()) {
         this.foodTimer++;
         if (this.foodTimer >= 80) {
            player.heal(1.0F);
            this.addExhaustion(3.0F);
            this.foodTimer = 0;
         }
      } else if (this.foodLevel <= 0) {
         this.foodTimer++;
         if (this.foodTimer >= 80) {
            if (player.getCurrentHealth() > 10.0F || difficulty == EnumDifficulty.HARD || player.getCurrentHealth() > 1.0F && difficulty == EnumDifficulty.NORMAL) {
               player.attackEntityFrom(DamageSource.starve, 1.0F);
            }

            this.foodTimer = 0;
         }
      } else {
         this.foodTimer = 0;
      }
   }

   public void addStats(int n, float f) {
      this.foodLevel = Math.min(n + this.foodLevel, 20);
      this.foodSaturationLevel = Math.min(this.foodSaturationLevel + (float)n * f * 2.0F, (float)this.foodLevel);
   }

   public boolean needFood() {
      return this.foodLevel < 20;
   }

   public void addStats(ItemFood itemFood, ItemStack stack) {
      this.addStats(itemFood.getHealAmount(stack), itemFood.getSaturationModifier(stack));
   }

   public int getFoodLevel() {
      return this.foodLevel;
   }

   public int getPrevFoodLevel() {
      return this.prevFoodLevel;
   }

   public void writeNBT(NBTTagCompound tag) {
      tag.setInteger("foodLevel", this.foodLevel);
      tag.setInteger("foodTickTimer", this.foodTimer);
      tag.setFloat("foodSaturationLevel", this.foodSaturationLevel);
      tag.setFloat("foodExhaustionLevel", this.foodExhaustionLevel);
   }

   public void setFoodLevel(int n) {
      this.foodLevel = n;
   }

   public void readNBT(NBTTagCompound tag) {
      if (tag.hasKey("foodLevel", 99)) {
         this.foodLevel = tag.getInteger("foodLevel");
         this.foodTimer = tag.getInteger("foodTickTimer");
         this.foodSaturationLevel = tag.getFloat("foodSaturationLevel");
         this.foodExhaustionLevel = tag.getFloat("foodExhaustionLevel");
      }
   }
}
