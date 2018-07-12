package com.example.my2048;

import java.util.Random;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class GameListener implements OnTouchListener,OnClickListener{
 private static final int MODE_PRIVATE = 0x0000;
private ImageView showMap;
 private GameActivity game;
 private Editor editor ;
 private SharedPreferences sp;
 private RectF rect;
 private Rect r;
 private Bitmap map,a,b,c,d,e,f,g,h,p,q,k;
 private Paint paint;
 private int ge=4;
 private int da;
 private Canvas canvas;
 private int grade=0,maxGrade=0;//储存分数
 private Random rand = new Random();
 private int i1 = 0, j1 = 0, i2 = 0, j2 = 0;
 private float x1,y1,x2,y2,count=0;
 private int array[][]=new int[ge][ge];//储存游戏数据
 public GameListener(ImageView showMap,GameActivity game){
	 this.game=game;
	 this.showMap=showMap;
	 editor= game.getSharedPreferences("userData", MODE_PRIVATE).edit();
	 sp=game.getSharedPreferences("userData",MODE_PRIVATE);
 }
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action =event.getAction();
		if(action==MotionEvent.ACTION_DOWN){
			x1=event.getX();
			y1=event.getY();
		}
		if(action==MotionEvent.ACTION_UP){
			x2=event.getX();
			y2=event.getY();
			{
				if(y1-y2>0&&Math.abs(y1-y2)>Math.abs(x1-x2)){
					for (int c = 0; c < array[0].length; c++) {// 控制列数
						for (int r = 0; r < array.length; r++) {// 控制行循环
							if (array[r][c] == 0){// 判断该位置是否为空
								for (int r1 = r + 1; r1 < array.length; r1++) {// 在r的基础上加1进行查找
								if (array[r1][c] != 0) {// 判断该位置是否不为空
								array[r][c] = array[r1][c];// 将不为空位置的数据存入到r，c位置
									array[r1][c] = 0;// 设置为空
									count++;// 表示移动
								break;// 跳出r1的循环
								}
							}
					}
							if (array[r][c] != 0) {// 判断该位置不为空
								for (int r1 = r + 1; r1 < array.length; r1++) {// 在r的基础上加1进行查找
									if (array[r1][c] == array[r][c]) {// 判断r1,c位置的值是否和r,c位置的相等
										array[r][c] = array[r1][c] * 2;// 进行数据相加
										grade+=array[r1][c];
										array[r1][c] = 0;// 设置为空
										count++;// 表示相加了
										break;// 跳出r1的循环
									} else if (array[r1][c] != 0) {// 判断是否为数字
										break;// 跳出r1的循环
									}
								}
							}
						}

					}
				}
				else if(y1-y2<0&&Math.abs(y1-y2)>Math.abs(x1-x2)){
					for (int c = 0; c < array[0].length; c++) {// 控制列数
						for (int r = array.length-1; r >= 0; r--) {// 控制行循环
							if (array[r][c] == 0) {// 判断该位置是否为空
								for (int r1 = r - 1; r1 >= 0; r1--) {// 在r的基础上加1进行查找
									if (array[r1][c] != 0) {// 判断该位置是否不为空
										array[r][c] = array[r1][c];// 将不为空位置的数据存入到r，c位置
										array[r1][c] = 0;// 设置为空
										count++;// 表示移动
										break;// 跳出r1的循环
									}
								}
							}
							if (array[r][c] != 0) {// 判断该位置不为空
								for (int r1 = r - 1; r1 >= 0; r1--) {// 在r的基础上加1进行查找
									if (array[r1][c] == array[r][c]) {// 判断r1,c位置的值是否和r,c位置的相等
										array[r][c] = array[r1][c] * 2;// 进行数据相加
										grade+=array[r1][c];
										array[r1][c] = 0;// 设置为空
										count++;// 表示相加了
										break;// 跳出r1的循环
									} else if (array[r1][c] != 0) {// 判断是否为数字
										break;// 跳出r1的循环
									}
								}
							}
						}

					}
				}
				else if(x1-x2<0&&Math.abs(x1-x2)>Math.abs(y1-y2)){
					for (int r = 0; r < array[0].length; r++) {// 控制行数
						for (int c =  array.length-1; c >= 0; c--) {// 控制列数
							if (array[r][c] == 0) {// 判断该位置是否为空
								for (int c1 = c - 1; c1 >= 0; c1--) {// 在r的基础上加1进行查找
									if (array[r][c1] != 0) {// 判断该位置是否不为空
										array[r][c] = array[r][c1];// 将不为空位置的数据存入到r，c位置
										array[r][c1] = 0;// 设置为空
										count++;// 表示移动
										break;// 跳出r1的循环
									}
								}
							}
							if (array[r][c] != 0) {// 判断该位置不为空
								for (int c1 = c - 1; c1 >= 0; c1--) {// 在r的基础上加1进行查找
									if (array[r][c1] == array[r][c]) {// 判断r1,c位置的值是否和r,c位置的相等
										array[r][c] = array[r][c1] * 2;// 进行数据相加
										grade+=array[r][c];
										array[r][c1] = 0;// 设置为空
										count++;// 表示相加了
										break;// 跳出r1的循环
									} else if (array[r][c1] != 0) {// 判断是否为数字
										break;// 跳出r1的循环
									}
								}
							}
						}

					}
				}
				else if(x1-x2>0&&Math.abs(x1-x2)>Math.abs(y1-y2)){
					for (int r = 0; r < array[0].length; r++) {// 控制行数
						for (int c = 0; c < array.length; c++) {// 控制列数
							if (array[r][c] == 0) {// 判断该位置是否为空
								for (int c1 = c + 1; c1 < array.length; c1++) {// 在r的基础上加1进行查找
									if (array[r][c1] != 0) {// 判断该位置是否不为空
										array[r][c] = array[r][c1];// 将不为空位置的数据存入到r，c位置
										array[r][c1] = 0;// 设置为空
										count++;// 表示移动
										break;// 跳出r1的循环
									}
								}
							}
							if (array[r][c] != 0) {// 判断该位置不为空
								for (int c1 = c + 1; c1 < array.length; c1++) {// 在r的基础上加1进行查找
									if (array[r][c1] == array[r][c]) {// 判断r1,c位置的值是否和r,c位置的相等
										array[r][c] = array[r][c1] * 2;// 进行数据相加
										grade+=array[r][c];
										array[r][c1] = 0;// 设置为空
										count++;// 表示相加了
										break;// 跳出r1的循环
									} else if (array[r][c1] != 0) {// 判断是否为数字
										break;// 跳出r1的循环
									}
								}
							}
						}

					}
				}
				
				}
			
		}
		if(action==MotionEvent.ACTION_MOVE){
		}
		winGame();
		loseGame();
		if (count > 0) {// 判断是否有移动或者相加
			initOneData();// 生成一个新的数据
			count = 0;// 标志位重置为0
			game.setGrade(grade);
		}
		 drawBackground();
		 return true;
	}
	public void loadImage(){
		if(map==null)
map=Bitmap.createBitmap(showMap.getWidth(), showMap.getHeight(), Config.ARGB_8888);
		if(a==null)
	a=BitmapFactory.decodeResource(game.getResources(), R.drawable.a);
		if(b==null)
			b=BitmapFactory.decodeResource(game.getResources(), R.drawable.b);
		if(c==null)
			c=BitmapFactory.decodeResource(game.getResources(), R.drawable.c);
		if(d==null)
			d=BitmapFactory.decodeResource(game.getResources(), R.drawable.d);
		if(e==null)
			e=BitmapFactory.decodeResource(game.getResources(), R.drawable.e);
		if(f==null)
			f=BitmapFactory.decodeResource(game.getResources(), R.drawable.f);
		if(g==null)
			g=BitmapFactory.decodeResource(game.getResources(), R.drawable.g);
		if(h==null)
			h=BitmapFactory.decodeResource(game.getResources(), R.drawable.h);
		if(p==null)
			p=BitmapFactory.decodeResource(game.getResources(), R.drawable.i);
		if(q==null)
			q=BitmapFactory.decodeResource(game.getResources(), R.drawable.j);
		if(k==null)
			k=BitmapFactory.decodeResource(game.getResources(), R.drawable.k);
		if(r==null)
				r=new Rect(0,0,a.getWidth(),a.getHeight());
			if(canvas==null) canvas=new Canvas(map);
			if(paint==null) paint=new Paint();
		
	}
	public void drawBackground(){
		    loadImage();
			paint.setColor(Color.GRAY);
			for(int i=0;i<array.length;i++){
				for(int j=0;j<array[i].length;j++){
					rect=new RectF((showMap.getWidth()/ge)*j+showMap.getWidth()/ge/43, 
							     (showMap.getHeight()/ge)*i+showMap.getHeight()/ge/48,
							     (showMap.getWidth()/ge)*(j+1)-showMap.getWidth()/ge/43,
							     (showMap.getHeight()/ge)*(i+1)-showMap.getHeight()/ge/48); 
					canvas.drawRoundRect(rect, 20, 20, paint);
					if(array[i][j]!=0){
						if(array[i][j]==2)
						canvas.drawBitmap(a, r, rect, paint);
						if(array[i][j]==4)
							canvas.drawBitmap(b, r, rect, paint);
						if(array[i][j]==8)
							canvas.drawBitmap(c, r, rect, paint);
						if(array[i][j]==16)
							canvas.drawBitmap(d, r, rect, paint);
						if(array[i][j]==32)
							canvas.drawBitmap(e, r, rect, paint);
						if(array[i][j]==64)
							canvas.drawBitmap(f, r, rect, paint);
						if(array[i][j]==128)
							canvas.drawBitmap(g, r, rect, paint);
						if(array[i][j]==256)
							canvas.drawBitmap(h, r, rect, paint);
						if(array[i][j]==512)
							canvas.drawBitmap(p, r, rect, paint);
						if(array[i][j]==1024)
							canvas.drawBitmap(q, r, rect, paint);
						if(array[i][j]==2048)
							canvas.drawBitmap(k, r, rect, paint);
						
					}
				}
			}
			showMap.setImageBitmap(map);
	}
	@Override
	public void onClick(View v) {
		Button b=(Button)v;
		String str=b.getText().toString();
		if("开始新游戏".equals(str)){
		getData();
		clear();
		data();
		drawBackground();
		}
	}
	public void data(){
		i1 = rand.nextInt(ge);
		j1 = rand.nextInt(ge);
		do {
			i2 = rand.nextInt(ge);
			j2 = rand.nextInt(ge);
		} while (i1 == i2 && j1 == j2);
		array[i1][j1] = rand.nextInt(2) * 2 + 2;
		array[i2][j2] = rand.nextInt(2) * 2 + 2;
	}
	public void clear(){
		grade = 0;
		game.setGrade(grade);
		for(int i=0;i<array.length;i++)
			for(int j=0;j<array[i].length;j++)
				array[i][j]=0;
	}
	public void initOneData() {
		int r = 0, c = 0;
		do {
			r = rand.nextInt(ge);
			c = rand.nextInt(ge);
		} while (array[r][c] != 0);
		array[r][c] = rand.nextInt(2) * 2 + 2;
	}
	public void keepData(){
		editor.putInt("maxGrade", maxGrade);
        editor.commit();
	}
	public int getData(){
		 da=sp.getInt("maxGrade", 0);
    	 game.setMaxGrade(da);
    	 return da;
	}
	public void winGame() {
		for (int i = 0; i < array[0].length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i][j] == 2048) {
					Toast.makeText(game, "你赢了!", Toast.LENGTH_LONG).show();
					break;
				}
			}
		}
	}
	public void loseGame() {
		int q = 0, p = 0, z = 0;
		for (int i = 0; i < array[0].length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i][j] != 0) {
					q++;
				}
			}
		}
		if (q == ge*ge) {
			for (int i = 0; i < array[0].length; i++) {
				for (int j = 0; j < array.length - 1; j++) {
					if (array[i][j] != array[i][j + 1]) {
						p++;
					}
				}
			}
			for (int j = 0; j < array[0].length; j++) {
				for (int i = 0; i < array.length - 1; i++) {
					if (array[i][j] != array[i + 1][j]) {
						z++;
					}
				}
			}
			if (p == ge*(ge-1) && z == ge*(ge-1)) {
				if(grade>getData()) {
					maxGrade=grade;
					keepData();
				}
				  getData();
				Toast.makeText(game, "游戏结束!", Toast.LENGTH_SHORT).show();
				
			}
		}

		q = 0;
		z = 0;
		p = 0;
	}
}


