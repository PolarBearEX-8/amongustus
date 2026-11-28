package baritone.api.cache;

import baritone.api.utils.BetterBlockPos;

public interface IWaypoint {
   long getCreationTimestamp();

   String getName();

   WaypointTag getTag();

   BetterBlockPos getLocation();
}
