package cc.baritone.utils;

import java.util.function.Predicate;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

@Annotation
public enum BreadthFirstResolver {
    ENTITIES(EntityMob.class::isInstance),
    PLAYERS(EntityPlayer.class::isInstance);

   public final Predicate predicate;
   public static final BreadthFirstResolver[] ENUM_VALUES = new BreadthFirstResolver[]{ENTITIES, PLAYERS};

   private BreadthFirstResolver(Predicate predicate2) {
      this.predicate = predicate2;
   }
}
