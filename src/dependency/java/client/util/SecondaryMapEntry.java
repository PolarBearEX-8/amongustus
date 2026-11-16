package client.util;

import client.event.PostRenderProcessor;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.IronDust;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class SecondaryMapEntry {
   public final Map map = Maps.newHashMap();
   public final Map map2 = Maps.newHashMap();
   public final Map map3 = Maps.newHashMap();
   public final IronDust ironDust;

   private int computeCount(Item item, int count) {
      return Item.getIndex(item) << 16 | count;
   }

   public ServiceStringEntry resolveServiceStringEntry(Item item, int count) {
      return this.resolveIBakedModel(new ItemStack(item, 1, count)).getServiceStringEntry();
   }

   public IronDust getIronDust() {
      return this.ironDust;
   }

   public void applyItem(Item item, int count, ResourceLocationAdapter resourceLocationAdapter) {
      this.map.put(this.computeCount(item, count), resourceLocationAdapter);
      this.map2.put(this.computeCount(item, count), this.ironDust.resolveIBakedModel(resourceLocationAdapter));
   }

   public IBakedModel resolveIBakedModel(ItemStack stack) {
      Item item = stack.getItem();
      IBakedModel iBakedModel = this.resolveIBakedModel(item, this.computeCount(stack));
      if (iBakedModel == null) {
         PostRenderProcessor postRenderProcessor = (PostRenderProcessor)this.map3.get(item);
         if (postRenderProcessor != null) {
            iBakedModel = this.ironDust.resolveIBakedModel(postRenderProcessor.resolveResourceLocationAdapter(stack));
         }
      }

      if (iBakedModel == null) {
         iBakedModel = this.ironDust.getIBakedModel();
      }

      if (BuildManager.isActive35()) {
         iBakedModel = InvalidItemIDManager.resolveIBakedModel(stack, iBakedModel, (ResourceLocation)null, true);
      }

      return iBakedModel;
   }

   public IBakedModel resolveIBakedModel(Item item, int count) {
      return (IBakedModel)this.map2.get(this.computeCount(item, count));
   }

   public ServiceStringEntry resolveServiceStringEntry(Item item) {
      return this.resolveServiceStringEntry(item, 0);
   }

   public int computeCount(ItemStack stack) {
      return stack.isActive3() ? 0 : stack.getIndexOffset();
   }

   public void updateState() {
      this.map2.clear();

      for (Entry entry : (Iterable<Entry>)(Iterable)this.map.entrySet()) {
         this.map2.put(entry.getKey(), this.ironDust.resolveIBakedModel((ResourceLocationAdapter)entry.getValue()));
      }
   }

   public void put(Item item, PostRenderProcessor postRenderProcessor) {
      this.map3.put(item, postRenderProcessor);
   }

   public SecondaryMapEntry(IronDust ironDust2) {
      this.ironDust = ironDust2;
   }
}
