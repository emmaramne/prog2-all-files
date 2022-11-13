package mountain;

import java.util.HashMap;
import java.util.Map;

import fractal.*;

public class Mountain extends Fractal {
	Point a;
	Point b;
	Point c;
	Map<Side, Point> sides;
	double deviation;
	
	public Mountain(Point a, Point b, Point c, double dev) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		deviation = dev;
		sides = new HashMap<Side, Point>();
	}
	@Override
	public String getTitle() {
		return "Fractal Mountain";
	}

	@Override
	public void draw(TurtleGraphics g) {
		draw(g, order, a, b, c, deviation);
	}
	
	private void draw(TurtleGraphics g, int order, Point a, Point b, Point c, double dev) {
		if (order == 0) {
			g.moveTo(a.getX(), a.getY());
			g.forwardTo(b.getX(), b.getY());
			g.forwardTo(c.getX(), c.getY());
			g.forwardTo(a.getX(), a.getY());
			
			sides.remove(new Side(a, b));
			sides.remove(new Side(a, c));
			sides.remove(new Side(b, c));
		} else {
			Point ab;
			Point ac;
			Point bc;
			if (sides.containsKey(new Side(a,b))) {
				ab = sides.get(new Side(a, b));
			} else {
				ab = new Point((a.getX() + b.getX())/2, (a.getY() + b.getY())/2 + (int) Math.round(RandomUtilities.randFunc(dev)));
				sides.put(new Side(a, b), ab);
			}
			if (sides.containsKey(new Side(a,c))) {
				ac = sides.get(new Side(a, c));
			} else {
				ac = new Point((a.getX() + c.getX())/2, (a.getY() + c.getY())/2 + (int) Math.round(RandomUtilities.randFunc(dev)));
				sides.put(new Side(a, c), ac);
			}
			if (sides.containsKey(new Side(b,c))) {
				bc = sides.get(new Side(b, c));
			} else {
				bc = new Point((b.getX() + c.getX())/2, (b.getY() + c.getY())/2 + (int) Math.round(RandomUtilities.randFunc(dev)));
				sides.put(new Side(b, c), bc);
			}
			
			draw(g, order - 1, a, ab, ac, dev / 2);
			draw(g, order - 1, b, ab, bc, dev / 2);
			draw(g, order - 1, c, ac, bc, dev / 2);
			draw(g, order - 1, ab, ac, bc, dev / 2);
		}
	}
	

}
