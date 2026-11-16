package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;
import java.util.ArrayList;
import java.util.List;

public class Intave14PostBlockPlaceCheck extends AntiCheatCheck {
   public CompositeDoubleEntry compositeDoubleEntry;
   public int count;
   public List list;
   public long timestamp2;
   public long timestamp = 0L;

   @Override
   public void updateState2() {
   }

   private double computeValue3(List list2) {
      double offset = 0.0;

      for (Long value : (java.lang.Iterable<Long>) (list2)) {
         offset += value.doubleValue();
      }

      return offset == 0.0 ? 0.0 : offset / (double)list2.size();
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14PostBlockPlaceCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.list = new ArrayList();
      this.timestamp2 = 0L;
      this.compositeDoubleEntry = new CompositeDoubleEntry(1337, "suspicious block-placement (Post Build)", DirectOptionType.SCAFFOLD);
   }
}
