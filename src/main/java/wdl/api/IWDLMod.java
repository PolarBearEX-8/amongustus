package wdl.api;

public interface IWDLMod {
   boolean isValidEnvironment(String str);

   String getEnvironmentErrorMessage(String str);
}
