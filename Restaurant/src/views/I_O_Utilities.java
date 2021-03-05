package views;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class I_O_Utilities {

	private static Scanner keyboard = new Scanner(System.in);
	public static final String defaultDateParsed = "yyyy/MM/dd";

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

	public static int getInt(String f) {
		System.out.print(f + " ");
		return I_O_Utilities.getInt();
	}

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

	public static float getFloat(String f) {
		System.out.print(f + " ");
		return I_O_Utilities.getFloat();
	}

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

	public static char getChar(String f) {

		String s;
		do {
			System.out.print(f + " ");
			s = I_O_Utilities.getString();
			if (s.length() != 1) {
				System.out.println("No válido");
			}
		} while (s.length() != 1);

		return s.charAt(0);

	}

	public static void clearScreen() {
		System.out.print("\n\n\n\n\n\n\n\n\n\n");
		System.out.flush();
	}

	public static LocalDateTime getDate(String s, String parsed) {
		boolean result = false;
		LocalDateTime time = null;

		while (!result) {
			try {
				String fecha = I_O_Utilities.getString(s + " [" + parsed + "]") + " 00:00";
				DateTimeFormatter format = DateTimeFormatter.ofPattern(parsed + " HH:mm");
				time = LocalDateTime.parse(fecha, format);
				result = true;
			} catch (Exception ex) {
				System.out.println("Fecha no correcta");
			}
		}
		return time;
	}

}
