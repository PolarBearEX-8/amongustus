package client.util;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;

public class ExtendedClassEntry extends PrimaryClientManagerAdapter {
   public final Class memberRef;
   public final int count5;
   public final AuxiliaryComparatorAdapter auxiliaryComparatorAdapter;
   public Predicate predicate;
   public EntityLivingBase entityLivingBase;

   public ExtendedClassEntry(AuxiliaryUUIDEntry auxiliaryUUIDEntry, Class clazz, int count, boolean flag, boolean flag2, Predicate predicate2) {
      super(auxiliaryUUIDEntry, flag, flag2);
      this.memberRef = clazz;
      this.count5 = count;
      this.auxiliaryComparatorAdapter = new AuxiliaryComparatorAdapter(auxiliaryUUIDEntry);
      this.setCount(1);
      this.predicate = new DefaultPredicateAdapter(this, predicate2);
   }

   @Override
   public boolean isActive() {
      if (this.count5 > 0 && this.auxiliaryUUIDEntry.getRandom().nextInt(this.count5) != 0) {
         return false;
      } else {
         double value = this.getValue();
         List list = this.auxiliaryUUIDEntry
            .worldObj
            .resolveList(
               this.memberRef, this.auxiliaryUUIDEntry.getBoundingBox2().offset(value, 4.0, value), Predicates.and(this.predicate, LivingEntityFilter.predicate4)
            );
         Collections.sort(list, this.auxiliaryComparatorAdapter);
         if (list.isEmpty()) {
            return false;
         } else {
            this.entityLivingBase = (EntityLivingBase)list.get(0);
            return true;
         }
      }
   }

   public ExtendedClassEntry(AuxiliaryUUIDEntry auxiliaryUUIDEntry, Class clazz, boolean flag) {
      this(auxiliaryUUIDEntry, clazz, flag, false);
   }

   @Override
   public void updateState3() {
      this.auxiliaryUUIDEntry.setEntityLivingBase(this.entityLivingBase);
      super.updateState3();
   }

   public ExtendedClassEntry(AuxiliaryUUIDEntry auxiliaryUUIDEntry, Class clazz, boolean flag, boolean flag2) {
      this(auxiliaryUUIDEntry, clazz, 10, flag, flag2, (Predicate)null);
   }
}
