package client.util;

public abstract class AbstractLISTManagerRecord {
   public String labelText;

   public abstract LISTManager getLISTManager() throws SecondaryExceptionAdapter;
}
