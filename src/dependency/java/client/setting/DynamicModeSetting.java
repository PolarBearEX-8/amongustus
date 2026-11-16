package client.setting;

import client.Client;
import client.event.ActiveListener;
import client.event.PostMotionSubscriber;
import client.event.TriFunction;
import client.module.Module;
import client.util.AuxiliaryModuleAdapter;
import client.util.BooleanSupplier;
import client.util.ConfigManager;
import client.util.ModeListTracker;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class DynamicModeSetting extends ModeSetting implements ActiveListener {
   public static final ArrayList itemList2 = new ArrayList();
   public final HashMap hashMap4;
   public final ConcurrentHashMap concurrentHashMap2;
   public final HashMap hashMap3;
   public Module module;
   public ModeOptionSupplier supplierEntry;
   public SettingGroup settingGroup;
   public final ConcurrentHashMap concurrentHashMap3;
   public static final ModeListTracker modeListTracker = new ModeListTracker();

   private byte[] resolveByteArray(File file) {
      if (file == null) {
         return null;
      } else {
         try {
            return MessageDigest.getInstance("SHA-256").digest(Files.readAllBytes(file.toPath()));
         } catch (IOException | NoSuchAlgorithmException ioException) {
            return null;
         }
      }
   }

   @Override
   public void setEnabled(boolean enabled) {
      if (this.supplierEntry != null) {
         this.supplierEntry.setEnabled(enabled);
      }
   }

   private Boolean resolveBooleanByModeOptionSuppli(ModeOptionSupplier modeOptionSupplier) {
      return this.supplierEntry != null && modeOptionSupplier.getString().equals(this.supplierEntry.getString());
   }

   private ModeOptionSupplier get(String text) {
      return (ModeOptionSupplier)this.concurrentHashMap2.get(text);
   }

   private void applyModuleByModule(Module module2, BiFunction biFunction) {
      ModeOptionSupplier modeOptionSupplier = (ModeOptionSupplier)biFunction.apply(module2, this.settingGroup);
      Supplier supplier = () -> this.resolveBooleanByModeOptionSuppli(modeOptionSupplier);
      modeOptionSupplier.setSupplier(supplier);
      this.concurrentHashMap2.put(modeOptionSupplier.getString(), modeOptionSupplier);
      this.hashMap4.put(modeOptionSupplier.getString(), modeOptionSupplier);
      this.hashMap3.put(modeOptionSupplier.getClass(), modeOptionSupplier);
   }

   public boolean matchesFile(File file, MotionSettingGroup motionSettingGroup, AuxiliaryModuleAdapter auxiliaryModuleAdapter) {
      String absolutePath = file.getParentFile().getAbsolutePath();
      String name = file.getName();
      ConfigManager.getJsonArray(absolutePath, name, (Runnable)null).forEach(o -> applyMotionSettingGroup(motionSettingGroup, (JsonElement)o));
      return true;
   }

   private static String[] resolveTextArray(int count) {
      return new String[count];
   }

   private Boolean resolveBoolean(ModeOptionSupplier modeOptionSupplier) {
      return this.supplierEntry != null && modeOptionSupplier.getString().equals(this.supplierEntry.getString());
   }

   @Override
   public void updateState5() {
      super.updateState5();
   }

   public DynamicModeSetting getDynamicModeSetting(TriFunction triFunction) {
      this.applyTriFunctionByTriFunction(triFunction);
      itemList2.add((Runnable)() -> this.applyTriFunction(triFunction));
      return this;
   }

   @Override
   public void updateState() {
      super.updateState();
      String selected = this.getSelected();
      this.supplierEntry = this.get(selected);
   }

   private void applyTriFunction(TriFunction triFunction) {
      this.applyTriFunctionByTriFunction(triFunction);
   }

   public ModeOptionSupplier getSupplierEntry() {
      return this.supplierEntry;
   }

   private static Boolean resolveBoolean(ModeOptionConfigurable modeOptionConfigurable) {
      return true;
   }

   public boolean checkClass(Class clazz) {
      ModeOptionSupplier supplier = (ModeOptionSupplier)this.hashMap3.get(clazz);
      return supplier != null && supplier.isEnabled();
   }

   @Override
   public void setSelected(String selected) {
      if (!selected.equals(this.getSelected())) {
         if (this.supplierEntry != null) {
            this.supplierEntry.setEnabled(false);
         }

         this.supplierEntry = this.get(selected);
         if (this.supplierEntry != null && this.getModule().isEnabled()) {
            this.supplierEntry.setEnabled(true);
         }
      }

      super.setSelected(selected);
   }

   public void applyTriFunctionByTriFunction(TriFunction triFunction) {
      String text = ConfigManager.labelText
         + Client.getClient().getString4().toLowerCase(Locale.ROOT)
         + File.separator
         + "motionmodifier"
         + File.separator
         + this.module.getString().toLowerCase();
      ConfigManager.applyString2(text);
      File file = new File(text);
      File[] fileArray = file.listFiles(File::isFile);
      if (fileArray != null) {
         HashMap map = new HashMap(this.hashMap4);

         for (File jsonFile : fileArray) {
            String fileName = jsonFile.getName();
            if (fileName.endsWith(".json")) {
               String modeName = fileName.substring(0, Math.max(fileName.length() - 5, 0));
               byte[] digest = null;
               if (this.concurrentHashMap2.containsKey(modeName)) {
                  if (!this.concurrentHashMap3.containsKey(modeName)) {
                     continue;
                  }

                  digest = this.resolveByteArray(jsonFile);
                  if (Arrays.equals(digest, (byte[])this.concurrentHashMap3.get(modeName))) {
                     map.put(modeName, this.concurrentHashMap2.get(modeName));
                     continue;
                  }
               }

               if (!map.containsKey(modeName)) {
                  PostMotionSubscriber subscriber = (PostMotionSubscriber)triFunction.apply(this.module, this.settingGroup, modeName);
                  ModeOptionSupplier modeSupplier = (ModeOptionSupplier)subscriber;
                  Supplier activeSupplier = () -> this.resolveBoolean2(modeSupplier);
                  modeSupplier.setSupplier(activeSupplier);
                  this.matchesFile(jsonFile, subscriber.getMotionSettingGroup(), subscriber.getAuxiliaryModuleAdapter());
                  map.put(modeName, modeSupplier);
                  byte[] storedDigest = digest == null ? this.resolveByteArray(jsonFile) : digest;
                  this.concurrentHashMap3.put(modeName, storedDigest);
               }
            }
         }

         String[] textArray = (String[])map.keySet().stream().sorted().toArray(DynamicModeSetting::resolveTextArray);
         this.applyStringArray2(textArray);
         this.concurrentHashMap2.clear();
         this.concurrentHashMap2.putAll(map);
      }
   }

   @Override
   public SettingGroup getSettingGroup() {
      return this.settingGroup;
   }

   private static void applyMotionSettingGroup(MotionSettingGroup motionSettingGroup, JsonElement jsonElement) {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      String asString = asJsonObject.get("Name").getAsString();
      Setting setting = (Setting)motionSettingGroup.getEntryMap2().get(asString);
      if (setting != null) {
         try {
            SettingSerializer advancedItemEntry = SettingSerializer.advancedItemEntry;
            setting.updateSupplementalListener(advancedItemEntry, asJsonObject);
         } catch (Exception exception) {
            System.err.println("Failed to load Setting: " + setting.getString());
         }
      }
   }

   private void applyModule3(Module module2, BiFunction biFunction) {
      ModeOptionSupplier modeOptionSupplier = (ModeOptionSupplier)biFunction.apply(module2, this.settingGroup);
      Supplier supplier = () -> this.resolveBoolean(modeOptionSupplier);
      modeOptionSupplier.setSupplier(supplier);
      this.concurrentHashMap2.put(modeOptionSupplier.getString(), modeOptionSupplier);
      this.hashMap4.put(modeOptionSupplier.getString(), modeOptionSupplier);
      this.hashMap3.put(modeOptionSupplier.getClass(), modeOptionSupplier);
   }

   private Boolean resolveBoolean2(ModeOptionSupplier modeOptionSupplier) {
      return this.supplierEntry != null && modeOptionSupplier.getString().equals(this.supplierEntry.getString());
   }

   public DynamicModeSetting(Module module2, String text, String text2, BiFunction[] biFunctionArray) {
      super(module2, text, text2, new String[0]);
      this.hashMap4 = new HashMap();
      this.concurrentHashMap2 = new ConcurrentHashMap();
      this.concurrentHashMap3 = new ConcurrentHashMap();
      this.hashMap3 = new HashMap();
      this.module = module2;
      SettingGroup settingGroup2 = new SettingGroup(module2, text + "ModeValuesGroup", true);
      this.settingGroup = (SettingGroup)settingGroup2.getSetting("ModeValues");
      BooleanSupplier booleanSupplier = this.settingGroup::isEmpty;
      this.settingGroup = this.settingGroup.getSettingGroup(booleanSupplier);
      Arrays.stream(biFunctionArray).forEach(o -> this.applyModuleByModule(module2, (BiFunction)o));
      String[] textArray = (String[])this.concurrentHashMap2.keySet().toArray(new String[0]);
      this.applyStringArray2(textArray);
      this.supplierEntry = this.get(text2);
      module2.getItemList2().add(this);
   }

   public DynamicModeSetting(Module module2, String text, String text2, SettingGroup settingGroup2, BiFunction[] biFunctionArray) {
      super(module2, text, text2, new String[0], settingGroup2);
      this.hashMap4 = new HashMap();
      this.concurrentHashMap2 = new ConcurrentHashMap();
      this.concurrentHashMap3 = new ConcurrentHashMap();
      this.hashMap3 = new HashMap();
      this.module = module2;
      SettingGroup settingGroup3 = new SettingGroup(module2, text + "ModeValuesGroup", true, settingGroup2);
      this.settingGroup = (SettingGroup)settingGroup3.getSetting("ModeValues");
      BooleanSupplier booleanSupplier = this.settingGroup::isEmpty;
      this.settingGroup = this.settingGroup.getSettingGroup(booleanSupplier);
      Arrays.stream(biFunctionArray).forEach(o -> this.applyModule3(module2, (BiFunction)o));
      String[] textArray = (String[])this.concurrentHashMap2.keySet().toArray(new String[0]);
      this.applyStringArray2(textArray);
      this.supplierEntry = this.get(text2);
      module2.getItemList2().add(this);
   }
}
