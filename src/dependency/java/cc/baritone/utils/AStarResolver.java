package cc.baritone.utils;

public final class AStarResolver {
   public final SecondaryOptionType secondaryOptionType;
   public final BreadthFirstService breadthFirstService;
   public final int count;
   public final int count2;

   public AStarResolver(SecondaryOptionType secondaryOptionType2, BreadthFirstService breadthFirstService2, int count3, int count4) {
      this.secondaryOptionType = secondaryOptionType2;
      this.breadthFirstService = breadthFirstService2;
      this.count = count3;
      this.count2 = count4;
   }

   public int getCount2() {
      return this.count2;
   }

   public int getCount() {
      return this.count;
   }

   public SecondaryOptionType getSecondaryOptionType() {
      return this.secondaryOptionType;
   }

   public BreadthFirstService getBreadthFirstService() {
      return this.breadthFirstService;
   }

   public boolean isActive() {
      return this.secondaryOptionType == SecondaryOptionType.POST && this.breadthFirstService.isActive();
   }
}
