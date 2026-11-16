package augustus.modes.movement;

import augustus.modules.movement.NoWeb;
import client.event.EventMove;
import client.event.EventStrafe;
import client.event.MoveListener;
import client.event.StrafeListener;
import client.setting.BooleanSetting;
import client.setting.SettingGroup;
import client.util.AuxiliaryAbstractEventAdapter;
import client.util.BooleanSupplier;
import client.util.CommonUpdateStateListenerAdapter;
import client.util.MinecraftAccessor;
import client.util.SmoothSynchronizer;

public class NoWebIntaveMode extends CommonUpdateStateListenerAdapter implements MoveListener, SmoothSynchronizer, StrafeListener {
   public final BooleanSetting settingHideSneakAnimation;

   @Override
   public void updateState() {
      super.updateState();
   }

   @Override
   public void onStrafe(EventStrafe event) {
      CommonUpdateStateListenerAdapter.getIndexArray();
      if (MinecraftAccessor.minecraft.thePlayer.isInWeb() && this.settingHideSneakAnimation.isEnabled()) {
         float factor = 1.62F;
         event.processAction3(factor);
      }
   }

   @Override
   public void applyAuxiliaryAbstractEventAdap(AuxiliaryAbstractEventAdapter event) {
      event.setEnabled(true);
      double offset = 1.0;
      event.setValue2(offset);
   }

   @Override
   public void clear() {
   }

   @Override
   public void onMove(EventMove event) {
      if (MinecraftAccessor.minecraft.thePlayer.isInWeb()) {
         event.setState4(true);
      }
   }

   @Override
   public void updateState2() {
      super.updateState2();
      byte byteValue = 21;
      this.applyIndex(byteValue);
      byte byteValue2 = 49;
      this.applyIndex(byteValue2);
      byte byteValue3 = 24;
      this.applyIndex(byteValue3);
   }

   public NoWebIntaveMode(NoWeb noWeb, SettingGroup settingGroup) {
        super(noWeb, settingGroup, "Intave3");
      
      BooleanSetting setting = new BooleanSetting(super.module, "Intave3HideSneakAnimation", true, super.settingGroup);
      String text = "HideSneakAnimation";
      setting = (BooleanSetting)setting.getSetting(text);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingHideSneakAnimation = (BooleanSetting)setting.getSetting(booleanSupplier);
   }
}
