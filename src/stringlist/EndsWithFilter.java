package stringlist;

public class EndsWithFilter implements Filter {

    private StringList sl;
    private String pattern;

    public EndsWithFilter(StringList sl, String pattern) {
        this.sl = sl;
        this.pattern = pattern;
    }

    public int[] test() {
        int[] temp = new int[sl.size()];
        int count = 0;
        for (int i = 0; i < sl.size(); i++) {
            String element = sl.get(i);
            if (element.endsWith(pattern)) {
                temp[count++] = i;
            }
        }
        int[] rv = new int[count];
        for (int i = 0; i < count; i++) {
            rv[i] = temp[i];
        }
        return rv;
    }

}
