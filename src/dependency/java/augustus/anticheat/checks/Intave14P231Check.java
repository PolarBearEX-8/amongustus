package augustus.anticheat.checks;

import client.Client;
import client.event.EventPacket;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;
import net.minecraft.util.MovingObjectPosition;

public class Intave14P231Check extends AntiCheatCheck {
   public final CompositeDoubleEntry compositeDoubleEntry;
   public int count = 0;
   public MovingObjectPosition movingObjectPosition;
   public int count4;
   public boolean enabled;
   public boolean enabled2 = false;
   public int count3;
   public int count2;

   @Override
   public void updateState2() {
      Client.getClient().getClass();
   }

   @Override
   public void onPacket(EventPacket packet) {
      Client.getClient().getClass();
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public Intave14P231Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.enabled = false;
      this.count3 = 0;
      this.movingObjectPosition = null;
      this.compositeDoubleEntry = new CompositeDoubleEntry(231, "attacks seem automated", DirectOptionType.KILLAURA);
   }
}
