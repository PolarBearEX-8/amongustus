package client.util;

import com.mojang.authlib.GameProfile;
import java.util.Date;

public class GameProfileEntry {
   public final GameProfile gameProfile;
   public final Date date;
   public final YyyyMMDdHHMmSsZManager yyyyMMDdHHMmSsZManager;

   public static Date resolveDate(GameProfileEntry gameProfileEntry) {
      return gameProfileEntry.date;
   }

   public Date getDate() {
      return this.date;
   }

   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   private GameProfileEntry(YyyyMMDdHHMmSsZManager yyyyMMDdHHMmSsZManager2, GameProfile gameProfile2, Date date2) {
      this.yyyyMMDdHHMmSsZManager = yyyyMMDdHHMmSsZManager2;
      this.gameProfile = gameProfile2;
      this.date = date2;
   }

   public GameProfileEntry(YyyyMMDdHHMmSsZManager yyyyMMDdHHMmSsZManager2, GameProfile gameProfile2, Date date2, AuxiliaryParameterizedTypeAdapter auxiliaryParameterizedTypeAdapter) {
      this(yyyyMMDdHHMmSsZManager2, gameProfile2, date2);
   }
}
