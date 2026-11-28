package com.thealtening.api.response;

public class Account {
   private String username;
   private String expires;
   private String token;
   private boolean limit;
   private AccountDetails info;

   public String getToken() {
      return this.token;
   }

   public String getUsername() {
      return this.username;
   }

   @Override
   public String toString() {
      return String.format("Account[%s:%s:%s:%s]", this.token, this.username, this.expires, this.limit);
   }

   public AccountDetails getInfo() {
      return this.info;
   }

   public String getExpires() {
      return this.expires;
   }

   public boolean isLimit() {
      return this.limit;
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof Account)) {
         return false;
      } else {
         Account account = (Account)obj;
         return account.getUsername().equals(this.username) && account.getToken().equals(this.token);
      }
   }
}
