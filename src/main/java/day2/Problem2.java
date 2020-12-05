package day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Problem2 {

	private static int validatePasswords() {
		int validPasscodes = 0;
		try ( BufferedReader br = new BufferedReader( new FileReader(
				"/Users/sandy/JavaProjects/advent2020/src/main/java/day2/input" ) ) ) {
			String line;
			while ( ( line = br.readLine() ) != null ) {

				String[] firstHalf = line.split( ":" );
				String firstSubHalf = firstHalf[0].trim();
				String passcode = firstHalf[1].trim();

				String[] firstSubHalves = firstSubHalf.split( " " );
				String character = firstSubHalves[1].trim();
				String[] limits = firstSubHalves[0].trim().split( "-" );

				int upperLimit = Integer.parseInt( limits[0].trim() ) - 1;
				int bottomLimit = Integer.parseInt( limits[1].trim() ) - 1;

				char[] passcodeChars = passcode.toCharArray();
				boolean upperLimitEquality = String.valueOf( passcodeChars[upperLimit] )
						.equals( character );
				boolean bottomLimitEquality = String.valueOf( passcodeChars[bottomLimit] )
						.equals( character );

				if ( ( upperLimitEquality && !bottomLimitEquality ) || ( !upperLimitEquality && bottomLimitEquality ) ) {
					validPasscodes++;
				}

			}
		} catch ( FileNotFoundException w ) {
		} catch ( IOException e ) {
		}

		return validPasscodes;
	}

	public static void main( String[] args ) {
		System.out.println( validatePasswords() );
	}
}
