package net.minecraft.potion;

import client.event.AuxiliaryListener;
import client.event.ExtendedListener;
import client.util.AdvancedMapEntry;
import client.util.AuxiliaryPotionAdapter;
import client.util.ClientBrandHelper;
import client.util.ExtendedPotionAdapter;
import client.util.InternalPotionAdapter;
import client.util.InvalidOperationManager;
import client.util.PotionAdapter;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class Potion {
   public static final Potion[] potionArray = new Potion[32];
   public static final Map map = Maps.newHashMap();
   public static final Potion potion20 = null;
   public static final Potion potion2 = new Potion(1, new ResourceLocation("speed"), false, 8171462)
      .withText("potion.moveSpeed")
      .resolvePotion(0, 0)
      .resolvePotion(SharedMonsterAttributes.extendedListener3, "91AEAA56-376B-4498-935B-2F7F68070635", 0.2F, 2);
   public static final Potion potion3 = new Potion(2, new ResourceLocation("slowness"), true, 5926017)
      .withText("potion.moveSlowdown")
      .resolvePotion(1, 0)
      .resolvePotion(SharedMonsterAttributes.extendedListener3, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15F, 2);
   public static final Potion potion28 = new Potion(3, new ResourceLocation("haste"), false, 14270531)
      .withText("potion.digSpeed")
      .resolvePotion(2, 0)
      .withCoordOffset(1.5);
   public static final Potion potion7 = new Potion(4, new ResourceLocation("mining_fatigue"), true, 4866583).withText("potion.digSlowDown").resolvePotion(3, 0);
   public static final Potion potion = new PotionAdapter(5, new ResourceLocation("strength"), false, 9643043)
      .withText("potion.damageBoost")
      .resolvePotion(4, 0)
      .resolvePotion(SharedMonsterAttributes.extendedListener, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 2.5, 2);
   public static final Potion potion21 = new InternalPotionAdapter(6, new ResourceLocation("instant_health"), false, 16262179).withText("potion.heal");
   public static final Potion potion10 = new InternalPotionAdapter(7, new ResourceLocation("instant_damage"), true, 4393481).withText("potion.harm");
   public static final Potion potion9 = new Potion(8, new ResourceLocation("jump_boost"), false, 2293580).withText("potion.jump").resolvePotion(2, 1);
   public static final Potion potion15 = new Potion(9, new ResourceLocation("nausea"), true, 5578058)
      .withText("potion.confusion")
      .resolvePotion(3, 1)
      .withCoordOffset(0.25);
   public static final Potion potion11 = new Potion(10, new ResourceLocation("regeneration"), false, 13458603)
      .withText("potion.regeneration")
      .resolvePotion(7, 0)
      .withCoordOffset(0.25);
   public static final Potion potion12 = new Potion(11, new ResourceLocation("resistance"), false, 10044730).withText("potion.resistance").resolvePotion(6, 1);
   public static final Potion potion13 = new Potion(12, new ResourceLocation("fire_resistance"), false, 14981690)
      .withText("potion.fireResistance")
      .resolvePotion(7, 1);
   public static final Potion potion18 = new Potion(13, new ResourceLocation("water_breathing"), false, 3035801)
      .withText("potion.waterBreathing")
      .resolvePotion(0, 2);
   public static final Potion potion30 = new Potion(14, new ResourceLocation("invisibility"), false, 8356754)
      .withText("potion.invisibility")
      .resolvePotion(0, 1);
   public static final Potion potion16 = new Potion(15, new ResourceLocation("blindness"), true, 2039587)
      .withText("potion.blindness")
      .resolvePotion(5, 1)
      .withCoordOffset(0.25);
   public static final Potion potion31 = new Potion(16, new ResourceLocation("night_vision"), false, 2039713)
      .withText("potion.nightVision")
      .resolvePotion(4, 1);
   public static final Potion potion29 = new Potion(17, new ResourceLocation("hunger"), true, 5797459).withText("potion.hunger").resolvePotion(1, 1);
   public static final Potion potion26 = new PotionAdapter(18, new ResourceLocation("weakness"), true, 4738376)
      .withText("potion.weakness")
      .resolvePotion(5, 0)
      .resolvePotion(SharedMonsterAttributes.extendedListener, "22653B89-116E-49DC-9B6B-9971489B5BE5", 2.0, 0);
   public static final Potion potion24 = new Potion(19, new ResourceLocation("poison"), true, 5149489)
      .withText("potion.poison")
      .resolvePotion(6, 0)
      .withCoordOffset(0.25);
   public static final Potion potion32 = new Potion(20, new ResourceLocation("wither"), true, 3484199)
      .withText("potion.wither")
      .resolvePotion(1, 2)
      .withCoordOffset(0.25);
   public static final Potion potion6 = new ExtendedPotionAdapter(21, new ResourceLocation("health_boost"), false, 16284963)
      .withText("potion.healthBoost")
      .resolvePotion(2, 2)
      .resolvePotion(SharedMonsterAttributes.extendedListener5, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0, 0);
   public static final Potion potion23 = new AuxiliaryPotionAdapter(22, new ResourceLocation("absorption"), false, 2445989)
      .withText("potion.absorption")
      .resolvePotion(2, 2);
   public static final Potion potion22 = new InternalPotionAdapter(23, new ResourceLocation("saturation"), false, 16262179).withText("potion.saturation");
   public static final Potion potion25 = null;
   public static final Potion potion27 = null;
   public static final Potion potion14 = null;
   public static final Potion potion5 = null;
   public static final Potion potion19 = null;
   public static final Potion potion17 = null;
   public static final Potion potion4 = null;
   public static final Potion potion8 = null;
   public final int indexOffset3;
   public final Map entryMap2 = Maps.newHashMap();
   public final boolean state2;
   public final int indexOffset1;
   public String text = "";
   public int indexOffset = -1;
   public double coordOffset;
   public boolean state;

   public boolean isState2() {
      return this.state2;
   }

   public static String getText(PotionEffect potionEffect) {
      return potionEffect.isState() ? "**:**" : ClientBrandHelper.resolveText(potionEffect.getIndexOffset());
   }

   public boolean canExecute() {
      return this.indexOffset >= 0;
   }

   public static Set keySet() {
      return map.keySet();
   }

   public Potion resolvePotion(int count, int count2) {
      this.indexOffset = count + count2 * 8;
      return this;
   }

   public Potion withText(String text2) {
      this.text = text2;
      return this;
   }

   public String getText() {
      return this.text;
   }

   public static Potion get(String text2) {
      return (Potion)map.get(new ResourceLocation(text2));
   }

   public void applyEntityLivingBase(EntityLivingBase livingEntity, AdvancedMapEntry advancedMapEntry, int count) {
      for (Object entryObj : this.entryMap2.entrySet()) {
         Entry entry = (Entry)entryObj;
         AuxiliaryListener auxiliaryListener = advancedMapEntry.get((ExtendedListener)entry.getKey());
         if (auxiliaryListener != null) {
            InvalidOperationManager value = (InvalidOperationManager)entry.getValue();
            auxiliaryListener.applyInvalidOperationManagerByInvalidOperation(value);
            auxiliaryListener.applyInvalidOperationManager(
               new InvalidOperationManager(value.getUUID(), this.getText() + " " + count, this.computeValue(count, value), value.getCount())
            );
         }
      }
   }

   public double getCoordOffset() {
      return this.coordOffset;
   }

   public void applyEntityLivingBase(EntityLivingBase livingEntity, int count) {
      if (this.indexOffset3 == potion11.indexOffset3) {
         if (livingEntity.getFloat2() < livingEntity.getFloat()) {
            livingEntity.applyFloatByFloat(1.0F);
         }
      } else if (this.indexOffset3 == potion24.indexOffset3) {
         if (livingEntity.getFloat2() > 1.0F) {
            livingEntity.matchesDamageSource(DamageSource.damageSource5, 1.0F);
         }
      } else if (this.indexOffset3 == potion32.indexOffset3) {
         livingEntity.matchesDamageSource(DamageSource.damageSource2, 1.0F);
      } else if (this.indexOffset3 == potion29.indexOffset3 && livingEntity instanceof EntityPlayer) {
         ((EntityPlayer)livingEntity).applyFloat4(0.025F * (float)(count + 1));
      } else if (this.indexOffset3 == potion22.indexOffset3 && livingEntity instanceof EntityPlayer) {
         if (!livingEntity.worldObj.state) {
            ((EntityPlayer)livingEntity).getFoodLevelManager().applyIndex(count + 1, 1.0F);
         }
      } else if ((this.indexOffset3 != potion21.indexOffset3 || livingEntity.isActive21()) && (this.indexOffset3 != potion10.indexOffset3 || !livingEntity.isActive21())) {
         if (this.indexOffset3 == potion10.indexOffset3 && !livingEntity.isActive21() || this.indexOffset3 == potion21.indexOffset3 && livingEntity.isActive21()) {
            livingEntity.matchesDamageSource(DamageSource.damageSource5, (float)(6 << count));
         }
      } else {
         livingEntity.applyFloatByFloat((float)Math.max(4 << count, 0));
      }
   }

   public Map getEntryMap2() {
      return this.entryMap2;
   }

   public boolean checkIndex(int count, int count2) {
      if (this.indexOffset3 == potion11.indexOffset3) {
         int count3 = 50 >> count2;
         return count3 > 0 ? count % count3 == 0 : true;
      } else if (this.indexOffset3 == potion24.indexOffset3) {
         int count4 = 25 >> count2;
         return count4 > 0 ? count % count4 == 0 : true;
      } else if (this.indexOffset3 == potion32.indexOffset3) {
         int count5 = 40 >> count2;
         return count5 > 0 ? count % count5 == 0 : true;
      } else {
         return this.indexOffset3 == potion29.indexOffset3;
      }
   }

   public Potion resolvePotion(ExtendedListener extendedListener, String text2, double offset, int count) {
      InvalidOperationManager invalidOperationManager = new InvalidOperationManager(UUID.fromString(text2), this.getText(), offset, count);
      this.entryMap2.put(extendedListener, invalidOperationManager);
      return this;
   }

   public void applyEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity, AdvancedMapEntry advancedMapEntry, int count) {
      for (Object entryObj : this.entryMap2.entrySet()) {
         Entry entry = (Entry)entryObj;
         AuxiliaryListener auxiliaryListener = advancedMapEntry.get((ExtendedListener)entry.getKey());
         if (auxiliaryListener != null) {
            auxiliaryListener.applyInvalidOperationManagerByInvalidOperation((InvalidOperationManager)entry.getValue());
         }
      }
   }

   public int getIndexOffset() {
      return this.indexOffset;
   }

   public double computeValue(int count, InvalidOperationManager invalidOperationManager) {
      return invalidOperationManager.getValue() * (double)(count + 1);
   }

   public boolean isActive() {
      return false;
   }

   public boolean isState() {
      return this.state;
   }

   public Potion withCoordOffset(double offset) {
      this.coordOffset = offset;
      return this;
   }

   public int getIndexOffset1() {
      return this.indexOffset1;
   }

   public int getIndexOffset3() {
      return this.indexOffset3;
   }

   public void applyEntity(Entity entity, Entity entity2, EntityLivingBase livingEntity, int count, double offset) {
      if ((this.indexOffset3 != potion21.indexOffset3 || livingEntity.isActive21()) && (this.indexOffset3 != potion10.indexOffset3 || !livingEntity.isActive21())) {
         if (this.indexOffset3 == potion10.indexOffset3 && !livingEntity.isActive21() || this.indexOffset3 == potion21.indexOffset3 && livingEntity.isActive21()) {
            int count2 = (int)(offset * (double)(6 << count) + 0.5);
            if (entity == null) {
               livingEntity.matchesDamageSource(DamageSource.damageSource5, (float)count2);
            } else {
               livingEntity.matchesDamageSource(DamageSource.resolveDamageSource(entity, entity2), (float)count2);
            }
         }
      } else {
         int count3 = (int)(offset * (double)(4 << count) + 0.5);
         livingEntity.applyFloatByFloat((float)count3);
      }
   }

   public Potion(int count, ResourceLocation location, boolean flag, int count2) {
      this.indexOffset3 = count;
      potionArray[count] = this;
      map.put(location, this);
      this.state2 = flag;
      if (flag) {
         this.coordOffset = 0.5;
      } else {
         this.coordOffset = 1.0;
      }

      this.indexOffset1 = count2;
   }
}
