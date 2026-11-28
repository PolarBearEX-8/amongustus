package wdl;

import wdl.api.ISpecialEntityHandler;
import wdl.api.IWDLModDescripted;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;

public class HologramHandler implements IWDLModDescripted, ISpecialEntityHandler {

   @Override
   public String getEnvironmentErrorMessage(String str) {
      return null;
   }

   @Override
   public String getMainAuthor() {
      return "Pokechu22";
   }

   @Override
   public int getSpecialEntityTrackDistance(String str) {
      return -1;
   }

   @Override
   public Multimap getSpecialEntities() {
      HashMultimap hashMultimap = HashMultimap.create();
      hashMultimap.put("ArmorStand", "Hologram");
      return hashMultimap;
   }

   @Override
   public String getURL() {
      return null;
   }

   @Override
   public String getDescription() {
      return "Provides basic support for disabling holograms.";
   }

   @Override
   public String[] getAuthors() {
      return null;
   }

   @Override
   public String getSpecialEntityName(Entity entity) {
      return entity instanceof EntityArmorStand && entity.isInvisible() && entity.hasCustomName() ? "Hologram" : null;
   }

   @Override
   public boolean isValidEnvironment(String str) {
      return true;
   }

   @Override
   public String getDisplayName() {
      return "Hologram support";
   }

   @Override
   public String getSpecialEntityCategory(String str) {
      return str.equals("Hologram") ? "Other" : null;
   }

}
