package mountain;

public class Side {
	Point start;
	Point end;
	
	public Side(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	
	public Point getStart() {
		return start;
	}
	
	public Point getEnd() {
		return end;
	}
	
	@Override
	public int hashCode() {
		return start.hashCode() + end.hashCode();
	}
	
	@Override
	public boolean equals(Object side) {
		if (side instanceof Side) {
			if (this.start.equals(((Side) side).getStart()) && this.end.equals(((Side) side).getEnd())) {
				return true;
			}
			if (this.start.equals(((Side) side).getEnd()) && this.end.equals(((Side) side).getStart())) {
				return true;
			}
			return false;
		}
		return false;
	}

}
