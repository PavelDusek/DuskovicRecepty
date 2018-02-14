package info.duskovi.recepty.duskovicrecepty;
        import android.content.Context;
        import android.content.Intent;
        import android.content.res.XmlResourceParser;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;

        import org.xmlpull.v1.XmlPullParserException;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        final List<Recept> recepty;

        try {
            XmlResourceParser parser = getResources().getXml(R.xml.recepty);
            recepty = ReceptyParser.parse(parser);


            String[] nazvy = new String[recepty.size()];
            for (int i = 0; i < recepty.size(); i++) {
                Recept recept = recepty.get(i);
                nazvy[i] = recept.nazev;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, nazvy);
            listView.setAdapter(adapter);
            //TODO search recepty, addTextChangeListener
            final Context context = this;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Recept vybranyRecept = recepty.get(position);

                    //implementovat IParcelable
                    ArrayList<String> prisadyNazvy = new ArrayList<String>();
                    ArrayList<String> prisadyMnozstvi = new ArrayList<String>();
                    ArrayList<String> prisadyJednotky = new ArrayList<String>();
                    for (int i = 0; i < vybranyRecept.prisady.size(); i++) {
                        prisadyNazvy.add(vybranyRecept.prisady.get(i).nazev);
                        prisadyMnozstvi.add(vybranyRecept.prisady.get(i).mnozstvi);
                        prisadyJednotky.add(vybranyRecept.prisady.get(i).jednotka);
                    }

                    Intent receptIntent = new Intent(context, ReceptActivity.class);
                    receptIntent.putExtra("nazev", vybranyRecept.nazev);
                    receptIntent.putStringArrayListExtra("prisadyNazvy", prisadyNazvy);
                    receptIntent.putStringArrayListExtra("prisadyMnozstvi", prisadyMnozstvi);
                    receptIntent.putStringArrayListExtra("prisadyJednotky", prisadyJednotky);
                    receptIntent.putExtra("postup", vybranyRecept.postup);
                    startActivity(receptIntent);
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (XmlPullParserException xppe) {
            xppe.printStackTrace();
        }
    }
}
