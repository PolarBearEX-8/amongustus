package net.minecraft.client.resources;

import client.Client;
import client.event.PreUpdateProcessor;
import client.render.TexturepackiconManager;
import client.setting.GameSettings;
import client.util.ConfigManager;
import client.util.GETManager;
import client.util.GeometrySystem;
import client.util.InvalidMetadataForManager;
import client.util.RotationWriter;
import client.util.SSSListenerAdapter;
import client.util.TargetEngine;
import client.util.ZipFileFilter;
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
import net.minecraft.client.Minecraft;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourceLanguageManager {
   public static final Logger logger = LogManager.getLogger();
   public static final FileFilter fileFilter = new ZipFileFilter();
   public final File file;
   public final PreUpdateProcessor preUpdateProcessor2;
   public final File file2;
   public final InvalidMetadataForManager invalidMetadataForManager;
   public PreUpdateProcessor preUpdateProcessor;
   public final ReentrantLock reentrantLock = new ReentrantLock();
   public ListenableFuture listenableFuture;
   public List list2 = Lists.newArrayList();
   public List list = Lists.newArrayList();

   public void updateState2() {
      this.reentrantLock.lock();

      try {
         if (this.listenableFuture != null) {
            this.listenableFuture.cancel(true);
         }

         this.listenableFuture = null;
         if (this.preUpdateProcessor != null) {
            this.preUpdateProcessor = null;
            Minecraft.getMinecraft().getListenableFuture();
         }
      } finally {
         this.reentrantLock.unlock();
      }
   }

   public ListenableFuture resolveListenableFuture(String text, String text2) {
      String text3;
      if (text2.matches("^[a-f0-9]{40}$")) {
         text3 = text2;
      } else {
         text3 = "legacy";
      }

      File file3 = new File(this.file2, text3);
      this.reentrantLock.lock();

      try {
         this.updateState2();
         if (file3.exists() && text2.length() == 40) {
            try {
               String text4 = Hashing.sha1().hashBytes(Files.toByteArray(file3)).toString();
               if (text4.equals(text2)) {
                  return this.resolveListenableFuture(file3);
               }

               logger.warn("File " + file3 + " had wrong hash (expected " + text2 + ", found " + text4 + "). Deleting it.");
               FileUtils.deleteQuietly(file3);
            } catch (IOException ioException) {
               logger.warn("File " + file3 + " couldn't be hashed. Deleting it.", ioException);
               FileUtils.deleteQuietly(file3);
            }
         }

         this.updateState4();
         GeometrySystem geometrySystem = new GeometrySystem();
         Map map = Minecraft.getMap();
         Minecraft mc = Minecraft.getMinecraft();
         RotationWriter rotationWriter = new RotationWriter(this, mc, geometrySystem);
         Futures.getUnchecked(mc.resolveListenableFuture(rotationWriter));
         this.listenableFuture = GETManager.submit(file3, text, map, 52428800, geometrySystem, mc.getProxy());
         Futures.addCallback(this.listenableFuture, new TargetEngine(this, file3), MoreExecutors.directExecutor());
         return this.listenableFuture;
      } finally {
         this.reentrantLock.unlock();
      }
   }

   public ListenableFuture resolveListenableFuture(File file3) {
      this.preUpdateProcessor = new SSSListenerAdapter(file3);
      return Minecraft.getMinecraft().getListenableFuture();
   }

   private void updateState() {
      if (this.file.exists()) {
         if (!this.file.isDirectory() && (!this.file.delete() || !this.file.mkdirs())) {
            logger.warn("Unable to recreate resourcepack folder, it exists but is not a directory: " + this.file);
         }
      } else if (!this.file.mkdirs()) {
         logger.warn("Unable to create resourcepack folder: " + this.file);
      }
   }

   public List copyOf() {
      return ImmutableList.copyOf(this.list2);
   }

   public ListenableFuture submit(String text) {
      return GETManager.listeningExecutorService.submit(() -> ResourceLanguageManager.applyText(text));
   }

   private static void applyText(String text) {
      try {
         HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(text).openConnection(Minecraft.getMinecraft().getProxy());

         for (Entry entry : (Iterable<Entry>) (Iterable) (Minecraft.getMap().entrySet())) {
            httpURLConnection.setRequestProperty((String)entry.getKey(), (String)entry.getValue());
         }

         Thread.sleep(2500L);
         httpURLConnection.disconnect();
      } catch (Exception exception) {
      }
   }

   private List getList() {
      File file3 = null;

      try {
         StringBuilder builder = new StringBuilder().append(System.getProperty("user.dir"));
         Client clientItem = Client.getClient();
         int count2 = (byte)-10;
         file3 = new File(builder.append(clientItem.getString4().toLowerCase()).append("/packs").toString());
         if (file3.exists()) {
            File[] fileArray = Objects.requireNonNull(file3.listFiles());
            count2 = fileArray.length;

            for (int index = 0; index < count2; index++) {
               fileArray[index].delete();
            }
         }
      } catch (Exception exception) {
      }

      StringBuilder builder2 = new StringBuilder();
      Client clientItem2 = Client.getClient();
      String text = builder2.append(clientItem2.getString4().toLowerCase()).append("/packs").toString();
      ConfigManager.applyString("assets/augustus/packs/AugustusSmoothFont.zip", text, true);
      builder2 = new StringBuilder();
      clientItem2 = Client.getClient();
      text = builder2.append(clientItem2.getString4().toLowerCase()).append("/packs").toString();
      ConfigManager.applyString("assets/augustus/packs/AugustusAmiFont.zip", text, true);
      builder2 = new StringBuilder();
      clientItem2 = Client.getClient();
      text = builder2.append(clientItem2.getString4().toLowerCase()).append("/packs").toString();
      ConfigManager.applyString("assets/augustus/packs/AugustusLowFire.zip", text, true);
      ArrayList list3 = new ArrayList(Collections.emptyList());
      if (this.file.isDirectory()) {
         list3.addAll(Arrays.asList(this.file.listFiles(fileFilter)));
      }

      if (file3 != null && file3.exists()) {
         for (File file4 : Objects.requireNonNull(file3.listFiles())) {
            if (file4.getName().endsWith(".zip")) {
               list3.add(file4);
            }
         }
      }

      return list3;
   }

   public void applyList(List list3) {
      this.list.clear();
      this.list.addAll(list3);
   }

   public File getFile() {
      return this.file;
   }

   private void updateState4() {
      if (this.file2 != null && !this.file2.exists()) {
         this.file2.mkdirs();
      }

      ArrayList list3 = Lists.newArrayList(FileUtils.listFiles(this.file2, TrueFileFilter.TRUE, (IOFileFilter)null));
      Collections.sort(list3, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
      int count = 0;

      for (File file3 : (Iterable<File>) (Iterable) (list3)) {
         if (count++ >= 10) {
            logger.info("Deleting old server resource pack " + file3.getName());
            FileUtils.deleteQuietly(file3);
         }
      }
   }

   public List copyOfAsList() {
      return ImmutableList.copyOf(this.list);
   }

   public PreUpdateProcessor getPreUpdateProcessor() {
      return this.preUpdateProcessor;
   }

   public void updateState3() {
      HashMap map = new HashMap();

      for (TexturepackiconManager texturepackiconManager : (Iterable<TexturepackiconManager>) (Iterable) (this.list2)) {
         map.put(texturepackiconManager.hashCode(), texturepackiconManager);
      }

      LinkedHashSet set = new LinkedHashSet();

      for (File file3 : (Iterable<File>) (Iterable) (this.getList())) {
         TexturepackiconManager texturepackiconManager2 = new TexturepackiconManager(this, file3, null);
         int hash = texturepackiconManager2.hashCode();
         if (!map.containsKey(hash)) {
            try {
               texturepackiconManager2.renderElement();
               set.add(texturepackiconManager2);
            } catch (Exception exception) {
               set.remove(texturepackiconManager2);
            }
         } else {
            set.add(map.get(hash));
         }
      }

      for (TexturepackiconManager texturepackiconManager3 : (Iterable<TexturepackiconManager>) (Iterable) (map.values())) {
         if (!set.contains(texturepackiconManager3)) {
            texturepackiconManager3.closeQuietly();
         }
      }

      this.list2 = new ArrayList(set);
   }

   public ResourceLanguageManager(File file3, File file4, PreUpdateProcessor preUpdateProcessor3, InvalidMetadataForManager invalidMetadataForManager2, GameSettings gameSettings) {
      this.file = file3;
      this.file2 = file4;
      this.preUpdateProcessor2 = preUpdateProcessor3;
      this.invalidMetadataForManager = invalidMetadataForManager2;
      this.updateState();
      this.updateState3();
      Iterator iterator = gameSettings.itemList.iterator();

      while (iterator.hasNext()) {
         String text = (String)iterator.next();

         for (TexturepackiconManager texturepackiconManager : (Iterable<TexturepackiconManager>) (Iterable) (this.list2)) {
            if (texturepackiconManager.getText().equals(text)) {
               if (texturepackiconManager.getCount() == 1 || gameSettings.itemList2.contains(texturepackiconManager.getText())) {
                  this.list.add(texturepackiconManager);
                  break;
               }

               iterator.remove();
               logger.warn("Removed selected resource pack {} because it's no longer compatible", new Object[]{texturepackiconManager.getText()});
            }
         }
      }
   }
}
