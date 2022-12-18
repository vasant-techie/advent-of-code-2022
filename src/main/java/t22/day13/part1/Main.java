package t22.day13.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {

    private Set<Integer> pairIndexesRightOrder = new HashSet<>();
    private int leftSize;
    private int rightSize;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.process("src/main/resources/day13");
    }

    private void process(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));

        AtomicInteger index = new AtomicInteger(0);
        Map<Integer, List<String>> pairsGroup = lines.stream().filter(l -> !l.isBlank()).collect(Collectors.groupingBy(l -> index.getAndIncrement() / 2));
        int pairCount = pairsGroup.size();
        for (int pairIndex = 0; pairIndex < pairCount; pairIndex++) {
            List<String> pairs = pairsGroup.get(pairIndex);
            String leftPacket = pairs.get(0);
            String rightPacket = pairs.get(1);
            LinkedList leftList = new LinkedList();
            LinkedList rightList = new LinkedList();

            mapStringToColl(leftPacket, leftList, 0);
            mapStringToColl(rightPacket, rightList, 0);

            System.out.println("== Pair " + (pairIndex + 1) + " ==");
            //Left side ran out of items, so inputs are in the right order
            LinkedList leftFirst = (LinkedList) leftList.get(0);
            LinkedList rightFirst = (LinkedList) rightList.get(0);
            if (leftFirst.size() == 0 && rightFirst.size() > 0) {
                System.out.println("Pair Index " + (pairIndex + 1) + ". Left side ran out of items, so inputs are in the right order");
                pairIndexesRightOrder.add((pairIndex + 1));
                continue;
            }
            this.leftSize = leftFirst.size();
            this.rightSize = rightFirst.size();
            System.out.println("Pair: " + (pairIndex + 1) + ", Left Size: " + this.leftSize + ", Right: " + this.rightSize);
            int result = compareLeftRightList(leftList, rightList, pairIndex, new StringBuilder());
            if (result == 1) {
                System.out.println("Pair Index in Right Order: " + (pairIndex + 1));
                pairIndexesRightOrder.add((pairIndex + 1));
            }
        }
        int totalRightOrder = pairIndexesRightOrder.stream().reduce((x, y) -> x + y).orElse(0);
        System.out.println("#### Final Total Count of Right Order is: " + totalRightOrder);

    }

    private static int getLastIndex(String leftPacket) {
        return leftPacket.lastIndexOf("]");
    }

    private int mapStringToColl(String packet, LinkedList list, int index) {
        StringBuilder sb = new StringBuilder();
        for (int i = index; i < packet.length(); i++) {
            char c = packet.charAt(i);
            if (c == ',') {
                if (!sb.toString().isEmpty()) {
                    list.add(Integer.parseInt(sb.toString()));
                    sb = new StringBuilder();
                }
            } else if (c == ']') {
                if (!sb.toString().isEmpty())
                    list.add(Integer.parseInt(sb.toString()));
                return i;
            } else if (c == '[') {
                LinkedList subList = new LinkedList();
                i = mapStringToColl(packet, subList, i + 1);
                list.add(subList);
            } else {
                sb.append(c);
            }
        }
        return 0;
    }

    private int compareLeftRightList(LinkedList leftList, LinkedList rightList, int pairIndex, StringBuilder strBuilder) {
        boolean isAllEqual = true;
        int result = 0;
        System.out.println(strBuilder.toString() + "- Pair Index: " + pairIndex + ". Compare " + leftList + " vs " + rightList);

        for (int i = 0; i < rightList.size(); i++) {

            if (i >= leftList.size()) {
                break;
            }

            Object left = leftList.get(i);
            Object right = rightList.get(i);

            if (left instanceof Integer && right instanceof Integer) {
                int leftVal = (Integer) left;
                int rightVal = (Integer) right;

                System.out.println(strBuilder + "- Compare " + leftVal + " vs " + rightVal);

                if (leftVal > rightVal) {
                    System.out.println(strBuilder.append(" ") + "- Right side is smaller, so inputs are not in the right order");
                    isAllEqual = false;
                    result = -1;
                    break;
                } else if (rightVal > leftVal) {
                    isAllEqual = false;
                    System.out.println(strBuilder.append(" ") + "- Left side is smaller, so inputs are in the right order");
                    result = 1;
                    break;
                }
            } else if (left instanceof Integer && right instanceof List) {
                leftList = convertIntToList(left);
                result = compareLeftRightList(leftList, new LinkedList((LinkedList) right), pairIndex, strBuilder.append(" "));
                if (result == -1 || result == 1) {
                    isAllEqual = false;
                    break;
                }
            } else if (left instanceof List && right instanceof Integer) {
                rightList = convertIntToList(right);
                result = compareLeftRightList(new LinkedList((LinkedList) left), rightList, pairIndex, strBuilder.append(" "));
                if (result == -1 || result == 1) {
                    isAllEqual = false;
                    break;
                }
            } else if (left instanceof List && right instanceof List) {
                result = compareLeftRightList(new LinkedList((LinkedList) left), new LinkedList((LinkedList) right), pairIndex, strBuilder.append(" "));
                if (result == -1 || result == 1) {
                    isAllEqual = false;
                    break;
                }
            }
        }

        if (isAllEqual == true) {
            if (rightList.size() < leftList.size()) {
                System.out.println(strBuilder.append(" ") + "- Right side ran out of items, so inputs are not in the right order");
                result = -1;
            } else if (rightList.size() == 0 && leftList.size() == 0) {
                System.out.println("## isEqual 0 ##");
                result = 0;
            } else if (rightList.size() > leftList.size()) {
                System.out.println(strBuilder.append(" ") + "- Left side ran out of items, so inputs are in the right order");
                result = 1;
            }
        }
        return result;
    }

    private LinkedList<Integer> convertIntToList(Object val) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add((Integer) val);
        return list;
    }
}
