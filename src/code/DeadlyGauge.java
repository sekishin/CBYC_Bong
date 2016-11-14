package code;

public class DeadlyGauge {
    private static int increment_gauge = 30;  // 1回で増えるゲージ量
    protected static int gauge_max = 300;  // ゲージの最大値

    // 必殺ゲージの上昇
    public static int gaugeUp(int current_gauge) {
        if ( current_gauge < gauge_max ) {
            current_gauge += increment_gauge;
        }
        return current_gauge;
    }

    // 必殺ゲージのリセット
    public int gaugeReset(int current_gauge) {
        current_gauge = 0;
        return current_gauge;
    }
}
