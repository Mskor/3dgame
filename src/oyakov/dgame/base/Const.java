package oyakov.dgame.base;

public class Const {
	public static Matrix createRotationMatrix(double angle){
		Matrix res = new Matrix(2, 2);
		res._values[0][0] = Math.cos(angle);
		res._values[0][1] = -Math.sin(angle);
		res._values[1][0] = Math.sin(angle);
		res._values[1][1] = Math.cos(angle);
		return res;
	}
}
