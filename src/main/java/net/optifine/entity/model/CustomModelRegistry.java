package net.optifine.entity.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.optifine.config.Config;

public class CustomModelRegistry {
   private static Map mapModelAdapters = makeMapModelAdapters();

   private static void addModelAdapter(Map map, ModelAdapter modelAdapter, String str) {
      if (map.containsKey(str)) {
         Config.warn("Model adapter already registered for id: " + str + ", class: " + modelAdapter.getEntityClass().getName());
      }

      map.put(str, modelAdapter);
   }

   private static void addModelAdapter(Map map, ModelAdapter modelAdapter) {
      addModelAdapter(map, modelAdapter, modelAdapter.getName());
      String[] aliases = modelAdapter.getAliases();
      if (aliases != null) {
         for (int i = 0; i < aliases.length; i++) {
            addModelAdapter(map, modelAdapter, aliases[i]);
         }
      }

      ModelBase modelBase = modelAdapter.makeModel();
      String[] modelRendererNames = modelAdapter.getModelRendererNames();

      for (int j = 0; j < modelRendererNames.length; j++) {
         String str2 = modelRendererNames[j];
         if (modelAdapter.getModelRenderer(modelBase, str2) == null) {
            Config.warn("Model renderer not found, model: " + modelAdapter.getName() + ", name: " + str2);
         }
      }
   }

   private static Map makeMapModelAdapters() {
      LinkedHashMap map = new LinkedHashMap();
      addModelAdapter(map, new ModelAdapterArmorStand());
      addModelAdapter(map, new ModelAdapterBat());
      addModelAdapter(map, new ModelAdapterBlaze());
      addModelAdapter(map, new ModelAdapterBoat());
      addModelAdapter(map, new ModelAdapterCaveSpider());
      addModelAdapter(map, new ModelAdapterChicken());
      addModelAdapter(map, new ModelAdapterCow());
      addModelAdapter(map, new ModelAdapterCreeper());
      addModelAdapter(map, new ModelAdapterDragon());
      addModelAdapter(map, new ModelAdapterEnderCrystal());
      addModelAdapter(map, new ModelAdapterEnderman());
      addModelAdapter(map, new ModelAdapterEndermite());
      addModelAdapter(map, new ModelAdapterGhast());
      addModelAdapter(map, new ModelAdapterGuardian());
      addModelAdapter(map, new ModelAdapterHorse());
      addModelAdapter(map, new ModelAdapterIronGolem());
      addModelAdapter(map, new ModelAdapterLeadKnot());
      addModelAdapter(map, new ModelAdapterMagmaCube());
      addModelAdapter(map, new ModelAdapterMinecart());
      addModelAdapter(map, new ModelAdapterMinecartTnt());
      addModelAdapter(map, new ModelAdapterMinecartMobSpawner());
      addModelAdapter(map, new ModelAdapterMooshroom());
      addModelAdapter(map, new ModelAdapterOcelot());
      addModelAdapter(map, new ModelAdapterPig());
      addModelAdapter(map, new ModelAdapterPigZombie());
      addModelAdapter(map, new ModelAdapterRabbit());
      addModelAdapter(map, new ModelAdapterSheep());
      addModelAdapter(map, new ModelAdapterSilverfish());
      addModelAdapter(map, new ModelAdapterSkeleton());
      addModelAdapter(map, new ModelAdapterSlime());
      addModelAdapter(map, new ModelAdapterSnowman());
      addModelAdapter(map, new ModelAdapterSpider());
      addModelAdapter(map, new ModelAdapterSquid());
      addModelAdapter(map, new ModelAdapterVillager());
      addModelAdapter(map, new ModelAdapterWitch());
      addModelAdapter(map, new ModelAdapterWither());
      addModelAdapter(map, new ModelAdapterWitherSkull());
      addModelAdapter(map, new ModelAdapterWolf());
      addModelAdapter(map, new ModelAdapterZombie());
      addModelAdapter(map, new ModelAdapterSheepWool());
      addModelAdapter(map, new ModelAdapterBanner());
      addModelAdapter(map, new ModelAdapterBook());
      addModelAdapter(map, new ModelAdapterChest());
      addModelAdapter(map, new ModelAdapterChestLarge());
      addModelAdapter(map, new ModelAdapterEnderChest());
      addModelAdapter(map, new ModelAdapterHeadHumanoid());
      addModelAdapter(map, new ModelAdapterHeadSkeleton());
      addModelAdapter(map, new ModelAdapterSign());
      return map;
   }

   public static String[] getModelNames() {
      Set keys = mapModelAdapters.keySet();
      return (String[])keys.toArray(new String[keys.size()]);
   }

   public static ModelAdapter getModelAdapter(String str) {
      return (ModelAdapter)mapModelAdapters.get(str);
   }
}
