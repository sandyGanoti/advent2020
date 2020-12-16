package day3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem1 {
	private static final Logger LOGGER = LoggerFactory.getLogger( Problem1.class );

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
				} else if ( steps == size ) {
					steps = 0;
				}

				if ( pattern[steps] == '#' ) {
					trees++;
				}

			}
		} catch ( FileNotFoundException w ) {
			LOGGER.error( "No file found ", w );
		} catch ( IOException e ) {
			LOGGER.error( "Another problem was encountered", e );
		}

		return trees;

	}

	public static void main( String[] args ) {
		System.out.println( getTreesNumber() );
	}

}
