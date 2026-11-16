package client.setting;

import augustus.modules.combat.KillAura;
import client.Client;
import client.ClientGlobals;
import client.event.EventClick;
import client.font.FontRegistry;
import client.gui.ClickGuiScreen;
import client.module.Module;
import client.rotation.Rotation;
import client.util.AuxiliaryModuleAdapter;
import client.util.CombatUtil;
import client.util.Listener72Entry;
import client.util.MinecraftAccessor;
import client.util.RayTraceUtils;
import client.util.SimulatedPlayer;
import client.util.SlotManager;
import client.util.TargetParser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class StandardSettingArrayEntry implements MinecraftAccessor {
   public static boolean enabled = false;

   public static void updateState() {
      short shortValue = 3355;
      short shortValue2 = 9785;
      short shortValue3 = -30665;
      long keySalt = ((long)shortValue << 32 | (long)shortValue2 << 48 >>> 32 | (long)shortValue3 << 48 >>> 48) ^ 0x5edb93c4a366L;
      int count = (int)((((long)shortValue << 32 | (long)shortValue2 << 48 >>> 32 | (long)shortValue3 << 48 >>> 48) ^ 0x5edb93c4a366L ^ 0x70799ac8ee7cL) << 16 >>> 32);
      long keySalt2 = keySalt ^ 0x298fd1b7e8f9L;
      int count2 = (int)((keySalt ^ 0x298fd1b7e8f9L) >>> 48);
      int count3 = (int)((keySalt ^ 0x298fd1b7e8f9L) << 16 >>> 32);
      int count4 = (int)(keySalt2 << 48 >>> 48);
      int count5 = (int)((keySalt ^ 0x1da8551bb2e1L) << 16 >>> 48);
      int count6 = (int)((keySalt ^ 0x1da8551bb2e1L) << 32 >>> 32);
      if (!enabled) {
         enabled = true;
         System.currentTimeMillis();
         SimulatedPlayer simulatedPlayer = new SimulatedPlayer();
         EntityPlayerSP player = MinecraftAccessor.minecraft.thePlayer;
         RangeSetting settingYawAcceleration = ClientGlobals.moduleManager.killAura.settingYawAcceleration;
         RangeSetting settingPitchAcceleration = ClientGlobals.moduleManager.killAura.settingPitchAcceleration;
         RangeSetting settingYawSpeed = ClientGlobals.moduleManager.killAura.settingYawSpeed;
         RangeSetting settingPitchSpeed = ClientGlobals.moduleManager.killAura.settingPitchSpeed;
         float yaw = MinecraftAccessor.minecraft.thePlayer.rotationYaw;
         float pitch = MinecraftAccessor.minecraft.thePlayer.rotationPitch;
         String selected = ClientGlobals.moduleManager.killAura.settingOnlyRotation.getSelected();
         boolean enabled2 = ClientGlobals.moduleManager.killAura.settingInterpolation.isEnabled();
         boolean enabled3 = ClientGlobals.moduleManager.killAura.settingSmartAim.isEnabled();
         boolean enabled4 = ClientGlobals.moduleManager.killAura.settingIntave13.isEnabled();
         float value = (float)ClientGlobals.moduleManager.killAura.settingRandomStrength.getValue();
         boolean flag = ClientGlobals.moduleManager.killAura.settingThroughWalls.isEnabled()
            && ClientGlobals.moduleManager.killAura.settingAimThroughWalls.isEnabled();
         boolean enabled5 = ClientGlobals.moduleManager.killAura.settingAdvancedRots.isEnabled();
         String selected2 = ClientGlobals.moduleManager.killAura.settingNone2.getSelected();
         String selected3 = ClientGlobals.moduleManager.killAura.settingBestHitVec.getSelected();
         int value2 = (int)ClientGlobals.moduleManager.killAura.settingHitBoxPercentageVertical2.getValue();
         int value3 = (int)ClientGlobals.moduleManager.killAura.settingHitBoxPercentageHorizontal2.getValue();
         String selected4 = ClientGlobals.moduleManager.killAura.settingSecureRandom.getSelected();
         String selected5 = ClientGlobals.moduleManager.killAura.settingNone4.getSelected();
         boolean enabled6 = ClientGlobals.moduleManager.killAura.settingSmartRotate.isEnabled();
         boolean enabled7 = ClientGlobals.moduleManager.killAura.settingReducedRotate.isEnabled();
         boolean enabled8 = ClientGlobals.moduleManager.killAura.settingPitchOutliers.isEnabled();
         RangeSetting settingPitchOutliersAdd = ClientGlobals.moduleManager.killAura.settingPitchOutliersAdd;
         RangeSetting settingPitchOutliersDelay = ClientGlobals.moduleManager.killAura.settingPitchOutliersDelay;
         double value4 = ClientGlobals.moduleManager.killAura.settingReduceRotationPercentage.getValue();
         float value5 = (float)ClientGlobals.moduleManager.killAura.settingMaxXAbsDist.getValue();
         float value6 = (float)ClientGlobals.moduleManager.killAura.settingMaxYAbsDist.getValue();
         float value7 = (float)ClientGlobals.moduleManager.killAura.settingMinAbsDist.getValue();
         float doubleItem = (float)ClientGlobals.moduleManager.killAura.settingXRelDist.getDouble3();
         float doubleItem2 = (float)ClientGlobals.moduleManager.killAura.settingYRelDist.getDouble3();
         float doubleItem3 = (float)ClientGlobals.moduleManager.killAura.settingXRelDist.getDouble6();
         float doubleItem4 = (float)ClientGlobals.moduleManager.killAura.settingYRelDist.getDouble6();
         float value8 = (float)ClientGlobals.moduleManager.killAura.settingMaxRotationAngle.getValue();
         float doubleItem5 = (float)ClientGlobals.moduleManager.killAura.settingRadius.getDouble3();
         float value9 = (float)ClientGlobals.moduleManager.killAura.settingBestHitXMinDist.getValue();
         float value10 = (float)ClientGlobals.moduleManager.killAura.settingBestHitYMInDist.getValue();
         boolean enabled9 = ClientGlobals.moduleManager.killAura.settingBetterAcceleration.isEnabled();
         int doubleItem6 = (int)ClientGlobals.moduleManager.killAura.settingFixRotationChance.getDouble2(true);
         double offset = ClientGlobals.moduleManager.killAura.settingMaxFixRotationReach.recalculate();
         double value11 = ClientGlobals.moduleManager.killAura.settingMinFixRotationAngle.getValue();
         double value12 = ClientGlobals.moduleManager.killAura.settingMinDistanceToBestVecYaw.getValue();
         double value13 = ClientGlobals.moduleManager.killAura.settingMinDistanceToBestVecPitch.getValue();
         double value14 = ClientGlobals.moduleManager.killAura.settingMinDistanceToBestVec.getValue();
         String selected6 = ClientGlobals.moduleManager.killAura.settingNone.getSelected();
         boolean enabled10 = ClientGlobals.moduleManager.killAura.settingStopRotationInsideTarget.isEnabled();
         String text = selected6;
         double offset2 = value14;
         double offset3 = value13;
         double offset4 = value12;
         double offset5 = value11;
         double offset6 = offset;
         int count7 = doubleItem6;
         boolean flag2 = enabled9;
         float factor = value10;
         float factor2 = value9;
         float factor3 = doubleItem5;
         float factor4 = value8;
         float factor5 = doubleItem4;
         float factor6 = doubleItem3;
         float factor7 = doubleItem2;
         float factor8 = doubleItem;
         float factor9 = value7;
         float factor10 = value6;
         float factor11 = value5;
         double offset7 = value4;
         RangeSetting setting = settingPitchOutliersDelay;
         RangeSetting setting2 = settingPitchOutliersAdd;
         boolean flag3 = enabled8;
         boolean flag4 = enabled7;
         boolean flag5 = enabled6;
         String text2 = selected5;
         String text3 = selected4;
         byte byteValue = 0;
         int count8 = value3;
         int count9 = value2;
         double offset8 = 3.0;
         String text4 = selected3;
         String text5 = selected2;
         boolean flag6 = enabled5;
         boolean flag7 = flag;
         Object entry = null;
         float factor12 = value;
         boolean flag8 = enabled4;
         boolean flag9 = enabled3;
         boolean flag10 = enabled2;
         String text6 = selected;
         float factor13 = pitch;
         float factor14 = yaw;
         RangeSetting setting3 = settingPitchSpeed;
         RangeSetting setting4 = settingYawSpeed;
         RangeSetting setting5 = settingPitchAcceleration;
         RangeSetting setting6 = settingYawAcceleration;
         EntityPlayerSP player2 = player;
         simulatedPlayer.getRotation(
            player2,
            setting6,
            setting5,
            setting4,
            setting3,
            factor14,
            count,
            factor13,
            text6,
            flag10,
            flag9,
            flag8,
            factor12,
            (Vec3)entry,
            flag7,
            flag6,
            text5,
            text4,
            offset8,
            count9,
            count8,
            byteValue,
            text3,
            text2,
            flag5,
            flag4,
            flag3,
            setting2,
            setting,
            offset7,
            factor11,
            factor10,
            factor9,
            factor8,
            factor7,
            factor6,
            factor5,
            factor4,
            factor3,
            factor2,
            factor,
            flag2,
            count7,
            offset6,
            offset5,
            offset4,
            offset3,
            offset2,
            text,
            enabled10
         );
         EntityPlayerSP player3 = MinecraftAccessor.minecraft.thePlayer;
         settingYawAcceleration = ClientGlobals.moduleManager.killAura.settingYawAcceleration;
         settingPitchAcceleration = ClientGlobals.moduleManager.killAura.settingPitchAcceleration;
         settingYawSpeed = ClientGlobals.moduleManager.killAura.settingYawSpeed;
         settingPitchSpeed = ClientGlobals.moduleManager.killAura.settingPitchSpeed;
         yaw = MinecraftAccessor.minecraft.thePlayer.rotationYaw;
         pitch = MinecraftAccessor.minecraft.thePlayer.rotationPitch;
         selected = ClientGlobals.moduleManager.killAura.settingOnlyRotation.getSelected();
         enabled2 = ClientGlobals.moduleManager.killAura.settingInterpolation.isEnabled();
         enabled3 = ClientGlobals.moduleManager.killAura.settingSmartAim.isEnabled();
         enabled4 = ClientGlobals.moduleManager.killAura.settingIntave13.isEnabled();
         value = (float)ClientGlobals.moduleManager.killAura.settingRandomStrength.getValue();
         flag = ClientGlobals.moduleManager.killAura.settingThroughWalls.isEnabled()
            && ClientGlobals.moduleManager.killAura.settingAimThroughWalls.isEnabled();
         enabled5 = ClientGlobals.moduleManager.killAura.settingAdvancedRots.isEnabled();
         selected2 = ClientGlobals.moduleManager.killAura.settingNone2.getSelected();
         selected3 = ClientGlobals.moduleManager.killAura.settingBestHitVec.getSelected();
         value2 = (int)ClientGlobals.moduleManager.killAura.settingHitBoxPercentageVertical2.getValue();
         value3 = (int)ClientGlobals.moduleManager.killAura.settingHitBoxPercentageHorizontal2.getValue();
         selected4 = ClientGlobals.moduleManager.killAura.settingSecureRandom.getSelected();
         selected5 = ClientGlobals.moduleManager.killAura.settingNone4.getSelected();
         enabled6 = ClientGlobals.moduleManager.killAura.settingSmartRotate.isEnabled();
         enabled7 = ClientGlobals.moduleManager.killAura.settingReducedRotate.isEnabled();
         enabled8 = ClientGlobals.moduleManager.killAura.settingPitchOutliers.isEnabled();
         settingPitchOutliersAdd = ClientGlobals.moduleManager.killAura.settingPitchOutliersAdd;
         settingPitchOutliersDelay = ClientGlobals.moduleManager.killAura.settingPitchOutliersDelay;
         value4 = ClientGlobals.moduleManager.killAura.settingReduceRotationPercentage.getValue();
         value5 = (float)ClientGlobals.moduleManager.killAura.settingMaxXAbsDist.getValue();
         value6 = (float)ClientGlobals.moduleManager.killAura.settingMaxYAbsDist.getValue();
         value7 = (float)ClientGlobals.moduleManager.killAura.settingMinAbsDist.getValue();
         doubleItem = (float)ClientGlobals.moduleManager.killAura.settingXRelDist.getDouble3();
         doubleItem2 = (float)ClientGlobals.moduleManager.killAura.settingYRelDist.getDouble3();
         doubleItem3 = (float)ClientGlobals.moduleManager.killAura.settingXRelDist.getDouble6();
         doubleItem4 = (float)ClientGlobals.moduleManager.killAura.settingYRelDist.getDouble6();
         value8 = (float)ClientGlobals.moduleManager.killAura.settingMaxRotationAngle.getValue();
         doubleItem5 = (float)ClientGlobals.moduleManager.killAura.settingRadius.getDouble3();
         value9 = (float)ClientGlobals.moduleManager.killAura.settingBestHitXMinDist.getValue();
         value10 = (float)ClientGlobals.moduleManager.killAura.settingBestHitYMInDist.getValue();
         enabled9 = ClientGlobals.moduleManager.killAura.settingBetterAcceleration.isEnabled();
         doubleItem6 = (int)ClientGlobals.moduleManager.killAura.settingFixRotationChance.getDouble2(true);
         offset = ClientGlobals.moduleManager.killAura.settingMaxFixRotationReach.recalculate();
         value11 = ClientGlobals.moduleManager.killAura.settingMinFixRotationAngle.getValue();
         value12 = ClientGlobals.moduleManager.killAura.settingMinDistanceToBestVecYaw.getValue();
         value13 = ClientGlobals.moduleManager.killAura.settingMinDistanceToBestVecPitch.getValue();
         value14 = ClientGlobals.moduleManager.killAura.settingMinDistanceToBestVec.getValue();
         selected6 = ClientGlobals.moduleManager.killAura.settingNone.getSelected();
         boolean enabled11 = ClientGlobals.moduleManager.killAura.settingStopRotationInsideTarget.isEnabled();
         String text7 = selected6;
         double offset9 = value14;
         double offset10 = value13;
         double offset11 = value12;
         double offset12 = value11;
         double offset13 = offset;
         int count10 = doubleItem6;
         boolean flag11 = enabled9;
         float factor15 = value10;
         float factor16 = value9;
         float factor17 = doubleItem5;
         float factor18 = value8;
         float factor19 = doubleItem4;
         float factor20 = doubleItem3;
         float factor21 = doubleItem2;
         float factor22 = doubleItem;
         float factor23 = value7;
         float factor24 = value6;
         float factor25 = value5;
         double offset14 = value4;
         RangeSetting setting7 = settingPitchOutliersDelay;
         RangeSetting setting8 = settingPitchOutliersAdd;
         boolean flag12 = enabled8;
         boolean flag13 = enabled7;
         boolean flag14 = enabled6;
         String text8 = selected5;
         String text9 = selected4;
         byte byteValue2 = 0;
         int count11 = value3;
         int count12 = value2;
         double offset15 = 3.0;
         String text10 = selected3;
         String text11 = selected2;
         boolean flag15 = enabled5;
         boolean flag16 = flag;
         Object entry2 = null;
         float factor26 = value;
         boolean flag17 = enabled4;
         boolean flag18 = enabled3;
         boolean flag19 = enabled2;
         String text12 = selected;
         float factor27 = pitch;
         float factor28 = yaw;
         RangeSetting setting9 = settingPitchSpeed;
         RangeSetting setting10 = settingYawSpeed;
         RangeSetting setting11 = settingPitchAcceleration;
         RangeSetting setting12 = settingYawAcceleration;
         EntityPlayerSP player4 = player3;
         simulatedPlayer.getRotation(
            player4,
            setting12,
            setting11,
            setting10,
            setting9,
            factor28,
            count,
            factor27,
            text12,
            flag19,
            flag18,
            flag17,
            factor26,
            (Vec3)entry2,
            flag16,
            flag15,
            text11,
            text10,
            offset15,
            count12,
            count11,
            byteValue2,
            text9,
            text8,
            flag14,
            flag13,
            flag12,
            setting8,
            setting7,
            offset14,
            factor25,
            factor24,
            factor23,
            factor22,
            factor21,
            factor20,
            factor19,
            factor18,
            factor17,
            factor16,
            factor15,
            flag11,
            count10,
            offset13,
            offset12,
            offset11,
            offset10,
            offset9,
            text7,
            enabled11
         );
         SimulatedPlayer.getVec3(new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0));
         short shortValue4 = (short)count5;
         SimulatedPlayer.getVec3(MinecraftAccessor.minecraft.thePlayer, shortValue4, count6);
         EntityPlayerSP player5 = MinecraftAccessor.minecraft.thePlayer;
         SimulatedPlayer.processAction(MinecraftAccessor.minecraft.thePlayer, player5);
         Vec3 vec = new Vec3(0.0, 0.0, 0.0);
         EntityPlayerSP player6 = MinecraftAccessor.minecraft.thePlayer;
         Vec3 vec2 = new Vec3(0.0, 0.0, 0.0);
         EntityPlayerSP player7 = player6;
         SimulatedPlayer.getVec32(vec, player7, vec2);
         float factor29 = 6.0F;
         simulatedPlayer.processAction(factor29, 5);
         Rotation rotation = new Rotation(20.0F, 30.0F);
         Rotation rotation2 = new Rotation(34.0F, 56.0F);
         Rotation rotation3 = new Rotation(100.0F, 102.0F);
         Rotation rotation4 = rotation2;
         SimulatedPlayer.getCoordinate(rotation, rotation4, rotation3);
         simulatedPlayer.updateState();
         SimulatedPlayer.checkCondition(10.0F);
         SimulatedPlayer.getFloat2(10.0F, 10.0F);
         SimulatedPlayer.getFloat3(10.0F, 10.0F);
         ClientGlobals.moduleManager.scaffold.getPlaceTarget();
         float factor30 = 10.0F;
         ClientGlobals.moduleManager.scaffold.checkCondition(factor30);
         ClientGlobals.moduleManager.scaffold.getFloat(false);
         Rotation rotation5 = new Rotation(10.0F, 10.0F);
         Rotation rotation6 = new Rotation(10.0F, 10.0F);
         double offset16 = 10.0;
         double offset17 = 0.3;
         byte byteValue3 = 1;
         Rotation rotation7 = rotation6;
         Rotation rotation8 = rotation5;
         simulatedPlayer.getSecondaryDoubleEntry(rotation8, rotation7, byteValue3, offset17, offset16, false);
         CombatUtil.getCoordOffsetArray(0, MinecraftAccessor.minecraft.thePlayer);
         byte byteValue4 = -1;
         SlotManager.applyModule3((Module)null, byteValue4, -1);

         try {
            Framebuffer framebuffer = new Framebuffer(MinecraftAccessor.minecraft.displayWidth, MinecraftAccessor.minecraft.displayHeight, false);
            framebuffer.setState(false);
            ClickGuiScreen clickGuiScreen = Client.getClient().getClickGuiScreen();
            Minecraft mc = Minecraft.getMinecraft();
            clickGuiScreen.setMc(mc);
            ClickGuiScreen clickGuiScreen2 = Client.getClient().getClickGuiScreen();
            float factor31 = 0.0F;
            byte byteValue5 = 10;
            byte byteValue6 = 10;
            clickGuiScreen2.applyIndex(byteValue6, byteValue5, factor31);
            Client.getClient().getClickGuiScreen().updateState4();
            framebuffer.setState(false);
            ClickGuiScreen clickGuiScreen3 = Client.getClient().getClickGuiScreen();
            float factor32 = 0.0F;
            byte byteValue7 = 10;
            byte byteValue8 = 10;
            clickGuiScreen3.applyIndex(byteValue8, byteValue7, factor32);
            MinecraftAccessor.minecraft.getFramebuffer().setState(false);
         } catch (Throwable throwable) {
            throwable.printStackTrace();
         }

         Listener72Entry listener72Entry = new Listener72Entry();
         short shortValue5 = (short)count2;
         double offset18 = 1.0;
         double offset19 = 1.0;
         double offset20 = 1.0;
         byte byteValue9 = 1;
         byte byteValue10 = 1;
         byte byteValue11 = 1;
         short shortValue6 = shortValue5;
         EntityPlayerSP player8 = MinecraftAccessor.minecraft.thePlayer;
         EntityPlayerSP player9 = MinecraftAccessor.minecraft.thePlayer;
         listener72Entry.resolveCoreDoubleEntryArray(player9, player8, shortValue6, byteValue11, byteValue10, byteValue9, count3, count4, offset20, offset19, offset18);
         (new AuxiliaryModuleAdapter()).onEnable();
         RayTraceUtils.getMovingObjectPosition6(1.0F, 1.0F, 1.0F);
         BlockPos blockPos = new BlockPos(1, 1, 1);
         double offset21 = 1.0;
         float factor33 = 1.0F;
         float factor34 = 1.0F;
         RayTraceUtils.getMovingObjectPosition(blockPos, factor34, factor33, offset21);
         KillAura killAura = ClientGlobals.moduleManager.killAura;
         EventClick event = new EventClick(MovementSpeedProperty.LEFT);
         killAura.onClick2(event, false);
         String text13 = "d";
         FontRegistry.fontFace8.getScaleFactor(text13);
         Client clientItem = Client.getClient();
         TargetParser targetParser = new TargetParser();
         clientItem.applyTargetParser(targetParser);
         Client.getClient().getTargetParser().setEnabledByFlag(true);
      }
   }

   public static Setting[] getSettingArray() {
      return null;
   }

   static {
      Setting[] settingArray = new Setting[2];
   }
}
