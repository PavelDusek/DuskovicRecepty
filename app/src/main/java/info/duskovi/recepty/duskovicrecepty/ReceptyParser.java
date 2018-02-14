package info.duskovi.recepty.duskovicrecepty;

import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReceptyParser {
    public static List<Recept> parse(XmlResourceParser parser) throws XmlPullParserException, IOException {
        List<Recept> recepty = new ArrayList<Recept>();
        String tagName = "";
        String nazev = "";
        List<Prisada> prisady = new ArrayList<Prisada>();
        String postup = "";
        String prisadaNazev;
        String prisadaMnozstvi;
        String prisadaJednotka;

        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                tagName = parser.getName();
                switch (tagName) {
                    case "recept":
                        //define everything newly
                        nazev = "";
                        prisady = new ArrayList<Prisada>();
                        postup = "";
                        break;
                    case "nazev":
                        break;
                    case "prisada":
                        prisadaNazev = parser.getAttributeValue(null, "nazev");
                        prisadaMnozstvi = parser.getAttributeValue(null, "mnozstvi");
                        prisadaJednotka = parser.getAttributeValue(null, "jednotka");
                        prisady.add(new Prisada(prisadaNazev, prisadaMnozstvi, prisadaJednotka));
                        break;
                    case "postup":
                        break;
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                tagName = parser.getName();
                switch (tagName) {
                    case "recept":
                        recepty.add(new Recept(nazev, prisady, postup));
                        break;
                    case "nazev":
                        break;
                    case "prisada":
                        break;
                    case "postup":
                        break;
                }
                tagName = "";
            } else if (eventType == XmlPullParser.TEXT) {
                switch (tagName) {
                    case "recept":
                        break;
                    case "nazev":
                        nazev = parser.getText();
                        break;
                    case "prisada":
                        break;
                    case "postup":
                        postup = parser.getText();
                        break;
                }
            }
            eventType = parser.next();
        }
        return recepty;
    }
}
