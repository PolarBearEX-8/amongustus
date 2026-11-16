package client.util;

import client.Client;
import client.event.PassiveListener;
import client.event.PreMotionDispatcher;
import client.render.RenderPlayer;
import client.setting.ActiveOptionType;
import client.setting.CombatDelayProperty;
import client.setting.CombatIntervalProperty;
import client.setting.ItemPhysicsNoneType;
import client.setting.MovementConfigProperty;
import client.setting.Setting;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.labymod.ModelData;
import net.labymod.SettingOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.FactoryItemEntry;
import net.minecraft.item.GoldenChestplate;
import net.minecraft.item.GoldenDust;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class FailedToLoadMaskFromManagerEntryAdapter extends FailedToLoadMaskFromManagerEntry {
   public DirectFloatHolder directFloatHolder;
   public JpegManager jpegManager2;
   public PreMotionDispatcher preMotionDispatcher;
   public boolean enabled;
   public JpegManager jpegManager = JpegManager.getJpegManager();
   public GoldenDust goldenDust;
   public static final HashMap hashMap3 = new HashMap();
   public long timestamp;
   public static final HashMap hashMap2 = new HashMap();
   public static final SettingOverlay settingOverlay = new SettingOverlay();
   public static final float factor = 0.0625F;
   public static final PassiveListener passiveListener = AuxiliaryListenerAdapter2.getAuxiliaryListenerAdapter2();
   private static Setting[] settingArray;

   public static Setting[] getSettingArray() {
      return null;
   }

   private boolean matchesUUID(UUID uuid, ResourceLocation location) {
      try {
         Minecraft.getMinecraft().getNetHandlerPlayClient().getNetworkPlayerInfo(uuid).setResourceLocation2(location);
         return true;
      } catch (Exception exception) {
         MoreCosmeticsManager.applyThrowable(exception);
         return false;
      }
   }

   public void applyAbstractClientPlayer(
      AbstractClientPlayer player,
      CosmeticsItemData cosmeticsItemData,
      ModelData modelData,
      float posX,
      float posX2,
      float posX3,
      float posX4,
      float posY,
      float posZ,
      AuxiliaryFloatEntryBridge auxiliaryFloatEntryBridge,
      boolean flag
   ) {
      GlStateManager.applyState14();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      passiveListener.updateState2();
      PassiveListener passiveListener2 = passiveListener;
      boolean flag2 = false;
      float factor2 = this.computeFactor(cosmeticsItemData, auxiliaryFloatEntryBridge, flag2, passiveListener2);
      float factor3 = 180.0F;
      passiveListener.applyFloat(factor3);
      if (auxiliaryFloatEntryBridge != null) {
         PassiveListener passiveListener3 = passiveListener;
         this.applyPrimaryFloatEntry(cosmeticsItemData, factor2, modelData, passiveListener3);
      }

      if (modelData.resize) {
         PassiveListener passiveListener4 = passiveListener;
         float factor4;
         float factor5 = factor4 = cosmeticsItemData.getFactor9();
         float factor6 = cosmeticsItemData.getFactor9();
         passiveListener4.applyFloat2(factor6, factor5, factor4);
      }

      float[] factorArray = cosmeticsItemData.getFactorArray3();
      if (!flag && factorArray != null && factorArray.length > 5) {
         float factor7 = 0.0F;
         if (cosmeticsItemData.getCount3() == BezierAdapter.SHIELD.getCount() && player.getItemStack3(2) != null) {
            factor7 = 0.05F;
         }

         float factor8 = factorArray[0];
         float factor9 = factorArray[1];
         float factor10 = factorArray[2] + factor7;
         float factor11 = factor9;
         float factor12 = factor8;
         passiveListener.applyFloat2(factor12, factor11, factor10);
         factor9 = factorArray[3];
         float factor13 = factorArray[4];
         float factor14 = factorArray[5] - factor7;
         float factor15 = factor13;
         float factor16 = factor9;
         passiveListener.applyFloatByFloat(factor16, factor15, factor14);
      }

      PassiveListener passiveListener5 = passiveListener;
      this.applyStandardHashMapEntry(cosmeticsItemData, posX3, posX4, posY, posZ, modelData, passiveListener5);
      if (cosmeticsItemData.isActive3()) {
         String text = cosmeticsItemData.getText();
         ArrayList arrayList = cosmeticsItemData.getArrayList();
         this.applyList(arrayList, modelData, auxiliaryFloatEntryBridge, posX3, posX4, posY, posZ, text, true);
      }

      if (cosmeticsItemData.isActive5() && player != null) {
         this.applyAbstractClientPlayer(player, cosmeticsItemData, modelData, auxiliaryFloatEntryBridge, posX3, posX4, posY, posZ);
      }

      Minecraft mc = Minecraft.getMinecraft();
      String labelText = cosmeticsItemData.getLabelText();
      if (labelText != null) {
         ResourceLocation location = (ResourceLocation)hashMap3.get(labelText);
         if (location == null) {
            hashMap3.put(labelText, location = new ResourceLocation("morecosmetics/" + labelText));
         }

         mc.getTextureManager().applyResourceLocation3(location);
      }

      passiveListener.updateState2();
      if (cosmeticsItemData.isActive4()) {
         if (cosmeticsItemData.getMainControllerManager().isActive()) {
            CombatDelayProperty combatDelayProperty = CombatDelayProperty.IDLE;
            if (player.lastTickPosX - player.posX != 0.0 || player.lastTickPosZ - player.posZ != 0.0) {
               combatDelayProperty = CombatDelayProperty.MOVING;
            }

            if (player.isSprinting()) {
               combatDelayProperty = CombatDelayProperty.SNEAKING;
            }

            cosmeticsItemData.getMainControllerManager().applyCombatDelayProperty(combatDelayProperty);
         }

         DirectFloatHolder directFloatHolder2 = this.directFloatHolder;
         Integer integer = cosmeticsItemData.getInteger();
         MainControllerManager mainControllerManager = cosmeticsItemData.getMainControllerManager();
         float factor17 = ExtraFloatEntry.factor2;
         MainControllerManager mainControllerManager2 = mainControllerManager;
         Integer count = integer;
         directFloatHolder2.applyInteger(count, mainControllerManager2, factor17);
      }

      GlStateManager.disableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      DirectFloatHolder directFloatHolder3 = this.directFloatHolder;
      EnhancedListEntry[] enhancedListEntryArray = cosmeticsItemData.getEnhancedListEntryArray();
      directFloatHolder3.applyCosmeticsItemData(cosmeticsItemData, this, enhancedListEntryArray, modelData, auxiliaryFloatEntryBridge, posX3, posX4, posY, posZ);
      GlStateManager.enableBlend();
      passiveListener.updateState();
      passiveListener.updateState();
   }

   private void applyAbstractClientPlayer(
      AbstractClientPlayer player, CosmeticsItemData cosmeticsItemData, ModelData modelData, AuxiliaryFloatEntryBridge auxiliaryFloatEntryBridge, float posX, float posX2, float posY, float posZ
   ) {

      for (int index = 0; index < cosmeticsItemData.getArrayListAsArrayList().size(); index++) {
         FloatEntryBridge floatEntryBridge = (FloatEntryBridge)cosmeticsItemData.getArrayListAsArrayList().get(index);
         SettingOverlay settingOverlay2 = modelData.item != null && modelData.item.length > index ? modelData.item[index] : settingOverlay;
         if (settingOverlay2.visible) {
            int count = settingOverlay2.id == 0 ? floatEntryBridge.getCount2() : settingOverlay2.id;
            ItemStack stack = (ItemStack)hashMap2.get(count);
            if (stack == null && !hashMap2.containsKey(floatEntryBridge.getCount2())) {
               hashMap2.put(floatEntryBridge.getCount2(), stack = new ItemStack(Item.processAction(floatEntryBridge.getCount2())));
            }

            if (stack != null) {
               passiveListener.updateState2();
               PassiveListener passiveListener2 = passiveListener;
               boolean flag = true;
               float factor2 = this.computeFactor(floatEntryBridge, auxiliaryFloatEntryBridge, flag, passiveListener2);
               PassiveListener passiveListener3 = passiveListener;
               this.applyPrimaryFloatEntry(floatEntryBridge, factor2, settingOverlay2, passiveListener3);
               PassiveListener passiveListener4 = passiveListener;
               this.applyStandardHashMapEntry(floatEntryBridge, posX, posX2, posY, posZ, settingOverlay2, passiveListener4);
               float factor3 = 0.5F;
               float factor4 = 0.5F;
               float factor5 = 0.5F;
               passiveListener.applyFloat2(factor5, factor4, factor3);
               Minecraft.getMinecraft().getItemRenderer().applyEntityLivingBaseByEntityLivingBase(player, stack, ItemPhysicsNoneType.FIXED);
               passiveListener.updateState();
            }
         }
      }
   }

   private void applyBoxesManager(BoxesManager boxesManager, boolean flag, PassiveListener passiveListener2) {
      if (flag) {
         float factor2 = 180.0F;
         passiveListener2.applyFloat(factor2);
      }

      float factor3 = boxesManager.factor5 * 0.0625F;
      float factor4 = boxesManager.factor2 * 0.0625F;
      float factor5 = boxesManager.factor10 * 0.0625F;
      float factor6 = factor4;
      float factor7 = factor3;
      passiveListener2.applyFloatByFloat(factor7, factor6, factor5);
      float factor8 = boxesManager.factor * (180.0F / (float)Math.PI);
      factor3 = boxesManager.factor9 * (180.0F / (float)Math.PI);
      float factor9 = boxesManager.factor8 * (180.0F / (float)Math.PI);
      float factor10 = factor3;
      float factor11 = factor8;
      passiveListener2.applyFloat(factor11, factor10, factor9);
      if (flag) {
         float factor12 = 180.0F;
         passiveListener2.applyFloat(factor12);
      }
   }

   public void applyPrimaryFloatEntry(PrimaryFloatEntry primaryFloatEntry, float factor2, SettingOverlay settingOverlay2, PassiveListener passiveListener2) {
      short shortValue = 6165;
      char character = '\ue30d';
      char character2 = '\ufd2d';
      float factor3 = primaryFloatEntry.getFactor7() + settingOverlay2.x;
      float factor4 = primaryFloatEntry.getFactor4() + factor2 + settingOverlay2.y;
      float factor5 = primaryFloatEntry.getFactor5() + settingOverlay2.z;
      float factor6 = factor4;
      float factor7 = factor3;
      passiveListener2.applyFloatByFloat(factor7, factor6, factor5);
      float factor8 = primaryFloatEntry.getFactor2() + settingOverlay2.yaw;
      passiveListener2.applyFloat2(factor8);
      float factor9 = primaryFloatEntry.getFactor() + settingOverlay2.pitch;
      passiveListener2.applyFloatByFloat(factor9);
      float factor10 = primaryFloatEntry.getFactor3() + settingOverlay2.roll;
      passiveListener2.applyFloat(factor10);
      float factor11 = primaryFloatEntry.getFactor6() * settingOverlay2.scale;
      float factor12;
      float factor13 = factor12 = primaryFloatEntry.getFactor6() * settingOverlay2.scale;
      float factor14 = factor11;
      passiveListener2.applyFloat2(factor14, factor13, factor12);
   }

   @Override
   public void updateState() {

      try {
         Map map = Minecraft.getMinecraft().getRenderManager().unmodifiableMap();

         for (Object textObj : map.keySet()) {
            String text = (String)textObj;
            RenderPlayer renderPlayer = (RenderPlayer)map.get(text);
            List list = renderPlayer.getList();
            list.add(0, new FactoryItemEntry(renderPlayer, this, text.equals("slim")));
            int count = -1;

            for (int index = 0; index < list.size(); index++) {
               if ((PreMotionDispatcher)list.get(index) instanceof GoldenChestplate) {
                  count = index;
               }
            }

            if (count != -1) {
               this.preMotionDispatcher = (PreMotionDispatcher)list.remove(count);
            }

            super.hashMap.put(text, list);
         }
      } catch (Throwable throwable) {
         MoreCosmeticsManager.applyThrowable(throwable);
      }
   }

   public void applyStandardHashMapEntry(StandardHashMapEntry standardHashMapEntry, float posX, float posX2, float posY, float posZ, SettingOverlay settingOverlay2, PassiveListener passiveListener2) {
      if (standardHashMapEntry.isActive() && settingOverlay2.animation) {
         for (Object activeOptionTypeObj : standardHashMapEntry.getHashMap().keySet()) {
            ActiveOptionType activeOptionType = (ActiveOptionType)activeOptionTypeObj;
            LocalFloatHolder localFloatHolder = (LocalFloatHolder)standardHashMapEntry.getHashMap().get(activeOptionType);
            CombatIntervalProperty combatIntervalProperty = localFloatHolder.getCombatIntervalProperty();
            float factor2 = localFloatHolder.getFactor() * settingOverlay2.mulitply;
            switch (AngleEstimator.countArray2[activeOptionType.ordinal()]) {
               case 1:
                  float factor3 = factor2 * InternalFloatArrayEntry.computeFactor(posX / 10.0F) / 20.0F;
                  float factor4 = (float)combatIntervalProperty.x * factor3;
                  float factor5 = (float)combatIntervalProperty.y * factor3;
                  float factor6 = (float)combatIntervalProperty.z * factor3;
                  float factor7 = factor5;
                  float factor8 = factor4;
                  passiveListener2.applyFloatByFloat(factor8, factor7, factor6);
                  break;
               case 2:
                  float factor9 = factor2 * posX;
                  float factor10 = (float)combatIntervalProperty.x * factor9;
                  float factor11 = (float)combatIntervalProperty.y * factor9;
                  float factor12 = (float)combatIntervalProperty.z * factor9;
                  float factor13 = factor11;
                  float factor14 = factor10;
                  passiveListener2.applyFloat(factor14, factor13, factor12);
                  break;
               case 3:
                  float factor15 = factor2 * (posX2 / 3.0F);
                  float factor16 = (float)combatIntervalProperty.x * factor15;
                  float factor17 = (float)combatIntervalProperty.y * factor15;
                  float factor18 = (float)combatIntervalProperty.z * factor15;
                  float factor19 = factor17;
                  float factor20 = factor16;
                  passiveListener2.applyFloat(factor20, factor19, factor18);
                  break;
               case 4:
                  float factor21 = factor2 * (posY / 3.0F);
                  float factor22 = (float)combatIntervalProperty.x * factor21;
                  float factor23 = (float)combatIntervalProperty.y * factor21;
                  float factor24 = (float)combatIntervalProperty.z * factor21;
                  float factor25 = factor23;
                  float factor26 = factor22;
                  passiveListener2.applyFloat(factor26, factor25, factor24);
                  break;
               case 5:
                  float factor27 = 1.0F + factor2 * InternalFloatArrayEntry.computeFactor(posX / 10.0F) / 20.0F;
                  passiveListener2.applyFloat2(factor27, factor27, factor27);
               case 6:
            }
         }
      }
   }

   public float computeFactor(
      AbstractClientPlayer player, byte byteValue, float posX, float posY, float posZ, AuxiliaryFloatEntryBridge auxiliaryFloatEntryBridge, boolean flag, float factor2
   ) {
      ExtraFloatEntry.factor = posZ;
      ExtraFloatEntry.factor2 = factor2;
      if (!player.isActive12() && !player.matchesEntityPlayer4(Minecraft.getMinecraft().thePlayer)) {
         if (this.preMotionDispatcher != null) {
            this.preMotionDispatcher.applyEntityLivingBase(player, posX, posY, factor2, posZ, 0.0F, 0.0F, 0.0625F);
         }

         float factor3 = 0.0F;
         Client.getClient().getModuleManager().cosmetics.applyAbstractClientPlayer(player);
         if (player.isEmpty()) {
            try {
               double offset = player.value30 + (player.value25 - player.value30) * (double)factor2 - (player.prevPosX + (player.posX - player.prevPosX) * (double)factor2);
               double offset2 = player.value28 + (player.value27 - player.value28) * (double)factor2 - (player.prevPosY + (player.posY - player.prevPosY) * (double)factor2);
               double offset3 = player.value29 + (player.value26 - player.value29) * (double)factor2 - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double)factor2);
               float factor4 = player.factor12 + (player.scaleFactor8 - player.factor12) * factor2;
               double offset4 = (double)InternalFloatArrayEntry.computeFactorByFloat(factor4 * (float) Math.PI / 180.0F);
               double offset5 = (double)(-InternalFloatArrayEntry.computeFactor(factor4 * (float) Math.PI / 180.0F));
               float factor5 = (float)offset2 * 10.0F;
               float factor6 = InternalFloatArrayEntry.computeFactor(factor5, -6.0F, 32.0F);
               float factor7 = (float)(offset * offset4 + offset3 * offset5) * 100.0F;
               float factor8 = InternalFloatArrayEntry.computeFactor(factor7, 0.0F, 180.0F);
               float factor9 = InternalFloatArrayEntry.computeFactor(factor7, -180.0F, 0.0F);
               passiveListener.updateState2();
               if (player.isSprinting()) {
                  float factor10 = 0.0F;
                  float factor11 = 0.2F;
                  float factor12 = 0.0F;
                  passiveListener.applyFloatByFloat(factor12, factor11, factor10);
               }

               for (Object countObj : player.getMap2().keySet()) {
                  Integer count = (Integer)countObj;
                  ModelData modelData = (ModelData)player.getMap2().get(count);
                  if (modelData != null && modelData.isActive()) {
                     CosmeticsItemData cosmeticsItemData = (CosmeticsItemData)super.failedToLoadMaskFromManager.getHashMap().get(count);
                     if (cosmeticsItemData != null) {
                        if (AngleFormatter.getAngleFormatter().enabled) {
                           int count2 = cosmeticsItemData.getMovementConfigProperty().getCount();
                           if (count2 != -1 && player.getItemStack3(count2) != null) {
                              continue;
                           }
                        }

                        float factor13 = cosmeticsItemData.getFactor11();
                        if (cosmeticsItemData.getMovementConfigProperty() == MovementConfigProperty.HEAD
                           || cosmeticsItemData.getMovementConfigProperty() == MovementConfigProperty.ABOVE_HEAD) {
                           factor13 += modelData.y;
                        }

                        if (factor3 < factor13) {
                           factor3 = factor13;
                        }

                        this.applyAbstractClientPlayer(player, cosmeticsItemData, modelData, posX, posY, posZ, factor8, factor9, factor6, auxiliaryFloatEntryBridge, flag);
                     }
                  }
               }

               passiveListener.updateState();
            } catch (Throwable throwable) {
               MoreCosmeticsManager.applyThrowable(throwable);
            }
         }

         return factor3;
      } else {
         return 0.0F;
      }
   }

   public float computeFactor(PrimaryFloatEntry primaryFloatEntry, AuxiliaryFloatEntryBridge auxiliaryFloatEntryBridge, boolean flag, PassiveListener passiveListener2) {
      float factor2 = 0.0F;
      if (auxiliaryFloatEntryBridge != null) {
         switch (AngleEstimator.countArray[primaryFloatEntry.getMovementConfigProperty().ordinal()]) {
            case 1:
            default:
               break;
            case 2:
               BoxesManager boxesManager = auxiliaryFloatEntryBridge.boxesManager7;
               this.applyBoxesManager(boxesManager, flag, passiveListener2);
               factor2 = -0.3F;
               break;
            case 3:
               BoxesManager boxesManager2 = auxiliaryFloatEntryBridge.boxesManager3;
               this.applyBoxesManager(boxesManager2, flag, passiveListener2);
               factor2 = 0.5F;
               break;
            case 4:
               factor2 = 0.75F;
               break;
            case 5:
               BoxesManager boxesManager3 = auxiliaryFloatEntryBridge.boxesManager2;
               this.applyBoxesManager(boxesManager3, flag, passiveListener2);
               factor2 = -0.25F;
               break;
            case 6:
               BoxesManager boxesManager4 = auxiliaryFloatEntryBridge.boxesManager;
               this.applyBoxesManager(boxesManager4, flag, passiveListener2);
               factor2 = -0.25F;
               break;
            case 7:
               BoxesManager boxesManager5 = auxiliaryFloatEntryBridge.boxesManager5;
               this.applyBoxesManager(boxesManager5, flag, passiveListener2);
               factor2 = -0.35F;
               break;
            case 8:
               BoxesManager boxesManager6 = auxiliaryFloatEntryBridge.boxesManager6;
               this.applyBoxesManager(boxesManager6, flag, passiveListener2);
               factor2 = -0.35F;
         }
      }

      return factor2;
   }

   public void applyList(List list, ModelData modelData, AuxiliaryFloatEntryBridge auxiliaryFloatEntryBridge, float posX, float posX2, float posY, float posZ, String text, boolean flag) {
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.disableBlend();

      for (Object nativeFloatEntryObj : list) {
         NativeFloatEntry nativeFloatEntry = (NativeFloatEntry)nativeFloatEntryObj;
         if (flag != nativeFloatEntry.isActiveAsFlag()) {
            SettingOverlay settingOverlay2 = modelData.texture != null && modelData.texture.length > nativeFloatEntry.getCount2() ? modelData.texture[nativeFloatEntry.getCount2()] : settingOverlay;
            if (settingOverlay2.visible) {
               String text2 = settingOverlay2.url == null ? nativeFloatEntry.getLabelText() : settingOverlay2.url;
               if (text2 != null && text2.length() >= 3) {
                  String text3 = text2 + text + nativeFloatEntry.getCount2();
                  JpegManager jpegManager3 = this.jpegManager;
                  BufferedImage bufferedImage = nativeFloatEntry.getBufferedImage();
                  BufferedImage bufferedImage2 = bufferedImage;
                  AuxiliaryLoggerEntryBridge auxiliaryLoggerEntryBridge = jpegManager3.resolveAuxiliaryLoggerEntryBridge(text3, text2, bufferedImage2);
                  if (auxiliaryLoggerEntryBridge != null) {
                     passiveListener.updateState2();
                     PassiveListener passiveListener2 = passiveListener;
                     boolean flag2 = true;
                     float factor2 = this.computeFactor(nativeFloatEntry, auxiliaryFloatEntryBridge, flag2, passiveListener2);
                     PassiveListener passiveListener3 = passiveListener;
                     this.applyPrimaryFloatEntry(nativeFloatEntry, factor2, settingOverlay2, passiveListener3);
                     PassiveListener passiveListener4 = passiveListener;
                     this.applyStandardHashMapEntry(nativeFloatEntry, posX, posX2, posY, posZ, settingOverlay2, passiveListener4);
                     Minecraft.getMinecraft().getTextureManager().applyResourceLocation3(auxiliaryLoggerEntryBridge.getResourceLocation3());
                     float factor3 = -0.5F;
                     float factor4 = -0.5F;
                     float factor5 = -0.5F;
                     passiveListener.applyFloat2(factor5, factor4, factor3);
                     int count3 = settingOverlay2.color == 0 ? nativeFloatEntry.getCount4() : settingOverlay2.color;
                     client.render.Helper.applyFloat(-0.5F, -0.5F, 256.0F, 256.0F, auxiliaryLoggerEntryBridge.getFactor(), 1.0F, nativeFloatEntry.getFactor8(), count3);
                     passiveListener.updateState();
                  }
               }
            }
         }
      }

      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.enableBlend();
   }

   public FailedToLoadMaskFromManagerEntryAdapter() {
      this.jpegManager2 = new JpegManager("mcloaks");
      this.goldenDust = new GoldenDust();
      this.directFloatHolder = new DirectFloatHolder();
   }
}
