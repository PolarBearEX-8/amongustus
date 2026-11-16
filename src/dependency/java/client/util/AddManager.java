package client.util;

import com.google.gson.JsonObject;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL14;

public class AddManager {
   public static AddManager addManager = null;
   public final int count3;
   public final int count;
   public final int count2;
   public final int count5;
   public final int count4;
   public final boolean enabled;
   public final boolean enabled2;

   private static int computeCountByText(String text) {
      String text2 = text.trim().toLowerCase();
      return text2.equals("add")
         ? 32774
         : (
            text2.equals("subtract")
               ? 32778
               : (
                  text2.equals("reversesubtract")
                     ? 32779
                     : (text2.equals("reverse_subtract") ? 32779 : (text2.equals("min") ? 32775 : (text2.equals("max") ? 32776 : 32774)))
               )
         );
   }

   public static AddManager resolveAddManager(JsonObject jsonObject) {
      if (jsonObject == null) {
         return new AddManager();
      } else {
         int count6 = 32774;
         int count7 = 1;
         int count8 = 0;
         int count9 = 1;
         int count10 = 0;
         boolean flag = true;
         boolean flag2 = false;
         if (MissingManager.matchesJsonObject4(jsonObject, "func")) {
            count6 = computeCountByText(jsonObject.get("func").getAsString());
            if (count6 != 32774) {
               flag = false;
            }
         }

         if (MissingManager.matchesJsonObject4(jsonObject, "srcrgb")) {
            count7 = computeCount(jsonObject.get("srcrgb").getAsString());
            if (count7 != 1) {
               flag = false;
            }
         }

         if (MissingManager.matchesJsonObject4(jsonObject, "dstrgb")) {
            count8 = computeCount(jsonObject.get("dstrgb").getAsString());
            if (count8 != 0) {
               flag = false;
            }
         }

         if (MissingManager.matchesJsonObject4(jsonObject, "srcalpha")) {
            count9 = computeCount(jsonObject.get("srcalpha").getAsString());
            if (count9 != 1) {
               flag = false;
            }

            flag2 = true;
         }

         if (MissingManager.matchesJsonObject4(jsonObject, "dstalpha")) {
            count10 = computeCount(jsonObject.get("dstalpha").getAsString());
            if (count10 != 0) {
               flag = false;
            }

            flag2 = true;
         }

         return flag ? new AddManager() : (flag2 ? new AddManager(count7, count8, count9, count10, count6) : new AddManager(count7, count8, count6));
      }
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   private static int computeCount(String text) {
      String text2 = text.trim().toLowerCase().replaceAll("_", "").replaceAll("one", "1").replaceAll("zero", "0").replaceAll("minus", "-");
      return text2.equals("0")
         ? 0
         : (
            text2.equals("1")
               ? 1
               : (
                  text2.equals("srccolor")
                     ? 768
                     : (
                        text2.equals("1-srccolor")
                           ? 769
                           : (
                              text2.equals("dstcolor")
                                 ? 774
                                 : (
                                    text2.equals("1-dstcolor")
                                       ? 775
                                       : (
                                          text2.equals("srcalpha")
                                             ? 770
                                             : (text2.equals("1-srcalpha") ? 771 : (text2.equals("dstalpha") ? 772 : (text2.equals("1-dstalpha") ? 773 : -1)))
                                       )
                                 )
                           )
                     )
               )
         );
   }

   public void updateState() {
      if (!this.equals(addManager)) {
         if (addManager == null || this.enabled2 != addManager.isEnabled2()) {
            addManager = this;
            if (this.enabled2) {
               GlStateManager.enableBlend();
               return;
            }

            GlStateManager.disableBlend();
         }

         GL14.glBlendEquation(this.count4);
         if (this.enabled) {
            GlStateManager.tryBlendFuncSeparate(this.count3, this.count2, this.count, this.count5);
         } else {
            GlStateManager.glStateAction3(this.count3, this.count2);
         }
      }
   }

   public AddManager() {
      this(false, true, 1, 0, 1, 0, 32774);
   }

   public AddManager(int count6, int count7, int count8, int count9, int count10) {
      this(true, false, count6, count7, count8, count9, count10);
   }

   private AddManager(boolean flag, boolean flag2, int count6, int count7, int count8, int count9, int count10) {
      this.enabled = flag;
      this.count3 = count6;
      this.count2 = count7;
      this.count = count8;
      this.count5 = count9;
      this.enabled2 = flag2;
      this.count4 = count10;
   }

   public AddManager(int count6, int count7, int count8) {
      this(false, false, count6, count7, count6, count7, count8);
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof AddManager)) {
         return false;
      } else {
         AddManager addManager2 = (AddManager)other;
         return this.count4 != addManager2.count4
            ? false
            : (
               this.count5 != addManager2.count5
                  ? false
                  : (
                     this.count2 != addManager2.count2
                        ? false
                        : (
                           this.enabled2 != addManager2.enabled2
                              ? false
                              : (this.enabled != addManager2.enabled ? false : (this.count != addManager2.count ? false : this.count3 == addManager2.count3))
                        )
                  )
            );
      }
   }

   @Override
   public int hashCode() {
      int count6 = this.count3;
      int count7 = 31 * count6 + this.count;
      int count8 = 31 * count7 + this.count2;
      int count9 = 31 * count8 + this.count5;
      int count10 = 31 * count9 + this.count4;
      int count11 = 31 * count10 + (this.enabled ? 1 : 0);
      return 31 * count11 + (this.enabled2 ? 1 : 0);
   }
}
