package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;

public class Intave14P83Check extends AntiCheatCheck {
   public double value;
   public final CompositeDoubleEntry compositeDoubleEntry = new CompositeDoubleEntry(83, "high accuracy rotation yaw vl:", DirectOptionType.KILLAURA);

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14P83Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.value = 0.0;
   }
}
