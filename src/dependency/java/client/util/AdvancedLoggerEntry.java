package client.util;

import client.event.AuxiliaryListener;
import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdvancedLoggerEntry extends RotationPool {
   public static final Logger logger = LogManager.getLogger();
   public EntityMob entityMob;
   public final Predicate predicate;
   public final AuxiliaryComparatorAdapter auxiliaryComparatorAdapter;
   public EntityLivingBase entityLivingBase;
   public Class memberRef;

   @Override
   public void updateState3() {
      this.entityMob.setEntityLivingBase(this.entityLivingBase);
      super.updateState3();
   }

   @Override
   public boolean isActive() {
      double value = this.getValue();
      List list = this.entityMob.worldObj.resolveList(this.memberRef, this.entityMob.getBoundingBox2().offset(value, 4.0, value), this.predicate);
      Collections.sort(list, this.auxiliaryComparatorAdapter);
      if (list.isEmpty()) {
         return false;
      } else {
         this.entityLivingBase = (EntityLivingBase)list.get(0);
         return true;
      }
   }

   public double getValue() {
      AuxiliaryListener auxiliaryListener = this.entityMob.getAuxiliaryListener(SharedMonsterAttributes.extendedListener4);
      return auxiliaryListener == null ? 16.0 : auxiliaryListener.getDouble();
   }

   public static EntityMob resolveEntityMob(AdvancedLoggerEntry advancedLoggerEntry) {
      return advancedLoggerEntry.entityMob;
   }

   @Override
   public boolean isActiveAsFlag() {
      EntityLivingBase entityLivingBase2 = this.entityMob.getEntityLivingBase4();
      if (entityLivingBase2 == null) {
         return false;
      } else if (!entityLivingBase2.canExecute()) {
         return false;
      } else {
         double value = this.getValue();
         return this.entityMob.getCoordinate(entityLivingBase2) > value * value
            ? false
            : !(entityLivingBase2 instanceof EntityPlayerAdapter) || !((EntityPlayerAdapter)entityLivingBase2).internalWorldEntry.isActiveAsFlag();
      }
   }

   @Override
   public void updateState() {
      this.entityMob.setEntityLivingBase((EntityLivingBase)null);
      super.updateState3();
   }

   public AdvancedLoggerEntry(EntityMob entityMob2, Class clazz) {
      this.entityMob = entityMob2;
      this.memberRef = clazz;
      if (entityMob2 instanceof AuxiliaryUUIDEntry) {
         logger.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
      }

      this.predicate = new CustomPredicateAdapter(this);
      this.auxiliaryComparatorAdapter = new AuxiliaryComparatorAdapter(entityMob2);
   }
}
