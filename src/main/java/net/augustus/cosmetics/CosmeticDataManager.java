package net.augustus.cosmetics;

import net.augustus.ClientManagers;
import net.augustus.cosmetics.model.CosmeticModel;
import net.augustus.settings.ColorSetting;
import net.augustus.utils.ChatUtil;
import java.util.HashMap;
import java.util.UUID;
import net.labymod.ConfigAccessor;
import net.labymod.ModelConfig;
import net.labymod.ModelData;
import net.labymod.SettingOverlay;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;

public class CosmeticDataManager implements CosmeticTickListener {
   private HashMap modelDataCache = new HashMap();
   private static long lastHintTimestamp;

   private SettingOverlay[] createSettingOverlays(int n) {
      SettingOverlay[] settingOverlays = new SettingOverlay[n];

      for (int i = 0; i < settingOverlays.length; i++) {
         settingOverlays[i] = new SettingOverlay();
      }

      return settingOverlays;
   }

   @Override
   public void onTick(int n) {
   }

   public CosmeticDataManager(UUID uuid) {
   }

   public ModelData getOrCreateModelData(CosmeticModel cosmeticModel, boolean flag) {
      return (ModelData)(this.modelDataCache.getOrDefault(cosmeticModel, this.modelDataCache.computeIfAbsent(cosmeticModel, obj -> this.createModelDataCallback(cosmeticModel, flag, (CosmeticModel)obj))));
   }

   private ModelData buildModelData(CosmeticModel cosmeticModel, boolean flag) {
      ModelData modelData = null;
      if (flag) {
         int id = cosmeticModel.getId();
         modelData = this.loadSavedModelData(id);
         if (modelData != null && cosmeticModel.getVersion() == modelData.getVersion()) {
            return modelData;
         }
      }

      modelData = new ModelData();
      modelData.setVersion(cosmeticModel.getVersion());
      modelData.setSubModels(this.createSettingOverlays(cosmeticModel.getSubModels().length));
      if (cosmeticModel.hasTextureModels()) {
         modelData.setTextureModels(this.createSettingOverlays(cosmeticModel.getTextureModels().size()));
      }

      if (cosmeticModel.hasItemModels()) {
         modelData.setItemModels(this.createSettingOverlays(cosmeticModel.getItemModels().size()));
      }

      if (cosmeticModel.hasConfigs()) {
         for (ModelConfig modelConfig : cosmeticModel.getConfigs()) {
            Object value = modelConfig.value;
            if (value != null) {
               if (modelConfig.mult != 1.0F && value instanceof Number) {
                  value = ((Number)value).floatValue() / modelConfig.mult;
               }

               ConfigAccessor.set(modelConfig, modelData, value);
            }
         }
      }

      return modelData;
   }

   public void applyCosmeticToPlayer(boolean flag, CosmeticModel cosmeticModel, AbstractClientPlayer clientPlayer, float f, float f2, ColorSetting[] colorSettings) {
      ModelData modelData = (ModelData)clientPlayer.getCosmetics().get(cosmeticModel.getId());
      if (flag
         && !(clientPlayer instanceof EntityPlayerSP)
         && (clientPlayer.getCosmetics().size() > 3 || (double)f > 1.5)
         && System.currentTimeMillis() - lastHintTimestamp > 108000000L
         && !ClientManagers.moduleManager.cosmetics.hideCosmeticsOfOtherUsers.getValue()
         && ClientManagers.moduleManager.cosmetics.hintMessageThatRenderingOfCosmeticsCanBeDisabled.getValue()) {
         ChatUtil.sendChat("Hint: You can disable rendering of cosmetics. Render -> Cosmetics -> Hide cosmetics of other users");
         lastHintTimestamp = System.currentTimeMillis();
      }

      if (modelData != null) {
         if (modelData.isActive()) {
            for (int i = 0; i < modelData.model.length; i++) {
               SettingOverlay settingOverlay = modelData.model[i];
               settingOverlay.scale = f;
               modelData.scale = f;
               settingOverlay.y = f2;
               modelData.y = f2;
               settingOverlay.color = colorSettings[i].getColorInt((byte)0);
            }
         }

         if (modelData.isActive() != flag) {
            modelData.setActive(flag);
         }
      } else {
         modelData = this.getOrCreateModelData(cosmeticModel, true);
         modelData.setActive(flag);
         clientPlayer.getCosmetics().put(cosmeticModel.getId(), modelData.clone_m1());
      }
   }

   private ModelData loadSavedModelData(int n) {
      return null;
   }

   private ModelData createModelDataCallback(CosmeticModel cosmeticModel, boolean flag, CosmeticModel cosmeticModel2) {
      return this.buildModelData(cosmeticModel, flag);
   }

   static {

      lastHintTimestamp = 0L;
   }



}
