package client.util;

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

public class JoinerEntry {
   public static final Joiner joiner = Joiner.on(",");
   public final List list = Lists.newArrayList();
   public final Map map = Maps.newHashMap();
   public int count;
   public int count2;

   private JoinerEntry() {
      this.map.put(' ', Predicates.alwaysTrue());
   }

   public JoinerEntry resolveJoinerEntry(char character, Predicate predicate) {
      this.map.put(character, predicate);
      return this;
   }

   public PredicateArrayEntry getPredicateArrayEntry() {
      return new PredicateArrayEntry(this.getPredicateArray());
   }

   private void updateState() {
      ArrayList list2 = Lists.newArrayList();

      for (Entry entry : (Iterable<Entry>) (Iterable) (this.map.entrySet())) {
         if (entry.getValue() == null) {
            list2.add(entry.getKey());
         }
      }

      if (!list2.isEmpty()) {
         throw new IllegalStateException("Predicates for character(s) " + joiner.join(list2) + " are missing");
      }
   }

   private Predicate[][][] getPredicateArray() {
      this.updateState();
      Predicate[][][] predicateMatrix = (Predicate[][][])Array.newInstance(Predicate.class, this.list.size(), this.count, this.count2);

      for (int index = 0; index < this.list.size(); index++) {
         for (int index2 = 0; index2 < this.count; index2++) {
            for (int index3 = 0; index3 < this.count2; index3++) {
               predicateMatrix[index][index2][index3] = (Predicate)this.map.get(((String[])this.list.get(index))[index2].charAt(index3));
            }
         }
      }

      return predicateMatrix;
   }

   public JoinerEntry resolveJoinerEntry(String[] textArray) {
      if (!ArrayUtils.isEmpty(textArray) && !StringUtils.isEmpty(textArray[0])) {
         if (this.list.isEmpty()) {
            this.count = textArray.length;
            this.count2 = textArray[0].length();
         }

         if (textArray.length != this.count) {
            throw new IllegalArgumentException("Expected aisle with height of " + this.count + ", but was given one with a height of " + textArray.length + ")");
         } else {
            for (String text : textArray) {
               if (text.length() != this.count2) {
                  throw new IllegalArgumentException(
                     "Not all rows in the given aisle are the correct width (expected " + this.count2 + ", found one with " + text.length() + ")"
                  );
               }

               for (char character : text.toCharArray()) {
                  if (!this.map.containsKey(character)) {
                     this.map.put(character, (Predicate)null);
                  }
               }
            }

            this.list.add(textArray);
            return this;
         }
      } else {
         throw new IllegalArgumentException("Empty pattern for aisle");
      }
   }

   public static JoinerEntry getJoinerEntry() {
      return new JoinerEntry();
   }
}
