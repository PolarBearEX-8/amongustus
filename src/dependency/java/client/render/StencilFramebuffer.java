package client.render;

import client.event.EventBusManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.item.TertiaryItemCallback;

public class StencilFramebuffer extends Framebuffer implements TertiaryItemCallback {
   @Override
   public void applyIndex(int count, int count2) {
      this.applyIndex2(count, count2);
   }

   public StencilFramebuffer(int count, int count2, boolean flag) {
      super(count, count2, flag);
      EventBusManager.getDietrichEvents2().subscribe(47, (TertiaryItemCallback)this::applyIndex2);
   }
}
