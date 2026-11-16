package client.util;

import client.setting.TabListIntegerType;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class HealthHandlerBridge extends HealthHandlerAdapter {
   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public TabListIntegerType getINTEGER() {
      return TabListIntegerType.HEARTS;
   }

   @Override
   public int computeCount(List list) {
      float factor = 0.0F;

      for (EntityPlayer player : (Iterable<EntityPlayer>)(Iterable)list) {
         factor += player.getFloat2() + player.getScaleFactor13();
      }

      if (list.size() > 0) {
         factor /= (float)list.size();
      }

      return MathHelper.computeCount(factor);
   }

   public HealthHandlerBridge(String text) {
      super(text);
   }
}
