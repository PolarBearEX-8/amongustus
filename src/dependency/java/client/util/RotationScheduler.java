package client.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class RotationScheduler extends RotationPool {
   public HitBoxAdapter hitBoxAdapter;
   public EntityLivingBase entityLivingBase;
   public World world;
   public double value;
   public PathfindManager pathfindManager;
   public int count2;
   public float factor;
   public float factor2;
   public boolean enabled;

   @Override
   public void updateState2() {
      this.hitBoxAdapter.getEntityRavagerAdapter().applyEntityByEntity(this.entityLivingBase, 10.0F, (float)this.hitBoxAdapter.getCount8());
      if (!this.hitBoxAdapter.isActive33() && --this.count2 <= 0) {
         this.count2 = 10;
         if (!this.pathfindManager.matchesEntityByEntity(this.entityLivingBase, this.value)
            && !this.hitBoxAdapter.isEnabled23()
            && this.hitBoxAdapter.getCoordinate(this.entityLivingBase) >= 144.0) {
            int count = MathHelper.ceiling_double_int(this.entityLivingBase.posX) - 2;
            int count3 = MathHelper.ceiling_double_int(this.entityLivingBase.posZ) - 2;
            int count4 = MathHelper.ceiling_double_int(this.entityLivingBase.getBoundingBox2().minY);

            for (int index = 0; index <= 4; index++) {
               for (int index2 = 0; index2 <= 4; index2++) {
                  if ((index < 1 || index2 < 1 || index > 3 || index2 > 3)
                     && World.matchesIBlockAccess(this.world, count + index, count4 - 1, count3 + index2)
                     && this.matchesBlockPos(new BlockPos(count + index, count4, count3 + index2))
                     && this.matchesBlockPos(new BlockPos(count + index, count4 + 1, count3 + index2))) {
                     this.hitBoxAdapter
                        .applyDoubleByDouble(
                           (double)((float)(count + index) + 0.5F),
                           (double)count4,
                           (double)((float)(count3 + index2) + 0.5F),
                           this.hitBoxAdapter.rotationYaw,
                           this.hitBoxAdapter.rotationPitch
                        );
                     this.pathfindManager.updateState4();
                     return;
                  }
               }
            }
         }
      }
   }

   @Override
   public void updateState() {
      this.entityLivingBase = null;
      this.pathfindManager.updateState4();
      ((PathfindManagerAdapter)this.hitBoxAdapter.getPathfindManager()).setEnabled3(true);
   }

   @Override
   public boolean isActive() {
      EntityLivingBase entityLivingBase2 = this.hitBoxAdapter.getEntityLivingBase7();
      if (entityLivingBase2 == null) {
         return false;
      } else if (entityLivingBase2 instanceof EntityPlayer && ((EntityPlayer)entityLivingBase2).isActive23()) {
         return false;
      } else if (this.hitBoxAdapter.isActive33()) {
         return false;
      } else if (this.hitBoxAdapter.getCoordinate(entityLivingBase2) < (double)(this.factor2 * this.factor2)) {
         return false;
      } else {
         this.entityLivingBase = entityLivingBase2;
         return true;
      }
   }

   @Override
   public void updateState3() {
      this.count2 = 0;
      this.enabled = ((PathfindManagerAdapter)this.hitBoxAdapter.getPathfindManager()).isActive4();
      ((PathfindManagerAdapter)this.hitBoxAdapter.getPathfindManager()).setEnabled3(false);
   }

   private boolean matchesBlockPos(BlockPos blockPos) {
      Block block = this.world.getBlockState(blockPos).getBlock();
      return block == Blocks.air ? true : !block.processAction14();
   }

   @Override
   public boolean isActiveAsFlag() {
      return !this.pathfindManager.isActive2()
         && this.hitBoxAdapter.getCoordinate(this.entityLivingBase) > (double)(this.factor * this.factor)
         && !this.hitBoxAdapter.isActive33();
   }

   public RotationScheduler(HitBoxAdapter hitBoxAdapter2, double offset, float factor3, float factor4) {
      this.hitBoxAdapter = hitBoxAdapter2;
      this.world = hitBoxAdapter2.worldObj;
      this.value = offset;
      this.pathfindManager = hitBoxAdapter2.getPathfindManager();
      this.factor2 = factor3;
      this.factor = factor4;
      this.setCount(3);
      if (!(hitBoxAdapter2.getPathfindManager() instanceof PathfindManagerAdapter)) {
         throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
      }
   }
}
