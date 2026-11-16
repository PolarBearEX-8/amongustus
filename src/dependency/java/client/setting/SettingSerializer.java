package client.setting;

import client.event.SupplementalListener;
import client.util.DoubleArrayNameParentNameRotation;
import client.util.DoubleListNameParentNameRotation;
import client.util.DoubleListNameParentNameRotationImpl;
import client.util.IntArrayNameParentNameRotation;
import client.util.KeyNameParentNameRotation;
import client.util.KeyPLEASENameParentNameRotation;
import client.util.MinValueMaxValueNameParentNameRotation;
import client.util.NameParentNameRotationFormatter;
import client.util.RotationFormatter;
import client.util.RuntimeComponentEntry;
import client.util.RuntimeComponentSupport;
import client.util.SelectedOptionNameParentNameRotation;
import client.util.SettingSerializerNameParentNameRotation;
import client.util.StringArrayNameParentNameRotation;
import client.util.StringListNameParentNameRotation;
import client.util.ValueNameParentNameRotation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SettingSerializer implements SupplementalListener {
   public static final SettingSerializer advancedItemEntry = new SettingSerializer();
   public final NameParentNameRotationFormatter nameParentNameRotationFormatter;
   public final NameParentNameRotationFormatter nameParentNameRotationFormatter2;
   public final NameParentNameRotationFormatter nameParentNameRotationFormatter3;
   public NameParentNameRotationFormatter nameParentNameRotationFormatter4 = new RuntimeComponentEntry(this);
   public final NameParentNameRotationFormatter nameParentNameRotationFormatter5;
   public final NameParentNameRotationFormatter nameParentNameRotationFormatter6;
   public final NameParentNameRotationFormatter nameParentNameRotationFormatter7;
   public final NameParentNameRotationFormatter nameParentNameRotationFormatter8;
   public final NameParentNameRotationFormatter nameParentNameRotationFormatter9;
   public final NameParentNameRotationFormatter nameParentNameRotationFormatter10;
   public final NameParentNameRotationFormatter nameParentNameRotationFormatter11;
   public final NameParentNameRotationFormatter nameParentNameRotationFormatter12;
   public final NameParentNameRotationFormatter nameParentNameRotationFormatter13;
   public final NameParentNameRotationFormatter nameParentNameRotationFormatter14;

   @Override
   public RotationFormatter getRotationFormatter8() {
      return this.nameParentNameRotationFormatter13;
   }

   @Override
   public RotationFormatter getRotationFormatter11() {
      return this.nameParentNameRotationFormatter11;
   }

   @Override
   public RotationFormatter getRotationFormatter5() {
      return this.nameParentNameRotationFormatter9;
   }

   @Override
   public RotationFormatter getRotationFormatter6() {
      return this.nameParentNameRotationFormatter3;
   }

   @Override
   public RotationFormatter getRotationFormatter13() {
      return this.nameParentNameRotationFormatter5;
   }

   @Override
   public RotationFormatter getRotationFormatterAsRotationFormatte() {
      return this.nameParentNameRotationFormatter12;
   }

   @Override
   public RotationFormatter getRotationFormatter9() {
      return this.nameParentNameRotationFormatter7;
   }

   @Override
   public Gson getGson() {
      return new GsonBuilder().setPrettyPrinting().create();
   }

   @Override
   public RotationFormatter getRotationFormatter4() {
      return this.nameParentNameRotationFormatter10;
   }

   @Override
   public RotationFormatter getRotationFormatter2() {
      return this.nameParentNameRotationFormatter14;
   }

   @Override
   public RotationFormatter getRotationFormatter() {
      return this.nameParentNameRotationFormatter6;
   }

   @Override
   public RotationFormatter getRotationFormatter3() {
      return this.nameParentNameRotationFormatter;
   }

   @Override
   public RotationFormatter getRotationFormatter12() {
      return this.nameParentNameRotationFormatter8;
   }

   @Override
   public RotationFormatter getRotationFormatter7() {
      return this.nameParentNameRotationFormatter2;
   }

   @Override
   public RotationFormatter getRotationFormatter10() {
      return this.nameParentNameRotationFormatter4;
   }

   public SettingSerializer() {
      this.nameParentNameRotationFormatter2 = new SettingSerializerNameParentNameRotation(this);
      this.nameParentNameRotationFormatter3 = new RuntimeComponentSupport(this);
      this.nameParentNameRotationFormatter5 = new DoubleArrayNameParentNameRotation(this);
      this.nameParentNameRotationFormatter13 = new DoubleListNameParentNameRotationImpl(this);
      this.nameParentNameRotationFormatter14 = new ValueNameParentNameRotation(this);
      this.nameParentNameRotationFormatter = new IntArrayNameParentNameRotation(this);
      this.nameParentNameRotationFormatter6 = new DoubleListNameParentNameRotation(this);
      this.nameParentNameRotationFormatter11 = new KeyNameParentNameRotation(this);
      this.nameParentNameRotationFormatter10 = new MinValueMaxValueNameParentNameRotation(this);
      this.nameParentNameRotationFormatter9 = new KeyPLEASENameParentNameRotation(this);
      this.nameParentNameRotationFormatter8 = new StringArrayNameParentNameRotation(this);
      this.nameParentNameRotationFormatter12 = new StringListNameParentNameRotation(this);
      this.nameParentNameRotationFormatter7 = new SelectedOptionNameParentNameRotation(this);
   }
}
