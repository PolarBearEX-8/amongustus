package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;
import com.google.common.collect.Lists;
import java.util.List;

public class Intave14P92Check extends AntiCheatCheck {
   public final CompositeDoubleEntry compositeDoubleEntry;
   public final List list = Lists.newArrayList();
   public final List list2 = Lists.newArrayList();

   @Override
   public void updateState2() {
   }

   private double doubleValue(Double offset) {
      return offset;
   }

   private double computeValue3(List list3) {
      double offset = 0.0;

      for (Double offset2 : (Iterable<Double>)(Iterable)list3) {
         offset += offset2;
      }

      return offset == 0.0 ? 0.0 : offset / (double)list3.size();
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14P92Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.compositeDoubleEntry = new CompositeDoubleEntry(92, "precise rotation yaw", DirectOptionType.KILLAURA);
   }
}
