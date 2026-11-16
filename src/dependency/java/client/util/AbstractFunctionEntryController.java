package client.util;

public abstract class AbstractFunctionEntryController {
   public final FunctionEntry functionEntry;
   public final Object dataRef;
   public final double value;

   public AbstractFunctionEntryController(Object entry, double offset, FunctionEntry functionEntry2) {
      this.dataRef = entry;
      this.value = offset;
      this.functionEntry = functionEntry2;
   }

   public Object getDataRef() {
      return this.dataRef;
   }

   public double getValue() {
      return this.value;
   }

   public FunctionEntry getFunctionEntry() {
      return this.functionEntry;
   }
}
