package net.augustus.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public enum ClientHoliday {
   HALLOWEEN(0, "31.10"),
   MINIMI(5, "09.04"),

   FIRST_APRIL(1, "01.04"),
   CHRISTMAS_24(2, "24.12"),
   CHRISTMAS_25(3, "25.12"),
   CHRISTMAS_26(4, "26.12");

public boolean active;




   private ClientHoliday(int param3, String str) {
      this.active = new SimpleDateFormat("dd.MM.yy").format(new Date()).startsWith(str);
   }
}
