package net.minecraft.item;

import client.util.AuxiliaryIterableExtension;
import client.util.CommonMapEntry;
import client.util.JsonManager;
import client.util.ListenerAdapter27;
import client.util.ResourceLocationAdapter;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.model.IBakedModel;

public class IronDust implements IResourceManagerReloadListener {
   public AuxiliaryIterableExtension auxiliaryIterableExtension;
   public final ListenerAdapter27 listenerAdapter27;
   public final CommonMapEntry commonMapEntry;
   public IBakedModel iBakedModel;

   public IBakedModel resolveIBakedModel(ResourceLocationAdapter resourceLocationAdapter) {
      if (resourceLocationAdapter == null) {
         return this.iBakedModel;
      } else {
         IBakedModel object = (IBakedModel)this.auxiliaryIterableExtension.getObject(resourceLocationAdapter);
         return object == null ? this.iBakedModel : object;
      }
   }

   public ListenerAdapter27 getListenerAdapter27() {
      return this.listenerAdapter27;
   }

   public CommonMapEntry getCommonMapEntry() {
      return this.commonMapEntry;
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) {
      JsonManager jsonManager = new JsonManager(iResourceManager, this.listenerAdapter27, this.commonMapEntry);
      this.auxiliaryIterableExtension = jsonManager.getAuxiliaryIterableExtension();
      this.iBakedModel = (IBakedModel)this.auxiliaryIterableExtension.getObject(JsonManager.resourceLocationAdapter);
      this.commonMapEntry.updateState2();
   }

   public IBakedModel getIBakedModel() {
      return this.iBakedModel;
   }

   public IronDust(ListenerAdapter27 listenerAdapter) {
      this.listenerAdapter27 = listenerAdapter;
      this.commonMapEntry = new CommonMapEntry(this);
   }
}
