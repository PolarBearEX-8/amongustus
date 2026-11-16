package client.setting;

import org.lwjgl.opengl.GL11;

public enum PassiveOptionType {
    LINE_SMOOTH(2848),
    DEPTH_TEST(2929),
    CULL_FACE(2884),
    TEXTURE_2D(3553),
    BLEND(3042),
    ALPHA_TEST(3008);

   public final int count;
   public boolean enabled;

   public boolean glIsEnabled() {
      return GL11.glIsEnabled(this.count);
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = this.glIsEnabled();
      this.glDisable(enabled2);
   }

   public void glDisable(boolean flag) {
      if (flag) {
         GL11.glEnable(this.count);
      } else {
         GL11.glDisable(this.count);
      }
   }

   public void updateState() {
      boolean enabled2 = this.enabled;
      this.glDisable(enabled2);
      this.enabled = this.glIsEnabled();
   }

   private PassiveOptionType(int count2) {
      this.count = count2;
   }


}
