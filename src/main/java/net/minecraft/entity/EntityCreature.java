package net.minecraft.entity;

import java.util.UUID;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class EntityCreature extends EntityLiving {
   public static final UUID FLEEING_SPEED_MODIFIER_UUID = UUID.fromString("E199AD21-BA8A-4C53-8D13-6182D5C69D3A");
   public static final AttributeModifier FLEEING_SPEED_MODIFIER = new AttributeModifier(FLEEING_SPEED_MODIFIER_UUID, "Fleeing speed bonus", 2.0, 2)
      .setSaved(false);
   private BlockPos homePosition = BlockPos.ORIGIN;
   private float maximumHomeDistance = -1.0F;
   private EntityAIBase aiBase = new EntityAIMoveTowardsRestriction(this, 1.0);
   private boolean isMovementAITaskSet;

   public float getBlockPathWeight(BlockPos pos) {
      return 0.0F;
   }

   public boolean hasHome() {
      return this.maximumHomeDistance != -1.0F;
   }

   public float getMaximumHomeDistance() {
      return this.maximumHomeDistance;
   }

   public boolean hasPath() {
      return !this.navigator.noPath();
   }

   @Override
   public boolean getCanSpawnHere() {
      return super.getCanSpawnHere() && this.getBlockPathWeight(new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ)) >= 0.0F;
   }

   public boolean isWithinHomeDistanceFromPosition(BlockPos pos) {
      return this.maximumHomeDistance == -1.0F ? true : this.homePosition.distanceSq(pos) < (double)(this.maximumHomeDistance * this.maximumHomeDistance);
   }

   public EntityCreature(World world) {
      super(world);
   }

   public boolean isWithinHomeDistanceCurrentPosition() {
      return this.isWithinHomeDistanceFromPosition(new BlockPos(this));
   }

   @Override
   public void updateLeashedState() {
      super.updateLeashedState();
      if (this.getLeashed() && this.getLeashedToEntity() != null && this.getLeashedToEntity().entityWorld == this.entityWorld) {
         Entity leashedToEntity2 = this.getLeashedToEntity();
         this.setHomePosAndDistance(new BlockPos((int)leashedToEntity2.posX, (int)leashedToEntity2.posY, (int)leashedToEntity2.posZ), 5);
         float distanceToEntity = this.getDistanceToEntity(leashedToEntity2);
         if (this instanceof EntityTameable && ((EntityTameable)this).isSitting()) {
            if (distanceToEntity > 10.0F) {
               this.clearLeashed(true, true);
            }

            return;
         }

         if (!this.isMovementAITaskSet) {
            this.tasks.addTask(2, this.aiBase);
            if (this.getNavigator() instanceof PathNavigateGround) {
               ((PathNavigateGround)this.getNavigator()).setAvoidsWater(false);
            }

            this.isMovementAITaskSet = true;
         }

         this.onLeashDistance(distanceToEntity);
         if (distanceToEntity > 4.0F) {
            this.getNavigator().tryMoveToEntityLiving(leashedToEntity2, 1.0);
         }

         if (distanceToEntity > 6.0F) {
            double d = (leashedToEntity2.posX - this.posX) / (double)distanceToEntity;
            double d2 = (leashedToEntity2.posY - this.posY) / (double)distanceToEntity;
            double d3 = (leashedToEntity2.posZ - this.posZ) / (double)distanceToEntity;
            this.motionX = this.motionX + d * Math.abs(d) * 0.4;
            this.motionY = this.motionY + d2 * Math.abs(d2) * 0.4;
            this.motionZ = this.motionZ + d3 * Math.abs(d3) * 0.4;
         }

         if (distanceToEntity > 10.0F) {
            this.clearLeashed(true, true);
         }
      } else if (!this.getLeashed() && this.isMovementAITaskSet) {
         this.isMovementAITaskSet = false;
         this.tasks.removeTask(this.aiBase);
         if (this.getNavigator() instanceof PathNavigateGround) {
            ((PathNavigateGround)this.getNavigator()).setAvoidsWater(true);
         }

         this.detachHome();
      }
   }

   public BlockPos getHomePosition() {
      return this.homePosition;
   }

   public void onLeashDistance(float f) {
   }

   public void setHomePosAndDistance(BlockPos pos, int n) {
      this.homePosition = pos;
      this.maximumHomeDistance = (float)n;
   }

   public void detachHome() {
      this.maximumHomeDistance = -1.0F;
   }
}
