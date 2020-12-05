package day3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Problem1 {

	private static int getTreesNumber() {
		int trees = 0;
		int steps = 0;
		boolean ignoreFirstLine = true;
		try ( BufferedReader br = new BufferedReader( new FileReader(
				"/Users/sandy/JavaProjects/advent2020/src/main/java/day3/input" ) ) ) {
			String line;
			while ( ( line = br.readLine() ) != null ) {
				if ( ignoreFirstLine ) {
					ignoreFirstLine = false;
					continue;
				}

				char[] pattern = line.toCharArray();

				int size = pattern.length;
				steps += 3;

				if ( steps > size ) {
					int diff = steps - size;
					steps = diff;
				} else if (steps == size) {
					steps = 0;
				}

				if ( pattern[steps] == '#' ) {
					trees++;
				}

			}
		} catch ( FileNotFoundException w ) {
		} catch ( IOException e ) {
		}

		return trees;

	}

	public static void main( String[] args ) {
		System.out.println( getTreesNumber() );
	}

}
