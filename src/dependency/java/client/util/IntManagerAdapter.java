package client.util;

import client.render.ShaderManager;
import org.lwjgl.util.vector.Matrix4f;

public class IntManagerAdapter extends IntManager {
   @Override
   public void applyFloatByFloat(float posX, float posX2, float posY, float posZ) {
   }

   @Override
   public void applyFloat(float posX, float posY, float posZ) {
   }

   @Override
   public void applyMatrix4f(Matrix4f matrix) {
   }

   @Override
   public void applyFloatArray(float[] factorArray) {
   }

   @Override
   public void applyFloat(
      float posX,
      float posX2,
      float posX3,
      float posX4,
      float posX5,
      float posX6,
      float posX7,
      float posX8,
      float posX9,
      float posX10,
      float posX11,
      float posX12,
      float posX13,
      float posX14,
      float posY,
      float posZ
   ) {
   }

   @Override
   public void applyFloat(float posX, float posX2, float posY, float posZ) {
   }

   @Override
   public void applyFloat(float factor) {
   }

   @Override
   public void applyFloat(float factor, float factor2) {
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4) {
   }

   public IntManagerAdapter() {
      super("dummy", 4, 1, (ShaderManager)null);
   }
}
