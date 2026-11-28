package net.minecraft.server.management;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;

public class UserListOps extends UserList {
   @Override
   public UserListEntry createEntry(JsonObject json) {
      return new UserListOpsEntry(json);
   }

   public String getObjectKey(GameProfile profile) {
      return profile.getId().toString();
   }

   public boolean bypassesPlayerLimit(GameProfile profile) {
      UserListOpsEntry entry = (UserListOpsEntry)this.getEntry(profile);
      return entry != null ? entry.isBypassesPlayerLimit() : false;
   }

   @Override
   public String getObjectKey(Object obj) {
      return this.getObjectKey((GameProfile)obj);
   }

   @Override
   public String[] getKeys() {
      String[] strings = new String[this.getValues().size()];
      int n = 0;

      for (UserListOpsEntry userListOpsEntry : (Iterable<UserListOpsEntry>)(this.getValues().values())) {
         strings[n++] = ((GameProfile)userListOpsEntry.getValue()).getName();
      }

      return strings;
   }

   public UserListOps(File file) {
      super(file);
   }

   public GameProfile getGameProfileFromName(String str) {
      for (UserListOpsEntry userListOpsEntry : (Iterable<UserListOpsEntry>)(this.getValues().values())) {
         if (str.equalsIgnoreCase(((GameProfile)userListOpsEntry.getValue()).getName())) {
            return (GameProfile)userListOpsEntry.getValue();
         }
      }

      return null;
   }
}
