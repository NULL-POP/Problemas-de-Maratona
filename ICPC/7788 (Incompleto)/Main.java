import java.util.Scanner;

public class Main
{

	static boolean [][] lines = new boolean[6][9];
	static boolean [][] cols = new boolean[6][9];
	static boolean [][] rects = new boolean[6][9];
	static Point [][] grid = new Point[6][6];


	public static void main(String [] args)
	{
		Scanner in = new Scanner(System.in);
		int p = in.nextInt();
		for(int iii = 0; iii < p; iii++)
		{
			int k = in.nextInt();

			for (int i = 0; i < 6; i++)
			{
				String [] line = in.nextLine().split(" ");
				for (int j = 0; j < 6; j++)
				{
					boolean is_barra = line[j].contains("/");
					boolean down_dash = line[j].endsWith("-");
					boolean up_dash = is_barra ? line[j].startsWith("-") : false;
					int cima = !up_dash ? (int) line[j].split("/")[0] : 0;
					int baixo = !down_dash ? ( is_barra ? (int) line[j].split("/")[0] : (int) line[j]) : 0 ;
					grid[i][j] = new Point(cima,baixo,is_barra);
					int curr_rect = i/2*2+j/3;

					if(baixo > 0)
					{
						lines[i][baixo-1] = true;
						cols[j][baixo-1] = true;
						rects[curr_rect][baixo-1] = true;
					}
					if(is_barra && cima > 0)
					{
						lines[i][cima-1] = true;
						cols[j][cima-1] = true;
						rects[curr_rect][cima-1] = true; 
					}
				}
			}
			boolean add_alguem = true;
			while(add_alguem)
			{
				for (int i = 0; i < 6; i++)
				{
					for (int j = 0; j < 6; j++)
					{
						Point curr = grid[i][j];
						int rect = i/2*2+j/3;
						boolean [] intersections = intersect(i,j,rect);
						int count = 0;
						//guardo os últimos q encontrei - melhor q percorrer de novo 
						int fst = 0;
						int snd = 0;
						for (int i = 0; i < 9; i++)
							//i não é bloqueado por ninguém
							if (!intersections[i])
							{
								count++;
								if (fst == 0) fst = i+1;
								else if(snd == 0) snd = i+1;
							}
						if (count==2 && curr.is_barra && curr.is_unit())
						{
							set(fst,i,j,rect);
							set(snd,i,j,rect);
							add_alguem = true;
						}
						else if(count==1 && !curr.is_barra && curr.baixo == 0)
						{
							set(fst,i,j,rect);
							add_alguem = true;
						}
					}
				}
			}
		}
	}


	public static void set(int val, int line, int col, int rect)
	{
		int pos = val - 1;
		lines[line][pos] = true;
		cols[col][pos] = true;
		rects[rect][pos] = true;
		Point curr = grid[line][pos];
		if (curr.is_barra)
		{
				if (val > curr.baixo)
				{
					if (curr.baixo > 0) curr.cima = curr.baixo; 
					curr.baixo = val;
				}
				else if (val < curr.cima)
				{
					if (curr.cima > 0) curr.baixo = curr.cima;
					curr.cima = val;
				}
		}
		else curr.baixo = val;
	}

	public static boolean [] intersect(int line, int col, int rect)
	{
		boolean [] intersect = new boolean[9];
		//o nor[i] retorna true quando i é bloqueado por alguém
		for (int i = 0; i < 9; i++)	intersect[i] = lines[line][i] | cols[col][i] | rects[rect][i];
		return intersect;
	}

	private static class Point
	{

		public boolean is_unit()
		{
			if (is_barra) return this.cima == 0 ^ this.baixo == 0;
			else return true;
		}

		public Point(int cima, int baixo, boolean is_barra)
		{
			this.cima = cima;
			this.baixo = baixo;
			this.is_barra = is_barra;
		}

		public int cima;
		public int baixo;
		public boolean is_barra;
	}
}