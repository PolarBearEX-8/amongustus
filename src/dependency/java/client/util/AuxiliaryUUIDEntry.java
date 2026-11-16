package client.util;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMob;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class AuxiliaryUUIDEntry extends EntityMob {
   public static final UUID uUID3;
   public static final InvalidOperationManager invalidOperationManager2;
   public BlockPos blockPos2 = BlockPos.origin;
   public float factor39 = -1.0F;
   public RotationPool rotationPool = new CoreClientManagerAdapter(this, 1.0);
   public boolean enabled27;

   public float computeFactor(BlockPos blockPos) {
      return 0.0F;
   }

   public void applyBlockPos(BlockPos blockPos, int count) {
      this.blockPos2 = blockPos;
      this.factor39 = (float)count;
   }

   public boolean matchesBlockPos(BlockPos blockPos) {
      return this.factor39 == -1.0F ? true : this.blockPos2.getCoordinate(blockPos) < (double)(this.factor39 * this.factor39);
   }

   public void applyFloat4(float factor) {
   }

   public BlockPos getBlockPos2() {
      return this.blockPos2;
   }

   @Override
   public void updateState42() {
      super.updateState42();
      if (this.isEnabled23() && this.getEntity6() != null && this.getEntity6().worldObj == this.worldObj) {
         Entity entity = this.getEntity6();
         this.applyBlockPos(new BlockPos((int)entity.posX, (int)entity.posY, (int)entity.posZ), 5);
         float distance = this.getDistanceSqToEntity(entity);
         if (this instanceof HitBoxAdapter && ((HitBoxAdapter)this).isActive33()) {
            if (distance > 10.0F) {
               this.applyFlag(true, true);
            }

            return;
         }

         if (!this.enabled27) {
            this.goalSetupManager.add(2, this.rotationPool);
            if (this.getPathfindManager() instanceof PathfindManagerAdapter) {
               ((PathfindManagerAdapter)this.getPathfindManager()).setEnabled3(false);
            }

            this.enabled27 = true;
         }

         this.applyFloat4(distance);
         if (distance > 4.0F) {
            this.getPathfindManager().matchesEntityByEntity(entity, 1.0);
         }

         if (distance > 6.0F) {
            double offset = (entity.posX - this.posX) / (double)distance;
            double offset2 = (entity.posY - this.posY) / (double)distance;
            double offset3 = (entity.posZ - this.posZ) / (double)distance;
            this.motionX = this.motionX + offset * Math.abs(offset) * 0.4;
            this.motionY = this.motionY + offset2 * Math.abs(offset2) * 0.4;
            this.motionZ = this.motionZ + offset3 * Math.abs(offset3) * 0.4;
         }

         if (distance > 10.0F) {
            this.applyFlag(true, true);
         }
      } else if (!this.isEnabled23() && this.enabled27) {
         this.enabled27 = false;
         this.goalSetupManager.applyRotationPool(this.rotationPool);
         if (this.getPathfindManager() instanceof PathfindManagerAdapter) {
            ((PathfindManagerAdapter)this.getPathfindManager()).setEnabled3(true);
         }

         this.updateState47();
      }
   }

   public boolean onUpdate() {
      return !this.pathfindManager.isActive2();
   }

   public boolean isActive29() {
      return this.matchesBlockPos(new BlockPos(this));
   }

   public boolean isActive30() {
      return this.factor39 != -1.0F;
   }

   public float onPreDisable() {
      return this.factor39;
   }

   public void updateState47() {
      this.factor39 = -1.0F;
   }

   @Override
   public boolean nextInt() {
      return super.nextInt() && this.computeFactor(new BlockPos(this.posX, this.getBoundingBox2().minY, this.posZ)) >= 0.0F;
   }

   public AuxiliaryUUIDEntry(World world) {
      super(world);
   }

   static {
      String[] textArray = new String[]{"Fleeing speed bonus", "E199AD21-BA8A-4C53-8D13-6182D5C69D3A"};
      uUID3 = UUID.fromString(textArray[1]);
      invalidOperationManager2 = new InvalidOperationManager(uUID3, textArray[0], 2.0, 2).withEnabled(false);
   }
}
