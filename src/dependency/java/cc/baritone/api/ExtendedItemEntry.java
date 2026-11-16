package cc.baritone.api;

import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DynamicListener;
import cc.baritone.utils.StaticListener;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ExtendedItemEntry implements DynamicListener {
   public ActiveListener activeListener;
   public StaticListener staticListener;
   public final List list;

   @Override
   public final List getListAsList() {
      return this.list;
   }

   private static String toLowerCase(String text) {
      return text.toLowerCase(Locale.US);
   }

   public ExtendedItemEntry(ActiveListener activeListener2, String[] textArray) {
      this.list = Collections.unmodifiableList(Stream.of(textArray).map(ExtendedItemEntry::toLowerCase).collect(Collectors.toList()));
      this.activeListener = activeListener2;
      this.staticListener = activeListener2.getStaticListener();
   }
}
