package client.util;

import client.event.AuxiliaryListener;
import client.event.PostMotionListener;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.StringUtils;

public abstract class PrimaryClientManagerAdapter extends RotationPool {
   public final AuxiliaryUUIDEntry auxiliaryUUIDEntry;
   public boolean enabled;
   public boolean enabled2;
   public int count2;
   public int count3;
   public int count4;

   private boolean matchesEntityLivingBase(EntityLivingBase livingEntity) {
      this.count3 = 10 + this.auxiliaryUUIDEntry.getRandom().nextInt(5);
      InterpolationConverter interpolationConverter = this.auxiliaryUUIDEntry.getPathfindManager().resolveInterpolationConverter(livingEntity);
      if (interpolationConverter == null) {
         return false;
      } else {
         BaseFloatEntry baseFloatEntry = interpolationConverter.getBaseFloatEntry();
         if (baseFloatEntry == null) {
            return false;
         } else {
            int count = baseFloatEntry.count - MathHelper.ceiling_double_int(livingEntity.posX);
            int count5 = baseFloatEntry.count3 - MathHelper.ceiling_double_int(livingEntity.posZ);
            return (double)(count * count + count5 * count5) <= 2.25;
         }
      }
   }

   public PrimaryClientManagerAdapter(AuxiliaryUUIDEntry auxiliaryUUIDEntry2, boolean flag, boolean flag2) {
      this.auxiliaryUUIDEntry = auxiliaryUUIDEntry2;
      this.enabled = flag;
      this.enabled2 = flag2;
   }

   @Override
   public void updateState() {
      this.auxiliaryUUIDEntry.setEntityLivingBase((EntityLivingBase)null);
   }

   @Override
   public void updateState3() {
      this.count2 = 0;
      this.count3 = 0;
      this.count4 = 0;
   }

   public boolean matchesEntityLivingBase(EntityLivingBase livingEntity, boolean flag) {
      if (!matchesEntityMob(this.auxiliaryUUIDEntry, livingEntity, flag, this.enabled)) {
         return false;
      } else if (!this.auxiliaryUUIDEntry.matchesBlockPos(new BlockPos(livingEntity))) {
         return false;
      } else {
         if (this.enabled2) {
            if (--this.count3 <= 0) {
               this.count2 = 0;
            }

            if (this.count2 == 0) {
               this.count2 = this.matchesEntityLivingBase(livingEntity) ? 1 : 2;
            }

            if (this.count2 == 2) {
               return false;
            }
         }

         return true;
      }
   }

   public static boolean matchesEntityMob(EntityMob entityMob, EntityLivingBase livingEntity, boolean flag, boolean flag2) {
      if (livingEntity == null) {
         return false;
      } else if (livingEntity == entityMob) {
         return false;
      } else if (!livingEntity.canExecute()) {
         return false;
      } else if (!entityMob.matchesClass(livingEntity.getClass())) {
         return false;
      } else {
         SecondaryClientManager secondaryClientManager = entityMob.getSecondaryClientManager();
         SecondaryClientManager secondaryClientManager2 = livingEntity.getSecondaryClientManager();
         if (secondaryClientManager != null && secondaryClientManager2 == secondaryClientManager) {
            return false;
         } else {
            if (entityMob instanceof PostMotionListener && StringUtils.isNotEmpty(((PostMotionListener)entityMob).getTextAsText())) {
               if (livingEntity instanceof PostMotionListener && ((PostMotionListener)entityMob).getTextAsText().equals(((PostMotionListener)livingEntity).getTextAsText())) {
                  return false;
               }

               if (livingEntity == ((PostMotionListener)entityMob).getEntityAsEntity()) {
                  return false;
               }
            } else if (livingEntity instanceof EntityPlayer && !flag && ((EntityPlayer)livingEntity).invulnerableManager.enabled) {
               return false;
            }

            return !flag2 || entityMob.getCanSeeManager().matchesEntityByEntity(livingEntity);
         }
      }
   }

   public PrimaryClientManagerAdapter(AuxiliaryUUIDEntry auxiliaryUUIDEntry2, boolean flag) {
      this(auxiliaryUUIDEntry2, flag, false);
   }

   @Override
   public boolean isActiveAsFlag() {
      EntityLivingBase entityLivingBase = this.auxiliaryUUIDEntry.getEntityLivingBase4();
      if (entityLivingBase == null) {
         return false;
      } else if (!entityLivingBase.canExecute()) {
         return false;
      } else {
         SecondaryClientManager secondaryClientManager = this.auxiliaryUUIDEntry.getSecondaryClientManager();
         SecondaryClientManager secondaryClientManager2 = entityLivingBase.getSecondaryClientManager();
         if (secondaryClientManager != null && secondaryClientManager2 == secondaryClientManager) {
            return false;
         } else {
            double value = this.getValue();
            if (this.auxiliaryUUIDEntry.getCoordinate(entityLivingBase) > value * value) {
               return false;
            } else {
               if (this.enabled) {
                  if (this.auxiliaryUUIDEntry.getCanSeeManager().matchesEntityByEntity(entityLivingBase)) {
                     this.count4 = 0;
                  } else if (++this.count4 > 60) {
                     return false;
                  }
               }

               return !(entityLivingBase instanceof EntityPlayer) || !((EntityPlayer)entityLivingBase).invulnerableManager.enabled;
            }
         }
      }
   }

   public double getValue() {
      AuxiliaryListener auxiliaryListener = this.auxiliaryUUIDEntry.getAuxiliaryListener(SharedMonsterAttributes.extendedListener4);
      return auxiliaryListener == null ? 16.0 : auxiliaryListener.getDouble();
   }
}
