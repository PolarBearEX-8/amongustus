package net.minecraft.client.renderer.texture;

import java.util.concurrent.Callable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.augustus.cosmetics.MoreCosmeticsBootstrap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomGuis;
import net.optifine.EmissiveTextures;
import net.optifine.RandomEntities;
import net.optifine.config.Config;
import net.optifine.shaders.ShadersTex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextureManager implements IResourceManagerReloadListener, ITickable {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Map mapTextureObjects = Maps.newHashMap();
   private final List listTickables = Lists.newArrayList();
   private final Map mapTextureCounters = Maps.newHashMap();
   private IResourceManager theResourceManager;
   private ITextureObject boundTexture;
   private ResourceLocation boundTextureLocation;

   public void deleteTexture(ResourceLocation location) {
      ITextureObject texture = this.getTexture(location);
      if (texture != null) {
         this.mapTextureObjects.remove(location);
         TextureUtil.deleteTexture(texture.getGlTextureId());
      }
   }

   public void bindTexture(ResourceLocation location) {
      this.bindTexture(location, true);
   }

   public ITextureObject getBoundTexture() {
      return this.boundTexture;
   }

   public boolean loadTickableTexture(ResourceLocation location, ITickableTextureObject tickableTextureObject) {
      if (this.loadTexture(location, tickableTextureObject)) {
         this.listTickables.add(tickableTextureObject);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void tick() {
      MoreCosmeticsBootstrap.tick();

      for (Object obj : this.listTickables) {
         ((ITickable)obj).tick();
      }
   }

   public void bindTexture(ResourceLocation location, boolean flag) {
      if (Config.isRandomEntities()) {
         location = RandomEntities.getTextureLocation(location);
      }

      if (Config.isCustomGuis()) {
         location = CustomGuis.getTextureLocation(location);
      }

      Object obj = (ITextureObject)this.mapTextureObjects.get(location);
      if (EmissiveTextures.isActive()) {
         obj = EmissiveTextures.getEmissiveTexture((ITextureObject)obj, this.mapTextureObjects);
      }

      if (obj == null) {
         obj = new SimpleTexture(location, flag);
         this.loadTexture(location, (ITextureObject)obj);
      }

      if (Config.isShaderPackLoaded()) {
         ShadersTex.bindTexture((ITextureObject)obj);
      } else {
         TextureUtil.bindTexture(((ITextureObject)obj).getGlTextureId());
      }

      this.boundTexture = (ITextureObject)obj;
      this.boundTextureLocation = location;
   }

   public ResourceLocation getDynamicTextureLocation(String str, DynamicTexture dynamicTexture) {
      Integer num = (Integer)this.mapTextureCounters.get(str);
      if (num == null) {
         num = 1;
      } else {
         num = num + 1;
      }

      this.mapTextureCounters.put(str, num);
      ResourceLocation location = new ResourceLocation(String.format("dynamic/%s_%d", str, num));
      this.loadTexture(location, dynamicTexture);
      return location;
   }

   public TextureManager(IResourceManager resourceManager) {
      this.theResourceManager = resourceManager;
   }

   public ITextureObject getTexture(ResourceLocation location) {
      return (ITextureObject)this.mapTextureObjects.get(location);
   }

   public ResourceLocation getBoundTextureLocation() {
      return this.boundTextureLocation;
   }

   public boolean loadTexture(ResourceLocation location, ITextureObject textureObject) {
      boolean flag = true;
      Object obj = textureObject;

      try {
         textureObject.loadTexture(this.theResourceManager);
      } catch (IOException e) {
         LOGGER.warn("Failed to load texture: " + location, e);
         obj = TextureUtil.missingTexture;
         this.mapTextureObjects.put(location, obj);
         flag = false;
      } catch (Throwable e2) {
         CrashReport crashReport = CrashReport.makeCrashReport(e2, "Registering texture");
         CrashReportCategory crashReportCategory = crashReport.makeCategory("Resource location being registered");
         crashReportCategory.addCrashSection("Resource location", location);
         crashReportCategory.addCrashSectionCallable("Texture object class", new TextureManager.ValueCallable(this, textureObject));
         throw new ReportedException(crashReport);
      }

      this.mapTextureObjects.put(location, obj);
      return flag;
   }

   public void reloadBannerTextures() {
      for (Object obj : new HashSet(this.mapTextureObjects.entrySet())) {
         Entry entry = (Entry)obj;
         ResourceLocation key = (ResourceLocation)entry.getKey();
         ITextureObject value = (ITextureObject)entry.getValue();
         if (value instanceof LayeredColorMaskTexture) {
            this.loadTexture(key, value);
         }
      }
   }

   @Override
   public void onResourceManagerReload(IResourceManager resourceManager) {
      Config.dbg("*** Reloading textures ***");
      Config.log("Resource packs: " + Config.getResourcePackNames());
      Iterator it = this.mapTextureObjects.keySet().iterator();

      while (it.hasNext()) {
         ResourceLocation location = (ResourceLocation)it.next();
         String resourcePath = location.getResourcePath();
         if (resourcePath.startsWith("mcpatcher/") || resourcePath.startsWith("optifine/") || EmissiveTextures.isEmissive(location)) {
            ITextureObject textureObject = (ITextureObject)this.mapTextureObjects.get(location);
            if (textureObject instanceof AbstractTexture) {
               ((AbstractTexture)textureObject).deleteGlTexture();
            }

            it.remove();
         }
      }

      EmissiveTextures.update();

      for (Object obj : new HashSet(this.mapTextureObjects.entrySet())) {
         Entry entry = (Entry)obj;
         this.loadTexture((ResourceLocation)entry.getKey(), (ITextureObject)entry.getValue());
      }
   }


   public static class ValueCallable implements Callable {
      public final ITextureObject textureObject;
      public final TextureManager textureManager;

      public String getTextureObjectClassName() throws Exception {
         return this.textureObject.getClass().getName();
      }

      public ValueCallable(TextureManager textureManager2, ITextureObject textureObject2) {
         this.textureManager = textureManager2;
         this.textureObject = textureObject2;
      }

      @Override
      public Object call() throws Exception {
         return this.getTextureObjectClassName();
      }
   }
}
