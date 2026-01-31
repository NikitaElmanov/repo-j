package patterns.generative.prototype2;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Page implements CopyInterface {

    private int number;
    private int width;
    private String text;

    @Override
    public Object copy() {
        return new Page(this.number, this.width, this.text);
    }
}
