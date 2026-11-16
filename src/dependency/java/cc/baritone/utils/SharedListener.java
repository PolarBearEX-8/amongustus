package cc.baritone.utils;

import java.util.List;
import net.minecraft.block.Block;

public interface SharedListener {
   List resolveList(StaticListener staticListener, HeuristicSystem heuristicSystem, AStarVerifier aStarVerifier, int count, int count2);

   default List resolveList(StaticListener staticListener, List list, AStarVerifier aStarVerifier, int count, int count2) {
      return this.resolveList(staticListener, new HeuristicSystem(list), aStarVerifier, count, count2);
   }

   default List resolveList(StaticListener staticListener, List list, int count, int count2, int count3) {
      return this.resolveList(staticListener, new HeuristicSystem((Block[])list.toArray(new Block[0])), count, count2, count3);
   }

   List resolveList(StaticListener staticListener, HeuristicSystem heuristicSystem, int count, int count2, int count3);

   int computeCount(StaticListener staticListener, int count);

   int computeCount(StaticListener staticListener);
}
