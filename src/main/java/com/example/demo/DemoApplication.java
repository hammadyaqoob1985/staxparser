package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

@Component
class XmlParser implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        String fileName = "real.xml";

        List<BvdCsvReportDataCollectorEntry> bvdCsvReportDataCollectorEntries = new ArrayList<>();
        parseXML(fileName, bvdCsvReportDataCollectorEntries);

        System.out.println(bvdCsvReportDataCollectorEntries.stream().map(entry -> entry.getSubsidiaryBVDID())
                .flatMap(Collection::stream)
                .collect(Collectors.joining(";")));

    }

    private void parseXML(String fileName, List<BvdCsvReportDataCollectorEntry> bvdCsvReportDataCollectorEntries) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            File file = new File(fileName);
            String str = new String(Files.readAllBytes(file.toPath()), "UTF-8");
            String[] split = str.split(" <GetDataResult>");
            String xml = split[1].split("</GetDataResult>")[0];
            InputStream targetStream = new ByteArrayInputStream(xml.getBytes());
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(targetStream);
            List<BvdCsvReportDataCollectorEntry> bvdCsvReportDataCollectorExistingEntries = new ArrayList<>();
            String bvdId = EMPTY;
            String name = EMPTY;
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("record")) {
                        //Get the 'id' attribute from Employee element
                        Attribute idAttr = startElement.getAttributeByName(new QName("selectionId"));
                        if (idAttr != null) {
                            bvdId = idAttr.getValue();
                        }
                    } else if (startElement.getName().getLocalPart().equals("item")) {
                        //Get the 'id' attribute from Employee element
                        Attribute alias = startElement.getAttributeByName(new QName("alias"));
                        Attribute resultType = startElement.getAttributeByName(new QName("resultType"));
                        if ((alias != null) && (!"NotAvailable".equals(resultType.getValue()))) {
                            String aliasValue = alias.getValue();
                            if (aliasValue.equals("companyName")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                name = xmlEvent.asCharacters().getData();
                                bvdCsvReportDataCollectorExistingEntries = getExisting(bvdId, name, bvdCsvReportDataCollectorEntries);
                                if (CollectionUtils.isEmpty(bvdCsvReportDataCollectorExistingEntries)) {
                                    BvdCsvReportDataCollectorEntry bvdCsvReportDataCollectorEntry = new BvdCsvReportDataCollectorEntry();
                                    if(!StringUtils.isEmpty(name)) {
                                        bvdCsvReportDataCollectorEntry.setCompanyName(name);
                                    }
                                    if(!StringUtils.isEmpty(bvdId)) {
                                        bvdCsvReportDataCollectorEntry.setBvdId(bvdId);
                                    }
                                    bvdCsvReportDataCollectorExistingEntries = Collections.singletonList(bvdCsvReportDataCollectorEntry);
                                    bvdCsvReportDataCollectorEntries.addAll(bvdCsvReportDataCollectorExistingEntries);
                                }
                            } else if (aliasValue.equals("city")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                if(xmlEvent.isCharacters() && isNotEmpty(xmlEvent.asCharacters().getData())) {
                                    String value = xmlEvent.asCharacters().getData();
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setCity(value));
                                }
                            } else if (aliasValue.equals("countryIso")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                if(xmlEvent.isCharacters() && isNotEmpty(xmlEvent.asCharacters().getData())) {
                                    String value = xmlEvent.asCharacters().getData();
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setCountryIso(value));
                                }
                            } else if (aliasValue.equals("country")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                if(xmlEvent.isCharacters() && isNotEmpty(xmlEvent.asCharacters().getData())) {
                                    String value = xmlEvent.asCharacters().getData();
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setCountry(value));
                                }
                            } else if (aliasValue.equals("operatingRevenueUSD")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                if(xmlEvent.isCharacters() && isNotEmpty(xmlEvent.asCharacters().getData())) {
                                    String value = xmlEvent.asCharacters().getData();
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setOperatingRevenueUSD(value));
                                }
                            } else if (aliasValue.equals("tickerNo")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                if(xmlEvent.isCharacters() && isNotEmpty(xmlEvent.asCharacters().getData())) {
                                    String value = xmlEvent.asCharacters().getData();
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setTickerNo(value));
                                }
                            } else if (aliasValue.equals("akaName")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setAkaName(listReturned));
                                }
                            } else if (aliasValue.equals("regionInCountry")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                if(xmlEvent.isCharacters() && isNotEmpty(xmlEvent.asCharacters().getData())) {
                                    String value = xmlEvent.asCharacters().getData();
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setRegionInCountry(value));
                                }
                            } else if (aliasValue.equals("standardisedLegalForm")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                if(xmlEvent.isCharacters() && isNotEmpty(xmlEvent.asCharacters().getData())) {
                                    String value = xmlEvent.asCharacters().getData();
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setStandardisedLegalForm(value));
                                }
                            } else if (aliasValue.equals("usSicPrimary")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                if(xmlEvent.isCharacters() && isNotEmpty(xmlEvent.asCharacters().getData())) {
                                    String value = xmlEvent.asCharacters().getData();
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setUsSicPrimary(value));
                                }
                            } else if (aliasValue.equals("usSicPrimaryIbidText")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                if(xmlEvent.isCharacters() && isNotEmpty(xmlEvent.asCharacters().getData())) {
                                    String value = xmlEvent.asCharacters().getData();
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setUsSicPrimaryIbidText(value));
                                }
                            } else if (aliasValue.equals("usSicSecondary")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                if(xmlEvent.isCharacters() && isNotEmpty(xmlEvent.asCharacters().getData())) {
                                    String value = xmlEvent.asCharacters().getData();
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setUsSicSecondary(value));
                                }
                            } else if (aliasValue.equals("usSicSecondaryIbidText")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                if(xmlEvent.isCharacters() && isNotEmpty(xmlEvent.asCharacters().getData())) {
                                    String value = xmlEvent.asCharacters().getData();
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setUsSicSecondaryIbidText(value));
                                }
                            } else if (aliasValue.equals("immediateParentCompanyName")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setImmediateParentCompanyName(listReturned));
                                }
                            } else if (aliasValue.equals("immediateParentISO")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setImmediateParentISO(listReturned));
                                }
                            } else if (aliasValue.equals("immediateParentBvDID")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setImmediateParentBvDID(listReturned));
                                }
                            } else if (aliasValue.equals("domesticParentCompanyName")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setDomesticParentCompanyName(listReturned));
                                }
                            } else if (aliasValue.equals("domesticParentBVDID")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setDomesticParentBVDID(listReturned));
                                }
                            } else if (aliasValue.equals("domesticParentISO")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setDomesticParentISO(listReturned));
                                }
                            } else if (aliasValue.equals("globalParentCompanyName")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setGlobalParentCompanyName(listReturned));
                                }
                            } else if (aliasValue.equals("globalParentISO")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setGlobalParentISO(listReturned));
                                }
                            } else if (aliasValue.equals("globalParentBvD")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setGlobalParentBvdId(listReturned));
                                }
                            } else if (aliasValue.equals("shareholderCompanyName")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setShareholderCompanyName(listReturned));
                                }
                            } else if (aliasValue.equals("shareholderCity")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setShareholderCity(listReturned));
                                }
                            } else if (aliasValue.equals("shareholderIsoCode")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setShareholderIsoCode(listReturned));
                                }
                            } else if (aliasValue.equals("shareholderTicker")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setShareholderTicker(listReturned));
                                }
                            } else if (aliasValue.equals("shareholderBvdID")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setShareholderBvdID(listReturned));
                                }
                            } else if (aliasValue.equals("shareholderType")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setShareholderType(listReturned));
                                }
                            } else if (aliasValue.equals("shareholderDirect")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setShareholderDirect(listReturned));
                                }
                            } else if (aliasValue.equals("shareholderTotal")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setShareholderTotal(listReturned));
                                }
                            } else if (aliasValue.equals("source")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setSource(listReturned));
                                }
                            } else if (aliasValue.equals("date")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setDate(listReturned));
                                }
                            } else if (aliasValue.equals("subsidiaryName")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setSubsidiaryName(listReturned));
                                }
                            } else if (aliasValue.equals("subsidiaryBvdId")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setSubsidiaryBVDID(listReturned));
                                }
                            } else if (aliasValue.equals("subsidiaryStatus")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setSubsidiaryStatus(listReturned));
                                }
                            } else if (aliasValue.equals("subsidiaryType")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setSubsidiaryType(listReturned));
                                }
                            } else if (aliasValue.equals("subsidiaryLevel")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setSubsidiaryLevel(listReturned));
                                }
                            } else if (aliasValue.equals("subsidiaryCity")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setSubsidiaryCity(listReturned));
                                }
                            } else if (aliasValue.equals("subsidiaryTicker")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setSubsidiaryTicker(listReturned));
                                }
                            } else if (aliasValue.equals("subsidiaryIsoCode")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setSubsidiaryIsoCode(listReturned));
                                }
                            } else if (aliasValue.equals("subsidiaryDirect")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setSubsidiaryDirect(listReturned));
                                }
                            } else if (aliasValue.equals("subsidiaryTotal")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setSubsidiaryTotal(listReturned));
                                }
                            } else if (aliasValue.equals("subsidiarySource")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setSubsidiarySource(listReturned));
                                }
                            } else if (aliasValue.equals("subsidiaryDate")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setSubsidiaryDate(listReturned));
                                }
                            } else if (aliasValue.equals("globalParentSalutation")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setGlobalParentSalutation(listReturned));
                                }
                            } else if (aliasValue.equals("globalParentFirstName")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setGlobalParentFirstName(listReturned));
                                }
                            } else if (aliasValue.equals("globalParentLastName")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setAkaName(listReturned));
                                }
                            } else if (aliasValue.equals("globalParentTicker")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setGlobalParentLastName(listReturned));
                                }
                            } else if (aliasValue.equals("globalUltimateParentType")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setGlobalUltimateParentType(listReturned));
                                }
                            } else if (aliasValue.equals("globalParentCity")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setGlobalParentCity(listReturned));
                                }
                            } else if (aliasValue.equals("controllingShareholderCompanyName")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setControllingShareholderCompanyName(listReturned));
                                }
                            } else if (aliasValue.equals("controllingShareholderSalutation")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setControllingShareholderSalutation(listReturned));
                                }
                            } else if (aliasValue.equals("controllingShareholderFirstName")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setControllingShareholderFirstName(listReturned));
                                }
                            } else if (aliasValue.equals("controllingShareholderLastName")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setControllingShareholderLastName(listReturned));
                                }
                            } else if (aliasValue.equals("controllingShareholderBVDID")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setControllingShareholderBVDID(listReturned));
                                }
                            } else if (aliasValue.equals("controllingShareholderTicker")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setControllingShareholderTicker(listReturned));
                                }
                            } else if (aliasValue.equals("controllingShareholderISO")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setControllingShareholderISO(listReturned));
                                }
                            } else if (aliasValue.equals("controllingShareholderCity")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setControllingShareholderCity(listReturned));
                                }
                            } else if (aliasValue.equals("controllingShareholderType")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setControllingShareholderType(listReturned));
                                }
                            } else if (aliasValue.equals("controllingShareholderLevel")) {
                                List<String> listReturned = parseArray(xmlEvent, xmlEventReader);
                                if(!CollectionUtils.isEmpty(listReturned)) {
                                    bvdCsvReportDataCollectorExistingEntries.forEach(entry -> entry.setControllingShareholderLevel(listReturned));
                                }
                            } else {
                                xmlEventReader.nextEvent();
                            }
                        }
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<BvdCsvReportDataCollectorEntry> getExisting(String bvdId, String name, List<BvdCsvReportDataCollectorEntry> bvdCsvReportDataCollectorEntries) {
        return bvdCsvReportDataCollectorEntries.stream().filter(entry -> entry.getBvdId().equals(bvdId) || entry.getCompanyName().equals(name)).collect(Collectors.toList());
    }

    private boolean isEndElement(XMLEvent xmlEvent, String endTag) {
        EndElement endElement = xmlEvent.asEndElement();
        return endElement.getName().getLocalPart().equals(endTag);
    }

    private List<String> parseArray(XMLEvent xmlEvent, XMLEventReader xmlEventReader) {
        List<String> listToBePopulated = new ArrayList<>();
        try {
            if (xmlEvent.isStartElement()) {
                StartElement startElement = xmlEvent.asStartElement();
                if (startElement.getName().getLocalPart().equals("item")) {
                    Attribute resultType = startElement.getAttributeByName(new QName("resultType"));
                    if (resultType.getValue().equals("Repeat")) {
                        XMLEvent newxtEvent = (XMLEvent) xmlEventReader.next();
                        while (!(newxtEvent.isEndElement() && isEndElement(newxtEvent, "item"))) {
                            getvalueFromChildItem(listToBePopulated, xmlEventReader, newxtEvent);
                            newxtEvent = (XMLEvent) xmlEventReader.next();
                        }

                    }

                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return listToBePopulated;
    }

    private void getvalueFromChildItem(List<String> listToBePopulated, XMLEventReader xmlEventReader, XMLEvent newxtEvent) throws XMLStreamException {
        if (newxtEvent.isStartElement()) {
            StartElement childXmlEventStartElement = newxtEvent.asStartElement();
            if (childXmlEventStartElement.getName().getLocalPart().equals("childItem")) {
                Attribute resultTypeOfChild = childXmlEventStartElement.getAttributeByName(new QName("resultType"));
                if (resultTypeOfChild.getValue().equals("String") || resultTypeOfChild.getValue().equals("DashDisplayed")) {
                    XMLEvent childItemValueEvent = xmlEventReader.nextEvent();
                    listToBePopulated.add(childItemValueEvent.asCharacters().getData());
                } else if (resultTypeOfChild.getValue().equals("NotAvailable")) {
                    xmlEventReader.nextEvent();
                    listToBePopulated.add(EMPTY);
                }
            }
        }
    }
}



