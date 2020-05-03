package col106.assignment4.HashMap;
import java.util.Vector;

public class HashMap<V> implements HashMapInterface<V> {

    int size;
    Vector<String> keys;
    Vector<V> values;

	public HashMap(int s) {
        size = s;
        keys = new Vector<>(size);
        values = new Vector<>(size);
        for (int i = 0; i < size; i++)
        {
            keys.add(null);
            values.add(null);
        }
        //System.out.println("Size: " + values.size());
	}

    public int hash(String key)
    {
        long hash = 0;
        int l = key.length();

        for (int i = 0; i < l; i++)
            hash = hash + (long)Math.pow(41, i) * key.charAt(i);
            //hash = 41 * (hash + key.charAt(i));
        //System.out.println("Index: " + (int)(hash % (long) size));
        //System.out.println("Hash: " + hash);

        return (int)(Map.abs(hash) %(long) size); 
    }


	public V put(String key, V value){
        //System.out.println("key: " + key);
        int index = hash(key);
        V old_val = null;

        for (int i = 0; i < size; i++)
        {
            if (values.get(index) != null)
            {
                if (key.compareTo(keys.get(index)) == 0)
                {
                    old_val = values.get(index);
                    values.set(index, value);
                    break;
                }
                index++;
                index = index % size;
            }
            else
            {
                keys.set(index, key);
                values.set(index, value);
                break;
            }
        }

		return old_val;
	}

	public V get(String key){
        int index = hash(key);
        
        for (int i = 0; i < size; i++)
        {
            if (values.get(index) != null)
            {
                if (keys.get(index).compareTo(key) == 0)
                    return values.get(index);
                else
                    index++;
            }
            else
                return null;

            index = index % size;
        }
		return null;
	}

	public boolean remove(String key){
        int hash = hash(key);
        int index = hash;
        for (int i = 0; i < size; i++)
        {
            if (values.get(index) != null)
            {
                if (keys.get(index).compareTo(key) == 0)
                {
                    values.set(index, null);
                    keys.set(index, null);
                    invariant(index, key);
                    return true;
                }
                else
                    index++;
            }
            else
                return false;
            index = index % size;
        }

		return false;
	}

    private void invariant(int index, String key)
    {
        int hash = hash(key);
        int index2 = index + 1;
        for (int i = 0; i < size - 1; i++)
        {
            //System.out.println("index1: " + index + " index2: " + index2);
            index2 = index2 % size;
            if (keys.get(index2) != null)
            {
                if (hash(keys.get(index2)) == hash)
                {
                    //System.out.println("swapped");
                    swap (index2, index);
                    index = index2;
                }
            }
            index2  = index2 + 1;
        }
    }
/*
 *    {
 *        int index2 = (index+1) % size;
 *        String key = keys.get(index2);
 *        for (int i = 0; i < size; i++)
 *        {
 *            key = keys.get(index2);
 *            if (key != null && hash(key) == hash)
 *            {
 *                swap(index2, index);
 *                index = index2;
 *                index2++;
 *            }
 *            else
 *                index2++;
 *            index2 = index2 % size;
 *        }
 *    }
 *
 */
    private void swap(int s, int t)
    {
        String str;
        V val;
        str = keys.get(s);
        val = values.get(s);
        keys.set(s, keys.get(t));
        values.set(s, values.get(t));
        keys.set(t, str);
        values.set(t, val);
    }

	public boolean contains(String key){
        int index = hash(key);
        if (keys.contains(key))
            return true;
        else
            return false;
	}

	public Vector<String> getKeysInOrder(){
        Vector<String> arr = new Vector<String>();
        for (int i = 0; i < size; i++)
        {
            if(keys.get(i) != null)
            {
                arr.add(keys.get(i));
            }
        }
		return arr;
	}
}
