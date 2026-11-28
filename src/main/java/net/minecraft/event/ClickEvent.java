package net.minecraft.event;



import com.google.common.collect.Maps;
import java.util.Map;
public class ClickEvent {
   private final ClickEvent.Action action;
   private final String value;

   public ClickEvent.Action getAction() {
      return this.action;
   }

   public String getValue() {
      return this.value;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         ClickEvent clickEvent = (ClickEvent)obj;
         if (this.action != clickEvent.action) {
            return false;
         } else {
            if (this.value != null) {
               if (!this.value.equals(clickEvent.value)) {
                  return false;
               }
            } else if (clickEvent.value != null) {
               return false;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return 31 * this.action.hashCode() + (this.value != null ? this.value.hashCode() : 0);
   }

   public ClickEvent(ClickEvent.Action action2, String str) {
      this.action = action2;
      this.value = str;
   }

   @Override
   public String toString() {
      return "ClickEvent{action=" + this.action + ", value='" + this.value + '\'' + '}';
   }


   public enum Action {
      OPEN_URL("open_url", true),
      OPEN_FILE("open_file", false),
      RUN_COMMAND("run_command", true),
      TWITCH_USER_INFO("twitch_user_info", false),
      SUGGEST_COMMAND("suggest_command", true),
      CHANGE_PAGE("change_page", true);

   private static final Map nameMapping = Maps.newHashMap();
      private final boolean allowedInChat;
      private final String canonicalName;
   public String getCanonicalName() {
         return this.canonicalName;
      }

      public boolean shouldAllowInChat() {
         return this.allowedInChat;
      }

      private Action(String str, boolean flag) {
         this.canonicalName = str;
         this.allowedInChat = flag;
      }

      public static Action getValueByCanonicalName(String str) {
         return (Action)nameMapping.get(str);
      }

      static {
         for (Action action : values()) {
            nameMapping.put(action.getCanonicalName(), action);
         }
      }
   }
}
