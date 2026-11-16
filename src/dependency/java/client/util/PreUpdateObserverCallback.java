package client.util;

import client.event.PreUpdateObserver;

public interface PreUpdateObserverCallback {
   PreUpdateObserver resolvePreUpdateObserver(String text);
}
