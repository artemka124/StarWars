import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Boss implements Runnable {
	public static final int MAX_TOP = 2;
	public static final int MAX_BOT = 490;
	Image img2;
	int x = 1300;
	int y;
	int boomX;
	int boomY;
	int dy = 0;
	int dx = 0;
	int v = 5;
	int isNeed = 0;
	int schet = -1;
	int health;
	int id;
	Random k = new Random();
	Vector<Object> gun;
	Thread gunFactory = new Thread(this);

	public Boss(int i) {
		id = i;
		if (i==1){
		try {

			img2 = ImageIO.read(new File("C:\\textures\\enemyboss.png"));
		} catch (IOException e) {
			System.out.println(4);
		}
		y = k.nextInt(100) + 100;
		schet = 0;
		health = 50;
		gun = new Vector();
		boomXY(100, 0);
		gunFactory.start();
		}
		else
		{
			try {

				img2 = ImageIO.read(new File("C:\\textures\\boss2.png"));
			} catch (IOException e) {
				System.out.println(4);
			}
			health=6;
			x=1400;
			y=0;
			
		}

	}

	public void boomXY(int x, int y) {
		this.boomX = x;
		this.boomY = y;
	}

	public void move() {
		if (id==1){
			if ((dx == 0) && (dy == 0)) {
				if (x >= 650)
					x -= 3;
				else {
					dy = k.nextInt(4) + +3;
					dx = k.nextInt(4) + +3;
				}
			} else {
				if (x > 1000)
					dx *= -1;
				if (x < 600)
					dx *= -1;
				if (y > 470)
					dy *= -1;
				if (y < 0)
					dy *= -1;
			}
			x += dx;
			y += dy;
			// GunBoss n = new GunBoss(x + 250, y + 40);
			// gun.add(n);
		}
		if (id ==2){
			dx=-4;
			x+=dx;
			if (x<662) x+=4;
		}

	}
	
	
	
	public void changeimg(String a){
		try {

			img2 = ImageIO.read(new File("C:\\textures\\"+a+".png"));
		} catch (IOException e) {
			System.out.println(4);
		}
	}

	public Rectangle getRectangle() {
		return new Rectangle(x, y, img2.getWidth(null), img2.getHeight(null));
	}

	public void delete() {
		gun = null;
		try {
			gunFactory.stop();
		} catch (Exception e) {
		}

	}
	

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GunBoss n = new GunBoss(x, y + 40);
			gun.add(n);
		}

	}

}
