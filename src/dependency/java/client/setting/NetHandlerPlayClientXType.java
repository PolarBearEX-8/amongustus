package client.setting;

import java.util.EnumSet;
import java.util.Set;

public enum NetHandlerPlayClientXType {
   X(0),
   Y(1),
   Z(2),
   Y_ROT(3),
   X_ROT(4);

   public int count;
   public static final NetHandlerPlayClientXType[] ENUM_VALUES = new NetHandlerPlayClientXType[]{X, Y, Z, Y_ROT, X_ROT};

   public static Set getSet(int count2) {
      EnumSet enumSet = EnumSet.noneOf(NetHandlerPlayClientXType.class);

      for (NetHandlerPlayClientXType netHandlerPlayClientXType : values()) {
         if (netHandlerPlayClientXType.checkIndex(count2)) {
            enumSet.add(netHandlerPlayClientXType);
         }
      }

      return enumSet;
   }

   private int getCount() {
      return 1 << this.count;
   }

   public static int getIndex(Set set) {
      int count2 = 0;

      for (NetHandlerPlayClientXType netHandlerPlayClientXType : (Iterable<NetHandlerPlayClientXType>)(set)) {
         count2 |= netHandlerPlayClientXType.getCount();
      }

      return count2;
   }

   private boolean checkIndex(int count2) {
      return (count2 & this.getCount()) == this.getCount();
   }

   private NetHandlerPlayClientXType(int count2) {
      this.count = count2;
   }


}
