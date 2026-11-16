package client.util;

public class AuxiliaryManagerAdapter extends RootManager {
   public static final String[] stringArray4 = new String[]{"default", "fast", "fancy", "off"};
   public static final String[] stringArray3 = new String[]{"Default", "Fast", "Fancy", "OFF"};

   public boolean isActive() {
      return this.getCount() == 0;
   }

   public boolean isActive3() {
      return this.getCount() == 2;
   }

   @Override
   public boolean matchesText(String text) {
      String text2 = text;
      if (BuildManager.matchesTarget(text, "none")) {
         text2 = "off";
      }

      return super.matchesText(text2);
   }

   public boolean isActiveAsFlag() {
      return this.getCount() == 3;
   }

   public boolean isActive2() {
      return this.getCount() == 1;
   }

   public AuxiliaryManagerAdapter(String text, String text2, int count) {
      super(text, stringArray4, text2, stringArray3, count);
   }
}
