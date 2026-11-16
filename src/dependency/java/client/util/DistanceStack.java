package client.util;

import client.event.BridgeListener;
import java.util.concurrent.Callable;
import net.minecraft.client.renderer.texture.TextureManager;

public class DistanceStack implements Callable {
   public final BridgeListener bridgeListener;
   public final TextureManager textureManager;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public DistanceStack(TextureManager textureManager2, BridgeListener bridgeListener2) {
      this.textureManager = textureManager2;
      this.bridgeListener = bridgeListener2;
   }

   public String getText() throws Exception {
      return this.bridgeListener.getClass().getName();
   }
}
