package client.util;

import net.minecraft.client.renderer.RendererLivingEntity;

public class AuxiliaryClientManagerBridge extends GeometryFilter {
   public final AuxiliaryResourceLocationEntryBridge auxiliaryResourceLocationEntryBridge;

   @Override
   public void updateState() {
      this.passiveFloatEntry2 = new InternalFloatEntryBridge(0.5F, true);
      this.passiveFloatEntry = new InternalFloatEntryBridge(1.0F, true);
   }

   public AuxiliaryClientManagerBridge(AuxiliaryResourceLocationEntryBridge auxiliaryResourceLocationEntryBridge2, RendererLivingEntity rendererLivingEntity) {
      super(rendererLivingEntity);
      this.auxiliaryResourceLocationEntryBridge = auxiliaryResourceLocationEntryBridge2;
   }
}
