package net.augustus.ai;

import net.augustus.utils.file.DirectoryWatchService;
import net.augustus.utils.file.DirectoryWatcher;
import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import net.augustus.settings.StringValue;

public final class OnnxModelRegistry {
   private volatile File modelsDir;
   private final StringValue modelSetting;
   private final Set watchedDirectories;
   private final List modelNames = new CopyOnWriteArrayList();

   public OnnxModelRegistry(StringValue booleansSetting) {
      this.watchedDirectories = new HashSet();
      this.modelSetting = booleansSetting;
   }

   public List getModelNames() {
      return Collections.unmodifiableList(this.modelNames);
   }

   public File getModelFile(String str) {
      return new File(this.modelsDir, str + ".onnx");
   }

   public void setModelsDirectory(File file) {
      this.modelsDir = file;
      this.scanModels();
      this.watchDirectory(file);
   }

   public void refresh() {
      this.scanModels();
   }

   private synchronized void scanModels() {
      this.modelNames.clear();
      File modelsDir2 = this.modelsDir;
      if (modelsDir2 != null && modelsDir2.isDirectory()) {
         File[] files = modelsDir2.listFiles((file, str) -> str.endsWith(".onnx"));
         if (files != null) {
            for (File file2 : files) {
               String str2 = file2.getName().substring(0, file2.getName().length() - 5);
               if (new File(modelsDir2, str2 + ".json").exists()) {
                  this.modelNames.add(str2);
               }
            }
         }

         Collections.sort(this.modelNames);
         this.updateModelSetting();
      } else {
         this.updateModelSetting();
      }
   }

   public File getModelsDir() {
      return this.modelsDir;
   }

   private void updateModelSetting() {
      String[] strings = this.modelNames.isEmpty() ? new String[]{"None"} : (String[])this.modelNames.toArray(new String[0]);
      this.modelSetting.setOptions(strings);
      if (!Arrays.asList(strings).contains(this.modelSetting.getSelectedValue())) {
         String str = strings[0];
         this.modelSetting.setSelectedValue(str);
      }
   }

   public File getNormalizationFile(String str) {
      return new File(this.modelsDir, str + ".json");
   }

   private void onFileChanged(Path path) {
      String str = path.getFileName().toString();
      if (str.endsWith(".onnx") || str.endsWith(".json")) {
         this.scanModels();
      }
   }

   private void watchDirectory(File file) {
      if (file != null) {
         String absolutePath = file.getAbsolutePath();
         if (!this.watchedDirectories.contains(absolutePath)) {
            this.watchedDirectories.add(absolutePath);

            try {
               DirectoryWatcher directoryWatcher = DirectoryWatchService.watch(absolutePath);
               directoryWatcher = directoryWatcher.onCreate(obj -> this.onFileChanged((Path)obj));
               directoryWatcher = directoryWatcher.onModify(obj2 -> this.onFileChanged((Path)obj2));
               directoryWatcher.onDelete(obj3 -> this.onFileChanged((Path)obj3)).register();
            } catch (Exception e) {
            }
         }
      }
   }

}
