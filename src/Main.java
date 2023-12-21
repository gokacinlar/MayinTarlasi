public class Main {
    public static void main(String[] args) {

        /**
         * Java dilinde yazılmış olan bir mayın tarlası oyunudur.
         * Yazar: @gokacinlar (with help of dear friend GPT 3.5 (:
         * Kullanım: Repoyu git clone ile klonlayarak, IntelliJ IDE'sinde açabilirsiniz.
         */


        MineSweeper game = new MineSweeper(3, 3);
        game.printMineLocations();
        game.playGame();
    }
}