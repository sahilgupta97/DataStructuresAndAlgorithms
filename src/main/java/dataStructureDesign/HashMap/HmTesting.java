package dataStructureDesign.HashMap;

public class HmTesting {

  public static void main(String[] args) {
    HashMap<String, Integer> map = new HashMap<>();
    map.put("sahil", 10);
    map.put("priya", 25);
    map.put("mummy", 100);
    System.out.println(map.size());

    map.put("random", 1);
    map.put("xyz", 2);

    map.put("sahil", 333);
    System.out.println(map.get("sahil"));
    map.remove("mummy");
    map.remove("invalid");
    System.out.println(map.size());
    System.out.println(map.containsKey("priya"));
    System.out.println(map.containsKey("nofound"));

  }
}
