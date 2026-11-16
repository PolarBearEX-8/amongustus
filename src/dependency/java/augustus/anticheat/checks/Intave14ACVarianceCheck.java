package augustus.anticheat.checks;

import client.Client;
import client.util.Packet;
import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.item.ItemFishingRod;

public class Intave14ACVarianceCheck extends AntiCheatCheck {
   public boolean enabled;
   public double value;
   public long timestamp;
   public long timestamp3 = 0L;
   public long timestamp2;
   public final ArrayList arrayList = new ArrayList();

   @Override
   public void updateState2() {
   }

   private double computeValue(Collection collection) {
      double offset = 0.0;
      double offset2 = 0.0;

      for (Long value2 : (Iterable<Long>)(Iterable<?>)collection) {
         offset += value2.doubleValue();
      }

      double offset3 = offset / (double)collection.size();

      for (Long value3 : (Iterable<Long>)(Iterable<?>)collection) {
         offset2 += (value3.doubleValue() - offset3) * (value3.doubleValue() - offset3);
      }

      return Math.sqrt(offset2 / (double)collection.size());
   }

   private boolean checkTime(long value2) {
      return value2 > 4000L
         || this.enabled
         || System.currentTimeMillis() - this.timestamp < 3000L
            && minecraft.thePlayer.getHeldItem() != null
            && minecraft.thePlayer.getHeldItem().getItem() instanceof ItemFishingRod;
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14ACVarianceCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.timestamp2 = 0L;
      this.value = 0.0;
      this.enabled = false;
      this.timestamp = 0L;
   }
}
