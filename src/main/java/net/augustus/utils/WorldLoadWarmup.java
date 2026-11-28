package net.augustus.utils;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.clickgui.ClickGuiScreen;
import net.augustus.cosmetics.MoreCosmeticsCore;
import net.augustus.cosmetics.model.CosmeticModel;
import net.augustus.cosmetics.render.AbstractCosmeticRenderer;
import net.augustus.cosmetics.render.PlayerCosmeticRenderer;
import net.augustus.events.ClickButtonType;
import net.augustus.events.EventClick;
import net.augustus.font.FontManager;
import net.augustus.modules.misc.helper.EventWarmupDummyModule;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.utils.rotation.RayTraceUtil;
import net.augustus.utils.rotation.Rotation;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.utils.rotation.TargetTrajectoryPredictor;
import java.util.HashMap;
import java.util.Map.Entry;
import net.augustus.modules.Module;
import net.augustus.modules.combat.KillAura;
import net.augustus.modules.misc.Dummy;
import net.augustus.utils.interfaces.MC;
import net.labymod.ModelData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class WorldLoadWarmup implements MC {
   private static boolean warmedUp = false;


   public static void warmup() {
      if (!warmedUp) {
         warmedUp = true;
         System.currentTimeMillis();
         RotationUtil rotationUtil = new RotationUtil();
         EntityPlayerSP thePlayer = mc.thePlayer;
         RandomizedRangeSettings yawAcceleration = ClientManagers.moduleManager.killAura.yawAcceleration;
         RandomizedRangeSettings pitchAcceleration = ClientManagers.moduleManager.killAura.pitchAcceleration;
         RandomizedRangeSettings yawSpeed = ClientManagers.moduleManager.killAura.yawSpeed;
         RandomizedRangeSettings pitchSpeed = ClientManagers.moduleManager.killAura.pitchSpeed;
         float rotationYaw = mc.thePlayer.rotationYaw;
         float rotationPitch = mc.thePlayer.rotationPitch;
         String selectedValue = ClientManagers.moduleManager.killAura.randomizeMode.getSelectedValue();
         boolean value = ClientManagers.moduleManager.killAura.interpolation.getValue();
         boolean value2 = ClientManagers.moduleManager.killAura.smartAim.getValue();
         boolean value3 = ClientManagers.moduleManager.killAura.intave13.getValue();
         float value4 = (float)ClientManagers.moduleManager.killAura.randomStrength.getValue();
         boolean value5 = ClientManagers.moduleManager.killAura.advancedRots.getValue();
         boolean value6 = ClientManagers.moduleManager.killAura.advancedRotsNotAtTarget.getValue();
         String selectedValue2 = ClientManagers.moduleManager.killAura.heuristics.getSelectedValue();
         String selectedValue3 = ClientManagers.moduleManager.killAura.rotationMode.getSelectedValue();
         int value7 = (int)ClientManagers.moduleManager.killAura.hitBoxPercentageVertical.getValue();
         int value8 = (int)ClientManagers.moduleManager.killAura.hitBoxPercentageHorizontal.getValue();
         int value9 = (int)ClientManagers.moduleManager.killAura.hitBoxPercentageVertical1.getValue();
         int value10 = (int)ClientManagers.moduleManager.killAura.hitBoxPercentageHorizontal1.getValue();
         String selectedValue4 = ClientManagers.moduleManager.killAura.randomType.getSelectedValue();
         String selectedValue5 = ClientManagers.moduleManager.killAura.fixRotationMode.getSelectedValue();
         boolean value11 = ClientManagers.moduleManager.killAura.smartRotate.getValue();
         boolean value12 = ClientManagers.moduleManager.killAura.reducedRotate.getValue();
         boolean value13 = ClientManagers.moduleManager.killAura.pitchOutliers.getValue();
         RandomizedRangeSettings pitchOutliersAdd = ClientManagers.moduleManager.killAura.pitchOutliersAdd;
         RandomizedRangeSettings pitchOutliersDelay = ClientManagers.moduleManager.killAura.pitchOutliersDelay;
         double value14 = ClientManagers.moduleManager.killAura.reduceRotationPercentage.getValue();
         float value15 = (float)ClientManagers.moduleManager.killAura.maxXAbsDist.getValue();
         float value16 = (float)ClientManagers.moduleManager.killAura.maxYAbsDist.getValue();
         float value17 = (float)ClientManagers.moduleManager.killAura.minAbsDist.getValue();
         float minValue = (float)ClientManagers.moduleManager.killAura.xRelDist.getMinValue();
         float minValue2 = (float)ClientManagers.moduleManager.killAura.yRelDist.getMinValue();
         float maxValue = (float)ClientManagers.moduleManager.killAura.xRelDist.getMaxValue();
         float maxValue2 = (float)ClientManagers.moduleManager.killAura.yRelDist.getMaxValue();
         float value18 = (float)ClientManagers.moduleManager.killAura.maxRotationAngle.getValue();
         float minValue3 = (float)ClientManagers.moduleManager.killAura.innerRadius.getMinValue();
         float value19 = (float)ClientManagers.moduleManager.killAura.bestHitXMinDist.getValue();
         float value20 = (float)ClientManagers.moduleManager.killAura.bestHitYMInDist.getValue();
         boolean value21 = ClientManagers.moduleManager.killAura.betterAcceleration.getValue();
         int value22 = (int)ClientManagers.moduleManager.killAura.fixRotationChance.getValue(true);
         double d = ClientManagers.moduleManager.killAura.maxFixRotationReach.nextValue();
         double value23 = ClientManagers.moduleManager.killAura.minFixRotationAngle.getValue();
         double value24 = ClientManagers.moduleManager.killAura.minDistanceToBestVecYaw.getValue();
         double value25 = ClientManagers.moduleManager.killAura.minDistanceToBestVecPitch.getValue();
         double value26 = ClientManagers.moduleManager.killAura.minDistanceToBestVec.getValue();
         String selectedValue6 = ClientManagers.moduleManager.killAura.minDistanceToBestVecCalculation.getSelectedValue();
         boolean value27 = ClientManagers.moduleManager.killAura.stopRotationInsideTarget.getValue();
         String str = selectedValue6;
         double d2 = value26;
         double d3 = value25;
         double d4 = value24;
         double d5 = value23;
         double d6 = d;
         int n = value22;
         boolean flag2 = value21;
         float f = value20;
         float f2 = value19;
         float f3 = minValue3;
         float f4 = value18;
         float f5 = maxValue2;
         float f6 = maxValue;
         float f7 = minValue2;
         float f8 = minValue;
         float f9 = value17;
         float f10 = value16;
         float f11 = value15;
         double d7 = value14;
         RandomizedRangeSettings randomizedRangeSettings = pitchOutliersDelay;
         RandomizedRangeSettings randomizedRangeSettings2 = pitchOutliersAdd;
         boolean flag3 = value13;
         boolean flag4 = value12;
         boolean flag5 = value11;
         String str2 = selectedValue5;
         String str3 = selectedValue4;
         int n2 = value10;
         int n3 = value9;
         int n4 = value8;
         int n5 = value7;
         double d8 = 3.0;
         String str4 = selectedValue3;
         String str5 = selectedValue2;
         boolean flag6 = value6;
         boolean flag7 = value5;
         float f12 = value4;
         boolean flag8 = value3;
         boolean flag9 = value2;
         boolean flag10 = value;
         String str6 = selectedValue;
         float f13 = rotationPitch;
         float f14 = rotationYaw;
         RandomizedRangeSettings randomizedRangeSettings3 = pitchSpeed;
         RandomizedRangeSettings randomizedRangeSettings4 = yawSpeed;
         RandomizedRangeSettings randomizedRangeSettings5 = pitchAcceleration;
         RandomizedRangeSettings randomizedRangeSettings6 = yawAcceleration;
         rotationUtil.computeKillAuraRotation(
            (EntityLivingBase)thePlayer,
            randomizedRangeSettings6,
            randomizedRangeSettings5,
            randomizedRangeSettings4,
            randomizedRangeSettings3,
            f14,
            f13,
            str6,
            flag10,
            flag9,
            flag8,
            f12,
            (Vec3)null,
            flag7,
            flag6,
            str5,
            str4,
            d8,
            n5,
            n4,
            n3,
            n2,
            (byte)0,
            str3,
            str2,
            flag5,
            flag4,
            flag3,
            randomizedRangeSettings2,
            randomizedRangeSettings,
            d7,
            f11,
            f10,
            f9,
            f8,
            f7,
            f6,
            f5,
            f4,
            f3,
            f2,
            f,
            flag2,
            n,
            d6,
            d5,
            d4,
            d3,
            d2,
            str,
            value27
         );
         EntityPlayerSP thePlayer2 = mc.thePlayer;
         yawAcceleration = ClientManagers.moduleManager.killAura.yawAcceleration;
         pitchAcceleration = ClientManagers.moduleManager.killAura.pitchAcceleration;
         yawSpeed = ClientManagers.moduleManager.killAura.yawSpeed;
         pitchSpeed = ClientManagers.moduleManager.killAura.pitchSpeed;
         rotationYaw = mc.thePlayer.rotationYaw;
         rotationPitch = mc.thePlayer.rotationPitch;
         selectedValue = ClientManagers.moduleManager.killAura.randomizeMode.getSelectedValue();
         value = ClientManagers.moduleManager.killAura.interpolation.getValue();
         value2 = ClientManagers.moduleManager.killAura.smartAim.getValue();
         value3 = ClientManagers.moduleManager.killAura.intave13.getValue();
         value4 = (float)ClientManagers.moduleManager.killAura.randomStrength.getValue();
         value5 = ClientManagers.moduleManager.killAura.advancedRots.getValue();
         value6 = ClientManagers.moduleManager.killAura.advancedRotsNotAtTarget.getValue();
         selectedValue2 = ClientManagers.moduleManager.killAura.heuristics.getSelectedValue();
         selectedValue3 = ClientManagers.moduleManager.killAura.rotationMode.getSelectedValue();
         value7 = (int)ClientManagers.moduleManager.killAura.hitBoxPercentageVertical.getValue();
         value8 = (int)ClientManagers.moduleManager.killAura.hitBoxPercentageHorizontal.getValue();
         value9 = (int)ClientManagers.moduleManager.killAura.hitBoxPercentageVertical1.getValue();
         value10 = (int)ClientManagers.moduleManager.killAura.hitBoxPercentageHorizontal1.getValue();
         selectedValue4 = ClientManagers.moduleManager.killAura.randomType.getSelectedValue();
         selectedValue5 = ClientManagers.moduleManager.killAura.fixRotationMode.getSelectedValue();
         value11 = ClientManagers.moduleManager.killAura.smartRotate.getValue();
         value12 = ClientManagers.moduleManager.killAura.reducedRotate.getValue();
         value13 = ClientManagers.moduleManager.killAura.pitchOutliers.getValue();
         pitchOutliersAdd = ClientManagers.moduleManager.killAura.pitchOutliersAdd;
         pitchOutliersDelay = ClientManagers.moduleManager.killAura.pitchOutliersDelay;
         value14 = ClientManagers.moduleManager.killAura.reduceRotationPercentage.getValue();
         value15 = (float)ClientManagers.moduleManager.killAura.maxXAbsDist.getValue();
         value16 = (float)ClientManagers.moduleManager.killAura.maxYAbsDist.getValue();
         value17 = (float)ClientManagers.moduleManager.killAura.minAbsDist.getValue();
         minValue = (float)ClientManagers.moduleManager.killAura.xRelDist.getMinValue();
         minValue2 = (float)ClientManagers.moduleManager.killAura.yRelDist.getMinValue();
         maxValue = (float)ClientManagers.moduleManager.killAura.xRelDist.getMaxValue();
         maxValue2 = (float)ClientManagers.moduleManager.killAura.yRelDist.getMaxValue();
         value18 = (float)ClientManagers.moduleManager.killAura.maxRotationAngle.getValue();
         minValue3 = (float)ClientManagers.moduleManager.killAura.innerRadius.getMinValue();
         value19 = (float)ClientManagers.moduleManager.killAura.bestHitXMinDist.getValue();
         value20 = (float)ClientManagers.moduleManager.killAura.bestHitYMInDist.getValue();
         value21 = ClientManagers.moduleManager.killAura.betterAcceleration.getValue();
         value22 = (int)ClientManagers.moduleManager.killAura.fixRotationChance.getValue(true);
         d = ClientManagers.moduleManager.killAura.maxFixRotationReach.nextValue();
         value23 = ClientManagers.moduleManager.killAura.minFixRotationAngle.getValue();
         value24 = ClientManagers.moduleManager.killAura.minDistanceToBestVecYaw.getValue();
         value25 = ClientManagers.moduleManager.killAura.minDistanceToBestVecPitch.getValue();
         value26 = ClientManagers.moduleManager.killAura.minDistanceToBestVec.getValue();
         selectedValue6 = ClientManagers.moduleManager.killAura.minDistanceToBestVecCalculation.getSelectedValue();
         boolean value28 = ClientManagers.moduleManager.killAura.stopRotationInsideTarget.getValue();
         String str7 = selectedValue6;
         double d9 = value26;
         double d10 = value25;
         double d11 = value24;
         double d12 = value23;
         double d13 = d;
         int n6 = value22;
         boolean flag12 = value21;
         float f15 = value20;
         float f16 = value19;
         float f17 = minValue3;
         float f18 = value18;
         float f19 = maxValue2;
         float f20 = maxValue;
         float f21 = minValue2;
         float f22 = minValue;
         float f23 = value17;
         float f24 = value16;
         float f25 = value15;
         double d14 = value14;
         RandomizedRangeSettings randomizedRangeSettings7 = pitchOutliersDelay;
         RandomizedRangeSettings randomizedRangeSettings8 = pitchOutliersAdd;
         boolean flag13 = value13;
         boolean flag14 = value12;
         boolean flag15 = value11;
         String str8 = selectedValue5;
         String str9 = selectedValue4;
         int n7 = value10;
         int n8 = value9;
         int n9 = value8;
         int n10 = value7;
         double d15 = 3.0;
         String str10 = selectedValue3;
         String str11 = selectedValue2;
         boolean flag16 = value6;
         boolean flag17 = value5;
         float f26 = value4;
         boolean flag18 = value3;
         boolean flag19 = value2;
         boolean flag20 = value;
         String str12 = selectedValue;
         float f27 = rotationPitch;
         float f28 = rotationYaw;
         RandomizedRangeSettings randomizedRangeSettings9 = pitchSpeed;
         RandomizedRangeSettings randomizedRangeSettings10 = yawSpeed;
         RandomizedRangeSettings randomizedRangeSettings11 = pitchAcceleration;
         RandomizedRangeSettings randomizedRangeSettings12 = yawAcceleration;
         rotationUtil.computeKillAuraRotation(
            (EntityLivingBase)thePlayer2,
            randomizedRangeSettings12,
            randomizedRangeSettings11,
            randomizedRangeSettings10,
            randomizedRangeSettings9,
            f28,
            f27,
            str12,
            flag20,
            flag19,
            flag18,
            f26,
            (Vec3)null,
            flag17,
            flag16,
            str11,
            str10,
            d15,
            n10,
            n9,
            n8,
            n7,
            (byte)0,
            str9,
            str8,
            flag15,
            flag14,
            flag13,
            randomizedRangeSettings8,
            randomizedRangeSettings7,
            d14,
            f25,
            f24,
            f23,
            f22,
            f21,
            f20,
            f19,
            f18,
            f17,
            f16,
            f15,
            flag12,
            n6,
            d13,
            d12,
            d11,
            d10,
            d9,
            str7,
            value28
         );
         RotationUtil.getNearestPointInBox(new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0));
         RotationUtil.getNearestPointOnEntity((Entity)mc.thePlayer);
         EntityPlayerSP thePlayer3 = mc.thePlayer;
         RotationUtil.getNearestPointFromEntity((Entity)mc.thePlayer, (Entity)thePlayer3);
         Vec3 vec = new Vec3(0.0, 0.0, 0.0);
         EntityPlayerSP thePlayer4 = mc.thePlayer;
         Vec3 vec2 = new Vec3(0.0, 0.0, 0.0);
         RotationUtil.getNearestPointOnEntityAt(vec, (EntityLivingBase)thePlayer4, vec2);
         rotationUtil.generateDeltaCandidates(6.0F, (byte)5);
         RotationUtil.computeAngleBetweenRotations(new Rotation(20.0F, 30.0F), new Rotation(34.0F, 56.0F), new Rotation(100.0F, 102.0F));
         rotationUtil.reset();
         RotationUtil.isNearCardinalYaw(10.0F);
         RotationUtil.applyYawGcd(10.0F, 10.0F);
         RotationUtil.applyPitchGcd(10.0F, 10.0F);
         ClientManagers.moduleManager.scaffold.findBlockPlacementData();
         float f30 = 10.0F;
         ClientManagers.moduleManager.scaffold.isPredictedOutsideNextBlock(f30);
         ClientManagers.moduleManager.scaffold.computePitchRandomOffset(false);
         Rotation rotation5 = new Rotation(10.0F, 10.0F);
         Rotation rotation6 = new Rotation(10.0F, 10.0F);
         double d16 = 10.0;
         rotationUtil.createBezierCurve(rotation5, rotation6, (byte)1, 0.3, d16, false);
         PlayerUtil.predictPlayerPosition(0, mc.thePlayer);
         SilentSlotManager.requestSlot((Module)null, (byte)-1, (byte)-1);

         try {
            Framebuffer framebuffer = new Framebuffer(mc.displayWidth, mc.displayHeight, false);
            framebuffer.bindFramebuffer(false);
            Augustus.getInstance().getClickGui().setMc(Minecraft.getMinecraft());
            Augustus.getInstance().getClickGui().drawScreen((byte)10, (byte)10, 0.0F);
            Augustus.getInstance().getClickGui().updateScreen();
            framebuffer.bindFramebuffer(false);
            Augustus.getInstance().getClickGui().drawScreen((byte)10, (byte)10, 0.0F);
            mc.getFramebuffer().bindFramebuffer(false);
         } catch (Throwable e) {
            e.printStackTrace();
         }

         TargetTrajectoryPredictor predictor = new TargetTrajectoryPredictor();
         double d18 = 1.0;
         double d19 = 1.0;
         double d20 = 1.0;
         EntityPlayerSP thePlayer5 = mc.thePlayer;
         predictor.predictYawBounds(mc.thePlayer, (EntityLivingBase)thePlayer5, (byte)1, (byte)1, (byte)1, d20, d19, d18);
         (new Dummy()).updateEventSubscriptions();
         RayTraceUtil.rayTraceDefaultReach(1.0F, 1.0F, 1.0F);
         BlockPos pos = new BlockPos(1, 1, 1);
         double d21 = 1.0;
         float f33 = 1.0F;
         RayTraceUtil.rayTraceBlock(pos, 1.0F, f33, d21);
         ClientManagers.moduleManager.killAura.dispatchAttackClick(new EventClick(ClickButtonType.LEFT), false);
         String str13 = "d";
         FontManager.rubikBold32.getStringWidth(str13);
         Augustus.getInstance().setEventWarmupDummyModule(new EventWarmupDummyModule());
         Augustus.getInstance().getEventWarmupDummyModule().setWarmupActive(true);
         Augustus.getInstance().getModuleManager().cosmetics.initLocalPlayerModelData();
         AbstractCosmeticRenderer cosmeticRenderer = MoreCosmeticsCore.getInstance().getCosmeticRenderer();
         if (cosmeticRenderer instanceof PlayerCosmeticRenderer) {
            PlayerCosmeticRenderer playerCosmeticRenderer = (PlayerCosmeticRenderer)cosmeticRenderer;
            HashMap modelsById = MoreCosmeticsCore.getInstance().getModelLoader().getModelsById();
            Render entityRenderObject = mc.getRenderManager().getEntityRenderObject(mc.thePlayer);
            if (entityRenderObject instanceof RenderPlayer) {
               RenderPlayer renderPlayer = (RenderPlayer)entityRenderObject;

               for (Entry entry : (Iterable<Entry>)(modelsById.entrySet())) {
                  Integer key = (Integer)entry.getKey();
                  CosmeticModel value29 = (CosmeticModel)entry.getValue();
                  ModelData modelData;
                  if ((modelData = (ModelData)mc.thePlayer.getCosmetics().get(key)) != null) {
                     EntityPlayerSP thePlayer7 = mc.thePlayer;
                     ModelPlayer playerModel = renderPlayer.getPlayerModel();
                     float f35 = 1.0F;
                     float f36 = 1.0F;
                     float f37 = 1.0F;
                     float f38 = 1.0F;
                     float f39 = 1.0F;
                     float f40 = 1.0F;
                     playerCosmeticRenderer.renderCosmeticModel((AbstractClientPlayer)thePlayer7, value29, modelData, f40, f39, f38, f37, f36, f35, playerModel, false);
                  }
               }
            }
         }

         if (!ClientManagers.moduleManager.cosmetics.isToggled()) {
            ClientManagers.moduleManager.cosmetics.clearLocalPlayerCosmetics();
         }
      }
   }



}
