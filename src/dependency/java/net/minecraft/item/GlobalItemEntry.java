package net.minecraft.item;

import client.util.HitBoxBuffer;
import client.util.RotationPool;
import java.util.List;

public class GlobalItemEntry extends RotationPool {
   public HitBoxBuffer hitBoxBuffer2;
   public HitBoxBuffer hitBoxBuffer;
   public double value;
   public int count2;

   @Override
   public void updateState() {
      this.hitBoxBuffer = null;
   }

   @Override
   public void updateState3() {
      this.count2 = 0;
   }

   @Override
   public void updateState2() {
      if (--this.count2 <= 0) {
         this.count2 = 10;
         this.hitBoxBuffer2.getPathfindManager().matchesEntityByEntity(this.hitBoxBuffer, this.value);
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      if (this.hitBoxBuffer2.getCount9() >= 0) {
         return false;
      } else if (!this.hitBoxBuffer.canExecute()) {
         return false;
      } else {
         double coordinate = this.hitBoxBuffer2.getCoordinate(this.hitBoxBuffer);
         return coordinate >= 9.0 && coordinate <= 256.0;
      }
   }

   @Override
   public boolean isActive() {
      if (this.hitBoxBuffer2.getCount9() >= 0) {
         return false;
      } else {
         List list = this.hitBoxBuffer2.worldObj.resolveList(this.hitBoxBuffer2.getClass(), this.hitBoxBuffer2.getBoundingBox2().offset(8.0, 4.0, 8.0));
         HitBoxBuffer hitBoxBuffer3 = null;
         double offset = Double.MAX_VALUE;

         for (Object hitBoxBuffer4Obj : list) {
            HitBoxBuffer hitBoxBuffer4 = (HitBoxBuffer)hitBoxBuffer4Obj;
            if (hitBoxBuffer4.getCount9() >= 0) {
               double coordinate = this.hitBoxBuffer2.getCoordinate(hitBoxBuffer4);
               if (coordinate <= offset) {
                  offset = coordinate;
                  hitBoxBuffer3 = hitBoxBuffer4;
               }
            }
         }

         if (hitBoxBuffer3 == null) {
            return false;
         } else if (offset < 9.0) {
            return false;
         } else {
            this.hitBoxBuffer = hitBoxBuffer3;
            return true;
         }
      }
   }

   public GlobalItemEntry(HitBoxBuffer hitBoxBuffer3, double offset) {
      this.hitBoxBuffer2 = hitBoxBuffer3;
      this.value = offset;
   }
}
