package client.util;

public class FriendProfile {
   public int count2;
   public int count;
   public final String labelText;

   public int getCount2() {
      return this.count2;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public void setCount(int count3) {
      this.count = count3;
   }

   public int getIndex() {
      return this.count;
   }

   public FriendProfile(String text, int count3, int count4) {
      this.labelText = text;
      FriendListManager.getSettingArray();
      this.count = count3;
      this.count2 = count4;
   }
}
