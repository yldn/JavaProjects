
class Main {

	public static void main(String[] args) {

		int i, j, k, l, m;

		char c = 'b'; // ASCII码里面 3 就是一个字符小爱心

		for (i = 1; i <= 5; i++)
			System.out.printf("\n"); // 开头空出5行

		for (i = 1; i <= 3; i++) { // 前3行中间有空隙分开来写

			for (j = 1; j <= 32 - 2 * i; j++)
				System.out.printf(" "); // 左边的空格，每下一行左边的空格比上一行少2个 //8*n-2*i

			for (k = 1; k <= 4 * i + 1; k++)
				System.out.printf("%c", c);// 输出左半部分字符小爱心

			for (l = 1; l <= 13 - 4 * i; l++)
				System.out.printf(" "); // 中间的空格，每下一行的空格比上一行少4个

			for (m = 1; m <= 4 * i + 1; m++)
				System.out.printf("%c", c);// 输出右半部分字符小爱心

			System.out.printf("\n"); // 每一行输出完毕换行

		}

		for (i = 1; i <= 3; i++) { // 下3行中间没有空格

			for (j = 1; j <= 24 + 1; j++)
				System.out.printf(" "); // 左边的空格 //8*(n-1)+1

			for (k = 1; k <= 29; k++)
				System.out.printf("%c", c);// 输出字符小爱心

			System.out.printf("\n"); // 每一行输出完毕换行

		}

		for (i = 7; i >= 1; i--) { // 下7行

			for (j = 1; j <= 40 - 2 * i; j++)
				System.out.printf(" "); // 左边的空格，每下一行左边的空格比上一行少2个//8*(n+1)-2*i

			for (k = 1; k <= 4 * i - 1; k++)
				System.out.printf("%c", c);// 每下一行的字符小爱心比上一行少4个（这个循环是i--）

			System.out.printf("\n"); // 每一行输出完毕换行

		}

		for (i = 1; i <= 39; i++)
			System.out.printf(" "); // 最后一行左边的空格

		System.out.printf("%c\n", c); // 最后一个字符小爱心

		for (i = 1; i <= 5; i++)
			System.out.printf("\n"); // 最后空出5行

		return ;

	}

}