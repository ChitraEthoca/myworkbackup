echo on
set JAVA_HOME=%~1
set ANT_HOME=C:\Users\P3SO\Desktop\Automation_Framework\AutomationFramework\AutomationFramework\ContactUs\Apache-Ant
set path=%JAVA_HOME%;%path%;%ANT_HOME%\bin
.\Apache-Ant\bin\ant -f build.xml compile.test RunTestNG