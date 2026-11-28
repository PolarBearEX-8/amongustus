package net.augustus.cosmetics;

import net.augustus.cosmetics.model.CosmeticModelLoader;
import net.augustus.cosmetics.render.AbstractCosmeticRenderer;
import java.util.UUID;

public interface MinecraftBridge extends CosmeticTickListener {
   void bindTexture(int n);

   String getUsername();

   boolean joinServer(String str);

   int getScrollDelta();

   boolean isKeyDown(int n);

   UUID getPlayerUuid(boolean flag);

   AbstractCosmeticRenderer createCosmeticRenderer();

   CosmeticModelLoader createModelLoader();
}
