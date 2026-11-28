package net.minecraft.server.management;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;

public class UserListBans extends UserList {
   public boolean isBanned(GameProfile profile) {
      return this.hasEntry(profile);
   }

   @Override
   public String[] getKeys() {
      String[] strings = new String[this.getValues().size()];
      int n = 0;

      for (UserListBansEntry userListBansEntry : (Iterable<UserListBansEntry>)(this.getValues().values())) {
         strings[n++] = ((GameProfile)userListBansEntry.getValue()).getName();
      }

      return strings;
   }

   @Override
   public String getObjectKey(Object obj) {
      return this.getObjectKey((GameProfile)obj);
   }

   public GameProfile isUsernameBanned(String str) {
      for (UserListBansEntry userListBansEntry : (Iterable<UserListBansEntry>)(this.getValues().values())) {
         if (str.equalsIgnoreCase(((GameProfile)userListBansEntry.getValue()).getName())) {
            return (GameProfile)userListBansEntry.getValue();
         }
      }

      return null;
   }

   public String getObjectKey(GameProfile profile) {
      return profile.getId().toString();
   }

   public UserListBans(File file) {
      super(file);
   }

   @Override
   public UserListEntry createEntry(JsonObject json) {
      return new UserListBansEntry(json);
   }
}
