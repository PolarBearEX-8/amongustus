package client.util;

import net.minecraft.entity.EntityGuardian;
import net.minecraft.entity.EntityPillagerAdapter;
import net.minecraft.entity.EntityRavagerAdapter;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.MathHelper;

public class BezierDirector extends EntityPillagerAdapter {
   public EntityGuardian entityGuardian;

   @Override
   public void updateState() {
      if (this.enabled && !this.entityGuardian.getPathfindManager().isActive2()) {
         double offset = this.value4 - this.entityGuardian.posX;
         double offset2 = this.value2 - this.entityGuardian.posY;
         double offset3 = this.value3 - this.entityGuardian.posZ;
         double offset4 = (double)MathHelper.sqrt_double(offset * offset + offset2 * offset2 + offset3 * offset3);
         double offset5 = offset2 / offset4;
         float factor = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI) - 90.0F;
         this.entityGuardian.rotationYaw = this.computeFactor(this.entityGuardian.rotationYaw, factor, 30.0F);
         this.entityGuardian.scaleFactor8 = this.entityGuardian.rotationYaw;
         float factor2 = (float)(this.value * this.entityGuardian.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).getDouble());
         this.entityGuardian.setScaleFactor6(this.entityGuardian.getScaleFactor6() + (factor2 - this.entityGuardian.getScaleFactor6()) * 0.125F);
         double offset6 = Math.sin((double)(this.entityGuardian.ticksExisted + this.entityGuardian.getEntityId()) * 0.5) * 0.05;
         double offset7 = Math.cos((double)(this.entityGuardian.rotationYaw * (float) Math.PI / 180.0F));
         double offset8 = Math.sin((double)(this.entityGuardian.rotationYaw * (float) Math.PI / 180.0F));
         this.entityGuardian.motionX += offset6 * offset7;
         this.entityGuardian.motionZ += offset6 * offset8;
         double offset9 = Math.sin((double)(this.entityGuardian.ticksExisted + this.entityGuardian.getEntityId()) * 0.75) * 0.05;
         this.entityGuardian.motionY += offset9 * (offset8 + offset7) * 0.25;
         this.entityGuardian.motionY = this.entityGuardian.motionY + (double)this.entityGuardian.getScaleFactor6() * offset5 * 0.1;
         EntityRavagerAdapter entityRavagerAdapter = this.entityGuardian.getEntityRavagerAdapter();
         double offset10 = this.entityGuardian.posX + offset / offset4 * 2.0;
         double offset11 = (double)this.entityGuardian.getHeight2() + this.entityGuardian.posY + offset5 / offset4 * 1.0;
         double offset12 = this.entityGuardian.posZ + offset3 / offset4 * 2.0;
         double value = entityRavagerAdapter.getValue2();
         double value2 = entityRavagerAdapter.getValue3();
         double value3 = entityRavagerAdapter.getValue();
         if (!entityRavagerAdapter.isEnabled()) {
            value = offset10;
            value2 = offset11;
            value3 = offset12;
         }

         this.entityGuardian
            .getEntityRavagerAdapter()
            .applyDouble(value + (offset10 - value) * 0.125, value2 + (offset11 - value2) * 0.125, value3 + (offset12 - value3) * 0.125, 10.0F, 40.0F);
         EntityGuardian.applyEntityGuardian(this.entityGuardian, true);
      } else {
         this.entityGuardian.setScaleFactor6(0.0F);
         EntityGuardian.applyEntityGuardian(this.entityGuardian, false);
      }
   }

   public BezierDirector(EntityGuardian entityGuardian2) {
      super(entityGuardian2);
      this.entityGuardian = entityGuardian2;
   }
}
