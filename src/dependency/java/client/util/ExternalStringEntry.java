package client.util;

import java.util.Collection;
import net.minecraft.network.play.server.S20PacketEntityProperties;

public class ExternalStringEntry {
   public final String labelText;
   public final double value;
   public final Collection collection;
   public final S20PacketEntityProperties s20PacketEntityProperties;

   public Collection getCollection() {
      return this.collection;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public double getValue() {
      return this.value;
   }

   public ExternalStringEntry(S20PacketEntityProperties packet, String text, double offset, Collection collection2) {
      this.s20PacketEntityProperties = packet;
      this.labelText = text;
      this.value = offset;
      this.collection = collection2;
   }
}
