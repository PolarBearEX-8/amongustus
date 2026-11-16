package augustus.anticheat.checks;

import client.Client;
import client.util.Packet;
import java.util.ArrayList;
import java.util.List;

public class Intave14InventoryAnalysisCheck extends AntiCheatCheck {
   public ArrayList arrayList;
   public long timestamp;
   public long timestamp2 = 0L;

   public void updateState5() {
      long timestamp3 = System.currentTimeMillis();
      if (this.timestamp != 0L) {
         long value = timestamp3 - this.timestamp;
         this.arrayList.add(value);
         if (this.arrayList.size() > 3) {
            this.arrayList.remove(0);
         }
      }

      this.timestamp = timestamp3;
   }

   @Override
   public void updateState2() {
   }

   private double computeValue3(List list) {
      double offset = 0.0;

      for (Long value : (Iterable<Long>) (list)) {
         offset += value.doubleValue();
      }

      return offset == 0.0 ? 0.0 : offset / (double)list.size();
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14InventoryAnalysisCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.arrayList = new ArrayList();
      this.timestamp = 0L;
   }
}
