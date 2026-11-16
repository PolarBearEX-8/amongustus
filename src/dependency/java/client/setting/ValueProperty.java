package client.setting;

import client.event.Listener;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ValueProperty implements ModeOptionConfigurable {
   public Supplier supplier;
   public Object dataRef2;
   public boolean enabled;
   public Consumer consumer = null;
   public ArrayList arrayList;
   public Object dataRef;
   public ModuleModeOption moduleModeOption;
   public final String labelText;
   public Consumer consumer2 = null;

   public Object getDataRef() {
      return this.dataRef;
   }

   public void applyTarget(Object entry) {
      if (this.consumer != null && entry != this.getValue()) {
         this.consumer.accept(entry);
      }

      if (this.consumer2 != null) {
         this.consumer2.accept(entry);
      }

      this.applyObject2(entry);
   }

   public ValueProperty withConsumer(Consumer consumer3) {
      this.consumer = consumer3;
      return this;
   }

   @Override
   public void setModuleModeOption(ModuleModeOption moduleModeOption2) {
      this.moduleModeOption = moduleModeOption2;
   }

   public void add(Runnable task) {
      this.arrayList.add(task);
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void updateState() {
      Object dataRef3 = this.dataRef;
      this.applyTarget(dataRef3);
   }

   public Object getValue() {
      return this.dataRef2;
   }

   public ValueProperty withSupplier(Supplier supplier2) {
      this.supplier = supplier2;
      return this;
   }

   @Override
   public String getString() {
      return this.labelText;
   }

   public ValueProperty withConsumer2(Consumer consumer3) {
      this.consumer2 = consumer3;
      return this;
   }

   @Override
   public ModuleModeOption getModuleModeOption() {
      return this.moduleModeOption;
   }

   public void applyObject2(Object entry) {
      if (!this.isEnabled()) {
         this.dataRef2 = entry;

         for (int index = 0; index < this.arrayList.size(); index++) {
            ((Runnable)this.arrayList.get(index)).run();
         }
      }
   }

   public void setDataRef(Object dataRef3) {
      this.dataRef = dataRef3;
   }

   @Override
   public JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper) {
      if (this.supplier == null) {
         return jsonObject;
      }

      Object entry = this.supplier.get();
      this.applyObject2(entry);
      return jsonObject;
   }

   public ValueProperty(ModuleModeOption moduleModeOption2, String text, Object entry, Object entry2) {
      this.supplier = null;
      this.arrayList = new ArrayList();
      this.enabled = false;
      this.moduleModeOption = moduleModeOption2;
      this.labelText = text;
      this.dataRef2 = entry;
      this.dataRef = entry2;
      this.moduleModeOption.add(this);
   }

   public ValueProperty(ModuleModeOption moduleModeOption2, String text, Object entry) {
      this.supplier = null;
      this.arrayList = new ArrayList();
      this.enabled = false;
      this.moduleModeOption = moduleModeOption2;
      this.labelText = text;
      this.dataRef2 = entry;
      this.dataRef = entry;
      this.moduleModeOption.add(this);
   }
}
