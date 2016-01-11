import service.MowerService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        Stream<String> infos;

        if (args.length == 2) {
            infos = Files.lines(Paths.get(args[1]));
        } else {
            InputStream is = Main.class.getResourceAsStream("TEST");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            infos = reader.lines();
        }

        MowerService service = new MowerService();
        service.init(infos);
        service.start();
    }
}
