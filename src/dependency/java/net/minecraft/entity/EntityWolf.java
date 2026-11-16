package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.LocalOptionType;
import client.util.AdvancedClientManagerAdapter;
import client.util.AuxiliaryWorldEntry;
import client.util.BaseClientManagerAdapter;
import client.util.ClassArrayEntry;
import client.util.ClientManagerBridge;
import client.util.DoMobLootClientManager;
import client.util.ExtendedClassEntry;
import client.util.FovFormatter;
import client.util.HitBoxAdapter;
import client.util.HitBoxBuffer;
import client.util.HitBoxPicker;
import client.util.HitBoxScheduler;
import client.util.HitBoxService;
import client.util.PathfindManagerAdapter;
import client.util.RotationFactory;
import client.util.RotationScheduler;
import client.util.SupplementalClientManagerAdapter;
import client.util.UUIDEntryAdapter;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityWolf extends HitBoxAdapter {
   public float factor55;
   public float factor53;
   public boolean enabled29;
   public boolean enabled28;
   public float factor52;
   public float factor50;

   @Override
   public boolean isActive26() {
      return !this.isActive35() && super.isActive26();
   }

   @Override
   public boolean matchesItemStackByItemStack(ItemStack stack) {
      return stack == null ? false : (!(stack.getItem() instanceof ItemFood) ? false : ((ItemFood)stack.getItem()).isEnabled());
   }

   @Override
   public float getFactor() {
      return 0.4F;
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, Block block) {
      this.applyText("mob.wolf.step", 0.15F, 1.0F);
   }

   @Override
   public String getText9() {
      return "mob.wolf.death";
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.setEnabled11(tagCompound.matchesText("Angry"));
      if (tagCompound.matchesText("CollarColor", 99)) {
         this.applyLocalOptionType(LocalOptionType.resolveLocalOptionTypeByIndex(tagCompound.computeByteCode("CollarColor")));
      }
   }

   @Override
   public float getHeight2() {
      return this.height * 0.8F;
   }

   @Override
   public boolean matchesEntity4(Entity entity) {
      boolean flag = entity.matchesDamageSource(
         DamageSource.resolveDamageSourceByEntityLivingBase(this),
         (float)((int)this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).getDouble())
      );
      if (flag) {
         this.applyEntityLivingBase(this, entity);
      }

      return flag;
   }

   @Override
   public boolean matchesHitBoxBuffer(HitBoxBuffer hitBoxBuffer) {
      if (hitBoxBuffer == this) {
         return false;
      } else if (!this.isActive32()) {
         return false;
      } else if (!(hitBoxBuffer instanceof EntityWolf)) {
         return false;
      } else {
         EntityWolf entityWolf = (EntityWolf)hitBoxBuffer;
         return !entityWolf.isActive32() ? false : (entityWolf.isActive33() ? false : this.isActive31() && entityWolf.isActive31());
      }
   }

   @Override
   public boolean matchesEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity, EntityLivingBase livingEntity2) {
      if (!(livingEntity instanceof EntityCreeper) && !(livingEntity instanceof EntityGhast)) {
         if (livingEntity instanceof EntityWolf) {
            EntityWolf entityWolf = (EntityWolf)livingEntity;
            if (entityWolf.isActive32() && entityWolf.getEntityLivingBase7() == livingEntity2) {
               return false;
            }
         }

         return livingEntity instanceof EntityPlayer && livingEntity2 instanceof EntityPlayer && !((EntityPlayer)livingEntity2).matchesEntityPlayer6((EntityPlayer)livingEntity)
            ? false
            : !(livingEntity instanceof EntityHorse) || !((EntityHorse)livingEntity).isActive43();
      } else {
         return false;
      }
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.3F);
      if (this.isActive32()) {
         this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(20.0);
      } else {
         this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(8.0);
      }

      this.getAdvancedMapEntry().resolveAuxiliaryListenerByExtendedListener(SharedMonsterAttributes.extendedListener);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).applyDouble(2.0);
   }

   public EntityWolf resolveEntityWolf(UUIDEntryAdapter uUIDEntryAdapter) {
      EntityWolf entityWolf = new EntityWolf(this.worldObj);
      String textAsText = this.getTextAsText();
      if (textAsText != null && textAsText.trim().length() > 0) {
         entityWolf.applyText(textAsText);
         entityWolf.setEnabled9(true);
      }

      return entityWolf;
   }

   public float computeFactorByFloat(float factor, float factor2) {
      float factor3 = (this.factor50 + (this.factor52 - this.factor50) * factor + factor2) / 1.8F;
      if (factor3 < 0.0F) {
         factor3 = 0.0F;
      } else if (factor3 > 1.0F) {
         factor3 = 1.0F;
      }

      return MathHelper.abs(factor3 * (float) Math.PI) * MathHelper.abs(factor3 * (float) Math.PI * 11.0F) * 0.15F * (float) Math.PI;
   }

   @Override
   public Item getGunpowder() {
      return Item.processAction(-1);
   }

   @Override
   public UUIDEntryAdapter resolveUUIDEntryAdapter(UUIDEntryAdapter uUIDEntryAdapter) {
      return this.resolveEntityWolf(uUIDEntryAdapter);
   }

   @Override
   public void processByte(byte byteValue) {
      if (byteValue == 8) {
         this.enabled28 = true;
         this.factor52 = 0.0F;
         this.factor50 = 0.0F;
      } else {
         super.processByte(byteValue);
      }
   }

   @Override
   public boolean matchesEntityPlayer6(EntityPlayer player) {
      ItemStack slot = player.inventoryPlayer.getCurrentItem();
      if (this.isActive32()) {
         if (slot != null) {
            if (slot.getItem() instanceof ItemFood) {
               ItemFood item = (ItemFood)slot.getItem();
               if (item.isEnabled() && this.dataWatcher.computeFactor(18) < 20.0F) {
                  if (!player.invulnerableManager.enabled2) {
                     slot.animationsToGo--;
                  }

                  this.applyFloatByFloat((float)item.computeCount(slot));
                  if (slot.animationsToGo <= 0) {
                     player.inventoryPlayer.processAction(player.inventoryPlayer.currentItem, (ItemStack)null);
                  }

                  return true;
               }
            } else if (slot.getItem() == Items.dye) {
               LocalOptionType localOptionType = LocalOptionType.resolveLocalOptionTypeByIndex(slot.getIndexOffset());
               if (localOptionType != this.getLocalOptionType()) {
                  this.applyLocalOptionType(localOptionType);
                  if (!player.invulnerableManager.enabled2 && --slot.animationsToGo <= 0) {
                     player.inventoryPlayer.processAction(player.inventoryPlayer.currentItem, (ItemStack)null);
                  }

                  return true;
               }
            }
         }

         if (this.matchesEntityLivingBase3(player) && !this.worldObj.state && !this.matchesItemStackByItemStack(slot)) {
            this.hitBoxCoordinator.setEnabled(!this.isActive33());
            this.state31 = false;
            this.pathfindManager.updateState4();
            this.setEntityLivingBase((EntityLivingBase)null);
         }
      } else if (slot != null && slot.getItem() == Items.bone && !this.isActive35()) {
         if (!player.invulnerableManager.enabled2) {
            slot.animationsToGo--;
         }

         if (slot.animationsToGo <= 0) {
            player.inventoryPlayer.processAction(player.inventoryPlayer.currentItem, (ItemStack)null);
         }

         if (!this.worldObj.state) {
            if (this.random.nextInt(3) == 0) {
               this.setEnabled9(true);
               this.pathfindManager.updateState4();
               this.setEntityLivingBase((EntityLivingBase)null);
               this.hitBoxCoordinator.setEnabled(true);
               this.applyFloat(20.0F);
               this.applyText(player.getEntityUniqueID().toString());
               this.setEnabled7(true);
               this.worldObj.applyEntity(this, (byte)7);
            } else {
               this.setEnabled7(false);
               this.worldObj.applyEntity(this, (byte)6);
            }
         }

         return true;
      }

      return super.matchesEntityPlayer6(player);
   }

   @Override
   public void setEntityLivingBase(EntityLivingBase entityLivingBase) {
      super.setEntityLivingBase(entityLivingBase);
      if (entityLivingBase == null) {
         this.setEnabled11(false);
      } else if (!this.isActive32()) {
         this.setEnabled11(true);
      }
   }

   public float computeFactor4(float factor) {
      return 0.75F + (this.factor50 + (this.factor52 - this.factor50) * factor) / 2.0F * 0.25F;
   }

   @Override
   public String getText3() {
      return this.isActive35()
         ? "mob.wolf.growl"
         : (
            this.random.nextInt(3) == 0
               ? (this.isActive32() && this.dataWatcher.computeFactor(18) < 10.0F ? "mob.wolf.whine" : "mob.wolf.panting")
               : "mob.wolf.bark"
         );
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      float factor2 = factor;
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else {
         Entity entity = damageSource.getEntity();
         this.hitBoxCoordinator.setEnabled(false);
         if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
            factor2 = (factor + 1.0F) / 2.0F;
         }

         return super.matchesDamageSource(damageSource, factor2);
      }
   }

   public void applyLocalOptionType(LocalOptionType localOptionType) {
      this.dataWatcher.applyTargetByTarget(20, (byte)(localOptionType.getCount2() & 15));
   }

   public LocalOptionType getLocalOptionType() {
      return LocalOptionType.resolveLocalOptionTypeByIndex(this.dataWatcher.computeByteCode(20) & 15);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("Angry", this.isActive35());
      tagCompound.put("CollarColor", (byte)this.getLocalOptionType().getCount2());
   }

   @Override
   public String getText10() {
      return "mob.wolf.hurt";
   }

   public float computeFactor3(float factor) {
      return (this.factor53 + (this.factor55 - this.factor53) * factor) * 0.15F * (float) Math.PI;
   }

   @Override
   public int getCount6() {
      return 8;
   }

   public boolean isEnabled29() {
      return this.enabled29;
   }

   @Override
   public void updateState3() {
      this.dataWatcher.applyTargetByTarget(18, this.getFloat2());
   }

   public void setEnabled11(boolean enabled) {
      byte byteValue = this.dataWatcher.computeByteCode(16);
      if (enabled) {
         this.dataWatcher.applyTargetByTarget(16, (byte)(byteValue | 2));
      } else {
         this.dataWatcher.applyTargetByTarget(16, (byte)(byteValue & -3));
      }
   }

   public void setEnabled10(boolean enabled) {
      if (enabled) {
         this.dataWatcher.applyTargetByTarget(19, (byte)1);
      } else {
         this.dataWatcher.applyTargetByTarget(19, (byte)0);
      }
   }

   @Override
   public int getCount8() {
      return this.isActive33() ? 20 : super.getCount8();
   }

   public boolean isActive35() {
      return (this.dataWatcher.computeByteCode(16) & 2) != 0;
   }

   public boolean isActive34() {
      return this.dataWatcher.computeByteCode(19) == 1;
   }

   @Override
   public void setEnabled9(boolean enabled) {
      super.setEnabled9(enabled);
      if (enabled) {
         this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(20.0);
      } else {
         this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(8.0);
      }

      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).applyDouble(4.0);
   }

   @Override
   public void updateState() {
      super.updateState();
      this.factor53 = this.factor55;
      if (this.isActive34()) {
         this.factor55 = this.factor55 + (1.0F - this.factor55) * 0.4F;
      } else {
         this.factor55 = this.factor55 + (0.0F - this.factor55) * 0.4F;
      }

      if (this.isActive11()) {
         this.enabled29 = true;
         this.enabled28 = false;
         this.factor52 = 0.0F;
         this.factor50 = 0.0F;
      } else if ((this.enabled29 || this.enabled28) && this.enabled28) {
         if (this.factor52 == 0.0F) {
            this.applyText("mob.wolf.shake", this.getFactor(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
         }

         this.factor50 = this.factor52;
         this.factor52 += 0.05F;
         if (this.factor50 >= 2.0F) {
            this.enabled29 = false;
            this.enabled28 = false;
            this.factor50 = 0.0F;
            this.factor52 = 0.0F;
         }

         if (this.factor52 > 0.4F) {
            float minY = (float)this.getBoundingBox2().minY;
            int count = (int)(MathHelper.abs((this.factor52 - 0.4F) * (float) Math.PI) * 7.0F);

            for (int index = 0; index < count; index++) {
               float factor = (this.random.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
               float factor2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
               this.worldObj
                  .applyAttackEffectsExplosionNorm(
                     AttackEffectsExplosionNormalType.WATER_SPLASH,
                     this.posX + (double)factor,
                     (double)(minY + 0.8F),
                     this.posZ + (double)factor2,
                     this.motionX,
                     this.motionY,
                     this.motionZ,
                     new int[0]
                  );
            }
         }
      }
   }

   public float getFactor3() {
      return this.isActive35()
         ? 1.5393804F
         : (this.isActive32() ? (0.55F - (20.0F - this.dataWatcher.computeFactor(18)) * 0.02F) * (float) Math.PI : (float) (Math.PI / 5));
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(18, this.getFloat2());
      this.dataWatcher.applyTarget(19, (byte)0);
      this.dataWatcher.applyTarget(20, (byte)LocalOptionType.RED.getCount());
   }

   @Override
   public boolean isActive24() {
      return !this.isActive32() && this.ticksExisted > 2400;
   }

   @Override
   public void updateState33() {
      super.updateState33();
      if (!this.worldObj.state && this.enabled29 && !this.enabled28 && !this.onUpdate() && this.onGround) {
         this.enabled28 = true;
         this.factor52 = 0.0F;
         this.factor50 = 0.0F;
         this.worldObj.applyEntity(this, (byte)8);
      }

      if (!this.worldObj.state && this.getEntityLivingBase4() == null && this.isActive35()) {
         this.setEnabled11(false);
      }
   }

   public EntityWolf(World world) {
      super(world);
      this.applyFloat(0.6F, 0.8F);
      ((PathfindManagerAdapter)this.getPathfindManager()).setEnabled3(true);
      this.goalSetupManager.add(1, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(2, this.hitBoxCoordinator);
      this.goalSetupManager.add(3, new ClientManagerBridge(this, 0.4F));
      this.goalSetupManager.add(4, new AuxiliaryWorldEntry(this, 1.0, true));
      this.goalSetupManager.add(5, new RotationScheduler(this, 1.0, 10.0F, 2.0F));
      this.goalSetupManager.add(6, new DoMobLootClientManager(this, 1.0));
      this.goalSetupManager.add(7, new AdvancedClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(8, new RotationFactory(this, 8.0F));
      this.goalSetupManager.add(9, new BaseClientManagerAdapter(this, EntityPlayer.class, 8.0F));
      this.goalSetupManager.add(9, new ComplexItemEntry(this));
      this.goalSetupManager2.add(1, new HitBoxPicker(this));
      this.goalSetupManager2.add(2, new HitBoxService(this));
      this.goalSetupManager2.add(3, new ClassArrayEntry(this, true, new Class[0]));
      this.goalSetupManager2.add(4, new HitBoxScheduler(this, HitBoxBuffer.class, false, new FovFormatter(this)));
      this.goalSetupManager2.add(5, new ExtendedClassEntry(this, EntitySkeleton.class, false));
      this.setEnabled9(false);
   }
}
