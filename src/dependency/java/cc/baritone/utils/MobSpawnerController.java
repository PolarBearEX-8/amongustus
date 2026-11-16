package cc.baritone.utils;

import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEnderman;
import net.minecraft.entity.EntityMonster;
import net.minecraft.entity.EntityPigzombie;
import net.minecraft.entity.EntitySpider;
import net.minecraft.util.BlockPos;

public class MobSpawnerController {
   public final int count;
   public final int count2;
   public final int count3;
   public final double value;
   public final int count4;
   public final int count5;

   public double computeValue(int count6, int count7, int count8) {
      int count9 = count6 - this.count;
      int count10 = count7 - this.count2;
      int count11 = count8 - this.count3;
      return count9 * count9 + count10 * count10 + count11 * count11 <= this.count5 ? this.value : 1.0;
   }

   private static void applyList(List list, double offset, Entity entity) {
      list.add(new MobSpawnerController(new BlockPos(entity), offset, (Integer)BaritoneManager.getSettings().mobAvoidanceRadius.dataRef));
   }

   public static List resolveList(StaticListener staticListener) {
      if (!(Boolean)BaritoneManager.getSettings().avoidance.dataRef) {
         return Collections.emptyList();
      } else {
         java.util.ArrayList list = new java.util.ArrayList();
         double dataRef = (Double)BaritoneManager.getSettings().mobSpawnerAvoidanceCoefficient.dataRef;
         double dataRef2 = (Double)BaritoneManager.getSettings().mobAvoidanceCoefficient.dataRef;
         if (dataRef != 1.0) {
            staticListener.getNativeListener()
               .getBreadthFirstPipeline()
               .resolveArrayList(
                  "mob_spawner",
                  1,
                  staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count9,
                  staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count10,
                  2
               )
               .forEach(pos -> MobSpawnerController.applyList(list, dataRef, (BlockPos)pos));
         }

         if (dataRef2 != 1.0) {
            staticListener.getWorld()
               .loadedEntityList
               .stream()
               .filter((item -> MobSpawnerController.matchesEntity((Entity)item)))
               .filter(entity -> MobSpawnerController.matchesStaticListener(staticListener, (Entity)entity))
               .filter(entity -> MobSpawnerController.matchesEntity2((Entity)entity))
               .filter(entity -> MobSpawnerController.matchesEntityByEntity((Entity)entity))
               .forEach(entity -> MobSpawnerController.applyList(list, dataRef2, (Entity)entity));
         }

         return list;
      }
   }

   private static void applyList(List list, double offset, BlockPos blockPos) {
      list.add(new MobSpawnerController(blockPos, offset, (Integer)BaritoneManager.getSettings().mobSpawnerAvoidanceRadius.dataRef));
   }

   private static boolean matchesEntity(Entity entity) {
      return entity instanceof EntityMonster;
   }

   private static boolean matchesEntityByEntity(Entity entity) {
      return !(entity instanceof EntityEnderman) || ((EntityEnderman)entity).isActive32();
   }

   public void applyLong2DoubleOpenHashMap(Long2DoubleOpenHashMap long2DoubleOpenHashMap) {
      for (int index = -this.count4; index <= this.count4; index++) {
         for (int index2 = -this.count4; index2 <= this.count4; index2++) {
            for (int index3 = -this.count4; index3 <= this.count4; index3++) {
               if (index * index + index2 * index2 + index3 * index3 <= this.count4 * this.count4) {
                  long value2 = BetterBlockPosXSYSZSBlockPos.computeTimestampByIndex(this.count + index, this.count2 + index2, this.count3 + index3);
                  long2DoubleOpenHashMap.put(value2, long2DoubleOpenHashMap.get(value2) * this.value);
               }
            }
         }
      }
   }

   private static boolean matchesStaticListener(StaticListener staticListener, Entity entity) {
      return !(entity instanceof EntitySpider) || (double)staticListener.getEntityPlayerSP().computeFactor(Minecraft.getMinecraft().timer.scaleFactor6) < 0.5;
   }

   public MobSpawnerController(BlockPos blockPos, double offset, int count6) {
      this(blockPos.getX(), blockPos.getY(), blockPos.getZ(), offset, count6);
   }

   public MobSpawnerController(int count6, int count7, int count8, double offset, int count9) {
      this.count = count6;
      this.count2 = count7;
      this.count3 = count8;
      this.value = offset;
      this.count4 = count9;
      this.count5 = count9 * count9;
   }

   private static boolean matchesEntity2(Entity entity) {
      return !(entity instanceof EntityPigzombie) || ((EntityPigzombie)entity).isActive34();
   }
}
