package it.polito.tdp.borders.model;

public class Border {

	private int state1no;
	private int state2no;
	
		public Border() {
	}
	
	public Border(int state1no, int state2no) {
			this.state1no = state1no;
			this.state2no = state2no;
		}

	public int getState1no() {
		return state1no;
	}
	public int getState2no() {
		return state2no;
	}
	public void setState1no(int state1no) {
		this.state1no = state1no;
	}
	public void setState2no(int state2no) {
		this.state2no = state2no;
	}

	@Override
	public String toString() {
		return "Border: " + state1no + ", state2no=" + state2no + "]";
	}

			
	
}
