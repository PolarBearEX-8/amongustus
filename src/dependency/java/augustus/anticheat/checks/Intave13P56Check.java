package augustus.anticheat.checks;

import client.ClientGlobals;
import client.util.Packet;
import client.util.RayTraceUtil;
import client.util.TimeHelper;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C05PacketPlayerLook;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;

public class Intave13P56Check extends AntiCheatCheck {
   public int count;
   public final TimeHelper timer = new TimeHelper();

   @Override
   public void updateState2() {
      if (this.count > 12) {
         RayTraceUtil.applyString("P56: Hits get canceled. Level: " + this.count);
         if (this.count > 50 && (this.count < 100 || ThreadLocalRandom.current().nextBoolean() && ThreadLocalRandom.current().nextBoolean())) {
            RayTraceUtil.applyString("P56 (Intave13 Debug) " + minecraft.thePlayer.getText() + " is rotating suspiciously unsmooth | vl: " + this.count);
         }
      }
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C06PacketPlayerPosLook || packet instanceof C05PacketPlayerLook) {
         float yaw = minecraft.thePlayer.rotationYaw;
         float pitch = minecraft.thePlayer.rotationPitch;
         float yaw2 = ClientGlobals.rotationManager.getRotation3().yaw;
         float pitch2 = ClientGlobals.rotationManager.getRotation3().pitch;
         double offset = (double)Math.abs(yaw2 - yaw);
         double offset2 = (double)Math.abs(pitch2 - pitch);
         double offset3 = Math.abs(offset) + Math.abs(offset2);
         long time = this.timer.getTime();
         if (offset3 > 8.0 && time < 4000L) {
            this.count++;
            if (offset3 > 30.0 && time < 250L) {
               this.count++;
               if (offset3 > 60.0) {
                  this.count++;
               }
            }
         } else if (this.count > 0) {
            if (!(offset3 < 3.0) && time <= 5000L) {
               this.count--;
            } else {
               this.count = 0;
            }
         }
      }

      if (packet instanceof C02PacketUseEntity) {
         this.timer.reset();
      }
   }

   public Intave13P56Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.count = 0;
      this.state = true;
   }
}
