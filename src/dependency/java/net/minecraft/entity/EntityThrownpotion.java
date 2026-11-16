package net.minecraft.entity;

import java.util.List;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityThrownpotion extends EntityThrowable {
   public ItemStack itemStack;

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      if (tagCompound.matchesText("Potion", 10)) {
         this.itemStack = ItemStack.resolveItemStack(tagCompound.getNBTTagCompound("Potion"));
      } else {
         this.applyIndex5(tagCompound.computeCount("potionValue"));
      }

      if (this.itemStack == null) {
         this.updateState9();
      }
   }

   @Override
   public void applyMovingObjectPosition(MovingObjectPosition hitResult) {
      if (!this.worldObj.state) {
         List list = Items.potion.getList(this.itemStack);
         if (list != null && !list.isEmpty()) {
            AxisAlignedBB boundingBox = this.getBoundingBox2().offset(4.0, 2.0, 4.0);
            List list2 = this.worldObj.resolveList(EntityLivingBase.class, boundingBox);
            if (!list2.isEmpty()) {
               for (EntityLivingBase livingEntity : (Iterable<EntityLivingBase>)(Iterable<?>)list2) {
                  double coordinate = this.getCoordinate(livingEntity);
                  if (coordinate < 16.0) {
                     double offset = 1.0 - Math.sqrt(coordinate) / 4.0;
                     if (livingEntity == hitResult.entityHit) {
                        offset = 1.0;
                     }

                     for (PotionEffect potionEffect : (Iterable<PotionEffect>)(Iterable<?>)list) {
                        int indexOffset = potionEffect.getIndexOffset3();
                        if (Potion.potionArray[indexOffset].isActive()) {
                           Potion.potionArray[indexOffset].applyEntity(this, this.getEntityLivingBase(), livingEntity, potionEffect.getIndexOffset2(), offset);
                        } else {
                           int count = (int)(offset * (double)potionEffect.getIndexOffset() + 0.5);
                           if (count > 20) {
                              livingEntity.applyPotionEffectByPotionEffect(new PotionEffect(indexOffset, count, potionEffect.getIndexOffset2()));
                           }
                        }
                     }
                  }
               }
            }
         }

         this.worldObj.applyBlockPosByBlockPos(2002, new BlockPos(this), this.getCount4());
         this.updateState9();
      }
   }

   public void applyIndex5(int count) {
      if (this.itemStack == null) {
         this.itemStack = new ItemStack(Items.potion, 1, 0);
      }

      this.itemStack.applyIndexByIndex(count);
   }

   @Override
   public float getFactor() {
      return -20.0F;
   }

   @Override
   public float getFactorAsFloat() {
      return 0.05F;
   }

   @Override
   public float getFactor2() {
      return 0.5F;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      if (this.itemStack != null) {
         tagCompound.applyText("Potion", this.itemStack.resolveNBTTagCompound(new NBTTagCompound()));
      }
   }

   public int getCount4() {
      if (this.itemStack == null) {
         this.itemStack = new ItemStack(Items.potion, 1, 0);
      }

      return this.itemStack.getIndexOffset();
   }

   public EntityThrownpotion(World world, EntityLivingBase livingEntity, int count) {
      this(world, livingEntity, new ItemStack(Items.potion, 1, count));
   }

   public EntityThrownpotion(World world) {
      super(world);
   }

   public EntityThrownpotion(World world, double posX, double posY, double posZ, ItemStack stack) {
      super(world, posX, posY, posZ);
      this.itemStack = stack;
   }

   public EntityThrownpotion(World world, double posX, double posY, double posZ, int count) {
      this(world, posX, posY, posZ, new ItemStack(Items.potion, 1, count));
   }

   public EntityThrownpotion(World world, EntityLivingBase livingEntity, ItemStack stack) {
      super(world, livingEntity);
      this.itemStack = stack;
   }
}
