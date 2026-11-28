package net.augustus.modules.legit;

import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.modules.Module;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class ClickDensitySpike {
   private DoubleValue strength;
   private RandomizedRangeSettings time;

   public ClickDensitySpike(Module module, SettingGroup doubleValue, int n) {
       super();
      SettingGroup settingGroup = new SettingGroup(module, "Spike " + n, doubleValue);
      this.strength = new DoubleValue(module, "Strength" + n + doubleValue.getName(), 50.0, 0.0, 100.0, 0, settingGroup).named("Strength");
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(module, "Time" + n + doubleValue.getName(), 50.0, 100.0, 0.0, 200.0, 0, settingGroup)).withoutNotInARow();
      double d = 2.0;
      double d2 = 70.0;
      double d3 = 0.2;
      this.time = (RandomizedRangeSettings)randomizedRangeSettings.withNormalDistribution(1.0, d3, d2, d, true, true).withDisplayNamePrefix("Time");
   }

   public double getStrength() {
      return this.strength.getValue();
   }

   public int getTime() {
      return this.time.nextInt();
   }

   public void randomize() {
      this.strength.randomize();
      this.time.randomize();
   }
}
