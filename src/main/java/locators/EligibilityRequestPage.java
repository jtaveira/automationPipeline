package locators;

/**
 * Created by jose taveira gomes on 19/11/2018.
 */

public class EligibilityRequestPage {

    public String identityCardImage = "//div[@class='panel-body']/img";
    public String insurerLabel = "//label[contains(text(),'Insurer')]";
    public String insurerDropdown = "//label[contains(text(),'Insurer')]/following-sibling::div[@class='dropdown']";
    public String insurerOption = "//label[contains(text(),'Insurer')]/following-sibling::div[@class='dropdown']//div[contains(text(),'" + "" + "')]";
    public String clinicianDropdown = "//label[contains(text(),'Clinician')]/following-sibling::div[@class='dropdown']";
    public String clinicianInput = "//label[contains(text(),'Clinician')]/following-sibling::div[@class='dropdown']//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all dropdown inputtext dropdown-input']";
    public String clinicianOption =  "//label[contains(text(),'Clinician')]/following-sibling::div[@class='dropdown']//div[contains(text(),'" + "" + "')]";
    public String checkbox = "//div[@class='checkbox']/label/span";
    public String cardNumberInput = "//label[contains(text(),'Card Number')]/following-sibling::input";
    public String mobileNumberInput = "//label[contains(text(),'Mobile Number')]/following-sibling::input";
    public String serviceCategoryDropdown = "//label[contains(text(),'Service Category')]/following-sibling::div[@class='dropdown']";
    public String serviceCategoryOption =  "//label[contains(text(),'Service Category')]/following-sibling::div[@class='dropdown']//div[contains(text(),'" + "" + "')]";
    public String submitButton = "//button[@type='submit']";

    public String setInsurerOption (String option) {
        this.insurerOption = "//label[contains(text(),'Insurer')]/following-sibling::div[@class='dropdown']//div[contains(text(),'" + option + "')]";
        return this.insurerOption;
    }

    public String setClinicianOption (String option) {
        this.clinicianOption = "//label[contains(text(),'Clinician')]/following-sibling::div[@class='dropdown']//div[contains(text(),'" + option + "')]";
        return this.clinicianOption;
    }

    public String setServiceCategoryOption (String option) {
        this.serviceCategoryOption = "//label[contains(text(),'Service Category')]/following-sibling::div[@class='dropdown']//div[contains(text(),'" + option + "')]";
        return this.serviceCategoryOption;
    }

}
