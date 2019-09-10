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

#代码迭代优化的次数，取值范围0-7，默认5
-optimizationpasses

#混淆时不适用大小写混合的方式，这样混淆后都是小写字母的组合
-dontusemixedcaseclassnames

#混淆时不做预校验，由前面的介绍可以知道，预校验是Proguard四大功能之一，在Android中
#一般不需要校验，这样可以加快混淆的速读
-dontpreverify

#混淆时记录日志，同时会生成映射文件，AndroidStudio中，生成的默认映射文件为 build/output/mapping/release/mapping.txt
-verbose

#指定生成的映射文件的路径和名称
-printmapping build/output/mapping/release/mymapping.txt

#混淆时所采用的算法，参数是 Google 官方推荐的过滤器算法
-optimizations !code/simplification/arithemtic,!field/*,!class/merging/*

#如果项目中使用到奥注解，需要保留注解属性
-keepattributes *Annotation*

#不混淆泛型
-keepattributes Signature

#保留代码行号，这在混淆后代码运行中抛出异常信息时，有利于定位出问题的代码
-keepattributes SourceFile,LineNumberTable

#保持 Android SDK 相关 API 类不被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

#保留 R 类
-keep class **.R$*{
 *;
}

#保持native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context,android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet,int);
}

#保持 Activity 中参数时 View 类型的函数，保证在 Layout XML 文件中配置的 onClick 属性的值能够正常调用到
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}

#保持枚举 enum 类不被混淆
-keepclassmembers enum *{
    public static **[] values();
    public static ** valueOf(Java.lang.String);
}

#保持 Prrcelable 不被混淆
-keep class * implements android.os.Parcelable{
    public static final android.os.Parcelable$Creator *;
}

#保持 Serializable 序列化类相关方法和字段不被混淆
-keepclassmembers class * implements java.io.Serializable{
    static final long serialVersiionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjcetOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#保持自定义控件不被混淆
-keep public class * extends android.view.View{
    public <init>(android.content.Context);
    public <init>(android.content.Context,android.util,AttributeSet);
    public <init>(android.content.Context,android.util.AttributeSet,int);
    public void set*(...);
    *** get*();
}

#引入 LoganSquare 开源库所需增加的混淆配置
-keep class com.bluelinelabs.logansquare.** { *; }
-keep @com.bluelinelabs.logansquare.annotation.JsonObject class *
-keep class **$JsonObjcetMapper { *; }