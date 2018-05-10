import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class testYaMarket extends  BasePage{
    String str;
    String str2;


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {{"Телевизор"},{"Наушники"}});
    }

    @Parameterized.Parameter()
    public String itemName;



    //{"Телевизор"},


    @FindBy(xpath =  "//a[contains(text(),'Маркет')]")
    private WebElement buttonMarket;

    @FindBy(xpath =  "//a[contains(text(),'Электроника') and @class='link topmenu__link']")
    private WebElement buttonElectronic;

    //a[contains(text(),'Перейти')]
    @FindBy(xpath =  "//a[contains(text(),'Перейти')]")
    private WebElement buttonMove;


  /*  @FindBy(xpath =  strname)
    private WebElement buttonTV;*/

    @FindBy(xpath =  "//input[@id='header-search']")
    private WebElement searchField;

    @FindBy(xpath =  "//input[@name='glf-pricefrom-var']")
    private WebElement priceField;

    //input[@type='radio' and @name='viewtype']
    @FindBy(xpath =  "//input[@type='radio' and @name='viewtype']")
    private WebElement radioButton;

    //button[@role='listbox']
    @FindBy(xpath =  "//button[@role='listbox']")
    private WebElement listboxButton;

    //span[contains(text(),'по 12')]
    @FindBy(xpath =  "//span[contains(text(),'по 12')]")
    private WebElement Button12;

    //span[contains(text(),'Показать подходящие')]
    @FindBy(xpath =  "//span[contains(text(),'Показать подходящие')]/..")
    private WebElement ButtonFind;

    //span[text()='Samsung']
    @FindBy(xpath =  "//label[contains(text(),'Samsung')]")
    private WebElement checkNameSamsung;

    @FindBy(xpath =  "//label[contains(text(),'LG')]")
    private WebElement checkNameLG;

    @FindBy(xpath =  "//*[contains(text(),'Beats')]")
    private WebElement checkNameBeats;


    @FindBy(xpath =  "//a[contains(@class,'link n-link')]")
    private List<WebElement> listTV;


    @FindBy(xpath =  "//*[contains(text(),'Да, спасибо')]/..")
    private WebElement buttonYes;


    private testYaMarket waitAndClick(String _itemName)
    {

        String s="//a[contains(text(),'"+_itemName+"')and contains(@class,'top')]";
        System.out.println(s);


        new Actions(webDriver).moveToElement(buttonElectronic).build().perform();


        WebElement button = webDriver.findElement(By.xpath(s));
        webDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOf(button));
        button.click();
        return this;
    }

    private testYaMarket clickButton(WebElement clickButton)
    {
        wait.until(ExpectedConditions.visibilityOf(clickButton));
        webDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        new Actions(webDriver).moveToElement(clickButton).build().perform();
        clickButton.click();

        return this;
    }

    private testYaMarket sendSeachField(WebElement field,String search)
    {
        webDriver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
        field.sendKeys(search);
        return this;
    }

    private testYaMarket assertNum(){
        webDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        if (listTV.size()==12){
            System.out.println("На странице 12 элементов");
        }else{System.out.println("На странице не 12 элементов");}
        return this;
    }

    private testYaMarket getStr(){
        str =  listTV.get(0).getText();
        return this;
    }

    private testYaMarket assertField(String str1)
    {
        str2 = listTV.get(0).getText();
        Assert.assertEquals("Товар соответстувет выбору",str1,str2);
        webDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        return this;
    }


    @Test
    public void testSber()
    {
        this.clickButton(buttonMarket);

        if (itemName.equals("Телевизор")){

                this.waitAndClick(itemName)
                        .clickButton(buttonMove)
                        .sendSeachField(priceField,"20000")
                .clickButton(checkNameLG)
                .clickButton(checkNameSamsung)
                .clickButton(ButtonFind)
                .clickButton(listboxButton)
                .clickButton(Button12)
                .assertNum()
                .getStr()
                .sendSeachField(searchField,str)
                .assertField(str);
        }else{
                this.clickButton(buttonYes)
                        .waitAndClick(itemName)
                        .clickButton(buttonMove)
                        .sendSeachField(priceField,"5000")
                    .clickButton(checkNameBeats)
                        .clickButton(ButtonFind)
                        .getStr()
                    .sendSeachField(searchField,str)
                    .assertField(str);

        }
   }

}

/*
1. Открыть браузер и развернуть на весь экран.

        2. Зайти на yandex.ru.

        3. Перейти в яндекс маркет

        4. Выбрать раздел Электроника

        5. Выбрать раздел Телевизоры

        6. Зайти в расширенный поиск (Перейти ко всем фильтрам)

        7. Задать параметр поиска от 20000 рублей.

        8. Выбрать производителей Samsung и LG

        9. Нажать кнопку Применить (Показать подходящие)

        10. Выбрать в нижнем селекте - "Показывать по 12", Проверить, что элементов на странице 12.

        11. Запомнить первый элемент в списке.

        12. В поисковую строку ввести запомненное значение.

        13. Найти и проверить, что наименование товара соответствует запомненному значению.



        1. Открыть браузер и развернуть на весь экран.

        2. Зайти на yandex.ru.

        3. Перейти в яндекс маркет

        4. Выбрать раздел Электроника

        5. Выбрать раздел Наушники

        6. Зайти в расширенный поиск (Перейти ко всем фильтрам)

        7. Задать параметр поиска от 5000 рублей.

        8. Выбрать производителя Beats

        9. Нажать кнопку Применить (Показать подходящие)

        10. Выбрать в нижнем селекте - "Показывать по 12", Проверить, что элементов на странице 12.

        11. Запомнить первый элемент в списке.

        12. В поисковую строку ввести запомненное значение.

        13. Найти и проверить, что наименование товара соответствует запомненному значению.*/