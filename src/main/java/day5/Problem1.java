package day5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem1 {
	private static final Logger LOGGER = LoggerFactory.getLogger( Problem1.class );

	private static int getHigherSeatId() {
		int higherSeatId = 0;
		try ( BufferedReader br = new BufferedReader( new FileReader(
				"/Users/sandyganoti/JavaProjects/advent2020/src/main/java/day5/input" ) ) ) {
			String line;
			while ( ( line = br.readLine() ) != null ) {
				if ( line.strip().length() != 10 ) {
					continue;
				}
				char[] seat = line.toCharArray();
				int row = 0;
				int column = 0;

				int rowUpperHalf = 127;
				int rowLowerHalf = 0;

				for ( int i = 0; i < 7; i++ ) {
					int tempMiddlePoint = ( rowUpperHalf + rowLowerHalf ) / 2;

					if ( seat[i] == 'F' ) {
						rowUpperHalf = tempMiddlePoint;
					} else if ( seat[i] == 'B' ) {
						rowLowerHalf = tempMiddlePoint + 1;
					} else {
						continue;
					}

					if ( rowLowerHalf == rowUpperHalf ) {
						row = rowLowerHalf;
					}
				}
				int columnUpperHalf = 7;
				int columnLowerHalf = 0;
				for ( int i = 7; i < 10; i++ ) {
					int tempMiddlePoint = ( columnUpperHalf + columnLowerHalf ) / 2;

					if ( seat[i] == 'L' ) {
						columnUpperHalf = tempMiddlePoint;
					} else if ( seat[i] == 'R' ) {
						columnLowerHalf = tempMiddlePoint + 1;
					}
					if ( columnLowerHalf == columnUpperHalf ) {
						column = columnLowerHalf;
					}
				}
				int seatId = row * 8 + column;
				higherSeatId = seatId > higherSeatId ? seatId : higherSeatId;
			}
		} catch ( FileNotFoundException w ) {
			LOGGER.error( "No file found ", w );
		} catch ( IOException e ) {
			LOGGER.error( "Another problem was encountered", e );
		}
		return higherSeatId;
	}

	public static void main( String[] args ) {
		System.out.println( getHigherSeatId() );
	}

}
