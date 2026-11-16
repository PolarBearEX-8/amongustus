package client.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;

public class ClassArrayEntry extends PrimaryClientManagerAdapter {
   public boolean enabled3;
   public int count5;
   public final Class[] itemArray;

   @Override
   public void updateState3() {
      this.auxiliaryUUIDEntry.setEntityLivingBase(this.auxiliaryUUIDEntry.getTargetEntity());
      this.count5 = this.auxiliaryUUIDEntry.getIndexOffset5();
      if (this.enabled3) {
         double value = this.getValue();

         for (AuxiliaryUUIDEntry auxiliaryUUIDEntry : (Iterable<AuxiliaryUUIDEntry>)this.auxiliaryUUIDEntry
            .worldObj
            .resolveList(
               this.auxiliaryUUIDEntry.getClass(),
               new AxisAlignedBB(
                     this.auxiliaryUUIDEntry.posX,
                     this.auxiliaryUUIDEntry.posY,
                     this.auxiliaryUUIDEntry.posZ,
                     this.auxiliaryUUIDEntry.posX + 1.0,
                     this.auxiliaryUUIDEntry.posY + 1.0,
                     this.auxiliaryUUIDEntry.posZ + 1.0
                  )
                  .offset(value, 10.0, value)
            )) {
            if (this.auxiliaryUUIDEntry != auxiliaryUUIDEntry
               && auxiliaryUUIDEntry.getEntityLivingBase4() == null
               && !auxiliaryUUIDEntry.matchesEntityLivingBase(this.auxiliaryUUIDEntry.getTargetEntity())) {
               boolean flag = false;

               for (Class clazz : this.itemArray) {
                  if (auxiliaryUUIDEntry.getClass() == clazz) {
                     flag = true;
                     break;
                  }
               }

               if (!flag) {
                  this.applyAuxiliaryUUIDEntry(auxiliaryUUIDEntry, this.auxiliaryUUIDEntry.getTargetEntity());
               }
            }
         }
      }

      super.updateState3();
   }

   public ClassArrayEntry(AuxiliaryUUIDEntry auxiliaryUUIDEntry, boolean flag, Class[] clazzArray) {
      super(auxiliaryUUIDEntry, false);
      this.enabled3 = flag;
      this.itemArray = clazzArray;
      this.setCount(1);
   }

   @Override
   public boolean isActive() {
      return this.auxiliaryUUIDEntry.getIndexOffset5() != this.count5 && this.matchesEntityLivingBase(this.auxiliaryUUIDEntry.getTargetEntity(), false);
   }

   public void applyAuxiliaryUUIDEntry(AuxiliaryUUIDEntry auxiliaryUUIDEntry, EntityLivingBase livingEntity) {
      auxiliaryUUIDEntry.setEntityLivingBase(livingEntity);
   }
}
