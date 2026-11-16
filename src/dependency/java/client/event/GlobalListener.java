package client.event;

import client.util.ExtraHashMapEntry;
import client.util.GenericListEntry;
import client.util.ListenerAdapter23;
import client.util.ModelBone;
import net.labymod.ModelProperties;

public interface GlobalListener {
   ListenerAdapter23 resolveListenerAdapter23(ModelBone modelBone, ModelProperties modelProperties, ListenerAdapter23 listenerAdapter);

   GenericListEntry resolveGenericListEntry(ExtraHashMapEntry extraHashMapEntry);
}
