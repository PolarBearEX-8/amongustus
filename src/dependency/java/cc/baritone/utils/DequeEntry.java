package cc.baritone.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DequeEntry {
   public final Deque deque = new LinkedList();
   public final Set set = new HashSet();
   public final Collection collection = Collections.unmodifiableCollection(this.deque);

   public Iterator descendingIterator() {
      return this.deque.descendingIterator();
   }

   public Iterator iterator() {
      return this.deque.iterator();
   }

   public void applyTarget(Object entry) {
      if (this.contains(entry)) {
         this.deque.remove(entry);
         this.set.remove(entry);
      }
   }

   public Stream stream() {
      return this.deque.stream();
   }

   public boolean contains(Object entry) {
      return this.set.contains(entry);
   }

   public Stream getStream() {
      return StreamSupport.stream(Spliterators.spliterator(this.descendingIterator(), (long)this.deque.size(), 16448), false);
   }

   public boolean matchesTarget(Object entry) {
      if (!this.contains(entry)) {
         this.deque.addFirst(entry);
         this.set.add(entry);
         return true;
      } else {
         return false;
      }
   }
}
