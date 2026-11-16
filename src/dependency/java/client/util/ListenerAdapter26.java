package client.util;

import client.event.BridgeListener;
import client.render.AllocateTextureMapManager;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public abstract class ListenerAdapter26 implements BridgeListener {
   public int count = -1;
   public boolean enabled2;
   public boolean enabled;
   public boolean enabled4;
   public boolean enabled3;
   public DistanceTransformer distanceTransformer;

   @Override
   public void applyFlag(boolean flag, boolean flag2) {
      this.enabled4 = this.enabled2;
      this.enabled3 = this.enabled;
      this.applyFlagByFlag(flag, flag2);
   }

   @Override
   public DistanceTransformer getDistanceTransformer() {
      return AllocateTextureMapManager.resolveDistanceTransformer(this);
   }

   @Override
   public void updateState() {
      this.applyFlagByFlag(this.enabled4, this.enabled3);
   }

   @Override
   public int getCount() {
      if (this.count == -1) {
         this.count = SpecialLoggerEntry.getCount();
      }

      return this.count;
   }

   public void applyFlagByFlag(boolean flag, boolean flag2) {
      this.enabled2 = flag;
      this.enabled = flag2;
      int count2;
      short shortValue;
      if (flag) {
         count2 = flag2 ? 9987 : 9729;
         shortValue = 9729;
      } else {
         count2 = flag2 ? 9986 : 9728;
         shortValue = 9728;
      }

      GlStateManager.setActiveTexture(this.getCount());
      GL11.glTexParameteri(3553, 10241, count2);
      GL11.glTexParameteri(3553, 10240, shortValue);
   }

   public void updateState3() {
      AllocateTextureMapManager.applyListenerAdapter26(this, this.count);
      if (this.count != -1) {
         SpecialLoggerEntry.applyIndex(this.count);
         this.count = -1;
      }
   }
}
