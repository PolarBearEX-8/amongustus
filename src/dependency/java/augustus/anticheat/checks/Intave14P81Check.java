package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;

public class Intave14P81Check extends AntiCheatCheck {
   public final CompositeDoubleEntry compositeDoubleEntry = new CompositeDoubleEntry(81, "follows entity movement too precisely", DirectOptionType.KILLAURA);

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14P81Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
   }
}
