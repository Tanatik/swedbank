package swedbank.base;

import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Locator
{
    private By      _wdLocator;
    private Locator _parent;

    public Locator (String strLocator)     {
       // this._wdLocator = By.xpath(strLocator);
        this._wdLocator = parseLocator(strLocator);
    }

    private By parseLocator (String locatorString){
        final int flags = Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.UNICODE_CASE;
        // Try xPath pattern
        Pattern pattern = Pattern.compile("^\\(*((:?\\/\\/)|(:?\\/)|(:?\\.\\/)).+?$", flags);
        Matcher matcher = pattern.matcher(locatorString);
        if(matcher.matches())
            return By.xpath(locatorString);
        // Try CSS pattern
        pattern = Pattern.compile("^(#|\\.|(:?[\\w]+?[\\[\\s#\\.])).+?$", flags);
        matcher = pattern.matcher(locatorString);
        if (matcher.matches())
            return By.cssSelector(locatorString);

        throw new RuntimeException("Invalid locator "+ locatorString);
    }

    public Locator (String strLocator, Locator parent)
    {
        this(strLocator);
        this._parent = parent;
    }

    public static Locator create(String strLocator)
    {
        return new Locator(strLocator);
    }

    public Locator createChild (String strLocator)
    {
        return new Locator(strLocator, this);
    }

    public By getWDLocator()
    {
        return this._wdLocator;
    }

    public Locator getParent()
    {
        return this._parent;
    }
}
