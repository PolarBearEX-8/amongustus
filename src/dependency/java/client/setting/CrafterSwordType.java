package client.setting;

public enum CrafterSwordType {
    SWORD(2, -1),
    CHEST_PLATE(8, 1),
    TROUSERS(7, 2),
    HELMET(5, 0),
    SHOES(4, 3),
    NULL(1, 1);

   public final int count;
   public final int count2;
   public static final CrafterSwordType[] ENUM_VALUES = new CrafterSwordType[]{SWORD, CHEST_PLATE, TROUSERS, HELMET, SHOES, NULL};

   private CrafterSwordType(int count3, int count4) {
      this.count = count3;
      this.count2 = count4;
   }


}
