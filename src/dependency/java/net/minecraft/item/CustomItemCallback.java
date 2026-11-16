package net.minecraft.item;

import client.event.PreRenderListener;
import client.util.AimDeserializer;

public interface CustomItemCallback {
   CustomItemCallback customItemCallback = new AimDeserializer();

   ItemStack resolveItemStack(PreRenderListener preRenderListener, ItemStack stack);
}
