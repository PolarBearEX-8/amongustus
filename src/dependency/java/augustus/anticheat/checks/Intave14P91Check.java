package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;
import com.google.common.collect.Lists;
import java.util.List;

public class Intave14P91Check extends AntiCheatCheck {
   public final List list2;
   public final List list = Lists.newArrayList();
   public final CompositeDoubleEntry compositeDoubleEntry;

   @Override
   public void updateState2() {
   }

   private double computeValue3(List list3) {
      double offset = 0.0;

      for (Object offset2Obj : list3) {
         Double offset2 = (Double)offset2Obj;
         offset += offset2;
      }

      return offset == 0.0 ? 0.0 : offset / (double)list3.size();
   }

   private double doubleValue(Double offset) {
      return offset;
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14P91Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.list2 = Lists.newArrayList();
      this.compositeDoubleEntry = new CompositeDoubleEntry(91, "rotated suspiciously", DirectOptionType.KILLAURA);
   }
}
