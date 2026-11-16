package net.minecraft.client.model;

import client.event.PreMotionDispatcher;
import client.event.PreRenderDispatcher;
import client.render.EntitySkinLocation;
import client.render.RenderManager;
import client.render.VertexArrayRenderer;
import client.util.AuxiliaryHeadClassEntry;
import client.util.BuildManager;
import client.util.CustomListenerHandler;
import client.util.PassiveFloatEntry;
import client.util.SupplementalFloatEntry;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntitySheep;
import net.minecraft.item.ItemEntryHandler;

public class ModelSheepWool extends AuxiliaryHeadClassEntry {
   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      Object entry = (EntitySkinLocation)renderManager.getEntryMap().get(EntitySheep.class);
      if (!(entry instanceof VertexArrayRenderer)) {
         BuildManager.applyText3("Not a RenderSheep: " + entry);
         return null;
      } else {
         if (((EntitySkinLocation)entry).getMemberRef() == null) {
            entry = new VertexArrayRenderer(renderManager, new SupplementalFloatEntry(), 0.7F);
         }

         VertexArrayRenderer vertexArrayRenderer = (VertexArrayRenderer)entry;
         Iterator iterator = vertexArrayRenderer.getList().iterator();

         while (iterator.hasNext()) {
            if ((PreMotionDispatcher)iterator.next() instanceof CustomListenerHandler) {
               iterator.remove();
            }
         }

         CustomListenerHandler customListenerHandler = new CustomListenerHandler(vertexArrayRenderer);
         customListenerHandler.itemEntryHandler = (ItemEntryHandler)passiveFloatEntry;
         vertexArrayRenderer.add(customListenerHandler);
         return vertexArrayRenderer;
      }
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new ItemEntryHandler();
   }

   public ModelSheepWool() {
      super(EntitySheep.class, "sheep_wool", 0.7F);
   }
}
