package day3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem2 {
	private static final Logger LOGGER = LoggerFactory.getLogger( Problem2.class );

	private static BigInteger getMultiTreesNumber() {
		/*
		 * The set keeps the slops we need to run on this part
		 * Specifically:
		 * Right 1, down 1.
		 * Right 3, down 1. (This is the slope you already checked.)
		 * Right 5, down 1.
		 * Right 7, down 1.
		 * Right 1, down 2.
		 */

		Set<String> slopes = new HashSet<>();
		slopes.add( "1,1" );
		slopes.add( "3,1" );
		slopes.add( "5,1" );
		slopes.add( "7,1" );
		slopes.add( "1,2" );

		BigInteger multiplyNumberOfTrees = BigInteger.ONE;
		for ( String slope : slopes ) {
			String[] data = slope.split( "," );
			int key = Integer.parseInt( data[0] );
			int value = Integer.parseInt( data[1] );
			int trees = 0;
			int steps = 0;

			boolean ignoreFirstLine = true;
			try ( BufferedReader br = new BufferedReader( new FileReader(
					"/Users/sandy/JavaProjects/advent2020/src/main/java/day3/input" ) ) ) {
				String line;

				int jump = 0;
				while ( ( line = br.readLine() ) != null ) {
					if ( ignoreFirstLine ) {
						ignoreFirstLine = false;
						continue;
					}
					if ( value != 1 ) {
						jump++;
						if ( jump == value ) {
							jump = 0;
						} else {
							continue;
						}
					}
					char[] pattern = line.toCharArray();

					int size = pattern.length;
					steps += key;

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
			if ( trees != 0 ) {
				multiplyNumberOfTrees = multiplyNumberOfTrees.multiply(
						BigInteger.valueOf( trees ) );
			}
		}
		return multiplyNumberOfTrees;
	}

	public static void main( String[] args ) {
		System.out.println( getMultiTreesNumber() );
	}

}
