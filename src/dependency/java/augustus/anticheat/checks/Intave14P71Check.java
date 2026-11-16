package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;

public class Intave14P71Check extends AntiCheatCheck {
   public CompositeDoubleEntry compositeDoubleEntry = new CompositeDoubleEntry(71, "rotated pitch too precisely", DirectOptionType.KILLAURA);

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14P71Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
   }
}
