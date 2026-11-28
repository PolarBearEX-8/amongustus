package baritone.api.cache;

import baritone.api.utils.BetterBlockPos;
import java.util.Date;
import net.minecraft.util.BlockPos;

public class Waypoint implements IWaypoint {
   private final String name;
   private final WaypointTag tag;
   private final long timestamp;
   private final BetterBlockPos location;

   @Override
   public int hashCode() {
      return this.name.hashCode() ^ this.tag.hashCode() ^ this.location.hashCode() ^ Long.hashCode(this.timestamp);
   }

   @Override
   public WaypointTag getTag() {
      return this.tag;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (!(obj instanceof IWaypoint)) {
         return false;
      } else {
         IWaypoint waypoint = (IWaypoint)obj;
         return this.name.equals(waypoint.getName()) && this.tag == waypoint.getTag() && this.location.equals(waypoint.getLocation());
      }
   }

   public Waypoint(String str, WaypointTag waypointTag, BetterBlockPos betterBlockPos, long l) {
      this.name = str;
      this.tag = waypointTag;
      this.location = betterBlockPos;
      this.timestamp = l;
   }

   @Override
   public String toString() {
      return String.format("%s %s %s", this.name, BetterBlockPos.from((BlockPos)this.location).toString(), new Date(this.timestamp).toString());
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public BetterBlockPos getLocation() {
      return this.location;
   }

   @Override
   public long getCreationTimestamp() {
      return this.timestamp;
   }

   public Waypoint(String str, WaypointTag waypointTag, BetterBlockPos betterBlockPos) {
      this(str, waypointTag, betterBlockPos, System.currentTimeMillis());
   }
}
