package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;

public class CompositeDoubleEntryAntiCheatCheck extends AntiCheatCheck {
   public final CompositeDoubleEntry compositeDoubleEntry;
   public boolean enabled = false;

   public CompositeDoubleEntryAntiCheatCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.compositeDoubleEntry = new CompositeDoubleEntry(31, "", DirectOptionType.KILLAURA);
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   @Override
   public void updateState2() {
   }
}
