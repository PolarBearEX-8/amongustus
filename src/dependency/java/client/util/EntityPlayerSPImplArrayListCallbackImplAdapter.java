package client.util;

import client.event.RandomizeReadStepListener;
import java.util.ArrayList;
import net.minecraft.client.entity.EntityPlayerSPImpl;

public class EntityPlayerSPImplArrayListCallbackImplAdapter implements ArrayListCallbackImpl {
   public final EntityPlayerSPImpl entityPlayerSPImpl;
   public final RandomizeReadStepListener randomizeReadStepListener;

   @Override
   public ArrayList resolveArrayList(int count, int count2, int count3, int count4) {
      return (ArrayList)RandomizeReadStepListener.resolveHashMap(this.randomizeReadStepListener)
         .computeIfAbsent(
            count + "_" + count2 + "_" + count3 + "_" + count4,
            key -> resolveArrayList(this.entityPlayerSPImpl, count, count2, count3, count4, (String)key)
         );
   }

   public EntityPlayerSPImplArrayListCallbackImplAdapter(RandomizeReadStepListener randomizeReadStepListener2, EntityPlayerSPImpl entityPlayerSPImpl2) {
      this.randomizeReadStepListener = randomizeReadStepListener2;
      this.entityPlayerSPImpl = entityPlayerSPImpl2;
   }

   private static ArrayList resolveArrayList(EntityPlayerSPImpl entityPlayerSPImpl2, int count, int count2, int count3, int count4, String text) {
      return PositionHistory.resolveArrayList(entityPlayerSPImpl2, count, count2, count3, count4, true);
   }
}
