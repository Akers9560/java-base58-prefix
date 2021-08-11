public class Tools {

    public static final int INDEX_NOT_FOUND = -1;

    public static int indexOf(CharSequence[] array, CharSequence value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(value)) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

}
