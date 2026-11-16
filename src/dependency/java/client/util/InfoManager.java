package client.util;

public class InfoManager {
   public static final InfoManager[] infoManagerArray = new InfoManager[16];
   public static final InfoManager infoManager7 = new InfoManager(0, "default", 1).getInfoManager();
   public static final InfoManager infoManager2 = new InfoManager(1, "flat");
   public static final InfoManager infoManager5 = new InfoManager(2, "largeBiomes");
   public static final InfoManager infoManager = new InfoManager(3, "amplified").getInfoManagerAsInfoManager();
   public static final InfoManager infoManager3 = new InfoManager(4, "customized");
   public static final InfoManager infoManager4 = new InfoManager(5, "debug_all_block_states");
   public static final InfoManager infoManager6 = new InfoManager(8, "default_1_1", 0).withEnabled(false);
   public final int count2;
   public final String labelText;
   public final int count;
   public boolean enabled;
   public boolean enabled2;
   public boolean enabled3;

   public String getTextAsText() {
      return this.getText2() + ".info";
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public static InfoManager resolveInfoManager(String text) {
      for (int index = 0; index < infoManagerArray.length; index++) {
         if (infoManagerArray[index] != null && infoManagerArray[index].labelText.equalsIgnoreCase(text)) {
            return infoManagerArray[index];
         }
      }

      return null;
   }

   public String getText() {
      return this.labelText;
   }

   private InfoManager getInfoManager() {
      this.enabled2 = true;
      return this;
   }

   public int getCount() {
      return this.count;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public int getCount2() {
      return this.count2;
   }

   private InfoManager getInfoManagerAsInfoManager() {
      this.enabled3 = true;
      return this;
   }

   private InfoManager withEnabled(boolean flag) {
      this.enabled = flag;
      return this;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public String getText2() {
      return "generator." + this.labelText;
   }

   public InfoManager resolveInfoManager(int count3) {
      return this == infoManager7 && count3 == 0 ? infoManager6 : this;
   }

   private InfoManager(int count3, String text) {
      this(count3, text, 0);
   }

   private InfoManager(int count3, String text, int count4) {
      this.labelText = text;
      this.count = count4;
      this.enabled = true;
      this.count2 = count3;
      infoManagerArray[count3] = this;
   }
}
