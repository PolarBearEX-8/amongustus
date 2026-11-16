package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;

public class Intave14P131Check extends AntiCheatCheck {
   public final CompositeDoubleEntry compositeDoubleEntry = new CompositeDoubleEntry(131, "closed inventory too quickly (0)", DirectOptionType.KILLAURA);
   public int count;
   public boolean enabled;

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14P131Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
   }
}
