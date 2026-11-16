package client.util;

public enum ABCDEFGHIJKLMNOPQREnum {
    IDENTIFIER("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_:."),
    NUMBER("0123456789", "0123456789."),
    OPERATOR("+-*/%!&|<>=", "&|="),
    COMMA(","),
    BRACKET_OPEN("("),
    BRACKET_CLOSE(")");

   public String labelText;
   public String labelText2;
   public static final ABCDEFGHIJKLMNOPQREnum[] VALUES = values();
   public static final ABCDEFGHIJKLMNOPQREnum[] ENUM_VALUES = new ABCDEFGHIJKLMNOPQREnum[]{IDENTIFIER, NUMBER, OPERATOR, COMMA, BRACKET_OPEN, BRACKET_CLOSE};

   public static ABCDEFGHIJKLMNOPQREnum resolveABCDEFGHIJKLMNOPQREnum(char character) {
      for (int index = 0; index < VALUES.length; index++) {
         ABCDEFGHIJKLMNOPQREnum aBCDEFGHIJKLMNOPQREnum = VALUES[index];
         if (aBCDEFGHIJKLMNOPQREnum.getLabelText().indexOf(character) >= 0) {
            return aBCDEFGHIJKLMNOPQREnum;
         }
      }

      return null;
   }

   private ABCDEFGHIJKLMNOPQREnum(String text) {
      this(text, "");
   }

   public String getLabelText() {
      return this.labelText;
   }

   public boolean indexOf(char character) {
      return this.labelText2.indexOf(character) >= 0;
   }



   private ABCDEFGHIJKLMNOPQREnum(String text, String text2) {
      this.labelText = text;
      this.labelText2 = text2;
   }

   public String getLabelText2() {
      return this.labelText2;
   }
}
