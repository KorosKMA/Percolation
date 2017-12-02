package WeightedQuickUnionUF;

import java.util.Random;

public class PercolationStats {
	
	private static final int GRID_SIZE=200;
	private static final int NUM_OF_TRIES=100;
	
	Random random = new Random();
	
	private double[] results;
	
	public PercolationStats(int n, int t){
		results = new double[t];
		Percolation p;
		for(int i=0;i<t;i++){
			p = new Percolation(n);
			while(!p.percolates())
				p.open(random.nextInt(n),random.nextInt(n));
			results[i]=1.0*p.getOpenedCount()/(n*n);
		}
	}
	
	public double mean(){
		double sum=0;
		for(double i:results){
			sum+=i;
		}
		return sum/results.length;
	}
	
	public double stddev(){
		double mean = mean();
		double stddev=0;
		for(double i:results){
			stddev+=(i-mean)*(i-mean);
		}
		return Math.sqrt(stddev/(results.length-1));
	}
	
	public static void main(String[] args) {
		PercolationStats pS = new PercolationStats(GRID_SIZE,NUM_OF_TRIES);
		int t = pS.results.length;
		double mean = pS.mean();
		double stddev = pS.stddev();
		System.out.println("PercolationStats "+GRID_SIZE+" "+NUM_OF_TRIES);
		System.out.println("mean = "+mean);
		System.out.println("stddev = "+stddev);
		System.out.println("95% confidence interval = "+(mean-1.96*stddev/Math.sqrt(t))+", "+(mean+1.96*stddev/Math.sqrt(t)));
	}

}
