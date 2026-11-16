package client.util;

public class DuplicateStatIdManagerEntry {
   public final int count2;
   public final int count3;
   public final int count;
   public final DuplicateStatIdManager duplicateStatIdManager2;
   public final DuplicateStatIdManager duplicateStatIdManager;

   public DuplicateStatIdManagerEntry(int count4, int count5, int count6) {
      this.count2 = count4;
      this.count3 = count5;
      this.count = count6;
      this.duplicateStatIdManager2 = StatCraftItemManager.resolveDuplicateStatIdManagerByDuplicateStatIdM(this);
      this.duplicateStatIdManager = StatCraftItemManager.resolveDuplicateStatIdManager(this);
   }
}
