package client.util;

import client.Client;
import client.setting.ActiveOptionType;
import client.setting.CombatIntervalProperty;
import client.setting.MovementConfigProperty;
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
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import net.labymod.ModelConfig;
import net.minecraft.item.DefaultItemEntry;
import net.minecraft.item.ItemEntry20Entry;
import org.apache.commons.io.FileUtils;

public abstract class FailedToLoadMaskFromManager {
   public HashMap hashMap = new HashMap();
   public boolean enabled;
   public File file;
   public HashMap hashMap2 = new HashMap();

   private void applyJsonObjectByJsonObject(JsonObject jsonObject, PrimaryFloatEntry primaryFloatEntry) {
      if (jsonObject.has("pos")) {
         int asInt = jsonObject.get("pos").getAsInt();
         MovementConfigProperty movementConfigProperty = MovementConfigProperty.resolveMovementConfigProperty(asInt);
         if (movementConfigProperty != null) {
            primaryFloatEntry.setMovementConfigProperty(movementConfigProperty);
         }
      }

      if (jsonObject.has("scale")) {
         float asFloat = jsonObject.get("scale").getAsFloat();
         primaryFloatEntry.setFactor6(asFloat);
      }

      if (jsonObject.has("x")) {
         float asFloat2 = jsonObject.get("x").getAsFloat();
         primaryFloatEntry.setFactor7(asFloat2);
      }

      if (jsonObject.has("y")) {
         float asFloat3 = jsonObject.get("y").getAsFloat();
         primaryFloatEntry.setFactor4(asFloat3);
      }

      if (jsonObject.has("z")) {
         float asFloat4 = jsonObject.get("z").getAsFloat();
         primaryFloatEntry.setFactor5(asFloat4);
      }

      if (jsonObject.has("yaw")) {
         float asFloat5 = jsonObject.get("yaw").getAsFloat();
         primaryFloatEntry.setFactor2(asFloat5);
      }

      if (jsonObject.has("pitch")) {
         float asFloat6 = jsonObject.get("pitch").getAsFloat();
         primaryFloatEntry.setFactor(asFloat6);
      }

      if (jsonObject.has("roll")) {
         float asFloat7 = jsonObject.get("roll").getAsFloat();
         primaryFloatEntry.setFactor3(asFloat7);
      }
   }

   public HashMap getEntryMap2() {
      return this.hashMap2;
   }

   private static void applyConsumer(Consumer consumer) {
      consumer.accept(true);
   }

   public void applyFile(File file2, boolean flag) {

      try {
         InputStreamReader inputStreamReader = new InputStreamReader(new BufferedInputStream(new FileInputStream(file2)), Charset.forName("UTF-8"));
         Throwable throwable = null;
         boolean flag2 = false ;

         try {
            flag2 = true;
            JsonObject asJsonObject = MoreCosmeticsManager.jsonParser.parse(inputStreamReader).getAsJsonObject();
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

            int count = !flag && asJsonObject.has("id") ? asJsonObject.get("id").getAsInt() : asString.hashCode();
            int count2 = asJsonObject.has("category") ? asJsonObject.get("category").getAsInt() : 0;
            int count3 = asJsonObject.has("version") ? asJsonObject.get("version").getAsInt() : 0;
            String text = null;
            if (asJsonObject.has("texture")) {
               try {
                  ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(asJsonObject.get("texture").getAsString()));
                  Throwable throwable2 = null;
                  boolean flag3 = false ;

                  try {
                     flag3 = true;
                     text = asString.replace(" ", "").toLowerCase() + count;
                     BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
                     this.applyText(text, bufferedImage);
                     flag3 = false;
                  } catch (Throwable throwable3) {
                     throwable2 = throwable3;
                     throw throwable3;
                  } finally {
                     if (flag3) {
                        if (throwable2 != null) {
                           try {
                              byteArrayInputStream.close();
                           } catch (Throwable throwable4) {
                              throwable2.addSuppressed(throwable4);
                           }
                        } else {
                           byteArrayInputStream.close();
                        }
                     }
                  }

                  byteArrayInputStream.close();
               } catch (IOException ioException) {
                  MoreCosmeticsManager.applyText("Failed to load texture from " + asString);
               }
            }

            ModelConfig[] modelConfigArray = null;
            if (asJsonObject.has("config")) {
               JsonArray asJsonArray = asJsonObject.get("config").getAsJsonArray();
               modelConfigArray = new ModelConfig[asJsonArray.size()];

               for (int index = 0; index < asJsonArray.size(); index++) {
                  modelConfigArray[index] = (ModelConfig)MoreCosmeticsManager.gson.fromJson(asJsonArray.get(index), ModelConfig.class);
                  JsonObject asJsonObject2 = asJsonArray.get(index).getAsJsonObject();
                  if (asJsonObject2.has("value")) {
                     if (modelConfigArray[index].value.getClass().equals(Double.class) && !asJsonObject2.get("value").getAsString().contains(".")) {
                        modelConfigArray[index].value = asJsonObject2.get("value").getAsInt();
                     }

                     if (modelConfigArray[index].value.getClass().equals(String.class)) {
                        String asString2 = asJsonObject2.get("value").getAsString();
                        if (asString2.startsWith("http") && !CollectionHelper.matchesText(asString2)) {
                           MoreCosmeticsManager.applyText("Removed url: " + asString2);
                           modelConfigArray[index].value = "";
                        }
                     }

                     if (modelConfigArray[index].src != null && !CollectionHelper.matchesText(modelConfigArray[index].src)) {
                        String text2 = "Removed mask url: " + modelConfigArray[index].src;
                        MoreCosmeticsManager.applyText(text2);
                        modelConfigArray[index].src = null;
                     }
                  }
               }
            }

            ErrorParsingSManager errorParsingSManager = ItemEntry20Entry.errorParsingSManager;
            String text3 = asJsonObject.get("model").getAsJsonObject().toString();
            GenericListEntry genericListEntry = errorParsingSManager.resolveGenericListEntry(asString, text3);
            EnhancedListEntry[] enhancedListEntryArray = new EnhancedListEntry[0];
            if (asJsonObject.has("models")) {
               JsonArray asJsonArray2 = asJsonObject.get("models").getAsJsonArray();
               enhancedListEntryArray = new EnhancedListEntry[asJsonArray2.size()];

               for (int index2 = 0; index2 < asJsonArray2.size(); index2++) {
                  JsonObject asJsonObject3 = asJsonArray2.get(index2).getAsJsonObject();
                  String text4 = asJsonObject3.has("name") ? asJsonObject3.get("name").getAsString() : "ModelComponent " + (index2 + 1);
                  int count4 = asJsonObject3.has("color") ? 0xFF000000 | asJsonObject3.get("color").getAsInt() : -1;
                  enhancedListEntryArray[index2] = new EnhancedListEntry(text4, count4, true);
                  EnhancedListEntry enhancedListEntry = enhancedListEntryArray[index2];
                  this.applyJsonObject2(asJsonObject3, enhancedListEntry);
               }
            }

            for (int index3 = 0; index3 < enhancedListEntryArray.length; index3++) {
               EnhancedListEntry enhancedListEntry2 = enhancedListEntryArray[index3];
               List list = genericListEntry.list;
               this.applyList(list, enhancedListEntry2, index3);
            }

            CosmeticsItemData cosmeticsItemData = new CosmeticsItemData(asString, count, count2, count3, flag, text, genericListEntry, enhancedListEntryArray, modelConfigArray, file2);
            if (asJsonObject.has("animation")) {
               CouldNotLoadAnimationManager couldNotLoadAnimationManager = ItemEntry20Entry.couldNotLoadAnimationManager;
               DefaultItemEntry defaultItemEntry = ItemEntry20Entry.defaultItemEntry;
               JsonObject asJsonObject4 = asJsonObject.get("animation").getAsJsonObject();
               DefaultItemEntry defaultItemEntry2 = defaultItemEntry;
               ExtendedHashMapHolder extendedHashMapHolder = couldNotLoadAnimationManager.resolveExtendedHashMapHolder(defaultItemEntry2, asJsonObject4);
               cosmeticsItemData.applyExtendedHashMapHolder(extendedHashMapHolder);
            }

            if (asJsonObject.has("items")) {
               JsonArray asJsonArray3 = asJsonObject.get("items").getAsJsonArray();

               for (int index4 = 0; index4 < asJsonArray3.size(); index4++) {
                  JsonObject asJsonObject5 = asJsonArray3.get(index4).getAsJsonObject();
                  FloatEntryBridge floatEntryBridge = new FloatEntryBridge(asJsonObject5.get("item").getAsInt());
                  this.applyJsonObject2(asJsonObject5, floatEntryBridge);
                  cosmeticsItemData.getArrayListAsArrayList().add(floatEntryBridge);
               }
            }

            if (asJsonObject.has("textures")) {
               JsonArray asJsonArray4 = asJsonObject.get("textures").getAsJsonArray();

               for (int index5 = 0; index5 < asJsonArray4.size(); index5++) {
                  JsonObject asJsonObject6 = asJsonArray4.get(index5).getAsJsonObject();
                  String asString3 = asJsonObject6.get("url").getAsString();
                  if (asString3 != null && !asString3.isEmpty() && !CollectionHelper.matchesText(asString3)) {
                     MoreCosmeticsManager.applyText("Removed texture url: " + asString3);
                     asString3 = "";
                  }

                  NativeFloatEntry nativeFloatEntry = new NativeFloatEntry(asString3, index5);
                  if (asJsonObject6.has("opacity")) {
                     float asFloat = asJsonObject6.get("opacity").getAsFloat();
                     nativeFloatEntry.setFactor8(asFloat);
                  }

                  if (asJsonObject6.has("color")) {
                     int asInt2 = asJsonObject6.get("color").getAsInt();
                     nativeFloatEntry.setCount4(asInt2);
                  }

                  if (asJsonObject6.has("bound")) {
                     int asInt3 = asJsonObject6.get("bound").getAsInt();
                     nativeFloatEntry.setCount3(asInt3);
                     if (nativeFloatEntry.getCount3() >= 0) {
                        cosmeticsItemData.getEnhancedListEntryArray()[nativeFloatEntry.getCount3()].getList().add(nativeFloatEntry);
                     }
                  }

                  if (asJsonObject6.has("mask")) {
                     String asString4 = asJsonObject6.get("mask").getAsString();
                     Consumer consumer = (Consumer)null;
                     this.applyNativeFloatEntry(nativeFloatEntry, asString, asString4, consumer);
                  }

                  this.applyJsonObject2(asJsonObject6, nativeFloatEntry);
                  cosmeticsItemData.getArrayList().add(nativeFloatEntry);
               }
            }

            if (asJsonObject.has("height")) {
               float asFloat2 = asJsonObject.get("height").getAsFloat();
               cosmeticsItemData.setFactor11(asFloat2);
            }

            if (asJsonObject.has("resize")) {
               float asFloat3 = asJsonObject.get("resize").getAsFloat();
               cosmeticsItemData.setFactor9(asFloat3);
            }

            if (asJsonObject.has("previewScale")) {
               float asFloat4 = asJsonObject.get("previewScale").getAsFloat();
               cosmeticsItemData.setFactor10(asFloat4);
            }

            if (asJsonObject.has("previewY")) {
               float asFloat5 = asJsonObject.get("previewY").getAsFloat();
               cosmeticsItemData.setFactor8(asFloat5);
            }

            if (asJsonObject.has("previewRot")) {
               float[] factorArray = (float[])MoreCosmeticsManager.gson.fromJson(asJsonObject.get("previewRot"), float[].class);
               cosmeticsItemData.setFactorArray(factorArray);
            }

            if (asJsonObject.has("adjustment")) {
               float[] factorArray2 = (float[])MoreCosmeticsManager.gson.fromJson(asJsonObject.get("adjustment"), float[].class);
               cosmeticsItemData.setFactorArray3(factorArray2);
            }

            if (asJsonObject.has("sideTransform")) {
               float[] factorArray3 = (float[])MoreCosmeticsManager.gson.fromJson(asJsonObject.get("sideTransform"), float[].class);
               cosmeticsItemData.setFactorArray2(factorArray3);
            }

            this.applyJsonObject2(asJsonObject, cosmeticsItemData);
            this.hashMap.put(count, cosmeticsItemData);
            this.hashMap2.put(cosmeticsItemData.getText(), cosmeticsItemData);
            flag2 = false;
         } catch (Throwable throwable5) {
            throwable = throwable5;
            throw throwable5;
         } finally {
            if (flag2) {
               if (throwable != null) {
                  try {
                     inputStreamReader.close();
                  } catch (Throwable throwable6) {
                     throwable.addSuppressed(throwable6);
                  }
               } else {
                  inputStreamReader.close();
               }
            }
         }

         inputStreamReader.close();
      } catch (Exception exception) {
      }
   }

   public HashMap getHashMap() {
      return this.hashMap;
   }

   private static void applyText(String text, NativeFloatEntry nativeFloatEntry, Optional optional, String text2) {
      try {
         Object[] entryArray = new Object[0];
         short shortValue = 5000;
         boolean flag = false ;

         try (InputStream inputStream = SSLSocketFactoryEntry.resolveInputStream(text, shortValue, entryArray)) {
            flag = true;
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            nativeFloatEntry.setBufferedImage(bufferedImage);
            nativeFloatEntry.setLabelText2(text);
            optional.ifPresent((item -> FailedToLoadMaskFromManager.applyConsumer((Consumer)item)));
            flag = false;
         }
      } catch (IOException ioException) {
         MoreCosmeticsManager.applyText("Failed to load mask from " + text2 + ": " + ioException.getMessage());
         optional.ifPresent((item -> FailedToLoadMaskFromManager.applyConsumer2((Consumer)item)));
      }
   }

   private void applyList(List list, EnhancedListEntry enhancedListEntry, int count) {
      for (ListenerAdapter23 listenerAdapter : (Iterable<ListenerAdapter23>)(Iterable)list) {
         if (listenerAdapter.getLabelText().toLowerCase().startsWith(enhancedListEntry.getLabelText().toLowerCase())) {
            listenerAdapter.count = count;
         } else {
            List list2 = listenerAdapter.list2;
            this.applyList(list2, enhancedListEntry, count);
         }
      }
   }

   private void applyJsonObject(JsonObject jsonObject, StandardHashMapEntry standardHashMapEntry) {
      JsonArray jsonArray = new JsonArray();

      for (LocalFloatHolder localFloatHolder : (Iterable<LocalFloatHolder>)(Iterable)standardHashMapEntry.getHashMap().values()) {
         JsonObject jsonObject2 = new JsonObject();
         jsonObject2.addProperty("type", localFloatHolder.getActiveOptionType().getCount());
         if (localFloatHolder.getCombatIntervalProperty() != null) {
            jsonObject2.addProperty("axis", localFloatHolder.getCombatIntervalProperty().getCount());
         }

         jsonObject2.addProperty("mult", localFloatHolder.getFactor());
         jsonArray.add(jsonObject2);
      }

      jsonObject.add("animations", jsonArray);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   private static void applyConsumer2(Consumer consumer) {
      consumer.accept(false);
   }

   public void applyCosmeticsItemData(CosmeticsItemData cosmeticsItemData) {
      try {
         BufferedReader reader = new BufferedReader(new FileReader(cosmeticsItemData.getFile()));
         Throwable throwable = null;
         boolean flag = false ;

         try {
            flag = true;
            JsonObject asJsonObject = MoreCosmeticsManager.jsonParser.parse(reader).getAsJsonObject();
            asJsonObject.addProperty("name", cosmeticsItemData.getText());
            asJsonObject.addProperty("category", cosmeticsItemData.getCount3());
            asJsonObject.addProperty("height", cosmeticsItemData.getFactor11());
            asJsonObject.addProperty("previewY", cosmeticsItemData.getFactor8());
            asJsonObject.addProperty("previewScale", cosmeticsItemData.getFactor10());
            if (cosmeticsItemData.getFactorArray() != null) {
               asJsonObject.add("previewRot", MoreCosmeticsManager.gson.toJsonTree(cosmeticsItemData.getFactorArray()));
            }

            this.applyJsonObject(asJsonObject, cosmeticsItemData);
            if (cosmeticsItemData.isActive5()) {
               JsonArray jsonArray = new JsonArray();

               for (FloatEntryBridge floatEntryBridge : (Iterable<FloatEntryBridge>)(Iterable)cosmeticsItemData.getArrayListAsArrayList()) {
                  JsonObject jsonObject = new JsonObject();
                  jsonObject.addProperty("item", floatEntryBridge.getCount2());
                  this.applyJsonObject(jsonObject, floatEntryBridge);
                  jsonArray.add(jsonObject);
               }

               asJsonObject.add("items", jsonArray);
            }

            if (cosmeticsItemData.isActive3()) {
               JsonArray jsonArray2 = new JsonArray();

               for (NativeFloatEntry nativeFloatEntry : (Iterable<NativeFloatEntry>)(Iterable)cosmeticsItemData.getArrayList()) {
                  JsonObject jsonObject2 = new JsonObject();
                  jsonObject2.addProperty("url", nativeFloatEntry.getLabelText());
                  jsonObject2.addProperty("opacity", nativeFloatEntry.getFactor8());
                  jsonObject2.addProperty("color", nativeFloatEntry.getCount4());
                  jsonObject2.addProperty("bound", nativeFloatEntry.getCount3());
                  if (nativeFloatEntry.getLabelText2() != null && nativeFloatEntry.getLabelText2().startsWith("http")) {
                     jsonObject2.addProperty("mask", nativeFloatEntry.getLabelText2());
                  }

                  this.applyJsonObject(jsonObject2, nativeFloatEntry);
                  jsonArray2.add(jsonObject2);
               }

               asJsonObject.add("textures", jsonArray2);
            }

            if (cosmeticsItemData.isActive6()) {
               JsonArray asJsonArray = asJsonObject.get("models").getAsJsonArray();

               for (int index = 0; index < asJsonArray.size(); index++) {
                  JsonObject asJsonObject2 = asJsonArray.get(index).getAsJsonObject();
                  EnhancedListEntry enhancedListEntry = cosmeticsItemData.getEnhancedListEntryArray()[index];
                  asJsonObject2.addProperty("visible", enhancedListEntry.isEnabled());
                  this.applyJsonObject(asJsonObject2, enhancedListEntry);
               }
            }

            if (cosmeticsItemData.isActiveAsFlag()) {
               asJsonObject.add("config", MoreCosmeticsManager.gson.toJsonTree(cosmeticsItemData.getModelConfigArray()));
            }

            FileUtils.writeStringToFile(cosmeticsItemData.getFile(), asJsonObject.toString());
            flag = false;
         } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
         } finally {
            if (flag) {
               if (throwable != null) {
                  try {
                     reader.close();
                  } catch (Throwable throwable3) {
                     throwable.addSuppressed(throwable3);
                  }
               } else {
                  reader.close();
               }
            }
         }

         reader.close();
      } catch (IOException ioException) {
         MoreCosmeticsManager.applyThrowable(ioException);
      }
   }

   public void updateState3() {
      this.updateState();

      for (File file2 : this.file.listFiles()) {
         if (file2.getName().toLowerCase().endsWith(".json")) {
            this.applyFile(file2, false);
         }
      }

      this.enabled = true;
   }

   private void applyJsonObject2(JsonObject jsonObject, PrimaryFloatEntry primaryFloatEntry) {
      this.applyJsonObjectByJsonObject(jsonObject, primaryFloatEntry);
      this.applyJsonObjectByJsonObject(jsonObject, primaryFloatEntry);
   }

   public CosmeticsItemData get(Integer count) {
      return (CosmeticsItemData)this.hashMap.get(count);
   }

   public abstract void applyText(String text, BufferedImage bufferedImage);

   public void applyNativeFloatEntry(NativeFloatEntry nativeFloatEntry, String text, String text2, Consumer consumer) {
      Optional optional = Optional.ofNullable(consumer);
      if (text2.startsWith("http")) {
         if (CollectionHelper.matchesText(text2)) {
            MoreCosmeticsManager.executorService.execute(() -> FailedToLoadMaskFromManager.applyText(text2, nativeFloatEntry, optional, text));
         }
      } else {
         try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(text2));
            Throwable throwable = null;
            boolean flag = false ;

            try {
               flag = true;
               BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
               nativeFloatEntry.setBufferedImage(bufferedImage);
               optional.ifPresent((item -> FailedToLoadMaskFromManager.applyConsumerByConsumer((Consumer)item)));
               flag = false;
            } catch (Throwable throwable2) {
               throwable = throwable2;
               throw throwable2;
            } finally {
               if (flag) {
                  if (throwable != null) {
                     try {
                        byteArrayInputStream.close();
                     } catch (Throwable throwable3) {
                        throwable.addSuppressed(throwable3);
                     }
                  } else {
                     byteArrayInputStream.close();
                  }
               }
            }

            byteArrayInputStream.close();
         } catch (IOException ioException) {
            MoreCosmeticsManager.applyText("Failed to load mask from " + text + ": " + ioException.getMessage());
            optional.ifPresent((item -> FailedToLoadMaskFromManager.applyConsumer3((Consumer)item)));
         }
      }
   }

   private void applyJsonObject3(JsonObject jsonObject, PrimaryFloatEntry primaryFloatEntry) {
      jsonObject.addProperty("pos", primaryFloatEntry.getMovementConfigProperty().getCount2());
      jsonObject.addProperty("scale", primaryFloatEntry.getFactor6());
      jsonObject.addProperty("x", primaryFloatEntry.getFactor7());
      jsonObject.addProperty("y", primaryFloatEntry.getFactor4());
      jsonObject.addProperty("z", primaryFloatEntry.getFactor5());
      jsonObject.addProperty("yaw", primaryFloatEntry.getFactor2());
      jsonObject.addProperty("pitch", primaryFloatEntry.getFactor());
      jsonObject.addProperty("roll", primaryFloatEntry.getFactor3());
   }

   private static void applyConsumer3(Consumer consumer) {
      consumer.accept(false);
   }

   private void updateState2() {
      this.updateState3();
   }

   private void applyJsonObjectByJsonObject(JsonObject jsonObject, StandardHashMapEntry standardHashMapEntry) {
      if (jsonObject.has("animations")) {
         Iterator iterator = jsonObject.get("animations").getAsJsonArray().iterator();

         while (iterator.hasNext()) {
            JsonObject asJsonObject = ((JsonElement)iterator.next()).getAsJsonObject();
            LocalFloatHolder localFloatHolder = new LocalFloatHolder(ActiveOptionType.resolveActiveOptionType(asJsonObject.get("type").getAsInt()));
            if (asJsonObject.has("axis")) {
               CombatIntervalProperty combatIntervalProperty = CombatIntervalProperty.resolveCombatIntervalProperty(asJsonObject.get("axis").getAsInt());
               if (combatIntervalProperty != null) {
                  localFloatHolder.setCombatIntervalProperty(combatIntervalProperty);
               }
            }

            float asFloat = asJsonObject.get("mult").getAsFloat();
            localFloatHolder.setFactor(asFloat);
            standardHashMapEntry.applyLocalFloatHolder(localFloatHolder);
         }
      }
   }

   private void applyJsonObject(JsonObject jsonObject, PrimaryFloatEntry primaryFloatEntry) {
      this.applyJsonObject3(jsonObject, primaryFloatEntry);
      this.applyJsonObject(jsonObject, primaryFloatEntry);
   }

   public CosmeticsItemData get(String text) {
      return (CosmeticsItemData)this.hashMap2.get(text);
   }

   private static void applyConsumerByConsumer(Consumer consumer) {
      consumer.accept(true);
   }

   private void updateState() {
      String text = Client.getClient().getString4().toLowerCase() + File.separator + "cosmetics";
      String text2 = "assets/augustus/cosmetics/";
      ConfigManager.applyString(text2 + "1.json", text, true);
      ConfigManager.applyString(text2 + "2.json", text, true);
      ConfigManager.applyString(text2 + "3.json", text, true);
      ConfigManager.applyString(text2 + "4.json", text, true);
      ConfigManager.applyString(text2 + "5.json", text, true);
      ConfigManager.applyString(text2 + "6.json", text, true);
      ConfigManager.applyString(text2 + "7.json", text, true);
      ConfigManager.applyString(text2 + "8.json", text, true);
      ConfigManager.applyString(text2 + "10.json", text, true);
      ConfigManager.applyString(text2 + "11.json", text, true);
      ConfigManager.applyString(text2 + "12.json", text, true);
      ConfigManager.applyString(text2 + "13.json", text, true);
      ConfigManager.applyString(text2 + "14.json", text, true);
      ConfigManager.applyString(text2 + "16.json", text, true);
      ConfigManager.applyString(text2 + "17.json", text, true);
      ConfigManager.applyString(text2 + "18.json", text, true);
      ConfigManager.applyString(text2 + "19.json", text, true);
      ConfigManager.applyString(text2 + "20.json", text, true);
      ConfigManager.applyString(text2 + "21.json", text, true);
      ConfigManager.applyString(text2 + "22.json", text, true);
      ConfigManager.applyString(text2 + "23.json", text, true);
      ConfigManager.applyString(text2 + "24.json", text, true);
   }

   public FailedToLoadMaskFromManager() {
      this.file = new File(OnlineConfigsManager.text, "cosmetics");
      this.file.mkdirs();
      MoreCosmeticsManager.executorService.execute(this::updateState2);
   }
}
