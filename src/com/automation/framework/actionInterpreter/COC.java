package com.automation.framework.actionInterpreter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.automation.framework.core.COCDriverScript;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.KeywordTraceException;
import com.automation.framework.exceptions.WebAdaptorException;
import com.automation.framework.webAdaptor.ElementKeywords;
import com.automation.framework.webAdaptor.Formkeywords;
import com.automation.framework.webAdaptor.JavascriptKeywords;
import com.automation.framework.webAdaptor.TableKeywords;
import com.automation.framework.webAdaptor.ValidationKeywords;
import com.automation.framework.webAdaptor.WaitKeywords;
import com.automation.framework.webAdaptor.WindowsKeywords;

// TODO: Auto-generated Javadoc
/**
 * The Class D2WebElement.
 */


public class COC {

	private static volatile Map<String, Map<String, Object>> methods = new HashMap<String, Map<String, Object>>(
			100);
	private static Map<String, Object> objMethods;
	private static String packageNameForBaseKeywords = "com.automation.framework.webAdaptor";
	public static Map<String, String> mapValues = new HashMap<String, String>();

	private static final Logger LOG = Logger.getLogger(COC.class);

	public COC() {
		objMethods = new HashMap<String, Object>();
		objMethods.put("elmentKeywords", new ElementKeywords());
		objMethods.put("formkeywords", new Formkeywords());
		objMethods.put("javascriptKeywords", new JavascriptKeywords());
		objMethods.put("tableKeywords", new TableKeywords());
		objMethods.put("validationKeywords", new ValidationKeywords());
		objMethods.put("waitKeywords", new WaitKeywords());
		objMethods.put("windowsKeywords", new WindowsKeywords());
//		objMethods.put("businessComponents", new BusinessActionFactory());
	}

	/**
	 * The Enum webElementEnum.
	 */
	public static enum Actions {

		click,getElementsCount,sendKeyboardActions, getText,handleDownloadPopup,scrollByKeyDownAndClick,setValueToXpathAndVerifyElementContainsText,verifyElementContainsText,switchToFrameBasedOnWebElement,switchToFrameBasedOnFrameNo,isElementNotExists,setValueToXpathAndVerifyElementTextUsingJavascript,setValueToXpathAndDoubleClick, setValueToXpathAndRightClick, 
		setXpathAndwaitForObjectToLoad, doubleClick, rightClick, IsElementExist, isElementDisplayed, 
		getElementAttributeValue, mouseHover, clear, setValueToXpathAndClick, selectCheckBox, unSelectCheckBox, 
		isCheckboxChecked, isRadioButtonSelected, selectValueFromDropdown, setMandatoryTextToElement, 
		setOptionalTextToElement, scrollAndClick, scrollToBottom, handleAlert, injectJsOnNotVisible, 
		verifyPageTitle, verifyElementText, verifyPageURL, implicitWait, wait, waitUntilElementVisible,clickUsingJavascript, 
		waitUntilElementClickable, closeBrowser, navigateToURL, switchBetweenWindows, switchToWindowTitle, 
		maximize,setXpathAndElementCount,elementCount, switchToFrame, closeAllWindows, switchToLastWindow, waitForObjectToLoad, 
		setText, setValueToXpathAndGetText, setValueToXpathAndverifyElementText, 
		clickOnContextMenu, setValueToXpathAndSetText, isElementDisabled, setValueToXpathAndIsElementNotExists, setvalueToXpathAndIsElementExist,verifyBrokenLink, 
		isElementNotDisplayed,setValueToXpathAndElementNotDisplayed,setValueToXpathAndElementDisabled,setValueToXpathAndElementDisplayed,setValueToXpathAndGetTextUsingJavascript,setValueToXpathAndGetCssValue,setValueToXpathAndGetElementAttributeValue,scrollAndClickBasedOnTags,setValueToXpathAndSetTextUsingJavascript,verifyBrokenLinkByLinkName,setTextUsingJavaScript,getTextUsingJavascript, verifyTextUsingJavascript,verifyElementTextUsingJavascript,waitForText,setValueToXpathAndClickUsingJavascript
	
	}


	/**
	 * Web element action invoke.
	 *
	 * @param elementEnum
	 *            the element enum
	 * @param object
	 *            the object
	 * @param args
	 *            the args
	 * @throws DriverScriptException
	 */
	public static void webAdaptor(Actions elementEnum, String... args)
			throws WebAdaptorException {
		if (LOG.isDebugEnabled()) {
			COCDriverScript
					.logMessage(
							"info", "webElementActionInvoke(Actions, String, String..)- Start"); //$NON-NLS-1$
		}
		switch (args.length) {
		case 4:
			webElementInvoke(elementEnum, args[0], args[1], args[2], args[3]);
			break;
		case 3:
			webElementInvoke(elementEnum, args[0], args[1], args[2], null);
			break;
		case 2:
			webElementInvoke(elementEnum, args[0], args[1], null, null);
			break;
		case 1:
			webElementInvoke(elementEnum, args[0], null, null, null);
			break;
		default:
			COCDriverScript
					.logMessage(
							"info",
							"calling webElementInvoke(elementEnum, object, null, null) with no arguments by default");
			webElementInvoke(elementEnum, null, null, null, null);
			break;
		}
		if (LOG.isDebugEnabled()) {
			COCDriverScript
					.logMessage(
							"info", "webElementActionInvoke(Actions, String, String..)- End"); //$NON-NLS-1$
		}
	}

	/**
	 * 
	 * webadaptor invoke to execute the keywords
	 * @param elementEnum
	 * @param object
	 * @param text
	 * @param string
	 * @throws WebAdaptorException
	 */
	private static void webElementInvoke(Actions elementEnum, String arg0,
			String arg1, String arg2, String args) throws WebAdaptorException {

		if (LOG.isDebugEnabled()) {
			COCDriverScript
					.logMessage(
							"info", "webElementInvoke(Actions, String, String, String)- start"); //$NON-NLS-1$
		}
		try {
			Map<String, Object> argMap = new HashMap<String, Object>();
			COC d2WebElement = new COC();
			Method actionMethd = null;
			String methodName = elementEnum.name();
			synchronized (methods) {
				actionMethd = traceKeyword(arg0, arg1, arg2, args, argMap,
						d2WebElement, actionMethd, methodName,
						packageNameForBaseKeywords);
				COCDriverScript.logMessage("info",
						"calling traceKeyword method webElementInvoke");
			}
			if (actionMethd != null) {
				COCDriverScript
						.logMessage("info",
								"verifying and invoking actionMethod for webElementInvoke ");
				try {
					actionMethd.invoke(methods.get(methodName)
							.get("objectPath"), argMap);
				} catch (IllegalAccessException | IllegalArgumentException e) {
					COCDriverScript
							.logMessage(
									"error",
									"webElementInvoke(Actions, String, String, String)- passing illigal argument or illigal action");
					throw new WebAdaptorException(
							"Error while executing keywords" + e);
				} catch (InvocationTargetException e) {
					try {
						throw e.getCause();
					} catch (Throwable e1) {
						throw new WebAdaptorException(e1.getCause());
					}
				}
			}
		} catch (KeywordTraceException e) {
			throw new WebAdaptorException(e);
		}
		if (LOG.isDebugEnabled()) {
			COCDriverScript
					.logMessage(
							"info", "webElementInvoke(Actions, String, String, String)- end"); //$NON-NLS-1$
		}
	}

	/**
	 * 
	 * trace the class of the keyword by providing keyword name 
	 * @param object
	 * @param text
	 * @param argMap
	 * @param d2WebElement
	 * @param actionMethd
	 * @param methodName
	 * @return
	 * @throws DriverScriptException
	 */
	public static Method traceKeyword(String arg0, String arg1, String arg2,
			String arg3, Map<String, Object> argMap, COC d2WebElement,
			Method actionMethd, String methodName, String packageName)
			throws KeywordTraceException {

		if (LOG.isDebugEnabled()) {
			COCDriverScript
					.logMessage(
							"info", "traceKeyword(String, String ,argMap,  d2WebElement ,actionMethd ,methodName,packageName) - Start"); //$NON-NLS-1$
		}
		argExtract(arg0, "arg0", argMap);
		argExtract(arg1, "arg1", argMap);
		argExtract(arg2, "arg2", argMap);
		argExtract(arg3, "arg3", argMap);

		boolean foundMethod = false;
		if (methods.get(methodName) == null) {
			COCDriverScript.logMessage("info", "verifying invoking the method");
			for (Map.Entry<String, Object> entry : objMethods.entrySet()) {
				try {
					actionMethd = entry.getValue().getClass()
							.getMethod(methodName, HashMap.class);
					Map<String, Object> miniMap = new HashMap<String, Object>();
					miniMap.put("methodPath", actionMethd);
					miniMap.put("objectPath", entry.getValue());
					methods.put(methodName, miniMap);
					foundMethod = true;
					break;
				} catch (NoSuchMethodException | SecurityException e) {
					COCDriverScript.logMessage("error", methodName
							+ "does not exists in  "
							+ entry.getValue().getClass().getName()
							+ " checking in next class....");
				}
			}
			if (!foundMethod) {
				COCDriverScript.logMessage("error",
						"method not found in the classes ");
				throw new KeywordTraceException(new Throwable(methodName
						+ " keyword not found in the " + packageName
						+ " package classes"));
			}
		} else {
			actionMethd = (Method) methods.get(methodName).get("methodPath");
		}
		if (LOG.isDebugEnabled()) {
			COCDriverScript
					.logMessage(
							"info",
							"traceKeyword(String, String ,argMap,  d2WebElement ,actionMethd ,methodName,packageName) - End");
		}
		return actionMethd;
	}

	/**
	 * Arg extract- extract the value of the value which are stored in the map during script execution
	 *
	 * @param arg the arg
	 * @param key the key
	 * @param argMap the arg map
	 * @throws KeywordTraceException the keyword trace exception
	 */
	private static void argExtract(String arg, String key,
			Map<String, Object> argMap) throws KeywordTraceException {
		if (arg != null) {
			if (arg.startsWith("###")) {
				arg = getValueFromMap(arg);
			}
			COCDriverScript.logMessage("info", "putting text into argMap");
			argMap.put(key, arg);
		}
	}

	/**
	 * Gets the value from map.
	 *
	 * @param args the args
	 * @return the value from map
	 * @throws KeywordTraceException the keyword trace exception
	 */
	private static String getValueFromMap(String args)
			throws KeywordTraceException {
		String strValue = args.substring(3, args.length());
		if (mapValues.get(strValue) == null) {
			throw new KeywordTraceException(new Throwable(strValue
					+ " is not set by any keywords"));
		}
		return mapValues.get(strValue);

	}

	

}