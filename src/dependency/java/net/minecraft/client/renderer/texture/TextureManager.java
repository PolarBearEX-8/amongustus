package net.minecraft.client.renderer.texture;

import client.event.BridgeListener;
import client.event.PostRenderBroadcaster;
import client.gui.OptifineGuiContainerManager;
import client.render.AllocateTextureMapManager;
import client.render.EmissiveTexturesManager;
import client.util.BuildManager;
import client.util.DefaultLoggerEntry;
import client.util.DistanceStack;
import client.util.Listener68Extension;
import client.util.ListenerAdapter26;
import client.util.OptifineRandomManager;
import client.util.SecondaryRuntimeExceptionAdapter;
import client.util.SmoothCoordinator;
import client.util.SmoothScheduler;
import client.util.SpecialLoggerEntry;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextureManager implements IResourceManagerReloadListener, PostRenderBroadcaster {
   public static final Logger logger = LogManager.getLogger();
   public final Map map2 = Maps.newHashMap();
   public final List list = Lists.newArrayList();
   public final Map map = Maps.newHashMap();
   public IResourceManager iResourceManager;
   public BridgeListener bridgeListener;
   public ResourceLocation resourceLocation;

   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   public void applyResourceLocation(ResourceLocation location, boolean flag) {
      if (BuildManager.isActive30()) {
         location = OptifineRandomManager.resolveResourceLocation(location);
      }

      if (BuildManager.isActive56()) {
         location = OptifineGuiContainerManager.resolveResourceLocation(location);
      }

      Object entry = (BridgeListener)this.map2.get(location);
      if (EmissiveTexturesManager.isEnabled2()) {
         entry = EmissiveTexturesManager.resolveBridgeListener((BridgeListener)entry, this.map2);
      }

      if (entry == null) {
         entry = new DefaultLoggerEntry(location, flag);
         this.matchesResourceLocation(location, (BridgeListener)entry);
      }

      if (BuildManager.canExecute()) {
         AllocateTextureMapManager.applyBridgeListener((BridgeListener)entry);
      } else {
         SpecialLoggerEntry.applyIndexByIndex(((BridgeListener)entry).getCount());
      }

      this.bridgeListener = (BridgeListener)entry;
      this.resourceLocation = location;
   }

   public void applyResourceLocation2(ResourceLocation location) {
      BridgeListener bridgeListener2 = this.get(location);
      if (bridgeListener2 != null) {
         this.map2.remove(location);
         SpecialLoggerEntry.applyIndex(bridgeListener2.getCount());
      }
   }

   public boolean matchesResourceLocation(ResourceLocation location, BridgeListener bridgeListener2) {
      boolean flag = true;
      BridgeListener textureObject = bridgeListener2;

      try {
         bridgeListener2.applyIResourceManager(this.iResourceManager);
      } catch (IOException ioException) {
         logger.warn("Failed to load texture: " + location, ioException);
         textureObject = SpecialLoggerEntry.dynamicTexture;
         this.map2.put(location, textureObject);
         flag = false;
      } catch (Throwable throwable) {
         CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Registering texture");
         CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Resource location being registered");
         crashReportCategory.add("Resource location", location);
         crashReportCategory.applyText("Texture object class", new DistanceStack(this, bridgeListener2));
         throw new SecondaryRuntimeExceptionAdapter(crashReport);
      }

      this.map2.put(location, textureObject);
      return flag;
   }

   @Override
   public void updateState2() {
      SmoothScheduler.updateState();
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         ((PostRenderBroadcaster)iterator.next()).updateState2();
      }
   }

   public ResourceLocation resolveResourceLocationByText(String text, DynamicTexture dynamicTexture) {
      Integer count = (Integer)this.map.get(text);
      if (count == null) {
         count = 1;
      } else {
         count = count + 1;
      }

      this.map.put(text, count);
      ResourceLocation location = new ResourceLocation(String.format("dynamic/%s_%d", text, count));
      this.matchesResourceLocation(location, dynamicTexture);
      return location;
   }

   public BridgeListener get(ResourceLocation location) {
      return (BridgeListener)this.map2.get(location);
   }

   public boolean matchesResourceLocationByResourceLocation(ResourceLocation location, Listener68Extension listener68Extension) {
      if (this.matchesResourceLocation(location, listener68Extension)) {
         this.list.add(listener68Extension);
         return true;
      } else {
         return false;
      }
   }

   public void applyResourceLocation3(ResourceLocation location) {
      this.applyResourceLocation(location, true);
   }

   public BridgeListener getBridgeListener() {
      return this.bridgeListener;
   }

   public void updateState() {
      for (Entry entry : (java.lang.Iterable<Entry>) (new HashSet(this.map2.entrySet()))) {
         ResourceLocation key = (ResourceLocation)entry.getKey();
         BridgeListener value = (BridgeListener)entry.getValue();
         if (value instanceof SmoothCoordinator) {
            this.matchesResourceLocation(key, value);
         }
      }
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager2) {
      BuildManager.applyText4("*** Reloading textures ***");
      BuildManager.applyText("Resource packs: " + BuildManager.getText3());
      Iterator iterator = this.map2.keySet().iterator();

      while (iterator.hasNext()) {
         ResourceLocation location = (ResourceLocation)iterator.next();
         String text = location.getText1();
         if (text.startsWith("mcpatcher/") || text.startsWith("optifine/") || EmissiveTexturesManager.matchesResourceLocationByResourceLocation(location)) {
            BridgeListener bridgeListener2 = (BridgeListener)this.map2.get(location);
            if (bridgeListener2 instanceof ListenerAdapter26) {
               ((ListenerAdapter26)bridgeListener2).updateState3();
            }

            iterator.remove();
         }
      }

      EmissiveTexturesManager.renderElement5();

      for (Object element : new HashSet(this.map2.entrySet())) {
         Entry entry = (Entry)element;
         this.matchesResourceLocation((ResourceLocation)entry.getKey(), (BridgeListener)entry.getValue());
      }
   }

   public TextureManager(IResourceManager iResourceManager2) {
      this.iResourceManager = iResourceManager2;
   }
}
