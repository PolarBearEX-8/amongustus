package net.minecraft.scoreboard;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Collection;

public abstract class Team {
   public abstract boolean getAllowFriendlyFire();

   public abstract String formatString(String str);

   public abstract Collection getMembershipCollection();

   public abstract Team.EnumVisible getNameTagVisibility();

   public abstract String getRegisteredName();

   public boolean isSameTeam(Team team) {
      return team == null ? false : this == team;
   }

   public abstract boolean getSeeFriendlyInvisiblesEnabled();

   public abstract Team.EnumVisible getDeathMessageVisibility();


   public enum EnumVisible {
      ALWAYS("always", 0),
      NEVER("never", 1),
      HIDE_FOR_OTHER_TEAMS("hideForOtherTeams", 2),
      HIDE_FOR_OWN_TEAM("hideForOwnTeam", 3);

   private static Map NAME_MAP = Maps.newHashMap();
      public final String internalName;
      public final int id;
   public static EnumVisible getByName(String str) {
         return (EnumVisible)NAME_MAP.get(str);
      }

      private EnumVisible(String str, int n) {
         this.internalName = str;
         this.id = n;
      }

      static {
         for (EnumVisible enumVisible : values()) {
            NAME_MAP.put(enumVisible.internalName, enumVisible);
         }
      }

      public static String[] getAllNames() {
         return (String[])NAME_MAP.keySet().toArray(new String[NAME_MAP.size()]);
      }
   }
}
