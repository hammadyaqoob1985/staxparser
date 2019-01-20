package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlElement;
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
import java.util.List;

import static org.apache.logging.log4j.util.Strings.EMPTY;

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
        BvdCsvReportDataCollectorEntry bvdCsvReportDataCollectorEntry = parseXML(fileName);

        System.out.println(bvdCsvReportDataCollectorEntry.toString());

    }

    private BvdCsvReportDataCollectorEntry parseXML(String fileName) {
        BvdCsvReportDataCollectorEntry bvdCsvReportDataCollectorEntry = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            File file = new File(fileName);
            String str = new String(Files.readAllBytes(file.toPath()), "UTF-8");
            String[] split = str.split(" <GetDataResult>");
            String xml = split[1].split("</GetDataResult>")[0];
            InputStream targetStream = new ByteArrayInputStream(xml.getBytes());
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(targetStream);
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("record")) {
                        bvdCsvReportDataCollectorEntry = new BvdCsvReportDataCollectorEntry();
                        //Get the 'id' attribute from Employee element
                        Attribute idAttr = startElement.getAttributeByName(new QName("selectionId"));
                        if (idAttr != null) {
                            bvdCsvReportDataCollectorEntry.setBvdId(idAttr.getValue());
                        }
                    } else if (startElement.getName().getLocalPart().equals("item")) {
                        //Get the 'id' attribute from Employee element
                        Attribute alias = startElement.getAttributeByName(new QName("alias"));
                        if (alias != null) {
                            String aliasValue = alias.getValue();

                            if (aliasValue.equals("companyName")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                bvdCsvReportDataCollectorEntry.setCompanyName(xmlEvent.asCharacters().getData());
                            } else if (aliasValue.equals("city")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                bvdCsvReportDataCollectorEntry.setCity(xmlEvent.asCharacters().getData());
                            } else if (aliasValue.equals("countryIso")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                bvdCsvReportDataCollectorEntry.setCountryIso(xmlEvent.asCharacters().getData());
                            } else if (aliasValue.equals("country")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                bvdCsvReportDataCollectorEntry.setCountry(xmlEvent.asCharacters().getData());
                            } else if (aliasValue.equals("operatingRevenueUSD")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                bvdCsvReportDataCollectorEntry.setOperatingRevenueUSD(xmlEvent.asCharacters().getData());
                            } else if (aliasValue.equals("tickerNo")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                bvdCsvReportDataCollectorEntry.setTickerNo(xmlEvent.asCharacters().getData());
                            } else if (aliasValue.equals("akaName")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getAkaName(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("regionInCountry")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                bvdCsvReportDataCollectorEntry.setRegionInCountry(xmlEvent.asCharacters().getData());
                            } else if (aliasValue.equals("standardisedLegalForm")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                bvdCsvReportDataCollectorEntry.setStandardisedLegalForm(xmlEvent.asCharacters().getData());
                            } else if (aliasValue.equals("usSicPrimary")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                bvdCsvReportDataCollectorEntry.setUsSicPrimary(xmlEvent.asCharacters().getData());
                            } else if (aliasValue.equals("usSicPrimaryIbidText")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                bvdCsvReportDataCollectorEntry.setUsSicPrimaryIbidText(xmlEvent.asCharacters().getData());
                            } else if (aliasValue.equals("usSicSecondary")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                bvdCsvReportDataCollectorEntry.setUsSicSecondary(xmlEvent.asCharacters().getData());
                            } else if (aliasValue.equals("usSicSecondaryIbidText")) {
                                xmlEvent = xmlEventReader.nextEvent();
                                bvdCsvReportDataCollectorEntry.setUsSicSecondaryIbidText(xmlEvent.asCharacters().getData());
                            } else if (aliasValue.equals("immediateParentCompanyName")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getImmediateParentCompanyName(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("immediateParentISO")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getImmediateParentISO(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("immediateParentBvDID")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getImmediateParentBvDID(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("domesticParentCompanyName")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getDomesticParentCompanyName(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("domesticParentCompanyName")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getDomesticParentCompanyName(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("domesticParentISO")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getDomesticParentISO(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("globalParentCompanyName")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getGlobalParentCompanyName(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("globalParentISO")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getGlobalParentISO(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("globalParentBvD")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getGlobalParentBvdId(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("shareholderCompanyName")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getShareholderCompanyName(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("shareholderCity")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getShareholderCity(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("shareholderIsoCode")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getShareholderIsoCode(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("shareholderTicker")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getShareholderTicker(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("shareholderBvdID")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getShareholderBvdID(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("shareholderType")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getShareholderType(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("shareholderDirect")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getShareholderDirect(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("shareholderTotal")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getShareholderTotal(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("source")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getSource(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("date")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getDate(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("subsidiaryName")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getSubsidiaryName(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("subsidiaryBvdId")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getSubsidiaryBVDID(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("subsidiaryStatus")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getSubsidiaryStatus(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("subsidiaryType")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getSubsidiaryType(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("subsidiaryLevel")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getSubsidiaryLevel(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("subsidiaryCity")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getSubsidiaryCity(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("subsidiaryTicker")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getSubsidiaryTicker(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("subsidiaryIsoCode")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getSubsidiaryIsoCode(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("subsidiaryDirect")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getSubsidiaryDirect(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("subsidiaryTotal")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getSubsidiaryTotal(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("subsidiarySource")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getSubsidiarySource(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("subsidiaryDate")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getSubsidiaryDate(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("globalParentSalutation")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getGlobalParentSalutation(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("globalParentFirstName")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getGlobalParentFirstName(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("globalParentLastName")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getGlobalParentLastName(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("globalParentTicker")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getGlobalParentTicker(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("globalUltimateParentType")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getGlobalUltimateParentType(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("globalParentCity")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getGlobalParentCity(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("controllingShareholderCompanyName")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getControllingShareholderCompanyName(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("controllingShareholderSalutation")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getControllingShareholderSalutation(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("controllingShareholderFirstName")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getControllingShareholderFirstName(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("controllingShareholderLastName")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getControllingShareholderLastName(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("controllingShareholderBVDID")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getControllingShareholderBVDID(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("controllingShareholderTicker")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getControllingShareholderTicker(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("controllingShareholderISO")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getControllingShareholderISO(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("controllingShareholderCity")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getControllingShareholderCity(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("controllingShareholderType")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getControllingShareholderType(), xmlEvent, xmlEventReader);
                            } else if (aliasValue.equals("controllingShareholderLevel")) {
                                parseArray(bvdCsvReportDataCollectorEntry.getControllingShareholderLevel(), xmlEvent, xmlEventReader);
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
        return bvdCsvReportDataCollectorEntry;
    }

    private boolean isEndElement(XMLEvent xmlEvent, String endTag) {
        EndElement endElement = xmlEvent.asEndElement();
        return endElement.getName().getLocalPart().equals(endTag);
    }

    private void parseArray(List<String> listToBePopulated, XMLEvent xmlEvent, XMLEventReader xmlEventReader) {
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



