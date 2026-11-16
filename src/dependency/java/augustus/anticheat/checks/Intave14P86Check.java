package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;

public class Intave14P86Check extends AntiCheatCheck {
   public final CompositeDoubleEntry compositeDoubleEntry = new CompositeDoubleEntry(86, "suspicious rotation snap", DirectOptionType.KILLAURA);

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14P86Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
   }
}
