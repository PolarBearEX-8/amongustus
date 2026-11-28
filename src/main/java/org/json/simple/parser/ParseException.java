package org.json.simple.parser;


public class ParseException extends Exception {
   private static final long serialVersionUID = -7880698968187728547L;
   public static final int ERROR_UNEXPECTED_CHAR = 0;
   public static final int ERROR_UNEXPECTED_TOKEN = 1;
   public static final int ERROR_UNEXPECTED_EXCEPTION = 2;
   private int errorType;
   private Object unexpectedObject;
   private int position;

   public int getErrorType() {
      return this.errorType;
   }

   @Override
   public String getMessage() {
      StringBuffer sb = new StringBuffer();
      switch (this.errorType) {
         case 0:
            sb.append("Unexpected character (").append(this.unexpectedObject).append(") at position ").append(this.position).append(".");
            break;
         case 1:
            sb.append("Unexpected token ").append(this.unexpectedObject).append(" at position ").append(this.position).append(".");
            break;
         case 2:
            sb.append("Unexpected exception at position ").append(this.position).append(": ").append(this.unexpectedObject);
            break;
         default:
            sb.append("Unkown error at position ").append(this.position).append(".");
      }

      return sb.toString();
   }

   public ParseException(int n, Object obj) {
      this(-1, n, obj);
   }

   public void setErrorType(int n) {
      this.errorType = n;
   }

   public ParseException(int n) {
      this(-1, n, null);
   }

   public void setPosition(int n) {
      this.position = n;
   }

   public void setUnexpectedObject(Object obj) {
      this.unexpectedObject = obj;
   }

   public Object getUnexpectedObject() {
      return this.unexpectedObject;
   }

   public ParseException(int n, int n2, Object obj) {
      this.position = n;
      this.errorType = n2;
      this.unexpectedObject = obj;
   }

   public int getPosition() {
      return this.position;
   }
}
