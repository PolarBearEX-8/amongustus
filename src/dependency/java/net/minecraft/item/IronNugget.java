package net.minecraft.item;

import client.util.Listener137Entry;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.properties.IProperty;

public class IronNugget {
   public IProperty iProperty;
   public String labelText;
   public final List list = Lists.newArrayList();

   public Listener137Entry getListener137Entry() {
      return new Listener137Entry(this.iProperty, this.labelText, this.list, null);
   }

   public IronNugget withLabelText(String text) {
      this.labelText = text;
      return this;
   }

   public IronNugget addAll(IProperty[] iPropertyArray) {
      Collections.addAll(this.list, iPropertyArray);
      return this;
   }

   public IronNugget withIProperty(IProperty iProperty2) {
      this.iProperty = iProperty2;
      return this;
   }
}
