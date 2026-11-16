package client.event;

import client.setting.MovementOptionProperty;
import client.util.CompositeListEntry;

@FunctionalInterface
public interface PostUpdateConsumer {
   MovementOptionProperty resolveMovementOptionProperty(CompositeListEntry compositeListEntry);
}
