package net.minecraft.item;

public class ItemRecord {
   public Item item;
   public int count;
   public String labelText;
   public String labelText2;

   public ItemRecord(Item item2, int count2, String text, String text2) {
      this.item = item2;
      this.count = count2;
      this.labelText = text;
      this.labelText2 = text2;
   }
}
