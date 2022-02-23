import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Main {
    public static void main(String... args) throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("asdasd");
    }

    public static void testSomething() throws FileNotFoundException {
        Main.main("Dimo");
    }

    public static void testSomething2(){
        try {
            testSomething();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
