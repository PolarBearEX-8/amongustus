package net.minecraft.entity.monster;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityPigZombie extends EntityZombie {
   private static final UUID ATTACK_SPEED_BOOST_MODIFIER_UUID = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
   private static final AttributeModifier ATTACK_SPEED_BOOST_MODIFIER = new AttributeModifier(
         ATTACK_SPEED_BOOST_MODIFIER_UUID, "Attacking speed boost", 0.05, 0
      )
      .setSaved(false);
   private int angerLevel;
   private int randomSoundDelay;
   private UUID angerTargetUUID;

   @Override
   public boolean isNotColliding() {
      return this.entityWorld.checkNoEntityCollision(this.getEntityBoundingBox(), this)
         && this.entityWorld.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty()
         && !this.entityWorld.isAnyLiquid(this.getEntityBoundingBox());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.angerLevel = tag.getShort("Anger");
      String string = tag.getString("HurtBy");
      if (string.length() > 0) {
         this.angerTargetUUID = UUID.fromString(string);
         EntityPlayer playerEntityByUUID = this.entityWorld.getPlayerEntityByUUID(this.angerTargetUUID);
         this.setRevengeTarget(playerEntityByUUID);
         if (playerEntityByUUID != null) {
            this.attackingPlayer = playerEntityByUUID;
            this.recentlyHit = this.getRevengeTimer();
         }
      }
   }

   public boolean isAngry() {
      return this.angerLevel > 0;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
   }

   public static void invokeBecomeAngryAt(EntityPigZombie entityPigZombie, Entity entity) {
      entityPigZombie.becomeAngryAt(entity);
   }

   @Override
   public void setRevengeTarget(EntityLivingBase entity) {
      super.setRevengeTarget(entity);
      if (entity != null) {
         this.angerTargetUUID = entity.getUniqueID();
      }
   }

   @Override
   public void updateAITasks() {
      IAttributeInstance entityAttribute = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
      if (this.isAngry()) {
         if (!this.isChild() && !entityAttribute.hasModifier(ATTACK_SPEED_BOOST_MODIFIER)) {
            entityAttribute.applyModifier(ATTACK_SPEED_BOOST_MODIFIER);
         }

         this.angerLevel--;
      } else if (entityAttribute.hasModifier(ATTACK_SPEED_BOOST_MODIFIER)) {
         entityAttribute.removeModifier(ATTACK_SPEED_BOOST_MODIFIER);
      }

      if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0) {
         this.playSound("mob.zombiepig.zpigangry", this.getSoundVolume() * 2.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
      }

      if (this.angerLevel > 0 && this.angerTargetUUID != null && this.getAITarget() == null) {
         EntityPlayer playerEntityByUUID = this.entityWorld.getPlayerEntityByUUID(this.angerTargetUUID);
         this.setRevengeTarget(playerEntityByUUID);
         this.attackingPlayer = playerEntityByUUID;
         this.recentlyHit = this.getRevengeTimer();
      }

      super.updateAITasks();
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficultyInstance, IEntityLivingData entityLivingData) {
      super.onInitialSpawn(difficultyInstance, entityLivingData);
      this.setVillager(false);
      return entityLivingData;
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else {
         Entity entity = damageSource.getEntity();
         if (entity instanceof EntityPlayer) {
            this.becomeAngryAt(entity);
         }

         return super.attackEntityFrom(damageSource, f);
      }
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(reinforcementChance).setBaseValue(0.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23F);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
   }

   @Override
   public void addRandomDrop() {
      this.dropItem(Items.gold_ingot, 1);
   }

   @Override
   public void applyEntityAI() {
      this.targetTasks.addTask(1, new EntityPigZombie.AIHurtByAggressor(this));
      this.targetTasks.addTask(2, new EntityPigZombie.AITargetAggressor(this));
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setShort("Anger", (short)this.angerLevel);
      if (this.angerTargetUUID != null) {
         tag.setString("HurtBy", this.angerTargetUUID.toString());
      } else {
         tag.setString("HurtBy", "");
      }
   }

   private void becomeAngryAt(Entity entity) {
      this.angerLevel = 400 + this.rand.nextInt(400);
      this.randomSoundDelay = this.rand.nextInt(40);
      if (entity instanceof EntityLivingBase) {
         this.setRevengeTarget((EntityLivingBase)entity);
      }
   }

   @Override
   public String getLivingSound() {
      return "mob.zombiepig.zpig";
   }

   public EntityPigZombie(World world) {
      super(world);
      this.isImmuneToFire = true;
   }

   @Override
   public void dropFewItems(boolean flag, int n) {
      int n2 = this.rand.nextInt(2 + n);

      for (int i = 0; i < n2; i++) {
         this.dropItem(Items.rotten_flesh, 1);
      }

      n2 = this.rand.nextInt(2 + n);

      for (int j = 0; j < n2; j++) {
         this.dropItem(Items.gold_nugget, 1);
      }
   }

   @Override
   public boolean interact(EntityPlayer player) {
      return false;
   }

   @Override
   public String getDeathSound() {
      return "mob.zombiepig.zpigdeath";
   }

   @Override
   public String getHurtSound() {
      return "mob.zombiepig.zpighurt";
   }

   @Override
   public boolean getCanSpawnHere() {
      return this.entityWorld.getDifficulty() != EnumDifficulty.PEACEFUL;
   }

   @Override
   public void setEquipmentBasedOnDifficulty(DifficultyInstance difficultyInstance) {
      this.setCurrentItemOrArmor(0, new ItemStack(Items.golden_sword));
   }


   public static class AIHurtByAggressor extends EntityAIHurtByTarget {
      @Override
      public void setEntityAttackTarget(EntityCreature entityCreature, EntityLivingBase entity) {
         super.setEntityAttackTarget(entityCreature, entity);
         if (entityCreature instanceof EntityPigZombie) {
            EntityPigZombie.invokeBecomeAngryAt((EntityPigZombie)entityCreature, entity);
         }
      }

      public AIHurtByAggressor(EntityPigZombie entityPigZombie) {
         super(entityPigZombie, true, new Class[0]);
      }
   }

   public static class AITargetAggressor extends EntityAINearestAttackableTarget {
      public AITargetAggressor(EntityPigZombie entityPigZombie) {
         super(entityPigZombie, EntityPlayer.class, true);
      }

      @Override
      public boolean shouldExecute() {
         return ((EntityPigZombie)this.taskOwner).isAngry() && super.shouldExecute();
      }
   }
}
