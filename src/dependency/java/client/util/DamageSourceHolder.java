package client.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;

public class DamageSourceHolder {
   public final DamageSource damageSource;
   public final int count;
   public final float factor;
   public final float factor2;
   public final String labelText;
   public final float factor3;

   public DamageSource getDamageSource() {
      return this.damageSource;
   }

   public DamageSourceHolder(DamageSource damageSource2, int count2, float factor4, float factor5, String text, float factor6) {
      this.damageSource = damageSource2;
      this.count = count2;
      this.factor = factor5;
      this.factor2 = factor4;
      this.labelText = text;
      this.factor3 = factor6;
   }

   public boolean isActive() {
      return this.damageSource.getEntity() instanceof EntityLivingBase;
   }

   public float getFactorAsFloat() {
      return this.damageSource == DamageSource.damageSource10 ? Float.MAX_VALUE : this.factor3;
   }

   public float getFactor() {
      return this.factor;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public IChatComponent getIChatComponent() {
      return this.getDamageSource().getEntity() == null ? null : this.getDamageSource().getEntity().getChatComponent();
   }
}
