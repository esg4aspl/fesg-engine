package tr.edu.iyte.fesg.testsequencecomposition;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.esg.model.Vertex;

public class FullTestSequenceComposer {
	private Map<String,Set<EventSequence>> esgSequenceMap = null;
	private Set<String> startVertexEventNames = null;
	private Set<String> finishVertexEventNames = null;

	private int k = 2;
	private Set<String> coveredSequences = null;
	private Set<EventSequence> startSequences = null;
	
	private Set<EventSequence> completeSequences = null;
	
	public FullTestSequenceComposer(Map<String,Set<EventSequence>> esgSequenceMap, Set<String> startVertexEventNames, Set<String> finishVertexEventNames, int k) {
		this.esgSequenceMap = esgSequenceMap;
		this.startVertexEventNames = startVertexEventNames;
		this.finishVertexEventNames = finishVertexEventNames;
		this.k = k;
	}
	
	public void compose() {
		this.coveredSequences = new LinkedHashSet<String>();
		initializeStartSequences();
		
		boolean notfinished = true;
		while(notfinished) {
			notfinished = updateStartSequences();
		}
		
		initializeCompleteSequences();
		notfinished = true;
		while(notfinished) {
			notfinished = updateCompleteSequences();
		}
	}

	public Set<EventSequence> getCompleteSequences() {
		return this.completeSequences;
	}
	
	private void initializeStartSequences() {
		this.startSequences = new LinkedHashSet<EventSequence>();
		Iterator<String> kitr = this.esgSequenceMap.keySet().iterator();
		while(kitr.hasNext()) {
			String key = kitr.next();
			transferStartSequences(this.esgSequenceMap.get(key));
		}
	}
	
	private void transferStartSequences(Set<EventSequence> sequences) {
		Iterator<EventSequence> itr = sequences.iterator();
		while(itr.hasNext()) {
			EventSequence es = itr.next();
//System.out.println("transferStartSequences " + es.toString());
			if(isStartSequence(es)) {
				
				EventSequence es_flat = TestSequenceCompositionUtilities.flatten(es);
//System.out.println("FLATTEN " + es_flat);
				if(increasesCoverage(es_flat, 0, es_flat.getEventSequence().size()-1)) {
					this.startSequences.add(es_flat);
				}
				itr.remove();
			}
		}
	}
	
	private boolean updateStartSequences() {
		boolean notfinished = false;
		Iterator<String> kitr = this.esgSequenceMap.keySet().iterator();
		while(kitr.hasNext()) {
			String key = kitr.next();
//System.out.println("Current ESG " + key);
			Set<EventSequence> sequences = this.esgSequenceMap.get(key);
			boolean c = connectWithStartSequences(sequences);
			notfinished = notfinished || c;
		}
		return notfinished;
	}

	private boolean connectWithStartSequences(Set<EventSequence> sequences) {
		boolean connected = false;
		Iterator<EventSequence> itr = sequences.iterator();
//System.out.println(itr.hasNext());
		while(itr.hasNext()) {
			EventSequence seq = itr.next();
//System.out.println("   seq: " + seq);
			Object[] seq_index = findConnectionPointToSucceedAPartialStartSequence(seq);
			if(seq_index != null) {
				EventSequence ss = (EventSequence)seq_index[0];
//System.out.println("   ss: " + ss);
				int index = (int)seq_index[1];
//System.out.println("INDEX " + index);
				EventSequence seq_new = TestSequenceCompositionUtilities.connectToSucceedFlattened(seq, 0, ss, index);
//System.out.println("   --- connectWithStartSequences - iteration ---");
//System.out.println("   start sequences (before): " ); this.startSequences.forEach(sseq -> System.out.println(sseq));
//System.out.println("   covered sequences (before): "); this.coveredSequences.forEach(cseq -> System.out.println(cseq));

//System.out.println("   seq_new: " + seq_new);
				if(increasesCoverage(seq_new, 0, seq_new.getEventSequence().size()-1)) {
					if(index == ss.getEventSequence().size()-1) {
						this.startSequences.remove(ss);
					}
					this.startSequences.add(seq_new);
					connected = true;
//System.out.println("   start sequences (after): "); this.startSequences.forEach(sseq -> System.out.println(sseq));
//System.out.println("   covered sequences (after): "); this.coveredSequences.forEach(cseq -> System.out.println(cseq));

				}
				itr.remove();
//System.out.println("   ---");
			}
		}
		return connected;
	}

	private void initializeCompleteSequences() {
		this.completeSequences = new LinkedHashSet<EventSequence>();
		Iterator<EventSequence> itr = this.startSequences.iterator();
		while(itr.hasNext()) {
			EventSequence seq = itr.next();
			if(isFinishSequence(seq)) {
				this.completeSequences.add(seq);
				itr.remove();
			}
		}
	}
	
	private boolean updateCompleteSequences() {
		boolean notfinished = false;

		Iterator<EventSequence> itr = this.startSequences.iterator();
		while(itr.hasNext()) {
			EventSequence seq = itr.next();

			Object[] seq_index = findConnectionPointToPreceedAPartialCompleteSequence(seq);
			if(seq_index != null) {
				EventSequence cs = (EventSequence)seq_index[0];
//System.out.println("cs: " + cs);
				int index = (int)seq_index[1];
//System.out.println("INDEX: " + index);
//System.out.println("   --- updateCompleteSequences - iteration ---");
//System.out.println("   start sequences (before): " ); this.startSequences.forEach(sseq -> System.out.println(sseq));
//System.out.println("   complete sequences (before): "); this.completeSequences.forEach(cseq -> System.out.println(cseq));
//				EventSequence seq_new = TestSequenceCompositionUtilities.connectToSucceedFlattened(cs, index, seq, seq.getEventSequence().size()-1);
				EventSequence seq_new = TestSequenceCompositionUtilities.connectToSucceed(cs, index, seq, seq.getEventSequence().size()-1);
//System.out.println("seq: " + seq);
//System.out.println("seq_new:" + seq_new);
				this.completeSequences.add(seq_new);
				itr.remove();
				notfinished = true;
//System.out.println("   start sequences (after): "); this.startSequences.forEach(sseq -> System.out.println(sseq));
//System.out.println("   complete sequences (after): "); this.completeSequences.forEach(cseq -> System.out.println(cseq));
//System.out.println("-------------------------------");
			}
		}
		return notfinished;
	}

	private Object[] findConnectionPointToSucceedAPartialStartSequence(EventSequence seq) {
		Vertex v1 = seq.getEventSequence().get(0);
//System.out.println("v1" + v1);
//System.out.println("v1.getEvent().getName() " + v1.getEvent().getName());
		String[] esg_ve1 = TestSequenceCompositionUtilities.splitVertexEventName(v1.getEvent().getName()); //!!!
//System.out.println("esg_ve1[0] "  + esg_ve1[0]);
//System.out.println("esg_ve1[1] "  + esg_ve1[1]);
		/*Object[] seq_index findConnectionPoint_FirstSequence_EndPoint(seq, esg_ve1[1], this.startSequences);
		if(seq_index == null) {
			seq_index = findConnectionPoint_FirstSequence_FirstPoint(seq, esg_ve1[1], this.startSequences);
		}*/
		Object[] seq_index = TestSequenceCompositionUtilities.findSequenceAndIndexByEventName_FirstSequence_FirstPoint(seq, esg_ve1[1], this.startSequences);
//System.out.println("seq_index is null " + seq_index == null);
		return seq_index;
	}
	
	private Object[] findConnectionPointToPreceedAPartialCompleteSequence(EventSequence seq) {
		Vertex v1 = seq.getEventSequence().get(seq.getEventSequence().size()-1);
		//String[] esg_ve1 = splitPair(v1.getEvent().getName(), "(,)"); //!!!
		Object[] seq_index = TestSequenceCompositionUtilities.findSequenceAndIndexByEventName_FirstSequence_LastPoint(seq, v1.getEvent().getName(), this.completeSequences);
		return seq_index;
	}
	
	private boolean isStartSequence(EventSequence seq) {
		return this.startVertexEventNames.contains(seq.getEventSequence().get(0).getEvent().getName());
	}
	
	private boolean isFinishSequence(EventSequence seq) {
		return this.finishVertexEventNames.contains(seq.getEventSequence().get(seq.getEventSequence().size()-1).getEvent().getName());
	}
	
	private boolean increasesCoverage(EventSequence seq, int start, int end) {
		int l = end-start+1;
		if(l >= this.k) {
			return increasesCoverage(seq, start, end, this.k);
		}
		else {
			return increasesCoverage(seq, start, end, l);
		}
	}
	
	private boolean increasesCoverage(EventSequence seq, int start, int end, int k) {
		boolean increases = false;
		List<Vertex> l = seq.getEventSequence();
		int last_i = end - k + 1;
		for(int i = start; i <= last_i; i++) {
			ListIterator<Vertex> itr = l.listIterator(i);
			StringBuilder sb = new StringBuilder();
			sb.append(itr.next().getEvent().getName());
			for(int j = 1; j < k; j++) {
				sb.append(":"); //!!!
				sb.append(itr.next().getEvent().getName());
			}
			boolean added = this.coveredSequences.add(sb.toString());
			increases = increases || added;
		}
		return increases;
	}
}
