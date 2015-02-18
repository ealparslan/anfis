package phd.anfis.datastructures;

import java.util.Random;

import phd.anfis.logger.FileLogger;
import phd.anfis.logger.ILogger;
import phd.anfis.membership.GeneralizedBell;
import phd.anfis.membership.IMembershipFunction;

public class Layer1Node extends AbstractNode {

	IMembershipFunction mf;
	ILogger logger = new FileLogger();
	
	public Layer1Node(double val) {
		super(val);
		params = new Parameter[3];
		Random r = new Random();
		for (int i=0 ; i<params.length ; i++){
			params[i] = new Parameter(1 * r.nextDouble());
		}
			
		mf = new GeneralizedBell();
	}
	
	@Override
	public void compute(double... param){
		super.setValue(mf.compute(super.getPreNodes().get(0).getValue(),params));
	}

	@Override
	public void calculateError(double... param){
		double error = 0;
		for (INode postNode : super.getPostNodes()) {
			error += derivative((Layer2Node)postNode) * postNode.getError();
		}
		super.setError(error);
	}
	
	private double derivative(Layer2Node postNode){
		return (postNode.getValue()/super.getValue());
	}
	
	@Override
	public void update_de_dp(){
		//logger.printLog("\nParameter: " + params[0].getValue() + "\t" + params[1].getValue() + "\t" + params[2].getValue());
		for (int p=0; p<params.length ; p++) {
			double actual = params[p].getDe_dp();
			double derivative = derivative_o_p(p);
			double updated = actual + super.getError() * derivative;
			//logger.printLog("Actual dp: " + actual + "\t" + "Derivative: "+ derivative + "\t" + " Updated dp: " + updated);
			params[p].setDe_dp(updated);
		}
	//	params[params.length-1].setDe_dp(params[params.length-1].getDe_dp() + super.getError() * super.getPreNodes().get(0).getValue());
	}
	
	private double derivative_o_p(int p){
		double c, a, b, x;
		double tmp1, tmp2;
		double denom;
		double retval=0;
		x = super.getPreNodes().get(0).getValue();
		a = params[0].getValue();
		b = params[1].getValue();
		c = params[2].getValue();
		tmp1 = (x - c)/a;
		tmp2 = tmp1 == 0 ? 0 : Math.pow(Math.pow(tmp1, 2.0), b);
		denom = (1 + tmp2)*(1 + tmp2);
		//logger.printLog("tmp1: " + tmp1 + "\t" + "tmp2: "+ tmp2 + "\t" + " denom: " + denom);
		switch(p) {
		case 0: /* partial mf to partial a */
			retval = 2*b*tmp2/(a*denom);
			break;
		case 1: /* partial mf to partial b */
			if (tmp1 == 0)
				retval = 0.0;
			else
				retval = -Math.log(tmp1*tmp1)*tmp2/denom;
			break;
		case 2: /* partial mf to partial c */
			if (x == c)
				retval = 0.0;
			else
				retval = 2*b*tmp2/((x - c)*(denom));
			break;
		default:
			retval = 0.0;
		}
		return retval;
	}
}
