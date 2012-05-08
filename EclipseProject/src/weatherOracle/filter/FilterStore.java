package weatherOracle.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import weatherOracle.control.MainControl;

import android.app.Activity;
import android.content.Context;
import android.util.Pair;

/**
 * keeps a single up to data copy of the filters on disk, and can update and
 * provide it on demand. Any version requested gets a version number that is
 * larger than all previous differing version returned since launch (The version
 * number may increase without changes, and may reset on launch). This is all
 * fully thread safe! All combinations of calls from differing threads are
 * allowed.
 * 
 */
public abstract class FilterStore {
	private static String fileName = "filterFile";
	private static Context ctx;
	
	private static int versionNumber = 0;
	private static byte[] data;

	/**
	 * Replace the currently saved version, and increment the filter version
	 * number.
	 * 
	 * @param filters
	 *            A list of filter to be saved.
	 */
	public synchronized static void setFilters(Activity activity, List<Filter> filters) {
		// TODO not sure if this is good style
		if (ctx == null) {
			ctx = activity.getApplicationContext();
		}
		
		ByteArrayOutputStream fbos = new ByteArrayOutputStream();
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(fbos);
			out.writeObject(filters);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		data = fbos.toByteArray();
		modified();
		versionNumber++;
		writeOut();
	}

	// save data to file
	private synchronized static void writeOut() {
		try {
			FileOutputStream fos = ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.write(data);
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// read data from file, if there is a file
	private synchronized static void readIn() {
		try {
			FileInputStream fis = ctx.openFileInput(fileName);
			List<Byte> byteList = new ArrayList<Byte>();
			int b;
			while ((b = fis.read()) != -1) {
				byteList.add(new Byte((byte)b));
			}
			byte[] array = new byte[byteList.size()];
			
			for (int i = 0; i < array.length; i++) {
				array[i] = byteList.get(i).byteValue();
			}
			data = array;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Retrieves the filters from persistent memory
	 * 
	 * @return A Pair containing a list of Filters and a version number
	 */
	public synchronized static Pair<List<Filter>, Integer> getFilters() {
		if (data == null) { // no data set or read in yet
			readIn();
			if (data == null) { // nothing to read/read failed
				return new Pair<List<Filter>, Integer>(new ArrayList<Filter>(),
						versionNumber);
			}
		}

		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new ByteArrayInputStream(data));
			List<Filter> copy = (List<Filter>) in.readObject();
			return new Pair<List<Filter>, Integer>(copy, versionNumber);
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assert (false);
		return null;
	}

	private static void modified() {
		MainControl.startUpdate();
	}
}
