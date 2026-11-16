package client.util;

public class ManagerAdapter extends RootManager {
   public static final String[] stringArray3;
   public static final String[] stringArray4;

   public boolean isActive2() {
      return this.getCount() == 2;
   }

   @Override
   public String getTextAsText() {
      return this.isActive()
         ? GeneratorDefaultManager.getText3()
         : (this.isActiveAsFlag() ? GeneratorDefaultManager.getTextAsText() : (this.isActive2() ? GeneratorDefaultManager.getText4() : super.getTextAsText()));
   }

   public boolean isActiveAsFlag() {
      return this.getCount() == 1;
   }

   public boolean isActive() {
      return this.getCount() == 0;
   }

   public ManagerAdapter(String text, String text2, int count) {
      super(text, stringArray3, text2, stringArray4, count);
   }

   static {
      String[] textArray = new String[]{"OFF", "ON", "default", "false", "true", "Default"};
      stringArray3 = new String[]{textArray[2], textArray[4], textArray[3]};
      stringArray4 = new String[]{textArray[5], textArray[1], textArray[0]};
   }
}
