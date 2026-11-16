package client.util;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.item.DiamondWeapon;

public class BottomClassEntryAdapter extends ModelMinecart {
   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      SmoothMonitor smoothMonitor = new SmoothMonitor(renderManager);
      smoothMonitor.passiveFloatEntry = passiveFloatEntry;
      smoothMonitor.factor = factor;
      return smoothMonitor;
   }

   public BottomClassEntryAdapter() {
      super(DiamondWeapon.class, "spawner_minecart", 0.5F);
   }
}
