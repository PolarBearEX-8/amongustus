package cc.baritone.utils;

import net.minecraft.entity.Entity;

public class Listener12ExtensionAdapter implements SecondaryListener12Extension, AuxiliaryListener12Extension {
   @Override
   public boolean matchesText(String text) {
      return true;
   }

   private static double computeValue(int count) {
      return (double)count / 32.0;
   }

   @Override
   public String getTextAsText() {
      return "Pokechu22";
   }

   @Override
   public String getText() {
      return null;
   }

   @Override
   public boolean matchesEntity(Entity entity) {
      return entity.indexOffset2 != 0 || entity.indexOffset3 != 0 || entity.indexOffset != 0;
   }

   @Override
   public String[] getTextArray() {
      return null;
   }

   @Override
   public String resolveText(String text) {
      return null;
   }

   @Override
   public String getText2() {
      return "Entity realigner";
   }

   @Override
   public String getText3() {
      return "Realigns entities to their serverside position to deal with entities that drift clientside (for example, boats).";
   }

   @Override
   public void applyEntity(Entity entity) {
      System.out.println("Realigning " + entity);
      entity.posX = computeValue(entity.indexOffset2);
      entity.posY = computeValue(entity.indexOffset3);
      entity.posZ = computeValue(entity.indexOffset);
      System.out.println("Realigned " + entity);
   }
}
