package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import SetUp.PlayerStone;
import SetUp.Stone;
import nextMoves.BlackWhiteNextMove;
import nextMoves.ProposedAsNextMove;
import storageOfResults.EucliResult;
import storageOfResults.Scorelead;
import storageOfResults.Utility;

public class main {
	public static Pattern patternSymboleBefore = Pattern.compile(";(B|W)\\[[a-z][a-z]\\]?");

	// Base Folder
	private static String baseFolder = "\\PRODZ2015";
	// Files SGF files input.
	private static File[] verginFiles = new File(
			"C:\\Users\\schro\\OneDrive\\Desktop\\Data_Forschung\\dataToAnalyse\\" + baseFolder).listFiles();
	// Analyzed Files as input.
	private static File[] analysedFiles = new File(
			"C:\\Users\\schro\\OneDrive\\Desktop\\Data_Forschung\\outPutAfterAnalysis\\" + baseFolder).listFiles();

	// File output folder
	public static void main(String[] args) throws Exception {

		// Iterate over the sgf files (vergin files).
		for (int i = 0; i < verginFiles.length; i++) {
			String fileName = verginFiles[i].getName().substring(0, verginFiles[i].getName().indexOf("sgf") - 1);

			// Setting up de files where the resulting data needs to be stored.
			File euclidienWhiteFile = new File(
					"C:\\Users\\schro\\OneDrive\\Desktop\\Data_Forschung\\outPutMidDenZahlenDieDuWillst\\" + baseFolder
							+ "\\euclidien\\white\\" + fileName + ".txt");
			File euclidienBlackFile = new File(
					"C:\\Users\\schro\\OneDrive\\Desktop\\Data_Forschung\\outPutMidDenZahlenDieDuWillst\\" + baseFolder
							+ "\\euclidien\\black\\" + fileName + ".txt");

			File scoreleadFile = new File(
					"C:\\Users\\schro\\OneDrive\\Desktop\\Data_Forschung\\outPutMidDenZahlenDieDuWillst\\" + baseFolder
							+ "\\scorelead\\" + fileName + ".txt");
			File utilityFile = new File(
					"C:\\Users\\schro\\OneDrive\\Desktop\\Data_Forschung\\outPutMidDenZahlenDieDuWillst\\" + baseFolder
							+ "\\utility\\" + fileName + ".txt");
			File representedFile = new File(
					"C:\\Users\\schro\\OneDrive\\Desktop\\Data_Forschung\\outPutMidDenZahlenDieDuWillst\\" + baseFolder
							+ "\\isRepresented\\" + fileName + ".txt");

			// Iterate over the AnalysedFiles
			for (int pl = 0; pl < analysedFiles.length; pl++) {

				String normalAnalysedFilename = analysedFiles[pl].getName();

				String analysedFileName = analysedFiles[pl].getName()
						.substring(0, analysedFiles[pl].getName().indexOf("sgf")).trim();
				String verginFileName = verginFiles[i].getName().substring(0, verginFiles[i].getName().indexOf("sgf"))
						.trim();

				// Cheack if the vergin fileName is the same as the analysed filename
				// Is required so that there is no mismatch of files compared.
				if (verginFileName.equals(analysedFileName)) {
					Gson euc = new Gson();

					// Set up the process reading the analyzed files and setting the different
					// values.
					String analysedAsString = getJsonText(analysedFiles[pl]);

					JsonObject analysedJSON = euc.fromJson(analysedAsString, JsonObject.class);
					JsonArray dataArray = analysedJSON.getAsJsonArray("data");

					// Initialise the Lists to store the different values.
					List<Double> blackScoreValues = new ArrayList<Double>();
					List<Double> whiteScoreValues = new ArrayList<Double>();
					List<Double> blackUtilityValues = new ArrayList<Double>();
					List<Double> whiteUtilityValues = new ArrayList<Double>();

					List<ProposedAsNextMove> whiteWasProposed = new ArrayList<ProposedAsNextMove>();
					List<ProposedAsNextMove> blackWasProposed = new ArrayList<ProposedAsNextMove>();

					List<Stone> stones = getAllStonesFromTheGame(verginFiles[i]);

					// Iterate over all the stones from the SGF FILE (Virgin file).
					for (int j = 0; j < stones.size() - 2; j++) {
						// Checker to not get outofbounds errors. (Only safety check).
						if (j >= stones.size() || j >= dataArray.size()) {
							break;
						}

						ProposedAsNextMove prosedMove = null;
						JsonObject current = dataArray.get(j).getAsJsonObject();
						int index = j + 2;
						Stone nextStone = null;
						if (index < (stones.size())) {
							nextStone = stones.get(index);

							String currentPlayer = nextStone.getPlayer();
							// Get move infos from jsonObject from the Analysed files as string.
							JsonArray currentMoveInfos = current.getAsJsonArray("moveInfos");
							int listItem = 0;

							// KataGo suggests multiple moves not only one. Thus multiple arrays.
							for (int k = 0; k < currentMoveInfos.size(); k++) {

								boolean checker = true;
								JsonElement el = currentMoveInfos.get(k);
								JsonObject object = el.getAsJsonObject();
								// PV: are the suggested stones
								JsonArray array = object.getAsJsonArray("pv");

								int indexOfElementInList = 0;
								prosedMove = null;
								for (JsonElement ele : array) {

									String token = ele.getAsString().replace("\"", "");
									Integer x = null;
									Integer y = null;
									// If a player has given up. No occurence in our data.
									if (!token.contains("pass")) {
										// Checks if token contains the player. Then we have to remove the player to get
										// the x and y position of the stone.
										if (token.length() == 3) {
											x = (int) token.charAt(0) - 65;
											y = Integer.valueOf((String) token.subSequence(1, token.length()));
										} else {
											x = (int) token.charAt(0) - 65;

											y = Integer.valueOf((String) token.subSequence(1, token.length()));
										}

										// Finaly create the stone containing the player and the respective position
										Stone stone = new Stone(currentPlayer, x, y);

										// Checks if the Stones are the same!
										// If they are the same the suggested move was proposed and can be flagged as
										// such.
										if (stone.getPlayer().equals(nextStone.getPlayer())
												&& stone.getX() == nextStone.getX()
												&& stone.getY() == nextStone.getY()) {

											prosedMove = new ProposedAsNextMove(true, indexOfElementInList,
													array.size(), listItem);
											System.out.println(prosedMove.toString());
											if (nextStone.getPlayer().contains("B")) {
												blackWasProposed.add(prosedMove);
											} else if (nextStone.getPlayer().contains("W")) {
												whiteWasProposed.add(prosedMove);
											} else {
												System.out.println("ERROR NO PLAYER SPECIFIED LINE:140!");
											}

											checker = false;
											break;
										}

										indexOfElementInList++;

									}

								}
								if (checker == false) {
									break;
								}
								listItem++;
								if (prosedMove == null) {
									prosedMove = new ProposedAsNextMove(false, 0, 0, 0);

									if (j % 2 == 0) {
										blackWasProposed.add(prosedMove);
									} else {
										whiteWasProposed.add(prosedMove);
									}

								}
							}
						} else {

						}

						System.out.println("----------------------------------------");
						System.out.println("Analysed: " + analysedFileName);
						System.out.println("Vergin : " + verginFileName);
						System.out.println("----------------------------------------");
						// Set up score lead and uility values and place them into an array.
						// The j%2 is black since everytime in Go the BLACK player starts.
						double scoreCurrent = current.get("rootInfo").getAsJsonObject().get("scoreLead").getAsDouble();
						double utility = current.get("rootInfo").getAsJsonObject().get("scoreLead").getAsDouble();
						if (j % 2 == 0) {
							blackScoreValues.add(scoreCurrent);
							blackUtilityValues.add(utility);
						} else {
							whiteScoreValues.add(scoreCurrent);
							whiteUtilityValues.add(utility);
						}
					}

					// Set up the scorelead values for final output.
					Scorelead scoreLead = new Scorelead(blackScoreValues, whiteScoreValues);

					List<Double> whiteDistances = new ArrayList<Double>();
					List<Double> blackDistances = new ArrayList<Double>();

					Utility utility = new Utility(whiteUtilityValues, blackUtilityValues);
					// White and black player stones with their respective positions.
					PlayerStone pls = readCompleteFile(verginFiles[i]);

					// Retrieving black and white player stones.
					List<Stone> whiteStones = pls.getWhitePlayerStones();
					List<Stone> blackStones = pls.getBlackPlayerStones();
					// Getting and setting up the white Euclidean distances in a list. (For black and white).
					for (Stone st : whiteStones) {
						whiteDistances.add(st.getEuclidienDistance());
					}

					for (Stone st : blackStones) {
						blackDistances.add(st.getEuclidienDistance());
					}
					
					// Creating the final JSON String and store it into a file.
					BlackWhiteNextMove bwNext = new BlackWhiteNextMove(whiteWasProposed, blackWasProposed);
					EucliResult whiteResult = new EucliResult(whiteDistances);
					EucliResult blackResult = new EucliResult(blackDistances);
					Gson gson = new Gson();
					
					String utilityString = gson.toJson(utility);
					String scoreLeadString = gson.toJson(scoreLead);
					String blackResultString = gson.toJson(blackResult);
					String whiteResultString = gson.toJson(whiteResult);
					String bwNextString = gson.toJson(bwNext);
					
					// Store to a file. Specified at the beginning.
					whenWriteStringUsingBufferedWritter_thenCorrect(blackResultString, euclidienBlackFile);
					whenWriteStringUsingBufferedWritter_thenCorrect(whiteResultString, euclidienWhiteFile);
					whenWriteStringUsingBufferedWritter_thenCorrect(utilityString, utilityFile);
					whenWriteStringUsingBufferedWritter_thenCorrect(scoreLeadString, scoreleadFile);
					whenWriteStringUsingBufferedWritter_thenCorrect(bwNextString, representedFile);

				}
			}

		}

	}

	/**
	 * Function which allows to get the current file and returns it to a String.
	 * 
	 * @param f the current file.
	 * @return the file as a String.
	 * @throws IOException
	 */
	public static String getJsonText(File f) throws IOException {
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		StringBuffer sb = new StringBuffer();
		String line;

		StringBuilder textBuilder = new StringBuilder();
		while ((line = br.readLine()) != null) {
			textBuilder.append(line);
		}

		return textBuilder.toString();
	}

	/**
	 * Function which allows to write a String into a file.
	 * 
	 * @param jsonString the string which is used to be written into the file.
	 * @param file       the file in which the string needs to be stored.
	 * @throws IOException
	 */
	public static void whenWriteStringUsingBufferedWritter_thenCorrect(String jsonString, File file)
			throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(jsonString);
		writer.flush();
		writer.close();
	}

	/**
	 * Function which allows to read the complete file (Vergin Files SGF_FILES). The
	 * file is converted into a string and gets processed to get all the stones
	 * position from the board.
	 * 
	 * @param file the vergin sgf file as a parameter.
	 * @return returns PlayerStone which contains two lists containing the white and
	 *         black player stone positions.
	 * @throws Exception
	 */
	public static PlayerStone readCompleteFile(File file) throws Exception {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		StringBuilder stringBuilder = new StringBuilder();
		String currentLine;
		while ((currentLine = br.readLine()) != null)
			stringBuilder.append(currentLine);
		return getAllMoves(stringBuilder);
	}

	/**
	 * Function which allows to get ALL the stones from the SGF file.
	 * 
	 * @param file (Vergin files)
	 * @return return all Stones from all the players.
	 * @throws Exception
	 */
	public static List<Stone> getAllStonesFromTheGame(File file) throws Exception {

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		StringBuilder stringBuilder = new StringBuilder();
		String currentLine;
		while ((currentLine = br.readLine()) != null)
			stringBuilder.append(currentLine);
		return getAllMovesForAllThePLayers(stringBuilder);
	}

	/**
	 * Function which allows to retrieve all the moves from the players in the same
	 * list.
	 * 
	 * @param builder The string containg all the moves (vergin files sgf).
	 * @return List of stones containing each move by every player.
	 * @throws Exception
	 */
	public static List<Stone> getAllMovesForAllThePLayers(StringBuilder builder) throws Exception {
		List<Stone> allStones = new ArrayList<Stone>();
		Matcher matcherActualMoves = patternSymboleBefore.matcher(builder);
		while (matcherActualMoves.find()) {
			String moveWithoutSymbol = matcherActualMoves.group().replace(";", "");
			Stone stone = new Stone(moveWithoutSymbol);
			allStones.add(stone);
		}
		return allStones;
	}

	/**
	 * Function which returns all the moves from all the players. Specifically Black
	 * and white players.
	 * 
	 * @param builder The string of the players moves (Normally the processed vergin
	 *                files as a string).
	 * @return PlayerStone Object contining the moves of the white and black player.
	 * @throws Exception
	 */
	public static PlayerStone getAllMoves(StringBuilder builder) throws Exception {
		List<Stone> whitePlayerStones = new ArrayList<Stone>();
		List<Stone> blackPlayerStones = new ArrayList<Stone>();

		Matcher matcherActualMoves = patternSymboleBefore.matcher(builder);

		while (matcherActualMoves.find()) {
			String moveWithoutSymbol = matcherActualMoves.group().replace(";", "");
			Stone stone = new Stone(moveWithoutSymbol);

			if (stone.getPlayer().equals("B")) {
				blackPlayerStones.add(stone);

			} else if (stone.getPlayer().equals("W")) {
				whitePlayerStones.add(stone);
			} else {
				throw new Exception("Stone playername missing");
			}
		}

		PlayerStone plS = new PlayerStone(blackPlayerStones, whitePlayerStones);
		return plS;
	}

	/**
	 * Other functions which aren't required. public static ArrayList<Result>
	 * getResultForOnePlayer(JsonArray jsonArray, JsonArray rootInfos, String
	 * player) {
	 * 
	 * String stoneFrom = null; String stoneTo = null; ArrayList<Result> resultList
	 * = new ArrayList<Result>();
	 * 
	 * double eucLi = 0; for (JsonElement elem : jsonArray) {
	 * 
	 * List<Value> values = new ArrayList<Value>(); JsonObject obj =
	 * elem.getAsJsonObject();
	 * 
	 * int turnNumberFrom = Integer.valueOf(obj.get("turnNumFrom").toString());
	 * 
	 * eucLi = Double.valueOf(obj.get("distance").toString()); stoneFrom =
	 * obj.get("from").toString().replace("\"", ""); JsonObject
	 * jsonTurnNumberFrom_Object = (JsonObject) rootInfos.get(turnNumberFrom);
	 * 
	 * JsonObject rootInfoFrom_Object = (JsonObject)
	 * jsonTurnNumberFrom_Object.get("rootInfo"); Value valueFrom =
	 * getValue(rootInfoFrom_Object, false);
	 * 
	 * JsonObject jsonTurnNumberTo_Object = (JsonObject)
	 * rootInfos.get(turnNumberFrom); JsonObject rootInfoTo_Object = (JsonObject)
	 * jsonTurnNumberTo_Object.get("rootInfo"); stoneTo =
	 * obj.get("to").toString().replace("\"", "");
	 * 
	 * Value valueTO = getValue(rootInfoTo_Object, true);
	 * 
	 * values.add(valueFrom); values.add(valueTO); // resultList.add(new
	 * Result(stoneFrom, stoneTo, eucLi, values)); } return resultList; }
	 * 
	 * 
	 * public static Value getValue(JsonObject rootInfo, boolean isItValueTo) {
	 * String utility = rootInfo.get("utility").toString(); String scoreLead =
	 * rootInfo.get("scoreLead").toString(); String winrate =
	 * rootInfo.get("winrate").toString(); String scoreStdev =
	 * rootInfo.get("scoreStdev").toString(); return new Value(winrate, scoreLead,
	 * utility, isItValueTo); }
	 */

}
