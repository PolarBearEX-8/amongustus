package client.util;

import net.minecraft.client.renderer.RendererLivingEntity;

public class GeometryFilter extends AdvancedResourceLocationEntry {
   public void applyFloatEntryRegistry(FloatEntryRegistry floatEntryRegistry, int count) {
      this.applyFloatEntryRegistry(floatEntryRegistry);
      switch (count) {
         case 1:
            floatEntryRegistry.boxesManager5.enabled3 = true;
            floatEntryRegistry.boxesManager6.enabled3 = true;
            break;
         case 2:
            floatEntryRegistry.boxesManager7.enabled3 = true;
            floatEntryRegistry.boxesManager5.enabled3 = true;
            floatEntryRegistry.boxesManager6.enabled3 = true;
            break;
         case 3:
            floatEntryRegistry.boxesManager7.enabled3 = true;
            floatEntryRegistry.boxesManager2.enabled3 = true;
            floatEntryRegistry.boxesManager.enabled3 = true;
            break;
         case 4:
            floatEntryRegistry.boxesManager3.enabled3 = true;
            floatEntryRegistry.boxesManager4.enabled3 = true;
      }
   }

   @Override
   public void applyPassiveFloatEntry(PassiveFloatEntry passiveFloatEntry, int count) {
      this.applyFloatEntryRegistry((FloatEntryRegistry)passiveFloatEntry, count);
   }

   @Override
   public void updateState() {
      this.passiveFloatEntry2 = new FloatEntryRegistry(0.5F);
      this.passiveFloatEntry = new FloatEntryRegistry(1.0F);
   }

   public void applyFloatEntryRegistry(FloatEntryRegistry floatEntryRegistry) {
      floatEntryRegistry.setEnabled(false);
   }

   public GeometryFilter(RendererLivingEntity rendererLivingEntity) {
      super(rendererLivingEntity);
   }
}
