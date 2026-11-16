package client.util;

import augustus.anticheat.checks.AntiCheatCheck;
import augustus.anticheat.checks.AntiCheatManager;
import client.Client;
import client.event.EventPacket;
import com.google.common.util.concurrent.AtomicDouble;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.server.S07PacketRespawn;

public abstract class StringEntryBridge extends AntiCheatCheck {
   public boolean enabled3;
   public final ArrayList arrayList;
   public int count4 = 0;
   public int count;
   public int count3;
   public boolean enabled2;
   public final boolean enabled = true;
   public int count6;
   public int count5;
   public final ArrayList arrayList2;
   public final boolean enabled4 = false;
   public final ArrayList arrayList3;

   @Override
   public void updateState2() {
   }

   private boolean isActiveAsFlag() {
      return minecraft.objectMouseOver != null
         && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY
         && minecraft.objectMouseOver.entityHit instanceof EntityLivingBase;
   }

   @Override
   public void onPacket(EventPacket packet) {
      if (packet.getPacket() instanceof S07PacketRespawn) {
         this.count = 0;
      }
   }

   public double computeValue(Collection collection) {
      double offset = 0.0;
      double offset2 = 0.0;

      for (Integer count2 : (Iterable<Integer>)(Iterable<?>)collection) {
         offset += count2.doubleValue();
      }

      double offset3 = offset / (double)collection.size();

      for (Integer count7 : (Iterable<Integer>)(Iterable<?>)collection) {
         offset2 += (count7.doubleValue() - offset3) * (count7.doubleValue() - offset3);
      }

      return Math.sqrt(offset2 / (double)collection.size());
   }

   public double computeValue4(List list) {
      return 20.0 / this.computeValue3(list);
   }

   public double computeValue3(List list) {
      return list.stream().mapToDouble((x -> ((Number)x).doubleValue())).average().orElse(0.0);
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   private static void applyAtomicDouble(AtomicDouble atomicDouble, double offset, Integer count2) {
      atomicDouble.getAndAdd(Math.pow(count2.doubleValue() - offset, 2.0));
   }

   public abstract void applyList(List list);

   public StringEntryBridge(String text, AntiCheatManager antiCheatManager, int count2) {
      super(text, antiCheatManager);
      this.arrayList = new ArrayList();
      this.count3 = 0;
      this.count = 0;
      this.arrayList2 = new ArrayList();
      this.arrayList3 = new ArrayList();
      this.count6 = 0;
      this.enabled2 = false;
      this.enabled3 = false;
      this.count4 = count2;
   }
}
