package com.example.wanwan.ShowHistory;

import org.afree.chart.AFreeChart;
import org.afree.chart.ChartFactory;
import org.afree.chart.plot.CategoryPlot;
import org.afree.chart.plot.PiePlot;
import org.afree.chart.plot.PlotOrientation;
import org.afree.data.category.DefaultCategoryDataset;
import org.afree.data.general.DefaultPieDataset;
import org.afree.graphics.geom.Font;

import com.example.wanwan.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

public class ShowHistory extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_history);
        
		/*
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        data.addValue(10.38, "weight", "10:00");
        data.addValue(10.59, "weight", "13:00");
        data.addValue(11.77, "weight", "20:00");
        AFreeChart chart = ChartFactory.createLineChart(
        		"2013/01/23Åiã‡Åj",
        		"time",
        		"weight",
        		data,
        		PlotOrientation.VERTICAL,
        		true,
        		false,
        		false);
        		*/
        /* GraphView spcv = (GraphView) findViewById(R.id.spcv);
        spcv.setChart(chart); */
		
		WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		Log.d("ShowHistory", "width: "+ disp.getWidth());
		Log.d("ShowHistory", "height: " + disp.getHeight());
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.common_static, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent;
    	switch (item.getItemId()) {
    	case R.id.gotoTop:
    		Log.d("top", "option 'gotoTop' is clicked.");
    		intent = new Intent(ShowHistory.this, com.example.wanwan.Top.Top.class);
    		startActivity(intent);
    		break;
    		
    	case R.id.showHistory:
    		Log.d("top", "option 'showHistory' is clicked.");
    		break;

    	case R.id.inputWeight:
    		Log.d("top", "option 'inputWeight' is clicked.");
    		intent = new Intent(ShowHistory.this, com.example.wanwan.InputWeight.InputWeight.class);
    		startActivity(intent);
    		break;

    	case R.id.inputFood:
    		Log.d("top", "option 'inputFood' is clicked.");
    		intent = new Intent(ShowHistory.this, com.example.wanwan.InputFood.InputFood.class);
    		startActivity(intent);
    		break;

    	case R.id.switchFood:
    		Log.d("top", "option 'switchFood' is clicked.");
    		intent = new Intent(ShowHistory.this, com.example.wanwan.SwitchFood.SwitchFood.class);
    		startActivity(intent);
    		break;

    	case R.id.registerFood:
    		Log.d("top", "option 'registerFood' is clicked.");
    		intent = new Intent(ShowHistory.this, com.example.wanwan.RegisterFood.RegisterFood.class);
    		startActivity(intent);
    		break;
    		
    	case R.id.registerDog:
    		Log.d("top", "option 'registerDog' is clicked.");
    		intent = new Intent(ShowHistory.this, com.example.wanwan.RegisterDog.RegisterDog.class);
    		startActivity(intent);
    		break;
    		
    	case R.id.showTemplate:
    		Log.d("top", "option 'showTemplate' is clicked.");
    		break;
    		
    	default:
    		break;
    	}
    	return true;
    }
}
