package client.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.model.ModelArmorStand;
import net.minecraft.client.model.ModelBanner;
import net.minecraft.client.model.ModelBat;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelChestLarge;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelDragon;
import net.minecraft.client.model.ModelEnderChest;
import net.minecraft.client.model.ModelEndermite;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.model.ModelGuardian;
import net.minecraft.client.model.ModelHeadHumanoid;
import net.minecraft.client.model.ModelHeadSkeleton;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.model.ModelLeadKnot;
import net.minecraft.client.model.ModelMagmaCube;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelRabbit;
import net.minecraft.client.model.ModelSheepWool;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.model.ModelSnowGolem;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.model.ModelWither;
import net.minecraft.client.model.ModelWitherSkull;
import net.minecraft.client.model.ModelWolf;

public class ExtendedNameManager {
   public static Map map = getMap();

   private static void applyMap(Map map2, ClassEntry classEntry) {
      applyMap(map2, classEntry, classEntry.getLabelText());
      String[] stringArray = classEntry.getStringArray();
      if (stringArray != null) {
         for (int index = 0; index < stringArray.length; index++) {
            String text = stringArray[index];
            applyMap(map2, classEntry, text);
         }
      }

      PassiveFloatEntry passiveFloatEntry = classEntry.getPassiveFloatEntry();
      String[] textArray = classEntry.getTextArray();

      for (int index2 = 0; index2 < textArray.length; index2++) {
         String text2 = textArray[index2];
         if (classEntry.resolveBoxesManager(passiveFloatEntry, text2) == null) {
            BuildManager.applyText3("Model renderer not found, model: " + classEntry.getLabelText() + ", name: " + text2);
         }
      }
   }

   private static Map getMap() {
      LinkedHashMap map2 = new LinkedHashMap();
      applyMap(map2, new ModelArmorStand());
      applyMap(map2, new ModelBat());
      applyMap(map2, new ModelBlaze());
      applyMap(map2, new ModelBoat());
      applyMap(map2, new BezierSorter());
      applyMap(map2, new ModelChicken());
      applyMap(map2, new BezierTracker());
      applyMap(map2, new ModelCreeper());
      applyMap(map2, new ModelDragon());
      applyMap(map2, new CubeClassEntry());
      applyMap(map2, new AngleTransformer());
      applyMap(map2, new ModelEndermite());
      applyMap(map2, new ModelGhast());
      applyMap(map2, new ModelGuardian());
      applyMap(map2, new ModelHorse());
      applyMap(map2, new ModelIronGolem());
      applyMap(map2, new ModelLeadKnot());
      applyMap(map2, new ModelMagmaCube());
      applyMap(map2, new ModelMinecart());
      applyMap(map2, new BezierRanker());
      applyMap(map2, new BottomClassEntryAdapter());
      applyMap(map2, new BezierMatcher());
      applyMap(map2, new ModelOcelot());
      applyMap(map2, new BezierWatcher());
      applyMap(map2, new AuxiliaryHeadClassEntryAdapter());
      applyMap(map2, new ModelRabbit());
      applyMap(map2, new BezierCollector());
      applyMap(map2, new ModelSilverfish());
      applyMap(map2, new InternalHeadClassEntryAdapter());
      applyMap(map2, new ModelSlime());
      applyMap(map2, new ModelSnowGolem());
      applyMap(map2, new ModelSpider());
      applyMap(map2, new ModelSquid());
      applyMap(map2, new ModelVillager());
      applyMap(map2, new ModelWitch());
      applyMap(map2, new ModelWither());
      applyMap(map2, new ModelWitherSkull());
      applyMap(map2, new ModelWolf());
      applyMap(map2, new HeadClassEntryAdapter());
      applyMap(map2, new ModelSheepWool());
      applyMap(map2, new ModelBanner());
      applyMap(map2, new ModelBook());
      applyMap(map2, new ModelChest());
      applyMap(map2, new ModelChestLarge());
      applyMap(map2, new ModelEnderChest());
      applyMap(map2, new ModelHeadHumanoid());
      applyMap(map2, new ModelHeadSkeleton());
      applyMap(map2, new ModelSign());
      return map2;
   }

   public static ClassEntry get(String text) {
      return (ClassEntry)map.get(text);
   }

   private static void applyMap(Map map2, ClassEntry classEntry, String text) {
      if (map2.containsKey(text)) {
         BuildManager.applyText3("Model adapter already registered for id: " + text + ", class: " + classEntry.getMemberRef().getName());
      }

      map2.put(text, classEntry);
   }

   public static String[] getTextArray() {
      Set keys = map.keySet();
      return ((String[])keys.toArray(new String[keys.size()]));
   }
}
