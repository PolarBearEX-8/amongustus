package net.augustus.modules.modes.clicking;

import net.augustus.ClientManagers;
import net.augustus.events.EventClick;
import net.augustus.modules.combat.helper.ClickModuleContext;
import net.augustus.modules.legit.ClickDensitySpike;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.TuplePair;
import java.security.SecureRandom;
import java.util.ArrayList;
import net.augustus.modules.Module;
import net.augustus.settings.SettingGroup;

public class DensityClickPatternMode extends AbstractClickPatternMode {
   private long nextClickTimeMs;
   private long releaseTimeMs;
   private long virtualTickTimeMs;
   private ClickPattern clickPattern;
   private long lastMouseMoveTimeMs;
   private int[] targetMouseDelta;
   private float[] lastPatternRotation;
   private double[] accumulatedMouseDelta;
   private int[] appliedMouseDelta;
   private int tickDurationMs;
   public SettingGroup pressedTimings;
   public SettingGroup unpressedTimings;
   private ArrayList pressedSpikes;
   private ArrayList unpressedSpikes;
   private SecureRandom secureRandom;

   public DensityClickPatternMode(Module module, SettingGroup doubleValue) {
       super(module, doubleValue, "Density");
      this.nextClickTimeMs = 0L;
      this.releaseTimeMs = 0L;
      this.virtualTickTimeMs = 0L;
      this.clickPattern = null;
      this.lastMouseMoveTimeMs = 0L;
      this.targetMouseDelta = new int[2];
      this.lastPatternRotation = new float[2];
      this.accumulatedMouseDelta = new double[2];
      this.appliedMouseDelta = new int[2];
      this.tickDurationMs = 0;
      this.pressedSpikes = new ArrayList();
      this.unpressedSpikes = new ArrayList();
      this.secureRandom = new SecureRandom();
      SettingGroup pressedTimingsGroup = new SettingGroup(module, "Pressed Timings");
      this.pressedTimings = pressedTimingsGroup.visibleWhen(() -> this.isSelected());
      pressedTimingsGroup = new SettingGroup(module, "Unpressed Timings");
      this.unpressedTimings = pressedTimingsGroup.visibleWhen(() -> this.isSelected());
      byte b = 4;

      for (int i = 0; i < b; i++) {
         this.pressedSpikes.add(new ClickDensitySpike(module, this.pressedTimings, i + 1));
      }

      for (int j = 0; j < b; j++) {
         this.unpressedSpikes.add(new ClickDensitySpike(module, this.unpressedTimings, j + 1));
      }
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
      this.nextClickTimeMs = System.currentTimeMillis();
      this.lastMouseMoveTimeMs = System.currentTimeMillis();
      this.resetTimers();
   }

   @Override
   public void onDisable() {
      ((ClickModuleContext)this.parentModule).setHoldingAttack(false);
   }

   @Override
   public int computeClicks(EventClick event) {
      int n = 0;
      long currentTimeMs = System.currentTimeMillis();

      while (currentTimeMs >= this.nextClickTimeMs) {
         n++;
         TuplePair tuplePair = this.sampleTotalAndPressedDelay();
         int first = (Integer)tuplePair.getFirst();
         this.releaseTimeMs = this.nextClickTimeMs;
         this.releaseTimeMs = this.releaseTimeMs + (long)((Integer)tuplePair.getSecond()).intValue();
         this.nextClickTimeMs += (long)first;
      }

      ClickModuleContext parentModule2 = (ClickModuleContext)this.parentModule;
      parentModule2.setHoldingAttack(this.releaseTimeMs > currentTimeMs);
      n = ((ClickModuleContext)this.parentModule).applyHitReduction(n);
      if (ClientManagers.moduleManager.smartClicking.isSuppressingClicks() || n != n) {
         n = 0;
         parentModule2 = (ClickModuleContext)this.parentModule;
         parentModule2.setHoldingAttack(false);
      }

      return n;
   }

   @Override
   public void onTick() {
      this.targetMouseDelta[0] = this.targetMouseDelta[1] = 0;
      this.appliedMouseDelta[0] = this.appliedMouseDelta[1] = 0;
      this.accumulatedMouseDelta[0] = this.accumulatedMouseDelta[1] = 0.0;
   }

   public TuplePair sampleTotalAndPressedDelay() {
      int n = this.sampleWeightedSpikeDelay(this.pressedSpikes);
      int n2 = this.sampleWeightedSpikeDelay(this.unpressedSpikes);
      return new TuplePair(n + n2, n);
   }

   public TuplePair sampleUnpressedAndPressedDelay() {
      int n = this.sampleWeightedSpikeDelay(this.pressedSpikes);
      int n2 = this.sampleWeightedSpikeDelay(this.unpressedSpikes);
      return new TuplePair(n2, n);
   }

   public void resetTimers() {
      long currentTimeMs = System.currentTimeMillis();
      this.nextClickTimeMs = currentTimeMs;
      this.virtualTickTimeMs = currentTimeMs;
   }

   private int sampleWeightedSpikeDelay(ArrayList list) {
      double d = 0.0;

      for (int i = 0; i < list.size(); i++) {
         ClickDensitySpike clickDensitySpike = (ClickDensitySpike)list.get(i);
         d += clickDensitySpike.getStrength();
      }

      if (!list.isEmpty() && d != 0.0) {
         double d2 = (double)this.secureRandom.nextFloat() * d;

         for (int j = 0; j < list.size(); j++) {
            ClickDensitySpike clickDensitySpike2 = (ClickDensitySpike)list.get(j);
            d2 -= clickDensitySpike2.getStrength();
            if (d2 <= 0.0) {
               return clickDensitySpike2.getTime();
            }
         }

         return ((ClickDensitySpike)list.get(list.size() - 1)).getTime();
      } else {
         return 0;
      }
   }

   public void recalculateSpikes() {

      for (int i = 0; i < this.pressedSpikes.size(); i++) {
         ((ClickDensitySpike)this.pressedSpikes.get(i)).randomize();
      }

      for (int j = 0; j < this.unpressedSpikes.size(); j++) {
         ((ClickDensitySpike)this.unpressedSpikes.get(j)).randomize();
      }
   }

   public void setTickDurationMs(int n) {
      this.tickDurationMs = n;
   }

}
