package client.setting;

public enum RenderStateProperty {
    TEXTURE_1D(3552),
    TEXTURE_2D(3553),
    TEXTURE_3D(32879),
    TEXTURE_RECTANGLE(34037);

   public int count;

   public int getCount() {
      return this.count;
   }

   private RenderStateProperty(int count2) {
      this.count = count2;
   }


}
