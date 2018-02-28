import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.HashMap;

import java.util.Map;

import java.util.concurrent.ForkJoinPool;

import java.util.concurrent.RecursiveAction;



//Anushka Bhandari

class recurse extends RecursiveAction {

	private volatile	 static	Map<String,	recurse>	instances	=		

			new	HashMap<String, recurse>();	

			public	static synchronized	recurse	getInstance(long	x,	long	y)		

			{	

				String	key	=	x	+	",	"	+	y;	



				if	(!instances.containsKey(key))	{	 											

					instances.put(key,	new	recurse(x,	y));	

				}	

				return	instances.get(key);	

			}

			public static Map<String, recurse> getInstances() {

				return instances;

			}

			public static void setInstances(Map<String, recurse> instances) {

				recurse.instances = instances;

			}

			public long getAns() {

				return ans;

			}

			private final long n;

			private final	long k;

			 long ans;

			public recurse(long n,long k) {

				this.n=n;

				this.k=k;

			}

			@Override

			protected void compute() {

				if (n <= 0 || k <=0 || n == k) {

				ans= 1;

			}

			else {

				recurse left = recurse.getInstance(n - 1, k - 1);

				recurse right = recurse.getInstance(n - 1, k);

				left.fork();

				right.compute();

				left.join();

				ans= (left.ans + right.ans);

			}

			

				

			}

			//4324678919983428184





}



class recursePP extends RecursiveAction{

			 long n;

				long k;

			 long ans;

			public recursePP(long n,long k) {

				this.n=n;

				this.k=k;

			}



			@Override

			protected void compute() {

				if (n <= 0 || k <=0 || n == k) {

				ans= 1;

			}

			else {

				recursePP left = new recursePP(n - 1, k - 1);

				recursePP right = new recursePP(n - 1, k);

				left.fork();

				right.compute();

				left.join();

				ans= (left.ans + right.ans);

			}

			}

			//4324678919983428184

}

// 1//61 sec//64

// 2//70 sec//87

// 3//80 sec//256

// 4//84 sec//97

public class lab8{	



	public static void main(String[] args) throws NumberFormatException, IOException {

		System.out.println("Enter the number of Threads");

		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

//		int r=Integer.parseInt(reader.readLine());

		final long startTime = System.currentTimeMillis();



		ForkJoinPool pool =new ForkJoinPool(4);

		recurse s= recurse.getInstance(50,25);

		pool.invoke(s);

		final long finaltime = System.currentTimeMillis();



		System.out.println(s.ans);

		System.out.println(finaltime-startTime+" "+ "TIME");

		



	}

}
