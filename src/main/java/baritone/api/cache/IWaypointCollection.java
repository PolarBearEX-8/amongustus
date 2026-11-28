package baritone.api.cache;

import java.util.Set;

public interface IWaypointCollection {
   void removeWaypoint(IWaypoint waypoint);

   Set getByTag(WaypointTag waypointTag);

   void addWaypoint(IWaypoint waypoint);

   IWaypoint getMostRecentByTag(WaypointTag waypointTag);

   Set getAllWaypoints();
}
