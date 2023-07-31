//Victor Ericson vier1798

import java.util.*;

public class HuffmanCoder {

    public EncodedMessage<?, ?> encode(String message) {


        Map<Character, Integer> frequency = new HashMap<>();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (frequency.containsKey(c)) {
                int count = frequency.get(c);
                frequency.put(c, count + 1);
            } else {
                frequency.put(c, 1);
            }
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            queue.offer(new Node(entry.getKey(), entry.getValue()));
        }

        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();

            /*
            den nya frekvensen sätts i konstruktorn
             */
            Node parent = new Node(left, right);
            queue.offer(parent);

        }

        Node root = queue.poll();

        Map<Character, String> codes = new HashMap<>();
        root.generateCodes("", codes);

        StringBuilder sb = new StringBuilder();

        for (char c : message.toCharArray()) {
            sb.append(codes.get(c));
        }
        return new EncodedMessage<>(root, sb.toString());
    }

    public String decode(EncodedMessage<?, ?> message) {

        Node root = (Node) message.header;

        String code = (String) message.message;

        StringBuilder sb = new StringBuilder();
        Node node = root;
        for (char c : code.toCharArray()) {
            if (c == '0') {
                node = node.left;
            } else {
                node = node.right;
            }
            if (node.isLeaf()) {
                sb.append(node.ch);
                node = root;
            }
        }
        return sb.toString();
    }


    private static class Node implements Comparable<Node> {
        private char ch;
        private int frequency;
        private Node left;
        private Node right;

        Node(char ch, int frequency) {
            this.ch = ch;
            this.frequency = frequency;
        }

        Node(Node left, Node right) {
            this.frequency = left.frequency + right.frequency;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public void generateCodes(String code, Map<Character, String> codes) {
            if (isLeaf()) {
                codes.put(ch, code);
            } else {
                left.generateCodes(code + "0", codes);
                right.generateCodes(code + "1", codes);
            }
        }

        public int compareTo(Node other) {
            return frequency - other.frequency;
        }
    }
}
