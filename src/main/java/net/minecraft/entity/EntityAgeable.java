package net.minecraft.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityAgeable extends EntityCreature {
   public int growingAge;
   public int forcedAge;
   public int forcedAgeTimer;
   private float ageWidth = -1.0F;
   private float ageHeight;

   public void ageUp(int n, boolean flag) {
      int growingAge2 = this.getGrowingAge();
      growingAge2 += n * 20;
      if (growingAge2 > 0) {
         growingAge2 = 0;
         if (growingAge2 < 0) {
            this.onGrowingAdult();
         }
      }

      int n2 = growingAge2 - growingAge2;
      this.setGrowingAge(growingAge2);
      if (flag) {
         this.forcedAge += n2;
         if (this.forcedAgeTimer == 0) {
            this.forcedAgeTimer = 40;
         }
      }

      if (this.getGrowingAge() == 0) {
         this.setGrowingAge(this.forcedAge);
      }
   }

   public void setGrowingAge(int n) {
      this.dataWatcher.updateObject(12, (byte)MathHelper.clamp_int(n, -1, 1));
      this.growingAge = n;
      this.setScaleForAge(this.isChild());
   }

   public abstract EntityAgeable createAgeableChild(EntityAgeable entityAgeable);

   public void addGrowth(int n) {
      this.ageUp(n, false);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.setGrowingAge(tag.getInteger("Age"));
      this.forcedAge = tag.getInteger("ForcedAge");
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.entityWorld.isRemote) {
         if (this.forcedAgeTimer > 0) {
            if (this.forcedAgeTimer % 4 == 0) {
               this.entityWorld
                  .spawnParticle(
                     EnumParticleTypes.VILLAGER_HAPPY,
                     this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width,
                     this.posY + 0.5 + (double)(this.rand.nextFloat() * this.height),
                     this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width,
                     0.0,
                     0.0,
                     0.0,
                     new int[0]
                  );
            }

            this.forcedAgeTimer--;
         }

         this.setScaleForAge(this.isChild());
      } else {
         int growingAge2 = this.getGrowingAge();
         if (growingAge2 < 0) {
            this.setGrowingAge(++growingAge2);
            if (growingAge2 == 0) {
               this.onGrowingAdult();
            }
         } else if (growingAge2 > 0) {
            this.setGrowingAge(--growingAge2);
         }
      }
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(12, (byte)0);
   }

   public EntityAgeable(World world) {
      super(world);
   }

   @Override
   public final void setSize(float f, float f2) {
      boolean flag = this.ageWidth > 0.0F;
      this.ageWidth = f;
      this.ageHeight = f2;
      if (!flag) {
         this.setScale(1.0F);
      }
   }

   public final void setScale(float f) {
      super.setSize(this.ageWidth * f, this.ageHeight * f);
   }

   @Override
   public boolean interact(EntityPlayer player) {
      ItemStack currentItem = player.inventory.getCurrentItem();
      if (currentItem != null && currentItem.getItem() == Items.spawn_egg) {
         if (!this.entityWorld.isRemote) {
            Class classFromID = EntityList.getClassFromID(currentItem.getMetadata());
            if (classFromID != null && this.getClass() == classFromID) {
               EntityAgeable entityAgeable = this.createAgeableChild(this);
               if (entityAgeable != null) {
                  entityAgeable.setGrowingAge(-24000);
                  entityAgeable.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
                  this.entityWorld.spawnEntityInWorld(entityAgeable);
                  if (currentItem.hasDisplayName()) {
                     entityAgeable.setCustomNameTag(currentItem.getDisplayName());
                  }

                  if (!player.capabilities.isCreativeMode) {
                     currentItem.stackSize--;
                     if (currentItem.stackSize <= 0) {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
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

   public int getGrowingAge() {
      return this.entityWorld.isRemote ? this.dataWatcher.getWatchableObjectByte(12) : this.growingAge;
   }

   @Override
   public boolean isChild() {
      return this.getGrowingAge() < 0;
   }

   public void onGrowingAdult() {
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setInteger("Age", this.getGrowingAge());
      tag.setInteger("ForcedAge", this.forcedAge);
   }

   public void setScaleForAge(boolean flag) {
      this.setScale(flag ? 0.5F : 1.0F);
   }
}
