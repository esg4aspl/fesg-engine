package tr.edu.iyte.fesg.testsequencecomposition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.esg.eventsequence.EventSequenceUtilties;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.model.Edge;
import tr.edu.iyte.esg.model.EventSimple;
import tr.edu.iyte.esg.model.Vertex;
import tr.edu.iyte.esg.model.VertexSimple;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class TestSequenceCompositionUtilities {
	public static final String USEFULNESS_START = "[[";
	public static final String USEFULNESS_FINISH = "]]";
	public static final String VERTEXEVENTNAME_DELIMS = "(,)";
	
	public static String[] splitVertexEventName(String str) {
		String[] s = new String[2];
		StringTokenizer st = new StringTokenizer(str, VERTEXEVENTNAME_DELIMS);
		s[0] = st.nextToken();
		if(st.hasMoreTokens()) {
			s[1] = st.nextToken();
		}
		else {
			s[1] = s[0];
			s[0] = null;
		}
		return s;
	}
	
	public static Vertex flatten(Vertex v) {
		Vertex v_flat = v;
		String[] esg_ve = splitVertexEventName(v.getEvent().getName());
		if(esg_ve[0] != null) {
			v_flat = new VertexSimple(v.getID(), new EventSimple(v.getEvent().getID(), esg_ve[1])); //!!! Vertex clone needed. For now, VertexSimple is used.
		}
		return v_flat;
	}
	
	public static Vertex unflatten(Vertex v, String esgname) {	
		Vertex v_unflat = v;
		String[] esg_ve = splitVertexEventName(v.getEvent().getName());
		if(esg_ve[0] == null) {
			String eventname = VERTEXEVENTNAME_DELIMS.charAt(0) + esgname + VERTEXEVENTNAME_DELIMS.charAt(1) + v.getEvent().getName() + VERTEXEVENTNAME_DELIMS.charAt(2);
			v_unflat = new VertexSimple(v.getID(), new EventSimple(v.getEvent().getID(), eventname)); //!!! Vertex clone needed. For now, VertexSimple is used.
		}
		return v_unflat;
	}
	
	public static List<Vertex> flatten(List<Vertex> seq) {
		//seq.forEach(e-> System.out.print(e.toString() + " "));
		List<Vertex> seq_flat = new ArrayList<Vertex>(seq.size());
		Iterator<Vertex> seq_itr = seq.listIterator();
		while(seq_itr.hasNext()) {
			Vertex v = seq_itr.next();
			String[] esg_ve = splitVertexEventName(v.getEvent().getName());
			if(esg_ve[0] != null) {
				v = new VertexSimple(v.getID(), new EventSimple(v.getEvent().getID(), esg_ve[1])); //!!! Vertex clone needed. For now, VertexSimple is used.
			}
			seq_flat.add(v);
		}
		//System.out.print("\nFlatten: ");
		//seq_flat.forEach(e-> System.out.print(e.toString() + " "));
		return seq_flat;
		
	}
	
	public static EventSequence flatten(EventSequence seq) {
		EventSequence es_flat = new EventSequence();
		es_flat.setEventSequence(flatten(seq.getEventSequence()));
		return es_flat;
	}
	
	public static Object[] findSequenceAndIndexByEventName_FirstSequence_EndPoint(EventSequence seq, String vertexeventname, Set<EventSequence> sequences) {
		Object[] seq_index = null;
		Iterator<EventSequence> itr = sequences.iterator();
		while(itr.hasNext() && seq_index==null) {
			EventSequence ss = itr.next();
			if(EventSequenceUtilties.endsWith(ss, vertexeventname)) {
				seq_index = new Object[2];
				seq_index[0] = ss;
				seq_index[1] = ss.getEventSequence().size()-1;
			}
		}
		return seq_index;
	}
	
	public static Object[] findSequenceAndIndexByEventName_FirstSequence_FirstPoint(EventSequence seq, String vertexeventname, Set<EventSequence> sequences) {
		Object[] seq_index = null;
		Iterator<EventSequence> itr = sequences.iterator();
		while(itr.hasNext() && seq_index==null) {
			EventSequence ss = itr.next();
//System.out.println("CURRENT EventSequence " + ss);
//System.out.println("Vertex event name " + vertexeventname);
			int i = EventSequenceUtilties.getFirstIndexByEventName(ss, vertexeventname);
//System.out.println("i " + i);
			if(i != -1) {
				seq_index = new Object[2];
				seq_index[0] = ss;
				seq_index[1] = i;
			}
		}
		return seq_index;
	}
	
	public static Object[] findSequenceAndIndexByEventName_FirstSequence_LastPoint(EventSequence seq, String vertexeventname, Set<EventSequence> sequences) {
		Object[] seq_index = null;
		Iterator<EventSequence> itr = sequences.iterator();
		while(itr.hasNext() && seq_index==null) {
			EventSequence cs = itr.next();
			int i = EventSequenceUtilties.getLastIndexByEventName(cs, vertexeventname);
			if(i != -1) {
				seq_index = new Object[2];
				seq_index[0] = cs;
				seq_index[1] = i;
			}
		}
		return seq_index;
	}
	
	public static EventSequence connectToSucceedFlattened(EventSequence seq1, int i1, EventSequence seq2, int i2) {
		int last_i1 = seq1.getEventSequence().size()-1;
		List<Vertex> seq_new = new ArrayList<Vertex>((i2+1) + (last_i1-i1));
		int k = 0;
		Iterator<Vertex> seq2_itr = seq2.getEventSequence().listIterator();
		while(k <= i2) {
			seq_new.add(seq2_itr.next());
			k++;
		}
		k = i1+1;
		Iterator<Vertex> seq1_itr = seq1.getEventSequence().listIterator(i1+1);
		while(k <= last_i1) {
//			seq_new.add(seq1_itr.next());
			seq_new.add(TestSequenceCompositionUtilities.flatten(seq1_itr.next()));
			k++;
		}
		EventSequence es = new EventSequence();
		es.setEventSequence(seq_new);
		return es;
	}
	
	public static EventSequence connectToSucceed(EventSequence seq1, int i1, EventSequence seq2, int i2) {
//System.out.println("seq1: " + seq1 + "\ni1: " + i1);
//System.out.println("seq2: " + seq2 + "\ni2: " + i2);
		int last_i1 = seq1.getEventSequence().size()-1;
		List<Vertex> seq_new = new ArrayList<Vertex>((i2+1) + (last_i1-i1));

		int k = 0;
		Iterator<Vertex> seq2_itr = seq2.getEventSequence().listIterator();
		while(k <= i2) {
			seq_new.add(seq2_itr.next());
//System.out.println("seq2_itr.next() " + seq2_itr.next());
			k++;
			
		}
		k = i1+1;
		Iterator<Vertex> seq1_itr = seq1.getEventSequence().listIterator(i1+1);
		while(k <= last_i1) {
			seq_new.add(seq1_itr.next());
//System.out.println("seq1_itr.next() " + seq1_itr.next());
			k++;
		}
		EventSequence es = new EventSequence();
		es.setEventSequence(seq_new);
		return es;
	}
	
	public static boolean isConnectionPoint(String eventName) {

		return eventName.startsWith("(") && eventName.endsWith(")") && eventName.contains(",");
	}
	
	public static String[] getESGAndEventNameInConnectionPoint(String eventName) {
		int commaIndex = eventName.indexOf(",");
		String esg = eventName.substring(1, commaIndex);
		String event = eventName.substring(commaIndex + 1, eventName.length() - 1);
		String[] esgEvent = new String[2];
		esgEvent[0] = esg;
		esgEvent[1] = event;

		return esgEvent;
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

	public static List<Integer> indexesOfMiddleConnectionPoints(EventSequence eventSequence) {

		List<Integer> indexList = new ArrayList<Integer>();

		if (numberOfConnectionPointsInEventSequence(eventSequence) >= 1) {
			for (int i = 1; i < eventSequence.length() - 1; i++) {
				Vertex vertex = eventSequence.getEventSequence().get(i);
				if (vertex.getEvent().getName().startsWith("(") && vertex.getEvent().getName().endsWith(")")
						&& vertex.getEvent().getName().contains(",")) {
					indexList.add(i);
				}
			}
		}

		return indexList;
	}
	
	public static boolean hasConnectionPointAtStart(Set<EventSequence> eventSequenceSet) {
		boolean b = false;
		Iterator<EventSequence> currentEventSequenceSetIterator = eventSequenceSet.iterator();

		while (currentEventSequenceSetIterator.hasNext()) {
			EventSequence eventSequence = currentEventSequenceSetIterator.next();
			b = b || TestSequenceCompositionUtilities.isConnectionPoint(eventSequence.getStartVertex().getEvent().getName());
		}

		return b;
	}

	public static boolean hasConnectionPointAtEnd(Set<EventSequence> eventSequenceSet) {
		boolean b = false;
		Iterator<EventSequence> currentEventSequenceSetIterator = eventSequenceSet.iterator();

		while (currentEventSequenceSetIterator.hasNext()) {
			EventSequence eventSequence = currentEventSequenceSetIterator.next();
			b = b || TestSequenceCompositionUtilities.isConnectionPoint(eventSequence.getEndVertex().getEvent().getName());
		}

		return b;
	}

	public static boolean hasConnectionPointInMiddle(Set<EventSequence> eventSequenceSet) {
		boolean b = false;
		Iterator<EventSequence> currentEventSequenceSetIterator = eventSequenceSet.iterator();

		while (currentEventSequenceSetIterator.hasNext()) {
			EventSequence eventSequence = currentEventSequenceSetIterator.next();

			boolean b1 = false;
			for (int i = 1; i < eventSequence.length(); i++) {
				Vertex vertex = eventSequence.getEventSequence().get(i);
				b1 = b1 || TestSequenceCompositionUtilities.isConnectionPoint(vertex.getEvent().getName());
			}

			b = b || b1;
		}

		return b;
	}
	
	public static Set<String> getNextVertexEventNames(FeaturedESG featuredESG, String esgname, String vertexeventname) {
		Set<String> nextvertexeventnames = new LinkedHashSet<String>();
		for(ESG esg: featuredESG.getFeaturedESGSet()) {
			if(esg.getName().compareTo(esgname) == 0) {
				for(Edge e : esg.getEdgeList()) {
					if(e.getSource().getEvent().getName().compareTo(vertexeventname)==0 && 
							e.getTarget().getEvent().getName().compareTo(TestSequenceCompositionUtilities.USEFULNESS_START)!=0) {
						nextvertexeventnames.add(e.getTarget().getEvent().getName());
					}
				}
			} else {
				for(Edge e : esg.getEdgeList()) {
					String[] esg_ve_s = TestSequenceCompositionUtilities.splitVertexEventName(e.getSource().getEvent().getName());
					String[] esg_ve_t = TestSequenceCompositionUtilities.splitVertexEventName(e.getTarget().getEvent().getName());
					if(esg_ve_s[0]!=null && esg_ve_s[0].compareTo(esgname)==0 && 
							esg_ve_s[1].compareTo(vertexeventname)==0 && esg_ve_t[1].compareTo(TestSequenceCompositionUtilities.USEFULNESS_START)!=0) {
						nextvertexeventnames.add(esg_ve_t[1]);
					}
				}
			}
		}
		return nextvertexeventnames;
	}
	
	public static Set<String> getPreviousVertexEventNames(FeaturedESG featuredESG, String esgname, String vertexeventname) {
		Set<String> previousvertexeventnames = new LinkedHashSet<String>();
		for(ESG esg: featuredESG.getFeaturedESGSet()) {
			if(esg.getName().compareTo(esgname) == 0) {
				for(Edge e : esg.getEdgeList()) {
					if(e.getTarget().getEvent().getName().compareTo(vertexeventname)==0&& 
							e.getSource().getEvent().getName().compareTo(TestSequenceCompositionUtilities.USEFULNESS_FINISH)!=0) {
						previousvertexeventnames.add(e.getSource().getEvent().getName());
					}
				}
			} else {
				for(Edge e : esg.getEdgeList()) {
					//System.out.println("EDGE " + e.toString());
					String[] esg_ve_t = TestSequenceCompositionUtilities.splitVertexEventName(e.getTarget().getEvent().getName());
					String[] esg_ve_s = TestSequenceCompositionUtilities.splitVertexEventName(e.getSource().getEvent().getName());
					//System.out.println("Source "+esg_ve_s[0] + " " + esg_ve_s[1] );
					//System.out.println("Target "+esg_ve_t[0] + " " + esg_ve_t[1] );
					
					if(esg_ve_t[0]!=null && esg_ve_t[0].compareTo(esgname)==0 && 
							esg_ve_t[1].compareTo(vertexeventname)==0 && esg_ve_s[1].compareTo(TestSequenceCompositionUtilities.USEFULNESS_FINISH)!=0) {
						previousvertexeventnames.add(esg_ve_s[1]);
					}
				}
			}
		}
		return previousvertexeventnames;
	}


}
