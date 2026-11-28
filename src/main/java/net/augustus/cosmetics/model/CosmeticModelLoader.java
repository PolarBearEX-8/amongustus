package net.augustus.cosmetics.model;

import net.augustus.Augustus;
import net.augustus.cosmetics.CosmeticsHttpUtil;
import net.augustus.cosmetics.MoreCosmeticsCore;
import net.augustus.cosmetics.TrustedHostWhitelist;
import net.augustus.cosmetics.molang.MolangParser;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.ClientPaths;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import net.labymod.ModelConfig;
import org.apache.commons.io.FileUtils;

public abstract class CosmeticModelLoader {
   public HashMap modelsById;
   public HashMap modelsByName;
   private boolean loaded;
   private File cosmeticsDir;

   private void extractBundledCosmetics() {
      String str = Augustus.getInstance().getName().toLowerCase() + File.separator + "cosmetics";
      String str2 = "assets/augustus/cosmetics/";
      String str3 = str2 + "1.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "2.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "3.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "4.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "5.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "6.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "7.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "8.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "10.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "11.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "12.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "13.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "14.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "16.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "17.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "18.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "19.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "20.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "21.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "22.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "23.json";
      ClientFileUtil.extractResource(str3, str, true);
      str3 = str2 + "24.json";
      ClientFileUtil.extractResource(str3, str, true);
   }

   public boolean isLoaded() {
      return this.loaded;
   }

   private void loadAllAsyncTask() {
      this.loadAllModels();
   }

   public HashMap getModelsByName() {
      return this.modelsByName;
   }

   public CosmeticModelLoader() {
       super();
      this.modelsById = new HashMap();
      this.modelsByName = new HashMap();
      this.cosmeticsDir = new File(ClientPaths.CLIENT_DIR, "cosmetics");
      this.cosmeticsDir.mkdirs();
      MoreCosmeticsCore.EXECUTOR.execute(() -> this.loadAllAsyncTask());
   }

   public void loadAllModels() {
      this.extractBundledCosmetics();

      for (File file : this.cosmeticsDir.listFiles()) {
         if (file.getName().toLowerCase().endsWith(".json")) {
            this.loadModelFile(file, false);
         }
      }

      this.loaded = true;
   }

   private void readPartTransform(JsonObject json, AbstractCosmeticPart cosmeticPart) {
      if (json.has("pos")) {
         CosmeticBodyPart cosmeticBodyPart = CosmeticBodyPart.byId(json.get("pos").getAsInt());
         if (cosmeticBodyPart != null) {
            cosmeticPart.setBodyPart(cosmeticBodyPart);
         }
      }

      if (json.has("scale")) {
         cosmeticPart.setScale(json.get("scale").getAsFloat());
      }

      if (json.has("x")) {
         cosmeticPart.setOffsetX(json.get("x").getAsFloat());
      }

      if (json.has("y")) {
         cosmeticPart.setOffsetY(json.get("y").getAsFloat());
      }

      if (json.has("z")) {
         cosmeticPart.setOffsetZ(json.get("z").getAsFloat());
      }

      if (json.has("yaw")) {
         cosmeticPart.setYaw(json.get("yaw").getAsFloat());
      }

      if (json.has("pitch")) {
         cosmeticPart.setPitch(json.get("pitch").getAsFloat());
      }

      if (json.has("roll")) {
         cosmeticPart.setRoll(json.get("roll").getAsFloat());
      }
   }

   public void loadModelFile(File file, boolean flag) {

      try (InputStreamReader inputStreamReader = new InputStreamReader(new BufferedInputStream(new FileInputStream(file)), Charset.forName("UTF-8"))) {
         JsonObject asJsonObject = MoreCosmeticsCore.JSON_PARSER.parse(inputStreamReader).getAsJsonObject();
         if (!asJsonObject.has("name")) {
            throw new IllegalArgumentException("Missing name");
         }

         if (!asJsonObject.has("model")) {
            throw new IllegalArgumentException("Missing model");
         }

         String asString = asJsonObject.get("name").getAsString();
         if (asJsonObject.has("minVersion")) {
            int asInt = asJsonObject.get("minVersion").getAsInt();
            if (asInt > 1200) {
               throw new IllegalStateException("Model requieres version " + asInt);
            }
         }

         int n = !flag && asJsonObject.has("id") ? asJsonObject.get("id").getAsInt() : asString.hashCode();
         int n2 = asJsonObject.has("category") ? asJsonObject.get("category").getAsInt() : 0;
         int n3 = asJsonObject.has("version") ? asJsonObject.get("version").getAsInt() : 0;
         String str = null;
         if (asJsonObject.has("texture")) {
            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(asJsonObject.get("texture").getAsString()))) {
               str = asString.replace(" ", "").toLowerCase() + n;
               this.registerModelTexture(str, ImageIO.read(byteArrayInputStream));
            } catch (IOException e) {
               MoreCosmeticsCore.logInfo("Failed to load texture from " + asString);
            }
         }

         ModelConfig[] modelConfigs = null;
         if (asJsonObject.has("config")) {
            JsonArray asJsonArray = asJsonObject.get("config").getAsJsonArray();
            modelConfigs = new ModelConfig[asJsonArray.size()];

            for (int i = 0; i < asJsonArray.size(); i++) {
               modelConfigs[i] = (ModelConfig)MoreCosmeticsCore.GSON.fromJson(asJsonArray.get(i), ModelConfig.class);
               JsonObject asJsonObject2 = asJsonArray.get(i).getAsJsonObject();
               if (asJsonObject2.has("value")) {
                  if (modelConfigs[i].value.getClass().equals(Double.class)) {
                     if (!asJsonObject2.get("value").getAsString().contains(".")) {
                        modelConfigs[i].value = asJsonObject2.get("value").getAsInt();
                     }
                  }

                  if (modelConfigs[i].value.getClass().equals(String.class)) {
                     String asString3 = asJsonObject2.get("value").getAsString();
                     if (asString3.startsWith("http")) {
                        if (!TrustedHostWhitelist.isTrustedHost(asString3)) {
                           MoreCosmeticsCore.logInfo("Removed url: " + asString3);
                           modelConfigs[i].value = "";
                        }
                     }
                  }

                  if (modelConfigs[i].src != null && !TrustedHostWhitelist.isTrustedHost(modelConfigs[i].src)) {
                     MoreCosmeticsCore.logInfo("Removed mask url: " + modelConfigs[i].src);
                     modelConfigs[i].src = null;
                  }
               }
            }
         }

         GeoModel geoModel = GeckoLibRegistry.MODEL_PARSER.loadModel(asString, asJsonObject.get("model").getAsJsonObject().toString());
         CosmeticSubModel[] cosmeticSubModels = new CosmeticSubModel[0];
         if (asJsonObject.has("models")) {
            JsonArray asJsonArray2 = asJsonObject.get("models").getAsJsonArray();
            cosmeticSubModels = new CosmeticSubModel[asJsonArray2.size()];

            for (int j = 0; j < asJsonArray2.size(); j++) {
               JsonObject asJsonObject3 = asJsonArray2.get(j).getAsJsonObject();
               String str4 = asJsonObject3.has("name") ? asJsonObject3.get("name").getAsString() : "ModelComponent " + (j + 1);
               int n4 = asJsonObject3.has("color") ? 0xFF000000 | asJsonObject3.get("color").getAsInt() : -1;
               cosmeticSubModels[j] = new CosmeticSubModel(str4, n4, true);
               this.readPartData(asJsonObject3, cosmeticSubModels[j]);
            }
         }

         for (int k = 0; k < cosmeticSubModels.length; k++) {
            CosmeticSubModel cosmeticSubModel2 = cosmeticSubModels[k];
            this.bindSubModelToBones(geoModel.topLevelBones, cosmeticSubModel2, k);
         }

         CosmeticModel cosmeticModel = new CosmeticModel(asString, n, n2, n3, flag, str, geoModel, cosmeticSubModels, modelConfigs, file);
         if (asJsonObject.has("animation")) {
            cosmeticModel.setAnimationFile(GeckoLibRegistry.ANIMATION_LOADER.loadAllAnimations(GeckoLibRegistry.MOLANG_PARSER, asJsonObject.get("animation").getAsJsonObject()));
         }

         if (asJsonObject.has("items")) {
            JsonArray asJsonArray3 = asJsonObject.get("items").getAsJsonArray();

            for (int m = 0; m < asJsonArray3.size(); m++) {
               JsonObject asJsonObject5 = asJsonArray3.get(m).getAsJsonObject();
               CosmeticItemModel cosmeticItemModel = new CosmeticItemModel(asJsonObject5.get("item").getAsInt());
               this.readPartData(asJsonObject5, cosmeticItemModel);
               cosmeticModel.getItemModels().add(cosmeticItemModel);
            }
         }

         if (asJsonObject.has("textures")) {
            JsonArray asJsonArray4 = asJsonObject.get("textures").getAsJsonArray();

            for (int idx = 0; idx < asJsonArray4.size(); idx++) {
               JsonObject asJsonObject6 = asJsonArray4.get(idx).getAsJsonObject();
               String asString4 = asJsonObject6.get("url").getAsString();
               if (asString4 != null && !asString4.isEmpty() && !TrustedHostWhitelist.isTrustedHost(asString4)) {
                  MoreCosmeticsCore.logInfo("Removed texture url: " + asString4);
                  asString4 = "";
               }

               CosmeticTextureModel cosmeticTextureModel = new CosmeticTextureModel(asString4, idx);
               if (asJsonObject6.has("opacity")) {
                  cosmeticTextureModel.setOpacity(asJsonObject6.get("opacity").getAsFloat());
               }

               if (asJsonObject6.has("color")) {
                  cosmeticTextureModel.setColor(asJsonObject6.get("color").getAsInt());
               }

               if (asJsonObject6.has("bound")) {
                  cosmeticTextureModel.setBoundSubModelIndex(asJsonObject6.get("bound").getAsInt());
                  if (cosmeticTextureModel.getBoundSubModelIndex() >= 0) {
                     cosmeticModel.getSubModels()[cosmeticTextureModel.getBoundSubModelIndex()].getBoundTextures().add(cosmeticTextureModel);
                  }
               }

               if (asJsonObject6.has("mask")) {
                  this.loadTextureMask(cosmeticTextureModel, asString, asJsonObject6.get("mask").getAsString(), (Consumer)null);
               }

               this.readPartData(asJsonObject6, cosmeticTextureModel);
               cosmeticModel.getTextureModels().add(cosmeticTextureModel);
            }
         }

         if (asJsonObject.has("height")) {
            cosmeticModel.setHeight(asJsonObject.get("height").getAsFloat());
         }

         if (asJsonObject.has("resize")) {
            cosmeticModel.setResizeFactor(asJsonObject.get("resize").getAsFloat());
         }

         if (asJsonObject.has("previewScale")) {
            cosmeticModel.setPreviewScale(asJsonObject.get("previewScale").getAsFloat());
         }

         if (asJsonObject.has("previewY")) {
            cosmeticModel.setPreviewY(asJsonObject.get("previewY").getAsFloat());
         }

         if (asJsonObject.has("previewRot")) {
            cosmeticModel.setPreviewRotation((float[])MoreCosmeticsCore.GSON.fromJson(asJsonObject.get("previewRot"), float[].class));
         }

         if (asJsonObject.has("adjustment")) {
            cosmeticModel.setAdjustment((float[])MoreCosmeticsCore.GSON.fromJson(asJsonObject.get("adjustment"), float[].class));
         }

         if (asJsonObject.has("sideTransform")) {
            cosmeticModel.setSideTransform((float[])MoreCosmeticsCore.GSON.fromJson(asJsonObject.get("sideTransform"), float[].class));
         }

         this.readPartData(asJsonObject, cosmeticModel);
         this.modelsById.put(n, cosmeticModel);
         this.modelsByName.put(cosmeticModel.getName(), cosmeticModel);
      } catch (Exception e2) {
      }
   }

   private void readPartData(JsonObject json, AbstractCosmeticPart cosmeticPart) {
      this.readPartTransform(json, cosmeticPart);
      this.readPartEffects(json, cosmeticPart);
   }

   private void writePartData(JsonObject json, AbstractCosmeticPart cosmeticPart) {
      this.writePartTransform(json, cosmeticPart);
      this.writePartEffects(json, cosmeticPart);
   }

   public CosmeticModel getModelByName(String str) {
      return (CosmeticModel)this.modelsByName.get(str);
   }

   private void writePartEffects(JsonObject json, AnimatedCosmeticPart animatedCosmeticPart) {
      JsonArray array = new JsonArray();

      for (CosmeticEffect cosmeticEffect : (Iterable<CosmeticEffect>)(animatedCosmeticPart.getEffects().values())) {
         JsonObject json2 = new JsonObject();
         json2.addProperty("type", cosmeticEffect.getType().getId());
         if (cosmeticEffect.getAxis() != null) {
            json2.addProperty("axis", cosmeticEffect.getAxis().getId());
         }

         json2.addProperty("mult", cosmeticEffect.getMultiplier());
         array.add(json2);
      }

      json.add("animations", array);
   }

   public void loadTextureMask(CosmeticTextureModel cosmeticTextureModel, String str, String str2, Consumer consumer) {
      Optional optional = Optional.ofNullable(consumer);
      if (str2.startsWith("http")) {
         if (TrustedHostWhitelist.isTrustedHost(str2)) {
            MoreCosmeticsCore.EXECUTOR.execute(() -> {

               try {
                  Object[] array = new Object[0];

                  try (InputStream in = CosmeticsHttpUtil.openUrlStream(str2, (short)5000, array)) {
                     cosmeticTextureModel.setMaskImage(ImageIO.read(in));
                     cosmeticTextureModel.setMaskUrl(str2);
                     optional.ifPresent(obj -> ((Consumer)obj).accept(true));
                  }
               } catch (IOException e) {
                  MoreCosmeticsCore.logInfo("Failed to load mask from " + str + ": " + e.getMessage());
                  optional.ifPresent(obj2 -> ((Consumer)obj2).accept(false));
               }
            });
         }
      } else {
         try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(str2))) {
            cosmeticTextureModel.setMaskImage(ImageIO.read(byteArrayInputStream));
            optional.ifPresent(obj3 -> ((Consumer)obj3).accept(true));
         } catch (IOException e2) {
            MoreCosmeticsCore.logInfo("Failed to load mask from " + str + ": " + e2.getMessage());
            optional.ifPresent(obj4 -> ((Consumer)obj4).accept(false));
         }
      }
   }

   private void writePartTransform(JsonObject json, AbstractCosmeticPart cosmeticPart) {
      json.addProperty("pos", cosmeticPart.getBodyPart().getId());
      json.addProperty("scale", cosmeticPart.getScale());
      json.addProperty("x", cosmeticPart.getOffsetX());
      json.addProperty("y", cosmeticPart.getOffsetY());
      json.addProperty("z", cosmeticPart.getOffsetZ());
      json.addProperty("yaw", cosmeticPart.getYaw());
      json.addProperty("pitch", cosmeticPart.getPitch());
      json.addProperty("roll", cosmeticPart.getRoll());
   }

   public CosmeticModel getModelById(Integer num) {
      return (CosmeticModel)this.modelsById.get(num);
   }

   public void saveModelFile(CosmeticModel cosmeticModel) {

      try (BufferedReader reader = new BufferedReader(new FileReader(cosmeticModel.getSourceFile()))) {
         JsonObject asJsonObject = MoreCosmeticsCore.JSON_PARSER.parse(reader).getAsJsonObject();
         asJsonObject.addProperty("name", cosmeticModel.getName());
         asJsonObject.addProperty("category", cosmeticModel.getCategoryId());
         asJsonObject.addProperty("height", cosmeticModel.getHeight());
         asJsonObject.addProperty("previewY", cosmeticModel.getPreviewY());
         asJsonObject.addProperty("previewScale", cosmeticModel.getPreviewScale());
         if (cosmeticModel.getPreviewRotation() != null) {
            asJsonObject.add("previewRot", MoreCosmeticsCore.GSON.toJsonTree(cosmeticModel.getPreviewRotation()));
         }

         this.writePartData(asJsonObject, cosmeticModel);
         if (cosmeticModel.hasItemModels()) {
            JsonArray array = new JsonArray();

            for (CosmeticItemModel cosmeticItemModel : (Iterable<CosmeticItemModel>)(cosmeticModel.getItemModels())) {
               JsonObject json = new JsonObject();
               json.addProperty("item", cosmeticItemModel.getItemId());
               this.writePartData(json, cosmeticItemModel);
               array.add(json);
            }

            asJsonObject.add("items", array);
         }

         if (cosmeticModel.hasTextureModels()) {
            JsonArray array2 = new JsonArray();

            for (CosmeticTextureModel cosmeticTextureModel : (Iterable<CosmeticTextureModel>)(cosmeticModel.getTextureModels())) {
               JsonObject json2 = new JsonObject();
               json2.addProperty("url", cosmeticTextureModel.getUrl());
               json2.addProperty("opacity", cosmeticTextureModel.getOpacity());
               json2.addProperty("color", cosmeticTextureModel.getColor());
               json2.addProperty("bound", cosmeticTextureModel.getBoundSubModelIndex());
               if (cosmeticTextureModel.getMaskUrl() != null && cosmeticTextureModel.getMaskUrl().startsWith("http")) {
                  json2.addProperty("mask", cosmeticTextureModel.getMaskUrl());
               }

               this.writePartData(json2, cosmeticTextureModel);
               array2.add(json2);
            }

            asJsonObject.add("textures", array2);
         }

         if (cosmeticModel.hasSubModels()) {
            JsonArray asJsonArray = asJsonObject.get("models").getAsJsonArray();

            for (int i = 0; i < asJsonArray.size(); i++) {
               JsonObject asJsonObject2 = asJsonArray.get(i).getAsJsonObject();
               CosmeticSubModel cosmeticSubModel = cosmeticModel.getSubModels()[i];
               asJsonObject2.addProperty("visible", cosmeticSubModel.isVisible());
               this.writePartData(asJsonObject2, cosmeticSubModel);
            }
         }

         if (cosmeticModel.hasConfigs()) {
            asJsonObject.add("config", MoreCosmeticsCore.GSON.toJsonTree(cosmeticModel.getConfigs()));
         }

         FileUtils.writeStringToFile(cosmeticModel.getSourceFile(), asJsonObject.toString());
      } catch (IOException e) {
         MoreCosmeticsCore.logError(e);
      }
   }

   private void bindSubModelToBones(List list, CosmeticSubModel cosmeticSubModel, int n) {

      for (GeoBone geoBone : (Iterable<GeoBone>)(list)) {
         if (geoBone.getName().toLowerCase().startsWith(cosmeticSubModel.getName().toLowerCase())) {
            geoBone.subModelIndex = n;
         } else {
            this.bindSubModelToBones(geoBone.childBones, cosmeticSubModel, n);
         }
      }
   }

   private void readPartEffects(JsonObject json, AnimatedCosmeticPart animatedCosmeticPart) {
      if (json.has("animations")) {
         for (JsonElement elem : json.get("animations").getAsJsonArray()) {
            JsonObject asJsonObject = elem.getAsJsonObject();
            CosmeticEffect cosmeticEffect = new CosmeticEffect(CosmeticEffectType.byId(asJsonObject.get("type").getAsInt()));
            if (asJsonObject.has("axis")) {
               CosmeticAxis cosmeticAxis = CosmeticAxis.byId(asJsonObject.get("axis").getAsInt());
               if (cosmeticAxis != null) {
                  cosmeticEffect.setAxis(cosmeticAxis);
               }
            }

            cosmeticEffect.setMultiplier(asJsonObject.get("mult").getAsFloat());
            animatedCosmeticPart.addEffect(cosmeticEffect);
         }
      }
   }

   public HashMap getModelsById() {
      return this.modelsById;
   }

   public abstract void registerModelTexture(String str, BufferedImage image);

}
