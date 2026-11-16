package client.setting;

import client.util.BooleanSupplier;

public class BooleanSettingOption {
   public BooleanSetting settingEnableSilence;
   public RangeSetting settingSilenceStrength;
   public RangeSetting settingLoudInterval;
   public boolean enabled;
   public int count;
   public BooleanSetting settingSynchronizeSilence;
   public RangeSetting settingSilenceDecreaseFactor;
   public BooleanSetting settingKeepTicking;
   public RangeSetting settingSilentIntervalLength;
   public double value;
   public BooleanSetting settingBeSilentEvenIfNotOnTarget;

   public double computeValue(double offset) {
      if (this.settingEnableSilence != null && !this.settingEnableSilence.isEnabled()) {
         return 0.0;
      } else if (this.settingEnableSilence != null && this.settingSynchronizeSilence.isEnabled()) {
         return offset;
      } else {
         if (this.count > 0) {
            this.count--;
         }

         if (this.count == 0) {
            this.enabled = !this.enabled;
            boolean flag = false;
            if (this.enabled) {
               flag = true;
            } else {
               this.count = this.settingLoudInterval.getInt5(true);
               if (this.count == 0) {
                  flag = true;
                  this.enabled = true;
               }
            }

            if (flag) {
               this.count = this.settingSilentIntervalLength.getInt5(true);
               this.value = this.settingSilenceDecreaseFactor.computeValue(true);
               this.settingSilenceStrength.computeValue(true);
            }
         }

         return !this.enabled
            ? 0.0
            : (1.0 - (double)this.count / (double)this.settingSilentIntervalLength.getIndexOffset2() * this.value) * this.settingSilenceStrength.getDouble3();
      }
   }

   public void updateState() {
      double offset = 0.0;
      this.settingSilenceStrength.processAction(offset);
      this.count = this.settingLoudInterval.getInt5(true);
      if (this.count <= 1) {
         this.count = 2;
      }
   }

   private boolean checkFlag(boolean flag) {
      return flag || this.settingEnableSilence.isEnabled() && !this.settingSynchronizeSilence.isEnabled();
   }

   public boolean checkFlagByFlag(boolean flag) {
      return this.settingSynchronizeSilence != null && this.settingSynchronizeSilence.isEnabled() ? flag : this.settingBeSilentEvenIfNotOnTarget.isEnabled();
   }

   public boolean checkFlag2(boolean flag) {
      return this.settingSynchronizeSilence != null && this.settingSynchronizeSilence.isEnabled() ? flag : this.settingKeepTicking.isEnabled();
   }

   public BooleanSettingOption(BooleanSettingProperty booleanSettingProperty, boolean flag) {
      super();
      SettingGroup settingGroup = new SettingGroup(booleanSettingProperty.getModule(), booleanSettingProperty.getSettingGroup().getString() + "_silence", booleanSettingProperty.getSettingGroup());
      String text = "Silence";
      SettingGroup setting = (SettingGroup)settingGroup.getSetting(text);
      if (!flag) {
         BooleanSetting setting2 = new BooleanSetting(booleanSettingProperty.getModule(), booleanSettingProperty.getSettingGroup().getString() + "_allowforsilence", false, setting);
         String text2 = "Enable Silence";
         this.settingEnableSilence = (BooleanSetting)setting2.getSetting(text2);
         setting2 = new BooleanSetting(booleanSettingProperty.getModule(), booleanSettingProperty.getSettingGroup().getString() + "_synchronizesilence", true, setting);
         String text3 = "Synchronize Silence";
         setting2 = (BooleanSetting)setting2.getSetting(text3);
         BooleanSupplier booleanSupplier = this.settingEnableSilence::isEnabled;
         this.settingSynchronizeSilence = (BooleanSetting)setting2.getSetting(booleanSupplier);
      }

      BooleanSupplier booleanSupplier2 = () -> this.checkFlag(flag);
      RangeSetting setting3 = new RangeSetting(booleanSettingProperty.getModule(), booleanSettingProperty.getSettingGroup().getString() + "_silentinterval", 1.0, 1.0, 1.0, 20.0, 0, setting);
      String text4 = "Silent Interval Length";
      this.settingSilentIntervalLength = setting3.getRangeSetting(text4).getRangeSetting(15343, booleanSupplier2);
      setting3 = (new RangeSetting(booleanSettingProperty.getModule(), booleanSettingProperty.getSettingGroup().getString() + "_nonsilentinterval", 3.0, 5.0, 0.0, 40.0, 0, setting))
         .getRangeSetting(15343, booleanSupplier2);
      String text5 = "Loud interval";
      this.settingLoudInterval = setting3.getRangeSetting(text5);
      setting3 = new RangeSetting(booleanSettingProperty.getModule(), booleanSettingProperty.getSettingGroup().getString() + "_silencestrength", 1.0, 1.0, 0.0, 1.0, 2, setting);
      String text6 = "Silence Strength";
      this.settingSilenceStrength = setting3.getRangeSetting(text6).getRangeSetting(15343, booleanSupplier2).getRangeSetting2();
      setting3 = new RangeSetting(booleanSettingProperty.getModule(), booleanSettingProperty.getSettingGroup().getString() + "_silencedecreasefactor", 0.0, 0.0, 0.0, 1.0, 2, setting);
      String text7 = "Silence Decrease Factor";
      this.settingSilenceDecreaseFactor = setting3.getRangeSetting(text7).getRangeSetting(15343, booleanSupplier2).getRangeSetting2();
      BooleanSetting setting4 = new BooleanSetting(booleanSettingProperty.getModule(), booleanSettingProperty.getSettingGroup().getString() + "_keepticking", true, setting);
      String text8 = "Keep Ticking";
      this.settingKeepTicking = (BooleanSetting)((BooleanSetting)setting4.getSetting(text8)).getSetting(booleanSupplier2);
      BooleanSetting setting5 = new BooleanSetting(booleanSettingProperty.getModule(), booleanSettingProperty.getSettingGroup().getString() + "_shutup", false, setting);
      String text9 = "Be silent even if not on target";
      this.settingBeSilentEvenIfNotOnTarget = (BooleanSetting)((BooleanSetting)setting5.getSetting(text9)).getSetting(booleanSupplier2);
   }
}
