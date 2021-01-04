import java.util.Scanner;

public class Starter {

	public static void main(String[] args) {
		String s;
		System.out.println("Inserisci la stringa s:");
		Scanner tastiera = new Scanner(System.in);
		s=tastiera.nextLine();
		System.out.println("Il numero totale di caratteri è: " + s.length());
		int a,b,c;
		System.out.println("Inserisci a e b e c:");
		a=tastiera.nextInt();
		b=tastiera.nextInt();
		c=tastiera.nextInt();
		System.out.println("La sottostringa è " + s.substring(a-1, b-1));
		System.out.println("il valore di c è " +c);
		System.out.println("Culetto");
		
		
		tastiera.close();

	}

}
