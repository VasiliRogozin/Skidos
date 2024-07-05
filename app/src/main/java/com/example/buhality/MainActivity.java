package com.example.buhality;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Char> chars = new ArrayList<Char>();
    BoxAdapter boxAdapter;


    private TextView summaText;
 //   private ImageButton delbtn, plusbtn;
    private ListView listView;
     ArrayList<Float> monyF = new ArrayList<>();
     ArrayList<Float> rezF = new ArrayList<>();
    public ArrayList<String> idA = new ArrayList<>();
     ArrayList<String> nameA = new ArrayList<>();
     ArrayList<String> monyA = new ArrayList<>();
    private ArrayList<String> rezA = new ArrayList<>();
    private ArrayList<String> textA = new ArrayList<>();
    private Button sum;

//    private float limsum, index;
    private int size = 0;
    private float summa;
    float x = 0;
    float z = 0;
    private String l;

    @Override
    protected void onStop() {
        super.onStop();
        save();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        summaText = findViewById(R.id.summa);

        load();
        Sum();

        fillData();
        boxAdapter = new BoxAdapter(this, chars);
        listView = findViewById(R.id.listView);
        listView.setAdapter(boxAdapter);

    }

    private void fillData() {
        chars.clear();
        for (int i =0; i <= idA.size()-1; i ++){

            String ds = String.format("%.2f", rezF.get(i));

            chars.add(new Char(idA.get(i), nameA.get(i), monyA.get(i), ds, rezF.get(i)));

        }

    }



    public void btnPlus(View v){
        size = size+1;
        idA.add(String.valueOf(idA.size()+1));
        nameA.add("");
        monyA.add("0");
        rezA.add("100");
        monyF.add(10F);
        rezF.add(10F);


        sav();
        Sum();

        fillData();
        boxAdapter = new BoxAdapter(this, chars);
        listView = findViewById(R.id.listView);
        //https://vikaskanani.wordpress.com/2011/07/27/android-focusable-edittext-inside-listview/ взял от сюда
        listView.setItemsCanFocus(true);
        listView.setAdapter(boxAdapter);



    }

    public void btnMinus(View v){

        idA.remove(String.valueOf(idA.size()));
        nameA.remove(String.valueOf(nameA.size()));
        monyA.remove(String.valueOf(monyA.size()));
        rezA.remove(String.valueOf(rezA.size()));
        rezF.remove(String.valueOf(rezF.size()));
        monyF.remove(String.valueOf(monyF.size()));
        size = size-1;

        sav();

        Sum();

        fillData();
        boxAdapter = new BoxAdapter(this, chars);
        listView = findViewById(R.id.listView);
        listView.setAdapter(boxAdapter);


    }

    private void sav() {
       LayoutInflater LInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       int count = boxAdapter.getCount();
       for (int i = 0; i <= size-1; i++) {
         View view = listView.getChildAt(i);
         if (view == null){

            break;
         }
         else
         {
          int t = boxAdapter.getCount();
//https://translated.turbopages.org/proxy_u/en-ru.ru.f2196a71-662e9814-d14b7fd1-74722d776562/https/stackoverflow.com/questions/17691534/how-to-get-value-of-the-edittext-in-the-adapter-in-androi
            int zx = (int) boxAdapter.getItemId(i);
            TextView TV = (TextView) listView.getChildAt(i).findViewById(R.id.id);
            int x = Integer.parseInt(TV.getText().toString());
            int z = x-1;
            EditText ET = (EditText) listView.getChildAt(i).findViewById(R.id.name);
            String et = ET.getText().toString();
            nameA.set(z, String.valueOf(et));
            EditText Et = (EditText) view.findViewById(R.id.mony);
            String ett= Et.getText().toString();
            monyA.set(z, ett);
       }
       }
    }
//
//    private void coloring (){
//        for (int i = 0; i <= size-1; i++) {
//            View vi = listView.getChildAt(i);
//            if (vi == null){
//                break;
//            }
//            else
//            {
//                TextView TV = (TextView) listView.getChildAt(i).findViewById(R.id.id);
//                int x = Integer.parseInt(TV.getText().toString());
//                int z = x-1;
//
//                if (rezF.get(z) < 0 ) ((TextView) vi.findViewById(R.id.rez)).setTextColor(Color.GREEN);
//                if (rezF.get(z) == 0 ) ((TextView) vi.findViewById(R.id.rez)).setTextColor(Color.GRAY);
//                if (rezF.get(z) > 0 ) ((TextView) vi.findViewById(R.id.rez)).setTextColor(Color.RED);
//
//            }
//        }
//
//    }


    private boolean save(){
        SharedPreferences sPref = getSharedPreferences("MyPref6", MODE_PRIVATE);

        SharedPreferences.Editor ed = sPref.edit();

        ed.putInt("Status_idA_size", idA.size());
        for(int i=0;i<idA.size();i++)
        {
            ed.remove("Status_idA_" + i);
            ed.putString("Status_idA_" + i, String.valueOf(idA.get(i)));
        }

        ed.putInt("Status_nameA_size", idA.size());
        for(int i=0;i<nameA.size();i++)
        {
            ed.remove("Status_nameA_" + i);
            ed.putString("Status_nameA_" + i, String.valueOf(nameA.get(i)));
        }

        ed.putInt("Status_monyA_size", idA.size());
        for(int i=0;i<monyA.size();i++)
        {
            ed.remove("Status_monyA_" + i);
            ed.putString("Status_monyA_" + i, String.valueOf(monyA.get(i)));
        }



        ed.putInt("Status_rezA_size", idA.size());
        for(int i=0;i<rezA.size();i++)
        {
            ed.remove("Status_rezA_" + i);
            ed.putString("Status_rezA_" + i, String.valueOf(rezA.get(i)));
        }

        ed.putInt("Status_rezF_size", idA.size());
        for(int i=0;i<rezF.size();i++)
        {
            ed.remove("Status_rezF_" + i);
            ed.putFloat("Status_rezF_" + i, Float.valueOf(rezF.get(i)));
        }
        ed.putInt("Status_monyF_size", idA.size());
        for(int i=0;i<monyF.size();i++)
        {
            ed.remove("Status_monyF_" + i);
            ed.putFloat("Status_monyF_" + i, Float.valueOf(monyF.get(i)));
        }

        ed.putInt("x",size);

        return ed.commit();
    }
    private void load() {

        SharedPreferences sPref = getSharedPreferences("MyPref6", MODE_PRIVATE);

        size = sPref.getInt("x", 0);

        idA.clear();
        int size1 = sPref.getInt("Status_idA_size", 0);
        for(int i=0;i<size;i++)
        {
            idA.add(sPref.getString("Status_idA_" + i, String.valueOf(0)));
        }

        monyA.clear();
        int size2 = sPref.getInt("Status_monyA_size", 0);
        for(int i=0;i<size;i++)
        {
            monyA.add(sPref.getString("Status_monyA_" + i, "null"));
        }

        rezA.clear();
        int size3 = sPref.getInt("Status_rezA_size", 0);
        for(int i=0;i<size;i++)
        {
            rezA.add(sPref.getString("Status_rezA_" + i, "null"));
        }

        nameA.clear();
        int size4 = sPref.getInt("Status_mameA_size", 0);
        for(int i=0;i<size;i++)
        {
            nameA.add(sPref.getString("Status_nameA_" + i, "null"));
        }

        rezF.clear();
        int size5 = sPref.getInt("Status_rezF_size", 0);
        for(int i=0;i<size;i++)
        {
            rezF.add(sPref.getFloat("Status_rezF_" + i, 0));
        }
        monyF.clear();
        int size6 = sPref.getInt("Status_monyF_size", 0);
        for(int i=0;i<size;i++)
        {
            monyF.add(sPref.getFloat("Status_monyF_" + i, 0));
        }


    }


    public void btnSumma(View view) {
        summaText.setText(String.valueOf(summa));
        sav();
        Sum();
        summaText.setText(String.valueOf(summa));



        fillData();
        boxAdapter = new BoxAdapter(this, chars);
        listView = findViewById(R.id.listView);
        listView.setAdapter(boxAdapter);

        save();
        load();


    }

    private void Sum() {
        x = 0;
        z = 0;
        for (int i =0; i <= monyF.size()-1; i ++){
            monyF.set(i, Float.parseFloat(monyA.get(i)));
        }
        for (int i =0; i <= size-1; i ++){
            x = x + monyF.get(i);
        }
        z = x / size;
        summa = x;
        summaText.setText(String.valueOf(x));
        for (int i =0; i <= idA.size()-1; i ++){
            Float zx = z - monyF.get(i);
            rezF.set(i, zx);
            rezA.set(i, String.valueOf(zx));
        }
    }
    // Текст в буфер обмена
    public  void btnCopy(View view)
    {
        String textt ="";

        textA.clear();
        for (int i =0; i <= idA.size()-1; i ++){
            String s = nameA.get(i) + "                     ";
            s = s.substring(0, 10);
            String ds = String.format("%.2f", rezF.get(i));

            textt = idA.get(i) + " I " + s + " I " + monyA.get(i) + " I "+ ds;

            textA.add(textt);
        }

      String text ="";
      for (int i =0; i <= idA.size()-1; i ++){
          text = text + textA.get(i) + "\n";

      }

        copyTextToClipboard(this, text);}

    public void copyTextToClipboard(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Текст скопирован в буфер обмена", Toast.LENGTH_SHORT).show();
    }

}
