package client.util;

import client.event.EventBusManager;
import client.event.TickListener;
import net.minecraft.util.MathHelper;

public class TickListenerAdapter extends ConsumerEntry implements TickListener {
   public final TimeHelper timer = new TimeHelper();
   public int count = 0;

   @Override
   public void updateState2() {
      EventBusManager.getDietrichEvents2().unsubscribe(40, this);
   }

   @Override
   public String getTextAsText() {
      return "blocks";
   }

   @Override
   public String getText() {
      return "Move";
   }

   @Override
   public void updateState() {
      EventBusManager.getDietrichEvents2().subscribe(40, this);
   }

   @Override
   public void onPreTick() {
      if (this.timer.hasReached(900L)) {
         this.count = 0;
         this.timer.reset();
      }

      double offset = Math.abs(minecraft.thePlayer.posX - minecraft.thePlayer.lastTickPosX);
      double offset2 = Math.abs(minecraft.thePlayer.posZ - minecraft.thePlayer.lastTickPosZ);
      if (offset < 1.0 && offset2 < 1.0 && this.count < 21) {
         double offset3 = (double)MathHelper.sqrt_double(offset * offset + offset2 * offset2);
         this.applyDouble(offset3);
         this.count++;
      }
   }

   public TickListenerAdapter(int count2) {
      super((double)count2);
   }
}
