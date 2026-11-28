package net.augustus.render;

import net.augustus.utils.render.ShaderRenderPass;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class GlStateSnapshot {
   public boolean polygonOffsetEnabled;
   private float colorAlpha;
   private RenderPassTask restoreCallback;
   public float lineWidth;
   public int[] boundTextureIds;
   public boolean[] lightsEnabled = new boolean[8];
   public int colorMaterialMode;
   public boolean alphaTestEnabled;
   public int blendSrcRgb;
   public float fogStart;
   public boolean colorMaterialEnabled;
   public float pointSize;
   public int activeTextureUnit;
   public boolean rescaleNormalEnabled;
   public boolean blendEnabled;
   public int shadeModel;
   public boolean[] textureUnitsEnabled = new boolean[32];
   public boolean depthTestEnabled;
   public boolean cullEnabled;
   public int blendDstAlpha;
   public int blendDstFactor;
   public boolean depthMask;
   public int blendSrcAlpha;
   public float polygonOffsetUnits;
   public boolean lightingEnabled;
   public boolean colorLogicEnabled;
   public float fogDensity;
   public float alphaRef;
   public int colorMaterialFace;
   public boolean normalizeEnabled;
   private final FloatBuffer modelViewMatrix;
   public boolean lineSmoothEnabled;
   public boolean pointSmoothEnabled;
   public int depthFunc;
   private float colorBlue;
   private final IntBuffer viewportBuffer;
   private float colorGreen;
   public int blendDstRgb;
   public boolean fogEnabled;
   public int cullMode;
   private final FloatBuffer projectionMatrix;
   private float colorRed;
   public int fogMode;
   public int blendSrcFactor;
   public int alphaFunc;
   public int colorLogicOp;
   public float fogEnd;
   public float polygonOffsetFactor;

   private boolean matricesEqual(FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
      if (floatBuffer.capacity() != floatBuffer2.capacity()) {
         return false;
      } else {
         for (int i = 0; i < floatBuffer.capacity(); i++) {
            if (Math.abs(floatBuffer.get(i) - floatBuffer2.get(i)) > 0.001F) {
               return false;
            }
         }

         return true;
      }
   }

   public void printStateDiff(String str) {
      StringBuilder sb = new StringBuilder();
      sb.append("\n====== State Difference Check [" + str + "] ======\n");
      boolean flag = false;
      float red = GlStateManager.colorState.red;
      if (this.floatDiffers(this.colorRed, red)) {
         sb.append(String.format("  DIFF Color Red: Snapshot=%.2f, Current=%.2f\n", this.colorRed, GlStateManager.colorState.red));
         flag = true;
      }

      float green = GlStateManager.colorState.green;
      if (this.floatDiffers(this.colorGreen, green)) {
         sb.append(String.format("  DIFF Color Green: Snapshot=%.2f, Current=%.2f\n", this.colorGreen, GlStateManager.colorState.green));
         flag = true;
      }

      float blue = GlStateManager.colorState.blue;
      if (this.floatDiffers(this.colorBlue, blue)) {
         sb.append(String.format("  DIFF Color Blue: Snapshot=%.2f, Current=%.2f\n", this.colorBlue, GlStateManager.colorState.blue));
         flag = true;
      }

      float alpha = GlStateManager.colorState.alpha;
      if (this.floatDiffers(this.colorAlpha, alpha)) {
         sb.append(String.format("  DIFF Color Alpha: Snapshot=%.2f, Current=%.2f\n", this.colorAlpha, GlStateManager.colorState.alpha));
         flag = true;
      }

      boolean isAlphaEnabled = GlStateManager.isAlphaEnabled();
      if (this.alphaTestEnabled != isAlphaEnabled) {
         sb.append("  DIFF Alpha Enabled: Snapshot=" + this.alphaTestEnabled + ", Current=" + isAlphaEnabled + "\n");
         flag = true;
      }

      int alphaFunc2 = GlStateManager.getAlphaFunc();
      if (this.alphaFunc != alphaFunc2) {
         sb.append("  DIFF Alpha Func: Snapshot=" + this.alphaFunc + ", Current=" + alphaFunc2 + "\n");
         flag = true;
      }

      float alphaRef2 = GlStateManager.getAlphaRef();
      if (this.floatDiffers(this.alphaRef, alphaRef2)) {
         sb.append("  DIFF Alpha Ref: Snapshot=" + this.alphaRef + ", Current=" + alphaRef2 + "\n");
         flag = true;
      }

      boolean isLightingEnabled = GlStateManager.isLightingEnabled();
      if (this.lightingEnabled != isLightingEnabled) {
         sb.append("  DIFF Lighting Enabled: Snapshot=" + this.lightingEnabled + ", Current=" + isLightingEnabled + "\n");
         flag = true;
      }

      for (int i = 0; i < 8; i++) {
         boolean isLightEnabled = GlStateManager.isLightEnabled(i);
         if (this.lightsEnabled[i] != isLightEnabled) {
            sb.append("  DIFF Light[" + i + "] Enabled: Snapshot=" + this.lightsEnabled[i] + ", Current=" + isLightEnabled + "\n");
            flag = true;
         }
      }

      boolean isColorMaterialEnabled = GlStateManager.isColorMaterialEnabled();
      if (this.colorMaterialEnabled != isColorMaterialEnabled) {
         sb.append("  DIFF ColorMaterial Enabled: Snapshot=" + this.colorMaterialEnabled + ", Current=" + isColorMaterialEnabled + "\n");
         flag = true;
      }

      boolean isBlendEnabled = GlStateManager.isBlendEnabled();
      if (this.blendEnabled != isBlendEnabled) {
         sb.append("  DIFF Blend Enabled: Snapshot=" + this.blendEnabled + ", Current=" + isBlendEnabled + "\n");
         flag = true;
      }

      int blendSrcFactor2 = GlStateManager.getBlendSrcFactor();
      int blendDstFactor2 = GlStateManager.getBlendDstFactor();
      int blendSrcFactorAlpha = GlStateManager.getBlendSrcFactorAlpha();
      int blendDstFactorAlpha = GlStateManager.getBlendDstFactorAlpha();
      if (this.blendSrcRgb != blendSrcFactor2) {
         sb.append("  DIFF Blend Src RGB: Snapshot=" + this.blendSrcRgb + ", Current=" + blendSrcFactor2 + "\n");
         flag = true;
      }

      if (this.blendDstRgb != blendDstFactor2) {
         sb.append("  DIFF Blend Dst RGB: Snapshot=" + this.blendDstRgb + ", Current=" + blendDstFactor2 + "\n");
         flag = true;
      }

      if (this.blendSrcAlpha != blendSrcFactorAlpha) {
         sb.append("  DIFF Blend Src Alpha: Snapshot=" + this.blendSrcAlpha + ", Current=" + blendSrcFactorAlpha + "\n");
         flag = true;
      }

      if (this.blendDstAlpha != blendDstFactorAlpha) {
         sb.append("  DIFF Blend Dst Alpha: Snapshot=" + this.blendDstAlpha + ", Current=" + blendDstFactorAlpha + "\n");
         flag = true;
      }

      boolean isDepthEnabled = GlStateManager.isDepthEnabled();
      if (this.depthTestEnabled != isDepthEnabled) {
         sb.append("  DIFF DepthTest Enabled: Snapshot=" + this.depthTestEnabled + ", Current=" + isDepthEnabled + "\n");
         flag = true;
      }

      int depthFunc2 = GlStateManager.getDepthFunc();
      if (this.depthFunc != depthFunc2) {
         sb.append("  DIFF Depth Func: Snapshot=" + this.depthFunc + ", Current=" + depthFunc2 + "\n");
         flag = true;
      }

      boolean isDepthMaskEnabled = GlStateManager.isDepthMaskEnabled();
      if (this.depthMask != isDepthMaskEnabled) {
         sb.append("  DIFF DepthMask: Snapshot=" + this.depthMask + ", Current=" + isDepthMaskEnabled + "\n");
         flag = true;
      }

      boolean isFogEnabled = GlStateManager.isFogEnabled();
      if (this.fogEnabled != isFogEnabled) {
         sb.append("  DIFF Fog Enabled: Snapshot=" + this.fogEnabled + ", Current=" + isFogEnabled + "\n");
         flag = true;
      }

      boolean isCullEnabled = GlStateManager.isCullEnabled();
      if (this.cullEnabled != isCullEnabled) {
         sb.append("  DIFF Cull Enabled: Snapshot=" + this.cullEnabled + ", Current=" + isCullEnabled + "\n");
         flag = true;
      }

      int cullFaceMode = GlStateManager.getCullFaceMode();
      if (this.cullMode != cullFaceMode) {
         sb.append("  DIFF Cull Mode: Snapshot=" + this.cullMode + ", Current=" + cullFaceMode + "\n");
         flag = true;
      }

      for (int j = 0; j < 32; j++) {
         boolean isTexture2DEnabled = GlStateManager.isTexture2DEnabled(j);
         if (this.textureUnitsEnabled[j] != isTexture2DEnabled) {
            sb.append("  DIFF TextureUnit[" + j + "] Enabled: Snapshot=" + this.textureUnitsEnabled[j] + ", Current=" + isTexture2DEnabled + "\n");
            flag = true;
         }

         int boundTexture = GlStateManager.getBoundTexture(j);
         if (this.boundTextureIds[j] != boundTexture) {
            sb.append("  DIFF TextureUnit[" + j + "] Bound ID: Snapshot=" + this.boundTextureIds[j] + ", Current=" + boundTexture + "\n");
            flag = true;
         }
      }

      int activeTextureUnit2 = GlStateManager.getActiveTextureUnit();
      if (this.activeTextureUnit != activeTextureUnit2) {
         sb.append("  DIFF Active Texture Unit: Snapshot=" + this.activeTextureUnit + ", Current=" + activeTextureUnit2 + "\n");
         flag = true;
      }

      float f = GL11.glGetFloat(2849);
      if (this.floatDiffers(this.lineWidth, f)) {
         sb.append("  DIFF Line Width: Snapshot=" + this.lineWidth + ", Current=" + f + "\n");
         flag = true;
      }

      FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16);
      GL11.glGetFloat(2982, floatBuffer);
      if (!this.matricesEqual(this.modelViewMatrix, floatBuffer)) {
         sb.append("  DIFF ModelView Matrix has changed.\n");
         flag = true;
      }

      FloatBuffer floatBuffer2 = BufferUtils.createFloatBuffer(16);
      GL11.glGetFloat(2983, floatBuffer2);
      if (!this.matricesEqual(this.projectionMatrix, floatBuffer2)) {
         sb.append("  DIFF Projection Matrix has changed.\n");
         flag = true;
      }

      if (!flag) {
         sb.append("  No differences found (State matches Snapshot).\n");
      }

      sb.append("==================================================\n");
      System.out.println(sb.toString());
   }

   public void capture(RenderPassTask renderPassTask) {
      this.restoreCallback = renderPassTask;
      this.colorRed = GlStateManager.colorState.red;
      this.colorGreen = GlStateManager.colorState.green;
      this.colorBlue = GlStateManager.colorState.blue;
      this.colorAlpha = GlStateManager.colorState.alpha;
      int n = GL11.glGetInteger(2976);
      this.alphaTestEnabled = GlStateManager.isAlphaEnabled();
      this.alphaFunc = GlStateManager.getAlphaFunc();
      this.alphaRef = GlStateManager.getAlphaRef();
      this.lightingEnabled = GlStateManager.isLightingEnabled();

      for (int i = 0; i < 8; i++) {
         this.lightsEnabled[i] = GlStateManager.isLightEnabled(i);
      }

      this.colorMaterialEnabled = GlStateManager.isColorMaterialEnabled();
      this.colorMaterialFace = GlStateManager.getColorMaterialFace();
      this.colorMaterialMode = GlStateManager.getColorMaterialMode();
      this.blendEnabled = GlStateManager.isBlendEnabled();
      this.blendSrcFactor = GlStateManager.getBlendSrcFactor();
      this.blendDstFactor = GlStateManager.getBlendDstFactor();
      this.blendSrcRgb = GlStateManager.getBlendSrcFactor();
      this.blendDstRgb = GlStateManager.getBlendDstFactor();
      this.blendSrcAlpha = GlStateManager.getBlendSrcFactorAlpha();
      this.blendDstAlpha = GlStateManager.getBlendDstFactorAlpha();
      this.depthTestEnabled = GlStateManager.isDepthEnabled();
      this.depthFunc = GlStateManager.getDepthFunc();
      this.depthMask = GlStateManager.isDepthMaskEnabled();
      this.fogEnabled = GlStateManager.isFogEnabled();
      this.fogMode = GlStateManager.getFogMode();
      this.fogDensity = GlStateManager.getFogDensity();
      this.fogStart = GlStateManager.getFogStart();
      this.fogEnd = GlStateManager.getFogEnd();
      this.cullEnabled = GlStateManager.isCullEnabled();
      this.cullMode = GlStateManager.getCullFaceMode();
      this.polygonOffsetEnabled = GlStateManager.isPolygonOffsetEnabled();
      this.polygonOffsetFactor = GlStateManager.getPolygonOffsetFactor();
      this.polygonOffsetUnits = GlStateManager.getPolygonOffsetUnits();
      this.colorLogicEnabled = GlStateManager.isColorLogicEnabled();
      this.colorLogicOp = GlStateManager.getColorLogicOp();
      this.normalizeEnabled = GlStateManager.isNormalizeEnabled();
      this.rescaleNormalEnabled = GlStateManager.isRescaleNormalEnabled();
      this.shadeModel = GlStateManager.getActiveShadeModel();

      for (int j = 0; j < 32; j++) {
         this.textureUnitsEnabled[j] = GlStateManager.isTexture2DEnabled(j);
         this.boundTextureIds[j] = GlStateManager.getBoundTexture(j);
      }

      this.activeTextureUnit = GlStateManager.getActiveTextureUnit();
      ((Buffer)this.modelViewMatrix).clear();
      ((Buffer)this.projectionMatrix).clear();
      GL11.glGetFloat(2982, this.modelViewMatrix);
      GL11.glGetFloat(2983, this.projectionMatrix);
      this.lineWidth = GL11.glGetFloat(2849);
      this.pointSize = GL11.glGetFloat(2833);
      this.lineSmoothEnabled = GL11.glIsEnabled(2848);
      this.pointSmoothEnabled = GL11.glIsEnabled(2832);
      GL11.glGetInteger(2978, this.viewportBuffer);
      GL11.glMatrixMode(n);
   }

   public void restore(ShaderRenderPass shaderRenderPass) {
      int n = GL11.glGetInteger(2976);
      GL11.glViewport(this.viewportBuffer.get(0), this.viewportBuffer.get(1), this.viewportBuffer.get(2), this.viewportBuffer.get(3));
      GL11.glMatrixMode(5889);
      GL11.glLoadIdentity();
      GL11.glLoadMatrix(this.projectionMatrix);
      GL11.glMatrixMode(5888);
      GL11.glLoadIdentity();
      GL11.glLoadMatrix(this.modelViewMatrix);
      GL11.glMatrixMode(n);
      GlStateManager.resetColor();
      GlStateManager.color(this.colorRed, this.colorGreen, this.colorBlue, this.colorAlpha);
      if (this.alphaTestEnabled) {
         GlStateManager.enableAlpha();
      } else {
         GlStateManager.disableAlpha();
      }

      GlStateManager.alphaFunc(this.alphaFunc, this.alphaRef);
      if (this.lightingEnabled) {
         GlStateManager.enableLighting();
      } else {
         GlStateManager.disableLighting();
      }

      for (int i = 0; i < 8; i++) {
         if (this.lightsEnabled[i]) {
            GlStateManager.enableLight(i);
         } else {
            GlStateManager.disableLight(i);
         }
      }

      if (this.colorMaterialEnabled) {
         GlStateManager.enableColorMaterial();
      } else {
         GlStateManager.disableColorMaterial();
      }

      GlStateManager.colorMaterial(this.colorMaterialFace, this.colorMaterialMode);
      if (this.blendEnabled) {
         GlStateManager.enableBlend();
      } else {
         GlStateManager.disableBlend();
      }

      GlStateManager.tryBlendFuncSeparate(this.blendSrcRgb, this.blendDstRgb, this.blendSrcAlpha, this.blendDstAlpha);
      if (this.depthTestEnabled) {
         GlStateManager.enableDepth();
      } else {
         GlStateManager.disableDepth();
      }

      GlStateManager.depthFunc(this.depthFunc);
      GlStateManager.depthMask(this.depthMask);
      if (this.fogEnabled) {
         GlStateManager.enableFog();
      } else {
         GlStateManager.disableFog();
      }

      GlStateManager.setFog(this.fogMode);
      GlStateManager.setFogDensity(this.fogDensity);
      GlStateManager.setFogStart(this.fogStart);
      GlStateManager.setFogEnd(this.fogEnd);
      if (this.cullEnabled) {
         GlStateManager.enableCull();
      } else {
         GlStateManager.disableCull();
      }

      GlStateManager.cullFace(this.cullMode);
      if (this.polygonOffsetEnabled) {
         GlStateManager.enablePolygonOffset();
      } else {
         GlStateManager.disablePolygonOffset();
      }

      GlStateManager.doPolygonOffset(this.polygonOffsetFactor, this.polygonOffsetUnits);
      if (this.colorLogicEnabled) {
         GlStateManager.enableColorLogic();
      } else {
         GlStateManager.disableColorLogic();
      }

      GlStateManager.colorLogicOp(this.colorLogicOp);
      if (this.normalizeEnabled) {
         GlStateManager.enableNormalize();
      } else {
         GlStateManager.disableNormalize();
      }

      if (this.rescaleNormalEnabled) {
         GlStateManager.enableRescaleNormal();
      } else {
         GlStateManager.disableRescaleNormal();
      }

      GlStateManager.shadeModel(this.shadeModel);

      for (int j = 0; j < 32; j++) {
         if (this.textureUnitsEnabled[j]) {
            GlStateManager.enableTexture2D(j);
         } else {
            GlStateManager.disableTexture2D(j);
         }

         GlStateManager.bindTexture(this.boundTextureIds[j]);
      }

      GlStateManager.setActiveTexture(this.activeTextureUnit);
      GL11.glLineWidth(this.lineWidth);
      GL11.glPointSize(this.pointSize);
      if (this.lineSmoothEnabled) {
         GL11.glEnable(2848);
      } else {
         GL11.glDisable(2848);
      }

      if (this.pointSmoothEnabled) {
         GL11.glEnable(2832);
      } else {
         GL11.glDisable(2832);
      }

      if (this.restoreCallback != null) {
         this.restoreCallback.execute(shaderRenderPass);
      }
   }

   public GlStateSnapshot() {
      this.boundTextureIds = new int[32];
      this.modelViewMatrix = BufferUtils.createFloatBuffer(16);
      this.projectionMatrix = BufferUtils.createFloatBuffer(16);
      this.viewportBuffer = BufferUtils.createIntBuffer(16);
   }

   public static void dumpCurrentGlState(String str) {
      StringBuilder sb = new StringBuilder();
      sb.append("\n▓▓▓▓▓▓ GL STATE DUMP [" + str + "] ▓▓▓▓▓▓\n");
      sb.append(" [General]\n");
      sb.append(
         String.format(
            "  Color: R=%.2f, G=%.2f, B=%.2f, A=%.2f\n",
            GlStateManager.colorState.red,
            GlStateManager.colorState.green,
            GlStateManager.colorState.blue,
            GlStateManager.colorState.alpha
         )
      );
      sb.append(String.format("  Alpha Test: %b (Func: %d, Ref: %.2f)\n", GlStateManager.isAlphaEnabled(), GlStateManager.getAlphaFunc(), GlStateManager.getAlphaRef()));
      sb.append(
         String.format(
            "  Blend: %b (Src: %d, Dst: %d, SrcAlpha: %d, DstAlpha: %d)\n",
            GlStateManager.isBlendEnabled(),
            GlStateManager.getBlendSrcFactor(),
            GlStateManager.getBlendDstFactor(),
            GlStateManager.getBlendSrcFactorAlpha(),
            GlStateManager.getBlendDstFactorAlpha()
         )
      );
      sb.append(" [Depth & Geometry]\n");
      sb.append(String.format("  Depth Test: %b (Func: %d, Mask: %b)\n", GlStateManager.isDepthEnabled(), GlStateManager.getDepthFunc(), GlStateManager.isDepthMaskEnabled()));
      sb.append(String.format("  Cull Face: %b (Mode: %d)\n", GlStateManager.isCullEnabled(), GlStateManager.getCullFaceMode()));
      sb.append(String.format("  Polygon Offset: %b (Factor: %.2f, Units: %.2f)\n", GlStateManager.isPolygonOffsetEnabled(), GlStateManager.getPolygonOffsetFactor(), GlStateManager.getPolygonOffsetUnits()));
      sb.append(
         String.format(
            "  Fog: %b (Mode: %d, Density: %.2f, Start: %.2f, End: %.2f)\n",
            GlStateManager.isFogEnabled(),
            GlStateManager.getFogMode(),
            GlStateManager.getFogDensity(),
            GlStateManager.getFogStart(),
            GlStateManager.getFogEnd()
         )
      );
      sb.append(" [Lighting]\n");
      sb.append(String.format("  Lighting Enabled: %b\n", GlStateManager.isLightingEnabled()));
      sb.append(String.format("  Color Material: %b (Face: %d, Mode: %d)\n", GlStateManager.isColorMaterialEnabled(), GlStateManager.getColorMaterialFace(), GlStateManager.getColorMaterialMode()));
      sb.append(String.format("  Shade Model: %d\n", GlStateManager.getActiveShadeModel()));
      sb.append("  Lights: ");

      for (int i = 0; i < 8; i++) {
         if (GlStateManager.isLightEnabled(i)) {
            sb.append("[").append(i).append(":ON] ");
         }
      }

      sb.append("\n");
      sb.append(" [Textures]\n");
      sb.append("  Active Unit: ").append(GlStateManager.getActiveTextureUnit()).append("\n");
      boolean flag = false;

      for (int j = 0; j < 32; j++) {
         boolean isTexture2DEnabled = GlStateManager.isTexture2DEnabled(j);
         int boundTexture = GlStateManager.getBoundTexture(j);
         if (isTexture2DEnabled || boundTexture != 0) {
            sb.append(String.format("  Unit %d: Enabled=%-5b BoundID=%d\n", j, isTexture2DEnabled, boundTexture));
            flag = true;
         }
      }

      if (!flag) {
         sb.append("  (No textures enabled or bound)\n");
      }

      sb.append(" [Misc]\n");
      sb.append(String.format("  Line: Width=%.2f, Smooth=%b\n", GL11.glGetFloat(2849), GL11.glIsEnabled(2848)));
      sb.append(String.format("  Point: Size=%.2f, Smooth=%b\n", GL11.glGetFloat(2833), GL11.glIsEnabled(2832)));
      sb.append(String.format("  Color Logic: %b (Op: %d)\n", GlStateManager.isColorLogicEnabled(), GlStateManager.getColorLogicOp()));
      sb.append(String.format("  Normalize: %b, RescaleNormal: %b\n", GlStateManager.isNormalizeEnabled(), GlStateManager.isRescaleNormalEnabled()));
      IntBuffer intBuffer = BufferUtils.createIntBuffer(16);
      GL11.glGetInteger(2978, intBuffer);
      sb.append(String.format(" [Viewport] X=%d, Y=%d, W=%d, H=%d\n", intBuffer.get(0), intBuffer.get(1), intBuffer.get(2), intBuffer.get(3)));
      sb.append("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n");
      System.out.println(sb.toString());
   }

   private boolean floatDiffers(float f, float f2) {
      return Math.abs(f - f2) > 0.001F;
   }

   public static void checkGlStateDesync(String str) {
      StringBuilder sb = new StringBuilder();
      sb.append("\n====== GL State Desync Check [" + str + "] ======\n");
      boolean flag = false;
      boolean isTexture2DEnabled = GlStateManager.isTexture2DEnabled();
      boolean flag2 = GL11.glIsEnabled(3553);
      if (isTexture2DEnabled != flag2) {
         sb.append("  DESYNC Texture2D: GlStateManager=" + isTexture2DEnabled + " GL11=" + flag2 + "\n");
         flag = true;
      }

      int boundTexture = GlStateManager.getBoundTexture();
      int n = GL11.glGetInteger(32873);
      if (boundTexture != n) {
         sb.append("  DESYNC BoundTexture: GlStateManager=" + boundTexture + " GL11=" + n + "\n");
         flag = true;
      }

      boolean isBlendEnabled = GlStateManager.isBlendEnabled();
      boolean flag3 = GL11.glIsEnabled(3042);
      if (isBlendEnabled != flag3) {
         sb.append("  DESYNC Blend: GlStateManager=" + isBlendEnabled + " GL11=" + flag3 + "\n");
         flag = true;
      }

      int n2 = GL11.glGetInteger(3041);
      int n3 = GL11.glGetInteger(3040);
      int blendSrcFactor2 = GlStateManager.getBlendSrcFactor();
      int blendDstFactor2 = GlStateManager.getBlendDstFactor();
      if (blendSrcFactor2 != n2 || blendDstFactor2 != n3) {
         sb.append("  DESYNC BlendFunc: GlStateManager src=" + blendSrcFactor2 + " dst=" + blendDstFactor2 + " GL11 src=" + n2 + " dst=" + n3 + "\n");
         flag = true;
      }

      int blendSrcFactor3 = GlStateManager.getBlendSrcFactor();
      int blendDstFactor3 = GlStateManager.getBlendDstFactor();
      int blendSrcFactorAlpha = GlStateManager.getBlendSrcFactorAlpha();
      int blendDstFactorAlpha = GlStateManager.getBlendDstFactorAlpha();
      int n4 = GL11.glGetInteger(32969);
      int n5 = GL11.glGetInteger(32968);
      int n6 = GL11.glGetInteger(32971);
      int n7 = GL11.glGetInteger(32970);
      if (blendSrcFactor3 != n4 || blendDstFactor3 != n5 || blendSrcFactorAlpha != n6 || blendDstFactorAlpha != n7) {
         sb.append(
            "  DESYNC BlendFunc try: GlStateManager src="
               + blendSrcFactor2
               + " dst="
               + blendDstFactor2
               + " alphaSrc="
               + blendSrcFactorAlpha
               + " alphaDst="
               + blendDstFactorAlpha
               + " GL11 src="
               + n2
               + " dst="
               + n3
               + " alphaSrc="
               + n6
               + " alphaDst="
               + n7
               + "\n"
         );
         flag = true;
      }

      boolean isAlphaEnabled = GlStateManager.isAlphaEnabled();
      boolean flag4 = GL11.glIsEnabled(3008);
      if (isAlphaEnabled != flag4) {
         sb.append("  DESYNC AlphaTest: GlStateManager=" + isAlphaEnabled + " GL11=" + flag4 + "\n");
         flag = true;
      }

      int alphaFunc2 = GlStateManager.getAlphaFunc();
      float alphaRef2 = GlStateManager.getAlphaRef();
      int n8 = GL11.glGetInteger(3009);
      float f = GL11.glGetFloat(3010);
      if (alphaFunc2 != n8 || Math.abs(alphaRef2 - f) > 0.001F) {
         sb.append("  DESYNC AlphaFunc: GlStateManager func=" + alphaFunc2 + " ref=" + alphaRef2 + " GL11 func=" + n8 + " ref=" + f + "\n");
         flag = true;
      }

      boolean isDepthEnabled = GlStateManager.isDepthEnabled();
      boolean flag5 = GL11.glIsEnabled(2929);
      if (isDepthEnabled != flag5) {
         sb.append("  DESYNC DepthTest: GlStateManager=" + isDepthEnabled + " GL11=" + flag5 + "\n");
         flag = true;
      }

      int depthFunc2 = GlStateManager.getDepthFunc();
      int n9 = GL11.glGetInteger(2932);
      if (depthFunc2 != n9) {
         sb.append("  DESYNC DepthFunc: GlStateManager=" + depthFunc2 + " GL11=" + n9 + "\n");
         flag = true;
      }

      boolean isDepthMaskEnabled = GlStateManager.isDepthMaskEnabled();
      boolean flag6 = GL11.glGetBoolean(2930);
      if (isDepthMaskEnabled != flag6) {
         sb.append("  DESYNC DepthMask: GlStateManager=" + isDepthMaskEnabled + " GL11=" + flag6 + "\n");
         flag = true;
      }

      boolean isLightingEnabled = GlStateManager.isLightingEnabled();
      boolean flag7 = GL11.glIsEnabled(2896);
      if (isLightingEnabled != flag7) {
         sb.append("  DESYNC Lighting: GlStateManager=" + isLightingEnabled + " GL11=" + flag7 + "\n");
         flag = true;
      }

      boolean isCullEnabled = GlStateManager.isCullEnabled();
      boolean flag8 = GL11.glIsEnabled(2884);
      if (isCullEnabled != flag8) {
         sb.append("  DESYNC CullFace: GlStateManager=" + isCullEnabled + " GL11=" + flag8 + "\n");
         flag = true;
      }

      boolean isFogEnabled = GlStateManager.isFogEnabled();
      boolean flag9 = GL11.glIsEnabled(2912);
      if (isFogEnabled != flag9) {
         sb.append("  DESYNC Fog: GlStateManager=" + isFogEnabled + " GL11=" + flag9 + "\n");
         flag = true;
      }

      boolean isColorMaterialEnabled = GlStateManager.isColorMaterialEnabled();
      boolean flag10 = GL11.glIsEnabled(2903);
      if (isColorMaterialEnabled != flag10) {
         sb.append("  DESYNC ColorMaterial: GlStateManager=" + isColorMaterialEnabled + " GL11=" + flag10 + "\n");
         flag = true;
      }

      boolean isNormalizeEnabled = GlStateManager.isNormalizeEnabled();
      boolean flag11 = GL11.glIsEnabled(2977);
      if (isNormalizeEnabled != flag11) {
         sb.append("  DESYNC Normalize: GlStateManager=" + isNormalizeEnabled + " GL11=" + flag11 + "\n");
         flag = true;
      }

      boolean isColorLogicEnabled = GlStateManager.isColorLogicEnabled();
      boolean flag12 = GL11.glIsEnabled(3058);
      if (isColorLogicEnabled != flag12) {
         sb.append("  DESYNC ColorLogicOp: GlStateManager=" + isColorLogicEnabled + " GL11=" + flag12 + "\n");
         flag = true;
      }

      boolean isPolygonOffsetEnabled = GlStateManager.isPolygonOffsetEnabled();
      boolean flag13 = GL11.glIsEnabled(32823);
      if (isPolygonOffsetEnabled != flag13) {
         sb.append("  DESYNC PolygonOffset: GlStateManager=" + isPolygonOffsetEnabled + " GL11=" + flag13 + "\n");
         flag = true;
      }

      FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16);
      GL11.glGetFloat(2816, floatBuffer);
      float f2 = floatBuffer.get(0);
      float f3 = floatBuffer.get(1);
      float f4 = floatBuffer.get(2);
      float f5 = floatBuffer.get(3);
      float red = GlStateManager.colorState.red;
      float green = GlStateManager.colorState.green;
      float blue = GlStateManager.colorState.blue;
      float alpha = GlStateManager.colorState.alpha;
      if (red >= 0.0F
         && green >= 0.0F
         && blue >= 0.0F
         && alpha >= 0.0F
         && (Math.abs(red - f2) > 0.01F || Math.abs(green - f3) > 0.01F || Math.abs(blue - f4) > 0.01F || Math.abs(alpha - f5) > 0.01F)) {
         sb.append(
            String.format(
               "  DESYNC Color: GlStateManager=(%.2f, %.2f, %.2f, %.2f) GL11=(%.2f, %.2f, %.2f, %.2f)\n",
               red,
               green,
               blue,
               alpha,
               f2,
               f3,
               f4,
               f5
            )
         );
         flag = true;
      }

      if (!flag) {
         sb.append("  No desync detected.\n");
      }

      sb.append("================================================\n");
      System.out.println(sb.toString());
   }

}
