class Solution {
    
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int bestIdx = -1;       
        int bestLen = Integer.MAX_VALUE;
    }
    
    private TrieNode root;
    private String[] wordsContainer;
    
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        this.wordsContainer = wordsContainer;
        root = new TrieNode();
        
        
        for (int i = 0; i < wordsContainer.length; i++) {
            updateNode(root, i);
        }
        
        
        for (int i = 0; i < wordsContainer.length; i++) {
            insert(wordsContainer[i], i);
        }
        
        int[] ans = new int[wordsQuery.length];
        for (int q = 0; q < wordsQuery.length; q++) {
            ans[q] = search(wordsQuery[q]);
        }
        
        return ans;
    }
    
    private void insert(String word, int idx) {
        TrieNode node = root;
        
        for (int i = word.length() - 1; i >= 0; i--) {
            int c = word.charAt(i) - 'a';
            if (node.children[c] == null) {
                node.children[c] = new TrieNode();
            }
            node = node.children[c];
            updateNode(node, idx);
        }
    }
    
    private void updateNode(TrieNode node, int idx) {
        int len = wordsContainer[idx].length();
        if (len < node.bestLen) {
            node.bestLen = len;
            node.bestIdx = idx;
        }
        
    }
    
    private int search(String query) {
        TrieNode node = root;
        int bestIdx = root.bestIdx;
        
        for (int i = query.length() - 1; i >= 0; i--) {
            int c = query.charAt(i) - 'a';
            if (node.children[c] == null) break;
            node = node.children[c];
            bestIdx = node.bestIdx; 
        }
        
        return bestIdx;
    }
}