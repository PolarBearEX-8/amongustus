package net.minecraft.potion;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

public class Potion {
   public static final Potion[] potionTypes = new Potion[32];
   private static final Map locationPotions = Maps.newHashMap();
   public static final Potion unusedPotion0 = null;
   public static final Potion moveSpeed = new Potion(1, new ResourceLocation("speed"), false, 8171462)
      .setPotionName("potion.moveSpeed")
      .setIconIndex(0, 0)
      .registerPotionAttributeModifier(SharedMonsterAttributes.movementSpeed, "91AEAA56-376B-4498-935B-2F7F68070635", 0.2F, 2);
   public static final Potion moveSlowdown = new Potion(2, new ResourceLocation("slowness"), true, 5926017)
      .setPotionName("potion.moveSlowdown")
      .setIconIndex(1, 0)
      .registerPotionAttributeModifier(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15F, 2);
   public static final Potion digSpeed = new Potion(3, new ResourceLocation("haste"), false, 14270531)
      .setPotionName("potion.digSpeed")
      .setIconIndex(2, 0)
      .setEffectiveness(1.5);
   public static final Potion digSlowdown = new Potion(4, new ResourceLocation("mining_fatigue"), true, 4866583)
      .setPotionName("potion.digSlowDown")
      .setIconIndex(3, 0);
   public static final Potion damageBoost = new PotionAttackDamage(5, new ResourceLocation("strength"), false, 9643043)
      .setPotionName("potion.damageBoost")
      .setIconIndex(4, 0)
      .registerPotionAttributeModifier(SharedMonsterAttributes.attackDamage, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 2.5, 2);
   public static final Potion heal = new PotionHealth(6, new ResourceLocation("instant_health"), false, 16262179).setPotionName("potion.heal");
   public static final Potion harm = new PotionHealth(7, new ResourceLocation("instant_damage"), true, 4393481).setPotionName("potion.harm");
   public static final Potion jump = new Potion(8, new ResourceLocation("jump_boost"), false, 2293580).setPotionName("potion.jump").setIconIndex(2, 1);
   public static final Potion confusion = new Potion(9, new ResourceLocation("nausea"), true, 5578058)
      .setPotionName("potion.confusion")
      .setIconIndex(3, 1)
      .setEffectiveness(0.25);
   public static final Potion regeneration = new Potion(10, new ResourceLocation("regeneration"), false, 13458603)
      .setPotionName("potion.regeneration")
      .setIconIndex(7, 0)
      .setEffectiveness(0.25);
   public static final Potion resistance = new Potion(11, new ResourceLocation("resistance"), false, 10044730)
      .setPotionName("potion.resistance")
      .setIconIndex(6, 1);
   public static final Potion fireResistance = new Potion(12, new ResourceLocation("fire_resistance"), false, 14981690)
      .setPotionName("potion.fireResistance")
      .setIconIndex(7, 1);
   public static final Potion waterBreathing = new Potion(13, new ResourceLocation("water_breathing"), false, 3035801)
      .setPotionName("potion.waterBreathing")
      .setIconIndex(0, 2);
   public static final Potion invisibility = new Potion(14, new ResourceLocation("invisibility"), false, 8356754)
      .setPotionName("potion.invisibility")
      .setIconIndex(0, 1);
   public static final Potion blindness = new Potion(15, new ResourceLocation("blindness"), true, 2039587)
      .setPotionName("potion.blindness")
      .setIconIndex(5, 1)
      .setEffectiveness(0.25);
   public static final Potion nightVision = new Potion(16, new ResourceLocation("night_vision"), false, 2039713)
      .setPotionName("potion.nightVision")
      .setIconIndex(4, 1);
   public static final Potion hunger = new Potion(17, new ResourceLocation("hunger"), true, 5797459).setPotionName("potion.hunger").setIconIndex(1, 1);
   public static final Potion weakness = new PotionAttackDamage(18, new ResourceLocation("weakness"), true, 4738376)
      .setPotionName("potion.weakness")
      .setIconIndex(5, 0)
      .registerPotionAttributeModifier(SharedMonsterAttributes.attackDamage, "22653B89-116E-49DC-9B6B-9971489B5BE5", 2.0, 0);
   public static final Potion poison = new Potion(19, new ResourceLocation("poison"), true, 5149489)
      .setPotionName("potion.poison")
      .setIconIndex(6, 0)
      .setEffectiveness(0.25);
   public static final Potion wither = new Potion(20, new ResourceLocation("wither"), true, 3484199)
      .setPotionName("potion.wither")
      .setIconIndex(1, 2)
      .setEffectiveness(0.25);
   public static final Potion healthBoost = new PotionHealthBoost(21, new ResourceLocation("health_boost"), false, 16284963)
      .setPotionName("potion.healthBoost")
      .setIconIndex(2, 2)
      .registerPotionAttributeModifier(SharedMonsterAttributes.maxHealth, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0, 0);
   public static final Potion absorption = new PotionAbsorption(22, new ResourceLocation("absorption"), false, 2445989)
      .setPotionName("potion.absorption")
      .setIconIndex(2, 2);
   public static final Potion saturation = new PotionHealth(23, new ResourceLocation("saturation"), false, 16262179).setPotionName("potion.saturation");
   public static final Potion unusedPotion24 = null;
   public static final Potion unusedPotion25 = null;
   public static final Potion unusedPotion26 = null;
   public static final Potion unusedPotion27 = null;
   public static final Potion unusedPotion28 = null;
   public static final Potion unusedPotion29 = null;
   public static final Potion unusedPotion30 = null;
   public static final Potion unusedPotion31 = null;
   public final int id;
   private final Map attributeModifierMap = Maps.newHashMap();
   private final boolean isBadEffect;
   private final int liquidColor;
   private String name = "";
   private int statusIconIndex = -1;
   private double effectiveness;
   private boolean usable;

   public void applyAttributesModifiersToEntity(EntityLivingBase entity, BaseAttributeMap baseAttributeMap, int n) {
      for (Entry entry : (Iterable<Entry>)(this.attributeModifierMap.entrySet())) {
         IAttributeInstance attribute = baseAttributeMap.getAttribute((IAttribute)entry.getKey());
         if (attribute != null) {
            AttributeModifier value = (AttributeModifier)entry.getValue();
            attribute.removeModifier(value);
            attribute.applyModifier(
               new AttributeModifier(value.getID(), this.getName() + " " + n, this.getAttributeModifierAmount(n, value), value.getOperation())
            );
         }
      }
   }

   public boolean isReady(int n, int n2) {
      if (this.id == regeneration.id) {
         int n3 = 50 >> n2;
         return n3 > 0 ? n % n3 == 0 : true;
      } else if (this.id == poison.id) {
         int n4 = 25 >> n2;
         return n4 > 0 ? n % n4 == 0 : true;
      } else if (this.id == wither.id) {
         int n5 = 40 >> n2;
         return n5 > 0 ? n % n5 == 0 : true;
      } else {
         return this.id == hunger.id;
      }
   }

   public void removeAttributesModifiersFromEntity(EntityLivingBase entity, BaseAttributeMap baseAttributeMap, int n) {
      for (Entry entry : (Iterable<Entry>)(this.attributeModifierMap.entrySet())) {
         IAttributeInstance attribute = baseAttributeMap.getAttribute((IAttribute)entry.getKey());
         if (attribute != null) {
            attribute.removeModifier((AttributeModifier)entry.getValue());
         }
      }
   }

   public boolean isInstant() {
      return false;
   }

   public static String getDurationString(PotionEffect potionEffect) {
      if (potionEffect.getIsPotionDurationMax()) {
         return "**:**";
      } else {
         return StringUtils.ticksToElapsedTime(potionEffect.getDuration());
      }
   }

   public static Set getPotionLocations() {
      return locationPotions.keySet();
   }

   public void affectEntity(Entity entity, Entity entity2, EntityLivingBase entity3, int n, double d) {
      if ((this.id != heal.id || entity3.isEntityUndead()) && (this.id != harm.id || !entity3.isEntityUndead())) {
         if (this.id == harm.id && !entity3.isEntityUndead() || this.id == heal.id && entity3.isEntityUndead()) {
            int n2 = (int)(d * (double)(6 << n) + 0.5);
            if (entity == null) {
               entity3.attackEntityFrom(DamageSource.magic, (float)n2);
            } else {
               entity3.attackEntityFrom(DamageSource.causeIndirectMagicDamage(entity, entity2), (float)n2);
            }
         }
      } else {
         entity3.heal((float)((int)(d * (double)(4 << n) + 0.5)));
      }
   }

   public void performEffect(EntityLivingBase entity, int n) {
      if (this.id == regeneration.id) {
         if (entity.getCurrentHealth() < entity.getMaxHealth()) {
            entity.heal(1.0F);
         }
      } else if (this.id == poison.id) {
         if (entity.getCurrentHealth() > 1.0F) {
            entity.attackEntityFrom(DamageSource.magic, 1.0F);
         }
      } else if (this.id == wither.id) {
         entity.attackEntityFrom(DamageSource.wither, 1.0F);
      } else if (this.id == hunger.id && entity instanceof EntityPlayer) {
         ((EntityPlayer)entity).addExhaustion(0.025F * (float)(n + 1));
      } else if (this.id == saturation.id && entity instanceof EntityPlayer) {
         if (!entity.entityWorld.isRemote) {
            ((EntityPlayer)entity).getFoodStats().addStats(n + 1, 1.0F);
         }
      } else if ((this.id != heal.id || entity.isEntityUndead()) && (this.id != harm.id || !entity.isEntityUndead())) {
         if (this.id == harm.id && !entity.isEntityUndead() || this.id == heal.id && entity.isEntityUndead()) {
            entity.attackEntityFrom(DamageSource.magic, (float)(6 << n));
         }
      } else {
         entity.heal((float)Math.max(4 << n, 0));
      }
   }

   public boolean isBadEffect() {
      return this.isBadEffect;
   }

   public String getName() {
      return this.name;
   }

   public Potion setIconIndex(int n, int n2) {
      this.statusIconIndex = n + n2 * 8;
      return this;
   }

   public Potion setPotionName(String str) {
      this.name = str;
      return this;
   }

   public Potion(int n, ResourceLocation location, boolean flag, int n2) {
      this.id = n;
      potionTypes[n] = this;
      locationPotions.put(location, this);
      this.isBadEffect = flag;
      if (flag) {
         this.effectiveness = 0.5;
      } else {
         this.effectiveness = 1.0;
      }

      this.liquidColor = n2;
   }

   public double getEffectiveness() {
      return this.effectiveness;
   }

   public int getStatusIconIndex() {
      return this.statusIconIndex;
   }

   public boolean isUsable() {
      return this.usable;
   }

   public boolean hasStatusIcon() {
      return this.statusIconIndex >= 0;
   }

   public static Potion getPotionFromResourceLocation(String str) {
      return (Potion)locationPotions.get(new ResourceLocation(str));
   }

   public Map getAttributeModifierMap() {
      return this.attributeModifierMap;
   }

   public int getLiquidColor() {
      return this.liquidColor;
   }

   public double getAttributeModifierAmount(int n, AttributeModifier attributeModifier) {
      return attributeModifier.getAmount() * (double)(n + 1);
   }

   public Potion registerPotionAttributeModifier(IAttribute attribute, String str, double d, int n) {
      AttributeModifier attributeModifier = new AttributeModifier(UUID.fromString(str), this.getName(), d, n);
      this.attributeModifierMap.put(attribute, attributeModifier);
      return this;
   }

   public int getId() {
      return this.id;
   }

   public Potion setEffectiveness(double d) {
      this.effectiveness = d;
      return this;
   }
}
