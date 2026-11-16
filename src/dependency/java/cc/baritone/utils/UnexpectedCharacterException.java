package cc.baritone.utils;

public class UnexpectedCharacterException extends Exception {
   public static final long serialVersionUID = -0x6d5dddf5349df2a3L;
   public static final int count = 0;
   public static final int count2 = 1;
   public static final int count3 = 2;
   public int count4;
   public Object dataRef;
   public int count5;

   @Override
   public String getMessage() {
      StringBuffer builder = new StringBuffer();
      switch (this.count4) {
         case 0:
            builder.append("Unexpected character (").append(this.dataRef).append(") at position ").append(this.count5).append(".");
            break;
         case 1:
            builder.append("Unexpected token ").append(this.dataRef).append(" at position ").append(this.count5).append(".");
            break;
         case 2:
            builder.append("Unexpected exception at position ").append(this.count5).append(": ").append(this.dataRef);
            break;
         default:
            builder.append("Unkown error at position ").append(this.count5).append(".");
      }

      return builder.toString();
   }

   public void setCount4(int count6) {
      this.count4 = count6;
   }

   public UnexpectedCharacterException(int count6) {
      this(-1, count6, null);
   }

   public int getCount5() {
      return this.count5;
   }

   public UnexpectedCharacterException(int count6, Object entry) {
      this(-1, count6, entry);
   }

   public Object getDataRef() {
      return this.dataRef;
   }

   public void setCount5(int count6) {
      this.count5 = count6;
   }

   public int getCount4() {
      return this.count4;
   }

   public UnexpectedCharacterException(int count6, int count7, Object entry) {
      this.count5 = count6;
      this.count4 = count7;
      this.dataRef = entry;
   }

   public void setDataRef(Object dataRef2) {
      this.dataRef = dataRef2;
   }
}
