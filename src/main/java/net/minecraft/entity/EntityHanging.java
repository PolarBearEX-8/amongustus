package net.minecraft.entity;

import net.augustus.Augustus;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.apache.commons.lang3.Validate;

public abstract class EntityHanging extends Entity {
   private int tickCounter1;
   public BlockPos hangingPosition;
   public EnumFacing facingDirection;

   public boolean onValidSurface() {
      if (!this.entityWorld.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty()) {
         return false;
      } else {
         int n = Math.max(1, this.getWidthPixels() / 16);
         int n2 = Math.max(1, this.getHeightPixels() / 16);
         BlockPos pos = this.hangingPosition.offset(this.facingDirection.getOpposite());
         EnumFacing facing = this.facingDirection.rotateYCCW();

         for (int i = 0; i < n; i++) {
            for (int j = 0; j < n2; j++) {
               BlockPos pos2 = pos.offset(facing, i).up(j);
               Block block = this.entityWorld.getBlockState(pos2).getBlock();
               if (!block.getMaterial().isSolid() && !BlockRedstoneDiode.isRedstoneRepeaterBlockID(block)) {
                  return false;
               }
            }
         }

         for (Entity entity : (Iterable<Entity>)(this.entityWorld.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()))) {
            if (entity instanceof EntityHanging) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public void entityInit() {
   }

   public void updateFacingWithBoundingBox(EnumFacing facing) {
      Validate.notNull(facing);
      Validate.isTrue(facing.getAxis().isHorizontal());
      this.facingDirection = facing;
      this.prevRotationYaw = this.rotationYaw = (float)(this.facingDirection.getHorizontalIndex() * 90);
      this.updateBoundingBox();
   }

   public abstract int getHeightPixels();

   public EntityHanging(World world) {
       super(world);
      this.setSize(0.5F, 0.5F);
      if (Augustus.getInstance().getModuleManager().exploitFixer.isToggled()) {
         this.facingDirection = EnumFacing.NORTH;
      }
   }

   @Override
   public boolean hitByEntity(Entity entity) {
      return entity instanceof EntityPlayer ? this.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)entity), 0.0F) : false;
   }

   public abstract int getWidthPixels();

   public abstract void onBroken(Entity entity);

   @Override
   public void setPosition(double d, double d2, double d3) {
      this.posX = d;
      this.posY = d2;
      this.posZ = d3;
      BlockPos hangingPosition2 = this.hangingPosition;
      this.hangingPosition = new BlockPos(d, d2, d3);
      if (!this.hangingPosition.equals(hangingPosition2)) {
         this.updateBoundingBox();
         this.isAirBorne = true;
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      tag.setByte("Facing", (byte)this.facingDirection.getHorizontalIndex());
      tag.setInteger("TileX", this.getHangingPosition().getX());
      tag.setInteger("TileY", this.getHangingPosition().getY());
      tag.setInteger("TileZ", this.getHangingPosition().getZ());
   }

   private void updateBoundingBox() {
      if (this.facingDirection != null) {
         double d = (double)this.hangingPosition.getX() + 0.5;
         double d2 = (double)this.hangingPosition.getY() + 0.5;
         double d3 = (double)this.hangingPosition.getZ() + 0.5;
         double d4 = 0.46875;
         double d5 = this.offs(this.getWidthPixels());
         double d6 = this.offs(this.getHeightPixels());
         d -= (double)this.facingDirection.getFrontOffsetX() * 0.46875;
         d3 -= (double)this.facingDirection.getFrontOffsetZ() * 0.46875;
         d2 += d6;
         EnumFacing facing = this.facingDirection.rotateYCCW();
         d += d5 * (double)facing.getFrontOffsetX();
         d3 += d5 * (double)facing.getFrontOffsetZ();
         this.posX = d;
         this.posY = d2;
         this.posZ = d3;
         double widthPixels = (double)this.getWidthPixels();
         double heightPixels = (double)this.getHeightPixels();
         double widthPixels2 = (double)this.getWidthPixels();
         if (this.facingDirection.getAxis() == EnumFacing.Axis.Z) {
            widthPixels2 = 1.0;
         } else {
            widthPixels = 1.0;
         }

         widthPixels /= 32.0;
         heightPixels /= 32.0;
         widthPixels2 /= 32.0;
         this.setEntityBoundingBox(new AxisAlignedBB(d - widthPixels, d2 - heightPixels, d3 - widthPixels2, d + widthPixels, d2 + heightPixels, d3 + widthPixels2));
      }
   }

   public EntityHanging(World world, BlockPos pos) {
      this(world);
      this.hangingPosition = pos;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      this.hangingPosition = new BlockPos(tag.getInteger("TileX"), tag.getInteger("TileY"), tag.getInteger("TileZ"));
      EnumFacing facing;
      if (tag.hasKey("Direction", 99)) {
         facing = EnumFacing.getHorizontal(tag.getByte("Direction"));
         this.hangingPosition = this.hangingPosition.offset(facing);
      } else if (tag.hasKey("Facing", 99)) {
         facing = EnumFacing.getHorizontal(tag.getByte("Facing"));
      } else {
         facing = EnumFacing.getHorizontal(tag.getByte("Dir"));
      }

      this.updateFacingWithBoundingBox(facing);
   }

   @Override
   public boolean canBeCollidedWith() {
      return true;
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else {
         if (!this.isDead && !this.entityWorld.isRemote) {
            this.setDead();
            this.setBeenAttacked();
            this.onBroken(damageSource.getEntity());
         }

         return true;
      }
   }

   @Override
   public void addVelocity(double d, double d2, double d3) {
      if (!this.entityWorld.isRemote && !this.isDead && d * d + d2 * d2 + d3 * d3 > 0.0) {
         this.setDead();
         this.onBroken((Entity)null);
      }
   }

   @Override
   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.tickCounter1++ == 100 && !this.entityWorld.isRemote) {
         this.tickCounter1 = 0;
         if (!this.isDead && !this.onValidSurface()) {
            this.setDead();
            this.onBroken((Entity)null);
         }
      }
   }

   @Override
   public boolean shouldSetPosAfterLoading() {
      return false;
   }

   private double offs(int n) {
      return n % 32 == 0 ? 0.5 : 0.0;
   }

   public BlockPos getHangingPosition() {
      return this.hangingPosition;
   }

   @Override
   public EnumFacing getHorizontalFacing() {
      return this.facingDirection;
   }

   @Override
   public void moveEntity(double d, double d2, double d3) {
      if (!this.entityWorld.isRemote && !this.isDead && d * d + d2 * d2 + d3 * d3 > 0.0) {
         this.setDead();
         this.onBroken((Entity)null);
      }
   }
}
