package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum DebugInfoElementOption implements SelectableOption {
   FPS("FPS"),
   PING("Ping"),
   CPS("Cps"),
   POS_X("PosX"),
   POS_Y("PosY"),
   POS_Z("PosZ"),
   BPS("BPS"),
   RX("RX"),
   TX("TX"),
   DOWNLOAD("Download"),
   UPLOAD("Upload"),
   DATE("Date"),
   TIME("Time");

private boolean selected = true;
   private final String name;
private DebugInfoElementOption(String str) {
      this.name = str;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   @Override
   public void setSelected(boolean flag) {
      this.selected = flag;
   }

   public static boolean isOptionSelected(DebugInfoElementOption debugInfoElementOption) {
      return debugInfoElementOption.selected;
   }
}
