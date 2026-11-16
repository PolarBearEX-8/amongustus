package cc.baritone.utils;

import client.util.AimObserver;
import java.util.stream.Stream;

public interface GenericListener {
   boolean matchesText(String text);

   boolean matchesAimObserver(AimObserver aimObserver);

   Stream resolveStream(String text);

   DequeEntry getDequeEntry();

   DynamicListener resolveDynamicListener(String text);

   ActiveListener getActiveListener();

   Stream resolveStream(AimObserver aimObserver);
}
