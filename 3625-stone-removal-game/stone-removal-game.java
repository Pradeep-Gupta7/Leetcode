class Solution {
    public boolean canAliceWin(int n) {
        int take = 10;
        boolean aliceTurn = true;

        while (take > 0 && n >= take) {
            n -= take;
            take--;
            aliceTurn = !aliceTurn;
        }

        // The player whose turn it is now cannot move, so they lose
        return !aliceTurn;
    }
}