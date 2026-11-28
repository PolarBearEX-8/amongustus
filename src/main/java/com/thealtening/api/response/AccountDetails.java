package com.thealtening.api.response;

public class AccountDetails {
   private int hypixelLevel;
   private String hypixelRank;
   private int mineplexLevel;
   private String mineplexRank;
   private boolean labymodCape;
   private boolean fiveZigCape;

   public boolean isFiveZigCape() {
      return this.fiveZigCape;
   }

   public boolean isLabymodCape() {
      return this.labymodCape;
   }

   public int getHypixelLevel() {
      return this.hypixelLevel;
   }

   public int getMineplexLevel() {
      return this.mineplexLevel;
   }

   public String getMineplexRank() {
      return this.mineplexRank;
   }

   public String getHypixelRank() {
      return this.hypixelRank;
   }

   @Override
   public String toString() {
      return String.format("AccountDetails[%s:%s:%s:%s:%s:%s]", this.hypixelLevel, this.hypixelRank, this.mineplexLevel, this.mineplexRank, this.labymodCape, this.fiveZigCape);
   }
}
