package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;

public class Intave14InvalidPitchCheck extends AntiCheatCheck {
   public final CompositeDoubleEntry compositeDoubleEntry = new CompositeDoubleEntry(9669, "sent invalid rotation", DirectOptionType.KILLAURA);

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14InvalidPitchCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
   }
}
