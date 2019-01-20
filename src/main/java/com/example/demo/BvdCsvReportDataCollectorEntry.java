package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.EMPTY;

public class BvdCsvReportDataCollectorEntry {

    private String companyName = EMPTY;
    private String bvdId = EMPTY;
    private String city = EMPTY;
    private String regionInCountry = EMPTY;
    private String country = EMPTY;
    private String countryIso = EMPTY;
    private String standardisedLegalForm = EMPTY;
    private String tickerNo = EMPTY;
    private List<String> akaName = new ArrayList<>();
    private String usSicPrimary = EMPTY;
    private String usSicPrimaryIbidText = EMPTY;
    private String usSicSecondary = EMPTY;
    private String usSicSecondaryIbidText = EMPTY;
    private String operatingRevenueUSD = EMPTY;
    private List<String> globalParentCompanyName = new ArrayList<>();
    private List<String> globalParentBvdId = new ArrayList<>();
    private List<String> globalParentISO = new ArrayList<>();
    private List<String> immediateParentCompanyName = new ArrayList<>();
    private List<String> immediateParentBvDID = new ArrayList<>();
    private List<String> immediateParentISO = new ArrayList<>();
    private List<String> domesticParentCompanyName = new ArrayList<>();
    private List<String> domesticParentBVDID = new ArrayList<>();
    private List<String> domesticParentISO = new ArrayList<>();

    private List<String> shareholderCompanyName = new ArrayList<>();
    private List<String> shareholderCity = new ArrayList<>();
    private List<String> shareholderIsoCode = new ArrayList<>();
    private List<String> shareholderTicker = new ArrayList<>();
    private List<String> shareholderBvdID = new ArrayList<>();
    private List<String> shareholderType = new ArrayList<>();
    private List<String> shareholderDirect = new ArrayList<>();
    private List<String> shareholderTotal = new ArrayList<>();
    private List<String> source = new ArrayList<>();
    private List<String> date = new ArrayList<>();

    private List<String> subsidiaryName = new ArrayList<>();
    private List<String> subsidiaryBVDID = new ArrayList<>();
    private List<String> subsidiaryStatus = new ArrayList<>();
    private List<String> subsidiaryType = new ArrayList<>();
    private List<String> subsidiaryLevel = new ArrayList<>();
    private List<String> subsidiaryCity = new ArrayList<>();
    private List<String> subsidiaryTicker = new ArrayList<>();
    private List<String> subsidiaryIsoCode = new ArrayList<>();
    private List<String> subsidiaryDirect = new ArrayList<>();
    private List<String> subsidiaryTotal = new ArrayList<>();
    private List<String> subsidiarySource = new ArrayList<>();
    private List<String> subsidiaryDate = new ArrayList<>();

    private String typeOfShareholder = EMPTY;
    private String percentageOfDirectShareholding = EMPTY;
    private String percentageOfTotalShareholding = EMPTY;
    private String sourceOfShareholdingInformation = EMPTY;
    private String dateOfShareholdingInformation = EMPTY;
    private String shareholderLevel = EMPTY;

    private String role = EMPTY;

    private List<String> globalParentSalutation = new ArrayList<>();
    private List<String> globalParentFirstName = new ArrayList<>();
    private List<String> globalParentLastName = new ArrayList<>();
    private List<String> globalParentTicker = new ArrayList<>();
    private List<String> globalUltimateParentType = new ArrayList<>();
    private List<String> globalParentCity = new ArrayList<>();

    private List<String> controllingShareholderCompanyName = new ArrayList<>();
    private List<String> controllingShareholderSalutation = new ArrayList<>();
    private List<String> controllingShareholderFirstName = new ArrayList<>();
    private List<String> controllingShareholderLastName = new ArrayList<>();
    private List<String> controllingShareholderBVDID = new ArrayList<>();
    private List<String> controllingShareholderTicker = new ArrayList<>();
    private List<String> controllingShareholderISO = new ArrayList<>();
    private List<String> controllingShareholderCity = new ArrayList<>();
    private List<String> controllingShareholderType = new ArrayList<>();
    private List<String> controllingShareholderLevel = new ArrayList<>();

    private String salutationOfGlobalParent = EMPTY;
    private String firstNameOfGlobalParent = EMPTY;
    private String lastNameOfGlobalParent = EMPTY;

    private String salutationOfControllingShareholder = EMPTY;
    private String firstNameOfControllingShareholder = EMPTY;
    private String lastNameOfControllingShareholder = EMPTY;

    private String levelOfControllingShareholder = EMPTY;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBvdId() {
        return bvdId;
    }

    public void setBvdId(String bvdId) {
        this.bvdId = bvdId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegionInCountry() {
        return regionInCountry;
    }

    public void setRegionInCountry(String regionInCountry) {
        this.regionInCountry = regionInCountry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryIso() {
        return countryIso;
    }

    public void setCountryIso(String countryIso) {
        this.countryIso = countryIso;
    }

    public String getStandardisedLegalForm() {
        return standardisedLegalForm;
    }

    public void setStandardisedLegalForm(String standardisedLegalForm) {
        this.standardisedLegalForm = standardisedLegalForm;
    }

    public String getTickerNo() {
        return tickerNo;
    }

    public void setTickerNo(String tickerNo) {
        this.tickerNo = tickerNo;
    }

    public List<String> getAkaName() {
        return akaName;
    }

    public void setAkaName(List<String> akaName) {
        this.akaName = akaName;
    }

    public String getUsSicPrimary() {
        return usSicPrimary;
    }

    public void setUsSicPrimary(String usSicPrimary) {
        this.usSicPrimary = usSicPrimary;
    }

    public String getUsSicPrimaryIbidText() {
        return usSicPrimaryIbidText;
    }

    public void setUsSicPrimaryIbidText(String usSicPrimaryIbidText) {
        this.usSicPrimaryIbidText = usSicPrimaryIbidText;
    }

    public String getUsSicSecondary() {
        return usSicSecondary;
    }

    public void setUsSicSecondary(String usSicSecondary) {
        this.usSicSecondary = usSicSecondary;
    }

    public String getUsSicSecondaryIbidText() {
        return usSicSecondaryIbidText;
    }

    public void setUsSicSecondaryIbidText(String usSicSecondaryIbidText) {
        this.usSicSecondaryIbidText = usSicSecondaryIbidText;
    }

    public String getOperatingRevenueUSD() {
        return operatingRevenueUSD;
    }

    public void setOperatingRevenueUSD(String operatingRevenueUSD) {
        this.operatingRevenueUSD = operatingRevenueUSD;
    }

    public List<String> getGlobalParentCompanyName() {
        return globalParentCompanyName;
    }

    public void setGlobalParentCompanyName(List<String> globalParentCompanyName) {
        this.globalParentCompanyName = globalParentCompanyName;
    }

    public List<String> getGlobalParentBvdId() {
        return globalParentBvdId;
    }

    public void setGlobalParentBvdId(List<String> globalParentBvD) {
        this.globalParentBvdId = globalParentBvD;
    }

    public List<String> getGlobalParentISO() {
        return globalParentISO;
    }

    public void setGlobalParentISO(List<String> globalParentISO) {
        this.globalParentISO = globalParentISO;
    }

    public List<String> getImmediateParentCompanyName() {
        return immediateParentCompanyName;
    }

    public void setImmediateParentCompanyName(List<String> immediateParentCompanyName) {
        this.immediateParentCompanyName = immediateParentCompanyName;
    }

    public List<String> getImmediateParentBvDID() {
        return immediateParentBvDID;
    }

    public void setImmediateParentBvDID(List<String> immediateParentBvDID) {
        this.immediateParentBvDID = immediateParentBvDID;
    }

    public List<String> getImmediateParentISO() {
        return immediateParentISO;
    }

    public void setImmediateParentISO(List<String> immediateParentISO) {
        this.immediateParentISO = immediateParentISO;
    }

    public List<String> getDomesticParentCompanyName() {
        return domesticParentCompanyName;
    }

    public void setDomesticParentCompanyName(List<String> domesticParentCompanyName) {
        this.domesticParentCompanyName = domesticParentCompanyName;
    }

    public List<String> getDomesticParentBVDID() {
        return domesticParentBVDID;
    }

    public void setDomesticParentBVDID(List<String> domesticParentBVDID) {
        this.domesticParentBVDID = domesticParentBVDID;
    }

    public List<String> getDomesticParentISO() {
        return domesticParentISO;
    }

    public void setDomesticParentISO(List<String> domesticParentISO) {
        this.domesticParentISO = domesticParentISO;
    }

    public List<String> getShareholderCompanyName() {
        return shareholderCompanyName;
    }

    public void setShareholderCompanyName(List<String> shareholderCompanyName) {
        this.shareholderCompanyName = shareholderCompanyName;
    }

    public List<String> getShareholderBvdID() {
        return shareholderBvdID;
    }

    public void setShareholderBvdID(List<String> shareholderBvdID) {
        this.shareholderBvdID = shareholderBvdID;
    }

    public List<String> getShareholderType() {
        return shareholderType;
    }

    public void setShareholderType(List<String> shareholderType) {
        this.shareholderType = shareholderType;
    }

    public List<String> getShareholderDirect() {
        return shareholderDirect;
    }

    public void setShareholderDirect(List<String> shareholderDirect) {
        this.shareholderDirect = shareholderDirect;
    }

    public List<String> getShareholderTotal() {
        return shareholderTotal;
    }

    public void setShareholderTotal(List<String> shareholderTotal) {
        this.shareholderTotal = shareholderTotal;
    }

    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public String getShareholderLevel() {
        return shareholderLevel;
    }

    public void setShareholderLevel(String shareholderLevel) {
        this.shareholderLevel = shareholderLevel;
    }

    public List<String> getSubsidiaryName() {
        return subsidiaryName;
    }

    public void setSubsidiaryName(List<String> subsidiaryName) {
        this.subsidiaryName = subsidiaryName;
    }

    public List<String> getSubsidiaryBVDID() {
        return subsidiaryBVDID;
    }

    public void setSubsidiaryBVDID(List<String> subsidiaryBVDID) {
        this.subsidiaryBVDID = subsidiaryBVDID;
    }

    public List<String> getSubsidiaryStatus() {
        return subsidiaryStatus;
    }

    public void setSubsidiaryStatus(List<String> subsidiaryStatus) {
        this.subsidiaryStatus = subsidiaryStatus;
    }

    public List<String> getSubsidiaryType() {
        return subsidiaryType;
    }

    public void setSubsidiaryType(List<String> subsidiaryType) {
        this.subsidiaryType = subsidiaryType;
    }

    public List<String> getSubsidiaryLevel() {
        return subsidiaryLevel;
    }

    public void setSubsidiaryLevel(List<String> subsidiaryLevel) {
        this.subsidiaryLevel = subsidiaryLevel;
    }

    public String getTypeOfShareholder() {
        return typeOfShareholder;
    }

    public void setTypeOfShareholder(String typeOfShareholder) {
        this.typeOfShareholder = typeOfShareholder;
    }

    public String getPercentageOfDirectShareholding() {
        return percentageOfDirectShareholding;
    }

    public void setPercentageOfDirectShareholding(String percentageOfDirectShareholding) {
        this.percentageOfDirectShareholding = percentageOfDirectShareholding;
    }

    public String getPercentageOfTotalShareholding() {
        return percentageOfTotalShareholding;
    }

    public void setPercentageOfTotalShareholding(String percentageOfTotalShareholding) {
        this.percentageOfTotalShareholding = percentageOfTotalShareholding;
    }

    public String getSourceOfShareholdingInformation() {
        return sourceOfShareholdingInformation;
    }

    public void setSourceOfShareholdingInformation(String sourceOfShareholdingInformation) {
        this.sourceOfShareholdingInformation = sourceOfShareholdingInformation;
    }

    public String getDateOfShareholdingInformation() {
        return dateOfShareholdingInformation;
    }

    public void setDateOfShareholdingInformation(String dateOfShareholdingInformation) {
        this.dateOfShareholdingInformation = dateOfShareholdingInformation;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getShareholderCity() {
        return shareholderCity;
    }

    public void setShareholderCity(List<String> shareholderCity) {
        this.shareholderCity = shareholderCity;
    }

    public List<String> getShareholderIsoCode() {
        return shareholderIsoCode;
    }

    public void setShareholderIsoCode(List<String> shareholderIsoCode) {
        this.shareholderIsoCode = shareholderIsoCode;
    }

    public List<String> getShareholderTicker() {
        return shareholderTicker;
    }

    public void setShareholderTicker(List<String> shareholderTicker) {
        this.shareholderTicker = shareholderTicker;
    }

    public List<String> getSubsidiaryCity() {
        return subsidiaryCity;
    }

    public void setSubsidiaryCity(List<String> subsidiaryCity) {
        this.subsidiaryCity = subsidiaryCity;
    }

    public List<String> getSubsidiaryTicker() {
        return subsidiaryTicker;
    }

    public void setSubsidiaryTicker(List<String> subsidiaryTicker) {
        this.subsidiaryTicker = subsidiaryTicker;
    }

    public List<String> getSubsidiaryIsoCode() {
        return subsidiaryIsoCode;
    }

    public void setSubsidiaryIsoCode(List<String> subsidiaryIsoCode) {
        this.subsidiaryIsoCode = subsidiaryIsoCode;
    }

    public List<String> getSubsidiaryDirect() {
        return subsidiaryDirect;
    }

    public void setSubsidiaryDirect(List<String> subsidiaryDirect) {
        this.subsidiaryDirect = subsidiaryDirect;
    }

    public List<String> getSubsidiaryTotal() {
        return subsidiaryTotal;
    }

    public void setSubsidiaryTotal(List<String> subsidiaryTotal) {
        this.subsidiaryTotal = subsidiaryTotal;
    }

    public List<String> getSubsidiarySource() {
        return subsidiarySource;
    }

    public void setSubsidiarySource(List<String> subsidiarySource) {
        this.subsidiarySource = subsidiarySource;
    }

    public List<String> getSubsidiaryDate() {
        return subsidiaryDate;
    }

    public void setSubsidiaryDate(List<String> subsidiaryDate) {
        this.subsidiaryDate = subsidiaryDate;
    }

    public List<String> getGlobalParentSalutation() {
        return globalParentSalutation;
    }

    public void setGlobalParentSalutation(List<String> globalParentSalutation) {
        this.globalParentSalutation = globalParentSalutation;
    }

    public List<String> getGlobalParentFirstName() {
        return globalParentFirstName;
    }

    public void setGlobalParentFirstName(List<String> globalParentFirstName) {
        this.globalParentFirstName = globalParentFirstName;
    }

    public List<String> getGlobalParentLastName() {
        return globalParentLastName;
    }

    public void setGlobalParentLastName(List<String> globalParentLastName) {
        this.globalParentLastName = globalParentLastName;
    }

    public List<String> getGlobalParentTicker() {
        return globalParentTicker;
    }

    public void setGlobalParentTicker(List<String> globalParentTicker) {
        this.globalParentTicker = globalParentTicker;
    }

    public List<String> getGlobalUltimateParentType() {
        return globalUltimateParentType;
    }

    public void setGlobalUltimateParentType(List<String> globalUltimateParentType) {
        this.globalUltimateParentType = globalUltimateParentType;
    }

    public List<String> getGlobalParentCity() {
        return globalParentCity;
    }

    public void setGlobalParentCity(List<String> globalParentCity) {
        this.globalParentCity = globalParentCity;
    }

    public List<String> getControllingShareholderCompanyName() {
        return controllingShareholderCompanyName;
    }

    public void setControllingShareholderCompanyName(List<String> controllingShareholderCompanyName) {
        this.controllingShareholderCompanyName = controllingShareholderCompanyName;
    }

    public List<String> getControllingShareholderSalutation() {
        return controllingShareholderSalutation;
    }

    public void setControllingShareholderSalutation(List<String> controllingShareholderSalutation) {
        this.controllingShareholderSalutation = controllingShareholderSalutation;
    }

    public List<String> getControllingShareholderFirstName() {
        return controllingShareholderFirstName;
    }

    public void setControllingShareholderFirstName(List<String> controllingShareholderFirstName) {
        this.controllingShareholderFirstName = controllingShareholderFirstName;
    }

    public List<String> getControllingShareholderLastName() {
        return controllingShareholderLastName;
    }

    public void setControllingShareholderLastName(List<String> controllingShareholderLastName) {
        this.controllingShareholderLastName = controllingShareholderLastName;
    }

    public List<String> getControllingShareholderBVDID() {
        return controllingShareholderBVDID;
    }

    public void setControllingShareholderBVDID(List<String> controllingShareholderBVDID) {
        this.controllingShareholderBVDID = controllingShareholderBVDID;
    }

    public List<String> getControllingShareholderTicker() {
        return controllingShareholderTicker;
    }

    public void setControllingShareholderTicker(List<String> controllingShareholderTicker) {
        this.controllingShareholderTicker = controllingShareholderTicker;
    }

    public List<String> getControllingShareholderISO() {
        return controllingShareholderISO;
    }

    public void setControllingShareholderISO(List<String> controllingShareholderISO) {
        this.controllingShareholderISO = controllingShareholderISO;
    }

    public List<String> getControllingShareholderCity() {
        return controllingShareholderCity;
    }

    public void setControllingShareholderCity(List<String> controllingShareholderCity) {
        this.controllingShareholderCity = controllingShareholderCity;
    }

    public List<String> getControllingShareholderType() {
        return controllingShareholderType;
    }

    public void setControllingShareholderType(List<String> controllingShareholderType) {
        this.controllingShareholderType = controllingShareholderType;
    }

    public List<String> getControllingShareholderLevel() {
        return controllingShareholderLevel;
    }

    public void setControllingShareholderLevel(List<String> controllingShareholderLevel) {
        this.controllingShareholderLevel = controllingShareholderLevel;
    }

    public String getSalutationOfGlobalParent() {
        return salutationOfGlobalParent;
    }

    public void setSalutationOfGlobalParent(String salutationOfGlobalParent) {
        this.salutationOfGlobalParent = salutationOfGlobalParent;
    }

    public String getFirstNameOfGlobalParent() {
        return firstNameOfGlobalParent;
    }

    public void setFirstNameOfGlobalParent(String firstNameOfGlobalParent) {
        this.firstNameOfGlobalParent = firstNameOfGlobalParent;
    }

    public String getLastNameOfGlobalParent() {
        return lastNameOfGlobalParent;
    }

    public void setLastNameOfGlobalParent(String lastNameOfGlobalParent) {
        this.lastNameOfGlobalParent = lastNameOfGlobalParent;
    }

    public String getSalutationOfControllingShareholder() {
        return salutationOfControllingShareholder;
    }

    public void setSalutationOfControllingShareholder(String salutationOfControllingShareholder) {
        this.salutationOfControllingShareholder = salutationOfControllingShareholder;
    }

    public String getFirstNameOfControllingShareholder() {
        return firstNameOfControllingShareholder;
    }

    public void setFirstNameOfControllingShareholder(String firstNameOfControllingShareholder) {
        this.firstNameOfControllingShareholder = firstNameOfControllingShareholder;
    }

    public String getLastNameOfControllingShareholder() {
        return lastNameOfControllingShareholder;
    }

    public void setLastNameOfControllingShareholder(String lastNameOfControllingShareholder) {
        this.lastNameOfControllingShareholder = lastNameOfControllingShareholder;
    }

    public String getLevelOfControllingShareholder() {
        return levelOfControllingShareholder;
    }

    public void setLevelOfControllingShareholder(String levelOfControllingShareholder) {
        this.levelOfControllingShareholder = levelOfControllingShareholder;
    }
}
