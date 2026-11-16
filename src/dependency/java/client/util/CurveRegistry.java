package client.util;

import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.MathHelper;

public class CurveRegistry {
   public static double computeValue(int count) {
      double doubleItem = MathHelper.getDouble7((double)count / 4096.0 * 0.995 * 2.0 - 1.0, -1.0, 1.0);
      return (double)MathHelper.sqrt_double(1.0 - doubleItem * doubleItem);
   }

   public static int computeCount(RenderChunk renderChunk) {
      InternalCharArrayEntry[] internalCharArrayEntryArray = renderChunk.getAlertNotificationSound().getInternalCharArrayEntryArray();
      if (internalCharArrayEntryArray == null) {
         return 0;
      } else {
         int count = renderChunk.getBlockPos().getY() >> 4;
         InternalCharArrayEntry internalCharArrayEntry = internalCharArrayEntryArray[count];
         return internalCharArrayEntry == null ? 0 : internalCharArrayEntry.getCount2();
      }
   }

   public static double computeValue(RenderChunk renderChunk) {
      return computeValue(computeCount(renderChunk));
   }
}
