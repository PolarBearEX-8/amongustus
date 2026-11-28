package net.optifine.expr;

public class Token {
   private TokenType type;
   private String text;

   public TokenType getType() {
      return this.type;
   }

   @Override
   public String toString() {
      return this.text;
   }

   public Token(TokenType tokenType, String str) {
      this.type = tokenType;
      this.text = str;
   }

   public String getText() {
      return this.text;
   }
}
