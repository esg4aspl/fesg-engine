package tr.edu.iyte.fesg.featuredesgtransformation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.model.Edge;
import tr.edu.iyte.esg.model.EdgeSimple;
import tr.edu.iyte.esg.model.Event;
import tr.edu.iyte.esg.model.EventSimple;
import tr.edu.iyte.esg.model.Vertex;
import tr.edu.iyte.esg.model.VertexRefinedBySequence;
import tr.edu.iyte.esg.model.VertexSimple;
import tr.edu.iyte.esg.model.comparators.SequenceComparator;
import tr.edu.iyte.esg.model.sequenceesg.ESGtoSequenceESGConverter;
import tr.edu.iyte.esg.model.sequenceesg.Sequence;
import tr.edu.iyte.esg.model.sequenceesg.SequenceESGUtilities;
import tr.edu.iyte.esg.model.sequenceesg.VertexSequenceUtilities;
import tr.edu.iyte.fesg.model.comparators.VertexComparatorFlattenedEventNameBased;
import tr.edu.iyte.fesg.testsequencecomposition.TestSequenceCompositionUtilities;

public class FeaturedESGTransformer {
	private Map<String,ESG> kESG_nameESGMap;
	private Map<String,ESG> oneESG_nameESGMap;
	
	private Map<String,Set<Edge>> esgname_usefulnessstartedges_map;
	private Map<String,Set<Edge>> esgname_usefulnessfinishedges_map;
	private Map<String,Vertex> esgname_usefulnessstartvertex_map;
	private Map<String,Vertex> esgname_usefulnessfinishvertex_map;
	private Map<String,Vertex> esgname_pseudostartvertex_map;
	private Map<String,Vertex> esgname_pseudofinishvertex_map;
	
	public FeaturedESGTransformer(List<ESG> esgList) {
		ESGtoSequenceESGConverter esgtoSequenceESGConverter = new ESGtoSequenceESGConverter();
		this.oneESG_nameESGMap = new LinkedHashMap<String,ESG>();
		for(ESG esg : esgList) {
			ESG oneESG = esgtoSequenceESGConverter.convert(esg);
			this.oneESG_nameESGMap.put(esg.getName(), oneESG);
		}
		this.kESG_nameESGMap = this.oneESG_nameESGMap;
	}
	
	public FeaturedESGTransformer(Map<String,ESG> esg_nameESGMap) {
		ESGtoSequenceESGConverter esgtoSequenceESGConverter = new ESGtoSequenceESGConverter();
		this.oneESG_nameESGMap = new LinkedHashMap<String,ESG>();
		Iterator<String> kitr = esg_nameESGMap.keySet().iterator();
		while(kitr.hasNext()) {
			String key = kitr.next();
			ESG esg = esg_nameESGMap.get(key);
			ESG oneESG = esgtoSequenceESGConverter.convert(esg);
			this.oneESG_nameESGMap.put(key, oneESG);
		}
		this.kESG_nameESGMap = this.oneESG_nameESGMap;
	}
	
	public FeaturedESGTransformer(Map<String,ESG> kESG_nameESGMap, Map<String,ESG> oneESG_nameESGMap) {
		this.kESG_nameESGMap = kESG_nameESGMap;
		this.oneESG_nameESGMap = oneESG_nameESGMap;
	}
	
	public Map<String,ESG> getkESGMap() {
		return this.kESG_nameESGMap;
	}
	
	public Map<String,ESG> getoneESGMap() {
		return this.oneESG_nameESGMap;
	}
	
	public List<String> getESGNameList() {
		return new LinkedList<String>(this.oneESG_nameESGMap.keySet());
	}
	
	public List<ESG> getkESGList() {
		return new LinkedList<ESG>(this.kESG_nameESGMap.values());
	}
	
	public List<ESG> getoneESGList() {
		return new LinkedList<ESG>(this.oneESG_nameESGMap.values());
	}
	
	public void transform(int n) {
		List<String> esgnameList = new ArrayList<String>(this.oneESG_nameESGMap.keySet());
		List<ESG> oneesgList = new ArrayList<ESG>(this.oneESG_nameESGMap.values());
		for(int k=1; k<=n; k++) {
			Map<String,ESG> new_kESG_nameESGMap = new LinkedHashMap<String,ESG>();
			this.esgname_usefulnessstartedges_map = new LinkedHashMap<String,Set<Edge>>();
			this.esgname_usefulnessfinishedges_map = new LinkedHashMap<String,Set<Edge>>();
			this.esgname_usefulnessstartvertex_map = new LinkedHashMap<String,Vertex>();
			this.esgname_usefulnessfinishvertex_map = new LinkedHashMap<String,Vertex>();
			this.esgname_pseudostartvertex_map = new LinkedHashMap<String,Vertex>();
			this.esgname_pseudofinishvertex_map = new LinkedHashMap<String,Vertex>();
			List<ESG> kesgList = new ArrayList<ESG>(this.kESG_nameESGMap.values());
			for(int i=0; i<kesgList.size(); i++) {
				ESG kESG = kesgList.get(i);
				String kESGName = esgnameList.get(i);
//ESGToDOTFileConverter.buildDOTFileFromESG(kESG, "files/MXEFiles/BankAccountPL/k_" + kESG.getName() + ".dot");
				ESG oneESG = oneesgList.get(i);
				String oneESGName = esgnameList.get(i);
//ESGToDOTFileConverter.buildDOTFileFromESG(kESG, "files/MXEFiles/BankAccountPL/one_" + oneESG.getName() + ".dot");
				ESG kp1ESG = transformIncludingShorterSequences(kESG, oneESG, kESGName);
//ESGToDOTFileConverter.buildDOTFileFromESG(kESG, "files/MXEFiles/BankAccountPL/kp1_" + kp1ESG.getName() + ".dot");
//if(kESGName.compareTo("deposit")==0) {
//System.out.println(" - "+kESG.getName());
//SequenceESGUtilities.printEdges(kESG);	
//System.out.println(" - "+oneESG.getName());
//SequenceESGUtilities.printEdges(oneESG);	
//System.out.println(" - "+kp1ESG.getName());
//SequenceESGUtilities.printEdges(kp1ESG);
//System.out.println();
////System.out.println("...maps...");
////System.out.println(this.esgname_usefulnessstartedges_map);
////System.out.println(this.esgname_usefulnessfinishedges_map);
////System.out.println(this.esgname_usefulnessstartvertex_map);
////System.out.println(this.esgname_usefulnessfinishvertex_map);
////System.out.println(this.esgname_pseudostartvertex_map);
////System.out.println(this.esgname_pseudofinishvertex_map);
////System.out.println("...");
//System.out.println("Press Enter key to continue...");
//try { System.in.read(); }
//catch(Exception e){}
//}
				for(int j=i-1; j>=0; j--) {
					oneESG = oneesgList.get(j);
					oneESGName = esgnameList.get(j);
					transformIncludingShorterSequencesUsingNoncorrespondingOneESG(kp1ESG, kESG, oneESG, kESGName, oneESGName);
//if(kESGName.compareTo("deposit")==0) {
//System.out.println(" - "+kESG.getName());
//SequenceESGUtilities.printEdges(kESG);	
//System.out.println(" - "+oneESG.getName());
//SequenceESGUtilities.printEdges(oneESG);	
//System.out.println(" - "+kp1ESG.getName());
//SequenceESGUtilities.printEdges(kp1ESG);
//System.out.println();
////System.out.println("...maps...");
////System.out.println(this.esgname_usefulnessstartedges_map);
////System.out.println(this.esgname_usefulnessfinishedges_map);
////System.out.println(this.esgname_usefulnessstartvertex_map);
////System.out.println(this.esgname_usefulnessfinishvertex_map);
////System.out.println(this.esgname_pseudostartvertex_map);
////System.out.println(this.esgname_pseudofinishvertex_map);
////System.out.println("...");
//System.out.println("Press Enter key to continue...");
//try { System.in.read(); }
//catch(Exception e){}
//}
				}
				new_kESG_nameESGMap.put(kESGName, kp1ESG);
			}
//System.out.println("...maps...");
//System.out.println(this.esgname_usefulnessstartedges_map);
//System.out.println(this.esgname_usefulnessfinishedges_map);
//System.out.println(this.esgname_pseudostartvertex_map);
//System.out.println(this.esgname_pseudofinishvertex_map);
//System.out.println("...");
			this.kESG_nameESGMap = new_kESG_nameESGMap;
//System.out.println("---after transformation:");
//for(ESG esg : this.kESG_nameESGMap.values()) {
//	System.out.println(esg.getName());
//	SequenceESGUtilities.printEdges(esg);
//}
//System.out.println("---");
		}
}
	
	private ESG transformIncludingShorterSequences(ESG kESG, ESG oneESG, String esgName) {
		ESG kp1ESG = new ESG(kESG.getID()+1, kESG.getName()+"t"); //!!! esg id and name
		for(Event e : oneESG.getEventList()) {
			kp1ESG.addEvent(e);
		}
		Comparator<Vertex> vc = new VertexComparatorFlattenedEventNameBased(); //!!! [ and (...,[) are equal, and ] and (...,]) are equal.
		Comparator<Sequence<Vertex>> comp = new SequenceComparator<Vertex>(vc);
		for(Edge qr : kESG.getEdgeList()) {
//System.out.println("*   "+qr);
			VertexRefinedBySequence q = (VertexRefinedBySequence)qr.getSource();
			VertexRefinedBySequence r = (VertexRefinedBySequence)qr.getTarget();
			if(!(r.isPseudoEndVertex() || isPseudoUsefulnessFinish(r) || isPseudoUsefulnessStart(r))) {
				Vertex rLast = r.getSequence().getElement(r.getSequence().getSize()-1);
				//System.out.println("rLast " + rLast.getEvent().getName());
				for(Edge ab : oneESG.getEdgeList()) {
//System.out.println("**  "+ab);
					VertexRefinedBySequence a = (VertexRefinedBySequence)ab.getSource();
					Vertex a1 = a.getSequence().getElement(0);
					//System.out.println("a1 " + a1.getEvent().getName());
					//if(vc.compare(rLast, a1)==0) {
					if(!a1.isPseudoStartVertex() && vc.compare(rLast, a1)==0) { //!!! [ and (...,[) are equal.
						VertexRefinedBySequence b = (VertexRefinedBySequence)ab.getTarget();
						Vertex b1 = b.getSequence().getElement(0);
						//System.out.println("b1 " + b1.getEvent().getName());
						//below part is simplified from the old transformation method.
						if(!((q.isPseudoStartVertex() || isPseudoUsefulnessStart(q)) && (b1.isPseudoEndVertex() || isPseudoUsefulnessFinish(b1)))) {
							Sequence<Vertex> s = new Sequence<Vertex>();
							Sequence<Vertex> t = new Sequence<Vertex>();
							s.addElements(q.getSequence()); //!!! existing vertex instances
							if(!(q.isPseudoStartVertex() || isPseudoUsefulnessStart(q))) {
								s.addElement(rLast); //!!! existing vertex instances
							}
							if(!(b1.isPseudoEndVertex() || isPseudoUsefulnessFinish(b1))) {
								t.addElements(r.getSequence()); //!!! existing vertex instances
							}
							t.addElement(b1); //!!! existing vertex instances
							VertexRefinedBySequence v = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, s, comp); //!!! look up to avoid using redundant instances (performance decrease)
							if(v == null) {
								Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(s));
								v = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, s);
								kp1ESG.addVertex(v);
							}
							VertexRefinedBySequence w = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, t, comp); //!!! look up to avoid using redundant instances (performance decrease)
							if(w == null) {
								Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(t));
								w = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, t);
								kp1ESG.addVertex(w);
							}
							//System.out.println("v "+v.getEvent().getName());
							//System.out.println("w "+w.getEvent().getName());
							Edge e = new EdgeSimple(kp1ESG.getNextEdgeID(), v, w);
//System.out.println("*** "+e);
							kp1ESG.addEdge(e);
							___update_maps(esgName, e);
						}
						else {
							//q is start and b1 is end.
							//if the only following vertex of rLast is the end vertex in the 1-ESG, 
							//then k-sequence r cannot be included in a longer sequence.
							//such sequences are discarded.
							//to include each of them, two edges must be added (start,r) and (r,end).
							if(getOutDegreeWithoutPseudoTargets(esgName, a1, vc)==0) { //!!! a1 can be (...,]); it cannot be [, ] or (...,[).
								Sequence<Vertex> s = new Sequence<Vertex>();
								Sequence<Vertex> t = new Sequence<Vertex>();
								Sequence<Vertex> u = new Sequence<Vertex>();
								s.addElements(q.getSequence());
								t.addElements(r.getSequence());
								u.addElements(b.getSequence());
								VertexRefinedBySequence v = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, s, comp); //!!! look up to avoid using redundant instances (performance decrease)
								if(v == null) {
									Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(s));
									v = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, s);
									kp1ESG.addVertex(v);
								}
								VertexRefinedBySequence w = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, t, comp); //!!! look up to avoid using redundant instances (performance decrease)
								if(w == null) {
									Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(t));
									w = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, t);
									kp1ESG.addVertex(w);
								}
								VertexRefinedBySequence x = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, u, comp); //!!! look up to avoid using redundant instances (performance decrease)
								if(x == null) {
									Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(u));
									x = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, u);
									kp1ESG.addVertex(x);
								}
								Edge e1 = new EdgeSimple(kp1ESG.getNextEdgeID(), v, w);
								Edge e2 = new EdgeSimple(kp1ESG.getNextEdgeID(), w, x);
//System.out.println("*** "+e1);
//System.out.println("*** "+e2);
								kp1ESG.addEdge(e1);
								kp1ESG.addEdge(e2);
								___update_maps(esgName, e1);
								___update_maps(esgName, e2);
							}
						}
					}
				}
			}
			else if((r.isPseudoEndVertex() && isPseudoUsefulnessFinish(q)) || (isPseudoUsefulnessStart(r) && q.isPseudoStartVertex())) {
				Sequence<Vertex> s = new Sequence<Vertex>();
				Sequence<Vertex> t = new Sequence<Vertex>();
				s.addElements(q.getSequence());
				t.addElements(r.getSequence());
				VertexRefinedBySequence v = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, s, comp); //!!! look up to avoid using redundant instances (performance decrease)
				if(v == null) {
					Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(s));
					v = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, s);
					kp1ESG.addVertex(v);
				}
				VertexRefinedBySequence w = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, t, comp); //!!! look up to avoid using redundant instances (performance decrease)
				if(w == null) {
					Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(t));
					w = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, t);
					kp1ESG.addVertex(w);
				}
				Edge e = new EdgeSimple(kp1ESG.getNextEdgeID(), v, w);
//System.out.println("*** "+e);
				kp1ESG.addEdge(e);
				___update_maps(esgName, e);
			}
//System.out.println();
		}
		return kp1ESG;
	}
	
	// This method is implemented in the following way to avoid from having possibly empty ESGs during transformation.
	// It relies on the fact that each ESG at index i is transformed using ESGs at indices <=i.
	// An ESG can be empty if it has only short sequences and they can be extended only by using ESGs at indices >i. 
	private int getOutDegreeWithoutPseudoTargets(String sesgname, Vertex v, Comparator<Vertex> comp) {
		int z = 0;
		Iterator<String> sesgnameitr = oneESG_nameESGMap.keySet().iterator();
		boolean notfound = true;
		while(sesgnameitr.hasNext() && notfound) {
			String s = sesgnameitr.next();
			ESG sesg = oneESG_nameESGMap.get(s);
			for(Edge e : sesg.getEdgeList()) {
				VertexRefinedBySequence vs1 = (VertexRefinedBySequence)e.getSource();
				VertexRefinedBySequence vs2 = (VertexRefinedBySequence)e.getTarget();
				if(comp.compare(vs1.getSequence().getElement(0), v)==0 && 
						comp.compare(vs2.getSequence().getElement(0), v)!=0 && //!!! loops are ignored on purpose.
						!vs2.isPseudoEndVertex() && !isPseudoUsefulnessFinish(vs2)) {
					z++;
				}
			}
			notfound = (s.compareTo(sesgname)!=0);
		}
		return z;
	}
	
	// This method is implemented in the following way to avoid from having possibly empty ESGs during transformation.
	// It relies on the fact that each ESG at index i is transformed using ESGs at indices <=i.
	// An ESG can be empty if it has only short sequences and they can be extended only by using ESGs at indices >i. 
	private int getInDegreeWithoutPseudoSources(String sesgname, Vertex v, Comparator<Vertex> comp) {
		int z = 0;
		Iterator<String> sesgnameitr = oneESG_nameESGMap.keySet().iterator();
		boolean notfound = true;
		while(sesgnameitr.hasNext() && notfound) {
			String s = sesgnameitr.next();
			ESG sesg = oneESG_nameESGMap.get(s);
			for(Edge e : sesg.getEdgeList()) {
				VertexRefinedBySequence vs1 = (VertexRefinedBySequence)e.getSource();
				VertexRefinedBySequence vs2 = (VertexRefinedBySequence)e.getTarget();
				if(comp.compare(vs2.getSequence().getElement(0), v)==0 && 
						comp.compare(vs1.getSequence().getElement(0), v)!=0 && //!!! loops are ignored on purpose.
						!vs1.isPseudoStartVertex() && !isPseudoUsefulnessStart(vs1)) {
					z++;
				}
			}
			notfound = (s.compareTo(sesgname)!=0);
		}
		return z;
	}
	
	private void ___update_maps(String esgName, Edge e) {
		if(isPseudoUsefulnessStart((VertexRefinedBySequence)e.getSource())) {
			Set<Edge> usefulnessstartedges = this.esgname_usefulnessstartedges_map.get(esgName);
			if(usefulnessstartedges==null) {
				usefulnessstartedges = new LinkedHashSet<Edge>();
				this.esgname_usefulnessstartedges_map.put(esgName, usefulnessstartedges);
			}
			usefulnessstartedges.add(e);
		}
		if(isPseudoUsefulnessFinish((VertexRefinedBySequence)e.getTarget())) {
			Set<Edge> usefulnessfinishedges = this.esgname_usefulnessfinishedges_map.get(esgName);
			if(usefulnessfinishedges==null) {
				usefulnessfinishedges = new LinkedHashSet<Edge>();
				this.esgname_usefulnessfinishedges_map.put(esgName, usefulnessfinishedges);
			}
			usefulnessfinishedges.add(e);
		}
		if(isPseudoUsefulnessStart((VertexRefinedBySequence)e.getSource())) {
			this.esgname_usefulnessstartvertex_map.put(esgName, e.getSource());
		}
		if(isPseudoUsefulnessStart((VertexRefinedBySequence)e.getTarget())) {
			this.esgname_usefulnessstartvertex_map.put(esgName, e.getTarget());
		}
		if(isPseudoUsefulnessFinish((VertexRefinedBySequence)e.getTarget())) {
			this.esgname_usefulnessfinishvertex_map.put(esgName, e.getTarget());
		}
		if(isPseudoUsefulnessFinish((VertexRefinedBySequence)e.getSource())) {
			this.esgname_usefulnessfinishvertex_map.put(esgName, e.getSource());
		}
		if(e.getSource().isPseudoStartVertex()) {
			this.esgname_pseudostartvertex_map.put(esgName, e.getSource());
		}
		if(e.getTarget().isPseudoEndVertex()) {
			this.esgname_pseudofinishvertex_map.put(esgName, e.getTarget());
		}
	}
	
	private void transformIncludingShorterSequencesUsingNoncorrespondingOneESG(ESG kp1ESG, ESG kESG, ESG oneESG, String kESGName, String oneESGName) {
		for(Event e : oneESG.getEventList()) {
			kp1ESG.addEvent(e);
		}
		Comparator<Vertex> vc = new VertexComparatorFlattenedEventNameBased(); //!!! [ and (...,[) are equal, and ] and (...,]) are equal.
		Comparator<Sequence<Vertex>> comp = new SequenceComparator<Vertex>(vc);
		for(Edge qr : kESG.getEdgeList()) {
			VertexRefinedBySequence q = (VertexRefinedBySequence)qr.getSource();
			VertexRefinedBySequence r = (VertexRefinedBySequence)qr.getTarget();
//			if(!(r.isPseudoEndVertex() || isPseudoUsefulnessFinish(r) || isPseudoUsefulnessStart(r))) {
			if(!(r.isPseudoEndVertex() || isPseudoUsefulnessStart(r))) {
				Vertex rLast = r.getSequence().getElement(r.getSequence().getSize()-1);
				Vertex qFirst = q.getSequence().getElement(0);
				//System.out.println("rLast " + rLast.getEvent().getName());
				for(Edge ab : oneESG.getEdgeList()) {
					VertexRefinedBySequence a = (VertexRefinedBySequence)ab.getSource();
					Vertex a1 = a.getSequence().getElement(0);
					VertexRefinedBySequence b = (VertexRefinedBySequence)ab.getTarget();
					Vertex b1 = b.getSequence().getElement(0);
					//System.out.println("a1 " + a1.getEvent().getName());
					//if(vc.compare(rLast, a1)==0) {
					if(!a1.isPseudoStartVertex() && vc.compare(rLast, a1)==0) { //!!! [ and (...,[) are equal.
						//System.out.println("b1 " + b1.getEvent().getName());
						//below part is simplified from the old transformation method.
						if(!((q.isPseudoStartVertex() || isPseudoUsefulnessStart(q)) && (b1.isPseudoEndVertex() || isPseudoUsefulnessFinish(b1)))) {
							if(!(b1.isPseudoEndVertex() || isPseudoUsefulnessFinish(b1))) {
								includeATailExtendedSequence(kp1ESG, q, r, rLast, b1, comp, kESGName, oneESGName);
							}
						}
						else {
							//q is start and b1 is end.
							//if the only following vertex of rLast is the end vertex in the 1-ESG, 
							//then k-sequence r cannot be included in a longer sequence.
							//such sequences are discarded.
							//to include each of them, two edges must be added (start,r) and (r,end).
							if(getOutDegreeWithoutPseudoTargets(kESGName, a1, vc)==0 && 
									getInDegreeWithoutPseudoSources(kESGName, r.getSequence().getElement(0), vc)==0) {
								includeATailExtendedShorterSequence(kp1ESG, q, r, b, comp, kESGName, oneESGName);
							}
						}
					}
					else if(vc.compare(b1, qFirst)==0) {
						if(!((r.isPseudoEndVertex() || isPseudoUsefulnessFinish(r)) && (a1.isPseudoStartVertex() || isPseudoUsefulnessStart(a1)))) {
							if(!(a1.isPseudoStartVertex() || isPseudoUsefulnessStart(a1))) {
								includeAHeadExtendedSequence(kp1ESG, a1, qFirst, q, r, comp, kESGName, oneESGName);
							}
						}
						else {
							//a1 is start and r is end.
							//if the only following vertex of qLast is the end vertex in the 1-ESG, 
							//then k-sequence q cannot be included in a longer sequence.
							//such sequences are discarded.
							//to include each of them, two edges must be added (start,q) and (q,end).
							if(getInDegreeWithoutPseudoSources(kESGName, r.getSequence().getElement(0), vc)==0 &&
									getOutDegreeWithoutPseudoTargets(kESGName, q.getSequence().getElement(q.getSequence().getSize()-1), vc)==0) { //!!! qLast can be (...,]); it cannot be [, ] or (...,[).
//System.out.println("     " + kESGName + "," +oneESGName);
//System.out.println("     a: " + a);
//System.out.println("     b: " + b);
//System.out.println("     q: " + q);
//System.out.println("     r: " + r);
//System.out.println("     qLast: " + q.getSequence().getElement(q.getSequence().getSize()-1));
								includeAHeadExtendedShorterSequence(kp1ESG, a, q, r, comp, kESGName, oneESGName);
							}
						}
					}
				}
			}
			else if((r.isPseudoEndVertex() && isPseudoUsefulnessFinish(q)) || (isPseudoUsefulnessStart(r) && q.isPseudoStartVertex())) {
				includeAPseudoSequence(kp1ESG, kESGName, q, r, comp);
			}
		}
	}

	private void includeATailExtendedSequence(ESG kp1ESG, 
			VertexRefinedBySequence q, VertexRefinedBySequence r, Vertex rLast, Vertex b1, 
			Comparator<Sequence<Vertex>> comp, 
			String kESGName, String oneESGName) {
		Sequence<Vertex> s = new Sequence<Vertex>();
		Sequence<Vertex> t = new Sequence<Vertex>();
//		Vertex rLast_flat = TestSequenceCompositionUtilities.flatten(rLast);
		s.addElements(q.getSequence()); //!!! existing vertex instances
		if(!(q.isPseudoStartVertex() || isPseudoUsefulnessStart(q))) {
			s.addElement(rLast); //!!! existing vertex instances
		}
		if(!(b1.isPseudoEndVertex() || isPseudoUsefulnessFinish(b1))) {
			t.addElements(r.getSequence(), r.getSequence().getSize()-1); //!!! existing vertex instances // t.addElements(r.getSequence()); //!!! existing vertex instances
			t.addElement(rLast);
			t.addElement(TestSequenceCompositionUtilities.unflatten(b1,oneESGName));
		}
		else {
			t.addElement(b1); //!!! existing vertex instances
		}
		VertexRefinedBySequence v = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, s, comp); //!!! look up to avoid using redundant instances (performance decrease)
		VertexRefinedBySequence w = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, t, comp); //!!! look up to avoid using redundant instances (performance decrease)
		boolean vnull = (v == null);
		boolean wnull = (w == null);
		if(vnull) {
			Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(s));
			v = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, s);
			kp1ESG.addVertex(v);
		}
		if(wnull) {
			Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(t));
			w = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, t);
			kp1ESG.addVertex(w);
		}
		//System.out.println("v "+v.getEvent().getName());
		//System.out.println("w "+w.getEvent().getName());
		if(!(vnull || wnull)) {
			Edge e = SequenceESGUtilities.getEdgeByVertexSequences(kp1ESG, v.getSequence(), w.getSequence(), comp);
			if(e == null) {
				e = new EdgeSimple(kp1ESG.getNextEdgeID(), v, w);
				kp1ESG.addEdge(e);
//				System.out.println("   "+kESGName+" tailextended-1 "+e+" - "+q+","+r+"  "+rLast+","+b1);
				___arrange_usefulnessfinishedges(kp1ESG, kESGName, e, comp);
			}
		}
		else {
			Edge e = new EdgeSimple(kp1ESG.getNextEdgeID(), v, w);
			kp1ESG.addEdge(e);
//			System.out.println("   "+kESGName+" tailextended-2 "+e+" - "+q+","+r+"  "+rLast+","+b1);
			___arrange_usefulnessfinishedges(kp1ESG, kESGName, e, comp);
		}
	}
	
	private void ___arrange_usefulnessfinishedges(ESG esg, String esgname, Edge xy, Comparator<Sequence<Vertex>> comp) {
		Set<Edge> usefulnessfinishedges = this.esgname_usefulnessfinishedges_map.get(esgname);
		if(usefulnessfinishedges != null && !usefulnessfinishedges.isEmpty()) {
			Iterator<Edge> itr = usefulnessfinishedges.iterator();
			boolean notremoved = true;
			Edge xu = null;
			while(itr.hasNext() && notremoved) {
				xu = itr.next();
				if(comp.compare(((VertexRefinedBySequence)xu.getSource()).getSequence(),
						((VertexRefinedBySequence)xy.getSource()).getSequence())==0) {
					esg.removeEdge(xu);
					notremoved = false;
					itr.remove();
				}
			}
			Edge yu = new EdgeSimple(esg.getNextEdgeID(), xy.getTarget(), xu.getTarget());
			esg.addEdge(yu);
//			System.out.println("   "+esgname+" usefulnessfinish-1 "+yu+"   "+xy);
			usefulnessfinishedges.add(yu);
		}
		else {
			Vertex f = this.esgname_pseudofinishvertex_map.get(esgname);
			Vertex u = this.esgname_usefulnessfinishvertex_map.get(esgname);
			if(u==null) {
				VertexSimple v = new VertexSimple(-1, new EventSimple(-1,TestSequenceCompositionUtilities.USEFULNESS_FINISH));
				Sequence<Vertex> s = new Sequence<Vertex>();
				s.addElement(v);
				Event e = new EventSimple(esg.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(s));
				u = new VertexRefinedBySequence(esg.getNextVertexID(), e, s);
				esg.addVertex(u);
				this.esgname_usefulnessfinishvertex_map.put(esgname, u);
			}
			Edge yu = new EdgeSimple(esg.getNextEdgeID(), xy.getTarget(), u);
			esg.addEdge(yu);
//			System.out.println("   "+esgname+" usefulnessfinish-2 "+yu+"   "+xy);
			Edge uf = SequenceESGUtilities.getEdgeByVertexSequences(esg, ((VertexRefinedBySequence)u).getSequence(), ((VertexRefinedBySequence)f).getSequence(), comp);
			if(uf==null) {
				uf = new EdgeSimple(esg.getNextEdgeID(), u, f);
				esg.addEdge(uf);
//				System.out.println("   "+esgname+" usefulnessfinish-3 "+uf);
			}
			usefulnessfinishedges = new LinkedHashSet<Edge>();
			usefulnessfinishedges.add(yu);
			this.esgname_usefulnessfinishedges_map.put(esgname, usefulnessfinishedges);
		}
	}

	private void includeAHeadExtendedSequence(ESG kp1ESG, 
			Vertex a1, Vertex qFirst, VertexRefinedBySequence q, VertexRefinedBySequence r, 
			Comparator<Sequence<Vertex>> comp, 
			String kESGName, String oneESGName) {
		Sequence<Vertex> s = new Sequence<Vertex>();
		Sequence<Vertex> t = new Sequence<Vertex>();
//		Vertex qFirst_flat = TestSequenceCompositionUtilities.flatten(qFirst);
		if(!(a1.isPseudoStartVertex() || isPseudoUsefulnessStart(a1))) {
			s.addElement(TestSequenceCompositionUtilities.unflatten(a1, oneESGName)); //s.addElement(a1); //!!! existing vertex instances
			s.addElement(qFirst);
			s.addElementsAfterSkip(q.getSequence(), q.getSequence().getSize()-1, 1); //!!! existing vertex instances
		}
		else {
			s.addElement(a1);
		}
		if(!(r.isPseudoEndVertex() || isPseudoUsefulnessFinish(r))) {
			t.addElement(qFirst); //!!! existing vertex instances
		}
		t.addElements(r.getSequence()); //!!! existing vertex instances
		VertexRefinedBySequence v = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, s, comp); //!!! look up to avoid using redundant instances (performance decrease)
		VertexRefinedBySequence w = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, t, comp); //!!! look up to avoid using redundant instances (performance decrease)
		boolean vnull = (v == null);
		boolean wnull = (w == null);
		if(vnull) {
			Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(s));
			v = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, s);
			kp1ESG.addVertex(v);
		}
		if(wnull) {
			Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(t));
			w = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, t);
			kp1ESG.addVertex(w);
		}
		//System.out.println("v "+v.getEvent().getName());
		//System.out.println("w "+w.getEvent().getName());
		if(!(vnull || wnull)) {
			Edge e = SequenceESGUtilities.getEdgeByVertexSequences(kp1ESG, v.getSequence(), w.getSequence(), comp);
			if(e == null) {
				e = new EdgeSimple(kp1ESG.getNextEdgeID(), v, w);
				kp1ESG.addEdge(e);
//				System.out.println("   "+kESGName+" headextended-1 "+e+" - "+a1+","+qFirst+"  "+q+","+r);
				___arrange_usefulnessstartedges(kp1ESG, kESGName, e, comp);
			}
		}
		else {
			Edge e = new EdgeSimple(kp1ESG.getNextEdgeID(), v, w);
			kp1ESG.addEdge(e);
//			System.out.println("   "+kESGName+" headextended-2 "+e+" - "+a1+","+qFirst+"  "+q+","+r);
			___arrange_usefulnessstartedges(kp1ESG, kESGName, e, comp);
		}
	}
	
	private void ___arrange_usefulnessstartedges(ESG esg, String esgname, Edge xy, Comparator<Sequence<Vertex>> comp) {
		Set<Edge> usefulnessstartedges = this.esgname_usefulnessstartedges_map.get(esgname);
		if(usefulnessstartedges != null && !usefulnessstartedges.isEmpty()) {
			Iterator<Edge> itr = usefulnessstartedges.iterator();
			boolean notremoved = true;
			Edge uy = null;
			while(itr.hasNext() && notremoved) {
				uy = itr.next();
				if(comp.compare(((VertexRefinedBySequence)uy.getTarget()).getSequence(),
						((VertexRefinedBySequence)xy.getTarget()).getSequence())==0) {
					esg.removeEdge(uy);
					notremoved = false;
					itr.remove();
				}
			}
			Edge ux = new EdgeSimple(esg.getNextEdgeID(), uy.getSource(), xy.getSource());
			esg.addEdge(ux);
//			System.out.println("   "+esgname+" usefulnessstart-1 "+ux+"   "+xy);
			usefulnessstartedges.add(ux);
		}
		else {
			Vertex s = this.esgname_pseudostartvertex_map.get(esgname);
			Vertex u = this.esgname_usefulnessstartvertex_map.get(esgname);
			if(u==null) {
				VertexSimple v = new VertexSimple(-1, new EventSimple(-1,TestSequenceCompositionUtilities.USEFULNESS_START));
				Sequence<Vertex> seq = new Sequence<Vertex>();
				seq.addElement(v);
				Event e = new EventSimple(esg.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(seq));
				u = new VertexRefinedBySequence(esg.getNextVertexID(), e, seq);
				esg.addVertex(u);
				this.esgname_usefulnessstartvertex_map.put(esgname, u);
			}
			Edge ux = new EdgeSimple(esg.getNextEdgeID(), u, xy.getSource());
			esg.addEdge(ux);
//			System.out.println("   "+esgname+" usefulnessstart-2 "+ux+"   "+xy);
			Edge su = SequenceESGUtilities.getEdgeByVertexSequences(esg, ((VertexRefinedBySequence)s).getSequence(), ((VertexRefinedBySequence)u).getSequence(), comp);
			if(su==null) {
				su = new EdgeSimple(esg.getNextEdgeID(), s, u);
				esg.addEdge(su);
//				System.out.println("   "+esgname+" usefulnessstart-3 "+su+"   ");
			}
			usefulnessstartedges = new LinkedHashSet<Edge>();
			usefulnessstartedges.add(ux);
			this.esgname_usefulnessstartedges_map.put(esgname, usefulnessstartedges);
		}
	}
	
	private void includeATailExtendedShorterSequence(ESG kp1ESG, 
			VertexRefinedBySequence q, VertexRefinedBySequence r, VertexRefinedBySequence b, 
			Comparator<Sequence<Vertex>> comp, 
			String kESGName, String oneESGName) {
		Sequence<Vertex> s = new Sequence<Vertex>();
		Sequence<Vertex> t = new Sequence<Vertex>();
		Sequence<Vertex> u = new Sequence<Vertex>();
		s.addElements(q.getSequence());
		t.addElements(r.getSequence());
		if(b.isPseudoEndVertex()) {
			VertexRefinedBySequence v = (VertexRefinedBySequence)this.esgname_usefulnessfinishvertex_map.get(kESGName);
			if(v!=null) {
				u.addElements(v.getSequence());
			}
			else {
				v = (VertexRefinedBySequence)this.esgname_pseudofinishvertex_map.get(kESGName);
				u.addElements(v.getSequence());
			}
		}
		else {
			u.addElements(b.getSequence());
		}
		VertexRefinedBySequence v = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, s, comp); //!!! look up to avoid using redundant instances (performance decrease)
		VertexRefinedBySequence w = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, t, comp); //!!! look up to avoid using redundant instances (performance decrease)
		VertexRefinedBySequence x = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, u, comp); //!!! look up to avoid using redundant instances (performance decrease)
		boolean vnull = (v == null);
		boolean wnull = (w == null);
		boolean xnull = (x == null);
		if(v == null) {
			Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(s));
			v = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, s);
			kp1ESG.addVertex(v);
		}
		if(w == null) {
			Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(t));
			w = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, t);
			kp1ESG.addVertex(w);
		}
		if(x == null) {
			Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(u));
			x = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, u);
			kp1ESG.addVertex(x);
		}
		if(!(vnull || wnull)) {
			Edge e = SequenceESGUtilities.getEdgeByVertexSequences(kp1ESG, v.getSequence(), w.getSequence(), comp);
			if(e == null) {
				e = new EdgeSimple(kp1ESG.getNextEdgeID(), v, w);
				kp1ESG.addEdge(e);
//				System.out.println("   "+kESGName+" tailshorter-1 "+e+" - "+q+","+r+","+b);
			}
		}
		else {
			Edge e = new EdgeSimple(kp1ESG.getNextEdgeID(), v, w);
			kp1ESG.addEdge(e);
//			System.out.println("   "+kESGName+" tailshorter-2 "+e+" - "+q+","+r+","+b);
		}
		if(!(wnull || xnull)) {
			Edge e = SequenceESGUtilities.getEdgeByVertexSequences(kp1ESG, w.getSequence(), x.getSequence(), comp);
			if(e == null) {
				e = new EdgeSimple(kp1ESG.getNextEdgeID(), w, x);
				kp1ESG.addEdge(e);
//				System.out.println("   "+kESGName+" tailshorter-3 "+e+" - "+q+","+r+","+b);
			}
		}
		else {
			Edge e = new EdgeSimple(kp1ESG.getNextEdgeID(), w, x);
			kp1ESG.addEdge(e);
//			System.out.println("   "+kESGName+" tailshorter-4 "+e+" - "+q+","+r+","+b);
		}
	}
	
	private void includeAHeadExtendedShorterSequence(ESG kp1ESG, 
			VertexRefinedBySequence a, VertexRefinedBySequence q, VertexRefinedBySequence r, 
			Comparator<Sequence<Vertex>> comp, 
			String kESGName, String oneESGName) {
		Sequence<Vertex> s = new Sequence<Vertex>();
		Sequence<Vertex> t = new Sequence<Vertex>();
		Sequence<Vertex> u = new Sequence<Vertex>();
		if(a.isPseudoStartVertex()) {
			VertexRefinedBySequence v = (VertexRefinedBySequence)this.esgname_usefulnessstartvertex_map.get(kESGName);
			if(v!=null) {
				s.addElements(v.getSequence());
			}
			else {
				v = (VertexRefinedBySequence)this.esgname_pseudostartvertex_map.get(kESGName);
				s.addElements(v.getSequence());
			}
		}
		else {
			s.addElements(a.getSequence());
		}
		t.addElements(q.getSequence());
		u.addElements(r.getSequence());
		VertexRefinedBySequence v = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, s, comp); //!!! look up to avoid using redundant instances (performance decrease)
		VertexRefinedBySequence w = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, t, comp); //!!! look up to avoid using redundant instances (performance decrease)
		VertexRefinedBySequence x = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, u, comp); //!!! look up to avoid using redundant instances (performance decrease)
		boolean vnull = (v == null);
		boolean wnull = (w == null);
		boolean xnull = (x == null);
		if(v == null) {
			Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(s));
			v = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, s);
			kp1ESG.addVertex(v);
		}
		if(w == null) {
			Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(t));
			w = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, t);
			kp1ESG.addVertex(w);
		}
		if(x == null) {
			Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(u));
			x = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, u);
			kp1ESG.addVertex(x);
		}
		if(!(vnull || wnull)) {
			Edge e = SequenceESGUtilities.getEdgeByVertexSequences(kp1ESG, v.getSequence(), w.getSequence(), comp);
			if(e == null) {
				e = new EdgeSimple(kp1ESG.getNextEdgeID(), v, w);
				kp1ESG.addEdge(e);
//				System.out.println("   "+kESGName+" headshorter-1 "+e+" - "+a+","+q+","+r);
			}
		}
		else {
			Edge e = new EdgeSimple(kp1ESG.getNextEdgeID(), v, w);
			kp1ESG.addEdge(e);
//			System.out.println("   "+kESGName+" headshorter-2 "+e+" - "+a+","+q+","+r);
		}
		if(!(wnull || xnull)) {
			Edge e = SequenceESGUtilities.getEdgeByVertexSequences(kp1ESG, w.getSequence(), x.getSequence(), comp);
			if(e == null) {
				e = new EdgeSimple(kp1ESG.getNextEdgeID(), w, x);
				kp1ESG.addEdge(e);
//				System.out.println("   "+kESGName+" headshorter-3 "+e+" - "+a+","+q+","+r);
			}
		}
		else {
			Edge e = new EdgeSimple(kp1ESG.getNextEdgeID(), w, x);
			kp1ESG.addEdge(e);
//			System.out.println("   "+kESGName+" headshorter-4 "+e+" - "+a+","+q+","+r);
		}
	}
	
	private void includeAPseudoSequence(ESG kp1ESG, String kESGName, 
			VertexRefinedBySequence q, VertexRefinedBySequence r, 
			Comparator<Sequence<Vertex>> comp) {
		Sequence<Vertex> s = new Sequence<Vertex>();
		Sequence<Vertex> t = new Sequence<Vertex>();
		s.addElements(q.getSequence());
		t.addElements(r.getSequence());
		VertexRefinedBySequence v = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, s, comp); //!!! look up to avoid using redundant instances (performance decrease)
		VertexRefinedBySequence w = (VertexRefinedBySequence) SequenceESGUtilities.getVertexByVertexSequence(kp1ESG, t, comp); //!!! look up to avoid using redundant instances (performance decrease)
		boolean vnull = (v==null);
		boolean wnull = (v==null);
		if(vnull) {
			Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(s));
			v = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, s);
			kp1ESG.addVertex(v);
		}
		if(wnull) {
			Event e = new EventSimple(kp1ESG.getNextEventID(), VertexSequenceUtilities.getStringFormWithContextedEvents(t));
			w = new VertexRefinedBySequence(kp1ESG.getNextVertexID(), e, t);
			kp1ESG.addVertex(w);
		}
		if(!(vnull || wnull)) {
			Edge e = SequenceESGUtilities.getEdgeByVertexSequences(kp1ESG, v.getSequence(), w.getSequence(), comp);
			if(e == null) {
				e = new EdgeSimple(kp1ESG.getNextEdgeID(), v, w);
				kp1ESG.addEdge(e);
//				System.out.println("   "+kESGName+" pseudo-1 "+e+" - "+q+","+r);
			}
		}
		else {
			Edge e = new EdgeSimple(kp1ESG.getNextEdgeID(), v, w);
			kp1ESG.addEdge(e);
//			System.out.println("   "+kESGName+" pseudo-2 "+e+" - "+q+","+r);
		}
	}
	
	public boolean isPseudoUsefulnessStart(Vertex v) {
		return (v.getEvent().getName().equals(TestSequenceCompositionUtilities.USEFULNESS_START)); //!!!
	}
	
	public boolean isPseudoUsefulnessFinish(Vertex v) {
		return (v.getEvent().getName().equals(TestSequenceCompositionUtilities.USEFULNESS_FINISH)); //!!!
	}
	
	public boolean isPseudoUsefulnessStart(VertexRefinedBySequence v) {
		return (v.getSequence().getSize()==1) && 
				(v.getSequence().getElement(0).getEvent().getName().equals(TestSequenceCompositionUtilities.USEFULNESS_START)); //!!!
	}
	
	public boolean isPseudoUsefulnessFinish(VertexRefinedBySequence v) {
		return (v.getSequence().getSize()==1) && 
				(v.getSequence().getElement(0).getEvent().getName().equals(TestSequenceCompositionUtilities.USEFULNESS_FINISH)); //!!!
	}
}
