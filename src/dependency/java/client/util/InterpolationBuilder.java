package client.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;

public final class InterpolationBuilder implements ProfileLookupCallback {
   public final GameProfile[] gameProfileArray;

   public InterpolationBuilder(GameProfile[] gameProfileArray2) {
      this.gameProfileArray = gameProfileArray2;
   }

   public void onProfileLookupFailed(GameProfile gameProfile, Exception exception) {
      this.gameProfileArray[0] = null;
   }

   public void onProfileLookupSucceeded(GameProfile gameProfile) {
      this.gameProfileArray[0] = gameProfile;
   }
}
