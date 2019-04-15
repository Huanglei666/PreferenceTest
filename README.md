# 实验四 实现设置Activity                                    
>                                                                               软工（1）班 黄磊 123012016005
## 1.实验内容
能够用PreferenceFragment实现设置页面，掌握包括CheckBoxPreference、EditTextPreference、ListPreference、
PreferenceScreen以及Itent等等的用法。

## 2.相关概念
应用通常包括允许用户修改应用特性和行为的设置。例如，有些应用允许用户指定是否启用通知，或指定应用与云端同步数据的频率。

若要为应用提供设置，开发人员应该使用 Android 的 Preference API 构建一个与其他 Android 应用中的用户体验一致的界面（包括系统设置）。

![安卓手机设置页面](https://developer.android.google.cn/images/ui/settings/settings.png)

**图1 安卓手机设置页面截图**

设置是使用开发人员在 XML 文件中声明的 Preference 类的各种子类构建而成，而不是使用 View 对象构建用户界面。

Preference 对象是单个设置的构建基块。每个 Preference 均作为项目显示在列表中，并提供适当的 UI 供用户修改设置。例如，CheckBoxPreference 
可创建一个列表项用于显示复选框，ListPreference 可创建一个项目用于打开包含选择列表的对话框。

开发人员添加的每个 Preference 都有一个相应的键值对，可供系统用来将设置保存在应用设置的默认 SharedPreferences 文件中。当用户更改设置时，
系统会为开发人员更新 SharedPreferences 文件中的相应值。开发人员只应在需要读取值以根据用户设置确定应用的行为时，才与关联的 SharedPreferences 
文件直接交互。

有关所有其他子类及其对应属性的列表，请参阅谷歌官方的[Preference](https://developer.android.google.cn/reference/android/preference/Preference.html)类。

使用 XML 文件定义设置的集合是首选方法，因为该文件提供了一个便于更新的易读结构。此外，应用的设置通常是预先确定的，不过您仍可在运行时修改此集合。

每个 Preference 子类均可以使用与类名（如 <CheckBoxPreference>）匹配的 XML 元素来声明。

您必须将 XML 文件保存在 res/xml/ 目录中。尽管您可以随意命名该文件，但它通常命名为 preferences.xml。 您通常只需一个文件，因为层次结构中的分支（可打开
各自的设置列表）是使用 PreferenceScreen 的嵌套实例声明的。

## 3.实验关键/核心代码/运行效果图
1) CheckBoxPreference

显示一个包含已启用或已停用设置复选框的项目。保存的值是布尔型（如果选中则为 true）。

关键实现代码：

    <PreferenceCategory
        android:title="In-line preferences">

        <CheckBoxPreference
            android:key="pref_sync"
            android:title="CheckBox Preference"
            android:summary="This is a checkbox"
            android:defaultValue="false" />

    </PreferenceCategory>


![CheckBoxPreference 效果图](https://github.com/Huanglei666/PreferenceTest/blob/master/%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE/CheckboxPreference.png)

**图2 CheckBoxPreference 效果图**

2) EditTextPreference

打开一个包含 EditText 小部件的对话框。保存的值是 String。

关键实现代码：

        <EditTextPreference
            android:title="Edit text preference"
            android:summary="An example that uses an edit text dialog"
            android:dialogTitle="Enter your favorite animal" />

![EditTextPreference 效果图](https://github.com/Huanglei666/PreferenceTest/blob/master/%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE/EditText_Preference.png)

**图3 EditTextPreference 效果图**

3) ListPreference

打开一个包含单选按钮列表的对话框。保存的值可以是任一受支持的值类型。

关键实现代码：

        <ListPreference
            android:key="pref_syncConnectionType"
            android:title="List preference"
            android:summary="An example that uses an list dialog"
            android:dialogTitle="Choose one"
            android:entries="@array/choices"
            android:entryValues="@array/choices_items"
            android:defaultValue="1" />

![ListPreference 效果图](https://github.com/Huanglei666/PreferenceTest/blob/master/%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE/List_Preference.png)

**图4 ListPreference 效果图**

4) PreferenceScreen-CheckBox

若要将设置组放入子屏幕，则将 Preference 对象组放入 PreferenceScreen 内。<PreferenceScreen> 元素创建的项目选中后，即会打开一个单独的列表来显示嵌套设置。

关键实现代码：

        <PreferenceScreen
            android:title="Sreen Preference"
            android:summary="show another screen of preferences">

            <CheckBoxPreference
                android:key="inbox"
                android:title="Toggle preference"
                android:summary="Preference that is on the next screen but same hierarchy"
                android:defaultValue="false"/>

        </PreferenceScreen>

![PreferenceScreen-CheckBox 效果图](https://github.com/Huanglei666/PreferenceTest/blob/master/%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE/PreferenceScreen_Checkbox.png)

**图5 PreferenceScreen-CheckBox 效果图**

5) PreferenceScreen-Intent

在某些情况下，您可能需要首选项来打开不同的 Activity（而不是网络浏览器等设置屏幕）或查看网页。 要在用户选择首选项时调用 Intent，
则要将<intent> 元素添加为相应 <Preference> 元素的子元素。
如下面的代码所示：android:action存的值是它要分配的操作，android:data存的值则是本次操作要跳转的网址，本实验网址为福师大官网。

关键实现代码：

        <PreferenceScreen
            android:title="Intent Preference"
            android:summary="Launch an Activity from an Intent">

            <intent
                android:action="android.intent.action.VIEW"
                android:data="@string/FJNU"/>

        </PreferenceScreen>

![PreferenceScreen-Intent 效果图](https://github.com/Huanglei666/PreferenceTest/blob/master/%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE/PreferenceScreen_Intent.png)

**图6 PreferenceScreen-Intent 效果图**

6) Parent-Child-CheckBox-Preference

有时还要用到类似“父子”的复选框，就是只有选中了“父”复选框，“子”复选框才变成有效的，否则“子”复选框无效。
要实现以上功能，关键在于“子”复选框的android:dependency参数，它的值是“父”复选框的key值。如下代码：

关键实现代码：

        <CheckBoxPreference
            android:key="pref_sync1"
            android:title="Parent checkBox preference"
            android:summary="This is visually a parent"
            android:defaultValue="false"/>

        <CheckBoxPreference
            android:key="pref_sync2"
            android:title="Child checkBox preference"
            android:summary="This is visually a child"
            android:defaultValue="false"
            android:dependency="pref_sync1"/>

![Parent-Child-CheckBox-Preference 效果图](https://github.com/Huanglei666/PreferenceTest/blob/master/%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE/Parent_Child_Checkbox_Preference.png)

**图7 Parent-Child-CheckBox-Preferencet 效果图**

7）Preference 调用

不同于普通layout文件的加载调用：setContentView(R.layout.activity_main)，Preference.xml的加载的方式为：addPreferencesFromResource(R.xml.preferences)。

关键实现代码：

        public class Preference_Activity  extends PreferenceActivity {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                addPreferencesFromResource(R.xml.preferences);
            }
        }
