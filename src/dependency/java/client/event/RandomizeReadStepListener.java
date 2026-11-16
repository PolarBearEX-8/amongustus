package client.event;

import augustus.modules.misc.DummyAura;
import augustus.modules.misc.RotationRecorder;
import client.Client;
import client.render.AttackRangeRenderer;
import client.render.BoundingBoxDimension;
import client.rotation.Rotation;
import client.setting.BooleanSettingProperty;
import client.setting.ModuleOrCategoryNaHelper;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.util.ArrayListData;
import client.util.EntityPlayerSPImplArrayListCallbackImpl;
import client.util.EntityPlayerSPImplArrayListCallbackImplAdapter;
import client.util.HitBoxUtils;
import client.util.PlayerUtil;
import client.util.PositionHistory;
import client.util.RayTraceUtil;
import client.util.RotationRecordPoint;
import client.util.TertiaryStringEntryAdapter;
import client.util.Vec3Entry;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSPImpl;
import net.minecraft.entity.ItemStackEntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RandomizeReadStepListener implements StepListener {
   public ArrayList arrayList = new ArrayList();
   public float factor;
   public HashMap hashMap;
   public HashMap hashMap2 = new HashMap();

   private Object[] getTargetArray() {
      Minecraft mc = Minecraft.getMinecraft();
      EntityPlayerSPImpl entityPlayerSPImpl = new EntityPlayerSPImpl(mc, mc.theWorld, mc.getNetHandlerPlayClient(), mc.thePlayer.getStatFileWriter());
      ItemStackEntityLivingBase itemStackEntityLivingBase = new ItemStackEntityLivingBase(mc.theWorld);
      DummyAura dummyAura = new DummyAura();
      SettingGroup settingGroup = new SettingGroup(dummyAura, "Randomize");
      BooleanSettingProperty booleanSettingProperty = new BooleanSettingProperty(dummyAura, settingGroup);
      AttackRangeRenderer attackRangeRenderer = new AttackRangeRenderer(booleanSettingProperty, RandomizeReadStepListener::isActive);

      for (Setting setting : (Iterable<Setting>)(Iterable<?>)Client.getClient().getAuxiliaryCopyOnWriteArrayLis().getList()) {
         if (setting.getModule() == dummyAura) {
            setting.updateState2();
         }
      }

      PositionHistory.arrayListCallbackImpl2 = new EntityPlayerSPImplArrayListCallbackImplAdapter(this, entityPlayerSPImpl);
      PositionHistory.arrayListCallbackImpl = new EntityPlayerSPImplArrayListCallbackImpl(this, entityPlayerSPImpl, itemStackEntityLivingBase);
      attackRangeRenderer.enabled3 = true;
      RotationRecordPoint rotationRecordPoint = (RotationRecordPoint)this.arrayList.get(0);
      float factor2 = MathHelper.sqrt_float(rotationRecordPoint.scaleFactor6);
      float scaleFactor = rotationRecordPoint.scaleFactor;
      entityPlayerSPImpl.scaleFactor30 = entityPlayerSPImpl.scaleFactor11 = factor2;
      entityPlayerSPImpl.scaleFactor20 = entityPlayerSPImpl.scaleFactor27 = scaleFactor;
      ArrayListData arrayListData = new ArrayListData(this.arrayList.size());
      RotationRecordPoint rotationRecordPoint2 = (RotationRecordPoint)this.arrayList.get(0);

      for (RotationRecordPoint rotationRecordPoint3 : (Iterable<RotationRecordPoint>)(Iterable<?>)this.arrayList) {
         entityPlayerSPImpl.ticksExisted = rotationRecordPoint3.count;
         entityPlayerSPImpl.onGround = rotationRecordPoint3.boundingBoxDimension.enabled4;
         entityPlayerSPImpl.motionX = rotationRecordPoint3.value;
         entityPlayerSPImpl.motionY = rotationRecordPoint3.value2;
         entityPlayerSPImpl.motionZ = rotationRecordPoint3.value3;
         entityPlayerSPImpl.scaleFactor10 = rotationRecordPoint3.factor;
         entityPlayerSPImpl.setState3(rotationRecordPoint3.enabled);
         entityPlayerSPImpl.processDouble2(rotationRecordPoint3.coordOffset4, rotationRecordPoint3.coordOffset1, rotationRecordPoint3.coordOffset5);
         this.factor = rotationRecordPoint3.factor2;
         itemStackEntityLivingBase.posX = rotationRecordPoint3.coordOffset2;
         itemStackEntityLivingBase.posY = rotationRecordPoint3.coordOffset;
         itemStackEntityLivingBase.posZ = rotationRecordPoint3.coordOffset3;
         BoundingBoxDimension boundingBoxDimension = rotationRecordPoint3.boundingBoxDimension;
         entityPlayerSPImpl.backtrackRecord5.applyObject(boundingBoxDimension);
         entityPlayerSPImpl.secondaryFloatEntry = rotationRecordPoint3.movementInput;
         Vec3 vec3 = rotationRecordPoint3.vec3;
         itemStackEntityLivingBase.backtrackRecord.applyObject(vec3);
         Vec3Entry vec3Entry = rotationRecordPoint3.vec3Entry;
         itemStackEntityLivingBase.backtrackRecord3.applyObject(vec3Entry);
         if (rotationRecordPoint3.enabled2) {
            double offset = (double)(entityPlayerSPImpl.ticksExisted + 1);
            attackRangeRenderer.rangeSettingEntry.numberSettingEntry.arrayListData.processAction(offset);
         }

         double offset2 = 0.0;
         double offset3 = 0.0;
         Rotation rotation = attackRangeRenderer.resolveRotationByEntityLivingBase(itemStackEntityLivingBase, entityPlayerSPImpl, factor2, scaleFactor, offset3, offset2, (Vec3)null, (Vec3)null);
         float factor3 = factor2;
         float factor4 = scaleFactor;
         factor2 = rotation.yaw;
         scaleFactor = rotation.pitch;
         double doubleItem = HitBoxUtils.getDouble((double)factor3, (double)factor2);
         double offset4 = (double)(scaleFactor - factor4);
         double doubleItem2 = HitBoxUtils.getDouble(MathHelper.getDouble4((double)rotationRecordPoint2.scaleFactor6), MathHelper.getDouble4((double)rotationRecordPoint3.scaleFactor6));
         double offset5 = (double)(rotationRecordPoint3.scaleFactor - rotationRecordPoint2.scaleFactor);
         double offset6 = doubleItem - doubleItem2;
         double offset7 = offset4 - offset5;
         double offset8 = Math.sqrt(offset6 * offset6 + offset7 * offset7);
         arrayListData.processAction(offset8);
      }

      ArrayList list = new ArrayList();

      for (Setting setting2 : (Iterable<Setting>)(Iterable<?>)Client.getClient().getAuxiliaryCopyOnWriteArrayLis().getList()) {
         if (setting2.getModule() == dummyAura) {
            list.add(setting2);
         }
      }

      for (Setting setting3 : (Iterable<Setting>)(Iterable<?>)list) {
         Client.getClient().getAuxiliaryCopyOnWriteArrayLis().getList().remove(setting3);
      }

      TertiaryStringEntryAdapter.clear();
      return new Object[]{arrayListData, dummyAura};
   }

   public static HashMap resolveHashMap(RandomizeReadStepListener randomizeReadStepListener) {
      return randomizeReadStepListener.hashMap;
   }

   private static boolean isActive() {
      return true;
   }

   @Override
   public void onStep(EventStep event) {
      float factor2 = this.factor;
      event.processAction(factor2);
   }

   public static HashMap resolveHashMapByRandomizeReadSte(RandomizeReadStepListener randomizeReadStepListener) {
      return randomizeReadStepListener.hashMap2;
   }

   public RandomizeReadStepListener() throws IOException {
      this.hashMap = new HashMap();
      EventBusManager.getDietrichEvents2().subscribe(0, this);
      String text = "C:\\Users\\tobia\\AppData\\Roaming\\ElectricLauncher\\data\\data\\augustus\\augustus\\rotation\\standingtargetlegit";
      JsonArray jsonArray = this.resolveJsonArray(text);
      Gson gson = new Gson();

      for (int index = 0; index < jsonArray.size(); index++) {
         JsonObject asJsonObject = jsonArray.get(index).getAsJsonObject();
         this.arrayList.add(gson.fromJson(asJsonObject, RotationRecordPoint.class));
      }

      RayTraceUtil.applyString("Read " + jsonArray.size() + " ticks");
      double offset = 10000.0;
      ArrayListData arrayListData = null;
      DummyAura dummyAura = null;

      for (int index2 = 0; index2 < 100; index2++) {
         Object[] targetArray = this.getTargetArray();
         ArrayListData arrayListData2 = (ArrayListData)targetArray[0];
         DummyAura dummyAura2 = (DummyAura)targetArray[1];
         double doubleItem = arrayListData2.getDouble7();
         System.out.println("#" + index2 + " " + doubleItem + " " + arrayListData2.getValue4() + " " + arrayListData2.getValueAsDouble());
         if (offset > doubleItem) {
            offset = doubleItem;
            arrayListData = arrayListData2;
            dummyAura = dummyAura2;
         }
      }

      ModuleOrCategoryNaHelper.matchesModule2(dummyAura);
      RayTraceUtil.applyString(
         "avg="
            + PlayerUtil.getDouble5(arrayListData.getDouble7(), 4)
            + " min="
            + PlayerUtil.getDouble5(arrayListData.getValue4(), 4)
            + " max="
            + PlayerUtil.getDouble5(arrayListData.getValueAsDouble(), 4)
      );
   }

   private JsonArray resolveJsonArray(String text) throws IOException {
      byte[] byteArray = RotationRecorder.getByteArray2(Files.readAllBytes(new File(text).toPath()));
      return JsonParser.parseString(new String(byteArray, StandardCharsets.UTF_8)).getAsJsonArray();
   }
}
