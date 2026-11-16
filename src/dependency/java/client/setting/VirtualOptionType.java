package client.setting;

public enum VirtualOptionType {
    RED(6403),
    RG(33319),
    RGB(6407),
    BGR(32992),
    RGBA(6408),
    BGRA(32993),
    RED_INTEGER(36244),
    RG_INTEGER(33320),
    RGB_INTEGER(36248),
    BGR_INTEGER(36250),
    RGBA_INTEGER(36249),
    BGRA_INTEGER(36251);

   public int count;

   public int getCount() {
      return this.count;
   }

   private VirtualOptionType(int count2) {
      this.count = count2;
   }


}
