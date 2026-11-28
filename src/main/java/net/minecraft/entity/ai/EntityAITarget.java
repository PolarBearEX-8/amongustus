package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.StringUtils;

public abstract class EntityAITarget extends EntityAIBase {
   public final EntityCreature taskOwner;
   public boolean shouldCheckSight;
   private boolean nearbyOnly;
   private int targetSearchStatus;
   private int targetSearchDelay;
   private int targetUnseenTicks;

   public static boolean isSuitableTarget(EntityLiving entityLiving, EntityLivingBase entity, boolean flag, boolean flag2) {
      if (entity == null) {
         return false;
      } else if (entity == entityLiving) {
         return false;
      } else if (!entity.isEntityAlive()) {
         return false;
      } else if (!entityLiving.canAttackClass(entity.getClass())) {
         return false;
      } else {
         Team team = entityLiving.getTeam();
         Team team2 = entity.getTeam();
         if (team != null && team2 == team) {
            return false;
         } else {
            if (entityLiving instanceof IEntityOwnable && StringUtils.isNotEmpty(((IEntityOwnable)entityLiving).getOwnerId())) {
               if (entity instanceof IEntityOwnable && ((IEntityOwnable)entityLiving).getOwnerId().equals(((IEntityOwnable)entity).getOwnerId())) {
                  return false;
               }

               if (entity == ((IEntityOwnable)entityLiving).getOwner()) {
                  return false;
               }
            } else if (entity instanceof EntityPlayer && !flag && ((EntityPlayer)entity).capabilities.disableDamage) {
               return false;
            }

            return !flag2 || entityLiving.getEntitySenses().canSee(entity);
         }
      }
   }

   public EntityAITarget(EntityCreature entityCreature, boolean flag, boolean flag2) {
      this.taskOwner = entityCreature;
      this.shouldCheckSight = flag;
      this.nearbyOnly = flag2;
   }

   private boolean canEasilyReach(EntityLivingBase entity) {
      this.targetSearchDelay = 10 + this.taskOwner.getRNG().nextInt(5);
      PathEntity pathToEntityLiving = this.taskOwner.getNavigator().getPathToEntityLiving(entity);
      if (pathToEntityLiving == null) {
         return false;
      } else {
         PathPoint finalPathPoint = pathToEntityLiving.getFinalPathPoint();
         if (finalPathPoint == null) {
            return false;
         } else {
            int n = finalPathPoint.xCoord - MathHelper.floor_double(entity.posX);
            int n2 = finalPathPoint.zCoord - MathHelper.floor_double(entity.posZ);
            return (double)(n * n + n2 * n2) <= 2.25;
         }
      }
   }

   @Override
   public void startExecuting() {
      this.targetSearchStatus = 0;
      this.targetSearchDelay = 0;
      this.targetUnseenTicks = 0;
   }

   public boolean isSuitableTarget(EntityLivingBase entity, boolean flag) {
      if (!isSuitableTarget(this.taskOwner, entity, flag, this.shouldCheckSight)) {
         return false;
      } else if (!this.taskOwner.isWithinHomeDistanceFromPosition(new BlockPos(entity))) {
         return false;
      } else {
         if (this.nearbyOnly) {
            if (--this.targetSearchDelay <= 0) {
               this.targetSearchStatus = 0;
            }

            if (this.targetSearchStatus == 0) {
               this.targetSearchStatus = this.canEasilyReach(entity) ? 1 : 2;
            }

            if (this.targetSearchStatus == 2) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public void resetTask() {
      this.taskOwner.setAttackTarget((EntityLivingBase)null);
   }

   public double getTargetDistance() {
      IAttributeInstance entityAttribute = this.taskOwner.getEntityAttribute(SharedMonsterAttributes.followRange);
      return entityAttribute == null ? 16.0 : entityAttribute.getAttributeValue();
   }

   @Override
   public boolean continueExecuting() {
      EntityLivingBase attackTarget = this.taskOwner.getAttackTarget();
      if (attackTarget == null) {
         return false;
      } else if (!attackTarget.isEntityAlive()) {
         return false;
      } else {
         Team team = this.taskOwner.getTeam();
         Team team2 = attackTarget.getTeam();
         if (team != null && team2 == team) {
            return false;
         } else {
            double targetDistance = this.getTargetDistance();
            if (this.taskOwner.getDistanceSqToEntity(attackTarget) > targetDistance * targetDistance) {
               return false;
            } else {
               if (this.shouldCheckSight) {
                  if (this.taskOwner.getEntitySenses().canSee(attackTarget)) {
                     this.targetUnseenTicks = 0;
                  } else if (++this.targetUnseenTicks > 60) {
                     return false;
                  }
               }

               return !(attackTarget instanceof EntityPlayer) || !((EntityPlayer)attackTarget).capabilities.disableDamage;
            }
         }
      }
   }

   public EntityAITarget(EntityCreature entityCreature, boolean flag) {
      this(entityCreature, flag, false);
   }
}
