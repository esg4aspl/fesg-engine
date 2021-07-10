package tr.edu.iyte.fesg.testsequencecomposition;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import java.util.Map;
import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.esg.eventsequence.EventSequenceUtilties;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.model.Event;
import tr.edu.iyte.esg.model.EventSimple;
import tr.edu.iyte.esg.model.Vertex;
import tr.edu.iyte.esg.model.VertexSimple;

public class FESGEventSequenceUtilities {

	public static void esgTestSequenceMapPrinter(Map<String,Set<EventSequence>> esgTestSequenceMap, String header) {
		String str = "";
		
		for(String esgName:esgTestSequenceMap.keySet()) {
			str += esgName + "\n";
			
			for(EventSequence ES: esgTestSequenceMap.get(esgName)) {
				str += ES.toString() + "\n";
			}
			str += "\n";
		}
		System.out.println(header);
		System.out.println(str);
		System.out.println("----------------");
		System.out.println();

	}

	
	public static void esgListPrinter(List<ESG> esgList) {
		for(ESG esg : esgList) {
			System.out.println(esg.getName());
		}
	}


	public static Set<EventSequence> pickCESList(String startVertexName,
			String endVertexName, Set<EventSequence> eventSequenceSet) {
		Set<EventSequence> cesList = new LinkedHashSet<EventSequence>();
		int counter = 1;
		for (EventSequence es: eventSequenceSet) {
			int count = 0;
			System.out.println(counter + ": Start vertex " + es.getStartVertex());
			if (es.getStartVertex().getEvent().getName().equals(startVertexName)) count++;

			System.out.println(counter + ": End vertex " + es.getEndVertex());
			if (es.getEndVertex().getEvent().getName().equals(endVertexName)) count++;
			
			if (count == 2) {
				cesList.add(es);
				System.out.println("added to CES List");
			}
			counter++;
		}
		return cesList;
	}

	public static Set<EventSequence> divideEventSequencesWithConnectionPoints(EventSequence eventSequence) {

		Set<EventSequence> esSet = new LinkedHashSet<EventSequence>();

		List<Integer> indexList = indexesOfConnectionPoints(eventSequence);

		for (int i = 0; i < indexList.size(); i++) {
			int index1 = indexList.get(i);

			Iterator<Integer> indexListIterator = indexList.listIterator(i + 1);

			while (indexListIterator.hasNext()) {
				int index2 = indexListIterator.next();
				List<Vertex> newES = eventSequence.getEventSequence().subList(index1, index2 + 1);
				EventSequence newEventSequence = new EventSequence();
				newEventSequence.setEventSequence(newES);
				modifyConnectionPointsInEventSequence(newEventSequence);
				esSet.add(newEventSequence);

			}
		}

		return esSet;
	}

	public static boolean isEventSequenceWithConnectionPoint(EventSequence eventSequence) {

		Iterator<Vertex> eventSequenceIterator = eventSequence.getEventSequence().iterator();

		while (eventSequenceIterator.hasNext()) {
			Vertex vertex = eventSequenceIterator.next();
			if (vertex.getEvent().getName().startsWith("(") && vertex.getEvent().getName().endsWith(")")
					&& vertex.getEvent().getName().contains(",")) {
				return true;
			}
		}

		return false;
	}

	public static int numberOfConnectionPointsInEventSequence(EventSequence eventSequence) {
		int numberOfConnectionPoints = 0;

		if (isEventSequenceWithConnectionPoint(eventSequence)) {
			Iterator<Vertex> eventSequenceIterator = eventSequence.getEventSequence().iterator();

			while (eventSequenceIterator.hasNext()) {
				Vertex vertex = eventSequenceIterator.next();

				if (vertex.getEvent().getName().startsWith("(") && vertex.getEvent().getName().endsWith(")")
						&& vertex.getEvent().getName().contains(",")) {
					numberOfConnectionPoints++;
				}
			}
		}

		return numberOfConnectionPoints;
	}

	public static List<Integer> indexesOfConnectionPoints(EventSequence eventSequence) {

		List<Integer> indexList = new ArrayList<Integer>();

		if (numberOfConnectionPointsInEventSequence(eventSequence) > 1) {
			for (int i = 0; i < eventSequence.length(); i++) {
				Vertex vertex = eventSequence.getEventSequence().get(i);
				if (vertex.getEvent().getName().startsWith("(") && vertex.getEvent().getName().endsWith(")")
						&& vertex.getEvent().getName().contains(",")) {
					indexList.add(i);
				}
			}
		}

		return indexList;
	}

	public static void modifyConnectionPointsInEventSequence(EventSequence eventSequence) {

		List<Vertex> vertexList = new LinkedList<>();
		vertexList.add(eventSequence.getEventSequence().get(0));

		for (int i = 1; i < eventSequence.length() - 1; i++) {
			Vertex vertex = eventSequence.getEventSequence().get(i);
			if (vertex.getEvent().getName().startsWith("(") && vertex.getEvent().getName().endsWith(")")) {
				int commaIndex = vertex.getEvent().getName().indexOf(",");
				String eventName = vertex.getEvent().getName().substring(commaIndex + 1,
						vertex.getEvent().getName().length() - 1);
				Event event = new EventSimple(vertex.getEvent().getID(), eventName);
				Vertex newVertex = new VertexSimple(vertex.getID(), event);

				vertexList.add(newVertex);
			} else {
				vertexList.add(vertex);
			}

		}
		vertexList.add(eventSequence.getEventSequence().get(eventSequence.length() - 1));
		eventSequence.setEventSequence(vertexList);
	}

	public static Set<EventSequence> divideESsWithoutConnectionPointAsPreceeding(EventSequence eventSequence,
			String eventName) {

		Set<EventSequence> esSet = new LinkedHashSet<EventSequence>();

		List<Integer> indexList = EventSequenceUtilties.indexesOfRepeatedEvent(eventSequence, eventName);

		for (int i = 0; i < indexList.size(); i++) {
			int index1 = indexList.get(i);

			List<Vertex> newES = eventSequence.getEventSequence().subList(0, index1 + 1);
			EventSequence newEventSequence = new EventSequence();
			newEventSequence.setEventSequence(newES);
			esSet.add(newEventSequence);

		}

		return esSet;
	}
	
	public static Set<EventSequence> divideESsWithoutConnectionPointAsProceeding(EventSequence eventSequence,
			String eventName) {

		Set<EventSequence> esSet = new LinkedHashSet<EventSequence>();

		List<Integer> indexList = EventSequenceUtilties.indexesOfRepeatedEvent(eventSequence, eventName);
		//System.out.println("eventSequence.length() " + eventSequence.length());
		for (int i = 0; i < indexList.size(); i++) {
			int index = indexList.get(i);
			//System.out.println("index " + index);
			List<Vertex> newES = null;
			if((index == (eventSequence.length()-1))) {
				Vertex vertex  = eventSequence.getEventSequence().get(index);
				newES = new LinkedList<>();
				newES.add(vertex);
			}else{
				newES = eventSequence.getEventSequence().subList(index, eventSequence.length());
			}
			EventSequence newEventSequence = new EventSequence();
			newEventSequence.setEventSequence(newES);
			esSet.add(newEventSequence);

		}

		return esSet;
	}

}
