package client.util;

import client.render.RenderPass;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class StepHistoryArray {
   public int[] countArray;
   public RunnableTask runnableTask;
   public boolean enabled;
   public boolean enabled4;
   public boolean enabled2;
   public float factor3;
   public float factor5;
   public float factor;
   public boolean enabled13;
   public float factor2;
   public boolean enabled10;
   public int count6;
   public int count11;
   public float factor6;
   public final FloatBuffer floatBuffer;
   public float factor4;
   public boolean enabled7;
   public boolean enabled8;
   public int count8;
   public boolean[] enabledArray;
   public boolean enabled12;
   public boolean enabled14;
   public int count7;
   public int count5;
   public int count;
   public float factor8;
   public int count2;
   public final IntBuffer intBuffer;
   public float factor7;
   public boolean enabled5;
   public boolean enabled3;
   public boolean[] enabledArray2 = new boolean[8];
   public boolean enabled9;
   public int count10;
   public int count3;
   public int count9;
   public boolean enabled11;
   public final FloatBuffer floatBuffer2;
   public int count4;
   public boolean enabled6;

   public void applyRunnableTask(RunnableTask runnableTask2) {
      this.runnableTask = runnableTask2;
      int count12 = GL11.glGetInteger(2976);
      this.enabled10 = GlStateManager.obtainState11();
      this.count = GlStateManager.getStateInt11();
      this.factor3 = GlStateManager.getStateFloat();
      this.enabled14 = GlStateManager.obtainState7();

      for (int index = 0; index < 8; index++) {
         this.enabledArray2[index] = GlStateManager.obtainState(index);
      }

      this.enabled11 = GlStateManager.obtainState();
      this.count7 = GlStateManager.getStateInt9();
      this.count5 = GlStateManager.getStateInt8();
      this.enabled = GlStateManager.obtainState9();
      this.count10 = GlStateManager.getStateInt5();
      this.count3 = GlStateManager.getStateInt12();
      this.enabled12 = GlStateManager.obtainState12();
      this.count11 = GlStateManager.getStateInt7();
      this.enabled6 = GlStateManager.obtainState4();
      this.enabled2 = GlStateManager.obtainState10();
      this.count2 = GlStateManager.getStateInt3();
      this.factor2 = GlStateManager.getStateFloat3();
      this.factor4 = GlStateManager.getStateFloat6();
      this.factor5 = GlStateManager.getStateFloat2();
      this.enabled5 = GlStateManager.obtainState6();
      this.count8 = GlStateManager.getStateInt2();
      this.enabled3 = GlStateManager.obtainState13();
      this.factor7 = GlStateManager.getStateFloat5();
      this.factor = GlStateManager.getStateFloat4();
      this.enabled13 = GlStateManager.obtainState3();
      this.count4 = GlStateManager.getStateInt();
      this.enabled8 = GlStateManager.obtainState5();
      this.enabled9 = GlStateManager.obtainState2();
      this.count9 = GlStateManager.getStateInt13();

      for (int index2 = 0; index2 < 32; index2++) {
         this.enabledArray[index2] = GlStateManager.obtainState2(index2);
         this.countArray[index2] = GlStateManager.getStateInt(index2);
      }

      this.count6 = GlStateManager.getStateInt10();
      ((Buffer)this.floatBuffer2).clear();
      ((Buffer)this.floatBuffer).clear();
      GL11.glGetFloat(2982, this.floatBuffer2);
      GL11.glGetFloat(2983, this.floatBuffer);
      this.factor8 = GL11.glGetFloat(2849);
      this.factor6 = GL11.glGetFloat(2833);
      this.enabled7 = GL11.glIsEnabled(2848);
      this.enabled4 = GL11.glIsEnabled(2832);
      GL11.glGetInteger(2978, this.intBuffer);
      GL11.glMatrixMode(count12);
   }

   public void applyRenderPassByRenderPass(RenderPass renderPass) {
      int count12 = GL11.glGetInteger(2976);
      GL11.glViewport(this.intBuffer.get(0), this.intBuffer.get(1), this.intBuffer.get(2), this.intBuffer.get(3));
      GL11.glMatrixMode(5889);
      GL11.glLoadIdentity();
      GL11.glLoadMatrix(this.floatBuffer);
      GL11.glMatrixMode(5888);
      GL11.glLoadIdentity();
      GL11.glLoadMatrix(this.floatBuffer2);
      GL11.glMatrixMode(count12);
      if (this.enabled10) {
         GlStateManager.applyState14();
      } else {
         GlStateManager.applyState10();
      }

      GlStateManager.alphaFunc(this.count, this.factor3);
      if (this.enabled14) {
         GlStateManager.applyState13();
      } else {
         GlStateManager.applyState3();
      }

      for (int index = 0; index < 8; index++) {
         if (this.enabledArray2[index]) {
            GlStateManager.enableState4(index);
         } else {
            GlStateManager.enableLight(index);
         }
      }

      if (this.enabled11) {
         GlStateManager.applyState6();
      } else {
         GlStateManager.applyState7();
      }

      GlStateManager.glColorMaterial(this.count7, this.count5);
      if (this.enabled) {
         GlStateManager.disableBlend();
      } else {
         GlStateManager.enableBlend();
      }

      GlStateManager.glStateAction3(this.count10, this.count3);
      if (this.enabled12) {
         GlStateManager.disableDepth();
      } else {
         GlStateManager.enableDepth();
      }

      GlStateManager.glDepthFunc(this.count11);
      GlStateManager.depthMask(this.enabled6);
      if (this.enabled2) {
         GlStateManager.applyState19();
      } else {
         GlStateManager.applyState20();
      }

      GlStateManager.applyIndex(this.count2);
      GlStateManager.applyFloat(this.factor2);
      GlStateManager.glFogf(this.factor4);
      GlStateManager.glFogfByFloat(this.factor5);
      if (this.enabled5) {
         GlStateManager.enableLighting();
      } else {
         GlStateManager.disableAlpha();
      }

      GlStateManager.glCullFace(this.count8);
      if (this.enabled3) {
         GlStateManager.applyState4();
      } else {
         GlStateManager.enableAlpha();
      }

      GlStateManager.applyFloat(this.factor7, this.factor);
      if (this.enabled13) {
         GlStateManager.applyState9();
      } else {
         GlStateManager.applyState31();
      }

      GlStateManager.glLogicOp(this.count4);
      if (this.enabled8) {
         GlStateManager.enableCull();
      } else {
         GlStateManager.disableCull();
      }

      if (this.enabled9) {
         GlStateManager.disableLighting();
      } else {
         GlStateManager.applyState8();
      }

      GlStateManager.glShadeModel(this.count9);

      for (int index2 = 0; index2 < 32; index2++) {
         if (this.enabledArray[index2]) {
            GlStateManager.disableLight(index2);
         } else {
            GlStateManager.bindCurrentTexture(index2);
         }

         GlStateManager.setActiveTexture(this.countArray[index2]);
      }

      GlStateManager.enableState5(this.count6);
      GL11.glLineWidth(this.factor8);
      GL11.glPointSize(this.factor6);
      if (this.enabled7) {
         GL11.glEnable(2848);
      } else {
         GL11.glDisable(2848);
      }

      if (this.enabled4) {
         GL11.glEnable(2832);
      } else {
         GL11.glDisable(2832);
      }

      if (this.runnableTask != null) {
         this.runnableTask.render(renderPass);
      }
   }

   public StepHistoryArray() {
      this.enabledArray = new boolean[32];
      this.countArray = new int[32];
      this.floatBuffer2 = BufferUtils.createFloatBuffer(16);
      this.floatBuffer = BufferUtils.createFloatBuffer(16);
      this.intBuffer = BufferUtils.createIntBuffer(16);
   }
}
