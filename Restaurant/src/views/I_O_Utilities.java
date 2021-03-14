package views;

import java.util.Scanner;

public class I_O_Utilities {

	private static Scanner keyboard = new Scanner(System.in);

	/**
	 * Este metodo sirve para recoger un entero leido por teclado
	 * @return entero recogido
	 */
	public static int getInt() {
		int result = 0;
		boolean valid = false;
		do {
			try {
				result = Integer.parseInt(keyboard.nextLine());
				valid = true;
			} catch (IllegalStateException ex) {
				keyboard = new Scanner(System.in);
				System.out.println("Error in keyboard. Please, try it again: ");
			} catch (NumberFormatException ex) {
				System.out.println("Error reading integer type. Please, try it again: ");
			} catch (Exception ex) {
				System.out.println("Error unknown. Please, try it again: ");
			}
		} while (!valid);
		return result;
	}

	/**
	 * Este método sirve para recoger un float del teclado
	 * @return float recogido
	 */
	public static float getFloat() {
		float result = 0;
		boolean valid = false;
		do {
			try {
				result = Float.parseFloat(keyboard.nextLine());
				valid = true;
			} catch (IllegalStateException ex) {
				keyboard = new Scanner(System.in);
				System.out.println("Error in keyboard. Please, try it again: ");
			} catch (NumberFormatException ex) {
				System.out.println("Error reading decimal number. Please, try it again: ");
			} catch (Exception ex) {
				System.out.println("Error unknown. Please, try it again: ");
			}
		} while (!valid);
		return result;
	}

	/**
	 * Este método sirve para devolver un String recogido por teclado
	 * @return String recogido
	 */
	public static String getString() {
		String result = "";
		boolean valid = false;
		do {
			try {
				result = keyboard.nextLine();
				valid = true;
			} catch (Exception ex) {
				System.out.println("Error unknown. Please, try it again: ");
			}
		} while (!valid);
		return result;
	}

	public static String getString(String f) {
		System.out.print(f + " ");
		return I_O_Utilities.getString();
	}

	/**
	 * Este método sirve para devolver un Char
	 * @return Char recogido
	 */
	public static char getChar(){
		char result=' ';
		do{
			String s=I_O_Utilities.getString();
			if(s.length()==1){
				result=s.charAt(0);
			}else{
				result=' ';
				System.out.println("Please type only one key");
			}
		}while(result==' ');
		return result;
	}

}
