
class Main {

	public static void main(String[] args) {

		int i, j, k, l, m;

		char c = 'b'; // ASCII������ 3 ����һ���ַ�С����

		for (i = 1; i <= 5; i++)
			System.out.printf("\n"); // ��ͷ�ճ�5��

		for (i = 1; i <= 3; i++) { // ǰ3���м��п�϶�ֿ���д

			for (j = 1; j <= 32 - 2 * i; j++)
				System.out.printf(" "); // ��ߵĿո�ÿ��һ����ߵĿո����һ����2�� //8*n-2*i

			for (k = 1; k <= 4 * i + 1; k++)
				System.out.printf("%c", c);// �����벿���ַ�С����

			for (l = 1; l <= 13 - 4 * i; l++)
				System.out.printf(" "); // �м�Ŀո�ÿ��һ�еĿո����һ����4��

			for (m = 1; m <= 4 * i + 1; m++)
				System.out.printf("%c", c);// ����Ұ벿���ַ�С����

			System.out.printf("\n"); // ÿһ�������ϻ���

		}

		for (i = 1; i <= 3; i++) { // ��3���м�û�пո�

			for (j = 1; j <= 24 + 1; j++)
				System.out.printf(" "); // ��ߵĿո� //8*(n-1)+1

			for (k = 1; k <= 29; k++)
				System.out.printf("%c", c);// ����ַ�С����

			System.out.printf("\n"); // ÿһ�������ϻ���

		}

		for (i = 7; i >= 1; i--) { // ��7��

			for (j = 1; j <= 40 - 2 * i; j++)
				System.out.printf(" "); // ��ߵĿո�ÿ��һ����ߵĿո����һ����2��//8*(n+1)-2*i

			for (k = 1; k <= 4 * i - 1; k++)
				System.out.printf("%c", c);// ÿ��һ�е��ַ�С���ı���һ����4�������ѭ����i--��

			System.out.printf("\n"); // ÿһ�������ϻ���

		}

		for (i = 1; i <= 39; i++)
			System.out.printf(" "); // ���һ����ߵĿո�

		System.out.printf("%c\n", c); // ���һ���ַ�С����

		for (i = 1; i <= 5; i++)
			System.out.printf("\n"); // ���ճ�5��

		return ;

	}

}