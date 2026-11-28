package net.augustus.utils.render;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.render.GlStateSnapshot;
import net.augustus.render.RenderPassTask;
import net.augustus.utils.ChatUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;

public final class BlurShadowManager {
   public static byte shadowState = 0;
   public static byte blurState = 0;
   public static byte guiLayerState = 0;
   public static byte normalPassState = 0;
   private static final BlurShadowRenderQueue guiBlurQueue = new BlurShadowRenderQueue();
   private static final GlStateSnapshot savedGlState = new GlStateSnapshot();
   public static boolean shouldWarnIncompatibleSettings = false;
   private static final BlurShadowRenderQueue hudBlurQueue = new BlurShadowRenderQueue();
   public static final byte STATE_ONE_SHOT = 2;
   public static final byte STATE_DISABLED = 0;
   private static final BlurShadowRenderQueue shadowQueue = new BlurShadowRenderQueue();
   public static boolean hudCompositionActive = false;
   private static final BlurShaderRenderer blurShaderRenderer;
   public static byte renderingPassFlag = 0;
   public static final byte STATE_ENABLED = 1;
   private static final BlurShadowRenderQueue hudPostBlurQueue = new BlurShadowRenderQueue();
   private static final BlurShadowRenderQueue guiPostBlurQueue = new BlurShadowRenderQueue();

   public static void endHudComposition() {
      hudCompositionActive = false;
   }

   public static void enableBlur() {
      if (!isInRenderPass()) {
         blurState = 1;
      }
   }

   public static boolean isGuiLayerEnabled() {
      return guiLayerState >= 1;
   }

   public static void enableShadowOnce() {
      if (!isInRenderPass()) {
         shadowState = 2;
      }
   }

   public static void enableNormalPassOnce() {
      if (!isInRenderPass()) {
         normalPassState = 2;
      }
   }

   public static boolean isHudCompositionActive() {
      return hudCompositionActive;
   }

   public static boolean isShadersCompatible() {
      return !Config.isFastRender() && !Config.isAntialiasing() && !Config.isAnisotropicFiltering();
   }

   public static void submitRenderTask(boolean flag, RenderPassTask renderPassTask) {
      if (!isInRenderPass()) {
         if (isShadersCompatible() && !Shaders.isShadowPass) {
            if (shadowState >= 1 && guiLayerState == 0) {
               if (flag) {
                  ClientManagers.moduleManager.shadow.bindShadowMaskFramebuffer();
                  renderPassTask.execute(ShaderRenderPass.SHADOW);
                  ClientManagers.moduleManager.shadow.unbindShadowMaskFramebuffer();
               } else {
                  shadowQueue.enqueue(renderPassTask);
               }
            }

            if (blurState >= 1) {
               if (flag) {
                  boolean flag2 = guiLayerState >= 1;
                  blurShaderRenderer.beginMaskCapture(true, flag2);
                  renderPassTask.execute(ShaderRenderPass.BLUR);
                  blurShaderRenderer.endMaskCapture();
               } else if (guiLayerState >= 1) {
                  guiBlurQueue.enqueue(renderPassTask);
               } else {
                  hudBlurQueue.enqueue(renderPassTask);
               }
            }
         }

         if (normalPassState >= 1) {
            renderPassTask.execute(ShaderRenderPass.NONE);
         }

         if (blurState == 2) {
            blurState = 0;
         }

         if (shadowState == 2) {
            shadowState = 0;
         }

         if (normalPassState == 2) {
            normalPassState = 0;
         }

         if (guiLayerState == 2) {
            guiLayerState = 0;
         }
      }
   }

   static {
      blurShaderRenderer = new BlurShaderRenderer();
   }

   public static boolean isBlurEnabled() {
      return blurState >= 1;
   }

   public static boolean isShadowEnabled() {
      return shadowState >= 1;
   }

   public static void disableShadow() {
      if (!isInRenderPass()) {
         shadowState = 0;
      }
   }

   private static void warnIncompatibleSettings() {
      if (shouldWarnIncompatibleSettings && Minecraft.getMinecraft().thePlayer != null) {
         ChatUtil.sendChat(
            "Shadow/Blur error: Please make sure you have the minecraft options Fastrender, Antialiasing and Anisotropicfiltering disabled."
         );
         shouldWarnIncompatibleSettings = false;
      }
   }

   public static void enableBlurOnce() {
      if (!isInRenderPass()) {
         blurState = 2;
      }
   }

   public static void enableOneShotPass(boolean flag, boolean flag2) {
      if (!isInRenderPass()) {
         enableNormalPassOnce();
         if (flag) {
            enableBlurOnce();
         }

         if (flag2) {
            enableShadowOnce();
         }
      }
   }

   public static void enqueuePostBlurTask(RenderPassTask renderPassTask) {
      if (!isInRenderPass()) {
         if (guiLayerState >= 1) {
            guiPostBlurQueue.enqueue(renderPassTask);
         } else {
            hudPostBlurQueue.enqueue(renderPassTask);
         }
      }
   }

   public static void enableGuiLayer() {
      if (!isInRenderPass()) {
         guiLayerState = 1;
      }
   }

   private static void exitRenderPass() {
      renderingPassFlag = 0;
   }

   public static void disableBlur() {
      if (!isInRenderPass()) {
         blurState = 0;
      }
   }

   public static void disableNormalPass() {
      if (!isInRenderPass()) {
         normalPassState = 0;
      }
   }

   public static void enableNormalPass() {
      if (!isInRenderPass()) {
         normalPassState = 1;
      }
   }

   public static boolean isInRenderPass() {
      return renderingPassFlag >= 1;
   }

   public static void disableGuiLayer() {
      if (!isInRenderPass()) {
         guiLayerState = 0;
      }
   }

   public static void beginHudComposition() {
      hudCompositionActive = true;
   }

   public static boolean isNormalPassEnabled() {
      return normalPassState >= 1;
   }

   public static void enableGuiLayerOnce() {
      if (!isInRenderPass()) {
         guiLayerState = 2;
      }
   }

   public static void flushRenderQueues(ScaledResolution sr, boolean flag) {
      if (!isShadersCompatible()) {
         warnIncompatibleSettings();
      } else {
         savedGlState.capture((RenderPassTask)null);
         if (ClientManagers.moduleManager.blur.isToggled() && (ClientManagers.moduleManager.blur.inScreens.getValue() || !flag)
            || ClientManagers.moduleManager.blur.inScreens.getValue() && flag) {
            enterRenderPass();
            if (!hudBlurQueue.isEmpty()) {
               blurShaderRenderer.beginMaskCapture(true, false);
               ShaderRenderPass blur = ShaderRenderPass.BLUR;
               hudBlurQueue.flush(blur);
               blurShaderRenderer.endMaskCapture();
               savedGlState.restore((ShaderRenderPass)null);
            }

            if (!guiBlurQueue.isEmpty()) {
               blurShaderRenderer.beginMaskCapture(true, true);
               ShaderRenderPass blur2 = ShaderRenderPass.BLUR;
               guiBlurQueue.flush(blur2);
               blurShaderRenderer.endMaskCapture();
               savedGlState.restore((ShaderRenderPass)null);
            }

            exitRenderPass();
            blurShaderRenderer.renderBlurPass(flag, (int)Augustus.getInstance().getModuleManager().blur.sigma.getValue());
         }

         BlurShadowRenderQueue blurShadowRenderQueue = flag ? guiPostBlurQueue : hudPostBlurQueue;
         if (!blurShadowRenderQueue.isEmpty()) {
            enterRenderPass();
            blurShadowRenderQueue.flush(ShaderRenderPass.POST_BLUR);
            exitRenderPass();
            savedGlState.restore((ShaderRenderPass)null);
         }

         if (!flag) {
            if (!shadowQueue.isEmpty()) {
               ClientManagers.moduleManager.shadow.bindShadowMaskFramebuffer();
               enterRenderPass();
               ShaderRenderPass shadow = ShaderRenderPass.SHADOW;
               shadowQueue.flush(shadow);
               exitRenderPass();
               ClientManagers.moduleManager.shadow.unbindShadowMaskFramebuffer();
               savedGlState.restore((ShaderRenderPass)null);
            }

            if (ClientManagers.moduleManager.shadow.isToggled()) {
               ClientManagers.moduleManager.shadow.renderShadowOverlay(sr);
            }
         }
      }
   }

   private static void enterRenderPass() {
      renderingPassFlag = 1;
   }

   public static void enableShadow() {
      if (!isInRenderPass()) {
         shadowState = 1;
      }
   }

}
