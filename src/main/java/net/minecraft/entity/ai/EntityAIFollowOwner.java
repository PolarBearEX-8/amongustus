package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAIFollowOwner extends EntityAIBase {
   private EntityTameable thePet;
   private EntityLivingBase theOwner;
   public World theWorld;
   private double followSpeed;
   private PathNavigate petPathfinder;
   private int timeToRecalcPath;
   public float maxDist;
   public float minDist;
   private boolean avoidsWater;

   @Override
   public void startExecuting() {
      this.timeToRecalcPath = 0;
      this.avoidsWater = ((PathNavigateGround)this.thePet.getNavigator()).getAvoidsWater();
      ((PathNavigateGround)this.thePet.getNavigator()).setAvoidsWater(false);
   }

   private boolean isEmptyBlock(BlockPos pos) {
      Block block = this.theWorld.getBlockState(pos).getBlock();
      return block == Blocks.air ? true : !block.isFullCube();
   }

   public EntityAIFollowOwner(EntityTameable entityTameable, double d, float f, float f2) {
      this.thePet = entityTameable;
      this.theWorld = entityTameable.entityWorld;
      this.followSpeed = d;
      this.petPathfinder = entityTameable.getNavigator();
      this.minDist = f;
      this.maxDist = f2;
      this.setMutexBits(3);
      if (!(entityTameable.getNavigator() instanceof PathNavigateGround)) {
         throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
      }
   }

   @Override
   public void updateTask() {
      this.thePet.getLookHelper().setLookPositionWithEntity(this.theOwner, 10.0F, (float)this.thePet.getVerticalFaceSpeed());
      if (!this.thePet.isSitting() && --this.timeToRecalcPath <= 0) {
         this.timeToRecalcPath = 10;
         if (!this.petPathfinder.tryMoveToEntityLiving(this.theOwner, this.followSpeed)
            && !this.thePet.getLeashed()
            && this.thePet.getDistanceSqToEntity(this.theOwner) >= 144.0) {
            int n = MathHelper.floor_double(this.theOwner.posX) - 2;
            int n2 = MathHelper.floor_double(this.theOwner.posZ) - 2;
            int n3 = MathHelper.floor_double(this.theOwner.getEntityBoundingBox().minY);

            for (int i = 0; i <= 4; i++) {
               for (int j = 0; j <= 4; j++) {
                  if ((i < 1 || j < 1 || i > 3 || j > 3)
                     && World.doesBlockHaveSolidTopSurface(this.theWorld, n + i, n3 - 1, n2 + j)
                     && this.isEmptyBlock(new BlockPos(n + i, n3, n2 + j))
                     && this.isEmptyBlock(new BlockPos(n + i, n3 + 1, n2 + j))) {
                     this.thePet
                        .setLocationAndAngles(
                           (double)((float)(n + i) + 0.5F),
                           (double)n3,
                           (double)((float)(n2 + j) + 0.5F),
                           this.thePet.rotationYaw,
                           this.thePet.rotationPitch
                        );
                     this.petPathfinder.clearPathEntity();
                     return;
                  }
               }
            }
         }
      }
   }

   @Override
   public void resetTask() {
      this.theOwner = null;
      this.petPathfinder.clearPathEntity();
      ((PathNavigateGround)this.thePet.getNavigator()).setAvoidsWater(true);
   }

   @Override
   public boolean shouldExecute() {
      EntityLivingBase ownerEntity = this.thePet.getOwnerEntity();
      if (ownerEntity == null) {
         return false;
      } else if (ownerEntity instanceof EntityPlayer && ((EntityPlayer)ownerEntity).isSpectator()) {
         return false;
      } else if (this.thePet.isSitting()) {
         return false;
      } else if (this.thePet.getDistanceSqToEntity(ownerEntity) < (double)(this.minDist * this.minDist)) {
         return false;
      } else {
         this.theOwner = ownerEntity;
         return true;
      }
   }

   @Override
   public boolean continueExecuting() {
      return !this.petPathfinder.noPath()
         && this.thePet.getDistanceSqToEntity(this.theOwner) > (double)(this.maxDist * this.maxDist)
         && !this.thePet.isSitting();
   }
}
