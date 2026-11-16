package net.minecraft.entity;

import cc.baritone.utils.AStarSystem;
import cc.baritone.utils.SettingsTxtManager;
import client.Client;
import client.ClientGlobals;
import client.event.AuxiliaryListener;
import client.event.EventTargetEntityCallback;
import client.event.ExtendedListener;
import client.module.Module;
import client.rotation.Rotation;
import client.rotation.RotationManager;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.DisablerUndefinedType;
import client.util.AdvancedMapEntry;
import client.util.BacktrackRecord;
import client.util.BezierReader;
import client.util.CancellableEventCallbackAdapter;
import client.util.ClientManagerController;
import client.util.IBlockState;
import client.util.InternalClientManagerRegistry;
import client.util.InvalidOperationManager;
import client.util.KeybindAnimation;
import client.util.LISTManager;
import client.util.LivingEntityFilter;
import client.util.LvlManager;
import client.util.PrimaryPredicateAdapter;
import client.util.SecondaryClientManager;
import client.util.SecondaryLoggerEntry;
import client.util.SetEntry;
import client.util.SpecialAbstractEventAdapter;
import client.util.StepManager;
import client.util.Vec3Entry;
import client.via.ViaLoadingBase;
import client.via.ViaProtocolHelper;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SecondaryItemCallback;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.CombatTracker;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityLivingBase extends Entity {
   public static final UUID uUID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
   public static final InvalidOperationManager invalidOperationManager = new InvalidOperationManager(uUID, "Sprinting speed boost", 0.3F, 2).withEnabled(false);
   public final BacktrackRecord backtrackRecord2 = new BacktrackRecord(23);
   public final BacktrackRecord backtrackRecord3 = new BacktrackRecord(23);
   public final BacktrackRecord backtrackRecord4 = new BacktrackRecord(23);
   public final BacktrackRecord backtrackRecord5 = new BacktrackRecord(23);
   public final HashMap entryMap = new HashMap();
   public final CombatTracker genericManager = new CombatTracker(this);
   public final Map map = Maps.newHashMap();
   public final ItemStack[] itemStackArray = new ItemStack[5];
   public boolean state2;
   public int indexOffset20;
   public int count16;
   public int hurtTime;
   public int indexOffset15;
   public int count20;
   public int maxHurtTime = 0;
   public int count15 = 0;
   public int count12;
   public float factor17;
   public int indexOffset29;
   public float factor22;
   public float factor7;
   public float factor9;
   public float factor11;
   public float factor14;
   public int count14 = 20;
   public float factor18;
   public float factor8;
   public float factor15;
   public float factor10;
   public float scaleFactor8;
   public float factor12;
   public float scaleFactor12;
   public float scaleFactor22;
   public float scaleFactor21;
   public float scaleFactor;
   public float scaleFactor10 = 0.02F;
   public boolean state31;
   public float scaleFactor24;
   public float scaleFactor3;
   public int count22;
   public int indexOffset23;
   public double value6;
   public double value8;
   public double value15;
   public double coordOffset5 = 0.0;
   public double coordOffset20 = 0.0;
   public double coordOffset2 = 0.0;
   public double value12 = 0.0;
   public double value20 = 0.0;
   public double value10 = 0.0;
   public double value16 = 0.0;
   public double value7 = 0.0;
   public double value17 = 0.0;
   public double value9;
   public double value11;
   public int indexOffset11 = 0;
   public int indexOffset17 = 0;
   public int indexOffset1 = 0;
   public int indexOffset19 = 0;
   public int indexOffset16;
   public KeybindAnimation keybindAnimation = new KeybindAnimation(0.0F, 0.0F, 0.0F, 0.0F);
   public int count13 = 0;
   public int count18 = 0;
   public int count19 = 0;
   public double value13 = 0.0;
   public double value14 = 0.0;
   public double value5 = 0.0;
   public Vec3 positionVec3;
   public Vec3 positionVec;
   public AxisAlignedBB axisAlignedBB2 = null;
   public Vec3 positionVec4;
   public Vec3 positionVec5;
   public EntityPlayer entityPlayer;
   public int count11;
   public boolean enabled17;
   public int indexOffset14;
   public float factor6;
   public float factor5;
   public float factor25;
   public float factor20;
   public float factor21;
   public int count17;
   public float factor13;
   public float factor16;
   public AdvancedMapEntry advancedMapEntry;
   public boolean enabled14 = true;
   public EntityLivingBase targetEntity;
   public int deathTime;
   public EntityLivingBase targetEntity2;
   public int indexOffset7;
   public float scaleFactor6;
   public int indexOffset18;
   public float scaleFactor13;
   public AStarSystem aStarSystem2;
   public AStarSystem aStarSystem;

   public void updateState40() {
      Iterator iterator = this.map.keySet().iterator();

      while (iterator.hasNext()) {
         Integer count = (Integer)iterator.next();
         PotionEffect potionEffect = (PotionEffect)this.map.get(count);
         if (!this.worldObj.state) {
            iterator.remove();
            this.applyPotionEffect2(potionEffect);
         }
      }
   }

   public void applyEntity9(Entity entity) {
      entity.applyEntity(this);
   }

   public Collection getCollection() {
      return this.map.values();
   }

   @Override
   public void setScaleFactor12(float scaleFactor2) {
      this.scaleFactor12 = scaleFactor2;
   }

   public float computeFactorByDamageSource(DamageSource damageSource, float factor) {
      float factor2 = factor;
      if (!damageSource.canExecute()) {
         int count = 25 - this.getCount4();
         float factor3 = factor * (float)count;
         this.applyFloat2(factor);
         factor2 = factor3 / 25.0F;
      }

      return factor2;
   }

   public SecondaryClientManager getSecondaryClientManager() {
      return this.worldObj.getThePlayerNameManager().getScorePlayerTeam(this.getEntityUniqueID().toString());
   }

   public float getFactor() {
      return 1.0F;
   }

   public boolean matchesSecondaryClientManager(SecondaryClientManager secondaryClientManager) {
      return this.getSecondaryClientManager() != null && this.getSecondaryClientManager().matchesSecondaryClientManager(secondaryClientManager);
   }

   @Override
   public void updateState19() {
      this.matchesDamageSource(DamageSource.damageSource10, 4.0F);
   }

   public void applyEntity12(Entity entity) {
      double posX = entity.posX;
      double offset = entity.getBoundingBox2().minY + (double)entity.height;
      double posZ = entity.posZ;

      for (int index = -1; index <= 1; index++) {
         for (int index2 = -1; index2 < 1; index2++) {
            if (index != 0 || index2 != 0) {
               int count = (int)(this.posX + (double)index);
               int count2 = (int)(this.posZ + (double)index2);
               AxisAlignedBB boundingBox = this.getBoundingBox2().expand((double)index, 1.0, (double)index2);
               if (this.worldObj.resolveList(boundingBox).isEmpty()) {
                  if (World.matchesIBlockAccess(this.worldObj, count, MathHelper.ceiling_double_int(this.posY), count2)) {
                     this.applyDoubleByDouble(this.posX + (double)index, this.posY + 1.0, this.posZ + (double)index2);
                     return;
                  }

                  if (World.matchesIBlockAccess(this.worldObj, count, MathHelper.ceiling_double_int(this.posY - 1.0), count2)
                     || this.worldObj.getBlockState(new BlockPos(count, (int)this.posY - 1, count2)).getBlock().getMaterial() == Material.coreItemEntry1) {
                     posX = this.posX + (double)index;
                     offset = this.posY + 1.0;
                     posZ = this.posZ + (double)index2;
                  }
               }
            }
         }
      }

      this.applyDoubleByDouble(posX, offset, posZ);
   }

   public CombatTracker getGenericManager() {
      return this.genericManager;
   }

   public String getText9() {
      return "game.neutral.die";
   }

   public DisablerUndefinedType getUNDEAD() {
      return DisablerUndefinedType.UNDEFINED;
   }

   public void applyFlagByFlag(boolean flag, int count) {
   }

   public EntityLivingBase getEntityLivingBase() {
      return (EntityLivingBase)(this.genericManager.getEntityLivingBase() != null
         ? this.genericManager.getEntityLivingBase()
         : (this.entityPlayer != null ? this.entityPlayer : (this.targetEntity != null ? this.targetEntity : null)));
   }

   public final void applyIndex6(int count) {
      this.dataWatcher.applyTargetByTarget(9, (byte)count);
   }

   public void setState31(boolean state) {
      this.state31 = state;
   }

   public void applyFloat(float factor) {
      this.dataWatcher.applyTargetByTarget(6, MathHelper.clamp_float(factor, 0.0F, this.getFloat()));
   }

   @Override
   public void setEntity8(Entity entity) {
      if (this.ridingEntity != null && entity == null) {
         if (!this.worldObj.state) {
            this.applyEntity12(this.ridingEntity);
         }

         if (this.ridingEntity != null) {
            this.ridingEntity.entity = null;
         }

         this.ridingEntity = null;
      } else {
         super.setEntity8(entity);
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      this.applyFloat3(tagCompound.computeFactor("AbsorptionAmount"));
      if (tagCompound.matchesText("Attributes", 9) && this.worldObj != null && !this.worldObj.state) {
         SharedMonsterAttributes.applyAdvancedMapEntry(this.getAdvancedMapEntry(), tagCompound.getExtendedLoggerEntry("Attributes", 10));
      }

      if (tagCompound.matchesText("ActiveEffects", 9)) {
         NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("ActiveEffects", 10);

         for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
            PotionEffect potionEffect = PotionEffect.resolvePotionEffect(extendedLoggerEntry.getNBTTagCompound(index));
            if (potionEffect != null) {
               this.map.put(potionEffect.getIndexOffset3(), potionEffect);
            }
         }
      }

      if (tagCompound.matchesText("HealF", 99)) {
         this.applyFloat(tagCompound.computeFactor("HealF"));
      } else {
         LISTManager lISTManager = tagCompound.get("Health");
         if (lISTManager == null) {
            this.applyFloat(this.getFloat());
         } else if (lISTManager.getByteCode() == 5) {
            this.applyFloat(((InternalClientManagerRegistry)lISTManager).getFactor());
         } else if (lISTManager.getByteCode() == 2) {
            this.applyFloat((float)((ClientManagerController)lISTManager).getShortCode());
         }
      }

      this.hurtTime = tagCompound.processValue("HurtTime");
      this.indexOffset29 = tagCompound.processValue("DeathTime");
      this.deathTime = tagCompound.computeCount("HurtByTimestamp");
   }

   public float computeFactorByFloat(float factor) {
      float factor2 = this.factor7 - this.factor22;
      if (factor2 < 0.0F) {
         factor2++;
      }

      return this.factor22 + factor2 * factor;
   }

   @Override
   public void updateState21() {
      super.updateState21();
      this.factor6 = this.factor5;
      this.factor5 = 0.0F;
      this.fallDistance = 0.0F;
      this.factor2 = 0.0F;
   }

   public boolean checkPotion(Potion potion) {
      return this.map.containsKey(potion.indexOffset3);
   }

   public int nextInt(EntityPlayer player) {
      return 0;
   }

   public abstract ItemStack getItemStack();

   public int getCount4() {
      int count = 0;

      for (ItemStack stack : this.getItemStackArray2()) {
         if (stack != null && stack.getItem() instanceof ItemGoldenBoots) {
            int indexOffset = ((ItemGoldenBoots)stack.getItem()).indexOffset4;
            count += indexOffset;
         }
      }

      return count;
   }

   public void applyFloat3(float factor) {
      float factor2 = factor;
      if (factor < 0.0F) {
         factor2 = 0.0F;
      }

      this.scaleFactor13 = factor2;
   }

   public void applyPotionEffect(PotionEffect potionEffect) {
      this.enabled14 = true;
      if (!this.worldObj.state) {
         Potion.potionArray[potionEffect.getIndexOffset3()].applyEntityLivingBase(this, this.getAdvancedMapEntry(), potionEffect.getIndexOffset2());
      }
   }

   @Override
   public boolean canExecute() {
      return !this.isDead && this.getFloat2() > 0.0F;
   }

   public void applyFloat2(float factor) {
   }

   public final float getFloat() {
      return (float)this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).getDouble();
   }

   public AdvancedMapEntry getAdvancedMapEntry() {
      if (this.advancedMapEntry == null) {
         this.advancedMapEntry = new SetEntry();
      }

      return this.advancedMapEntry;
   }

   public void applyIndex5(int count) {
      PotionEffect potionEffect = (PotionEffect)this.map.remove(count);
      if (potionEffect != null) {
         this.applyPotionEffect2(potionEffect);
      }
   }

   public boolean matchesEntity4(Entity entity) {
      this.applyEntity14(entity);
      return false;
   }

   private Optional getOptional() {
      return this instanceof EntityPlayerSP
         ? Optional.ofNullable(SettingsTxtManager.getExtraListener().resolveActiveListenerByEntityPlayerSP((EntityPlayerSP)this))
         : Optional.empty();
   }

   @Override
   public abstract void applyItemStack(int count, ItemStack stack);

   public void applyEntity2(Entity entity, int count) {
      if (!entity.isDead && !this.worldObj.state) {
         SecondaryLoggerEntry secondaryLoggerEntry = ((WorldServer)this.worldObj).getSecondaryLoggerEntry();
         if (entity instanceof EntityItem) {
            secondaryLoggerEntry.applyEntity3(entity, new S0DPacketCollectItem(entity.getEntityId(), this.getEntityId()));
         }

         if (entity instanceof EntityArrow) {
            secondaryLoggerEntry.applyEntity3(entity, new S0DPacketCollectItem(entity.getEntityId(), this.getEntityId()));
         }

         if (entity instanceof EntityXporb) {
            secondaryLoggerEntry.applyEntity3(entity, new S0DPacketCollectItem(entity.getEntityId(), this.getEntityId()));
         }
      }
   }

   public void updateState27() {
      this.getAdvancedMapEntry().resolveAuxiliaryListenerByExtendedListener(SharedMonsterAttributes.extendedListener5);
      this.getAdvancedMapEntry().resolveAuxiliaryListenerByExtendedListener(SharedMonsterAttributes.extendedListener2);
      this.getAdvancedMapEntry().resolveAuxiliaryListenerByExtendedListener(SharedMonsterAttributes.extendedListener3);
   }

   public void updateState2() {
   }

   public void updateState31() {
      this.enabled14 = true;
   }

   public boolean isActive22() {
      return false;
   }

   public boolean isActive18() {
      return !this.worldObj.state;
   }

   public void updateState37() {
      int intItem = this.getInt3();
      if (this.state2) {
         this.indexOffset20++;
         if (this.indexOffset20 >= intItem) {
            this.indexOffset20 = 0;
            this.state2 = false;
         }
      } else {
         this.indexOffset20 = 0;
      }

      this.factor7 = (float)this.indexOffset20 / (float)intItem;
   }

   public void applyIndex(int count, int count2, int count3) {
      this.coordOffset5 = this.value12 = this.value16 = (double)count / 32.0;
      this.coordOffset20 = this.value20 = this.value7 = (double)count2 / 32.0;
      this.coordOffset2 = this.value10 = this.value17 = (double)count3 / 32.0;
      this.count13 = count;
      this.count18 = count2;
      this.count19 = count3;
      this.positionVec3 = new Vec3(this.coordOffset5, this.coordOffset20, this.coordOffset2);
      this.positionVec = new Vec3(this.positionVec3);
      this.positionVec4 = new Vec3(this.positionVec);
      this.positionVec5 = new Vec3(this.positionVec5);
   }

   @Override
   public void setScaleFactor8(float scaleFactor2) {
      this.scaleFactor8 = scaleFactor2;
   }

   @Override
   public float getScaleFactor12() {
      return this.scaleFactor12;
   }

   public void applyEntity14(Entity entity) {
      if (entity instanceof EntityLivingBase) {
         this.targetEntity2 = (EntityLivingBase)entity;
      } else {
         this.targetEntity2 = null;
      }

      this.indexOffset7 = this.ticksExisted;
   }

   public boolean matchesPotionEffect(PotionEffect potionEffect) {
      if (this.getUNDEAD() != DisablerUndefinedType.UNDEAD) {
         return true;
      } else {
         int indexOffset = potionEffect.getIndexOffset3();
         return indexOffset != Potion.potion11.indexOffset3 && indexOffset != Potion.potion24.indexOffset3;
      }
   }

   public void applyFlag(boolean flag, int count) {
   }

   public int getInt3() {
      int count = this.checkPotion(Potion.potion28)
         ? 6 - (1 + this.getPotionEffect(Potion.potion28).getIndexOffset2())
         : (this.checkPotion(Potion.potion7) ? 6 + (1 + this.getPotionEffect(Potion.potion7).getIndexOffset2()) * 2 : 6);
      if (((Module)ClientGlobals.moduleManager.blockAnimation).isToggled() && this instanceof EntityPlayerSP) {
         count = (int)((double)count / ClientGlobals.moduleManager.blockAnimation.settingSpeed.getValue());
      }

      return count;
   }

   public Random getRandom() {
      return this.random;
   }

   public int computeCount(int count) {
      int count2 = LvlManager.computeCount(this);
      return count2 > 0 && this.random.nextInt(count2 + 1) > 0 ? count : count - 1;
   }

   @Override
   public void processFloat2(float factor, float factor2) {
      super.processFloat2(factor, factor2);
      PotionEffect potionEffect = this.getPotionEffect(Potion.potion9);
      float factor3 = potionEffect != null ? (float)(potionEffect.getIndexOffset2() + 1) : 0.0F;
      int count = MathHelper.computeCount((factor - 3.0F - factor3) * factor2);
      if (count > 0) {
         this.applyText(this.resolveText(count), 1.0F, 1.0F);
         this.matchesDamageSource(DamageSource.damageSource11, (float)count);
         int count2 = MathHelper.ceiling_double_int(this.posX);
         int count3 = MathHelper.ceiling_double_int(this.posY - 0.2F);
         int count4 = MathHelper.ceiling_double_int(this.posZ);
         Block block = this.worldObj.getBlockState(new BlockPos(count2, count3, count4)).getBlock();
         if (block.getMaterial() != Material.coreItemEntry) {
            StepManager stepManager = block.stepManager20;
            this.applyText(stepManager.getText(), stepManager.getFactor2() * 0.5F, stepManager.getFactor() * 0.75F);
         }
      }
   }

   public int getIndexOffset18() {
      return this.indexOffset18;
   }

   public void applyItemStackByItemStack(ItemStack stack) {
      this.applyText("random.break", 0.8F, 0.8F + this.worldObj.random.nextFloat() * 0.4F);

      for (int index = 0; index < 5; index++) {
         Vec3 vec = new Vec3(((double)this.random.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0)
            .resolveVecByFloat(-this.rotationPitch * (float) Math.PI / 180.0F)
            .resolveVec3(-this.rotationYaw * (float) Math.PI / 180.0F);
         double offset = (double)(-this.random.nextFloat()) * 0.6 - 0.3;
         Vec3 vec3 = new Vec3(((double)this.random.nextFloat() - 0.5) * 0.3, offset, 0.6)
            .resolveVecByFloat(-this.rotationPitch * (float) Math.PI / 180.0F)
            .resolveVec3(-this.rotationYaw * (float) Math.PI / 180.0F)
            .getVec312(this.posX, this.posY + (double)this.getHeight2(), this.posZ);
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.ITEM_CRACK,
               vec3.xCoord,
               vec3.yCoord,
               vec3.zCoord,
               vec.xCoord,
               vec.yCoord + 0.05,
               vec.zCoord,
               new int[]{Item.getIndex(stack.getItem())}
            );
      }
   }

   public boolean checkEntity4(Entity entity) {
      return this.worldObj
            .getMovingObjectPosition2(this.posX, this.posY + (double)this.getHeight2(), this.posZ, entity.posX, entity.posY + (double)entity.getHeight2(), entity.posZ)
         == null;
   }

   public void updateState41() {
      if (((Module)ClientGlobals.moduleManager.jesus).isToggled() && ClientGlobals.moduleManager.jesus.settingMode.getSelected().equals("1.13")) {
         this.motionY += 0.04F;
      } else {
         this.motionY += 0.04F;
      }
   }

   public boolean checkIndexByIndex(int count) {
      return this.map.containsKey(count);
   }

   @Override
   public abstract ItemStack[] getItemStackArray2();

   @Override
   public void processByte(byte byteValue) {
      if (byteValue == 2) {
         this.factor11 = 1.5F;
         this.hurtResistantTime = this.count14;
         this.hurtTime = this.count12 = 10;
         this.maxHurtTime = this.count12 = 10;
         this.count15 = this.count12 = 10;
         EventTargetEntityCallback.applyEntityLivingBase(this);
         this.factor17 = 0.0F;
         if (this.getText10() != null) {
            this.applyText(this.getText10(), this.getFactor(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
         }

         this.matchesDamageSource(DamageSource.damageSource12, 0.0F);
      } else if (byteValue == 3) {
         if (this.getText9() != null) {
            this.applyText(this.getText9(), this.getFactor(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
         }

         this.applyFloat(0.0F);
         this.applyDamageSource(DamageSource.damageSource12);
      } else {
         super.processByte(byteValue);
      }
   }

   public void applyPotionEffectByPotionEffect(PotionEffect potionEffect) {
      if (this.matchesPotionEffect(potionEffect)) {
         if (this.map.containsKey(potionEffect.getIndexOffset3())) {
            ((PotionEffect)this.map.get(potionEffect.getIndexOffset3())).applyPotionEffect(potionEffect);
            this.applyPotionEffect((PotionEffect)this.map.get(potionEffect.getIndexOffset3()), true);
         } else {
            this.map.put(potionEffect.getIndexOffset3(), potionEffect);
            this.applyPotionEffect(potionEffect);
         }
      }
   }

   public void setIndexOffset18(int indexOffset) {
      this.indexOffset18 = indexOffset;
   }

   @Override
   public void setState3(boolean state) {
      super.setState3(state);
      AuxiliaryListener auxiliaryListener = this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3);
      if (auxiliaryListener.get(uUID) != null) {
         auxiliaryListener.applyInvalidOperationManagerByInvalidOperation(invalidOperationManager);
      }

      if (state) {
         auxiliaryListener.applyInvalidOperationManager(invalidOperationManager);
      }
   }

   @Override
   public boolean isActive9() {
      return !this.isDead;
   }

   public float getFloat6() {
      return 0.42F;
   }

   public void applyEntityByEntity(Entity entity, float factor, double offset, double offset2) {
      if (this.random.nextDouble() >= this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener2).getDouble()) {
         this.enabled4 = true;
         float factor2 = MathHelper.sqrt_double(offset * offset + offset2 * offset2);
         float factor3 = 0.4F;
         this.motionX /= 2.0;
         this.motionY /= 2.0;
         this.motionZ /= 2.0;
         this.motionX -= offset / (double)factor2 * (double)factor3;
         this.motionY += (double)factor3;
         this.motionZ -= offset2 / (double)factor2 * (double)factor3;
         if (this.motionY > 0.4F) {
            this.motionY = 0.4F;
         }
      }
   }

   public float getFactorAsFloat() {
      return this.isActive16()
         ? (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.5F
         : (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F;
   }

   public void applyPotionEffect2(PotionEffect potionEffect) {
      this.enabled14 = true;
      if (!this.worldObj.state) {
         Potion.potionArray[potionEffect.getIndexOffset3()].applyEntityLivingBaseByEntityLivingBase(this, this.getAdvancedMapEntry(), potionEffect.getIndexOffset2());
      }
   }

   public boolean isActive17() {
      return false;
   }

   public abstract ItemStack resolveItemStack(int count);

   public float computeFactor(DamageSource damageSource, float factor) {
      float factor2 = factor;
      if (damageSource.canExecute2()) {
         return factor;
      } else {
         if (this.checkPotion(Potion.potion12) && damageSource != DamageSource.damageSource10) {
            int count = (this.getPotionEffect(Potion.potion12).getIndexOffset2() + 1) * 5;
            int count2 = 25 - count;
            factor2 = factor * (float)count2 / 25.0F;
         }

         if (factor2 <= 0.0F) {
            return 0.0F;
         } else {
            int count3 = LvlManager.computeCount(this.getItemStackArray2(), damageSource);
            if (count3 > 20) {
               count3 = 20;
            }

            if (count3 > 0 && count3 <= 20) {
               int count4 = 25 - count3;
               factor2 = factor2 * (float)count4 / 25.0F;
            }

            return factor2;
         }
      }
   }

   public EntityLivingBase getTargetEntity() {
      return this.targetEntity;
   }

   public AuxiliaryListener getAuxiliaryListener(ExtendedListener extendedListener) {
      return this.getAdvancedMapEntry().get(extendedListener);
   }

   public float getScaleFactor6() {
      return this.scaleFactor6;
   }

   public boolean matchesEntityLivingBase(EntityLivingBase livingEntity) {
      return this.matchesSecondaryClientManager(livingEntity.getSecondaryClientManager());
   }

   public void applyDamageSource(DamageSource damageSource) {
      Entity entity = damageSource.getEntity();
      EntityLivingBase entityLivingBase = this.getEntityLivingBase();
      if (this.count17 >= 0 && entityLivingBase != null) {
         entityLivingBase.applyEntityByEntity(this, this.count17);
      }

      if (entity != null) {
         entity.applyEntityLivingBase(this);
      }

      this.enabled17 = true;
      this.getGenericManager().updateState();
      if (!this.worldObj.state) {
         int count = 0;
         if (entity instanceof EntityPlayer) {
            count = LvlManager.computeCount7((EntityLivingBase)entity);
         }

         if (this.isActive19() && this.worldObj.getDoFireTickManager().matchesText("doMobLoot")) {
            this.applyFlag(this.count11 > 0, count);
            this.applyFlagByFlag(this.count11 > 0, count);
            if (this.count11 > 0 && this.random.nextFloat() < 0.025F + (float)count * 0.01F) {
               this.updateState2();
            }
         }
      }

      this.worldObj.applyEntity(this, (byte)3);
   }

   public boolean isActive16() {
      return false;
   }

   private float computeFactor2(float factor) {
      return ((Module)Client.getClient().getModuleManager().baritone).isToggled()
            && this instanceof EntityPlayerSP
            && SettingsTxtManager.getExtraListener().resolveActiveListenerByEntityPlayerSP((EntityPlayerSP)this) != null
         ? this.aStarSystem2.getFactor()
         : factor;
   }

   public float computeFactor(float factor, float factor2) {
      float factor3 = factor2;
      float factor4 = MathHelper.sqrt_float(factor - this.scaleFactor8);
      this.scaleFactor8 += factor4 * 0.3F;
      float factor19 = MathHelper.sqrt_float(this.rotationYaw - this.scaleFactor8);
      boolean flag = factor19 < -90.0F || factor19 >= 90.0F;
      if (factor19 < -75.0F) {
         factor19 = -75.0F;
      }

      if (factor19 >= 75.0F) {
         factor19 = 75.0F;
      }

      this.scaleFactor8 = this.rotationYaw - factor19;
      if (factor19 * factor19 > 2500.0F) {
         this.scaleFactor8 += factor19 * 0.2F;
      }

      if (flag) {
         factor3 = factor2 * -1.0F;
      }

      return factor3;
   }

   @Override
   public void applyBlock(double offset, boolean flag, Block block, BlockPos blockPos) {
      if (!this.isInWater()) {
         this.isActive2();
      }

      if (!this.worldObj.state && this.fallDistance > 3.0F && flag) {
         IBlockState blockState = this.worldObj.getBlockState(blockPos);
         Block block2 = blockState.getBlock();
         float factor = (float)MathHelper.computeCount(this.fallDistance - 3.0F);
         if (block2.getMaterial() != Material.coreItemEntry) {
            double offset2 = (double)Math.min(0.2F + factor / 15.0F, 10.0F);
            if (offset2 > 2.5) {
               offset2 = 2.5;
            }

            int count = (int)(150.0 * offset2);
            ((WorldServer)this.worldObj)
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.BLOCK_DUST,
                  this.posX,
                  this.posY,
                  this.posZ,
                  count,
                  0.0,
                  0.0,
                  0.0,
                  0.15F,
                  new int[]{Block.processAction5(blockState)}
               );
         }
      }

      super.applyBlock(offset, flag, block, blockPos);
   }

   @Override
   public void updateState15() {
      this.matchesDamageSource(DamageSource.damageSource10, Float.MAX_VALUE);
   }

   public float getScaleFactor13() {
      return this.scaleFactor13;
   }

   public void applyDamageSource(DamageSource damageSource, float factor) {
      if (!this.matchesDamageSource(damageSource)) {
         float factor2 = this.computeFactorByDamageSource(damageSource, factor);
         factor2 = this.computeFactor(damageSource, factor2);
         float factor3 = Math.max(factor2 - this.getScaleFactor13(), 0.0F);
         this.applyFloat3(this.getScaleFactor13() - (factor2 - factor3));
         if (factor3 != 0.0F) {
            float floatItem = this.getFloat2();
            this.applyFloat(floatItem - factor3);
            this.getGenericManager().applyDamageSource(damageSource, floatItem, factor3);
            this.applyFloat3(this.getScaleFactor13() - factor3);
         }
      }
   }

   public String getText10() {
      return "game.neutral.hurt";
   }

   @Override
   public Vec3 getVec36(float factor) {
      if (factor == 1.0F) {
         return this.getVec34(this.rotationPitch, this.scaleFactor12);
      } else {
         float factor2 = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * factor;
         float factor3 = this.scaleFactor22 + (this.scaleFactor12 - this.scaleFactor22) * factor;
         return this.getVec34(factor2, factor3);
      }
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      float factor2 = factor;
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else if (this.worldObj.state) {
         return false;
      } else {
         this.indexOffset14 = 0;
         if (this.getFloat2() <= 0.0F) {
            return false;
         } else if (damageSource.isEnabled6() && this.checkPotion(Potion.potion13)) {
            return false;
         } else {
            if ((damageSource == DamageSource.damageSource14 || damageSource == DamageSource.damageSource6) && this.resolveItemStack(4) != null) {
               this.resolveItemStack(4).applyEntityLivingBaseByEntityLivingBase((int)(factor * 4.0F + this.random.nextFloat() * factor * 2.0F), this);
               factor2 = factor * 0.75F;
            }

            this.factor11 = 1.5F;
            boolean flag = true;
            if ((float)this.hurtResistantTime > (float)this.count14 / 2.0F) {
               if (factor2 <= this.factor13) {
                  return false;
               }

               this.applyDamageSource(damageSource, factor2 - this.factor13);
               this.factor13 = factor2;
               flag = false;
            } else {
               this.factor13 = factor2;
               this.hurtResistantTime = this.count14;
               this.applyDamageSource(damageSource, factor2);
               this.hurtTime = this.count12 = 10;
               this.maxHurtTime = this.count12 = 10;
               this.count15 = this.count12 = 10;
               EventTargetEntityCallback.applyEntityLivingBase(this);
            }

            this.factor17 = 0.0F;
            Entity entity = damageSource.getEntity();
            if (entity != null) {
               if (entity instanceof EntityLivingBase) {
                  this.applyEntityLivingBase2((EntityLivingBase)entity);
               }

               if (entity instanceof EntityPlayer) {
                  this.count11 = 100;
                  this.entityPlayer = (EntityPlayer)entity;
               } else if (entity instanceof EntityWolf && ((EntityWolf)entity).isActive32()) {
                  this.count11 = 100;
                  this.entityPlayer = null;
               }
            }

            if (flag) {
               this.worldObj.applyEntity(this, (byte)2);
               if (damageSource != DamageSource.damageSource) {
                  this.updateState8();
               }

               if (entity != null) {
                  double offset = entity.posX - this.posX;

                  double offset2;
                  for (offset2 = entity.posZ - this.posZ; offset * offset + offset2 * offset2 < 1.0E-4; offset2 = (Math.random() - Math.random()) * 0.01) {
                     offset = (Math.random() - Math.random()) * 0.01;
                  }

                  this.factor17 = (float)(MathHelper.getDouble6(offset2, offset) * 180.0 / Math.PI - (double)this.rotationYaw);
                  this.applyEntityByEntity(entity, factor2, offset, offset2);
               } else {
                  this.factor17 = (float)((int)(Math.random() * 2.0) * 180);
               }
            }

            if (this.getFloat2() <= 0.0F) {
               String text = this.getText9();
               if (flag && text != null) {
                  this.applyText(text, this.getFactor(), this.getFactorAsFloat());
               }

               this.applyDamageSource(damageSource);
            } else {
               String text2 = this.getText10();
               if (flag && text2 != null) {
                  this.applyText(text2, this.getFactor(), this.getFactorAsFloat());
               }
            }

            return true;
         }
      }
   }

   @Override
   public void applyDouble(double posX, double posY, double posZ, float factor, float factor2, int count, boolean flag) {
      this.value6 = posX;
      this.value8 = posY;
      this.value15 = posZ;
      this.value9 = (double)factor;
      this.value11 = (double)factor2;
      this.count22 = count;
   }

   public int getIndexOffset5() {
      return this.deathTime;
   }

   public EntityLivingBase getTargetEntity2() {
      return this.targetEntity2;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("HealF", this.getFloat2());
      tagCompound.put("Health", (short)((int)Math.ceil((double)this.getFloat2())));
      tagCompound.put("HurtTime", (short)this.hurtTime);
      tagCompound.put("HurtByTimestamp", this.deathTime);
      tagCompound.put("DeathTime", (short)this.indexOffset29);
      tagCompound.put("AbsorptionAmount", this.getScaleFactor13());

      for (ItemStack stack : this.getItemStackArray2()) {
         if (stack != null) {
            this.advancedMapEntry.applyMultimap(stack.getMultimap());
         }
      }

      tagCompound.applyText("Attributes", SharedMonsterAttributes.resolveNBTTagList(this.getAdvancedMapEntry()));

      for (ItemStack stack2 : this.getItemStackArray2()) {
         if (stack2 != null) {
            this.advancedMapEntry.applyMultimapByMultimap(stack2.getMultimap());
         }
      }

      if (!this.map.isEmpty()) {
         NBTTagList nBTTagList = new NBTTagList();

         for (PotionEffect potionEffect : (Iterable<PotionEffect>)(Iterable<?>)this.map.values()) {
            nBTTagList.applyLISTManager(potionEffect.resolveNBTTagCompound(new NBTTagCompound()));
         }

         tagCompound.applyText("ActiveEffects", nBTTagList);
      }
   }

   public abstract ItemStack getItemStack3(int count);

   public void applyFloatByFloat(float factor) {
      float floatItem = this.getFloat2();
      if (floatItem > 0.0F) {
         this.applyFloat(floatItem + factor);
      }
   }

   public void applyFloat2(float factor, float factor2) {
      if (this.isActive18()) {
         this.indexOffset16++;
         if (!this.isInWater() || this instanceof EntityPlayer && ((EntityPlayer)this).invulnerableManager.state) {
            if (!this.isWet() || this instanceof EntityPlayer && ((EntityPlayer)this).invulnerableManager.state) {
               float factor3 = 0.91F;
               if (this.onGround) {
                  factor3 = this.worldObj
                        .getBlockState(
                           new BlockPos(
                              MathHelper.ceiling_double_int(this.posX),
                              MathHelper.ceiling_double_int(this.getBoundingBox2().minY) - 1,
                              MathHelper.ceiling_double_int(this.posZ)
                           )
                        )
                        .getBlock()
                        .scaleFactor
                     * 0.91F;
               }

               float factor4 = 0.16277136F / (factor3 * factor3 * factor3);
               float factor19;
               if (this.onGround) {
                  factor19 = this.getScaleFactor6() * factor4;
               } else {
                  factor19 = this.scaleFactor10;
               }

               this.applyFloat(factor, factor2, factor19);
               float factor23 = 0.91F;
               if (this.onGround) {
                  factor23 = this.worldObj
                        .getBlockState(
                           new BlockPos(
                              MathHelper.ceiling_double_int(this.posX),
                              MathHelper.ceiling_double_int(this.getBoundingBox2().minY) - 1,
                              MathHelper.ceiling_double_int(this.posZ)
                           )
                        )
                        .getBlock()
                        .scaleFactor
                     * 0.91F;
               }

               if (this.canExecute9()) {
                  float factor24 = 0.15F;
                  this.motionX = MathHelper.getDouble7(this.motionX, (double)(-factor24), (double)factor24);
                  this.motionZ = MathHelper.getDouble7(this.motionZ, (double)(-factor24), (double)factor24);
                  this.fallDistance = 0.0F;
                  if (this.motionY < -0.15) {
                     this.motionY = -0.15;
                  }

                  if (this.isSprinting() && this instanceof EntityPlayer && this.motionY < 0.0) {
                     this.motionY = 0.0;
                  }
               }

               this.applyDouble(this.motionX, this.motionY, this.motionZ);
               if (this.isCollidedHorizontally && this.canExecute9()) {
                  if (this instanceof EntityPlayerSP) {
                     SpecialAbstractEventAdapter event = SecondaryItemCallback.resolveSpecialAbstractEventAdapter(0.2);
                     this.motionY = event.getValue();
                  } else {
                     this.motionY = 0.2;
                  }
               }

               if (this.worldObj.state
                  && (
                     !this.worldObj.checkBlockPos12(new BlockPos((int)this.posX, 0, (int)this.posZ))
                        || !this.worldObj.getLoggerEntry(new BlockPos((int)this.posX, 0, (int)this.posZ)).canExecute2()
                  )) {
                  if (this.posY > 0.0) {
                     this.motionY = -0.1;
                  } else {
                     this.motionY = 0.0;
                  }
               } else {
                  this.motionY -= 0.08;
               }

               this.motionY *= 0.98F;
               this.motionX *= (double)factor23;
               this.motionZ *= (double)factor23;
            } else {
               double posY = this.posY;
               this.applyFloat(factor, factor2, 0.02F);
               this.applyDouble(this.motionX, this.motionY, this.motionZ);
               this.motionX *= 0.5;
               this.motionY *= 0.5;
               this.motionZ *= 0.5;
               this.motionY -= 0.02;
               if (this.isCollidedHorizontally && this.checkDoubleByDouble(this.motionX, this.motionY + 0.6F - this.posY + posY, this.motionZ)) {
                  this.motionY = 0.3F;
               }
            }
         } else if (this instanceof EntityPlayerSP) {
            if (!CancellableEventCallbackAdapter.resolveCancellableEventAdapter(factor2, factor).isCancelled()) {
               double posY2 = this.posY;
               float factor26 = 0.8F;
               float factor27 = 0.02F;
               float factor28 = (float)LvlManager.computeCount2(this);
               if (factor28 > 3.0F) {
                  factor28 = 3.0F;
               }

               if (!this.onGround) {
                  factor28 *= 0.5F;
               }

               if (factor28 > 0.0F) {
                  factor26 += (0.54600006F - factor26) * factor28 / 3.0F;
                  factor27 += (this.getScaleFactor6() - factor27) * factor28 / 3.0F;
               }

               this.applyFloat(factor, factor2, factor27);
               this.applyDouble(this.motionX, this.motionY, this.motionZ);
               this.motionX *= (double)factor26;
               this.motionY *= 0.8F;
               this.motionZ *= (double)factor26;
               this.motionY -= 0.02;
               if (this.isCollidedHorizontally && this.checkDoubleByDouble(this.motionX, this.motionY + 0.6F - this.posY + posY2, this.motionZ)) {
                  this.motionY = 0.3F;
               }
            }
         } else {
            double posY3 = this.posY;
            float factor29 = 0.8F;
            float factor30 = 0.02F;
            float factor31 = (float)LvlManager.computeCount2(this);
            if (factor31 > 3.0F) {
               factor31 = 3.0F;
            }

            if (!this.onGround) {
               factor31 *= 0.5F;
            }

            if (factor31 > 0.0F) {
               factor29 += (0.54600006F - factor29) * factor31 / 3.0F;
               factor30 += (this.getScaleFactor6() - factor30) * factor31 / 3.0F;
            }

            this.applyFloat(factor, factor2, factor30);
            this.applyDouble(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)factor29;
            this.motionY *= 0.8F;
            this.motionZ *= (double)factor29;
            this.motionY -= 0.02;
            if (this.isCollidedHorizontally && this.checkDoubleByDouble(this.motionX, this.motionY + 0.6F - this.posY + posY3, this.motionZ)) {
               this.motionY = 0.3F;
            }
         }
      }

      this.factor9 = this.factor11;
      double offset = this.posX - this.prevPosX;
      double offset2 = this.posZ - this.prevPosZ;
      float factor32 = MathHelper.sqrt_double(offset * offset + offset2 * offset2) * 4.0F;
      if (factor32 > 1.0F) {
         factor32 = 1.0F;
      }

      this.factor11 = this.factor11 + (factor32 - this.factor11) * 0.4F;
      this.factor14 = this.factor14 + this.factor11;
   }

   public boolean checkCondition(double posX, double posY, double posZ) {
      return this.worldObj.getMovingObjectPosition2(this.posX, this.posY + (double)this.getHeight2(), this.posZ, posX, posY, posZ) == null;
   }

   public void applyEntityLivingBase2(EntityLivingBase livingEntity) {
      this.targetEntity = livingEntity;
      this.deathTime = this.ticksExisted;
   }

   @Override
   public Vec3 getVecAsVec3() {
      return this.getVec36(1.0F);
   }

   public PotionEffect getPotionEffect(Potion potion) {
      return (PotionEffect)this.map.get(potion.indexOffset3);
   }

   public final float getFloat2() {
      return this.dataWatcher.computeFactor(6);
   }

   public void applyPotionEffect(PotionEffect potionEffect, boolean flag) {
      this.enabled14 = true;
      if (flag && !this.worldObj.state) {
         Potion.potionArray[potionEffect.getIndexOffset3()].applyEntityLivingBaseByEntityLivingBase(this, this.getAdvancedMapEntry(), potionEffect.getIndexOffset2());
         Potion.potionArray[potionEffect.getIndexOffset3()].applyEntityLivingBase(this, this.getAdvancedMapEntry(), potionEffect.getIndexOffset2());
      }
   }

   public void applyIndex7(int count) {
      this.map.remove(count);
   }

   public boolean isActive21() {
      return this.getUNDEAD() == DisablerUndefinedType.UNDEAD;
   }

   public String resolveText(int count) {
      return count > 4 ? "game.neutral.hurt.fall.big" : "game.neutral.hurt.fall.small";
   }

   public void setScaleFactor6(float scaleFactor2) {
      this.scaleFactor6 = scaleFactor2;
   }

   public void updateState28() {
      if (this.map.isEmpty()) {
         this.updateState30();
         this.setEnabledByFlag(false);
      } else {
         int count = BezierReader.computeCount(this.map.values());
         this.dataWatcher.applyTargetByTarget(8, Byte.valueOf((byte)(BezierReader.matchesCollection(this.map.values()) ? 1 : 0)));
         this.dataWatcher.applyTargetByTarget(7, count);
         this.setEnabledByFlag(this.checkIndexByIndex(Potion.potion30.indexOffset3));
      }
   }

   public int getIndexOffset7() {
      return this.indexOffset7;
   }

   public final int getCount5() {
      return this.dataWatcher.computeByteCode(9);
   }

   public int getIndexOffset14() {
      return this.indexOffset14;
   }

   public void updateState38() {
   }

   public boolean isActive20() {
      return this.getFloat2() <= 0.0F;
   }

   @Override
   public boolean isActive5() {
      return !this.isDead;
   }

   @Override
   public boolean isActive13() {
      return false;
   }

   @Override
   public void updateState16() {
      this.hurtTime = this.count12 = 10;
      this.maxHurtTime = this.count12 = 10;
      this.count15 = this.count12 = 10;
      this.factor17 = 0.0F;
      EventTargetEntityCallback.applyEntityLivingBase(this);
   }

   public boolean isActive19() {
      return !this.isActive16();
   }

   public boolean canExecute9() {
      int count = MathHelper.ceiling_double_int(this.posX);
      int count2 = MathHelper.ceiling_double_int(this.getBoundingBox2().minY);
      int count3 = MathHelper.ceiling_double_int(this.posZ);
      Block block = this.worldObj.getIBlockState(count, count2, count3).getBlock();
      return (block == Blocks.block81 || block == Blocks.block144) && (!(this instanceof EntityPlayer) || !((EntityPlayer)this).isActive23());
   }

   @Override
   public void updateState10() {
      this.scaleFactor = this.scaleFactor21;
      this.factor22 = this.factor7;
      super.updateState10();
      this.worldObj.uNKNOWNManager.applyTextByText("livingEntityBaseTick");
      boolean flag = this instanceof EntityPlayer;
      if (this.canExecute()) {
         if (this.isActive15()) {
            this.matchesDamageSource(DamageSource.damageSource3, 1.0F);
         } else if (flag && !this.worldObj.getInternalListEntry().matchesAxisAlignedBB(this.getBoundingBox2())) {
            double offset = this.worldObj.getInternalListEntry().computeValueByEntity(this) + this.worldObj.getInternalListEntry().getValue4();
            if (offset < 0.0) {
               this.matchesDamageSource(
                  DamageSource.damageSource3, (float)Math.max(1, MathHelper.ceiling_double_int(-offset * this.worldObj.getInternalListEntry().getValue10()))
               );
            }
         }
      }

      if (this.isState4() || this.worldObj.state) {
         this.updateState12();
      }

      boolean flag2 = flag && ((EntityPlayer)this).invulnerableManager.enabled;
      if (this.canExecute()) {
         if (this.matchesMaterial(Material.coreItemEntry1)) {
            if (!this.isActive17() && !this.checkIndexByIndex(Potion.potion18.indexOffset3) && !flag2) {
               this.applyIndex3(this.computeCount(this.getCount()));
               if (this.getCount() == -20) {
                  this.applyIndex3(0);

                  for (int index = 0; index < 8; index++) {
                     float factor = this.random.nextFloat() - this.random.nextFloat();
                     float factor2 = this.random.nextFloat() - this.random.nextFloat();
                     float factor3 = this.random.nextFloat() - this.random.nextFloat();
                     this.worldObj
                        .applyAttackEffectsExplosionNorm(
                           AttackEffectsExplosionNormalType.WATER_BUBBLE,
                           this.posX + (double)factor,
                           this.posY + (double)factor2,
                           this.posZ + (double)factor3,
                           this.motionX,
                           this.motionY,
                           this.motionZ,
                           new int[0]
                        );
                  }

                  this.matchesDamageSource(DamageSource.damageSource, 2.0F);
               }
            }

            if (!this.worldObj.state && this.isSneaking() && this.ridingEntity instanceof EntityLivingBase) {
               this.setEntity8(null);
            }
         } else {
            this.applyIndex3(300);
         }
      }

      if (this.canExecute() && this.isActive11()) {
         this.updateState12();
      }

      this.factor18 = this.factor8;
      this.indexOffset15 = this.hurtTime;
      if (this.hurtTime > 0) {
         this.hurtTime--;
      }

      if (this.count20 > 0) {
         this.count20--;
      }

      if (this.maxHurtTime > 0) {
         this.maxHurtTime--;
      }

      this.count15--;
      if (this.hurtResistantTime > 0 && !(this instanceof EntityPlayerAdapter)) {
         this.hurtResistantTime--;
      }

      if (this.getFloat2() <= 0.0F) {
         this.updateState36();
      }

      if (this.count11 > 0) {
         this.count11--;
      } else {
         this.entityPlayer = null;
      }

      if (this.targetEntity2 != null && !this.targetEntity2.canExecute()) {
         this.targetEntity2 = null;
      }

      if (this.targetEntity != null) {
         if (!this.targetEntity.canExecute()) {
            this.applyEntityLivingBase2(null);
         } else if (this.ticksExisted - this.deathTime > 100) {
            this.applyEntityLivingBase2(null);
         }
      }

      this.updateState39();
      this.factor20 = this.factor25;
      this.factor12 = this.scaleFactor8;
      this.scaleFactor22 = this.scaleFactor12;
      this.prevRotationYaw = this.rotationYaw;
      this.prevRotationPitch = this.rotationPitch;
      this.worldObj.uNKNOWNManager.updateState();
   }

   @Override
   public void updateState() {
      super.updateState();
      if (!this.worldObj.state) {
         int count = this.getCount5();
         if (count > 0) {
            if (this.count16 <= 0) {
               this.count16 = 20 * (30 - count);
            }

            this.count16--;
            if (this.count16 <= 0) {
               this.applyIndex6(count - 1);
            }
         }

         for (int index = 0; index < 5; index++) {
            ItemStack stack = this.itemStackArray[index];
            ItemStack stack2 = this.resolveItemStack(index);
            if (!ItemStack.matchesItemStack(stack2, stack)) {
               ((WorldServer)this.worldObj).getSecondaryLoggerEntry().applyEntity3(this, new S04PacketEntityEquipment(this.getEntityId(), index, stack2));
               if (stack != null) {
                  this.advancedMapEntry.applyMultimap(stack.getMultimap());
               }

               if (stack2 != null) {
                  this.advancedMapEntry.applyMultimapByMultimap(stack2.getMultimap());
               }

               this.itemStackArray[index] = stack2 == null ? null : stack2.getItemStack();
            }
         }

         if (this.ticksExisted % 20 == 0) {
            this.getGenericManager().updateState();
         }
      }

      this.updateState33();
      double offset = this.posX - this.prevPosX;
      double offset2 = this.posZ - this.prevPosZ;
      float factor = (float)(offset * offset + offset2 * offset2);
      float scaleFactor2 = this.scaleFactor8;
      float factor2 = 0.0F;
      this.factor6 = this.factor5;
      float factor3 = 0.0F;
      if (factor > 0.0025000002F) {
         factor3 = 1.0F;
         factor2 = (float)Math.sqrt((double)factor) * 3.0F;
         scaleFactor2 = (float)MathHelper.getDouble6(offset2, offset) * 180.0F / (float) Math.PI - 90.0F;
      }

      if (this.factor7 > 0.0F) {
         scaleFactor2 = this.rotationYaw;
      }

      if (!this.onGround) {
         factor3 = 0.0F;
      }

      this.factor5 = this.factor5 + (factor3 - this.factor5) * 0.3F;
      this.worldObj.uNKNOWNManager.applyTextByText("headTurn");
      float factor4 = this.computeFactor(scaleFactor2, factor2);
      this.worldObj.uNKNOWNManager.updateState();
      this.worldObj.uNKNOWNManager.applyTextByText("rangeChecks");

      while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
         this.prevRotationYaw -= 360.0F;
      }

      while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
         this.prevRotationYaw += 360.0F;
      }

      while (this.scaleFactor8 - this.factor12 < -180.0F) {
         this.factor12 -= 360.0F;
      }

      while (this.scaleFactor8 - this.factor12 >= 180.0F) {
         this.factor12 += 360.0F;
      }

      while (this.rotationPitch - this.prevRotationPitch < -180.0F) {
         this.prevRotationPitch -= 360.0F;
      }

      while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
         this.prevRotationPitch += 360.0F;
      }

      while (this.scaleFactor12 - this.scaleFactor22 < -180.0F) {
         this.scaleFactor22 -= 360.0F;
      }

      while (this.scaleFactor12 - this.scaleFactor22 >= 180.0F) {
         this.scaleFactor22 += 360.0F;
      }

      if (this instanceof EntityPlayerSP) {
         ViaProtocolHelper viaProtocolHelper = Client.getClient().getViaProtocolHelper();
         float scaleFactor4 = viaProtocolHelper.scaleFactor3;
         float scaleFactor5 = viaProtocolHelper.scaleFactor1;
         viaProtocolHelper.scaleFactor3 = viaProtocolHelper.computeFactor(scaleFactor5, scaleFactor4);
         float scaleFactor7 = viaProtocolHelper.scaleFactor;
         float scaleFactor9 = viaProtocolHelper.scaleFactor2;
         viaProtocolHelper.scaleFactor = viaProtocolHelper.computeFactor(scaleFactor9, scaleFactor7);
      }

      this.value13 = this.motionX;
      this.value14 = this.motionY;
      this.value5 = this.motionZ;
      this.worldObj.uNKNOWNManager.updateState();
      this.factor25 += factor4;
   }

   @Override
   public void updateState8() {
      this.enabled8 = this.random.nextDouble() >= this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener2).getDouble();
   }

   @Override
   public void updateState7() {
      this.dataWatcher.applyTarget(7, 0);
      this.dataWatcher.applyTarget(8, (byte)0);
      this.dataWatcher.applyTarget(9, (byte)0);
      this.dataWatcher.applyTarget(6, 1.0F);
   }

   public void updateState30() {
      this.dataWatcher.applyTargetByTarget(8, (byte)0);
      this.dataWatcher.applyTargetByTarget(7, 0);
   }

   public void updateState32() {
   }

   public boolean isState5() {
      return false;
   }

   public void updateState39() {
      Iterator iterator = this.map.keySet().iterator();

      while (iterator.hasNext()) {
         Integer count = (Integer)iterator.next();
         PotionEffect potionEffect = (PotionEffect)this.map.get(count);
         if (!potionEffect.matchesEntityLivingBase(this)) {
            if (!this.worldObj.state) {
               iterator.remove();
               this.applyPotionEffect2(potionEffect);
            }
         } else if (potionEffect.getIndexOffset() % 600 == 0) {
            this.applyPotionEffect(potionEffect, false);
         }
      }

      if (this.enabled14) {
         if (!this.worldObj.state) {
            this.updateState28();
         }

         this.enabled14 = false;
      }

      int count2 = this.dataWatcher.computeCount(7);
      boolean flag = this.dataWatcher.computeByteCode(8) > 0;
      if (count2 > 0) {
         boolean flag2;
         if (!this.isActive12()) {
            flag2 = this.random.nextBoolean();
         } else {
            flag2 = this.random.nextInt(15) == 0;
         }

         if (flag) {
            flag2 &= this.random.nextInt(5) == 0;
         }

         if (flag2 && count2 > 0) {
            double offset = (double)(count2 >> 16 & 0xFF) / 255.0;
            double offset2 = (double)(count2 >> 8 & 0xFF) / 255.0;
            double offset3 = (double)(count2 >> 0 & 0xFF) / 255.0;
            this.worldObj
               .applyAttackEffectsExplosionNorm(
                  flag ? AttackEffectsExplosionNormalType.SPELL_MOB_AMBIENT : AttackEffectsExplosionNormalType.SPELL_MOB,
                  this.posX + (this.random.nextDouble() - 0.5) * (double)this.width,
                  this.posY + this.random.nextDouble() * (double)this.height,
                  this.posZ + (this.random.nextDouble() - 0.5) * (double)this.width,
                  offset,
                  offset2,
                  offset3,
                  new int[0]
               );
         }
      }
   }

   public void updateState34() {
      this.motionY += 0.04F;
   }

   public void closeScreen() {
      this.motionY = (double)this.getFloat6();
      if (this.checkPotion(Potion.potion9)) {
         this.motionY = this.motionY + (double)((float)(this.getPotionEffect(Potion.potion9).getIndexOffset2() + 1) * 0.1F);
      }

      this.indexOffset16 = 0;
      if (this.isRiding()) {
         float factor = this.computeFactor2(this.rotationYaw) * (float) (Math.PI / 180.0);
         this.motionX = this.motionX - (double)(MathHelper.abs(factor) * 0.2F);
         this.motionZ = this.motionZ + (double)(MathHelper.cos(factor) * 0.2F);
      }

      this.enabled4 = true;
   }

   public void updateState33() {
      if (this.indexOffset18 > 0) {
         this.indexOffset18--;
      }

      this.positionVec = new Vec3(this.positionVec3.xCoord, this.positionVec3.yCoord, this.positionVec3.zCoord);
      this.positionVec5 = new Vec3(this.positionVec4.xCoord, this.positionVec4.yCoord, this.positionVec4.zCoord);
      if (this.indexOffset23 > 0) {
         double offset = this.positionVec3.xCoord + (this.coordOffset5 - this.positionVec3.xCoord) / (double)this.indexOffset23;
         double offset2 = this.positionVec3.yCoord + (this.coordOffset20 - this.positionVec3.yCoord) / (double)this.indexOffset23;
         double offset3 = this.positionVec3.zCoord + (this.coordOffset2 - this.positionVec3.zCoord) / (double)this.indexOffset23;
         this.indexOffset23--;
         this.positionVec3 = new Vec3(offset, offset2, offset3);
         float factor = this.width / 2.0F;
         float height = this.height;
         this.axisAlignedBB2 = new AxisAlignedBB(
            offset - (double)factor, offset2, offset3 - (double)factor, offset + (double)factor, offset2 + (double)height, offset3 + (double)factor
         );
         if (this == Minecraft.getMinecraft().thePlayer) {
            float factor2 = this.rotationPitch;
            float factor3 = this.rotationYaw;
            ClientGlobals.moduleManager.killAura.simulatedPlayer.customFloatEntry.applyFloat(factor3, factor2, false);
         }
      }

      this.positionVec4 = new Vec3(this.coordOffset5, this.coordOffset20, this.coordOffset2);
      if (this.count22 > 0) {
         double offset4 = this.posX + (this.value6 - this.posX) / (double)this.count22;
         double offset5 = this.posY + (this.value8 - this.posY) / (double)this.count22;
         double offset6 = this.posZ + (this.value15 - this.posZ) / (double)this.count22;
         double doubleItem = MathHelper.getDouble4(this.value9 - (double)this.rotationYaw);
         this.rotationYaw = (float)((double)this.rotationYaw + doubleItem / (double)this.count22);
         this.rotationPitch = (float)((double)this.rotationPitch + (this.value11 - (double)this.rotationPitch) / (double)this.count22);
         this.count22--;
         this.processDouble2(offset4, offset5, offset6);
         this.processFloat4(this.rotationYaw, this.rotationPitch);
         if (this instanceof EntityPlayerSP) {
            RotationManager rotationManager = Client.getClient().getRotationManager();
            Rotation rotation = new Rotation(this.rotationYaw, this.rotationPitch);
            rotationManager.setServerRotation(rotation);
         }
      } else if (!this.isActive18()) {
         this.motionX *= 0.98;
         this.motionY *= 0.98;
         this.motionZ *= 0.98;
      }

      if (this.ticksExisted > 5 && Minecraft.getMinecraft().isSingleplayer()) {
         BacktrackRecord backtrackRecord = this.backtrackRecord3;
         Vec3Entry vec3Entry = new Vec3Entry(this);
         backtrackRecord.applyObject(vec3Entry);
      }

      double offset7 = ViaLoadingBase.processAction2();
      if (Math.abs(this.motionX) < offset7) {
         this.motionX = 0.0;
      }

      if (Math.abs(this.motionY) < offset7) {
         this.motionY = 0.0;
      }

      if (Math.abs(this.motionZ) < offset7) {
         this.motionZ = 0.0;
      }

      this.worldObj.uNKNOWNManager.applyTextByText("ai");
      if (this.isActive20()) {
         this.state31 = false;
         this.scaleFactor24 = 0.0F;
         this.scaleFactor3 = 0.0F;
         this.factor16 = 0.0F;
      } else if (this.isActive18()) {
         this.worldObj.uNKNOWNManager.applyTextByText("newAi");
         this.updateState38();
         this.worldObj.uNKNOWNManager.updateState();
      }

      this.worldObj.uNKNOWNManager.updateState();
      this.worldObj.uNKNOWNManager.applyTextByText("jump");
      if (this.state31) {
         if (this.isInWater()) {
            this.updateState41();
         } else if (this.isWet()) {
            this.updateState34();
         } else if (this.onGround && this.indexOffset18 == 0) {
            this.closeScreen();
            this.indexOffset18 = 10;
         }
      } else {
         this.indexOffset18 = 0;
      }

      this.worldObj.uNKNOWNManager.updateState();
      this.worldObj.uNKNOWNManager.applyTextByText("travel");
      this.scaleFactor24 *= 0.98F;
      this.scaleFactor3 *= 0.98F;
      this.factor16 *= 0.9F;
      this.applyFloat2(this.scaleFactor24, this.scaleFactor3);
      this.value13 = this.motionX;
      this.value14 = this.motionY;
      this.value5 = this.motionZ;
      this.worldObj.uNKNOWNManager.updateState();
      this.worldObj.uNKNOWNManager.applyTextByText("push");
      if (!this.worldObj.state) {
         this.updateState35();
      }

      this.worldObj.uNKNOWNManager.updateState();
   }

   public void updateState29() {
   }

   public void updateState36() {
      this.indexOffset29++;
      if (this.indexOffset29 == 20) {
         if (!this.worldObj.state
            && (this.count11 > 0 || this.isActive22())
            && this.isActive19()
            && this.worldObj.getDoFireTickManager().matchesText("doMobLoot")) {
            int count = this.nextInt(this.entityPlayer);

            while (count > 0) {
               int count2 = EntityXporb.computeCount(count);
               count -= count2;
               this.worldObj.matchesEntity(new EntityXporb(this.worldObj, this.posX, this.posY, this.posZ, count2));
            }
         }

         this.updateState9();

         for (int index = 0; index < 20; index++) {
            double offset = this.random.nextGaussian() * 0.02;
            double offset2 = this.random.nextGaussian() * 0.02;
            double offset3 = this.random.nextGaussian() * 0.02;
            this.worldObj
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.EXPLOSION_NORMAL,
                  this.posX + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width,
                  this.posY + (double)(this.random.nextFloat() * this.height),
                  this.posZ + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width,
                  offset,
                  offset2,
                  offset3,
                  new int[0]
               );
         }
      }
   }

   public void respawnPlayer() {
      if (!this.state2 || this.indexOffset20 >= this.getInt3() / 2 || this.indexOffset20 < 0) {
         this.indexOffset20 = -1;
         this.state2 = true;
         if (this.worldObj instanceof WorldServer) {
            ((WorldServer)this.worldObj).getSecondaryLoggerEntry().applyEntity3(this, new S0BPacketAnimation(this, 0));
         }
      }
   }

   public void updateState35() {
      List entitiesInAABBexcluding = this.worldObj
         .getEntitiesInAABBexcluding(
            this, this.getBoundingBox2().offset(0.2F, 0.0, 0.2F), Predicates.and(LivingEntityFilter.predicate4, new PrimaryPredicateAdapter(this))
         );
      if (!entitiesInAABBexcluding.isEmpty()) {
         for (int index = 0; index < entitiesInAABBexcluding.size(); index++) {
            Entity entity = (Entity)entitiesInAABBexcluding.get(index);
            this.applyEntity9(entity);
         }
      }
   }

   public EntityLivingBase(World world) {
      super(world);
      this.updateState27();
      this.applyFloat(this.getFloat());
      this.enabled = true;
      this.factor10 = (float)((Math.random() + 1.0) * 0.01F);
      this.processDouble2(this.posX, this.posY, this.posZ);
      this.positionVec3 = new Vec3(this.posX, this.posY, this.posZ);
      this.positionVec = new Vec3(this.posX, this.posY, this.posZ);
      this.positionVec4 = new Vec3(this.posX, this.posY, this.posZ);
      this.positionVec5 = new Vec3(this.posX, this.posY, this.posZ);
      this.factor15 = (float)Math.random() * 12398.0F;
      this.rotationYaw = (float)(Math.random() * Math.PI * 2.0);
      this.scaleFactor12 = this.rotationYaw;
      this.stepHeight = 0.6F;
   }
}
