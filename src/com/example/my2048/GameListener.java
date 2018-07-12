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
 private int grade=0,maxGrade=0;//�������
 private Random rand = new Random();
 private int i1 = 0, j1 = 0, i2 = 0, j2 = 0;
 private float x1,y1,x2,y2,count=0;
 private int array[][]=new int[ge][ge];//������Ϸ����
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
					for (int c = 0; c < array[0].length; c++) {// ��������
						for (int r = 0; r < array.length; r++) {// ������ѭ��
							if (array[r][c] == 0){// �жϸ�λ���Ƿ�Ϊ��
								for (int r1 = r + 1; r1 < array.length; r1++) {// ��r�Ļ����ϼ�1���в���
								if (array[r1][c] != 0) {// �жϸ�λ���Ƿ�Ϊ��
								array[r][c] = array[r1][c];// ����Ϊ��λ�õ����ݴ��뵽r��cλ��
									array[r1][c] = 0;// ����Ϊ��
									count++;// ��ʾ�ƶ�
								break;// ����r1��ѭ��
								}
							}
					}
							if (array[r][c] != 0) {// �жϸ�λ�ò�Ϊ��
								for (int r1 = r + 1; r1 < array.length; r1++) {// ��r�Ļ����ϼ�1���в���
									if (array[r1][c] == array[r][c]) {// �ж�r1,cλ�õ�ֵ�Ƿ��r,cλ�õ����
										array[r][c] = array[r1][c] * 2;// �����������
										grade+=array[r1][c];
										array[r1][c] = 0;// ����Ϊ��
										count++;// ��ʾ�����
										break;// ����r1��ѭ��
									} else if (array[r1][c] != 0) {// �ж��Ƿ�Ϊ����
										break;// ����r1��ѭ��
									}
								}
							}
						}

					}
				}
				else if(y1-y2<0&&Math.abs(y1-y2)>Math.abs(x1-x2)){
					for (int c = 0; c < array[0].length; c++) {// ��������
						for (int r = array.length-1; r >= 0; r--) {// ������ѭ��
							if (array[r][c] == 0) {// �жϸ�λ���Ƿ�Ϊ��
								for (int r1 = r - 1; r1 >= 0; r1--) {// ��r�Ļ����ϼ�1���в���
									if (array[r1][c] != 0) {// �жϸ�λ���Ƿ�Ϊ��
										array[r][c] = array[r1][c];// ����Ϊ��λ�õ����ݴ��뵽r��cλ��
										array[r1][c] = 0;// ����Ϊ��
										count++;// ��ʾ�ƶ�
										break;// ����r1��ѭ��
									}
								}
							}
							if (array[r][c] != 0) {// �жϸ�λ�ò�Ϊ��
								for (int r1 = r - 1; r1 >= 0; r1--) {// ��r�Ļ����ϼ�1���в���
									if (array[r1][c] == array[r][c]) {// �ж�r1,cλ�õ�ֵ�Ƿ��r,cλ�õ����
										array[r][c] = array[r1][c] * 2;// �����������
										grade+=array[r1][c];
										array[r1][c] = 0;// ����Ϊ��
										count++;// ��ʾ�����
										break;// ����r1��ѭ��
									} else if (array[r1][c] != 0) {// �ж��Ƿ�Ϊ����
										break;// ����r1��ѭ��
									}
								}
							}
						}

					}
				}
				else if(x1-x2<0&&Math.abs(x1-x2)>Math.abs(y1-y2)){
					for (int r = 0; r < array[0].length; r++) {// ��������
						for (int c =  array.length-1; c >= 0; c--) {// ��������
							if (array[r][c] == 0) {// �жϸ�λ���Ƿ�Ϊ��
								for (int c1 = c - 1; c1 >= 0; c1--) {// ��r�Ļ����ϼ�1���в���
									if (array[r][c1] != 0) {// �жϸ�λ���Ƿ�Ϊ��
										array[r][c] = array[r][c1];// ����Ϊ��λ�õ����ݴ��뵽r��cλ��
										array[r][c1] = 0;// ����Ϊ��
										count++;// ��ʾ�ƶ�
										break;// ����r1��ѭ��
									}
								}
							}
							if (array[r][c] != 0) {// �жϸ�λ�ò�Ϊ��
								for (int c1 = c - 1; c1 >= 0; c1--) {// ��r�Ļ����ϼ�1���в���
									if (array[r][c1] == array[r][c]) {// �ж�r1,cλ�õ�ֵ�Ƿ��r,cλ�õ����
										array[r][c] = array[r][c1] * 2;// �����������
										grade+=array[r][c];
										array[r][c1] = 0;// ����Ϊ��
										count++;// ��ʾ�����
										break;// ����r1��ѭ��
									} else if (array[r][c1] != 0) {// �ж��Ƿ�Ϊ����
										break;// ����r1��ѭ��
									}
								}
							}
						}

					}
				}
				else if(x1-x2>0&&Math.abs(x1-x2)>Math.abs(y1-y2)){
					for (int r = 0; r < array[0].length; r++) {// ��������
						for (int c = 0; c < array.length; c++) {// ��������
							if (array[r][c] == 0) {// �жϸ�λ���Ƿ�Ϊ��
								for (int c1 = c + 1; c1 < array.length; c1++) {// ��r�Ļ����ϼ�1���в���
									if (array[r][c1] != 0) {// �жϸ�λ���Ƿ�Ϊ��
										array[r][c] = array[r][c1];// ����Ϊ��λ�õ����ݴ��뵽r��cλ��
										array[r][c1] = 0;// ����Ϊ��
										count++;// ��ʾ�ƶ�
										break;// ����r1��ѭ��
									}
								}
							}
							if (array[r][c] != 0) {// �жϸ�λ�ò�Ϊ��
								for (int c1 = c + 1; c1 < array.length; c1++) {// ��r�Ļ����ϼ�1���в���
									if (array[r][c1] == array[r][c]) {// �ж�r1,cλ�õ�ֵ�Ƿ��r,cλ�õ����
										array[r][c] = array[r][c1] * 2;// �����������
										grade+=array[r][c];
										array[r][c1] = 0;// ����Ϊ��
										count++;// ��ʾ�����
										break;// ����r1��ѭ��
									} else if (array[r][c1] != 0) {// �ж��Ƿ�Ϊ����
										break;// ����r1��ѭ��
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
		if (count > 0) {// �ж��Ƿ����ƶ��������
			initOneData();// ����һ���µ�����
			count = 0;// ��־λ����Ϊ0
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
		if("��ʼ����Ϸ".equals(str)){
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
					Toast.makeText(game, "��Ӯ��!", Toast.LENGTH_LONG).show();
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
				Toast.makeText(game, "��Ϸ����!", Toast.LENGTH_SHORT).show();
				
			}
		}

		q = 0;
		z = 0;
		p = 0;
	}
}


