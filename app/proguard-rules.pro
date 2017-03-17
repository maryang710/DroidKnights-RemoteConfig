## Option
# https://www.guardsquare.com/en/proguard/manual/usage
-verbose
-printusage
-optimizationpasses 5
-allowaccessmodification
-dontpreverify
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers

## 공통
-libraryjars libs
-libraryjars <java.home>/lib/rt.jar
-keep class sun.misc.Unsafe { *; }
-keepattributes SourceFile, LineNumberTable, *Annotation*
-keepattributes Exceptions, Signature, InnerClasses

# 아래와 같은 것들은 무시한다
-dontwarn org.slf4j.**
-dontwarn org.dom4j.**
-dontwarn org.jdom.**
-dontwarn org.jdom2.**
-dontwarn org.codehaus.**

-dontwarn com.sun.**
-dontwarn com.google.**
-dontwarn com.joda.**
-dontwarn com.adobe.xmp.**
-dontwarn com.google.android.gms.**
-dontwarn com.thoughtworks.xstream.**

-dontwarn java.**
-dontwarn javax.**
-dontwarn sun.misc.Unsafe
-dontwarn freemarker.**
-dontwarn android.support.**
-dontwarn kr.co.rememberapp.**


## Keep classes that are referenced on the AndroidManifest
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService


## Maintain java native methods
-keepclasseswithmembernames class * {
    native <methods>;
}


## Uncomment if using Serializable
-keepclassmembers class * implements java.io.Serializable {
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}


## To maintain custom components names that are used on layouts XML:
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}


## Maintain enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


## To remove debug logs:
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** e(...);
    public static *** i(...);
    public static *** v(...);
    public static *** w(...);
}


## Keep the R
-keepclassmembers class **.R$* {
    public static <fields>;
}


## android support design
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }


## Appcompat
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}


## Google Play Services
# https://developer.android.com/google/play-services/setup.html#Proguard ##
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}


## Retrolambda specific rules ##
# as per official recommendation: https://github.com/evant/gradle-retrolambda#proguard
-dontwarn java.lang.invoke.*


## GSON
-keepattributes EnclosingMethod

# Gson specific classes
-keep class com.google.gson.stream.** { *; }
