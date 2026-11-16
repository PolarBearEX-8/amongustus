package client.event;

import client.util.FailedToLoadMaskFromManager;
import client.util.FailedToLoadMaskFromManagerEntry;
import java.util.UUID;

public interface PostUpdateNotifier extends PreRenderObserver {
   boolean matchesText(String text);

   UUID resolveUUID(boolean flag);

   int getDWheel();

   String getText();

   FailedToLoadMaskFromManagerEntry getFailedToLoadMaskFromManagerE();

   boolean isKeyDown(int count);

   FailedToLoadMaskFromManager getFailedToLoadMaskFromManager();

   void applyIndexByIndex(int count);
}
