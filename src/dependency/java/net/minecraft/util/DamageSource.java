package net.minecraft.util;

import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.DamageSourceAdapter;
import client.util.InterpolationSelector;
import net.minecraft.block.BlockBreakState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;

public class DamageSource {
   public static DamageSource damageSource8 = new DamageSource("inFire").getDamageSource5();
   public static DamageSource damageSource15 = new DamageSource("lightningBolt");
   public static DamageSource damageSource9 = new DamageSource("onFire").getDamageSource3().getDamageSource5();
   public static DamageSource damageSource4 = new DamageSource("lava").getDamageSource5();
   public static DamageSource damageSource3 = new DamageSource("inWall").getDamageSource3();
   public static DamageSource damageSource = new DamageSource("drown").getDamageSource3();
   public static DamageSource damageSource13 = new DamageSource("starve").getDamageSource3().getDamageSource6();
   public static DamageSource damageSource7 = new DamageSource("cactus");
   public static DamageSource damageSource11 = new DamageSource("fall").getDamageSource3();
   public static DamageSource damageSource10 = new DamageSource("outOfWorld").getDamageSource3().getDamageSource7();
   public static DamageSource damageSource12 = new DamageSource("generic").getDamageSource3();
   public static DamageSource damageSource5 = new DamageSource("magic").getDamageSource3().getDamageSourceAsDamageSource();
   public static DamageSource damageSource2 = new DamageSource("wither").getDamageSource3();
   public static DamageSource damageSource14 = new DamageSource("anvil");
   public static DamageSource damageSource6 = new DamageSource("fallingBlock");
   public boolean enabled;
   public boolean enabled4;
   public boolean enabled7;
   public float factor = 0.3F;
   public boolean enabled6;
   public boolean enabled8;
   public boolean enabled2;
   public boolean enabled3;
   public boolean enabled5;
   public String labelText;

   public static DamageSource resolveDamageSourceByEntityLivingBase(EntityLivingBase livingEntity) {
      return new DamageSourceAdapter("mob", livingEntity);
   }

   public boolean isEnabled4() {
      return this.enabled4;
   }

   public DamageSource getDamageSource() {
      this.enabled2 = true;
      return this;
   }

   public static DamageSource resolveDamageSource2(Entity entity, Entity entity2) {
      return new InterpolationSelector("thrown", entity, entity2).getDamageSource2();
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public DamageSource getDamageSource6() {
      this.enabled7 = true;
      this.factor = 0.0F;
      return this;
   }

   public static DamageSource resolveDamageSource(Entity entity, Entity entity2) {
      return new InterpolationSelector("indirectMagic", entity, entity2).getDamageSource3().getDamageSourceAsDamageSource();
   }

   public Entity getEntity() {
      return null;
   }

   public static DamageSource resolveDamageSourceByEntity(Entity entity) {
      return new DamageSourceAdapter("thorns", entity).getDamageSourceAdapter().getDamageSourceAsDamageSource();
   }

   public DamageSource getDamageSource2() {
      this.enabled8 = true;
      return this;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public float getFactor() {
      return this.factor;
   }

   public DamageSource getDamageSource4() {
      this.enabled5 = true;
      return this;
   }

   public boolean isEnabled5() {
      return this.enabled5;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public DamageSource getDamageSource5() {
      this.enabled6 = true;
      return this;
   }

   public static DamageSource resolveDamageSource(EntityFireball entityFireball, Entity entity) {
      return entity == null
         ? new InterpolationSelector("onFire", entityFireball, entityFireball).getDamageSource5().getDamageSource2()
         : new InterpolationSelector("fireball", entityFireball, entity).getDamageSource5().getDamageSource2();
   }

   public boolean isEnabled8() {
      return this.enabled8;
   }

   public boolean isEnabled6() {
      return this.enabled6;
   }

   public boolean canExecute() {
      return this.enabled;
   }

   public DamageSource getDamageSource7() {
      this.enabled4 = true;
      return this;
   }

   public Entity getEntityAsEntity() {
      return this.getEntity();
   }

   public static DamageSource resolveDamageSource(BlockBreakState blockBreakState) {
      return blockBreakState != null && blockBreakState.getEntityLivingBase() != null
         ? new DamageSourceAdapter("explosion.player", blockBreakState.getEntityLivingBase()).getDamageSource().getDamageSource4()
         : new DamageSource("explosion").getDamageSource().getDamageSource4();
   }

   public static DamageSource resolveDamageSource(EntityPlayer player) {
      return new DamageSourceAdapter("player", player);
   }

   public boolean isActive() {
      Entity entity = this.getEntity();
      return entity instanceof EntityPlayer && ((EntityPlayer)entity).invulnerableManager.enabled2;
   }

   public DamageSource getDamageSourceAsDamageSource() {
      this.enabled3 = true;
      return this;
   }

   public IChatComponent resolveIChatComponent(EntityLivingBase livingEntity) {
      EntityLivingBase entityLivingBase = livingEntity.getEntityLivingBase();
      String text = "death.attack." + this.labelText;
      String text2 = text + ".player";
      return entityLivingBase != null && I18n.matchesText(text2)
         ? new BaseManagerStyleIChatComponentAdapter(text2, new Object[]{livingEntity.getChatComponent(), entityLivingBase.getChatComponent()})
         : new BaseManagerStyleIChatComponentAdapter(text, new Object[]{livingEntity.getChatComponent()});
   }

   public static DamageSource resolveDamageSourceByEntityArrow(EntityArrow entityArrow, Entity entity) {
      return new InterpolationSelector("arrow", entityArrow, entity).getDamageSource2();
   }

   public DamageSource getDamageSource3() {
      this.enabled = true;
      this.factor = 0.0F;
      return this;
   }

   public boolean canExecute2() {
      return this.enabled7;
   }

   public DamageSource(String text) {
      this.labelText = text;
   }
}
