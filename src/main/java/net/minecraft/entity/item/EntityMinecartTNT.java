package net.minecraft.entity.item;

import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityMinecartTNT extends EntityMinecart {
   private int minecartTNTFuse = -1;

   @Override
   public void handleStatusUpdate(byte b) {
      if (b == 10) {
         this.ignite();
      } else {
         super.handleStatusUpdate(b);
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setInteger("TNTFuse", this.minecartTNTFuse);
   }

   public void explodeCart(double d) {
      if (!this.entityWorld.isRemote) {
         double d2 = Math.sqrt(d);
         if (d2 > 5.0) {
            d2 = 5.0;
         }

         this.entityWorld.createExplosion(this, this.posX, this.posY, this.posZ, (float)(4.0 + this.rand.nextDouble() * 1.5 * d2), true);
         this.setDead();
      }
   }

   @Override
   public void onActivatorRailPass(int n, int n2, int n3, boolean flag) {
      if (flag && this.minecartTNTFuse < 0) {
         this.ignite();
      }
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.minecartTNTFuse > 0) {
         this.minecartTNTFuse--;
         this.entityWorld.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5, this.posZ, 0.0, 0.0, 0.0, new int[0]);
      } else if (this.minecartTNTFuse == 0) {
         this.explodeCart(this.motionX * this.motionX + this.motionZ * this.motionZ);
      }

      if (this.isCollidedHorizontally) {
         double d = this.motionX * this.motionX + this.motionZ * this.motionZ;
         if (d >= 0.01F) {
            this.explodeCart(d);
         }
      }
   }

   @Override
   public float getExplosionResistance(Explosion explosion, World world, BlockPos pos, IBlockState state) {
      return this.isIgnited() && (BlockRailBase.isRailBlock(state) || BlockRailBase.isRailBlock(world, pos.up()))
         ? 0.0F
         : super.getExplosionResistance(explosion, world, pos, state);
   }

   @Override
   public void fall(float f, float f2) {
      if (f >= 3.0F) {
         float f3 = f / 10.0F;
         this.explodeCart((double)(f3 * f3));
      }

      super.fall(f, f2);
   }

   @Override
   public void killMinecart(DamageSource damageSource) {
      super.killMinecart(damageSource);
      double d = this.motionX * this.motionX + this.motionZ * this.motionZ;
      if (!damageSource.isExplosion() && this.entityWorld.getGameRules().getBoolean("doEntityDrops")) {
         this.entityDropItem(new ItemStack(Blocks.tnt, 1), 0.0F);
      }

      if (damageSource.isFireDamage() || damageSource.isExplosion() || d >= 0.01F) {
         this.explodeCart(d);
      }
   }

   @Override
   public boolean verifyExplosion(Explosion explosion, World world, BlockPos pos, IBlockState state, float f) {
      return this.isIgnited() && (BlockRailBase.isRailBlock(state) || BlockRailBase.isRailBlock(world, pos.up()))
         ? false
         : super.verifyExplosion(explosion, world, pos, state, f);
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      Entity sourceOfDamage = damageSource.getSourceOfDamage();
      if (sourceOfDamage instanceof EntityArrow) {
         EntityArrow entityArrow = (EntityArrow)sourceOfDamage;
         if (entityArrow.isBurning()) {
            this.explodeCart(entityArrow.motionX * entityArrow.motionX + entityArrow.motionY * entityArrow.motionY + entityArrow.motionZ * entityArrow.motionZ);
         }
      }

      return super.attackEntityFrom(damageSource, f);
   }

   @Override
   public IBlockState getDefaultDisplayTile() {
      return Blocks.tnt.getDefaultState();
   }

   public EntityMinecartTNT(World world) {
      super(world);
   }

   public EntityMinecartTNT(World world, double d, double d2, double d3) {
      super(world, d, d2, d3);
   }

   public int getFuseTicks() {
      return this.minecartTNTFuse;
   }

   public boolean isIgnited() {
      return this.minecartTNTFuse > -1;
   }

   @Override
   public EntityMinecart.EnumMinecartType getMinecartType() {
      return EntityMinecart.EnumMinecartType.TNT;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      if (tag.hasKey("TNTFuse", 99)) {
         this.minecartTNTFuse = tag.getInteger("TNTFuse");
      }
   }

   public void ignite() {
      this.minecartTNTFuse = 80;
      if (!this.entityWorld.isRemote) {
         this.entityWorld.setEntityState(this, (byte)10);
         if (!this.isSilent()) {
            this.entityWorld.playSoundAtEntity(this, "game.tnt.primed", 1.0F, 1.0F);
         }
      }
   }
}
