package client.setting;

import client.util.GenericDoubleEntry;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;

public class RangeSettingEntry {
   public int count7;
   public int count4;
   public RangeSetting settingInputOffset;
   public BooleanSettingProperty booleanSettingProperty;
   public int count9;
   public RangeSetting settingPredictionFailTicks;
   public int count3;
   public RangeSetting settingPlayerPredictionFailTicks;
   public boolean enabled2 = false;
   public RangeSetting settingInputKnowledgeOffset;
   public RangeSetting settingTimeOffset;
   public RangeSetting settingLookaheadTime;
   public int count2;
   public GenericDoubleEntry[] genericDoubleEntryArray;
   public GenericDoubleEntry[] genericDoubleEntryArray2;
   public int count5;
   public int count8;
   public NumberSettingEntry numberSettingEntry;
   public int count;
   public RangeSetting settingTargetPredictionFailTicks;
   public int count6;
   public RangeSetting settingReactiotime;

   public void applyEntityPlayerSPByEntityPlayerSP(EntityPlayerSP player, EntityLivingBase livingEntity) {
      if (player.ticksExisted - this.count8 >= this.count || this.count7 > 0 && this.count9 <= player.ticksExisted || this.enabled2) {
         this.count8 = player.ticksExisted - this.settingTimeOffset.getInt5(true);
         this.count = this.settingReactiotime.getInt5(true);
         this.count3 = this.settingPredictionFailTicks.getInt5(true);
         this.count4 = this.settingPlayerPredictionFailTicks.getInt5(true);
         this.count5 = this.settingTargetPredictionFailTicks.getInt5(true);
         this.count2 = this.settingInputKnowledgeOffset.getInt5(true);
         this.count6 = this.settingInputOffset.getInt5(true);
         this.count7 = this.settingLookaheadTime.getInt5(true);
         if (this.count7 > 0) {
            this.count9 = this.count7 + player.ticksExisted;
         } else {
            this.count9 = 0;
         }

         this.enabled2 = false;
      }

      int count10 = player.ticksExisted + this.count3;
      if (this.count7 > 0) {
         count10 = this.count9;
      }

      int count11 = player.ticksExisted - this.count2;
      int count12 = this.count6;
      int count13 = count11;
      int count14 = this.count8;
      this.genericDoubleEntryArray2 = this.numberSettingEntry.resolveGenericDoubleEntryArray(player, livingEntity, count14, count13, count12, count10);
      int count15 = player.ticksExisted - this.count2;
      int count16 = this.count6;
      int count17 = count15;
      int count18 = this.count8;
      this.genericDoubleEntryArray = this.numberSettingEntry.resolveGenericDoubleEntryArrayByEntityPlayerSP(player, livingEntity, count18, count17, count16, count10);
      int count19 = this.count3;
      this.count3 = this.computeCount(count19);
      int count20 = this.count4;
      this.count4 = this.computeCount(count20);
      int count21 = this.count5;
      this.count5 = this.computeCount(count21);
   }

   public void applyIndex(int count10) {
      this.count8 = count10 - this.settingTimeOffset.getInt5(true);
   }

   private int computeCount(int count10) {
      return (int)(Math.signum((float)count10) * (float)(Math.abs(count10) - 1));
   }

   public RangeSettingEntry(BooleanSettingProperty booleanSettingProperty2) {
      this.booleanSettingProperty = booleanSettingProperty2;
      this.numberSettingEntry = new NumberSettingEntry(booleanSettingProperty2);
      RangeSetting setting = new RangeSetting(booleanSettingProperty2.getModule(), "UNoise3reactiontime", 3.0, 5.0, 0.0, 8.0, 0, booleanSettingProperty2.getSettingGroup());
      String text = "Reactiotime";
      this.settingReactiotime = setting.getRangeSetting(text);
      setting = new RangeSetting(booleanSettingProperty2.getModule(), "UNoise3timeoffset", 0.0, 0.0, 0.0, 3.0, 0, booleanSettingProperty2.getSettingGroup());
      String text2 = "Time offset";
      this.settingTimeOffset = setting.getRangeSetting(text2);
      setting = new RangeSetting(booleanSettingProperty2.getModule(), "UNoise3predictionfailticks", 0.0, 0.0, -3.0, 3.0, 0, booleanSettingProperty2.getSettingGroup());
      String text3 = "Prediction Fail Ticks";
      this.settingPredictionFailTicks = setting.getRangeSetting(text3);
      setting = new RangeSetting(booleanSettingProperty2.getModule(), "UNoise3playerpredictionfailticks", 0.0, 0.0, -3.0, 3.0, 0, booleanSettingProperty2.getSettingGroup());
      String text4 = "Player Prediction Fail Ticks";
      this.settingPlayerPredictionFailTicks = setting.getRangeSetting(text4);
      setting = new RangeSetting(booleanSettingProperty2.getModule(), "UNoise3targetpredictionfailticks", 0.0, 0.0, -3.0, 3.0, 0, booleanSettingProperty2.getSettingGroup());
      String text5 = "Target Prediction Fail Ticks";
      this.settingTargetPredictionFailTicks = setting.getRangeSetting(text5);
      setting = new RangeSetting(booleanSettingProperty2.getModule(), "UNoise3inputticksoffset", 0.0, 0.0, 0.0, 3.0, 0, booleanSettingProperty2.getSettingGroup());
      String text6 = "Input Knowledge Offset";
      this.settingInputKnowledgeOffset = setting.getRangeSetting(text6);
      setting = new RangeSetting(booleanSettingProperty2.getModule(), "UNoise3inputoffset", 0.0, 0.0, -3.0, 3.0, 0, booleanSettingProperty2.getSettingGroup());
      String text7 = "Input Offset";
      this.settingInputOffset = setting.getRangeSetting(text7);
      setting = new RangeSetting(booleanSettingProperty2.getModule(), "UNoise3lookahead", 0.0, 0.0, 0.0, 3.0, 0, booleanSettingProperty2.getSettingGroup());
      String text8 = "Lookahead Time";
      this.settingLookaheadTime = setting.getRangeSetting(text8);
   }
}
