package client.event;

import client.util.InvalidMetadataForManager;
import client.util.LeakedResourceInputStream;
import client.util.ListenerResolver;
import com.google.common.collect.Lists;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class McmetaListener implements IResourceManager {
   public static final Logger logger = LogManager.getLogger();
   public final List list = Lists.newArrayList();
   public final InvalidMetadataForManager invalidMetadataForManager;

   public void add(PreUpdateProcessor preUpdateProcessor) {
      this.list.add(preUpdateProcessor);
   }

   public static ResourceLocation resolveResourceLocation(ResourceLocation location) {
      return new ResourceLocation(location.getText(), location.getText1() + ".mcmeta");
   }

   @Override
   public Set getSet() {
      return null;
   }

   @Override
   public List resolveListByResourceLocation(ResourceLocation location) throws IOException {
      ArrayList list2 = Lists.newArrayList();
      ResourceLocation location2 = resolveResourceLocation(location);

      for (PreUpdateProcessor preUpdateProcessor : (Iterable<PreUpdateProcessor>)(Iterable)this.list) {
         if (preUpdateProcessor.matchesResourceLocation(location)) {
            InputStream inputStream = preUpdateProcessor.matchesResourceLocation(location2) ? this.resolveInputStreamByResourceLocation(location2, preUpdateProcessor) : null;
            list2.add(new ListenerResolver(preUpdateProcessor.getName(), location, this.resolveInputStreamByResourceLocation(location, preUpdateProcessor), inputStream, this.invalidMetadataForManager));
         }
      }

      if (list2.isEmpty()) {
         throw new FileNotFoundException(location.toString());
      } else {
         return list2;
      }
   }

   public InputStream resolveInputStreamByResourceLocation(ResourceLocation location, PreUpdateProcessor preUpdateProcessor) throws IOException {
      InputStream inputStream = preUpdateProcessor.resolveInputStream(location);
      return (InputStream)(logger.isDebugEnabled() ? new LeakedResourceInputStream(inputStream, location, preUpdateProcessor.getName()) : inputStream);
   }

   @Override
   public IResource resolveIResourceByResourceLocation(ResourceLocation location) throws IOException {
      PreUpdateProcessor preUpdateProcessor = null;
      ResourceLocation location2 = resolveResourceLocation(location);

      for (int index = this.list.size() - 1; index >= 0; index--) {
         PreUpdateProcessor preUpdateProcessor2 = (PreUpdateProcessor)this.list.get(index);
         if (preUpdateProcessor == null && preUpdateProcessor2.matchesResourceLocation(location2)) {
            preUpdateProcessor = preUpdateProcessor2;
         }

         if (preUpdateProcessor2.matchesResourceLocation(location)) {
            InputStream inputStream = null;
            if (preUpdateProcessor != null) {
               inputStream = this.resolveInputStreamByResourceLocation(location2, preUpdateProcessor);
            }

            return new ListenerResolver(preUpdateProcessor2.getName(), location, this.resolveInputStreamByResourceLocation(location, preUpdateProcessor2), inputStream, this.invalidMetadataForManager);
         }
      }

      throw new FileNotFoundException(location.toString());
   }

   public static Logger getLogger() {
      return logger;
   }

   public McmetaListener(InvalidMetadataForManager invalidMetadataForManager2) {
      this.invalidMetadataForManager = invalidMetadataForManager2;
   }
}
