package client.setting;

import com.google.common.collect.Maps;
import java.util.Map;

public enum ClientSettingsOpenUrlType {
    OPEN_URL("open_url", true),
    OPEN_FILE("open_file", false),
    RUN_COMMAND("run_command", true),
    TWITCH_USER_INFO("twitch_user_info", false),
    SUGGEST_COMMAND("suggest_command", true),
    CHANGE_PAGE("change_page", true);

   public static final Map map = Maps.newHashMap();
   public final boolean enabled;
   public final String labelText;
   public static final ClientSettingsOpenUrlType[] ENUM_VALUES = new ClientSettingsOpenUrlType[]{
      OPEN_URL, OPEN_FILE, RUN_COMMAND, TWITCH_USER_INFO, SUGGEST_COMMAND, CHANGE_PAGE
   };

   public static ClientSettingsOpenUrlType get(String text) {
      return (ClientSettingsOpenUrlType)map.get(text);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public String getLabelText() {
      return this.labelText;
   }

   private ClientSettingsOpenUrlType(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }



   static {
      for (ClientSettingsOpenUrlType entry : values()) {
               map.put(entry.getLabelText(), entry);
            }
   }
}
