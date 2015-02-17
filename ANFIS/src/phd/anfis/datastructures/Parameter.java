package phd.anfis.datastructures;

public class Parameter {
	
	private double value;
	private double de_dp;
	
	public Parameter(double value) {
		this.value=value;
		this.de_dp=0;
	}
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getDe_dp() {
		return de_dp;
	}
	public void setDe_dp(double de_dp) {
		this.de_dp = de_dp;
	}

}
