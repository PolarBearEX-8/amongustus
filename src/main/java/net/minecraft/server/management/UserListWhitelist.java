package net.minecraft.server.management;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;

public class UserListWhitelist extends UserList {
   public String getObjectKey(GameProfile profile) {
      return profile.getId().toString();
   }

   @Override
   public String getObjectKey(Object obj) {
      return this.getObjectKey((GameProfile)obj);
   }

   @Override
   public String[] getKeys() {
      String[] strings = new String[this.getValues().size()];
      int n = 0;

      for (UserListWhitelistEntry userListWhitelistEntry : (Iterable<UserListWhitelistEntry>)(this.getValues().values())) {
         strings[n++] = ((GameProfile)userListWhitelistEntry.getValue()).getName();
      }

      return strings;
   }

   @Override
   public UserListEntry createEntry(JsonObject json) {
      return new UserListWhitelistEntry(json);
   }

   public GameProfile getByName(String str) {
      for (UserListWhitelistEntry userListWhitelistEntry : (Iterable<UserListWhitelistEntry>)(this.getValues().values())) {
         if (str.equalsIgnoreCase(((GameProfile)userListWhitelistEntry.getValue()).getName())) {
            return (GameProfile)userListWhitelistEntry.getValue();
         }
      }

      return null;
   }

   public UserListWhitelist(File file) {
      super(file);
   }
}
