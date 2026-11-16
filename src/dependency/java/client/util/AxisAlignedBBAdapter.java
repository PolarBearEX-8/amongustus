package client.util;

import client.event.PreMotionConsumer;
import client.render.EntityEspRenderer;
import net.minecraft.util.AxisAlignedBB;

public class AxisAlignedBBAdapter extends AxisAlignedBB {
   public int count = -1;
   public boolean enabled = false;

   public boolean matchesPreMotionConsumer(PreMotionConsumer preMotionConsumer, int count2) {
      if (this.count != count2) {
         this.enabled = preMotionConsumer instanceof EntityEspRenderer
            ? ((EntityEspRenderer)preMotionConsumer).checkDouble(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ)
            : false;
         this.count = count2;
      }

      return this.enabled;
   }

   public AxisAlignedBBAdapter(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(posX, posX2, posX3, posX4, posY, posZ);
   }
}
