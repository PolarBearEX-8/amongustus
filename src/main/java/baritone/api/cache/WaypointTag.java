package baritone.api.cache;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public enum WaypointTag {
   HOME(new String[]{"home", "base"}),
   DEATH(new String[]{"death"}),
   BED(new String[]{"bed", "spawn"}),
   USER(new String[]{"user"});

private static final List VALUES = Collections.unmodifiableList(Arrays.asList(values()));
   public final String[] names;
public static String[] getAllNames() {
      HashSet set = new HashSet();

      for (WaypointTag waypointTag : values()) {
         set.addAll(Arrays.asList(waypointTag.names));
      }

      return (String[])set.toArray(new String[0]);
   }

   public String getName() {
      return this.names[0];
   }

   public static WaypointTag getByName(String str) {
      for (WaypointTag waypointTag : values()) {
         for (String str2 : waypointTag.names) {
            if (str2.equalsIgnoreCase(str)) {
               return waypointTag;
            }
         }
      }

      return null;
   }

   private WaypointTag(String[] strings) {
      this.names = strings;
   }
}
