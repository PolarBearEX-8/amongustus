package client.setting;

import com.google.common.collect.Maps;
import java.util.Map;

public enum SessionLegacyType {
    LEGACY("legacy"),
    MOJANG("mojang");

   public static final Map map = Maps.newHashMap();
   public final String labelText;
   public static final SessionLegacyType[] ENUM_VALUES = new SessionLegacyType[]{LEGACY, MOJANG};

   public static SessionLegacyType resolveSessionLegacyType(String text) {
      return (SessionLegacyType)map.get(text.toLowerCase());
   }

   private SessionLegacyType(String text) {
      this.labelText = text;
   }



   static {
      for (SessionLegacyType entry : values()) {
               map.put(entry.labelText, entry);
            }
   }
}
