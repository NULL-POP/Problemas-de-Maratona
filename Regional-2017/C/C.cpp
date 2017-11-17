#include <iostream>

using std::cin;
using std::cout;

long long gcd(long x, long y){

	if (y == 0) return x;

	return gcd(y, x % y);
}

long long lcm(long x, long y){

	return x / gcd(x,y) * y;
}

int main(){

	long N, L;
	long long _lcm = 1;
	cin >> N;
	cin >> L;

	long P;

	for (long i = 0; i < N; i++){
		cin >> P;
		_lcm = lcm(_lcm,P); 
	}

	long best_val = 1;
	long long best_lcm = _lcm;

	for (long pop = 2; pop <= L; pop++){

		long long pop_lcm = lcm(_lcm,pop);

		if (pop_lcm <= L && pop_lcm > best_lcm){
			
			best_val = pop;
			best_lcm = pop_lcm;
		}
	}

	cout << best_val << std::endl;

	return 0;
}