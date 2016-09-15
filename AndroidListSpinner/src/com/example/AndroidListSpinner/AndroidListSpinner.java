package com.example.AndroidListSpinner;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AndroidListSpinner extends Activity {

    Button btnMove;
    Spinner MySpinner1, MySpinner2;
    List<String> myList1, myList2;
    private ArrayAdapter<String> myAdapter1, myAdapter2;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnMove = (Button)findViewById(R.id.move);
        MySpinner1 = (Spinner)findViewById(R.id.myspinner1);
        MySpinner2 = (Spinner)findViewById(R.id.myspinner2);

        initList();
        myAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myList1);
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        MySpinner1.setAdapter(myAdapter1);

        myAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myList2);
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        MySpinner2.setAdapter(myAdapter2);

        btnMove.setOnClickListener(MoveOnClickListener);

    }

    Button.OnClickListener MoveOnClickListener = new Button.OnClickListener() {

        @Override
            public void onClick(View arg0){
            int pos = MySpinner1.getSelectedItemPosition();

            if(pos != AdapterView.INVALID_POSITION){
                myList2.add( myList1.get(pos) );
                //myList1.remove(pos);
                myAdapter1.notifyDataSetChanged();
                myAdapter2.notifyDataSetChanged();
            }
        }};

    void initList(){
        myList1 = new ArrayList<String>();
        myList1.add("Sunday");
        myList1.add("Monday");
        myList1.add("Tuesday");
        myList1.add("Wednesday");
        myList1.add("Thursday");
        myList1.add("Friday");
        myList1.add("Saturday");

       myList2 = new ArrayList<String>();
    }

}
