package client.util;

import client.event.PreUpdateObserver;

public interface AimValidator extends PreUpdateObserver {
   float getFactor();
}
