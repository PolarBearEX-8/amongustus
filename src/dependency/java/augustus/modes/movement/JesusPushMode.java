package augustus.modes.movement;

import augustus.modules.combat.Velocity;
import client.event.UpdateStateListener;
import client.setting.AuxiliaryNumberSettingEntry;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.InternalStringArrayEntry;
import client.util.MoveUtil;
import java.util.HashMap;
import java.util.function.BiFunction;

public class JesusPushMode extends InternalStringArrayEntry implements UpdateStateListener {
   public final ModeSetting settingExpert;
   public final NumberSetting settingPushMotion;
   public final NumberSetting settingPushStart;
   public final NumberSetting settingPushEnd;
   public final BooleanSetting settingOnGround;
   public final HashMap hashMap;

   @Override
   public void updateState() {
   }

   private boolean isActive2() {
      return this.booleanValue() && this.isActive6();
   }

   private boolean isActiveAsFlag() {
      return this.booleanValue() && this.isActive6();
   }

   private boolean isActive3() {
      return this.booleanValue() && !this.isActive6();
   }

   private boolean isActive7() {
      return this.booleanValue() && this.isActive6();
   }

   private boolean isActive8() {
      return this.booleanValue() && this.isActive6();
   }

   @Override
   public void clear() {
   }

   private boolean isActive4() {
      return this.booleanValue() && !this.isActive6();
   }

   private boolean isActive5() {
      return this.booleanValue() && !this.isActive6();
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new JesusPushMode((Velocity)item, (SettingGroup)group));
   }

   @Override
   public void updateState2() {
      byte byteValue = 43;
      this.applyIndex(byteValue);
   }

   private boolean isActive6() {
      return this.settingExpert.getSelected().equals("Simple");
   }

   @Override
   public void onPostUpdateState() {
      if (this.isActive()) {
         if (this.isActive6()) {
            if ((double)minecraft.thePlayer.hurtTime <= Math.max(this.settingPushStart.getValue(), this.settingPushEnd.getValue())
               && (double)minecraft.thePlayer.hurtTime >= Math.min(this.settingPushStart.getValue(), this.settingPushEnd.getValue())) {
               MoveUtil.processAction(this.settingPushMotion.getValue() / 100.0, true);
               if (this.settingOnGround.isEnabled()) {
                  minecraft.thePlayer.onGround = true;
               }
            }
         } else {
            AuxiliaryNumberSettingEntry auxiliaryNumberSettingEntry = (AuxiliaryNumberSettingEntry)this.hashMap.get(minecraft.thePlayer.hurtTime);
            if (auxiliaryNumberSettingEntry != null) {
               MoveUtil.processAction(auxiliaryNumberSettingEntry.getValue(), true);
               if (auxiliaryNumberSettingEntry.isActive()) {
                  minecraft.thePlayer.onGround = true;
               }
            }
         }
      }
   }

   public JesusPushMode(Velocity velocity, SettingGroup settingGroup) {
        super(velocity, settingGroup, "Push");
      
      ModeSetting setting = new ModeSetting(this.module, "PushMode", "Simple", new String[]{"Simple", "Expert"}, this.settingGroup);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingExpert = setting.getModeSetting(booleanSupplier);
      NumberSetting setting2 = new NumberSetting(this.module, "Push", 1.1, 0.01, 20.0, 2, this.settingGroup);
      String text = "PushMotion";
      NumberSetting numberSetting = setting2.getNumberSetting(text);
      BooleanSupplier booleanSupplier2 = this::isActiveAsFlag;
      this.settingPushMotion = numberSetting.getNumberSetting(booleanSupplier2);
      NumberSetting setting3 = new NumberSetting(this.module, "PushStart", 9.0, 1.0, 10.0, 0, this.settingGroup);
      BooleanSupplier booleanSupplier3 = this::isActive8;
      this.settingPushStart = setting3.getNumberSetting(booleanSupplier3);
      NumberSetting setting4 = new NumberSetting(this.module, "PushEnd", 2.0, 1.0, 10.0, 0, this.settingGroup);
      BooleanSupplier booleanSupplier4 = this::isActive2;
      this.settingPushEnd = setting4.getNumberSetting(booleanSupplier4);
      BooleanSetting setting5 = new BooleanSetting(this.module, "OnGround", false, this.settingGroup);
      BooleanSupplier booleanSupplier5 = this::isActive7;
      this.settingOnGround = (BooleanSetting)setting5.getSetting(booleanSupplier5);
      this.hashMap = new HashMap();

      for (int index = 10; index > 0; index--) {
         SettingGroup settingGroup2 = new SettingGroup(velocity, "Hurttime " + index, settingGroup);
         BooleanSupplier booleanSupplier6 = this::isActive4;
         BooleanSupplier booleanSupplier7 = booleanSupplier6;
         SettingGroup settingGroup3 = settingGroup2.getSettingGroup(booleanSupplier7);
         NumberSetting setting6 = new NumberSetting(velocity, "PushMotion" + index, 0.0, 0.0, 20.0, 2, settingGroup3);
         String text2 = "PushMotion";
         NumberSetting numberSetting2 = setting6.getNumberSetting(text2);
         BooleanSupplier booleanSupplier8 = this::isActive5;
         NumberSetting numberSetting3 = numberSetting2.getNumberSetting(booleanSupplier8);
         BooleanSetting setting7 = new BooleanSetting(velocity, "PushOnGround" + index, false, settingGroup3);
         String text3 = "PushOnGround";
         BooleanSetting setting8 = (BooleanSetting)setting7.getSetting(text3);
         BooleanSupplier booleanSupplier9 = this::isActive3;
         BooleanSetting setting9 = (BooleanSetting)setting8.getSetting(booleanSupplier9);
         this.hashMap.put(index, new AuxiliaryNumberSettingEntry(this, numberSetting3, setting9));
      }
   }
}
