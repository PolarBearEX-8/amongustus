package augustus.anticheat.checks;

import client.Client;
import client.util.Packet;
import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.item.ItemFishingRod;

public class Intave14ACSpikeCheck extends AntiCheatCheck {
   public final ArrayList arrayList;
   public double value2;
   public double value;
   public long timestamp;
   public long timestamp2 = 0L;
   public boolean enabled;

   @Override
   public void updateState2() {
   }

   private boolean checkTime(long value3) {
      return value3 > 4000L
         || this.enabled
         || System.currentTimeMillis() - this.timestamp < 3000L
            && minecraft.thePlayer.getHeldItem() != null
            && minecraft.thePlayer.getHeldItem().getItem() instanceof ItemFishingRod;
   }

   private double computeValueByCollection(Collection collection) {
      double offset = 0.0;

      for (Long value3 : (Iterable<Long>)(Iterable<?>)collection) {
         offset += value3.doubleValue();
      }

      return offset;
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   private double computeValue(Collection collection) {
      return 20.0 / this.computeValueByCollection(collection) * 50.0;
   }

   public Intave14ACSpikeCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.arrayList = new ArrayList();
      this.value2 = 0.0;
      this.value = 0.0;
      this.enabled = false;
      this.timestamp = 0L;
   }
}
