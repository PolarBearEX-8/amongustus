package net.optifine.config;


public class VillagerProfession {
   private int profession;
   private int[] careers;

   public VillagerProfession(int n) {
      this(n, (int[])null);
   }

   public VillagerProfession(int n, int[] ints) {
      this.profession = n;
      this.careers = ints;
   }

   @Override
   public String toString() {
      return this.careers == null ? "" + this.profession : "" + this.profession + ":" + Config.arrayToString(this.careers);
   }

   public int[] getCareers() {
      return this.careers;
   }

   public VillagerProfession(int n, int n2) {
      this(n, new int[]{n2});
   }

   public boolean addCareer(int n) {
      if (this.careers == null) {
         this.careers = new int[]{n};
         return true;
      } else if (this.hasCareer(n)) {
         return false;
      } else {
         this.careers = Config.addIntToArray(this.careers, n);
         return true;
      }
   }

   public int getProfession() {
      return this.profession;
   }

   private boolean hasCareer(int n) {
      return this.careers == null ? false : Config.equalsOne(n, this.careers);
   }

   public boolean matches(int n, int n2) {
      return this.profession != n ? false : this.careers == null || Config.equalsOne(n2, this.careers);
   }
}
