package net.augustus.modules.modes.clicking;

import net.augustus.ClientManagers;
import net.augustus.events.EventClick;
import net.augustus.events.EventMouseMove;
import net.augustus.events.listeners.MouseMoveListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.modules.combat.helper.ClickModuleContext;
import net.augustus.settings.SettingChangeListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.TuplePair;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.rotation.RotationUtil;
import java.util.function.Consumer;
import net.augustus.modules.Module;
import net.augustus.modules.combat.KillAura;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class RecordedClickPatternMode extends AbstractClickPatternMode implements MouseMoveListener, TickListener {
   private final StringValue recordedClickPattern;
   private final StringValue recordedMode;
   private final DoubleValue multiplier;
   private final DoubleValue recordedAirMultiplier;
   private final BooleanValue playRecordedMouseMovements;
   private final ClickPatternManager clickPatternManager;
   private long nextClickTimeMs;
   private long releaseTimeMs;
   private long virtualTickTimeMs;
   private ClickPattern activePattern;
   private long lastMouseMoveTimeMs;
   private int[] targetMouseDelta;
   private float[] lastPatternRotation;
   private double[] accumulatedMouseDelta;
   private int[] appliedMouseDelta;
   private int tickDurationMs;

   public RecordedClickPatternMode(Module module, SettingGroup doubleValue) {
       super(module, doubleValue, "Recorded");
      this.nextClickTimeMs = 0L;
      this.releaseTimeMs = 0L;
      this.virtualTickTimeMs = 0L;
      this.activePattern = null;
      this.lastMouseMoveTimeMs = 0L;
      this.targetMouseDelta = new int[2];
      this.lastPatternRotation = new float[2];
      this.accumulatedMouseDelta = new double[2];
      this.appliedMouseDelta = new int[2];
      this.tickDurationMs = 0;
      this.recordedClickPattern = (StringValue)((StringValue)new StringValue(module, "RecordedClickPattern", "", new String[0], doubleValue).withDisplayName("ClickPattern")).withVisibility(() -> this.isSelected());
      this.recordedMode = (StringValue)new StringValue(module, "RecordedMode", "TimeMillis", new String[]{"TimeMillis", "Ticks"}, doubleValue).withVisibility(() -> this.isSelected());
      this.multiplier = new DoubleValue(module, "Multiplier", 1.0, 0.0, 5.0, 2, doubleValue).visibleWhen(() -> this.isSelected());
      this.recordedAirMultiplier = new DoubleValue(module, "RecordedAirMultiplier", 0.4, 0.0, 1.0, 2, doubleValue).named("AirMultiplier").visibleWhen(() -> this.isSelected());
      BooleanValue booleanValue = new BooleanValue(module, "PlayRecordedMouseMovements", false, doubleValue);
      SettingVisibilityCondition condition5 = () -> {
         return this.isSelected() && !(this.getParentModule() instanceof KillAura);
      };
      this.playRecordedMouseMovements = (BooleanValue)booleanValue.withVisibility(condition5);
      this.clickPatternManager = ClickPatternManager.builder().withSelectorAndCallback(this.recordedClickPattern, obj -> this.activePattern = (ClickPattern)obj).build();
      this.recordedClickPattern.withChangeListener(() -> this.updateActivePattern());
      this.clickPatternManager.startWatchingAndLoad();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(19);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onEnable() {
      this.nextClickTimeMs = System.currentTimeMillis();
      this.lastMouseMoveTimeMs = System.currentTimeMillis();
      this.resetTiming();
   }

   @Override
   public void onDisable() {
      ((ClickModuleContext)this.parentModule).setHoldingAttack(false);
   }

   @Override
   public int computeClicks(EventClick event) {
      if (this.activePattern == null) {
         return 0;
      } else {
         int n = 0;
         if (!this.recordedMode.getSelectedValue().equals("Ticks")) {
            long currentTimeMs = System.currentTimeMillis();

            while (currentTimeMs >= this.nextClickTimeMs) {
               n++;
               TuplePair tuplePair = this.nextTotalAndPressedDelay();
               int first = (Integer)tuplePair.getFirst();
               this.releaseTimeMs = this.nextClickTimeMs;
               this.releaseTimeMs = this.releaseTimeMs + (long)((Integer)tuplePair.getSecond()).intValue();
               this.nextClickTimeMs += (long)first;
            }

            ((ClickModuleContext)this.parentModule).setHoldingAttack(this.releaseTimeMs > currentTimeMs);
         } else {
            while (this.virtualTickTimeMs >= this.nextClickTimeMs) {
               n++;
               TuplePair tuplePair2 = this.nextTotalAndPressedDelay();
               int first2 = (Integer)tuplePair2.getFirst();
               this.releaseTimeMs = this.nextClickTimeMs;
               this.releaseTimeMs = this.releaseTimeMs + (long)((Integer)tuplePair2.getSecond()).intValue();
               this.nextClickTimeMs += (long)first2;
            }

            ((ClickModuleContext)this.parentModule).setHoldingAttack(this.releaseTimeMs > this.virtualTickTimeMs);
            this.virtualTickTimeMs += 50L;
         }

         this.activePattern.advanceRotationIndex();
         float[] currentRotation = this.activePattern.getCurrentRotation();
         this.targetMouseDelta = RotationUtil.computeMouseDeltas(currentRotation[0], currentRotation[1], this.lastPatternRotation[0], this.lastPatternRotation[1]);
         this.lastPatternRotation[0] = currentRotation[0];
         this.lastPatternRotation[1] = currentRotation[1];
         n = ((ClickModuleContext)this.parentModule).applyHitReduction(n);
         if (ClientManagers.moduleManager.smartClicking.isSuppressingClicks() || n != n) {
            n = 0;
            ((ClickModuleContext)this.parentModule).setHoldingAttack(false);
         }

         return n;
      }
   }

   @Override
   public void onTick() {
   }

   @Override
   public void onPreTick() {
      this.targetMouseDelta[0] = this.targetMouseDelta[1] = 0;
      this.appliedMouseDelta[0] = this.appliedMouseDelta[1] = 0;
      this.accumulatedMouseDelta[0] = this.accumulatedMouseDelta[1] = 0.0;
   }

   @Override
   public void onMouseMove(EventMouseMove event) {
      if (this.isEnabled() && this.playRecordedMouseMovements.getValue()) {
         int n = (int)(System.currentTimeMillis() - this.lastMouseMoveTimeMs);
         double d = (double)this.targetMouseDelta[0] / (double)this.tickDurationMs * (double)n;
         double d2 = (double)this.targetMouseDelta[1] / (double)this.tickDurationMs * (double)n;
         this.accumulatedMouseDelta[0] = this.accumulatedMouseDelta[0] + d;
         this.accumulatedMouseDelta[1] = this.accumulatedMouseDelta[1] + d2;
         int n2 = (int)Math.round(this.accumulatedMouseDelta[0] - (double)this.appliedMouseDelta[0]);
         int n3 = (int)Math.round(this.accumulatedMouseDelta[1] - (double)this.appliedMouseDelta[1]);
         this.appliedMouseDelta[0] = this.appliedMouseDelta[0] + n2;
         this.appliedMouseDelta[1] = this.appliedMouseDelta[1] + n3;
         event.setDeltaX(event.getDeltaX() + n2);
         event.setDeltaY(event.getDeltaY() + n3);
         this.lastMouseMoveTimeMs = System.currentTimeMillis();
      }
   }

   private TuplePair nextTotalAndPressedDelay() {
      int n = this.activePattern.nextDelay();
      int n2 = this.activePattern.nextDelay();
      if (this.isNotLookingAtEntity()) {
         n = (int)((double)n / this.recordedAirMultiplier.getValue());
         n2 = (int)((double)n2 / this.recordedAirMultiplier.getValue());
      }

      n = (int)Math.min((double)n * (1.0 / this.multiplier.getValue()), 1000.0);
      n2 = (int)Math.min((double)n2 * (1.0 / this.multiplier.getValue()), 1000.0);
      return new TuplePair(n + n2, n);
   }

   public void resetTiming() {
      long currentTimeMs = System.currentTimeMillis();
      this.nextClickTimeMs = currentTimeMs - (long)DefaultRandomGenerator.instance.nextInt((byte)0, (byte)45);
      this.virtualTickTimeMs = currentTimeMs;
   }

   @Override
   public boolean simulatesMousePress() {
      return true;
   }

   public void setTickDurationMs(int n) {
      this.tickDurationMs = n;
   }

   public ClickPattern getActivePattern() {
      return this.activePattern;
   }

   private void updateActivePattern() {
      this.activePattern = this.clickPatternManager.getPattern(this.recordedClickPattern.getSelectedValue());
   }

}
