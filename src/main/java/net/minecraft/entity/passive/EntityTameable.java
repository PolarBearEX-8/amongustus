package net.minecraft.entity.passive;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public abstract class EntityTameable extends EntityAnimal implements IEntityOwnable {
   public EntityAISit aiSit = new EntityAISit(this);

   @Override
   public Entity getOwner() {
      return this.getOwnerEntity();
   }

   public EntityLivingBase getOwnerEntity() {
      try {
         UUID uuid = UUID.fromString(this.getOwnerId());
         return uuid == null ? null : this.entityWorld.getPlayerEntityByUUID(uuid);
      } catch (IllegalArgumentException e) {
         return null;
      }
   }

   public boolean isOwner(EntityLivingBase entity) {
      return entity == this.getOwnerEntity();
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(16, (byte)0);
      this.dataWatcher.addObject(17, "");
   }

   public void setOwnerId(String str) {
      this.dataWatcher.updateObject(17, str);
   }

   @Override
   public void onDeath(DamageSource damageSource) {
      if (!this.entityWorld.isRemote && this.entityWorld.getGameRules().getBoolean("showDeathMessages") && this.hasCustomName() && this.getOwnerEntity() instanceof EntityPlayerMP) {
         ((EntityPlayerMP)this.getOwnerEntity()).addChatMessage(this.getCombatTracker().getDeathMessage());
      }

      super.onDeath(damageSource);
   }

   @Override
   public String getOwnerId() {
      return this.dataWatcher.getWatchableObjectString(17);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      if (this.getOwnerId() == null) {
         tag.setString("OwnerUUID", "");
      } else {
         tag.setString("OwnerUUID", this.getOwnerId());
      }

      tag.setBoolean("Sitting", this.isSitting());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      String str = "";
      if (tag.hasKey("OwnerUUID", 8)) {
         str = tag.getString("OwnerUUID");
      } else {
         str = PreYggdrasilConverter.getStringUUIDFromName(tag.getString("Owner"));
      }

      if (str.length() > 0) {
         this.setOwnerId(str);
         this.setTamed(true);
      }

      this.aiSit.setSitting(tag.getBoolean("Sitting"));
      this.setSitting(tag.getBoolean("Sitting"));
   }

   public EntityTameable(World world) {
      super(world);
      this.setupTamedAI();
   }

   public boolean shouldAttackEntity(EntityLivingBase entity, EntityLivingBase entity2) {
      return true;
   }

   public boolean isTamed() {
      return (this.dataWatcher.getWatchableObjectByte(16) & 4) != 0;
   }

   @Override
   public Team getTeam() {
      if (this.isTamed()) {
         EntityLivingBase ownerEntity = this.getOwnerEntity();
         if (ownerEntity != null) {
            return ownerEntity.getTeam();
         }
      }

      return super.getTeam();
   }

   public void setSitting(boolean flag) {
      byte watchableObjectByte = this.dataWatcher.getWatchableObjectByte(16);
      if (flag) {
         this.dataWatcher.updateObject(16, (byte)(watchableObjectByte | 1));
      } else {
         this.dataWatcher.updateObject(16, (byte)(watchableObjectByte & -2));
      }
   }

   public boolean isSitting() {
      return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
   }

   public void playTameEffect(boolean flag) {
      EnumParticleTypes heart = EnumParticleTypes.HEART;
      if (!flag) {
         heart = EnumParticleTypes.SMOKE_NORMAL;
      }

      for (int i = 0; i < 7; i++) {
         double d = this.rand.nextGaussian() * 0.02;
         double d2 = this.rand.nextGaussian() * 0.02;
         double d3 = this.rand.nextGaussian() * 0.02;
         this.entityWorld
            .spawnParticle(
               heart,
               this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width,
               this.posY + 0.5 + (double)(this.rand.nextFloat() * this.height),
               this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width,
               d,
               d2,
               d3,
               new int[0]
            );
      }
   }

   public void setTamed(boolean flag) {
      byte watchableObjectByte = this.dataWatcher.getWatchableObjectByte(16);
      if (flag) {
         this.dataWatcher.updateObject(16, (byte)(watchableObjectByte | 4));
      } else {
         this.dataWatcher.updateObject(16, (byte)(watchableObjectByte & -5));
      }

      this.setupTamedAI();
   }

   @Override
   public boolean isOnSameTeam(EntityLivingBase entity) {
      if (this.isTamed()) {
         EntityLivingBase ownerEntity = this.getOwnerEntity();
         if (entity == ownerEntity) {
            return true;
         }

         if (ownerEntity != null) {
            return ownerEntity.isOnSameTeam(entity);
         }
      }

      return super.isOnSameTeam(entity);
   }

   public EntityAISit getAISit() {
      return this.aiSit;
   }

   public void setupTamedAI() {
   }

   @Override
   public void handleStatusUpdate(byte b) {
      if (b == 7) {
         this.playTameEffect(true);
      } else if (b == 6) {
         this.playTameEffect(false);
      } else {
         super.handleStatusUpdate(b);
      }
   }
}
