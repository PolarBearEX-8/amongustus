package net.augustus.modules.modes.clicking;

import net.augustus.Augustus;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.ClientPaths;
import net.augustus.utils.file.DirectoryWatchService;
import net.augustus.utils.file.DirectoryWatcher;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import net.augustus.settings.StringValue;

public class ClickPatternManager {
   private final Map patternsByName = new HashMap();
   private static final String PATTERNS_DIR = ClientPaths.CLIENT_DIR + File.separator + "clickpattern";
   private final Consumer onPatternChanged;
   private final StringValue patternSelectorSetting;

   private ClickPatternManager(StringValue booleansSetting, Consumer consumer) {
      this.patternSelectorSetting = booleansSetting;
      this.onPatternChanged = consumer;
   }

   public static ClickPatternManagerBuilder builder() {
      return new ClickPatternManagerBuilder();
   }

   private void onPatternFileModified(Path path) {
      ClickPattern clickPattern = this.loadPatternFile(path.toFile());
      if (clickPattern != null) {
         this.patternsByName.put(clickPattern.name, clickPattern);
         this.refreshPatternSelector();
      }
   }

   public ClickPattern getPattern(String str) {
      return (ClickPattern)this.patternsByName.get(str);
   }

   private ClickPattern loadPatternFile(File file) {
      String name = file.getName();

      try {
         if (!file.exists() || !name.endsWith(".json")) {
            return null;
         }

         JsonObject json = ClientFileUtil.readJsonObject(PATTERNS_DIR, name, (Runnable)null);
         if (json == null) {
            return null;
         }

         ArrayList list = new ArrayList();
         ArrayList list2 = new ArrayList();

         for (Entry entry : json.entrySet()) {
            JsonArray asJsonArray = ((JsonElement)entry.getValue()).getAsJsonArray();
            if (asJsonArray != null) {
               if (((String)entry.getKey()).equals("Delays")) {
                  asJsonArray.forEach(elem -> list.add(elem.getAsInt()));
               } else if (((String)entry.getKey()).equals("Rotations")) {
                  for (JsonElement elem2 : asJsonArray) {
                     JsonArray asJsonArray2 = elem2.getAsJsonArray();
                     float[] floats = new float[asJsonArray2.size()];

                     for (int i = 0; i < floats.length; i++) {
                        floats[i] = asJsonArray2.get(i).getAsFloat();
                     }

                     list2.add(floats);
                  }
               }
            }
         }

         if (!list.isEmpty()) {
            String str = name.substring(0, name.length() - 5);
            return new ClickPattern(str, (Integer[])list.toArray(new Integer[0]), list2);
         }

         ChatUtil.sendChat("Failed to load click pattern " + name + "!");
      } catch (Exception e) {
         ChatUtil.sendChat("Failed to load click pattern " + name + "!");
      }

      return null;
   }

   public String resolvePatternName(String str) {
      if (this.patternsByName.isEmpty()) {
         return "";
      } else {
         ClickPattern clickPattern = (ClickPattern)this.patternsByName.get(str);
         return clickPattern != null ? clickPattern.name : (String)this.patternsByName.keySet().iterator().next();
      }
   }

   public void startWatchingAndLoad() {
      DirectoryWatcher directoryWatcher = DirectoryWatchService.watch(PATTERNS_DIR);
      Consumer consumer = obj -> {
         ClickPattern clickPattern = this.loadPatternFile(((Path)obj).toFile());
         if (clickPattern != null) {
            this.patternsByName.put(clickPattern.name, clickPattern);
            this.refreshPatternSelector();
         }
      };
      directoryWatcher = directoryWatcher.onCreate(consumer);
      directoryWatcher = directoryWatcher.onModify(obj2 -> this.onPatternFileModified((Path)obj2));
      directoryWatcher.onDelete(obj3 -> this.onPatternFileDeleted((Path)obj3)).register();
      this.reloadAllPatterns();
      this.refreshPatternSelector();
   }

   private void refreshPatternSelector() {
      if (this.patternSelectorSetting != null) {
         String[] strings = (String[])this.patternsByName.keySet().toArray(new String[0]);
         strings = Arrays.stream(strings).sorted(Comparator.comparing(String::toLowerCase)).toArray(String[]::new);
         this.patternSelectorSetting.setOptions(strings);
         this.patternSelectorSetting.setSelectedValue(this.resolvePatternName(this.patternSelectorSetting.getSelectedValue()));
         this.onPatternChanged.accept(this.getPattern(this.patternSelectorSetting.getSelectedValue()));
      }
   }

   private void reloadAllPatterns() {
      String str = Augustus.getInstance().getName().toLowerCase() + "/clickpattern";
      ClientFileUtil.extractResource("assets/augustus/clicks/DefaultButterfly.json", str, true);
      HashMap map = new HashMap();
      String str3 = PATTERNS_DIR;
      File file = new File(str3);
      ClientFileUtil.ensureDirectory(str3);
      File[] files = file.listFiles(file2 -> {
         return !file2.isDirectory() && file2.getName().endsWith(".json");
      });
      if (files != null) {
         for (File file3 : files) {
            ClickPattern clickPattern = this.loadPatternFile(file3);
            if (clickPattern != null) {
               map.put(clickPattern.name, clickPattern);
            }
         }
      }

      this.patternsByName.clear();
      this.patternsByName.putAll(map);
   }

   public ClickPatternManager(StringValue booleansSetting, Consumer consumer, ClickPatternManagerSyntheticMarker marker) {
      this(booleansSetting, consumer);
   }

   private void onPatternFileDeleted(Path path) {
      String name = path.toFile().getName();
      short s = 20357;
      if (name.endsWith(".json")) {
         String str = name.substring(0, name.length() - 5);
         this.patternsByName.remove(str);
         this.refreshPatternSelector();
      }
   }

}
