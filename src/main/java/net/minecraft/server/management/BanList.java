package net.minecraft.server.management;

import com.google.gson.JsonObject;
import java.io.File;
import java.net.SocketAddress;

public class BanList extends UserList {
   public boolean isBanned(SocketAddress socketAddress) {
      return this.hasEntry(this.addressToString(socketAddress));
   }

   @Override
   public UserListEntry createEntry(JsonObject json) {
      return new IPBanEntry(json);
   }

   public BanList(File file) {
      super(file);
   }

   private String addressToString(SocketAddress socketAddress) {
      String str = socketAddress.toString();
      if (str.contains("/")) {
         str = str.substring(str.indexOf(47) + 1);
      }

      if (str.contains(":")) {
         str = str.substring(0, str.indexOf(58));
      }

      return str;
   }

   public IPBanEntry getBanEntry(SocketAddress socketAddress) {
      return (IPBanEntry)this.getEntry(this.addressToString(socketAddress));
   }
}
