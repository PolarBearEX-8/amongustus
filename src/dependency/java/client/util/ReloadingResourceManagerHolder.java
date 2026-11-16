package client.util;

import client.event.McmetaListener;
import client.event.PreUpdateProcessor;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReloadingResourceManagerHolder implements Listener69Extension {
   public static final Logger logger = LogManager.getLogger();
   public static final Joiner joiner = Joiner.on(", ");
   public final Map map = Maps.newHashMap();
   public final List list = Lists.newArrayList();
   public final Set set = Sets.newLinkedHashSet();
   public final InvalidMetadataForManager invalidMetadataForManager;

   public void applyPreUpdateProcessor(PreUpdateProcessor preUpdateProcessor) {
      for (Object textObj : preUpdateProcessor.getSet()) {
         String text = (String)textObj;
         this.set.add(text);
         McmetaListener mcmetaListener = (McmetaListener)this.map.get(text);
         if (mcmetaListener == null) {
            mcmetaListener = new McmetaListener(this.invalidMetadataForManager);
            this.map.put(text, mcmetaListener);
         }

         mcmetaListener.add(preUpdateProcessor);
      }
   }

   private void updateState2() {
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         ((IResourceManagerReloadListener)iterator.next()).applyIResourceManager(this);
      }
   }

   @Override
   public void applyList(List list2) {
      this.updateState();
      logger.info("Reloading ResourceManager: " + joiner.join(Iterables.transform(list2, new AltFunctionAdapter(this))));

      for (Object preUpdateProcessorObj : list2) {
         PreUpdateProcessor preUpdateProcessor = (PreUpdateProcessor)preUpdateProcessorObj;
         this.applyPreUpdateProcessor(preUpdateProcessor);
      }

      this.updateState2();
   }

   @Override
   public void applyIResourceManagerReloadList(IResourceManagerReloadListener iResourceManagerReloadListener) {
      this.list.add(iResourceManagerReloadListener);
      iResourceManagerReloadListener.applyIResourceManager(this);
   }

   @Override
   public List resolveListByResourceLocation(ResourceLocation location) throws IOException {
      IResourceManager iResourceManager = (IResourceManager)this.map.get(location.getText());
      if (iResourceManager != null) {
         return iResourceManager.resolveListByResourceLocation(location);
      } else {
         throw new FileNotFoundException(location.toString());
      }
   }

   @Override
   public Set getSet() {
      return this.set;
   }

   @Override
   public IResource resolveIResourceByResourceLocation(ResourceLocation location) throws IOException {
      IResourceManager iResourceManager = (IResourceManager)this.map.get(location.getText());
      if (iResourceManager != null) {
         return iResourceManager.resolveIResourceByResourceLocation(location);
      } else {
         throw new FileNotFoundException(location.toString());
      }
   }

   private void updateState() {
      this.map.clear();
      this.set.clear();
   }

   public ReloadingResourceManagerHolder(InvalidMetadataForManager invalidMetadataForManager2) {
      this.invalidMetadataForManager = invalidMetadataForManager2;
   }
}
