package net.augustus.settings.property;

import net.augustus.Augustus;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.augustus.modules.FeatureBase;

public abstract class AbstractConfigProperty implements ConfigProperty {
   private ArrayList changeListeners;
   private FeatureBase featureBase;
   private boolean locked;
   private Object defaultValue;
   private final String name;
   private Supplier valueSupplier;
   private Consumer onValueChanged;
   private Object value;
   private Consumer onValueApplied;

   public AbstractConfigProperty withValueSupplier(Supplier supplier) {
      this.valueSupplier = supplier;
      return this;
   }

   private void invokeConsumerSafely(Consumer consumer, Object obj) {
      try {
         consumer.accept(obj);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void addChangeListener(Runnable runnable) {
      this.changeListeners.add(runnable);
   }

   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      if (this.valueSupplier != null) {
         this.setValue(this.valueSupplier.get());
      }

      return json;
   }

   public String getName() {
      return this.name;
   }

   public void setFeatureBase(FeatureBase featureBase2) {
      this.featureBase = featureBase2;
   }

   public void setValue(Object obj) {
      if (!this.isLocked()) {
         this.value = obj;

         for (int i = 0; i < this.changeListeners.size(); i++) {
            ((Runnable)this.changeListeners.get(i)).run();
         }
      }
   }

   public void resetToDefault() {
      this.applyValue(this.defaultValue);
   }

   public AbstractConfigProperty(FeatureBase featureBase2, String str, Object obj, Object obj2) {
      this.onValueChanged = null;
      this.onValueApplied = null;
      this.valueSupplier = null;
      this.changeListeners = new ArrayList();
      this.locked = false;
      this.featureBase = featureBase2;
      this.name = str;
      this.value = obj;
      this.defaultValue = obj2;
      this.featureBase.addProperty(this);
   }

   public FeatureBase getFeatureBase() {
      return this.featureBase;
   }

   public void applyValue(Object obj) {
      if (this.onValueChanged != null && obj != this.getValue()) {
         this.invokeConsumerSafely(this.onValueChanged, obj);
      }

      if (this.onValueApplied != null) {
         this.invokeConsumerSafely(this.onValueApplied, obj);
      }

      this.setValue(obj);
   }

   public boolean isLocked() {
      return this.locked;
   }

   public Object getDefaultValue() {
      return this.defaultValue;
   }

   public Object getValue() {
      return this.value;
   }

   public AbstractConfigProperty withOnValueChanged(Consumer consumer) {
      this.onValueChanged = consumer;
      return this;
   }

   public AbstractConfigProperty withOnValueApplied(Consumer consumer) {
      this.onValueApplied = consumer;
      return this;
   }

   public AbstractConfigProperty(FeatureBase featureBase2, String str, Object obj) {
      this.onValueChanged = null;
      this.onValueApplied = null;
      this.valueSupplier = null;
      this.changeListeners = new ArrayList();
      this.locked = false;
      this.featureBase = featureBase2;
      this.name = str;
      this.value = obj;
      this.defaultValue = obj;
      this.featureBase.addProperty(this);
   }

   public void setLocked(boolean flag) {
      this.locked = flag;
   }

   public void setDefaultValue(Object obj) {
      this.defaultValue = obj;
   }



}
