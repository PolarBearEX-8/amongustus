package client.util;

import org.lwjgl.opengl.GL11;

public class FloatBufferControlleControllerImpl {
   public FloatBufferControllerImpl floatBufferControllerImpl;

   public void updateState() {
      this.floatBufferControllerImpl = new FloatBufferControllerImpl(1000);
      float[] factorArray = new float[]{1.0F, 0.0F, 0.0F, 1.0F};
      float factor = 1.0F;
      float factor2 = 0.0F;
      float factor3 = 64.0F;
      float factor4 = 0.0F;
      this.floatBufferControllerImpl.applyFloatArray(factor4, factor3, factor2, factor, factorArray);
   }

   public void updateState2() {
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      this.floatBufferControllerImpl.updateState();
      GL11.glEnable(2896);
      GL11.glDisable(3042);
   }

   public void applyFloatArray(float posX, float posX2, float posY, float posZ, float[] factorArray) {
      this.floatBufferControllerImpl.applyFloatArray(posX, posX2, posY, posZ, factorArray);
   }
}
