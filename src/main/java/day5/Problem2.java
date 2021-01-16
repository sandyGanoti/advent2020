package day5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem2 {
	private static final Logger LOGGER = LoggerFactory.getLogger( Problem2.class );

	private static int getSeatId() {
		Map<Integer, List<Integer>> seatIds = new TreeMap<>();
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
				List<Integer> columns = seatIds.get( row );
				if ( columns == null ) {
					columns = new ArrayList<>();
				}
				columns.add( column );
				seatIds.put( row, columns );
			}
		} catch ( FileNotFoundException w ) {
			LOGGER.error( "No file found ", w );
		} catch ( IOException e ) {
			LOGGER.error( "Another problem was encountered", e );
		}

		int missingRow = 0;
		int missingColumn = 0;
		for ( Map.Entry<Integer, List<Integer>> entry : seatIds.entrySet() ) {
			List<Integer> columns = entry.getValue();
			Collections.sort( columns );

			/* we are interested in the seats with IDs +1 and -1 from yours will be in your list */
			if ( columns.size() == 8 || columns.size() < 7 ) {
				continue;
			}
			missingRow = entry.getKey();

			// given that the columns start from 0 up to 7
			int counter = 0;
			for ( int i = 0; i < columns.size(); i++ ) {
				missingRow = entry.getKey();
				if ( columns.get( i ).equals( Integer.valueOf( counter ) ) ) {
					counter++;
					continue;
				}
				missingColumn = columns.get( i );
			}
		}
		if ( missingColumn == 0 ) {
			missingColumn = 7;
		}
		return missingRow * 8 + missingColumn;
	}

	public static void main( String[] args ) {
		System.out.println( getSeatId() );
	}

}
