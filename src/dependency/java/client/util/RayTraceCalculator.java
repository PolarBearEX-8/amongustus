package client.util;

import net.minecraft.entity.EntityMob;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class RayTraceCalculator extends PathfindManager {
   @Override
   public void updateState2() {
      super.updateState2();
   }

   @Override
   public OWKNOWSManagerEntry getOWKNOWSManagerEntry() {
      return new OWKNOWSManagerEntry(new BaseFloatEntryListener97Entry());
   }

   @Override
   public Vec3 getVec3() {
      return new Vec3(this.entityMob.posX, this.entityMob.posY + (double)this.entityMob.height * 0.5, this.entityMob.posZ);
   }

   @Override
   public boolean isActive() {
      return this.isActiveAsFlag();
   }

   @Override
   public boolean matchesVec3(Vec3 vec, Vec3 vec2, int count, int count2, int count3) {
      MovingObjectPosition movingObjectPosition = this.world
         .getMovingObjectPosition4(
            vec.xCoord, vec.yCoord, vec.zCoord, vec2.xCoord, vec2.yCoord + (double)this.entityMob.height * 0.5, vec2.zCoord, false, true, false
         );
      return movingObjectPosition == null || movingObjectPosition.typeOfHit == MovingObjectType.MISS;
   }

   public RayTraceCalculator(EntityMob entityMob, World world) {
      super(entityMob, world);
   }

   @Override
   public void updateState() {
      Vec3 vec3 = this.getVec3();
      float factor = this.entityMob.width * this.entityMob.width;
      if (vec3.dotProduct(this.interpolationConverter.resolveVecByEntity(this.entityMob, this.interpolationConverter.getCount())) < (double)factor) {
         this.interpolationConverter.updateState();
      }

      for (int index = Math.min(this.interpolationConverter.getCount() + 6, this.interpolationConverter.getCount2() - 1);
         index > this.interpolationConverter.getCount();
         index--
      ) {
         Vec3 vec = this.interpolationConverter.resolveVecByEntity(this.entityMob, index);
         if (vec.dotProduct(vec3) <= 36.0 && this.matchesVec3(vec3, vec, 0, 0, 0)) {
            this.interpolationConverter.setCount(index);
            break;
         }
      }

      this.applyVec3(vec3);
   }
}
