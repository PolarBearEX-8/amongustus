package cc.baritone.utils;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

public enum DiagonalNavigator implements AdvancedListener {
    INSTANCE;

   public final DequeEntry dequeEntry = new DequeEntry();
   public static final DiagonalNavigator[] ENUM_VALUES = new DiagonalNavigator[]{INSTANCE};

   @Override
   public DequeEntry getDequeEntry() {
      return this.dequeEntry;
   }



   @Override
   public Optional resolveOptional(File file) {
      return this.dequeEntry.stream().filter(strategy -> matchesFile(file, (HeuristicStrategy)strategy)).findFirst();
   }

   private DiagonalNavigator() {
      Arrays.stream(SchemSPONGEEnum.values()).forEach(this.dequeEntry::matchesTarget);
   }

   private static boolean matchesFile(File file, HeuristicStrategy heuristicStrategy) {
      return heuristicStrategy.matchesFile(file);
   }
}
