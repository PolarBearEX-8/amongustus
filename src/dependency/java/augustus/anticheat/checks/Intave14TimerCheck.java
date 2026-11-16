package augustus.anticheat.checks;

import client.Client;
import client.event.EventPacket;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class Intave14TimerCheck extends AntiCheatCheck {
   public double value;
   public final int count;
   public long timestamp = 0L;
   public final boolean enabled;
   public final CompositeDoubleEntry compositeDoubleEntry;
   public long timestamp5 = 0L;
   public long timestamp2 = 0L;
   public long timestamp3;
   public long timestamp4 = 0L;

   @Override
   public void updateState2() {
   }

   @Override
   public void onPacket(EventPacket packet) {
      Packet packet2 = packet.getPacket();
      if (packet2 instanceof S07PacketRespawn) {
         this.timestamp2 = System.currentTimeMillis();
         this.value -= 20.0;
      }

      if (packet2 instanceof S08PacketPlayerPosLook) {
         this.timestamp = 0L;
         double offset = 2.0;
         this.value -= offset;
         this.timestamp5 = System.currentTimeMillis();
      }
   }

   @Override
   public void onPacketByEventPacket(EventPacket packet) {
      Client.getClient().getClass();
   }

   @Override
   public void handlePacket(Packet packet) {
   }

   public Intave14TimerCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.timestamp3 = 0L;
      this.value = 0.0;
      this.enabled = false;
      this.count = 1000;
      this.compositeDoubleEntry = new CompositeDoubleEntry(13, "", DirectOptionType.KILLAURA);
   }
}
