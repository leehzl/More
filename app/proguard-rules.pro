# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\ASSdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#压缩级别
-optimizationpasses 5

#不使用大小写混合类名
-dontusemixedcaseclassnames

#混淆时记录日志
-verbose

-libraryjars com.android.support:support-v4:25.2.0
-libraryjars com.google.code.gson:gson:2.7
-libraryjars com.android.support:appcompat-v7:25.2.0

