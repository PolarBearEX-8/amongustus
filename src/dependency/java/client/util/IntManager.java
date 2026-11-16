package client.util;

import client.render.ShaderManager;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.client.renderer.OpenGlHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;

public class IntManager {
   public static final Logger logger = LogManager.getLogger();
   public int count;
   public final int count2;
   public final int count3;
   public final IntBuffer intBuffer;
   public final FloatBuffer floatBuffer;
   public final String labelText;
   public boolean enabled;
   public final ShaderManager shaderManager;

   private void updateState4() {
      switch (this.count3) {
         case 8:
            OpenGlHelper.applyFloatBuffer2(this.count, true, this.floatBuffer);
            break;
         case 9:
            OpenGlHelper.applyFloatBufferByFloatBuffer(this.count, true, this.floatBuffer);
            break;
         case 10:
            OpenGlHelper.applyFloatBuffer(this.count, true, this.floatBuffer);
      }
   }

   public void applyFloat(float posX, float posY, float posZ) {
      ((Buffer)this.floatBuffer).position(0);
      this.floatBuffer.put(0, posX);
      this.floatBuffer.put(1, posY);
      this.floatBuffer.put(2, posZ);
      this.updateState();
   }

   private void updateState2() {
      switch (this.count3) {
         case 4:
            OpenGlHelper.applyFloatBufferByFloatBuffer(this.count, this.floatBuffer);
            break;
         case 5:
            OpenGlHelper.applyFloatBuffer(this.count, this.floatBuffer);
            break;
         case 6:
            OpenGlHelper.applyFloatBuffer3(this.count, this.floatBuffer);
            break;
         case 7:
            OpenGlHelper.applyFloatBuffer2(this.count, this.floatBuffer);
            break;
         default:
            logger.warn("Uniform.upload called, but count value (" + this.count2 + ") is not in the range of 1 to 4. Ignoring.");
      }
   }

   public void applyFloatByFloat(float posX, float posX2, float posY, float posZ) {
      ((Buffer)this.floatBuffer).position(0);
      this.floatBuffer.put(posX);
      this.floatBuffer.put(posX2);
      this.floatBuffer.put(posY);
      this.floatBuffer.put(posZ);
      ((Buffer)this.floatBuffer).flip();
      this.updateState();
   }

   public static int computeCount(String text) {
      byte byteValue = -1;
      if (text.equals("int")) {
         byteValue = 0;
      } else if (text.equals("float")) {
         byteValue = 4;
      } else if (text.startsWith("matrix")) {
         if (text.endsWith("2x2")) {
            byteValue = 8;
         } else if (text.endsWith("3x3")) {
            byteValue = 9;
         } else if (text.endsWith("4x4")) {
            byteValue = 10;
         }
      }

      return byteValue;
   }

   public void applyFloatArray(float[] factorArray) {
      if (factorArray.length < this.count2) {
         logger.warn("Uniform.set called with a too-small value array (expected " + this.count2 + ", got " + factorArray.length + "). Ignoring.");
      } else {
         ((Buffer)this.floatBuffer).position(0);
         this.floatBuffer.put(factorArray);
         ((Buffer)this.floatBuffer).position(0);
         this.updateState();
      }
   }

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
      ((Buffer)this.floatBuffer).position(0);
      this.floatBuffer.put(0, posX);
      this.floatBuffer.put(1, posX2);
      this.floatBuffer.put(2, posX3);
      this.floatBuffer.put(3, posX4);
      this.floatBuffer.put(4, posX5);
      this.floatBuffer.put(5, posX6);
      this.floatBuffer.put(6, posX7);
      this.floatBuffer.put(7, posX8);
      this.floatBuffer.put(8, posX9);
      this.floatBuffer.put(9, posX10);
      this.floatBuffer.put(10, posX11);
      this.floatBuffer.put(11, posX12);
      this.floatBuffer.put(12, posX13);
      this.floatBuffer.put(13, posX14);
      this.floatBuffer.put(14, posY);
      this.floatBuffer.put(15, posZ);
      this.updateState();
   }

   public void applyMatrix4f(Matrix4f matrix) {
      this.applyFloat(
         matrix.m00,
         matrix.m01,
         matrix.m02,
         matrix.m03,
         matrix.m10,
         matrix.m11,
         matrix.m12,
         matrix.m13,
         matrix.m20,
         matrix.m21,
         matrix.m22,
         matrix.m23,
         matrix.m30,
         matrix.m31,
         matrix.m32,
         matrix.m33
      );
   }

   public void applyFloat(float posX, float posX2, float posY, float posZ) {
      ((Buffer)this.floatBuffer).position(0);
      if (this.count3 >= 4) {
         this.floatBuffer.put(0, posX);
      }

      if (this.count3 >= 5) {
         this.floatBuffer.put(1, posX2);
      }

      if (this.count3 >= 6) {
         this.floatBuffer.put(2, posY);
      }

      if (this.count3 >= 7) {
         this.floatBuffer.put(3, posZ);
      }

      this.updateState();
   }

   public void setCount(int count4) {
      this.count = count4;
   }

   private void updateState() {
      this.enabled = true;
      if (this.shaderManager != null) {
         this.shaderManager.renderElement4();
      }
   }

   private void updateState3() {
      switch (this.count3) {
         case 0:
            OpenGlHelper.applyIntBuffer(this.count, this.intBuffer);
            break;
         case 1:
            OpenGlHelper.applyIntBuffer3(this.count, this.intBuffer);
            break;
         case 2:
            OpenGlHelper.applyIntBuffer2(this.count, this.intBuffer);
            break;
         case 3:
            OpenGlHelper.applyIntBufferByIntBuffer(this.count, this.intBuffer);
            break;
         default:
            logger.warn("Uniform.upload called, but count value (" + this.count2 + ") is  not in the range of 1 to 4. Ignoring.");
      }
   }

   public void updateState5() {
      this.enabled = false;
      if (this.count3 <= 3) {
         this.updateState3();
      } else if (this.count3 <= 7) {
         this.updateState2();
      } else {
         if (this.count3 > 10) {
            logger.warn("Uniform.upload called, but type value (" + this.count3 + ") is not a valid type. Ignoring.");
            return;
         }

         this.updateState4();
      }
   }

   public void applyFloat(float factor) {
      ((Buffer)this.floatBuffer).position(0);
      this.floatBuffer.put(0, factor);
      this.updateState();
   }

   public String getLabelText() {
      return this.labelText;
   }

   public void applyIndex(int count4, int count5, int count6, int count7) {
      ((Buffer)this.intBuffer).position(0);
      if (this.count3 >= 0) {
         this.intBuffer.put(0, count4);
      }

      if (this.count3 >= 1) {
         this.intBuffer.put(1, count5);
      }

      if (this.count3 >= 2) {
         this.intBuffer.put(2, count6);
      }

      if (this.count3 >= 3) {
         this.intBuffer.put(3, count7);
      }

      this.updateState();
   }

   public void applyFloat(float factor, float factor2) {
      ((Buffer)this.floatBuffer).position(0);
      this.floatBuffer.put(0, factor);
      this.floatBuffer.put(1, factor2);
      this.updateState();
   }

   public IntManager(String text, int count4, int count5, ShaderManager shaderManager2) {
      this.labelText = text;
      this.count2 = count5;
      this.count3 = count4;
      this.shaderManager = shaderManager2;
      if (count4 <= 3) {
         this.intBuffer = BufferUtils.createIntBuffer(count5);
         this.floatBuffer = null;
      } else {
         this.intBuffer = null;
         this.floatBuffer = BufferUtils.createFloatBuffer(count5);
      }

      this.count = -1;
      this.updateState();
   }
}
