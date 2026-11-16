package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;

public class Intave14InvalidBlockPlaceCheck extends AntiCheatCheck {
   public final CompositeDoubleEntry compositeDoubleEntry = new CompositeDoubleEntry(1337, "suspicious block-placement", DirectOptionType.SCAFFOLD);

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14InvalidBlockPlaceCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
   }
}
