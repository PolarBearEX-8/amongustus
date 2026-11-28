package baritone.utils.pathing;

import baritone.Baritone;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.IPlayerContext;
import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;

public class Avoidance {
   private final int x;
   private final int y;
   private final int z;
   private final double coeff;
   private final int radius;
   private final int radiusSq;

   public void applySpherical(Long2DoubleOpenHashMap long2DoubleOpenHashMap) {
      for (int i = -this.radius; i <= this.radius; i++) {
         for (int j = -this.radius; j <= this.radius; j++) {
            for (int k = -this.radius; k <= this.radius; k++) {
               if (i * i + j * j + k * k <= this.radius * this.radius) {
                  long l = BetterBlockPos.longHash(this.x + i, this.y + j, this.z + k);
                  long2DoubleOpenHashMap.put(l, long2DoubleOpenHashMap.get(l) * this.coeff);
               }
            }
         }
      }
   }

   public static List create(IPlayerContext playerContext) {
      if (!(Boolean)Baritone.settings().avoidance.value) {
         return Collections.emptyList();
      } else {
         ArrayList list = new ArrayList();
         double value = (Double)Baritone.settings().mobSpawnerAvoidanceCoefficient.value;
         double value2 = (Double)Baritone.settings().mobAvoidanceCoefficient.value;
         if (value != 1.0) {
            playerContext.worldData()
               .getCachedWorld()
               .getLocationsOf("mob_spawner", 1, playerContext.playerFeet().x2, playerContext.playerFeet().z2, 2)
               .forEach(obj -> list.add(new Avoidance((BlockPos)obj, value, (Integer)Baritone.settings().mobSpawnerAvoidanceRadius.value)));
         }

         if (value2 != 1.0) {
            playerContext.world()
               .loadedEntityList
               .stream()
               .filter(obj2 -> obj2 instanceof EntityMob)
               .filter(obj3 -> !(obj3 instanceof EntitySpider) || (double)playerContext.player().getBrightness(Minecraft.getMinecraft().timer.renderPartialTicks) < 0.5)
               .filter(obj4 -> !(obj4 instanceof EntityPigZombie) || ((EntityPigZombie)obj4).isAngry())
               .filter(obj5 -> !(obj5 instanceof EntityEnderman) || ((EntityEnderman)obj5).isScreaming())
               .forEach(obj6 -> list.add(new Avoidance(new BlockPos((Entity)obj6), value2, (Integer)Baritone.settings().mobAvoidanceRadius.value)));
         }

         return list;
      }
   }

   public Avoidance(BlockPos pos, double d, int n) {
      this(pos.getX(), pos.getY(), pos.getZ(), d, n);
   }

   public Avoidance(int n, int n2, int n3, double d, int n4) {
      this.x = n;
      this.y = n2;
      this.z = n3;
      this.coeff = d;
      this.radius = n4;
      this.radiusSq = n4 * n4;
   }

   public double getMultiplier(int n, int n2, int n3) {
      int n4 = n - this.x;
      int n5 = n2 - this.y;
      int n6 = n3 - this.z;
      return n4 * n4 + n5 * n5 + n6 * n6 <= this.radiusSq ? this.coeff : 1.0;
   }

}
