package net.augustus.cosmetics.model;

import net.augustus.cosmetics.MoreCosmeticsCore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import net.labymod.Bone;
import net.labymod.MinecraftGeometry;
import net.labymod.ModelProperties;
import net.labymod.RawGeoModel;

public class RawGeometryTree {
   public ModelProperties properties;
   public HashMap topLevelBones = new HashMap();

   public static boolean hasParent(Bone bone) {
      return bone.getParent() != null;
   }

   public static RawGeometryTree parseHierarchy(RawGeoModel rawGeoModel, String str) {
      RawGeometryTree rawGeometryTree = new RawGeometryTree();
      MinecraftGeometry minecraftGeometry = rawGeoModel.getMinecraftGeometry()[0];
      rawGeometryTree.properties = minecraftGeometry.getProperties();
      ArrayList list = new ArrayList<>(Arrays.asList(minecraftGeometry.getBones()));
      int n = list.size() - 1;
      int n2 = 0;

      while (true) {
         if (++n2 > 10000) {
            MoreCosmeticsCore.LOGGER.warn("Some bones in " + str + " do not have existing parents: ");
            MoreCosmeticsCore.LOGGER.warn(list.stream().map(obj -> ((Bone)obj).getName()).collect(Collectors.joining(", ")));
            break;
         }

         Bone bone = (Bone)list.get(n);
         if (!hasParent(bone)) {
            rawGeometryTree.topLevelBones.put(bone.getName(), new RawBoneGroup(bone));
            list.remove(bone);
            n2 = 0;
         } else {
            RawBoneGroup groupFromName = getGroupFromName(rawGeometryTree, bone.getParent());
            if (groupFromName != null) {
               groupFromName.children.put(bone.getName(), new RawBoneGroup(bone));
               list.remove(bone);
               n2 = 0;
            }
         }

         if (n == 0) {
            n = list.size() - 1;
            if (n == -1) {
               break;
            }
         } else {
            n--;
         }
      }

      return rawGeometryTree;
   }

   public static void indexBoneChildren(HashMap map, RawBoneGroup rawBoneGroup) {

      for (RawBoneGroup rawBoneGroup2 : (Iterable<RawBoneGroup>)(rawBoneGroup.children.values())) {
         map.put(rawBoneGroup2.selfBone.getName(), rawBoneGroup2);
         indexBoneChildren(map, rawBoneGroup2);
      }
   }

   public static RawBoneGroup getGroupFromName(RawGeometryTree rawGeometryTree, String str) {
      HashMap map = new HashMap();

      for (RawBoneGroup rawBoneGroup : (Iterable<RawBoneGroup>)(rawGeometryTree.topLevelBones.values())) {
         map.put(rawBoneGroup.selfBone.getName(), rawBoneGroup);
         indexBoneChildren(map, rawBoneGroup);
      }

      return (RawBoneGroup)map.get(str);
   }





}
