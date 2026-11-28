package net.augustus.modules.modes.anticheat;



import net.augustus.utils.ChatUtil;
public class AntiCheatCheckTracker {
   private int checkId;
   private String description;
   private double allTimeViolation = 0.0;
   private AntiCheatCategory antiCheatCategory;
   private FlagCertainty flagCertainty;
   private double violationLevel = 0.0;

   public AntiCheatCategory getAntiCheatCategory() {
      return this.antiCheatCategory;
   }

   public double getViolationLevel() {
      return this.violationLevel;
   }

   public void flag(FlagCertainty flagCertainty2) {
      this.flagCertainty = flagCertainty2;
      ChatUtil.sendChat(
         "P" + this.checkId + ": " + this.description + "; Certainty: " + flagCertainty2.getLabel() + "; Violation " + this.checkId + "; AllTimeViolation: " + this.allTimeViolation
      );
   }

   public double getAllTimeViolation() {
      return this.allTimeViolation;
   }

   public void reportHitbug(HitbugFlagType hitbugFlagType) {
      this.debugLog("You got Hitbug! Congratulations! " + hitbugFlagType.getLabel());
   }

   public AntiCheatCheckTracker(int n, String str, AntiCheatCategory antiCheatCategory2) {
      this.flagCertainty = FlagCertainty.NONE;
      this.checkId = n;
      this.description = str;
      this.antiCheatCategory = antiCheatCategory2;
   }

   public void addViolation(double d) {
      if (d > 0.0) {
         this.allTimeViolation += d;
      }

      this.violationLevel += d;
   }

   public void flagWithDetail(FlagCertainty flagCertainty2, String str) {
      this.flagCertainty = flagCertainty2;
      ChatUtil.sendChat(
         "P" + this.checkId + ": " + this.description + " " + str + "; Certainty: " + flagCertainty2.getLabel() + "; Violation " + this.checkId + "; AllTimeViolation: " + this.allTimeViolation
      );
   }

   public FlagCertainty getFlagCertainty() {
      return this.flagCertainty;
   }

   public void debugLog(String str) {
      ChatUtil.sendChat("(Debug P" + this.checkId + ") " + str);
   }

   public String getDescription() {
      return this.description;
   }
}
