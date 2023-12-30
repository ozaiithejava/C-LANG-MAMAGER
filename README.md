# C-LANG-MAMAGER
C and C++ codes in using java 


## Usage:
```Java
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String cCode = "#include <stdio.h>\n\nint main() {\n    printf(\"Hello from C code!\\n\");\n    return 0;\n}";

        String cppCode = "#include <iostream>\n\nint main() {\n    std::cout << \"Hello from C++ code!\" << std::endl;\n    return 0;\n}";

        try {
            CLangsManager.runCode(cCode, CLangsManager.Lang.C);
            CLangsManager.runCode(cppCode, CLangsManager.Lang.CPP);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
