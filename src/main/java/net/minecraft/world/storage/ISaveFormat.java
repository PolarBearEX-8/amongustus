package net.minecraft.world.storage;

import java.util.List;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.util.IProgressUpdate;

public interface ISaveFormat {
   List getSaveList() throws AnvilConverterException;

   boolean convertMapFormat(String str, IProgressUpdate progressUpdate);

   boolean isNewLevelIdAcceptable(String str);

   void renameWorld(String str, String str2);

   boolean isOldMapFormat(String str);

   String getName();

   void flushCache();

   boolean isConvertible(String str);

   WorldInfo getWorldInfo(String str);

   ISaveHandler getSaveLoader(String str, boolean flag);

   boolean deleteWorldDirectory(String str);

   boolean canLoadWorld(String str);
}
