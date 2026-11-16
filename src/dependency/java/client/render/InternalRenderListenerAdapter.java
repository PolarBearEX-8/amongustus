package client.render;

import client.event.EventBusManager;
import client.event.RenderListener;
import org.lwjgl.input.Mouse;

public class InternalRenderListenerAdapter implements RenderListener {
   public int count = 0;

   @Override
   public void onRender3D(float factor) {
      this.count = Mouse.getDWheel();
   }

   public InternalRenderListenerAdapter() {
      EventBusManager.getDietrichEvents2().subscribe(32, this);
   }
}
