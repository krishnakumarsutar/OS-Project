import java.lang.*;

public class combo {
    private String key;
    private Integer value1;

    public combo(String key, Integer value) {
        this.key = key;
        this.value1 = value;
    }

    @Override
    public String toString()
    {
        return key;
    }

    public String getKey(){
        return key;
    }

    public int getValue(){
        return value1;
    }
}
