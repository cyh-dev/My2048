package com.example.my2048;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {
private int count=0;
private TextView grade,maxGrade;
    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ImageView showmap=(ImageView)this.findViewById(R.id.showmap);
        Button begin=(Button)this.findViewById(R.id.beginButton);
        GameListener game=new GameListener(showmap,this);
        showmap.setOnTouchListener(game);
        begin.setX(showmap.getWidth()/2-begin.getWidth()/2);
        begin.setOnClickListener(game);
        maxGrade = (TextView)this.findViewById(R.id.maxGrade);   
        grade = (TextView)this.findViewById(R.id.grade);   
        grade.setX(showmap.getWidth()/2-begin.getWidth()/2-grade.getWidth());
        maxGrade.setX(showmap.getWidth()/2+begin.getWidth()/2);
    }
    public void setGrade(int g){
    	
    	grade.setText(""+g);
    }
     public void setMaxGrade(int g){
    	 
    	maxGrade.setText(""+g);
    }
      @SuppressLint("ShowToast") public void onBackPressed(){
    	count++;
    	Toast.makeText(this, "再按一次退出2048",Toast.LENGTH_SHORT).show();
    	if(count==2){
    		super.onBackPressed();
    	}
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }
    
}
