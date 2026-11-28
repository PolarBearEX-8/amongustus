package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;

public class EntityEnderCrystal extends Entity {
   public int innerRotation;
   public int health;

   public EntityEnderCrystal(World world, double d, double d2, double d3) {
      this(world);
      this.setPosition(d, d2, d3);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else {
         if (!this.isDead && !this.entityWorld.isRemote) {
            this.health = 0;
            if (this.health <= 0) {
               this.setDead();
               if (!this.entityWorld.isRemote) {
                  this.entityWorld.createExplosion((Entity)null, this.posX, this.posY, this.posZ, 6.0F, true);
               }
            }
         }

         return true;
      }
   }

   @Override
   public boolean canTriggerWalking() {
      return false;
   }

   @Override
   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.innerRotation++;
      this.dataWatcher.updateObject(8, this.health);
      int n = MathHelper.floor_double(this.posX);
      int n2 = MathHelper.floor_double(this.posY);
      int n3 = MathHelper.floor_double(this.posZ);
      if (this.entityWorld.provider instanceof WorldProviderEnd && this.entityWorld.getBlockState(new BlockPos(n, n2, n3)).getBlock() != Blocks.fire) {
         this.entityWorld.setBlockState(new BlockPos(n, n2, n3), Blocks.fire.getDefaultState());
      }
   }

   @Override
   public boolean canBeCollidedWith() {
      return true;
   }

   @Override
   public void entityInit() {
      this.dataWatcher.addObject(8, this.health);
   }

   public EntityEnderCrystal(World world) {
      super(world);
      this.preventEntitySpawning = true;
      this.setSize(2.0F, 2.0F);
      this.health = 5;
      this.innerRotation = this.rand.nextInt(100000);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
   }
}
