package baritone.cache;

import baritone.api.cache.IWaypoint;
import baritone.api.cache.IWaypointCollection;
import baritone.api.cache.Waypoint;
import baritone.api.utils.BetterBlockPos;
import baritone.api.cache.WaypointTag;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WaypointCollection implements IWaypointCollection {
   private static final long MAGIC = 121977993584L;
   private final Path directory;
   private final Map byTag;

   @Override
   public IWaypoint getMostRecentByTag(WaypointTag waypointTag) {
      return (IWaypoint)(((Set)this.byTag.get(waypointTag)).stream().min(Comparator.comparingLong(obj -> -((IWaypoint)obj).getCreationTimestamp())).orElse(null));
   }

   private void loadAll() {
      for (WaypointTag waypointTag : WaypointTag.values()) {
         this.load(waypointTag);
      }
   }

   public WaypointCollection(Path path) {
      this.directory = path;
      if (!Files.exists(path)) {
         try {
            Files.createDirectories(path);
         } catch (IOException e) {
         }
      }

      System.out.println("Would save waypoints to " + path);
      this.byTag = new HashMap();
      this.loadAll();
   }

   @Override
   public void addWaypoint(IWaypoint waypoint) {
      if (((Set)this.byTag.get(waypoint.getTag())).add(waypoint)) {
         this.save(waypoint.getTag());
      }
   }

   private synchronized void load(WaypointTag waypointTag) {
      this.byTag.put(waypointTag, new HashSet());
      Path path = this.directory.resolve(waypointTag.name().toLowerCase() + ".mp4");
      if (Files.exists(path)) {
         try (
            FileInputStream fileInputStream = new FileInputStream(path.toFile());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            DataInputStream in = new DataInputStream(bufferedInputStream);
         ) {
            long l = in.readLong();
            if (l != 121977993584L) {
               throw new IOException("Bad magic value " + l);
            }

            long l2 = in.readLong();

            while (l2-- > 0L) {
               String str = in.readUTF();
               long l3 = in.readLong();
               int n = in.readInt();
               int n2 = in.readInt();
               int n3 = in.readInt();
               ((Set)this.byTag.get(waypointTag)).add(new Waypoint(str, waypointTag, new BetterBlockPos(n, n2, n3), l3));
            }
         } catch (IOException e) {
         }
      }
   }

   @Override
   public Set getByTag(WaypointTag waypointTag) {
      return Collections.unmodifiableSet((Set)this.byTag.get(waypointTag));
   }

   @Override
   public Set getAllWaypoints() {
      return (Set)this.byTag.values().stream().flatMap(obj -> ((Collection)obj).stream()).collect(Collectors.toSet());
   }

   private synchronized void save(WaypointTag waypointTag) {
      Path path = this.directory.resolve(waypointTag.name().toLowerCase() + ".mp4");

      try (
         FileOutputStream fileOutputStream = new FileOutputStream(path.toFile());
         BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
         DataOutputStream out = new DataOutputStream(bufferedOutputStream);
      ) {
         out.writeLong(121977993584L);
         out.writeLong((long)((Set)this.byTag.get(waypointTag)).size());

         for (IWaypoint waypoint : (Iterable<IWaypoint>)((Set)this.byTag.get(waypointTag))) {
            out.writeUTF(waypoint.getName());
            out.writeLong(waypoint.getCreationTimestamp());
            out.writeInt(waypoint.getLocation().getX());
            out.writeInt(waypoint.getLocation().getY());
            out.writeInt(waypoint.getLocation().getZ());
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   @Override
   public void removeWaypoint(IWaypoint waypoint) {
      if (((Set)this.byTag.get(waypoint.getTag())).remove(waypoint)) {
         this.save(waypoint.getTag());
      }
   }

}
