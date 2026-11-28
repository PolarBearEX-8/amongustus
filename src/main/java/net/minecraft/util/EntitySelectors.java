package net.minecraft.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import com.google.common.base.Predicate;

public final class EntitySelectors {
   public static final Predicate selectAnything = new EntitySelectors.FilterPredicate();
   public static final Predicate IS_STANDALONE = new EntitySelectors.FilterPredicate2();
   public static final Predicate selectInventories = new EntitySelectors.FilterPredicate3();
   public static final Predicate NOT_SPECTATING = new EntitySelectors.FilterPredicate4();


   public static final class FilterPredicate implements Predicate {
      public boolean apply(Entity entity) {
         return entity.isEntityAlive();
      }

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }
   }

   public static final class FilterPredicate2 implements Predicate {
      public boolean apply(Entity entity) {
         return entity.isEntityAlive() && entity.riddenByEntity == null && entity.ridingEntity == null;
      }

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }
   }

   public static final class FilterPredicate3 implements Predicate {
      public boolean apply(Entity entity) {
         return entity instanceof IInventory && entity.isEntityAlive();
      }

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }
   }

   public static final class FilterPredicate4 implements Predicate {
      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public boolean apply(Entity entity) {
         return !(entity instanceof EntityPlayer) || !((EntityPlayer)entity).isSpectator();
      }
   }

   public static class ArmoredMob implements Predicate {
      private final ItemStack armor;

      public ArmoredMob(ItemStack stack) {
         this.armor = stack;
      }

      public boolean apply(Entity entity) {
         if (!entity.isEntityAlive()) {
            return false;
         } else if (!(entity instanceof EntityLivingBase)) {
            return false;
         } else {
            EntityLivingBase entity2 = (EntityLivingBase)entity;
            return entity2.getEquipmentInSlot(EntityLiving.getArmorPosition(this.armor)) != null
               ? false
               : (entity2 instanceof EntityLiving ? ((EntityLiving)entity2).canPickUpLoot() : (entity2 instanceof EntityArmorStand ? true : entity2 instanceof EntityPlayer));
         }
      }

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }
   }
}
