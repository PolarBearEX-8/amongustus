package client.util;

import client.render.BoundingBoxDimension;
import java.util.ArrayList;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class PositionHistory implements MinecraftAccessor {
   public static ArrayListCallbackImpl arrayListCallbackImpl = null;
   public static ArrayListCallbackImpl arrayListCallbackImpl2 = null;

   public static ArrayList resolveArrayList(EntityPlayerSP player, EntityLivingBase livingEntity, int count, int count2, int count3, boolean flag, byte byteValue, int count4) {
      int count5 = count;
      if (livingEntity.backtrackRecord.getInt() != 0 && livingEntity.backtrackRecord3.getInt() != 0) {
         int ticksExisted = player.ticksExisted;
         if (count > ticksExisted) {
            count5 = ticksExisted;
         }

         if (ticksExisted - count2 > livingEntity.backtrackRecord.getInt() - 1) {
            ArrayList list = new ArrayList();
            list.add(livingEntity.backtrackRecord.getObject(0));
            return list;
         } else if (count5 < count2 && count5 >= 0) {
            int count8 = ticksExisted - count5;
            int count9 = count2 - count5;
            int count10 = livingEntity.backtrackRecord.getInt() - (ticksExisted - count5) - 1;
            Controller6Controller controller6Controller = new Controller6Controller();
            Vec3 object = (Vec3)livingEntity.backtrackRecord.getObject(0);
            Vec3 object2 = (Vec3)livingEntity.backtrackRecord.getObject(count10);
            double offset = (double)count10 / 20.0;
            double offset2 = offset;
            Vec3 vec = object2;
            Vec3 vec2 = object;
            controller6Controller.applyVec3(vec2, vec, offset2);
            double[] offsetArray = new double[]{((Vec3)livingEntity.backtrackRecord.getObject(0)).xCoord, ((Vec3)livingEntity.backtrackRecord.getObject(0)).zCoord};
            double[] offsetArray2 = new double[2];

            for (int index = 0; index <= count10; index++) {
               Vec3 object3 = (Vec3)livingEntity.backtrackRecord.getObject(index);
               double posZ = object3.zCoord;
               double posX = object3.xCoord;
               controller6Controller.applyDouble(posX, posZ);
               offsetArray = offsetArray2;
               offsetArray2 = controller6Controller.getDoubleArray();
            }

            double[] offsetArray3 = new double[]{offsetArray2[0] - offsetArray[0], offsetArray2[1] - offsetArray[1]};
            double offset3 = computeValue(livingEntity, count10);
            if (count8 > livingEntity.backtrackRecord3.getItemList().size() - 1) {
               Vec3Entry object4 = (Vec3Entry)livingEntity.backtrackRecord3.getObject(0);
               double offset4 = offsetArray3[0];
               double offset5 = offsetArray3[1];
               double offset6 = offset4;
               Vec3Entry vec3Entry = object4;
               return CombatTargetFilter.resolveArrayListByEntityLivingBase(livingEntity, vec3Entry, count9, offset6, offset3, offset5, flag);
            } else {
               Vec3Entry object5 = (Vec3Entry)livingEntity.backtrackRecord3.getObject3(count8);
               double offset7 = offsetArray3[0];
               double offset8 = offsetArray3[1];
               double offset9 = offset7;
               Vec3Entry vec3Entry2 = object5;
               return CombatTargetFilter.resolveArrayListByEntityLivingBase(livingEntity, vec3Entry2, count9, offset9, offset3, offset8, flag);
            }
         } else {
            ArrayList list2 = new ArrayList();
            int count11 = ticksExisted - count2;
            list2.add(livingEntity.backtrackRecord.getObject3(count11));
            return list2;
         }
      } else {
         ArrayList list3 = new ArrayList();
         list3.add(livingEntity.getVec32(1.0F));
         return list3;
      }
   }

   public static ArrayList resolveArrayList(EntityPlayerSP player, int count, int count2, int count3, int count4) {
      return resolveArrayList(player, count, count2, count3, count4, false);
   }

   public static ArrayList resolveArrayList(EntityPlayerSP player, EntityLivingBase livingEntity, int count, int count2) {
      if (arrayListCallbackImpl != null) {
         return arrayListCallbackImpl.resolveArrayList(count, 0, 0, count2);
      } else {
         byte byteValue = (byte)52;
         boolean flag = false;
         return resolveArrayList(player, livingEntity, count, count2, 136, flag, byteValue, 10230076);
      }
   }

   public static Vec3 resolveTrackedPosition(ArrayList list, int count) {
      if (count < 0) {
         return (Vec3)list.get(0);
      } else {
         return count >= list.size() ? (Vec3)list.get(list.size() - 1) : (Vec3)list.get(count);
      }
   }

   public static ArrayList resolveArrayList(EntityPlayerSP player, int count, int count2, int count3, int count4, boolean flag) {
      int count5 = count;
      if (!flag && arrayListCallbackImpl2 != null) {
         return arrayListCallbackImpl2.resolveArrayList(count, count2, count3, count4);
      } else if (player.backtrackRecord5.getInt() == 0) {
         ArrayList list = new ArrayList();
         list.add(player.getVec32(1.0F));
         return list;
      } else {
         int ticksExisted = player.ticksExisted;
         if (count > ticksExisted) {
            count5 = ticksExisted;
         }

         if (player.ticksExisted - count4 > player.backtrackRecord5.getItemList().size() - 1) {
            ArrayList list2 = new ArrayList();
            list2.add(((BoundingBoxDimension)player.backtrackRecord5.getObject(0)).positionVec2);
            return list2;
         } else if (count5 >= count4) {
            ArrayList list3 = new ArrayList();
            int count6 = player.ticksExisted - count4;
            list3.add(((BoundingBoxDimension)player.backtrackRecord5.getObject3(count6)).positionVec2);
            return list3;
         } else {
            int count7 = ticksExisted - count5;
            int count8 = count4 - count5;
            if (player.backtrackRecord5.getInt() <= count7) {
               ArrayList list4 = new ArrayList();
               list4.add(player.getVec32(1.0F));
               return list4;
            } else {
               ArrayList list5 = new ArrayList();

               for (int index = count5; index <= count2; index++) {
                  int count9 = ticksExisted - index + count3 - 1;
                  if (count9 >= 0 && count9 < player.backtrackRecord5.getInt()) {
                     list5.add(player.backtrackRecord5.getObject3(count9));
                  }
               }

               BoundingBoxDimension object = (BoundingBoxDimension)player.backtrackRecord5.getObject3(count7);
               int count10 = count8 - 1;
               ArrayList list6 = CombatUtil.resolveArrayList(player, list5, object, count10, false);
               ArrayList list7 = new ArrayList();

               for (Object offsetArray_o : list6) {
                  double[] offsetArray = (double[])offsetArray_o;
                  list7.add(new Vec3(offsetArray[0], offsetArray[1], offsetArray[2]));
               }

               return list7;
            }
         }
      }
   }

   public static double computeValue(EntityLivingBase livingEntity, int count) {
      Controller6Controller controller6Controller = new Controller6Controller();
      Vec3 object = (Vec3)livingEntity.backtrackRecord.getObject(0);
      Vec3 object2 = (Vec3)livingEntity.backtrackRecord.getObject(count);
      double offset = (double)count / 20.0;
      Vec3 vec = object2;
      Vec3 vec2 = object;
      controller6Controller.applyVecByVec3(vec2, vec, offset);
      double posY = ((Vec3)livingEntity.backtrackRecord.getObject(0)).yCoord;
      double offset2 = 0.0;
      double posY2 = ((Vec3)livingEntity.backtrackRecord.getObject(0)).yCoord;

      for (int index = 0; index <= count; index++) {
         Vec3 object3 = (Vec3)livingEntity.backtrackRecord.getObject(index);
         if (object3.yCoord - posY2 > 0.8) {
            return 0.0;
         }

         posY2 = object3.yCoord;
      }

      for (int index2 = 0; index2 <= count; index2++) {
         Vec3 object4 = (Vec3)livingEntity.backtrackRecord.getObject(index2);
         double offset3 = 0.0;
         double posY3 = object4.yCoord;
         controller6Controller.applyDouble(posY3, offset3);
         posY = offset2;
         offset2 = controller6Controller.getDoubleArray()[0];
      }

      return MathHelper.getDouble7(offset2 - posY, -1.0, 1.0);
   }
}
