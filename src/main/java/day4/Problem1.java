package day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem1 {
	final static Set<String> REQUIRED_FIELDS = new HashSet<>(
			Arrays.asList( "byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:" ) );
	private static final Logger LOGGER = LoggerFactory.getLogger( Problem1.class );

	private static boolean checkValidity( StringBuilder sb ) {
		final String passportDetails = sb.toString();
		return REQUIRED_FIELDS.stream()
				.filter( field -> passportDetails.contains( field ) )
				.count() == REQUIRED_FIELDS.size();
	}

	private static int getValidPassports() {
		int validPassports = 0;
		try ( BufferedReader br = new BufferedReader( new FileReader(
				"/Users/sandy/JavaProjects/advent2020/src/main/java/day4/input" ) ) ) {

			StringBuilder sb = new StringBuilder();
			String line;
			while ( ( line = br.readLine() ) != null ) {
				if ( StringUtils.isBlank( line ) ) {
					if ( checkValidity( sb ) ) {
						validPassports++;
					}
					sb.setLength( 0 );
				} else {
					sb.append( line ).append( " " );
				}
			}
			/* handle last entry */
			if ( checkValidity( sb ) ) {
				validPassports++;
			}

		} catch ( FileNotFoundException w ) {
			LOGGER.error( "No file found ", w );
		} catch ( IOException e ) {
			LOGGER.error( "Another problem was encountered", e );
		}

		return validPassports;
	}

	public static void main( String[] args ) {
		System.out.println( getValidPassports() );
	}

}
