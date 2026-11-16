package client.util;

import client.event.AuxiliaryListener;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class PathfindManager {
   public EntityMob entityMob;
   public World world;
   public InterpolationConverter interpolationConverter;
   public double value;
   public final AuxiliaryListener auxiliaryListener;
   public int count2;
   public int count;
   public Vec3 vec3 = new Vec3(0.0, 0.0, 0.0);
   public float factor = 1.0F;
   public final OWKNOWSManagerEntry oWKNOWSManagerEntry;

   public void updateState2() {
   }

   public InterpolationConverter getInterpolationConverter() {
      return this.interpolationConverter;
   }

   public boolean isActive2() {
      return this.interpolationConverter == null || this.interpolationConverter.isActive();
   }

   public abstract OWKNOWSManagerEntry getOWKNOWSManagerEntry();

   public final InterpolationConverter resolveInterpolationConverter(double posX, double posY, double posZ) {
      return this.resolveInterpolationConverter(new BlockPos(MathHelper.ceiling_double_int(posX), (int)posY, MathHelper.ceiling_double_int(posZ)));
   }

   public boolean matchesEntityByEntity(Entity entity, double offset) {
      InterpolationConverter interpolationConverter2 = this.resolveInterpolationConverter(entity);
      return interpolationConverter2 != null ? this.matchesInterpolationConverter(interpolationConverter2, offset) : false;
   }

   public boolean isActiveAsFlag() {
      return this.entityMob.isInWater() || this.entityMob.isWet();
   }

   public void applyVec3(Vec3 vec) {
      if (this.count2 - this.count > 100) {
         if (vec.dotProduct(this.vec3) < 2.25) {
            this.updateState4();
         }

         this.count = this.count2;
         this.vec3 = vec;
      }
   }

   public void updateState() {
      Vec3 vec32 = this.getVec3();
      int count3 = this.interpolationConverter.getCount2();

      for (int index = this.interpolationConverter.getCount(); index < this.interpolationConverter.getCount2(); index++) {
         if (this.interpolationConverter.resolveBaseFloatEntry(index).count5 != (int)vec32.yCoord) {
            count3 = index;
            break;
         }
      }

      float factor2 = this.entityMob.width * this.entityMob.width * this.factor;

      for (int index2 = this.interpolationConverter.getCount(); index2 < count3; index2++) {
         Vec3 vec = this.interpolationConverter.resolveVecByEntity(this.entityMob, index2);
         if (vec32.dotProduct(vec) < (double)factor2) {
            this.interpolationConverter.setCount(index2 + 1);
         }
      }

      int count4 = MathHelper.computeCount(this.entityMob.width);
      int count5 = (int)this.entityMob.height + 1;

      for (int index3 = count3 - 1; index3 >= this.interpolationConverter.getCount(); index3--) {
         if (this.matchesVec3(vec32, this.interpolationConverter.resolveVecByEntity(this.entityMob, index3), count4, count5, count4)) {
            this.interpolationConverter.setCount(index3);
            break;
         }
      }

      this.applyVec3(vec32);
   }

   public void setValue(double value2) {
      this.value = value2;
   }

   public boolean matchesInterpolationConverter(InterpolationConverter interpolationConverter2, double offset) {
      if (interpolationConverter2 == null) {
         this.interpolationConverter = null;
         return false;
      } else {
         if (!interpolationConverter2.matchesInterpolationConverter(this.interpolationConverter)) {
            this.interpolationConverter = interpolationConverter2;
         }

         this.updateState2();
         if (this.interpolationConverter.getCount2() == 0) {
            return false;
         } else {
            this.value = offset;
            Vec3 vec32 = this.getVec3();
            this.count = this.count2;
            this.vec3 = vec32;
            return true;
         }
      }
   }

   public void updateState4() {
      this.interpolationConverter = null;
   }

   public abstract boolean isActive();

   public InterpolationConverter resolveInterpolationConverter(Entity entity) {
      if (!this.isActive()) {
         return null;
      } else {
         float factor2 = this.getFactor();
         this.world.uNKNOWNManager.applyTextByText("pathfind");
         int count3 = MathHelper.ceiling_double_int(this.entityMob.posX);
         int count4 = MathHelper.ceiling_double_int(this.entityMob.posY) + 1;
         int count5 = MathHelper.ceiling_double_int(this.entityMob.posZ);
         int count6 = (int)(factor2 + 16.0F);
         ListenerAdapter28 listenerAdapter = new ListenerAdapter28(this.world, count3 - count6, count4 - count6, count5 - count6, count3 + count6, count4 + count6, count5 + count6, 0);
         InterpolationConverter interpolationConverter2 = this.oWKNOWSManagerEntry.resolveInterpolationConverterByIBlockAccess(listenerAdapter, this.entityMob, entity, factor2);
         this.world.uNKNOWNManager.updateState();
         return interpolationConverter2;
      }
   }

   public void setFactor(float factor2) {
      this.factor = factor2;
   }

   public boolean checkDouble(double posX, double posX2, double posY, double posZ) {
      InterpolationConverter interpolationConverter2 = this.resolveInterpolationConverter(
         (double)MathHelper.ceiling_double_int(posX), (double)((int)posX2), (double)MathHelper.ceiling_double_int(posY)
      );
      return this.matchesInterpolationConverter(interpolationConverter2, posZ);
   }

   public float getFactor() {
      return (float)this.auxiliaryListener.getDouble();
   }

   public void updateState3() {
      this.count2++;
      if (!this.isActive2()) {
         if (this.isActive()) {
            this.updateState();
         } else if (this.interpolationConverter != null && this.interpolationConverter.getCount() < this.interpolationConverter.getCount2()) {
            Vec3 vec32 = this.getVec3();
            Vec3 vec = this.interpolationConverter.resolveVecByEntity(this.entityMob, this.interpolationConverter.getCount());
            if (vec32.yCoord > vec.yCoord
               && !this.entityMob.onGround
               && MathHelper.ceiling_double_int(vec32.xCoord) == MathHelper.ceiling_double_int(vec.xCoord)
               && MathHelper.ceiling_double_int(vec32.zCoord) == MathHelper.ceiling_double_int(vec.zCoord)) {
               this.interpolationConverter.setCount(this.interpolationConverter.getCount() + 1);
            }
         }

         if (!this.isActive2()) {
            Vec3 vec2 = this.interpolationConverter.resolveVec3(this.entityMob);
            if (vec2 != null) {
               AxisAlignedBB boundingBox = new AxisAlignedBB(vec2.xCoord, vec2.yCoord, vec2.zCoord, vec2.xCoord, vec2.yCoord, vec2.zCoord).offset(0.5, 0.5, 0.5);
               List list = this.world.getList2(this.entityMob, boundingBox.contract(0.0, -1.0, 0.0));
               double offset = -1.0;
               AxisAlignedBB boundingBox2 = boundingBox.expand(0.0, 1.0, 0.0);
               Iterator iterator = list.iterator();

               while (iterator.hasNext()) {
                  offset = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox2, offset);
               }

               this.entityMob.getEntityPillagerAdapter().applyDouble(vec2.xCoord, vec2.yCoord + offset, vec2.zCoord, this.value);
            }
         }
      }
   }

   public abstract Vec3 getVec3();

   public abstract boolean matchesVec3(Vec3 vec, Vec3 vec2, int count3, int count4, int count5);

   public InterpolationConverter resolveInterpolationConverter(BlockPos blockPos) {
      if (!this.isActive()) {
         return null;
      } else {
         float factor2 = this.getFactor();
         this.world.uNKNOWNManager.applyTextByText("pathfind");
         int count3 = MathHelper.ceiling_double_int(this.entityMob.posX);
         int count4 = MathHelper.ceiling_double_int(this.entityMob.posY);
         int count5 = MathHelper.ceiling_double_int(this.entityMob.posZ);
         int count6 = (int)(factor2 + 8.0F);
         ListenerAdapter28 listenerAdapter = new ListenerAdapter28(this.world, count3 - count6, count4 - count6, count5 - count6, count3 + count6, count4 + count6, count5 + count6, 0);
         InterpolationConverter interpolationConverter2 = this.oWKNOWSManagerEntry.resolveInterpolationConverter(listenerAdapter, this.entityMob, blockPos, factor2);
         this.world.uNKNOWNManager.updateState();
         return interpolationConverter2;
      }
   }

   public PathfindManager(EntityMob entityMob2, World world2) {
      this.entityMob = entityMob2;
      this.world = world2;
      this.auxiliaryListener = entityMob2.getAuxiliaryListener(SharedMonsterAttributes.extendedListener4);
      this.oWKNOWSManagerEntry = this.getOWKNOWSManagerEntry();
   }
}
