import java.util.Scanner;

public class Starter {

	public static void main(String[] args) {
		String s;
		System.out.println("Inserisci la stringa s:");
		Scanner tastiera = new Scanner(System.in);
		s=tastiera.nextLine();
		System.out.println("Il numero totale di caratteri è: " + s.length());
		int x,y;
		System.out.println("Inserisci x e y:");
		x=tastiera.nextInt();
		y=tastiera.nextInt();
		System.out.println("La sottostringa è " + s.substring(x-1, y-1));

		tastiera.close();

	}

}
