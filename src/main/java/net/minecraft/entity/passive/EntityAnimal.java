package net.minecraft.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityAnimal extends EntityAgeable implements IAnimals {
   public Block spawnableBlock = Blocks.grass;
   private int inLove;
   private EntityPlayer playerInLove;

   @Override
   public boolean getCanSpawnHere() {
      int n = MathHelper.floor_double(this.posX);
      int n2 = MathHelper.floor_double(this.getEntityBoundingBox().minY);
      int n3 = MathHelper.floor_double(this.posZ);
      return this.entityWorld.getBlockState2(n, n2 - 1, n3).getBlock() == this.spawnableBlock && this.entityWorld.getLightFromNeighbors(n, n2, n3) > 8 && super.getCanSpawnHere();
   }

   @Override
   public float getBlockPathWeight(BlockPos pos) {
      return this.entityWorld.getBlockState2(pos.getX(), pos.getY() - 1, pos.getZ()).getBlock() == Blocks.grass
         ? 10.0F
         : this.entityWorld.getLightBrightness(pos.getX(), pos.getY(), pos.getZ()) - 0.5F;
   }

   public EntityPlayer getPlayerInLove() {
      return this.playerInLove;
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.getGrowingAge() != 0) {
         this.inLove = 0;
      }

      if (this.inLove > 0) {
         this.inLove--;
         if (this.inLove % 10 == 0) {
            double d = this.rand.nextGaussian() * 0.02;
            double d2 = this.rand.nextGaussian() * 0.02;
            double d3 = this.rand.nextGaussian() * 0.02;
            this.entityWorld
               .spawnParticle(
                  EnumParticleTypes.HEART,
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
   }

   public void consumeItemFromStack(EntityPlayer player, ItemStack stack) {
      if (!player.capabilities.isCreativeMode) {
         stack.stackSize--;
         if (stack.stackSize <= 0) {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
         }
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else {
         this.inLove = 0;
         return super.attackEntityFrom(damageSource, f);
      }
   }

   @Override
   public void updateAITasks() {
      if (this.getGrowingAge() != 0) {
         this.inLove = 0;
      }

      super.updateAITasks();
   }

   @Override
   public void handleStatusUpdate(byte b) {
      if (b == 18) {
         for (int i = 0; i < 7; i++) {
            double d = this.rand.nextGaussian() * 0.02;
            double d2 = this.rand.nextGaussian() * 0.02;
            double d3 = this.rand.nextGaussian() * 0.02;
            this.entityWorld
               .spawnParticle(
                  EnumParticleTypes.HEART,
                  this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width,
                  this.posY + 0.5 + (double)(this.rand.nextFloat() * this.height),
                  this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width,
                  d,
                  d2,
                  d3,
                  new int[0]
               );
         }
      } else {
         super.handleStatusUpdate(b);
      }
   }

   public void setInLove(EntityPlayer player) {
      this.inLove = 600;
      this.playerInLove = player;
      this.entityWorld.setEntityState(this, (byte)18);
   }

   public EntityAnimal(World world) {
      super(world);
   }

   @Override
   public boolean canDespawn() {
      return false;
   }

   public boolean isBreedingItem(ItemStack stack) {
      return stack == null ? false : stack.getItem() == Items.wheat;
   }

   @Override
   public int getExperiencePoints(EntityPlayer player) {
      return 1 + this.entityWorld.rand.nextInt(3);
   }

   public void resetInLove() {
      this.inLove = 0;
   }

   @Override
   public boolean interact(EntityPlayer player) {
      ItemStack currentItem = player.inventory.getCurrentItem();
      if (currentItem != null) {
         if (this.isBreedingItem(currentItem) && this.getGrowingAge() == 0 && this.inLove <= 0) {
            this.consumeItemFromStack(player, currentItem);
            this.setInLove(player);
            return true;
         }

         if (this.isChild() && this.isBreedingItem(currentItem)) {
            this.consumeItemFromStack(player, currentItem);
            this.ageUp((int)((float)(-this.getGrowingAge() / 20) * 0.1F), true);
            return true;
         }
      }

      return super.interact(player);
   }

   public boolean canMateWith(EntityAnimal entityAnimal) {
      return entityAnimal == this ? false : (entityAnimal.getClass() != this.getClass() ? false : this.isInLove() && entityAnimal.isInLove());
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setInteger("InLove", this.inLove);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.inLove = tag.getInteger("InLove");
   }

   public boolean isInLove() {
      return this.inLove > 0;
   }

   @Override
   public int getTalkInterval() {
      return 120;
   }
}
