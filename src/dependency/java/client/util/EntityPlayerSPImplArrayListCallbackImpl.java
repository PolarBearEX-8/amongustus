package client.util;

import client.event.RandomizeReadStepListener;
import java.util.ArrayList;
import net.minecraft.client.entity.EntityPlayerSPImpl;
import net.minecraft.entity.ItemStackEntityLivingBase;

public class EntityPlayerSPImplArrayListCallbackImpl implements ArrayListCallbackImpl {
   public final EntityPlayerSPImpl entityPlayerSPImpl;
   public final RandomizeReadStepListener randomizeReadStepListener;
   public final ItemStackEntityLivingBase itemStackEntityLivingBase;

   private static ArrayList resolveArrayList(EntityPlayerSPImpl entityPlayerSPImpl2, ItemStackEntityLivingBase itemStackEntityLivingBase2, int count, int count2, String text) {
      boolean flag = false;
      return PositionHistory.resolveArrayList(entityPlayerSPImpl2, itemStackEntityLivingBase2, count, count2, 136, flag, (byte)52, 10230076);
   }

   @Override
   public ArrayList resolveArrayList(int count, int count2, int count3, int count4) {
      return (ArrayList)RandomizeReadStepListener.resolveHashMapByRandomizeReadSte(this.randomizeReadStepListener)
         .computeIfAbsent(count + "_" + count4, key -> resolveArrayList(this.entityPlayerSPImpl, this.itemStackEntityLivingBase, count, count4, (String)key));
   }

   public EntityPlayerSPImplArrayListCallbackImpl(RandomizeReadStepListener randomizeReadStepListener2, EntityPlayerSPImpl entityPlayerSPImpl2, ItemStackEntityLivingBase itemStackEntityLivingBase2) {
      this.randomizeReadStepListener = randomizeReadStepListener2;
      this.entityPlayerSPImpl = entityPlayerSPImpl2;
      this.itemStackEntityLivingBase = itemStackEntityLivingBase2;
   }
}
