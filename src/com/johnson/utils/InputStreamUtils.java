package com.johnson.utils;

import java.util.Scanner;

public class InputStreamUtils {
  
  /*
   * This is a solution for converting an InputStream into a String 
   * using only Java libraries.
   */
  @SuppressWarnings("resource")
  public static String convertStreamToString(java.io.InputStream inputStream) {
    Scanner s = new Scanner(inputStream).useDelimiter("\\A");
    if(s.hasNext()) {
      String returnString = s.next();
      s.close();
      return returnString;
    } else {
      s.close();
      return "";
    }
  }

}
