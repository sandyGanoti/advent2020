package day6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem1 {
	private static final Logger LOGGER = LoggerFactory.getLogger( Problem1.class );

	private static int getSumFromGroup( final char[] group ) {
		Set<String> occurencies = new HashSet<>();
		for ( char character : group ) {
			occurencies.add( String.valueOf( character ) );
		}
		return occurencies.size();
	}

	private static int getSum() {
		int sum = 0;
		try ( BufferedReader br = new BufferedReader( new FileReader(
				"/Users/sandyganoti/JavaProjects/advent2020/src/main/java/day6/input" ) ) ) {
			String line;
			StringBuilder sb = new StringBuilder();

			char[] group;
			while ( ( line = br.readLine() ) != null ) {
				if ( StringUtils.isBlank( line ) ) {
					group = sb.toString().toCharArray();
					sb.setLength( 0 );
				} else {
					sb.append( line );
					continue;
				}
				sum += getSumFromGroup( group );
			}
			/* last group */
			sum += getSumFromGroup( sb.toString().toCharArray() );
		} catch ( FileNotFoundException w ) {
			LOGGER.error( "No file found ", w );
		} catch ( IOException e ) {
			LOGGER.error( "Another problem was encountered", e );
		}
		return sum;
	}

	public static void main( String[] args ) {
		System.out.println( getSum() );
	}

}
