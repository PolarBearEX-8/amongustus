package net.minecraft.util;

import net.minecraft.entity.EntityLivingBase;

public class CombatEntry {
   private final DamageSource damageSrc;
   private final int time;
   private final float damage;
   private final float health;
   private final String fallSuffix;
   private final float fallDistance;

   public String getFallSuffix() {
      return this.fallSuffix;
   }

   public boolean isLivingDamageSrc() {
      return this.damageSrc.getEntity() instanceof EntityLivingBase;
   }

   public IChatComponent getDamageSrcDisplayName() {
      return this.getDamageSrc().getEntity() == null ? null : this.getDamageSrc().getEntity().getDisplayName();
   }

   public DamageSource getDamageSrc() {
      return this.damageSrc;
   }

   public float getDamage() {
      return this.damage;
   }

   public CombatEntry(DamageSource damageSource, int n, float f, float f2, String str, float f3) {
      this.damageSrc = damageSource;
      this.time = n;
      this.damage = f2;
      this.health = f;
      this.fallSuffix = str;
      this.fallDistance = f3;
   }

   public float getDamageAmount() {
      return this.damageSrc == DamageSource.outOfWorld ? Float.MAX_VALUE : this.fallDistance;
   }
}
