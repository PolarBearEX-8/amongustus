package client.network;

public class NetworkPlayerInfoRecord {
   public String labelText;
   public String labelText2;
   public final NetworkPlayerInfo networkPlayerInfo;

   private NetworkPlayerInfoRecord(NetworkPlayerInfo networkPlayerInfo2, String text, String text2, String text3, String text4) {
      this.networkPlayerInfo = networkPlayerInfo2;
      this.labelText = text;
      this.labelText2 = text2;
   }
}
