package info.duskovi.recepty.duskovicrecepty;
import java.util.List;

public class Recept {
    public final String nazev;
    public final List<Prisada> prisady;
    public final String postup;

    public Recept(String nazev, List<Prisada> prisady, String postup) {
        this.nazev = nazev;
        this.prisady = prisady;
        this.postup = postup;
    }
}
