package day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem2 {
	private static final Logger LOGGER = LoggerFactory.getLogger( Problem1.class );

	private static final Map<String, Function<String, Boolean>> FIELD_VALIDATION_RULES = new HashMap<>() {{
		put( "byr", Problem2::validateByr );
		put( "iyr", Problem2::validateIyr );
		put( "eyr", Problem2::validateEyr );
		put( "hgt", Problem2::validateHgt );
		put( "hcl", Problem2::validateHcl );
		put( "ecl", Problem2::validateEcl );
		put( "pid", Problem2::validatePid );
	}};
	private static final int NUMBER_OF_FIELDS = FIELD_VALIDATION_RULES.size();

	private static boolean checkValidity( StringBuilder sb ) {
		final String passportDetails = sb.toString();

		String[] fields = passportDetails.split( " " );
		int countValidFields = 0;
		for ( String field : fields ) {
			String[] parts = field.split( ":" );
			if ( parts.length == 1 ) {
				return false;
			}
			String key = parts[0];
			String value = parts[1];

			if ( key.equals( "cid" ) ) {
				continue;
			}
			if ( !FIELD_VALIDATION_RULES.get( key ).apply( value ) ) {
				System.out.println(
						String.format( "False on %s with value %s for the: %s", key, value,
								passportDetails ) );
				return false;
			} else {
				countValidFields++;
			}
		}

		return countValidFields == NUMBER_OF_FIELDS;
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

	private static boolean validateByr( String birthYear ) {
		try {
			int yob = Integer.parseInt( birthYear );
			return yob >= 1920 && yob <= 2002;
		} catch ( NumberFormatException e ) {
			return false;
		}
	}

	private static boolean validateIyr( String issueYear ) {
		try {
			int yob = Integer.parseInt( issueYear );
			return yob >= 2010 && yob <= 2020;
		} catch ( NumberFormatException e ) {
			return false;
		}
	}

	private static boolean validateEyr( String value ) {
		try {
			int yob = Integer.parseInt( value );
			return yob >= 2020 && yob <= 2030;
		} catch ( NumberFormatException e ) {
			return false;
		}
	}

	private static boolean validateHgt( String heightValue ) {
		if ( !heightValue.contains( "cm" ) ) {
			if ( !heightValue.contains( "in" ) ) {
				return false;
			}
			String[] parts = heightValue.split( "in" );
			try {
				int height = Integer.parseInt( parts[0] );
				return height >= 59 && height <= 76;
			} catch ( NumberFormatException e ) {
				return false;
			}
		} else {
			String[] parts = heightValue.split( "cm" );
			try {
				int height = Integer.parseInt( parts[0] );
				return height >= 150 && height <= 193;
			} catch ( NumberFormatException e ) {
				return false;
			}
		}
	}

	private static boolean validateHcl( String hairColourValue ) {
		String[] parts = hairColourValue.split( "#" );
		if ( parts.length == 1 ) {
			return false;
		}
		try {
			String hairColour = parts[1];
			return hairColour.length() == 6 && hairColour.matches( "[A-Fa-f0-9]+" );
		} catch ( NumberFormatException e ) {
			return false;
		} catch ( ArrayIndexOutOfBoundsException e ) {
			System.out.println( e );
			return false;
		}
	}

	private static boolean validateEcl( String eyeColor ) {
		return eyeColor.equals( "amb" ) || eyeColor.equals( "blu" ) || eyeColor.equals(
				"brn" ) || eyeColor.equals( "gry" ) || eyeColor.equals( "grn" ) || eyeColor.equals(
				"hzl" ) || eyeColor.equals( "oth" );
	}

	private static boolean validatePid( String passportId ) {
		return passportId.length() == 9 && passportId.matches( "[0-9]+" );
	}

}
