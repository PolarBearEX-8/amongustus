package client.setting;

public enum CombatValueProperty {
   FLOAT(4, "Float", 5126),
   UBYTE(1, "Unsigned Byte", 5121),
   BYTE(1, "Byte", 5120),
   USHORT(2, "Unsigned Short", 5123),
   SHORT(2, "Short", 5122),
   UINT(4, "Unsigned Int", 5125),
   INT(4, "Int", 5124);

   public final int count;
   public final String labelText;
   public final int count2;
   public static final CombatValueProperty[] ENUM_VALUES = values();

   public int getCount2() {
      return this.count2;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getCount() {
      return this.count;
   }

   private CombatValueProperty(int count3, String text, int count4) {
      this.count = count3;
      this.labelText = text;
      this.count2 = count4;
   }
}
