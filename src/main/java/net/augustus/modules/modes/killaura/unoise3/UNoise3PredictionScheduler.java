package net.augustus.modules.modes.killaura.unoise3;

import net.augustus.settings.RandomizedRangeSettings;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;

public class UNoise3PredictionScheduler {
   private UNoise3SettingContext uNoise3SettingContext;
   private int targetFailTicks;
   public UNoise3MovementFilter uNoise3MovementFilter;
   public int lookaheadTicks;
   private RandomizedRangeSettings uNoise3targetpredictionfailticks;
   public boolean forceRecompute;
   private RandomizedRangeSettings uNoise3inputticksoffset;
   private int inputKnowledgeOffset;
   private int reactionDelayTicks;
   private RandomizedRangeSettings uNoise3lookahead;
   private RandomizedRangeSettings uNoise3playerpredictionfailticks;
   private RandomizedRangeSettings uNoise3timeoffset;
   private int inputOffset;
   private RandomizedRangeSettings uNoise3reactiontime;
   private int playerFailTicks;
   public UNoise3PredictionEntry[] yawPredictions;
   private RandomizedRangeSettings uNoise3predictionfailticks;
   public UNoise3PredictionEntry[] pitchPredictions;
   public int baseSnapshotTick;
   private RandomizedRangeSettings uNoise3inputoffset;
   private int predictionFailTicks;
   public int lookaheadEndTick;

   public UNoise3PredictionScheduler(UNoise3SettingContext uNoise3SettingContext2) {
       super();
      this.forceRecompute = false;
      this.uNoise3SettingContext = uNoise3SettingContext2;
      this.uNoise3MovementFilter = new UNoise3MovementFilter(uNoise3SettingContext2);
      RandomizedRangeSettings randomizedRangeSettings = new RandomizedRangeSettings(uNoise3SettingContext2.getModule(), "UNoise3reactiontime", 3.0, 5.0, 0.0, 8.0, 0, uNoise3SettingContext2.getParentGroup());
      this.uNoise3reactiontime = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Reactiotime");
      randomizedRangeSettings = new RandomizedRangeSettings(uNoise3SettingContext2.getModule(), "UNoise3timeoffset", 0.0, 0.0, 0.0, 3.0, 0, uNoise3SettingContext2.getParentGroup());
      this.uNoise3timeoffset = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Time offset");
      randomizedRangeSettings = new RandomizedRangeSettings(uNoise3SettingContext2.getModule(), "UNoise3predictionfailticks", 0.0, 0.0, -3.0, 3.0, 0, uNoise3SettingContext2.getParentGroup());
      this.uNoise3predictionfailticks = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Prediction Fail Ticks");
      randomizedRangeSettings = new RandomizedRangeSettings(uNoise3SettingContext2.getModule(), "UNoise3playerpredictionfailticks", 0.0, 0.0, -3.0, 3.0, 0, uNoise3SettingContext2.getParentGroup());
      this.uNoise3playerpredictionfailticks = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Player Prediction Fail Ticks");
      randomizedRangeSettings = new RandomizedRangeSettings(uNoise3SettingContext2.getModule(), "UNoise3targetpredictionfailticks", 0.0, 0.0, -3.0, 3.0, 0, uNoise3SettingContext2.getParentGroup());
      this.uNoise3targetpredictionfailticks = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Target Prediction Fail Ticks");
      randomizedRangeSettings = new RandomizedRangeSettings(uNoise3SettingContext2.getModule(), "UNoise3inputticksoffset", 0.0, 0.0, 0.0, 3.0, 0, uNoise3SettingContext2.getParentGroup());
      this.uNoise3inputticksoffset = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Input Knowledge Offset");
      randomizedRangeSettings = new RandomizedRangeSettings(uNoise3SettingContext2.getModule(), "UNoise3inputoffset", 0.0, 0.0, -3.0, 3.0, 0, uNoise3SettingContext2.getParentGroup());
      this.uNoise3inputoffset = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Input Offset");
      randomizedRangeSettings = new RandomizedRangeSettings(uNoise3SettingContext2.getModule(), "UNoise3lookahead", 0.0, 0.0, 0.0, 3.0, 0, uNoise3SettingContext2.getParentGroup());
      this.uNoise3lookahead = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Lookahead Time");
   }

   public void updatePredictions(EntityPlayerSP player, EntityLivingBase entity) {
      if (player.ticksExisted - this.baseSnapshotTick >= this.reactionDelayTicks || this.lookaheadTicks > 0 && this.lookaheadEndTick <= player.ticksExisted || this.forceRecompute) {
         this.baseSnapshotTick = player.ticksExisted - this.uNoise3timeoffset.nextInt(true);
         this.reactionDelayTicks = this.uNoise3reactiontime.nextInt(true);
         this.predictionFailTicks = this.uNoise3predictionfailticks.nextInt(true);
         this.playerFailTicks = this.uNoise3playerpredictionfailticks.nextInt(true);
         this.targetFailTicks = this.uNoise3targetpredictionfailticks.nextInt(true);
         this.inputKnowledgeOffset = this.uNoise3inputticksoffset.nextInt(true);
         this.inputOffset = this.uNoise3inputoffset.nextInt(true);
         this.lookaheadTicks = this.uNoise3lookahead.nextInt(true);
         if (this.lookaheadTicks > 0) {
            this.lookaheadEndTick = this.lookaheadTicks + player.ticksExisted;
         } else {
            this.lookaheadEndTick = 0;
         }

         this.forceRecompute = false;
      }

      int n = player.ticksExisted + this.predictionFailTicks;
      if (this.lookaheadTicks > 0) {
         n = this.lookaheadEndTick;
      }

      int n2 = player.ticksExisted - this.inputKnowledgeOffset;
      int inputOffset2 = this.inputOffset;
      int baseSnapshotTick2 = this.baseSnapshotTick;
      this.yawPredictions = this.uNoise3MovementFilter.predictYawEntries(player, entity, baseSnapshotTick2, n2, inputOffset2, n);
      int n4 = player.ticksExisted - this.inputKnowledgeOffset;
      int inputOffset3 = this.inputOffset;
      int baseSnapshotTick3 = this.baseSnapshotTick;
      this.pitchPredictions = this.uNoise3MovementFilter.predictPitchEntries(player, entity, baseSnapshotTick3, n4, inputOffset3, n);
      this.predictionFailTicks = this.decrementTowardZero(this.predictionFailTicks);
      this.playerFailTicks = this.decrementTowardZero(this.playerFailTicks);
      this.targetFailTicks = this.decrementTowardZero(this.targetFailTicks);
   }

   private int decrementTowardZero(int n) {
      return (int)(Math.signum((float)n) * (float)(Math.abs(n) - 1));
   }

   public void resetSnapshotTick(int n) {
      this.baseSnapshotTick = n - this.uNoise3timeoffset.nextInt(true);
   }

}
