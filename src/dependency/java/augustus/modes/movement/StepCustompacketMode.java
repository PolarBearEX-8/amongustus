package augustus.modes.movement;

import augustus.modules.combat.Criticals;
import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.setting.SettingProvider;
import client.util.BooleanSupplier;
import client.util.ExtendedStringArrayEntry;
import java.util.function.BiFunction;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;

public class StepCustompacketMode extends ExtendedStringArrayEntry implements AttackEntityListener {
   public final BooleanSetting settingUseC06;
   public final NumberSetting settingInterval;
   public final BooleanSetting settingSendFirst;
   public final BooleanSetting settingOnGroundFirst;
   public final ActionSetting actionSetting3;
   public final BooleanSetting settingSendSecond;
   public final BooleanSetting settingOnGroundSecond;
   public final ActionSetting actionSetting2;
   public final BooleanSetting settingSendThird;
   public final BooleanSetting settingOnGroundThird;
   public final ActionSetting actionSetting4;
   public final BooleanSetting settingSendFourth;
   public final BooleanSetting settingOnGroundFourth;
   public final ActionSetting actionSetting;
   public int count2;

   @Override
   public void updateState() {
      this.count2 = -1;
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      this.count2++;
      if (this.isActive() && !((double)this.count2 < this.settingInterval.getValue())) {
         this.count2 = -1;
         if (this.settingSendFirst.isEnabled()) {
            SettingProvider settingProvider = SettingContainer.settingProvider;
            double doubleItem = this.actionSetting3.getDouble6();
            double doubleItem2 = this.actionSetting3.getDouble3();
            double doubleItem3 = settingProvider.getDouble4(doubleItem2, doubleItem);
            boolean enabled = this.settingOnGroundFirst.isEnabled();
            double offset = doubleItem3;
            this.applyDouble(offset, enabled);
         }

         if (this.settingSendSecond.isEnabled()) {
            SettingProvider settingProvider2 = SettingContainer.settingProvider;
            double doubleItem4 = this.actionSetting2.getDouble6();
            double doubleItem5 = this.actionSetting2.getDouble3();
            double doubleItem6 = settingProvider2.getDouble4(doubleItem5, doubleItem4);
            boolean enabled2 = this.settingOnGroundSecond.isEnabled();
            double offset2 = doubleItem6;
            this.applyDouble(offset2, enabled2);
         }

         if (this.settingSendThird.isEnabled()) {
            SettingProvider settingProvider3 = SettingContainer.settingProvider;
            double doubleItem7 = this.actionSetting4.getDouble6();
            double doubleItem8 = this.actionSetting4.getDouble3();
            double doubleItem9 = settingProvider3.getDouble4(doubleItem8, doubleItem7);
            boolean enabled3 = this.settingOnGroundThird.isEnabled();
            double offset3 = doubleItem9;
            this.applyDouble(offset3, enabled3);
         }

         if (this.settingSendFourth.isEnabled()) {
            SettingProvider settingProvider4 = SettingContainer.settingProvider;
            double doubleItem10 = this.actionSetting.getDouble6();
            double doubleItem11 = this.actionSetting.getDouble3();
            double doubleItem12 = settingProvider4.getDouble4(doubleItem11, doubleItem10);
            boolean enabled4 = this.settingOnGroundFourth.isEnabled();
            double offset4 = doubleItem12;
            this.applyDouble(offset4, enabled4);
         }
      }
   }

   private boolean isActive3() {
      return this.booleanValue() && this.settingSendFourth.isEnabled();
   }

   private boolean isActive2() {
      return this.booleanValue() && this.settingSendSecond.isEnabled();
   }

   private void applyDouble(double offset, boolean flag) {
      if (this.settingUseC06.isEnabled()) {
         minecraft.thePlayer
            .netHandlerPlayClient
            .handlePacket2(
               new C06PacketPlayerPosLook(
                  minecraft.thePlayer.posX, minecraft.thePlayer.posY + offset, minecraft.thePlayer.posZ, minecraft.thePlayer.rotationYaw, minecraft.thePlayer.rotationPitch, flag
               )
            );
      } else {
         minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C04PacketPlayerPosition(minecraft.thePlayer.posX, minecraft.thePlayer.posY + offset, minecraft.thePlayer.posZ, flag));
      }
   }

   private boolean isActive5() {
      return this.booleanValue() && this.settingSendFirst.isEnabled();
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new StepCustompacketMode((Criticals)item, (SettingGroup)group));
   }

   private boolean isActive9() {
      return this.booleanValue() && this.settingSendThird.isEnabled();
   }

   private boolean isActive6() {
      return this.booleanValue() && this.settingSendFourth.isEnabled();
   }

   @Override
   public void clear() {
   }

   private boolean isActive4() {
      return this.booleanValue() && this.settingSendThird.isEnabled();
   }

   @Override
   public void updateState2() {
      byte byteValue = 1;
      this.applyIndex(byteValue);
   }

   private boolean isActive7() {
      return this.booleanValue() && this.settingSendSecond.isEnabled();
   }

   private boolean isActive8() {
      return this.booleanValue() && this.settingSendFirst.isEnabled();
   }

   public StepCustompacketMode(Criticals criticals, SettingGroup settingGroup) {
        super(criticals, settingGroup, "CustomPacket");
      
      BooleanSetting setting = new BooleanSetting(this.module, "UseC06", false, this.settingGroup);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingUseC06 = (BooleanSetting)setting.getSetting(booleanSupplier);
      NumberSetting setting2 = new NumberSetting(this.module, "Interval", 3.0, 0.0, 10.0, 0, this.settingGroup);
      BooleanSupplier booleanSupplier2 = this::booleanValue;
      this.settingInterval = setting2.getNumberSetting(booleanSupplier2);
      setting = new BooleanSetting(this.module, "SendFirst", true, this.settingGroup);
      BooleanSupplier booleanSupplier3 = this::booleanValue;
      this.settingSendFirst = (BooleanSetting)setting.getSetting(booleanSupplier3);
      setting = new BooleanSetting(this.module, "OnGroundFirst", false, this.settingGroup);
      BooleanSupplier booleanSupplier4 = this::isActive8;
      this.settingOnGroundFirst = (BooleanSetting)setting.getSetting(booleanSupplier4);
      ActionSetting setting3 = new ActionSetting(this.module, "YAddFirst", 0.001, 0.001, 0.0, 1.0, 12, this.settingGroup);
      BooleanSupplier booleanSupplier5 = this::isActive5;
      this.actionSetting3 = setting3.getActionSetting(booleanSupplier5);
      setting = new BooleanSetting(this.module, "SendSecond", true, this.settingGroup);
      BooleanSupplier booleanSupplier6 = this::booleanValue;
      this.settingSendSecond = (BooleanSetting)setting.getSetting(booleanSupplier6);
      setting = new BooleanSetting(this.module, "OnGroundSecond", false, this.settingGroup);
      BooleanSupplier booleanSupplier7 = this::isActive2;
      this.settingOnGroundSecond = (BooleanSetting)setting.getSetting(booleanSupplier7);
      ActionSetting setting4 = new ActionSetting(this.module, "YAddSecond", 0.001, 0.001, 0.0, 1.0, 12, this.settingGroup);
      BooleanSupplier booleanSupplier8 = this::isActive7;
      this.actionSetting2 = setting4.getActionSetting(booleanSupplier8);
      setting = new BooleanSetting(this.module, "SendThird", true, this.settingGroup);
      BooleanSupplier booleanSupplier9 = this::booleanValue;
      this.settingSendThird = (BooleanSetting)setting.getSetting(booleanSupplier9);
      setting = new BooleanSetting(this.module, "OnGroundThird", false, this.settingGroup);
      BooleanSupplier booleanSupplier10 = this::isActive4;
      this.settingOnGroundThird = (BooleanSetting)setting.getSetting(booleanSupplier10);
      ActionSetting setting5 = new ActionSetting(this.module, "YAddThird", 0.001, 0.001, 0.0, 1.0, 12, this.settingGroup);
      BooleanSupplier booleanSupplier11 = this::isActive9;
      this.actionSetting4 = setting5.getActionSetting(booleanSupplier11);
      setting = new BooleanSetting(this.module, "SendFourth", true, this.settingGroup);
      BooleanSupplier booleanSupplier12 = this::booleanValue;
      this.settingSendFourth = (BooleanSetting)setting.getSetting(booleanSupplier12);
      setting = new BooleanSetting(this.module, "OnGroundFourth", false, this.settingGroup);
      BooleanSupplier booleanSupplier13 = this::isActive3;
      this.settingOnGroundFourth = (BooleanSetting)setting.getSetting(booleanSupplier13);
      ActionSetting setting6 = new ActionSetting(this.module, "YAddFourth", 0.001, 0.001, 0.0, 1.0, 12, this.settingGroup);
      BooleanSupplier booleanSupplier14 = this::isActive6;
      this.actionSetting = setting6.getActionSetting(booleanSupplier14);
      this.count2 = -1;
   }
}
