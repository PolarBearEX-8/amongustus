package augustus.modes.movement;

import augustus.modules.movement.NoWeb;
import client.event.UpdateStateListener;
import client.setting.BooleanSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.CommonUpdateStateListenerAdapter;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class FlyIntaveLegacyMode extends CommonUpdateStateListenerAdapter implements UpdateStateListener {
   public final BooleanSetting settingStopOnHit;

   @Override
   public void updateState() {
      super.updateState();
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
      super.updateState2();
   }

   @Override
   public void onPostUpdateState() {
      CommonUpdateStateListenerAdapter.getIndexArray();
      if (MinecraftAccessor.minecraft.thePlayer.isInWeb()
         && MoveUtil.canExecute()
         && MinecraftAccessor.minecraft.thePlayer.ticksExisted % 4 != 0
         && super.enabled2) {
         if (MinecraftAccessor.minecraft.thePlayer.hurtTime > 0) {
            if (!this.settingStopOnHit.isEnabled()) {
               MoveUtil.processFloat2(0.2F, false);
               MinecraftAccessor.minecraft.thePlayer.setScaleFactor2(0.02F);
            }
         } else {
            MoveUtil.processFloat2(0.3F, false);
            MinecraftAccessor.minecraft.thePlayer.setScaleFactor2(0.08F);
         }
      }
   }

   public FlyIntaveLegacyMode(NoWeb noWeb, SettingGroup settingGroup) {
        super(noWeb, settingGroup, "Intave 14.7.3-b2");
      
      BooleanSetting setting = new BooleanSetting(super.module, "Intave1473b2StopOnHit", false, super.settingGroup);
      String text = "StopOnHit";
      setting = (BooleanSetting)setting.getSetting(text);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingStopOnHit = (BooleanSetting)setting.getSetting(booleanSupplier);
   }
}
