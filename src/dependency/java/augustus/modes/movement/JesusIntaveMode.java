package augustus.modes.movement;

import augustus.modules.combat.Velocity;
import client.Client;
import client.event.EventChat;
import client.event.EventChatCallback;
import client.event.EventMove;
import client.event.EventPacket;
import client.event.EventSendPacket;
import client.event.MoveListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.util.ArrayListData;
import client.util.BooleanSupplier;
import client.util.CombatUtil;
import client.util.InternalStringArrayEntry;
import client.util.MoveUtil;
import client.util.Packet;
import client.util.SimulatedPlayer;
import client.util.TimeHelper;
import java.util.function.BiFunction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class JesusIntaveMode extends InternalStringArrayEntry implements PacketListener, EventChatCallback, NetworkPacketListener, MoveListener {
   public final NumberSetting settingXZOnHit;
   public final NumberSetting settingXZOnHitSprint;
   public final BooleanSetting settingIntaveSmart;
   public final BooleanSetting settingIntaveBoost;
   public final NumberSetting settingIntaveBoostDelay;
   public final NumberSetting settingIntaveBoostOnGroundTicks;
   public final NumberSetting settingXZVelocity;
   public final NumberSetting settingYVelocity;
   public final BooleanSetting settingSneakInAir;
   public final BooleanSetting settingWater;
   public final NumberSetting settingWaterInterval;
   public final BooleanSetting settingCobweb;
   public final BooleanSetting settingLadder;
   public final BooleanSetting settingLadderXZOnly;
   public final BooleanSetting settingJump;
   public final BooleanSetting settingJumpInInv;
   public final ModeSetting settingJumpDelayMode;
   public final RangeSetting settingMaxJumpTickDelay;
   public final RangeSetting settingMaxJumpDelay;
   public final NumberSetting settingJumpChance;
   public final BooleanSetting settingCheckFallDamage;
   public final NumberSetting settingFallDistance;
   public final NumberSetting settingFallDistanceBufferSize;
   public final BooleanSetting settingNotWhileSpeed;
   public final BooleanSetting settingNotWhileJumpBoost;
   public final TimeHelper timer;
   public final TimeHelper timer2;
   public int count3;
   public boolean enabled2;
   public int count2;
   public ArrayListData arrayListData;

   @Override
   public void onNetworkPacket(EventSendPacket packet) {
      long value = 2083205164L;
      short shortValue = 6282;
      if (this.isActive()) {
         Packet packet2 = packet.getPacket();
         if (packet2 instanceof S12PacketEntityVelocity
            && minecraft.thePlayer != null
            && minecraft.thePlayer.onGround
            && MoveUtil.canExecute()
            && !minecraft.thePlayer.isCollidedHorizontally
            && this.settingIntaveBoost.isEnabled()
            && (double)minecraft.thePlayer.indexOffset67 > this.settingIntaveBoostOnGroundTicks.getValue()
            && ((S12PacketEntityVelocity)packet2).getEntityId() == minecraft.thePlayer.getEntityId()) {
            TimeHelper timer3 = this.timer;
            double value2 = this.settingIntaveBoostDelay.getValue();
            if (timer3.hasReachedDouble(value2)) {
               this.timer.reset();
               this.enabled2 = true;
            }
         }

         boolean flag = minecraft.thePlayer != null && minecraft.thePlayer.ticksExisted > 20 && this.enabled2 && this.settingIntaveBoost.isEnabled();
         packet.setCancelled(flag);
      }
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new JesusIntaveMode((Velocity)item, (SettingGroup)group));
   }

   @Override
   public void updateState() {
      this.updateState3();
   }

   private boolean isActive2() {
      return this.booleanValue() && this.settingJump.isEnabled();
   }

   private boolean isActiveAsFlag() {
      return this.booleanValue() && this.settingJumpDelayMode.getSelected().equals("Tick") && this.settingJump.isEnabled();
   }

   private void applyS12PacketEntityVelocity(S12PacketEntityVelocity packet) {
      packet.setEntityId3(0);
      packet.setEntityId4(0);
   }

   private boolean isActive17() {
      return this.booleanValue() && this.settingIntaveBoost.isEnabled();
   }

   @Override
   public void onChat(EventChat event) {
      if (this.isActive()) {
         boolean flag = true;
         if (this.settingIntaveSmart.isEnabled() && minecraft.objectMouseOver.entityHit instanceof EntityLivingBase) {
            Entity entityHit = minecraft.objectMouseOver.entityHit;
            double value = this.settingXZOnHit.getValue();
            float yaw = minecraft.thePlayer.rotationYaw;
            double offset = value;
            Vec3 vec3 = CombatUtil.resolveVec3(true, entityHit, offset, yaw).getVec312(0.0, (double)minecraft.thePlayer.getHeight2(), 0.0);
            entityHit = minecraft.objectMouseOver.entityHit;
            value = this.settingXZOnHit.getValue();
            float yaw2 = minecraft.thePlayer.rotationYaw;
            double offset2 = value;
            Vec3 vec32 = CombatUtil.resolveVec3(false, entityHit, offset2, yaw2).getVec312(0.0, (double)minecraft.thePlayer.getHeight2(), 0.0);
            float height = minecraft.objectMouseOver.entityHit.getHeight();
            AxisAlignedBB boundingBox = minecraft.objectMouseOver.entityHit.getBoundingBox2().offset((double)height, (double)height, (double)height);
            Vec3 vec = SimulatedPlayer.processAction(vec3, boundingBox);
            Vec3 vec2 = SimulatedPlayer.processAction(vec32, boundingBox);
            if (vec3.getCoordinate4(vec) >= vec32.getCoordinate4(vec2)) {
               flag = false;
            }
         }

         if (minecraft.thePlayer.hurtTime != 0 && ((Velocity)this.module).keyCode2 == 0 && flag) {
            event.setEnabled(true);
            double value2 = this.settingXZOnHit.getValue();
            event.processAction(value2);
            event.setActive(false);
            Client.getClient().getClass();
         }
      }
   }

   private void updateState3() {
      this.settingMaxJumpDelay.recalculate();
      this.settingMaxJumpTickDelay.recalculate();
      this.timer2.reset();
      this.count2 = 0;
   }

   private boolean isActive20() {
      return this.booleanValue() && this.settingIntaveBoost.isEnabled();
   }

   private boolean isActive21() {
      return this.settingCobweb.isEnabled() && minecraft.thePlayer != null && minecraft.thePlayer.isInWeb();
   }

   private boolean isActive18() {
      boolean flag;
      if (this.settingJumpDelayMode.getSelected().equals("Delay")) {
         TimeHelper timer3 = this.timer2;
         long indexOffset = (long)this.settingMaxJumpDelay.getIndexOffset2();
         flag = timer3.hasReached(indexOffset);
      } else {
         flag = this.settingJumpDelayMode.getSelected().equals("Chance")
            ? (double)SettingContainer.settingProvider.getInt2(0, 99) < this.settingJumpChance.getValue()
            : this.count2 > this.settingMaxJumpTickDelay.getIndexOffset2();
      }

      return flag;
   }

   @Override
   public void clear() {
   }

   private boolean isActive9() {
      return this.booleanValue() && this.settingJump.isEnabled();
   }

   private boolean isActive19() {
      return this.booleanValue() && this.settingJumpDelayMode.getSelected().equals("Chance");
   }

   @Override
   public void onMove(EventMove event) {
      if (this.arrayListData.getCount() != (int)this.settingFallDistanceBufferSize.getDouble2(true)) {
         this.arrayListData = new ArrayListData((int)this.settingFallDistanceBufferSize.getDouble2(true));
      }

      if (!this.isActive()) {
         double fallDistance = (double)minecraft.thePlayer.fallDistance;
         this.arrayListData.processAction(fallDistance);
      } else {
         if (this.isActive7()) {
            if (this.settingJump.isEnabled() && this.isActive18()) {
               event.setState(true);
               this.updateState3();
            }

            this.count2++;
         }

         double fallDistance2 = (double)minecraft.thePlayer.fallDistance;
         this.arrayListData.processAction(fallDistance2);
         if (!this.settingIntaveBoost.isEnabled()) {
            this.enabled2 = false;
         } else {
            if (this.enabled2) {
               if (minecraft.thePlayer.onGround) {
                  event.setState4(true);
                  float factor = 0.0F;
                  event.setScaleFactor7(factor);
               }

               this.enabled2 = false;
            }

            if (this.settingSneakInAir.isEnabled() && minecraft.thePlayer.hurtTime > 2) {
               event.setState4(true);
            }
         }
      }
   }

   @Override
   public void onPacketPostSend(EventPacket packet) {
      Packet packet2 = packet.getPacket();
      if (packet2 instanceof S12PacketEntityVelocity) {
         S12PacketEntityVelocity packet3 = (S12PacketEntityVelocity)packet2;
         if (minecraft.thePlayer != null && packet3.getEntityId() == minecraft.thePlayer.getEntityId() && this.isActive()) {
            if (this.isActive15() && (double)this.count3 % this.settingWaterInterval.getValue() == 0.0) {
               this.applyS12PacketEntityVelocity(packet3);
               this.count3++;
            }

            if (this.isActive21()) {
               this.applyS12PacketEntityVelocity(packet3);
            }

            if (this.isActive4()) {
               this.applyS12PacketEntityVelocity(packet3);
               if (!this.settingLadderXZOnly.isEnabled()) {
                  packet3.setEntityId2(0);
               }
            }

            if (minecraft.thePlayer != null
               && minecraft.thePlayer.onGround
               && MoveUtil.canExecute()
               && !minecraft.thePlayer.isCollidedHorizontally
               && this.settingIntaveBoost.isEnabled()
               && (double)minecraft.thePlayer.indexOffset67 > this.settingIntaveBoostOnGroundTicks.getValue()
               && packet3.getEntityId() == minecraft.thePlayer.getEntityId()) {
               TimeHelper timer3 = this.timer;
               double value = this.settingIntaveBoostDelay.getValue();
               if (timer3.hasReachedDouble(value)) {
                  if (this.settingXZVelocity.getValue() == 0.0 && this.settingYVelocity.getValue() == 0.0) {
                     packet.cancel();
                     return;
                  }

                  packet3.setEntityId3((int)((double)packet3.getEntityId3() * this.settingXZVelocity.getValue() / 100.0));
                  packet3.setEntityId2((int)((double)packet3.getEntityId2() * this.settingYVelocity.getValue() / 100.0));
                  packet3.setEntityId4((int)((double)packet3.getEntityId4() * this.settingXZVelocity.getValue() / 100.0));
               }
            }
         }
      }
   }

   @Override
   public void onChat2(EventChat event) {
      if (this.isActive()) {
         if (minecraft.thePlayer.hurtTime != 0 && ((Velocity)this.module).keyCode2 == 0) {
            double value = this.settingXZOnHitSprint.getValue();
            event.processAction(value);
            event.setActive(false);
         }
      }
   }

   @Override
   public void updateState2() {
      byte byteValue = 26;
      this.applyIndex(byteValue);
      byte byteValue2 = 25;
      this.applyIndex(byteValue2);
      this.applyIndex(2, 100);
      byte byteValue3 = 21;
      this.applyIndex(byteValue3);
   }

   private boolean isActive8() {
      return this.settingCheckFallDamage.isVisible() && this.settingCheckFallDamage.isEnabled();
   }

   private boolean isActive16() {
      return this.booleanValue() && this.settingJump.isEnabled();
   }

   private boolean isActive10() {
      return this.settingJump.isVisible() && this.settingJump.isEnabled() && this.settingJumpDelayMode.getSelected().equals("Delay");
   }

   private boolean isActive6() {
      return this.booleanValue() && this.settingWater.isEnabled();
   }

   private boolean isActive4() {
      return this.settingLadder.isEnabled() && minecraft.thePlayer != null && minecraft.thePlayer.canExecute9();
   }

   private boolean isActive5() {
      return (!this.settingNotWhileSpeed.isEnabled() || !minecraft.thePlayer.checkPotion(Potion.potion2))
         && (!this.settingNotWhileJumpBoost.isEnabled() || !minecraft.thePlayer.checkPotion(Potion.potion9));
   }

   private boolean isActive13() {
      return this.booleanValue() && this.settingIntaveBoost.isEnabled();
   }

   private boolean isActive7() {
      return minecraft.thePlayer.isRiding()
         && (minecraft.isIntegratedServerRunning() || this.settingJumpInInv.isEnabled())
         && (this.arrayListData.getValueAsDouble() < this.settingFallDistance.getValue() || !this.settingCheckFallDamage.isEnabled())
         && !minecraft.thePlayer.canExecute2()
         && minecraft.thePlayer.onGround
         && minecraft.thePlayer.hurtTime == 9
         && this.isActive5();
   }

   private boolean isActive12() {
      return this.booleanValue() && this.settingIntaveBoost.isEnabled();
   }

   private boolean isActive3() {
      return this.booleanValue() && this.settingIntaveBoost.isEnabled();
   }

   private boolean isActive11() {
      return this.booleanValue() && this.settingCheckFallDamage.isVisible() && this.settingCheckFallDamage.isEnabled();
   }

   private boolean isActive14() {
      return this.booleanValue() && this.settingLadder.isEnabled();
   }

   private boolean isActive15() {
      return this.settingWater.isEnabled() && minecraft.thePlayer != null && minecraft.thePlayer.isInWater();
   }

   public JesusIntaveMode(Velocity velocity, SettingGroup settingGroup) {
        super(velocity, settingGroup, "Intave");
      
      NumberSetting setting = new NumberSetting(this.module, "XZOnHit", 0.6, -1.0, 1.0, 2, this.settingGroup);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingXZOnHit = setting.getNumberSetting(booleanSupplier);
      setting = new NumberSetting(this.module, "XZOnHitSprint", 0.6, -1.0, 1.0, 2, this.settingGroup);
      BooleanSupplier booleanSupplier2 = this::booleanValue;
      this.settingXZOnHitSprint = setting.getNumberSetting(booleanSupplier2);
      BooleanSetting setting2 = new BooleanSetting(this.module, "IntaveSmart", false, this.settingGroup);
      BooleanSupplier booleanSupplier3 = this::booleanValue;
      this.settingIntaveSmart = (BooleanSetting)setting2.getSetting(booleanSupplier3);
      BooleanSetting setting3 = new BooleanSetting(this.module, "IntaveBoost", false, this.settingGroup);
      BooleanSupplier booleanSupplier4 = this::booleanValue;
      this.settingIntaveBoost = (BooleanSetting)setting3.getSetting(booleanSupplier4);
      setting = new NumberSetting(this.module, "IntaveBoostDelay", 2000.0, 0.0, 10000.0, 0, this.settingGroup);
      BooleanSupplier booleanSupplier5 = this::isActive20;
      this.settingIntaveBoostDelay = setting.getNumberSetting(booleanSupplier5);
      setting = new NumberSetting(this.module, "IntaveBoostOnGroundTicks", 2.0, 0.0, 10.0, 0, this.settingGroup);
      BooleanSupplier booleanSupplier6 = this::isActive3;
      this.settingIntaveBoostOnGroundTicks = setting.getNumberSetting(booleanSupplier6);
      setting = new NumberSetting(this.module, "XZVelocityIntave", 100.0, -300.0, 300.0, 0, this.settingGroup);
      String text = "XZVelocity";
      setting = setting.getNumberSetting(text);
      BooleanSupplier booleanSupplier7 = this::isActive13;
      this.settingXZVelocity = setting.getNumberSetting(booleanSupplier7);
      setting = new NumberSetting(this.module, "YVelocityIntave", 100.0, -100.0, 200.0, 0, this.settingGroup);
      String text2 = "YVelocity";
      setting = setting.getNumberSetting(text2);
      BooleanSupplier booleanSupplier8 = this::isActive17;
      this.settingYVelocity = setting.getNumberSetting(booleanSupplier8);
      BooleanSetting setting4 = new BooleanSetting(this.module, "SneakInAir", false, this.settingGroup);
      BooleanSupplier booleanSupplier9 = this::isActive12;
      this.settingSneakInAir = (BooleanSetting)setting4.getSetting(booleanSupplier9);
      BooleanSetting setting5 = new BooleanSetting(this.module, "Water", true, this.settingGroup);
      BooleanSupplier booleanSupplier10 = this::booleanValue;
      this.settingWater = (BooleanSetting)setting5.getSetting(booleanSupplier10);
      setting = new NumberSetting(this.module, "WaterInterval", 1.0, 0.0, 3.0, 0, this.settingGroup);
      BooleanSupplier booleanSupplier11 = this::isActive6;
      this.settingWaterInterval = setting.getNumberSetting(booleanSupplier11);
      BooleanSetting setting6 = new BooleanSetting(this.module, "Cobweb", true, this.settingGroup);
      BooleanSupplier booleanSupplier12 = this::booleanValue;
      this.settingCobweb = (BooleanSetting)setting6.getSetting(booleanSupplier12);
      BooleanSetting setting7 = new BooleanSetting(this.module, "Ladder", true, this.settingGroup);
      BooleanSupplier booleanSupplier13 = this::booleanValue;
      this.settingLadder = (BooleanSetting)setting7.getSetting(booleanSupplier13);
      BooleanSetting setting8 = new BooleanSetting(this.module, "LadderXZOnly", true, this.settingGroup);
      BooleanSupplier booleanSupplier14 = this::isActive14;
      this.settingLadderXZOnly = (BooleanSetting)setting8.getSetting(booleanSupplier14);
      BooleanSetting setting9 = new BooleanSetting(this.module, "Jump", false, this.settingGroup);
      BooleanSupplier booleanSupplier15 = this::booleanValue;
      this.settingJump = (BooleanSetting)setting9.getSetting(booleanSupplier15);
      BooleanSetting setting10 = new BooleanSetting(this.module, "JumpInInvIntave", false, this.settingGroup);
      String text3 = "JumpInInv";
      BooleanSetting setting11 = (BooleanSetting)setting10.getSetting(text3);
      BooleanSupplier booleanSupplier16 = this::isActive16;
      this.settingJumpInInv = (BooleanSetting)setting11.getSetting(booleanSupplier16);
      ModeSetting setting12 = new ModeSetting(this.module, "IntaveJumpDelayMode", "Delay", new String[]{"Delay", "Tick", "Chance"}, this.settingGroup);
      String text4 = "JumpDelayMode";
      ModeSetting modeSetting = setting12.getModeSetting(text4);
      BooleanSupplier booleanSupplier17 = this::isActive9;
      this.settingJumpDelayMode = modeSetting.getModeSetting(booleanSupplier17);
      RangeSetting rangeSetting = (new RangeSetting(this.module, "JumpTicksIntave", 1.0, 3.0, 0.0, 10.0, 0, this.settingGroup)).getRangeSetting2();
      String text5 = "MaxJumpTickDelay";
      String text6 = "MinJumpTickDelay";
      RangeSetting rangeSetting2 = rangeSetting.getRangeSetting(text6, text5);
      BooleanSupplier booleanSupplier18 = this::isActiveAsFlag;
      this.settingMaxJumpTickDelay = rangeSetting2.getRangeSetting(15343, booleanSupplier18);
      RangeSetting rangeSetting3 = (new RangeSetting(this.module, "JumpDelayIntave", 0.0, 0.0, 0.0, 2000.0, 0, this.settingGroup)).getRangeSetting2();
      String text7 = "MaxJumpDelay";
      String text8 = "MinJumpDelay";
      RangeSetting rangeSetting4 = rangeSetting3.getRangeSetting(text8, text7);
      BooleanSupplier booleanSupplier19 = this::isActive10;
      this.settingMaxJumpDelay = rangeSetting4.getRangeSetting(15343, booleanSupplier19);
      setting = new NumberSetting(this.module, "JumpChanceIntave", 50.0, 0.0, 100.0, 0, this.settingGroup);
      String text9 = "JumpChance";
      setting = setting.getNumberSetting(text9);
      BooleanSupplier booleanSupplier20 = this::isActive19;
      this.settingJumpChance = setting.getNumberSetting(booleanSupplier20);
      BooleanSetting setting13 = new BooleanSetting(this.module, "CheckFallDamageIntave", true, this.settingGroup);
      String text10 = "CheckFallDamage";
      BooleanSetting setting14 = (BooleanSetting)setting13.getSetting(text10);
      BooleanSupplier booleanSupplier21 = this::isActive2;
      this.settingCheckFallDamage = (BooleanSetting)setting14.getSetting(booleanSupplier21);
      setting = new NumberSetting(this.module, "FallDistanceIntave", 3.5, 1.0, 10.0, 2, this.settingGroup);
      String text11 = "FallDistance";
      setting = setting.getNumberSetting(text11);
      BooleanSupplier booleanSupplier22 = this::isActive8;
      this.settingFallDistance = setting.getNumberSetting(booleanSupplier22);
      setting = new NumberSetting(this.module, "FallDistanceBufferSizeIntave", 2.0, 1.0, 5.0, 0, this.settingGroup);
      String text12 = "FallDistanceBufferSize";
      setting = setting.getNumberSetting(text12);
      BooleanSupplier booleanSupplier23 = this::isActive11;
      this.settingFallDistanceBufferSize = setting.getNumberSetting(booleanSupplier23);
      BooleanSetting setting15 = new BooleanSetting(this.module, "NotWhileSpeedIntave", true, this.settingGroup);
      String text13 = "NotWhileSpeed";
      BooleanSetting setting16 = (BooleanSetting)setting15.getSetting(text13);
      BooleanSupplier booleanSupplier24 = this::booleanValue;
      this.settingNotWhileSpeed = (BooleanSetting)setting16.getSetting(booleanSupplier24);
      BooleanSetting setting17 = new BooleanSetting(this.module, "NotWhileJumpBoostIntave", true, this.settingGroup);
      String text14 = "NotWhileJumpBoost";
      BooleanSetting setting18 = (BooleanSetting)setting17.getSetting(text14);
      BooleanSupplier booleanSupplier25 = this::booleanValue;
      this.settingNotWhileJumpBoost = (BooleanSetting)setting18.getSetting(booleanSupplier25);
      this.timer = new TimeHelper();
      this.timer2 = new TimeHelper();
      this.count3 = 0;
      this.count2 = 0;
      this.arrayListData = new ArrayListData(5);
   }
}
