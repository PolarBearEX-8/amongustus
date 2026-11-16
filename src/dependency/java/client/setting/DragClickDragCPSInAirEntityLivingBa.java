package client.setting;

import client.ClientGlobals;
import client.module.Module;
import client.util.AnimationTimer;
import client.util.CombatRandomizer;
import client.util.MovingObjectType;
import client.util.SmoothDamper;
import java.util.ArrayList;
import java.util.function.BiFunction;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;

public class DragClickDragCPSInAirEntityLivingBa extends AbstractEntityLivingBaseModeOptionSupplier {
   public final RangeSetting settingCPSInAir;
   public final RangeSetting settingCPSInAirRecalculateTickDelay;
   public final RangeSetting settingMaxCPS;
   public final RangeSetting settingMaxCPSRecalculateTickDelay;
   public final NumberSetting settingMinStandardDeviation;
   public final SmoothDamper smoothDamper;
   public final ArrayList arrayList;
   public final AnimationTimer animationTimer;
   public final AnimationTimer animationTimer2;

   public DragClickDragCPSInAirEntityLivingBa(Module module, SettingGroup settingGroup) {
      super(module, settingGroup, "DragClick");
      RangeSetting setting = new RangeSetting(this.module, "DragCPSInAir", 4.0, 8.0, 0.0, 20.0, 0, this.settingGroup);
      String text = "CPSInAir";
      this.settingCPSInAir = setting.getRangeSetting(text);
      setting = new RangeSetting(this.module, "DragCPSInAirRecalculateTickDelay", 10.0, 30.0, 0.0, 60.0, 0, this.settingGroup);
      String text2 = "CPSInAirRecalculateTickDelay";
      this.settingCPSInAirRecalculateTickDelay = setting.getRangeSetting(text2).getRangeSetting2();
      setting = new RangeSetting(this.module, "DragMaxCPS", 18.0, 20.0, 1.0, 40.0, 0, this.settingGroup);
      String text3 = "MaxCPS";
      this.settingMaxCPS = setting.getRangeSetting(text3);
      setting = (new RangeSetting(this.module, "DragMaxCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingGroup)).getRangeSetting2();
      String text4 = "MaxCPSRecalculateTickDelay";
      this.settingMaxCPSRecalculateTickDelay = setting.getRangeSetting(text4);
      NumberSetting setting2 = new NumberSetting(this.module, "DragMinStandardDeviation", 0.5, 0.0, 1.0, 2, this.settingGroup);
      String text5 = "MinStandardDeviation";
      this.settingMinStandardDeviation = setting2.getNumberSetting(text5);
      this.smoothDamper = new SmoothDamper(20, 0.0);
      this.arrayList = new ArrayList();
      this.animationTimer = new AnimationTimer();
      this.animationTimer2 = new AnimationTimer();
   }

   @Override
   public int computeCount(EntityLivingBase livingEntity) {
      MovingObjectPosition objectMouseOver = minecraft.objectMouseOver;
      int count;
      if (this.arrayList.size() < 5) {
         count = 1;
      } else {
         AnimationTimer animationTimer3 = this.animationTimer;
         int indexOffset = this.settingMaxCPSRecalculateTickDelay.getIndexOffset2();
         if (animationTimer3.checkCondition(indexOffset)) {
            this.animationTimer.processAction3();
            this.settingMaxCPSRecalculateTickDelay.getDouble4();
            this.settingMaxCPS.getDouble4();
         }

         ArrayList arrayList2 = this.arrayList;
         double value = this.settingMinStandardDeviation.getValue();
         double indexOffset2 = (double)this.settingMaxCPS.getIndexOffset2();
         double offset = value;
         byte byteValue = 21;
         count = CombatRandomizer.selectWeightedIndex(arrayList2, byteValue, offset, indexOffset2);
      }

      if (ClientGlobals.moduleManager.smartClicking.canExecute5()) {
         count = 0;
      }

      if (objectMouseOver == null || objectMouseOver.typeOfHit != MovingObjectType.ENTITY) {
         AnimationTimer animationTimer4 = this.animationTimer2;
         int indexOffset3 = this.settingCPSInAirRecalculateTickDelay.getIndexOffset2();
         if (animationTimer4.checkCondition(indexOffset3)) {
            this.animationTimer2.processAction3();
            this.settingCPSInAirRecalculateTickDelay.getDouble4();
            this.settingCPSInAir.getDouble4();
         }

         SmoothDamper smoothDamper2 = this.smoothDamper;
         int indexOffset4 = this.settingCPSInAir.getIndexOffset2();
         count = smoothDamper2.getInt(indexOffset4);
      }

      this.arrayList.add(count);

      while (this.arrayList.size() > 21) {
         this.arrayList.remove(0);
      }

      return count;
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity) {
   }

   @Override
   public void updateState3() {
      this.animationTimer.processAction();
      this.animationTimer2.processAction();
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
      return ((item, group) -> new DragClickDragCPSInAirEntityLivingBa((Module)item, (SettingGroup)group));
   }
}
