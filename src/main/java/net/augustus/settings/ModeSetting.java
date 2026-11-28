package net.augustus.settings;

import net.augustus.Augustus;
import net.augustus.config.FeatureListJsonCodec;
import net.augustus.modules.ModuleToggleListener;
import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.modules.movement.helper.MotionModifierProvider;
import net.augustus.utils.UnaryFunction;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.DirectoryWatchService;
import net.augustus.utils.file.DirectoryWatcher;
import com.google.gson.JsonObject;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.augustus.modules.Module;
import net.augustus.modules.misc.Dummy;

public class ModeSetting extends StringValue implements ModuleToggleListener {
   private final ConcurrentHashMap modesByName;
   private final Set customModeNames;
   private final HashMap modesByClass;
   private AbstractSubMode abstractSubMode;
   private final List builtInModeNames;
   private SettingGroup modeValuesGroup;
   private final HashMap builtInModes;
   private final String customModesDir;
   private static final FeatureListJsonCodec CUSTOM_MODE_CODEC = new FeatureListJsonCodec();
   private Module ownerModule;

   @Override
   public void resetToDefault() {
      super.resetToDefault();
      this.abstractSubMode = this.resolveSubMode(this.getSelectedValue());
   }

   public AbstractSubMode getAbstractSubMode() {
      return this.abstractSubMode;
   }

   private AbstractSubMode resolveSubMode(String str) {
      AbstractSubMode subMode = (AbstractSubMode)this.modesByName.get(str);
      if (subMode == null) {
         subMode = (AbstractSubMode)this.modesByName.values().stream().findFirst().orElse(null);
      }

      return subMode;
   }

   public ModeSetting visibleWhen(SettingVisibilityCondition condition) {
      this.visibilityCondition = condition;
      return this;
   }

   private boolean isNestedModeGroupVisible() {
      return this.modeValuesGroup.hasVisibleChildren() && this.isVisible();
   }

   public boolean isSubModeActive(Class clazz) {
      AbstractSubMode subMode = (AbstractSubMode)this.modesByClass.get(clazz);
      return subMode != null && subMode.isEnabled();
   }

   private void onModeValueChanged() {
      if (this.abstractSubMode != null && this.getParentModule().isToggled()) {
         this.abstractSubMode.setEnabled(true);
      }

      if (this.changeListener != null) {
         this.changeListener.onChanged();
      }
   }

   public ModeSetting(Module module, String str, String str2, BiFunction[] biFunctions) {
       super(module, str, str2, new String[0]);
      this.builtInModes = new HashMap();
      this.builtInModeNames = new ArrayList();
      this.modesByName = new ConcurrentHashMap();
      this.customModeNames = ConcurrentHashMap.newKeySet();
      this.modesByClass = new HashMap();
      this.ownerModule = module;
      this.modeValuesGroup = (SettingGroup)new SettingGroup(module, str + "ModeValuesGroup", true).withDisplayName("ModeValues");
      this.modeValuesGroup = this.modeValuesGroup.visibleWhen(() -> this.isModeGroupVisible());
      Stream<BiFunction> stream = Arrays.stream(biFunctions);
      stream.forEach(biFunction -> {
         AbstractSubMode subMode = (AbstractSubMode)biFunction.apply(module, this.modeValuesGroup);
         Supplier supplier = () -> {
            return subMode.getName().equals(this.abstractSubMode.getName());
         };
         subMode.setVisibilitySupplier(supplier);
         this.builtInModeNames.add(subMode.getName());
         this.modesByName.put(subMode.getName(), subMode);
         this.builtInModes.put(subMode.getName(), subMode);
         this.modesByClass.put(subMode.getClass(), subMode);
      });
      this.setOptions((String[])this.builtInModeNames.toArray(new String[0]));
      this.abstractSubMode = this.resolveSubMode(str2);
      module.getToggleListeners().add(this);
      this.customModesDir = ClientFileUtil.WORKING_DIR
         + Augustus.getInstance().getName().toLowerCase(Locale.ROOT)
         + File.separator
         + "motionmodifier"
         + File.separator
         + this.ownerModule.getName().toLowerCase();
   }

   public ModeSetting(Module module, String str, String str2, SettingGroup doubleValue, BiFunction[] biFunctions) {
       super(module, str, str2, new String[0], doubleValue);
      this.builtInModes = new HashMap();
      this.builtInModeNames = new ArrayList();
      this.modesByName = new ConcurrentHashMap();
      this.customModeNames = ConcurrentHashMap.newKeySet();
      this.modesByClass = new HashMap();
      this.ownerModule = module;
      SettingGroup settingGroup = new SettingGroup(module, str + "ModeValuesGroup", true, doubleValue);
      this.modeValuesGroup = (SettingGroup)settingGroup.withDisplayName("ModeValues");
      settingGroup = this.modeValuesGroup;
      this.modeValuesGroup = settingGroup.visibleWhen(() -> this.isNestedModeGroupVisible());
      Stream<BiFunction> stream = Arrays.stream(biFunctions);
      stream.forEach(biFunction -> {
         AbstractSubMode subMode = (AbstractSubMode)biFunction.apply(module, this.modeValuesGroup);
         subMode.setVisibilitySupplier(() -> this.isSelectedSubMode(subMode));
         this.builtInModeNames.add(subMode.getName());
         this.modesByName.put(subMode.getName(), subMode);
         this.builtInModes.put(subMode.getName(), subMode);
         this.modesByClass.put(subMode.getClass(), subMode);
      });
      this.setOptions((String[])this.builtInModeNames.toArray(new String[0]));
      this.abstractSubMode = this.resolveSubMode(str2);
      module.getToggleListeners().add(this);
      this.customModesDir = ClientFileUtil.WORKING_DIR
         + Augustus.getInstance().getName().toLowerCase(Locale.ROOT)
         + File.separator
         + "motionmodifier"
         + File.separator
         + this.ownerModule.getName().toLowerCase();
   }

   private void loadCustomModeAndRefresh(File file, CustomSubModeFactory customSubModeFactory) {
      this.registerCustomModeFromFile(this.modesByName, file, customSubModeFactory);
      this.refreshModeOptions();
   }

   @Override
   public void onModuleToggled(boolean flag) {
      this.abstractSubMode.setEnabled(flag);
   }

   private Boolean isSelectedSubMode(AbstractSubMode subMode) {
      return subMode.getName().equals(this.abstractSubMode.getName());
   }

   public void reloadCustomModes(CustomSubModeFactory customSubModeFactory) {
      ClientFileUtil.ensureDirectory(this.customModesDir);
      File[] files = new File(this.customModesDir).listFiles(File::isFile);
      if (files != null) {
         HashMap map = new HashMap(this.builtInModes);

         for (File file2 : files) {
            this.registerCustomModeFromFile(map, file2, customSubModeFactory);
         }

         this.modesByName.clear();
         this.modesByName.putAll(map);
         this.refreshModeOptions();
      }
   }

   private void refreshModeOptions() {
      if (!this.modesByName.keySet().stream().anyMatch(obj -> this.matchesSelectedMode((String)obj)) && !this.modesByName.isEmpty()) {
         this.setSelectedValue((String)this.modesByName.keySet().iterator().next());
      }

      String[] strings = ((Map<String, ?>)this.modesByName).keySet().stream().sorted(Comparator.comparing(String::toLowerCase)).toArray(String[]::new);
      this.setOptions(strings);
   }

   private void registerCustomModeFromFile(Map map, File file, CustomSubModeFactory customSubModeFactory) {
      String name = file.getName();
      short s2 = 28597;
      if (name.endsWith(".json")) {
         String str = name.substring(0, Math.max(name.length() - 5, 0));
         if (!map.containsKey(str) || this.customModeNames.contains(str)) {
            if (this.customModeNames.contains(str)) {
               ((AbstractSubMode)map.get(str)).setEnabled(false);
            }

            SettingGroup modeValuesGroup2 = this.modeValuesGroup;
            MotionModifierProvider motionModifierProvider = (MotionModifierProvider)customSubModeFactory.create(this.ownerModule, modeValuesGroup2, str);
            AbstractSubMode subMode2 = (AbstractSubMode)motionModifierProvider;
            Supplier supplier = () -> {
               return subMode2.getName().equals(this.abstractSubMode.getName());
            };
            subMode2.setVisibilitySupplier(supplier);
            this.loadCustomModeConfig(file, motionModifierProvider.getDummy());
            map.put(str, subMode2);
            this.customModeNames.add(str);
         }
      }
   }

   @Override
   public synchronized void notifyUserChanged() {
      this.dispatchUserChangeListener(() -> this.onUserModeChanged());
   }

   @Override
   public Setting withVisibility(SettingVisibilityCondition condition) {
      return this.visibleWhen(condition);
   }

   public boolean loadCustomModeConfig(File file, Dummy dummy) {
      JsonObject json = ClientFileUtil.readJsonObject(file.getParentFile().getAbsolutePath(), file.getName(), (Runnable)null);
      if (json == null) {
         return false;
      } else {
         CUSTOM_MODE_CODEC.setFeatures2(new Module[]{dummy});
         if (!CUSTOM_MODE_CODEC.deserializeFromObject(json, true, obj2 -> true)) {
            System.out.println("Failed to read custom mode");
            return false;
         } else {
            return true;
         }
      }
   }

   private void onCustomModeFileDeleted(Path path) {
      this.removeCustomModeFile(path.toFile());
   }

   public AbstractSubMode getSubMode(String str) {
      return (AbstractSubMode)this.modesByName.get(str);
   }

   public ModeSetting named(String str) {
      this.setDisplayName(str);
      return this;
   }

   public ModeSetting enableCustomModes(CustomSubModeFactory customSubModeFactory) {
      this.reloadCustomModes(customSubModeFactory);
      DirectoryWatcher directoryWatcher = DirectoryWatchService.watch(this.customModesDir);
      directoryWatcher = directoryWatcher.onCreate(obj -> this.onCustomModeFileCreated(customSubModeFactory, (Path)obj));
      Consumer consumer2 = obj2 -> {
         File file = ((Path)obj2).toFile();
         CompletableFuture.runAsync(() -> {
            this.registerCustomModeFromFile(this.modesByName, file, customSubModeFactory);
            this.refreshModeOptions();
         });
      };
      directoryWatcher = directoryWatcher.onModify(consumer2);
      directoryWatcher.onDelete(obj3 -> this.onCustomModeFileDeleted((Path)obj3)).register();
      return this;
   }

   private boolean matchesSelectedMode(String str) {
      return this.getSelectedValue().equals(str);
   }

   private void removeCustomModeFile(File file) {
      String name = file.getName();
      if (name.endsWith(".json")) {
         String str = name.substring(0, Math.max(name.length() - 5, 0));
         if (this.customModeNames.contains(str)) {
            ((AbstractSubMode)this.modesByName.get(str)).setEnabled(false);
         }

         this.customModeNames.remove(str);
         this.modesByName.remove(str);
         this.refreshModeOptions();
      }
   }

   @Override
   public void setSelectedValue(String selectedValue) {
      if (!selectedValue.equals(this.getSelectedValue())) {
         if (this.abstractSubMode != null) {
            this.abstractSubMode.setEnabled(false);
         }

         this.abstractSubMode = this.resolveSubMode(selectedValue);
         if (this.abstractSubMode != null && this.getParentModule().isToggled()) {
            this.abstractSubMode.setEnabled(true);
         }
      }

      super.setSelectedValue(selectedValue);
   }

   private boolean isModeGroupVisible() {
      return this.modeValuesGroup.hasVisibleChildren() && this.isVisible();
   }

   @Override
   public synchronized void notifyValueChanged() {
      this.dispatchChangeListener(() -> this.onModeValueChanged());
   }

   private void onUserModeChanged() {
      if (this.abstractSubMode != null && this.getParentModule().isToggled()) {
         this.abstractSubMode.setEnabled(true);
      }

      if (this.userChangeListener != null) {
         this.userChangeListener.onChanged();
      }
   }

   @Override
   public Setting withDisplayName(String str) {
      return this.named(str);
   }

   private void onCustomModeFileCreated(CustomSubModeFactory customSubModeFactory, Path path) {
      File file = path.toFile();
      CompletableFuture.runAsync(() -> this.loadCustomModeAndRefresh(file, customSubModeFactory));
   }

}
