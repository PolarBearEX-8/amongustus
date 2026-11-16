package client.util;

import net.minecraft.client.renderer.RendererLivingEntity;

public class ClientManagerDispatcher extends GeometryFilter {
   public final InternalResourceLocationEntryBridge internalResourceLocationEntryBridge;

   @Override
   public void updateState() {
      this.passiveFloatEntry2 = new AimMonitor(0.5F, true);
      this.passiveFloatEntry = new AimMonitor(1.0F, true);
   }

   public ClientManagerDispatcher(InternalResourceLocationEntryBridge internalResourceLocationEntryBridge2, RendererLivingEntity rendererLivingEntity) {
      super(rendererLivingEntity);
      this.internalResourceLocationEntryBridge = internalResourceLocationEntryBridge2;
   }
}
