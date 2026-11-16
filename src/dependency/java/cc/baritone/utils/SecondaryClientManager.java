package cc.baritone.utils;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArmorstand;

public class SecondaryClientManager implements Listener12Extension, AuxiliaryListener12Extension {
   @Override
   public String resolveTextByText(String text) {
      return text.equals("Hologram") ? "Other" : null;
   }

   @Override
   public boolean matchesText(String text) {
      return true;
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
   public String[] getTextArray() {
      return null;
   }

   @Override
   public int computeCount(String text) {
      return -1;
   }

   @Override
   public Multimap getMultimap() {
      HashMultimap hashMultimap = HashMultimap.create();
      hashMultimap.put("ArmorStand", "Hologram");
      return hashMultimap;
   }

   @Override
   public String resolveText(String text) {
      return null;
   }

   @Override
   public String getText2() {
      return "Hologram support";
   }

   @Override
   public String getText3() {
      return "Provides basic support for disabling holograms.";
   }

   @Override
   public String resolveText(Entity entity) {
      return entity instanceof EntityArmorstand && entity.isActive12() && entity.processAction() ? "Hologram" : null;
   }
}
