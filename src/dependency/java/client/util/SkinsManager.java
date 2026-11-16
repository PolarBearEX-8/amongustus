package client.util;

import client.event.PreMotionSubscriber;
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
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.DiamondLeggings;
import net.minecraft.util.ResourceLocation;

public class SkinsManager {
   public static final ExecutorService executorService = new ThreadPoolExecutor(0, 2, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
   public final TextureManager textureManager;
   public final File file;
   public final MinecraftSessionService minecraftSessionService;
   public final LoadingCache loadingCache;

   public void submit(GameProfile gameProfile, PreMotionSubscriber preMotionSubscriber, boolean flag) {
      executorService.submit(new AdvancedRunnableAdapter(this, gameProfile, flag, preMotionSubscriber));
   }

   public Map getUnchecked(GameProfile gameProfile) {
      return (Map)this.loadingCache.getUnchecked(gameProfile);
   }

   public static MinecraftSessionService resolveMinecraftSessionService(SkinsManager skinsManager) {
      return skinsManager.minecraftSessionService;
   }

   public ResourceLocation resolveResourceLocationByMinecraftProfile(MinecraftProfileTexture minecraftProfileTexture, Type type, PreMotionSubscriber preMotionSubscriber) {
      ResourceLocation location = new ResourceLocation("skins/" + minecraftProfileTexture.getHash());
      if (this.textureManager.get(location) != null) {
         if (preMotionSubscriber != null) {
            preMotionSubscriber.applyType(type, location, minecraftProfileTexture);
         }
      } else {
         File file2 = new File(this.file, minecraftProfileTexture.getHash().length() > 2 ? minecraftProfileTexture.getHash().substring(0, 2) : "xx");
         File file3 = new File(file2, minecraftProfileTexture.getHash());
         ServiceIntArrayEntry serviceIntArrayEntry = type == Type.SKIN ? new ServiceIntArrayEntry() : null;
         LoggerEntryHandler loggerEntryHandler = new LoggerEntryHandler(
            file3, minecraftProfileTexture.getUrl(), SlimManager.getResourceLocation(), new DiamondLeggings(this, serviceIntArrayEntry, preMotionSubscriber, type, location, minecraftProfileTexture)
         );
         this.textureManager.matchesResourceLocation(location, loggerEntryHandler);
      }

      return location;
   }

   public ResourceLocation resolveResourceLocationByMinecraftProfile(MinecraftProfileTexture minecraftProfileTexture, Type type) {
      return this.resolveResourceLocationByMinecraftProfile(minecraftProfileTexture, type, (PreMotionSubscriber)null);
   }

   public SkinsManager(TextureManager textureManager2, File file2, MinecraftSessionService minecraftSessionService2) {
      this.textureManager = textureManager2;
      this.file = file2;
      this.minecraftSessionService = minecraftSessionService2;
      this.loadingCache = CacheBuilder.newBuilder().expireAfterAccess(15L, TimeUnit.SECONDS).build(new CacheLoaderAdapter(this));
   }
}
