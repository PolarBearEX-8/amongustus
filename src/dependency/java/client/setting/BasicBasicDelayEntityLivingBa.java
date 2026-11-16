package client.setting;

import client.module.Module;
import client.util.TimeHelper;
import java.util.function.BiFunction;
import net.minecraft.entity.EntityLivingBase;

public class BasicBasicDelayEntityLivingBa extends AbstractEntityLivingBaseModeOptionSupplier {
   public final RangeSetting settingDelay;
   public final TimeHelper timer;

   public BasicBasicDelayEntityLivingBa(Module module, SettingGroup settingGroup) {
      super(module, settingGroup, "Basic");
      RangeSetting setting = new RangeSetting(this.module, "BasicDelay", 50.0, 100.0, 0.0, 400.0, 0, this.settingGroup);
      String text = "Delay";
      this.settingDelay = setting.getRangeSetting(text);
      this.timer = new TimeHelper();
   }

   @Override
   public int computeCount(EntityLivingBase livingEntity) {
      TimeHelper timer2 = this.timer;
      long indexOffset = (long)this.settingDelay.getIndexOffset2();
      boolean hasReached = timer2.hasReached(indexOffset);
      if (hasReached) {
         this.timer.reset();
      }

      return hasReached ? 1 : 0;
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity) {
      this.settingDelay.recalculate();
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
      return ((item, group) -> new BasicBasicDelayEntityLivingBa((Module)item, (SettingGroup)group));
   }
}
