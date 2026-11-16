package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.renderer.EffectRenderer;

public class FovPool implements Callable {
   public final int count;
   public final EffectRenderer effectRenderer;

   public String getText() throws Exception {
      return this.count == 0
         ? "MISC_TEXTURE"
         : (this.count == 1 ? "TERRAIN_TEXTURE" : (this.count == 3 ? "ENTITY_PARTICLE_TEXTURE" : "Unknown - " + this.count));
   }

   public FovPool(EffectRenderer effectRenderer2, int count2) {
      this.effectRenderer = effectRenderer2;
      this.count = count2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
