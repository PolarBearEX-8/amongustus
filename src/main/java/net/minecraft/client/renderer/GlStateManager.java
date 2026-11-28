package net.minecraft.client.renderer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.optifine.render.GlAlphaState;
import net.optifine.SmartAnimations;
import net.optifine.config.Config;
import net.optifine.render.GlBlendState;
import net.optifine.shaders.Shaders;
import net.optifine.util.LockCounter;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public class GlStateManager {
   private static GlStateManager.AlphaState alphaState = new GlStateManager.AlphaState();
   private static GlStateManager.BooleanState lightingState = new GlStateManager.BooleanState(2896);
   private static GlStateManager.BooleanState[] lightState = new GlStateManager.BooleanState[8];
   private static GlStateManager.ColorMaterialState colorMaterialState = new GlStateManager.ColorMaterialState();
   private static GlStateManager.BlendState blendState = new GlStateManager.BlendState();
   private static GlStateManager.DepthState depthState = new GlStateManager.DepthState();
   private static GlStateManager.FogState fogState = new GlStateManager.FogState();
   private static GlStateManager.CullState cullState = new GlStateManager.CullState();
   private static GlStateManager.PolygonOffsetState polygonOffsetState = new GlStateManager.PolygonOffsetState();
   private static GlStateManager.ColorLogicState colorLogicState = new GlStateManager.ColorLogicState();
   private static GlStateManager.TexGenState texGenState = new GlStateManager.TexGenState();
   private static GlStateManager.ClearState clearState = new GlStateManager.ClearState();
   private static GlStateManager.StencilState stencilState = new GlStateManager.StencilState();
   private static GlStateManager.BooleanState normalizeState = new GlStateManager.BooleanState(2977);
   private static int activeTextureUnit = 0;
   private static GlStateManager.TextureState[] textureState = new GlStateManager.TextureState[32];
   private static int activeShadeModel = 7425;
   private static GlStateManager.BooleanState rescaleNormalState = new GlStateManager.BooleanState(32826);
   private static GlStateManager.ColorMask colorMaskState = new GlStateManager.ColorMask();
   public static GlStateManager.Color colorState = new GlStateManager.Color();
   public static boolean clearEnabled = true;
   private static LockCounter alphaLock = new LockCounter();
   private static GlAlphaState alphaLockState = new GlAlphaState();
   private static LockCounter blendLock = new LockCounter();
   private static GlBlendState blendLockState = new GlBlendState();
   private static boolean creatingDisplayList = false;

   public static int getDepthFunc() {
      return depthState.depthFunc;
   }

   public static void glEndList() {
      GL11.glEndList();
      creatingDisplayList = false;
   }

   public static void disablePolygonOffset() {
      polygonOffsetState.polygonOffsetFill.setDisabled();
   }

   public static void enableBlend() {
      if (blendLock.isLocked()) {
         blendLockState.setEnabled();
      } else {
         blendState.blend.setEnabled();
      }
   }

   public static float getPolygonOffsetFactor() {
      return polygonOffsetState.factor;
   }

   public static void cullFace(int n) {
      if (n != cullState.mode) {
         cullState.mode = n;
         GL11.glCullFace(n);
      }
   }

   static {
      for (int i = 0; i < 8; i++) {
         lightState[i] = new GlStateManager.BooleanState(16384 + i);
      }

      for (int j = 0; j < textureState.length; j++) {
         textureState[j] = new GlStateManager.TextureState();
      }
   }

   public static void lockBlend(GlBlendState glBlendState) {
      if (!blendLock.isLocked()) {
         getBlendState(blendLockState);
         setBlendState(glBlendState);
         blendLock.lock();
      }
   }

   public static void color(float f, float f2, float f3) {
      color(f, f2, f3, 1.0F);
   }

   public static void glNormalPointer(int n, int n2, ByteBuffer buf) {
      GL11.glNormalPointer(n, n2, buf);
   }

   public static void enableColorMaterial() {
      colorMaterialState.colorMaterial.setEnabled();
   }

   public static void setFogStart(float f) {
      if (f != fogState.start) {
         fogState.start = f;
         GL11.glFogf(2915, f);
      }
   }

   public static void depthMask(boolean flag) {
      if (flag != depthState.maskEnabled) {
         depthState.maskEnabled = flag;
         GL11.glDepthMask(flag);
      }
   }

   public static boolean isLightEnabled(int n) {
      return GlStateManager.BooleanState.getCurrentState(lightState[n]);
   }

   public static void setActiveTexture(int n) {
      if (activeTextureUnit != n - OpenGlHelper.defaultTexUnit) {
         activeTextureUnit = n - OpenGlHelper.defaultTexUnit;
         OpenGlHelper.setActiveTexture(n);
      }
   }

   public static void ortho(double d, double d2, double d3, double d4, double d5, double d6) {
      GL11.glOrtho(d, d2, d3, d4, d5, d6);
   }

   public static void disableBlend() {
      if (blendLock.isLocked()) {
         blendLockState.setDisabled();
      } else {
         blendState.blend.setDisabled();
      }
   }

   public static void setFogEnabled(boolean flag) {
      fogState.fog.setState(flag);
   }

   public static void enableAlpha() {
      if (alphaLock.isLocked()) {
         alphaLockState.setEnabled();
      } else {
         alphaState.alphaTest.setEnabled();
      }
   }

   public static int getActiveTextureUnit() {
      return OpenGlHelper.defaultTexUnit + activeTextureUnit;
   }

   public static boolean isRescaleNormalEnabled() {
      return GlStateManager.BooleanState.getCurrentState(rescaleNormalState);
   }

   private static GlStateManager.TexGenCoord texGenCoord(GlStateManager.TexGen texGen) {
      switch (texGen) {
         case S:
            return texGenState.s;
         case T:
            return texGenState.t;
         case R:
            return texGenState.r;
         case Q:
            return texGenState.q;
         default:
            return texGenState.s;
      }
   }

   public static void disableColorLogic() {
      colorLogicState.colorLogicOp.setDisabled();
   }

   public static void deleteTextures(IntBuffer intBuffer) {
      ((Buffer)intBuffer).rewind();

      while (intBuffer.position() < intBuffer.limit()) {
         deleteTexture(intBuffer.get());
      }

      ((Buffer)intBuffer).rewind();
   }

   public static void disableFog() {
      fogState.fog.setDisabled();
   }

   public static void colorMaterial(int n, int n2) {
      if (n != colorMaterialState.face || n2 != colorMaterialState.mode) {
         colorMaterialState.face = n;
         colorMaterialState.mode = n2;
         GL11.glColorMaterial(n, n2);
      }
   }

   public static void enableDepth() {
      depthState.depthTest.setEnabled();
   }

   public static void glDisableClientState(int n) {
      GL11.glDisableClientState(n);
   }

   public static void setFog(int n) {
      if (n != fogState.mode) {
         fogState.mode = n;
         GL11.glFogi(2917, n);
         if (Config.isShaderPackLoaded()) {
            Shaders.setFogMode(n);
         }
      }
   }

   public static float getAlphaRef() {
      return alphaState.ref;
   }

   public static boolean isFogEnabled() {
      return GlStateManager.BooleanState.getCurrentState(fogState.fog);
   }

   public static int getBlendDstFactor() {
      return blendState.dstFactor;
   }

   public static void enablePolygonOffset() {
      polygonOffsetState.polygonOffsetFill.setEnabled();
   }

   public static void pushMatrix() {
      GL11.glPushMatrix();
   }

   public static void glTexImage2D(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, IntBuffer intBuffer) {
      GL11.glTexImage2D(n, n2, n3, n4, n5, n6, n7, n8, intBuffer);
   }

   public static boolean isBlendEnabled() {
      return GlStateManager.BooleanState.getCurrentState(blendState.blend);
   }

   public static void pushAttrib() {
      GL11.glPushAttrib(8256);
   }

   public static int getBlendDstFactorAlpha() {
      return blendState.dstFactorAlpha;
   }

   public static int glGetError() {
      return GL11.glGetError();
   }

   public static boolean isColorLogicEnabled() {
      return GlStateManager.BooleanState.getCurrentState(colorLogicState.colorLogicOp);
   }

   public static void unlockAlpha() {
      if (alphaLock.unlock()) {
         setAlphaState(alphaLockState);
      }
   }

   public static boolean isTexture2DEnabled() {
      return GlStateManager.BooleanState.getCurrentState(textureState[activeTextureUnit].texture2DState);
   }

   public static void setAlphaState(GlAlphaState glAlphaState) {
      if (alphaLock.isLocked()) {
         alphaLockState.setState(glAlphaState);
      } else {
         alphaState.alphaTest.setState(glAlphaState.isEnabled());
         alphaFunc(glAlphaState.getFunc(), glAlphaState.getRef());
      }
   }

   public static void enableTexture2D(int n) {
      textureState[n].texture2DState.setEnabled();
   }

   public static void checkBoundTexture() {
      if (Config.isMinecraftThread()) {
         int n = GL11.glGetInteger(34016);
         int n2 = GL11.glGetInteger(32873);
         int activeTextureUnit2 = getActiveTextureUnit();
         int boundTexture = getBoundTexture();
         if (boundTexture > 0 && (n != activeTextureUnit2 || n2 != boundTexture)) {
            Config.dbg("checkTexture: act: " + activeTextureUnit2 + ", glAct: " + n + ", tex: " + boundTexture + ", glTex: " + n2);
         }
      }
   }

   public static void colorLogicOp(int n) {
      if (n != colorLogicState.opcode) {
         colorLogicState.opcode = n;
         GL11.glLogicOp(n);
      }
   }

   public static int getCullFaceMode() {
      return cullState.mode;
   }

   public static void disableLight(int n) {
      lightState[n].setDisabled();
   }

   public static void scale(double d, double d2, double d3) {
      GL11.glScaled(d, d2, d3);
   }

   public static void matrixMode(int n) {
      GL11.glMatrixMode(n);
   }

   public static void colorMask(boolean flag, boolean flag2, boolean flag3, boolean flag4) {
      if (flag != colorMaskState.red || flag2 != colorMaskState.green || flag3 != colorMaskState.blue || flag4 != colorMaskState.alpha) {
         colorMaskState.red = flag;
         colorMaskState.green = flag2;
         colorMaskState.blue = flag3;
         colorMaskState.alpha = flag4;
         GL11.glColorMask(flag, flag2, flag3, flag4);
      }
   }

   public static boolean isTexture2DEnabled(int n) {
      return GlStateManager.BooleanState.getCurrentState(textureState[n].texture2DState);
   }

   public static void viewport(int n, int n2, int n3, int n4) {
      GL11.glViewport(n, n2, n3, n4);
   }

   public static void glColorPointer(int n, int n2, int n3, int n4) {
      GL11.glColorPointer(n, n2, n3, (long)n4);
   }

   public static boolean isDepthEnabled() {
      return GlStateManager.BooleanState.getCurrentState(depthState.depthTest);
   }

   public static void shadeModel(int n) {
      if (n != activeShadeModel) {
         activeShadeModel = n;
         GL11.glShadeModel(n);
      }
   }

   public static void clear(int n) {
      if (clearEnabled) {
         GL11.glClear(n);
      }
   }

   public static void getFloat(int n, FloatBuffer floatBuffer) {
      GL11.glGetFloat(n, floatBuffer);
   }

   public static void glVertexPointer(int n, int n2, int n3, int n4) {
      GL11.glVertexPointer(n, n2, n3, (long)n4);
   }

   public static void disableDepth() {
      depthState.depthTest.setDisabled();
   }

   public static int getActiveShadeModel() {
      return activeShadeModel;
   }

   public static void multMatrix(FloatBuffer floatBuffer) {
      GL11.glMultMatrix(floatBuffer);
   }

   public static void disableTexGenCoord(GlStateManager.TexGen texGen) {
      texGenCoord(texGen).textureGen.setDisabled();
   }

   public static void depthFunc(int n) {
      if (n != depthState.depthFunc) {
         depthState.depthFunc = n;
         GL11.glDepthFunc(n);
      }
   }

   public static int getBoundTexture() {
      return textureState[activeTextureUnit].textureName;
   }

   public static void disableRescaleNormal() {
      rescaleNormalState.setDisabled();
   }

   public static void translate(float f, float f2, float f3) {
      GL11.glTranslatef(f, f2, f3);
   }

   public static void enableNormalize() {
      normalizeState.setEnabled();
   }

   public static void color(float f, float f2, float f3, float f4) {
      if (f != colorState.red || f2 != colorState.green || f3 != colorState.blue || f4 != colorState.alpha) {
         colorState.red = f;
         colorState.green = f2;
         colorState.blue = f3;
         colorState.alpha = f4;
         GL11.glColor4f(f, f2, f3, f4);
      }
   }

   public static void popAttrib() {
      GL11.glPopAttrib();
   }

   public static int getAlphaFunc() {
      return alphaState.func;
   }

   public static void disableTexture2D() {
      textureState[activeTextureUnit].texture2DState.setDisabled();
   }

   public static boolean isDepthMaskEnabled() {
      return depthState.maskEnabled;
   }

   public static boolean isAlphaEnabled() {
      return GlStateManager.BooleanState.getCurrentState(alphaState.alphaTest);
   }

   public static void enableRescaleNormal() {
      rescaleNormalState.setEnabled();
   }

   public static void glEnd() {
      GL11.glEnd();
   }

   public static void disableLighting() {
      lightingState.setDisabled();
   }

   public static void glFogi(int n, int n2) {
      GL11.glFogi(n, n2);
   }

   public static void loadIdentity() {
      GL11.glLoadIdentity();
   }

   public static void disableAlpha() {
      if (alphaLock.isLocked()) {
         alphaLockState.setDisabled();
      } else {
         alphaState.alphaTest.setDisabled();
      }
   }

   public static int generateTexture() {
      return GL11.glGenTextures();
   }

   public static void glEnableClientState(int n) {
      GL11.glEnableClientState(n);
   }

   public static void enableCull() {
      cullState.cullFace.setEnabled();
   }

   public static void callList(int n) {
      GL11.glCallList(n);
      if (Config.isShaderPackLoaded() && !creatingDisplayList) {
         int countInstances = Shaders.activeProgram.getCountInstances();
         if (countInstances > 1) {
            for (int i = 1; i < countInstances; i++) {
               Shaders.uniform_instanceId.setValue(i);
               GL11.glCallList(n);
            }

            Shaders.uniform_instanceId.setValue(0);
         }
      }
   }

   public static float getFogStart() {
      return fogState.start;
   }

   public static void glVertexPointer2(int n, int n2, int n3, ByteBuffer buf) {
      GL11.glVertexPointer(n, n2, n3, buf);
   }

   public static void enableTexGenCoord(GlStateManager.TexGen texGen) {
      texGenCoord(texGen).textureGen.setEnabled();
   }

   public static void glColorPointer2(int n, int n2, int n3, ByteBuffer buf) {
      GL11.glColorPointer(n, n2, n3, buf);
   }

   public static void glTexCoordPointer(int n, int n2, int n3, ByteBuffer buf) {
      GL11.glTexCoordPointer(n, n2, n3, buf);
   }

   public static void getAlphaState(GlAlphaState glAlphaState) {
      if (alphaLock.isLocked()) {
         glAlphaState.setState(alphaLockState);
      } else {
         glAlphaState.setState(GlStateManager.BooleanState.getCurrentState(alphaState.alphaTest), alphaState.func, alphaState.ref);
      }
   }

   public static void glFog(int n, FloatBuffer floatBuffer) {
      GL11.glFog(n, floatBuffer);
   }

   public static void enableFog() {
      fogState.fog.setEnabled();
   }

   public static void callLists(IntBuffer intBuffer) {
      GL11.glCallLists(intBuffer);
      if (Config.isShaderPackLoaded() && !creatingDisplayList) {
         int countInstances = Shaders.activeProgram.getCountInstances();
         if (countInstances > 1) {
            for (int i = 1; i < countInstances; i++) {
               Shaders.uniform_instanceId.setValue(i);
               GL11.glCallLists(intBuffer);
            }

            Shaders.uniform_instanceId.setValue(0);
         }
      }
   }

   public static float getPolygonOffsetUnits() {
      return polygonOffsetState.units;
   }

   public static void clearDepth(double d) {
      if (d != clearState.depth) {
         clearState.depth = d;
         GL11.glClearDepth(d);
      }
   }

   public static int getFogMode() {
      return fogState.mode;
   }

   public static float getFogEnd() {
      return fogState.end;
   }

   public static boolean isCullEnabled() {
      return GlStateManager.BooleanState.getCurrentState(cullState.cullFace);
   }

   public static int getBoundTexture(int n) {
      return textureState[n].textureName;
   }

   public static int glGetTexLevelParameteri(int n, int n2, int n3) {
      return GL11.glGetTexLevelParameteri(n, n2, n3);
   }

   public static void setFogDensity(float f) {
      if (f < 0.0F) {
         f = 0.0F;
      }

      if (f != fogState.density) {
         fogState.density = f;
         GL11.glFogf(2914, f);
         if (Config.isShaderPackLoaded()) {
            Shaders.setFogDensity(f);
         }
      }
   }

   public static void enableLight(int n) {
      lightState[n].setEnabled();
   }

   public static void texGen(GlStateManager.TexGen texGen, int n, FloatBuffer floatBuffer) {
      GL11.glTexGen(texGenCoord(texGen).coord, n, floatBuffer);
   }

   public static void texGen(GlStateManager.TexGen texGen, int n) {
      GlStateManager.TexGenCoord texGenCoord = texGenCoord(texGen);
      if (n != texGenCoord.param) {
         texGenCoord.param = n;
         GL11.glTexGeni(texGenCoord.coord, 9472, n);
      }
   }

   public static int getColorLogicOp() {
      return colorLogicState.opcode;
   }

   public static boolean isLightingEnabled() {
      return GlStateManager.BooleanState.getCurrentState(lightingState);
   }

   public static int getBlendSrcFactor() {
      return blendState.srcFactor;
   }

   public static void deleteTexture(int n) {
      if (n != 0) {
         GL11.glDeleteTextures(n);

         for (GlStateManager.TextureState textureState2 : textureState) {
            if (textureState2.textureName == n) {
               textureState2.textureName = 0;
            }
         }
      }
   }

   public static void unlockBlend() {
      if (blendLock.unlock()) {
         setBlendState(blendLockState);
      }
   }

   public static void glTexParameteri(int n, int n2, int n3) {
      GL11.glTexParameteri(n, n2, n3);
   }

   public static boolean isNormalizeEnabled() {
      return GlStateManager.BooleanState.getCurrentState(normalizeState);
   }

   public static void popMatrix() {
      GL11.glPopMatrix();
   }

   public static void disableTexture2D(int n) {
      textureState[n].texture2DState.setDisabled();
   }

   public static int getColorMaterialFace() {
      return colorMaterialState.face;
   }

   public static void glTexSubImage2D(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, IntBuffer intBuffer) {
      GL11.glTexSubImage2D(n, n2, n3, n4, n5, n6, n7, n8, intBuffer);
   }

   public static void glNewList(int n, int n2) {
      GL11.glNewList(n, n2);
      creatingDisplayList = true;
   }

   public static void disableCull() {
      cullState.cullFace.setDisabled();
   }

   public static void alphaFunc(int n, float f) {
      if (alphaLock.isLocked()) {
         alphaLockState.setFuncRef(n, f);
      } else if (n != alphaState.func || f != alphaState.ref) {
         alphaState.func = n;
         alphaState.ref = f;
         GL11.glAlphaFunc(n, f);
      }
   }

   public static void clearColor(float f, float f2, float f3, float f4) {
      if (f != clearState.color.red || f2 != clearState.color.green || f3 != clearState.color.blue || f4 != clearState.color.alpha) {
         clearState.color.red = f;
         clearState.color.green = f2;
         clearState.color.blue = f3;
         clearState.color.alpha = f4;
         GL11.glClearColor(f, f2, f3, f4);
      }
   }

   public static void glDrawArrays(int n, int n2, int n3) {
      GL11.glDrawArrays(n, n2, n3);
      if (Config.isShaderPackLoaded() && !creatingDisplayList) {
         int countInstances = Shaders.activeProgram.getCountInstances();
         if (countInstances > 1) {
            for (int i = 1; i < countInstances; i++) {
               Shaders.uniform_instanceId.setValue(i);
               GL11.glDrawArrays(n, n2, n3);
            }

            Shaders.uniform_instanceId.setValue(0);
         }
      }
   }

   public static int getColorMaterialMode() {
      return colorMaterialState.mode;
   }

   public static boolean isPolygonOffsetEnabled() {
      return GlStateManager.BooleanState.getCurrentState(polygonOffsetState.polygonOffsetFill);
   }

   public static void doPolygonOffset(float f, float f2) {
      if (f != polygonOffsetState.factor || f2 != polygonOffsetState.units) {
         polygonOffsetState.factor = f;
         polygonOffsetState.units = f2;
         GL11.glPolygonOffset(f, f2);
      }
   }

   public static void rotate(float f, float f2, float f3, float f4) {
      GL11.glRotatef(f, f2, f3, f4);
   }

   public static void setBlendState(GlBlendState glBlendState) {
      if (blendLock.isLocked()) {
         blendLockState.setState(glBlendState);
      } else {
         blendState.blend.setState(glBlendState.isEnabled());
         if (!glBlendState.isSeparate()) {
            blendFunc(glBlendState.getSrcFactor(), glBlendState.getDstFactor());
         } else {
            tryBlendFuncSeparate(glBlendState.getSrcFactor(), glBlendState.getDstFactor(), glBlendState.getSrcFactorAlpha(), glBlendState.getDstFactorAlpha());
         }
      }
   }

   public static int getBlendSrcFactorAlpha() {
      return blendState.srcFactorAlpha;
   }

   public static void enableColorLogic() {
      colorLogicState.colorLogicOp.setEnabled();
   }

   public static void bindTexture(int n) {
      if (n != textureState[activeTextureUnit].textureName) {
         textureState[activeTextureUnit].textureName = n;
         GL11.glBindTexture(3553, n);
         if (SmartAnimations.isActive()) {
            SmartAnimations.textureRendered(n);
         }
      }
   }

   public static void disableColorMaterial() {
      colorMaterialState.colorMaterial.setDisabled();
   }

   public static void blendFunc(int n, int n2) {
      if (blendLock.isLocked()) {
         blendLockState.setFactors(n, n2);
      } else if (n != blendState.srcFactor || n2 != blendState.dstFactor || n != blendState.srcFactorAlpha || n2 != blendState.dstFactorAlpha) {
         blendState.srcFactor = n;
         blendState.dstFactor = n2;
         blendState.srcFactorAlpha = n;
         blendState.dstFactorAlpha = n2;
         if (Config.isShaderPackLoaded()) {
            Shaders.uniform_blendFunc.setValue(n, n2, n, n2);
         }

         GL11.glBlendFunc(n, n2);
      }
   }

   public static void bindCurrentTexture() {
      GL11.glBindTexture(3553, textureState[activeTextureUnit].textureName);
   }

   public static void lockAlpha(GlAlphaState glAlphaState) {
      if (!alphaLock.isLocked()) {
         getAlphaState(alphaLockState);
         setAlphaState(glAlphaState);
         alphaLock.lock();
      }
   }

   public static boolean isColorMaterialEnabled() {
      return GlStateManager.BooleanState.getCurrentState(colorMaterialState.colorMaterial);
   }

   public static float getFogDensity() {
      return fogState.density;
   }

   public static void scale(float f, float f2, float f3) {
      GL11.glScalef(f, f2, f3);
   }

   public static void glDeleteLists(int n, int n2) {
      GL11.glDeleteLists(n, n2);
   }

   public static void disableNormalize() {
      normalizeState.setDisabled();
   }

   public static void tryBlendFuncSeparate(int n, int n2, int n3, int n4) {
      if (blendLock.isLocked()) {
         blendLockState.setFactors(n, n2, n3, n4);
      } else if (n != blendState.srcFactor || n2 != blendState.dstFactor || n3 != blendState.srcFactorAlpha || n4 != blendState.dstFactorAlpha) {
         blendState.srcFactor = n;
         blendState.dstFactor = n2;
         blendState.srcFactorAlpha = n3;
         blendState.dstFactorAlpha = n4;
         if (Config.isShaderPackLoaded()) {
            Shaders.uniform_blendFunc.setValue(n, n2, n3, n4);
         }

         OpenGlHelper.glBlendFunc(n, n2, n3, n4);
      }
   }

   public static void getBlendState(GlBlendState glBlendState) {
      if (blendLock.isLocked()) {
         glBlendState.setState(blendLockState);
      } else {
         glBlendState.setState(
            GlStateManager.BooleanState.getCurrentState(blendState.blend), blendState.srcFactor, blendState.dstFactor, blendState.srcFactorAlpha, blendState.dstFactorAlpha
         );
      }
   }

   public static void glGetTexImage(int n, int n2, int n3, int n4, IntBuffer intBuffer) {
      GL11.glGetTexImage(n, n2, n3, n4, intBuffer);
   }

   public static void glMultiDrawArrays(int n, IntBuffer intBuffer, IntBuffer intBuffer2) {
      GL14.glMultiDrawArrays(n, intBuffer, intBuffer2);
      if (Config.isShaderPackLoaded() && !creatingDisplayList) {
         int countInstances = Shaders.activeProgram.getCountInstances();
         if (countInstances > 1) {
            for (int i = 1; i < countInstances; i++) {
               Shaders.uniform_instanceId.setValue(i);
               GL14.glMultiDrawArrays(n, intBuffer, intBuffer2);
            }

            Shaders.uniform_instanceId.setValue(0);
         }
      }
   }

   public static void glTexCoordPointer2(int n, int n2, int n3, int n4) {
      GL11.glTexCoordPointer(n, n2, n3, (long)n4);
   }

   public static void enableLighting() {
      lightingState.setEnabled();
   }

   public static void resetColor() {
      colorState.red = colorState.green = colorState.blue = colorState.alpha = -1.0F;
   }

   public static void setFogEnd(float f) {
      if (f != fogState.end) {
         fogState.end = f;
         GL11.glFogf(2916, f);
      }
   }

   public static void glCopyTexSubImage2D(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
      GL11.glCopyTexSubImage2D(n, n2, n3, n4, n5, n6, n7, n8);
   }

   public static void glBegin(int n) {
      GL11.glBegin(n);
   }

   public static void glTexParameterf(int n, int n2, float f) {
      GL11.glTexParameterf(n, n2, f);
   }

   public static void enableTexture2D() {
      textureState[activeTextureUnit].texture2DState.setEnabled();
   }

   public static void translate(double d, double d2, double d3) {
      GL11.glTranslated(d, d2, d3);
   }

   public static class Color {
      public float red = 1.0F;
      public float green = 1.0F;
      public float blue = 1.0F;
      public float alpha = 1.0F;

      public Color(float f, float f2, float f3, float f4) {
         this.red = f;
         this.green = f2;
         this.blue = f3;
         this.alpha = f4;
      }

      public Color() {
      }
   }

   public enum TexGen {
      S,
      T,
      R,
      Q;

   }

   public static class FogState {
      public GlStateManager.BooleanState fog = new GlStateManager.BooleanState(2912);
      public int mode = 2048;
      public float density = 1.0F;
      public float start = 0.0F;
      public float end = 1.0F;


      private FogState() {
      }
   }

   public static class ColorMask {
      public boolean red = true;
      public boolean green = true;
      public boolean blue = true;
      public boolean alpha = true;


      private ColorMask() {
      }
   }

   public static class CullState {
      public GlStateManager.BooleanState cullFace = new GlStateManager.BooleanState(2884);
      public int mode = 1029;


      private CullState() {
      }
   }

   public static class AlphaState {
      public GlStateManager.BooleanState alphaTest = new GlStateManager.BooleanState(3008);
      public int func = 519;
      public float ref = -1.0F;

      private AlphaState() {
      }

   }

   public static class BlendState {
      public GlStateManager.BooleanState blend = new GlStateManager.BooleanState(3042);
      public int srcFactor = 1;
      public int dstFactor = 0;
      public int srcFactorAlpha = 1;
      public int dstFactorAlpha = 0;


      private BlendState() {
      }
   }

   public static class ClearState {
      public double depth = 1.0;
      public GlStateManager.Color color = new GlStateManager.Color(0.0F, 0.0F, 0.0F, 0.0F);
      public int clear = 0;


      private ClearState() {
      }
   }

   public static class DepthState {
      public GlStateManager.BooleanState depthTest = new GlStateManager.BooleanState(2929);
      public boolean maskEnabled = true;
      public int depthFunc = 513;

      private DepthState() {
      }

   }

   public static class StencilFunc {
      public int func = 519;
      public int ref = 0;
      public int mask = -1;

      private StencilFunc() {
      }

   }

   public static class TexGenCoord {
      public GlStateManager.BooleanState textureGen;
      public int coord;
      public int param = -1;

      public TexGenCoord(int n, int n2) {
         this.coord = n;
         this.textureGen = new GlStateManager.BooleanState(n2);
      }
   }

   public static class TexGenState {
      public GlStateManager.TexGenCoord s = new GlStateManager.TexGenCoord(8192, 3168);
      public GlStateManager.TexGenCoord t = new GlStateManager.TexGenCoord(8193, 3169);
      public GlStateManager.TexGenCoord r = new GlStateManager.TexGenCoord(8194, 3170);
      public GlStateManager.TexGenCoord q = new GlStateManager.TexGenCoord(8195, 3171);


      private TexGenState() {
      }
   }

   public static class BooleanState {
      private final int capability;
      private boolean currentState = false;

      public BooleanState(int n) {
         this.capability = n;
      }

      public void setDisabled() {
         this.setState(false);
      }

      public void setEnabled() {
         this.setState(true);
      }

      public static boolean getCurrentState(BooleanState booleanState) {
         return booleanState.currentState;
      }

      public void setState(boolean flag) {
         if (flag != this.currentState) {
            this.currentState = flag;
            if (flag) {
               GL11.glEnable(this.capability);
            } else {
               GL11.glDisable(this.capability);
            }
         }
      }
   }

   public static class StencilState {
      public GlStateManager.StencilFunc func = new GlStateManager.StencilFunc();
      public int mask = -1;
      public int fail = 7680;
      public int zfail = 7680;
      public int zpass = 7680;

      private StencilState() {
      }

   }

   public static class TextureState {
      public GlStateManager.BooleanState texture2DState = new GlStateManager.BooleanState(3553);
      public int textureName = 0;

      private TextureState() {
      }

   }

   public static class ColorLogicState {
      public GlStateManager.BooleanState colorLogicOp = new GlStateManager.BooleanState(3058);
      public int opcode = 5379;


      private ColorLogicState() {
      }
   }

   public static class ColorMaterialState {
      public GlStateManager.BooleanState colorMaterial = new GlStateManager.BooleanState(2903);
      public int face = 1032;
      public int mode = 5634;


      private ColorMaterialState() {
      }
   }

   public static class PolygonOffsetState {
      public GlStateManager.BooleanState polygonOffsetFill = new GlStateManager.BooleanState(32823);
      public GlStateManager.BooleanState polygonOffsetLine = new GlStateManager.BooleanState(10754);
      public float factor = 0.0F;
      public float units = 0.0F;

      private PolygonOffsetState() {
      }

   }
}
