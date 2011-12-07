/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sam
 */
import java.util.*;
import java.io.*;
//import java.text.DateFormat;
import javax.xml.parsers.*;
import javax.xml.stream.*;
import org.w3c.dom.*;
import java.text.*;
import javax.xml.stream.events.*;

public class Highscores {
    private String fileName;
    private ArrayList<HighScoreElement> scores;
    
    Highscores(String fn) {
        this.fileName = fn;
        scores = new ArrayList<HighScoreElement>();
        this.populateHighscores();
    }
    
    public String viewHighscores() {
        String tmp = "";
        if(this.populateHighscores()) {
            ArrayList<HighScoreElement> sortedScores = this.newInstance();
            
            Collections.sort(sortedScores);
            for(HighScoreElement hse: sortedScores) {
                tmp += ""+hse+"\n";
            }
        }
        
        return tmp;
    }
    
    public int indexOfLowest() {
        ArrayList<HighScoreElement> sortedScores = this.newInstance();   
        Collections.sort(sortedScores);
        int score = sortedScores.get(sortedScores.size() -1 ).getScore();
        return findIndex(score);
    }
    
    public int getScore(int index) {
        return scores.get(index).getScore();
    }
    
    public int getLength() {
        return scores.size();
    }
    
    public ArrayList<HighScoreElement> newInstance() {
        return (ArrayList<HighScoreElement>) this.scores.clone();
    }
    
    private boolean populateHighscores() {
        scores.clear();
       try {
            File file = new File(fileName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            
            NodeList nodeLst = doc.getElementsByTagName("scoreList");
            
            for (int i = 0; i < nodeLst.getLength(); i++) {
                Node fstNode = nodeLst.item(i);
                
                if(fstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element fstElement = (Element) fstNode;
                    NodeList nameLst = fstElement.getElementsByTagName("name");
                    NodeList scoreLst = fstElement.getElementsByTagName("score");
                    NodeList dateLst = fstElement.getElementsByTagName("date");

                    String name = "", score = "";
                    Date date;
                    
                    try {
                        DateFormat fmt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        date = fmt.parse(dateLst.item(0).getTextContent());
                        name = nameLst.item(0).getTextContent();
                        score = scoreLst.item(0).getTextContent();
                        this.scores.add(new HighScoreElement(name,Integer.parseInt(score), date));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public void writeToFile() {
        try {
            XMLOutputFactory output = XMLOutputFactory.newFactory();
            XMLEventWriter eventWriter = output.createXMLEventWriter(new FileOutputStream(this.fileName));
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            XMLEvent tab = eventFactory.createDTD("\t");
            
            StartDocument startDoc = eventFactory.createStartDocument();
            eventWriter.add(startDoc);
            
            
            StartElement startScores = eventFactory.createStartElement("", "", "scores");
            eventWriter.add(startScores);
            eventWriter.add(end);
            
            for(HighScoreElement hse: this.scores) {
                StartElement startScore = eventFactory.createStartElement("", "", "scoreList");
                //
                eventWriter.add(tab);
                eventWriter.add(startScore);
                eventWriter.add(end);
                eventWriter.add(tab);
                this.createElement(eventWriter, "name", hse.getName());
                eventWriter.add(tab);
                this.createElement(eventWriter, "score", ""+hse.getScore());
                eventWriter.add(tab);
                DateFormat fmt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String dString = fmt.format(hse.getDate());
                this.createElement(eventWriter, "date", dString);
                eventWriter.add(tab);
                EndElement endScore = eventFactory.createEndElement("", "", "scoreList");
                eventWriter.add(endScore);
                eventWriter.add(end);
            }
            
            EndElement endScores = eventFactory.createEndElement("", ",", "scores");
            eventWriter.add(endScores);
            eventWriter.add(end);
            
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
            
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
    
    private void createElement(XMLEventWriter eventWriter, String key, String value) throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        
        StartElement startElement = eventFactory.createStartElement("", "", key);
        eventWriter.add(tab);
        eventWriter.add(startElement);
        
        Characters chars = eventFactory.createCharacters(value);
        eventWriter.add(chars);
        
        EndElement endElement = eventFactory.createEndElement("","",key);
        eventWriter.add(endElement);
        eventWriter.add(end);
    }
    
    public void overwriteScore(int index,String name, int newScore) {
        HighScoreElement newNode = new HighScoreElement(name, newScore, new Date() );
        this.scores.set(index, newNode);
    }
    
    private int findIndex(int score) {
        int index = -1;
        int foundIndex = -1;
        for(HighScoreElement hse: this.scores) {
            index++;
            if(hse.getScore() == score) {
                if(foundIndex == -1) {
                    foundIndex = index;
                } else {
                    if(this.scores.get(index).getDate().compareTo(this.scores.get(foundIndex).getDate()) == 1) {
                        foundIndex = index;
                    }
                }
            }
        }
        return foundIndex;
    }
}
