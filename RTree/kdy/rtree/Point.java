package kdy.rtree;

class Point {
	private int x;
	private int y;
	
	public Point() {
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object other) {
		if( other instanceof Point ) {
			Point o = (Point)other;
			return 
			x == o.x &&
			y == o.y;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "" + x + " " + y;
	}
}