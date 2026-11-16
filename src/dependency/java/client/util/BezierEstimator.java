package client.util;

import client.setting.MovementIntervalProperty;

public class BezierEstimator {
   public static String labelText = "MC_";
   public static final String labelText2 = "MC_VERSION";
   public static final String labelText3 = "MC_GL_VERSION";
   public static final String labelText4 = "MC_GLSL_VERSION";
   public static final String labelText5 = "MC_OS_WINDOWS";
   public static final String labelText6 = "MC_OS_MAC";
   public static final String labelText7 = "MC_OS_LINUX";
   public static final String labelText8 = "MC_OS_OTHER";
   public static final String labelText9 = "MC_GL_VENDOR_ATI";
   public static final String labelText10 = "MC_GL_VENDOR_INTEL";
   public static final String labelText11 = "MC_GL_VENDOR_NVIDIA";
   public static final String labelText12 = "MC_GL_VENDOR_XORG";
   public static final String labelText13 = "MC_GL_VENDOR_OTHER";
   public static final String labelText14 = "MC_GL_RENDERER_RADEON";
   public static final String labelText15 = "MC_GL_RENDERER_GEFORCE";
   public static final String labelText16 = "MC_GL_RENDERER_QUADRO";
   public static final String labelText17 = "MC_GL_RENDERER_INTEL";
   public static final String labelText18 = "MC_GL_RENDERER_GALLIUM";
   public static final String labelText19 = "MC_GL_RENDERER_MESA";
   public static final String labelText20 = "MC_GL_RENDERER_OTHER";
   public static final String labelText21 = "MC_FXAA_LEVEL";
   public static final String labelText22 = "MC_NORMAL_MAP";
   public static final String labelText23 = "MC_SPECULAR_MAP";
   public static final String labelText24 = "MC_RENDER_QUALITY";
   public static final String labelText25 = "MC_SHADOW_QUALITY";
   public static final String labelText26 = "MC_HAND_DEPTH";
   public static final String labelText27 = "MC_OLD_HAND_LIGHT";
   public static final String labelText28 = "MC_OLD_LIGHTING";
   public static FovFactory[] fovFactoryArray;

   public static String getLabelText() {
      return labelText;
   }

   public static String getText() {
      StringBuilder builder = new StringBuilder();
      if (PreEndHandManager.count5 > 0) {
         applyStringBuilder(builder, "MC_FXAA_LEVEL", PreEndHandManager.count5);
      }

      if (PreEndHandManager.enabled15) {
         applyStringBuilder(builder, "MC_NORMAL_MAP");
      }

      if (PreEndHandManager.enabled37) {
         applyStringBuilder(builder, "MC_SPECULAR_MAP");
      }

      applyStringBuilder(builder, "MC_RENDER_QUALITY", PreEndHandManager.factor31);
      applyStringBuilder(builder, "MC_SHADOW_QUALITY", PreEndHandManager.factor5);
      applyStringBuilder(builder, "MC_HAND_DEPTH", PreEndHandManager.factor20);
      if (PreEndHandManager.isActive13()) {
         applyStringBuilder(builder, "MC_OLD_HAND_LIGHT");
      }

      if (PreEndHandManager.isActive12()) {
         applyStringBuilder(builder, "MC_OLD_LIGHTING");
      }

      return builder.toString();
   }

   public static String getText4() {
      StringBuilder builder = new StringBuilder();
      applyStringBuilder(builder, "MC_VERSION", BuildManager.getCountAsIndex());
      applyStringBuilder(builder, "MC_GL_VERSION " + BuildManager.getDampenerDispatcherAsDampenerDispatch().getCount());
      applyStringBuilder(builder, "MC_GLSL_VERSION " + BuildManager.getDampenerDispatcher().getCount());
      applyStringBuilder(builder, getText3());
      applyStringBuilder(builder, getTextAsText());
      applyStringBuilder(builder, getText2());
      return builder.toString();
   }

   public static FovFactory[] getFovFactoryArray() {
      if (fovFactoryArray == null) {
         String[] textArrayAsTextArray = BuildManager.getTextArrayAsTextArray();
         FovFactory[] fovFactoryArray2 = new FovFactory[textArrayAsTextArray.length];

         for (int index = 0; index < textArrayAsTextArray.length; index++) {
            fovFactoryArray2[index] = new FovFactory(labelText + textArrayAsTextArray[index], "");
         }

         fovFactoryArray = fovFactoryArray2;
      }

      return fovFactoryArray;
   }

   public static String getText2() {
      String labelText29 = BuildManager.labelText;
      if (labelText29 == null) {
         return "MC_GL_RENDERER_OTHER";
      } else {
         String text = labelText29.toLowerCase();
         return text.startsWith("amd")
            ? "MC_GL_RENDERER_RADEON"
            : (
               text.startsWith("ati")
                  ? "MC_GL_RENDERER_RADEON"
                  : (
                     text.startsWith("radeon")
                        ? "MC_GL_RENDERER_RADEON"
                        : (
                           text.startsWith("gallium")
                              ? "MC_GL_RENDERER_GALLIUM"
                              : (
                                 text.startsWith("intel")
                                    ? "MC_GL_RENDERER_INTEL"
                                    : (
                                       text.startsWith("geforce")
                                          ? "MC_GL_RENDERER_GEFORCE"
                                          : (
                                             text.startsWith("nvidia")
                                                ? "MC_GL_RENDERER_GEFORCE"
                                                : (
                                                   text.startsWith("quadro")
                                                      ? "MC_GL_RENDERER_QUADRO"
                                                      : (
                                                         text.startsWith("nvs")
                                                            ? "MC_GL_RENDERER_QUADRO"
                                                            : (text.startsWith("mesa") ? "MC_GL_RENDERER_MESA" : "MC_GL_RENDERER_OTHER")
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

   public static String getTextAsText() {
      String labelText29 = BuildManager.labelText6;
      if (labelText29 == null) {
         return "MC_GL_VENDOR_OTHER";
      } else {
         String text = labelText29.toLowerCase();
         return text.startsWith("ati")
            ? "MC_GL_VENDOR_ATI"
            : (
               text.startsWith("intel")
                  ? "MC_GL_VENDOR_INTEL"
                  : (text.startsWith("nvidia") ? "MC_GL_VENDOR_NVIDIA" : (text.startsWith("x.org") ? "MC_GL_VENDOR_XORG" : "MC_GL_VENDOR_OTHER"))
            );
      }
   }

   public static String getText3() {
      MovementIntervalProperty movementIntervalProperty = OsNameManager.getMovementIntervalProperty();
      switch (BezierEstimatorMovementIntervalPropSwitchMap.countArray[movementIntervalProperty.ordinal()]) {
         case 1:
            return "MC_OS_WINDOWS";
         case 2:
            return "MC_OS_MAC";
         case 3:
            return "MC_OS_LINUX";
         default:
            return "MC_OS_OTHER";
      }
   }

   private static void applyStringBuilder(StringBuilder builder, String text, float factor) {
      builder.append("#define ");
      builder.append(text);
      builder.append(" ");
      builder.append(factor);
      builder.append("\n");
   }

   private static void applyStringBuilder(StringBuilder builder, String text, int count) {
      builder.append("#define ");
      builder.append(text);
      builder.append(" ");
      builder.append(count);
      builder.append("\n");
   }

   private static void applyStringBuilder(StringBuilder builder, String text) {
      builder.append("#define ");
      builder.append(text);
      builder.append("\n");
   }
}
