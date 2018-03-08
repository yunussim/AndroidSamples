package yunussimulya.gmail.com.mysample.data;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.util.Base64;
import android.util.DisplayMetrics;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataHandler {

	public static long getFolderSize(File f) {
		long size = 0;
		if (f.isDirectory()) {
			for (File file : f.listFiles()) {
				size += getFolderSize(file);
			}
		} else {
			size = f.length();
		}
		return size;
	}

	public static Bitmap getBitmapFromURL(String link) {
		try {
			URL url = new URL(link);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			return BitmapFactory.decodeStream(input);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static float convertDpToPixel(float dp, Context context){
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
		return px;
	}

	public static float convertPixelsToDp(float px, Context context){
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
		return dp;
	}

	public static String getB64Auth (String login, String pass) {
		String source = login + ":" + pass;
		return "Basic " + Base64.encodeToString(source.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
	}

	public static boolean checkEmail(String s) {
		Pattern p = Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])) {1}|([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$");
		//Match the given string with the pattern
		Matcher m = p.matcher(s);

		//check whether match is found
		return m.matches();
	}

	public static Calendar getCalendarFromString(String dateString, String datePattern) {
		//Z = timezone
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(datePattern);
		try {
			Date date = format.parse(dateString);
			cal.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cal;
	}

	public static String getDateFromString(String dateString, String datePattern, String outputPattern) {
		//Z = timezone
		String result = "";
		SimpleDateFormat format = new SimpleDateFormat(datePattern);
		DateFormat targetFormat = new SimpleDateFormat(outputPattern);
		try {
			Date date = format.parse(dateString);
			result = targetFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Date getDate(String dateString, String datePattern) {
		//Z = timezone
		Date result = null;
		SimpleDateFormat format = new SimpleDateFormat(datePattern);
		try {
			result = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			result = Calendar.getInstance().getTime();
		}
		return result;
	}

	public static Address startReverseGeocode(Activity activity, float latitude, float longitude) {
		if (Geocoder.isPresent()) {
			Geocoder geocoder = new Geocoder(activity, new Locale("id"));
			try {
				List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
				return addresses.get(0);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			sb.deleteCharAt(sb.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static int getCameraPhotoOrientation(Context context, String imagePath) {
	    int rotate = 0;
	    try {
	        File imageFile = new File(imagePath);
	        ExifInterface exif = new ExifInterface(
	                imageFile.getAbsolutePath());
	        int orientation = exif.getAttributeInt(
	                ExifInterface.TAG_ORIENTATION,
	                ExifInterface.ORIENTATION_NORMAL);
	        switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_270:
					rotate = 270;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					rotate = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_90:
					rotate = 90;
					break;
				default:
					break;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	   return rotate;
	}

	public static ExifInterface getExifData(Context context, String imagePath) {
		File imageFile = new File(imagePath);
        ExifInterface exif = null;
		try {
			exif = new ExifInterface(imageFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	   return exif;
	}
	
	public static Bitmap getResizedBitmap(Bitmap img, int newHeight, int newWidth) {
		Bitmap result = null;
		try {
			int width = img.getWidth();
			int height = img.getHeight();
			
			float scaleWidth = ((float) newWidth) / width;
	        float scaleHeight = ((float) newHeight) / height;
	
		    Matrix matrix = new Matrix();
		    matrix.postScale(scaleWidth, scaleHeight);

		    result = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return result;
	}

	public static Bitmap getResizedBitmapAndRotate(Bitmap img, int newHeight, int newWidth, int degree) {
		Bitmap result = null;
		try {
			int width = img.getWidth();
			int height = img.getHeight();

			float scaleWidth = ((float) newWidth) / width;
			float scaleHeight = ((float) newHeight) / height;

			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			matrix.postRotate(degree, width / 2, height / 2);

			result = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Bitmap decodeBitmapFromPathWithSampleSize(String path, int inSampleSize) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inPreferredConfig = Config.ARGB_8888;
	    options.inSampleSize = inSampleSize;
	    BitmapFactory.decodeFile(path, options);
	    return BitmapFactory.decodeFile(path, options);
	}

	public static String getCurrency(float price) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');

		DecimalFormat decimalFormat = new DecimalFormat("Rp #,###", symbols);
		return decimalFormat.format(price);
	}

	public static String getCurrency(double price) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');

		DecimalFormat decimalFormat = new DecimalFormat("Rp #,###", symbols);
		return decimalFormat.format(price);
	}

	public static String escapeJavaString(String st) {
        StringBuilder builder = new StringBuilder();
        try {
            for (int i = 0; i < st.length(); i++) {
                 char c = st.charAt(i);
                 if (!Character.isLetterOrDigit(c) && !Character.isSpaceChar(c) && !Character.isWhitespace(c)) {
                     String unicode = String.valueOf(c);
                     int code = (int)c;
                     if (!(code >= 0 && code <= 255)) {
                         unicode = "\\u" + Integer.toHexString(c);
                     }
                     builder.append(unicode);
                 }
                 else{
                     builder.append(c);
                 }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

	public static String stringTrim(String source) {
		String result = "";
		int i = 0;
		int lastIndex = 0;
		int continuity = 0;
		while (i > -1) {
			i = source.indexOf("\n", i);
			if (i > -1) {
				if (lastIndex + 1 == i) {
					continuity ++;
					if (continuity > 1) {
						//remove the current one
						String first = source.substring(0, lastIndex);
						String next = source.substring(lastIndex + 1);
						source = first + next;
						continuity = 0;
					}
				}
				lastIndex = i;
				i++;
			}
		}
		source = source.replaceAll(" +", " ");
		result = source.trim();
		return result;
	}

	public static boolean isApplicationInstalled(Activity activity, String uri) {
		PackageManager pm = activity.getPackageManager();
		boolean app_installed;
		try {
			pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
			app_installed = true;
		} catch (PackageManager.NameNotFoundException e) {
			app_installed = false;
		}
		return app_installed;
	}

	public static int getAge(long birthdayInMillies) {
        if (birthdayInMillies == 0) {
            return 0;
        }
		Calendar c = Calendar.getInstance();
		int todayYear = c.get(Calendar.YEAR);
		int todayDay = c.get(Calendar.DAY_OF_YEAR);
		c.setTimeInMillis(birthdayInMillies);
		int birthdayYear = c.get(Calendar.YEAR);
		int birthdayDay = c.get(Calendar.DAY_OF_YEAR);
		int age = todayYear - birthdayYear;
		if (todayDay < birthdayDay) age--;
		return age;
	}

	public static Integer versionCompare(String str1, String str2) {
		Scanner s1 = new Scanner(str1);
		Scanner s2 = new Scanner(str2);
		s1.useDelimiter("\\.");
		s2.useDelimiter("\\.");

		while (s1.hasNextInt() && s2.hasNextInt()) {
			int v1 = s1.nextInt();
			int v2 = s2.nextInt();
			if (v1 < v2) {
				return -1;
			} else if (v1 > v2) {
				return 1;
			}
		}

		if (s1.hasNextInt()) {
			return 1;
		} else if (s2.hasNextInt()) {
			return -1;
		}
		return 0;
	}

	public static String extractNumber(String source) {
		String result = source.trim();
		try {
			Pattern pt = Pattern.compile("[^0-9]");
			Matcher match = pt.matcher(source);
			while (match.find()) {
				String s = match.group();
				result = result.replaceAll(s, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}