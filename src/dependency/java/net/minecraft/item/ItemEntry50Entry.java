package net.minecraft.item;

import client.render.WorldRenderer;
import client.util.BuildManager;
import client.util.DampenerService;

public class ItemEntry50Entry extends DampenerService {
   public DiamondChestplate diamondChestplate = null;

   public void setDiamondChestplate(DiamondChestplate diamondChestplate2) {
      this.diamondChestplate = diamondChestplate2;
   }

   @Override
   public void applyWorldRenderer(WorldRenderer worldRenderer) {
      if (worldRenderer.getCountAsIndex() == 7 && BuildManager.isActive78()) {
         worldRenderer.renderElement2();
         this.diamondChestplate.setCount2(worldRenderer.getCountAsIndex());
      }

      this.diamondChestplate.applyByteBuffer(worldRenderer.getByteBuffer());
      worldRenderer.renderElement5();
   }
}
