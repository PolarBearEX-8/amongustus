package baritone.command.defaults;

import baritone.KeepName;
import java.util.function.Predicate;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

@KeepName
public enum FollowGroup {
   ENTITIES(EntityLiving.class::isInstance),
   PLAYERS(EntityPlayer.class::isInstance);

public final Predicate filter;
private FollowGroup(Predicate predicate) {
      this.filter = predicate;
   }
}
