package client.setting;

import java.io.File;

public enum RenderProperty {
   WINDOWS("AppData" + File.separator + "Roaming" + File.separator + ".minecraft"),
   MAC("Library" + File.separator + "Application Support" + File.separator + "minecraft"),
   LINUX(".minecraft");

   public final String labelText;

   public static final RenderProperty getRenderProperty() {
      String text = System.getProperty("os.name").toLowerCase();
      if (text.startsWith("windows")) {
         return WINDOWS;
      } else {
         return text.startsWith("mac") ? MAC : LINUX;
      }
   }

   public String getText() {
      return System.getProperty("user.home") + this.labelText;
   }

   private RenderProperty(String text) {
      this.labelText = File.separator + text + File.separator;
   }
}
