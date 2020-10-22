package col106.assignment4.HashMap;

public class WordCounter {


	public WordCounter(){
	}

	public int count(String str, String word){
        int len = str.length();
        int w_len = word.length();
        String key = "";
        HashMap<Integer> map = new HashMap<>(len+1);

        for (int i = 0; i < (len - w_len + 1); i++)
        {
            key = str.substring(i, i + w_len);
            if (map.contains(key))
            {
                int count = map.get(key);
                map.put(key, count+1);
            }
            else
            {
                map.put(key, 1);
            }
        }
        int count = 0;
        if (map.get(word) != null)
            count = map.get(word);

		return count;
	}
}
