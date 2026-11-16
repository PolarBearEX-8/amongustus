package client.setting;

import com.google.common.collect.Maps;
import java.util.Map;

public enum ClientSettingsShowTextType {
    SHOW_TEXT("show_text", true),
    SHOW_ACHIEVEMENT("show_achievement", true),
    SHOW_ITEM("show_item", true),
    SHOW_ENTITY("show_entity", true);

   public static final Map map = Maps.newHashMap();
   public final boolean enabled;
   public final String labelText;
   public static final ClientSettingsShowTextType[] ENUM_VALUES = new ClientSettingsShowTextType[]{SHOW_TEXT, SHOW_ACHIEVEMENT, SHOW_ITEM, SHOW_ENTITY};

   public String getLabelText() {
      return this.labelText;
   }

   public static ClientSettingsShowTextType get(String text) {
      return (ClientSettingsShowTextType)map.get(text);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   private ClientSettingsShowTextType(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }



   static {
      for (ClientSettingsShowTextType entry : values()) {
               map.put(entry.getLabelText(), entry);
            }
   }
}
