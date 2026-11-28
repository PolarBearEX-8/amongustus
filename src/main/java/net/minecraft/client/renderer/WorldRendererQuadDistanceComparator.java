package net.minecraft.client.renderer;

import com.google.common.primitives.Floats;
import java.util.Comparator;

public class WorldRendererQuadDistanceComparator implements Comparator {
   public final float[] quadDistances;
   public final WorldRenderer worldRenderer;

   public WorldRendererQuadDistanceComparator(WorldRenderer worldRenderer2, float[] floats) {
      this.worldRenderer = worldRenderer2;
      this.quadDistances = floats;
   }

   public int compare2(Integer num, Integer num2) {
      return Floats.compare(this.quadDistances[num2], this.quadDistances[num]);
   }

   @Override
   public int compare(Object a, Object b) {
      return this.compare2((Integer)a, (Integer)b);
   }
}
