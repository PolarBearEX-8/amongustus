package client.util;

import com.mojang.authlib.GameProfile;

public class GameProfileArrayHolder {
   public final int count;
   public final int count2;
   public GameProfile[] gameProfileArray;

   public void setGameProfileArray(GameProfile[] gameProfileArray2) {
      this.gameProfileArray = gameProfileArray2;
   }

   public int getCount() {
      return this.count;
   }

   public GameProfile[] getGameProfileArray() {
      return this.gameProfileArray;
   }

   public int getCount2() {
      return this.count2;
   }

   public GameProfileArrayHolder(int count3, int count4) {
      this.count = count3;
      this.count2 = count4;
   }
}
