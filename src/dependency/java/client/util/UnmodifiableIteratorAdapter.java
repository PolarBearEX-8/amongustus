package client.util;

import com.google.common.collect.UnmodifiableIterator;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnmodifiableIteratorAdapter extends UnmodifiableIterator {
   public int count = -2;
   public final Iterable[] iterableArray;
   public final Iterator[] iteratorArray;
   public final Object[] objectArray;

   private void updateState() {
      this.count = -1;
      Arrays.fill(this.iteratorArray, null);
      Arrays.fill(this.objectArray, null);
   }

   public Object[] getTargetArray() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         while (this.count < this.iteratorArray.length) {
            this.objectArray[this.count] = this.iteratorArray[this.count].next();
            this.count++;
         }

         return (Object[])this.objectArray.clone();
      }
   }

   public UnmodifiableIteratorAdapter(Class clazz, Iterable[] collectionArray, RotationParser rotationParser) {
      this(clazz, collectionArray);
   }

   private UnmodifiableIteratorAdapter(Class clazz, Iterable[] collectionArray) {
      this.iterableArray = collectionArray;
      this.iteratorArray = (Iterator[])SmoothFilter.resolveTargetArray(Iterator.class, this.iterableArray.length);

      for (int index = 0; index < this.iterableArray.length; index++) {
         this.iteratorArray[index] = collectionArray[index].iterator();
      }

      this.objectArray = SmoothFilter.resolveTargetArray(clazz, this.iteratorArray.length);
   }

   public boolean hasNext() {
      if (this.count == -2) {
         this.count = 0;
         Iterator[] iteratorArray2 = this.iteratorArray;
         int length = iteratorArray2.length;

         for (int index = 0; index < length; index++) {
            if (!iteratorArray2[index].hasNext()) {
               this.updateState();
               break;
            }
         }

         return true;
      } else {
         if (this.count >= this.iteratorArray.length) {
            for (this.count = this.iteratorArray.length - 1; this.count >= 0 && !this.iteratorArray[this.count].hasNext(); this.count--) {
               if (this.count == 0) {
                  this.updateState();
                  break;
               }

               Iterator iterator = this.iterableArray[this.count].iterator();
               this.iteratorArray[this.count] = iterator;
               if (!iterator.hasNext()) {
                  this.updateState();
                  break;
               }
            }
         }

         return this.count >= 0;
      }
   }

   public Object next() {
      return this.getTargetArray();
   }
}
