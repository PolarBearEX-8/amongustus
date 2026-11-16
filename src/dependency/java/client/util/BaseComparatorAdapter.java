package client.util;

import client.render.WorldRenderer;
import com.google.common.primitives.Floats;
import java.util.Comparator;

public class BaseComparatorAdapter implements Comparator {
   public final float[] factorArray;
   public final WorldRenderer worldRenderer;

   public int computeCount(Integer count, Integer count2) {
      return Floats.compare(this.factorArray[count2], this.factorArray[count]);
   }

   public BaseComparatorAdapter(WorldRenderer worldRenderer2, float[] factorArray2) {
      this.worldRenderer = worldRenderer2;
      this.factorArray = factorArray2;
   }

   @Override
   public int compare(Object entry, Object entry2) {
      return this.computeCount((Integer)entry, (Integer)entry2);
   }
}
