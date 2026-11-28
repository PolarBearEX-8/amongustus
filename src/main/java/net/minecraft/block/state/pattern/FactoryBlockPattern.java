package net.minecraft.block.state.pattern;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class FactoryBlockPattern {
   private static final Joiner COMMA_JOIN = Joiner.on(",");
   private final List depth = Lists.newArrayList();
   private final Map symbolMap = Maps.newHashMap();
   private int aisleHeight;
   private int rowWidth;

   public BlockPattern build() {
      return new BlockPattern(this.makePredicateArray());
   }

   public FactoryBlockPattern where(char ch, Predicate predicate) {
      this.symbolMap.put(ch, predicate);
      return this;
   }

   private void checkMissingPredicates() {
      ArrayList list = Lists.newArrayList();

      for (Entry entry : (Iterable<Entry>)(this.symbolMap.entrySet())) {
         if (entry.getValue() == null) {
            list.add(entry.getKey());
         }
      }

      if (!list.isEmpty()) {
         throw new IllegalStateException("Predicates for character(s) " + COMMA_JOIN.join(list) + " are missing");
      }
   }

   public static FactoryBlockPattern start() {
      return new FactoryBlockPattern();
   }

   private Predicate[][][] makePredicateArray() {
      this.checkMissingPredicates();
      Predicate[][][] predicatesArrs = (Predicate[][][])Array.newInstance(Predicate.class, this.depth.size(), this.aisleHeight, this.rowWidth);

      for (int i = 0; i < this.depth.size(); i++) {
         for (int j = 0; j < this.aisleHeight; j++) {
            for (int k = 0; k < this.rowWidth; k++) {
               predicatesArrs[i][j][k] = (Predicate)this.symbolMap.get(((String[])this.depth.get(i))[j].charAt(k));
            }
         }
      }

      return predicatesArrs;
   }

   private FactoryBlockPattern() {
      this.symbolMap.put(' ', Predicates.alwaysTrue());
   }

   public FactoryBlockPattern aisle(String[] strings) {
      if (!ArrayUtils.isEmpty(strings) && !StringUtils.isEmpty(strings[0])) {
         if (this.depth.isEmpty()) {
            this.aisleHeight = strings.length;
            this.rowWidth = strings[0].length();
         }

         if (strings.length != this.aisleHeight) {
            throw new IllegalArgumentException(
               "Expected aisle with height of " + this.aisleHeight + ", but was given one with a height of " + strings.length + ")"
            );
         } else {
            for (String str : strings) {
               if (str.length() != this.rowWidth) {
                  throw new IllegalArgumentException(
                     "Not all rows in the given aisle are the correct width (expected " + this.rowWidth + ", found one with " + str.length() + ")"
                  );
               }

               for (char ch : str.toCharArray()) {
                  if (!this.symbolMap.containsKey(ch)) {
                     this.symbolMap.put(ch, (Predicate)null);
                  }
               }
            }

            this.depth.add(strings);
            return this;
         }
      } else {
         throw new IllegalArgumentException("Empty pattern for aisle");
      }
   }
}
