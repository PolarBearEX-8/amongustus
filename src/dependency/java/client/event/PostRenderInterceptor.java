package client.event;

import client.util.CancelledManager;
import client.util.ServersManager;
import net.minecraft.client.gui.GuiScreen;

public interface PostRenderInterceptor {
   default ServersManager getServersManager() {
      return null;
   }

   default void applyIndex(int count) {
   }

   default void applyCanTResolveHostnameListene(CanTResolveHostnameListener canTResolveHostnameListener, int count, boolean flag) {
   }

   void applyGuiScreen(GuiScreen screen, int count, int count2);

   default boolean matchesCanTResolveHostnameListene(CanTResolveHostnameListener canTResolveHostnameListener, int count) {
      return false;
   }

   default boolean matchesCanTResolveHostnameListeneByCanTResolveHostn(CanTResolveHostnameListener canTResolveHostnameListener, int count) {
      return false;
   }

   void setLabelText(String labelText);

   default void applyCanTResolveHostnameListeneByCanTResolveHostn(CanTResolveHostnameListener canTResolveHostnameListener, int count, boolean flag) {
   }

   void applyFlag(boolean flag, int count);

   CancelledManager getCancelledManager();

   default void renderElement() {
   }
}
