package wdl;

import wdl.api.IEntityEditor;
import wdl.api.IWDLModDescripted;
import net.minecraft.entity.Entity;

public class EntityRealigner implements IWDLModDescripted, IEntityEditor {

   @Override
   public void editEntity(Entity entity) {
      System.out.println("Realigning " + entity);
      entity.posX = convertServerPos(entity.serverPosX);
      entity.posY = convertServerPos(entity.serverPosY);
      entity.posZ = convertServerPos(entity.serverPosZ);
      System.out.println("Realigned " + entity);
   }

   private static double convertServerPos(int n) {
      return (double)n / 32.0;
   }

   @Override
   public boolean shouldEdit(Entity entity) {
      return entity.serverPosX != 0 || entity.serverPosY != 0 || entity.serverPosZ != 0;
   }

   @Override
   public String getDescription() {
      return "Realigns entities to their serverside position to deal with entities that drift clientside (for example, boats).";
   }

   @Override
   public String[] getAuthors() {
      return null;
   }

   @Override
   public boolean isValidEnvironment(String str) {
      return true;
   }

   @Override
   public String getEnvironmentErrorMessage(String str) {
      return null;
   }

   @Override
   public String getURL() {
      return null;
   }

   @Override
   public String getDisplayName() {
      return "Entity realigner";
   }

   @Override
   public String getMainAuthor() {
      return "Pokechu22";
   }

}
