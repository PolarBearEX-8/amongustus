package augustus.modes.movement;

import augustus.modules.combat.Velocity;
import client.event.EventMove;
import client.event.MoveListener;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.util.AnimationTimer;
import client.util.ArrayListData;
import client.util.BooleanSupplier;
import client.util.InternalStringArrayEntry;
import java.util.function.BiFunction;
import net.minecraft.potion.Potion;

public class JesusLegitMode extends InternalStringArrayEntry implements MoveListener {
   public final BooleanSetting settingJumpInInv;
   public final ModeSetting settingJumpDelayMode;
   public final RangeSetting settingJumpInterval;
   public final RangeSetting settingTickDelayBetweenJumps;
   public final NumberSetting settingJumpChance;
   public final BooleanSetting settingCheckFallDamage;
   public final NumberSetting settingFallDistance;
   public final NumberSetting settingFallDistanceBufferSize;
   public final BooleanSetting settingNotWhileSpeed;
   public final BooleanSetting settingNotWhileJumpBoost;
   public int count2;
   public ArrayListData arrayListData;
   public final AnimationTimer animationTimer;

   @Override
   public void updateState() {
      this.updateState3();
   }

   private boolean isActive2() {
      return this.booleanValue() && this.settingJumpDelayMode.getSelected().equals("IntervalDelay");
   }

   private boolean isActiveAsFlag() {
      return (!this.settingNotWhileSpeed.isEnabled() || !minecraft.thePlayer.checkPotion(Potion.potion2))
         && (!this.settingNotWhileJumpBoost.isEnabled() || !minecraft.thePlayer.checkPotion(Potion.potion9));
   }

   private boolean isActive3() {
      return this.booleanValue() && this.settingCheckFallDamage.isEnabled();
   }

   private void updateState3() {
      this.settingTickDelayBetweenJumps.recalculate();
      this.settingJumpInterval.recalculate();
      this.animationTimer.processAction3();
      this.count2 = 0;
   }

   private boolean isActive6() {
      return this.booleanValue() && this.settingCheckFallDamage.isEnabled();
   }

   private boolean isActive8() {
      byte byteValue = 0;
      boolean flag;
      if (this.settingJumpDelayMode.getSelected().equals("Chance")) {
         flag = (double)SettingContainer.settingProvider.getInt2(0, 99) < this.settingJumpChance.getValue();
      } else {
         AnimationTimer animationTimer2 = this.animationTimer;
         int indexOffset = this.settingTickDelayBetweenJumps.getIndexOffset2();
         flag = animationTimer2.checkCondition(indexOffset) && this.count2 > this.settingJumpInterval.getIndexOffset2();
      }

      return flag;
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new JesusLegitMode((Velocity)item, (SettingGroup)group));
   }

   @Override
   public void clear() {
   }

   private boolean isActive4() {
      return this.booleanValue() && this.settingJumpDelayMode.getSelected().equals("IntervalDelay");
   }

   private boolean isActive5() {
      return minecraft.thePlayer.isRiding()
         && (minecraft.isIntegratedServerRunning() || this.settingJumpInInv.isEnabled())
         && (this.arrayListData.getValueAsDouble() < this.settingFallDistance.getValue() || !this.settingCheckFallDamage.isEnabled())
         && !minecraft.thePlayer.canExecute2()
         && minecraft.thePlayer.onGround
         && minecraft.thePlayer.hurtTime == 9
         && this.isActiveAsFlag();
   }

   @Override
   public void onMove(EventMove event) {
      if (this.arrayListData.getCount() != (int)this.settingFallDistanceBufferSize.getDouble2(true)) {
         this.arrayListData = new ArrayListData((int)this.settingFallDistanceBufferSize.getDouble2(true));
      }

      this.animationTimer.processAction();
      if (!this.isActive()) {
         double fallDistance = (double)minecraft.thePlayer.fallDistance;
         this.arrayListData.processAction(fallDistance);
      } else {
         if (this.isActive5()) {
            if (this.isActive8() && (minecraft.isIntegratedServerRunning() || this.settingJumpInInv.isEnabled())) {
               event.setState(true);
               this.updateState3();
               this.count2++;
               return;
            }

            this.count2++;
         }

         double fallDistance2 = (double)minecraft.thePlayer.fallDistance;
         this.arrayListData.processAction(fallDistance2);
      }
   }

   @Override
   public void updateState2() {
      byte byteValue = 21;
      this.applyIndex(byteValue);
   }

   private boolean isActive7() {
      return this.booleanValue() && this.settingJumpDelayMode.getSelected().equals("Chance");
   }

   public JesusLegitMode(Velocity velocity, SettingGroup settingGroup) {
        super(velocity, settingGroup, "Legit");
      
      BooleanSetting setting = new BooleanSetting(this.module, "JumpInInv", false, this.settingGroup);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingJumpInInv = (BooleanSetting)setting.getSetting(booleanSupplier);
      ModeSetting setting2 = new ModeSetting(this.module, "LegitJumpDelayMode", "IntervalDelay", new String[]{"IntervalDelay", "Chance"}, this.settingGroup);
      String text = "JumpDelayMode";
      ModeSetting modeSetting = setting2.getModeSetting(text);
      BooleanSupplier booleanSupplier2 = this::booleanValue;
      this.settingJumpDelayMode = modeSetting.getModeSetting(booleanSupplier2);
      RangeSetting setting3 = new RangeSetting(this.module, "JumpInterval", 0.0, 0.0, 0.0, 5.0, 0, this.settingGroup);
      BooleanSupplier booleanSupplier3 = this::isActive2;
      RangeSetting rangeSetting = setting3.getRangeSetting(15343, booleanSupplier3);
      double offset = 30.0;
      double offset2 = 0.0;
      double offset3 = 5.0;
      double offset4 = 0.0;
      double offset5 = 3.0;
      double offset6 = 0.0;
      RangeSetting setting4 = rangeSetting.resolveRangeSettingByDouble(offset6, offset5, offset4, offset3, offset2, offset, false);
      double offset7 = 5.0;
      double offset8 = 0.5;
      double offset9 = 1.0;
      this.settingJumpInterval = setting4.getRangeSetting4(offset9, offset8, offset7, false);
      RangeSetting rangeSetting2 = (new RangeSetting(this.module, "TickDelayBetweenJumps", 10.0, 10.0, 0.0, 100.0, 0, this.settingGroup)).getRangeSetting2();
      BooleanSupplier booleanSupplier4 = this::isActive4;
      this.settingTickDelayBetweenJumps = rangeSetting2.getRangeSetting(15343, booleanSupplier4);
      NumberSetting setting5 = new NumberSetting(this.module, "JumpChanceLegit", 50.0, 0.0, 100.0, 0, this.settingGroup);
      String text2 = "JumpChance";
      NumberSetting numberSetting = setting5.getNumberSetting(text2);
      BooleanSupplier booleanSupplier5 = this::isActive7;
      this.settingJumpChance = numberSetting.getNumberSetting(booleanSupplier5);
      setting = new BooleanSetting(this.module, "CheckFallDamageLegit", true, this.settingGroup);
      String text3 = "CheckFallDamage";
      setting = (BooleanSetting)setting.getSetting(text3);
      BooleanSupplier booleanSupplier6 = this::booleanValue;
      this.settingCheckFallDamage = (BooleanSetting)setting.getSetting(booleanSupplier6);
      NumberSetting setting6 = new NumberSetting(this.module, "FallDistanceLegit", 3.0, 1.0, 10.0, 2, this.settingGroup);
      String text4 = "FallDistance";
      NumberSetting numberSetting2 = setting6.getNumberSetting(text4);
      BooleanSupplier booleanSupplier7 = this::isActive6;
      this.settingFallDistance = numberSetting2.getNumberSetting(booleanSupplier7);
      NumberSetting setting7 = new NumberSetting(this.module, "FallDistanceBufferSizeLegit", 2.0, 1.0, 5.0, 0, this.settingGroup);
      String text5 = "FallDistanceBufferSize";
      NumberSetting numberSetting3 = setting7.getNumberSetting(text5);
      BooleanSupplier booleanSupplier8 = this::isActive3;
      this.settingFallDistanceBufferSize = numberSetting3.getNumberSetting(booleanSupplier8);
      setting = new BooleanSetting(this.module, "NotWhileSpeedLegit", true, this.settingGroup);
      String text6 = "NotWhileSpeed";
      setting = (BooleanSetting)setting.getSetting(text6);
      BooleanSupplier booleanSupplier9 = this::booleanValue;
      this.settingNotWhileSpeed = (BooleanSetting)setting.getSetting(booleanSupplier9);
      setting = new BooleanSetting(this.module, "NotWhileJumpBoostLegit", true, this.settingGroup);
      String text7 = "NotWhileJumpBoost";
      setting = (BooleanSetting)setting.getSetting(text7);
      BooleanSupplier booleanSupplier10 = this::booleanValue;
      this.settingNotWhileJumpBoost = (BooleanSetting)setting.getSetting(booleanSupplier10);
      this.count2 = 0;
      this.arrayListData = new ArrayListData(5);
      this.animationTimer = new AnimationTimer();
   }
}
