package net.minecraft.entity.item;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityXPOrb extends Entity {
   public int xpColor;
   public int xpOrbAge;
   public int delayBeforeCanPickup;
   private int xpOrbHealth = 5;
   private int xpValue;
   private EntityPlayer closestPlayer;
   private int xpTargetColor;

   @Override
   public int getBrightnessForRender(float f) {
      float f2 = 0.5F;
      f2 = MathHelper.clamp_float(f2, 0.0F, 1.0F);
      int brightnessForRender = super.getBrightnessForRender(f);
      int n = brightnessForRender & 0xFF;
      int n2 = brightnessForRender >> 16 & 0xFF;
      n += (int)(f2 * 15.0F * 16.0F);
      if (n > 240) {
         n = 240;
      }

      return n | n2 << 16;
   }

   @Override
   public void onCollideWithPlayer(EntityPlayer player) {
      if (!this.entityWorld.isRemote && this.delayBeforeCanPickup == 0 && player.xpCooldown == 0) {
         player.xpCooldown = 2;
         this.entityWorld.playSoundAtEntity(player, "random.orb", 0.1F, 0.5F * ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.8F));
         player.onItemPickup(this, 1);
         player.addExperience(this.xpValue);
         this.setDead();
      }
   }

   public int getXpValue() {
      return this.xpValue;
   }

   public EntityXPOrb(World world, double d, double d2, double d3, int n) {
      super(world);
      this.setSize(0.5F, 0.5F);
      this.setPosition(d, d2, d3);
      this.rotationYaw = (float)(Math.random() * 360.0);
      this.motionX = (double)((float)(Math.random() * 0.2F - 0.1F) * 2.0F);
      this.motionY = (double)((float)(Math.random() * 0.2) * 2.0F);
      this.motionZ = (double)((float)(Math.random() * 0.2F - 0.1F) * 2.0F);
      this.xpValue = n;
   }

   @Override
   public boolean canAttackWithItem() {
      return false;
   }

   public EntityXPOrb(World world) {
      super(world);
      this.setSize(0.25F, 0.25F);
   }

   public int getTextureByXP() {
      return this.xpValue >= 2477
         ? 10
         : (
            this.xpValue >= 1237
               ? 9
               : (
                  this.xpValue >= 617
                     ? 8
                     : (
                        this.xpValue >= 307
                           ? 7
                           : (
                              this.xpValue >= 149
                                 ? 6
                                 : (
                                    this.xpValue >= 73
                                       ? 5
                                       : (this.xpValue >= 37 ? 4 : (this.xpValue >= 17 ? 3 : (this.xpValue >= 7 ? 2 : (this.xpValue >= 3 ? 1 : 0))))
                                 )
                           )
                     )
               )
         );
   }

   @Override
   public void entityInit() {
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.delayBeforeCanPickup > 0) {
         this.delayBeforeCanPickup--;
      }

      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.motionY -= 0.03F;
      if (this.entityWorld.getBlockState(new BlockPos(this)).getBlock().getMaterial() == Material.lava) {
         this.motionY = 0.2F;
         this.motionX = (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
         this.motionZ = (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
         this.playSound("random.fizz", 0.4F, 2.0F + this.rand.nextFloat() * 0.4F);
      }

      this.pushOutOfBlocks(this.posX, (this.getEntityBoundingBox().minY + this.getEntityBoundingBox().maxY) / 2.0, this.posZ);
      double d = 8.0;
      if (this.xpTargetColor < this.xpColor - 20 + this.getEntityId() % 100) {
         if (this.closestPlayer == null || this.closestPlayer.getDistanceSqToEntity(this) > d * d) {
            this.closestPlayer = this.entityWorld.getClosestPlayerToEntity(this, d);
         }

         this.xpTargetColor = this.xpColor;
      }

      if (this.closestPlayer != null && this.closestPlayer.isSpectator()) {
         this.closestPlayer = null;
      }

      if (this.closestPlayer != null) {
         double d2 = (this.closestPlayer.posX - this.posX) / d;
         double d3 = (this.closestPlayer.posY + (double)this.closestPlayer.getEyeHeight() - this.posY) / d;
         double d4 = (this.closestPlayer.posZ - this.posZ) / d;
         double d5 = Math.sqrt(d2 * d2 + d3 * d3 + d4 * d4);
         double d6 = 1.0 - d5;
         if (d6 > 0.0) {
            d6 *= d6;
            this.motionX += d2 / d5 * d6 * 0.1;
            this.motionY += d3 / d5 * d6 * 0.1;
            this.motionZ += d4 / d5 * d6 * 0.1;
         }
      }

      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      float f = 0.98F;
      if (this.onGround) {
         f = this.entityWorld
               .getBlockState(
                  new BlockPos(
                     MathHelper.floor_double(this.posX), MathHelper.floor_double(this.getEntityBoundingBox().minY) - 1, MathHelper.floor_double(this.posZ)
                  )
               )
               .getBlock()
               .slipperiness
            * 0.98F;
      }

      this.motionX *= (double)f;
      this.motionY *= 0.98F;
      this.motionZ *= (double)f;
      if (this.onGround) {
         this.motionY *= -0.9F;
      }

      this.xpColor++;
      this.xpOrbAge++;
      if (this.xpOrbAge >= 6000) {
         this.setDead();
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      this.xpOrbHealth = tag.getShort("Health") & 255;
      this.xpOrbAge = tag.getShort("Age");
      this.xpValue = tag.getShort("Value");
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      tag.setShort("Health", (short)((byte)this.xpOrbHealth));
      tag.setShort("Age", (short)this.xpOrbAge);
      tag.setShort("Value", (short)this.xpValue);
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else {
         this.setBeenAttacked();
         this.xpOrbHealth = (int)((float)this.xpOrbHealth - f);
         if (this.xpOrbHealth <= 0) {
            this.setDead();
         }

         return false;
      }
   }

   @Override
   public boolean handleWaterMovement() {
      return this.entityWorld.handleMaterialAcceleration(this.getEntityBoundingBox(), Material.water, this);
   }

   public static int getXPSplit(int n) {
      return n >= 2477
         ? 2477
         : (
            n >= 1237
               ? 1237
               : (
                  n >= 617
                     ? 617
                     : (
                        n >= 307
                           ? 307
                           : (n >= 149 ? 149 : (n >= 73 ? 73 : (n >= 37 ? 37 : (n >= 17 ? 17 : (n >= 7 ? 7 : (n >= 3 ? 3 : 1))))))
                     )
               )
         );
   }

   @Override
   public void dealFireDamage(int n) {
      this.attackEntityFrom(DamageSource.inFire, (float)n);
   }

   @Override
   public boolean canTriggerWalking() {
      return false;
   }
}
