package net.minecraft.world.storage;

import client.event.CompositeListener;
import client.util.RandomSeedManager;
import client.util.TertiaryExceptionAdapter;
import java.util.List;

public interface ISaveFormat {
   boolean matchesText2(String text);

   RandomSeedManager resolveRandomSeedManager(String text);

   boolean isDirectory(String text);

   ISaveHandler resolveISaveHandler(String text, boolean flag);

   String getText();

   List getList() throws TertiaryExceptionAdapter;

   boolean matchesTextByText(String text);

   boolean matchesText(String text, CompositeListener compositeListener);

   void applyText(String text, String text2);

   boolean matchesText3(String text);

   void updateState();

   boolean matchesText(String text);
}
