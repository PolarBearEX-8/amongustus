package client.render;

import com.google.common.primitives.Floats;
import java.util.Comparator;

public class WorldRendererComparator implements Comparator {
   public final float[] factorArray;
   public final WorldRenderer worldRenderer;

   @Override
   public int compare(Object entry, Object entry2) {
      return this.computeCount((Integer)entry, (Integer)entry2);
   }

   public WorldRendererComparator(WorldRenderer worldRenderer2, float[] factorArray2) {
      this.worldRenderer = worldRenderer2;
      this.factorArray = factorArray2;
   }

   public int computeCount(Integer count, Integer count2) {
      return Floats.compare(this.factorArray[count2], this.factorArray[count]);
   }
}
