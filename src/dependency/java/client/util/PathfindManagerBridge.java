package client.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMob;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class PathfindManagerBridge extends PathfindManagerAdapter {
   public BlockPos blockPos;

   @Override
   public void updateState3() {
      if (!this.isActive2()) {
         super.updateState3();
      } else if (this.blockPos != null) {
         double offset = (double)(this.entityMob.width * this.entityMob.width);
         if (!(this.entityMob.getCoordinate(this.blockPos) >= offset)
            || !(this.entityMob.posY <= (double)this.blockPos.getY())
               && !(
                  this.entityMob
                        .getCoordinate(new BlockPos(this.blockPos.getX(), MathHelper.ceiling_double_int(this.entityMob.posY), this.blockPos.getZ()))
                     >= offset
               )) {
            this.blockPos = null;
         } else {
            this.entityMob
               .getEntityPillagerAdapter()
               .applyDouble((double)this.blockPos.getX(), (double)this.blockPos.getY(), (double)this.blockPos.getZ(), this.value);
         }
      }
   }

   @Override
   public boolean matchesEntityByEntity(Entity entity, double offset) {
      InterpolationConverter interpolationConverter = this.resolveInterpolationConverter(entity);
      if (interpolationConverter != null) {
         return this.matchesInterpolationConverter(interpolationConverter, offset);
      } else {
         this.blockPos = new BlockPos(entity);
         this.value = offset;
         return true;
      }
   }

   @Override
   public InterpolationConverter resolveInterpolationConverter(Entity entity) {
      this.blockPos = new BlockPos(entity);
      return super.resolveInterpolationConverter(entity);
   }

   public PathfindManagerBridge(EntityMob entityMob, World world) {
      super(entityMob, world);
   }

   @Override
   public InterpolationConverter resolveInterpolationConverter(BlockPos blockPos2) {
      this.blockPos = blockPos2;
      return super.resolveInterpolationConverter(blockPos2);
   }
}
