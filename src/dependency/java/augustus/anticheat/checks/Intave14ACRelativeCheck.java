package augustus.anticheat.checks;

import client.Client;
import client.util.Packet;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import net.minecraft.item.ItemFishingRod;

public class Intave14ACRelativeCheck extends AntiCheatCheck {
   public double value;
   public final Deque deque;
   public boolean enabled;
   public double value2;
   public long timestamp = 0L;
   public long timestamp2;

   @Override
   public void updateState2() {
   }

   private boolean checkTime(long value3) {
      return value3 > 4000L
         || this.enabled
         || System.currentTimeMillis() - this.timestamp2 < 3000L
            && minecraft.thePlayer.getHeldItem() != null
            && minecraft.thePlayer.getHeldItem().getItem() instanceof ItemFishingRod;
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   private double computeValue(Collection collection) {
      double offset = 0.0;
      int count = 0;

      for (Long value3 : (Iterable<Long>)(Iterable<?>)collection) {
         offset += value3.doubleValue();
         count++;
      }

      if ((double)count < 3.0) {
         return 0.0;
      } else {
         double offset2 = (double)count * ((double)count + 1.0) / (((double)count - 1.0) * ((double)count - 2.0) * ((double)count - 3.0));
         double offset3 = 3.0 * Math.pow((double)count - 1.0, 2.0) / (((double)count - 2.0) * ((double)count - 3.0));
         double offset4 = offset / (double)count;
         double offset5 = 0.0;
         double offset6 = 0.0;

         for (Number number : (Iterable<Number>)(Iterable<?>)collection) {
            offset5 += Math.pow(offset4 - number.doubleValue(), 2.0);
            offset6 += Math.pow(offset4 - number.doubleValue(), 4.0);
         }

         return offset2 * (offset6 / Math.pow(offset5 / offset, 2.0)) - offset3;
      }
   }

   public Intave14ACRelativeCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.deque = new ArrayDeque();
      this.value2 = 0.0;
      this.value = 0.0;
      this.enabled = false;
      this.timestamp2 = 0L;
   }
}
