import java.util.HashMap;
import java.util.Map;

public class Statistic {
    private Map<String, Integer> voiceMap = new HashMap<>();
    private static Statistic stat = new Statistic();
    private Statistic() {
        super();
    }
    public static Statistic getInstance() {
        return stat;
    }

    public synchronized void addVoice(String voice) {
        if (voiceMap.containsKey(voice)) {
            Integer voiceCount = voiceMap.get(voice);
            voiceMap.put(voice, voiceCount + 1);
        } else {
            voiceMap.put(voice, 1);
        }
    }

    public synchronized String toString() {
        StringBuilder sb = new StringBuilder();
        voiceMap.forEach((key, value) ->
                sb.append(key).append("\t").append(value).append("<br>"));
        return sb.toString();
    }
}
