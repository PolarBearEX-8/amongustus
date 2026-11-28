package net.minecraft.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityChicken extends EntityAnimal {
   public float wingRotation;
   public float destPos;
   public float prevDestPos;
   public float prevWingRotation;
   public float wingRotDelta = 1.0F;
   public int timeUntilNextEgg;
   public boolean chickenJockey;

   public EntityChicken createChild(EntityAgeable entityAgeable) {
      return new EntityChicken(this.entityWorld);
   }

   @Override
   public String getDeathSound() {
      return "mob.chicken.hurt";
   }

   public void setChickenJockey(boolean flag) {
      this.chickenJockey = flag;
   }

   @Override
   public EntityAgeable createAgeableChild(EntityAgeable entityAgeable) {
      return this.createChild(entityAgeable);
   }

   @Override
   public boolean isBreedingItem(ItemStack stack) {
      return stack != null && stack.getItem() == Items.wheat_seeds;
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.prevWingRotation = this.wingRotation;
      this.prevDestPos = this.destPos;
      this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3);
      this.destPos = MathHelper.clamp_float(this.destPos, 0.0F, 1.0F);
      if (!this.onGround && this.wingRotDelta < 1.0F) {
         this.wingRotDelta = 1.0F;
      }

      this.wingRotDelta = (float)((double)this.wingRotDelta * 0.9);
      if (!this.onGround && this.motionY < 0.0) {
         this.motionY *= 0.6;
      }

      this.wingRotation = this.wingRotation + this.wingRotDelta * 2.0F;
      if (!this.entityWorld.isRemote && !this.isChild() && !this.isChickenJockey() && --this.timeUntilNextEgg <= 0) {
         this.playSound("mob.chicken.plop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
         this.dropItem(Items.egg, 1);
         this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
      }
   }

   @Override
   public void dropFewItems(boolean flag, int n) {
      int n2 = this.rand.nextInt(3) + this.rand.nextInt(1 + n);

      for (int i = 0; i < n2; i++) {
         this.dropItem(Items.feather, 1);
      }

      if (this.isBurning()) {
         this.dropItem(Items.cooked_chicken, 1);
      } else {
         this.dropItem(Items.chicken, 1);
      }
   }

   @Override
   public void updateRiderPosition() {
      super.updateRiderPosition();
      float f = MathHelper.sin(this.renderYawOffset * (float) Math.PI / 180.0F);
      float f2 = MathHelper.cos(this.renderYawOffset * (float) Math.PI / 180.0F);
      float f3 = 0.1F;
      float f4 = 0.0F;
      this.riddenByEntity
         .setPosition(
            this.posX + (double)(f3 * f),
            this.posY + (double)(this.height * 0.5F) + this.riddenByEntity.getYOffset() + (double)f4,
            this.posZ - (double)(f3 * f2)
         );
      if (this.riddenByEntity instanceof EntityLivingBase) {
         ((EntityLivingBase)this.riddenByEntity).renderYawOffset = this.renderYawOffset;
      }
   }

   @Override
   public int getExperiencePoints(EntityPlayer player) {
      return this.isChickenJockey() ? 10 : super.getExperiencePoints(player);
   }

   public EntityChicken(World world) {
      super(world);
      this.setSize(0.4F, 0.7F);
      this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIPanic(this, 1.4));
      this.tasks.addTask(2, new EntityAIMate(this, 1.0));
      this.tasks.addTask(3, new EntityAITempt(this, 1.0, Items.wheat_seeds, false));
      this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1));
      this.tasks.addTask(5, new EntityAIWander(this, 1.0));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.tasks.addTask(7, new EntityAILookIdle(this));
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setBoolean("IsChickenJockey", this.chickenJockey);
      tag.setInteger("EggLayTime", this.timeUntilNextEgg);
   }

   @Override
   public float getEyeHeight() {
      return this.height;
   }

   @Override
   public Item getDropItem() {
      return Items.feather;
   }

   @Override
   public void fall(float f, float f2) {
   }

   public boolean isChickenJockey() {
      return this.chickenJockey;
   }

   @Override
   public String getLivingSound() {
      return "mob.chicken.say";
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.chickenJockey = tag.getBoolean("IsChickenJockey");
      if (tag.hasKey("EggLayTime")) {
         this.timeUntilNextEgg = tag.getInteger("EggLayTime");
      }
   }

   @Override
   public boolean canDespawn() {
      return this.isChickenJockey() && this.riddenByEntity == null;
   }

   @Override
   public void playStepSound(BlockPos pos, Block block) {
      this.playSound("mob.chicken.step", 0.15F, 1.0F);
   }

   @Override
   public String getHurtSound() {
      return "mob.chicken.hurt";
   }
}
