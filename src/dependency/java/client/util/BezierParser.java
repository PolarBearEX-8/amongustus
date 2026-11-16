package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.renderer.EffectRenderer;
import net.minecraft.entity.TertiaryEntityAdapter;

public class BezierParser implements Callable {
   public final TertiaryEntityAdapter tertiaryEntityAdapter;
   public final EffectRenderer effectRenderer;

   public String getText() throws Exception {
      return this.tertiaryEntityAdapter.toString();
   }

   public BezierParser(EffectRenderer effectRenderer2, TertiaryEntityAdapter tertiaryEntityAdapter2) {
      this.effectRenderer = effectRenderer2;
      this.tertiaryEntityAdapter = tertiaryEntityAdapter2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
