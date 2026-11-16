package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.ExtraResourceLocationEntryAdapter;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.StandardItemArrayEntry;

public class ModelMinecart extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"bottom", "back", "front", "right", "left", "dirt"};
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new StandardItemArrayEntry();
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      ExtraResourceLocationEntryAdapter extraResourceLocationEntryAdapter = new ExtraResourceLocationEntryAdapter(renderManager);
      extraResourceLocationEntryAdapter.passiveFloatEntry = passiveFloatEntry;
      extraResourceLocationEntryAdapter.factor = factor;
      return extraResourceLocationEntryAdapter;
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof StandardItemArrayEntry)) {
         return null;
      } else {
         StandardItemArrayEntry standardItemArrayEntry = (StandardItemArrayEntry)passiveFloatEntry;
         return text.equals("bottom")
            ? standardItemArrayEntry.boxesManagerArray[0]
            : (
               text.equals("back")
                  ? standardItemArrayEntry.boxesManagerArray[1]
                  : (
                     text.equals("front")
                        ? standardItemArrayEntry.boxesManagerArray[2]
                        : (
                           text.equals("right")
                              ? standardItemArrayEntry.boxesManagerArray[3]
                              : (text.equals("left") ? standardItemArrayEntry.boxesManagerArray[4] : (text.equals("dirt") ? standardItemArrayEntry.boxesManagerArray[5] : null))
                        )
                  )
            );
      }
   }

   public ModelMinecart(Class clazz, String text, float factor) {
      super(clazz, text, factor);
   }

   public ModelMinecart() {
      super(EntityMinecart.class, "minecart", 0.5F);
   }
}
