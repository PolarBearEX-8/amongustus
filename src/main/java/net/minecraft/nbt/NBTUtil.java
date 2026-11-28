package net.minecraft.nbt;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.UUID;
import net.minecraft.util.StringUtils;

public final class NBTUtil {

   public static NBTTagCompound writeGameProfile(NBTTagCompound tag, GameProfile profile) {
      if (!StringUtils.isNullOrEmpty(profile.getName())) {
         tag.setString("Name", profile.getName());
      }

      if (profile.getId() != null) {
         tag.setString("Id", profile.getId().toString());
      }

      if (!profile.getProperties().isEmpty()) {
         NBTTagCompound tag2 = new NBTTagCompound();

         for (String str : profile.getProperties().keySet()) {
            NBTTagList tagList = new NBTTagList();

            for (Property property : profile.getProperties().get(str)) {
               NBTTagCompound tag3 = new NBTTagCompound();
               tag3.setString("Value", property.getValue());
               if (property.hasSignature()) {
                  tag3.setString("Signature", property.getSignature());
               }

               tagList.appendTag(tag3);
            }

            tag2.setTag(str, tagList);
         }

         tag.setTag("Properties", tag2);
      }

      return tag;
   }

   public static GameProfile readGameProfileFromNBT(NBTTagCompound tag) {
      String str = null;
      String str2 = null;
      if (tag.hasKey("Name", 8)) {
         str = tag.getString("Name");
      }

      if (tag.hasKey("Id", 8)) {
         str2 = tag.getString("Id");
      }

      if (StringUtils.isNullOrEmpty(str) && StringUtils.isNullOrEmpty(str2)) {
         return null;
      } else {
         UUID uuid;
         try {
            uuid = UUID.fromString(str2);
         } catch (Throwable e) {
            uuid = null;
         }

         GameProfile profile = new GameProfile(uuid, str);
         if (tag.hasKey("Properties", 10)) {
            NBTTagCompound compoundTag = tag.getCompoundTag("Properties");

            for (String str3 : (Iterable<String>)(compoundTag.getKeySet())) {
               NBTTagList tagList = compoundTag.getTagList(str3, 10);

               for (int i = 0; i < tagList.tagCount(); i++) {
                  NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
                  String string = compoundTagAt.getString("Value");
                  if (compoundTagAt.hasKey("Signature", 8)) {
                     profile.getProperties().put(str3, new Property(str3, string, compoundTagAt.getString("Signature")));
                  } else {
                     profile.getProperties().put(str3, new Property(str3, string));
                  }
               }
            }
         }

         return profile;
      }
   }

   public static boolean areNBTEquals(NBTBase nbt, NBTBase nbt2, boolean flag) {
      if (nbt == nbt2) {
         return true;
      } else if (nbt == null) {
         return true;
      } else if (nbt2 == null) {
         return false;
      } else if (!nbt.getClass().equals(nbt2.getClass())) {
         return false;
      } else if (nbt instanceof NBTTagCompound) {
         NBTTagCompound tag = (NBTTagCompound)nbt;
         NBTTagCompound tag2 = (NBTTagCompound)nbt2;

         for (String str : (Iterable<String>)(tag.getKeySet())) {
            if (!areNBTEquals(tag.getTag(str), tag2.getTag(str), flag)) {
               return false;
            }
         }

         return true;
      } else if (nbt instanceof NBTTagList && flag) {
         NBTTagList tagList = (NBTTagList)nbt;
         NBTTagList tagList2 = (NBTTagList)nbt2;
         if (tagList.tagCount() == 0) {
            return tagList2.tagCount() == 0;
         } else {
            for (int i = 0; i < tagList.tagCount(); i++) {
               NBTBase nbt3 = tagList.get(i);
               boolean flag2 = false;

               for (int j = 0; j < tagList2.tagCount(); j++) {
                  if (areNBTEquals(nbt3, tagList2.get(j), flag)) {
                     flag2 = true;
                     break;
                  }
               }

               if (!flag2) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return nbt.equals(nbt2);
      }
   }
}
