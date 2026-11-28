package baritone.api.command.argparser;


import baritone.api.command.argument.ICommandArgument;
public interface IStatelessArgParser extends IArgParser {
   Object parseArg(ICommandArgument commandArgument) throws Exception;
}
