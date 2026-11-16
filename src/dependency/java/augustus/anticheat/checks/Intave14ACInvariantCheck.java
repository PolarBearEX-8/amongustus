package augustus.anticheat.checks;

import client.Client;
import client.util.Packet;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.item.ItemFishingRod;

public class Intave14ACInvariantCheck extends AntiCheatCheck {
   public final ArrayList arrayList;
   public long timestamp;
   public double value;
   public boolean enabled;
   public long timestamp3 = 0L;
   public long timestamp2;

   @Override
   public void updateState2() {
   }

   private double computeValueByArrayList(ArrayList list) {
      double offset = 0.0;
      int count = 0;
      ArrayList<Double> list2 = new ArrayList<>();
      Iterator iterator = list.iterator();

      while (iterator.hasNext()) {
         double offset2 = ((Long)iterator.next()).doubleValue();
         offset += offset2;
         count++;
         list2.add(offset2);
      }

      if (count == 0) {
         return 0.0;
      } else {
         list2.sort(Double::compareTo);
         double offset3 = offset / (double)count;
         double offset4 = (Double)list2.get((count % 2 != 0 ? count : count - 1) / 2);
         double offset5 = this.computeValue(list2);
         return 3.0 * (offset3 - offset4) / offset5;
      }
   }

   private double computeValue(ArrayList list) {
      double offset = 0.0;
      double offset2 = 0.0;

      for (Double offset3 : (Iterable<Double>) list) {
         offset += offset3;
      }

      double offset4 = offset / (double)list.size();

      for (Double offset5 : (Iterable<Double>) list) {
         offset2 += (offset5 - offset4) * (offset5 - offset4);
      }

      return Math.sqrt(offset2 / (double)list.size());
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   private boolean checkTime(long value2) {
      return value2 > 4000L
         || this.enabled
         || System.currentTimeMillis() - this.timestamp2 < 3000L
            && minecraft.thePlayer.getHeldItem() != null
            && minecraft.thePlayer.getHeldItem().getItem() instanceof ItemFishingRod;
   }

   public Intave14ACInvariantCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.timestamp = 0L;
      this.value = 0.0;
      this.arrayList = new ArrayList();
      this.enabled = false;
      this.timestamp2 = 0L;
   }
}
