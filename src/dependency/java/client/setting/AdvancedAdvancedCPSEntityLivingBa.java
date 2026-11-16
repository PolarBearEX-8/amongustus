package client.setting;

import client.ClientGlobals;
import client.module.Module;
import client.util.BooleanSupplier;
import client.util.MovingObjectType;
import client.util.SmoothDamper;
import client.util.TimeHelper;
import java.util.function.BiFunction;
import net.minecraft.entity.EntityLivingBase;

public class AdvancedAdvancedCPSEntityLivingBa extends AbstractEntityLivingBaseModeOptionSupplier {
   public final RangeSetting settingCPS;
   public final RangeSetting settingCPSRecalculateDelay;
   public final BooleanSetting settingSpikes;
   public final NumberSetting settingSpikeDelay;
   public final RangeSetting settingSpikeDuration;
   public final NumberSetting settingSpikeMultiplier;
   public final BooleanSetting settingDrops;
   public final NumberSetting settingDropDelay;
   public final RangeSetting settingDropDuration;
   public final NumberSetting settingDropMultiplier;
   public final BooleanSetting settingReduceHitsInAir;
   public final RangeSetting settingReduceRecalculateDelay;
   public final RangeSetting settingReduceMultiplier;
   public final BooleanSetting settingReduceHitsOnWall;
   public final RangeSetting settingReduceRecalculateDelayOnWall;
   public final RangeSetting settingReduceMultiplierOnWall;
   public final SmoothDamper smoothDamper;
   public final TimeHelper timer;
   public final TimeHelper timer2;
   public final TimeHelper timer3;
   public final TimeHelper timer4;
   public final TimeHelper timer5;
   public long timestamp;
   public long timestamp2;

   public AdvancedAdvancedCPSEntityLivingBa(Module module, SettingGroup settingGroup) {
        super(module, settingGroup, "Advanced");
      
      RangeSetting rangeSetting = (new RangeSetting(this.module, "AdvancedCPS", 20.0, 20.0, 0.0, 40.0, 0, this.settingGroup)).getRangeSetting2();
      String text = "CPS";
      this.settingCPS = rangeSetting.getRangeSetting(text);
      rangeSetting = (new RangeSetting(this.module, "AdvancedCPSRecalculateDelay", 200.0, 600.0, 0.0, 4000.0, 0, this.settingGroup)).getRangeSetting2();
      String text2 = "CPSRecalculateDelay";
      this.settingCPSRecalculateDelay = rangeSetting.getRangeSetting(text2);
      BooleanSetting setting = new BooleanSetting(this.module, "AdvancedSpikes", false, this.settingGroup);
      String text3 = "Spikes";
      this.settingSpikes = (BooleanSetting)setting.getSetting(text3);
      NumberSetting setting2 = new NumberSetting(this.module, "AdvancedSpikeDelay", 15000.0, 0.0, 20000.0, 0, this.settingGroup);
      String text4 = "SpikeDelay";
      NumberSetting numberSetting = setting2.getNumberSetting(text4);
      BooleanSupplier booleanSupplier = this.settingSpikes::isEnabled;
      this.settingSpikeDelay = numberSetting.getNumberSetting(booleanSupplier);
      rangeSetting = (new RangeSetting(this.module, "AdvancedSpikeDuration", 500.0, 1000.0, 0.0, 2000.0, 0, this.settingGroup)).getRangeSetting2();
      String text5 = "SpikeDuration";
      rangeSetting = rangeSetting.getRangeSetting(text5);
      BooleanSupplier booleanSupplier2 = this.settingSpikes::isEnabled;
      this.settingSpikeDuration = rangeSetting.getRangeSetting(15343, booleanSupplier2);
      NumberSetting setting3 = new NumberSetting(this.module, "AdvancedSpikeMultiplier", 1.5, 1.0, 3.0, 2, this.settingGroup);
      String text6 = "SpikeMultiplier";
      NumberSetting numberSetting2 = setting3.getNumberSetting(text6);
      BooleanSupplier booleanSupplier3 = this.settingSpikes::isEnabled;
      this.settingSpikeMultiplier = numberSetting2.getNumberSetting(booleanSupplier3);
      BooleanSetting setting4 = new BooleanSetting(this.module, "AdvancedDrops", false, this.settingGroup);
      String text7 = "Drops";
      this.settingDrops = (BooleanSetting)setting4.getSetting(text7);
      NumberSetting setting5 = new NumberSetting(this.module, "AdvancedDropDelay", 7000.0, 0.0, 20000.0, 0, this.settingGroup);
      String text8 = "DropDelay";
      NumberSetting numberSetting3 = setting5.getNumberSetting(text8);
      BooleanSupplier booleanSupplier4 = this.settingDrops::isEnabled;
      this.settingDropDelay = numberSetting3.getNumberSetting(booleanSupplier4);
      rangeSetting = new RangeSetting(this.module, "AdvancedDropDuration", 500.0, 1000.0, 0.0, 2000.0, 0, this.settingGroup);
      String text9 = "DropDuration";
      rangeSetting = rangeSetting.getRangeSetting(text9).getRangeSetting2();
      BooleanSupplier booleanSupplier5 = this.settingDrops::isEnabled;
      this.settingDropDuration = rangeSetting.getRangeSetting(15343, booleanSupplier5);
      NumberSetting setting6 = new NumberSetting(this.module, "AdvancedDropMultiplier", 0.5, 0.0, 1.0, 2, this.settingGroup);
      String text10 = "DropMultiplier";
      NumberSetting numberSetting4 = setting6.getNumberSetting(text10);
      BooleanSupplier booleanSupplier6 = this.settingDrops::isEnabled;
      this.settingDropMultiplier = numberSetting4.getNumberSetting(booleanSupplier6);
      BooleanSetting setting7 = new BooleanSetting(this.module, "AdvancedReduceHitsInAir", true, this.settingGroup);
      String text11 = "ReduceHitsInAir";
      this.settingReduceHitsInAir = (BooleanSetting)setting7.getSetting(text11);
      rangeSetting = (new RangeSetting(this.module, "AdvancedReduceRecalculateDelay", 100.0, 200.0, 0.0, 1000.0, 0, this.settingGroup)).getRangeSetting2();
      String text12 = "ReduceRecalculateDelay";
      rangeSetting = rangeSetting.getRangeSetting(text12);
      BooleanSupplier booleanSupplier7 = this.settingReduceHitsInAir::isEnabled;
      this.settingReduceRecalculateDelay = rangeSetting.getRangeSetting(15343, booleanSupplier7);
      rangeSetting = (new RangeSetting(this.module, "AdvancedReduceMultiplier", 0.4, 0.4, 0.0, 1.0, 2, this.settingGroup)).getRangeSetting2();
      String text13 = "ReduceMultiplier";
      rangeSetting = rangeSetting.getRangeSetting(text13);
      BooleanSupplier booleanSupplier8 = this.settingReduceHitsInAir::isEnabled;
      this.settingReduceMultiplier = rangeSetting.getRangeSetting(15343, booleanSupplier8);
      BooleanSetting setting8 = new BooleanSetting(this.module, "AdvancedReduceHitsOnWall", false, this.settingGroup);
      String text14 = "ReduceHitsOnWall";
      this.settingReduceHitsOnWall = (BooleanSetting)setting8.getSetting(text14);
      rangeSetting = (new RangeSetting(this.module, "AdvancedReduceRecalculateDelayOnWall", 100.0, 200.0, 0.0, 1000.0, 0, this.settingGroup)).getRangeSetting2();
      String text15 = "ReduceRecalculateDelayOnWall";
      rangeSetting = rangeSetting.getRangeSetting(text15);
      BooleanSupplier booleanSupplier9 = this.settingReduceHitsOnWall::isEnabled;
      this.settingReduceRecalculateDelayOnWall = rangeSetting.getRangeSetting(15343, booleanSupplier9);
      rangeSetting = (new RangeSetting(this.module, "AdvancedReduceMultiplierOnWall", 0.4, 0.4, 0.0, 1.0, 2, this.settingGroup)).getRangeSetting2();
      String text16 = "ReduceMultiplierOnWall";
      rangeSetting = rangeSetting.getRangeSetting(text16);
      BooleanSupplier booleanSupplier10 = this.settingReduceHitsOnWall::isEnabled;
      this.settingReduceMultiplierOnWall = rangeSetting.getRangeSetting(15343, booleanSupplier10);
      this.smoothDamper = new SmoothDamper(20, 0.0);
      this.timer = new TimeHelper();
      this.timer2 = new TimeHelper();
      this.timer3 = new TimeHelper();
      this.timer4 = new TimeHelper();
      this.timer5 = new TimeHelper();
      this.timestamp = 0L;
      this.timestamp2 = 0L;
   }

   @Override
   public int computeCount(EntityLivingBase livingEntity) {
      TimeHelper timer6 = this.timer;
      long indexOffset = (long)this.settingCPSRecalculateDelay.getIndexOffset2();
      if (timer6.hasReached(indexOffset)) {
         this.settingCPS.getDouble4();
         this.settingCPSRecalculateDelay.recalculate();
         this.timer.reset();
      }

      int indexOffset2 = this.settingCPS.getIndexOffset2();
      if (this.settingSpikes.isEnabled()) {
         int value = (int)this.settingSpikeDelay.getValue();
         long timestamp3 = this.timestamp;
         if (this.timer3.hasReached(timestamp3)) {
            indexOffset2 = (int)Math.round((double)this.settingCPS.getIndexOffset2() * this.settingSpikeMultiplier.getValue());
            timer6 = this.timer3;
            long timestamp4 = this.timestamp;
            SettingProvider settingProvider = SettingContainer.settingProvider;
            int doubleItem = (int)this.settingSpikeDuration.getDouble2();
            int doubleItem2 = (int)this.settingSpikeDuration.getDouble9();
            long value2 = timestamp4 + (long)settingProvider.getInt3(doubleItem2, doubleItem);
            if (timer6.hasReached(value2)) {
               SettingProvider settingProvider2 = SettingContainer.settingProvider;
               int count = Math.max(0, value - 100);
               int count2 = value + 100;
               int count3 = count;
               this.timestamp = (long)settingProvider2.getInt3(count3, count2);
               this.timer3.reset();
            }
         }
      }

      if (this.settingDrops.isEnabled()) {
         int value3 = (int)this.settingDropDelay.getValue();
         long timestamp5 = this.timestamp2;
         if (this.timer2.hasReached(timestamp5)) {
            indexOffset2 = (int)Math.round((double)this.settingCPS.getIndexOffset2() * this.settingDropMultiplier.getValue());
            timer6 = this.timer2;
            long timestamp6 = this.timestamp2;
            SettingProvider settingProvider3 = SettingContainer.settingProvider;
            int doubleItem3 = (int)this.settingDropDuration.getDouble2();
            int doubleItem4 = (int)this.settingDropDuration.getDouble9();
            long value4 = timestamp6 + (long)settingProvider3.getInt3(doubleItem4, doubleItem3);
            if (timer6.hasReached(value4)) {
               SettingProvider settingProvider4 = SettingContainer.settingProvider;
               int count4 = Math.max(0, value3 - 100);
               int count5 = value3 + 100;
               int count6 = count4;
               this.timestamp2 = (long)settingProvider4.getInt3(count6, count5);
               this.timer2.reset();
            }
         }
      }

      if (this.settingReduceHitsInAir.isEnabled() && minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.MISS) {
         timer6 = this.timer4;
         long indexOffset3 = (long)this.settingReduceRecalculateDelay.getIndexOffset2();
         if (timer6.hasReached(indexOffset3)) {
            this.settingReduceRecalculateDelay.recalculate();
            this.settingReduceMultiplier.recalculate();
            this.timer4.reset();
         }

         indexOffset2 = (int)Math.round((double)this.settingCPS.getIndexOffset2() * this.settingReduceMultiplier.getDouble3());
      }

      if (this.settingReduceHitsOnWall.isEnabled() && minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
         timer6 = this.timer5;
         long indexOffset4 = (long)this.settingReduceRecalculateDelayOnWall.getIndexOffset2();
         if (timer6.hasReached(indexOffset4)) {
            this.settingReduceRecalculateDelayOnWall.recalculate();
            this.settingReduceMultiplierOnWall.recalculate();
            this.timer5.reset();
         }

         indexOffset2 = (int)Math.round((double)this.settingCPS.getIndexOffset2() * this.settingReduceMultiplierOnWall.getDouble3());
      }

      return ClientGlobals.moduleManager.smartClicking.canExecute5() ? 0 : this.smoothDamper.getInt(indexOffset2);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity) {
   }

   @Override
   public void updateState3() {
   }

   @Override
   public void updateState2() {
   }

   @Override
   public void updateState() {
   }

   @Override
   public void clear() {
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new AdvancedAdvancedCPSEntityLivingBa((Module)item, (SettingGroup)group));
   }
}
