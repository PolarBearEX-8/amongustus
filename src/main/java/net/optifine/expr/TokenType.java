package net.optifine.expr;

public enum TokenType {
   IDENTIFIER("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_:."),
   NUMBER("0123456789", "0123456789."),
   OPERATOR("+-*/%!&|<>=", "&|="),
   COMMA(","),
   BRACKET_OPEN("("),
   BRACKET_CLOSE(")");

private String charsFirst;
   private String charsNext;
   public static final TokenType[] VALUES = values();
public String getCharsNext() {
      return this.charsNext;
   }

   public String getCharsFirst() {
      return this.charsFirst;
   }

   public static TokenType getTypeByFirstChar(char ch) {
      for (int i = 0; i < VALUES.length; i++) {
         TokenType tokenType = VALUES[i];
         if (tokenType.getCharsFirst().indexOf(ch) >= 0) {
            return tokenType;
         }
      }

      return null;
   }

   public boolean hasCharNext(char ch) {
      return this.charsNext.indexOf(ch) >= 0;
   }

   private TokenType(String str) {
      this(str, "");
   }

   private TokenType(String str, String str2) {
      this.charsFirst = str;
      this.charsNext = str2;
   }
}
