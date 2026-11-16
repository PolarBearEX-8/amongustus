package client.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import net.labymod.Bone;
import net.labymod.MinecraftGeometry;
import net.labymod.ModelProperties;
import net.labymod.RawGeoModel;

public class ExtraHashMapEntry {
   public HashMap hashMap = new HashMap();
   public ModelProperties modelProperties;

   public static boolean getParent(Bone bone) {
      return bone.getParent() != null;
   }

   public static ModelBone resolveModelBone(ExtraHashMapEntry extraHashMapEntry, String text) {
      HashMap map = new HashMap();

      for (ModelBone modelBone : (Iterable<ModelBone>)(Iterable)(extraHashMapEntry.hashMap.values())) {
         map.put(modelBone.bone.getName(), modelBone);
         applyHashMap(map, modelBone);
      }

      return (ModelBone)map.get(text);
   }

   public static void applyHashMap(HashMap map, ModelBone modelBone) {
      for (ModelBone modelBone2 : (Iterable<ModelBone>)(Iterable)(modelBone.hashMap.values())) {
         map.put(modelBone2.bone.getName(), modelBone2);
         applyHashMap(map, modelBone2);
      }
   }

   private static String getName(Bone bone) {
      return bone.getName();
   }

   public static ExtraHashMapEntry resolveExtraHashMapEntry(RawGeoModel rawGeoModel, String text) {
      ExtraHashMapEntry extraHashMapEntry = new ExtraHashMapEntry();
      MinecraftGeometry minecraftGeometry = rawGeoModel.getMinecraftGeometry()[0];
      extraHashMapEntry.modelProperties = minecraftGeometry.getProperties();
      ArrayList list = new ArrayList<>(Arrays.asList(minecraftGeometry.getBones()));
      int count2 = list.size() - 1;
      int count3 = 0;

      while (true) {
         if (++count3 > 10000) {
            MoreCosmeticsManager.logger.warn("Some bones in " + text + " do not have existing parents: ");
            MoreCosmeticsManager.logger.warn(list.stream().map((item -> ExtraHashMapEntry.getName((Bone)item))).collect(Collectors.joining(", ")));
            break;
         }

         Bone bone = (Bone)list.get(count2);
         if (!getParent(bone)) {
            extraHashMapEntry.hashMap.put(bone.getName(), new ModelBone(bone));
            list.remove(bone);
            count3 = 0;
         } else {
            String parent = bone.getParent();
            String text2 = parent;
            ModelBone modelBone = resolveModelBone(extraHashMapEntry, text2);
            if (modelBone != null) {
               modelBone.hashMap.put(bone.getName(), new ModelBone(bone));
               list.remove(bone);
               count3 = 0;
            }
         }

         if (count2 == 0) {
            count2 = list.size() - 1;
            if (count2 == -1) {
               break;
            }
         } else {
            count2--;
         }
      }

      return extraHashMapEntry;
   }
}
