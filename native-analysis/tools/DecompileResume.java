// Resume decompilation while restarting the native decompiler for each function.
import ghidra.app.decompiler.DecompInterface;
import ghidra.app.decompiler.DecompileOptions;
import ghidra.app.decompiler.DecompileResults;
import ghidra.app.script.GhidraScript;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.FunctionIterator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DecompileResume extends GhidraScript {
    @Override
    public void run() throws Exception {
        String[] args = getScriptArgs();
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: DecompileResume.java <output.c> <start-address-hex>");
        }
        long startAddress = Long.parseUnsignedLong(args[1].replaceFirst("^0x", ""), 16);
        int total = currentProgram.getFunctionManager().getFunctionCount();
        int done = 0;
        int failed = 0;
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(args[0], true)))) {
            out.println("\n/* Resumed with a fresh native decompiler process per function. */");
            FunctionIterator functions = currentProgram.getFunctionManager().getFunctions(true);
            while (functions.hasNext() && !monitor.isCancelled()) {
                Function function = functions.next();
                if (function.getEntryPoint().getOffset() < startAddress) {
                    continue;
                }
                out.println("\n/* Function " + function.getName() + " at " + function.getEntryPoint() + " */");
                DecompInterface decompiler = new DecompInterface();
                try {
                    decompiler.setOptions(new DecompileOptions());
                    decompiler.toggleCCode(true);
                    decompiler.toggleSyntaxTree(false);
                    if (!decompiler.openProgram(currentProgram)) {
                        throw new IllegalStateException("Could not open program in decompiler");
                    }
                    DecompileResults result = decompiler.decompileFunction(function, 0, monitor);
                    if (result.decompileCompleted() && result.getDecompiledFunction() != null) {
                        out.println(result.getDecompiledFunction().getC());
                    } else {
                        failed++;
                        out.println("/* DECOMPILATION FAILED: " + result.getErrorMessage() + " */");
                    }
                } catch (Exception exc) {
                    failed++;
                    out.println("/* DECOMPILATION FAILED: " + exc + " */");
                } finally {
                    decompiler.dispose();
                    out.flush();
                }
                done++;
                if (done % 10 == 0 || !functions.hasNext()) {
                    println("Resumed " + done + " functions from 0x" + Long.toHexString(startAddress) +
                        "; failed=" + failed + "; all program functions=" + total);
                }
            }
        }
        println("RESUME COMPLETE: " + done + " functions; failed=" + failed);
    }
}
