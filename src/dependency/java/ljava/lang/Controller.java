package ljava.lang;

import client.util.BuildManager;

public class Controller {
   public Object[] objectArray;
   public int count = 0;

   private static boolean matchesTarget(Object entry, Object entry2) {
      return entry == entry2 ? true : (entry == null ? false : (entry2 == null ? false : entry.equals(entry2)));
   }

   @Override
   public String toString() {
      return "[" + BuildManager.resolveText(this.objectArray) + "]";
   }

   private Object[] getObjectArray() {
      return this.objectArray;
   }

   public Controller(Object entry, Object entry2, Object entry3) {
      this(new Object[]{entry, entry2, entry3});
   }

   public Object[] getTargetArray() {
      return (Object[])this.objectArray.clone();
   }

   public Controller(Object entry, Object entry2) {
      this(new Object[]{entry, entry2});
   }

   @Override
   public boolean equals(Object other) {
      if (other == null) {
         return false;
      } else if (other == this) {
         return true;
      } else if (!(other instanceof Controller)) {
         return false;
      } else {
         Object[] objectArray2 = ((Controller)other).getObjectArray();
         if (objectArray2.length != this.objectArray.length) {
            return false;
         } else {
            for (int index = 0; index < this.objectArray.length; index++) {
               if (!matchesTarget(this.objectArray[index], objectArray2[index])) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   @Override
   public int hashCode() {
      if (this.count == 0) {
         this.count = 7;

         for (int index = 0; index < this.objectArray.length; index++) {
            Object entry = this.objectArray[index];
            if (entry != null) {
               this.count = 31 * this.count + entry.hashCode();
            }
         }
      }

      return this.count;
   }

   public Controller(Object[] entryArray) {
      this.objectArray = (Object[])entryArray.clone();
   }
}
