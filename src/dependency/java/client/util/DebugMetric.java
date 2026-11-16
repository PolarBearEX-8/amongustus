package client.util;

import client.setting.NamedOption;

public enum DebugMetric implements NamedOption {
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

   public boolean enabled = true;
   public final String labelText;
   public static final DebugMetric[] ENUM_VALUES = new DebugMetric[]{FPS, PING, CPS, POS_X, POS_Y, POS_Z, BPS, RX, TX, DOWNLOAD, UPLOAD, DATE, TIME};

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static boolean checkDebugMetric(DebugMetric debugMetric) {
      return debugMetric.enabled;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private DebugMetric(String text) {
      this.labelText = text;
   }


}
