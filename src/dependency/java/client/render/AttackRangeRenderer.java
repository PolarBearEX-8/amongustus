package client.render;

import client.Client;
import client.ClientGlobals;
import client.event.ExternalListener;
import client.event.RenderListener;
import client.network.CosmeticsPayload;
import client.rotation.Rotation;
import client.setting.BooleanSettingProperty;
import client.setting.BooleanSettingValue;
import client.setting.CombatChoiceProperty;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.RangeSettingEntry;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.DoubleContainer;
import client.util.GenericDoubleEntry;
import client.util.GenericFloatEntry;
import client.util.HitBoxUtils;
import client.util.MinecraftAccessor;
import client.util.RayTraceUtil;
import client.util.RotationMatcher;
import client.util.SimulationSnapshot;
import client.util.SmoothEvaluator;
import client.util.TrajectoryPredictor;
import com.google.common.util.concurrent.AtomicDouble;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiConnecting;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class AttackRangeRenderer implements MinecraftAccessor, RenderListener {
   public double value2;
   public CombatChoiceProperty combatChoiceProperty6;
   public RangeSetting settingResyncRecalculateDelay;
   public NumberSetting settingResyncAtAimgoal;
   public RangeSettingEntry rangeSettingEntry;
   public int count2;
   public double value4;
   public double value3;
   public CombatChoiceProperty combatChoiceProperty5;
   public int count;
   public NumberSetting settingUNoise3RemoveNoiseChance;
   public static ArrayList arrayList = new ArrayList();
   public boolean enabled2;
   public double value;
   public NumberSetting settingUNoise3highspeed;
   public boolean enabled;
   public ModeSetting settingPrediction;
   public SimulationSnapshot simulationSnapshot;
   public boolean enabled3;
   public double value5;
   public BooleanSettingProperty booleanSettingProperty;
   public EntityLivingBase entityLivingBase;
   public CombatChoiceProperty combatChoiceProperty4;
   public CombatChoiceProperty combatChoiceProperty3;
   public int count4;
   public BooleanSettingValue booleanSettingValue;
   public GenericFloatEntry genericFloatEntry;
   public int count5;
   public CombatChoiceProperty combatChoiceProperty2;
   public RangeSetting settingResyncDelay;
   public RangeSetting settingNoNoiseRecalcDelay;
   public CombatChoiceProperty combatChoiceProperty;
   public int count3;
   public boolean enabled4;
   public NumberSetting settingUNoise3LargeRotation;
   public DoubleContainer doubleContainer;

   private void renderElement2() {
      if (this.count == 0 && this.enabled2) {
         this.renderElement();
      }
   }

   public void applyDoubleByDouble(double offset, double offset2) {
      double doubleItem = MathHelper.getDouble4(offset);
      double doubleItem2 = MathHelper.getDouble7(offset2, -90.0, 90.0);
      this.booleanSettingValue.applyDouble(doubleItem, doubleItem2);
      this.value5 = doubleItem;
      this.value4 = doubleItem2;
      this.value3 = doubleItem;
      this.value2 = doubleItem2;
   }

   private boolean isActive() {
      return this.settingPrediction.getSelected().equals("Prediction");
   }

   public int computeCount(double posX, double posX2, double posY, double posZ) {
      short shortValue = 16111;
      short shortValue2 = 27152;
      short shortValue3 = -29445;
      DoubleContainer doubleContainer2 = this.doubleContainer;
      RotationMatcher rotationMatcher = new RotationMatcher(posX, posX2, posY, posZ, posZ, 6.0);
      return doubleContainer2.resolveArrayList(rotationMatcher).size();
   }

   public void applyDouble(double offset, double offset2) {
      short shortValue = 15743;
      short shortValue2 = -26021;
      short shortValue3 = -21515;
      this.value5 = offset;
      this.value4 = offset2;
      this.value3 = offset;
      this.value2 = offset2;
      DoubleContainer doubleContainer2 = this.doubleContainer;
      double offset3 = this.combatChoiceProperty4.getValue() * 20.0;
      doubleContainer2.applyDouble(offset3, false);
      this.doubleContainer.value4 = this.combatChoiceProperty.getValue();
      this.doubleContainer.value10 = this.combatChoiceProperty3.getValue();
      this.doubleContainer.value6 = this.combatChoiceProperty5.getValue();
      this.doubleContainer.value3 = this.settingUNoise3highspeed.getValue();
      this.value = this.combatChoiceProperty2.getValue() * 20.0;
      this.simulationSnapshot = null;
   }

   private void renderElement() {
      this.count = (int)((double)this.settingResyncDelay.getInt5(true) / 16.6);
      this.enabled2 = false;
      this.enabled = true;
   }

   public static void applyDouble2(double offset, double offset2) {
      Iterator iterator = arrayList.iterator();

      while (iterator.hasNext()) {
         ((AttackRangeRenderer)iterator.next()).applyDoubleByDouble(offset, offset2);
      }
   }

   private void renderElement5() {
      boolean flag = true;
      if (!this.enabled4) {
         flag = false;
      }

      if (flag) {
         this.renderElement3();
      }

      DoubleContainer doubleContainer2 = this.doubleContainer;
      double offset = this.combatChoiceProperty6.getValue() * 20.0;
      doubleContainer2.applyDouble(offset, false);
      this.doubleContainer.updateState();
      this.doubleContainer.value4 = this.combatChoiceProperty.getValue();
      this.doubleContainer.value10 = this.combatChoiceProperty3.getValue();
      this.doubleContainer.value6 = this.combatChoiceProperty5.getValue();
      this.doubleContainer.value3 = this.settingUNoise3highspeed.getValue();
      this.value = this.combatChoiceProperty2.getValue() * 20.0;
      if (this.count4 > 0) {
         this.rangeSettingEntry.enabled2 = true;
      }

      this.count4 = 0;
      this.simulationSnapshot = null;
   }

   private boolean isActiveAsFlag() {
      return this.settingPrediction.getSelected().equals("Prediction");
   }

   public Rotation resolveRotationByEntityLivingBase(
      EntityLivingBase livingEntity, EntityPlayerSP player, float factor, float factor2, double offset, double offset2, Vec3 vec, Vec3 vec2
   ) {
      long value6 = 0x18F6FA73D8C7L;
      long keySalt = 0x65FF8B0C8FCEL ^ value6;
      long keySalt2 = 0x65FF8B0C8FCEL ^ value6 ^ 0x64BD1F7B9826L;
      int count6 = (int)((keySalt ^ 0x64BD1F7B9826L) >>> 48);
      int count7 = (int)(keySalt2 << 16 >>> 48);
      int count8 = (int)(keySalt2 << 32 >>> 32);
      int count9 = (int)((keySalt ^ 0x3C61A2375FA8L) >>> 48);
      if (CosmeticsPayload.checkCondition(6)) {
         return new Rotation(factor, factor2);
      } else {
         searchBlock:
         if (this.genericFloatEntry == null) {
            if (!CosmeticsPayload.checkCondition(1) && !Client.getClient().getString7().equals("Tom") && !Client.getClient().getString7().equals("karuz")) {
               if (GuiConnecting.labelText2 == null || !GuiConnecting.labelText2.toLowerCase().contains("ngrok")) {
                  break searchBlock;
               }

               Client.getClient().getClass();
            }

            this.genericFloatEntry = new GenericFloatEntry();
         }

         if (this.genericFloatEntry != null) {
            this.genericFloatEntry.count8 = 2;
            this.genericFloatEntry.count5 = 3;
            this.genericFloatEntry.count7 = 3;
            this.genericFloatEntry.count10 = 0;
            this.genericFloatEntry.value7 = 0.0;
            this.genericFloatEntry.value2 = 0.0;
            this.genericFloatEntry.value5 = 6.0;
            this.genericFloatEntry.value4 = 1.0;
            this.genericFloatEntry.value6 = 1.0;
            this.genericFloatEntry.enabled3 = false;
            this.genericFloatEntry.enabled4 = false;
            short shortValue = (short)count6;
            char character = (char)count7;
            short shortValue2 = shortValue;
            return this.genericFloatEntry.resolveRotation(shortValue2, livingEntity, player, character, count8, factor, factor2, offset, offset2, vec, vec2);
         } else {
            float factor3 = MathHelper.sqrt_float(factor);
            this.rangeSettingEntry.numberSettingEntry.entityPlayerSP = player;
            this.booleanSettingValue.entityPlayerSP = player;
            boolean flag = TrajectoryPredictor.getDouble4((double)factor3, this.value3) > 1.0 || Math.abs(this.value2 - (double)factor2) > 1.0;
            if (this.entityLivingBase != livingEntity
               || this.count3 + 1 != player.ticksExisted
                  && (!MinecraftAccessor.minecraft.enabled9 || ClientGlobals.moduleManager.rotationHandler.settingRotationMode.getSelected().equals("Tick"))
               || flag) {
               double offset3 = (double)factor3;
               double offset4 = (double)factor2;
               double offset5 = offset3;
               this.applyDouble(offset5, offset4);
               double offset6 = (double)factor3;
               double offset7 = (double)factor2;
               double offset8 = offset6;
               this.booleanSettingValue.applyDouble(offset8, offset7);
               int ticksExisted = player.ticksExisted;
               this.rangeSettingEntry.applyIndex(ticksExisted);
               double doubleItem = MathHelper.getDouble4((double)player.scaleFactor30);
               double doubleItem2 = MathHelper.getDouble4((double)player.scaleFactor11);
               double scaleFactor = (double)player.scaleFactor27;
               double scaleFactor2 = (double)player.scaleFactor20;
               this.doubleContainer.simulationSnapshot = new SimulationSnapshot(HitBoxUtils.getDouble(doubleItem, doubleItem2) / 0.15 / 3.0, (scaleFactor - scaleFactor2) / 0.15 / 3.0);
               this.doubleContainer.count2 = -1;
            }

            this.entityLivingBase = livingEntity;
            this.count3 = player.ticksExisted;
            if (!Double.isFinite(this.value3)) {
               this.value3 = (double)factor3;
               RayTraceUtil.applyString("Fixed flaw (1)");
            }

            if (!Double.isFinite(this.value2)) {
               this.value2 = (double)factor2;
               RayTraceUtil.applyString("Fixed flaw (2)");
            }

            if (!Double.isFinite(this.value5)) {
               this.value5 = this.value3;
               RayTraceUtil.applyString("Fixed flaw (3)");
            }

            if (!Double.isFinite(this.value4)) {
               this.value4 = this.value2;
               RayTraceUtil.applyString("Fixed flaw (4)");
            }

            if (this.count5 == 0) {
               this.enabled4 = Math.random() <= this.settingResyncAtAimgoal.getDouble2(true) / 100.0;
               this.count5 = this.settingResyncRecalculateDelay.getInt5(true);
            } else if (this.count5 > 0) {
               this.count5--;
            }

            if (this.count2 == 0) {
               this.doubleContainer.enabled2 = Math.random() <= this.settingUNoise3RemoveNoiseChance.getDouble2(true) / 100.0;
               this.count2 = this.settingNoNoiseRecalcDelay.getInt5(true);
            } else if (this.count2 > 0) {
               this.count2--;
            }

            GenericDoubleEntry[] genericDoubleEntryArray = null;
            GenericDoubleEntry[] genericDoubleEntryArray2 = null;
            Vec3 vec3 = player.getVec32(1.0F);
            player.getVec35(1.0F);
            Vec3 vec32 = livingEntity.getVec32(1.0F);
            this.rangeSettingEntry.applyEntityPlayerSPByEntityPlayerSP(player, livingEntity);
            if (this.rangeSettingEntry.genericDoubleEntryArray2 == null
               || !this.rangeSettingEntry.genericDoubleEntryArray2[0].enabled && !this.rangeSettingEntry.genericDoubleEntryArray2[0].enabled2) {
               genericDoubleEntryArray = this.rangeSettingEntry.genericDoubleEntryArray2;
               genericDoubleEntryArray2 = this.rangeSettingEntry.genericDoubleEntryArray;
            }

            if (genericDoubleEntryArray == null) {
               genericDoubleEntryArray = new GenericDoubleEntry[]{
                  new GenericDoubleEntry(vec3, vec32, 0.0, 0.0, this.booleanSettingValue.value2, this.booleanSettingValue.value2, 1),
                  new GenericDoubleEntry(vec3, vec32, 0.0, 0.0, this.booleanSettingValue.value2, this.booleanSettingValue.value2, 1)
               };
            }

            if (genericDoubleEntryArray2 == null) {
               genericDoubleEntryArray2 = new GenericDoubleEntry[]{
                  new GenericDoubleEntry(vec3, vec32, 0.0, 0.0, this.booleanSettingValue.value4, this.booleanSettingValue.value4, 1),
                  new GenericDoubleEntry(vec3, vec32, 0.0, 0.0, this.booleanSettingValue.value4, this.booleanSettingValue.value4, 1)
               };
            }

            SimulationSnapshot simulationSnapshot2 = this.resolveSimulationSnapshotByEntityLivingBase(livingEntity, player, factor3, factor2, offset, offset2, genericDoubleEntryArray, genericDoubleEntryArray2);
            AtomicDouble atomicDouble = new AtomicDouble(HitBoxUtils.getDouble(this.value5, MathHelper.getDouble4(simulationSnapshot2.value)));
            AtomicDouble atomicDouble2 = new AtomicDouble(simulationSnapshot2.value2 - this.value4);
            double offset9 = (genericDoubleEntryArray[0].value2 + genericDoubleEntryArray[1].value2) / 2.0;
            if (Math.sqrt(atomicDouble.get() * atomicDouble.get() + atomicDouble2.get() * atomicDouble2.get()) > this.settingUNoise3LargeRotation.getDouble2(true)) {
               double value7 = this.value;
               this.doubleContainer.applyDouble(value7, false);
            }

            if (!this.doubleContainer.isEnabled()
               && this.rangeSettingEntry.count7 > 0
               && this.count4 >= 0
               && this.settingPrediction.getSelected().equals("Prediction")) {
               double value8 = this.doubleContainer.getValue8();
               double offset10 = atomicDouble.get();
               double offset11;
               if (Math.signum(offset9) != Math.signum(offset10) && offset10 != 0.0) {
                  offset11 = 0.0;
               } else {
                  offset11 = Math.abs(offset9);
               }

               RotationMatcher rotationMatcher = new RotationMatcher(
                  atomicDouble.get() / 0.15, this.doubleContainer.simulationSnapshot.value, offset11 / 0.15, value8, value8, value8
               );
               RotationMatcher rotationMatcher2 = new RotationMatcher(atomicDouble2.get() / 0.15, this.doubleContainer.simulationSnapshot.value2, 0.0, value8, value8, value8);
               int count10 = this.count4 * 3 + 3;
               double value9 = this.doubleContainer.resolveRotationMatcher(count10, rotationMatcher).value4;
               int count11 = this.count4 * 3 + 3;
               double value10 = this.doubleContainer.resolveRotationMatcher(count11, rotationMatcher2).value4;
               DoubleContainer doubleContainer2 = this.doubleContainer;
               double offset12 = Math.max(value9, value10);
               doubleContainer2.applyDouble(offset12, true);
            }

            DoubleContainer doubleContainer3 = this.doubleContainer;
            double offset13 = offset9 / 0.15;
            ExternalListener externalListener = (count6x, offsetx, offset2x) -> this.applyAtomicDouble(atomicDouble, simulationSnapshot2, atomicDouble2, count6x, offsetx, offset2x);
            byte byteValue = 3;
            double offset14 = offset13;
            SmoothEvaluator smoothEvaluator = doubleContainer3.resolveSmoothEvaluator(atomicDouble, atomicDouble2, offset14, byteValue, externalListener);
            Client.getClient().getClass();
            this.combatChoiceProperty6.updateState8();
            this.combatChoiceProperty2.updateState8();
            this.combatChoiceProperty4.updateState8();
            this.combatChoiceProperty.updateState8();
            this.combatChoiceProperty3.updateState8();
            this.combatChoiceProperty5.updateState8();
            double offset15 = smoothEvaluator.simulationSnapshot2.value * 0.15;
            double offset16 = smoothEvaluator.simulationSnapshot2.value2 * 0.15;
            this.value5 = MathHelper.getDouble4(this.value5 + smoothEvaluator.simulationSnapshot.value * 0.15);
            this.value4 = MathHelper.getDouble7(this.value4 + smoothEvaluator.simulationSnapshot.value2 * 0.15, -90.0, 90.0);
            this.value3 = MathHelper.getDouble4(this.value3 + offset15);
            this.value2 = MathHelper.getDouble7(this.value2 + offset16, -90.0, 90.0);
            return new Rotation((float)this.value3, (float)this.value2);
         }
      }
   }

   private void renderElement3() {
      if (this.count > 0) {
         this.enabled2 = true;
      } else {
         this.renderElement();
      }
   }

   private void renderElement4() {
      if (this.count > 0) {
         this.count--;
      }
   }

   private SimulationSnapshot resolveSimulationSnapshotByEntityLivingBase(
      EntityLivingBase livingEntity, EntityPlayerSP player, float factor, float factor2, double offset, double offset2, GenericDoubleEntry[] genericDoubleEntryArray, GenericDoubleEntry[] genericDoubleEntryArray2
   ) {
      long value6 = 0x353E9806988BL;
      long keySalt = 0x65FF8B0C8FCEL ^ value6;
      long keySalt2 = 0x65FF8B0C8FCEL ^ value6 ^ 0x1F01C163EDCBL;
      int count6 = (int)((0x65FF8B0C8FCEL ^ value6 ^ 0x1F01C163EDCBL) >>> 48);
      int count7 = (int)((0x65FF8B0C8FCEL ^ value6 ^ 0x1F01C163EDCBL) << 16 >>> 32);
      int count8 = (int)(keySalt2 << 48 >>> 48);
      keySalt2 = keySalt ^ 0x3D941ECA645EL;
      int count9 = (int)((keySalt ^ 0x3D941ECA645EL) >>> 32);
      int count10 = (int)((keySalt ^ 0x3D941ECA645EL) << 32 >>> 48);
      int count11 = (int)(keySalt2 << 48 >>> 48);
      if (this.simulationSnapshot != null && this.rangeSettingEntry.count9 >= player.ticksExisted && this.count4 > 0) {
         this.count4--;
         return this.simulationSnapshot;
      } else {
         SimulationSnapshot simulationSnapshot2;
         if (this.settingPrediction.getSelected().equals("Prediction")) {
            double offset3 = (genericDoubleEntryArray[0].value2 + genericDoubleEntryArray[1].value2) / 2.0;
            double offset4 = (genericDoubleEntryArray2[0].value2 + genericDoubleEntryArray2[1].value2) / 2.0;
            BooleanSettingValue booleanSettingValue2 = this.booleanSettingValue;
            RangeSettingEntry rangeSettingEntry2 = this.rangeSettingEntry;
            Vec3 vec = genericDoubleEntryArray[0].vec2;
            Vec3 vec3 = genericDoubleEntryArray[0].vec3;
            Vec3 vec2 = genericDoubleEntryArray2[0].vec2;
            Vec3 vec32 = genericDoubleEntryArray2[0].vec3;
            double offset5 = (double)factor;
            double offset6 = (double)factor2;
            short shortValue = (short)count6;
            short shortValue2 = (short)count8;
            short shortValue3 = shortValue;
            double offset7 = this.rangeSettingEntry.numberSettingEntry.computeValue(player, shortValue3, count7, livingEntity, shortValue2, 4);
            double offset8 = offset6;
            double offset9 = offset5;
            Vec3 vec4 = vec32;
            Vec3 vec5 = vec2;
            Vec3 vec6 = vec3;
            Vec3 vec7 = vec;
            RangeSettingEntry rangeSettingEntry3 = rangeSettingEntry2;
            simulationSnapshot2 = booleanSettingValue2.resolveSimulationSnapshotByRangeSettingEntr(
               rangeSettingEntry3, player, count9, livingEntity, vec7, vec6, vec5, vec4, count10, offset9, offset8, offset, offset2, offset7, count11, offset3, offset4
            );
         } else {
            simulationSnapshot2 = new SimulationSnapshot(offset, offset2);
            this.booleanSettingValue.applyDouble(offset, offset2);
         }

         this.simulationSnapshot = simulationSnapshot2;
         this.count4 = this.rangeSettingEntry.count7;
         this.doubleContainer.updateState();
         return simulationSnapshot2;
      }
   }

   private void applyAtomicDouble(AtomicDouble atomicDouble, SimulationSnapshot simulationSnapshot2, AtomicDouble atomicDouble2, int count6, double offset, double offset2) {
      this.renderElement4();
      this.renderElement2();
      if (!this.enabled3) {
         ClientGlobals.moduleManager.line.itemList4.add((long)((double)System.currentTimeMillis() - (1.0 - (double)(count6 + 1) / 3.0) * 50.0));
         ClientGlobals.moduleManager.line.itemList6.add(MinecraftAccessor.minecraft.thePlayer.getVec35((float)(count6 + 1) / 3.0F));
         ClientGlobals.moduleManager
            .line
            .rotationHistory
            .add(
               new float[]{(float)MathHelper.getDouble4(this.value3 + offset * 0.15), MathHelper.clamp_float((float)(this.value2 + offset2 * 0.15), -90.0F, 90.0F)}
            );

         while (
            !ClientGlobals.moduleManager.line.itemList4.isEmpty()
               && (double)(System.currentTimeMillis() - (Long)ClientGlobals.moduleManager.line.itemList4.get(0))
                  > ClientGlobals.moduleManager.line.settingAuraLineTime.getValue()
         ) {
            ClientGlobals.moduleManager.line.itemList4.remove(0);
            ClientGlobals.moduleManager.line.rotationHistory.remove(0);
            ClientGlobals.moduleManager.line.itemList6.remove(0);
         }
      }

      if (this.enabled) {
         this.value5 = this.value3;
         this.value4 = this.value2;
         atomicDouble.set(HitBoxUtils.getDouble(this.value5, MathHelper.getDouble4(simulationSnapshot2.value)));
         atomicDouble2.set(simulationSnapshot2.value2 - this.value4);
         this.enabled = false;
      }
   }

   public AttackRangeRenderer(BooleanSettingProperty booleanSettingProperty2, BooleanSupplier booleanSupplier) {
        super();
      long value6 = 0x60DF964D76AFL;
      long keySalt = 0x65FF8B0C8FCEL ^ value6 ^ 0x38490201ED6DL;
      int count6 = (int)((0x65FF8B0C8FCEL ^ value6 ^ 0x38490201ED6DL) << 32 >>> 48);
      
      this.enabled = false;
      this.count3 = -1;
      this.entityLivingBase = null;
      this.enabled4 = true;
      this.count5 = 0;
      this.count2 = 0;
      this.enabled3 = false;
      this.simulationSnapshot = null;
      this.count4 = 0;
      if (CosmeticsPayload.checkCondition(1)) {
         this.genericFloatEntry = new GenericFloatEntry();
      }

      this.booleanSettingProperty = booleanSettingProperty2;
      SettingGroup settingGroup = (new SettingGroup(booleanSettingProperty2.getModule(), "Mouse", booleanSettingProperty2.getSettingGroup())).getSettingGroup(booleanSupplier);
      this.settingPrediction = (new ModeSetting(
            booleanSettingProperty2.getModule(), "Point selection mode", "Prediction", new String[]{"Provided", "Prediction"}, booleanSettingProperty2.getSettingGroup()
         ))
         .getModeSetting(booleanSupplier);
      SettingGroup settingGroup2 = new SettingGroup(booleanSettingProperty2.getModule(), "Point selection", booleanSettingProperty2.getSettingGroup());
      BooleanSupplier booleanSupplier2 = this::isActive;
      char character = (char)count6;
      BooleanSupplier booleanSupplier3 = booleanSupplier2;
      SettingGroup settingGroup3 = settingGroup2.getSettingGroup(booleanSupplier3).getSettingGroup(booleanSupplier);
      settingGroup2 = new SettingGroup(booleanSettingProperty2.getModule(), "Prediction", booleanSettingProperty2.getSettingGroup());
      booleanSupplier2 = this::isActiveAsFlag;
      character = (char)count6;
      BooleanSupplier booleanSupplier4 = booleanSupplier2;
      SettingGroup settingGroup4 = settingGroup2.getSettingGroup(booleanSupplier4).getSettingGroup(booleanSupplier);
      this.rangeSettingEntry = new RangeSettingEntry(booleanSettingProperty2.resolveBooleanSettingPropertyBySettingGroup(settingGroup4));
      this.booleanSettingValue = new BooleanSettingValue(booleanSettingProperty2.resolveBooleanSettingPropertyBySettingGroup(settingGroup3), this.rangeSettingEntry);
      CombatChoiceProperty combatChoiceProperty7 = new CombatChoiceProperty(booleanSettingProperty2.getModule(), "UNoise3Aimspeed", settingGroup, true);
      String text = "Aim speed";
      CombatChoiceProperty combatChoiceProperty8 = combatChoiceProperty7.resolveCombatChoiceProperty(text);
      double offset = 20.0;
      double offset2 = 15.0;
      double offset3 = 100.0;
      double offset4 = 1.0;
      this.combatChoiceProperty6 = combatChoiceProperty8.resolveCombatChoiceProperty(offset4, offset3, offset2, offset, 1);
      CombatChoiceProperty combatChoiceProperty9 = new CombatChoiceProperty(booleanSettingProperty2.getModule(), "UNoise3DecelerationFactorPre", settingGroup, false);
      String text2 = "Decelerationfactor Pre";
      CombatChoiceProperty combatChoiceProperty10 = combatChoiceProperty9.resolveCombatChoiceProperty(text2);
      double offset5 = 1.0;
      double offset6 = 1.0;
      double offset7 = 2.0;
      double offset8 = 0.5;
      this.combatChoiceProperty = combatChoiceProperty10.resolveCombatChoiceProperty(offset8, offset7, offset6, offset5, 2);
      CombatChoiceProperty combatChoiceProperty11 = new CombatChoiceProperty(booleanSettingProperty2.getModule(), "UNoise3DecelerationFactorPost", settingGroup, false);
      String text3 = "Decelerationfactor Post";
      CombatChoiceProperty combatChoiceProperty12 = combatChoiceProperty11.resolveCombatChoiceProperty(text3);
      double offset9 = 1.0;
      double offset10 = 1.0;
      double offset11 = 8.0;
      double offset12 = 0.5;
      this.combatChoiceProperty3 = combatChoiceProperty12.resolveCombatChoiceProperty(offset12, offset11, offset10, offset9, 2);
      CombatChoiceProperty combatChoiceProperty13 = new CombatChoiceProperty(booleanSettingProperty2.getModule(), "UNoise3DecelerationFactorPostHighspeed", settingGroup, false);
      String text4 = "Decelerationfactor Post at highspeed";
      CombatChoiceProperty combatChoiceProperty14 = combatChoiceProperty13.resolveCombatChoiceProperty(text4);
      double offset13 = 2.0;
      double offset14 = 2.0;
      double offset15 = 8.0;
      double offset16 = 0.5;
      this.combatChoiceProperty5 = combatChoiceProperty14.resolveCombatChoiceProperty(offset16, offset15, offset14, offset13, 2);
      NumberSetting setting = new NumberSetting(booleanSettingProperty2.getModule(), "UNoise3highspeed", 10.0, 5.0, 20.0, 1, settingGroup);
      String text5 = "How many degrees per tick is high speed?";
      this.settingUNoise3highspeed = setting.getNumberSetting(text5);
      CombatChoiceProperty combatChoiceProperty15 = new CombatChoiceProperty(booleanSettingProperty2.getModule(), "UNoise3SpeedAtLargeRotation", settingGroup, true);
      String text6 = "Speed at large rotation";
      CombatChoiceProperty combatChoiceProperty16 = combatChoiceProperty15.resolveCombatChoiceProperty(text6);
      double offset17 = 40.0;
      double offset18 = 30.0;
      double offset19 = 100.0;
      double offset20 = 1.0;
      this.combatChoiceProperty2 = combatChoiceProperty16.resolveCombatChoiceProperty(offset20, offset19, offset18, offset17, 1);
      CombatChoiceProperty combatChoiceProperty17 = new CombatChoiceProperty(booleanSettingProperty2.getModule(), "UNoise3SpeedAtInitialRotation", settingGroup, true);
      String text7 = "Speed at initial rotation";
      CombatChoiceProperty combatChoiceProperty18 = combatChoiceProperty17.resolveCombatChoiceProperty(text7);
      double offset21 = 40.0;
      double offset22 = 30.0;
      double offset23 = 100.0;
      double offset24 = 1.0;
      this.combatChoiceProperty4 = combatChoiceProperty18.resolveCombatChoiceProperty(offset24, offset23, offset22, offset21, 1);
      NumberSetting setting2 = new NumberSetting(booleanSettingProperty2.getModule(), "UNoise3LargeRotation", 30.0, 20.0, 180.0, 1, settingGroup);
      String text8 = "How many degrees difference do count as large rotation?";
      this.settingUNoise3LargeRotation = setting2.getNumberSetting(text8);
      this.doubleContainer = new DoubleContainer(booleanSettingProperty2.resolveBooleanSettingPropertyBySettingGroup(settingGroup));
      this.combatChoiceProperty6.updateState7();
      this.combatChoiceProperty.updateState7();
      this.combatChoiceProperty3.updateState7();
      this.combatChoiceProperty2.updateState7();
      this.combatChoiceProperty4.updateState7();
      this.combatChoiceProperty5.updateState7();
      NumberSetting setting3 = new NumberSetting(booleanSettingProperty2.getModule(), "UNoise3ResyncChanceAtAimgoal", 0.0, 0.0, 100.0, 0, settingGroup);
      String text9 = "Resync at aimgoal";
      this.settingResyncAtAimgoal = setting3.getNumberSetting(text9);
      RangeSetting setting4 = new RangeSetting(booleanSettingProperty2.getModule(), "UNoise3ResyncUpdateDelay", 1.0, 1.0, 1.0, 5.0, 0, settingGroup);
      String text10 = "Resync recalculate delay";
      this.settingResyncRecalculateDelay = setting4.getRangeSetting(text10);
      NumberSetting setting5 = new NumberSetting(booleanSettingProperty2.getModule(), "UNoise3RemoveNoiseChance", 0.0, 0.0, 100.0, 0, settingGroup);
      String text11 = "Apply no noise when near target chance";
      this.settingUNoise3RemoveNoiseChance = setting5.getNumberSetting(text11);
      RangeSetting setting6 = new RangeSetting(booleanSettingProperty2.getModule(), "UNoise3RecalcRemoveNoise", 1.0, 5.0, 1.0, 8.0, 0, settingGroup);
      String text12 = "No noise recalc delay";
      this.settingNoNoiseRecalcDelay = setting6.getRangeSetting(text12);
      RangeSetting setting7 = new RangeSetting(booleanSettingProperty2.getModule(), "UNoise3resyncdelay", 0.0, 0.0, 0.0, 1000.0, 0, settingGroup);
      String text13 = "Resync delay";
      this.settingResyncDelay = setting7.getRangeSetting(text13).getRangeSetting2();
      this.doubleContainer.runnable = this::renderElement5;
      this.doubleContainer.runnable2 = this::renderElement3;
      arrayList.add(this);
   }
}
