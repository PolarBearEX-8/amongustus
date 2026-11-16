package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;
import com.google.common.collect.Lists;
import java.util.List;

public class Intave14P51Check extends AntiCheatCheck {
   public final List list2;
   public final List list;
   public double value2;
   public double value;
   public final CompositeDoubleEntry compositeDoubleEntry = new CompositeDoubleEntry(
      51, "maintains high attack accuracy whilst aiming at hitbox corners", DirectOptionType.KILLAURA
   );

   private double computeValue3(List list3) {
      double offset = 0.0;

      for (Number number : (Iterable<Number>)(Iterable)list3) {
         offset += number.doubleValue();
      }

      return offset == 0.0 ? 0.0 : offset / (double)list3.size();
   }

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14P51Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.value = 0.0;
      this.value2 = 0.0;
      this.list2 = Lists.newArrayList();
      this.list = Lists.newArrayList();
   }
}
