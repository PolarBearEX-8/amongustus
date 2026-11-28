package net.optifine.shaders.config;

import net.minecraft.util.Util;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;

public class ShaderMacros {
   private static String PREFIX_MACRO = "MC_";
   public static final String MC_VERSION = null;
   public static final String MC_GL_VERSION = null;
   public static final String MC_GLSL_VERSION = null;
   public static final String MC_OS_WINDOWS = null;
   public static final String MC_OS_MAC = null;
   public static final String MC_OS_LINUX = null;
   public static final String MC_OS_OTHER = null;
   public static final String MC_GL_VENDOR_ATI = null;
   public static final String MC_GL_VENDOR_INTEL = null;
   public static final String MC_GL_VENDOR_NVIDIA = null;
   public static final String MC_GL_VENDOR_XORG = null;
   public static final String MC_GL_VENDOR_OTHER = null;
   public static final String MC_GL_RENDERER_RADEON = null;
   public static final String MC_GL_RENDERER_GEFORCE = null;
   public static final String MC_GL_RENDERER_QUADRO = null;
   public static final String MC_GL_RENDERER_INTEL = null;
   public static final String MC_GL_RENDERER_GALLIUM = null;
   public static final String MC_GL_RENDERER_MESA = null;
   public static final String MC_GL_RENDERER_OTHER = null;
   public static final String MC_FXAA_LEVEL = null;
   public static final String MC_NORMAL_MAP = null;
   public static final String MC_SPECULAR_MAP = null;
   public static final String MC_RENDER_QUALITY = null;
   public static final String MC_SHADOW_QUALITY = null;
   public static final String MC_HAND_DEPTH = null;
   public static final String MC_OLD_HAND_LIGHT = null;
   public static final String MC_OLD_LIGHTING = null;
   private static ShaderMacro[] extensionMacros;

   public static ShaderMacro[] getExtensions() {
      if (extensionMacros == null) {
         String[] openGlExtensions = Config.getOpenGlExtensions();
         ShaderMacro[] shaderMacros = new ShaderMacro[openGlExtensions.length];

         for (int i = 0; i < openGlExtensions.length; i++) {
            shaderMacros[i] = new ShaderMacro(PREFIX_MACRO + openGlExtensions[i], "");
         }

         extensionMacros = shaderMacros;
      }

      return extensionMacros;
   }

   public static String getFixedMacroLines() {
      StringBuilder sb = new StringBuilder();
      addMacroLine2(sb, "MC_VERSION", Config.getMinecraftVersionInt());
      addMacroLine(sb, "MC_GL_VERSION " + Config.getGlVersion().toInt());
      addMacroLine(sb, "MC_GLSL_VERSION " + Config.getGlslVersion().toInt());
      addMacroLine(sb, getOs());
      addMacroLine(sb, getVendor());
      addMacroLine(sb, getRenderer());
      return sb.toString();
   }

   private static void addMacroLine(StringBuilder sb, String str, float f) {
      sb.append("#define ");
      sb.append(str);
      sb.append(" ");
      sb.append(f);
      sb.append("\n");
   }

   public static String getVendor() {
      String openGlVendor = Config.openGlVendor;
      if (openGlVendor == null) {
         return "MC_GL_VENDOR_OTHER";
      } else {
         openGlVendor = openGlVendor.toLowerCase();
         return openGlVendor.startsWith("ati")
            ? "MC_GL_VENDOR_ATI"
            : (
               openGlVendor.startsWith("intel")
                  ? "MC_GL_VENDOR_INTEL"
                  : (openGlVendor.startsWith("nvidia") ? "MC_GL_VENDOR_NVIDIA" : (openGlVendor.startsWith("x.org") ? "MC_GL_VENDOR_XORG" : "MC_GL_VENDOR_OTHER"))
            );
      }
   }

   private static void addMacroLine(StringBuilder sb, String str) {
      sb.append("#define ");
      sb.append(str);
      sb.append("\n");
   }

   public static String getPrefixMacro() {
      return PREFIX_MACRO;
   }

   private static void addMacroLine2(StringBuilder sb, String str, int n) {
      sb.append("#define ");
      sb.append(str);
      sb.append(" ");
      sb.append(n);
      sb.append("\n");
   }

   public static String getRenderer() {
      String openGlRenderer = Config.openGlRenderer;
      if (openGlRenderer == null) {
         return "MC_GL_RENDERER_OTHER";
      } else {
         openGlRenderer = openGlRenderer.toLowerCase();
         return openGlRenderer.startsWith("amd")
            ? "MC_GL_RENDERER_RADEON"
            : (
               openGlRenderer.startsWith("ati")
                  ? "MC_GL_RENDERER_RADEON"
                  : (
                     openGlRenderer.startsWith("radeon")
                        ? "MC_GL_RENDERER_RADEON"
                        : (
                           openGlRenderer.startsWith("gallium")
                              ? "MC_GL_RENDERER_GALLIUM"
                              : (
                                 openGlRenderer.startsWith("intel")
                                    ? "MC_GL_RENDERER_INTEL"
                                    : (
                                       openGlRenderer.startsWith("geforce")
                                          ? "MC_GL_RENDERER_GEFORCE"
                                          : (
                                             openGlRenderer.startsWith("nvidia")
                                                ? "MC_GL_RENDERER_GEFORCE"
                                                : (
                                                   openGlRenderer.startsWith("quadro")
                                                      ? "MC_GL_RENDERER_QUADRO"
                                                      : (
                                                         openGlRenderer.startsWith("nvs")
                                                            ? "MC_GL_RENDERER_QUADRO"
                                                            : (openGlRenderer.startsWith("mesa") ? "MC_GL_RENDERER_MESA" : "MC_GL_RENDERER_OTHER")
                                                      )
                                                )
                                          )
                                    )
                              )
                        )
                  )
            );
      }
   }

   public static String getOptionMacroLines() {
      StringBuilder sb = new StringBuilder();
      if (Shaders.configAntialiasingLevel > 0) {
         addMacroLine2(sb, "MC_FXAA_LEVEL", Shaders.configAntialiasingLevel);
      }

      if (Shaders.configNormalMap) {
         addMacroLine(sb, "MC_NORMAL_MAP");
      }

      if (Shaders.configSpecularMap) {
         addMacroLine(sb, "MC_SPECULAR_MAP");
      }

      addMacroLine(sb, "MC_RENDER_QUALITY", Shaders.configRenderResMul);
      addMacroLine(sb, "MC_SHADOW_QUALITY", Shaders.configShadowResMul);
      addMacroLine(sb, "MC_HAND_DEPTH", Shaders.configHandDepthMul);
      if (Shaders.isOldHandLight()) {
         addMacroLine(sb, "MC_OLD_HAND_LIGHT");
      }

      if (Shaders.isOldLighting()) {
         addMacroLine(sb, "MC_OLD_LIGHTING");
      }

      return sb.toString();
   }

   public static String getOs() {
      Util.EnumOS osType = Util.getOSType();
      switch (osType) {
         case WINDOWS:
            return "MC_OS_WINDOWS";
         case OSX:
            return "MC_OS_MAC";
         case LINUX:
            return "MC_OS_LINUX";
         default:
            return "MC_OS_OTHER";
      }
   }

}
