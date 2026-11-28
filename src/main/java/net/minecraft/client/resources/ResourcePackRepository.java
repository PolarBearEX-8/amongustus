package net.minecraft.client.resources;

import com.google.common.util.concurrent.FutureCallback;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.Augustus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreenWorking;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.HttpUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourcePackRepository {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final FileFilter resourcePackFilter = new ResourcePackRepository.DefaultFileFilter();
   private final File dirResourcepacks;
   public final IResourcePack rprDefaultResourcePack;
   private final File dirServerResourcepacks;
   public final IMetadataSerializer rprMetadataSerializer;
   private IResourcePack resourcePackInstance;
   private final ReentrantLock lock = new ReentrantLock();
   private ListenableFuture downloadingPacks;
   private List repositoryEntriesAll = Lists.newArrayList();
   public List repositoryEntries = Lists.newArrayList();

   public List getRepositoryEntriesAll() {
      return ImmutableList.copyOf(this.repositoryEntriesAll);
   }

   public void setRepositories(List list) {
      this.repositoryEntries.clear();
      this.repositoryEntries.addAll(list);
   }

   public ListenableFuture pingResourcePackUrl(String str) {
      return HttpUtil.DOWNLOADER_EXECUTOR.submit(() -> {
         try {
            HttpURLConnection conn = (HttpURLConnection)new URL(str).openConnection(Minecraft.getMinecraft().getProxy());

            for (java.util.Map.Entry entry : (Iterable<java.util.Map.Entry>)(Minecraft.getSessionInfo().entrySet())) {
               conn.setRequestProperty((String)entry.getKey(), (String)entry.getValue());
            }

            Thread.sleep(2500L);
            conn.disconnect();
         } catch (Exception e) {
         }
      });
   }

   private void deleteOldServerResourcesPacks() {
      if (this.dirServerResourcepacks != null && !this.dirServerResourcepacks.exists()) {
         this.dirServerResourcepacks.mkdirs();
      }

      ArrayList list = Lists.newArrayList(FileUtils.listFiles(this.dirServerResourcepacks, TrueFileFilter.TRUE, (IOFileFilter)null));
      Collections.sort(list, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
      int n = 0;

      for (File file : (Iterable<File>)(list)) {
         if (n++ >= 10) {
            LOGGER.info("Deleting old server resource pack " + file.getName());
            FileUtils.deleteQuietly(file);
         }
      }
   }

   public IResourcePack getResourcePackInstance() {
      return this.resourcePackInstance;
   }

   public List getRepositoryEntries() {
      return ImmutableList.copyOf(this.repositoryEntries);
   }

   private void fixDirResourcepacks() {
      if (this.dirResourcepacks.exists()) {
         if (!this.dirResourcepacks.isDirectory() && (!this.dirResourcepacks.delete() || !this.dirResourcepacks.mkdirs())) {
            LOGGER.warn("Unable to recreate resourcepack folder, it exists but is not a directory: " + this.dirResourcepacks);
         }
      } else if (!this.dirResourcepacks.mkdirs()) {
         LOGGER.warn("Unable to create resourcepack folder: " + this.dirResourcepacks);
      }
   }

   public ListenableFuture setResourcePackInstance(File file) {
      this.resourcePackInstance = new FileResourcePack(file);
      return Minecraft.getMinecraft().scheduleResourcesRefresh();
   }

   public ResourcePackRepository(File file, File file2, IResourcePack resourcePack, IMetadataSerializer metadataSerializer, GameSettings gameSettings) {
      this.dirResourcepacks = file;
      this.dirServerResourcepacks = file2;
      this.rprDefaultResourcePack = resourcePack;
      this.rprMetadataSerializer = metadataSerializer;
      this.fixDirResourcepacks();
      this.updateRepositoryEntriesAll();
      Iterator it = gameSettings.resourcePacks.iterator();

      while (it.hasNext()) {
         String str = (String)it.next();

         for (ResourcePackRepository.Entry entry : (Iterable<ResourcePackRepository.Entry>)(this.repositoryEntriesAll)) {
            if (entry.getResourcePackName().equals(str)) {
               if (entry.getPackFormat() == 1 || gameSettings.incompatibleResourcePacks.contains(entry.getResourcePackName())) {
                  this.repositoryEntries.add(entry);
                  break;
               }

               it.remove();
               LOGGER.warn("Removed selected resource pack {} because it's no longer compatible", new Object[]{entry.getResourcePackName()});
            }
         }
      }
   }

   public File getDirResourcepacks() {
      return this.dirResourcepacks;
   }

   private List getResourcePackFiles() {
      File file = null;

      try {
         file = new File(System.getProperty("user.dir") + Augustus.getInstance().getName().toLowerCase() + "/packs");
         if (file.exists()) {
            for (File file2 : Objects.requireNonNull(file.listFiles())) {
               file2.delete();
            }
         }
      } catch (Exception e) {
      }

      String str = Augustus.getInstance().getName().toLowerCase() + "/packs";
      String str2 = str;
      ClientFileUtil.extractResource("assets/augustus/packs/AugustusSmoothFont.zip", str2, true);
      str = Augustus.getInstance().getName().toLowerCase() + "/packs";
      String str3 = str;
      ClientFileUtil.extractResource("assets/augustus/packs/AugustusAmiFont.zip", str3, true);
      str = Augustus.getInstance().getName().toLowerCase() + "/packs";
      String str4 = str;
      ClientFileUtil.extractResource("assets/augustus/packs/AugustusLowFire.zip", str4, true);
      ArrayList list = new ArrayList(Collections.emptyList());
      if (this.dirResourcepacks.isDirectory()) {
         list.addAll(Arrays.asList(this.dirResourcepacks.listFiles(resourcePackFilter)));
      }

      if (file != null && file.exists()) {
         for (File file3 : Objects.requireNonNull(file.listFiles())) {
            if (file3.getName().endsWith(".zip")) {
               list.add(file3);
            }
         }
      }

      return list;
   }

   public void clearResourcePack() {
      this.lock.lock();

      try {
         if (this.downloadingPacks != null) {
            this.downloadingPacks.cancel(true);
         }

         this.downloadingPacks = null;
         if (this.resourcePackInstance != null) {
            this.resourcePackInstance = null;
            Minecraft.getMinecraft().scheduleResourcesRefresh();
         }
      } finally {
         this.lock.unlock();
      }
   }

   public void updateRepositoryEntriesAll() {
      HashMap map = new HashMap();

      for (ResourcePackRepository.Entry entry : (Iterable<ResourcePackRepository.Entry>)(this.repositoryEntriesAll)) {
         map.put(entry.hashCode(), entry);
      }

      LinkedHashSet set = new LinkedHashSet();

      for (File file : (Iterable<File>)(this.getResourcePackFiles())) {
         ResourcePackRepository.Entry entry2 = new ResourcePackRepository.Entry(this, file, null);
         int n = entry2.hashCode();
         if (!map.containsKey(n)) {
            try {
               entry2.updateResourcePack();
               set.add(entry2);
            } catch (Exception e) {
               set.remove(entry2);
            }
         } else {
            set.add(map.get(n));
         }
      }

      for (ResourcePackRepository.Entry entry3 : (Iterable<ResourcePackRepository.Entry>)(map.values())) {
         if (!set.contains(entry3)) {
            entry3.closeResourcePack();
         }
      }

      this.repositoryEntriesAll = new ArrayList(set);
   }

   public ListenableFuture downloadResourcePack(String str, String str2) {
      String str3;
      if (str2.matches("^[a-f0-9]{40}$")) {
         str3 = str2;
      } else {
         str3 = "legacy";
      }

      File file = new File(this.dirServerResourcepacks, str3);
      this.lock.lock();

      try {
         this.clearResourcePack();
         if (file.exists() && str2.length() == 40) {
            try {
               String str4 = Hashing.sha1().hashBytes(Files.toByteArray(file)).toString();
               if (str4.equals(str2)) {
                  return this.setResourcePackInstance(file);
               }

               LOGGER.warn("File " + file + " had wrong hash (expected " + str2 + ", found " + str4 + "). Deleting it.");
               FileUtils.deleteQuietly(file);
            } catch (IOException e) {
               LOGGER.warn("File " + file + " couldn't be hashed. Deleting it.", e);
               FileUtils.deleteQuietly(file);
            }
         }

         this.deleteOldServerResourcesPacks();
         GuiScreenWorking guiScreenWorking = new GuiScreenWorking();
         Map sessionInfo = Minecraft.getSessionInfo();
         Minecraft minecraft = Minecraft.getMinecraft();
         Futures.getUnchecked(minecraft.addScheduledTask(new ResourcePackRepository.WorkerTask(this, minecraft, guiScreenWorking)));
         this.downloadingPacks = HttpUtil.downloadResourcePack(file, str, sessionInfo, 52428800, guiScreenWorking, minecraft.getProxy());
         Futures.addCallback(this.downloadingPacks, new ResourcePackRepository.DefaultFutureCallback(this, file), MoreExecutors.directExecutor());
         return this.downloadingPacks;
      } finally {
         this.lock.unlock();
      }
   }



   public static final class DefaultFileFilter implements FileFilter {

      @Override
      public boolean accept(File file) {
         boolean flag = file.isFile() && file.getName().endsWith(".zip");
         boolean flag2 = file.isDirectory() && new File(file, "pack.mcmeta").isFile();
         return flag || flag2;
      }

   }

   public static class WorkerTask implements Runnable {
      public final Minecraft mc;
      public final GuiScreenWorking workingScreen;
      public final ResourcePackRepository repository;

      @Override
      public void run() {
         GuiScreenWorking workingScreen2 = this.workingScreen;
         this.mc.displayGuiScreen(workingScreen2);
      }

      public WorkerTask(ResourcePackRepository resourcePackRepository, Minecraft mc2, GuiScreenWorking guiScreenWorking) {
         this.repository = resourcePackRepository;
         this.mc = mc2;
         this.workingScreen = guiScreenWorking;
      }
   }

   public static class DefaultFutureCallback implements FutureCallback {
      public final File packFile;
      public final ResourcePackRepository repository;

      public void onSuccess(Object obj) {
         this.repository.setResourcePackInstance(this.packFile);
      }

      public void onFailure(Throwable t) {
      }

      public DefaultFutureCallback(ResourcePackRepository resourcePackRepository, File file) {
         this.repository = resourcePackRepository;
         this.packFile = file;
      }
   }

   public static class Entry {
      private final File resourcePackFile;
      private IResourcePack reResourcePack;
      private PackMetadataSection rePackMetadataSection;
      private BufferedImage texturePackIcon;
      private ResourceLocation locationTexturePackIcon;
      public final ResourcePackRepository repository;

      public void bindTexturePackIcon(TextureManager textureManager) {
         if (this.locationTexturePackIcon == null) {
            this.locationTexturePackIcon = textureManager.getDynamicTextureLocation("texturepackicon", new DynamicTexture(this.texturePackIcon));
         }

         textureManager.bindTexture(this.locationTexturePackIcon);
      }

      public int getPackFormat() {
         return this.rePackMetadataSection.getPackFormat();
      }

      public IResourcePack getResourcePack() {
         return this.reResourcePack;
      }

      public Entry(ResourcePackRepository resourcePackRepository, File file, ResourcePackRepository.DefaultFileFilter obj) {
         this(resourcePackRepository, file);
      }

      @Override
      public boolean equals(Object obj) {
         return this == obj ? true : (obj instanceof Entry ? this.toString().equals(obj.toString()) : false);
      }

      @Override
      public int hashCode() {
         return this.toString().hashCode();
      }

      private Entry(ResourcePackRepository resourcePackRepository, File file) {
         this.repository = resourcePackRepository;
         this.resourcePackFile = file;
      }

      public String getTexturePackDescription() {
         return this.rePackMetadataSection == null
            ? EnumChatFormatting.RED + "Invalid pack.mcmeta (or missing 'pack' section)"
            : this.rePackMetadataSection.getPackDescription().getFormattedText();
      }

      public void updateResourcePack() throws IOException {
         this.reResourcePack = (IResourcePack)(this.resourcePackFile.isDirectory()
            ? new FolderResourcePack(this.resourcePackFile)
            : new FileResourcePack(this.resourcePackFile));

         try {
            this.rePackMetadataSection = (PackMetadataSection)this.reResourcePack.getPackMetadata(this.repository.rprMetadataSerializer, "pack");
         } catch (Exception e) {
            this.rePackMetadataSection = new PackMetadataSection(
               new ChatComponentText("Failed to load the real message caused by unicode characters. Please update your pack.mcmeta"), 1
            );
         }

         try {
            this.texturePackIcon = this.reResourcePack.getPackImage();
         } catch (IOException e2) {
         }

         if (this.texturePackIcon == null) {
            this.texturePackIcon = this.repository.rprDefaultResourcePack.getPackImage();
         }

         this.closeResourcePack();
      }

      public void closeResourcePack() {
         if (this.reResourcePack instanceof Closeable) {
            IOUtils.closeQuietly((Closeable)this.reResourcePack);
         }
      }

      @Override
      public String toString() {
         return String.format(
            "%s:%s:%d", this.resourcePackFile.getName(), this.resourcePackFile.isDirectory() ? "folder" : "zip", this.resourcePackFile.lastModified()
         );
      }

      public String getResourcePackName() {
         return this.reResourcePack.getPackName();
      }

   }
}
