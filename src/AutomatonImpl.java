import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class AutomatonImpl implements Automaton {

    class StateLabelPair {
        int state;
        char label;
        public StateLabelPair(int state_, char label_) { state = state_; label = label_; }

        @Override
        public int hashCode() {
            return Objects.hash((Integer) state, (Character) label);
        }

        @Override
        public boolean equals(Object o) {
            StateLabelPair o1 = (StateLabelPair) o;
            return (state == o1.state) && (label == o1.label);
        }
    }

    HashSet<Integer> start_states;
    HashSet<Integer> accept_states;
    HashSet<Integer> current_states;
    HashMap<StateLabelPair, HashSet<Integer>> transitions;

    public AutomatonImpl() {
        start_states = new HashSet<Integer>();
        accept_states = new HashSet<Integer>();
        transitions = new HashMap<StateLabelPair, HashSet<Integer>>();
        current_states = new HashSet<Integer>();
    }

    @Override
    public void addState(int s, boolean is_start, boolean is_accept) {
        // TODO Auto-generated method stub
        if(is_start) {
            start_states.add(s);
        }
        if(is_accept) {
            accept_states.add(s);
        }
    }

    @Override
    public void addTransition(int s_initial, char label, int s_final) {
        // TODO Auto-generated method stub
        StateLabelPair SLP = new StateLabelPair(s_initial, label);
        transitions.computeIfAbsent(SLP, slp -> new HashSet<>()).add(s_final);
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        current_states.clear();
        current_states.addAll(start_states);
    }

    @Override
    public void apply(char input) {
        // TODO Auto-generated method stub
    HashSet<Integer> c_sates = new HashSet<>();
    int index = 0;
    Object[] statesArray = current_states.toArray();
    while (index < statesArray.length) {
    int a = (Integer) statesArray[index];
    StateLabelPair SLP = new StateLabelPair(a, input);
    c_sates.addAll(transitions.getOrDefault(SLP, new HashSet<>()));
    index++;
    }
    current_states = c_sates;

    }

    @Override
    public boolean accepts() {
        // TODO Auto-generated method stub
    Object[] statesArray = current_states.toArray();
    int index = 0;
    while (index < statesArray.length) {
    int a = (Integer) statesArray[index];
    if (accept_states.contains(a)) {
        return true;
    }
    index++;
    }

        return false;
    }

    @Override
    public boolean hasTransitions(char label) {
        // TODO Auto-generated method stub
    int index = 0;
    Object[] statesArray = current_states.toArray();
    while (index < statesArray.length) {
    int a = (Integer) statesArray[index];
    StateLabelPair SLP = new StateLabelPair(a, label);
    if (transitions.containsKey(SLP)) {
        return true;
    }
    index++;
    }
        return false;
    }  
}
