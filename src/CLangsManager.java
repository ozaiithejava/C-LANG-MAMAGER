import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CLangsManager {

    public enum Lang {
        C, CPP
    }

    private static final String C_CODE_FILE = "TempCode.c";
    private static final String EXECUTABLE_FILE = "TempExecutable";

    public static void runCode(String code, Lang lang) throws IOException, InterruptedException {
        switch (lang) {
            case C:
                writeCodeToFile(code, "c");
                compileCode("gcc");
                break;
            case CPP:
                writeCodeToFile(code, "cpp");
                compileCode("g++");
                break;
        }
        runExecutable();
        cleanup();
    }

    private static void writeCodeToFile(String code, String extension) throws IOException {
        try (FileWriter fileWriter = new FileWriter(C_CODE_FILE)) {
            fileWriter.write(code);
        }
    }

    private static void compileCode(String compiler) throws IOException, InterruptedException {
        String compileCommand = compiler + " -o " + EXECUTABLE_FILE + " " + C_CODE_FILE;

        Process compileProcess = Runtime.getRuntime().exec(compileCommand);
        compileProcess.waitFor();
    }

    private static void runExecutable() throws IOException, InterruptedException {
        String runCommand = "./" + EXECUTABLE_FILE;

        Process runProcess = Runtime.getRuntime().exec(runCommand);

        Scanner scanner = new Scanner(runProcess.getInputStream());
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }

        runProcess.waitFor();
    }

    private static void cleanup() {
        Path codePath = Paths.get(C_CODE_FILE);
        Path executablePath = Paths.get(EXECUTABLE_FILE);

        try {
            Files.deleteIfExists(codePath);
            Files.deleteIfExists(executablePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
