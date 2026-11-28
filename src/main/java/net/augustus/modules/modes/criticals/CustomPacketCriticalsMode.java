package net.augustus.modules.modes.criticals;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.augustus.events.EventAttackEntity;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.settings.RangeSetting;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.math.RandomNumberGenerator;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Criticals;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class CustomPacketCriticalsMode extends CriticalsMode implements AttackEntityListener {
   private final BooleanValue useC06;
   public final DoubleValue interval;
   private final BooleanValue sendFirst;
   private final BooleanValue onGroundFirst;
   private final RangeSetting yAddFirst;
   private final BooleanValue sendSecond;
   private final BooleanValue onGroundSecond;
   private final RangeSetting yAddSecond;
   private final BooleanValue sendThird;
   private final BooleanValue onGroundThird;
   private final RangeSetting yAddThird;
   private final BooleanValue sendFourth;
   private final BooleanValue onGroundFourth;
   private final RangeSetting yAddFourth;
   private int attackIntervalCounter;

   public CustomPacketCriticalsMode(Criticals criticals, SettingGroup doubleValue) {
       super(criticals, doubleValue, "CustomPacket");
      this.useC06 = (BooleanValue)new BooleanValue(this.parentModule, "UseC06", false, this.modeSetting).withVisibility(() -> this.isSelected());
      this.interval = new DoubleValue(this.parentModule, "Interval", 3.0, 0.0, 10.0, 0, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.sendFirst = (BooleanValue)new BooleanValue(this.parentModule, "SendFirst", true, this.modeSetting).withVisibility(() -> this.isSelected());
      this.onGroundFirst = (BooleanValue)new BooleanValue(this.parentModule, "OnGroundFirst", false, this.modeSetting).withVisibility(() -> this.isFirstOnGroundVisible());
      this.yAddFirst = new RangeSetting(this.parentModule, "YAddFirst", 0.001, 0.001, 0.0, 1.0, 12, this.modeSetting).visibleWhen(() -> this.isFirstYAddVisible());
      this.sendSecond = (BooleanValue)new BooleanValue(this.parentModule, "SendSecond", true, this.modeSetting).withVisibility(() -> this.isSelected());
      BooleanValue booleanValue5 = new BooleanValue(this.parentModule, "OnGroundSecond", false, this.modeSetting);
      SettingVisibilityCondition condition7 = () -> {
         return this.isSelected() && this.sendSecond.getValue();
      };
      this.onGroundSecond = (BooleanValue)booleanValue5.withVisibility(condition7);
      RangeSetting rangeSetting2 = new RangeSetting(this.parentModule, "YAddSecond", 0.001, 0.001, 0.0, 1.0, 12, this.modeSetting);
      SettingVisibilityCondition condition8 = () -> {
         return this.isSelected() && this.sendSecond.getValue();
      };
      this.yAddSecond = rangeSetting2.visibleWhen(condition8);
      this.sendThird = (BooleanValue)new BooleanValue(this.parentModule, "SendThird", true, this.modeSetting).withVisibility(() -> this.isSelected());
      this.onGroundThird = (BooleanValue)new BooleanValue(this.parentModule, "OnGroundThird", false, this.modeSetting).withVisibility(() -> this.isThirdOnGroundVisible());
      this.yAddThird = new RangeSetting(this.parentModule, "YAddThird", 0.001, 0.001, 0.0, 1.0, 12, this.modeSetting).visibleWhen(() -> this.isThirdYAddVisible());
      this.sendFourth = (BooleanValue)new BooleanValue(this.parentModule, "SendFourth", true, this.modeSetting).withVisibility(() -> this.isSelected());
      BooleanValue booleanValue9 = new BooleanValue(this.parentModule, "OnGroundFourth", false, this.modeSetting);
      SettingVisibilityCondition condition13 = () -> {
         return this.isSelected() && this.sendFourth.getValue();
      };
      this.onGroundFourth = (BooleanValue)booleanValue9.withVisibility(condition13);
      this.yAddFourth = new RangeSetting(this.parentModule, "YAddFourth", 0.001, 0.001, 0.0, 1.0, 12, this.modeSetting).visibleWhen(() -> this.isFourthYAddVisible());
      this.attackIntervalCounter = -1;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(1);
   }

   @Override
   public void onEnable() {
      this.attackIntervalCounter = -1;
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new CustomPacketCriticalsMode((Criticals)obj, (SettingGroup)obj2);
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      this.attackIntervalCounter++;
      if (this.shouldCrit() && !((double)this.attackIntervalCounter < this.interval.getValue())) {
         this.attackIntervalCounter = -1;
         if (this.sendFirst.getValue()) {
            RandomNumberGenerator instance = DefaultRandomGenerator.instance;
            double minValue = this.yAddFirst.getMinValue();
            minValue = instance.nextDouble(minValue, this.yAddFirst.getMaxValue());
            this.sendOffsetPacket(minValue, this.onGroundFirst.getValue());
         }

         if (this.sendSecond.getValue()) {
            RandomNumberGenerator instance2 = DefaultRandomGenerator.instance;
            double minValue2 = this.yAddSecond.getMinValue();
            minValue2 = instance2.nextDouble(minValue2, this.yAddSecond.getMaxValue());
            this.sendOffsetPacket(minValue2, this.onGroundSecond.getValue());
         }

         if (this.sendThird.getValue()) {
            RandomNumberGenerator instance3 = DefaultRandomGenerator.instance;
            double minValue3 = this.yAddThird.getMinValue();
            minValue3 = instance3.nextDouble(minValue3, this.yAddThird.getMaxValue());
            this.sendOffsetPacket(minValue3, this.onGroundThird.getValue());
         }

         if (this.sendFourth.getValue()) {
            RandomNumberGenerator instance4 = DefaultRandomGenerator.instance;
            double minValue4 = this.yAddFourth.getMinValue();
            minValue4 = instance4.nextDouble(minValue4, this.yAddFourth.getMaxValue());
            this.sendOffsetPacket(minValue4, this.onGroundFourth.getValue());
         }
      }
   }

   private void sendOffsetPacket(double d, boolean flag) {
      if (this.useC06.getValue()) {
         mc.thePlayer
            .sendQueue
            .addToSendQueueUnchecked(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY + d, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, flag));
      } else {
         mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + d, mc.thePlayer.posZ, flag));
      }
   }

   private boolean isFourthYAddVisible() {
      return this.isSelected() && this.sendFourth.getValue();
   }

   private boolean isThirdYAddVisible() {
      return this.isSelected() && this.sendThird.getValue();
   }

   private boolean isThirdOnGroundVisible() {
      return this.isSelected() && this.sendThird.getValue();
   }

   private boolean isFirstYAddVisible() {
      return this.isSelected() && this.sendFirst.getValue();
   }

   private boolean isFirstOnGroundVisible() {
      return this.isSelected() && this.sendFirst.getValue();
   }

}
