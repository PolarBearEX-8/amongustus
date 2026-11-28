package net.minecraft.client.network;


public class NetworkPlayerInfoEntry {
   public String name;
   public String value;
   public final NetworkPlayerInfo networkPlayerInfo;

   private NetworkPlayerInfoEntry(NetworkPlayerInfo networkPlayerInfo2, String str, String str2, String str3, String str4) {
      this.networkPlayerInfo = networkPlayerInfo2;
      this.name = str;
      this.value = str2;
   }
}
