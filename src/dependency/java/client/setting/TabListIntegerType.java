package client.setting;

import com.google.common.collect.Maps;
import java.util.Map;

public enum TabListIntegerType {
    INTEGER("integer"),
    HEARTS("hearts");

   public static final Map map = Maps.newHashMap();
   public final String labelText;
   public static final TabListIntegerType[] ENUM_VALUES = new TabListIntegerType[]{INTEGER, HEARTS};

   public String getLabelText() {
      return this.labelText;
   }

   public static TabListIntegerType get(String text) {
      TabListIntegerType tabListIntegerType = (TabListIntegerType)map.get(text);
      return tabListIntegerType == null ? INTEGER : tabListIntegerType;
   }

   private TabListIntegerType(String text) {
      this.labelText = text;
   }



   static {
      for (TabListIntegerType entry : values()) {
               map.put(entry.getLabelText(), entry);
            }
   }
}
