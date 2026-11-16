package client.util;

import java.util.Collections;
import java.util.List;
import net.minecraft.client.resources.data.IMetadataSection;

public class SupplementalListEntry implements IMetadataSection {
   public final boolean enabled;
   public final boolean enabled2;
   public final List list;

   public List unmodifiableList() {
      return Collections.unmodifiableList(this.list);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public SupplementalListEntry(boolean flag, boolean flag2, List list2) {
      this.enabled = flag;
      this.enabled2 = flag2;
      this.list = list2;
   }
}
