package net.minecraft.client.resources;

import com.google.common.cache.CacheLoader;
import com.google.common.collect.Maps;
import com.mojang.authlib.minecraft.InsecureTextureException;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class SkinManager {
   private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(0, 2, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
   private final TextureManager textureManager;
   private final File skinCacheDir;
   private final MinecraftSessionService sessionService;
   private final LoadingCache skinCacheLoader;

   public ResourceLocation loadSkin(MinecraftProfileTexture minecraftProfileTexture, Type type, SkinManager.SkinAvailableCallback skinAvailableCallback) {
      ResourceLocation location = new ResourceLocation("skins/" + minecraftProfileTexture.getHash());
      if (this.textureManager.getTexture(location) != null) {
         if (skinAvailableCallback != null) {
            skinAvailableCallback.skinAvailable(type, location, minecraftProfileTexture);
         }
      } else {
         File file = new File(this.skinCacheDir, minecraftProfileTexture.getHash().length() > 2 ? minecraftProfileTexture.getHash().substring(0, 2) : "xx");
         File file2 = new File(file, minecraftProfileTexture.getHash());
         ImageBufferDownload imageBufferDownload = type == Type.SKIN ? new ImageBufferDownload() : null;
         ThreadDownloadImageData threadDownloadImageData = new ThreadDownloadImageData(
            file2, minecraftProfileTexture.getUrl(), DefaultPlayerSkin.getDefaultSkinLegacy(), new SkinManager.DefaultImageBuffer(this, imageBufferDownload, skinAvailableCallback, type, location, minecraftProfileTexture)
         );
         this.textureManager.loadTexture(location, threadDownloadImageData);
      }

      return location;
   }

   public static MinecraftSessionService accessSessionService(SkinManager skinManager) {
      return skinManager.sessionService;
   }

   public Map loadSkinFromCache(GameProfile profile) {
      return (Map)this.skinCacheLoader.getUnchecked(profile);
   }

   public ResourceLocation loadSkin(MinecraftProfileTexture minecraftProfileTexture, Type type) {
      return this.loadSkin(minecraftProfileTexture, type, (SkinManager.SkinAvailableCallback)null);
   }

   public SkinManager(TextureManager textureManager2, File file, MinecraftSessionService minecraftSessionService) {
      this.textureManager = textureManager2;
      this.skinCacheDir = file;
      this.sessionService = minecraftSessionService;
      this.skinCacheLoader = CacheBuilder.newBuilder().expireAfterAccess(15L, TimeUnit.SECONDS).build(new SkinManager.DefaultCacheLoader(this));
   }

   public void loadProfileTextures(GameProfile profile, SkinManager.SkinAvailableCallback skinAvailableCallback, boolean flag) {
      THREAD_POOL.submit(new SkinManager.WorkerTask(this, profile, flag, skinAvailableCallback));
   }



   public static class DefaultCacheLoader extends CacheLoader {
      public final SkinManager skinManager;

      public DefaultCacheLoader(SkinManager skinManager2) {
         this.skinManager = skinManager2;
      }

      public Object load(Object obj) throws Exception {
         return this.load((GameProfile)obj);
      }

      public Map load(GameProfile profile) throws Exception {
         return Minecraft.getMinecraft().getSessionService().getTextures(profile, false);
      }
   }

   public static class DefaultImageBuffer implements IImageBuffer {
      public final IImageBuffer imageBuffer;
      public final SkinManager.SkinAvailableCallback skinAvailableCallback;
      public final Type textureType;
      public final ResourceLocation resourceLocation;
      public final MinecraftProfileTexture profileTexture;
      public final SkinManager skinManager;

      @Override
      public void skinAvailable() {
         if (this.imageBuffer != null) {
            this.imageBuffer.skinAvailable();
         }

         if (this.skinAvailableCallback != null) {
            this.skinAvailableCallback.skinAvailable(this.textureType, this.resourceLocation, this.profileTexture);
         }
      }

      @Override
      public BufferedImage parseUserSkin(BufferedImage image) {
         if (this.imageBuffer != null) {
            image = this.imageBuffer.parseUserSkin(image);
         }

         return image;
      }

      public DefaultImageBuffer(
         SkinManager skinManager2, IImageBuffer imageBuffer2, SkinManager.SkinAvailableCallback skinAvailableCallback2, Type type, ResourceLocation location, MinecraftProfileTexture minecraftProfileTexture
      ) {
         this.skinManager = skinManager2;
         this.imageBuffer = imageBuffer2;
         this.skinAvailableCallback = skinAvailableCallback2;
         this.textureType = type;
         this.resourceLocation = location;
         this.profileTexture = minecraftProfileTexture;
      }
   }

   public static class WorkerTask implements Runnable {
      public final GameProfile profile;
      public final boolean requireSecure;
      public final SkinManager.SkinAvailableCallback skinAvailableCallback;
      public final SkinManager skinManager;

      @Override
      public void run() {
         HashMap map = Maps.newHashMap();

         try {
            map.putAll(SkinManager.accessSessionService(this.skinManager).getTextures(this.profile, this.requireSecure));
         } catch (InsecureTextureException e) {
         }

         if (map.isEmpty() && this.profile.getId().equals(Minecraft.getMinecraft().getSession().getProfile().getId())) {
            this.profile.getProperties().clear();
            this.profile.getProperties().putAll(Minecraft.getMinecraft().getTwitchDetails());
            map.putAll(SkinManager.accessSessionService(this.skinManager).getTextures(this.profile, false));
         }

         Minecraft.getMinecraft().addScheduledTask(new SkinManager.WorkerTask.WorkerTask2(this, map));
      }

      public WorkerTask(SkinManager skinManager2, GameProfile profile2, boolean flag, SkinManager.SkinAvailableCallback skinAvailableCallback2) {
         this.skinManager = skinManager2;
         this.profile = profile2;
         this.requireSecure = flag;
         this.skinAvailableCallback = skinAvailableCallback2;
      }



      public static class WorkerTask2 implements Runnable {
         public final Map textures;
         public final SkinManager.WorkerTask parentTask;

         @Override
         public void run() {
            if (this.textures.containsKey(Type.SKIN)) {
               this.parentTask.skinManager.loadSkin((MinecraftProfileTexture)this.textures.get(Type.SKIN), Type.SKIN, this.parentTask.skinAvailableCallback);
            }

            if (this.textures.containsKey(Type.CAPE)) {
               this.parentTask.skinManager.loadSkin((MinecraftProfileTexture)this.textures.get(Type.CAPE), Type.CAPE, this.parentTask.skinAvailableCallback);
            }
         }

         public WorkerTask2(SkinManager.WorkerTask obj, Map map) {
            this.parentTask = obj;
            this.textures = map;
         }
      }
   }

   public interface SkinAvailableCallback {
      void skinAvailable(Type type, ResourceLocation location, MinecraftProfileTexture minecraftProfileTexture);
   }
}
