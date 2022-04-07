import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;

import org.json.simple.JSONObject;
import primitives.Double3;

/**
 * Test program for the 1st stage
 *
 * @author Dan Zilberstein
 */
public final class Main {

	private static FileWriter file;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {


		String path = System.getProperty("user.dir");

		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(new FileReader(path +"\\Json\\stam.json"));

			String ambientLight = (String) obj.get("ambient-light");
			//JSONObject background = (JSONObject) obj.get("background");

			System.out.println("12345   " + ambientLight);



			JSONObject geometries = (JSONObject) obj.get("geometries");

			for (Object keyStr : geometries.keySet()) {
				JSONObject keyValue = (JSONObject) geometries.get(keyStr);
				if(keyStr.toString().startsWith("triangle")){
					String p0 = (String) keyValue.get("p0");
					String p1 = (String) keyValue.get("p1");
					String p2 = (String) keyValue.get("p2");

					Double3 d = new Double3(p0);

					System.out.println(d);

				}else if(keyStr.toString().startsWith("sphere")){
					int radius = Integer.parseInt((String) keyValue.get("radius"));
					String center = (String) keyValue.get("center");

					System.out.println("sphere  " + radius +"  " + center );
				}
			}

//			for (Object keyStr : geometries.keySet()) {
//				JSONObject keyvalue = (JSONObject) geometries.get(keyStr);
//
//				System.out.println(keyStr.toString().startsWith("triangle"));
//
//				//Print key and value
//				System.out.println("key: "+ keyStr + " value: " + keyvalue);
//
//				//for nested objects iteration if required
//				//if (keyvalue instanceof JSONObject)
//				//    printJsonObject((JSONObject)keyvalue);
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}


//		// JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
//		JSONObject scene = new JSONObject();
//		scene.put("background", "75 127 190");
//		scene.put("ambient-light", "255 191 191");
//
//		JSONArray geometries = new JSONArray();
//		geometries.add("Company: Facebook");
//		geometries.add("Company: PayPal");
//		geometries.add("Company: Google");
//		scene.put("Company List", geometries);
//		try {
//
//			// Constructs a FileWriter given a file name, using the platform's default charset
//			file = new FileWriter("C:\\Users\\pwlqr\\Documents\\ISE5782_8995_1159\\ISE5782_8995_1159\\Json\\stam.json");
//			file.write(scene.toJSONString());
//			CrunchifyLog("Successfully Copied JSON Object to File...");
//			CrunchifyLog("\nJSON Object: " + scene);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//
//		} finally {
//
//			try {
//				file.flush();
//				file.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	static public void CrunchifyLog(String str) {
//		System.out.println(str);
	}

}
















//
//	/**
//	 * Main program to tests initial functionality of the 1st stage
//	 *
//	 * @param args irrelevant here
//	 */
//	public static void main(String[] args) {
//
//
//
//
//
//		try { // test zero vector
//			new Vector(0, 0, 0);
//			out.println("ERROR: zero vector does not throw an exception");
//		} catch (Exception e) {
//		}
//
//
//
//		Vector v1 = new Vector(1, 2, 3);
//		Vector v2 = new Vector(-2, -4, -6);
//		Vector v3 = new Vector(0, 3, -2);
//
//		// test length..
//		if (!isZero(v1.lengthSquared() - 14))
//			out.println("ERROR: lengthSquared() wrong value");
//		if (!isZero(new Vector(0, 3, 4).length() - 5))
//			out.println("ERROR: length() wrong value");
//
//		// test Dot-Product
//		if (!isZero(v1.dotProduct(v3)))
//			out.println("ERROR: dotProduct() for orthogonal vectors is not zero");
//		if (!isZero(v1.dotProduct(v2) + 28))
//			out.println("ERROR: dotProduct() wrong value");
//
//		// test Cross-Product
//		try { // test zero vector
//			v1.crossProduct(v2);
//			out.println("ERROR: crossProduct() for parallel vectors does not throw an exception");
//		} catch (Exception e) {
//		}
//		Vector vr = v1.crossProduct(v3);
//		if (!isZero(vr.length() - v1.length() * v3.length()))
//			out.println("ERROR: crossProduct() wrong result length");
//		if (!isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)))
//			out.println("ERROR: crossProduct() result is not orthogonal to its operands");
//
//		// test vector normalization vs vector length and cross-product
//		Vector v = new Vector(1, 2, 3);
//		Vector u = v.normalize();
//		if (!isZero(u.length() - 1))
//			out.println("ERROR: the normalized vector is not a unit vector");
//		try { // test that the vectors are co-lined
//			v.crossProduct(u);
//			out.println("ERROR: the normalized vector is not parallel to the original one");
//		} catch (Exception e) {
//		}
//		if (v.dotProduct(u) < 0)
//			out.println("ERROR: the normalized vector is opposite to the original one");
//
//		// Test operations with points and vectors
//		Point p1 = new Point(1, 2, 3);
//		if (!(p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0))))
//			out.println("ERROR: Point + Vector does not work correctly");
//		if (!new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(p1)))
//			out.println("ERROR: Point - Point does not work correctly");
//
//		out.println("If there were no any other outputs - all tests succeeded!");
//	}
//}
