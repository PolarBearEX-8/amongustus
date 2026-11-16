package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;

public class Intave14P82Check extends AntiCheatCheck {
   public final CompositeDoubleEntry compositeDoubleEntry = new CompositeDoubleEntry(82, "rotated yaw too precise (0.0)", DirectOptionType.KILLAURA);

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14P82Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
   }
}
