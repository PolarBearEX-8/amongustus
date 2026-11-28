package net.augustus.utils.file;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.util.function.Consumer;
import net.augustus.utils.TimeHelper;
import net.minecraft.network.PacketThreadUtil;

public final class DirectoryWatcher {
   private Consumer onModify;
   private final String directoryPath;
   private Consumer onDelete;
   private Consumer onCreate = null;

   public DirectoryWatcher onCreate(Consumer consumer) {
      this.onCreate = consumer;
      return this;
   }

   public static void dispatchDelete(DirectoryWatcher directoryWatcher, Path path) {
      directoryWatcher.handleDelete(path);
   }

   public static void dispatchCreate(DirectoryWatcher directoryWatcher, Path path) {
      directoryWatcher.handleCreate(path);
   }

   private void handleCreate(Path path) {
      if (this.onCreate != null) {
         PacketThreadUtil.runOnMainThreadImmediate(() -> this.onCreate.accept(path));
      }
   }

   public void register() {
      if (this.onDelete != null || this.onModify != null || this.onCreate != null) {
         try {
            ClientFileUtil.ensureDirectory(this.directoryPath);
            WatchKey watchKey = Paths.get(this.directoryPath).register(
               DirectoryWatchService.getWatchService(), StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_CREATE
            );
            DirectoryWatchService.getWatchers().put(watchKey, this);
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }
   }

   public static String getDirectoryPath(DirectoryWatcher directoryWatcher) {
      return directoryWatcher.directoryPath;
   }

   private void handleDelete(Path path) {
      if (this.onDelete != null) {
         PacketThreadUtil.runOnMainThreadImmediate(() -> this.onDelete.accept(path));
      }
   }

   private void handleModify(Path path) {
      if (this.onModify != null) {
         PacketThreadUtil.runOnMainThreadImmediate(() -> this.onModify.accept(path));
      }
   }

   public DirectoryWatcher onModify(Consumer consumer) {
      this.onModify = consumer;
      return this;
   }

   public DirectoryWatcher(String str) {
      this.onModify = null;
      this.onDelete = null;
      this.directoryPath = str;
   }

   public DirectoryWatcher onDelete(Consumer consumer) {
      this.onDelete = consumer;
      return this;
   }

   public static void dispatchModify(DirectoryWatcher directoryWatcher, Path path) {
      directoryWatcher.handleModify(path);
   }
}
