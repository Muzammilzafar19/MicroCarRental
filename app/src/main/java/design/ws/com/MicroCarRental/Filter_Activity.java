package design.ws.com.MicroCarRental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;

import Adapters.ItemData_Cusine;
import Adapters.Spinner_Cusine_DataAdapter;

public class Filter_Activity extends AppCompatActivity  {

    private LineChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);



        Spinner spinner =  findViewById(R.id.spinner);
        Spinner spinner1 =  findViewById(R.id.spinner1);

   //     mSeekBarX = (SeekBar) findViewById(R.id.rangeSeekbar1);




        final CrystalRangeSeekbar rangeSeekbar = findViewById(R.id.rangeSeekbar1);

        // get min and max text view
        final TextView tvMin = findViewById(R.id.textMin1);
        final TextView tvMax =  findViewById(R.id.textMin2);

// set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText("Rs " + String.valueOf(minValue));
                tvMax.setText("Rs " + String.valueOf(maxValue));
            }
        });

// set final value listener
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });




        ArrayList<ItemData_Cusine> list = new ArrayList<>();


        list.add(new ItemData_Cusine("Select Cusine"));
        list.add(new ItemData_Cusine("Pakistan"));
        list.add(new ItemData_Cusine("South Inidan"));
        list.add(new ItemData_Cusine("Italian"));
        Spinner sp = findViewById(R.id.spinner);
        Spinner_Cusine_DataAdapter adapter = new Spinner_Cusine_DataAdapter(this, R.layout.spinner_list_cusine, R.id.data, list);
        sp.setAdapter(adapter);
        spinner.setAdapter(adapter);



        ArrayList<ItemData_Cusine> list1 = new ArrayList<>();


        list1.add(new ItemData_Cusine("None"));
        list1.add(new ItemData_Cusine("Vegan "));
        list1.add(new ItemData_Cusine("Ovo-Vegetarian"));
        list1.add(new ItemData_Cusine("Pescetarians "));
        Spinner sp1 = findViewById(R.id.spinner1);
        Spinner_Cusine_DataAdapter adapter1 = new Spinner_Cusine_DataAdapter(this, R.layout.spinner_list_cusine, R.id.data, list1);
        sp1.setAdapter(adapter1);
        spinner1.setAdapter(adapter1);


    }


}
