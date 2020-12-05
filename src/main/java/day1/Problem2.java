package day1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem2 {

	private static int threeSum( final List<Integer> expenses, final int target ) {
		for ( int i = 0; i < expenses.size(); i++ ) {
			int newTarget = target - expenses.get( i );
			Set<Integer> numbers = new HashSet<>();
			for ( int j = 0; j < expenses.size(); j++ ) {
				if ( i != j ) {
					int complement = newTarget - expenses.get( j );
					if ( numbers.contains( complement ) ) {
						System.out.println(
								expenses.get( j ) + "  " + complement + " " + expenses.get( i ) );
						return expenses.get( j ) * complement * expenses.get( i );
					}
					numbers.add( expenses.get( j ) );
				}
			}
		}
		return 0;
	}

	public static void main( String[] args ) {
		//        System.out.println(threeSum(Arrays.asList(1721,979, 366,299, 675,1456), 2020));

		System.out.println( threeSum(
				Arrays.asList( 1539, 1914, 1866, 1407, 1706, 1423, 1834, 1700, 1573, 1486, 1743,
						1394, 1693, 1705, 1530, 1811, 1626, 1473, 1901, 1481, 1527, 1841, 1891,
						1750, 1343, 1899, 401, 1896, 1627, 1593, 1541, 874, 1484, 1210, 1692, 1963,
						1964, 1780, 671, 1862, 1393, 1309, 1740, 1831, 1932, 1185, 1979, 1504, 1663,
						1610, 1494, 1511, 1103, 1738, 1816, 1871, 1545, 1595, 1784, 1412, 1815,
						1998, 1783, 1770, 1426, 1699, 1416, 1880, 1612, 1989, 1360, 1869, 1762,
						1690, 1999, 1990, 1521, 1730, 703, 1463, 1670, 1472, 1413, 1669, 1502, 1548,
						1475, 1694, 1314, 1980, 980, 1667, 890, 1569, 1456, 1406, 1924, 1973, 1965,
						1533, 1827, 2000, 1847, 1520, 1729, 1512, 1555, 1566, 1505, 1672, 1169,
						1835, 1850, 1493, 1861, 1288, 1675, 1676, 1556, 1320, 1757, 1870, 1642,
						1903, 1372, 1967, 1894, 176, 1908, 1418, 1535, 1487, 1496, 1491, 1611, 1970,
						1758, 1563, 1766, 1629, 1937, 1763, 1829, 1772, 1632, 1517, 1736, 1971,
						1721, 1716, 1429, 1408, 1560, 1958, 1359, 1890, 1825, 1536, 1819, 1697,
						1887, 1832, 2005, 892, 1471, 1425, 1677, 1673, 1128, 1878, 1062, 1470, 1875,
						1854, 1518, 1568, 1919, 256, 1532, 1711, 1944, 1344, 1330, 1636, 1957, 1709,
						1551, 1983, 1674, 1671, 1959, 1760, 1689, 1767, 1477, 1589, 1897, 1144,
						1982, 1544 ), 2020 ) );
	}

}
