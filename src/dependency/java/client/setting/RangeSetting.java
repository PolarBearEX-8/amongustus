package client.setting;

import client.ClientGlobals;
import client.event.SupplementalListener;
import client.module.Module;
import client.util.BooleanSupplier;
import client.util.ConsumerTask;
import client.util.PlayerUtil;
import com.google.gson.JsonObject;
import java.util.Random;
import net.minecraft.util.MathHelper;

public class RangeSetting extends Setting {
   public boolean enabled2;
   public BooleanSetting booleanSetting2;
   public NumberSetting numberSetting3;
   public ActionSetting actionSetting3;
   public final Random random;
   public NumberSetting numberSetting;
   public BooleanSetting settingNotInARow;
   public BooleanSetting booleanSetting;
   public int count12;
   public NumberSetting numberSetting2;
   public int count10;
   public double value;
   public int indexOffset2;
   public int count11;
   public SettingGroup settingGroup;
   public ActionSetting actionSetting;
   public ActionSetting actionSetting2;
   public int count13;

   public int getInt2() {
      return this.getInt5(false);
   }

   private void updateState7() {
      this.count12 = 0;
      this.count13 = SettingContainer.settingProvider.getInt(this.actionSetting.getDouble3(), this.actionSetting.getDouble6());
   }

   public double getValueAsDouble() {
      double offset = this.computeValue(this.value, this.actionSetting3, true, false);
      if (this.isActive2()) {
         double lowerSpan = Math.max(0.0, this.actionSetting3.getDouble3() - this.actionSetting2.getDouble3());
         double upperSpan = Math.max(0.0, this.actionSetting2.getDouble6() - this.actionSetting3.getDouble6());
         double totalSpan = lowerSpan + upperSpan;
         if (totalSpan > 0.0) {
            double pick;
            if (lowerSpan == 0.0) {
               double range = this.actionSetting2.getDouble6() - Math.max(this.actionSetting2.getDouble3(), this.actionSetting3.getDouble6());
               pick = SettingContainer.settingProvider.computeValueByDouble(0.0, Math.max(0.0, range));
            } else if (upperSpan == 0.0) {
               double range = Math.min(this.actionSetting2.getDouble6(), this.actionSetting3.getDouble3()) - this.actionSetting2.getDouble3();
               pick = SettingContainer.settingProvider.computeValueByDouble(0.0, Math.max(0.0, range));
            } else {
               pick = SettingContainer.settingProvider.computeValueByDouble(0.0, totalSpan);
            }

            if (pick < lowerSpan) {
               offset = this.actionSetting2.getDouble3() + pick;
            } else {
               offset = this.actionSetting2.getDouble6() - pick;
            }
         }
      }

      this.value = offset;
      return offset;
   }

   private boolean isActiveAsFlag() {
      return this.settingNotInARow.isEnabled() && this.settingNotInARow.isVisible();
   }

   private double computeValue(ActionSetting setting, boolean flag) {
      if (this.booleanSetting != null && this.booleanSetting.isEnabled()) {
         return MathHelper.getDouble7(this.computeValue(setting), setting.getDouble3(), setting.getDouble6());
      } else if (flag) {
         return SettingContainer.settingProvider.computeValueByDouble(setting.getDouble3(), setting.getDouble6());
      } else {
         return SettingContainer.settingProvider.getDouble4(setting.getDouble3(), setting.getDouble6());
      }
   }

   public RangeSetting getRangeSetting(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      return this.resolveRangeSettingByDouble(posX, posX2, posX3, posX4, posY, posZ, true);
   }

   public RangeSetting getRangeSetting(String text) {
      this.actionSetting3 = this.actionSetting3.getActionSetting("Min" + text, "Max" + text);
      return this;
   }

   private double computeValue(ActionSetting setting) {
      for (int index = 0; index < 50; index++) {
         double sample = this.random.nextGaussian() * this.numberSetting2.getValue()
            + PlayerUtil.getDouble(setting.getDouble3(), setting.getDouble6(), this.numberSetting3.getValue() / 100.0);
         if ((double)((int)sample) <= setting.getDouble6() && sample >= setting.getDouble3()) {
            return sample;
         }
      }

      return PlayerUtil.getDouble(setting.getDouble3(), setting.getDouble6(), Math.random());
   }

   public double getDouble2() {
      return this.actionSetting3.getDouble6();
   }

   public RangeSetting getRangeSetting2() {
      if (this.settingGroup != null) {
         this.settingGroup.getItemList3().remove(this.settingNotInARow);
      }

      ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.getList().remove(this.settingNotInARow);
      this.getModule().getSettings().remove(this.settingNotInARow);
      BooleanSupplier hiddenSupplier = RangeSetting::isActive6;
      this.settingNotInARow.getSetting(hiddenSupplier);
      this.enabled2 = true;
      return this;
   }

   private boolean isActive() {
      return this.isVisible() && this.booleanSetting.isEnabled();
   }

   private int computeCount(int count, ActionSetting setting, boolean flag) {
      return this.computeCount(count, setting, flag, false);
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return null;
   }

   public double getDouble3() {
      return this.count10 == 0 ? (double)this.indexOffset2 : this.value;
   }

   public double getDouble4() {
      return this.count10 == 0 ? (double)this.getCount() : this.getValueAsDouble();
   }

   @Override
   public void updateState() {
      this.actionSetting3.updateState();
      this.settingNotInARow.updateState();
      if (this.numberSetting != null) {
         this.numberSetting.updateState();
      }

      if (this.booleanSetting2 != null) {
         this.booleanSetting2.updateState();
      }

      if (this.actionSetting2 != null) {
         this.actionSetting2.updateState();
      }

      if (this.actionSetting != null) {
         this.actionSetting.updateState();
      }

      if (this.actionSetting2 != null) {
         this.actionSetting2.updateState();
      }

      if (this.booleanSetting != null) {
         this.booleanSetting.updateState();
      }
   }

   @Override
   public Setting getSetting(String text) {
      return this.getRangeSetting(text);
   }

   public int getCount() {
      int count = this.computeCount(this.indexOffset2, this.actionSetting3, true);
      if (this.isActive2()) {
         int lowerSpan = Math.max(0, (int)(this.actionSetting3.getDouble3() - this.actionSetting2.getDouble3()));
         int upperSpan = Math.max(0, (int)(this.actionSetting2.getDouble6() - this.actionSetting3.getDouble6()));
         int totalSpan = lowerSpan + upperSpan;
         if (totalSpan > 0) {
            int pick;
            if (lowerSpan == 0) {
               int range = (int)((double)((int)this.actionSetting2.getDouble6()) - Math.max(this.actionSetting2.getDouble3(), this.actionSetting3.getDouble6()));
               pick = SettingContainer.settingProvider.getInt3(0, Math.max(range, 0)) + 1;
            } else if (upperSpan == 0) {
               double clamped = Math.min(this.actionSetting2.getDouble6(), this.actionSetting3.getDouble3());
               int range = (int)(clamped - this.actionSetting2.getDouble3());
               pick = SettingContainer.settingProvider.getInt3(0, Math.max(range, 0));
            } else {
               pick = SettingContainer.settingProvider.getInt3(0, totalSpan - 1);
            }

            if (pick < lowerSpan) {
               count = (int)(this.actionSetting2.getDouble3() + (double)pick);
            } else {
               count = (int)(this.actionSetting2.getDouble6() - (double)(pick - 1));
            }
         }
      }

      this.indexOffset2 = count;
      return count;
   }

   private static boolean isActive6() {
      return false;
   }

   private boolean isActive7() {
      return this.booleanSetting2.isEnabled() && this.isVisible();
   }

   public double getDouble5(boolean flag) {
      double offset = this.computeValue(this.value, this.actionSetting3, false, flag);
      if (this.isActive2()) {
         double lowerSpan = Math.max(0.0, this.actionSetting3.getDouble3() - this.actionSetting2.getDouble3());
         double upperSpan = Math.max(0.0, this.actionSetting2.getDouble6() - this.actionSetting3.getDouble6());
         double totalSpan = lowerSpan + upperSpan;
         if (totalSpan > 0.0) {
            double pick;
            if (lowerSpan == 0.0) {
               double range = this.actionSetting2.getDouble4(flag) - Math.max(this.actionSetting2.getDouble5(flag), this.actionSetting3.getDouble4(flag));
               pick = SettingContainer.settingProvider.getDouble4(0.0, Math.max(0.0, range));
            } else if (upperSpan == 0.0) {
               double range = Math.min(this.actionSetting2.getDouble4(flag), this.actionSetting3.getDouble5(flag)) - this.actionSetting2.getDouble5(flag);
               pick = SettingContainer.settingProvider.getDouble4(0.0, Math.max(0.0, range));
            } else {
               pick = SettingContainer.settingProvider.getDouble4(0.0, totalSpan);
            }

            if (pick < lowerSpan) {
               offset = this.actionSetting2.getDouble5(flag) + pick;
            } else {
               offset = this.actionSetting2.getDouble4(flag) - pick;
            }
         }
      }

      this.value = offset;
      return offset;
   }

   public int getIndexOffset2() {
      return this.indexOffset2;
   }

   public double computeValue(boolean flag) {
      return this.count10 == 0 ? (double)this.getInt5(flag) : this.getDouble5(flag);
   }

   public double getValue2() {
      return this.getDouble5(false);
   }

   public RangeSetting getRangeSetting(int count, BooleanSupplier booleanSupplier) {
      this.setBooleanSupplier(booleanSupplier);
      return this;
   }

   private int computeCount(ActionSetting setting, boolean flag, boolean flag2) {
      if (this.booleanSetting != null && this.booleanSetting.isEnabled()) {
         return MathHelper.getInt12((int)Math.round(this.computeValue(setting)), (int)setting.getDouble3(), (int)setting.getDouble6());
      } else if (flag) {
         return (int)SettingContainer.settingProvider.computeValue(setting.getDouble5(flag2), setting.getDouble4(flag2));
      } else {
         return SettingContainer.settingProvider.getInt(setting.getDouble5(flag2), setting.getDouble4(flag2));
      }
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
   }

   private boolean isActive3() {
      return this.booleanSetting2.isEnabled() && this.isVisible();
   }

   public RangeSetting resolveRangeSettingByDouble(double posX, double posX2, double posX3, double posX4, double posY, double posZ, boolean flag) {
      BooleanSetting outlierToggle;
      if (this.settingGroup == null) {
         outlierToggle = new BooleanSetting(this.getModule(), this.getString() + "Outliers", flag);
         outlierToggle = (BooleanSetting)outlierToggle.getSetting("Outliers");
         BooleanSupplier visibleSupplier = this::isVisible;
         outlierToggle = (BooleanSetting)outlierToggle.getSetting(visibleSupplier);
      } else {
         outlierToggle = new BooleanSetting(this.getModule(), this.getString() + "Outliers", flag, this.settingGroup);
         outlierToggle = (BooleanSetting)outlierToggle.getSetting("Outliers");
         BooleanSupplier visibleSupplier = this::isVisible;
         outlierToggle = (BooleanSetting)outlierToggle.getSetting(visibleSupplier);
      }

      this.booleanSetting2 = outlierToggle;
      ActionSetting setting;
      if (this.settingGroup == null) {
         setting = new ActionSetting(this.getModule(), this.getString() + "OutlierValue", posX, posX2, posX3, posX4, this.count10);
         setting = setting.getActionSetting("MinOutlierValue", "MaxOutlierValue");
         BooleanSupplier booleanSupplier = this::isActive5;
         setting = setting.getActionSetting(booleanSupplier);
      } else {
         setting = new ActionSetting(this.getModule(), this.getString() + "OutlierValue", posX, posX2, posX3, posX4, this.count10, this.settingGroup);
         BooleanSupplier booleanSupplier2 = this::isActive4;
         setting = setting.getActionSetting(booleanSupplier2);
      }

      this.actionSetting2 = setting;
      if (this.settingGroup == null) {
         setting = new ActionSetting(this.getModule(), this.getString() + "OutlierDelay", posY, posZ, 0.0, 100.0, 0);
         setting = setting.getActionSetting("MinOutlierDelay", "MaxOutlierDelay");
         BooleanSupplier booleanSupplier3 = this::isActive7;
         setting = setting.getActionSetting(booleanSupplier3);
      } else {
         setting = new ActionSetting(this.getModule(), this.getString() + "OutlierDelay", posY, posZ, 0.0, 100.0, 0, this.settingGroup);
         setting = setting.getActionSetting("MinOutlierDelay", "MaxOutlierDelay");
         BooleanSupplier booleanSupplier4 = this::isActive3;
         setting = setting.getActionSetting(booleanSupplier4);
      }

      this.actionSetting = setting;
      Runnable task = this::updateState7;
      this.actionSetting2.withRunnable(task);
      Runnable task2 = this::updateState7;
      this.actionSetting.withRunnable(task2);
      this.updateState7();
      return this;
   }

   public double getValue() {
      return this.getDouble9() + (this.getDouble2() - this.getDouble9()) / 2.0;
   }

   public RangeSetting getRangeSetting4(double posX, double posY, double posZ, boolean flag) {
      BooleanSetting normalToggle;
      if (this.settingGroup == null) {
         normalToggle = new BooleanSetting(this.getModule(), this.getString() + "NormalDistribution", flag);
         normalToggle = (BooleanSetting)normalToggle.getSetting("Normal Distribution");
         BooleanSupplier visibleSupplier = this::isVisible;
         normalToggle = (BooleanSetting)normalToggle.getSetting(visibleSupplier);
      } else {
         normalToggle = new BooleanSetting(this.getModule(), this.getString() + "NormalDistribution", flag, this.settingGroup);
         normalToggle = (BooleanSetting)normalToggle.getSetting("Normal Distribution");
         BooleanSupplier visibleSupplier = this::isVisible;
         normalToggle = (BooleanSetting)normalToggle.getSetting(visibleSupplier);
      }

      this.booleanSetting = normalToggle;
      NumberSetting setting;
      if (this.settingGroup == null) {
         setting = new NumberSetting(this.getModule(), this.getString() + "Sigma", posX, posY, posZ, 2);
         setting = setting.getNumberSetting("Sigma");
         BooleanSupplier booleanSupplier = this::isActive8;
         setting = setting.getNumberSetting(booleanSupplier);
      } else {
         setting = new NumberSetting(this.getModule(), this.getString() + "Sigma", posX, posY, posZ, 2, this.settingGroup);
         setting = setting.getNumberSetting("Sigma");
         BooleanSupplier booleanSupplier2 = this::isActive;
         setting = setting.getNumberSetting(booleanSupplier2);
      }

      this.numberSetting2 = setting;
      if (this.settingGroup == null) {
         setting = new NumberSetting(this.getModule(), this.getString() + "Center", 50.0, 0.0, 100.0, 0);
         setting = setting.getNumberSetting("Center");
         BooleanSupplier booleanSupplier3 = this::isActive11;
         setting = setting.getNumberSetting(booleanSupplier3);
      } else {
         setting = new NumberSetting(this.getModule(), this.getString() + "Center", 50.0, 0.0, 100.0, 0, this.settingGroup);
         setting = setting.getNumberSetting("Center");
         BooleanSupplier booleanSupplier4 = this::isActive10;
         setting = setting.getNumberSetting(booleanSupplier4);
      }

      this.numberSetting3 = setting;
      return this;
   }

   private boolean isActive2() {
      boolean active = this.booleanSetting2 != null && this.booleanSetting2.isEnabled() && this.count12 > this.count13;
      if (active) {
         this.updateState7();
      }

      this.count12++;
      return active;
   }

   public double getDouble9() {
      return this.actionSetting3.getDouble3();
   }

   public RangeSetting getRangeSetting(String text, String text2) {
      this.actionSetting3 = this.actionSetting3.getActionSetting(text, text2);
      return this;
   }

   @Override
   public void updateState3() {
   }

   @Override
   public Setting getSetting(BooleanSupplier booleanSupplier) {
      return this.getRangeSetting(15343, booleanSupplier);
   }

   private int computeCount(int count, ActionSetting setting, boolean flag, boolean flag2) {
      int previous = count;
      int next = this.computeCount(setting, flag, flag2);
      boolean repeatAllowed = !this.enabled2
         && this.settingNotInARow.isEnabled()
         && (int)setting.getDouble5(flag2) != (int)setting.getDouble4(flag2);
      boolean retry = repeatAllowed;
      if (retry && this.numberSetting != null) {
         this.count11++;
         retry = false;
         if ((double)this.count11 >= this.numberSetting.getValue()) {
            retry = true;
            this.count11 = 0;
         }
      } else {
         this.count11 = 0;
      }

      for (int attempt = 0; attempt < 30 && retry && next == previous; attempt++) {
         next = this.computeCount(setting, flag, flag2);
      }

      return next;
   }

   @Override
   public int getCount10() {
      return 0;
   }

   public RangeSetting getRangeSetting5() {
      NumberSetting setting;
      if (this.settingGroup == null) {
         setting = new NumberSetting(this.getModule(), this.getString() + "MaxInARow", 1.0, 1.0, 10.0, 0);
         setting = setting.getNumberSetting("MaxInARow");
         BooleanSupplier booleanSupplier = this::isActiveAsFlag;
         setting = setting.getNumberSetting(booleanSupplier);
      } else {
         setting = new NumberSetting(this.getModule(), this.getString() + "MaxInARow", 1.0, 0.0, 10.0, 0, this.settingGroup);
         setting = setting.getNumberSetting("MaxInARow");
         BooleanSupplier booleanSupplier2 = this::isActive9;
         setting = setting.getNumberSetting(booleanSupplier2);
      }

      this.numberSetting = setting;
      return this;
   }

   private double computeValue(double offset, ActionSetting setting, boolean flag, boolean flag2) {
      double previous = offset;
      double next = this.computeValue(setting, flag);
      boolean retry = !this.enabled2
         && this.settingNotInARow.isEnabled()
         && setting.getDouble5(flag2) != setting.getDouble4(flag2)
         && !Double.isNaN(next);
      if (retry && this.numberSetting != null) {
         this.count11++;
         retry = false;
         if ((double)this.count11 >= this.numberSetting.getValue()) {
            retry = true;
            this.count11 = 0;
         }
      } else {
         this.count11 = 0;
      }

      for (int attempt = 0; attempt < 30 && retry && next == previous; attempt++) {
         next = this.computeValue(setting, flag);
      }

      return next;
   }

   public void processAction(double offset) {
      if (this.count10 == 0) {
         this.indexOffset2 = (int)offset;
      } else {
         this.value = offset;
      }
   }

   public double recalculate() {
      return this.count10 == 0 ? (double)this.getInt2() : this.getValue2();
   }

   public int getInt5(boolean flag) {
      int count = this.computeCount(this.indexOffset2, this.actionSetting3, false, flag);
      if (this.isActive2()) {
         int lowerSpan = Math.max(0, (int)(this.actionSetting3.getDouble5(flag) - this.actionSetting2.getDouble5(flag)));
         int upperSpan = Math.max(0, (int)(this.actionSetting2.getDouble4(flag) - this.actionSetting3.getDouble4(flag)));
         int totalSpan = lowerSpan + upperSpan;
         if (totalSpan > 0) {
            int pick;
            if (lowerSpan == 0) {
               int range = (int)((double)((int)this.actionSetting2.getDouble4(flag)) - Math.max(this.actionSetting2.getDouble5(flag), this.actionSetting3.getDouble4(flag)));
               pick = SettingContainer.settingProvider.getInt2(0, Math.max(range, 0)) + 1;
            } else if (upperSpan == 0) {
               double clamped = Math.min(this.actionSetting2.getDouble4(flag), this.actionSetting3.getDouble5(flag));
               int range = (int)(clamped - this.actionSetting2.getDouble5(flag));
               pick = SettingContainer.settingProvider.getInt2(0, Math.max(range, 0));
            } else {
               pick = SettingContainer.settingProvider.getInt2(0, totalSpan - 1);
            }

            if (pick < lowerSpan) {
               count = (int)(this.actionSetting2.getDouble5(flag) + (double)pick);
            } else {
               count = (int)(this.actionSetting2.getDouble4(flag) - (double)(pick - 1));
            }
         }
      }

      this.indexOffset2 = count;
      return count;
   }

   private boolean isActive11() {
      return this.isVisible() && this.booleanSetting.isEnabled();
   }

   private boolean isActive10() {
      return this.isVisible() && this.booleanSetting.isEnabled();
   }

   private boolean isActive8() {
      return this.isVisible() && this.booleanSetting.isEnabled();
   }

   private boolean isActive9() {
      return this.settingNotInARow.isEnabled() && this.settingNotInARow.isVisible();
   }

   private boolean isActive5() {
      return this.booleanSetting2.isEnabled() && this.isVisible();
   }

   private boolean isActive4() {
      return this.booleanSetting2.isEnabled() && this.isVisible();
   }

   public RangeSetting(Module module, String text, double posX, double posX2, double posY, double posZ, int count) {
      super(module, text);
      this.booleanSetting2 = null;
      this.actionSetting2 = null;
      this.actionSetting = null;
      this.booleanSetting = null;
      this.numberSetting2 = null;
      this.settingGroup = null;
      this.indexOffset2 = 0;
      this.value = 0.0;
      this.count12 = 0;
      this.enabled2 = false;
      this.random = new Random();
      this.count11 = 0;
      ActionSetting setting = new ActionSetting(module, text, posX, posX2, posY, posZ, count);
      BooleanSupplier booleanSupplier = this::isVisible;
      setting = setting.getActionSetting(booleanSupplier);
      ConsumerTask consumerTask = this::recalculate;
      this.actionSetting3 = setting.resolveActionSetting(consumerTask);
      BooleanSetting setting2 = new BooleanSetting(module, text + "NotInARow", false);
      BooleanSetting setting3 = (BooleanSetting)setting2.getSetting("NotInARow");
      BooleanSupplier booleanSupplier2 = this::isVisible;
      this.settingNotInARow = (BooleanSetting)setting3.getSetting(booleanSupplier2);
      this.count10 = count;
      Runnable task = this::recalculate;
      this.actionSetting3.withRunnable(task);
      this.recalculate();
   }

   public RangeSetting(Module module, String text, String text2, double posX, double posX2, double posY, double posZ, int count) {
      super(module, text);
      this.booleanSetting2 = null;
      this.actionSetting2 = null;
      this.actionSetting = null;
      this.booleanSetting = null;
      this.numberSetting2 = null;
      this.settingGroup = null;
      this.indexOffset2 = 0;
      this.value = 0.0;
      this.count12 = 0;
      this.enabled2 = false;
      this.random = new Random();
      this.count11 = 0;
      ActionSetting setting = new ActionSetting(module, text, text2, posX, posX2, posY, posZ, count);
      BooleanSupplier booleanSupplier = this::isVisible;
      setting = setting.getActionSetting(booleanSupplier);
      ConsumerTask consumerTask = this::recalculate;
      this.actionSetting3 = setting.resolveActionSetting(consumerTask);
      BooleanSetting setting2 = new BooleanSetting(module, text + "NotInARow", false);
      BooleanSetting setting3 = (BooleanSetting)setting2.getSetting("NotInARow");
      BooleanSupplier booleanSupplier2 = this::isVisible;
      this.settingNotInARow = (BooleanSetting)setting3.getSetting(booleanSupplier2);
      this.count10 = count;
      Runnable task = this::recalculate;
      this.actionSetting3.withRunnable(task);
      this.recalculate();
   }

   public RangeSetting(Module module, String text, String text2, double posX, double posX2, double posY, double posZ, int count, SettingGroup settingGroup2) {
      super(module, text);
      this.booleanSetting2 = null;
      this.actionSetting2 = null;
      this.actionSetting = null;
      this.booleanSetting = null;
      this.numberSetting2 = null;
      this.settingGroup = null;
      this.indexOffset2 = 0;
      this.value = 0.0;
      this.count12 = 0;
      this.enabled2 = false;
      this.random = new Random();
      this.count11 = 0;
      ActionSetting setting = new ActionSetting(module, text, text2, posX, posX2, posY, posZ, count, settingGroup2);
      BooleanSupplier booleanSupplier = this::isVisible;
      setting = setting.getActionSetting(booleanSupplier);
      ConsumerTask consumerTask = this::recalculate;
      this.actionSetting3 = setting.resolveActionSetting(consumerTask);
      BooleanSetting setting2 = new BooleanSetting(module, text + "NotInARow", false, settingGroup2);
      BooleanSetting setting3 = (BooleanSetting)setting2.getSetting("NotInARow");
      BooleanSupplier booleanSupplier2 = this::isVisible;
      this.settingNotInARow = (BooleanSetting)setting3.getSetting(booleanSupplier2);
      Runnable task = this::recalculate;
      this.actionSetting3.withRunnable(task);
      settingGroup2.getItemList3().add(this);
      this.settingGroup = settingGroup2;
      this.count10 = count;
      this.recalculate();
   }

   public RangeSetting(Module module, String text, double posX, double posX2, double posY, double posZ, int count, SettingGroup settingGroup2) {
      super(module, text);
      this.booleanSetting2 = null;
      this.actionSetting2 = null;
      this.actionSetting = null;
      this.booleanSetting = null;
      this.numberSetting2 = null;
      this.settingGroup = null;
      this.indexOffset2 = 0;
      this.value = 0.0;
      this.count12 = 0;
      this.enabled2 = false;
      this.random = new Random();
      this.count11 = 0;
      ActionSetting setting = new ActionSetting(module, text, posX, posX2, posY, posZ, count, settingGroup2);
      BooleanSupplier booleanSupplier = this::isVisible;
      setting = setting.getActionSetting(booleanSupplier);
      ConsumerTask consumerTask = this::recalculate;
      this.actionSetting3 = setting.resolveActionSetting(consumerTask);
      BooleanSetting setting2 = new BooleanSetting(module, text + "NotInARow", false, settingGroup2);
      BooleanSetting setting3 = (BooleanSetting)setting2.getSetting("NotInARow");
      BooleanSupplier booleanSupplier2 = this::isVisible;
      this.settingNotInARow = (BooleanSetting)setting3.getSetting(booleanSupplier2);
      settingGroup2.getItemList3().add(this);
      this.settingGroup = settingGroup2;
      this.count10 = count;
      Runnable task = this::recalculate;
      this.actionSetting3.withRunnable(task);
      this.recalculate();
   }
}
