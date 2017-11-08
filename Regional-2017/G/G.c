#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv){
	
	int mod = 1000000007;
 	int T,m,n;
	scanf("%d %d %d",&T,&m,&n);
	int range = n-m+1;
	int **diffs = (int **) malloc(2*range*sizeof(int));
	int prev = 0;
	int curr = 1;
	diffs[curr] = (int *) malloc(range*sizeof(int));
	diffs[prev] = (int *) malloc(range*sizeof(int));
	int i;

	//initializes everything in t=1 to 1	
	for (i = 0; i < range; i++) diffs[prev][i] = 1;

	//main loop	
	int t;
	for (t = 1; t < T; t++){
		//builds up t from t-1
		
		diffs[curr][0]       = diffs[prev][1];
		diffs[curr][range-1] = diffs[prev][range-2];
		
		for (i = 1; i < range-1; i++)
			diffs[curr][i] = (diffs[prev][i-1] + diffs[prev][i+1]) % mod;
		
		//updates the values for t-1 to be used in the next iteration
		
		for (i = 0; i < range; i++)
			diffs[prev][i] = diffs[curr][i];
	}

	int sum = 0;
	for (i = 0; i < range; i++) sum = (sum + diffs[prev][i]) % mod;
	printf("%d\n",sum);

	//sums values for the last t

}