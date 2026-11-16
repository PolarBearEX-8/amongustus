package cc.baritone.utils;

public interface ThrowableService {
   int count = 1;
   int count2 = 2;

   boolean matchesThrowable(Throwable throwable);

   void applyIndex(int count3, int count4, int count5);

   void applyAStarService(int count3, AStarService aStarService, ExtraCustom extraCustom);

   void applyAStarService(int count3, AStarService aStarService);

   void applyAStarServiceByAStarService(int count3, AStarService aStarService);
}
