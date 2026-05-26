package tetris;

import java.util.ArrayList;
import java.util.Random;

/**
 *  一个 Tetris 精确的 Tetromino 随机化器。
 *  这防止相同的 Tetromino 形状连续多次出现。
 *
 *  @author Erik Nelson
 */

public class BagRandomizer {

  private Random random;

  // "袋子"中当前值的列表。
  ArrayList<Integer> values;

  // 袋子的总容量。
  int capacity;

  public BagRandomizer(Random r, int n) {
    this.random = r;
    this.capacity = n;

    refillValues();
  }

  /**
   * 重置袋子的值，使其包含从 0（包含）到 capacity（不包含）的整数。
   */
  private void refillValues() {
    ArrayList<Integer> newValues = new ArrayList<>();
    for (int i = 0; i < this.capacity; i++) {
      newValues.add(i);
    }
    values = newValues;
  }

  /**
   * 从袋子中获取并移除一个随机项。如果袋子为空，则重新填充。
   * @return 移除的整数
   */
  public int getValue() {
    if (values.isEmpty()) {
      refillValues();
    }

    int randomIndex = random.nextInt(values.size());
    int randomValue = values.get(randomIndex);

    values.remove(randomIndex);
    return randomValue;
  }
}
