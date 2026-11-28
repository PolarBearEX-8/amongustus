package wdl.api;

public interface IWDLModDescripted extends IWDLMod {
   String getDescription();

   String getURL();

   String getDisplayName();

   String[] getAuthors();

   String getMainAuthor();
}
