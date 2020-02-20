import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
     * This class reads a text description of a metro subway system
     * and generates a graph representation of the metro.
     *
     * Students should feel free to modify this code as needed
     *  to complete this exercise.
     *
     *<p>
     *
     * The grammar for the file is described below in BNF. A typical line
     * in the file looks like this :
     *
     * <code> 20 NorthStation   Green 19 22  Orange 15 22  </code>
     *
     * where :
     *         20 is the StationID
     *         NorthStation is the StationName
     *         Green 19 22
     *                  Green is the LineName
     *                  19 is the StationID of the outbound station
     *                  22 is the StationID of the inbound station
     *         Orange 15 22 is a LineID in which :
     *                  Orange is the LineName
     *                  15 is the StationID of the outbound station
     *                  22 is the StationID of the inbound station
     *
     *         Therefore, NorthStation has two outgoing lines.
     *
     *  note : 0 denotes the end of a line : i.e. in this case,
     *  OakGrove would be at the end of the line, as there is no other outbound 
     *  station.
     *
     *<p>
     * metro-map ::= station-spec* <BR>
     * station-spec ::= station-id station-name station-line+ <BR>
     * station-id ::= (positive integer) <BR>
     * station-name ::= string <BR>
     * station-line ::= line-name station-id station-id <BR>
     *
     */

public class Parser {
	public static void main(String args[]) throws IOException, BadFileException {
		Parser parse = new Parser();
		parse.loadFile();
	}

	/**
	 * @throws java.io.IOException if there is a problem reading the file
	 * @throws BadFileException    if there is a problem with the format of the file
	 * @effects: parses the file, and generates a graph from it, unless there
	 * is a problem reading the file, or there is a problem with the format of the
	 * file.
	 * @returns the Graph generated by the file
	 */
	public Map<Station, ArrayList<Track>> loadFile() throws IOException, BadFileException {
		Map<Station, ArrayList<Track>> metroGraph = new HashMap<>();

		try (BufferedReader reader = new BufferedReader(new FileReader("bostonMetro.txt"))) {
			String line = reader.readLine();
			int weight;
			String id, name, lineColour, inStation, outStation;
			StringTokenizer st;
			while (line != null) {
				ArrayList<Track> tracks = new ArrayList<>();
				st = new StringTokenizer(line);

				//We want to handle empty lines effectively, we just ignore them!
				if (!st.hasMoreTokens()) {
					line = reader.readLine();
					continue;
				}

				//from the grammar, we know that the Station ID is the first token on the line
				id = st.nextToken();

				if (!st.hasMoreTokens()) {
					throw new BadFileException("no station name");
				}

				//from the grammar, we know that the Station Name is the second token on the line.
				name = st.nextToken();

				if (!st.hasMoreTokens()) {
					throw new BadFileException("station is on no lines");
				}

				Station currentStation = new Station(id, name);

				while (st.hasMoreTokens()) {
					lineColour = st.nextToken();

					if (!st.hasMoreTokens()) {
						throw new BadFileException("poorly formatted line info");
					}

					outStation = st.nextToken();

					if (!st.hasMoreTokens()) {
						throw new BadFileException("poorly formatted adjacent stations");
					}
					
					inStation = st.nextToken();
					
					if (!st.hasMoreTokens()) {
						throw new BadFileException("poorly formatted adjacent stations");
					}
					
					weight = Integer.parseInt(st.nextToken());
					
					Track inTrack = new Track(lineColour, inStation, id, weight);	//i've changed this to include weights
					Track outTrack = new Track(lineColour, id, outStation, weight);
					tracks.add(inTrack);
					tracks.add(outTrack);
				}
				metroGraph.put(currentStation, tracks);
				line = reader.readLine();
			}
		}
//		Iterator<Station> itr = metroGraph.keySet().iterator();
//		while (itr.hasNext()) {
//			System.out.println(metroGraph);
//		}
		System.out.println("I get here");
		return metroGraph;
	}
}


