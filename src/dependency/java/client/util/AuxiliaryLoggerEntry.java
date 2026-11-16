package client.util;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuxiliaryLoggerEntry {
   public static final Logger logger = LogManager.getLogger();
   public final List list = Lists.newArrayList();
   public final IntArrayList intArrayList = new IntArrayList();
   public int count2 = 0;
   public int count3 = -1;
   public IntArrayList intArrayList2 = new IntArrayList();
   public int count = -1;

   public boolean size(int count4) {
      return this.intArrayList2.size() - 1 >= count4;
   }

   public boolean isActive() {
      return this.count >= 0;
   }

   public int getCountAsIndex() {
      return this.count2 / 4;
   }

   public int getCount2() {
      return this.count2;
   }

   public int getInt(int count4) {
      return this.intArrayList2.getInt(count4);
   }

   public boolean isActiveAsFlag() {
      return this.count3 >= 0;
   }

   public AltLoggerEntry get(int count4) {
      return (AltLoggerEntry)this.list.get(count4);
   }

   public void updateState() {
      this.list.clear();
      this.intArrayList.clear();
      this.count3 = -1;
      this.intArrayList2.clear();
      this.count = -1;
      this.count2 = 0;
   }

   public int getCount3() {
      return this.count3;
   }

   public AuxiliaryLoggerEntry resolveAuxiliaryLoggerEntry(AltLoggerEntry altLoggerEntry) {
      if (altLoggerEntry.isActive() && this.isActive2()) {
         logger.warn("VertexFormat error: Trying to add a position VertexFormatElement when one already exists, ignoring.");
         return this;
      } else {
         this.list.add(altLoggerEntry);
         this.intArrayList.add(this.count2);
         switch (SmoothStack.countArray[altLoggerEntry.getRenderBooleanProperty().ordinal()]) {
            case 1:
               this.count = this.count2;
               break;
            case 2:
               this.count3 = this.count2;
               break;
            case 3:
               this.intArrayList2.add(altLoggerEntry.getCount(), this.count2);
         }

         this.count2 = this.count2 + altLoggerEntry.getCountAsIndex();
         return this;
      }
   }

   public List getList() {
      return this.list;
   }

   private boolean isActive2() {
      int count4 = 0;

      for (int index = this.list.size(); count4 < index; count4++) {
         if (((AltLoggerEntry)this.list.get(count4)).isActive()) {
            return true;
         }
      }

      return false;
   }

   public int size() {
      return this.list.size();
   }

   public int getCount() {
      return this.count;
   }

   public int getIntByIndex(int count4) {
      return this.intArrayList.getInt(count4);
   }

   public AuxiliaryLoggerEntry(AuxiliaryLoggerEntry auxiliaryLoggerEntry) {
      this();

      for (int index = 0; index < auxiliaryLoggerEntry.size(); index++) {
         this.resolveAuxiliaryLoggerEntry(auxiliaryLoggerEntry.get(index));
      }

      this.count2 = auxiliaryLoggerEntry.getCount2();
   }

   public AuxiliaryLoggerEntry() {
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         AuxiliaryLoggerEntry auxiliaryLoggerEntry = (AuxiliaryLoggerEntry)other;
         return this.count2 == auxiliaryLoggerEntry.count2 && this.list.equals(auxiliaryLoggerEntry.list) && this.intArrayList.equals(auxiliaryLoggerEntry.intArrayList);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("format: " + this.list.size() + " elements: ");

      for (int index = 0; index < this.list.size(); index++) {
         builder.append(((AltLoggerEntry)this.list.get(index)).toString());
         if (index != this.list.size() - 1) {
            builder.append(" ");
         }
      }

      return builder.toString();
   }

   @Override
   public int hashCode() {
      int hash = this.list.hashCode();
      int count4 = 31 * hash + this.intArrayList.hashCode();
      return 31 * count4 + this.count2;
   }
}
