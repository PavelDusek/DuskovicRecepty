package info.duskovi.recepty.duskovicrecepty;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup.LayoutParams;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ReceptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recept);

        String nazev = this.getIntent().getExtras().getString("nazev");
        ArrayList<String> prisadyNazvy = this.getIntent().getExtras().getStringArrayList("prisadyNazvy");
        ArrayList<String> prisadyMnozstvi = this.getIntent().getExtras().getStringArrayList("prisadyMnozstvi");
        ArrayList<String> prisadyJednotky = this.getIntent().getExtras().getStringArrayList("prisadyJednotky");
        String postup = this.getIntent().getExtras().getString("postup");
        setTitle(nazev);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        LinearLayout content = (LinearLayout) findViewById(R.id.recept_content);
        TextView view;
        for (int i = 0; i < prisadyNazvy.size(); i++ ) {
            String s = prisadyNazvy.get(i);
            if (prisadyMnozstvi.get(i) != null) {
                s += " " + prisadyMnozstvi.get(i);
            }
            if (prisadyJednotky.get(i) != null) {
                s += " " + prisadyJednotky.get(i);
            }
            view = new TextView(this);
            view.setLayoutParams(params);
            view.setText(s);
            //TODO style view, větší písmo
            content.addView(view);
        }
        //TODO kilojoules
        view = new TextView(this);
        view.setLayoutParams(params);
        view.setText(postup);
        content.addView(view);
    }
}
