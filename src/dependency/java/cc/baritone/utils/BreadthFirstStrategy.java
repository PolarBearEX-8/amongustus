package cc.baritone.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;

public class BreadthFirstStrategy implements SuccessAlteningService {
   public String labelText8;

   @Override
   public List getList() {
      java.util.ArrayList list = new java.util.ArrayList();

      for (JsonElement jsonElement : this.resolveJsonElement("http://api.thealtening.com/v2/privates?key=" + this.labelText8).getAsJsonArray()) {
         if (jsonElement.isJsonObject()) {
            list.add(gson.fromJson(jsonElement, HeuristicOptimizer.class));
         }
      }

      return list;
   }

   @Override
   public boolean matchesTextByText(String text) throws RuntimeExceptionAdapter {
      JsonObject asJsonObject = this.resolveJsonElement("http://api.thealtening.com/v2/favorite?token=" + text + "&key=" + this.labelText8).getAsJsonObject();
      return this.matchesJsonObject(asJsonObject, (byte)0, 7089958, 15193099);
   }

   @Override
   public HeuristicOptimizer getHeuristicOptimizer() throws RuntimeExceptionAdapter {
      JsonObject asJsonObject = this.resolveJsonElement("http://api.thealtening.com/v2/generate?info=true&key=" + this.labelText8).getAsJsonObject();
      return (HeuristicOptimizer)gson.fromJson(asJsonObject, HeuristicOptimizer.class);
   }

   public BreadthFirstStrategy(String text) {
      this.labelText8 = text;
   }

   public CompletableFutureBreadthFirstStrategy getCompletableFutureBreadthFirs() {
      return new CompletableFutureBreadthFirstStrategy(this.labelText8);
   }

   @Override
   public void setLabelText8(String labelText) {
      this.labelText8 = labelText;
   }

   @Override
   public ControllerContext getControllerContext() throws RuntimeExceptionAdapter {
      JsonObject asJsonObject = this.resolveJsonElement("http://api.thealtening.com/v2/license?key=" + this.labelText8).getAsJsonObject();
      return (ControllerContext)gson.fromJson(asJsonObject, ControllerContext.class);
   }

   @Override
   public boolean matchesText(String text) throws RuntimeExceptionAdapter {
      JsonObject asJsonObject = this.resolveJsonElement("http://api.thealtening.com/v2/private?token=" + text + "&key=" + this.labelText8).getAsJsonObject();
      return this.matchesJsonObject(asJsonObject, (byte)0, 7089958, 15193099);
   }

   @Override
   public List getListAsList() {
      java.util.ArrayList list = new java.util.ArrayList();

      for (JsonElement jsonElement : this.resolveJsonElement("http://api.thealtening.com/v2/favorites?key=" + this.labelText8).getAsJsonArray()) {
         if (jsonElement.isJsonObject()) {
            list.add(gson.fromJson(jsonElement, HeuristicOptimizer.class));
         }
      }

      return list;
   }
}
