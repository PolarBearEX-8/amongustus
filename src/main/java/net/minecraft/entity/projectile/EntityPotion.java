package net.minecraft.entity.projectile;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPotion extends EntityThrowable {
   private ItemStack potionDamage;

   public EntityPotion(World world) {
      super(world);
   }

   public EntityPotion(World world, double d, double d2, double d3, int n) {
      this(world, d, d2, d3, new ItemStack(Items.potionitem, 1, n));
   }

   @Override
   public void onImpact(MovingObjectPosition hit) {
      if (!this.entityWorld.isRemote) {
         List effects = Items.potionitem.getEffects(this.potionDamage);
         if (effects != null && !effects.isEmpty()) {
            AxisAlignedBB box = this.getEntityBoundingBox().expand(4.0, 2.0, 4.0);
            List entitiesWithinAABB = this.entityWorld.getEntitiesWithinAABB(EntityLivingBase.class, box);
            if (!entitiesWithinAABB.isEmpty()) {
               for (EntityLivingBase entity : (Iterable<EntityLivingBase>)(entitiesWithinAABB)) {
                  double distanceSqToEntity = this.getDistanceSqToEntity(entity);
                  if (distanceSqToEntity < 16.0) {
                     double d = 1.0 - Math.sqrt(distanceSqToEntity) / 4.0;
                     if (entity == hit.entityHit) {
                        d = 1.0;
                     }

                     for (PotionEffect potionEffect : (Iterable<PotionEffect>)(effects)) {
                        int potionID = potionEffect.getPotionID();
                        if (Potion.potionTypes[potionID].isInstant()) {
                           Potion.potionTypes[potionID].affectEntity(this, this.getThrower(), entity, potionEffect.getAmplifier(), d);
                        } else {
                           int n = (int)(d * (double)potionEffect.getDuration() + 0.5);
                           if (n > 20) {
                              entity.addPotionEffect(new PotionEffect(potionID, n, potionEffect.getAmplifier()));
                           }
                        }
                     }
                  }
               }
            }
         }

         this.entityWorld.playAuxSFX(2002, new BlockPos(this), this.getPotionDamage());
         this.setDead();
      }
   }

   public EntityPotion(World world, double d, double d2, double d3, ItemStack stack) {
      super(world, d, d2, d3);
      this.potionDamage = stack;
   }

   @Override
   public float getInaccuracy() {
      return -20.0F;
   }

   public EntityPotion(World world, EntityLivingBase entity, int n) {
      this(world, entity, new ItemStack(Items.potionitem, 1, n));
   }

   @Override
   public float getVelocity() {
      return 0.5F;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      if (tag.hasKey("Potion", 10)) {
         this.potionDamage = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Potion"));
      } else {
         this.setPotionDamage(tag.getInteger("potionValue"));
      }

      if (this.potionDamage == null) {
         this.setDead();
      }
   }

   public int getPotionDamage() {
      if (this.potionDamage == null) {
         this.potionDamage = new ItemStack(Items.potionitem, 1, 0);
      }

      return this.potionDamage.getMetadata();
   }

   public EntityPotion(World world, EntityLivingBase entity, ItemStack stack) {
      super(world, entity);
      this.potionDamage = stack;
   }

   public void setPotionDamage(int n) {
      if (this.potionDamage == null) {
         this.potionDamage = new ItemStack(Items.potionitem, 1, 0);
      }

      this.potionDamage.setItemDamage(n);
   }

   @Override
   public float getGravityVelocity() {
      return 0.05F;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      if (this.potionDamage != null) {
         tag.setTag("Potion", this.potionDamage.writeToNBT(new NBTTagCompound()));
      }
   }
}
