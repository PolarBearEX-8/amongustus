package com.mojang.authlib;

import client.util.AdvancedCustom;
import client.util.VanillaRunnable;
import java.util.List;

public final class LookupUserProfileLookupCallback implements ProfileLookupCallback {
   public final VanillaRunnable vanillaRunnable;
   public final List list;

   public void onProfileLookupSucceeded(GameProfile gameProfile) {
      this.vanillaRunnable.getYyyyMMDdHHMmSsZManager().applyGameProfile(gameProfile);
      this.list.add(gameProfile);
   }

   public void onProfileLookupFailed(GameProfile gameProfile, Exception exception) {
      AdvancedCustom.getLogger().warn("Could not lookup user whitelist entry for " + gameProfile.getName(), exception);
   }

   public LookupUserProfileLookupCallback(VanillaRunnable vanillaRunnable2, List list2) {
      this.vanillaRunnable = vanillaRunnable2;
      this.list = list2;
   }
}
