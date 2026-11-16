package client.setting;

import java.text.SimpleDateFormat;
import java.util.Date;

public enum ClientSettingsChristmasType {
   HALLOWEEN("31.10"),
   FIRST_APRIL("01.04"),
   CHRISTMAS_24("24.12"),
   CHRISTMAS_25("25.12"),
   CHRISTMAS_26("26.12"),
   MINIMI("09.04");

   public boolean enabled;
   public static final ClientSettingsChristmasType[] ENUM_VALUES = values();

   private ClientSettingsChristmasType(String text) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy");
      this.enabled = simpleDateFormat.format(new Date()).startsWith(text);
   }
}
