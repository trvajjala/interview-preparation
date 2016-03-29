package output.streams;

import java.io.IOException;

public class AutoCloesableExample {

    public static void main(String[] args) throws Exception {

        try (MyStream stream = new MyStream()) {
            System.out.println("Executing sth");
        }
    }
}

class MyStream implements AutoCloseable {

    @Override
    public void close() throws IOException {
        System.out.println("Closing stream");
    }
}
