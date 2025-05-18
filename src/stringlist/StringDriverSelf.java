package stringlist;

public class StringDriverSelf {

        public static void main(String[] args) {
            StringList list = new StringList();
            list.add("Apple");
            list.add("Banana");
            list.add("Apricot");
            list.add("Blueberry");
            list.add("Avocado");

            System.out.println("Start of Test 1" );
            System.out.println(list.toString());
            System.out.println("Size of List: " + list.size());
            list.grow();
            System.out.println("Size of List after grow: " + list.size());





            ContainsFilter containsFilterAp = new ContainsFilter(list,"Ap");
            list.removeIf(containsFilterAp);
            System.out.println("After filter ap: \n" + list.toString());
            containsFilterAp = new ContainsFilter(list,"a");
            list.removeIf(containsFilterAp);
            System.out.println("After filter a: \n" + list.toString());

//            list.removeIf("A");

//            System.out.println("Start test toArray");
//            String[]list2 = list.toArray();
//            for (int i = 0; i < list2.length; i++) {
//                System.out.println(list2[i]);
//            }
//            System.out.println("end of array");
//
//            System.out.println(list.lastIndexOf("Blueberry"));

//            System.out.println("Size should be 5, and actual size:" + list.size());
//            System.out.println("Test index of Banana: "+ list.indexOf("Banana"));
//            System.out.println("End of Test 1" );
//            list.set(1,"strawberry");
//            System.out.println("Test index of strawberry after reset: "+ list.indexOf("strawberry"));
//            list.set(5,"strawberry");
//
//            System.out.println("Start of Test 2" );
//            System.out.println(list.toString());
//            System.out.println(list.subList(2,5).toString());
//            list.removeRange(0,3);
//            System.out.println(list.toString());
//            list.removeRange(2,2);


//             使用 StartsWithFilter 過濾以 "A" 開頭的字串
//            Filter startsWithA = new StartsWithFilter(list, "A");
//            System.out.println("StartsWithFilter result: ");
//            for (int index : startsWithA.test()) {
//                System.out.println("  - " + list.get(index));
//            }
//
//            // use EndsWithFilter filter the string end with "a"
//            Filter endsWithA = new EndsWithFilter(list, "a");
//            System.out.println("EndsWithFilter result: ");
//            for (int index : endsWithA.test()) {
//                System.out.println("  - " + list.get(index));
//            }
//
//            // Use ContainsFilter filter elements contain "oo"
//            Filter containsO = new ContainsFilter(list, "oo");
//            System.out.println("ContainsFilter result: ");
//            for (int index : containsO.test()) {
//                System.out.println("  - " + list.get(index));
//            }
//
//            // Use removeIf with StartsWithFilter to remove the String begin with "A".
//            list.removeIf(startsWithA);
//            System.out.println("移除以 A 開頭後的清單: " + list);
        }


}
