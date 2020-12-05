package day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Problem1 {

	private static int getValidPasswords() {
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

				int upperLimit = Integer.parseInt( limits[0].trim() );
				int bottomLimit = Integer.parseInt( limits[1].trim() );

				int count = 0;
				for ( char passcodeChar : passcode.toCharArray() ) {
                    if ( String.valueOf( passcodeChar ).equals( character ) ) {
                        count++;
                    }
				}
                if ( count >= upperLimit && count <= bottomLimit ) {
                    validPasscodes++;
                }
			}
		} catch ( FileNotFoundException w ) {
		} catch ( IOException e ) {
		}

		return validPasscodes;
	}

	public static void main( String[] args ) {
		System.out.println( getValidPasswords() );
	}
}
