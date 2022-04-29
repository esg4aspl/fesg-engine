package tr.edu.iyte.fesg.applications;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.esg.eventsequence.EventSequenceUtilities;
import tr.edu.iyte.esg.model.EventSimple;
import tr.edu.iyte.esg.model.Vertex;
import tr.edu.iyte.esg.model.VertexSimple;
import tr.edu.iyte.fesg.testsequencecomposition.FESGEventSequenceUtilities;

public class EventSequenceUtilitiesUsageApp {

	public static void main(String[] args) {

		EventSequence ESwithoutConnectionPoint = generateEventSequenceWithoutConnectionPoint();
		System.out.println("EventSequence: " + ESwithoutConnectionPoint.toString() + "\n");
		System.out.println("Divide Event Sequences without Connection Points as Proceeding");
		Set<EventSequence> ESset1 = EventSequenceUtilities.divideESsWithoutConnectionPointAsProceeding(ESwithoutConnectionPoint,"a");
		ESset1.forEach(ES->System.out.println(ES.toString() + "  "));
		System.out.println("Divide Event Sequences without Connection Points as Preceeding");
		Set<EventSequence> ESset2 = EventSequenceUtilities.divideESsWithoutConnectionPointAsPreceeding(ESwithoutConnectionPoint,"a");
		ESset2.forEach(ES->System.out.println(ES.toString() + "  "));
		
		EventSequence ESwithConnectionPoint = generateEventSequenceWithConnectionPoint();
		System.out.println("\nEventSequence: " + ESwithConnectionPoint.toString() + "\n");
		
		Set<EventSequence> ESset3 = FESGEventSequenceUtilities.divideEventSequencesWithConnectionPoints(ESwithConnectionPoint);
		System.out.println("Divide Event Sequences with Connection Points");
		ESset3.forEach(ES->System.out.println(ES.toString() + "  "));
	}
	
	public static EventSequence generateEventSequenceWithoutConnectionPoint() {
		EventSequence eventSequence = new EventSequence();
		Vertex v1 = new VertexSimple(0, new EventSimple(0, "u"));
		Vertex v2 = new VertexSimple(0, new EventSimple(0, "v"));
		Vertex v3 = new VertexSimple(0, new EventSimple(0, "a"));//2
		Vertex v4 = new VertexSimple(0, new EventSimple(0, "t"));
		Vertex v5 = new VertexSimple(0, new EventSimple(0, "a"));//4
		Vertex v6 = new VertexSimple(0, new EventSimple(0, "l"));
		Vertex v7 = new VertexSimple(0, new EventSimple(0, "m"));
		Vertex v8 = new VertexSimple(0, new EventSimple(0, "a"));//7
		Vertex v9 = new VertexSimple(0, new EventSimple(0, "a"));//8
		
		List<Vertex> vList = new LinkedList<Vertex>();
		vList.add(v1);
		vList.add(v2);
		vList.add(v3);
		vList.add(v4);
		vList.add(v5);
		vList.add(v6);
		vList.add(v7);
		vList.add(v8);
		vList.add(v9);
		
		eventSequence.setEventSequence(vList);
		
		return eventSequence;
	}
	
	public static EventSequence generateEventSequenceWithConnectionPoint() {
		
		EventSequence eventSequence = new EventSequence();
		Vertex v1 = new VertexSimple(0, new EventSimple(0, "(core,a)"));
		Vertex v2 = new VertexSimple(0, new EventSimple(0, "x"));
		Vertex v3 = new VertexSimple(0, new EventSimple(0, "(core,b)"));
		Vertex v4 = new VertexSimple(0, new EventSimple(0, "y"));
		Vertex v5 = new VertexSimple(0, new EventSimple(0, "(core,m)"));
		Vertex v6 = new VertexSimple(0, new EventSimple(0, "z"));
		
		
		List<Vertex> vList = new LinkedList<Vertex>();
		vList.add(v1);
		vList.add(v2);
		vList.add(v3);
		vList.add(v4);
		vList.add(v5);
		vList.add(v6);
		
		eventSequence.setEventSequence(vList);
		
		return eventSequence;
	}

}
