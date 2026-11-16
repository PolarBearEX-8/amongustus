package client.setting;

import com.google.common.collect.Maps;
import java.util.Map;

public enum MovementBooleanProperty {
   ALWAYS("always", 0),
   NEVER("never", 1),
   HIDE_FOR_OTHER_TEAMS("hideForOtherTeams", 2),
   HIDE_FOR_OWN_TEAM("hideForOwnTeam", 3);

   public static Map map = Maps.newHashMap();
   public final String labelText;
   public final int count;
   public static final MovementBooleanProperty[] ENUM_VALUES = new MovementBooleanProperty[]{ALWAYS, NEVER, HIDE_FOR_OTHER_TEAMS, HIDE_FOR_OWN_TEAM};

   public static MovementBooleanProperty get(String text) {
      return (MovementBooleanProperty)map.get(text);
   }

   public static String[] getTextArray() {
      return (String[])map.keySet().toArray(new String[map.size()]);
   }

   private MovementBooleanProperty(String text, int count2) {
      this.labelText = text;
      this.count = count2;
   }

   static {
      for (MovementBooleanProperty property : values()) {
         map.put(property.labelText, property);
      }
   }
}
