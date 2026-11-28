package net.minecraft.event;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.IChatComponent;

public class HoverEvent {
   private final HoverEvent.Action action;
   private final IChatComponent value;

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         HoverEvent hoverEvent = (HoverEvent)obj;
         if (this.action != hoverEvent.action) {
            return false;
         } else {
            if (this.value != null) {
               if (!this.value.equals(hoverEvent.value)) {
                  return false;
               }
            } else if (hoverEvent.value != null) {
               return false;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public HoverEvent.Action getAction() {
      return this.action;
   }

   @Override
   public String toString() {
      return "HoverEvent{action=" + this.action + ", value='" + this.value + '\'' + '}';
   }

   public IChatComponent getValue() {
      return this.value;
   }

   @Override
   public int hashCode() {
      return 31 * this.action.hashCode() + (this.value != null ? this.value.hashCode() : 0);
   }

   public HoverEvent(HoverEvent.Action action2, IChatComponent component) {
      this.action = action2;
      this.value = component;
   }


   public enum Action {
      SHOW_TEXT("show_text", true),
      SHOW_ACHIEVEMENT("show_achievement", true),
      SHOW_ITEM("show_item", true),
      SHOW_ENTITY("show_entity", true);

   private static final Map nameMapping = Maps.newHashMap();
      private final boolean allowedInChat;
      private final String canonicalName;
   private Action(String str, boolean flag) {
         this.canonicalName = str;
         this.allowedInChat = flag;
      }

      public static Action getValueByCanonicalName(String str) {
         return (Action)nameMapping.get(str);
      }

      public String getCanonicalName() {
         return this.canonicalName;
      }

      static {
         for (Action action : values()) {
            nameMapping.put(action.getCanonicalName(), action);
         }
      }

      public boolean shouldAllowInChat() {
         return this.allowedInChat;
      }
   }
}
