package WeightedQuickUnionUF;

public class Percolation {
	
	private byte[][] grid;
	private WeightedQuickUnionUF gridFind;
	private int opened;
	private int length;
	
	public Percolation(int n){
		grid = new byte[n][n];
		gridFind = new WeightedQuickUnionUF(n*n+2);
		length=n;
		for(int i=0;i<n;i++){
			gridFind.union(i,n*n);
			gridFind.union(n*n-i-1, n*n+1);
		}
		opened=0;
	}
	
	public int getOpenedCount(){
		return opened;
	}
	
	public void open(int i, int j){
		if(isOpen(i,j))
			return;
		grid[i][j]=1;
		if(i>0&&grid[i-1][j]==1)
			gridFind.union(i*length+j, (i-1)*length+j);
		if(j>0&&grid[i][j-1]==1)
			gridFind.union(i*length+j, i*length+(j-1));
		if(i+1<length&&grid[i+1][j]==1)
			gridFind.union(i*length+j, (i+1)*length+j);
		if(j+1<length&&grid[i][j+1]==1)
			gridFind.union(i*length+j, i*length+(j+1));
		opened++;
	}

	public boolean isOpen(int i, int j){
		return grid[i][j] == 1;
	}
	
	public boolean percolates(){
		return gridFind.connected(length*length,length*length+1);
	}
}
