# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# Keep annotations
-keepattributes *Annotation*
-keepattributes Exceptions

-keep public class * {
  public <methods>;
  public <fields>;
}

# Keep all enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Rules for the Volley library

# Keep all classes and methods from the Volley library
-keep class com.android.volley.** { *; }
##-dontwarn com.android.volley.**

# Keep specific classes related to your imports

-keep class com.android.volley.Request { *; }
-keep class com.android.volley.RequestQueue { *; }
-keep class com.android.volley.Response { *; }
-keep class com.android.volley.VolleyError { *; }
-keep class com.android.volley.toolbox.JsonObjectRequest { *; }
-keep class com.android.volley.toolbox.Volley { *; }

# Keep JSON related classes
-keep class org.json.JSONArray { *; }
-keep class org.json.JSONObject { *; }

-keepclassmembernames class * {
   java.lang.Class class$(java.lang.String);
   java.lang.Class class$(java.lang.String, boolean);
}

-keepclasseswithmembernames class * {
   native <methods>;
}