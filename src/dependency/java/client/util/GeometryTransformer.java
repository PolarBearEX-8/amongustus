package client.util;

import java.util.concurrent.Callable;
import net.minecraft.entity.EntityRenderer;
import org.lwjgl.input.Mouse;

public class GeometryTransformer implements Callable {
   public final int count;
   public final int count2;
   public final EntityRenderer entityRenderer;

   public GeometryTransformer(EntityRenderer entityRenderer2, int count3, int count4) {
      this.entityRenderer = entityRenderer2;
      this.count = count3;
      this.count2 = count4;
   }

   public String getText() throws Exception {
      return String.format("Scaled: (%d, %d). Absolute: (%d, %d)", this.count, this.count2, Mouse.getX(), Mouse.getY());
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
