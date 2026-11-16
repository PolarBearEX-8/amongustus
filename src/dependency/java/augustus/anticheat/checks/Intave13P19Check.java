package augustus.anticheat.checks;

import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;
import client.util.RayTraceUtil;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;

public class Intave13P19Check extends AntiCheatCheck {
   public final CompositeDoubleEntry compositeDoubleEntry;
   public long timestamp = 0L;

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C06PacketPlayerPosLook) {
         this.timestamp = System.currentTimeMillis();
      }

      if (packet instanceof C02PacketUseEntity) {
         long value = System.currentTimeMillis() - this.timestamp;
         if (value < 23L && value > 0L) {
            double offset = 1.0;
            this.compositeDoubleEntry.applyDouble(offset);
            int value2 = (int)this.compositeDoubleEntry.getValue2();
            if (this.compositeDoubleEntry.getValue2() > 10.0) {
               CompositeDoubleEntry compositeDoubleEntry2 = this.compositeDoubleEntry;
               double offset2 = (this.compositeDoubleEntry.getValue2() - 10.0) * -1.0;
               compositeDoubleEntry2.applyDouble(offset2);
            }

            if (value2 > 5) {
               RayTraceUtil.applyString("P19 (Intave13 Debug) " + minecraft.thePlayer.getText() + " l: " + value + " vl: " + value2);
            }

            if (value2 > 8) {
               RayTraceUtil.applyString("P19: Hits get canceled. Level: " + this.compositeDoubleEntry.getValue2());
            }
         } else if (this.compositeDoubleEntry.getValue2() > 0.0) {
            double offset3 = -1.0;
            this.compositeDoubleEntry.applyDouble(offset3);
         }
      }
   }

   public Intave13P19Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.compositeDoubleEntry = new CompositeDoubleEntry(19, "Attacks to fast after tick", DirectOptionType.KILLAURA);
      this.state = true;
   }
}
