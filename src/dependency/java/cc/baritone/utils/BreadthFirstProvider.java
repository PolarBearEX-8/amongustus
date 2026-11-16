package cc.baritone.utils;

import java.util.List;

public final class BreadthFirstProvider {
   public final AStarVerifier aStarVerifier;
   public final List list;

   public AStarVerifier getAStarVerifier() {
      return this.aStarVerifier;
   }

   public BreadthFirstProvider(AStarVerifier aStarVerifier2, List list2) {
      this.aStarVerifier = aStarVerifier2;
      this.list = list2;
   }

   public List getList() {
      return this.list;
   }
}
