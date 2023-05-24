import java.io.IOException;

import static Controller.Create.*;
import static Controller.Read.*;
import static Controller.Update.*;
import static Controller.Delete.*;

public class APP {
    public static void main(String[] args) throws IOException {
        createGame("TESET CRUD",0.0);
        System.out.println(" ");
        findGameByTitle("TESET CRUD");
        System.out.println(" ");
        updateGame(findIdWithTitle("TESET CRUD"),"TESTE CRUD",10.0);
        System.out.println(" ");
        deleteGame(findIdWithTitle("TESTE CRUD"));
    }
}
