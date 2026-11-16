package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;

public class Intave14P85Check extends AntiCheatCheck {
   public int count;
   public float factor = 0.0F;
   public final CompositeDoubleEntry compositeDoubleEntry = new CompositeDoubleEntry(
      85, "high accuracy rotation yaw on hit-box corners", DirectOptionType.KILLAURA
   );

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14P85Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
   }
}
