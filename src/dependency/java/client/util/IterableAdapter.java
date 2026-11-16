package client.util;

import com.google.common.collect.Iterators;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class IterableAdapter implements Iterable {
   public final Class memberRef;
   public final DonTKnowHowToSearchForAbstractSet donTKnowHowToSearchForAbstractSet;

   public IterableAdapter(DonTKnowHowToSearchForAbstractSet donTKnowHowToSearchForAbstractSet2, Class clazz) {
      this.donTKnowHowToSearchForAbstractSet = donTKnowHowToSearchForAbstractSet2;
      this.memberRef = clazz;
   }

   @Override
   public Iterator iterator() {
      List list = (List)DonTKnowHowToSearchForAbstractSet.resolveMap(this.donTKnowHowToSearchForAbstractSet)
         .get(this.donTKnowHowToSearchForAbstractSet.resolveClass(this.memberRef));
      return (Iterator)(list == null ? Collections.emptyIterator() : Iterators.filter(list.iterator(), this.memberRef));
   }
}
