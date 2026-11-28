package net.minecraft.client.util;

import com.google.gson.JsonObject;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.JsonUtils;
import org.lwjgl.opengl.GL14;

public class JsonBlendingMode {
   private static JsonBlendingMode lastApplied = null;
   private final int srcColorFactor;
   private final int srcAlphaFactor;
   private final int destColorFactor;
   private final int destAlphaFactor;
   private final int blendFunction;
   private final boolean separateBlend;
   private final boolean opaque;

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof JsonBlendingMode)) {
         return false;
      } else {
         JsonBlendingMode jsonBlendingMode = (JsonBlendingMode)obj;
         return this.blendFunction != jsonBlendingMode.blendFunction
            ? false
            : (
               this.destAlphaFactor != jsonBlendingMode.destAlphaFactor
                  ? false
                  : (
                     this.destColorFactor != jsonBlendingMode.destColorFactor
                        ? false
                        : (this.opaque != jsonBlendingMode.opaque ? false : (this.separateBlend != jsonBlendingMode.separateBlend ? false : (this.srcAlphaFactor != jsonBlendingMode.srcAlphaFactor ? false : this.srcColorFactor == jsonBlendingMode.srcColorFactor)))
                  )
            );
      }
   }

   private static int stringToBlendFunction(String str) {
      String str2 = str.trim().toLowerCase();
      return str2.equals("add")
         ? 32774
         : (
            str2.equals("subtract")
               ? 32778
               : (
                  str2.equals("reversesubtract")
                     ? 32779
                     : (str2.equals("reverse_subtract") ? 32779 : (str2.equals("min") ? 32775 : (str2.equals("max") ? 32776 : 32774)))
               )
         );
   }

   private static int stringToBlendFactor(String str) {
      String str2 = str.trim().toLowerCase();
      str2 = str2.replaceAll("_", "");
      str2 = str2.replaceAll("one", "1");
      str2 = str2.replaceAll("zero", "0");
      str2 = str2.replaceAll("minus", "-");
      return str2.equals("0")
         ? 0
         : (
            str2.equals("1")
               ? 1
               : (
                  str2.equals("srccolor")
                     ? 768
                     : (
                        str2.equals("1-srccolor")
                           ? 769
                           : (
                              str2.equals("dstcolor")
                                 ? 774
                                 : (
                                    str2.equals("1-dstcolor")
                                       ? 775
                                       : (
                                          str2.equals("srcalpha")
                                             ? 770
                                             : (str2.equals("1-srcalpha") ? 771 : (str2.equals("dstalpha") ? 772 : (str2.equals("1-dstalpha") ? 773 : -1)))
                                       )
                                 )
                           )
                     )
               )
         );
   }

   public JsonBlendingMode(int n, int n2, int n3) {
      this(false, false, n, n2, n, n2, n3);
   }

   public JsonBlendingMode() {
      this(false, true, 1, 0, 1, 0, 32774);
   }

   public boolean isOpaque() {
      return this.opaque;
   }

   public void apply() {
      if (!this.equals(lastApplied)) {
         if (lastApplied == null || this.opaque != lastApplied.isOpaque()) {
            lastApplied = this;
            if (this.opaque) {
               GlStateManager.disableBlend();
               return;
            }

            GlStateManager.enableBlend();
         }

         GL14.glBlendEquation(this.blendFunction);
         if (this.separateBlend) {
            GlStateManager.tryBlendFuncSeparate(this.srcColorFactor, this.destColorFactor, this.srcAlphaFactor, this.destAlphaFactor);
         } else {
            GlStateManager.blendFunc(this.srcColorFactor, this.destColorFactor);
         }
      }
   }

   public static JsonBlendingMode parseBlendNode(JsonObject json) {
      if (json == null) {
         return new JsonBlendingMode();
      } else {
         int n = 32774;
         int n2 = 1;
         int n3 = 0;
         int n4 = 1;
         int n5 = 0;
         boolean flag = true;
         boolean flag2 = false;
         if (JsonUtils.isString(json, "func")) {
            n = stringToBlendFunction(json.get("func").getAsString());
            if (n != 32774) {
               flag = false;
            }
         }

         if (JsonUtils.isString(json, "srcrgb")) {
            n2 = stringToBlendFactor(json.get("srcrgb").getAsString());
            if (n2 != 1) {
               flag = false;
            }
         }

         if (JsonUtils.isString(json, "dstrgb")) {
            n3 = stringToBlendFactor(json.get("dstrgb").getAsString());
            if (n3 != 0) {
               flag = false;
            }
         }

         if (JsonUtils.isString(json, "srcalpha")) {
            n4 = stringToBlendFactor(json.get("srcalpha").getAsString());
            if (n4 != 1) {
               flag = false;
            }

            flag2 = true;
         }

         if (JsonUtils.isString(json, "dstalpha")) {
            n5 = stringToBlendFactor(json.get("dstalpha").getAsString());
            if (n5 != 0) {
               flag = false;
            }

            flag2 = true;
         }

         return flag ? new JsonBlendingMode() : (flag2 ? new JsonBlendingMode(n2, n3, n4, n5, n) : new JsonBlendingMode(n2, n3, n));
      }
   }

   private JsonBlendingMode(boolean flag, boolean flag2, int n, int n2, int n3, int n4, int n5) {
      this.separateBlend = flag;
      this.srcColorFactor = n;
      this.destColorFactor = n2;
      this.srcAlphaFactor = n3;
      this.destAlphaFactor = n4;
      this.opaque = flag2;
      this.blendFunction = n5;
   }

   public JsonBlendingMode(int n, int n2, int n3, int n4, int n5) {
      this(true, false, n, n2, n3, n4, n5);
   }

   @Override
   public int hashCode() {
      int srcColorFactor2 = this.srcColorFactor;
      srcColorFactor2 = 31 * srcColorFactor2 + this.srcAlphaFactor;
      srcColorFactor2 = 31 * srcColorFactor2 + this.destColorFactor;
      srcColorFactor2 = 31 * srcColorFactor2 + this.destAlphaFactor;
      srcColorFactor2 = 31 * srcColorFactor2 + this.blendFunction;
      srcColorFactor2 = 31 * srcColorFactor2 + (this.separateBlend ? 1 : 0);
      return 31 * srcColorFactor2 + (this.opaque ? 1 : 0);
   }
}
