package t22.day13.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.process("src/main/resources/day13-sample");
    }

    private void process(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));

        AtomicInteger index = new AtomicInteger(0);
        Map<Integer, List<String>> pairsGroup = lines.stream().filter(l -> !l.isBlank()).collect(Collectors.groupingBy(l -> index.getAndIncrement() / 2));
        int pairCount = pairsGroup.size();
        for (int i = 0; i < pairCount; i++) {
            List<String> pairs = pairsGroup.get(i);
            String leftPacket = pairs.get(0);
            String rightPacket = pairs.get(1);
            //[[4,4],4,4]
            LinkedList leftList = new LinkedList();
            LinkedList rightList = new LinkedList();
            mapStringToColl(leftPacket, leftList, 0);
            mapStringToColl(rightPacket, rightList, 0);
            boolean isRightOrder = compareLeftRight((LinkedList) leftList.get(0), (LinkedList) rightList.get(0));
        }

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

    private boolean compareLeftRight(LinkedList leftPacket, LinkedList rightPacket) {

        for (int i = 0; i < leftPacket.size(); i++) {
            Object left = leftPacket.get(i);
            Object right = rightPacket.get(i);
            LinkedList leftList;
            LinkedList rightList;
            if (left instanceof Integer && right instanceof Integer) {
                int leftVal = (Integer) left;
                int rightVal = (Integer) right;

                if (leftVal > rightVal)
                    return false;
            } else {
                return compareLeftRightList(left, right);
            }

        }

        return true;
    }

    private boolean compareLeftRightList(Object left, Object right) {
        LinkedList rightList;
        LinkedList leftList;
        if (left instanceof Integer && right instanceof List) {
            leftList = convertIntToList(left);
            rightList = (LinkedList) right;
        } else if (left instanceof List && right instanceof Integer) {
            rightList = convertIntToList(right);
            leftList = (LinkedList) left;
        } else {
            leftList = (LinkedList) left;
            rightList = (LinkedList) right;
        }


        if (leftList.size() > rightList.size())
            return false;

        return compareLeftRight(leftList, rightList);
    }

    private LinkedList<Integer> convertIntToList(Object val) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add((Integer) val);
        return list;
    }
}