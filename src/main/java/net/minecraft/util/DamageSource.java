package net.minecraft.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.world.Explosion;

public class DamageSource {
   public static DamageSource inFire = new DamageSource("inFire").setFireDamage();
   public static DamageSource lightningBolt = new DamageSource("lightningBolt");
   public static DamageSource onFire = new DamageSource("onFire").setDamageBypassesArmor().setFireDamage();
   public static DamageSource lava = new DamageSource("lava").setFireDamage();
   public static DamageSource inWall = new DamageSource("inWall").setDamageBypassesArmor();
   public static DamageSource drown = new DamageSource("drown").setDamageBypassesArmor();
   public static DamageSource starve = new DamageSource("starve").setDamageBypassesArmor().setDamageIsAbsolute();
   public static DamageSource cactus = new DamageSource("cactus");
   public static DamageSource fall = new DamageSource("fall").setDamageBypassesArmor();
   public static DamageSource outOfWorld = new DamageSource("outOfWorld").setDamageBypassesArmor().setDamageAllowedInCreativeMode();
   public static DamageSource generic = new DamageSource("generic").setDamageBypassesArmor();
   public static DamageSource magic = new DamageSource("magic").setDamageBypassesArmor().setMagicDamage();
   public static DamageSource wither = new DamageSource("wither").setDamageBypassesArmor();
   public static DamageSource anvil = new DamageSource("anvil");
   public static DamageSource fallingBlock = new DamageSource("fallingBlock");
   private boolean isUnblockable;
   private boolean harmInCreative;
   private boolean damageAbsolute;
   private float hungerDamage = 0.3F;
   private boolean fireDamage;
   private boolean projectile;
   private boolean difficultyScaled;
   private boolean magicDamage;
   private boolean explosion;
   public String damageType;

   public DamageSource setExplosion() {
      this.explosion = true;
      return this;
   }

   public boolean canHarmInCreative() {
      return this.harmInCreative;
   }

   public boolean isMagicDamage() {
      return this.magicDamage;
   }

   public static DamageSource causeIndirectMagicDamage(Entity entity, Entity entity2) {
      return new EntityDamageSourceIndirect("indirectMagic", entity, entity2).setDamageBypassesArmor().setMagicDamage();
   }

   public IChatComponent getDeathMessage(EntityLivingBase entity) {
      EntityLivingBase attackingEntity = entity.getAttackingEntity();
      String str = "death.attack." + this.damageType;
      String str2 = str + ".player";
      return attackingEntity != null && StatCollector.canTranslate(str2)
         ? new ChatComponentTranslation(str2, new Object[]{entity.getDisplayName(), attackingEntity.getDisplayName()})
         : new ChatComponentTranslation(str, new Object[]{entity.getDisplayName()});
   }

   public static DamageSource causeThornsDamage(Entity entity) {
      return new EntityDamageSource("thorns", entity).setIsThornsDamage().setMagicDamage();
   }

   public static DamageSource causeFireballDamage(EntityFireball entityFireball, Entity entity) {
      return entity == null
         ? new EntityDamageSourceIndirect("onFire", entityFireball, entityFireball).setFireDamage().setProjectile()
         : new EntityDamageSourceIndirect("fireball", entityFireball, entity).setFireDamage().setProjectile();
   }

   public boolean isExplosion() {
      return this.explosion;
   }

   public DamageSource setMagicDamage() {
      this.magicDamage = true;
      return this;
   }

   public boolean isUnblockable() {
      return this.isUnblockable;
   }

   public boolean isCreativePlayer() {
      Entity entity = this.getEntity();
      return entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode;
   }

   public static DamageSource causeThrownDamage(Entity entity, Entity entity2) {
      return new EntityDamageSourceIndirect("thrown", entity, entity2).setProjectile();
   }

   public static DamageSource causeArrowDamage(EntityArrow entityArrow, Entity entity) {
      return new EntityDamageSourceIndirect("arrow", entityArrow, entity).setProjectile();
   }

   public boolean isDamageAbsolute() {
      return this.damageAbsolute;
   }

   public DamageSource setDamageIsAbsolute() {
      this.damageAbsolute = true;
      this.hungerDamage = 0.0F;
      return this;
   }

   public static DamageSource causePlayerDamage(EntityPlayer player) {
      return new EntityDamageSource("player", player);
   }

   public Entity getSourceOfDamage() {
      return this.getEntity();
   }

   public DamageSource setDifficultyScaled() {
      this.difficultyScaled = true;
      return this;
   }

   public boolean isProjectile() {
      return this.projectile;
   }

   public static DamageSource causeMobDamage(EntityLivingBase entity) {
      return new EntityDamageSource("mob", entity);
   }

   public float getHungerDamage() {
      return this.hungerDamage;
   }

   public DamageSource setProjectile() {
      this.projectile = true;
      return this;
   }

   public boolean isDifficultyScaled() {
      return this.difficultyScaled;
   }

   public Entity getEntity() {
      return null;
   }

   public static DamageSource setExplosionSource(Explosion explosion2) {
      return explosion2 != null && explosion2.getExplosivePlacedBy() != null
         ? new EntityDamageSource("explosion.player", explosion2.getExplosivePlacedBy()).setDifficultyScaled().setExplosion()
         : new DamageSource("explosion").setDifficultyScaled().setExplosion();
   }

   public boolean isFireDamage() {
      return this.fireDamage;
   }

   public DamageSource setFireDamage() {
      this.fireDamage = true;
      return this;
   }

   public DamageSource(String str) {
      this.damageType = str;
   }

   public DamageSource setDamageAllowedInCreativeMode() {
      this.harmInCreative = true;
      return this;
   }

   public DamageSource setDamageBypassesArmor() {
      this.isUnblockable = true;
      this.hungerDamage = 0.0F;
      return this;
   }

   public String getDamageType() {
      return this.damageType;
   }
}
