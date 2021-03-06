package com.example.bmi;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.os.Build;

import com.devadvance.circularseekbar.CircularSeekBar;

public class MainActivity extends ActionBarActivity {
		       // seekbar.getProgress();
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
  	  final  CircularSeekBar seekbar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);
		
        seekbar.getProgress();
        
    	final Dialog dialog = new Dialog(MainActivity.this);
		dialog.setContentView(R.layout.custom);
		
        Button ht = (Button) findViewById (R.id.button1);
        
        ht.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				dialog.setTitle("Select height");
				final NumberPicker pickerUnit = (NumberPicker) dialog.findViewById(R.id.pickerUnit);
				final NumberPicker pickerValue = (NumberPicker) dialog.findViewById(R.id.pickerValue);
				
				pickerUnit.setMinValue(0);
				pickerUnit.setMaxValue(1);
				pickerUnit.setDisplayedValues( new String[] { "cm", "ft inch" } );
				pickerValue.setMinValue(0);
				pickerValue.setMaxValue(200);
				dialog.show();
				
				
				
				Button btnSelect = (Button) dialog.findViewById(R.id.btnSelect);
				btnSelect.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						TextView txtHeight = (TextView) findViewById(R.id.textht);
						if(pickerUnit.getValue() == 0){
							txtHeight.setText(String.valueOf(pickerValue.getValue() + " " + String.valueOf(pickerUnit.getDisplayedValues()[pickerUnit.getValue()])));
						}
						else{
							txtHeight.setText(String.valueOf(pickerValue.getDisplayedValues()[pickerValue.getValue()] + " " + String.valueOf(pickerUnit.getDisplayedValues()[pickerUnit.getValue()])));
						}
						dialog.dismiss();
					}
				});
				
				Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
				btnCancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				
				
				pickerUnit.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
					
					@Override
					public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
						if(newVal == 0){
							pickerValue.setDisplayedValues(null);
							pickerValue.setMinValue(0);
							pickerValue.setMaxValue(200);
						}
						else if(newVal == 1){
							int index=0;
							pickerValue.setDisplayedValues(null);
							pickerValue.setMinValue(0);
							pickerValue.setMaxValue(59);
							String[] values = new String[60];
							for(int i=3; i<8; i++){
								for(int j=0; j<=11; j++){
									values[index] = String.valueOf(i) + "'" + String.valueOf(j) + "\"";
									index++;
								}
							}
							pickerValue.setDisplayedValues(values);
						}
					}
				});
			}
        	
        });
        
        Button wt = (Button) findViewById (R.id.button2);
        
        wt.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.setTitle("Select Weight");
				final NumberPicker pickerUnit = (NumberPicker) dialog.findViewById(R.id.pickerUnit);
				final NumberPicker pickerValue = (NumberPicker) dialog.findViewById(R.id.pickerValue);
				
				pickerUnit.setMinValue(0);
				pickerUnit.setMaxValue(1);
				pickerUnit.setDisplayedValues( new String[] { "kg", "lbs" } );
				pickerValue.setMinValue(0);
				pickerValue.setMaxValue(180);
				dialog.show();
				
				
				
				Button btnSelect = (Button) dialog.findViewById(R.id.btnSelect);
				btnSelect.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						TextView txtWeight = (TextView) findViewById(R.id.textwt);
						txtWeight.setText(String.valueOf(pickerValue.getValue()) + " " + String.valueOf(pickerUnit.getDisplayedValues()[pickerUnit.getValue()]));
						dialog.dismiss();
					}
				});
				
				Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
				btnCancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				
				
				
				pickerUnit.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
					
					@Override
					public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
						if(newVal == 0){
							pickerValue.setMinValue(0);
							pickerValue.setMaxValue(180);
						}
						else if(newVal == 1){
							pickerValue.setMinValue(0);
							pickerValue.setMaxValue(400);
						}
					
					}
				
        	
        });

			}
        	
        });
        
        Button calc=(Button) findViewById (R.id.calc);
        
        final TextView txtHeight = (TextView) findViewById(R.id.textht);
		final String heightString = (String) txtHeight.getText();
	    final TextView txtWeight = (TextView) findViewById(R.id.textwt);
		final String weightString = (String) txtWeight.getText();
		  final TextView comment = (TextView) findViewById(R.id.comment);	
		  
        calc.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
			//	if(heightString==null)
				//{comment.setText("Select Height");
				//}
			//else if(weightString==null)
			//{comment.setText("Select Weight");
		//	}
			//else{
			//calculation();}
				String unitHeight = (String) heightString.subSequence(heightString.indexOf(" ") + 1, heightString.length());
				float magnitudeHeight = 0f;
				if(unitHeight.equals("cm"))
					magnitudeHeight= Float.parseFloat((String) heightString.subSequence(0, heightString.indexOf(" ")));
				else if(unitHeight.equals("ft inch")){
					String feet = (String) heightString.subSequence(0, heightString.indexOf("'"));
					String inches = (String) heightString.subSequence(heightString.indexOf("'") + 1, heightString.indexOf(" ")-1);
					float inchtoft= (float) (Float.parseFloat(inches)*0.0833333);
					magnitudeHeight =Float.parseFloat(feet) + inchtoft;
					
				}				
				String unitWeight = (String) weightString.subSequence(weightString.indexOf(" ") + 1, weightString.length());
				float magnitudeWeight = Float.parseFloat((String) weightString.subSequence(0, weightString.indexOf(" ")));
				float BMIValue = 0f;
				
				if((unitHeight.equals("cm")) && (unitWeight.equals("kg"))){
					magnitudeHeight /= 100;
					BMIValue = magnitudeWeight/(magnitudeHeight * magnitudeHeight);
					
				}
				else if((unitHeight.equals("cm")) && (unitWeight.equals("lbs"))){
				//	magnitudeWeight *= 0.453592;
					magnitudeHeight *= 0.393701;   //cm to in
					BMIValue = (float) (magnitudeWeight/(magnitudeHeight * magnitudeHeight) * 703.06957964);
				}
				else if((unitHeight.equals("ft inch")) && (unitWeight.equals("lbs"))){
					magnitudeHeight *= 12;
					BMIValue = (float) ((magnitudeWeight/(magnitudeHeight * magnitudeHeight)) *703.06957964);
				}
				else if((unitHeight.equals("ft inch")) && (unitWeight.equals("kg"))){
				//	magnitudeWeight *= 2.20462;
					magnitudeHeight *= 0.3048;            //ft to m
					BMIValue = (float) (magnitudeWeight/(magnitudeHeight * magnitudeHeight));
				}
				else
					BMIValue = 0f;
				
				TextView txtBMIValue = (TextView) findViewById(R.id.bmivalue);
				if(BMIValue<=50)
				txtBMIValue.setText(String.format("%.1f", BMIValue));
				
				if(BMIValue < 18.5f)
					{comment.setText("Underweight");
					seekbar.setProgress(BMIValue);}
				else if((BMIValue < 24.9f) && (BMIValue > 18.5f))
					{comment.setText("Normal");
					seekbar.setProgress(BMIValue);}
				else if((BMIValue < 29f) && (BMIValue > 25f))
				{comment.setText("Overweight");
				seekbar.setProgress(BMIValue);}
				else if(BMIValue > 30 && BMIValue<50)
					{comment.setText("Obese");
					seekbar.setProgress(BMIValue);}
				else
					comment.setText("Invalid data");
			

				
			}
        });
		
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }
    
        public void calculation()
        {		}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
  /*  public void onClick(View v) {

		final Dialog dialog = new Dialog(MainActivity.this);
		dialog.setContentView(R.layout.custom);

		if(v.getId() == R.id.button1){
			dialog.setTitle("Select height");
			final NumberPicker pickerUnit = (NumberPicker) dialog.findViewById(R.id.pickerUnit);
			final NumberPicker pickerValue = (NumberPicker) dialog.findViewById(R.id.pickerValue);
			
			pickerUnit.setMinValue(0);
			pickerUnit.setMaxValue(1);
			pickerUnit.setDisplayedValues( new String[] { "cm", "feet" } );
			pickerValue.setMinValue(0);
			pickerValue.setMaxValue(200);
			dialog.show();
			
			
			
			Button btnSelect = (Button) dialog.findViewById(R.id.btnSelect);
			btnSelect.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					TextView txtHeight = (TextView) findViewById(R.id.txtHeight);
					if(pickerUnit.getValue() == 0){
						txtHeight.setText(String.valueOf(pickerValue.getValue() + " " + String.valueOf(pickerUnit.getDisplayedValues()[pickerUnit.getValue()])));
					}
					else{
						
						txtHeight.setText(String.valueOf(pickerValue.getDisplayedValues()[pickerValue.getValue()] + " " + String.valueOf(pickerUnit.getDisplayedValues()[pickerUnit.getValue()])));
					}
					dialog.dismiss();
				}
			});
			
			Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
			btnCancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			
			
			pickerUnit.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
				
				@Override
				public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
					if(newVal == 0){
						pickerValue.setDisplayedValues(null);
						pickerValue.setMinValue(0);
						pickerValue.setMaxValue(200);
					}
					else if(newVal == 1){
						int index=0;
						pickerValue.setDisplayedValues(null);
						pickerValue.setMinValue(0);
						pickerValue.setMaxValue(59);
						String[] values = new String[60];
						for(int i=3; i<8; i++){
							for(int j=0; j<=11; j++){
								values[index] = String.valueOf(i) + "'" + String.valueOf(j) + "\"";
								index++;
							}
						}
						pickerValue.setDisplayedValues(values);
					}
				}
			});
			
			
		}
    }
    */

}
