package oyakov.dgame.base;

public class Line {

	public Matrix points;

	public Line(double xa, double ya, double xb, double yb) {
		points = new Matrix(2, 2);
		points._values[0][0] = xa;
		points._values[0][1] = ya;
		points._values[1][0] = xb;
		points._values[1][1] = yb;
	}

	public Line(Matrix pMatrix) {
		points = pMatrix;
	}

	public int xa() {
		return (int) points._values[0][0];
	}

	public int ya() {
		return (int) points._values[0][1];
	}

	public int xb() {
		return (int) points._values[1][0];
	}

	public int yb() {
		return (int) points._values[1][1];
	}

	public double dx() {
		return this.xb() - this.xa();
	}

	public double dy() {
		return this.yb() - this.ya();
	}

	public double k() {
		return this.dy() / this.dx();
	}

	public void rotate(double angle, double xBase, double yBase) {
		Matrix relPoints = new Matrix(points);
		relPoints._values[0][0] -= xBase;
		relPoints._values[1][0] -= xBase;
		relPoints._values[0][1] -= yBase;
		relPoints._values[1][1] -= yBase;
		relPoints = relPoints.mul(Const.createRotationMatrix(angle));
		points._values[0][0] = xBase + relPoints._values[0][0];
		points._values[0][1] = yBase + relPoints._values[0][1];
		points._values[1][0] = xBase + relPoints._values[1][0];
		points._values[1][1] = yBase + relPoints._values[1][1];
	}

	public void draw(int[] pixels, int width, int height){
		int xa = xa(), ya = ya(), xb = xb(), yb = yb(); 
		int rise = yb - ya, run = xb - xa, pixI = 0;
		
		if(run == 0){
			if(yb < ya){
				for(int y = yb; y < ya + 1; y++){
					pixI = xa + y * width;
					if(pixI >= 0 || pixI < width * height)
						pixels[pixI] = 255;
				}
			} else {
				for(int y = ya; y < yb + 1; y++){
					pixI = xa + y * width;
					if(pixI >= 0 || pixI < width * height)
						pixels[pixI] = 255;
				}
			}
		} else {
			int adjust;
			double k = rise / run;
			if(k >= 0)
				adjust = 1;
			else
				adjust = -1;			
			double threshold = 0.5, offset = 0;
			if(k <=1 || k >= -1){
				double delta = Math.abs(k);
				int y = ya;
				if(xa > xb){
					int z;
					z = xa;
					xa = xb;
					xb = z;
					y = yb;
				}
				for (int x = xa; x < xb + 1; x++) {
					pixels[x + y * width] = 255;
					offset += delta;
					if(offset >= threshold){
						y += adjust;
						threshold++;
					}
				}
				
			} else {
				double delta = Math.abs(run / rise);
				int x = xa;
				if(ya > yb){
					int z;
					z = ya;
					ya = yb;
					yb = z;
					x = xb;
				}
				for (int y = 0; y < yb + 1; y++) {
					pixels[x + y * width] = 255;
					offset += delta;
					if (offset >= threshold) {
						x += adjust;
						threshold++;
					}
				}
			}
		}
	}
}
