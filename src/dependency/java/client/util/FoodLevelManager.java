package client.util;

import client.setting.GameSettingsPeacefulType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;

public class FoodLevelManager {
   public int count = 20;
   public float factor = 5.0F;
   public float factor2;
   public int count2;
   public int count3 = 20;

   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      if (tagCompound.matchesText("foodLevel", 99)) {
         this.count = tagCompound.computeCount("foodLevel");
         this.count2 = tagCompound.computeCount("foodTickTimer");
         this.factor = tagCompound.computeFactor("foodSaturationLevel");
         this.factor2 = tagCompound.computeFactor("foodExhaustionLevel");
      }
   }

   public void setCount(int count4) {
      this.count = count4;
   }

   public void min(float factor3) {
      this.factor2 = Math.min(this.factor2 + factor3, 40.0F);
   }

   public boolean isActive() {
      return this.count < 20;
   }

   public int getIndex() {
      return this.count;
   }

   public void setFactor(float factor3) {
      this.factor = factor3;
   }

   public void applyEntityPlayer(EntityPlayer player) {
      GameSettingsPeacefulType gameSettingsPeacefulType = player.worldObj.getGameSettingsPeacefulType();
      this.count3 = this.count;
      if (this.factor2 > 4.0F) {
         this.factor2 -= 4.0F;
         if (this.factor > 0.0F) {
            this.factor = Math.max(this.factor - 1.0F, 0.0F);
         } else if (gameSettingsPeacefulType != GameSettingsPeacefulType.PEACEFUL) {
            this.count = Math.max(this.count - 1, 0);
         }
      }

      if (player.worldObj.getDoFireTickManager().matchesText("naturalRegeneration") && this.count >= 18 && player.isPlayerSleeping()) {
         this.count2++;
         if (this.count2 >= 80) {
            player.applyFloatByFloat(1.0F);
            this.min(3.0F);
            this.count2 = 0;
         }
      } else if (this.count <= 0) {
         this.count2++;
         if (this.count2 >= 80) {
            if (player.getFloat2() > 10.0F || gameSettingsPeacefulType == GameSettingsPeacefulType.HARD || player.getFloat2() > 1.0F && gameSettingsPeacefulType == GameSettingsPeacefulType.NORMAL) {
               player.matchesDamageSource(DamageSource.damageSource13, 1.0F);
            }

            this.count2 = 0;
         }
      } else {
         this.count2 = 0;
      }
   }

   public int getCount3() {
      return this.count3;
   }

   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("foodLevel", this.count);
      tagCompound.put("foodTickTimer", this.count2);
      tagCompound.put("foodSaturationLevel", this.factor);
      tagCompound.put("foodExhaustionLevel", this.factor2);
   }

   public void applyIndex(int count4, float factor3) {
      this.count = Math.min(count4 + this.count, 20);
      this.factor = Math.min(this.factor + (float)count4 * factor3 * 2.0F, (float)this.count);
   }

   public void applyItemFood(ItemFood itemFood, ItemStack stack) {
      this.applyIndex(itemFood.computeCount(stack), itemFood.computeFactor(stack));
   }

   public float getFactor() {
      return this.factor;
   }
}
