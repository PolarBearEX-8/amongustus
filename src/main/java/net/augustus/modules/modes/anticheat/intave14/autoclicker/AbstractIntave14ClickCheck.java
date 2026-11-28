package net.augustus.modules.modes.anticheat.intave14.autoclicker;

import net.minecraft.util.MovingObjectPosition;
import com.google.common.util.concurrent.AtomicDouble;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.augustus.modules.ModuleMode;
import net.augustus.events.EventPacket;
import net.augustus.settings.Setting;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S07PacketRespawn;

public abstract class AbstractIntave14ClickCheck extends ModuleMode {
   public int violationLevel;
   private int ticksSinceClick;
   private final ArrayList recentClickDelays;
   private int clickCounter;
   private boolean diggingBlock;
   private final boolean debugMode;
   private int sampleThreshold;
   private final boolean checkEnabled;
   private int consecutiveFastClicks;
   private boolean placingBlock;
   private final ArrayList clickIntervals;
   private final ArrayList cpsSamples;

   @Override
   public void onTick() {
   }

   private static void accumulateSquaredDiff(AtomicDouble atomicDouble, double d, Integer num) {
      atomicDouble.getAndAdd(Math.pow(num.doubleValue() - d, 2.0));
   }

   public abstract void analyzeClicks(List list);

   @SuppressWarnings("unchecked")
   public double calculateMean(List list) {
      return ((List<Number>)list).stream().mapToDouble(Number::doubleValue).average().orElse(0.0);
   }

   private boolean isTargetingLivingEntity() {
      return mc.objectMouseOver != null
         && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY
         && mc.objectMouseOver.entityHit instanceof EntityLivingBase;
   }

   public double calculateStdDev(Collection items) {
      double d = 0.0;

      for (Object obj : items) {
         d += ((Integer)obj).doubleValue();
      }

      double d2 = d / (double)items.size();
      double d3 = 0.0;

      for (Object obj2 : items) {
         double d4 = ((Integer)obj2).doubleValue();
         d3 += (d4 - d2) * (d4 - d2);
      }

      return Math.sqrt(d3 / (double)items.size());
   }

   @Override
   public void onServerPacket(EventPacket event) {
      if (event.getPacket() instanceof S07PacketRespawn) {
         this.violationLevel = 0;
      }
   }

   public AbstractIntave14ClickCheck(String str, AntiCheatPlayerData antiCheatPlayerData2, int n) {
      super(str, antiCheatPlayerData2);
      this.sampleThreshold = 0;
      this.checkEnabled = true;
      this.debugMode = false;
      this.clickIntervals = new ArrayList();
      this.ticksSinceClick = 0;
      this.violationLevel = 0;
      this.cpsSamples = new ArrayList();
      this.recentClickDelays = new ArrayList();
      this.consecutiveFastClicks = 0;
      this.diggingBlock = false;
      this.placingBlock = false;
      this.sampleThreshold = n;
   }

   public double calculateCps(List list) {
      return 20.0 / this.calculateMean(list);
   }

   @Override
   public void onClientPacket(Packet packet) {
   }

}
