package net.minecraft.item;

import client.event.PreRenderConsumer;
import client.util.HEADManager;

public class DiamondRelic {
   public HEADManager hEADManager = null;
   public PreRenderConsumer preRenderConsumer = null;
   public boolean enabled = false;

   public HEADManager getHEADManager() {
      return this.hEADManager;
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public PreRenderConsumer getPreRenderConsumer() {
      return this.preRenderConsumer;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public DiamondRelic(HEADManager hEADManager2, PreRenderConsumer preRenderConsumer2) {
      this.hEADManager = hEADManager2;
      this.preRenderConsumer = preRenderConsumer2;
   }
}
