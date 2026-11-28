package net.minecraft.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityCreeper extends EntityMob {
   private int lastActiveTime;
   private int timeSinceIgnited;
   private int fuseTime = 30;
   private int explosionRadius = 3;
   private int droppedSkulls = 0;

   @Override
   public String getDeathSound() {
      return "mob.creeper.death";
   }

   public EntityCreeper(World world) {
      super(world);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAICreeperSwell(this));
      this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 1.0, 1.2));
      this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0, false));
      this.tasks.addTask(5, new EntityAIWander(this, 0.8));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
      this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
   }

   @Override
   public Item getDropItem() {
      return Items.gunpowder;
   }

   @Override
   public void fall(float f, float f2) {
      super.fall(f, f2);
      this.timeSinceIgnited = (int)((float)this.timeSinceIgnited + f * 1.5F);
      if (this.timeSinceIgnited > this.fuseTime - 5) {
         this.timeSinceIgnited = this.fuseTime - 5;
      }
   }

   public void ignite() {
      this.dataWatcher.updateObject(18, (byte)1);
   }

   public void incrementDroppedSkulls() {
      this.droppedSkulls++;
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.dataWatcher.addObject(16, (byte)-1);
      this.dataWatcher.addObject(17, (byte)0);
      this.dataWatcher.addObject(18, (byte)0);
   }

   public boolean isAIEnabled() {
      return this.droppedSkulls < 1 && this.entityWorld.getGameRules().getBoolean("doMobLoot");
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
   }

   @Override
   public void onStruckByLightning(EntityLightningBolt entityLightningBolt) {
      super.onStruckByLightning(entityLightningBolt);
      this.dataWatcher.updateObject(17, (byte)1);
   }

   public float getCreeperFlashIntensity(float f) {
      return ((float)this.lastActiveTime + (float)(this.timeSinceIgnited - this.lastActiveTime) * f) / (float)(this.fuseTime - 2);
   }

   public int getCreeperState() {
      return this.dataWatcher.getWatchableObjectByte(16);
   }

   @Override
   public void onUpdate() {
      if (this.isEntityAlive()) {
         this.lastActiveTime = this.timeSinceIgnited;
         if (this.hasIgnited()) {
            this.setCreeperState(1);
         }

         int creeperState = this.getCreeperState();
         if (creeperState > 0 && this.timeSinceIgnited == 0) {
            this.playSound("creeper.primed", 1.0F, 0.5F);
         }

         this.timeSinceIgnited += creeperState;
         if (this.timeSinceIgnited < 0) {
            this.timeSinceIgnited = 0;
         }

         if (this.timeSinceIgnited >= this.fuseTime) {
            this.timeSinceIgnited = this.fuseTime;
            this.explode();
         }
      }

      super.onUpdate();
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.dataWatcher.updateObject(17, (byte)(tag.getBoolean("powered") ? 1 : 0));
      if (tag.hasKey("Fuse", 99)) {
         this.fuseTime = tag.getShort("Fuse");
      }

      if (tag.hasKey("ExplosionRadius", 99)) {
         this.explosionRadius = tag.getByte("ExplosionRadius");
      }

      if (tag.getBoolean("ignited")) {
         this.ignite();
      }
   }

   @Override
   public int getMaxFallHeight() {
      return this.getAttackTarget() == null ? 3 : 3 + (int)(this.getCurrentHealth() - 1.0F);
   }

   @Override
   public boolean attackEntityAsMob(Entity entity) {
      return true;
   }

   public void setCreeperState(int n) {
      this.dataWatcher.updateObject(16, (byte)n);
   }

   @Override
   public boolean interact(EntityPlayer player) {
      ItemStack currentItem = player.inventory.getCurrentItem();
      if (currentItem != null && currentItem.getItem() == Items.flint_and_steel) {
         this.entityWorld.playSoundEffect(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5, "fire.ignite", 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
         player.swingItem();
         if (!this.entityWorld.isRemote) {
            this.ignite();
            currentItem.damageItem(1, player);
            return true;
         }
      }

      return super.interact(player);
   }

   public boolean hasIgnited() {
      return this.dataWatcher.getWatchableObjectByte(18) != 0;
   }

   public boolean getPowered() {
      return this.dataWatcher.getWatchableObjectByte(17) == 1;
   }

   @Override
   public String getHurtSound() {
      return "mob.creeper.say";
   }

   private void explode() {
      if (!this.entityWorld.isRemote) {
         boolean flag = this.entityWorld.getGameRules().getBoolean("mobGriefing");
         float f = this.getPowered() ? 2.0F : 1.0F;
         this.entityWorld.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius * f, flag);
         this.setDead();
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      if (this.dataWatcher.getWatchableObjectByte(17) == 1) {
         tag.setBoolean("powered", true);
      }

      tag.setShort("Fuse", (short)this.fuseTime);
      tag.setByte("ExplosionRadius", (byte)this.explosionRadius);
      tag.setBoolean("ignited", this.hasIgnited());
   }

   @Override
   public void onDeath(DamageSource damageSource) {
      super.onDeath(damageSource);
      if (damageSource.getEntity() instanceof EntitySkeleton) {
         int idFromItem = Item.getIdFromItem(Items.record_13);
         int idFromItem2 = Item.getIdFromItem(Items.record_wait);
         this.dropItem(Item.getItemById(idFromItem + this.rand.nextInt(idFromItem2 - idFromItem + 1)), 1);
      } else if (damageSource.getEntity() instanceof EntityCreeper
         && damageSource.getEntity() != this
         && ((EntityCreeper)damageSource.getEntity()).getPowered()
         && ((EntityCreeper)damageSource.getEntity()).isAIEnabled()) {
         ((EntityCreeper)damageSource.getEntity()).incrementDroppedSkulls();
         this.entityDropItem(new ItemStack(Items.skull, 1, 4), 0.0F);
      }
   }
}
