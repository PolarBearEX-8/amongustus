package client.util;

public enum LOCKEDUNLOCKEDEnum {
    LOCKED(0, 146),
    LOCKED_HOVER(0, 166),
    LOCKED_DISABLED(0, 186),
    UNLOCKED(20, 146),
    UNLOCKED_HOVER(20, 166),
    UNLOCKED_DISABLED(20, 186);

   public final int count;
   public final int count2;
   public static final LOCKEDUNLOCKEDEnum[] ENUM_VALUES = new LOCKEDUNLOCKEDEnum[]{
      LOCKED, LOCKED_HOVER, LOCKED_DISABLED, UNLOCKED, UNLOCKED_HOVER, UNLOCKED_DISABLED
   };

   public int getCount() {
      return this.count;
   }

   private LOCKEDUNLOCKEDEnum(int count3, int count4) {
      this.count = count3;
      this.count2 = count4;
   }

   public int getCount2() {
      return this.count2;
   }
}
