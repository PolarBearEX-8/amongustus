package net.minecraft.client.resources;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleReloadableResourceManager implements IReloadableResourceManager {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Joiner joinerResourcePacks = Joiner.on(", ");
   private final Map domainResourceManagers = Maps.newHashMap();
   private final List reloadListeners = Lists.newArrayList();
   private final Set setResourceDomains = Sets.newLinkedHashSet();
   private final IMetadataSerializer rmMetadataSerializer;

   public void reloadResourcePack(IResourcePack resourcePack) {
      for (String str : (Iterable<String>)(resourcePack.getResourceDomains())) {
         this.setResourceDomains.add(str);
         FallbackResourceManager fallbackResourceManager = (FallbackResourceManager)this.domainResourceManagers.get(str);
         if (fallbackResourceManager == null) {
            fallbackResourceManager = new FallbackResourceManager(this.rmMetadataSerializer);
            this.domainResourceManagers.put(str, fallbackResourceManager);
         }

         fallbackResourceManager.addResourcePack(resourcePack);
      }
   }

   private void notifyReloadListeners() {
      for (IResourceManagerReloadListener listener : (Iterable<IResourceManagerReloadListener>)(this.reloadListeners)) {
         listener.onResourceManagerReload(this);
      }
   }

   @Override
   public List getAllResources(ResourceLocation location) throws IOException {
      IResourceManager resourceManager = (IResourceManager)this.domainResourceManagers.get(location.getResourceDomain());
      if (resourceManager != null) {
         return resourceManager.getAllResources(location);
      } else {
         throw new FileNotFoundException(location.toString());
      }
   }

   @Override
   public IResource getResource(ResourceLocation location) throws IOException {
      IResourceManager resourceManager = (IResourceManager)this.domainResourceManagers.get(location.getResourceDomain());
      if (resourceManager != null) {
         return resourceManager.getResource(location);
      } else {
         throw new FileNotFoundException(location.toString());
      }
   }

   @Override
   public Set getResourceDomains() {
      return this.setResourceDomains;
   }

   private void clearResources() {
      this.domainResourceManagers.clear();
      this.setResourceDomains.clear();
   }

   @Override
   public void registerReloadListener(IResourceManagerReloadListener listener) {
      this.reloadListeners.add(listener);
      listener.onResourceManagerReload(this);
   }

   @Override
   public void reloadResources(List list) {
      this.clearResources();
      LOGGER.info("Reloading ResourceManager: " + joinerResourcePacks.join(Iterables.transform(list, new SimpleReloadableResourceManager.TransformFunction(this))));

      for (IResourcePack resourcePack : (Iterable<IResourcePack>)(list)) {
         this.reloadResourcePack(resourcePack);
      }

      this.notifyReloadListeners();
   }

   public SimpleReloadableResourceManager(IMetadataSerializer metadataSerializer) {
      this.rmMetadataSerializer = metadataSerializer;
   }


   public static class TransformFunction implements Function {
      public final SimpleReloadableResourceManager resourceManager;

      public Object apply(Object obj) {
         return this.apply((IResourcePack)obj);
      }

      public String apply(IResourcePack resourcePack) {
         return resourcePack.getPackName();
      }

      public TransformFunction(SimpleReloadableResourceManager manager) {
         this.resourceManager = manager;
      }
   }
}
