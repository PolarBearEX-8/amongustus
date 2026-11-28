package net.minecraft.entity.monster;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityEndermite extends EntityMob {
   private int lifetime = 0;
   private boolean playerSpawned = false;

   @Override
   public boolean isValidLightLevel() {
      return true;
   }

   @Override
   public String getHurtSound() {
      return "mob.silverfish.hit";
   }

   @Override
   public String getDeathSound() {
      return "mob.silverfish.kill";
   }

   public EntityEndermite(World world) {
      super(world);
      this.experienceValue = 3;
      this.setSize(0.4F, 0.3F);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0, false));
      this.tasks.addTask(3, new EntityAIWander(this, 1.0));
      this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(8, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.lifetime = tag.getInteger("Lifetime");
      this.playerSpawned = tag.getBoolean("PlayerSpawned");
   }

   @Override
   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.ARTHROPOD;
   }

   public void setSpawnedByPlayer(boolean flag) {
      this.playerSpawned = flag;
   }

   @Override
   public String getLivingSound() {
      return "mob.silverfish.say";
   }

   @Override
   public void onUpdate() {
      this.renderYawOffset = this.rotationYaw;
      super.onUpdate();
   }

   @Override
   public Item getDropItem() {
      return null;
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.entityWorld.isRemote) {
         for (int i = 0; i < 2; i++) {
            this.entityWorld
               .spawnParticle(
                  EnumParticleTypes.PORTAL,
                  this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width,
                  this.posY + this.rand.nextDouble() * (double)this.height,
                  this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width,
                  (this.rand.nextDouble() - 0.5) * 2.0,
                  -this.rand.nextDouble(),
                  (this.rand.nextDouble() - 0.5) * 2.0,
                  new int[0]
               );
         }
      } else {
         if (!this.isNoDespawnRequired()) {
            this.lifetime++;
         }

         if (this.lifetime >= 2400) {
            this.setDead();
         }
      }
   }

   @Override
   public float getEyeHeight() {
      return 0.1F;
   }

   public boolean isSpawnedByPlayer() {
      return this.playerSpawned;
   }

   @Override
   public boolean getCanSpawnHere() {
      if (super.getCanSpawnHere()) {
         return this.entityWorld.getClosestPlayerToEntity(this, 5.0) == null;
      } else {
         return false;
      }
   }

   @Override
   public boolean canTriggerWalking() {
      return false;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setInteger("Lifetime", this.lifetime);
      tag.setBoolean("PlayerSpawned", this.playerSpawned);
   }

   @Override
   public void playStepSound(BlockPos pos, Block block) {
      this.playSound("mob.silverfish.step", 0.15F, 1.0F);
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0);
   }
}
