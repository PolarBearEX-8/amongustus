package client.util;

import net.minecraft.client.renderer.RendererLivingEntity;

public class InternalClientManagerBridge extends GeometryFilter {
   public final ResourceLocationEntryHandler resourceLocationEntryHandler;

   @Override
   public void updateState() {
      this.passiveFloatEntry2 = new InternalFloatEntryBridge(0.5F, true);
      this.passiveFloatEntry = new InternalFloatEntryBridge(1.0F, true);
   }

   public InternalClientManagerBridge(ResourceLocationEntryHandler resourceLocationEntryHandler2, RendererLivingEntity rendererLivingEntity) {
      super(rendererLivingEntity);
      this.resourceLocationEntryHandler = resourceLocationEntryHandler2;
   }
}
