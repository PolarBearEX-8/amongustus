package client.util;

import java.util.Collections;
import java.util.Iterator;

public class AuxiliaryIterableAdapter implements Iterable {
   public final Class memberRef;
   public final Iterable[] iterableArray;

   public AuxiliaryIterableAdapter(Class clazz, Iterable[] collectionArray, RotationParser rotationParser) {
      this(clazz, collectionArray);
   }

   private AuxiliaryIterableAdapter(Class clazz, Iterable[] collectionArray) {
      this.memberRef = clazz;
      this.iterableArray = collectionArray;
   }

   @Override
   public Iterator iterator() {
      return (Iterator)(this.iterableArray.length <= 0
         ? Collections.singletonList(SmoothFilter.resolveTargetArray(this.memberRef, 0)).iterator()
         : new UnmodifiableIteratorAdapter(this.memberRef, this.iterableArray, null));
   }
}
