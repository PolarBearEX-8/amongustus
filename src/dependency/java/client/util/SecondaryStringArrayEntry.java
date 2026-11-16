package client.util;

import net.minecraft.item.DefaultItemEntry;

public class SecondaryStringArrayEntry {
   public static void applyDefaultItemEntry(DefaultItemEntry defaultItemEntry) {
      InternalListenerRegistry internalListenerRegistry = new InternalListenerRegistry("query.anim_time", 0.0);
      defaultItemEntry.applyInternalListenerRegistry(internalListenerRegistry);
      InternalListenerRegistry internalListenerRegistry2 = new InternalListenerRegistry("query.actor_count", 0.0);
      defaultItemEntry.applyInternalListenerRegistry(internalListenerRegistry2);
      InternalListenerRegistry internalListenerRegistry3 = new InternalListenerRegistry("query.health", 0.0);
      defaultItemEntry.applyInternalListenerRegistry(internalListenerRegistry3);
      InternalListenerRegistry internalListenerRegistry4 = new InternalListenerRegistry("query.max_health", 0.0);
      defaultItemEntry.applyInternalListenerRegistry(internalListenerRegistry4);
      InternalListenerRegistry internalListenerRegistry5 = new InternalListenerRegistry("query.distance_from_camera", 0.0);
      defaultItemEntry.applyInternalListenerRegistry(internalListenerRegistry5);
      InternalListenerRegistry internalListenerRegistry6 = new InternalListenerRegistry("query.yaw_speed", 0.0);
      defaultItemEntry.applyInternalListenerRegistry(internalListenerRegistry6);
      InternalListenerRegistry internalListenerRegistry7 = new InternalListenerRegistry("query.is_in_water_or_rain", 0.0);
      defaultItemEntry.applyInternalListenerRegistry(internalListenerRegistry7);
      InternalListenerRegistry internalListenerRegistry8 = new InternalListenerRegistry("query.is_in_water", 0.0);
      defaultItemEntry.applyInternalListenerRegistry(internalListenerRegistry8);
      InternalListenerRegistry internalListenerRegistry9 = new InternalListenerRegistry("query.is_on_ground", 0.0);
      defaultItemEntry.applyInternalListenerRegistry(internalListenerRegistry9);
      InternalListenerRegistry internalListenerRegistry10 = new InternalListenerRegistry("query.time_of_day", 0.0);
      defaultItemEntry.applyInternalListenerRegistry(internalListenerRegistry10);
      InternalListenerRegistry internalListenerRegistry11 = new InternalListenerRegistry("query.is_on_fire", 0.0);
      defaultItemEntry.applyInternalListenerRegistry(internalListenerRegistry11);
      InternalListenerRegistry internalListenerRegistry12 = new InternalListenerRegistry("query.ground_speed", 0.0);
      defaultItemEntry.applyInternalListenerRegistry(internalListenerRegistry12);
   }
}
