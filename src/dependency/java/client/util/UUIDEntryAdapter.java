package client.util;

import client.setting.AttackEffectsExplosionNormalType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class UUIDEntryAdapter extends AuxiliaryUUIDEntry {
   public int count29;
   public int count30;
   public int count28;
   public float factor42 = -1.0F;
   public float factor41;

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.applyIndex8(tagCompound.computeCount("Age"));
      this.count30 = tagCompound.computeCount("ForcedAge");
   }

   @Override
   public final void applyFloat(float factor, float factor2) {
      boolean flag = this.factor42 > 0.0F;
      this.factor42 = factor;
      this.factor41 = factor2;
      if (!flag) {
         this.applyFloat5(1.0F);
      }
   }

   public abstract UUIDEntryAdapter resolveUUIDEntryAdapter(UUIDEntryAdapter uUIDEntryAdapter);

   public final void applyFloat5(float factor) {
      super.applyFloat(this.factor42 * factor, this.factor41 * factor);
   }

   @Override
   public boolean matchesEntityPlayer6(EntityPlayer player) {
      ItemStack slot = player.inventoryPlayer.getCurrentItem();
      if (slot != null && slot.getItem() == Items.spawnEgg) {
         if (!this.worldObj.state) {
            Class clazz = SkippingEntityWithIdManager.resolveClass(slot.getIndexOffset());
            if (clazz != null && this.getClass() == clazz) {
               UUIDEntryAdapter uUIDEntryAdapter = this.resolveUUIDEntryAdapter(this);
               if (uUIDEntryAdapter != null) {
                  uUIDEntryAdapter.applyIndex8(-24000);
                  uUIDEntryAdapter.applyDoubleByDouble(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
                  this.worldObj.matchesEntity(uUIDEntryAdapter);
                  if (slot.isActive()) {
                     uUIDEntryAdapter.setLabelText(slot.getString());
                  }

                  if (!player.invulnerableManager.enabled2) {
                     slot.animationsToGo--;
                     if (slot.animationsToGo <= 0) {
                        player.inventoryPlayer.processAction(player.inventoryPlayer.currentItem, (ItemStack)null);
                     }
                  }
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public void applyIndexByIndex(int count, boolean flag) {
      int count2 = this.getCount9();
      int count3 = count2 + count * 20;
      if (count3 > 0) {
         count3 = 0;
         if (count2 < 0) {
            this.updateState48();
         }
      }

      int count4 = count3 - count2;
      this.applyIndex8(count3);
      if (flag) {
         this.count30 += count4;
         if (this.count28 == 0) {
            this.count28 = 40;
         }
      }

      if (this.getCount9() == 0) {
         this.applyIndex8(this.count30);
      }
   }

   @Override
   public boolean isActive16() {
      return this.getCount9() < 0;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("Age", this.getCount9());
      tagCompound.put("ForcedAge", this.count30);
   }

   public void setEnabled6(boolean enabled) {
      this.applyFloat5(enabled ? 0.5F : 1.0F);
   }

   public void updateState48() {
   }

   public void applyIndex9(int count) {
      this.applyIndexByIndex(count, false);
   }

   public void applyIndex8(int count) {
      this.dataWatcher.applyTargetByTarget(12, (byte)MathHelper.getInt12(count, -1, 1));
      this.count29 = count;
      this.setEnabled6(this.isActive16());
   }

   public int getCount9() {
      return this.worldObj.state ? this.dataWatcher.computeByteCode(12) : this.count29;
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(12, (byte)0);
   }

   @Override
   public void updateState33() {
      super.updateState33();
      if (this.worldObj.state) {
         if (this.count28 > 0) {
            if (this.count28 % 4 == 0) {
               this.worldObj
                  .applyAttackEffectsExplosionNorm(
                     AttackEffectsExplosionNormalType.VILLAGER_HAPPY,
                     this.posX + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width,
                     this.posY + 0.5 + (double)(this.random.nextFloat() * this.height),
                     this.posZ + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width,
                     0.0,
                     0.0,
                     0.0,
                     new int[0]
                  );
            }

            this.count28--;
         }

         this.setEnabled6(this.isActive16());
      } else {
         int count = this.getCount9();
         if (count < 0) {
            this.applyIndex8(++count);
            if (count == 0) {
               this.updateState48();
            }
         } else if (count > 0) {
            this.applyIndex8(--count);
         }
      }
   }

   public UUIDEntryAdapter(World world) {
      super(world);
   }
}
