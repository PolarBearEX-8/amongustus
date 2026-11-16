package client.util;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;

public class HelperAdapter {
   public static final List list = new ArrayList();

   public static void add(FloatBufferControllerAdapter floatBufferControllerAdapter) {
      list.add(floatBufferControllerAdapter);
   }

   public static void applyFloatBufferControllerAdapt(FloatBufferControllerAdapter floatBufferControllerAdapter) {
      list.remove(floatBufferControllerAdapter);
      floatBufferControllerAdapter.glDeleteBuffers();
   }

   public static void updateState2() {
      GL11.glPushAttrib(8193);
      GL11.glDisable(3553);
      GL11.glDisable(2896);
      GL11.glEnableClientState(32884);
      GL11.glDisable(2884);

      for (FloatBufferControllerAdapter floatBufferControllerAdapter : (Iterable<FloatBufferControllerAdapter>)(Iterable<?>)list) {
         float[] factorArray = new float[]{0.0F, 1.0F, 0.0F, 1.0F};
         floatBufferControllerAdapter.arraycopy(factorArray);
         floatBufferControllerAdapter.updateState();
      }

      GL11.glDisableClientState(32884);
      GL11.glEnable(3553);
      GL11.glEnable(2896);
      GL11.glPopAttrib();
   }

   public static void updateState() {
      list.forEach(item -> ((FloatBufferControllerAdapter)item).glDeleteBuffers());
      list.clear();
   }
}
