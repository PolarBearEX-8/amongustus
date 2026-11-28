package baritone.command.defaults;

import baritone.KeepName;
import baritone.api.command.datatypes.EntityClassById;
import baritone.api.command.datatypes.IDatatypeFor;
import baritone.api.command.datatypes.NearbyPlayer;

@KeepName
public enum FollowList {
   ENTITY(EntityClassById.INSTANCE),
   PLAYER(NearbyPlayer.INSTANCE);

public final IDatatypeFor datatype;
private FollowList(IDatatypeFor datatypeFor) {
      this.datatype = datatypeFor;
   }
}
