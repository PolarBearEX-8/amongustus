package baritone.api.command.datatypes;

import baritone.api.IBaritone;
import baritone.api.cache.IWaypoint;
import baritone.api.cache.IWaypointCollection;
import baritone.api.command.exception.CommandException;
import baritone.api.command.helpers.TabCompleteHelper;
import baritone.api.cache.WaypointTag;
import java.util.Comparator;
import java.util.stream.Stream;

public enum ForWaypoints implements IDatatypeFor {
   INSTANCE;
   @Override
   public Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException {
      return new TabCompleteHelper().append2(getWaypointNames(datatypeContext.getBaritone())).sortAlphabetically().prepend3(WaypointTag.getAllNames()).filterPrefix(datatypeContext.getConsumer().getString()).getStream();
   }

   public static IWaypoint[] getWaypointsByTag(IBaritone baritone, WaypointTag waypointTag) {
      return (IWaypoint[])waypoints(baritone).getByTag(waypointTag).stream().sorted(Comparator.comparingLong(IWaypoint::getCreationTimestamp).reversed()).toArray(IWaypoint[]::new);
   }

public static String[] getWaypointNames(IBaritone baritone) {
      return Stream.of(getWaypoints(baritone)).map(IWaypoint::getName).filter(str -> !str.isEmpty()).toArray(String[]::new);
   }

   public static IWaypointCollection waypoints(IBaritone baritone) {
      return baritone.getWorldProvider().getCurrentWorld().getWaypoints();
   }

   @Override
   public Object get(IDatatypeContext datatypeContext) throws CommandException {
      return this.get2(datatypeContext);
   }

   public static IWaypoint[] getWaypointsByName(IBaritone baritone, String str) {
      return Stream.of(getWaypoints(baritone)).filter(waypoint -> waypoint.getName().equalsIgnoreCase(str)).toArray(IWaypoint[]::new);
   }

   public IWaypoint[] get2(IDatatypeContext datatypeContext) throws CommandException {
      String string = datatypeContext.getConsumer().getString();
      WaypointTag byName = WaypointTag.getByName(string);
      return byName == null ? getWaypointsByName(datatypeContext.getBaritone(), string) : getWaypointsByTag(datatypeContext.getBaritone(), byName);
   }

   public static IWaypoint[] getWaypoints(IBaritone baritone) {
      return (IWaypoint[])waypoints(baritone).getAllWaypoints().stream().sorted(Comparator.comparingLong(IWaypoint::getCreationTimestamp).reversed()).toArray(IWaypoint[]::new);
   }

}
