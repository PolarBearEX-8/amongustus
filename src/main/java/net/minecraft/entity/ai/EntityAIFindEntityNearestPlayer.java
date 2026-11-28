package net.minecraft.entity.ai;

import net.minecraft.entity.Entity;
import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityAIFindEntityNearestPlayer extends EntityAIBase {
   private static final Logger LOGGER = LogManager.getLogger();
   private EntityLiving entityLiving;
   private final Predicate predicate;
   private final EntityAINearestAttackableTarget.Sorter sorter;
   private EntityLivingBase entityTarget;

   public static EntityLiving accessEntityLiving(EntityAIFindEntityNearestPlayer player) {
      return player.entityLiving;
   }

   @Override
   public boolean continueExecuting() {
      EntityLivingBase attackTarget = this.entityLiving.getAttackTarget();
      if (attackTarget == null) {
         return false;
      } else if (!attackTarget.isEntityAlive()) {
         return false;
      } else if (attackTarget instanceof EntityPlayer && ((EntityPlayer)attackTarget).capabilities.disableDamage) {
         return false;
      } else {
         Team team = this.entityLiving.getTeam();
         Team team2 = attackTarget.getTeam();
         if (team != null && team2 == team) {
            return false;
         } else {
            double d = this.maxTargetRange();
            return this.entityLiving.getDistanceSqToEntity(attackTarget) > d * d
               ? false
               : !(attackTarget instanceof EntityPlayerMP) || !((EntityPlayerMP)attackTarget).theItemInWorldManager.isCreative();
         }
      }
   }

   public double maxTargetRange() {
      IAttributeInstance entityAttribute = this.entityLiving.getEntityAttribute(SharedMonsterAttributes.followRange);
      return entityAttribute == null ? 16.0 : entityAttribute.getAttributeValue();
   }

   @Override
   public void resetTask() {
      this.entityLiving.setAttackTarget((EntityLivingBase)null);
      super.startExecuting();
   }

   @Override
   public boolean shouldExecute() {
      double d = this.maxTargetRange();
      List entitiesWithinAABB = this.entityLiving.entityWorld.getEntitiesWithinAABB(EntityPlayer.class, this.entityLiving.getEntityBoundingBox().expand(d, 4.0, d), this.predicate);
      Collections.sort(entitiesWithinAABB, this.sorter);
      if (entitiesWithinAABB.isEmpty()) {
         return false;
      } else {
         this.entityTarget = (EntityLivingBase)entitiesWithinAABB.get(0);
         return true;
      }
   }

   public EntityAIFindEntityNearestPlayer(EntityLiving entityLiving2) {
      this.entityLiving = entityLiving2;
      if (entityLiving2 instanceof EntityCreature) {
         LOGGER.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
      }

      this.predicate = new EntityAIFindEntityNearestPlayer.FilterPredicate(this);
      this.sorter = new EntityAINearestAttackableTarget.Sorter(entityLiving2);
   }

   @Override
   public void startExecuting() {
      this.entityLiving.setAttackTarget(this.entityTarget);
      super.startExecuting();
   }


   public static class FilterPredicate implements Predicate {
      public final EntityAIFindEntityNearestPlayer findPlayerTask;

      public FilterPredicate(EntityAIFindEntityNearestPlayer player) {
         this.findPlayerTask = player;
      }

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public boolean apply(Entity entity) {
         if (!(entity instanceof EntityPlayer)) {
            return false;
         } else if (((EntityPlayer)entity).capabilities.disableDamage) {
            return false;
         } else {
            double d = this.findPlayerTask.maxTargetRange();
            if (entity.isSneaking()) {
               d *= 0.8F;
            }

            if (entity.isInvisible()) {
               float armorVisibility = ((EntityPlayer)entity).getArmorVisibility();
               if (armorVisibility < 0.1F) {
                  armorVisibility = 0.1F;
               }

               d *= (double)(0.7F * armorVisibility);
            }

            return (double)entity.getDistanceToEntity(EntityAIFindEntityNearestPlayer.accessEntityLiving(this.findPlayerTask)) > d
               ? false
               : EntityAITarget.isSuitableTarget(EntityAIFindEntityNearestPlayer.accessEntityLiving(this.findPlayerTask), (EntityLivingBase)entity, false, true);
         }
      }
   }
}
