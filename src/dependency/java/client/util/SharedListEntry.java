package client.util;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.model.ModelClientManager;
import net.minecraft.client.resources.model.IBakedModel;

public class SharedListEntry {
   public List list = Lists.newArrayList();

   public InternalListenerDispatcher sort() {
      Collections.sort(this.list);
      return new InternalListenerDispatcher(this.list);
   }

   public SharedListEntry add(IBakedModel iBakedModel, int count) {
      this.list.add(new ModelClientManager(iBakedModel, count));
      return this;
   }

   public IBakedModel get() {
      return ((ModelClientManager)this.list.get(0)).iBakedModel;
   }
}
