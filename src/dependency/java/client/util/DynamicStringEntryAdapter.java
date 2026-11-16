package client.util;

public class DynamicStringEntryAdapter extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (clears your memory)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      System.gc();
      RayTraceUtil.applyString("Memory cleared.");
   }

   public DynamicStringEntryAdapter() {
      super("gc");
   }
}
