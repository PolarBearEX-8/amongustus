package client.util;

import client.event.PreUpdateObserver;

public interface TargetObserver extends PreUpdateObserver {
   boolean isActive();
}
