package client.util;

import client.event.PreRenderListener;
import net.minecraft.item.CustomItemCallback;
import net.minecraft.item.ItemStack;

public final class AimDeserializer implements CustomItemCallback {
   @Override
   public ItemStack resolveItemStack(PreRenderListener preRenderListener, ItemStack stack) {
      return stack;
   }
}
